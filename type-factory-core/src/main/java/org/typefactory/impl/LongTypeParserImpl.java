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

import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.function.Function;
import org.typefactory.InvalidValueException;
import org.typefactory.LongType;
import org.typefactory.LongTypeParser;
import org.typefactory.MessageCode;
import org.typefactory.NumberFormat;
import org.typefactory.Subset;

final class LongTypeParserImpl implements LongTypeParser {

  private final Class<?> targetTypeClass;
  private final MessageCode messageCode;
  private final NumberFormat defaultNumberFormat;
  private final WhiteSpace whiteSpace;
  private final Subset ignoreCharactersSubset;
  private final PrimitiveHashMapOfIntKeyToIntValue numericRadixCodePointsMap;
  private final int radix;
  private final boolean allowAllUnicodeDecimalDigits;
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
      final NumberFormat defaultNumberFormat,
      final WhiteSpace whiteSpace,
      final Subset ignoreCharactersSubset,
      final PrimitiveHashMapOfIntKeyToIntValue numericRadixCodePointsMap,
      final int[] numericRadixCodePoints,
      final boolean allowAllUnicodeDecimalDigits,
      final long minValue,
      final long maxValue,
      final boolean minValueComparisonInclusive,
      final boolean maxValueComparisonInclusive,
      final boolean ignoreLeadingNegativeSign,
      final boolean ignoreLeadingPositiveSign) {
    this.targetTypeClass = targetTypeClass;
    this.messageCode = messageCode;
    this.defaultNumberFormat = defaultNumberFormat;
    this.whiteSpace = whiteSpace;
    this.ignoreCharactersSubset = ignoreCharactersSubset;
    this.numericRadixCodePointsMap = numericRadixCodePointsMap;
    this.radix = numericRadixCodePoints.length;
    this.allowAllUnicodeDecimalDigits = allowAllUnicodeDecimalDigits;
    this.minValue = minValue;
    this.maxValue = maxValue;
    this.minValueComparisonInclusive = minValueComparisonInclusive;
    this.maxValueComparisonInclusive = maxValueComparisonInclusive;
    this.ignoreLeadingNegativeSign = ignoreLeadingNegativeSign;
    this.ignoreLeadingPositiveSign = ignoreLeadingPositiveSign;
  }

  @Override
  public int getRadix() {
    return radix;
  }

  @Override
  public Long of(final Short value) throws InvalidValueException {
    if (value == null) {
      return null;
    }
    checkValueIsWithinBounds(value);
    return value.longValue();
  }

  @Override
  public Long of(final Integer value) throws InvalidValueException {
    if (value == null) {
      return null;
    }
    checkValueIsWithinBounds(value);
    return value.longValue();
  }

  @Override
  public Long of(final Long value) throws InvalidValueException {
    if (value == null) {
      return null;
    }
    checkValueIsWithinBounds(value);
    return value;
  }

  @Override
  public Long of(final BigInteger value) throws InvalidValueException {
    if (value == null) {
      return null;
    }
    checkValueIsWithinBounds(value);
    return value.longValue();
  }

  @Override
  public long of(final short value) throws InvalidValueException {
    checkValueIsWithinBounds(value);
    return value;
  }

  @Override
  public long of(final int value) throws InvalidValueException {
    checkValueIsWithinBounds(value);
    return value;
  }

  @Override
  public long of(final long value) throws InvalidValueException {
    checkValueIsWithinBounds(value);
    return value;
  }

  @Override
  public <S extends LongType> S of(Short value, Function<Long, S> constructorOrFactoryMethod) throws InvalidValueException {
    return value == null
        ? null
        : constructorOrFactoryMethod.apply(of(value));
  }

  @Override
  public <S extends LongType> S of(Integer value, Function<Long, S> constructorOrFactoryMethod) throws InvalidValueException {
    return value == null
        ? null
        : constructorOrFactoryMethod.apply(of(value));
  }

  @Override
  public <S extends LongType> S of(Long value, Function<Long, S> constructorOrFactoryMethod) throws InvalidValueException {
    return value == null
        ? null
        : constructorOrFactoryMethod.apply(of(value));
  }

  @Override
  public <S extends LongType> S of(BigInteger value, Function<Long, S> constructorOrFactoryMethod) throws InvalidValueException {
    return value == null
        ? null
        : constructorOrFactoryMethod.apply(of(value));
  }

  public Long parse(final CharSequence originalValue) throws InvalidValueException {
    return parseImpl(originalValue, defaultNumberFormat);
  }

  public Long parse(final CharSequence source, final Locale locale) throws InvalidValueException {
    return parse(source, NumberFormat.of(locale));
  }

  public Long parse(final CharSequence source, final NumberFormat numberFormat) throws InvalidValueException {
    return parseImpl(source, numberFormat == null ? defaultNumberFormat : numberFormat);
  }

  public <T extends LongType> T parse(final CharSequence value, Function<Long, T> constructorOrFactoryMethod) throws InvalidValueException {
    final Long parsedValue = parse(value);
    return parsedValue == null
        ? null
        : constructorOrFactoryMethod.apply(parsedValue);
  }

  public <T extends LongType> T parse(final CharSequence value, final Locale locale, Function<Long, T> constructorOrFactoryMethod)
      throws InvalidValueException {
    return parse(value, NumberFormat.of(locale), constructorOrFactoryMethod);
  }

  public <T extends LongType> T parse(final CharSequence value, final NumberFormat numberFormat, Function<Long, T> constructorOrFactoryMethod)
      throws InvalidValueException {
    final Long parsedValue = parseImpl(value, numberFormat == null ? defaultNumberFormat : numberFormat);
    return parsedValue == null
        ? null
        : constructorOrFactoryMethod.apply(parsedValue);
  }

  // Suppress SonarCloud issues:
  // - "java:S3776 Cognitive Complexity of methods should not be too high"
  // - "java:S6541 Methods should not perform too many tasks (aka Brain method)"
  // - "java:S135  Loops should not contain more than a single "break" or "continue" statement"
  @SuppressWarnings({"java:S3776", "java:S6541", "java:S135"})
  private Long parseImpl(final CharSequence source, final NumberFormat numberFormat) throws InvalidValueException {
    if (source == null || source.isEmpty()) {
      return null;
    }

    final int sourceLength = source.length();
    int sourceIndex = 0;
    long targetValue = 0;
    long newTargetValue;
    long fractionalValue = 0;
    int firstFractionalDigit = -1;
    boolean intoDigits = false;
    boolean intoFractional = false;
    int firstNegativePrefixCodepoint = numberFormat.getNegativePrefixCodePointAt(0);
    int firstPositivePrefixCodepoint = numberFormat.getPositivePrefixCodePointAt(0);
    Sign sign = null;
    int codePoint;
    long indexAndCodePoint;

    while (sourceIndex < sourceLength) {
      indexAndCodePoint = nextCodePointAndSourceIndex(source, sourceIndex);
      codePoint = (int) (indexAndCodePoint >> 32);
      sourceIndex = (int) indexAndCodePoint;

      if (Character.isWhitespace(codePoint) || Character.isSpaceChar(codePoint)) {
        if (whiteSpace == WhiteSpace.IGNORE_WHITESPACE) {
          ++sourceIndex;
          continue;
        }
        throw ExceptionUtils.forInvalidCodePoint(messageCode, targetTypeClass, source, codePoint);
      }

      if (ignoreCharactersSubset.contains(codePoint)) {
        ++sourceIndex;
        continue;
      }

      // Check for negative
      if (sign == null && !ignoreLeadingNegativeSign && !intoDigits && isCodePointEqualToAnotherOrSimilar(codePoint, firstNegativePrefixCodepoint)) {
        int j = 1;
        int tempCodePoint;
        int tempIndex = sourceIndex + 1;
        for (; j < numberFormat.getNegativePrefixCodePointsLength() && tempIndex < sourceLength; ++j) {
          indexAndCodePoint = nextCodePointAndSourceIndex(source, tempIndex);
          tempCodePoint = (int) (indexAndCodePoint >> 32);
          tempIndex = (int) indexAndCodePoint;
          if (!isCodePointEqualToAnotherOrSimilar(tempCodePoint, numberFormat.getNegativePrefixCodePointAt(j))) {
            break;
          }
          ++tempIndex;
        }
        if (j == numberFormat.getNegativePrefixCodePointsLength()) {
          sign = Sign.NEGATIVE;
          sourceIndex = tempIndex;
          continue;
        }
      }

      // Check for positive
      if (sign == null && !ignoreLeadingPositiveSign && !intoDigits && isCodePointEqualToAnotherOrSimilar(codePoint, firstPositivePrefixCodepoint)) {
        int j = 1;
        int tempCodePoint;
        int tempIndex = sourceIndex + 1;
        for (; j < numberFormat.getPositivePrefixCodePointsLength() && tempIndex < sourceLength; ++j) {
          indexAndCodePoint = nextCodePointAndSourceIndex(source, tempIndex);
          tempCodePoint = (int) (indexAndCodePoint >> 32);
          tempIndex = (int) indexAndCodePoint;
          if (!isCodePointEqualToAnotherOrSimilar(tempCodePoint, numberFormat.getPositivePrefixCodePointAt(j))) {
            break;
          }
          ++tempIndex;
        }
        if (j == numberFormat.getPositivePrefixCodePointsLength()) {
          sign = Sign.POSITIVE;
          sourceIndex = tempIndex;
          continue;
        }
      }

      if (numberFormat.isGroupingSeparator(codePoint)) {
        ++sourceIndex;
        continue;
      }

      if (numberFormat.isDecimalSeparator(codePoint)) {
        if (radix != 10) {
          throw ExceptionUtils.forDecimalPointNotPermittedForNonBaseTenNumbers(messageCode, targetTypeClass, source, codePoint);
        }
        if (intoFractional) {
          throw ExceptionUtils.forMultipleDecimalPoints(messageCode, targetTypeClass, source, codePoint);
        }
        intoFractional = true;
        continue;
      }

      intoDigits = true;

      int digitValue = numericRadixCodePointsMap.get(codePoint);
      if (digitValue < 0 && allowAllUnicodeDecimalDigits) { // not found in map
        digitValue = Character.digit(codePoint, radix);
      }
      if (digitValue < 0) {
        throw ExceptionUtils.forInvalidCodePoint(messageCode, targetTypeClass, source, codePoint);
      }

      if (intoFractional) {
        if (firstFractionalDigit < 0) {
          firstFractionalDigit = digitValue;
        }
        fractionalValue = (fractionalValue * radix) + digitValue;
        continue;
      }

      targetValue = calculateNewTargetValue(source, sign, targetValue, digitValue);
      ++sourceIndex;
    }

    if (!intoDigits) {
      return null;
    }

    if (fractionalValue != 0) {
      targetValue += getRoundingValue(source, sign, numberFormat.getRoundingMode(), numberFormat.getPrimaryDecimalSeparator(), targetValue,
          firstFractionalDigit, fractionalValue);
    }
    checkValueIsWithinBounds(targetValue, source);

    return targetValue;
  }

  boolean isCodePointEqualToAnotherOrSimilar(final int codePoint, final int otherCodePoint) {
    if (codePoint == otherCodePoint) {
      return true;
    }
    return switch (codePoint) {
      case Constants.HYPHEN_MINUS -> otherCodePoint == Constants.MATH_MINUS || otherCodePoint == Constants.HEAVY_MINUS;
      case Constants.MATH_MINUS -> otherCodePoint == Constants.HYPHEN_MINUS || otherCodePoint == Constants.HEAVY_MINUS;
      case Constants.HEAVY_MINUS -> otherCodePoint == Constants.HYPHEN_MINUS || otherCodePoint == Constants.MATH_MINUS;
      case Constants.PLUS -> otherCodePoint == Constants.HEAVY_PLUS;
      case Constants.HEAVY_PLUS -> otherCodePoint == Constants.PLUS;
      default -> false;
    };
  }

  /**
   * Returns both the new sourceIndex and the next code point in the source CharSequence.
   *
   * @param source      the character sequence to get the next code point from
   * @param sourceIndex the index of the current code point in the source CharSequence
   * @return a long value where the upper 32 bits are the next code point and the lower 32 bits are the new sourceIndex
   */
  private long nextCodePointAndSourceIndex(final CharSequence source, int sourceIndex) {
    final char ch = source.charAt(sourceIndex);
    int codePoint;
    if (Character.isHighSurrogate(ch)) {
      if (++sourceIndex < source.length()) {
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
    return (long) codePoint << 32 | sourceIndex;
  }

  private long calculateNewTargetValue(final CharSequence source, final Sign sign, final long targetValue, final int digitValue) {
    if (sign == Sign.NEGATIVE) {
      long newTargetValue = (targetValue * radix) - digitValue;
      if (newTargetValue > targetValue) {
        // primitive overflow so number too small
        if (minValueComparisonInclusive) {
          throw ExceptionUtils.forValueMustBeGreaterThanOrEqualToMinValue(messageCode, targetTypeClass, source, minValue);
        } else {
          throw ExceptionUtils.forValueMustBeGreaterThanMinValue(messageCode, targetTypeClass, source, minValue);
        }
      }
      return newTargetValue;
    }

    long newTargetValue = (targetValue * radix) + digitValue;
    if (newTargetValue < targetValue) {
      // primitive overflow so number too large
      if (maxValueComparisonInclusive) {
        throw ExceptionUtils.forValueMustBeLessThanOrEqualToMaxValue(messageCode, targetTypeClass, source, maxValue);
      } else {
        throw ExceptionUtils.forValueMustBeLessThanMaxValue(messageCode, targetTypeClass, source, maxValue);
      }
    }
    return newTargetValue;
  }

  private long getRoundingValue(
      final CharSequence source, final Sign sign, final RoundingMode roundingMode,
      final int decimalSeparatorCodePoint, long targetValue,
      final int firstFractionalDigit, final long fractionalValue) throws ArithmeticException {

    if (sign == Sign.NEGATIVE) {
      return switch (roundingMode) {
        case CEILING -> 0;
        case DOWN -> 0;
        case FLOOR -> fractionalValue > 0 ? -1 : 0;
        case HALF_DOWN -> firstFractionalDigit > 5 ? -1 : 0;
        case HALF_EVEN -> {
          if (firstFractionalDigit < 5) {
            yield 0;
          }
          if (firstFractionalDigit > 5) {
            yield -1;
          }
          yield targetValue % 2 == 0 ? 0 : -1;
        }
        case HALF_UP -> firstFractionalDigit >= 5 ? -1 : 0;
        case UNNECESSARY -> {
          if (fractionalValue != 0) {
            throw ExceptionUtils.forExpectingWholeNumber(messageCode, targetTypeClass, source, decimalSeparatorCodePoint, fractionalValue);
          }
          yield 0;
        }
        case UP -> fractionalValue > 0 ? -1 : 0;
      };
    }

    return switch (roundingMode) {
      case CEILING -> fractionalValue > 0 ? 1 : 0;
      case DOWN -> 0;
      case FLOOR -> 0;
      case HALF_DOWN -> firstFractionalDigit > 5 ? 1 : 0;
      case HALF_EVEN -> {
        if (firstFractionalDigit < 5) {
          yield 0;
        }
        if (firstFractionalDigit > 5) {
          yield 1;
        }
        yield targetValue % 2 == 0 ? 0 : 1;
      }
      case HALF_UP -> firstFractionalDigit >= 5 ? 1 : 0;
      case UNNECESSARY -> {
        if (fractionalValue != 0) {
          throw ExceptionUtils.forExpectingWholeNumber(messageCode, targetTypeClass, source, decimalSeparatorCodePoint, fractionalValue);
        }
        yield 0;
      }
      case UP -> fractionalValue != 0 ? 1 : 0;
    };
  }

  @SuppressWarnings("Duplicates")
    // Duplicates are necessary to avoid boxing/unboxing
  void checkValueIsWithinBounds(final long value) throws InvalidValueException {
    if (minValueComparisonInclusive) {
      if (value < minValue) {
        throw ExceptionUtils.forValueMustBeGreaterThanOrEqualToMinValue(messageCode, targetTypeClass, value, minValue);
      }
    } else /* exclusive comparison */ {
      if (value <= minValue) {
        throw ExceptionUtils.forValueMustBeGreaterThanMinValue(messageCode, targetTypeClass, value, minValue);
      }
    }

    if (maxValueComparisonInclusive) {
      if (value > maxValue) {
        throw ExceptionUtils.forValueMustBeLessThanOrEqualToMaxValue(messageCode, targetTypeClass, value, maxValue);
      }
    } else /* exclusive comparison */ {
      if (value >= maxValue) {
        throw ExceptionUtils.forValueMustBeLessThanMaxValue(messageCode, targetTypeClass, value, maxValue);
      }
    }
  }

  @SuppressWarnings("Duplicates")
    // Duplicates are necessary to avoid boxing/unboxing
  <N extends Number> void checkValueIsWithinBounds(final N value) throws InvalidValueException {

    if (minValueComparisonInclusive) {
      if (value.longValue() < minValue) {
        throw ExceptionUtils.forValueMustBeGreaterThanOrEqualToMinValue(messageCode, targetTypeClass, value, minValue);
      }
    } else /* exclusive comparison */ {
      if (value.longValue() <= minValue) {
        throw ExceptionUtils.forValueMustBeGreaterThanMinValue(messageCode, targetTypeClass, value, minValue);
      }
    }

    if (maxValueComparisonInclusive) {
      if (value.longValue() > maxValue) {
        throw ExceptionUtils.forValueMustBeLessThanOrEqualToMaxValue(messageCode, targetTypeClass, value, maxValue);
      }
    } else /* exclusive comparison */ {
      if (value.longValue() >= maxValue) {
        throw ExceptionUtils.forValueMustBeLessThanMaxValue(messageCode, targetTypeClass, value, maxValue);
      }
    }
  }

  @SuppressWarnings("Duplicates")
    // Duplicates are necessary to avoid boxing/unboxing
  void checkValueIsWithinBounds(final BigInteger value) throws InvalidValueException {

    if (minValueComparisonInclusive) {
      if (value.compareTo(BigInteger.valueOf(minValue)) < 0) {
        throw ExceptionUtils.forValueMustBeGreaterThanOrEqualToMinValue(messageCode, targetTypeClass, value, minValue);
      }
    } else /* exclusive comparison */ {
      if (value.compareTo(BigInteger.valueOf(minValue)) <= 0) {
        throw ExceptionUtils.forValueMustBeGreaterThanMinValue(messageCode, targetTypeClass, value, minValue);
      }
    }

    if (maxValueComparisonInclusive) {
      if (value.compareTo(BigInteger.valueOf(maxValue)) > 0) {
        throw ExceptionUtils.forValueMustBeLessThanOrEqualToMaxValue(messageCode, targetTypeClass, value, maxValue);
      }
    } else /* exclusive comparison */ {
      if (value.compareTo(BigInteger.valueOf(maxValue)) >= 0) {
        throw ExceptionUtils.forValueMustBeLessThanMaxValue(messageCode, targetTypeClass, value, maxValue);
      }
    }
  }

  @SuppressWarnings("Duplicates")
    // Duplicates are necessary to avoid boxing/unboxing
  <T extends CharSequence> void checkValueIsWithinBounds(final long targetValue, final T sourceValue) throws InvalidValueException {
    if (minValueComparisonInclusive) {
      if (targetValue < minValue) {
        throw ExceptionUtils.forValueMustBeGreaterThanOrEqualToMinValue(messageCode, targetTypeClass, sourceValue, minValue);
      }
    } else /* exclusive comparison */ {
      if (targetValue <= minValue) {
        throw ExceptionUtils.forValueMustBeGreaterThanMinValue(messageCode, targetTypeClass, sourceValue, minValue);
      }
    }

    if (maxValueComparisonInclusive) {
      if (targetValue > maxValue) {
        throw ExceptionUtils.forValueMustBeLessThanOrEqualToMaxValue(messageCode, targetTypeClass, sourceValue, maxValue);
      }
    } else /* exclusive comparison */ {
      if (targetValue >= maxValue) {
        throw ExceptionUtils.forValueMustBeLessThanMaxValue(messageCode, targetTypeClass, sourceValue, maxValue);
      }
    }
  }

  enum WhiteSpace {
    FORBID_WHITESPACE,
    IGNORE_WHITESPACE
  }

  private enum Sign {
    NEGATIVE,
    POSITIVE
  }
}
