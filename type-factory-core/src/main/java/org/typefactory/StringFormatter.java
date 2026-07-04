/*
   Copyright 2021-2022 Evan Toliopoulos (typefactory.org)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package org.typefactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * A fluent wrapper around {@link StringBuilder}.
 *
 * <p>The wrapper keeps call chains alive for conditional and repeated appends.
 * For the low-level behavior of the wrapped operations, refer to the matching
 * {@link StringBuilder} methods.
 */
public final class StringFormatter implements CharSequence, Comparable<StringFormatter> {

  private static final String LINE_SEPARATOR = System.lineSeparator();
  private static final char[] SPACES = "                                                                                ".toCharArray();

  private final StringBuilder delegate;
  private Locale locale;
  private String nullText;

  /**
   * Creates a builder with default capacity and the default formatting locale.
   *
   * @see StringBuilder#StringBuilder()
   */
  public StringFormatter() {
    this(new StringBuilder(), Locale.getDefault(Locale.Category.FORMAT));
  }

  /**
   * Creates a builder with the supplied initial capacity and the default formatting locale.
   *
   * @param capacity the initial capacity
   * @return a new formatter
   * @see StringBuilder#StringBuilder(int)
   */
  public StringFormatter(final int capacity) {
    this(new StringBuilder(capacity), null);
  }

  /**
   * Creates a builder with default capacity and the supplied formatting locale.
   *
   * @param locale the formatting locale, or the default formatting locale when null
   * @return a new formatter
   * @see StringBuilder#StringBuilder()
   */
  public StringFormatter(final Locale locale) {
    this(new StringBuilder(), locale);
  }

  /**
   * Creates a builder with the supplied initial capacity and formatting locale.
   *
   * @param capacity the initial capacity
   * @param locale the formatting locale, or the default formatting locale when null
   * @see StringBuilder#StringBuilder(int)
   */
  public StringFormatter(final int capacity, final Locale locale) {
    this(new StringBuilder(capacity), locale);
  }

  /**
   * Creates a builder around the supplied delegate.
   *
   * @param delegate the wrapped string builder
   * @param locale the formatting locale
   */
  private StringFormatter(final StringBuilder delegate, final Locale locale) {
    this.delegate = delegate;
    this.locale = Objects.requireNonNullElse(locale, Locale.getDefault(Locale.Category.FORMAT));
    this.nullText = "";
  }

  /**
   * Sets the locale used for subsequent number formatting operations.
   *
   * @param locale the formatting locale, or the default formatting locale when null
   * @return this formatter
   * @see #append(int)
   * @see #append(long)
   * @see #append(float)
   * @see #append(double)
   */
  public StringFormatter setLocale(final Locale locale) {
    this.locale = Objects.requireNonNullElse(locale, Locale.getDefault(Locale.Category.FORMAT));
    return this;
  }

  /**
   * Formats future null values as empty strings.
   *
   * @return this formatter
   * @see #formatNullsAs(CharSequence)
   */
  public StringFormatter formatNullsAsEmptyString() {
    this.nullText = "";
    return this;
  }

  /**
   * Formats future null values as the supplied text.
   *
   * @param value the text to use when a null value is encountered
   * @return this formatter
   * @see #formatNullsAsEmptyString()
   */
  public StringFormatter formatNullsAs(final CharSequence value) {
    this.nullText = value == null ? "" : value.toString();
    return this;
  }

  /**
   * Returns the supplied value, or the current null replacement text when null.
   *
   * @param value the value to resolve
   * @return the value or the current null replacement text
   */
  private CharSequence nullSafe(final CharSequence value) {
    return value == null ? nullText : value;
  }

  /**
   * Invokes the consumer unconditionally.
   *
   * <p>See {@link StringBuilder} for the behavior of any delegated append calls.
   *
   * @param consumer the consumer to invoke
   * @return this formatter
   */
  public StringFormatter apply(final Consumer<StringFormatter> consumer) {
    Objects.requireNonNull(consumer, "consumer").accept(this);
    return this;
  }

  /**
   * Invokes the consumer when the condition is true.
   *
   * <p>See {@link StringBuilder} for the behavior of any delegated append calls.
   *
   * @param condition whether to invoke the consumer
   * @param consumer the consumer to invoke when the condition is true
   * @return this formatter
   */
  public StringFormatter when(
      final boolean condition,
      final Consumer<StringFormatter> consumer) {
    if (condition) {
      Objects.requireNonNull(consumer, "consumer").accept(this);
    }
    return this;
  }

