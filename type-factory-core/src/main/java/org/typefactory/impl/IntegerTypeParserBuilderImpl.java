package org.typefactory.impl;

import java.util.Locale;
import org.typefactory.IntegerTypeParser.IntegerTypeParserBuilder;

class IntegerTypeParserBuilderImpl implements IntegerTypeParserBuilder {

  private final LongTypeParserBuilderImpl wrapperBuilder =
      new LongTypeParserBuilderImpl(Integer.MIN_VALUE, Integer.MAX_VALUE);

  public IntegerTypeParserImpl build() {
    return new IntegerTypeParserImpl(wrapperBuilder.build());
  }

  @Override
  public IntegerTypeParserBuilder defaultLocale(Locale locale) {
    wrapperBuilder.defaultLocale(locale);
    return this;
  }

  @Override
  public IntegerTypeParserBuilder clearMinValue() {
    wrapperBuilder.clearMinValue();
    return this;
  }

  @Override
  public IntegerTypeParserBuilder clearMaxValue() {
    wrapperBuilder.clearMaxValue();
    return this;
  }

  @Override
  public IntegerTypeParserBuilder minValueInclusive(int minValue) {
    wrapperBuilder.minValueInclusive(minValue);
    return this;
  }

  @Override
  public IntegerTypeParserBuilder maxValueInclusive(int maxValue) {
    wrapperBuilder.maxValueInclusive(maxValue);
    return this;
  }

  @Override
  public IntegerTypeParserBuilder minValueExclusive(int minValue) {
    wrapperBuilder.minValueExclusive(minValue);
    return this;
  }

  @Override
  public IntegerTypeParserBuilder maxValueExclusive(int maxValue) {
    wrapperBuilder.maxValueExclusive(maxValue);
    return this;
  }

  @Override
  public IntegerTypeParserBuilder caseSensitive() {
    wrapperBuilder.caseSensitive();
    return this;
  }

  @Override
  public IntegerTypeParserBuilder caseInsensitive() {
    wrapperBuilder.caseInsensitive();
    return this;
  }

  @Override
  public IntegerTypeParserBuilder allowCustomBaseNumbers(char... charactersForCustomNumericBase) {
    wrapperBuilder.allowCustomBaseNumbers(charactersForCustomNumericBase);
    return this;
  }

  @Override
  public IntegerTypeParserBuilder allowCustomBaseNumbers(int... codePointsForCustomNumericBase) {
    wrapperBuilder.allowCustomBaseNumbers(codePointsForCustomNumericBase);
    return this;
  }

  @Override
  public IntegerTypeParserBuilder allowBase8Numbers() {
    wrapperBuilder.allowBase8Numbers();
    return this;
  }

  @Override
  public IntegerTypeParserBuilder allowBase10Numbers() {
    wrapperBuilder.allowBase10Numbers();
    return this;
  }

  @Override
  public IntegerTypeParserBuilder allowBase16Numbers() {
    wrapperBuilder.allowBase16Numbers();
    return this;
  }

  @Override
  public IntegerTypeParserBuilder allowBase32Numbers() {
    wrapperBuilder.allowBase32Numbers();
    return this;
  }

  @Override
  public IntegerTypeParserBuilder allowBase36Numbers() {
    wrapperBuilder.allowBase36Numbers();
    return this;
  }

  @Override
  public IntegerTypeParserBuilder allowBase62Numbers() {
    wrapperBuilder.allowBase62Numbers();
    return this;
  }

  @Override
  public IntegerTypeParserBuilder ignoreAllOccurrencesOfChar(char ch) {
    wrapperBuilder.ignoreAllOccurrencesOfChar(ch);
    return this;
  }

  @Override
  public IntegerTypeParserBuilder ignoreAllOccurrencesOfChars(char... chars) {
    wrapperBuilder.ignoreAllOccurrencesOfChars(chars);
    return this;
  }

  @Override
  public IntegerTypeParserBuilder ignoreAllOccurrencesOfCodePoint(int codePoint) {
    wrapperBuilder.ignoreAllOccurrencesOfCodePoint(codePoint);
    return this;
  }

  @Override
  public IntegerTypeParserBuilder ignoreAllOccurrencesOfCodePoints(int... codePoints) {
    wrapperBuilder.ignoreAllOccurrencesOfCodePoints(codePoints);
    return this;
  }

  @Override
  public IntegerTypeParserBuilder ignoreAllWhitespace() {
    wrapperBuilder.ignoreAllWhitespace();
    return this;
  }

  @Override
  public IntegerTypeParserBuilder ignoreAllDashesAndHyphens() {
    wrapperBuilder.ignoreAllDashesAndHyphens();
    return this;
  }

  @Override
  public IntegerTypeParserBuilder ignoreAllDashesAndHyphensExceptLeadingNegativeSign() {
    wrapperBuilder.ignoreAllDashesAndHyphensExceptLeadingNegativeSign();
    return this;
  }
}
