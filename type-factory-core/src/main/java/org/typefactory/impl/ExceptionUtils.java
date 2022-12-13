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

import static org.typefactory.impl.Constants.EMPTY_STRING;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import org.typefactory.ErrorCode;
import org.typefactory.InvalidValueException;
import org.typefactory.InvalidValueException.ParserErrorCode;
import org.typefactory.impl.ParserErrorCodeImpl.ParserErrorCodeArgKeys;

public final class ExceptionUtils {

  private ExceptionUtils() {
    // don't instantiate me
  }

  private static final String MESSAGES_RESOURCE_BUNDLE_BASE_NAME = "org.typefactory.Messages";

  private static final Object[] EMPTY_MESSAGE_ARGS = new Object[0];

  public static String getMessage(final ErrorCode errorCode) {
    return getMessage(errorCode, EMPTY_MESSAGE_ARGS);
  }

  public static String getMessage(final ErrorCode errorCode, final Object[] messageArgs) {
    return getMessage(Locale.getDefault(), errorCode, messageArgs);
  }

  public static String getMessage(final Locale locale, final ErrorCode errorCode) {
    return getMessage(locale, errorCode, EMPTY_MESSAGE_ARGS);
  }

  public static String getMessage(final Locale locale, final ErrorCode errorCode, final Object[] messageArgs) {
    if (errorCode == null || errorCode.isBlank()) {
      return EMPTY_STRING;
    }
    try {
      final String message = getMessage(errorCode, locale);
      final Object[] args = messageArgs == null
          ? EMPTY_MESSAGE_ARGS
          : messageArgs;
      return new MessageFormat(message, locale).format(args);
    } catch (final Exception ignore) {
      // ignore exception.
      return errorCode.errorCode();
    }
  }

  private static String getMessage(final ErrorCode errorCode, final Locale locale) {
    final ResourceBundle resourceBundle;
    String message = EMPTY_STRING;
    try {
      resourceBundle = ResourceBundle.getBundle(MESSAGES_RESOURCE_BUNDLE_BASE_NAME, locale);
      message = resourceBundle.getString(errorCode.errorCode());
      if (!message.isBlank()) {
        return message;
      }
    } catch (final Exception ignore) {
      // ignore exception
    }
    message = errorCode.defaultMessage();
    return message == null || message.isBlank()
        ? errorCode.errorCode()
        : message;
  }

  public static String combineMessagesIntoSentences(
      final String message1,
      final String message2) {

    if (message1 == null || message1.isBlank()) {
      return message2;
    }

    final StringBuilder s = new StringBuilder(message2.length() + message1.length() + 4);
    s.append(message1.trim());
    if (s.charAt(s.length() - 1) == '.') {
      s.append(' ');
    } else {
      s.append(". ");
    }
    s.append(message2);
    return s.toString();
  }

  static InvalidValueException forValueTooShort(
      final ErrorCode message,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final int minLength) {

    return InvalidValueException.builder()
        .invalidValue(value)
        .targetTypeClass(targetTypeClass)
        .errorCode(message)
        .parserErrorCode(ParserErrorCode.INVALID_VALUE_TOO_SHORT)
        .addParserErrorCodeArgs(
            ParserErrorCodeArgKeys.MIN_LENGTH,
            minLength)
        .build();
  }

  static InvalidValueException forValueTooLong(
      final ErrorCode message,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final int maxLength) {

    return InvalidValueException.builder()
        .invalidValue(value)
        .targetTypeClass(targetTypeClass)
        .errorCode(message)
        .parserErrorCode(ParserErrorCode.INVALID_VALUE_TOO_LONG)
        .addParserErrorCodeArgs(
            ParserErrorCodeArgKeys.MAX_LENGTH,
            maxLength)
        .build();
  }

  static InvalidValueException forInvalidCodePoint(
      final ErrorCode message,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final int invalidCodePoint) {

    if (Character.isWhitespace(invalidCodePoint)) {
      return InvalidValueException.builder()
          .invalidValue(value)
          .targetTypeClass(targetTypeClass)
          .errorCode(message)
          .parserErrorCode(ParserErrorCode.INVALID_VALUE_INVALID_WHITESPACE_CHARACTER)
          .addParserErrorCodeArgs(
              ParserErrorCodeArgKeys.INVALID_CODE_POINT,
              unicodeHexCode(invalidCodePoint))
          .build();
    }

    if (Character.isISOControl(invalidCodePoint)) {
      return InvalidValueException.builder()
          .invalidValue(value)
          .targetTypeClass(targetTypeClass)
          .errorCode(message)
          .parserErrorCode(ParserErrorCode.INVALID_VALUE_INVALID_CONTROL_CHARACTER)
          .addParserErrorCodeArgs(
              ParserErrorCodeArgKeys.INVALID_CODE_POINT,
              unicodeHexCode(invalidCodePoint))
          .build();
    }

    if ('\'' == invalidCodePoint) {
      return InvalidValueException.builder()
          .invalidValue(value)
          .targetTypeClass(targetTypeClass)
          .errorCode(message)
          .parserErrorCode(ParserErrorCode.INVALID_VALUE_INVALID_QUOTE_CHARACTER)
          .addParserErrorCodeArgs(
              ParserErrorCodeArgKeys.INVALID_CODE_POINT,
              "''")
          .build();
    }

    return InvalidValueException.builder()
        .invalidValue(value)
        .targetTypeClass(targetTypeClass)
        .errorCode(message)
        .parserErrorCode(ParserErrorCode.INVALID_VALUE_INVALID_CHARACTER)
        .addParserErrorCodeArgs(
            ParserErrorCodeArgKeys.INVALID_CODE_POINT,
            new String(new int[]{invalidCodePoint}, 0, 1))
        .build();
  }

  static InvalidValueException forValueNotMatchRegex(
      final ErrorCode message,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final Pattern regex) {

    return InvalidValueException.builder()
        .invalidValue(value)
        .targetTypeClass(targetTypeClass)
        .errorCode(message)
        .parserErrorCode(ParserErrorCode.INVALID_VALUE_DOES_NOT_MATCH_REGEX_PATTERN)
        .addParserErrorCodeArgs(
            ParserErrorCodeArgKeys.REGEX_PATTERN,
            regex.toString())
        .build();
  }

  static InvalidValueException forValueNotValidUsingCustomValidation(
      final ErrorCode message,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final Exception cause) {

    return InvalidValueException.builder()
        .cause(cause)
        .invalidValue(value)
        .targetTypeClass(targetTypeClass)
        .errorCode(message)
        .parserErrorCode(ParserErrorCode.INVALID_VALUE_DOES_NOT_PASS_CUSTOM_VALIDATION)
        .build();
  }

  private static String unicodeHexCode(final int codePoint) {
    return Character.isSupplementaryCodePoint(codePoint)
        ? String.format("U+%06x", codePoint)
        : String.format("U+%04x", (short) codePoint);
  }

}