  /**
   * Invokes the consumer for each element in the iterable.
   *
   * <p>A null iterable is treated as empty. See {@link StringBuilder} for the
   * behavior of any delegated append calls.
   *
   * @param iterable the values to visit, or null to skip the loop
   * @param consumer the consumer to invoke for each value
   * @param <T> the iterable element type
   * @return this formatter
   */
  public <T> StringFormatter forEach(
      final Iterable<T> iterable,
      final BiConsumer<StringFormatter, T> consumer) {
    final BiConsumer<StringFormatter, T> nonNullConsumer = Objects.requireNonNull(consumer, "consumer");
    if (iterable == null) {
      return this;
    }
    for (T value : iterable) {
      nonNullConsumer.accept(this, value);
    }
    return this;
  }

  /**
   * Repeats the consumer the requested number of times.
   *
   * <p>See {@link StringBuilder} for the behavior of any delegated append calls.
   *
   * @param count the number of times to invoke the consumer
   * @param consumer the consumer to invoke
   * @return this formatter
   */
  public StringFormatter repeat(
      final int count,
      final Consumer<StringFormatter> consumer) {
    if (count < 0) {
      throw new IllegalArgumentException("count must be >= 0");
    }
    if (count == 0) {
      return this;
    }
    final Consumer<StringFormatter> nonNullConsumer = Objects.requireNonNull(consumer, "consumer");
    for (int i = 0; i < count; ++i) {
      nonNullConsumer.accept(this);
    }
    return this;
  }

  /**
   * Repeats the consumer the requested number of times, passing the zero-based index.
   *
   * <p>See {@link StringBuilder} for the behavior of any delegated append calls.
   *
   * @param count the number of times to invoke the consumer
   * @param consumer the consumer to invoke with the index
   * @return this formatter
   */
  public StringFormatter repeat(
      final int count,
      final BiConsumer<StringFormatter, Integer> consumer) {
    if (count < 0) {
      throw new IllegalArgumentException("count must be >= 0");
    }
    if (count == 0) {
      return this;
    }
    final BiConsumer<StringFormatter, Integer> nonNullConsumer = Objects.requireNonNull(consumer, "consumer");
    for (int i = 0; i < count; ++i) {
      nonNullConsumer.accept(this, i);
    }
    return this;
  }

  /**
   * Appends the supplied character the requested number of times.
   *
   * @param value the character to append
   * @param width the number of copies to append
   * @return this formatter
   * @see StringBuilder#append(char)
   */
  public StringFormatter appendFill(final char value, final int width) {
    requireNonNegative(width, "width");
    for (int i = 0; i < width; ++i) {
      delegate.append(value);
    }
    return this;
  }

  /**
   * Appends the supplied code point the requested number of times.
   *
   * @param codePoint the code point to append
   * @param width the number of copies to append
   * @return this formatter
   * @see StringBuilder#appendCodePoint(int)
   */
  public StringFormatter appendFill(final int codePoint, final int width) {
    requireNonNegative(width, "width");
    for (int i = 0; i < width; ++i) {
      delegate.appendCodePoint(codePoint);
    }
    return this;
  }

  /**
   * Appends spaces the requested number of times.
   *
   * @param width the number of spaces to append
   * @return this formatter
   * @see StringBuilder#append(char)
   */
  public StringFormatter appendPadding(int width) {
    if (width < SPACES.length) {
        return append(SPACES, 0, width);
    }
    while (width > 0) {
      final int chunk = Math.min(width, SPACES.length);
      append(SPACES, 0, chunk);
      width -= chunk;
    }
    return this;
  }

  public StringFormatter appendPaddingToDistanceFromLastLineSeparator(final int width) {
    if (width < 0) {
      throw new IllegalArgumentException("width must be >= 0");
    }
    final int lastLineSeparatorIndex = delegate.lastIndexOf(LINE_SEPARATOR);
    final int distanceFromLastLineSeparator = delegate.length() - (lastLineSeparatorIndex + LINE_SEPARATOR.length());
    final int paddingWidth = width - distanceFromLastLineSeparator;
    if (paddingWidth > 0) {
      appendPadding(paddingWidth);
    }
    return this;
  }

  /**
   * Appends the platform line separator.
   *
   * @return this formatter
   * @see StringBuilder#append(String)
   */
  public StringFormatter appendLineSeparator() {
    delegate.append(LINE_SEPARATOR);
    return this;
  }

  /**
   * Appends a single space.
   *
   * @return this formatter
   * @see StringBuilder#append(char)
   */
  public StringFormatter appendSpace() {
    delegate.append(' ');
    return this;
  }

  /**
   * Appends a tilde character.
   *
   * @return this formatter
   * @see StringBuilder#append(char)
   */
  public StringFormatter appendTilde() {
    delegate.append('~');
    return this;
  }

  /**
   * Appends a tab character.
   *
   * @return this formatter
   * @see StringBuilder#append(char)
   */
  public StringFormatter appendTab() {
    delegate.append('\t');
    return this;
  }

  /**
   * Appends a pipe character.
   *
   * @return this formatter
   * @see StringBuilder#append(char)
   */
  public StringFormatter appendPipe() {
    delegate.append('|');
    return this;
  }

