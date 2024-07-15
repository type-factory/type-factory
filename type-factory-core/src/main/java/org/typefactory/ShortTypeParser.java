package org.typefactory;

import java.util.Locale;
import java.util.function.Function;
import java.util.function.IntFunction;
import org.typefactory.impl.Factory;

public interface ShortTypeParser extends NumericTypeParser<Short, ShortType> {

  static ShortTypeParserBuilder builder() {
    return Factory.shortTypeParserBuilder();
  }

  @Override
  Short of(Short value);

  @Override
  Short of(Integer value);

  @Override
  Short of(Long value);

  short of(final short value) throws InvalidValueException;

  short of(final int value) throws InvalidValueException;

  short of(final long value) throws InvalidValueException;

  /**
   * <p>Parse the provided {@code value} into a {@link Short} value.</p>
   *
   * <p><b>Example 1 – parse to a {@code Short} variable</b></p>
   *
   * <pre>{@code
   * Integer someVariable = TYPE_PARSER.parse(value);
   * }</pre>
   *
   * <p><b>Example 2 – parse to a custom type</b></p>
   *
   * <p>For example, the {@code ProductId} custom type below uses the type-parser directly in its
   * constructor to parse the provided {@code value} to create the {@code ProductId} instance:</p>
   *
   * <pre>{@code
   * public final class ProductId extends ShortType {
   *
   *   private static final TypeParser TYPE_PARSER = TypeParser.builder()...build();
   *
   *   public ProductId(final Short value) {
   *     super(TYPE_PARSER.parse(value));
   *   }
   * }
   * }</pre>
   *
   * <p><b>Note:</b> instantiating directly into a constructor as above may create an instance with
   * a null internal value. The instantiated object can be interrogated for a null internal value using {@link ShortType#isNull() isNull()}. An
   * alternative is to use a factory method using the example shown in {@link #parse(CharSequence, IntFunction)} instead.</p>
   *
   * @param value the value to parse into a {@link Short}.
   * @return parses the provided {@code value} into a {@link Short} value. May return {@code null} if the {@link TypeParser} was configured to
   * {@link TypeParserBuilder#preserveNullAndEmpty() preserveNullAndEmpty} or {@link TypeParserBuilder#convertEmptyToNull() convertEmptyToNull}.
   * @throws InvalidValueException if the provided {@code value} cannot be parsed in into a {@link Short}.
   * @see #parse(CharSequence, Function)
   */
  @Override
  Short parse(CharSequence value) throws InvalidValueException;

  @Override
  Short parse(CharSequence value, Locale locale) throws InvalidValueException;

  /**
   * <p>Parse the provided {@code value} into a custom type that extends {@link ShortType} using the provided
   * {@code constructorOrFactoryMethod}.</p>
   *
   * <p>For example, the {@code DepartmentId} type below uses a factory method,
   * {@code of(CharSequence value)}, to create instances using the {@code parse} method:</p>
   *
   * <pre>{@code
   * public final class ProductId extends ShortType {
   *
   *   private static final TypeParser TYPE_PARSER = TypeParser.builder()...build();
   *
   *   private ProductId(final Short value) {
   *     super(value);
   *   }
   *
   *   public static ProductId of(final CharSequence value) {
   *     return TYPE_PARSER.parse(value, ProductId::new);
   *   }
   * }
   * }</pre>
   *
   * <p><b>Note:</b> use the {@link #parse(CharSequence)} method to simply parse into a {@link Short} value.</p>
   *
   * @param value                      the value to parse into a custom type.
   * @param constructorOrFactoryMethod the functional interface that will be used to instantiate the custom type using the parsed value.
   * @param <T>                        a custom type that extends {@link ShortType}
   * @return a custom type instance that extends {@link ShortType}. May return {@code null} if the {@link TypeParser} was configured to
   * {@link TypeParserBuilder#preserveNullAndEmpty() preserveNullAndEmpty} or {@link TypeParserBuilder#convertEmptyToNull() convertEmptyToNull}.
   * @throws InvalidValueException if the provided {@code value} cannot be parsed in into a custom type.
   * @see #parseToString(CharSequence)
   */
  @Override
  <T extends ShortType> T parse(CharSequence value, Function<Short, T> constructorOrFactoryMethod) throws InvalidValueException;

