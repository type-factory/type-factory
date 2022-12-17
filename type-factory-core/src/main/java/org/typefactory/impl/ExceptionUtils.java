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
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.typefactory.ErrorCode;
import org.typefactory.InvalidValueException;
import org.typefactory.InvalidValueException.ParserErrorCode;
import org.typefactory.impl.ParserErrorCodeImpl.ParserErrorCodeArgKeys;

public class ExceptionUtils {

  private static final Logger logger = Logger.getLogger(ExceptionUtils.class.getName());

  private ExceptionUtils() {
    // don't instantiate me
  }

  private static final String MESSAGES_RESOURCE_BUNDLE_BASE_NAME = "org.typefactory.Messages";

  private static final Object[] EMPTY_MESSAGE_ARGS = new Object[0];

  public static String getMessage(final ErrorCode errorCode) {
    return loadMessageFromResourceBundleAndFormat(Locale.getDefault(), errorCode, EMPTY_MESSAGE_ARGS);
  }

  public static String getMessage(final Locale locale, final ErrorCode errorCode) {
    return loadMessageFromResourceBundleAndFormat(locale, errorCode, EMPTY_MESSAGE_ARGS);
  }

  public static String getMessage(final Locale locale, final ParserErrorCode parserErrorCode, final Object[] parserErrorMessageArgs) {
    return loadMessageFromResourceBundleAndFormat(locale, parserErrorCode, parserErrorMessageArgs);
  }

  public static String getCombinedMessage(final ErrorCode errorCode, final ParserErrorCode parserErrorCode, final Object[] parserErrorMessageArgs) {
    return combineMessagesIntoSentences(
        loadMessageFromResourceBundleAndFormat(Locale.getDefault(), errorCode, EMPTY_MESSAGE_ARGS),
        loadMessageFromResourceBundleAndFormat(Locale.getDefault(), parserErrorCode, parserErrorMessageArgs));
  }

  public static String getCombinedMessage(final Locale locale, final ErrorCode errorCode, final ParserErrorCode parserErrorCode,
      final Object[] parserErrorMessageArgs) {
    return combineMessagesIntoSentences(
        loadMessageFromResourceBundleAndFormat(locale, errorCode, EMPTY_MESSAGE_ARGS),
        loadMessageFromResourceBundleAndFormat(locale, parserErrorCode, parserErrorMessageArgs));
  }

  protected static String loadMessageFromResourceBundleAndFormat(final Locale locale, final ErrorCode errorCode, final Object[] messageArgs) {
    if (errorCode == null || errorCode.isBlank()) {
      return EMPTY_STRING;
    }
    try {
      final String message = loadMessageFromResourceBundle(MESSAGES_RESOURCE_BUNDLE_BASE_NAME, locale, errorCode);
      final Object[] args = messageArgs == null
          ? EMPTY_MESSAGE_ARGS
          : messageArgs;
      return new MessageFormat(message, locale).format(args);
    } catch (final Exception e) {
      logger.fine(() ->
          String.format("Can't load error message for key '%s' from resource bundle for base name %s, locale %s – %s",
              errorCode.code(), MESSAGES_RESOURCE_BUNDLE_BASE_NAME, locale.toLanguageTag(), e.getClass().getSimpleName()));
      return errorCode.code();
    }
  }