  /**
   * Appends a comma character.
   *
   * @return this formatter
   * @see StringBuilder#append(char)
   */
  public StringFormatter appendComma() {
    delegate.append(',');
    return this;
  }

  /**
   * Appends the value left-justified within the supplied width.
   *
   * <p>Null values are replaced with the current null-formatting text. See
   * {@link StringBuilder} for the behavior of the delegated append call.
   *
   * @param value the value to append, or null for an empty string
   * @param width the field width
   * @return this formatter
   */
  public StringFormatter leftAppend(final CharSequence value, final int width) {
    return appendAlignedLeft(nullSafe(value), width);
  }

  /**
   * Appends the value right-justified within the supplied width.
   *
   * <p>Null values are replaced with the current null-formatting text. See
   * {@link StringBuilder} for the behavior of the delegated append call.
   *
   * @param value the value to append, or null for an empty string
   * @param width the field width
   * @return this formatter
   */
  public StringFormatter rightAppend(final CharSequence value, final int width) {
    return appendAlignedRight(nullSafe(value), width);
  }

  /**
   * Appends the formatted int value left-justified within the supplied width.
   *
   * <p>See {@link StringBuilder#append(int)} for the delegate contract.
   *
   * @param value the value to append
   * @param width the field width
   * @return this formatter
   */
  public StringFormatter leftAppend(final int value, final int width) {
    return appendAlignedLeft(formatInteger(value), width);
  }

  /**
   * Appends the formatted long value left-justified within the supplied width.
   *
   * <p>See {@link StringBuilder#append(long)} for the delegate contract.
   *
   * @param value the value to append
   * @param width the field width
   * @return this formatter
   */
  public StringFormatter leftAppend(final long value, final int width) {
    return appendAlignedLeft(formatInteger(value), width);
  }

  /**
   * Appends the formatted boxed long value left-justified within the supplied width.
   *
   * <p>Null values are replaced with the current null-formatting text. See
   * {@link StringBuilder#append(long)}.
   *
   * @param value the value to append, or null for an empty string
   * @param width the field width
   * @return this formatter
   */
  public StringFormatter leftAppend(final Long value, final int width) {
    if (value == null) {
      return leftAppend(nullText, width);
    }
    return leftAppend(value.longValue(), width);
  }

  /**
   * Appends the formatted big integer left-justified within the supplied width.
   *
   * <p>Null values are replaced with the current null-formatting text. See
   * {@link StringBuilder} for the delegate contract.
   *
   * @param value the value to append, or null for an empty string
   * @param width the field width
   * @return this formatter
   */
  public StringFormatter leftAppend(final BigInteger value, final int width) {
    if (value == null) {
      return leftAppend(nullText, width);
    }
    return appendAlignedLeft(formatInteger(value), width);
  }

  /**
   * Appends the formatted double left-justified within the supplied width.
   *
   * <p>See {@link StringBuilder#append(double)} for the delegate contract.
   *
   * @param value the value to append
   * @param width the field width
   * @param precision the number of fractional digits to preserve
   * @return this formatter
   */
  public StringFormatter leftAppend(final double value, final int width, final int precision) {
    return appendAlignedLeft(formatDecimal(BigDecimal.valueOf(value), precision), width);
  }

  /**
   * Appends the formatted boxed double left-justified within the supplied width.
   *
   * <p>Null values are replaced with the current null-formatting text. See
   * {@link StringBuilder#append(double)}.
   *
   * @param value the value to append, or null for an empty string
   * @param width the field width
   * @param precision the number of fractional digits to preserve
   * @return this formatter
   */
  public StringFormatter leftAppend(final Double value, final int width, final int precision) {
    if (value == null) {
      return leftAppend(nullText, width);
    }
    return leftAppend(value.doubleValue(), width, precision);
  }

  /**
   * Appends the formatted big decimal left-justified within the supplied width.
   *
   * <p>Null values are replaced with the current null-formatting text. See
   * {@link StringBuilder} for the delegate contract.
   *
   * @param value the value to append, or null for an empty string
   * @param width the field width
   * @param precision the number of fractional digits to preserve
   * @return this formatter
   */
  public StringFormatter leftAppend(final BigDecimal value, final int width, final int precision) {
    if (value == null) {
      return leftAppend(nullText, width);
    }
    return appendAlignedLeft(formatDecimal(value, precision), width);
  }

  /**
   * Appends the formatted int right-justified within the supplied width.
   *
   * <p>See {@link StringBuilder#append(int)} for the delegate contract.
   *
   * @param value the value to append
   * @param width the field width
   * @return this formatter
   */
  public StringFormatter rightAppend(final int value, final int width) {
    return appendAlignedRight(formatInteger(value), width);
  }

