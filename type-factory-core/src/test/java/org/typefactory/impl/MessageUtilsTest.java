package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class MessageUtilsTest {

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = "  ")
  void getMessage_returnsEmptyStringForNulEmptyOrBlankMessageKey(final String messageKey) {
    final var actual = MessageUtils.getMessage(messageKey);
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
  void getMessage_returnsMessageKeyWhenItIsUnknown(final String messageKey) {
    final var actual = MessageUtils.getMessage(messageKey);
    assertThat(actual)
        .isNotNull()
        .isNotEmpty()
        .isEqualTo(messageKey);
  }

  @Test
  void getMessage_returnsMessageKeyWhenMessageCannotBeCreatedDueToException() {
    final var messageKey = "invalid_value_does_not_match_regex_pattern";
    final var messageArgsThatCannotBeUnderstood = new Object[]{new SomeUnknownClass()};
    final var actual = MessageUtils.getMessage(messageKey, messageArgsThatCannotBeUnderstood);
    assertThat(actual)
        .isNotNull()
        .isNotEmpty()
        .isEqualTo(messageKey);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      invalid_value_does_not_match_regex_pattern    | Invalid value - does not match pattern {0}
      invalid_value_does_not_pass_custom_validation | Invalid value - does not pass custom validation criteria.
      invalid_value_invalid_character               | Invalid value - invalid character '{0}'.
      invalid_value_invalid_control_character       | Invalid value - invalid control character {0}.
      invalid_value_invalid_quote_character         | Invalid value - invalid quote character "{0}".
      invalid_value_invalid_whitespace_character    | Invalid value - invalid white-space character {0}.
      invalid_value_too_long                        | Invalid value - too long, maximum length is {0}.
      invalid_value_too_short                       | Invalid value - too short, minimum length is {0}.
      """, delimiter = '|')
  void getMessage_returnMessageAsExpectedWithNoMessageArgs(final String messageKey, final String expected) {
    final var actual = MessageUtils.getMessage(messageKey);
    assertThat(actual)
        .isNotNull()
        .isNotEmpty()
        .isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      invalid_value_does_not_match_regex_pattern    | Invalid value - does not match pattern {0}
      invalid_value_does_not_pass_custom_validation | Invalid value - does not pass custom validation criteria.
      invalid_value_invalid_character               | Invalid value - invalid character '{0}'.
      invalid_value_invalid_control_character       | Invalid value - invalid control character {0}.
      invalid_value_invalid_quote_character         | Invalid value - invalid quote character "{0}".
      invalid_value_invalid_whitespace_character    | Invalid value - invalid white-space character {0}.
      invalid_value_too_long                        | Invalid value - too long, maximum length is {0}.
      invalid_value_too_short                       | Invalid value - too short, minimum length is {0}.
      """, delimiter = '|')
  void getMessage_getMessage_returnMessageAsExpectedWithNoMessageArgsForUnknownLocale(final String messageKey, final String expected) {
    final var locale = Locale.FRENCH;
    final var actual = MessageUtils.getMessage(locale, messageKey, new Object[0]);
    assertThat(actual)
        .isNotNull()
        .isNotEmpty()
        .isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      invalid_value_does_not_match_regex_pattern    | ^ABC[0-9]$ | Invalid value - does not match pattern ^ABC[0-9]$
      invalid_value_does_not_pass_custom_validation |            | Invalid value - does not pass custom validation criteria.
      invalid_value_invalid_character               | Z          | Invalid value - invalid character 'Z'.
      invalid_value_invalid_control_character       | U+0003     | Invalid value - invalid control character U+0003.
      invalid_value_invalid_quote_character         | ''''       | Invalid value - invalid quote character "'".
      invalid_value_invalid_whitespace_character    | U+0020     | Invalid value - invalid white-space character U+0020.
      invalid_value_too_long                        | 44         | Invalid value - too long, maximum length is 44.
      invalid_value_too_short                       | 66         | Invalid value - too short, minimum length is 66.
      """, delimiter = '|')
  void getMessage_returnMessageAsExpectedWithMessageArgs(final String messageKey, final String messageArg, final String expected) {

    final var messageArgs = switch (messageKey) {
      case "invalid_value_too_long", "invalid_value_too_short" -> new Object[]{Integer.parseInt(messageArg)};
      default -> new Object[]{messageArg};
    };

    final var actual = MessageUtils.getMessage(messageKey, messageArgs);

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
