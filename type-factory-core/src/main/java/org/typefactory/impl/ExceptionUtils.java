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

import static org.typefactory.Category.codePointIsInOneOfTheCategories;

import java.util.regex.Pattern;
import org.typefactory.Category;
import org.typefactory.InvalidValueException;
import org.typefactory.InvalidValueException.ParserMessageCode;
import org.typefactory.MessageCode;
import org.typefactory.impl.ParserMessageCodeImpl.ParserMessageCodeArgKeys;

class ExceptionUtils {

  private static final long SPACE_CONTROL_AND_FORMAT_CATEGORY_BIT_FLAGS =
      Category.getCategoryBitFlags(
          Category.CONTROL,
          Category.FORMAT,
          Category.SPACE_SEPARATOR,
          Category.LINE_SEPARATOR,
          Category.PARAGRAPH_SEPARATOR);

  private ExceptionUtils() {
    // don't instantiate me
  }

  static InvalidValueException forValueTooShort(
      final MessageCode messageCode,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final int minLength) {

    return InvalidValueException.builder()
        .invalidValue(value)
        .targetTypeClass(targetTypeClass)
        .messageCode(messageCode)
        .parserMessageCode(ParserMessageCode.INVALID_VALUE_TOO_SHORT)
        .addParserMessageCodeArg(
            ParserMessageCodeArgKeys.MIN_LENGTH,
            minLength)
        .build();
  }

  static InvalidValueException forValueTooLong(
      final MessageCode messageCode,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final int maxLength) {

    return InvalidValueException.builder()
        .invalidValue(value)
        .targetTypeClass(targetTypeClass)
        .messageCode(messageCode)
        .parserMessageCode(ParserMessageCode.INVALID_VALUE_TOO_LONG)
        .addParserMessageCodeArg(
            ParserMessageCodeArgKeys.MAX_LENGTH,
            maxLength)
        .build();
  }

  static <T> InvalidValueException forValueMustBeGreaterThanMinValue(
      final MessageCode messageCode,
      final Class<?> targetTypeClass,
      final T invalidValue,
      final long minExclusiveValue) {

    return InvalidValueException.builder()
        .invalidValue(invalidValue)
        .targetTypeClass(targetTypeClass)
        .messageCode(messageCode)
        .parserMessageCode(ParserMessageCode.INVALID_VALUE_MUST_BE_GREATER_THAN)
        .addParserMessageCodeArg(
            ParserMessageCodeArgKeys.MIN_INCLUSIVE_VALUE,
            minExclusiveValue)
        .build();
  }

  static <T> InvalidValueException forValueMustBeGreaterThanOrEqualToMinValue(
      final MessageCode messageCode,
      final Class<?> targetTypeClass,
      final T invalidValue,
      final long minInclusiveValue) {

    return InvalidValueException.builder()
        .invalidValue(invalidValue)
        .targetTypeClass(targetTypeClass)
        .messageCode(messageCode)
        .parserMessageCode(ParserMessageCode.INVALID_VALUE_MUST_BE_GREATER_THAN_OR_EQUAL_TO)
        .addParserMessageCodeArg(
            ParserMessageCodeArgKeys.MIN_INCLUSIVE_VALUE,
            minInclusiveValue)
        .build();
  }

  static <T> InvalidValueException forValueMustBeLessThanMaxValue(
      final MessageCode messageCode,
      final Class<?> targetTypeClass,
      final T invalidValue,
      final long maxExclusiveValue) {

    return InvalidValueException.builder()
        .invalidValue(invalidValue)
        .targetTypeClass(targetTypeClass)
        .messageCode(messageCode)
        .parserMessageCode(ParserMessageCode.INVALID_VALUE_MUST_BE_LESS_THAN)
        .addParserMessageCodeArg(
            ParserMessageCodeArgKeys.MAX_INCLUSIVE_VALUE,
            maxExclusiveValue)
        .build();
  }

