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

import static org.typefactory.impl.NumericNullHandling.PRESERVE_NULL;
import static org.typefactory.impl.NumericNullHandling.PRESERVE_NULL_AND_CONVERT_EMPTY_TO_NULL;

import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;
import org.typefactory.IntegerType;
import org.typefactory.InvalidValueException;
import org.typefactory.LongType;
import org.typefactory.MessageCode;
import org.typefactory.ShortType;
import org.typefactory.Subset;

final class IntegralNumericTypeParserImpl {

  private final Class<?> targetTypeClass;
  private final MessageCode messageCode;
  private final Locale defaultLocale;
  private final DecimalFormatSymbols defaultDecimalFormatSymbols;
  private final boolean caseSensitive;
  private final WhiteSpace whiteSpace;
  private final NumericNullHandling nullHandling;
  private final Subset ignoreCharactersSubset;
  private final PrimitiveHashMapOfIntKeyToIntValue numericRadixCodePointsMap;
  private final int [] numericRadixCodePoints;
  private final int radix;
  private final long defaultMinValue;
  private final long defaultMaxValue;
  private final long minValue;
  private final long maxValue;
  private final boolean minValueComparisonInclusive;
  private final boolean maxValueComparisonInclusive;
//  private boolean allowNegative;

  @SuppressWarnings("java:S107")
    // Suppress SonaQube "Methods should not have too many parameters" because this constructor is called by a builder
  IntegralNumericTypeParserImpl(
      final Class<?> targetTypeClass,
      final MessageCode messageCode,
      final Locale defaultLocale,
      final boolean caseSensitive,
      final WhiteSpace whiteSpace,
      final NumericNullHandling nullHandling,
      final Subset ignoreCharactersSubset,
      final PrimitiveHashMapOfIntKeyToIntValue numericRadixCodePointsMap,
      final int [] numericRadixCodePoints,
      final long defaultMinValue,
      final long defaultMaxValue,
      final long minValue,
      final long maxValue,
      final boolean minValueComparisonInclusive,
      final boolean maxValueComparisonInclusive) {
    this.targetTypeClass = targetTypeClass;
    this.messageCode = messageCode;
    this.defaultLocale = defaultLocale == null ? Locale.getDefault() : defaultLocale;
    this.defaultDecimalFormatSymbols = DecimalFormatSymbols.getInstance(this.defaultLocale);
    this.caseSensitive = caseSensitive;
    this.whiteSpace = whiteSpace;
    this.nullHandling = nullHandling;
    this.ignoreCharactersSubset = ignoreCharactersSubset;
    this.numericRadixCodePointsMap = numericRadixCodePointsMap;
    this.numericRadixCodePoints = numericRadixCodePoints;
    this.radix = numericRadixCodePoints.length;
    this.defaultMinValue = defaultMinValue;
    this.defaultMaxValue = defaultMaxValue;
    this.minValue = minValue;
    this.maxValue = maxValue;
    this.minValueComparisonInclusive = minValueComparisonInclusive;
    this.maxValueComparisonInclusive = maxValueComparisonInclusive;
//    this.allowNegative = minValueComparisonInclusive ? minValue < 0 : minValue < -1;
  }

  public <T extends ShortType> T parseToShortType(final CharSequence value, Function<Short, T> constructorOrFactoryMethod)
      throws InvalidValueException {
    final Short parsedValue = parseToShort(value);
    return parsedValue == null
        ? null
        : constructorOrFactoryMethod.apply(parsedValue);
  }

  public <T extends IntegerType> T parseToIntegerType(final CharSequence value, IntFunction<T> constructorOrFactoryMethod)
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

  public Short parseToShort(final CharSequence originalValue) throws InvalidValueException {
    final Long parsedValue = parse(originalValue);
    if (parsedValue == null) {
      return null;
    }
    return parsedValue.shortValue();
  }

  public Integer parseToInteger(final CharSequence originalValue) throws InvalidValueException {
    final Long parsedValue = parse(originalValue);
    if (parsedValue == null) {
      return null;
    }
    return parsedValue.intValue();
  }

  public Long parseToLong(final CharSequence originalValue) throws InvalidValueException {
    final Long parsedValue = parse(originalValue);
    if (parsedValue == null) {
      return null;
    }
    return parsedValue;
  }

  // Suppress SonarQube "java:S3776 Cognitive Complexity of methods should not be too high"
  @SuppressWarnings({"java:S3776"})
  private Long parse(final CharSequence source) throws InvalidValueException {
    if (source == null) {
      if (nullHandling == PRESERVE_NULL) {
        return null;
      }
    } else if (source.isEmpty()) {
      if (nullHandling == PRESERVE_NULL_AND_CONVERT_EMPTY_TO_NULL) {
        return null;
      }
    }

    final int groupingSeparator = defaultDecimalFormatSymbols.getGroupingSeparator();
    final int altGroupingSeparator = groupingSeparator == '\u2019' ? '\'' : -1;

    final int length = source.length();
    int sourceIndex = 0;
    long targetValue = 0;
    long newTargetValue = 0;
    char ch;
    int codePoint;
    boolean intoDigits = false;
    boolean negative = false;

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

      // Check for negative looking for either hyphen-minus sign (U+002D) or math-minus sign (U+2212)
      if ((codePoint == '-' || codePoint == '\u2212') && !intoDigits && !negative) {
        negative = true;
        ++sourceIndex;
        continue;
      }

      // Check for positive looking for plus sign (U+002B)
      if (codePoint == '+' && !intoDigits && !negative) {
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

      if (negative) {
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
