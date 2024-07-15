package org.typefactory.impl;

import static org.typefactory.TypeParserBuilderException.MessageCodes.INVALID_SHORT_MAX_VALUE_EXCEPTION_MESSAGE;
import static org.typefactory.TypeParserBuilderException.MessageCodes.INVALID_SHORT_MIN_VALUE_EXCEPTION_MESSAGE;

import java.util.Locale;
import org.typefactory.MessageCode;
import org.typefactory.ShortTypeParser.ShortTypeParserBuilder;
import org.typefactory.TypeParserBuilderException;

class ShortTypeParserBuilderImpl implements ShortTypeParserBuilder {

  private final LongTypeParserBuilderImpl wrapperBuilder = new LongTypeParserBuilderImpl(Short.MIN_VALUE, Short.MAX_VALUE);

  public ShortTypeParserImpl build() {
    return new ShortTypeParserImpl(wrapperBuilder.build());
  }

  @Override
  public ShortTypeParserBuilder targetTypeClass(Class<?> targetTypeClass) {
    wrapperBuilder.targetTypeClass(targetTypeClass);
    return this;
  }

  @Override
  public ShortTypeParserBuilder messageCode(MessageCode messageCode) {
    wrapperBuilder.messageCode(messageCode);
    return this;
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
  public ShortTypeParserBuilder minValueInclusive(int minValue) throws TypeParserBuilderException {
    if (minValue < Short.MIN_VALUE || minValue > Short.MAX_VALUE) {
      throw TypeParserBuilderException.builder()
          .messageCode(INVALID_SHORT_MIN_VALUE_EXCEPTION_MESSAGE)
          .build();
    }
    wrapperBuilder.minValueInclusive(minValue);
    return this;
  }

  @Override
  public ShortTypeParserBuilder minValueInclusive(long minValue) throws TypeParserBuilderException {
    if (minValue < Short.MIN_VALUE || minValue > Short.MAX_VALUE) {
      throw TypeParserBuilderException.builder()
          .messageCode(INVALID_SHORT_MIN_VALUE_EXCEPTION_MESSAGE)
          .build();
    }
    wrapperBuilder.minValueInclusive(minValue);
    return this;
  }

  @Override
  public ShortTypeParserBuilder maxValueInclusive(short maxValue) {
    wrapperBuilder.maxValueInclusive(maxValue);
    return this;
  }

  @Override
  public ShortTypeParserBuilder maxValueInclusive(int maxValue) throws TypeParserBuilderException {
    if (maxValue > Short.MAX_VALUE || maxValue < Short.MIN_VALUE) {
      throw TypeParserBuilderException.builder()
          .messageCode(INVALID_SHORT_MAX_VALUE_EXCEPTION_MESSAGE)
          .build();
    }
    wrapperBuilder.maxValueInclusive(maxValue);
    return this;
  }

  @Override
  public ShortTypeParserBuilder maxValueInclusive(long maxValue) throws TypeParserBuilderException {
    if (maxValue > Short.MAX_VALUE || maxValue < Short.MIN_VALUE) {
      throw TypeParserBuilderException.builder()
          .messageCode(INVALID_SHORT_MAX_VALUE_EXCEPTION_MESSAGE)
          .build();
    }
    wrapperBuilder.maxValueInclusive(maxValue);
    return this;
  }

  @Override
  public ShortTypeParserBuilder minValueExclusive(short minValue) {
    wrapperBuilder.minValueExclusive(minValue);
    return this;
  }

  @Override
  public ShortTypeParserBuilder minValueExclusive(int minValue) throws TypeParserBuilderException {
    if (minValue < Short.MIN_VALUE || minValue > Short.MAX_VALUE) {
      throw TypeParserBuilderException.builder()
          .messageCode(INVALID_SHORT_MIN_VALUE_EXCEPTION_MESSAGE)
          .build();
    }
    wrapperBuilder.minValueExclusive(minValue);
    return this;
  }

  @Override
  public ShortTypeParserBuilder minValueExclusive(long minValue) throws TypeParserBuilderException {
    if (minValue < Short.MIN_VALUE || minValue > Short.MAX_VALUE) {
      throw TypeParserBuilderException.builder()
          .messageCode(INVALID_SHORT_MIN_VALUE_EXCEPTION_MESSAGE)
          .build();
    }
    wrapperBuilder.minValueExclusive(minValue);
    return this;
  }

  @Override
  public ShortTypeParserBuilder maxValueExclusive(short maxValue) {
    wrapperBuilder.maxValueExclusive(maxValue);
    return this;
  }

  @Override
  public ShortTypeParserBuilder maxValueExclusive(int maxValue) throws TypeParserBuilderException {
    if (maxValue > Short.MAX_VALUE || maxValue < Short.MIN_VALUE) {
      throw TypeParserBuilderException.builder()
          .messageCode(INVALID_SHORT_MAX_VALUE_EXCEPTION_MESSAGE)
          .build();
    }
    wrapperBuilder.maxValueExclusive(maxValue);
    return this;
  }

  @Override
  public ShortTypeParserBuilder maxValueExclusive(long maxValue) throws TypeParserBuilderException {
    if (maxValue > Short.MAX_VALUE || maxValue < Short.MIN_VALUE) {
      throw TypeParserBuilderException.builder()
          .messageCode(INVALID_SHORT_MAX_VALUE_EXCEPTION_MESSAGE)
          .build();
    }
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
  public ShortTypeParserBuilder forbidWhitespace() {
    wrapperBuilder.forbidWhitespace();
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

  @Override
  public ShortTypeParserBuilder allowLeadingNegativeSign() {
    wrapperBuilder.allowLeadingNegativeSign();
    return this;
  }

  @Override
  public ShortTypeParserBuilder ignoreLeadingNegativeSign() {
    wrapperBuilder.ignoreLeadingNegativeSign();
    return this;
  }

  @Override
  public ShortTypeParserBuilder allowLeadingPositiveSign() {
    wrapperBuilder.allowLeadingPositiveSign();
    return this;
  }

  @Override
  public ShortTypeParserBuilder ignoreLeadingPositiveSign() {
    wrapperBuilder.ignoreLeadingPositiveSign();
    return this;
  }
}
