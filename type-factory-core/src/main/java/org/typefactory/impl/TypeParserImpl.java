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

import static java.lang.Character.isWhitespace;
import static org.typefactory.impl.Constants.REPLACEMENT_CHARACTER;
import static org.typefactory.impl.InvalidCharactersAction.FAIL_ON_INVALID_CHARACTERS;
import static org.typefactory.impl.InvalidCharactersAction.REMOVE_INVALID_CHARACTERS;
import static org.typefactory.impl.InvalidCharactersAction.REPLACE_INVALID_CHARACTERS;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import org.typefactory.IntegerType;
import org.typefactory.InvalidValueException;
import org.typefactory.LongType;
import org.typefactory.MessageCode;
import org.typefactory.ParseResult;
import org.typefactory.ShortType;
import org.typefactory.StringType;
import org.typefactory.Subset;
import org.typefactory.TypeParser;
import org.typefactory.impl.Converter.ConverterResults;

final class TypeParserImpl implements TypeParser {

  private final Class<?> targetTypeClass;
  private final MessageCode messageCode;
  private final TargetCase targetCase;
  private final WhiteSpace whiteSpace;
  private final NullHandling nullHandling;
  private final Normalizer.Form targetCharacterNormalizationForm;
  private final int minNumberOfCodePoints;
  private final int maxNumberOfCodePoints;
  private final int convertAnyDecimalDigitsToDigitsStartingWithZeroDigit;

  private final Pattern regex;

  private final Predicate<String> validationFunction;
  private final Subset acceptedCodePoints;
  private final Converter converter;

  @SuppressWarnings("java:S107")
    // Suppress SonaQube "Methods should not have too many parameters" because this constructor is called by a builder
  TypeParserImpl(
      final Class<?> targetTypeClass,
      final MessageCode messageCode,
      final TargetCase targetCase,
      final WhiteSpace whiteSpace,
      final NullHandling nullHandling,
      final Normalizer.Form targetCharacterNormalizationForm,
      final int minNumberOfCodePoints,
      final int maxNumberOfCodePoints,
      final int convertAnyDecimalDigitsToDigitsStartingWithZeroDigit,
      final Pattern regex,
      final Predicate<String> validationFunction,
      final Subset acceptedCodePoints,
      final Converter converter) {
    this.targetTypeClass = targetTypeClass;
    this.messageCode = messageCode;
    this.targetCase = targetCase;
    this.whiteSpace = whiteSpace;
    this.nullHandling = nullHandling;
    this.targetCharacterNormalizationForm = targetCharacterNormalizationForm;
    this.minNumberOfCodePoints = minNumberOfCodePoints;
    this.maxNumberOfCodePoints = maxNumberOfCodePoints;
    this.convertAnyDecimalDigitsToDigitsStartingWithZeroDigit = convertAnyDecimalDigitsToDigitsStartingWithZeroDigit;
    this.regex = regex;
    this.validationFunction = validationFunction;
    this.acceptedCodePoints = acceptedCodePoints;
    this.converter = converter;
  }


  @Override
  public <T extends StringType> T parseToStringType(final CharSequence value, Function<String, T> constructorOrFactoryMethod)
      throws InvalidValueException {
    final String parsedValue = parseToString(value);
    return switch (nullHandling) {
      case PRESERVE_NULL_AND_EMPTY, CONVERT_EMPTY_TO_NULL -> parsedValue == null
          ? null
          : constructorOrFactoryMethod.apply(parsedValue);
      case CONVERT_NULL_TO_EMPTY -> constructorOrFactoryMethod.apply(parsedValue);
    };
  }

  @Override
  public <T extends ShortType> T parseToShortType(final CharSequence value, Function<Short, T> constructorOrFactoryMethod)
      throws InvalidValueException {
    final Short parsedValue = parseToShort(value);
    return parsedValue == null
        ? null
        : constructorOrFactoryMethod.apply(parsedValue);
  }

  @Override
  public <T extends IntegerType> T parseToIntegerType(final CharSequence value, IntFunction<T> constructorOrFactoryMethod)
      throws InvalidValueException {
    final Integer parsedValue = parseToInteger(value);
    return parsedValue == null
        ? null
        : constructorOrFactoryMethod.apply(parsedValue);
  }

  @Override
  public <T extends LongType> T parseToLongType(final CharSequence value, LongFunction<T> constructorOrFactoryMethod) throws InvalidValueException {
    final Long parsedValue = parseToLong(value);
    return parsedValue == null
        ? null
        : constructorOrFactoryMethod.apply(parsedValue);
  }

  @Override
  public Short parseToShort(final CharSequence originalValue) throws InvalidValueException {
    final String parsedValue = parseToString(originalValue);
    if (parsedValue == null || parsedValue.isBlank()) {
      return null;
    }
    return Short.valueOf(parsedValue);
  }