  /**
   * Appends the formatted long right-justified within the supplied width.
   *
   * <p>See {@link StringBuilder#append(long)} for the delegate contract.
   *
   * @param value the value to append
   * @param width the field width
   * @return this formatter
   */
  public StringFormatter rightAppend(final long value, final int width) {
    return appendAlignedRight(formatInteger(value), width);
  }

  /**
   * Appends the formatted boxed long right-justified within the supplied width.
   *
   * <p>Null values are replaced with the current null-formatting text. See
   * {@link StringBuilder#append(long)}.
   *
   * @param value the value to append, or null for an empty string
   * @param width the field width
   * @return this formatter
   */
  public StringFormatter rightAppend(final Long value, final int width) {
    if (value == null) {
      return rightAppend(nullText, width);
    }
    return rightAppend(value.longValue(), width);
  }

  /**
   * Appends the formatted big integer right-justified within the supplied width.
   *
   * <p>Null values are replaced with the current null-formatting text. See
   * {@link StringBuilder} for the delegate contract.
   *
   * @param value the value to append, or null for an empty string
   * @param width the field width
   * @return this formatter
   */
  public StringFormatter rightAppend(final BigInteger value, final int width) {
    if (value == null) {
      return rightAppend(nullText, width);
    }
    return appendAlignedRight(formatInteger(value), width);
  }

  /**
   * Appends the formatted double right-justified within the supplied width.
   *
   * <p>See {@link StringBuilder#append(double)} for the delegate contract.
   *
   * @param value the value to append
   * @param width the field width
   * @param precision the number of fractional digits to preserve
   * @return this formatter
   */
  public StringFormatter rightAppend(final double value, final int width, final int precision) {
    return appendAlignedRight(formatDecimal(BigDecimal.valueOf(value), precision), width);
  }

  /**
   * Appends the formatted boxed double right-justified within the supplied width.
   *
   * <p>Null values are replaced with the current null-formatting text. See
   * {@link StringBuilder#append(double)}.
   *
   * @param value the value to append, or null for an empty string
   * @param width the field width
   * @param precision the number of fractional digits to preserve
   * @return this formatter
   */
  public StringFormatter rightAppend(final Double value, final int width, final int precision) {
    if (value == null) {
      return rightAppend(nullText, width);
    }
    return rightAppend(value.doubleValue(), width, precision);
  }

  /**
   * Appends the formatted big decimal right-justified within the supplied width.
   *
   * <p>Null values are replaced with the current null-formatting text. See
   * {@link StringBuilder} for the delegate contract.
   *
   * @param value the value to append, or null for an empty string
   * @param width the field width
   * @param precision the number of fractional digits to preserve
   * @return this formatter
   */
  public StringFormatter rightAppend(final BigDecimal value, final int width, final int precision) {
    if (value == null) {
      return rightAppend(nullText, width);
    }
    return appendAlignedRight(formatDecimal(value, precision), width);
  }

  /**
   * Appends an object value when it is non-null.
   *
   * <p>Null values are replaced with the current null-formatting text.
   *
   * @param value the value to append
   * @return this formatter
   * @see StringBuilder#append(Object)
   */
  public StringFormatter append(final Object value) {
    delegate.append(value == null ? nullText : value);
    return this;
  }

  /**
   * Appends a string value when it is non-null.
   *
   * <p>Null values are replaced with the current null-formatting text.
   *
   * @param value the value to append
   * @return this formatter
   * @see StringBuilder#append(String)
   */
  public StringFormatter append(final String value) {
    delegate.append(value == null ? nullText : value);
    return this;
  }

  /**
   * Appends a string buffer value when it is non-null.
   *
   * <p>Null values are replaced with the current null-formatting text.
   *
   * @param value the value to append
   * @return this formatter
   * @see StringBuilder#append(StringBuffer)
   */
  public StringFormatter append(final StringBuffer value) {
    if (value == null) {
      delegate.append(nullText);
    } else {
      delegate.append(value);
    }
    return this;
  }

  /**
   * Appends a character sequence value when it is non-null.
   *
   * <p>Null values are replaced with the current null-formatting text.
   *
   * @param value the value to append
   * @return this formatter
   * @see StringBuilder#append(CharSequence)
   */
  public StringFormatter append(final CharSequence value) {
    delegate.append(value == null ? nullText : value);
    return this;
  }

  /**
   * Appends a sub-sequence when the input value is non-null.
   *
   * <p>Null values are replaced with the current null-formatting text.
   *
   * @param value the value to append
   * @param start the start index
   * @param end the end index
   * @return this formatter
   * @see StringBuilder#append(CharSequence, int, int)
   */
  public StringFormatter append(
      final CharSequence value,
      final int start,
      final int end) {
    if (value == null) {
      delegate.append(nullText);
    } else {
      delegate.append(value, start, end);
    }
    return this;
  }

