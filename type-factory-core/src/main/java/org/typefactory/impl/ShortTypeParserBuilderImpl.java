package org.typefactory.impl;

import java.util.Locale;
import org.typefactory.ShortTypeParser.ShortTypeParserBuilder;

class ShortTypeParserBuilderImpl implements ShortTypeParserBuilder {

  private final IntegralNumericTypeParserBuilderImpl wrapperBuilder;

  ShortTypeParserBuilderImpl() {
    this.wrapperBuilder = new IntegralNumericTypeParserBuilderImpl(Short.MIN_VALUE, Short.MAX_VALUE);
  }

  ShortTypeParserBuilderImpl(final short defaultMinValue, final short defaultMaxValue) {
    this.wrapperBuilder = new IntegralNumericTypeParserBuilderImpl(defaultMinValue, defaultMaxValue);
  }

  public ShortTypeParserImpl build() {
    return new ShortTypeParserImpl(wrapperBuilder.build());
  }

  @Override
  public ShortTypeParserBuilder defaultLocale(Locale locale) {
    wrapperBuilder.defaultLocale(locale);
    return this;
  }

  @Override
  public ShortTypeParserBuilder minValueInclusive(short minValue) {
    wrapperBuilder.minValueInclusive(minValue);
    return this;
  }

  @Override
  public ShortTypeParserBuilder maxValueInclusive(short maxValue) {
    wrapperBuilder.maxValueInclusive(maxValue);
    return this;
  }

  @Override
  public ShortTypeParserBuilder minValueExclusive(short minValue) {
    wrapperBuilder.minValueExclusive(minValue);
    return this;
  }

  @Override
  public ShortTypeParserBuilder maxValueExclusive(short maxValue) {
    wrapperBuilder.maxValueExclusive(maxValue);
    return this;
  }

  @Override
  public ShortTypeParserBuilder caseSensitive() {
    wrapperBuilder.caseSensitive();
    return this;
  }

  @Override
  public ShortTypeParserBuilder caseInsensitive() {
    wrapperBuilder.caseInsensitive();
    return this;
  }

  @Override
  public ShortTypeParserBuilder allowCustomBaseNumbers(char... charactersForCustomNumericBase) {
    wrapperBuilder.allowCustomBaseNumbers(charactersForCustomNumericBase);
    return this;
  }

  @Override
  public ShortTypeParserBuilder allowCustomBaseNumbers(int... codePointsForCustomNumericBase) {
    wrapperBuilder.allowCustomBaseNumbers(codePointsForCustomNumericBase);
    return this;
  }

  @Override
  public ShortTypeParserBuilder allowBase8Numbers() {
    wrapperBuilder.allowBase8Numbers();
    return this;
  }

  @Override
  public ShortTypeParserBuilder allowBase10Numbers() {
    wrapperBuilder.allowBase10Numbers();
    return this;
  }

  @Override
  public ShortTypeParserBuilder allowBase16Numbers() {
    wrapperBuilder.allowBase16Numbers();
    return this;
  }

  @Override
  public ShortTypeParserBuilder allowBase32Numbers() {
    wrapperBuilder.allowBase32Numbers();
    return this;
  }

  @Override
  public ShortTypeParserBuilder allowBase36Numbers() {
    wrapperBuilder.allowBase36Numbers();
    return this;
  }

  @Override
  public ShortTypeParserBuilder allowBase62Numbers() {
    wrapperBuilder.allowBase62Numbers();
    return this;
  }

  @Override
  public ShortTypeParserBuilder ignoreAllOccurrencesOfChar(char ch) {
    wrapperBuilder.ignoreAllOccurrencesOfChar(ch);
    return this;
  }

  @Override
  public ShortTypeParserBuilder ignoreAllOccurrencesOfChars(char... chars) {
    wrapperBuilder.ignoreAllOccurrencesOfChars(chars);
    return this;
  }

  @Override
  public ShortTypeParserBuilder ignoreAllOccurrencesOfCodePoint(int codePoint) {
    wrapperBuilder.ignoreAllOccurrencesOfCodePoint(codePoint);
    return this;
  }

  @Override
  public ShortTypeParserBuilder ignoreAllOccurrencesOfCodePoints(int... codePoints) {
    wrapperBuilder.ignoreAllOccurrencesOfCodePoints(codePoints);
    return this;
  }

  @Override
  public ShortTypeParserBuilder ignoreAllWhitespace() {
    wrapperBuilder.ignoreAllWhitespace();
    return this;
  }

  @Override
  public ShortTypeParserBuilder ignoreAllDashesAndHyphens() {
    wrapperBuilder.ignoreAllDashesAndHyphens();
    return this;
  }

  @Override
  public ShortTypeParserBuilder ignoreAllDashesAndHyphensExceptLeadingNegativeSign() {
    wrapperBuilder.ignoreAllDashesAndHyphensExceptLeadingNegativeSign();
    return this;
  }
}
