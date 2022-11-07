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
import static org.typefactory.impl.NullHandling.CONVERT_EMPTY_TO_NULL;
import static org.typefactory.impl.NullHandling.PRESERVE_NULL_AND_EMPTY;

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
import org.typefactory.ShortType;
import org.typefactory.StringType;
import org.typefactory.Subset;
import org.typefactory.TypeParser;
import org.typefactory.impl.Converter.ConverterResults;

class TypeParserImpl implements TypeParser {

  private final Class<?> targetTypeClass;
  private final String errorMessage;
  private final TargetCase targetCase;
  private final WhiteSpace whiteSpace;
  private final NullHandling nullHandling;
  private final Normalizer.Form targetCharacterNormalizationForm;
  private final int minNumberOfCodePoints;
  private final int maxNumberOfCodePoints;

  private final Pattern regex;

  private final Predicate<String> validationFunction;
  private final Subset acceptedCodePoints;
  private final Converter converter;

  @SuppressWarnings("java:S107") // Suppress SonaQube "Methods should not have too many parameters" because this constructor is called by a builder
  TypeParserImpl(
      final Class<?> targetTypeClass,
      final String errorMessage,
      final TargetCase targetCase,
      final WhiteSpace whiteSpace,
      final NullHandling nullHandling,
      final Normalizer.Form targetCharacterNormalizationForm,
      final int minNumberOfCodePoints,
      final int maxNumberOfCodePoints,
      final Pattern regex,
      final Predicate<String> validationFunction,
      final Subset acceptedCodePoints,
      final Converter converter) {
    this.targetTypeClass = targetTypeClass;
    this.errorMessage = errorMessage;
    this.targetCase = targetCase;
    this.whiteSpace = whiteSpace;
    this.nullHandling = nullHandling;
    this.targetCharacterNormalizationForm = targetCharacterNormalizationForm;
    this.minNumberOfCodePoints = minNumberOfCodePoints;
    this.maxNumberOfCodePoints = maxNumberOfCodePoints;
    this.regex = regex;
    this.validationFunction = validationFunction;
    this.acceptedCodePoints = acceptedCodePoints;
    this.converter = converter;
  }

  @Override
  public <T extends StringType> T parseToStringType(final CharSequence value, Function<String, T> constructorOrFactoryMethod) throws InvalidValueException {
    return (nullHandling == PRESERVE_NULL_AND_EMPTY && value == null)
        || (nullHandling == CONVERT_EMPTY_TO_NULL && (value == null || value.isEmpty()))
        ? null
        : constructorOrFactoryMethod.apply(parseToString(value));
  }

  @Override
  public <T extends ShortType> T parseToShortType(final CharSequence value, Function<Short, T> constructorOrFactoryMethod) throws InvalidValueException {
    final Short parsedValue = parseToShort(value);
    return parsedValue == null
        ? null
        : constructorOrFactoryMethod.apply(parsedValue);
  }

