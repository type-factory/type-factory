package org.typefactory;

import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;
import java.util.regex.Pattern;
import org.typefactory.impl.Factory;

public interface TypeParser {

  static TypeParserBuilder builder() {
    return Factory.typeParserBuilder();
  }

  <T extends StringType> T parseToStringType(CharSequence value, Function<String, T> constructorOrFactoryMethod);

  <T extends ShortType> T parseToShortType(CharSequence value, Function<Short, T> constructorOrFactoryMethod);

  <T extends IntegerType> T parseToIntegerType(CharSequence value, IntFunction<T> constructorOrFactoryMethod);

  <T extends LongType> T parseToLongType(CharSequence value, LongFunction<T> constructorOrFactoryMethod);

  String parseToString(CharSequence value);

  Short parseToShort(CharSequence value);

  Integer parseToInteger(CharSequence value);

  Long parseToLong(CharSequence value);

  interface TypeParserBuilder {

    TypeParserBuilder targetTypeClass(final Class<?> targetTypeClass);

    TypeParserBuilder errorMessage(final String errorMessage);

    TypeParserBuilder minSize(int minSize);

    TypeParserBuilder maxSize(int maxSize);

    TypeParserBuilder fixedSize(int size);

    TypeParserBuilder preserveNullAndEmpty();

    TypeParserBuilder convertNullToEmpty();

    TypeParserBuilder convertEmptyToNull();

    TypeParserBuilder preserveCase();

    TypeParserBuilder toUpperCase();

    TypeParserBuilder toLowerCase();

    TypeParserBuilder toTitleCase();

    TypeParserBuilder preserveCharacterNormalizationForm();

    TypeParserBuilder toCharacterNormalizationFormNFC();

    TypeParserBuilder toCharacterNormalizationFormNFD();

    TypeParserBuilder toCharacterNormalizationFormNFKC();

    TypeParserBuilder toCharacterNormalizationFormNFKD();

    TypeParserBuilder acceptChar(char ch);

    TypeParserBuilder acceptChars(char... chars);

    TypeParserBuilder acceptCharRange(char inclusiveFrom, char inclusiveTo);

    TypeParserBuilder acceptCodePoint(int codePoint);

    TypeParserBuilder acceptCodePoints(int... codePoints);

    /**
     * <p>Private method that will call {@link #acceptCodePoints(int...)} with all the code-points in the specified {@code charSequence}.</p>
     *
     * <p>It is a convenience method for:</p>
     * <pre>{@code
     *   acceptCodePoints(charSequence.codePoints().toArray())
     * }</pre>
     *
     * <p>This is a private method because I am not convinced there is an adequate use-case for a developer calling this when configuring a builder.
     * I think it would generally be better to be explicit about what you will accept by calling the existing public methods.</p>
     *
     * @param charSequence the character sequence containing the code-points you wish to accept.
     * @return this {@code TypeParserBuilder}.
     */
    default TypeParserBuilder acceptCodePointsInCharSequence(CharSequence charSequence) {
      if (charSequence == null) {
        return this;
      }
      return acceptCodePoints(charSequence.codePoints().toArray());
    }

    TypeParserBuilder acceptCodePointRange(int inclusiveFrom, int inclusiveTo);

    TypeParserBuilder convertChar(char fromChar, char toChar);

    TypeParserBuilder convertChar(char fromChar, CharSequence toCharSequence);

    TypeParserBuilder convertChar(CharSequence fromCharSequence, CharSequence toCharSequence);

    TypeParserBuilder convertCodePoint(int fromCodePoint, int toCodePoint);

    TypeParserBuilder convertCodePoint(int fromCodePoint, CharSequence toCharSequence);

    TypeParserBuilder acceptSubset(Subset subset);

    TypeParserBuilder acceptSubsets(Subset... subsets);

    TypeParserBuilder acceptUnicodeCategory(Category category);

    TypeParserBuilder acceptUnicodeCategory(Category... categories);

    TypeParserBuilder acceptAllUnicodeLetters();

    TypeParserBuilder acceptLettersAtoZ();

    TypeParserBuilder acceptDigits0to9();

    TypeParserBuilder acceptAllDashes();

    TypeParserBuilder convertAllDashesToHyphen();

    TypeParserBuilder acceptHyphenAndConvertAllDashesToHyphen();

    TypeParserBuilder convertAllDashesTo(char toChar);

    TypeParserBuilder convertAllDashesTo(int toCodePoint);

    TypeParserBuilder convertAllDashesTo(CharSequence toCharSequence);

    TypeParserBuilder removeAllWhitespace();

    TypeParserBuilder normalizeWhitespace();

    TypeParserBuilder normalizeAndConvertWhitespaceTo(char toChar);

    TypeParserBuilder normalizeAndConvertWhitespaceTo(int toCodePoint);

    TypeParserBuilder normalizeAndConvertWhitespaceTo(CharSequence toCharSequence);

    TypeParserBuilder preserveWhitespace();

    TypeParserBuilder preserveAndConvertWhitespaceTo(char toChar);

    TypeParserBuilder preserveAndConvertWhitespaceTo(int toCodePoint);

    TypeParserBuilder preserveAndConvertWhitespaceTo(CharSequence toCharSequence);

    TypeParserBuilder matchesRegex(Pattern regex);

    TypeParserBuilder customValidator(Function<String, Boolean> validationFunction);

    TypeParser build();
  }
}