  /**
   * Appends a character array when it is non-null.
   *
   * <p>Null values are replaced with the current null-formatting text.
   *
   * @param value the array to append
   * @return this formatter
   * @see StringBuilder#append(char[])
   */
  public StringFormatter append(final char[] value) {
    if (value == null) {
      delegate.append(nullText);
    } else {
      delegate.append(value);
    }
    return this;
  }

  /**
   * Appends a character array slice when the input array is non-null.
   *
   * <p>Null values are replaced with the current null-formatting text.
   *
   * @param value the array to append
   * @param offset the starting offset within the array
   * @param len the number of characters to append
   * @return this formatter
   * @see StringBuilder#append(char[], int, int)
   */
  public StringFormatter append(
      final char[] value,
      final int offset,
      final int len) {
    if (value == null) {
      delegate.append(nullText);
    } else {
      delegate.append(value, offset, len);
    }
    return this;
  }

  /**
   * Appends a boolean value using the configured locale-neutral text form.
   *
   * @param value the value to append
   * @return this formatter
   * @see StringBuilder#append(boolean)
   */
  public StringFormatter append(final boolean value) {
    delegate.append(value);
    return this;
  }

  /**
   * Appends a single character.
   *
   * @param value the value to append
   * @return this formatter
   * @see StringBuilder#append(char)
   */
  public StringFormatter append(final char value) {
    delegate.append(value);
    return this;
  }

  /**
   * Appends an integer formatted with the configured locale.
   *
   * @param value the value to append
   * @return this formatter
   * @see StringBuilder#append(int)
   */
  public StringFormatter append(final int value) {
    delegate.append(formatInteger(value));
    return this;
  }

  /**
   * Appends a long formatted with the configured locale.
   *
   * @param value the value to append
   * @return this formatter
   * @see StringBuilder#append(long)
   */
  public StringFormatter append(final long value) {
    delegate.append(formatInteger(value));
    return this;
  }

  /**
   * Appends a float formatted with the configured locale.
   *
   * @param value the value to append
   * @return this formatter
   * @see StringBuilder#append(float)
   */
  public StringFormatter append(final float value) {
    delegate.append(formatDecimal(BigDecimal.valueOf(value)));
    return this;
  }

  /**
   * Appends a float formatted with the configured locale and fixed precision.
   *
   * @param value the value to append
   * @param precision the number of fractional digits to preserve
   * @return this formatter
   * @see StringBuilder#append(float)
   */
  public StringFormatter append(final float value, final int precision) {
    delegate.append(formatDecimal(BigDecimal.valueOf(value), precision));
    return this;
  }

  /**
   * Appends a double formatted with the configured locale.
   *
   * @param value the value to append
   * @return this formatter
   * @see StringBuilder#append(double)
   */
  public StringFormatter append(final double value) {
    delegate.append(formatDecimal(BigDecimal.valueOf(value)));
    return this;
  }

  /**
   * Appends a double formatted with the configured locale and fixed precision.
   *
   * @param value the value to append
   * @param precision the number of fractional digits to preserve
   * @return this formatter
   * @see StringBuilder#append(double)
   */
  public StringFormatter append(final double value, final int precision) {
    delegate.append(formatDecimal(BigDecimal.valueOf(value), precision));
    return this;
  }

  /**
   * Appends a Unicode code point.
   *
   * @param codePoint the code point to append
   * @return this formatter
   * @see StringBuilder#appendCodePoint(int)
   */
  public StringFormatter appendCodePoint(final int codePoint) {
    delegate.appendCodePoint(codePoint);
    return this;
  }

  /**
   * Deletes the characters in the supplied range.
   *
   * @param start the start index, inclusive
   * @param end the end index, exclusive
   * @return this formatter
   * @see StringBuilder#delete(int, int)
   */
  public StringFormatter delete(final int start, final int end) {
    delegate.delete(start, end);
    return this;
  }

  /**
   * Deletes the character at the supplied index.
   *
   * @param index the character index
   * @return this formatter
   * @see StringBuilder#deleteCharAt(int)
   */
  public StringFormatter deleteCharAt(final int index) {
    delegate.deleteCharAt(index);
    return this;
  }

  /**
   * Replaces the characters in the supplied range with the provided string.
   *
   * @param start the start index, inclusive
   * @param end the end index, exclusive
   * @param str the replacement text
   * @return this formatter
   * @see StringBuilder#replace(int, int, String)
   */
  public StringFormatter replace(
      final int start,
      final int end,
      final String str) {
    delegate.replace(start, end, str);
    return this;
  }

  /**
   * Inserts a character array slice at the supplied offset.
   *
   * @param offset the insertion index
   * @param str the source array
   * @param strOffset the source array offset
   * @param len the number of characters to insert
   * @return this formatter
   * @see StringBuilder#insert(int, char[], int, int)
   */
  public StringFormatter insert(
      final int offset,
      final char[] str,
      final int strOffset,
      final int len) {
    delegate.insert(offset, str, strOffset, len);
    return this;
  }