  @Override
  public <T extends IntegerType> T parseToIntegerType(final CharSequence value, IntFunction<T> constructorOrFactoryMethod) throws InvalidValueException {
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


  // Suppress SonarQube "Cognitive Complexity of methods should not be too high" – this is the main parse method and I think it reads well.
  @SuppressWarnings("java:S3776")
  @Override
  public String parseToString(final CharSequence originalValue) throws InvalidValueException {
    if ((nullHandling == PRESERVE_NULL_AND_EMPTY && originalValue == null)
        || (nullHandling == CONVERT_EMPTY_TO_NULL && (originalValue == null || originalValue.isEmpty()))) {
      return null;
    }

    final CharSequence source = targetCharacterNormalizationForm == null || Normalizer.isNormalized(originalValue, targetCharacterNormalizationForm)
        ? originalValue
        : Normalizer.normalize(originalValue, targetCharacterNormalizationForm);

    final int length = source.length();
    if (length == 0) {
      return switch (nullHandling) {
        case PRESERVE_NULL_AND_EMPTY, CONVERT_NULL_TO_EMPTY -> "";
        case CONVERT_EMPTY_TO_NULL -> null;
      };
    }
    int[] target = converter == null ? new int[length] : new int[Math.min(maxNumberOfCodePoints, length * 2)];
    boolean codePointWasWhitespace = true;
    boolean codePointIsRepeatedWhitespaceRequiringNormalisation = false;
    int sourceIndex = 0;
    int targetIndex = 0;
    char ch;
    int codePoint;
    int[] toCodePoints;
    final int[] reusableSingleCodePointArray = new int[1];

    ConverterResults converterResults = converter == null ? null : converter.createConverterResults();

    while (sourceIndex < length) {
      ch = source.charAt(sourceIndex);
      if (Character.isSurrogate(ch)) {
        if (++sourceIndex < length) {
          codePoint = Character.toCodePoint(ch, source.charAt(sourceIndex));
        } else {
          throw InvalidValueException.forInvalidCodePoint(errorMessage, targetTypeClass, source, ch);
        }
      } else {
        codePoint = ch;
      }

      if (Character.isWhitespace(codePoint)) {
        switch (whiteSpace) {
          case FORBID_WHITESPACE:
            if (converter != null && !converter.isCodePointConversionRequired(codePoint, targetIndex, converterResults)) {
              throw InvalidValueException.forInvalidCodePoint(errorMessage, targetTypeClass, source, ch);
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

      if (!isAcceptedCodePoint(codePoint) && !codePointWasWhitespace) {
        throw InvalidValueException.forInvalidCodePoint(errorMessage, targetTypeClass, source, codePoint);
      }

      if (converter != null &&
          converter.isCodePointConversionRequired(codePoint, targetIndex, converterResults)) {
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

      for (int j = 0; j < toCodePoints.length; ++j) {
        codePoint = toCodePoints[j];
        if (targetIndex >= maxNumberOfCodePoints) {
          throw InvalidValueException.forValueTooLong(errorMessage, targetTypeClass, source, maxNumberOfCodePoints);
        }
        if (targetIndex == target.length) {
          target = Arrays.copyOf(target, target.length + Math.max(16, toCodePoints.length));
        }
        target = appendCodePoint(target, targetIndex++,
            switch (targetCase) {
              case PRESERVE_CASE -> codePoint;
              case TO_LOWER_CASE -> Character.toLowerCase(codePoint);
              case TO_UPPER_CASE -> Character.toUpperCase(codePoint);
              case TO_TITLE_CASE -> targetIndex == 1 ? Character.toTitleCase(codePoint) : Character.toLowerCase(codePoint);
            });
      }
      ++sourceIndex;
    }
    if (targetIndex < minNumberOfCodePoints) {
      throw InvalidValueException.forValueTooShort(errorMessage, targetTypeClass, source, minNumberOfCodePoints);
    }

    final String parsedValue;
    if (whiteSpace == WhiteSpace.NORMALIZE_WHITESPACE) {
      final int targetEndIndex = endIndexIgnoringTrailingWhitespace(target, targetIndex);
      final int targetStartIndex = startIndexIgnoringLeadingWhitespace(target, targetEndIndex);
      parsedValue = new String(target, targetStartIndex, targetEndIndex - targetStartIndex);
    } else {
      parsedValue = new String(target, 0, targetIndex);
    }

    validateThatParsedValueConformToTheRegex(parsedValue, source);

    validationUsingCustomValidationFunction(parsedValue, source);

    return parsedValue;
  }

  private void validateThatParsedValueConformToTheRegex(final String parsedValue, final CharSequence originalValue) {
    if (regex == null) {
      return;
    }
    if (!regex.matcher(parsedValue).matches()) {
      throw InvalidValueException.forValueNotMatchRegex(errorMessage, targetTypeClass, originalValue, regex);
    }
  }

  private void validationUsingCustomValidationFunction(final String parsedValue, final CharSequence originalValue) {
    if (validationFunction == null) {
      return;
    }
    try {
      final boolean isValid = validationFunction.test(parsedValue);
      if (!isValid) {
        throw InvalidValueException.forValueNotValidUsingCustomValidation(errorMessage, targetTypeClass, originalValue, null);
      }
    } catch (final Exception e) {
      throw InvalidValueException.forValueNotValidUsingCustomValidation(errorMessage, targetTypeClass, originalValue, e);
    }
  }

  private boolean isAcceptedCodePoint(final int codePoint) {
    return acceptedCodePoints.contains(codePoint);
  }

  private static int[] appendCodePoint(int[] result, final int index, final int codePoint) {
    if (index == result.length) {
      result = Arrays.copyOf(result, result.length + 16);
    }
    result[index] = codePoint;
    return result;
  }

  /**
   * Return the end-index of the last char-character ignoring trailing whitespace
   *
   * @param codePoints the code-point array that may have trailing whitespace
   * @return the end-index of the last char-character ignoring trailing whitespace
   */
  private static int endIndexIgnoringTrailingWhitespace(final int [] codePoints, int endIndex) {
    if (codePoints == null) {
      return 0;
    }
    endIndex--;
    // Find end index of char-sequence ignoring trailing whitespace.
    while (endIndex >= 0 && isWhitespace(codePoints[endIndex])) {
      --endIndex;
    }
    return ++endIndex;
  }

  /**
   * Return the index of first character that is not whitespace
   *
   * @param codePoints the code-point array that may have trailing whitespace
   * @param endIndex the index at which to stop progressing through the char-sequence
   * @return the index of first character that is not whitespace
   */
  private static int startIndexIgnoringLeadingWhitespace(final int[] codePoints, final int endIndex) {
    if (codePoints == null || endIndex == 0) {
      return 0;
    }
    int startIndex = 0;
    while (startIndex < endIndex && isWhitespace(codePoints[startIndex])) {
      ++startIndex;
    }
    return startIndex;
  }

}
