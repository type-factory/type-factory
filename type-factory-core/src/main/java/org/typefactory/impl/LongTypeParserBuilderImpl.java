package org.typefactory.impl;

import java.util.Locale;
import org.typefactory.LongTypeParser.LongTypeParserBuilder;

class LongTypeParserBuilderImpl implements LongTypeParserBuilder {

  private final IntegralNumericTypeParserBuilderImpl wrapperBuilder =
      new IntegralNumericTypeParserBuilderImpl(Long.MIN_VALUE, Long.MAX_VALUE);

  public LongTypeParserImpl build() {
    return new LongTypeParserImpl(wrapperBuilder.build());
  }

  @Override
  public LongTypeParserBuilder defaultLocale(Locale locale) {
    wrapperBuilder.defaultLocale(locale);
    return this;
  }

  @Override
  public LongTypeParserBuilder clearMinValue() {
    wrapperBuilder.clearMinValue();
    return this;
  }

  @Override
  public LongTypeParserBuilder clearMaxValue() {
    wrapperBuilder.clearMaxValue();
    return this;
  }

  @Override
  public LongTypeParserBuilder minValueInclusive(long minValue) {
    wrapperBuilder.minValueInclusive(minValue);
    return this;
  }

  @Override
  public LongTypeParserBuilder maxValueInclusive(long maxValue) {
    wrapperBuilder.maxValueInclusive(maxValue);
    return this;
  }

  @Override
  public LongTypeParserBuilder minValueExclusive(long minValue) {
    wrapperBuilder.minValueExclusive(minValue);
    return this;
  }

  @Override
  public LongTypeParserBuilder maxValueExclusive(long maxValue) {
    wrapperBuilder.maxValueExclusive(maxValue);
    return this;
  }

  @Override
  public LongTypeParserBuilder caseSensitive() {
    wrapperBuilder.caseSensitive();
    return this;
  }

  @Override
  public LongTypeParserBuilder caseInsensitive() {
    wrapperBuilder.caseInsensitive();
    return this;
  }

  @Override
  public LongTypeParserBuilder acceptDigitsToArbitraryRadix(char... digitChars) {
    wrapperBuilder.acceptDigitsToArbitraryRadix(digitChars);
    return this;
  }

  @Override
  public LongTypeParserBuilder acceptDigitsToArbitraryRadix(int... digitCodePoints) {
    wrapperBuilder.acceptDigitsToArbitraryRadix(digitCodePoints);
    return this;
  }

  @Override
  public LongTypeParserBuilder acceptDigitsToArbitraryRadix(String digits) {
    wrapperBuilder.acceptDigitsToArbitraryRadix(digits);
    return this;
  }

  @Override
  public LongTypeParserBuilder acceptDigitsForBase8() {
    wrapperBuilder.acceptDigitsForBase8();
    return this;
  }

  @Override
  public LongTypeParserBuilder acceptDigitsForBase10() {
    wrapperBuilder.acceptDigitsForBase10();
    return this;
  }

  @Override
  public LongTypeParserBuilder acceptDigitsForBase16() {
    wrapperBuilder.acceptDigitsForBase16();
    return this;
  }

  @Override
  public LongTypeParserBuilder acceptDigitsForBase32() {
    wrapperBuilder.acceptDigitsForBase32();
    return this;
  }

  @Override
  public LongTypeParserBuilder acceptDigitsForBase36() {
    wrapperBuilder.acceptDigitsForBase36();
    return this;
  }

  @Override
  public LongTypeParserBuilder acceptDigitsForBase62() {
    wrapperBuilder.acceptDigitsForBase62();
    return this;
  }

  @Override
  public LongTypeParserBuilder ignoreAllOccurrencesOfChar(char ch) {
    wrapperBuilder.ignoreAllOccurrencesOfChar(ch);
    return this;
  }

  @Override
  public LongTypeParserBuilder ignoreAllOccurrencesOfChars(char... chars) {
    wrapperBuilder.ignoreAllOccurrencesOfChars(chars);
    return this;
  }

  @Override
  public LongTypeParserBuilder ignoreAllOccurrencesOfCodePoint(int codePoint) {
    wrapperBuilder.ignoreAllOccurrencesOfCodePoint(codePoint);
    return this;
  }

  @Override
  public LongTypeParserBuilder ignoreAllOccurrencesOfCodePoints(int... codePoints) {
    wrapperBuilder.ignoreAllOccurrencesOfCodePoints(codePoints);
    return this;
  }

  @Override
  public LongTypeParserBuilder ignoreAllWhitespace() {
    wrapperBuilder.ignoreAllWhitespace();
    return this;
  }

  @Override
  public LongTypeParserBuilder ignoreAllDashesAndHyphens() {
    wrapperBuilder.ignoreAllDashesAndHyphens();
    return this;
  }

  @Override
  public LongTypeParserBuilder ignoreAllDashesAndHyphensExceptLeadingMinusSign() {
    wrapperBuilder.ignoreAllDashesAndHyphensExceptLeadingMinusSign();
    return this;
  }
}