  /**
   * Inserts an object at the supplied offset.
   *
   * @param offset the insertion index
   * @param obj the value to insert
   * @return this formatter
   * <p>Null values are inserted using the current null-formatting text.
   * @see StringBuilder#insert(int, Object)
   */
  public StringFormatter insert(final int offset, final Object obj) {
    delegate.insert(offset, obj == null ? nullText : obj);
    return this;
  }

  /**
   * Inserts a string at the supplied offset.
   *
   * @param offset the insertion index
   * @param str the value to insert
   * @return this formatter
   * <p>Null values are inserted using the current null-formatting text.
   * @see StringBuilder#insert(int, String)
   */
  public StringFormatter insert(final int offset, final String str) {
    delegate.insert(offset, str == null ? nullText : str);
    return this;
  }

  /**
   * Inserts a character array at the supplied offset.
   *
   * @param offset the insertion index
   * @param str the value to insert
   * @return this formatter
   * <p>Null values are inserted using the current null-formatting text.
   * @see StringBuilder#insert(int, char[])
   */
  public StringFormatter insert(final int offset, final char[] str) {
    if (str == null) {
      delegate.insert(offset, nullText);
    } else {
      delegate.insert(offset, str);
    }
    return this;
  }

  /**
   * Inserts a character sequence at the supplied offset.
   *
   * @param offset the insertion index
   * @param csq the value to insert
   * @return this formatter
   * <p>Null values are inserted using the current null-formatting text.
   * @see StringBuilder#insert(int, CharSequence)
   */
  public StringFormatter insert(final int offset, final CharSequence csq) {
    delegate.insert(offset, csq == null ? nullText : csq);
    return this;
  }

  /**
   * Inserts a sub-sequence at the supplied offset.
   *
   * @param offset the insertion index
   * @param csq the value to insert
   * @param start the start index
   * @param end the end index
   * @return this formatter
   * <p>Null values are inserted using the current null-formatting text.
   * @see StringBuilder#insert(int, CharSequence, int, int)
   */
  public StringFormatter insert(
      final int offset,
      final CharSequence csq,
      final int start,
      final int end) {
    if (csq == null) {
      delegate.insert(offset, nullText);
    } else {
      delegate.insert(offset, csq, start, end);
    }
    return this;
  }

  /**
   * Inserts a boolean at the supplied offset.
   *
   * @param offset the insertion index
   * @param value the value to insert
   * @return this formatter
   * @see StringBuilder#insert(int, boolean)
   */
  public StringFormatter insert(final int offset, final boolean value) {
    delegate.insert(offset, value);
    return this;
  }

  /**
   * Inserts a character at the supplied offset.
   *
   * @param offset the insertion index
   * @param value the value to insert
   * @return this formatter
   * @see StringBuilder#insert(int, char)
   */
  public StringFormatter insert(final int offset, final char value) {
    delegate.insert(offset, value);
    return this;
  }

  /**
   * Inserts an integer at the supplied offset.
   *
   * @param offset the insertion index
   * @param value the value to insert
   * @return this formatter
   * @see StringBuilder#insert(int, int)
   */
  public StringFormatter insert(final int offset, final int value) {
    delegate.insert(offset, value);
    return this;
  }

  /**
   * Inserts a long at the supplied offset.
   *
   * @param offset the insertion index
   * @param value the value to insert
   * @return this formatter
   * @see StringBuilder#insert(int, long)
   */
  public StringFormatter insert(final int offset, final long value) {
    delegate.insert(offset, value);
    return this;
  }

  /**
   * Inserts a float at the supplied offset.
   *
   * @param offset the insertion index
   * @param value the value to insert
   * @return this formatter
   * @see StringBuilder#insert(int, float)
   */
  public StringFormatter insert(final int offset, final float value) {
    delegate.insert(offset, value);
    return this;
  }

  /**
   * Inserts a double at the supplied offset.
   *
   * @param offset the insertion index
   * @param value the value to insert
   * @return this formatter
   * @see StringBuilder#insert(int, double)
   */
  public StringFormatter insert(final int offset, final double value) {
    delegate.insert(offset, value);
    return this;
  }

  /**
   * Returns the first index of the supplied string.
   *
   * @param str the text to search for
   * @return the first matching index, or -1 when the text is not present
   *
   * @see StringBuilder#indexOf(String)
   */
  public int indexOf(final String str) {
    return delegate.indexOf(str);
  }

  /**
   * Returns the first index of the supplied string at or after the offset.
   *
   * @param str the text to search for
   * @param fromIndex the index to start searching from
   * @return the first matching index, or -1 when the text is not present
   *
   * @see StringBuilder#indexOf(String, int)
   */
  public int indexOf(final String str, final int fromIndex) {
    return delegate.indexOf(str, fromIndex);
  }

