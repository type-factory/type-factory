package org.typefactory.assertions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.typefactory.InvalidValueException;
import org.typefactory.InvalidValueException.ParserMessageCode;
import org.typefactory.LongType;
import org.typefactory.MessageCode;
import org.typefactory.StringType;

class InvalidValueExceptionAssertTest {

  @Test
  void invalidValueExceptionAssert_hasCustomAssertionMethods() {
    final var someActual = InvalidValueException.builder().build();
    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);
    assertThat(invalidValueExceptionAssert.getClass())
        .hasPublicMethods(
            "hasErrorCode",
            "hasErrorCodeAsString",
            "hasErrorMessage",
            "hasErrorMessage",
            "hasInvalidValue",
            "hasLocalizedMessage",
            "hasLocalizedMessage",
            "hasNoParserErrorProperties",
            "hasParserErrorArgs",
            "hasParserErrorArgsContainingAllOf",
            "hasParserErrorArgsContainingExactly",
            "hasParserErrorCode",
            "hasParserErrorCodeAsString",
            "hasParserErrorMessage",
            "hasParserErrorMessage",
            "hasParserErrorProperties",
            "hasParserErrorPropertiesContainingAllEntriesOf",
            "hasParserErrorPropertiesContainingExactlyEntriesOf",
            "hasTargetTypeClass");
  }

  @Test
  void classNameOfActual_returnsExtendsInvalidValueExceptionForNullActual() {
    final var someActual = (InvalidValueException) null;
    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);
    assertThat(invalidValueExceptionAssert.classNameOfActual()).isEqualTo("<? extends InvalidValueException>");
  }

  @Test
  void classNameOfActual_returnsInvalidValueExceptionForNonNullActual() {
    final var someActual = InvalidValueException.builder().build();
    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);
    assertThat(invalidValueExceptionAssert.classNameOfActual()).isEqualTo("InvalidValueException");
  }

  @Test
  void assertThat_returnsInvalidValueExceptionAssertInstance() {
    final var someActual = InvalidValueException.builder().build();
    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);
    assertThat(invalidValueExceptionAssert)
        .isInstanceOf(InvalidValueExceptionAssert.class)
        .isInstanceOf(AbstractInvalidValueExceptionAssert.class)
        .isInstanceOf(AbstractThrowableAssert.class);
  }

  private static Stream<Arguments> provideTargetTypeClassArguments() {
    final Class<?>[] classes = {null, StringType.class, LongType.class};
    final Class<?>[] expectedClasses = {null, StringType.class, LongType.class};
    return Stream.of(classes)
        .flatMap(targetClass -> Stream.of(expectedClasses)
            .map(expectedTargetClass -> {
              final var expectedExceptionMessage = targetClass == expectedTargetClass
                  ? null
                  : String.format(
                      "\nExpected actual of type:  InvalidValueException\nto have targetTypeClass:  %s\nbut targetTypeClass was:  %s",
                      expectedTargetClass == null ? "null" : expectedTargetClass.getName(),
                      targetClass == null ? "null" : targetClass.getName());
              return Arguments.of(targetClass, expectedTargetClass, expectedExceptionMessage);
            }));
  }

  @ParameterizedTest
  @MethodSource("provideTargetTypeClassArguments")
  void hasTargetTypeClass_throwsExceptionForNull(
      final Class<?> targetClass, final Class<?> expectedTargetClass, final String expectedExceptionMessage) {
    final var someActual = InvalidValueException.builder().targetTypeClass(targetClass).build();
    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);

    if (expectedExceptionMessage != null) {
      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(() -> invalidValueExceptionAssert.hasTargetTypeClass(expectedTargetClass))
          .withMessage(expectedExceptionMessage);
    } else {
      assertThatNoException().isThrownBy(() -> invalidValueExceptionAssert.hasTargetTypeClass(expectedTargetClass));
      assertThat(invalidValueExceptionAssert.hasTargetTypeClass(expectedTargetClass))
          .isInstanceOf(InvalidValueExceptionAssert.class)
          .isSameAs(invalidValueExceptionAssert);
    }
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      ACTUAL_CODE  | EXPECTED_CODE  | EXPECTED_EXCEPTION_MESSAGE
      null         | null           | '\nExpected actual of type:  InvalidValueException\nto have errorCode:  null\nbut errorCode was:  "" (empty)'
      null         | ''             |
      null         | some.code      | '\nExpected actual of type:  InvalidValueException\nto have errorCode:  "some.code"\nbut errorCode was:  "" (empty)'
      null         | other.code     | '\nExpected actual of type:  InvalidValueException\nto have errorCode:  "other.code"\nbut errorCode was:  "" (empty)'
      ''           | null           | '\nExpected actual of type:  InvalidValueException\nto have errorCode:  null\nbut errorCode was:  "" (empty)'
      ''           | ''             |
      ''           | some.code      | '\nExpected actual of type:  InvalidValueException\nto have errorCode:  "some.code"\nbut errorCode was:  "" (empty)'
      ''           | other.code     | '\nExpected actual of type:  InvalidValueException\nto have errorCode:  "other.code"\nbut errorCode was:  "" (empty)'
      some.code    | null           | '\nExpected actual of type:  InvalidValueException\nto have errorCode:  null\nbut errorCode was:  "some.code"'
      some.code    | ''             | '\nExpected actual of type:  InvalidValueException\nto have errorCode:  "" (empty)\nbut errorCode was:  "some.code"'
      some.code    | some.code      |
      some.code    | other.code     | '\nExpected actual of type:  InvalidValueException\nto have errorCode:  "other.code"\nbut errorCode was:  "some.code"'
      other.code   | null           | '\nExpected actual of type:  InvalidValueException\nto have errorCode:  null\nbut errorCode was:  "other.code"'
      other.code   | ''             | '\nExpected actual of type:  InvalidValueException\nto have errorCode:  "" (empty)\nbut errorCode was:  "other.code"'
      other.code   | some.code      | '\nExpected actual of type:  InvalidValueException\nto have errorCode:  "some.code"\nbut errorCode was:  "other.code"'
      other.code   | other.code     |
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void hasErrorCode_behavesAsExpected(
      final String actualCode, final String expectedCode,
      final String expectedExceptionMessage) {

    final var actualErrorCode = actualCode == null ? null : MessageCode.of(actualCode, "some-message");
    final var expectedErrorCode = expectedCode == null ? null : MessageCode.of(expectedCode, "some-message");
    final var expectedErrorCodeAsString = expectedCode;
    final var someActual = InvalidValueException.builder()
        .errorCode(actualErrorCode)
        .build();

    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);

    Assertions.setMaxStackTraceElementsDisplayed(10);

    if (expectedExceptionMessage != null) {

      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(() -> invalidValueExceptionAssert.hasErrorCode(expectedErrorCode))
          .withMessage(expectedExceptionMessage);

      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(() -> invalidValueExceptionAssert.hasErrorCodeAsString(expectedErrorCodeAsString))
          .withMessage(expectedExceptionMessage);

    } else {

      assertThatNoException().isThrownBy(() -> invalidValueExceptionAssert.hasErrorCode(expectedErrorCode));
      assertThatNoException().isThrownBy(() -> invalidValueExceptionAssert.hasErrorCodeAsString(expectedErrorCodeAsString));

      assertThat(invalidValueExceptionAssert.hasErrorCode(expectedErrorCode))
          .isInstanceOf(InvalidValueExceptionAssert.class)
          .isSameAs(invalidValueExceptionAssert);

      assertThat(invalidValueExceptionAssert.hasErrorCode(expectedErrorCode))
          .isInstanceOf(InvalidValueExceptionAssert.class)
          .isSameAs(invalidValueExceptionAssert);
    }
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      CODE      | DEFAULT_MESSAGE | EXPECTED_MESSAGE | EXPECTED_EXCEPTION_MESSAGE
      null      | null            | null             | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  null\nbut errorMessage was:  "" (empty)'
      null      | null            | ''               |
      null      | null            | some message     | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "some message"\nbut errorMessage was:  "" (empty)'
      null      | null            | other message    | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "other message"\nbut errorMessage was:  "" (empty)'
      null      | ''              | null             | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  null\nbut errorMessage was:  "" (empty)'
      null      | ''              | ''               |
      null      | ''              | some message     | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "some message"\nbut errorMessage was:  "" (empty)'
      null      | ''              | other message    | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "other message"\nbut errorMessage was:  "" (empty)'
      null      | some message    | null             | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  null\nbut errorMessage was:  "" (empty)'
      null      | some message    | ''               |
      null      | some message    | some message     | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "some message"\nbut errorMessage was:  "" (empty)'
      null      | some message    | other message    | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "other message"\nbut errorMessage was:  "" (empty)'
      null      | other message   | null             | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  null\nbut errorMessage was:  "" (empty)'
      null      | other message   | ''               |
      null      | other message   | some message     | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "some message"\nbut errorMessage was:  "" (empty)'
      null      | other message   | other message    | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "other message"\nbut errorMessage was:  "" (empty)'
      ''        | null            | null             | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  null\nbut errorMessage was:  "" (empty)'
      ''        | null            | ''               |
      ''        | null            | some message     | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "some message"\nbut errorMessage was:  "" (empty)'
      ''        | null            | other message    | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "other message"\nbut errorMessage was:  "" (empty)'
      ''        | ''              | null             | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  null\nbut errorMessage was:  "" (empty)'
      ''        | ''              | ''               |
      ''        | ''              | some message     | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "some message"\nbut errorMessage was:  "" (empty)'
      ''        | ''              | other message    | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "other message"\nbut errorMessage was:  "" (empty)'
      ''        | some message    | null             | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  null\nbut errorMessage was:  "" (empty)'
      ''        | some message    | ''               |
      ''        | some message    | some message     | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "some message"\nbut errorMessage was:  "" (empty)'
      ''        | some message    | other message    | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "other message"\nbut errorMessage was:  "" (empty)'
      ''        | other message   | null             | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  null\nbut errorMessage was:  "" (empty)'
      ''        | other message   | ''               |
      ''        | other message   | some message     | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "some message"\nbut errorMessage was:  "" (empty)'
      ''        | other message   | other message    | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "other message"\nbut errorMessage was:  "" (empty)'
      some.code | null            | null             | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  null\nbut errorMessage was:  "some.code"'
      some.code | null            | ''               | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "" (empty)\nbut errorMessage was:  "some.code"'
      some.code | null            | some message     | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "some message"\nbut errorMessage was:  "some.code"'
      some.code | null            | other message    | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "other message"\nbut errorMessage was:  "some.code"'
      some.code | ''              | null             | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  null\nbut errorMessage was:  "some.code"'
      some.code | ''              | ''               | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "" (empty)\nbut errorMessage was:  "some.code"'
      some.code | ''              | some message     | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "some message"\nbut errorMessage was:  "some.code"'
      some.code | ''              | other message    | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "other message"\nbut errorMessage was:  "some.code"'
      some.code | some message    | null             | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  null\nbut errorMessage was:  "some message"'
      some.code | some message    | ''               | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "" (empty)\nbut errorMessage was:  "some message"'
      some.code | some message    | some message     |
      some.code | some message    | other message    | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "other message"\nbut errorMessage was:  "some message"'
      some.code | other message   | null             | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  null\nbut errorMessage was:  "other message"'
      some.code | other message   | ''               | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "" (empty)\nbut errorMessage was:  "other message"'
      some.code | other message   | some message     | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "some message"\nbut errorMessage was:  "other message"'
      some.code | other message   | other message    |
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void hasErrorMessage_behavesAsExpected(
      final String code,
      final String defaultMessage, final String expectedMessage,
      final String expectedExceptionMessage) {

    final var someActual = InvalidValueException.builder()
        .errorCode(MessageCode.of(code, defaultMessage))
        .build();

    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);

    Assertions.setMaxStackTraceElementsDisplayed(10);

    if (expectedExceptionMessage != null) {

      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(() -> invalidValueExceptionAssert.hasErrorMessage(expectedMessage))
          .withMessage(expectedExceptionMessage);

    } else {

      assertThatNoException().isThrownBy(() -> invalidValueExceptionAssert.hasErrorMessage(expectedMessage));

      final var assertionResult = invalidValueExceptionAssert.hasErrorMessage(expectedMessage);
      assertThat(assertionResult)
          .isInstanceOf(InvalidValueExceptionAssert.class)
          .isSameAs(invalidValueExceptionAssert);
    }
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      CODE      | DEFAULT_MESSAGE | EXPECTED_MESSAGE | LOCALE | EXPECTED_EXCEPTION_MESSAGE
      null      | null            | null             | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  null\nfor locale:  de-DE\nbut errorMessage was:  "" (empty)'
      null      | null            | ''               | de-DE  |
      null      | null            | some message     | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "some message"\nfor locale:  de-DE\nbut errorMessage was:  "" (empty)'
      null      | null            | eine Nachricht   | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "eine Nachricht"\nfor locale:  de-DE\nbut errorMessage was:  "" (empty)'
      null      | ''              | null             | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  null\nfor locale:  de-DE\nbut errorMessage was:  "" (empty)'
      null      | ''              | ''               | de-DE  |
      null      | ''              | some message     | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "some message"\nfor locale:  de-DE\nbut errorMessage was:  "" (empty)'
      null      | ''              | eine Nachricht   | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "eine Nachricht"\nfor locale:  de-DE\nbut errorMessage was:  "" (empty)'
      null      | some message    | null             | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  null\nfor locale:  de-DE\nbut errorMessage was:  "" (empty)'
      null      | some message    | ''               | de-DE  |
      null      | some message    | some message     | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "some message"\nfor locale:  de-DE\nbut errorMessage was:  "" (empty)'
      null      | some message    | eine Nachricht   | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "eine Nachricht"\nfor locale:  de-DE\nbut errorMessage was:  "" (empty)'
      ''        | null            | null             | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  null\nfor locale:  de-DE\nbut errorMessage was:  "" (empty)'
      ''        | null            | ''               | de-DE  |
      ''        | null            | some message     | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "some message"\nfor locale:  de-DE\nbut errorMessage was:  "" (empty)'
      ''        | null            | eine Nachricht   | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "eine Nachricht"\nfor locale:  de-DE\nbut errorMessage was:  "" (empty)'
      ''        | ''              | null             | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  null\nfor locale:  de-DE\nbut errorMessage was:  "" (empty)'
      ''        | ''              | ''               | de-DE  |
      ''        | ''              | some message     | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "some message"\nfor locale:  de-DE\nbut errorMessage was:  "" (empty)'
      ''        | ''              | eine Nachricht   | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "eine Nachricht"\nfor locale:  de-DE\nbut errorMessage was:  "" (empty)'
      ''        | some message    | null             | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  null\nfor locale:  de-DE\nbut errorMessage was:  "" (empty)'
      ''        | some message    | ''               | de-DE  |
      ''        | some message    | some message     | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "some message"\nfor locale:  de-DE\nbut errorMessage was:  "" (empty)'
      ''        | some message    | eine Nachricht   | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "eine Nachricht"\nfor locale:  de-DE\nbut errorMessage was:  "" (empty)'
      some.code | null            | null             | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  null\nfor locale:  de-DE\nbut errorMessage was:  "eine Nachricht"'
      some.code | null            | ''               | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "" (empty)\nfor locale:  de-DE\nbut errorMessage was:  "eine Nachricht"'
      some.code | null            | some message     | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "some message"\nfor locale:  de-DE\nbut errorMessage was:  "eine Nachricht"'
      some.code | null            | eine Nachricht   | de-DE  |
      some.code | null            | other message    | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "other message"\nfor locale:  de-DE\nbut errorMessage was:  "eine Nachricht"'
      some.code | ''              | null             | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  null\nfor locale:  de-DE\nbut errorMessage was:  "eine Nachricht"'
      some.code | ''              | ''               | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "" (empty)\nfor locale:  de-DE\nbut errorMessage was:  "eine Nachricht"'
      some.code | ''              | some message     | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "some message"\nfor locale:  de-DE\nbut errorMessage was:  "eine Nachricht"'
      some.code | ''              | eine Nachricht   | de-DE  |
      some.code | some message    | null             | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  null\nfor locale:  de-DE\nbut errorMessage was:  "eine Nachricht"'
      some.code | some message    | ''               | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "" (empty)\nfor locale:  de-DE\nbut errorMessage was:  "eine Nachricht"'
      some.code | some message    | some message     | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have errorMessage:  "some message"\nfor locale:  de-DE\nbut errorMessage was:  "eine Nachricht"'
      some.code | some message    | eine Nachricht   | de-DE  |
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void hasErrorMessage_behavesAsExpectedForLocale(
      final String code,
      final String defaultMessage, final String expectedMessage,
      final String localeString,
      final String expectedExceptionMessage) {

    final Locale locale = Locale.forLanguageTag(localeString);
    final var someActual = InvalidValueException.builder()
        .errorCode(MessageCode.of(code, defaultMessage))
        .build();

    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);

    Assertions.setMaxStackTraceElementsDisplayed(10);

    if (expectedExceptionMessage != null) {

      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(() -> invalidValueExceptionAssert.hasErrorMessage(locale, expectedMessage))
          .withMessage(expectedExceptionMessage);

    } else {

      assertThatNoException().isThrownBy(() -> invalidValueExceptionAssert.hasErrorMessage(locale, expectedMessage));

      final var assertionResult = invalidValueExceptionAssert.hasErrorMessage(locale, expectedMessage);
      assertThat(assertionResult)
          .isInstanceOf(InvalidValueExceptionAssert.class)
          .isSameAs(invalidValueExceptionAssert);
    }
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      INVALID_VALUE | EXPECTED_VALUE | EXPECTED_EXCEPTION_MESSAGE
      null          | null           |
      null          | ''             | '\nExpected actual of type:  InvalidValueException\nto have invalidValue:  "" (empty)\nbut invalidValue was:  null'
      null          | some value     | '\nExpected actual of type:  InvalidValueException\nto have invalidValue:  "some value"\nbut invalidValue was:  null'
      null          | other value    | '\nExpected actual of type:  InvalidValueException\nto have invalidValue:  "other value"\nbut invalidValue was:  null'
      ''            | null           | '\nExpected actual of type:  InvalidValueException\nto have invalidValue:  null\nbut invalidValue was:  "" (empty)'
      ''            | ''             |
      ''            | some value     | '\nExpected actual of type:  InvalidValueException\nto have invalidValue:  "some value"\nbut invalidValue was:  "" (empty)'
      ''            | other value    | '\nExpected actual of type:  InvalidValueException\nto have invalidValue:  "other value"\nbut invalidValue was:  "" (empty)'
      some value    | null           | '\nExpected actual of type:  InvalidValueException\nto have invalidValue:  null\nbut invalidValue was:  "some value"'
      some value    | ''             | '\nExpected actual of type:  InvalidValueException\nto have invalidValue:  "" (empty)\nbut invalidValue was:  "some value"'
      some value    | some value     |
      some value    | other value    | '\nExpected actual of type:  InvalidValueException\nto have invalidValue:  "other value"\nbut invalidValue was:  "some value"'
      other value   | null           | '\nExpected actual of type:  InvalidValueException\nto have invalidValue:  null\nbut invalidValue was:  "other value"'
      other value   | ''             | '\nExpected actual of type:  InvalidValueException\nto have invalidValue:  "" (empty)\nbut invalidValue was:  "other value"'
      other value   | some value     | '\nExpected actual of type:  InvalidValueException\nto have invalidValue:  "some value"\nbut invalidValue was:  "other value"'
      other value   | other value    |
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void hasInvalidValue_behavesAsExpected(
      final String invalidValue, final String expectedInvalidValue,
      final String expectedExceptionMessage) {

    final var someActual = InvalidValueException.builder()
        .invalidValue(invalidValue)
        .build();

    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);

    Assertions.setMaxStackTraceElementsDisplayed(10);

    if (expectedExceptionMessage != null) {

      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(() -> invalidValueExceptionAssert.hasInvalidValue(expectedInvalidValue))
          .withMessage(expectedExceptionMessage);

    } else {

      assertThatNoException().isThrownBy(() -> invalidValueExceptionAssert.hasInvalidValue(expectedInvalidValue));

      final var assertionResult = invalidValueExceptionAssert.hasInvalidValue(expectedInvalidValue);
      assertThat(assertionResult)
          .isInstanceOf(InvalidValueExceptionAssert.class)
          .isSameAs(invalidValueExceptionAssert);
    }
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      CODE      | DEFAULT_MESSAGE | EXPECTED_MESSAGE | LOCALE | EXPECTED_EXCEPTION_MESSAGE
      null      | null            | null             | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have localizedMessage:  null\nfor locale:  de-DE\nbut localizedMessage was:  "" (empty)'
      null      | null            | ''               | de-DE  |
      null      | null            | some message     | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have localizedMessage:  "some message"\nfor locale:  de-DE\nbut localizedMessage was:  "" (empty)'
      null      | null            | eine Nachricht   | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have localizedMessage:  "eine Nachricht"\nfor locale:  de-DE\nbut localizedMessage was:  "" (empty)'
      null      | ''              | null             | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have localizedMessage:  null\nfor locale:  de-DE\nbut localizedMessage was:  "" (empty)'
      null      | ''              | ''               | de-DE  |
      null      | ''              | some message     | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have localizedMessage:  "some message"\nfor locale:  de-DE\nbut localizedMessage was:  "" (empty)'
      null      | ''              | eine Nachricht   | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have localizedMessage:  "eine Nachricht"\nfor locale:  de-DE\nbut localizedMessage was:  "" (empty)'
      null      | some message    | null             | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have localizedMessage:  null\nfor locale:  de-DE\nbut localizedMessage was:  "" (empty)'
      null      | some message    | ''               | de-DE  |
      null      | some message    | some message     | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have localizedMessage:  "some message"\nfor locale:  de-DE\nbut localizedMessage was:  "" (empty)'
      null      | some message    | eine Nachricht   | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have localizedMessage:  "eine Nachricht"\nfor locale:  de-DE\nbut localizedMessage was:  "" (empty)'
      ''        | null            | null             | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have localizedMessage:  null\nfor locale:  de-DE\nbut localizedMessage was:  "" (empty)'
      ''        | null            | ''               | de-DE  |
      ''        | null            | some message     | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have localizedMessage:  "some message"\nfor locale:  de-DE\nbut localizedMessage was:  "" (empty)'
      ''        | null            | eine Nachricht   | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have localizedMessage:  "eine Nachricht"\nfor locale:  de-DE\nbut localizedMessage was:  "" (empty)'
      ''        | ''              | null             | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have localizedMessage:  null\nfor locale:  de-DE\nbut localizedMessage was:  "" (empty)'
      ''        | ''              | ''               | de-DE  |
      ''        | ''              | some message     | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have localizedMessage:  "some message"\nfor locale:  de-DE\nbut localizedMessage was:  "" (empty)'
      ''        | ''              | eine Nachricht   | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have localizedMessage:  "eine Nachricht"\nfor locale:  de-DE\nbut localizedMessage was:  "" (empty)'
      ''        | some message    | null             | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have localizedMessage:  null\nfor locale:  de-DE\nbut localizedMessage was:  "" (empty)'
      ''        | some message    | ''               | de-DE  |
      ''        | some message    | some message     | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have localizedMessage:  "some message"\nfor locale:  de-DE\nbut localizedMessage was:  "" (empty)'
      ''        | some message    | eine Nachricht   | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have localizedMessage:  "eine Nachricht"\nfor locale:  de-DE\nbut localizedMessage was:  "" (empty)'
      some.code | null            | null             | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have localizedMessage:  null\nfor locale:  de-DE\nbut localizedMessage was:  "eine Nachricht"'
      some.code | null            | ''               | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have localizedMessage:  "" (empty)\nfor locale:  de-DE\nbut localizedMessage was:  "eine Nachricht"'
      some.code | null            | some message     | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have localizedMessage:  "some message"\nfor locale:  de-DE\nbut localizedMessage was:  "eine Nachricht"'
      some.code | null            | eine Nachricht   | de-DE  |
      some.code | null            | other message    | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have localizedMessage:  "other message"\nfor locale:  de-DE\nbut localizedMessage was:  "eine Nachricht"'
      some.code | ''              | null             | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have localizedMessage:  null\nfor locale:  de-DE\nbut localizedMessage was:  "eine Nachricht"'
      some.code | ''              | ''               | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have localizedMessage:  "" (empty)\nfor locale:  de-DE\nbut localizedMessage was:  "eine Nachricht"'
      some.code | ''              | some message     | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have localizedMessage:  "some message"\nfor locale:  de-DE\nbut localizedMessage was:  "eine Nachricht"'
      some.code | ''              | eine Nachricht   | de-DE  |
      some.code | some message    | null             | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have localizedMessage:  null\nfor locale:  de-DE\nbut localizedMessage was:  "eine Nachricht"'
      some.code | some message    | ''               | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have localizedMessage:  "" (empty)\nfor locale:  de-DE\nbut localizedMessage was:  "eine Nachricht"'
      some.code | some message    | some message     | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have localizedMessage:  "some message"\nfor locale:  de-DE\nbut localizedMessage was:  "eine Nachricht"'
      some.code | some message    | eine Nachricht   | de-DE  |
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void hasLocalizedMessage_behavesAsExpectedForLocale(
      final String code,
      final String defaultMessage, final String expectedMessage,
      final String localeString,
      final String expectedExceptionMessage) {

    final Locale locale = Locale.forLanguageTag(localeString);
    Locale.setDefault(locale);

    final var someActual = InvalidValueException.builder()
        .errorCode(MessageCode.of(code, defaultMessage))
        .build();

    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);

    Assertions.setMaxStackTraceElementsDisplayed(10);

    if (expectedExceptionMessage != null) {

      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(() -> invalidValueExceptionAssert.hasLocalizedMessage(expectedMessage))
          .withMessage(expectedExceptionMessage);

      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(() -> invalidValueExceptionAssert.hasLocalizedMessage(locale, expectedMessage))
          .withMessage(expectedExceptionMessage);


    } else {

      assertThatNoException().isThrownBy(() -> invalidValueExceptionAssert.hasLocalizedMessage(expectedMessage));
      assertThatNoException().isThrownBy(() -> invalidValueExceptionAssert.hasLocalizedMessage(locale, expectedMessage));

      assertThat(invalidValueExceptionAssert.hasLocalizedMessage(expectedMessage))
          .isInstanceOf(InvalidValueExceptionAssert.class)
          .isSameAs(invalidValueExceptionAssert);

      assertThat(invalidValueExceptionAssert.hasLocalizedMessage(locale, expectedMessage))
          .isInstanceOf(InvalidValueExceptionAssert.class)
          .isSameAs(invalidValueExceptionAssert);
    }
  }


  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      ACTUAL_CODE  | EXPECTED_CODE  | EXPECTED_EXCEPTION_MESSAGE
      null         | null           | '\nExpected actual of type:  InvalidValueException\nto have parserErrorCode:  null\nbut parserErrorCode was:  "" (empty)'
      null         | ''             |
      null         | some.code      | '\nExpected actual of type:  InvalidValueException\nto have parserErrorCode:  "some.code"\nbut parserErrorCode was:  "" (empty)'
      null         | other.code     | '\nExpected actual of type:  InvalidValueException\nto have parserErrorCode:  "other.code"\nbut parserErrorCode was:  "" (empty)'
      ''           | null           | '\nExpected actual of type:  InvalidValueException\nto have parserErrorCode:  null\nbut parserErrorCode was:  "" (empty)'
      ''           | ''             |
      ''           | some.code      | '\nExpected actual of type:  InvalidValueException\nto have parserErrorCode:  "some.code"\nbut parserErrorCode was:  "" (empty)'
      ''           | other.code     | '\nExpected actual of type:  InvalidValueException\nto have parserErrorCode:  "other.code"\nbut parserErrorCode was:  "" (empty)'
      some.code    | null           | '\nExpected actual of type:  InvalidValueException\nto have parserErrorCode:  null\nbut parserErrorCode was:  "some.code"'
      some.code    | ''             | '\nExpected actual of type:  InvalidValueException\nto have parserErrorCode:  "" (empty)\nbut parserErrorCode was:  "some.code"'
      some.code    | some.code      |
      some.code    | other.code     | '\nExpected actual of type:  InvalidValueException\nto have parserErrorCode:  "other.code"\nbut parserErrorCode was:  "some.code"'
      other.code   | null           | '\nExpected actual of type:  InvalidValueException\nto have parserErrorCode:  null\nbut parserErrorCode was:  "other.code"'
      other.code   | ''             | '\nExpected actual of type:  InvalidValueException\nto have parserErrorCode:  "" (empty)\nbut parserErrorCode was:  "other.code"'
      other.code   | some.code      | '\nExpected actual of type:  InvalidValueException\nto have parserErrorCode:  "some.code"\nbut parserErrorCode was:  "other.code"'
      other.code   | other.code     |
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void hasParserErrorCode_behavesAsExpected(
      final String actualCode, final String expectedCode,
      final String expectedExceptionMessage) {

    final var actualParserErrorCode = actualCode == null ? null : new SomeParserMessageCode(actualCode, "some-message");
    final var expectedParserErrorCode = expectedCode == null ? null : new SomeParserMessageCode(expectedCode, "some-message");
    final var expectedParserErrorCodeAsString = expectedCode;

    final var someActual = InvalidValueException.builder()
        .parserErrorCode(actualParserErrorCode)
        .build();

    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);

    Assertions.setMaxStackTraceElementsDisplayed(10);

    if (expectedExceptionMessage != null) {

      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(() -> invalidValueExceptionAssert.hasParserErrorCode(expectedParserErrorCode))
          .withMessage(expectedExceptionMessage);

      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(() -> invalidValueExceptionAssert.hasParserErrorCodeAsString(expectedParserErrorCodeAsString))
          .withMessage(expectedExceptionMessage);

    } else {

      assertThatNoException().isThrownBy(() -> invalidValueExceptionAssert.hasParserErrorCode(expectedParserErrorCode));
      assertThatNoException().isThrownBy(() -> invalidValueExceptionAssert.hasParserErrorCodeAsString(expectedParserErrorCodeAsString));

      assertThat(invalidValueExceptionAssert.hasParserErrorCode(expectedParserErrorCode))
          .isInstanceOf(InvalidValueExceptionAssert.class)
          .isSameAs(invalidValueExceptionAssert);

      assertThat(invalidValueExceptionAssert.hasParserErrorCodeAsString(expectedParserErrorCodeAsString))
          .isInstanceOf(InvalidValueExceptionAssert.class)
          .isSameAs(invalidValueExceptionAssert);
    }
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      CODE      | DEFAULT_MESSAGE | EXPECTED_MESSAGE | EXPECTED_EXCEPTION_MESSAGE
      null      | null            | null             | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  null\nbut parserErrorMessage was:  "" (empty)'
      null      | null            | ''               |
      null      | null            | some message     | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "some message"\nbut parserErrorMessage was:  "" (empty)'
      null      | null            | other message    | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "other message"\nbut parserErrorMessage was:  "" (empty)'
      null      | ''              | null             | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  null\nbut parserErrorMessage was:  "" (empty)'
      null      | ''              | ''               |
      null      | ''              | some message     | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "some message"\nbut parserErrorMessage was:  "" (empty)'
      null      | ''              | other message    | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "other message"\nbut parserErrorMessage was:  "" (empty)'
      null      | some message    | null             | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  null\nbut parserErrorMessage was:  "" (empty)'
      null      | some message    | ''               |
      null      | some message    | some message     | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "some message"\nbut parserErrorMessage was:  "" (empty)'
      null      | some message    | other message    | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "other message"\nbut parserErrorMessage was:  "" (empty)'
      null      | other message   | null             | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  null\nbut parserErrorMessage was:  "" (empty)'
      null      | other message   | ''               |
      null      | other message   | some message     | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "some message"\nbut parserErrorMessage was:  "" (empty)'
      null      | other message   | other message    | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "other message"\nbut parserErrorMessage was:  "" (empty)'
      ''        | null            | null             | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  null\nbut parserErrorMessage was:  "" (empty)'
      ''        | null            | ''               |
      ''        | null            | some message     | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "some message"\nbut parserErrorMessage was:  "" (empty)'
      ''        | null            | other message    | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "other message"\nbut parserErrorMessage was:  "" (empty)'
      ''        | ''              | null             | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  null\nbut parserErrorMessage was:  "" (empty)'
      ''        | ''              | ''               |
      ''        | ''              | some message     | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "some message"\nbut parserErrorMessage was:  "" (empty)'
      ''        | ''              | other message    | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "other message"\nbut parserErrorMessage was:  "" (empty)'
      ''        | some message    | null             | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  null\nbut parserErrorMessage was:  "" (empty)'
      ''        | some message    | ''               |
      ''        | some message    | some message     | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "some message"\nbut parserErrorMessage was:  "" (empty)'
      ''        | some message    | other message    | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "other message"\nbut parserErrorMessage was:  "" (empty)'
      ''        | other message   | null             | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  null\nbut parserErrorMessage was:  "" (empty)'
      ''        | other message   | ''               |
      ''        | other message   | some message     | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "some message"\nbut parserErrorMessage was:  "" (empty)'
      ''        | other message   | other message    | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "other message"\nbut parserErrorMessage was:  "" (empty)'
      some.code | null            | null             | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  null\nbut parserErrorMessage was:  "some.code"'
      some.code | null            | ''               | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "" (empty)\nbut parserErrorMessage was:  "some.code"'
      some.code | null            | some message     | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "some message"\nbut parserErrorMessage was:  "some.code"'
      some.code | null            | other message    | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "other message"\nbut parserErrorMessage was:  "some.code"'
      some.code | ''              | null             | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  null\nbut parserErrorMessage was:  "some.code"'
      some.code | ''              | ''               | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "" (empty)\nbut parserErrorMessage was:  "some.code"'
      some.code | ''              | some message     | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "some message"\nbut parserErrorMessage was:  "some.code"'
      some.code | ''              | other message    | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "other message"\nbut parserErrorMessage was:  "some.code"'
      some.code | some message    | null             | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  null\nbut parserErrorMessage was:  "some message"'
      some.code | some message    | ''               | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "" (empty)\nbut parserErrorMessage was:  "some message"'
      some.code | some message    | some message     |
      some.code | some message    | other message    | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "other message"\nbut parserErrorMessage was:  "some message"'
      some.code | other message   | null             | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  null\nbut parserErrorMessage was:  "other message"'
      some.code | other message   | ''               | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "" (empty)\nbut parserErrorMessage was:  "other message"'
      some.code | other message   | some message     | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "some message"\nbut parserErrorMessage was:  "other message"'
      some.code | other message   | other message    |
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void hasParserErrorMessage_behavesAsExpected(
      final String code,
      final String defaultMessage, final String expectedMessage,
      final String expectedExceptionMessage) {

    final var someActual = InvalidValueException.builder()
        .parserErrorCode(new SomeParserMessageCode(code, defaultMessage))
        .build();

    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);

    Assertions.setMaxStackTraceElementsDisplayed(10);

    if (expectedExceptionMessage != null) {

      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(() -> invalidValueExceptionAssert.hasParserErrorMessage(expectedMessage))
          .withMessage(expectedExceptionMessage);

    } else {

      assertThatNoException().isThrownBy(() -> invalidValueExceptionAssert.hasParserErrorMessage(expectedMessage));

      final var assertionResult = invalidValueExceptionAssert.hasParserErrorMessage(expectedMessage);
      assertThat(assertionResult)
          .isInstanceOf(InvalidValueExceptionAssert.class)
          .isSameAs(invalidValueExceptionAssert);
    }
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      CODE      | DEFAULT_MESSAGE | EXPECTED_MESSAGE | LOCALE | EXPECTED_EXCEPTION_MESSAGE
      null      | null            | null             | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  null\nfor locale:  de-DE\nbut parserErrorMessage was:  "" (empty)'
      null      | null            | ''               | de-DE  |
      null      | null            | some message     | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "some message"\nfor locale:  de-DE\nbut parserErrorMessage was:  "" (empty)'
      null      | null            | eine Nachricht   | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "eine Nachricht"\nfor locale:  de-DE\nbut parserErrorMessage was:  "" (empty)'
      null      | ''              | null             | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  null\nfor locale:  de-DE\nbut parserErrorMessage was:  "" (empty)'
      null      | ''              | ''               | de-DE  |
      null      | ''              | some message     | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "some message"\nfor locale:  de-DE\nbut parserErrorMessage was:  "" (empty)'
      null      | ''              | eine Nachricht   | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "eine Nachricht"\nfor locale:  de-DE\nbut parserErrorMessage was:  "" (empty)'
      null      | some message    | null             | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  null\nfor locale:  de-DE\nbut parserErrorMessage was:  "" (empty)'
      null      | some message    | ''               | de-DE  |
      null      | some message    | some message     | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "some message"\nfor locale:  de-DE\nbut parserErrorMessage was:  "" (empty)'
      null      | some message    | eine Nachricht   | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "eine Nachricht"\nfor locale:  de-DE\nbut parserErrorMessage was:  "" (empty)'
      ''        | null            | null             | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  null\nfor locale:  de-DE\nbut parserErrorMessage was:  "" (empty)'
      ''        | null            | ''               | de-DE  |
      ''        | null            | some message     | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "some message"\nfor locale:  de-DE\nbut parserErrorMessage was:  "" (empty)'
      ''        | null            | eine Nachricht   | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "eine Nachricht"\nfor locale:  de-DE\nbut parserErrorMessage was:  "" (empty)'
      ''        | ''              | null             | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  null\nfor locale:  de-DE\nbut parserErrorMessage was:  "" (empty)'
      ''        | ''              | ''               | de-DE  |
      ''        | ''              | some message     | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "some message"\nfor locale:  de-DE\nbut parserErrorMessage was:  "" (empty)'
      ''        | ''              | eine Nachricht   | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "eine Nachricht"\nfor locale:  de-DE\nbut parserErrorMessage was:  "" (empty)'
      ''        | some message    | null             | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  null\nfor locale:  de-DE\nbut parserErrorMessage was:  "" (empty)'
      ''        | some message    | ''               | de-DE  |
      ''        | some message    | some message     | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "some message"\nfor locale:  de-DE\nbut parserErrorMessage was:  "" (empty)'
      ''        | some message    | eine Nachricht   | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "eine Nachricht"\nfor locale:  de-DE\nbut parserErrorMessage was:  "" (empty)'
      some.code | null            | null             | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  null\nfor locale:  de-DE\nbut parserErrorMessage was:  "eine Nachricht"'
      some.code | null            | ''               | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "" (empty)\nfor locale:  de-DE\nbut parserErrorMessage was:  "eine Nachricht"'
      some.code | null            | some message     | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "some message"\nfor locale:  de-DE\nbut parserErrorMessage was:  "eine Nachricht"'
      some.code | null            | eine Nachricht   | de-DE  |
      some.code | null            | other message    | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "other message"\nfor locale:  de-DE\nbut parserErrorMessage was:  "eine Nachricht"'
      some.code | ''              | null             | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  null\nfor locale:  de-DE\nbut parserErrorMessage was:  "eine Nachricht"'
      some.code | ''              | ''               | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "" (empty)\nfor locale:  de-DE\nbut parserErrorMessage was:  "eine Nachricht"'
      some.code | ''              | some message     | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "some message"\nfor locale:  de-DE\nbut parserErrorMessage was:  "eine Nachricht"'
      some.code | ''              | eine Nachricht   | de-DE  |
      some.code | some message    | null             | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  null\nfor locale:  de-DE\nbut parserErrorMessage was:  "eine Nachricht"'
      some.code | some message    | ''               | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "" (empty)\nfor locale:  de-DE\nbut parserErrorMessage was:  "eine Nachricht"'
      some.code | some message    | some message     | de-DE  | '\nExpected actual of type:  InvalidValueException\nto have parserErrorMessage:  "some message"\nfor locale:  de-DE\nbut parserErrorMessage was:  "eine Nachricht"'
      some.code | some message    | eine Nachricht   | de-DE  |
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void hasParserErrorMessage_behavesAsExpectedForLocale(
      final String code,
      final String defaultMessage, final String expectedMessage,
      final String localeString,
      final String expectedExceptionMessage) {

    final Locale locale = Locale.forLanguageTag(localeString);
    final var someActual = InvalidValueException.builder()
        .parserErrorCode(new SomeParserMessageCode(code, defaultMessage))
        .build();

    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);

    Assertions.setMaxStackTraceElementsDisplayed(10);

    if (expectedExceptionMessage != null) {

      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(() -> invalidValueExceptionAssert.hasParserErrorMessage(locale, expectedMessage))
          .withMessage(expectedExceptionMessage);

    } else {

      assertThatNoException().isThrownBy(() -> invalidValueExceptionAssert.hasParserErrorMessage(locale, expectedMessage));

      final var assertionResult = invalidValueExceptionAssert.hasParserErrorMessage(locale, expectedMessage);
      assertThat(assertionResult)
          .isInstanceOf(InvalidValueExceptionAssert.class)
          .isSameAs(invalidValueExceptionAssert);
    }
  }

  @Test
  void hasNoParserErrorProperties_successWithNoProperties() {

    final var someActual = InvalidValueException.builder().build();

    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);

    assertThatNoException().isThrownBy(() -> invalidValueExceptionAssert.hasNoParserErrorProperties());

    final var assertionResult = invalidValueExceptionAssert.hasNoParserErrorProperties();
    assertThat(assertionResult)
        .isInstanceOf(InvalidValueExceptionAssert.class)
        .isSameAs(invalidValueExceptionAssert);
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      KEY      | VALUE      | EXPECTED_EXCEPTION_MESSAGE
      some-key | null       | '\nExpected actual of type:  InvalidValueException\nto have no parserErrorProperties\nbut parserErrorProperties contained:  [\n  "some-key": null]'
      some-key | ''         | '\nExpected actual of type:  InvalidValueException\nto have no parserErrorProperties\nbut parserErrorProperties contained:  [\n  "some-key": ""]'
      some-key | '  '       | '\nExpected actual of type:  InvalidValueException\nto have no parserErrorProperties\nbut parserErrorProperties contained:  [\n  "some-key": "  "]'
      some-key | some value | '\nExpected actual of type:  InvalidValueException\nto have no parserErrorProperties\nbut parserErrorProperties contained:  [\n  "some-key": "some value"]'
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void hasNoParserErrorProperties_throwsAssertionErrorWithProperties(
      final String key, final String value, final String expectedExceptionMessage) {

    final var someActual = InvalidValueException.builder()
        .addParserErrorCodeArg(key, value)
        .build();

    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(invalidValueExceptionAssert::hasNoParserErrorProperties)
        .withMessage(expectedExceptionMessage);
  }

  @Test
  void hasNoParserErrorProperties_throwsAssertionErrorWithMultipleProperties() {

    final var someActual = InvalidValueException.builder()
        .addParserErrorCodeArg("some-key", "some value")
        .addParserErrorCodeArg("another-key", 1122)
        .build();

    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(invalidValueExceptionAssert::hasNoParserErrorProperties)
        .withMessage("""
            
            Expected actual of type:  InvalidValueException
            to have no parserErrorProperties
            but parserErrorProperties contained:  [
              "some-key": "some value",
              "another-key": 1122]""");
  }

  @Test
  void hasParserErrorProperties_success() {

    final var someActual = InvalidValueException.builder()
        .addParserErrorCodeArg("some-key", "some value")
        .addParserErrorCodeArg("another-key", 1122)
        .build();

    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);

    assertThatNoException().isThrownBy(invalidValueExceptionAssert::hasParserErrorProperties);

    final var assertionResult = invalidValueExceptionAssert.hasParserErrorProperties();
    assertThat(assertionResult)
        .isInstanceOf(InvalidValueExceptionAssert.class)
        .isSameAs(invalidValueExceptionAssert);
  }

  @Test
  void hasParserErrorProperties_throwsAssertionErrorWhenNoPropertiesPresent() {

    final var someActual = InvalidValueException.builder().build();

    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(invalidValueExceptionAssert::hasParserErrorProperties)
        .withMessage("""
            
            Expected actual of type:  InvalidValueException
            to have non-empty parserErrorProperties
            but parserErrorProperties was:  [] (empty)""");
  }

  @Test
  void hasParserErrorPropertiesContainingAllEntriesOf_success() {

    final var someActual = InvalidValueException.builder()
        .addParserErrorCodeArg("some-key", "some value")
        .addParserErrorCodeArg("another-key", 1122)
        .build();

    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);
    final var expected = Map.<String, Serializable>of(
    "some-key", "some value",
    "another-key", 1122);

    assertThatNoException().isThrownBy(() ->
        invalidValueExceptionAssert.hasParserErrorPropertiesContainingAllEntriesOf(expected));

    final var assertionResult = invalidValueExceptionAssert.hasParserErrorPropertiesContainingAllEntriesOf(expected);
    assertThat(assertionResult)
        .isInstanceOf(InvalidValueExceptionAssert.class)
        .isSameAs(invalidValueExceptionAssert);
  }

  @Test
  void hasParserErrorPropertiesContainingAllEntriesOf_throwsAssertionErrorWithMissingProperties() {

    final var someActual = InvalidValueException.builder()
        .addParserErrorCodeArg("some-key", "some value")
        .addParserErrorCodeArg("another-key", 1122)
        .build();

    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);
    final var expected = new LinkedHashMap<String, Serializable>();
    expected.put("some-key", "some value");
    expected.put("another-key", 1122);
    expected.put("missing-key", "missing value");

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> invalidValueExceptionAssert.hasParserErrorPropertiesContainingAllEntriesOf(expected))
        .withMessage("""
            
            Expected actual of type:  InvalidValueException
            with parserErrorProperties:  [
              "some-key": "some value",
              "another-key": 1122]
            to contain all entries:  [
              "some-key": "some value",
              "another-key": 1122,
              "missing-key": "missing value"]
            but could not find:  [
              "missing-key": "missing value"]""");
  }

  @Test
  void hasParserErrorPropertiesContainingExactlyEntriesOf_success() {

    final var someActual = InvalidValueException.builder()
        .addParserErrorCodeArg("some-key", "some value")
        .addParserErrorCodeArg("another-key", 1122)
        .build();

    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);
    final var expected = Map.<String, Serializable>of(
        "some-key", "some value",
        "another-key", 1122);

    assertThatNoException().isThrownBy(() ->
        invalidValueExceptionAssert.hasParserErrorPropertiesContainingExactlyEntriesOf(expected));

    final var assertionResult = invalidValueExceptionAssert.hasParserErrorPropertiesContainingExactlyEntriesOf(expected);
    assertThat(assertionResult)
        .isInstanceOf(InvalidValueExceptionAssert.class)
        .isSameAs(invalidValueExceptionAssert);
  }

  @Test
  void hasParserErrorPropertiesContainingExactlyEntriesOf_throwsAssertionErrorWithMissingExpectedErrorProperties() {

    final var someActual = InvalidValueException.builder()
        .addParserErrorCodeArg("some-key", "some value")
        .addParserErrorCodeArg("another-key", 1122)
        .build();

    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);
    final var expected = new LinkedHashMap<String, Serializable>();
    expected.put("some-key", "some value");
    expected.put("another-key", 1122);
    expected.put("missing-key", "missing value");

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> invalidValueExceptionAssert.hasParserErrorPropertiesContainingExactlyEntriesOf(expected))
        .withMessage("""
            
            Expected actual of type:  InvalidValueException
            with parserErrorProperties:  [
              "some-key": "some value",
              "another-key": 1122]
            to contain exactly entries:  [
              "some-key": "some value",
              "another-key": 1122,
              "missing-key": "missing value"]
            but could not find:  [
              "missing-key": "missing value"]""");
  }

  @Test
  void hasParserErrorPropertiesContainingExactlyEntriesOf_throwsAssertionErrorWithMissingParserErrorProperties() {

    final var someActual = InvalidValueException.builder()
        .addParserErrorCodeArg("some-key", "some value")
        .addParserErrorCodeArg("another-key", 1122)
        .addParserErrorCodeArg("third-key", "third value")
        .build();

    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);
    final var expected = new LinkedHashMap<String, Serializable>();
    expected.put("some-key", "some value");
    expected.put("another-key", 1122);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> invalidValueExceptionAssert.hasParserErrorPropertiesContainingExactlyEntriesOf(expected))
        .withMessage("""
            
            Expected actual of type:  InvalidValueException
            with parserErrorProperties:  [
              "some-key": "some value",
              "another-key": 1122,
              "third-key": "third value"]
            to contain exactly entries:  [
              "some-key": "some value",
              "another-key": 1122]
            but found extra entries:  [
              "third-key": "third value"]""");
  }

  @Test
  void hasParserErrorPropertiesContainingExactlyEntriesOf_throwsAssertionErrorWithMissingParserAndExpectedErrorProperties() {

    final var someActual = InvalidValueException.builder()
        .addParserErrorCodeArg("some-key", "some value")
        .addParserErrorCodeArg("another-key", 1122)
        .addParserErrorCodeArg("third-key", "third value")
        .build();

    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);
    final var expected = new LinkedHashMap<String, Serializable>();
    expected.put("some-key", "some value");
    expected.put("another-key", 1122);
    expected.put("missing-key", "missing value");

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> invalidValueExceptionAssert.hasParserErrorPropertiesContainingExactlyEntriesOf(expected))
        .withMessage("""
            
            Expected actual of type:  InvalidValueException
            with parserErrorProperties:  [
              "some-key": "some value",
              "another-key": 1122,
              "third-key": "third value"]
            to contain exactly entries:  [
              "some-key": "some value",
              "another-key": 1122,
              "missing-key": "missing value"]
            but could not find:  [
              "missing-key": "missing value"]
            and found extra entries:  [
              "third-key": "third value"]""");
  }


  @Test
  void hasParserErrorArgs_success() {

    final var someActual = InvalidValueException.builder()
        .addParserErrorCodeArg("some-key", "some value")
        .addParserErrorCodeArg("another-key", "another value")
        .build();

    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);

    assertThatNoException().isThrownBy(invalidValueExceptionAssert::hasParserErrorArgs);

    final var assertionResult = invalidValueExceptionAssert.hasParserErrorArgs();
    assertThat(assertionResult)
        .isInstanceOf(InvalidValueExceptionAssert.class)
        .isSameAs(invalidValueExceptionAssert);
  }

  @Test
  void hasParserErrorArgs_throwsAssertionErrorWhenNoPropertiesPresent() {

    final var someActual = InvalidValueException.builder().build();

    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(invalidValueExceptionAssert::hasParserErrorArgs)
        .withMessage("""
            
            Expected actual of type:  InvalidValueException
            to have non-empty parserErrorArgs
            but parserErrorArgs was:  [] (empty)""");
  }

  @Test
  void hasParserErrorArgsContainingAllOf_success() {

    final var someActual = InvalidValueException.builder()
        .addParserErrorCodeArg("some-key", "some value")
        .addParserErrorCodeArg("another-key", 1122)
        .build();

    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);
    final var expected = new Serializable[] {"some value", 1122};

    assertThatNoException().isThrownBy(() ->
        invalidValueExceptionAssert.hasParserErrorArgsContainingAllOf(expected));

    final var assertionResult = invalidValueExceptionAssert.hasParserErrorArgsContainingAllOf(expected);
    assertThat(assertionResult)
        .isInstanceOf(InvalidValueExceptionAssert.class)
        .isSameAs(invalidValueExceptionAssert);
  }

  @Test
  void hasParserErrorArgsContainingAllOf_throwsAssertionErrorWithMissingArgValues() {

    final var someActual = InvalidValueException.builder()
        .addParserErrorCodeArg("some-key", "some value")
        .addParserErrorCodeArg("another-key", 1122)
        .build();

    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);
    final var expected = new Serializable[] {"some value", 1122, "missing value" };

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> invalidValueExceptionAssert.hasParserErrorArgsContainingAllOf(expected))
        .withMessage("""
            
            Expected actual of type:  InvalidValueException
            with parserErrorArgs:  [
              "some value",
              1122]
            to contain all entries:  [
              "some value",
              1122,
              "missing value"]
            but could not find:  [
              "missing value"]""");
  }







  @Test
  void hasParserErrorArgsContainingExactly_success() {

    final var someActual = InvalidValueException.builder()
        .addParserErrorCodeArg("some-key", "some value")
        .addParserErrorCodeArg("another-key", 1122)
        .build();

    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);
    final var expected = new Serializable[] {"some value", 1122};

    assertThatNoException().isThrownBy(() ->
        invalidValueExceptionAssert.hasParserErrorArgsContainingExactly(expected));

    final var assertionResult = invalidValueExceptionAssert.hasParserErrorArgsContainingExactly(expected);
    assertThat(assertionResult)
        .isInstanceOf(InvalidValueExceptionAssert.class)
        .isSameAs(invalidValueExceptionAssert);
  }

  @Test
  void hasParserErrorArgsContainingExactly_throwsAssertionErrorWithMissingExpectedErrorProperties() {

    final var someActual = InvalidValueException.builder()
        .addParserErrorCodeArg("some-key", "some value")
        .addParserErrorCodeArg("another-key", 1122)
        .build();

    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);
    final var expected = new Serializable[] {"some value", 1122, "missing value" };

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> invalidValueExceptionAssert.hasParserErrorArgsContainingExactly(expected))
        .withMessage("""
            
            Expected actual of type:  InvalidValueException
            with parserErrorArgs:  [
              "some value",
              1122]
            to contain exactly:  [
              "some value",
              1122,
              "missing value"]
            but could not find:  [
              "missing value"]""");
  }

  @Test
  void hasParserErrorArgsContainingExactly_throwsAssertionErrorWithMissingParserErrorProperties() {

    final var someActual = InvalidValueException.builder()
        .addParserErrorCodeArg("some-key", "some value")
        .addParserErrorCodeArg("another-key", 1122)
        .addParserErrorCodeArg("third-key", "third value")
        .build();

    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);
    final var expected = new Serializable[] {"some value", 1122};

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> invalidValueExceptionAssert.hasParserErrorArgsContainingExactly(expected))
        .withMessage("""
            
            Expected actual of type:  InvalidValueException
            with parserErrorArgs:  [
              "some value",
              1122,
              "third value"]
            to contain exactly:  [
              "some value",
              1122]
            but found extra values:  [
              "third value"]""");
  }

  @Test
  void hasParserErrorArgsContainingExactly_throwsAssertionErrorWithMissingParserAndExpectedErrorProperties() {

    final var someActual = InvalidValueException.builder()
        .addParserErrorCodeArg("some-key", "some value")
        .addParserErrorCodeArg("another-key", 1122)
        .addParserErrorCodeArg("third-key", "third value")
        .build();

    final var invalidValueExceptionAssert = InvalidValueExceptionAssert.assertThat(someActual);
    final var expected = new Serializable[] {"some value", 1122, "missing value" };

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> invalidValueExceptionAssert.hasParserErrorArgsContainingExactly(expected))
        .withMessage("""
            
            Expected actual of type:  InvalidValueException
            with parserErrorArgs:  [
              "some value",
              1122,
              "third value"]
            to contain exactly:  [
              "some value",
              1122,
              "missing value"]
            but could not find:  [
              "missing value"]
            and found extra values:  [
              "third value"]""");
  }



  private record SomeParserMessageCode(String code, String defaultMessage) implements ParserMessageCode {

    SomeParserMessageCode(final String code, String defaultMessage) {
      this.code = code == null || code.isBlank() ? "" : code;
      this.defaultMessage = defaultMessage == null || defaultMessage.isBlank() ? "" : defaultMessage;
    }

    @Override
    public String toString() {
      return code;
    }
  }
}