  protected static String combineMessagesIntoSentences(
      final String message1,
      final String message2) {

    if (message1 == null || message1.isBlank()) {
      if (message2 == null || message2.isBlank()) {
        return EMPTY_STRING;
      }
      return message2;
    }

    if (message2 == null || message2.isBlank()) {
      return message1;
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

  protected static String loadMessageFromResourceBundle(final String resourceBundleBaseName, final Locale locale, final ErrorCode errorCode) {
    final ResourceBundle resourceBundle;
    String message;
    try {
      resourceBundle = ResourceBundle.getBundle(resourceBundleBaseName, locale);
      message = resourceBundle.getString(errorCode.code());
      if (!message.isBlank()) {
        return message;
      }
    } catch (final MissingResourceException e) {
      logger.fine(() -> e.getMessage() + " - " + e.getClass().getSimpleName());
    } catch (final Exception e) {
      logger.fine(() -> String.format("Can't load error message for key '%s' from resource bundle for base name %s, locale %s – %s",
          errorCode.code(), resourceBundleBaseName, locale.toLanguageTag(), e.getClass().getSimpleName()));
    }
    message = errorCode.defaultMessage();
    return message == null || message.isBlank()
        ? errorCode.code()
        : message;
  }

  static InvalidValueException forValueTooShort(
      final ErrorCode errorCode,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final int minLength) {

    return InvalidValueException.builder()
        .invalidValue(value)
        .targetTypeClass(targetTypeClass)
        .errorCode(errorCode)
        .parserErrorCode(ParserErrorCode.INVALID_VALUE_TOO_SHORT)
        .addParserErrorCodeArgs(
            ParserErrorCodeArgKeys.MIN_LENGTH,
            minLength)
        .build();
  }

  static InvalidValueException forValueTooLong(
      final ErrorCode errorCode,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final int maxLength) {

    return InvalidValueException.builder()
        .invalidValue(value)
        .targetTypeClass(targetTypeClass)
        .errorCode(errorCode)
        .parserErrorCode(ParserErrorCode.INVALID_VALUE_TOO_LONG)
        .addParserErrorCodeArgs(
            ParserErrorCodeArgKeys.MAX_LENGTH,
            maxLength)
        .build();
  }

  static InvalidValueException forInvalidCodePoint(
      final ErrorCode errorCode,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final int invalidCodePoint) {

    if (Character.isWhitespace(invalidCodePoint)) {
      return InvalidValueException.builder()
          .invalidValue(value)
          .targetTypeClass(targetTypeClass)
          .errorCode(errorCode)
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
          .errorCode(errorCode)
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
          .errorCode(errorCode)
          .parserErrorCode(ParserErrorCode.INVALID_VALUE_INVALID_QUOTE_CHARACTER)
          .addParserErrorCodeArgs(
              ParserErrorCodeArgKeys.INVALID_CODE_POINT,
              "''")
          .build();
    }

    return InvalidValueException.builder()
        .invalidValue(value)
        .targetTypeClass(targetTypeClass)
        .errorCode(errorCode)
        .parserErrorCode(ParserErrorCode.INVALID_VALUE_INVALID_CHARACTER)
        .addParserErrorCodeArgs(
            ParserErrorCodeArgKeys.INVALID_CODE_POINT,
            new String(new int[]{invalidCodePoint}, 0, 1))
        .build();
  }

  static InvalidValueException forValueNotMatchRegex(
      final ErrorCode errorCode,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final Pattern regex) {

    return InvalidValueException.builder()
        .invalidValue(value)
        .targetTypeClass(targetTypeClass)
        .errorCode(errorCode)
        .parserErrorCode(ParserErrorCode.INVALID_VALUE_DOES_NOT_MATCH_REGEX_PATTERN)
        .addParserErrorCodeArgs(
            ParserErrorCodeArgKeys.REGEX_PATTERN,
            regex.toString())
        .build();
  }

  static InvalidValueException forValueNotValidUsingCustomValidation(
      final ErrorCode errorCode,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final Throwable cause) {

    return InvalidValueException.builder()
        .cause(cause)
        .invalidValue(value)
        .targetTypeClass(targetTypeClass)
        .errorCode(errorCode)
        .parserErrorCode(ParserErrorCode.INVALID_VALUE_DOES_NOT_PASS_CUSTOM_VALIDATION)
        .build();
  }

  static String unicodeHexCode(final int codePoint) {
    return Character.isSupplementaryCodePoint(codePoint)
        ? String.format("U+%06x", codePoint)
        : String.format("U+%04x", (short) codePoint);
  }

}