  @Override
  <T extends ShortType> T parse(CharSequence value, Locale locale, Function<Short, T> constructorOrFactoryMethod) throws InvalidValueException;

  /**
   * Returns the radix (numeric-base) of the type-parser which will be greater-than or equal to 2.
   * @return the radix (numeric-base) of the type-parser.
   */
  @Override
  int getRadix();

  interface ShortTypeParserBuilder {

    ShortTypeParser build();

    ShortTypeParserBuilder targetTypeClass(final Class<?> targetTypeClass);

    /**
     * <p>Set a {@link MessageCode} containing a message code and default message to be returned in the {@link InvalidValueException} if the
     * type-parser cannot parse a provided value.</p>
     *
     * <p>Error messages can be localized by providing one or more Java resource bundles with a base name
     * of {@code 'org.typefactory.Messages'}. The resource bundles can be either:</p>
     * <ul>
     *   <li>Java properties files.</li>
     *   <li>Java classes that implement {@link java.util.ResourceBundle}.</li>
     * </ul>
     *
     * <p>For example, the following are valid examples of localization properties files and classes for providing localized error messages:</p>
     * <pre>
     *   org/typefactory/Messages.properties
     *   org/typefactory/Messages_en.properties
     *   org/typefactory/Messages_en_US.properties
     *   class org.typefactory.Messages_fr extends java.util.ListResourceBundle
     *   class org.typefactory.Messages_fr_CA extends java.util.ListResourceBundle
     * </pre>
     *
     * @param messageCode a {@link MessageCode} containing a message code and default message to be.
     * @return this builder
     * @see java.util.ListResourceBundle
     * @see java.util.ResourceBundle
     */
    ShortTypeParserBuilder messageCode(final MessageCode messageCode);

    ShortTypeParserBuilder defaultLocale(Locale locale);

    /**
     * Set a minimum value that will be used to validate values that are parsed to a Short value.
     * @param minValue the minimum inclusive value allowed.
     * @return this builder
     */
    ShortTypeParserBuilder minValueInclusive(short minValue);

    /**
     * Set a minimum value that will be used to validate values that are parsed to a Short value.
     * @param minValue the minimum inclusive value allowed.
     * @return this builder
     */
    ShortTypeParserBuilder minValueInclusive(int minValue) throws TypeParserBuilderException;

    /**
     * Set a minimum value that will be used to validate values that are parsed to a Short value.
     * @param minValue the minimum inclusive value allowed.
     * @return this builder
     */
    ShortTypeParserBuilder minValueInclusive(long minValue) throws TypeParserBuilderException;

    /**
     * Set a maximum value that will be used to validate values that are parsed to a Short value.
     * @param maxValue the maximum inclusive value allowed.
     * @return this builder
     */
    ShortTypeParserBuilder maxValueInclusive(short maxValue);

    /**
     * Set a maximum value that will be used to validate values that are parsed to a Short value.
     * @param maxValue the maximum inclusive value allowed.
     * @return this builder
     */
    ShortTypeParserBuilder maxValueInclusive(int maxValue) throws TypeParserBuilderException;

    /**
     * Set a maximum value that will be used to validate values that are parsed to a Short value.
     * @param maxValue the maximum inclusive value allowed.
     * @return this builder
     */
    ShortTypeParserBuilder maxValueInclusive(long maxValue) throws TypeParserBuilderException;

    /**
     * Set a minimum value that will be used to validate values that are parsed to a Short value.
     * @param minValue the minimum exclusive value allowed.
     * @return this builder
     */
    ShortTypeParserBuilder minValueExclusive(short minValue);

