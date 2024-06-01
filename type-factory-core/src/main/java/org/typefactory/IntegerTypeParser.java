package org.typefactory;

import java.util.Locale;
import java.util.function.IntFunction;
import org.typefactory.impl.Factory;

public interface IntegerTypeParser {

  static IntegerTypeParserBuilder builder() {
    return Factory.integerTypeParserBuilder();
  }

  /**
   * <p>Parse the provided {@code value} into a {@link Integer} value.</p>
   *
   * <p><b>Example 1 – parse to a {@code Integer} variable</b></p>
   *
   * <pre>{@code
   * Integer someVariable = TYPE_PARSER.parseToInteger(value);
   * }</pre>
   *
   * <p><b>Example 2 – parse to a custom type</b></p>
   *
   * <p>For example, the {@code ProductId} custom type below uses the type-parser directly in its
   * constructor to parse the provided {@code value} to create the {@code ProductId} instance:</p>
   *
   * <pre>{@code
   * public final class ProductId extends IntegerType {
   *
   *   private static final TypeParser TYPE_PARSER = TypeParser.builder()...build();
   *
   *   public ProductId(final Integer value) {
   *     super(TYPE_PARSER.parseToIntegerType(value));
   *   }
   * }
   * }</pre>
   *
   * <p><b>Note:</b> instantiating directly into a constructor as above may create an instance with
   * a null internal value. The instantiated object can be interrogated for a null internal value using {@link IntegerType#isNull() isNull()}. An
   * alternative is to use a factory method using the example shown in {@link #parseToIntegerType(CharSequence, IntFunction)} instead.</p>
   *
   * @param value the value to parse into a {@link Integer}.
   * @return parses the provided {@code value} into a {@link Integer} value. May return {@code null} if the {@link TypeParser} was configured to
   * {@link TypeParserBuilder#preserveNullAndEmpty() preserveNullAndEmpty} or {@link TypeParserBuilder#convertEmptyToNull() convertEmptyToNull}.
   * @throws InvalidValueException if the provided {@code value} cannot be parsed in into a {@link Integer}.
   * @see #parseToIntegerType(CharSequence, IntegerFunction)
   */
  Integer parseToInteger(CharSequence value) throws InvalidValueException;

  /**
   * <p>Parse the provided {@code value} into a custom type that extends {@link IntegerType} using the provided
   * {@code constructorOrFactoryMethod}.</p>
   *
   * <p>For example, the {@code DepartmentId} type below uses a factory method,
   * {@code of(CharSequence value)}, to create instances using the {@code parseToIntegerType} method:</p>
   *
   * <pre>{@code
   * public final class ProductId extends IntegerType {
   *
   *   private static final TypeParser TYPE_PARSER = TypeParser.builder()...build();
   *
   *   private ProductId(final Integer value) {
   *     super(value);
   *   }
   *
   *   public static ProductId of(final CharSequence value) {
   *     return TYPE_PARSER.parseToIntegerType(value, ProductId::new);
   *   }
   * }
   * }</pre>
   *
   * <p><b>Note:</b> use the {@link #parseToInteger(CharSequence)} method to simply parse into a {@link Integer} value.</p>
   *
   * @param value                      the value to parse into a custom type.
   * @param constructorOrFactoryMethod the functional interface that will be used to instantiate the custom type using the parsed value.
   * @param <T>                        a custom type that extends {@link IntegerType}
   * @return a custom type instance that extends {@link IntegerType}. May return {@code null} if the {@link TypeParser} was configured to
   * {@link TypeParserBuilder#preserveNullAndEmpty() preserveNullAndEmpty} or {@link TypeParserBuilder#convertEmptyToNull() convertEmptyToNull}.
   * @throws InvalidValueException if the provided {@code value} cannot be parsed in into a custom type.
   * @see #parseToString(CharSequence)
   */
  <T extends IntegerType> T parseToIntegerType(CharSequence value, IntFunction<T> constructorOrFactoryMethod) throws InvalidValueException;

  interface IntegerTypeParserBuilder {

    IntegerTypeParser build();

    IntegerTypeParserBuilder targetTypeClass(final Class<?> targetTypeClass);

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
    IntegerTypeParserBuilder messageCode(final MessageCode messageCode);

    IntegerTypeParserBuilder defaultLocale(Locale locale);

    /**
     * Set a minimum value that will be used to validate values that are parsed to a Integer value.
     * @param minValue the minimum inclusive value allowed.
     * @return this builder
     */
    IntegerTypeParserBuilder minValueInclusive(int minValue);

    /**
     * Set a maximum value that will be used to validate values that are parsed to a Integer value.
     * @param maxValue the maximum inclusive value allowed.
     * @return this builder
     */
    IntegerTypeParserBuilder maxValueInclusive(int maxValue);

    /**
     * Set a minimum value that will be used to validate values that are parsed to a Integer value.
     * @param minValue the minimum exclusive value allowed.
     * @return this builder
     */
    IntegerTypeParserBuilder minValueExclusive(int minValue);

    /**
     * Set a maximum value that will be used to validate values that are parsed to a Integer value.
     * @param maxValue the maximum exclusive value allowed.
     * @return this builder
     */
    IntegerTypeParserBuilder maxValueExclusive(int maxValue);

    IntegerTypeParserBuilder caseSensitive();

    IntegerTypeParserBuilder caseInsensitive();

    IntegerTypeParserBuilder allowCustomBaseNumbers(char... charactersForCustomNumericBase);

    IntegerTypeParserBuilder allowCustomBaseNumbers(int... codePointsForCustomNumericBase);

    IntegerTypeParserBuilder allowBase8Numbers();

    IntegerTypeParserBuilder allowBase10Numbers();

    IntegerTypeParserBuilder allowBase16Numbers();

    IntegerTypeParserBuilder allowBase32Numbers();

    IntegerTypeParserBuilder allowBase36Numbers();

    IntegerTypeParserBuilder allowBase62Numbers();


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
    IntegerTypeParserBuilder ignoreAllOccurrencesOfChar(char ch);

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
    IntegerTypeParserBuilder ignoreAllOccurrencesOfChars(char... chars);

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
    IntegerTypeParserBuilder ignoreAllOccurrencesOfCodePoint(int codePoint);

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
    IntegerTypeParserBuilder ignoreAllOccurrencesOfCodePoints(int... codePoints);

    // TODO add javadoc
    IntegerTypeParserBuilder ignoreAllWhitespace();

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
    IntegerTypeParserBuilder ignoreAllDashesAndHyphens();

    IntegerTypeParserBuilder ignoreAllDashesAndHyphensExceptLeadingNegativeSign();
  }

}
