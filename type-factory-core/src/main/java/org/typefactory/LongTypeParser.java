package org.typefactory;

import java.util.Locale;
import java.util.function.LongFunction;
import org.typefactory.impl.Factory;

/**
 * <p>Interface for a type-parser that will create a custom {@link LongType} instance either using
 * a provided numeric value, or by parsing an input {@link CharSequence}, cleaning it up if required, and validating that the provided value conforms
 * to configured rules.</p>
 *
 * <p>Type-parsers created with the {@link LongTypeParser#builder()} are immutable and thread-safe.</p>
 *
 * <p>It is recommended that you create a type-parser once and reuse it wherever required.
 * They are consider expensive to create but cheap to reuse. Type-parsers created with {@link LongTypeParser#builder()} method have been designed to
 * do their job efficiently with minimal object creation and no auto-boxing.</p>
 *
 * <p><b>Example 1 – long type-parser for a 9-digit invoice-number that will ignore any whitespace, hyphens or dashes.</b></p>
 * <pre>{@code
 * static final LongTypeParser TYPE_PARSER = LongTypeParser.builder()
 *     .messageCode(MessageCode.of("invalid.invoice.number", "must be a 9-digit invoice-number"))
 *     .minValueInclusive(100_000_000L)
 *     .maxValueInclusive(999_999_999L)
 *     .allowBase10Numbers()
 *     .ignoreAllDashesAndHyphens()
 *     .ignoreAllWhitespace()
 *     .build();
 * }</pre>
 *
 * @see #builder()
 */
public interface LongTypeParser {

  /**
   * <p>Creates a builder with which to create a type-parser for long numeric values.</p>>
   *
   * @return a builder with which to create a type-parser for long numeric values.
   */
  static LongTypeParserBuilder builder() {
    return Factory.longTypeParserBuilder();
  }

  /**
   * <p>Parse the provided {@code value} into a {@link Long} value. This method is null-safe,
   * returning {@code null} if the provided {@code value} was null.</p>
   *
   * <p><b>Example 1 – parse to a {@code Long} instance</b></p>
   *
   * <pre>{@code
   * static final LongTypeParser TYPE_PARSER = LongTypeParser.builder()...build();
   *
   * Long someVariable = TYPE_PARSER.parseToLong(value);
   * }</pre>
   *
   * <p><b>Example 2 – parse to a custom type extending {@link LongType} using the constructor</b></p>
   *
   * <p>For example, the {@code ProductId} custom type below uses the type-parser directly in its
   * constructor to parse the provided {@code value} to create the {@code ProductId} instance:</p>
   *
   * <pre>{@code
   * public final class ProductId extends LongType {
   *
   *   static final LongTypeParser TYPE_PARSER = LongTypeParser.builder()...build();
   *
   *   public ProductId(final Long value) {
   *     super(TYPE_PARSER.parseToLong(value));
   *   }
   * }
   * }</pre>
   *
   * <p><b>Note:</b> instantiating directly into a constructor as above may create an instance with
   * a null internal value. The instantiated object can be interrogated for a null internal value using {@link LongType#isNull() isNull()}. An
   * alternative is to use a factory method using the example shown in {@link #parseToLongType(CharSequence, LongFunction)} instead.</p>
   *
   * @param value the value to parse into a {@link Long}.
   * @return parses the provided {@code value} into a {@link Long} value. Will return {@code null} if the provided {@code value} was null.
   * @throws InvalidValueException if the provided {@code value} cannot be parsed in into a {@link Long}.
   * @see #parseToLongType(CharSequence, LongFunction)
   */
  Long parseToLong(CharSequence value) throws InvalidValueException;

  /**
   * <p>Parse the provided {@code value} into a custom type that extends {@link LongType} using the provided
   * {@code constructorOrFactoryMethod}. This method is null-safe, returning {@code null} if the provided {@code value} was null.</p>
   *
   * <p>For example, the {@code ProductId} type below uses a factory method,
   * {@code of(CharSequence value)}, to create instances using the {@code parseToLongType} method:</p>
   *
   * <pre>{@code
   * public final class ProductId extends LongType {
   *
   *   private static final TypeParser TYPE_PARSER = LongTypeParser.builder()...build();
   *
   *   private ProductId(final Long value) {
   *     super(value);
   *   }
   *
   *   public static ProductId of(final CharSequence value) {
   *     return TYPE_PARSER.parseToLongType(value, ProductId::new);
   *   }
   * }
   * }</pre>
   *
   * <p><b>Note:</b></p>
   * <ul>
   *   <li>The above {@code of(CharSequence)} factory method will return {@code null} if the provided {@code value} was null.
   *   It will not instantiate a {@code ProductId} object with a null internal value.</li>
   *   <li>Use the {@link #parseToLong(CharSequence)} method to simply parse into a {@link Long} value.</li>
   * </ul>
   *
   * @param value                      the value to parse into a custom type.
   * @param constructorOrFactoryMethod the functional interface that will be used to instantiate the custom type using the parsed value.
   * @param <T>                        a custom type that extends {@link LongType}
   * @return a custom type instance that extends {@link LongType}. Will return {@code null} if the provided {@code value} was null.
   * @throws InvalidValueException if the provided {@code value} cannot be parsed in into a custom type.
   * @see #parseToLong(CharSequence)
   */
  <T extends LongType> T parseToLongType(CharSequence value, LongFunction<T> constructorOrFactoryMethod) throws InvalidValueException;