    /**
     * Set a minimum value that will be used to validate values that are parsed to a Short value.
     * @param minValue the minimum exclusive value allowed.
     * @return this builder
     */
    ShortTypeParserBuilder minValueExclusive(int minValue) throws TypeParserBuilderException;

    /**
     * Set a minimum value that will be used to validate values that are parsed to a Short value.
     * @param minValue the minimum exclusive value allowed.
     * @return this builder
     */
    ShortTypeParserBuilder minValueExclusive(long minValue) throws TypeParserBuilderException;

    /**
     * Set a maximum value that will be used to validate values that are parsed to a Short value.
     * @param maxValue the maximum exclusive value allowed.
     * @return this builder
     */
    ShortTypeParserBuilder maxValueExclusive(short maxValue);

    /**
     * Set a maximum value that will be used to validate values that are parsed to a Short value.
     * @param maxValue the maximum exclusive value allowed.
     * @return this builder
     */
    ShortTypeParserBuilder maxValueExclusive(int maxValue) throws TypeParserBuilderException;

    /**
     * Set a maximum value that will be used to validate values that are parsed to a Short value.
     * @param maxValue the maximum exclusive value allowed.
     * @return this builder
     */
    ShortTypeParserBuilder maxValueExclusive(long maxValue) throws TypeParserBuilderException;

    ShortTypeParserBuilder caseSensitive();

    ShortTypeParserBuilder caseInsensitive();

    ShortTypeParserBuilder allowCustomBaseNumbers(char... charactersForCustomNumericBase);

    ShortTypeParserBuilder allowCustomBaseNumbers(int... codePointsForCustomNumericBase);

    ShortTypeParserBuilder allowBase8Numbers();

    ShortTypeParserBuilder allowBase10Numbers();

    ShortTypeParserBuilder allowBase16Numbers();

    ShortTypeParserBuilder allowBase32Numbers();

    ShortTypeParserBuilder allowBase36Numbers();

    ShortTypeParserBuilder allowBase62Numbers();


    /**
     * <p>This will configure the type-parser to ignore all occurrences of the specified character, {@code 'ch'}, from the
     * parsed value.</p>
     *
     * <p>Specifying a character to ignore automatically adds it to the set of accepted characters – so there is no need
     * to specifically configure the type-parser to accept the character using {@link #acceptChar(char)}. If it wasn't considered an accepted
     * character then the first occurrence of the character in the input sequence would result in an {@link InvalidValueException}.</p>
     *
     * <p><b>Example – removing all hyphens from a parsed value</b></p>
     *
     * <pre>{@code
     * TypeParser.builder()
     *   .messageCode("must be an 8-digit product code")
     *   .minValueInclusive(1000_0000L)
     *   .maxValueInclusive(9999_9999L)
     *   .acceptDigitsForBase10()
     *   .removeAllChar('-')
     *   .removeAllWhitespace()
     *   .build();
     * }</pre>
     *
     * @param ch the character you want the type-parser to ignore from the parsed value
     * @return this builder.
     * @see #ignoreAllOccurrencesOfChars(char...)
     * @see #ignoreAllOccurrencesOfCodePoint(int)
     * @see #ignoreAllOccurrencesOfCodePoints(int...)
     */
    ShortTypeParserBuilder ignoreAllOccurrencesOfChar(char ch);