  /**
   * Returns the last index of the supplied string.
   *
   * @param str the text to search for
   * @return the last matching index, or -1 when the text is not present
   *
   * @see StringBuilder#lastIndexOf(String)
   */
  public int lastIndexOf(final String str) {
    return delegate.lastIndexOf(str);
  }

  /**
   * Returns the last index of the supplied string at or before the offset.
   *
   * @param str the text to search for
   * @param fromIndex the index to start searching backwards from
   * @return the last matching index, or -1 when the text is not present
   *
   * @see StringBuilder#lastIndexOf(String, int)
   */
  public int lastIndexOf(final String str, final int fromIndex) {
    return delegate.lastIndexOf(str, fromIndex);
  }

  /**
   * Reverses the current contents.
   *
   * @return this formatter
   *
   * @see StringBuilder#reverse()
   */
  public StringFormatter reverse() {
    delegate.reverse();
    return this;
  }

  /**
   * Returns the current contents as a string.
   *
   * @return the current contents
   *
   * @see StringBuilder#toString()
   */
  @Override
  public String toString() {
    return delegate.toString();
  }

  /**
   * Ensures that the capacity is at least the supplied value.
   *
   * @param minimumCapacity the minimum required capacity
   * @return this formatter
   *
   * @see StringBuilder#ensureCapacity(int)
   */
  public StringFormatter ensureCapacity(final int minimumCapacity) {
    delegate.ensureCapacity(minimumCapacity);
    return this;
  }

  /**
   * Trims the capacity to the current size.
   *
   * @return this formatter
   *
   * @see StringBuilder#trimToSize()
   */
  public StringFormatter trimToSize() {
    delegate.trimToSize();
    return this;
  }

  /**
   * Sets the logical length of the builder.
   *
   * @param newLength the new length
   * @return this formatter
   *
   * @see StringBuilder#setLength(int)
   */
  public StringFormatter setLength(final int newLength) {
    delegate.setLength(newLength);
    return this;
  }

  /**
   * Replaces the character at the supplied index.
   *
   * @param index the character index
   * @param ch the replacement character
   * @return this formatter
   *
   * @see StringBuilder#setCharAt(int, char)
   */
  public StringFormatter setCharAt(final int index, final char ch) {
    delegate.setCharAt(index, ch);
    return this;
  }

  /**
   * Copies characters into the supplied destination array.
   *
   * @param srcBegin the start index, inclusive
   * @param srcEnd the end index, exclusive
   * @param dst the destination array
   * @param dstBegin the destination offset
   * @return this formatter
   *
   * @see StringBuilder#getChars(int, int, char[], int)
   */
  public StringFormatter getChars(
      final int srcBegin,
      final int srcEnd,
      final char[] dst,
      final int dstBegin) {
    delegate.getChars(srcBegin, srcEnd, dst, dstBegin);
    return this;
  }

  /**
   * Returns the offset by code points from the supplied index.
   *
   * @param index the starting index
   * @param codePointOffset the number of code points to move
   * @return the resulting UTF-16 index
   *
   * @see StringBuilder#offsetByCodePoints(int, int)
   */
  public int offsetByCodePoints(final int index, final int codePointOffset) {
    return delegate.offsetByCodePoints(index, codePointOffset);
  }

  /**
   * Returns the number of code points in the supplied range.
   *
   * @param beginIndex the start index, inclusive
   * @param endIndex the end index, exclusive
   * @return the number of code points in the range
   *
   * @see StringBuilder#codePointCount(int, int)
   */
  public int codePointCount(final int beginIndex, final int endIndex) {
    return delegate.codePointCount(beginIndex, endIndex);
  }

  /**
   * Returns the code point before the supplied index.
   *
   * @param index the character index
   * @return the code point immediately before the supplied index
   *
   * @see StringBuilder#codePointBefore(int)
   */
  public int codePointBefore(final int index) {
    return delegate.codePointBefore(index);
  }

  /**
   * Returns the code point at the supplied index.
   *
   * @param index the character index
   * @return the code point at the supplied index
   *
   * @see StringBuilder#codePointAt(int)
   */
  public int codePointAt(final int index) {
    return delegate.codePointAt(index);
  }

  /**
   * Returns the character at the supplied index.
   *
   * @param index the character index
   * @return the character at the supplied index
   *
   * @see StringBuilder#charAt(int)
   */
  @Override
  public char charAt(final int index) {
    return delegate.charAt(index);
  }

  /**
   * Returns the current length.
   *
   * @return the current length
   *
   * @see StringBuilder#length()
   */
  @Override
  public int length() {
    return delegate.length();
  }

  /**
   * Returns the current capacity.
   *
   * @return the current capacity
   *
   * @see StringBuilder#capacity()
   */
  public int capacity() {
    return delegate.capacity();
  }

  /**
   * Returns whether the builder contains no characters.
   *
   * @return true when the builder is empty
   *
   * @see StringBuilder#isEmpty()
   */
  public boolean isEmpty() {
    return delegate.isEmpty();
  }