  @Override
  public Integer parseToInteger(final CharSequence originalValue) throws InvalidValueException {
    final String parsedValue = parseToString(originalValue);
    if (parsedValue == null || parsedValue.isBlank()) {
      return null;
    }
    return Integer.valueOf(parsedValue);
  }

  @Override
  public Long parseToLong(final CharSequence originalValue) throws InvalidValueException {
    final String parsedValue = parseToString(originalValue);
    if (parsedValue == null || parsedValue.isBlank()) {
      return null;
    }
    return Long.valueOf(parsedValue);
  }

  @Override
  public String parseToString(final CharSequence originalValue) throws InvalidValueException {
    return parse(originalValue, FAIL_ON_INVALID_CHARACTERS).parsedValue();
  }

  @Override
  public ParseResult parseToStringReplacingInvalidCharacters(final CharSequence originalValue) throws InvalidValueException {
    return parse(originalValue, REPLACE_INVALID_CHARACTERS);
  }

  @Override
  public ParseResult parseToStringRemovingInvalidCharacters(final CharSequence originalValue) throws InvalidValueException {
    return parse(originalValue, REMOVE_INVALID_CHARACTERS);
  }

  // Suppress SonarQube "java:S3776 Cognitive Complexity of methods should not be too high"
  // – This is the main parse method and, for the moment, I don't want to break it up and create and pass around instantiated state pass object/s.
  // - Though I am considering doing this to be able to build a parser as a composite of "plug-ins".
  @SuppressWarnings({"java:S3776"})
  public ParseResult parse(final CharSequence originalValue, final InvalidCharactersAction invalidCharactersAction)
      throws InvalidValueException {

    final ParseResultImpl parseResult = new ParseResultImpl();

    if (originalValue == null) {
      parseResult.setParsedValue(
          switch (nullHandling) {
            case CONVERT_NULL_TO_EMPTY -> Constants.EMPTY_STRING;
            case PRESERVE_NULL_AND_EMPTY, CONVERT_EMPTY_TO_NULL -> null;
          });
      return parseResult;
    } else if (originalValue.isEmpty()) {
      parseResult.setParsedValue(
          switch (nullHandling) {
            case PRESERVE_NULL_AND_EMPTY, CONVERT_NULL_TO_EMPTY -> Constants.EMPTY_STRING;
            case CONVERT_EMPTY_TO_NULL -> null;
          });
      return parseResult;
    }

    final CharSequence source = normalizeToTargetCharacterNormalizationForm(originalValue);

    final int length = source == null ? 0 : source.length();
    if (length == 0) {
      parseResult.setParsedValue(
          switch (nullHandling) {
            case PRESERVE_NULL_AND_EMPTY, CONVERT_NULL_TO_EMPTY -> "";
            case CONVERT_EMPTY_TO_NULL -> null;
          });
      return parseResult;
    }

    int[] target = converter == null ? new int[length] : new int[Math.min(maxNumberOfCodePoints, length * 2)];
    boolean codePointWasWhitespace = true;
    boolean codePointIsRepeatedWhitespaceRequiringNormalisation = false;
    int sourceIndex = 0;
    int targetIndex = 0;
    int codePoint;
    int[] toCodePoints;
    final int[] reusableSingleCodePointArray = new int[1];

    final ConverterResults converterResults = converter == null ? null : converter.createConverterResults();

    while (sourceIndex < length) {
      { // Get the codepoint. The anonymous block ensures that variable "ch" is limited in scope.
        final char ch = source.charAt(sourceIndex);
        if (Character.isHighSurrogate(ch)) {
          final int nextSourceIndex = sourceIndex + 1;
          if (nextSourceIndex < length) {
            final char lowCh = source.charAt(nextSourceIndex);
            if (Character.isLowSurrogate(lowCh)) {
              codePoint = Character.toCodePoint(ch, lowCh);
              sourceIndex = nextSourceIndex;
            } else {
              codePoint = handleInvalidCodePoints(
                  parseResult,
                  invalidCharactersAction,
                  ch,
                  () -> ExceptionUtils.forHighSurrogateWithoutLowSurrogate(messageCode, targetTypeClass, source, ch));
            }
          } else {
            codePoint = handleInvalidCodePoints(
                parseResult,
                invalidCharactersAction,
                ch,
                () -> ExceptionUtils.forHighSurrogateWithoutLowSurrogate(messageCode, targetTypeClass, source, ch));
          }
        } else if (Character.isLowSurrogate(ch)) {
          codePoint = handleInvalidCodePoints(
              parseResult,
              invalidCharactersAction,
              ch,
              () -> ExceptionUtils.forLowSurrogateWithoutHighSurrogate(messageCode, targetTypeClass, source, ch));
        } else {
          codePoint = ch;
        }
      }

      if (skipCodePointDueToRemoval(codePoint)) {
        ++sourceIndex;
        continue;
      }

      if (Character.isWhitespace(codePoint)) {
        switch (whiteSpace) {
          case FORBID_WHITESPACE:
            if (converter == null || converter.codePointConversionIsNotRequired(codePoint, targetIndex, converterResults)) {
              final int currentCodePoint = codePoint;
              codePoint = handleInvalidCodePoints(
                  parseResult,
                  invalidCharactersAction,
                  codePoint,
                  () -> ExceptionUtils.forInvalidCodePoint(messageCode, targetTypeClass, source, currentCodePoint));
              if (skipCodePointDueToRemoval(codePoint)) {
                ++sourceIndex;
                continue;
              }
            }
            break;
          case PRESERVE_WHITESPACE:
            // do nothing
            break;
          case PRESERVE_AND_CONVERT_WHITESPACE:
            codePoint = ' ';
            break;
          case NORMALIZE_WHITESPACE, NORMALIZE_AND_CONVERT_WHITESPACE:
            if (codePointWasWhitespace) { // if previous code-point was whitespace
              codePointIsRepeatedWhitespaceRequiringNormalisation = true;
            }
            codePoint = ' ';
            break;
          case REMOVE_WHITESPACE:
            ++sourceIndex;
            continue;
          default:
            // do nothing
            break;
        }
        codePointWasWhitespace = true;
      } else {
        codePointWasWhitespace = false;
      }

      if (convertAnyDecimalDigitsToDigitsStartingWithZeroDigit > 0 && Character.isDigit(codePoint)) {
        codePoint = convertAnyDecimalDigitsToDigitsStartingWithZeroDigit + Character.digit(codePoint, 10);
      }

      if (!isAcceptedCodePoint(codePoint) && !codePointWasWhitespace) {
        final int currentCodePoint = codePoint;
        codePoint = handleInvalidCodePoints(
            parseResult,
            invalidCharactersAction,
            codePoint,
            () -> ExceptionUtils.forInvalidCodePoint(messageCode, targetTypeClass, source, currentCodePoint));
        if (skipCodePointDueToRemoval(codePoint)) {
          ++sourceIndex;
          continue;
        }
      }

      if (converter != null && converter.codePointConversionIsRequired(codePoint, targetIndex, converterResults)) {
        targetIndex = converterResults.getConvertFromIndex();
        toCodePoints = converterResults.getConvertToCodePointSequence();
        if (toCodePoints.length == 0 && (targetIndex > 0 && Character.isWhitespace(target[targetIndex - 1]))) {
          codePointWasWhitespace = true;
        }
      } else {
        reusableSingleCodePointArray[0] = codePoint;
        toCodePoints = reusableSingleCodePointArray;
      }

      if (codePointIsRepeatedWhitespaceRequiringNormalisation) {
        codePointIsRepeatedWhitespaceRequiringNormalisation = false;
        ++sourceIndex;
        continue;
      }

      if ((targetIndex + toCodePoints.length) >= target.length) {
        target = Arrays.copyOf(target, target.length + Math.max(16, toCodePoints.length));
      }

      for (int j = 0; j < toCodePoints.length; ++j) {
        codePoint = toCodePoints[j];
        if (targetIndex >= maxNumberOfCodePoints) {
          throw ExceptionUtils.forValueTooLong(messageCode, targetTypeClass, source, maxNumberOfCodePoints);
        }
        target[targetIndex++] =
            switch (targetCase) {
              case PRESERVE_CASE -> codePoint;
              case TO_LOWER_CASE -> Character.toLowerCase(codePoint);
              case TO_UPPER_CASE -> Character.toUpperCase(codePoint);
              case TO_TITLE_CASE -> targetIndex == 1 ? Character.toTitleCase(codePoint) : Character.toLowerCase(codePoint);
            };
      }
      ++sourceIndex;
    }
    if (targetIndex > 0 && targetIndex < minNumberOfCodePoints) {
      throw ExceptionUtils.forValueTooShort(messageCode, targetTypeClass, source, minNumberOfCodePoints);
    }

    final int targetEndIndex;
    final int targetStartIndex;
    if (whiteSpace == WhiteSpace.NORMALIZE_WHITESPACE) {
      targetEndIndex = exclusiveEndIndexIgnoringTrailingWhitespace(target, targetIndex);
      targetStartIndex = inclusiveStartIndexIgnoringLeadingWhitespace(target, targetEndIndex);
    } else {
      targetEndIndex = targetIndex;
      targetStartIndex = 0;
    }

    final int parsedLength = targetEndIndex - targetStartIndex;
    if (parsedLength == 0) {
      parseResult.setParsedValue(switch (nullHandling) {
        case PRESERVE_NULL_AND_EMPTY, CONVERT_NULL_TO_EMPTY -> Constants.EMPTY_STRING;
        case CONVERT_EMPTY_TO_NULL -> null;
      });
      return parseResult;
    }

    parseResult.setParsedValue(new String(target, targetStartIndex, targetEndIndex - targetStartIndex));

    validateThatParsedValueConformsToTheRegex(parseResult, source);

    validationUsingCustomValidationFunction(parseResult, source);

    return parseResult;
  }

