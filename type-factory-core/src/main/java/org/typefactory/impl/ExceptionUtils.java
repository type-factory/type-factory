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

public class ExceptionUtils {

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

  static InvalidValueException forInvalidCodePoint(
      final MessageCode messageCode,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final int invalidCodePoint) {

    final ParserMessageCode parserMessageCode;

    if (invalidCodePoint == '\'') {
      parserMessageCode = ParserMessageCode.INVALID_VALUE_INVALID_QUOTE_CHARACTER;
    } else if (Character.isWhitespace(invalidCodePoint)) {
      parserMessageCode = ParserMessageCode.INVALID_VALUE_INVALID_WHITESPACE_CHARACTER;
    } else {
      parserMessageCode = switch (Character.getType(invalidCodePoint)) {
        case Character.CONTROL -> ParserMessageCode.INVALID_VALUE_INVALID_CONTROL_CHARACTER;
        case Character.FORMAT -> ParserMessageCode.INVALID_VALUE_INVALID_FORMAT_CHARACTER;
        case Character.SPACE_SEPARATOR,
             Character.LINE_SEPARATOR,
             Character.PARAGRAPH_SEPARATOR -> ParserMessageCode.INVALID_VALUE_INVALID_WHITESPACE_CHARACTER;
        default -> ParserMessageCode.INVALID_VALUE_INVALID_CHARACTER;
      };
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
      final int invalidCodePoint) {

    return InvalidValueException.builder()
        .invalidValue(value)
        .targetTypeClass(targetTypeClass)
        .messageCode(messageCode)
        .parserMessageCode(ParserMessageCode.INVALID_VALUE_HIGH_SURROGATE_WITHOUT_LOW_SURROGATE)
        .addParserMessageCodeArg(
            ParserMessageCodeArgKeys.INVALID_CHARACTER_DESCRIPTION,
            unicodeHexCode(invalidCodePoint))
        .build();
  }

  static InvalidValueException forLowSurrogateWithoutHighSurrogate(
      final MessageCode messageCode,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final int invalidCodePoint) {

    return InvalidValueException.builder()
        .invalidValue(value)
        .targetTypeClass(targetTypeClass)
        .messageCode(messageCode)
        .parserMessageCode(ParserMessageCode.INVALID_VALUE_LOW_SURROGATE_WITHOUT_HIGH_SURROGATE)
        .addParserMessageCodeArg(
            ParserMessageCodeArgKeys.INVALID_CHARACTER_DESCRIPTION,
            unicodeHexCode(invalidCodePoint))
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
    if (Character.isDefined(codePoint)) {
      if (codePointIsInOneOfTheCategories(codePoint, SPACE_CONTROL_AND_FORMAT_CATEGORY_BIT_FLAGS)) {
        return codePoint > 0xFFFF
            ? String.format("U+%06X %s", codePoint, Character.getName(codePoint))
            : String.format("U+%04X %s", (short) codePoint, Character.getName(codePoint));
      }
      if (Character.isHighSurrogate((char) codePoint)) {
        return String.format("U+%04X HIGH SURROGATE", (short) codePoint);
      }
      if (Character.isLowSurrogate((char) codePoint)) {
        return String.format("U+%04X LOW SURROGATE", (short) codePoint);
      }
      return codePoint > 0xFFFF
          ? String.format("%c U+%06X %s", codePoint, codePoint, Character.getName(codePoint))
          : String.format("%c U+%04X %s", codePoint, (short) codePoint, Character.getName(codePoint));
    }

    if (!Character.isValidCodePoint(codePoint)) {
      return codePoint > 0xFFFFFFL
          ? String.format("U+%08X NOT A UNICODE CHARACTER", codePoint)
          : String.format("U+%06X NOT A UNICODE CHARACTER", codePoint);
    }

    return codePoint > 0xFFFF
        ? String.format("U+%06X UNASSIGNED UNICODE CHARACTER", codePoint)
        : String.format("U+%04X UNASSIGNED UNICODE CHARACTER", codePoint);
  }
}