  /**
   * Returns the requested sub-sequence.
   *
   * @param start the start index, inclusive
   * @param end the end index, exclusive
   * @return the requested sub-sequence
   *
   * @see StringBuilder#subSequence(int, int)
   */
  @Override
  public CharSequence subSequence(final int start, final int end) {
    return delegate.subSequence(start, end);
  }

  /**
   * Returns the substring from the supplied start index.
   *
   * @param start the start index, inclusive
   * @return the substring from the supplied index
   *
   * @see StringBuilder#substring(int)
   */
  public String substring(final int start) {
    return delegate.substring(start);
  }

  /**
   * Returns the substring for the supplied range.
   *
   * @param start the start index, inclusive
   * @param end the end index, exclusive
   * @return the substring for the supplied range
   *
   * @see StringBuilder#substring(int, int)
   */
  public String substring(final int start, final int end) {
    return delegate.substring(start, end);
  }

  /**
   * Compares this builder to another builder lexicographically.
   *
   * @param other the other formatter
   * @return the comparison result
   *
   * @see StringBuilder#compareTo(StringBuilder)
   */
  @Override
  public int compareTo(final StringFormatter other) {
    return delegate.compareTo(other.delegate);
  }

  /**
   * Returns an {@link java.util.stream.IntStream} over the UTF-16 code units.
   *
   * @return a stream of UTF-16 code units
   *
   * @see StringBuilder#chars()
   */
  public java.util.stream.IntStream chars() {
    return delegate.chars();
  }

  /**
   * Returns an {@link java.util.stream.IntStream} over the Unicode code points.
   *
   * @return a stream of Unicode code points
   *
   * @see StringBuilder#codePoints()
   */
  public java.util.stream.IntStream codePoints() {
    return delegate.codePoints();
  }

  /**
   * Appends a value followed by any required trailing padding.
   *
   * @param value the text to append
   * @param width the minimum field width
   */
  private StringFormatter appendAlignedLeft(final CharSequence value, final int width) {
    requireNonNegative(width, "width");
    final CharSequence text = value == null ? nullText : value;
    delegate.append(text);
    appendPadding(Math.max(0, width - text.length()));
    return this;
  }

  /**
   * Appends any required leading padding followed by a value.
   *
   * @param value the text to append
   * @param width the minimum field width
   */
  private StringFormatter appendAlignedRight(final CharSequence value, final int width) {
    requireNonNegative(width, "width");
    final CharSequence text = value == null ? nullText : value;
    appendPadding(Math.max(0, width - text.length()));
    delegate.append(text);
    return this;
  }

  /**
   * Formats a long using the configured locale.
   *
   * @param value the value to format
   * @return the localized text representation
   */
  private String formatInteger(final long value) {
    final NumberFormat format = NumberFormat.getIntegerInstance(locale);
    format.setGroupingUsed(false);
    return format.format(value);
  }

  /**
   * Formats an int using the configured locale.
   *
   * @param value the value to format
   * @return the localized text representation
   */
  private String formatInteger(final int value) {
    return formatInteger((long) value);
  }

  /**
   * Formats a big integer using the configured locale.
   *
   * @param value the value to format
   * @return the localized text representation
   */
  private String formatInteger(final BigInteger value) {
    final NumberFormat format = NumberFormat.getIntegerInstance(locale);
    format.setGroupingUsed(false);
    return format.format((Object) value);
  }

  /**
   * Formats a decimal using the configured locale.
   *
   * @param value the value to format
   * @return the localized text representation
   */
  private String formatDecimal(final BigDecimal value) {
    final DecimalFormat format = (DecimalFormat) NumberFormat.getNumberInstance(locale);
    format.setGroupingUsed(false);
    format.setMinimumIntegerDigits(1);
    return format.format(value);
  }

  /**
   * Formats a decimal using the configured locale and fixed precision.
   *
   * @param value the value to format
   * @param precision the number of fractional digits to preserve
   * @return the localized text representation
   */
  private String formatDecimal(final BigDecimal value, final int precision) {
    requireNonNegative(precision, "precision");
    final BigDecimal scaled = value.setScale(precision, RoundingMode.HALF_UP);
    final DecimalFormat format = (DecimalFormat) NumberFormat.getNumberInstance(locale);
    format.setGroupingUsed(false);
    format.setMinimumIntegerDigits(1);
    format.setMinimumFractionDigits(precision);
    format.setMaximumFractionDigits(precision);
    format.setRoundingMode(RoundingMode.HALF_UP);
    return format.format((Object) scaled);
  }

  /**
   * Validates that a numeric argument is non-negative.
   *
   * @param value the numeric value to validate
   * @param name the argument name
   */
  private static void requireNonNegative(final int value, final String name) {
    if (value < 0) {
      throw new IllegalArgumentException(name + " must be >= 0");
    }
  }
}
