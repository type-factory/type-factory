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

import java.util.regex.Pattern;
import org.typefactory.InvalidValueException;
import org.typefactory.InvalidValueException.ParserMessageCode;
import org.typefactory.MessageCode;
import org.typefactory.impl.ParserMessageCodeImpl.ParserMessageCodeArgKeys;

public class ExceptionUtils {
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

  static InvalidValueException forValueMustBeGreaterThanOrEqualToMinValue(
      final MessageCode messageCode,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final String minInclusiveValue) {

    return InvalidValueException.builder()
        .invalidValue(value)
        .targetTypeClass(targetTypeClass)
        .messageCode(messageCode)
        .parserMessageCode(ParserMessageCode.INVALID_VALUE_MUST_BE_GREATER_THAN_OR_EQUAL_TO)
        .addParserMessageCodeArg(
            ParserMessageCodeArgKeys.MIN_INCLUSIVE_VALUE,
            minInclusiveValue)
        .build();
  }

  static InvalidValueException forValueMustBeLessThanOrEqualToMaxValue(
      final MessageCode messageCode,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final String maxInclusiveValue) {

    return InvalidValueException.builder()
        .invalidValue(value)
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

    if (Character.isWhitespace(invalidCodePoint)) {
      return InvalidValueException.builder()
          .invalidValue(value)
          .targetTypeClass(targetTypeClass)
          .messageCode(messageCode)
          .parserMessageCode(ParserMessageCode.INVALID_VALUE_INVALID_WHITESPACE_CHARACTER)
          .addParserMessageCodeArg(
              ParserMessageCodeArgKeys.INVALID_CODE_POINT,
              unicodeHexCode(invalidCodePoint))
          .build();
    }

    if (Character.isISOControl(invalidCodePoint)) {
      return InvalidValueException.builder()
          .invalidValue(value)
          .targetTypeClass(targetTypeClass)
          .messageCode(messageCode)
          .parserMessageCode(ParserMessageCode.INVALID_VALUE_INVALID_CONTROL_CHARACTER)
          .addParserMessageCodeArg(
              ParserMessageCodeArgKeys.INVALID_CODE_POINT,
              unicodeHexCode(invalidCodePoint))
          .build();
    }

    if ('\'' == invalidCodePoint) {
      return InvalidValueException.builder()
          .invalidValue(value)
          .targetTypeClass(targetTypeClass)
          .messageCode(messageCode)
          .parserMessageCode(ParserMessageCode.INVALID_VALUE_INVALID_QUOTE_CHARACTER)
          .addParserMessageCodeArg(
              ParserMessageCodeArgKeys.INVALID_CODE_POINT,
              "'")
          .build();
    }

    return InvalidValueException.builder()
        .invalidValue(value)
        .targetTypeClass(targetTypeClass)
        .messageCode(messageCode)
        .parserMessageCode(ParserMessageCode.INVALID_VALUE_INVALID_CHARACTER)
        .addParserMessageCodeArg(
            ParserMessageCodeArgKeys.INVALID_CODE_POINT,
            new String(new int[]{invalidCodePoint}, 0, 1))
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

  static String unicodeHexCode(final int codePoint) {
    return Character.isSupplementaryCodePoint(codePoint)
        ? String.format("U+%06x", codePoint)
        : String.format("U+%04x", (short) codePoint);
  }

}
