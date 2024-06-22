package org.typefactory.impl;

import static org.typefactory.TypeParserBuilderException.MessageCodes.INVALID_INTEGER_MAX_VALUE_EXCEPTION_MESSAGE;
import static org.typefactory.TypeParserBuilderException.MessageCodes.INVALID_INTEGER_MIN_VALUE_EXCEPTION_MESSAGE;

import java.util.Locale;
import org.typefactory.IntegerTypeParser.IntegerTypeParserBuilder;
import org.typefactory.MessageCode;
import org.typefactory.TypeParserBuilderException;

class IntegerTypeParserBuilderImpl implements IntegerTypeParserBuilder {

  private final LongTypeParserBuilderImpl wrapperBuilder =
      new LongTypeParserBuilderImpl(Integer.MIN_VALUE, Integer.MAX_VALUE);

  public IntegerTypeParserImpl build() {
    return new IntegerTypeParserImpl(wrapperBuilder.build());
  }

  @Override
  public IntegerTypeParserBuilder targetTypeClass(Class<?> targetTypeClass) {
    wrapperBuilder.targetTypeClass(targetTypeClass);
    return this;
  }

  @Override
  public IntegerTypeParserBuilder messageCode(MessageCode messageCode) {
    wrapperBuilder.messageCode(messageCode);
    return this;
  }

  @Override
  public IntegerTypeParserBuilder defaultLocale(Locale locale) {
    wrapperBuilder.defaultLocale(locale);
    return this;
  }

  @Override
  public IntegerTypeParserBuilder minValueInclusive(int minValue) {
    wrapperBuilder.minValueInclusive(minValue);
    return this;
  }

  @Override
  public IntegerTypeParserBuilder minValueInclusive(long minValue) throws TypeParserBuilderException {
    if (minValue < Integer.MIN_VALUE) {
      throw TypeParserBuilderException.builder()
          .messageCode(INVALID_INTEGER_MIN_VALUE_EXCEPTION_MESSAGE)
          .build();
    }
    wrapperBuilder.minValueInclusive(minValue);
    return this;
  }

  @Override
  public IntegerTypeParserBuilder maxValueInclusive(int maxValue) {
    wrapperBuilder.maxValueInclusive(maxValue);
    return this;
  }

  @Override
  public IntegerTypeParserBuilder maxValueInclusive(long maxValue) throws TypeParserBuilderException {
    if (maxValue > Integer.MAX_VALUE) {
      throw TypeParserBuilderException.builder()
          .messageCode(INVALID_INTEGER_MAX_VALUE_EXCEPTION_MESSAGE)
          .build();
    }
    wrapperBuilder.maxValueInclusive(maxValue);
    return this;
  }

  @Override
  public IntegerTypeParserBuilder minValueExclusive(int minValue) {
    wrapperBuilder.minValueExclusive(minValue);
    return this;
  }

  @Override
  public IntegerTypeParserBuilder minValueExclusive(long minValue) throws TypeParserBuilderException {
    if (minValue < Integer.MIN_VALUE) {
      throw TypeParserBuilderException.builder()
          .messageCode(INVALID_INTEGER_MIN_VALUE_EXCEPTION_MESSAGE)
          .build();
    }
    wrapperBuilder.minValueExclusive(minValue);
    return this;
  }

  @Override
  public IntegerTypeParserBuilder maxValueExclusive(int maxValue) {
    wrapperBuilder.maxValueExclusive(maxValue);
    return this;
  }

  @Override
  public IntegerTypeParserBuilder maxValueExclusive(long maxValue) throws TypeParserBuilderException {
    if (maxValue > Integer.MAX_VALUE) {
      throw TypeParserBuilderException.builder()
          .messageCode(INVALID_INTEGER_MAX_VALUE_EXCEPTION_MESSAGE)
          .build();
    }
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

  @Override
  public IntegerTypeParserBuilder allowLeadingNegativeSign() {
    wrapperBuilder.allowLeadingNegativeSign();
    return this;
  }

  @Override
  public IntegerTypeParserBuilder ignoreLeadingNegativeSign() {
    wrapperBuilder.ignoreLeadingNegativeSign();
    return this;
  }

  @Override
  public IntegerTypeParserBuilder allowLeadingPositiveSign() {
    wrapperBuilder.allowLeadingPositiveSign();
    return this;
  }
  @Override
  public IntegerTypeParserBuilder ignoreLeadingPositiveSign() {
    wrapperBuilder.ignoreLeadingPositiveSign();
    return this;
  }
}
