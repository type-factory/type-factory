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

import java.math.BigDecimal;
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

  private static FractionalPart calculateNewFractionalPart(
      final int fractionalDigitCount, final int fractionalDigitValue, final FractionalPart fractionalPart) {

    if (fractionalDigitCount == 1) {
      if (fractionalDigitValue == 5) {
        return FractionalPart.EQUIDISTANT;
      } else if (fractionalDigitValue > 5) {
        return FractionalPart.UPPER;
      } else if (fractionalDigitValue > 0) {
        return FractionalPart.LOWER;
      }
    } else if (fractionalDigitValue > 0) {
      if (fractionalPart == FractionalPart.ZERO) {
        return FractionalPart.LOWER;
      } else if (fractionalPart == FractionalPart.EQUIDISTANT) {
        return FractionalPart.UPPER;
      }
    }
    return fractionalPart;
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
  public Long of(final Float value) throws InvalidValueException {
    if (value == null) {
      return null;
    }
    return roundValueAndCheckValueIsWithinBounds(value).longValue();
  }

  @Override
  public Long of(final Double value) throws InvalidValueException {
    if (value == null) {
      return null;
    }
    return roundValueAndCheckValueIsWithinBounds(value).longValue();
  }

  @Override
  public Long of(final BigDecimal value) throws InvalidValueException {
    if (value == null) {
      return null;
    }
    return roundValueAndCheckValueIsWithinBounds(value).longValue();
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
  @SuppressWarnings("java:S4276") // SonarQube Refactor this code to use the more specialised Functional Interface 'LongFunction<S>'
  public <S extends LongType> S of(
      final Short value,
      // Using Function<Long, S> instead of LongFunction<S> to allow for method references that accept a Long value that may be null.
      final Function<Long, S> constructorOrFactoryMethod) throws InvalidValueException {

    return value == null
        ? null
        : constructorOrFactoryMethod.apply(of(value));
  }

  @Override
  @SuppressWarnings("java:S4276") // SonarQube Refactor this code to use the more specialised Functional Interface 'LongFunction<S>'
  public <S extends LongType> S of(
      final Integer value,
      // Using Function<Long, S> instead of LongFunction<S> to allow for method references that accept a Long value that may be null.
      final Function<Long, S> constructorOrFactoryMethod) throws InvalidValueException {

    return value == null
        ? null
        : constructorOrFactoryMethod.apply(of(value));
  }

  @Override
  @SuppressWarnings("java:S4276") // SonarQube Refactor this code to use the more specialised Functional Interface 'LongFunction<S>'
  public <S extends LongType> S of(
      final Long value,
      // Using Function<Long, S> instead of LongFunction<S> to allow for method references that accept a Long value that may be null.
      final Function<Long, S> constructorOrFactoryMethod) throws InvalidValueException {

    return value == null
        ? null
        : constructorOrFactoryMethod.apply(of(value));
  }

  @Override
  @SuppressWarnings("java:S4276") // SonarQube Refactor this code to use the more specialised Functional Interface 'LongFunction<S>'
  public <S extends LongType> S of(final BigInteger value,
      // Using Function<Long, S> instead of LongFunction<S> to allow for method references that accept a Long value that may be null.
      final Function<Long, S> constructorOrFactoryMethod) throws InvalidValueException {

    return value == null
        ? null
        : constructorOrFactoryMethod.apply(of(value));
  }

  @Override
  @SuppressWarnings("java:S4276") // SonarQube Refactor this code to use the more specialised Functional Interface 'LongFunction<S>'
  public <S extends LongType> S of(final Float value,
      // Using Function<Long, S> instead of LongFunction<S> to allow for method references that accept a Long value that may be null.
      final Function<Long, S> constructorOrFactoryMethod) throws InvalidValueException {

    return value == null
        ? null
        : constructorOrFactoryMethod.apply(of(value));
  }

  @Override
  @SuppressWarnings("java:S4276") // SonarQube Refactor this code to use the more specialised Functional Interface 'LongFunction<S>'
  public <S extends LongType> S of(final Double value,
      // Using Function<Long, S> instead of LongFunction<S> to allow for method references that accept a Long value that may be null.
      final Function<Long, S> constructorOrFactoryMethod) throws InvalidValueException {

    return value == null
        ? null
        : constructorOrFactoryMethod.apply(of(value));
  }

  @Override
  @SuppressWarnings("java:S4276") // SonarQube Refactor this code to use the more specialised Functional Interface 'LongFunction<S>'
  public <S extends LongType> S of(final BigDecimal value,
      // Using Function<Long, S> instead of LongFunction<S> to allow for method references that accept a Long value that may be null.
      final Function<Long, S> constructorOrFactoryMethod) throws InvalidValueException {

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

  @SuppressWarnings("java:S4276") // SonarQube Refactor this code to use the more specialised Functional Interface 'LongFunction<S>'
  public <T extends LongType> T parse(
      final CharSequence value,
      // Using Function<Long, S> instead of LongFunction<S> to allow for method references that accept a Long value that may be null.
      final Function<Long, T> constructorOrFactoryMethod) throws InvalidValueException {

    final Long parsedValue = parse(value);
    return parsedValue == null
        ? null
        : constructorOrFactoryMethod.apply(parsedValue);
  }

  @SuppressWarnings("java:S4276") // SonarQube Refactor this code to use the more specialised Functional Interface 'LongFunction<S>'
  public <T extends LongType> T parse(
      final CharSequence value,
      final Locale locale,
      // Using Function<Long, S> instead of LongFunction<S> to allow for method references that accept a Long value that may be null.
      final Function<Long, T> constructorOrFactoryMethod) throws InvalidValueException {

    return parse(value, NumberFormat.of(locale), constructorOrFactoryMethod);
  }

  @SuppressWarnings("java:S4276") // SonarQube Refactor this code to use the more specialised Functional Interface 'LongFunction<S>'
  public <T extends LongType> T parse(
      final CharSequence value,
      final NumberFormat numberFormat,
      // Using Function<Long, S> instead of LongFunction<S> to allow for method references that accept a Long value that may be null.
      final Function<Long, T> constructorOrFactoryMethod) throws InvalidValueException {

    final Long parsedValue = parseImpl(value, numberFormat == null ? defaultNumberFormat : numberFormat);
    return parsedValue == null
        ? null
        : constructorOrFactoryMethod.apply(parsedValue);
  }

  private static class ParseState {

    final NumberFormat numberFormat;
    final int numberFormatNegativePrefixLength;
    final int numberFormatPositivePrefixLength;
    final int firstNegativePrefixCodepoint;
    final int firstPositivePrefixCodepoint;
    final CharSequence source;
    final int sourceLength;
    int sourceIndex = 0;
    int codePoint;
    int lookAheadSourceIndex = 0;
    int lookAheadCodePoint;
    long targetValue = 0;
    int fractionalDigitCount = 0;
    boolean intoDigits = false;
    boolean intoFractional = false;
    FractionalPart fractionalPart = FractionalPart.ZERO;
    Sign sign = null;

    ParseState(final CharSequence source, final NumberFormat numberFormat) {
      this.numberFormat = numberFormat;
      this.numberFormatNegativePrefixLength = numberFormat.getNegativePrefixCodePointsLength();
      this.numberFormatPositivePrefixLength = numberFormat.getPositivePrefixCodePointsLength();
      this.firstNegativePrefixCodepoint = numberFormat.getNegativePrefixCodePointAt(0);
      this.firstPositivePrefixCodepoint = numberFormat.getPositivePrefixCodePointAt(0);
      this.source = source;
      this.sourceLength = source.length();
    }
  }

  private Long parseImpl(final CharSequence source, final NumberFormat numberFormat) throws InvalidValueException {
    if (source == null || source.isEmpty()) {
      return null;
    }
    return parseImpl(new ParseState(source, numberFormat));
  }

  // Suppress SonarCloud issues:
  // - "java:S3776 Cognitive Complexity of methods should not be too high"
  // - "java:S6541 Methods should not perform too many tasks (aka Brain method)"
  // - "java:S135  Loops should not contain more than a single "break" or "continue" statement"
  @SuppressWarnings({"java:S3776", "java:S6541", "java:S135"})
  private Long parseImpl(final ParseState parseState) throws InvalidValueException {

    while (parseState.sourceIndex < parseState.sourceLength) {
      advanceToNextCodePoint(parseState, false);

      if (parseState.numberFormat.isGroupingSeparator(parseState.codePoint)) {
        ++parseState.sourceIndex;
        continue;
      }

      if (Character.isWhitespace(parseState.codePoint) || Character.isSpaceChar(parseState.codePoint)) {
        if (whiteSpace == WhiteSpace.IGNORE_WHITESPACE) {
          ++parseState.sourceIndex;
          continue;
        }
        throw ExceptionUtils.forInvalidCodePoint(messageCode, targetTypeClass, parseState.source, parseState.codePoint);
      }

      if (ignoreCharactersSubset.contains(parseState.codePoint)) {
        ++parseState.sourceIndex;
        continue;
      }

      // Check for negative
      if (parseState.sign == null
          && !ignoreLeadingNegativeSign
          && !parseState.intoDigits
          && isCodePointEqualToAnotherOrSimilar(parseState.codePoint, parseState.firstNegativePrefixCodepoint)) {
        int j = 1;
        parseState.lookAheadSourceIndex = parseState.sourceIndex + 1;
        while (j < parseState.numberFormatNegativePrefixLength && parseState.lookAheadSourceIndex < parseState.sourceLength) {
          advanceToNextCodePoint(parseState, true);
          if (!isCodePointEqualToAnotherOrSimilar(parseState.lookAheadCodePoint, parseState.numberFormat.getNegativePrefixCodePointAt(j))) {
            break;
          }
          ++j;
          ++parseState.lookAheadSourceIndex;
        }
        if (j == parseState.numberFormat.getNegativePrefixCodePointsLength()) {
          parseState.sign = Sign.NEGATIVE;
          parseState.sourceIndex = parseState.lookAheadSourceIndex;
          continue;
        }
      }

      // Check for positive
      if (parseState.sign == null
          && !ignoreLeadingPositiveSign
          && !parseState.intoDigits
          && isCodePointEqualToAnotherOrSimilar(parseState.codePoint, parseState.firstPositivePrefixCodepoint)) {
        int j = 1;
        parseState.lookAheadSourceIndex = parseState.sourceIndex + 1;
        while (j < parseState.numberFormatPositivePrefixLength && parseState.lookAheadSourceIndex < parseState.sourceLength) {
          advanceToNextCodePoint(parseState, true);
          if (!isCodePointEqualToAnotherOrSimilar(parseState.lookAheadCodePoint, parseState.numberFormat.getPositivePrefixCodePointAt(j))) {
            break;
          }
          ++j;
          ++parseState.lookAheadSourceIndex;
        }
        if (j == parseState.numberFormat.getPositivePrefixCodePointsLength()) {
          parseState.sign = Sign.POSITIVE;
          parseState.sourceIndex = parseState.lookAheadSourceIndex;
          continue;
        }
      }

      if (parseState.numberFormat.isDecimalSeparator(parseState.codePoint)) {
        if (radix != 10) {
          throw ExceptionUtils.forDecimalPointNotPermittedForNonBaseTenNumbers(messageCode, targetTypeClass, parseState.source, parseState.codePoint);
        }
        if (parseState.intoFractional) {
          throw ExceptionUtils.forMultipleDecimalPoints(messageCode, targetTypeClass, parseState.source, parseState.codePoint);
        }
        parseState.intoFractional = true;
        ++parseState.sourceIndex;
        continue;
      }

      parseState.intoDigits = true;

      int digitValue = numericRadixCodePointsMap.get(parseState.codePoint);
      if (digitValue < 0 && allowAllUnicodeDecimalDigits) { // not found in map
        digitValue = Character.digit(parseState.codePoint, radix);
      }
      if (digitValue < 0) {
        throw ExceptionUtils.forInvalidCodePoint(messageCode, targetTypeClass, parseState.source, parseState.codePoint);
      }

      if (parseState.intoFractional) {
        ++parseState.fractionalDigitCount;
        parseState.fractionalPart = calculateNewFractionalPart(parseState.fractionalDigitCount, digitValue, parseState.fractionalPart);
        ++parseState.sourceIndex;
        continue;
      }

      parseState.targetValue = calculateNewTargetValueProvidedNextDigitValue(parseState, digitValue);
      ++parseState.sourceIndex;
    }

    if (!parseState.intoDigits) {
      return null;
    }

    if (parseState.intoFractional) {
      if (parseState.fractionalPart != FractionalPart.ZERO) {
        final long roundingValue = getRoundingValue(parseState);
        if (roundingValue != 0) {
          parseState.targetValue = calculateNewTargetValueByApplyingRoundingValue(parseState, roundingValue);
        }
      }
      checkValueIsWithinBounds(parseState.targetValue, parseState.source, parseState.numberFormat.getRoundingMode());
    } else {
      checkValueIsWithinBounds(parseState.targetValue, parseState.source, null);
    }
    return parseState.targetValue;
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
   * @param parseState the parse state containing the source CharSequence and NumberFormat along with all the state variables required for parsing.
   * @param lookAhead  if true, the method will not update the {@code lookAheadCodePoint} and {@code lookAheadSourceIndex} in the {@code parseState}.
   *                   It will leave untouched the {@code codePoint} and {@code sourceIndex}.
   */
  private void advanceToNextCodePoint(final ParseState parseState, final boolean lookAhead) {
    int tempIndex = lookAhead ? parseState.lookAheadSourceIndex : parseState.sourceIndex;
    final char ch = parseState.source.charAt(tempIndex);
    int codePoint;
    if (Character.isHighSurrogate(ch)) {
      if (++tempIndex < parseState.source.length()) {
        final char lowCh = parseState.source.charAt(tempIndex);
        if (Character.isLowSurrogate(lowCh)) {
          codePoint = Character.toCodePoint(ch, lowCh);
        } else {
          throw ExceptionUtils.forHighSurrogateWithoutLowSurrogate(messageCode, targetTypeClass, parseState.source, ch);
        }
      } else {
        throw ExceptionUtils.forHighSurrogateWithoutLowSurrogate(messageCode, targetTypeClass, parseState.source, ch);
      }
    } else if (Character.isLowSurrogate(ch)) {
      throw ExceptionUtils.forLowSurrogateWithoutHighSurrogate(messageCode, targetTypeClass, parseState.source, ch);
    } else {
      codePoint = ch;
    }
    if (lookAhead) {
      parseState.lookAheadCodePoint = codePoint;
      parseState.lookAheadSourceIndex = tempIndex;
    } else {
      parseState.codePoint = codePoint;
      parseState.sourceIndex = tempIndex;
    }
  }

  private long calculateNewTargetValueProvidedNextDigitValue(
      final ParseState parseState,
      final int nextDigitValue) {

    final long newTargetValue = parseState.sign == Sign.NEGATIVE
        ? (parseState.targetValue * radix) - nextDigitValue
        : (parseState.targetValue * radix) + nextDigitValue;
    checkThatPrimitiveOverflowOrUnderflowHasNotOccurred(parseState, newTargetValue);
    return newTargetValue;
  }

  private long calculateNewTargetValueByApplyingRoundingValue(
      final ParseState parseState,
      final long roundingValue) {

    long newTargetValue = parseState.targetValue + roundingValue;
    checkThatPrimitiveOverflowOrUnderflowHasNotOccurred(parseState, newTargetValue);
    return newTargetValue;
  }

  private void checkThatPrimitiveOverflowOrUnderflowHasNotOccurred(
      final ParseState parseState,
      final long newTargetValue) throws InvalidValueException {

    if (parseState.sign == Sign.NEGATIVE) {
      if (newTargetValue > parseState.targetValue) {
        // primitive underflow so number too small
        final RoundingMode roundingMode = sourceContainsDecimalPoint(parseState) ? parseState.numberFormat.getRoundingMode() : null;
        if (minValueComparisonInclusive) {
          throw ExceptionUtils.forValueMustBeGreaterThanOrEqualToMinValue(messageCode, targetTypeClass, parseState.source, minValue, roundingMode);
        } else {
          throw ExceptionUtils.forValueMustBeGreaterThanMinValue(messageCode, targetTypeClass, parseState.source, minValue, roundingMode);
        }
      }
    } else {
      if (newTargetValue < parseState.targetValue) {
        // primitive overflow so number too large
        final RoundingMode roundingMode = sourceContainsDecimalPoint(parseState) ? parseState.numberFormat.getRoundingMode() : null;
        if (maxValueComparisonInclusive) {
          throw ExceptionUtils.forValueMustBeLessThanOrEqualToMaxValue(messageCode, targetTypeClass, parseState.source, maxValue, roundingMode);
        } else {
          throw ExceptionUtils.forValueMustBeLessThanMaxValue(messageCode, targetTypeClass, parseState.source, maxValue, roundingMode);
        }
      }
    }
  }

  private boolean sourceContainsDecimalPoint(final ParseState parseState) {
    if (parseState.intoFractional) {
      return true;
    }
    parseState.lookAheadSourceIndex = parseState.sourceIndex + 1;
    while (parseState.lookAheadSourceIndex < parseState.sourceLength) {
      advanceToNextCodePoint(parseState, true);
      if (parseState.numberFormat.isDecimalSeparator(parseState.lookAheadCodePoint)) {
        return true;
      }
      ++parseState.lookAheadSourceIndex;
    }
    return false;
  }

  private long getRoundingValue(
      final ParseState parseState) throws InvalidValueException {

    if (parseState.sign == Sign.NEGATIVE) {
      return switch (parseState.numberFormat.getRoundingMode()) {
        case CEILING, DOWN -> 0;
        case FLOOR, UP -> parseState.fractionalPart == FractionalPart.ZERO ? 0 : -1;
        case HALF_DOWN -> parseState.fractionalPart == FractionalPart.UPPER ? -1 : 0;
        case HALF_EVEN -> switch (parseState.fractionalPart) {
          case LOWER -> 0;
          case UPPER -> -1;
          default -> parseState.targetValue % 2 == 0 ? 0 : -1;
        };
        case HALF_UP -> parseState.fractionalPart == FractionalPart.LOWER ? 0 : -1;
        case UNNECESSARY -> {
          if (parseState.fractionalPart != FractionalPart.ZERO) {
            throw ExceptionUtils.forExpectingWholeNumber(
                messageCode, targetTypeClass, parseState.source, parseState.numberFormat.getDecimalSeparators());
          }
          yield 0;
        }
      };
    }

    return switch (parseState.numberFormat.getRoundingMode()) {
      case CEILING, UP -> parseState.fractionalPart == FractionalPart.ZERO ? 0 : 1;
      case DOWN, FLOOR -> 0;
      case HALF_DOWN -> parseState.fractionalPart == FractionalPart.UPPER ? 1 : 0;
      case HALF_EVEN -> switch (parseState.fractionalPart) {
        case LOWER -> 0;
        case UPPER -> 1;
        default -> parseState.targetValue % 2 == 0 ? 0 : 1;
      };
      case HALF_UP -> parseState.fractionalPart == FractionalPart.LOWER ? 0 : 1;
      case UNNECESSARY -> {
        if (parseState.fractionalPart != FractionalPart.ZERO) {
          throw ExceptionUtils.forExpectingWholeNumber(
              messageCode, targetTypeClass, parseState.source, parseState.numberFormat.getDecimalSeparators());
        }
        yield 0;
      }
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

  void checkValueIsWithinBounds(final Short value) throws InvalidValueException {
    checkValueIsWithinBounds(value.longValue());
  }

  void checkValueIsWithinBounds(final Integer value) throws InvalidValueException {
    checkValueIsWithinBounds(value.longValue());
  }

  void checkValueIsWithinBounds(final Long value) throws InvalidValueException {
    checkValueIsWithinBounds(value.longValue());
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
  BigInteger roundValueAndCheckValueIsWithinBounds(final Number value) throws InvalidValueException {

    final BigDecimal bigDecimalValue = value instanceof BigDecimal bigDecimal ? bigDecimal : BigDecimal.valueOf(value.doubleValue());
    final BigInteger roundedValue = bigDecimalValue.setScale(0, defaultNumberFormat.getRoundingMode()).toBigIntegerExact();

    if (minValueComparisonInclusive) {
      if (roundedValue.compareTo(BigInteger.valueOf(minValue)) < 0) {
        throw ExceptionUtils.forValueMustBeGreaterThanOrEqualToMinValue(
            messageCode, targetTypeClass, value, minValue, defaultNumberFormat.getRoundingMode());
      }
    } else /* exclusive comparison */ {
      if (roundedValue.compareTo(BigInteger.valueOf(minValue)) <= 0) {
        throw ExceptionUtils.forValueMustBeGreaterThanMinValue(
            messageCode, targetTypeClass, value, minValue, defaultNumberFormat.getRoundingMode());
      }
    }

    if (maxValueComparisonInclusive) {
      if (roundedValue.compareTo(BigInteger.valueOf(maxValue)) > 0) {
        throw ExceptionUtils.forValueMustBeLessThanOrEqualToMaxValue(
            messageCode, targetTypeClass, value, maxValue, defaultNumberFormat.getRoundingMode());
      }
    } else /* exclusive comparison */ {
      if (roundedValue.compareTo(BigInteger.valueOf(maxValue)) >= 0) {
        throw ExceptionUtils.forValueMustBeLessThanMaxValue(
            messageCode, targetTypeClass, value, maxValue, defaultNumberFormat.getRoundingMode());
      }
    }
    return roundedValue;
  }

  @SuppressWarnings("Duplicates")
    // Duplicates are necessary to avoid boxing/unboxing
  <T extends CharSequence> void checkValueIsWithinBounds(final long targetValue, final T sourceValue, final RoundingMode roundingMode)
      throws InvalidValueException {
    if (minValueComparisonInclusive) {
      if (targetValue < minValue) {
        throw ExceptionUtils.forValueMustBeGreaterThanOrEqualToMinValue(messageCode, targetTypeClass, sourceValue, minValue, roundingMode);
      }
    } else /* exclusive comparison */ {
      if (targetValue <= minValue) {
        throw ExceptionUtils.forValueMustBeGreaterThanMinValue(messageCode, targetTypeClass, sourceValue, minValue, roundingMode);
      }
    }

    if (maxValueComparisonInclusive) {
      if (targetValue > maxValue) {
        throw ExceptionUtils.forValueMustBeLessThanOrEqualToMaxValue(messageCode, targetTypeClass, sourceValue, maxValue, roundingMode);
      }
    } else /* exclusive comparison */ {
      if (targetValue >= maxValue) {
        throw ExceptionUtils.forValueMustBeLessThanMaxValue(messageCode, targetTypeClass, sourceValue, maxValue, roundingMode);
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

  enum FractionalPart {
    ZERO,
    LOWER,
    EQUIDISTANT,
    UPPER
  }
}