  static <T> InvalidValueException forValueMustBeLessThanOrEqualToMaxValue(
      final MessageCode messageCode,
      final Class<?> targetTypeClass,
      final T invalidValue,
      final long maxInclusiveValue) {

    return InvalidValueException.builder()
        .invalidValue(invalidValue)
        .targetTypeClass(targetTypeClass)
        .messageCode(messageCode)
        .parserMessageCode(ParserMessageCode.INVALID_VALUE_MUST_BE_LESS_THAN_OR_EQUAL_TO)
        .addParserMessageCodeArg(
            ParserMessageCodeArgKeys.MAX_INCLUSIVE_VALUE,
            maxInclusiveValue)
        .build();
  }

  static InvalidValueException forInvalidCodePoint(
      final MessageCode messageCode,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final int invalidCodePoint) {

    final var codePointCategory = Character.getType(invalidCodePoint);

    final ParserMessageCode parserMessageCode;
    if (Character.isWhitespace(invalidCodePoint) || Character.isSpaceChar(invalidCodePoint)) {
      parserMessageCode = ParserMessageCode.INVALID_VALUE_INVALID_WHITESPACE_CHARACTER;
    } else if (Character.isISOControl(invalidCodePoint) || codePointCategory == Character.CONTROL) {
      parserMessageCode = ParserMessageCode.INVALID_VALUE_INVALID_CONTROL_CHARACTER;
    } else if (codePointCategory == Character.FORMAT) {
      parserMessageCode = ParserMessageCode.INVALID_VALUE_INVALID_FORMAT_CHARACTER;
    } else if ('\'' == invalidCodePoint) {
      parserMessageCode = ParserMessageCode.INVALID_VALUE_INVALID_QUOTE_CHARACTER;
    } else {
      parserMessageCode = ParserMessageCode.INVALID_VALUE_INVALID_CHARACTER;
    }

    return InvalidValueException.builder()
        .invalidValue(value)
        .targetTypeClass(targetTypeClass)
        .messageCode(messageCode)
        .parserMessageCode(parserMessageCode)
        .addParserMessageCodeArg(
            ParserMessageCodeArgKeys.INVALID_CHARACTER_DESCRIPTION,
            unicodeHexCode(invalidCodePoint))
        .build();
  }

  static InvalidValueException forHighSurrogateWithoutLowSurrogate(
      final MessageCode messageCode,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final char invalidChar) {

    return InvalidValueException.builder()
        .invalidValue(value)
        .targetTypeClass(targetTypeClass)
        .messageCode(messageCode)
        .parserMessageCode(ParserMessageCode.INVALID_VALUE_HIGH_SURROGATE_WITHOUT_LOW_SURROGATE)
        .addParserMessageCodeArg(
            ParserMessageCodeArgKeys.INVALID_CHARACTER_DESCRIPTION,
            unicodeHexCode(invalidChar))
        .build();
  }

  static InvalidValueException forLowSurrogateWithoutHighSurrogate(
      final MessageCode messageCode,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final char invalidChar) {

    return InvalidValueException.builder()
        .invalidValue(value)
        .targetTypeClass(targetTypeClass)
        .messageCode(messageCode)
        .parserMessageCode(ParserMessageCode.INVALID_VALUE_LOW_SURROGATE_WITHOUT_HIGH_SURROGATE)
        .addParserMessageCodeArg(
            ParserMessageCodeArgKeys.INVALID_CHARACTER_DESCRIPTION,
            unicodeHexCode(invalidChar))
        .build();
  }

  static InvalidValueException forValueNotMatchRegex(
      final MessageCode messageCode,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final Pattern regex) {

    return InvalidValueException.builder()
        .invalidValue(value)
        .targetTypeClass(targetTypeClass)
        .messageCode(messageCode)
        .parserMessageCode(ParserMessageCode.INVALID_VALUE_DOES_NOT_MATCH_REGEX_PATTERN)
        .addParserMessageCodeArg(
            ParserMessageCodeArgKeys.REGEX_PATTERN,
            regex.toString())
        .build();
  }

  static InvalidValueException forValueNotValidUsingCustomValidation(
      final MessageCode messageCode,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final Throwable cause) {

    return InvalidValueException.builder()
        .cause(cause)
        .invalidValue(value)
        .targetTypeClass(targetTypeClass)
        .messageCode(messageCode)
        .parserMessageCode(ParserMessageCode.INVALID_VALUE_DOES_NOT_PASS_CUSTOM_VALIDATION)
        .build();
  }

