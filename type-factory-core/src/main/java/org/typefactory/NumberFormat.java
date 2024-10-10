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

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.function.Function;
import org.typefactory.impl.Factory;

public interface NumberFormat {

  static NumberFormat of(Locale locale) {
    return builder(locale).build();
  }

  static NumberFormatBuilder builder() {
    return Factory.numberFormatBuilder();
  }

  static NumberFormatBuilder builder(Locale locale) {
    return Factory.numberFormatBuilder(locale);
  }

  int[] getDecimalSeparators();

  boolean isDecimalSeparator(char c);

  boolean isDecimalSeparator(int c);

  int[] getGroupingSeparators();

  boolean isGroupingSeparator(char c);

  boolean isGroupingSeparator(int c);

  int getGroupingSize();

  int getMinimumIntegerDigits();

  int getMaximumIntegerDigits();

  int getMinimumFractionDigits();

  int getMaximumFractionDigits();

  String getNegativePrefix();

  int getNegativePrefixCodePointAt(final int index);

  int getNegativePrefixCodePointsLength();

  String getNegativeSuffix();

  String getPositivePrefix();

  int getPositivePrefixCodePointAt(final int index);

  int getPositivePrefixCodePointsLength();

  String getPositiveSuffix();

  RoundingMode getRoundingMode();

  int getZeroDigit();

  interface NumberFormatBuilder {

    NumberFormat build();

    NumberFormatBuilder decimalSeparator(char decimalSeparator, char... additionalDecimalSeparators);

    NumberFormatBuilder decimalSeparator(int decimalSeparator, int... additionalDecimalSeparators);

    NumberFormatBuilder groupingSeparator(char groupingSeparator, char... additionalGroupingSeparators);

    NumberFormatBuilder groupingSeparator(int groupingSeparator, int... additionalGroupingSeparators);

    NumberFormatBuilder groupingSize(int groupingSize);

    NumberFormatBuilder minimumIntegerDigits(int minimumIntegerDigits);

    NumberFormatBuilder maximumIntegerDigits(int maximumIntegerDigits);

    NumberFormatBuilder minimumFractionDigits(int minimumFractionDigits);

    NumberFormatBuilder maximumFractionDigits(int maximumFractionDigits);

    NumberFormatBuilder negativePrefix(String negativePrefix);

    NumberFormatBuilder negativeSuffix(String negativeSuffix);

    NumberFormatBuilder positivePrefix(String positivePrefix);

    NumberFormatBuilder positiveSuffix(String positiveSuffix);

    /**
     * <p>The rounding mode to use when discarding precision – for example when parsing decimals into integers.
     * The round mode will determine how to calculate the least significant digit of a number when more digits are available.</p>
     *
     * <p>If not set, the type-parser will retrieve a default round mode by calling
     * {@link java.text.NumberFormat#getInstance(Locale) NumberFormat.getInstance(Locale).getRoundingMode()}</p>
     *
     * <p>This will not override the rounding mode when parsing with a specific locale:</p>
     * <ul>
     *   <li>{@link LongTypeParser#parse(CharSequence, Locale)}</li>
     *   <li>{@link LongTypeParser#parse(CharSequence, Locale, Function)}</li>
     * </ul>
     *
     * @return this builder.
     * @see RoundingMode
     */
    NumberFormatBuilder roundingMode(RoundingMode roundingMode);

    /**
     * <p>The character to use as the zero digit for radix 2..10 numbers. This is used to parse and format numbers in different
     * number systems, such as Arabic-Indic, Devanagari, and so on. The default value is '0'.</p>
     *
     * <p>If not set, the type-parser will retrieve a default zero digit by calling
     * {@link DecimalFormatSymbols#getZeroDigit() ((DecimalFormat)NumberFormat.getInstance(Locale)).getDecimalFormatSymbols().getZeroDigit()} if the
     * {@code NumberFormat} for the specified locale is a {@link DecimalFormat} instance. </p>
     *
     * <p>This will not override the zero digit when parsing or formatting using custom radix characters/codepoints nor when using a specific locale
     * with:</p>
     * <ul>
     *   <li>{@link LongTypeParser#parse(CharSequence, Locale)}</li>
     *   <li>{@link LongTypeParser#parse(CharSequence, Locale, Function)}</li>
     * </ul>
     *
     * @param zeroDigit the zero digit to use.
     * @return this builder.
     */
    NumberFormatBuilder zeroDigit(char zeroDigit);

    /**
     * <p>The character to use as the zero digit for radix 2..10 numbers. This is used to parse and format numbers in different
     * number systems, such as Arabic-Indic, Devanagari, and so on. The default value is '0'.</p>
     *
     * <p>If not set, the type-parser will retrieve a default zero digit by calling
     * {@link DecimalFormatSymbols#getZeroDigit() ((DecimalFormat)NumberFormat.getInstance(Locale)).getDecimalFormatSymbols().getZeroDigit()} if the
     * {@code NumberFormat} for the specified locale is a {@link DecimalFormat} instance. </p>
     *
     * <p>This will not override the zero digit when parsing or formatting using custom radix characters/codepoints nor when using a specific locale
     * with:</p>
     * <ul>
     *   <li>{@link LongTypeParser#parse(CharSequence, Locale)}</li>
     *   <li>{@link LongTypeParser#parse(CharSequence, Locale, Function)}</li>
     * </ul>
     *
     * @param zeroDigit the zero digit to use.
     * @return this builder.
     */
    NumberFormatBuilder zeroDigit(int zeroDigit);
  }
}
