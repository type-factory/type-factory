/*
   Copyright 2021-2022 Evan Toliopoulos (typefactory.org)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package org.typefactory.impl;

import static org.typefactory.impl.Constants.APOSTROPHE_SINGLE_QUOTATION_MARK;
import static org.typefactory.impl.Constants.RIGHT_SINGLE_QUOTATION_MARK;

import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;
import org.typefactory.IntegerType;
import org.typefactory.InvalidValueException;
import org.typefactory.LongType;
import org.typefactory.LongTypeParser;
import org.typefactory.MessageCode;
import org.typefactory.ShortType;
import org.typefactory.Subset;

final class LongTypeParserImpl implements LongTypeParser {

  private enum Sign {
    NEGATIVE,
    POSITIVE
  }

  private final Class<?> targetTypeClass;
  private final MessageCode messageCode;
  private final Locale defaultLocale;
  private final DecimalFormatSymbols defaultDecimalFormatSymbols;
  private final WhiteSpace whiteSpace;
  private final Subset ignoreCharactersSubset;
  private final PrimitiveHashMapOfIntKeyToIntValue numericRadixCodePointsMap;
  private final int radix;
  private final long minValue;
  private final long maxValue;
  private final boolean minValueComparisonInclusive;
  private final boolean maxValueComparisonInclusive;
  private final boolean ignoreLeadingNegativeSign;
  private final boolean ignoreLeadingPositiveSign;

  @SuppressWarnings("java:S107")
    // Suppress SonaQube "Methods should not have too many parameters" because this constructor is called by a builder
  LongTypeParserImpl(
      final Class<?> targetTypeClass,
      final MessageCode messageCode,
      final Locale defaultLocale,
      final WhiteSpace whiteSpace,
      final Subset ignoreCharactersSubset,
      final PrimitiveHashMapOfIntKeyToIntValue numericRadixCodePointsMap,
      final int [] numericRadixCodePoints,
      final long minValue,
      final long maxValue,
      final boolean minValueComparisonInclusive,
      final boolean maxValueComparisonInclusive,
      final boolean ignoreLeadingNegativeSign,
      final boolean ignoreLeadingPositiveSign) {
    this.targetTypeClass = targetTypeClass;
    this.messageCode = messageCode;
    this.defaultLocale = defaultLocale == null ? Locale.getDefault() : defaultLocale;
    this.defaultDecimalFormatSymbols = DecimalFormatSymbols.getInstance(this.defaultLocale);
    this.whiteSpace = whiteSpace;
    this.ignoreCharactersSubset = ignoreCharactersSubset;
    this.numericRadixCodePointsMap = numericRadixCodePointsMap;
    this.radix = numericRadixCodePoints.length;
    this.minValue = minValue;
    this.maxValue = maxValue;
    this.minValueComparisonInclusive = minValueComparisonInclusive;
    this.maxValueComparisonInclusive = maxValueComparisonInclusive;
    this.ignoreLeadingNegativeSign = ignoreLeadingNegativeSign;
    this.ignoreLeadingPositiveSign = ignoreLeadingPositiveSign;
  }

  <T extends ShortType> T parseToShortType(final CharSequence value, Function<Short, T> constructorOrFactoryMethod)
      throws InvalidValueException {
    final Short parsedValue = parseToShort(value);
    return parsedValue == null
        ? null
        : constructorOrFactoryMethod.apply(parsedValue);
  }

  <T extends IntegerType> T parseToIntegerType(final CharSequence value, IntFunction<T> constructorOrFactoryMethod)
      throws InvalidValueException {
    final Integer parsedValue = parseToInteger(value);
    return parsedValue == null
        ? null
        : constructorOrFactoryMethod.apply(parsedValue);
  }

  public <T extends LongType> T parseToLongType(final CharSequence value, LongFunction<T> constructorOrFactoryMethod) throws InvalidValueException {
    final Long parsedValue = parseToLong(value);
    return parsedValue == null
        ? null
        : constructorOrFactoryMethod.apply(parsedValue);
  }

  Short parseToShort(final CharSequence originalValue) throws InvalidValueException {
    final Long parsedValue = parse(originalValue, defaultDecimalFormatSymbols);
    return parsedValue == null
        ? null
        : parsedValue.shortValue();
  }

  Integer parseToInteger(final CharSequence originalValue) throws InvalidValueException {
    final Long parsedValue = parse(originalValue, defaultDecimalFormatSymbols);
    return parsedValue == null
        ? null
        : parsedValue.intValue();
  }

  public Long parseToLong(final CharSequence originalValue) throws InvalidValueException {
    return parse(originalValue, defaultDecimalFormatSymbols);
  }

  // TODO writes tests for this
  public Long parseToLong(final CharSequence source, final Locale locale) throws InvalidValueException {
    return parse(source, DecimalFormatSymbols.getInstance(locale));
  }

  // Suppress SonarCloud issues:
  // - "java:S3776 Cognitive Complexity of methods should not be too high"
  // - "java:S6541 Methods should not perform too many tasks (aka Brain method)"
  // - "java:S135  Loops should not contain more than a single "break" or "continue" statement"
  @SuppressWarnings({"java:S3776", "java:S6541", "java:S135"})
  private Long parse(final CharSequence source, final DecimalFormatSymbols decimalFormatSymbols) throws InvalidValueException {
    if (source == null || source.isEmpty()) {
      return null;
    }

    final int groupingSeparator = decimalFormatSymbols.getGroupingSeparator();
    // Locales that use a "right single quotation mark" (U+2019) as the grouping separator often simply use an apostrophe/single-quote (U+0027)
    final int altGroupingSeparator = groupingSeparator == RIGHT_SINGLE_QUOTATION_MARK ? APOSTROPHE_SINGLE_QUOTATION_MARK : -1;

    final int length = source.length();
    int sourceIndex = 0;
    long targetValue = 0;
    long newTargetValue;
    char ch;
    int codePoint;
    boolean intoDigits = false;
    Sign sign = null;

    while (sourceIndex < length) {
      ch = source.charAt(sourceIndex);
      if (Character.isHighSurrogate(ch)) {
        if (++sourceIndex < length) {
          final char lowCh = source.charAt(sourceIndex);
          if (Character.isLowSurrogate(lowCh)) {
            codePoint = Character.toCodePoint(ch, lowCh);
          } else {
            throw ExceptionUtils.forHighSurrogateWithoutLowSurrogate(messageCode, targetTypeClass, source, ch);
          }
        } else {
          throw ExceptionUtils.forHighSurrogateWithoutLowSurrogate(messageCode, targetTypeClass, source, ch);
        }
      } else if (Character.isLowSurrogate(ch)) {
        throw ExceptionUtils.forLowSurrogateWithoutHighSurrogate(messageCode, targetTypeClass, source, ch);
      } else {
        codePoint = ch;
      }

      if (Character.isWhitespace(codePoint)) {
        switch (whiteSpace) {
          case REMOVE_WHITESPACE:
            ++sourceIndex;
            continue;
          default:
            throw ExceptionUtils.forInvalidCodePoint(messageCode, targetTypeClass, source, codePoint);
        }
      }

      // Check for negative
      if (sign == null && !ignoreLeadingNegativeSign && (codePoint == '-' || codePoint == Constants.MATH_MINUS || codePoint == Constants.HEAVY_MINUS) && !intoDigits) {
        sign = Sign.NEGATIVE;
        ++sourceIndex;
        continue;
      }

      // Check for positive
      if (sign == null && !ignoreLeadingPositiveSign && (codePoint == '+' || codePoint == Constants.HEAVY_PLUS) && !intoDigits) {
        sign = Sign.POSITIVE;
        ++sourceIndex;
        continue;
      }

      // Check for grouping separator (a.k.a thousands separator)
      if (codePoint == groupingSeparator || codePoint == altGroupingSeparator) {
        ++sourceIndex;
        continue;
      }

      if (ignoreCharactersSubset.contains(codePoint)) {
        ++sourceIndex;
        continue;
      }

      intoDigits = true;

      int value = numericRadixCodePointsMap.get(codePoint);
      if (value < 0) { // not found in map
        throw ExceptionUtils.forInvalidCodePoint(messageCode, targetTypeClass, source, codePoint);
      }

      if (sign == Sign.NEGATIVE) {
        newTargetValue = (targetValue * radix) - value;
        if (newTargetValue > targetValue) {
          // primitive overflow so number too small
          if (minValueComparisonInclusive) {
            throw ExceptionUtils.forValueMustBeGreaterThanOrEqualToMinValue(messageCode, targetTypeClass, source, minValue);
          } else {
            throw ExceptionUtils.forValueMustBeGreaterThanMinValue(messageCode, targetTypeClass, source, minValue);
          }
        }
      } else {
        newTargetValue = (targetValue * radix) + value;
        if (newTargetValue < targetValue) {
          // primitive overflow so number too large
          if (maxValueComparisonInclusive) {
            throw ExceptionUtils.forValueMustBeLessThanOrEqualToMaxValue(messageCode, targetTypeClass, source, maxValue);
          } else {
            throw ExceptionUtils.forValueMustBeLessThanMaxValue(messageCode, targetTypeClass, source, maxValue);
          }
        }
      }
      targetValue = newTargetValue;
      ++sourceIndex;
    }

    if (!intoDigits) {
      return null;
    }

    if (minValueComparisonInclusive) {
      if (targetValue < minValue) {
        throw ExceptionUtils.forValueMustBeGreaterThanOrEqualToMinValue(messageCode, targetTypeClass, source, minValue);
      }
    } else /* exclusive comparison */ {
      if (targetValue <= minValue) {
        throw ExceptionUtils.forValueMustBeGreaterThanMinValue(messageCode, targetTypeClass, source, minValue);
      }
    }

    if (maxValueComparisonInclusive) {
      if (targetValue > maxValue) {
        throw ExceptionUtils.forValueMustBeLessThanOrEqualToMaxValue(messageCode, targetTypeClass, source, maxValue);
      }
    } else /* exclusive comparison */ {
      if (targetValue >= maxValue) {
        throw ExceptionUtils.forValueMustBeLessThanMaxValue(messageCode, targetTypeClass, source, maxValue);
      }
    }

    return targetValue;
  }
}