  static InvalidValueException forMultipleDecimalPoints(
      final MessageCode messageCode,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final int invalidCodePoint) {

    return InvalidValueException.builder()
        .invalidValue(value)
        .targetTypeClass(targetTypeClass)
        .messageCode(messageCode)
        .parserMessageCode(ParserMessageCode.INVALID_VALUE_MULTIPLE_DECIMAL_POINTS)
        .addParserMessageCodeArg(
            ParserMessageCodeArgKeys.INVALID_CHARACTER_DESCRIPTION,
            new String(new int[]{invalidCodePoint}, 0, 1))
        .build();
  }

  static InvalidValueException forDecimalPointNotPermittedForNonBaseTenNumbers(
      final MessageCode messageCode,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final int invalidCodePoint) {

    return InvalidValueException.builder()
        .invalidValue(value)
        .targetTypeClass(targetTypeClass)
        .messageCode(messageCode)
        .parserMessageCode(ParserMessageCode.INVALID_VALUE_DECIMAL_POINT_NOT_PERMITTED_FOR_NON_BASE_TEN_NUMBERS)
        .addParserMessageCodeArg(
            ParserMessageCodeArgKeys.INVALID_CHARACTER_DESCRIPTION,
            new String(new int[]{invalidCodePoint}, 0, 1))
        .build();
  }

  static InvalidValueException forExpectingWholeNumber(
      final MessageCode messageCode,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final int decimalSeparatorCodePoint,
      final long fractionalValue) {

    return InvalidValueException.builder()
        .invalidValue(value)
        .targetTypeClass(targetTypeClass)
        .messageCode(messageCode)
        .parserMessageCode(ParserMessageCode.INVALID_VALUE_EXPECTING_WHOLE_NUMBER)
        .addParserMessageCodeArg(
            ParserMessageCodeArgKeys.DECIMAL_SEPARATOR,
            new String(new int[]{decimalSeparatorCodePoint}, 0, 1))
        .addParserMessageCodeArg(
            ParserMessageCodeArgKeys.FRACTIONAL_VALUE,
            fractionalValue)
        .build();
  }

  static String unicodeHexCode(final char ch) {
    if (Character.isHighSurrogate(ch) || Character.isLowSurrogate(ch)) {
      return String.format("U+%04X", (int) ch);
    }
    return unicodeHexCode((int) ch);
  }

  static String unicodeHexCode(final int codePoint) {
    if (!Character.isValidCodePoint(codePoint)) {
      return codePoint > 0xFFFFFFL
          ? String.format("U+%08X NOT A UNICODE CHARACTER", codePoint)
          : String.format("U+%06X NOT A UNICODE CHARACTER", codePoint);
    }
    if (codePointIsInOneOfTheCategories(codePoint, SPACE_CONTROL_AND_FORMAT_CATEGORY_BIT_FLAGS)) {
      return Character.isSupplementaryCodePoint(codePoint)
          ? String.format("U+%06X %s", codePoint, getUnicodeCharacterName(codePoint))
          : String.format("U+%04X %s", codePoint, getUnicodeCharacterName(codePoint));
    }
    if (codePoint == '\'') {
      return String.format("%c U+%04X %s", codePoint, codePoint, getUnicodeCharacterName(codePoint));
    }
    return Character.isSupplementaryCodePoint(codePoint)
        ? String.format("'%c' U+%06X %s", codePoint, codePoint, getUnicodeCharacterName(codePoint))
        : String.format("'%c' U+%04X %s", codePoint, (short) codePoint, getUnicodeCharacterName(codePoint));
  }

  static String getUnicodeCharacterName(final int codePoint) {
    if (!Character.isValidCodePoint(codePoint)) {
      return "NOT A UNICODE CHARACTER";
    }
    return Character.isDefined(codePoint)
        ? Character.getName(codePoint)
        : "UNASSIGNED UNICODE CHARACTER";
  }
}
