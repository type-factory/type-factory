package org.typefactory.impl;

import java.util.Locale;
import org.typefactory.LongTypeParser.LongTypeParserBuilder;
import org.typefactory.MessageCode;

class LongTypeParserBuilderImpl implements LongTypeParserBuilder {

  private final IntegralNumericTypeParserBuilderImpl wrapperBuilder;

  LongTypeParserBuilderImpl() {
    this.wrapperBuilder = new IntegralNumericTypeParserBuilderImpl(Long.MIN_VALUE, Long.MAX_VALUE);
  }

  LongTypeParserBuilderImpl(final long defaultMinValue, final long defaultMaxValue) {
    this.wrapperBuilder = new IntegralNumericTypeParserBuilderImpl(defaultMinValue, defaultMaxValue);
  }

  public LongTypeParserImpl build() {
    return new LongTypeParserImpl(wrapperBuilder.build());
  }

  @Override
  public LongTypeParserBuilder targetTypeClass(final Class<?> targetTypeClass) {
    wrapperBuilder.targetTypeClass(targetTypeClass);
    return this;
  }

  @Override
  public LongTypeParserBuilder messageCode(final MessageCode messageCode) {
    wrapperBuilder.messageCode(messageCode);
    return this;
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
  public LongTypeParserBuilder allowCustomBaseNumbers(char... charactersForCustomNumericBase) {
    wrapperBuilder.allowCustomBaseNumbers(charactersForCustomNumericBase);
    return this;
  }

  @Override
  public LongTypeParserBuilder allowCustomBaseNumbers(int... codePointsForCustomNumericBase) {
    wrapperBuilder.allowCustomBaseNumbers(codePointsForCustomNumericBase);
    return this;
  }

  @Override
  public LongTypeParserBuilder allowBase8Numbers() {
    wrapperBuilder.allowBase8Numbers();
    return this;
  }

  @Override
  public LongTypeParserBuilder allowBase10Numbers() {
    wrapperBuilder.allowBase10Numbers();
    return this;
  }

  @Override
  public LongTypeParserBuilder allowBase16Numbers() {
    wrapperBuilder.allowBase16Numbers();
    return this;
  }

  @Override
  public LongTypeParserBuilder allowBase32Numbers() {
    wrapperBuilder.allowBase32Numbers();
    return this;
  }

  @Override
  public LongTypeParserBuilder allowBase36Numbers() {
    wrapperBuilder.allowBase36Numbers();
    return this;
  }

  @Override
  public LongTypeParserBuilder allowBase62Numbers() {
    wrapperBuilder.allowBase62Numbers();
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
  public LongTypeParserBuilder ignoreAllDashesAndHyphensExceptLeadingNegativeSign() {
    wrapperBuilder.ignoreAllDashesAndHyphensExceptLeadingNegativeSign();
    return this;
  }

  @Override
  public LongTypeParserBuilder allowLeadingNegativeSign() {
    wrapperBuilder.allowLeadingNegativeSign();
    return this;
  }

  @Override
  public LongTypeParserBuilder ignoreLeadingNegativeSign() {
    wrapperBuilder.ignoreLeadingNegativeSign();
    return this;
  }

  @Override
  public LongTypeParserBuilder allowLeadingPositiveSign() {
    wrapperBuilder.allowLeadingPositiveSign();
    return this;
  }
  @Override
  public LongTypeParserBuilder ignoreLeadingPositiveSign() {
    wrapperBuilder.ignoreLeadingPositiveSign();
    return this;
  }
}