    /**
     * <p>This will configure the type-parser to ignore all occurrences of the specified characters, {@code 'chars'}, from the
     * parsed value.</p>
     *
     * <p>Specifying characters to ignore automatically adds them to the set of accepted characters – so there is no need
     * to specifically configure the type-parser to accept the characters using {@link #acceptChars(char...)}. If it wasn't considered an accepted
     * character then the first occurrence of the character in the input sequence would result in an {@link InvalidValueException}.</p>
     *
     * <p><b>Example – removing all hyphens, en-dashes and em-dashes from a parsed value</b></p>
     *
     * <pre>{@code
     * TypeParser.builder()
     *   .messageCode("must be an 8-character product code")
     *   .fixedSize(8)
     *   .acceptCharRange('a', 'z')
     *   .acceptCharRange('A', 'Z')
     *   .removeAllChar('-', '–', '—') // hyphen, en-dash, em-dash
     *   .removeAllWhitespace()
     *   .toUpperCase()
     *   .build();
     * }</pre>
     *
     * @param chars the characters you want the type-parser to ignore from the parsed value
     * @return this builder.
     * @see #ignoreAllOccurrencesOfChar(char)
     * @see #ignoreAllOccurrencesOfCodePoint(int)
     * @see #ignoreAllOccurrencesOfCodePoints(int...)
     */
    ShortTypeParserBuilder ignoreAllOccurrencesOfChars(char... chars);

    /**
     * <p>This will configure the type-parser to ignore all occurrences of the specified {@code 'codePoint'}, from the
     * parsed value.</p>
     *
     * <p>Specifying a code-point to ignore automatically adds it to the set of accepted code-points – so there is no need
     * to specifically configure the type-parser to accept the character using {@link #acceptCodePoint(int)}. If it wasn't considered an accepted
     * code-point then the first occurrence of the code-point in the input sequence would result in an {@link InvalidValueException}.</p>
     *
     * <p><b>Example – removing grinning face emoticon</b></p>
     *
     * <pre>{@code
     * TypeParser.builder()
     *   .messageCode("must be an 8-character identifier code")
     *   .fixedSize(8)
     *   .acceptCharRange('a', 'z')
     *   .acceptCharRange('A', 'Z')
     *   .removeAllCodePoints(0x01F600) // 😀 U+01F600 grinning face emoticon
     *   .removeAllWhitespace()
     *   .toUpperCase()
     *   .build();
     * }</pre>
     *
     * @param codePoint the code-point you want the type-parser to ignore from the parsed value
     * @return this builder.
     * @see #ignoreAllOccurrencesOfChar(char)
     * @see #ignoreAllOccurrencesOfChars(char...)
     * @see #ignoreAllOccurrencesOfCodePoints(int...)
     */
    ShortTypeParserBuilder ignoreAllOccurrencesOfCodePoint(int codePoint);

    /**
     * <p>This will configure the type-parser to ignore all occurrences of the specified {@code 'codePoints'}, from the
     * parsed value.</p>
     *
     * <p>Specifying code-points to ignore automatically adds them to the set of accepted code-points – so there is no need
     * to specifically configure the type-parser to accept the characters using {@link #acceptChars(char...)}. If it wasn't considered an accepted
     * character then the first occurrence of the character in the input sequence would result in an {@link InvalidValueException}.</p>
     *
     * <p><b>Example – removing 'grinning face' emoticons</b></p>
     *
     * <pre>{@code
     * TypeParser.builder()
     *   .messageCode("must be an 8-character product code")
     *   .fixedSize(8)
     *   .acceptCharRange('a', 'z')
     *   .acceptCharRange('A', 'Z')
     *   .removeAllCodePoints(0x01F600, 0x01F601) // 😀 U+01F600 grinning face, 😁 U+01F601 grinning face with smiling eyes
     *   .removeAllWhitespace()
     *   .toUpperCase()
     *   .build();
     * }</pre>
     *
     * @param codePoints the code-points you want the type-parser to ignore from the parsed value
     * @return this builder.
     * @see #ignoreAllOccurrencesOfChar(char)
     * @see #ignoreAllOccurrencesOfChars(char...)
     * @see #ignoreAllOccurrencesOfCodePoint(int)
     */
    ShortTypeParserBuilder ignoreAllOccurrencesOfCodePoints(int... codePoints);

    // TODO add javadoc
    ShortTypeParserBuilder ignoreAllWhitespace();