  private CharSequence normalizeToTargetCharacterNormalizationForm(CharSequence originalValue) {
    return targetCharacterNormalizationForm == null || Normalizer.isNormalized(originalValue, targetCharacterNormalizationForm)
        ? originalValue
        : Normalizer.normalize(originalValue, targetCharacterNormalizationForm);
  }

  private static boolean skipCodePointDueToRemoval(int codePoint) {
    // -1 is used to indicate that the code-point was removed and should be skipped
    return codePoint < 0;
  }

  /**
   * Handle invalid code-points.
   *
   * @param invalidCharactersAction      the action to take for invalid characters
   * @param invalidValueExceptionCreator the functional interface that will create the exception to be thrown if we are to fail on invalid values
   * @return the code-point to use or -1 if it should be skipped
   */
  private static int handleInvalidCodePoints(
      final ParseResultImpl parseResult,
      final InvalidCharactersAction invalidCharactersAction,
      final int invalidCodePoint,
      final InvalidValueExceptionCreator invalidValueExceptionCreator) throws InvalidValueException {
    parseResult.addInvalidCodePoint(invalidCodePoint);
    return switch (invalidCharactersAction) {
      case FAIL_ON_INVALID_CHARACTERS -> throw invalidValueExceptionCreator.createException();
      case REPLACE_INVALID_CHARACTERS -> REPLACEMENT_CHARACTER;
      case REMOVE_INVALID_CHARACTERS -> -1; // -1 is used to indicate that the code-point should be skipped
    };
  }

