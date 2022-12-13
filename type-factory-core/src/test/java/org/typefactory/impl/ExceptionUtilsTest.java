package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.typefactory.InvalidValueException.ParserErrorCode.INVALID_VALUE_TOO_LONG;
import static org.typefactory.InvalidValueException.ParserErrorCode.INVALID_VALUE_TOO_SHORT;

import java.util.Locale;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.typefactory.ErrorCode;
import org.typefactory.InvalidValueException.ParserErrorCode;
import org.typefactory.testutils.ParserErrorCodeConverter;

class ExceptionUtilsTest {

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = "  ")
  void getMessage_returnsEmptyStringForNulEmptyOrBlankErrorCode(final String errorCode) {
    final var actual = ExceptionUtils.getMessage(ErrorCode.of(errorCode, ""));
    assertThat(actual)
        .isNotNull()
        .isEmpty();
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "a",
      "a.b",
      "aa_bb",
  })
  void getMessage_returnsErrorCodeWhenItIsUnknown(final String errorCode) {
    final var actual = ExceptionUtils.getMessage(ErrorCode.of(errorCode, ""));
    assertThat(actual)
        .isNotNull()
        .isNotEmpty()
        .isEqualTo(errorCode);
  }

  @Test
  void getMessage_returnsErrorCodeWhenMessageCannotBeCreatedDueToException() {
    final var errorCode = ParserErrorCode.INVALID_VALUE_DOES_NOT_MATCH_REGEX_PATTERN;
    final var messageArgsThatCannotBeUnderstood = new Object[]{new SomeUnknownClass()};
    final var actual = ExceptionUtils.getMessage(errorCode, messageArgsThatCannotBeUnderstood);
    assertThat(actual)
        .isNotNull()
        .isNotEmpty()
        .isEqualTo(errorCode.errorCode());
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      INVALID_VALUE_DOES_NOT_MATCH_REGEX_PATTERN    | Invalid value - does not match regular-expression pattern {0}
      INVALID_VALUE_DOES_NOT_PASS_CUSTOM_VALIDATION | Invalid value - does not pass custom validation criteria.
      INVALID_VALUE_INVALID_CHARACTER               | Invalid value - invalid character '{0}'.
      INVALID_VALUE_INVALID_CONTROL_CHARACTER       | Invalid value - invalid control character {0}.
      INVALID_VALUE_INVALID_QUOTE_CHARACTER         | Invalid value - invalid quote character "{0}".
      INVALID_VALUE_INVALID_WHITESPACE_CHARACTER    | Invalid value - invalid white-space character {0}.
      INVALID_VALUE_TOO_LONG                        | Invalid value - too long, maximum length is {0}.
      INVALID_VALUE_TOO_SHORT                       | Invalid value - too short, minimum length is {0}.
      """, delimiter = '|')
  void getMessage_returnMessageAsExpectedWithNoMessageArgs(
      @ConvertWith(ParserErrorCodeConverter.class) final ParserErrorCode errorCode,
      final String expected) {

    final var actual = ExceptionUtils.getMessage(errorCode);
    assertThat(actual)
        .isNotNull()
        .isNotEmpty()
        .isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      INVALID_VALUE_DOES_NOT_MATCH_REGEX_PATTERN    | Invalid value - does not match regular-expression pattern {0}
      INVALID_VALUE_DOES_NOT_PASS_CUSTOM_VALIDATION | Invalid value - does not pass custom validation criteria.
      INVALID_VALUE_INVALID_CHARACTER               | Invalid value - invalid character '{0}'.
      INVALID_VALUE_INVALID_CONTROL_CHARACTER       | Invalid value - invalid control character {0}.
      INVALID_VALUE_INVALID_QUOTE_CHARACTER         | Invalid value - invalid quote character "{0}".
      INVALID_VALUE_INVALID_WHITESPACE_CHARACTER    | Invalid value - invalid white-space character {0}.
      INVALID_VALUE_TOO_LONG                        | Invalid value - too long, maximum length is {0}.
      INVALID_VALUE_TOO_SHORT                       | Invalid value - too short, minimum length is {0}.
      """, delimiter = '|')
  void getMessage_getMessage_returnMessageAsExpectedWithNoMessageArgsForUnknownLocale(
      @ConvertWith(ParserErrorCodeConverter.class) final ParserErrorCode errorCode,
      final String expected) {

    final var locale = Locale.FRENCH;
    final var actual = ExceptionUtils.getMessage(locale, errorCode, new Object[0]);
    assertThat(actual)
        .isNotNull()
        .isNotEmpty()
        .isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      INVALID_VALUE_DOES_NOT_MATCH_REGEX_PATTERN    | ^ABC[0-9]$ | Invalid value - does not match regular-expression pattern ^ABC[0-9]$
      INVALID_VALUE_DOES_NOT_PASS_CUSTOM_VALIDATION |            | Invalid value - does not pass custom validation criteria.
      INVALID_VALUE_INVALID_CHARACTER               | Z          | Invalid value - invalid character 'Z'.
      INVALID_VALUE_INVALID_CONTROL_CHARACTER       | U+0003     | Invalid value - invalid control character U+0003.
      INVALID_VALUE_INVALID_QUOTE_CHARACTER         | ''''       | Invalid value - invalid quote character "'".
      INVALID_VALUE_INVALID_WHITESPACE_CHARACTER    | U+0020     | Invalid value - invalid white-space character U+0020.
      INVALID_VALUE_TOO_LONG                        | 44         | Invalid value - too long, maximum length is 44.
      INVALID_VALUE_TOO_SHORT                       | 66         | Invalid value - too short, minimum length is 66.
      """, delimiter = '|')
  void getMessage_returnMessageAsExpectedWithMessageArgs(
      @ConvertWith(ParserErrorCodeConverter.class) final ParserErrorCode errorCode,
      final String messageArg,
      final String expected) {

    final var messageArgs = INVALID_VALUE_TOO_LONG.equals(errorCode) || INVALID_VALUE_TOO_SHORT.equals(errorCode)
        ? new Object[]{Integer.parseInt(messageArg)}
        : new Object[]{messageArg};

    final var actual = ExceptionUtils.getMessage(errorCode, messageArgs);

    assertThat(actual)
        .isNotNull()
        .isNotEmpty()
        .isEqualTo(expected);
  }

  static class SomeUnknownClass {

    @Override
    public String toString() {
      throw new UnsupportedOperationException();
    }
  }
}
