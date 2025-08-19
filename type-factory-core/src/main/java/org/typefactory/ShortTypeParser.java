package org.typefactory;

import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.function.Function;
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

  @Override
  Short of(BigInteger value) throws InvalidValueException;

  short of(final short value) throws InvalidValueException;

  short of(final int value) throws InvalidValueException;

  short of(final long value) throws InvalidValueException;

  @Override
  <S extends ShortType> S of(Short value, Function<Short, S> constructorOrFactoryMethod) throws InvalidValueException;

  @Override
  <S extends ShortType> S of(Integer value, Function<Short, S> constructorOrFactoryMethod) throws InvalidValueException;

  @Override
  <S extends ShortType> S of(Long value, Function<Short, S> constructorOrFactoryMethod) throws InvalidValueException;

  @Override
  <S extends ShortType> S of(BigInteger value, Function<Short, S> constructorOrFactoryMethod) throws InvalidValueException;

  @Override
  Short parse(CharSequence value) throws InvalidValueException;

  @Override
  Short parse(CharSequence value, Locale locale) throws InvalidValueException;

  @Override
  Short parse(CharSequence value, NumberFormat numberFormat) throws InvalidValueException;

  @Override
  <T extends ShortType> T parse(CharSequence value, Function<Short, T> constructorOrFactoryMethod) throws InvalidValueException;

  @Override
  <T extends ShortType> T parse(CharSequence value, Locale locale, Function<Short, T> constructorOrFactoryMethod) throws InvalidValueException;

  @Override
  <T extends ShortType> T parse(CharSequence value, NumberFormat numberFormat, Function<Short, T> constructorOrFactoryMethod) throws InvalidValueException;

  @Override
  int getRadix();

  interface ShortTypeParserBuilder {

    ShortTypeParser build();

    ShortTypeParserBuilder targetTypeClass(final Class<?> targetTypeClass);

    ShortTypeParserBuilder messageCode(final MessageCode messageCode);

    ShortTypeParserBuilder defaultLocale(Locale locale);

    ShortTypeParserBuilder roundingMode(final RoundingMode roundingMode);

    ShortTypeParserBuilder decimalSeparator(final char decimalSeparator, char... alternativeDecimalSeparators);

    ShortTypeParserBuilder decimalSeparator(final int decimalSeparator, int... alternativeDecimalSeparators);

    ShortTypeParserBuilder groupingSeparator(final char groupingSeparator, char... alternativeGroupingSeparators);

    ShortTypeParserBuilder groupingSeparator(final int groupingSeparator, int... alternativeGroupingSeparators);

    ShortTypeParserBuilder minValueInclusive(short minValue);

    ShortTypeParserBuilder minValueInclusive(int minValue) throws TypeParserBuilderException;

    ShortTypeParserBuilder minValueInclusive(long minValue) throws TypeParserBuilderException;

    ShortTypeParserBuilder maxValueInclusive(short maxValue);

    ShortTypeParserBuilder maxValueInclusive(int maxValue) throws TypeParserBuilderException;

    ShortTypeParserBuilder maxValueInclusive(long maxValue) throws TypeParserBuilderException;

    ShortTypeParserBuilder minValueExclusive(short minValue);

    ShortTypeParserBuilder minValueExclusive(int minValue) throws TypeParserBuilderException;

    ShortTypeParserBuilder minValueExclusive(long minValue) throws TypeParserBuilderException;

    ShortTypeParserBuilder maxValueExclusive(short maxValue);

    ShortTypeParserBuilder maxValueExclusive(int maxValue) throws TypeParserBuilderException;

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

    ShortTypeParserBuilder ignoreAllOccurrencesOfChar(char ch);

    ShortTypeParserBuilder ignoreAllOccurrencesOfChars(char... chars);

    ShortTypeParserBuilder ignoreAllOccurrencesOfCodePoint(int codePoint);

    ShortTypeParserBuilder ignoreAllOccurrencesOfCodePoints(int... codePoints);

    ShortTypeParserBuilder ignoreAllWhitespace();

    ShortTypeParserBuilder forbidWhitespace();

    ShortTypeParserBuilder ignoreAllDashesAndHyphens();

    ShortTypeParserBuilder allowLeadingNegativeSign();

    ShortTypeParserBuilder ignoreLeadingNegativeSign();

    ShortTypeParserBuilder allowLeadingPositiveSign();

    ShortTypeParserBuilder ignoreLeadingPositiveSign();
  }

}