  @FunctionalInterface
  private interface InvalidValueExceptionCreator {
    InvalidValueException createException();
  }

  private boolean isAcceptedCodePoint(final int codePoint) {
    return acceptedCodePoints.contains(codePoint);
  }

  void validateThatParsedValueConformsToTheRegex(
      final ParseResult parseResult,
      final CharSequence originalValue) {
    if (regex == null || parseResult.parsedValueWasInvalid()) {
      return;
    }
    if (!regex.matcher(parseResult.parsedValue()).matches()) {
      throw ExceptionUtils.forValueNotMatchRegex(messageCode, targetTypeClass, originalValue, regex);
    }
  }

  void validationUsingCustomValidationFunction(final ParseResult parseResult, final CharSequence originalValue) {
    if (validationFunction == null || parseResult.parsedValueWasInvalid()) {
      return;
    }
    try {
      final boolean isValid = validationFunction.test(parseResult.parsedValue());
      if (!isValid) {
        throw ExceptionUtils.forValueNotValidUsingCustomValidation(messageCode, targetTypeClass, originalValue, null);
      }
    } catch (final Exception e) {
      throw ExceptionUtils.forValueNotValidUsingCustomValidation(messageCode, targetTypeClass, originalValue, e);
    }
  }

  /**
   * Return the exclusive end-index of the last code-point ignoring trailing whitespace
   *
   * @param codePoints the code-point array that may have trailing whitespace
   * @return the exclusive end-index of the last code-point ignoring trailing whitespace
   * @throws NullPointerException if {@code codePoints} argument is null
   */
  static int exclusiveEndIndexIgnoringTrailingWhitespace(final int[] codePoints, int endIndex) {
    endIndex--;
    // Find end index of char-sequence ignoring trailing whitespace.
    while (endIndex >= 0 && isWhitespace(codePoints[endIndex])) {
      --endIndex;
    }
    return ++endIndex;
  }

  /**
   * Return the inclusive index of first code-point that is not whitespace
   *
   * @param codePoints the code-point array that may have trailing whitespace
   * @param endIndex   the index at which to stop progressing through the char-sequence
   * @return the inclusive index of first code-point that is not whitespace
   * @throws NullPointerException if {@code codePoints} argument is null
   */
  static int inclusiveStartIndexIgnoringLeadingWhitespace(final int[] codePoints, final int endIndex) {
    if (endIndex == 0) {
      return 0;
    }
    int startIndex = 0;
    while (startIndex < endIndex && isWhitespace(codePoints[startIndex])) {
      ++startIndex;
    }
    return startIndex;
  }

}