    ShortTypeParserBuilder forbidWhitespace();

    /**
     * <p>This will configure the type-parser to ignore all occurrences of any dashes and hyphens
     * that are found in the <a href="https://www.compart.com/en/unicode/category/Pd">Unicode Dash_Punctuation (Pd) category</a> from the parsed
     * value.</p>
     *
     * <p>For more information about categories refer to the <a href="https://unicode.org/reports/tr44/#General_Category_Values">Unicode
     * General Character Categories</a> documentation.</p>
     *
     * <p>Specifying code-points to ignore automatically adds them to the set of accepted code-points – so there is no need
     * to specifically configure the type-parser to accept the hyphens and dashes category {@link #acceptUnicodeCategory(Category)}. If hyphens and
     * dashes weren't considered accepted characters then the first occurrence of the character in the input sequence would result in an
     * {@link InvalidValueException}.</p>
     *
     * <p><b>Example – removing 'grinning face' emoticons</b></p>
     *
     * <pre>{@code
     * TypeParser.builder()
     *     .messageCode("must be a 16-digit product-id")
     *     .removeAllWhitespace()
     *     .removeAllDashesAndHyphens() // ignores all hyphens and dashes
     *     .acceptDigits0to9()
     *     .fixedSize(16)
     *     .build();
     * }</pre>
     *
     * @return this builder.
     * @see #ignoreAllDashesAndHyphensExceptLeadingNegativeSign()
     * @see #ignoreAllOccurrencesOfChar(char)
     * @see #ignoreAllOccurrencesOfChars(char...)
     * @see #ignoreAllOccurrencesOfCodePoint(int)
     * @see #ignoreAllOccurrencesOfCodePoints(int...)
     * @see <a href="https://unicode.org/reports/tr44/#General_Category_Values">Unicode General Character Categories</a>
     * @see <a href="https://www.compart.com/en/unicode/category/Pd">Unicode Dash_Punctuation (Pd) category</a>
     */
    ShortTypeParserBuilder ignoreAllDashesAndHyphens();

    /**
     * <p>This will configure the type-parser to ignore all occurrences, excepting a single leading negative sign, of any dashes and hyphens
     * that are found in the <a href="https://www.compart.com/en/unicode/category/Pd">Unicode Dash_Punctuation (Pd) category</a> from the parsed
     * value.</p>
     *
     * <p>For more information about categories refer to the <a href="https://unicode.org/reports/tr44/#General_Category_Values">Unicode
     * General Character Categories</a> documentation.</p>
     *
     * <pre>{@code
     * TypeParser.builder()
     *   .messageCode("must be an 8-character product code")
     *   .minValueInclusive(1000_0000L)
     *   .maxValueInclusive(9999_9999L)
     *   .acceptDigitsForBase10()
     *   .ignoreAllDashesAndHyphens()
     *   .removeAllWhitespace()
     *   .build();
     * }</pre>
     *
     * @return this builder.
     * @see #ignoreAllDashesAndHyphens()
     * @see #ignoreAllOccurrencesOfChar(char)
     * @see #ignoreAllOccurrencesOfChars(char...)
     * @see #ignoreAllOccurrencesOfCodePoint(int)
     * @see #ignoreAllOccurrencesOfCodePoints(int...)
     * @see <a href="https://unicode.org/reports/tr44/#General_Category_Values">Unicode General Character Categories</a>
     * @see <a href="https://www.compart.com/en/unicode/category/Pd">Unicode Dash_Punctuation (Pd) category</a>
     */
    ShortTypeParserBuilder ignoreAllDashesAndHyphensExceptLeadingNegativeSign();

    ShortTypeParserBuilder allowLeadingNegativeSign();

    ShortTypeParserBuilder ignoreLeadingNegativeSign();

    ShortTypeParserBuilder allowLeadingPositiveSign();

    ShortTypeParserBuilder ignoreLeadingPositiveSign();
  }

}
