package org.typefactory.assertions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.Locale;
import org.assertj.core.api.AbstractCharSequenceAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.MessageCode;

class MessageCodeAssertTest {

  @Test
  void messageCodeAssert_hasCustomAssertionMethods() {
    final var someActual = MessageCode.of("some.code.a", "some-message");
    final var messageCodeAssert = MessageCodeAssert.assertThat(someActual);
    assertThat(messageCodeAssert.getClass())
        .hasPublicMethods("hasCode", "hasDefaultMessage",
            "hasMessage", "hasMessageForLocale", "hasMessageForLocaleAndArgs");
  }

  @Test
  void assertThat_returnsMessageCodeAssertInstance() {
    final var someActual = MessageCode.of("some.code.a", "some-message");
    final var messageCodeAssert = MessageCodeAssert.assertThat(someActual);
    assertThat(messageCodeAssert)
        .isInstanceOf(MessageCodeAssert.class)
        .isInstanceOf(AbstractMessageCodeAssert.class)
        .isInstanceOf(AbstractCharSequenceAssert.class);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      ARGS           | EXPECTED
      null           | []
      [null]         | [null]
      [some-value]   | ["some-value"]
      [aa, bb, cc]   | ["aa", "bb", "cc"]
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void messageArgsToString_returnsExpectedString(
      @ConvertWith(StringArrayConverter.class) final String[] args, final String expected) {

    final var actual = MessageCodeAssert.messageArgsToString((Object[]) args);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void messageArgsToString_returnsExpectedStringForEmptyArray() {
    final var actual = MessageCodeAssert.messageArgsToString(new Object[0]);
    assertThat(actual).isEqualTo("[]");
  }

  @Test
  void messageArgsToString_returnsExpectedStringForNestedArray() {
    final var actual = MessageCodeAssert.messageArgsToString("a", new Object[]{"b", null, "c"}, 23, null, 45.6, Locale.CANADA_FRENCH, "d");
    assertThat(actual).isEqualTo("""
        ["a", ["b", null, "c"], 23, null, 45.6, fr_CA, "d"]""");
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      EXPECTED_VALUE | EXPECTED_EXCEPTION_MESSAGE
      null           | `\nExpecting actual not to be null`
      ``             | `\nExpecting actual not to be null`
      `  `           | `\nExpecting actual not to be null`
      some-value     | `\nExpecting actual not to be null`
      """, delimiter = '|', quoteCharacter = '`', nullValues = "null", useHeadersInDisplayName = true)
  void hasCode_throwsAssertionErrorForNullActual(final String expectedValue, final String expectedExceptionMessage) {
    final var actual = (MessageCode) null;
    final var messageCodeAssert = MessageCodeAssert.assertThat(actual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> messageCodeAssert.hasCode(expectedValue))
        .withMessage(expectedExceptionMessage);
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      CODE        | EXPECTED_CODE | EXPECTED_EXCEPTION_MESSAGE
      null        | null          | '\nExpected actual of type:  MessageCode\nto have code:  null\nbut code was:  "" (empty)'
      null        | ''            |
      null        | some.code.a   | '\nExpected actual of type:  MessageCode\nto have code:  "some.code.a"\nbut code was:  "" (empty)'
      null        | other.code    | '\nExpected actual of type:  MessageCode\nto have code:  "other.code"\nbut code was:  "" (empty)'
      ''          | null          | '\nExpected actual of type:  MessageCode\nto have code:  null\nbut code was:  "" (empty)'
      ''          | ''            |
      ''          | some.code.a   | '\nExpected actual of type:  MessageCode\nto have code:  "some.code.a"\nbut code was:  "" (empty)'
      ''          | other.code    | '\nExpected actual of type:  MessageCode\nto have code:  "other.code"\nbut code was:  "" (empty)'
      some.code.a | null          | '\nExpected actual of type:  MessageCode\nto have code:  null\nbut code was:  "some.code.a"'
      some.code.a | ''            | '\nExpected actual of type:  MessageCode\nto have code:  "" (empty)\nbut code was:  "some.code.a"'
      some.code.a | some.code.a   |
      some.code.a | other.code    | '\nExpected actual of type:  MessageCode\nto have code:  "other.code"\nbut code was:  "some.code.a"'
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void hasCode_behavesAsExpected(
      final String actualValue, final String expectedValue,
      final String expectedExceptionMessage) {

    final var actual = MessageCode.of(actualValue, "some-message");
    final var messageCodeAssert = MessageCodeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {

      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(() -> messageCodeAssert.hasCode(expectedValue))
          .withMessage(expectedExceptionMessage);

    } else {

      final var hasCodeResult = messageCodeAssert.hasCode(expectedValue);
      assertThat(hasCodeResult)
          .isInstanceOf(MessageCodeAssert.class)
          .isSameAs(messageCodeAssert);
    }
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      DEFAULT_MESSAGE | EXPECTED_DEFAULT_MESSAGE | EXPECTED_EXCEPTION_MESSAGE
      null            | null                     | '\nExpected actual of type:  MessageCode\nto have defaultMessage:  null\nbut defaultMessage was:  "" (empty)'
      null            | ''                       |
      null            | some-message             | '\nExpected actual of type:  MessageCode\nto have defaultMessage:  "some-message"\nbut defaultMessage was:  "" (empty)'
      null            | other-message            | '\nExpected actual of type:  MessageCode\nto have defaultMessage:  "other-message"\nbut defaultMessage was:  "" (empty)'
      ''              | null                     | '\nExpected actual of type:  MessageCode\nto have defaultMessage:  null\nbut defaultMessage was:  "" (empty)'
      ''              | ''                       |
      ''              | some-message             | '\nExpected actual of type:  MessageCode\nto have defaultMessage:  "some-message"\nbut defaultMessage was:  "" (empty)'
      ''              | other-message            | '\nExpected actual of type:  MessageCode\nto have defaultMessage:  "other-message"\nbut defaultMessage was:  "" (empty)'
      some-message    | null                     | '\nExpected actual of type:  MessageCode\nto have defaultMessage:  null\nbut defaultMessage was:  "some-message"'
      some-message    | ''                       | '\nExpected actual of type:  MessageCode\nto have defaultMessage:  "" (empty)\nbut defaultMessage was:  "some-message"'
      some-message    | some-message             |
      some-message    | other-message            | '\nExpected actual of type:  MessageCode\nto have defaultMessage:  "other-message"\nbut defaultMessage was:  "some-message"'
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void hasDefaultMessage_behavesAsExpected(
      final String defaultMessage, final String expectedDefaultMessage, final String expectedExceptionMessage) {

    final var actual = MessageCode.of("some.code.a", defaultMessage);
    final var messageCodeAssert = MessageCodeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {

      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(() -> messageCodeAssert.hasDefaultMessage(expectedDefaultMessage))
          .withMessage(expectedExceptionMessage);

    } else {

      final var hasCodeResult = messageCodeAssert.hasDefaultMessage(expectedDefaultMessage);
      assertThat(hasCodeResult)
          .isInstanceOf(MessageCodeAssert.class)
          .isSameAs(messageCodeAssert);
    }
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      CODE        | DEFAULT_MESSAGE | EXPECTED_MESSAGE | EXPECTED_EXCEPTION_MESSAGE
      null        | null            | null             | '\nExpected actual of type:  MessageCode\nto have message:  null\nbut message was:  "" (empty)'
      null        | null            | ''               |
      null        | null            | some.code.a      | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.a"\nbut message was:  "" (empty)'
      null        | null            | some-message     | '\nExpected actual of type:  MessageCode\nto have message:  "some-message"\nbut message was:  "" (empty)'
      null        | ''              | null             | '\nExpected actual of type:  MessageCode\nto have message:  null\nbut message was:  "" (empty)'
      null        | ''              | ''               |
      null        | ''              | some.code.a      | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.a"\nbut message was:  "" (empty)'
      null        | ''              | some-message     | '\nExpected actual of type:  MessageCode\nto have message:  "some-message"\nbut message was:  "" (empty)'
      null        | some-message    | null             | '\nExpected actual of type:  MessageCode\nto have message:  null\nbut message was:  "" (empty)'
      null        | some-message    | ''               |
      null        | some-message    | some.code.a      | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.a"\nbut message was:  "" (empty)'
      null        | some-message    | some-message     | '\nExpected actual of type:  MessageCode\nto have message:  "some-message"\nbut message was:  "" (empty)'
      ''          | null            | null             | '\nExpected actual of type:  MessageCode\nto have message:  null\nbut message was:  "" (empty)'
      ''          | null            | ''               |
      ''          | null            | some.code.a      | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.a"\nbut message was:  "" (empty)'
      ''          | null            | some-message     | '\nExpected actual of type:  MessageCode\nto have message:  "some-message"\nbut message was:  "" (empty)'
      ''          | ''              | null             | '\nExpected actual of type:  MessageCode\nto have message:  null\nbut message was:  "" (empty)'
      ''          | ''              | ''               |
      ''          | ''              | some.code.a      | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.a"\nbut message was:  "" (empty)'
      ''          | ''              | some-message     | '\nExpected actual of type:  MessageCode\nto have message:  "some-message"\nbut message was:  "" (empty)'
      ''          | some-message    | null             | '\nExpected actual of type:  MessageCode\nto have message:  null\nbut message was:  "" (empty)'
      ''          | some-message    | ''               |
      ''          | some-message    | some.code.a      | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.a"\nbut message was:  "" (empty)'
      ''          | some-message    | some-message     | '\nExpected actual of type:  MessageCode\nto have message:  "some-message"\nbut message was:  "" (empty)'
      some.code.a | null            | null             | '\nExpected actual of type:  MessageCode\nto have message:  null\nbut message was:  "some.code.a"'
      some.code.a | null            | ''               | '\nExpected actual of type:  MessageCode\nto have message:  "" (empty)\nbut message was:  "some.code.a"'
      some.code.a | null            | some.code.a      |
      some.code.a | null            | some-message     | '\nExpected actual of type:  MessageCode\nto have message:  "some-message"\nbut message was:  "some.code.a"'
      some.code.a | ''              | null             | '\nExpected actual of type:  MessageCode\nto have message:  null\nbut message was:  "some.code.a"'
      some.code.a | ''              | ''               | '\nExpected actual of type:  MessageCode\nto have message:  "" (empty)\nbut message was:  "some.code.a"'
      some.code.a | ''              | some.code.a      |
      some.code.a | ''              | some-message     | '\nExpected actual of type:  MessageCode\nto have message:  "some-message"\nbut message was:  "some.code.a"'
      some.code.a | some-message    | null             | '\nExpected actual of type:  MessageCode\nto have message:  null\nbut message was:  "some-message"'
      some.code.a | some-message    | ''               | '\nExpected actual of type:  MessageCode\nto have message:  "" (empty)\nbut message was:  "some-message"'
      some.code.a | some-message    | some.code.a      | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.a"\nbut message was:  "some-message"'
      some.code.a | some-message    | some-message     |
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void hasMessage_behavesAsExpected(
      final String code, final String defaultMessage,
      final String expectedDefaultMessage, final String expectedExceptionMessage) {

    final var actual = MessageCode.of(code, defaultMessage);
    final var messageCodeAssert = MessageCodeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {

      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(() -> messageCodeAssert.hasMessage(expectedDefaultMessage))
          .withMessage(expectedExceptionMessage);

    } else {

      final var hasCodeResult = messageCodeAssert.hasMessage(expectedDefaultMessage);
      assertThat(hasCodeResult)
          .isInstanceOf(MessageCodeAssert.class)
          .isSameAs(messageCodeAssert);
    }
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      CODE        | DEFAULT_MESSAGE  | EXPECTED_MESSAGE  | EXPECTED_EXCEPTION_MESSAGE
      null        | null             | null              | '\nExpected actual of type:  MessageCode\nto have message:  null\nfor message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      null        | null             | ''                |
      null        | null             | some.code.a       | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.a"\nfor message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      null        | null             | some-arg1-message | '\nExpected actual of type:  MessageCode\nto have message:  "some-arg1-message"\nfor message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      null        | ''               | null              | '\nExpected actual of type:  MessageCode\nto have message:  null\nfor message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      null        | ''               | ''                |
      null        | ''               | some.code.a       | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.a"\nfor message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      null        | ''               | some-arg1-message | '\nExpected actual of type:  MessageCode\nto have message:  "some-arg1-message"\nfor message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      null        | some-{1}-message | null              | '\nExpected actual of type:  MessageCode\nto have message:  null\nfor message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      null        | some-{1}-message | ''                |
      null        | some-{1}-message | some.code.a       | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.a"\nfor message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      null        | some-{1}-message | some-arg1-message | '\nExpected actual of type:  MessageCode\nto have message:  "some-arg1-message"\nfor message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      ''          | null             | null              | '\nExpected actual of type:  MessageCode\nto have message:  null\nfor message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      ''          | null             | ''                |
      ''          | null             | some.code.a       | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.a"\nfor message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      ''          | null             | some-arg1-message | '\nExpected actual of type:  MessageCode\nto have message:  "some-arg1-message"\nfor message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      ''          | ''               | null              | '\nExpected actual of type:  MessageCode\nto have message:  null\nfor message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      ''          | ''               | ''                |
      ''          | ''               | some.code.a       | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.a"\nfor message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      ''          | ''               | some-arg1-message | '\nExpected actual of type:  MessageCode\nto have message:  "some-arg1-message"\nfor message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      ''          | some-{1}-message | null              | '\nExpected actual of type:  MessageCode\nto have message:  null\nfor message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      ''          | some-{1}-message | ''                |
      ''          | some-{1}-message | some.code.a       | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.a"\nfor message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      ''          | some-{1}-message | some-arg1-message | '\nExpected actual of type:  MessageCode\nto have message:  "some-arg1-message"\nfor message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      some.code.a | null             | null              | '\nExpected actual of type:  MessageCode\nto have message:  null\nfor message-args:  ["arg0", "arg1"]\nbut message was:  "some.code.a"'
      some.code.a | null             | ''                | '\nExpected actual of type:  MessageCode\nto have message:  "" (empty)\nfor message-args:  ["arg0", "arg1"]\nbut message was:  "some.code.a"'
      some.code.a | null             | some.code.a       |
      some.code.a | null             | some-arg1-message | '\nExpected actual of type:  MessageCode\nto have message:  "some-arg1-message"\nfor message-args:  ["arg0", "arg1"]\nbut message was:  "some.code.a"'
      some.code.a | ''               | null              | '\nExpected actual of type:  MessageCode\nto have message:  null\nfor message-args:  ["arg0", "arg1"]\nbut message was:  "some.code.a"'
      some.code.a | ''               | ''                | '\nExpected actual of type:  MessageCode\nto have message:  "" (empty)\nfor message-args:  ["arg0", "arg1"]\nbut message was:  "some.code.a"'
      some.code.a | ''               | some.code.a       |
      some.code.a | ''               | some-arg1-message | '\nExpected actual of type:  MessageCode\nto have message:  "some-arg1-message"\nfor message-args:  ["arg0", "arg1"]\nbut message was:  "some.code.a"'
      some.code.a | some-{1}-message | null              | '\nExpected actual of type:  MessageCode\nto have message:  null\nfor message-args:  ["arg0", "arg1"]\nbut message was:  "some-arg1-message"'
      some.code.a | some-{1}-message | ''                | '\nExpected actual of type:  MessageCode\nto have message:  "" (empty)\nfor message-args:  ["arg0", "arg1"]\nbut message was:  "some-arg1-message"'
      some.code.a | some-{1}-message | some.code.a        | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.a"\nfor message-args:  ["arg0", "arg1"]\nbut message was:  "some-arg1-message"'
      some.code.a | some-{1}-message | some-arg1-message |
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void hasMessageForArgs_behavesAsExpected(
      final String code, final String defaultMessage,
      final String expectedDefaultMessage, final String expectedExceptionMessage) {

    final var actual = MessageCode.of(code, defaultMessage);
    final var messageCodeAssert = MessageCodeAssert.assertThat(actual);

    Assertions.setMaxStackTraceElementsDisplayed(10);

    if (expectedExceptionMessage != null) {

      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(() -> messageCodeAssert.hasMessageForArgs(expectedDefaultMessage, "arg0", "arg1"))
          .withMessage(expectedExceptionMessage);

    } else {

      final var hasCodeResult = messageCodeAssert.hasMessageForArgs(expectedDefaultMessage, "arg0", "arg1");
      assertThat(hasCodeResult)
          .isInstanceOf(MessageCodeAssert.class)
          .isSameAs(messageCodeAssert);
    }
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      CODE        | DEFAULT_MESSAGE | EXPECTED_MESSAGE | EXPECTED_EXCEPTION_MESSAGE
      null        | null            | null             | '\nExpected actual of type:  MessageCode\nto have message:  null\nfor locale:  de-DE\nbut message was:  "" (empty)'
      null        | null            | ''               |
      null        | null            | some.code.a      | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.a"\nfor locale:  de-DE\nbut message was:  "" (empty)'
      null        | null            | eine Nachricht   | '\nExpected actual of type:  MessageCode\nto have message:  "eine Nachricht"\nfor locale:  de-DE\nbut message was:  "" (empty)'
      null        | ''              | null             | '\nExpected actual of type:  MessageCode\nto have message:  null\nfor locale:  de-DE\nbut message was:  "" (empty)'
      null        | ''              | ''               |
      null        | ''              | some.code.a      | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.a"\nfor locale:  de-DE\nbut message was:  "" (empty)'
      null        | ''              | eine Nachricht   | '\nExpected actual of type:  MessageCode\nto have message:  "eine Nachricht"\nfor locale:  de-DE\nbut message was:  "" (empty)'
      null        | some-message    | null             | '\nExpected actual of type:  MessageCode\nto have message:  null\nfor locale:  de-DE\nbut message was:  "" (empty)'
      null        | some-message    | ''               |
      null        | some-message    | some.code.a      | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.a"\nfor locale:  de-DE\nbut message was:  "" (empty)'
      null        | some-message    | eine Nachricht   | '\nExpected actual of type:  MessageCode\nto have message:  "eine Nachricht"\nfor locale:  de-DE\nbut message was:  "" (empty)'
      ''          | null            | null             | '\nExpected actual of type:  MessageCode\nto have message:  null\nfor locale:  de-DE\nbut message was:  "" (empty)'
      ''          | null            | ''               |
      ''          | null            | some.code.a      | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.a"\nfor locale:  de-DE\nbut message was:  "" (empty)'
      ''          | null            | eine Nachricht   | '\nExpected actual of type:  MessageCode\nto have message:  "eine Nachricht"\nfor locale:  de-DE\nbut message was:  "" (empty)'
      ''          | ''              | null             | '\nExpected actual of type:  MessageCode\nto have message:  null\nfor locale:  de-DE\nbut message was:  "" (empty)'
      ''          | ''              | ''               |
      ''          | ''              | some.code.a      | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.a"\nfor locale:  de-DE\nbut message was:  "" (empty)'
      ''          | ''              | eine Nachricht   | '\nExpected actual of type:  MessageCode\nto have message:  "eine Nachricht"\nfor locale:  de-DE\nbut message was:  "" (empty)'
      ''          | some-message    | null             | '\nExpected actual of type:  MessageCode\nto have message:  null\nfor locale:  de-DE\nbut message was:  "" (empty)'
      ''          | some-message    | ''               |
      ''          | some-message    | some.code.a      | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.a"\nfor locale:  de-DE\nbut message was:  "" (empty)'
      ''          | some-message    | eine Nachricht   | '\nExpected actual of type:  MessageCode\nto have message:  "eine Nachricht"\nfor locale:  de-DE\nbut message was:  "" (empty)'
      some.code.a | null            | null             | '\nExpected actual of type:  MessageCode\nto have message:  null\nfor locale:  de-DE\nbut message was:  "eine Nachricht"'
      some.code.a | null            | ''               | '\nExpected actual of type:  MessageCode\nto have message:  "" (empty)\nfor locale:  de-DE\nbut message was:  "eine Nachricht"'
      some.code.a | null            | some.code.a      | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.a"\nfor locale:  de-DE\nbut message was:  "eine Nachricht"'
      some.code.a | null            | eine Nachricht   |
      some.code.a | ''              | null             | '\nExpected actual of type:  MessageCode\nto have message:  null\nfor locale:  de-DE\nbut message was:  "eine Nachricht"'
      some.code.a | ''              | ''               | '\nExpected actual of type:  MessageCode\nto have message:  "" (empty)\nfor locale:  de-DE\nbut message was:  "eine Nachricht"'
      some.code.a | ''              | some.code.a      | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.a"\nfor locale:  de-DE\nbut message was:  "eine Nachricht"'
      some.code.a | ''              | eine Nachricht   |
      some.code.a | some-message    | null             | '\nExpected actual of type:  MessageCode\nto have message:  null\nfor locale:  de-DE\nbut message was:  "eine Nachricht"'
      some.code.a | some-message    | ''               | '\nExpected actual of type:  MessageCode\nto have message:  "" (empty)\nfor locale:  de-DE\nbut message was:  "eine Nachricht"'
      some.code.a | some-message    | some.code.a      | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.a"\nfor locale:  de-DE\nbut message was:  "eine Nachricht"'
      some.code.a | some-message    | eine Nachricht   |
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void hasMessageForLocale_de_behavesAsExpected(
      final String code, final String defaultMessage,
      final String expectedMessage, final String expectedExceptionMessage) {

    final var actual = MessageCode.of(code, defaultMessage);
    final var messageCodeAssert = MessageCodeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {

      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(() -> messageCodeAssert.hasMessageForLocale(expectedMessage, Locale.GERMANY))
          .withMessage(expectedExceptionMessage);

    } else {

      final var hasCodeResult = messageCodeAssert.hasMessageForLocale(expectedMessage, Locale.GERMANY);
      assertThat(hasCodeResult)
          .isInstanceOf(MessageCodeAssert.class)
          .isSameAs(messageCodeAssert);
    }
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      CODE        | DEFAULT_MESSAGE  | EXPECTED_MESSAGE             | EXPECTED_EXCEPTION_MESSAGE
      null        | null             | null                         | '\nExpected actual of type:  MessageCode\nto have message:  null\nfor locale:  de-DE\nand for message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      null        | null             | ''                           |
      null        | null             | some.code.b                  | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.b"\nfor locale:  de-DE\nand for message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      null        | null             | eine Nachricht mit Wert arg1 | '\nExpected actual of type:  MessageCode\nto have message:  "eine Nachricht mit Wert arg1"\nfor locale:  de-DE\nand for message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      null        | ''               | null                         | '\nExpected actual of type:  MessageCode\nto have message:  null\nfor locale:  de-DE\nand for message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      null        | ''               | ''                           |
      null        | ''               | some.code.b                  | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.b"\nfor locale:  de-DE\nand for message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      null        | ''               | eine Nachricht mit Wert arg1 | '\nExpected actual of type:  MessageCode\nto have message:  "eine Nachricht mit Wert arg1"\nfor locale:  de-DE\nand for message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      null        | some-{1}-message | null                         | '\nExpected actual of type:  MessageCode\nto have message:  null\nfor locale:  de-DE\nand for message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      null        | some-{1}-message | ''                           |
      null        | some-{1}-message | some.code.b                  | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.b"\nfor locale:  de-DE\nand for message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      null        | some-{1}-message | eine Nachricht mit Wert arg1 | '\nExpected actual of type:  MessageCode\nto have message:  "eine Nachricht mit Wert arg1"\nfor locale:  de-DE\nand for message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      ''          | null             | null                         | '\nExpected actual of type:  MessageCode\nto have message:  null\nfor locale:  de-DE\nand for message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      ''          | null             | ''                           |
      ''          | null             | some.code.b                  | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.b"\nfor locale:  de-DE\nand for message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      ''          | null             | eine Nachricht mit Wert arg1 | '\nExpected actual of type:  MessageCode\nto have message:  "eine Nachricht mit Wert arg1"\nfor locale:  de-DE\nand for message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      ''          | ''               | null                         | '\nExpected actual of type:  MessageCode\nto have message:  null\nfor locale:  de-DE\nand for message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      ''          | ''               | ''                           |
      ''          | ''               | some.code.b                  | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.b"\nfor locale:  de-DE\nand for message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      ''          | ''               | eine Nachricht mit Wert arg1 | '\nExpected actual of type:  MessageCode\nto have message:  "eine Nachricht mit Wert arg1"\nfor locale:  de-DE\nand for message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      ''          | some-{1}-message | null                         | '\nExpected actual of type:  MessageCode\nto have message:  null\nfor locale:  de-DE\nand for message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      ''          | some-{1}-message | ''                           |
      ''          | some-{1}-message | some.code.b                  | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.b"\nfor locale:  de-DE\nand for message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      ''          | some-{1}-message | eine Nachricht mit Wert arg1 | '\nExpected actual of type:  MessageCode\nto have message:  "eine Nachricht mit Wert arg1"\nfor locale:  de-DE\nand for message-args:  ["arg0", "arg1"]\nbut message was:  "" (empty)'
      some.code.b | null             | null                         | '\nExpected actual of type:  MessageCode\nto have message:  null\nfor locale:  de-DE\nand for message-args:  ["arg0", "arg1"]\nbut message was:  "eine Nachricht mit Wert arg1"'
      some.code.b | null             | ''                           | '\nExpected actual of type:  MessageCode\nto have message:  "" (empty)\nfor locale:  de-DE\nand for message-args:  ["arg0", "arg1"]\nbut message was:  "eine Nachricht mit Wert arg1"'
      some.code.b | null             | some.code.b                  | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.b"\nfor locale:  de-DE\nand for message-args:  ["arg0", "arg1"]\nbut message was:  "eine Nachricht mit Wert arg1"'
      some.code.b | null             | eine Nachricht mit Wert arg1 |
      some.code.b | ''               | null                         | '\nExpected actual of type:  MessageCode\nto have message:  null\nfor locale:  de-DE\nand for message-args:  ["arg0", "arg1"]\nbut message was:  "eine Nachricht mit Wert arg1"'
      some.code.b | ''               | ''                           | '\nExpected actual of type:  MessageCode\nto have message:  "" (empty)\nfor locale:  de-DE\nand for message-args:  ["arg0", "arg1"]\nbut message was:  "eine Nachricht mit Wert arg1"'
      some.code.b | ''               | some.code.b                  | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.b"\nfor locale:  de-DE\nand for message-args:  ["arg0", "arg1"]\nbut message was:  "eine Nachricht mit Wert arg1"'
      some.code.b | ''               | eine Nachricht mit Wert arg1 |
      some.code.b | some-{1}-message | null                         | '\nExpected actual of type:  MessageCode\nto have message:  null\nfor locale:  de-DE\nand for message-args:  ["arg0", "arg1"]\nbut message was:  "eine Nachricht mit Wert arg1"'
      some.code.b | some-{1}-message | ''                           | '\nExpected actual of type:  MessageCode\nto have message:  "" (empty)\nfor locale:  de-DE\nand for message-args:  ["arg0", "arg1"]\nbut message was:  "eine Nachricht mit Wert arg1"'
      some.code.b | some-{1}-message | some.code.b                  | '\nExpected actual of type:  MessageCode\nto have message:  "some.code.b"\nfor locale:  de-DE\nand for message-args:  ["arg0", "arg1"]\nbut message was:  "eine Nachricht mit Wert arg1"'
      some.code.b | some-{1}-message | eine Nachricht mit Wert arg1 |
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void hasMessageForLocaleAndArgs_behavesAsExpected(
      final String code, final String defaultMessage,
      final String expectedDefaultMessage, final String expectedExceptionMessage) {

    final var actual = MessageCode.of(code, defaultMessage);
    final var messageCodeAssert = MessageCodeAssert.assertThat(actual);

    Assertions.setMaxStackTraceElementsDisplayed(10);

    if (expectedExceptionMessage != null) {

      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(() -> messageCodeAssert.hasMessageForLocaleAndArgs(expectedDefaultMessage, Locale.GERMANY, "arg0", "arg1"))
          .withMessage(expectedExceptionMessage);

    } else {

      final var hasCodeResult = messageCodeAssert.hasMessageForLocaleAndArgs(expectedDefaultMessage, Locale.GERMANY, "arg0", "arg1");
      assertThat(hasCodeResult)
          .isInstanceOf(MessageCodeAssert.class)
          .isSameAs(messageCodeAssert);
    }
  }

  @Test
  void isNull_doesNotThrowAssertionErrorForNullActual() {
    final var actual = (MessageCode) null;
    final var messageCodeAssert = MessageCodeAssert.assertThat(actual);

    assertThatNoException()
        .isThrownBy(messageCodeAssert::isNull);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      CODE       | DEFAULT_MESSAGE | EXPECTED_EXCEPTION_MESSAGE
      null       | null            | `\nexpected: null\n but was: ""`
      ``         | ``              | `\nexpected: null\n but was: ""`
      `  `       | `  `            | `\nexpected: null\n but was: ""`
      some-value | some-message    | `\nexpected: null\n but was: "some-value"`
      """, delimiter = '|', quoteCharacter = '`', nullValues = "null", useHeadersInDisplayName = true)
  void isNull_throwAssertionErrorForNonNullActual(
      final String code, final String defaultMessage, final String expectedExceptionMessage) {

    final var actual = MessageCode.of(code, defaultMessage);
    final var messageCodeAssert = MessageCodeAssert.assertThat(actual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(messageCodeAssert::isNull)
        .withMessage(expectedExceptionMessage);
  }

  @Test
  void isNotNull_throwsAssertionErrorForNullActual() {
    final var actual = (MessageCode) null;
    final var messageCodeAssert = MessageCodeAssert.assertThat(actual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(messageCodeAssert::isNotNull)
        .withMessage("\nExpecting actual not to be null");
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      CODE        | DEFAULT_MESSAGE
      null        | null
      some-value  | some-message
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void isNotNull_doesNotThrowAssertionErrorForNonNullActual(final String code, final String defaultMessage) {

    final var actual = MessageCode.of(code, defaultMessage);
    final var messageCodeAssert = MessageCodeAssert.assertThat(actual);

    assertThatNoException()
        .isThrownBy(messageCodeAssert::isNotNull);
  }

}