  interface LongTypeParserBuilder {

    LongTypeParser build();

    LongTypeParserBuilder targetTypeClass(final Class<?> targetTypeClass);

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
    LongTypeParserBuilder messageCode(final MessageCode messageCode);

    /**
     * <p>Provide a default {@link Locale} which will be used when parsing {@link CharSequence} values to
     * correctly interpret grouping separators, also known as thousands separators or ten-of-thousands separators depending on the locale.</p>
     *
     * <p></p>
     *
     * @param locale a default {@link Locale} which will be used when parsing {@link CharSequence} values.
     * @return this builder
     */
    LongTypeParserBuilder defaultLocale(Locale locale);

    /**
     * Set a minimum value that will be used to validate values that are parsed to a Long value. Calling this will replace any previously set
     * inclusive or exclusive minimum value.
     *
     * @param minValue the minimum inclusive value allowed.
     * @return this builder
     * @see #minValueExclusive(long)
     */
    LongTypeParserBuilder minValueInclusive(long minValue);

    /**
     * Set a maximum value that will be used to validate values that are parsed to a Long value. Calling this will replace any previously set
     * inclusive or exclusive maximum value.
     *
     * @param maxValue the maximum inclusive value allowed.
     * @return this builder
     * @see #maxValueExclusive(long)
     */
    LongTypeParserBuilder maxValueInclusive(long maxValue);

    /**
     * Set a minimum value that will be used to validate values that are parsed to a Long value. Calling this will replace any previously set
     * inclusive or exclusive minimum value.
     *
     * @param minValue the minimum exclusive value allowed.
     * @return this builder
     * @see #minValueInclusive(long)
     */
    LongTypeParserBuilder minValueExclusive(long minValue);

    /**
     * Set a maximum value that will be used to validate values that are parsed to a Long value. Calling this will replace any previously set
     * inclusive or exclusive maximum value.
     *
     * @param maxValue the maximum exclusive value allowed.
     * @return this builder
     * @see #maxValueInclusive(long)
     */
    LongTypeParserBuilder maxValueExclusive(long maxValue);

    /**
     * When parsing numbers with a base (radix) larger than 10, the non-decimal characters that are also valid ‘digits’ will be parsed in a
     * case-sensitive fashion. This would be important, for example, for base-62 numbers using the digits and letters 0-9A-Za-z where the upper and
     * lower case letters represent different numeric values.
     *
     * @return this builder
     */
    LongTypeParserBuilder caseSensitive();

    /**
     * When parsing numbers with a base (radix) larger than 10, the non-decimal characters that are also valid ‘digits’ will be parsed in a
     * case-insensitive fashion. This would be important, for example, for base-16 or base-36 numbers that use the digits and letters 0-9A-Fa-f and
     * 0-9A-Za-z respectively.
     *
     * @return this builder
     */
    LongTypeParserBuilder caseInsensitive();

    /**
     * <p>Configures the type-parser to parse a number using the provided characters as the ‘digits’.
     * The base (or radix) of the number will be determined by the number of characters you provide. For example, if you wanted to parse base-20
     * numbers you may consider configuring the parser with:</p>
     *
     * <pre>{@code
     * LongTypeParser.builder()
     *   .allowCustomBaseNumbers(
     *       '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
     *       'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J')
     *   .build()
     * }</pre>
     *
     * <p>There is no restriction on which Unicode characters you may want to use.
     * You could consider using characters from any alphabet. For example, in Greece you may choose to have product codes written as base-34 numbers
     * using the Greek alphabet:</p>
     *
     * <pre>{@code
     * LongTypeParser.builder()
     *   .allowCustomBaseNumbers(
     *       '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
     *       'Α', 'Β', 'Γ', 'Δ', 'Ε', 'Ζ', 'Η', 'Θ', 'I', 'Κ',
     *       'Λ', 'Μ', 'Ν', 'Ξ', 'O', 'Π', 'Ρ', 'Σ', 'Τ', 'Υ',
     *       'Φ', 'Χ', 'Ψ', 'Ω')
     *   .build()
     * }</pre>
     *
     * <p>When creating such custom numeric bases, consider that some letters characters may
     * be best omitted if they are easily confused with numbers, for example the number 0 and letter O, or the number 1 and the letter I, which can be
     * hard to distinguish when displayed in certain fonts.</p>
     *
     * <p>It is also be possible to provide a set of characters that are Unicode symbols
     * instead of digits or letters. There really is no restriction.</p>
     *
     * @param charactersForCustomNumericBase the characters that you wish to use to represent numbers of any base or radix. The first character will
     *                                       map to zero and all the subsequent characters to the numeric values that follow.
     * @return this builder
     * @see #allowCustomBaseNumbers(int...)
     */
    LongTypeParserBuilder allowCustomBaseNumbers(char... charactersForCustomNumericBase);

