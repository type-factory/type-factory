package org.typefactory;

/**
 *
 * @param <T> A numeric type like {@link Short}, {@link Integer}, {@link Long}, {@link java.math.BigInteger}
 */
public interface NumericTypeParser<T extends Number> {

  interface NumericTypeParserBuilder<T extends Number> {

    NumericTypeParser<T> build();

    NumericTypeParserBuilder<T> clearMinValue();

    NumericTypeParserBuilder<T> clearMaxValue();

    /**
     * Set a minimum value that will be used to validate values that are parsed to a Long value.
     * @param minValue the minimum inclusive value allowed.
     * @return this {@code TypeParserBuilder}
     */
    NumericTypeParserBuilder<T> minValueInclusive(T minValue);

    /**
     * Set a maximum value that will be used to validate values that are parsed to a Long value.
     * @param maxValue the maximum inclusive value allowed.
     * @return this {@code TypeParserBuilder}
     */
    NumericTypeParserBuilder<T> maxValueInclusive(T maxValue);

    /**
     * Set a minimum value that will be used to validate values that are parsed to a Long value.
     * @param minValue the minimum exclusive value allowed.
     * @return this {@code TypeParserBuilder}
     */
    NumericTypeParserBuilder<T> minValueExclusive(T minValue);

    /**
     * Set a maximum value that will be used to validate values that are parsed to a Long value.
     * @param maxValue the maximum exclusive value allowed.
     * @return this {@code TypeParserBuilder}
     */
    NumericTypeParserBuilder<T> maxValueExclusive(T maxValue);

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
     *   .messageCode("must be an 8-character product code")
     *   .fixedSize(8)
     *   .acceptCharRange('a', 'z')
     *   .acceptCharRange('A', 'Z')
     *   .removeAllChar('-')
     *   .removeAllWhitespace()
     *   .toUpperCase()
     *   .build();
     * }</pre>
     *
     * @param ch the character you want the type-parser to ignore from the parsed value
     * @return this {@code TypeParserBuilder}.
     * @see #removeAllChars(char...)
     * @see #removeAllCodePoints(int)
     * @see #removeAllCodePoints(int...)
     */
    NumericTypeParserBuilder<T> ignoreAllChars(char ch);

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
     * @return this {@code TypeParserBuilder}.
     * @see #removeAllChars(char)
     * @see #removeAllCodePoints(int)
     * @see #removeAllCodePoints(int...)
     */
    NumericTypeParserBuilder<T> ignoreAllChars(char... chars);

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
     * @return this {@code TypeParserBuilder}.
     * @see #removeAllChars(char...)
     * @see #removeAllCodePoints(int)
     * @see #removeAllCodePoints(int...)
     */
    NumericTypeParserBuilder<T> ignoreAllCodePoints(int codePoint);

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
     * @return this {@code TypeParserBuilder}.
     * @see #removeAllChars(char)
     * @see #removeAllCodePoints(int)
     * @see #removeAllCodePoints(int...)
     */
    NumericTypeParserBuilder<T> ignoreAllCodePoints(int... codePoints);

    // TODO add javadoc
    NumericTypeParserBuilder<T> ignoreAllWhitespace();

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
     * @return this {@code TypeParserBuilder}.
     * @see #removeAllChars(char)
     * @see #removeAllCodePoints(int)
     * @see #removeAllCodePoints(int...)
     * @see <a href="https://unicode.org/reports/tr44/#General_Category_Values">Unicode General Character Categories</a>
     * @see <a href="https://www.compart.com/en/unicode/category/Pd">Unicode Dash_Punctuation (Pd) category</a>
     */
    NumericTypeParserBuilder<T> ignoreAllDashesAndHyphens();

  }

}