    /**
     * <p>Configures the type-parser to parse a number using the provided code-points as the ‘digits’.
     * The base (or radix) of the number will be the number of character you provide.</p>
     *
     * <p>This method works the same way at the character version, {@link #allowCustomBaseNumbers(char...)},
     * but expects {@code int} code-points instead of {@code char} characters. Refer to that method's documentation for more information.</p>
     *
     * @param codePointsForCustomNumericBase the code-points that you wish to use to represent numbers of any base or radix. The first code-point will
     *                                       map to zero and all the subsequent code-points to the numeric values that follow.
     * @return this builder
     * @see #allowCustomBaseNumbers(char...)
     */
    LongTypeParserBuilder allowCustomBaseNumbers(int... codePointsForCustomNumericBase);

    /**
     * Configures the type-parse to accept only the default base-8 digits {@code 0..7}.
     *
     * @return this builder
     */
    LongTypeParserBuilder allowBase8Numbers();

    /**
     * Configures the type-parse to accept only the default base-10 digits {@code 0..9}.
     *
     * @return this builder
     */
    LongTypeParserBuilder allowBase10Numbers();

    /**
     * Configures the type-parse to accept only the default base-16 digits and letters {@code 0..9A..F}. Remember to configure the type-parser to be
     * case-insensitive if you would like it to also accept {@code a..f}.
     *
     * @return this builder
     */
    LongTypeParserBuilder allowBase16Numbers();

    /**
     * Configures the type-parse to accept only the default base-32 digits and letters {@code 0..9A..V}. Remember to configure the type-parser to be
     * case-insensitive if you would like it to also accept {@code a..v}.
     *
     * @return this builder
     */
    LongTypeParserBuilder allowBase32Numbers();

    /**
     * Configures the type-parse to accept only the default base-36 digits and letters {@code 0..9A..Z}. Remember to configure the type-parser to be
     * case-insensitive if you would like it to also accept {@code a..z}.
     *
     * @return this builder
     */
    LongTypeParserBuilder allowBase36Numbers();

    /**
     * Configures the type-parse to accept only the default base-62 digits and letters {@code 0..9A..Za..z}. Remember to configure the type-parser to
     * be case-sensitive – the uppercase letters {@code A..Z} must be considered as separate numeric values to the lowercase letters {@code a..z}.
     *
     * @return this builder
     */
    LongTypeParserBuilder allowBase62Numbers();


    /**
     * <p>This will configure the type-parser to ignore all occurrences of the specified character, {@code 'ch'}, when
     * parsing the value.</p>
     *
     * <p>This can be useful if you have numbers that are usually printed with formatting to make them easier to read, but then wish to
     * remove any such formatting upon parsing values that are provided back to your application.</p>
     *
     * <p><b>Example – removing all hyphens from a parsed value</b></p>
     *
     * <pre>{@code
     * TypeParser.builder()
     *   .messageCode("must be an 8-character product code")
     *   .minValueInclusive(1000_0000L)
     *   .maxValueInclusive(9999_9999L)
     *   .acceptDigitsForBase10()
     *   .ignoreAllOccurrencesOfChar('-') // hyphen
     *   .removeAllWhitespace()
     *   .build();
     * }</pre>
     *
     * @param ch the characters you want the type-parser to ignore when parsing the value
     * @return this builder.
     * @see #ignoreAllOccurrencesOfChars(char...)
     * @see #ignoreAllOccurrencesOfCodePoint(int)
     * @see #ignoreAllOccurrencesOfCodePoints(int...)
     */
    LongTypeParserBuilder ignoreAllOccurrencesOfChar(char ch);

    /**
     * <p>This will configure the type-parser to ignore all occurrences of the specified characters, {@code 'chars'}, when
     * parsing the value.</p>
     *
     * <p>This can be useful if you have numbers that are usually printed with formatting to make them easier to read, but then wish to
     * remove any such formatting upon parsing values that are provided back to your application.</p>
     *
     * <p><b>Example – removing all underscores, hyphens, en-dashes and em-dashes from a parsed value</b></p>
     *
     * <pre>{@code
     * TypeParser.builder()
     *   .messageCode("must be an 8-character product code")
     *   .minValueInclusive(1000_0000L)
     *   .maxValueInclusive(9999_9999L)
     *   .acceptDigitsForBase10()
     *   .ignoreAllOccurrencesOfChars('_', '-', '–', '—') // underscore, hyphen, en-dash, em-dash
     *   .removeAllWhitespace()
     *   .build();
     * }</pre>
     *
     * @param chars the characters you want the type-parser to ignore when parsing the value
     * @return this builder.
     * @see #ignoreAllOccurrencesOfChar(char)
     * @see #ignoreAllOccurrencesOfCodePoint(int)
     * @see #ignoreAllOccurrencesOfCodePoints(int...)
     */
    LongTypeParserBuilder ignoreAllOccurrencesOfChars(char... chars);

    /**
     * <p>This will configure the type-parser to ignore all occurrences of the specified code-point, {@code 'codePoint'}, when
     * parsing the value.</p>
     *
     * <p>This can be useful if you have numbers that are usually printed with formatting to make them easier to read, but then wish to
     * remove any such formatting upon parsing values that are provided back to your application.</p>
     *
     * <p><b>Example – removing all hyphens</b></p>
     *
     * <pre>{@code
     * TypeParser.builder()
     *   .messageCode("must be an 8-character product code")
     *   .minValueInclusive(1000_0000L)
     *   .maxValueInclusive(9999_9999L)
     *   .acceptDigitsForBase10()
     *   .ignoreAllOccurrencesOfCodePoint('-') // hyphen
     *   .removeAllWhitespace()
     *   .build();
     * }</pre>
     *
     * @param codePoint the code-points you want the type-parser to ignore when parsing the value
     * @return this builder.
     * @see #ignoreAllOccurrencesOfChar(char)
     * @see #ignoreAllOccurrencesOfChars(char...)
     * @see #ignoreAllOccurrencesOfCodePoints(int...)
     */
    LongTypeParserBuilder ignoreAllOccurrencesOfCodePoint(int codePoint);

    /**
     * <p>This will configure the type-parser to ignore all occurrences of the specified code-points, {@code 'codePoints'}, when
     * parsing the value.</p>
     *
     * <p>This can be useful if you have numbers that are usually printed with formatting to make them easier to read, but then wish to
     * remove any such formatting upon parsing values that are provided back to your application.</p>
     *
     * <p><b>Example – removing all underscores, hyphens, en-dashes and em-dashes from a parsed value</b></p>
     *
     * <pre>{@code
     * TypeParser.builder()
     *   .messageCode("must be an 8-character product code")
     *   .minValueInclusive(1000_0000L)
     *   .maxValueInclusive(9999_9999L)
     *   .acceptDigitsForBase10()
     *   .ignoreAllOccurrencesOfCodePoints('_', '-', '–', '—') // underscore, hyphen, en-dash, em-dash
     *   .removeAllWhitespace()
     *   .build();
     * }</pre>
     *
     * @param codePoints the code-points you want the type-parser to ignore when parsing the value
     * @return this builder.
     * @see #ignoreAllOccurrencesOfChar(char)
     * @see #ignoreAllOccurrencesOfChars(char...)
     * @see #ignoreAllOccurrencesOfCodePoint(int)
     */
    LongTypeParserBuilder ignoreAllOccurrencesOfCodePoints(int... codePoints);

    // TODO add javadoc
    LongTypeParserBuilder ignoreAllWhitespace();

    /**
     * <p>This will configure the type-parser to ignore all occurrences of any dashes and hyphens
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
     * @see #ignoreAllDashesAndHyphensExceptLeadingNegativeSign()
     * @see #ignoreAllOccurrencesOfChar(char)
     * @see #ignoreAllOccurrencesOfChars(char...)
     * @see #ignoreAllOccurrencesOfCodePoint(int)
     * @see #ignoreAllOccurrencesOfCodePoints(int...)
     * @see <a href="https://unicode.org/reports/tr44/#General_Category_Values">Unicode General Character Categories</a>
     * @see <a href="https://www.compart.com/en/unicode/category/Pd">Unicode Dash_Punctuation (Pd) category</a>
     */
    LongTypeParserBuilder ignoreAllDashesAndHyphens();

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
    LongTypeParserBuilder ignoreAllDashesAndHyphensExceptLeadingNegativeSign();

    LongTypeParserBuilder allowLeadingNegativeSign();

    LongTypeParserBuilder ignoreLeadingNegativeSign();

    LongTypeParserBuilder allowLeadingPositiveSign();

    LongTypeParserBuilder ignoreLeadingPositiveSign();
  }
}