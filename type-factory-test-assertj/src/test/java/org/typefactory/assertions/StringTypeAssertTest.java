package org.typefactory.assertions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.io.Serial;
import org.assertj.core.api.AbstractCharSequenceAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.StringType;

class StringTypeAssertTest {

  @Test
  void stringTypeAssert_hasCustomAssertionMethods() {
    final var someActual = new SomeStringType("some-value");
    final var stringTypeAssert = StringTypeAssert.assertThat(someActual);
    assertThat(stringTypeAssert.getClass())
        .hasPublicMethods("hasValue", "hasNullValue",
            "isNull", "isNullOrHasNullValue",
            "isNotNull", "isNotNullAndHasNonNullValue");
  }

  @Test
  void assertThat_returnsStringTypeAssertInstance() {
    final var someActual = new SomeStringType("some-value");
    final var stringTypeAssert = StringTypeAssert.assertThat(someActual);
    assertThat(stringTypeAssert)
        .isInstanceOf(StringTypeAssert.class)
        .isInstanceOf(AbstractCharSequenceTypeAssert.class)
        .isInstanceOf(AbstractCharSequenceAssert.class);
  }

  @Test
  void valueOfActual_returnsNullForNullActual() {
    final var someActual = (SomeStringType) null;
    final var stringTypeAssert = StringTypeAssert.assertThat(someActual);
    assertThat(stringTypeAssert.valueOfActual()).isEqualTo("null");
  }

  @Test
  void valueOfActual_returnsNullForStringTypeWithNullValue() {
    final var someActual = new SomeStringType(null);
    final var stringTypeAssert = StringTypeAssert.assertThat(someActual);
    assertThat(stringTypeAssert.valueOfActual()).isEqualTo("null");
  }

  @Test
  void actualHasNullValue_returnsFalseForNullActual() {
    final var someActual = (SomeStringType) null;
    final var stringTypeAssert = StringTypeAssert.assertThat(someActual);
    assertThat(stringTypeAssert.actualHasNullValue()).isFalse();
  }

  @Test
  void actualHasNullValue_returnsTrueForForShortTypeWithNullValue() {
    final var someActual = new SomeStringType(null);
    final var stringTypeAssert = StringTypeAssert.assertThat(someActual);
    assertThat(stringTypeAssert.actualHasNullValue()).isTrue();
  }

  @Test
  void classNameOfActual_returnsExtendsStringTypeForNullActual() {
    final var someActual = (SomeStringType) null;
    final var stringTypeAssert = StringTypeAssert.assertThat(someActual);
    assertThat(stringTypeAssert.classNameOfActual()).isEqualTo("<? extends StringType>");
  }

  @Test
  void valueOfActual_returnsClassNameForActualInstanceWithNullValue() {
    final var someActual = new SomeStringType(null);
    final var stringTypeAssert = StringTypeAssert.assertThat(someActual);
    assertThat(stringTypeAssert.classNameOfActual()).isEqualTo(SomeStringType.class.getSimpleName());
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      EXPECTED_VALUE | EXPECTED_EXCEPTION_MESSAGE
      null           | `\nExpected actual of type:  <? extends StringType>\nto be an instance with value:  null\nbut actual was:  null\nConsider using '.isNullOrHasNullValue()' if that is what you want to test.`
      ``             | `\nExpected actual of type:  <? extends StringType>\nto be an instance with value:  "" (empty)\nbut actual was:  null`
      `  `           | `\nExpected actual of type:  <? extends StringType>\nto be an instance with value:  "  " (blank)\nbut actual was:  null`
      some-value     | `\nExpected actual of type:  <? extends StringType>\nto be an instance with value:  "some-value"\nbut actual was:  null`
      """, delimiter = '|', quoteCharacter = '`', nullValues = "null", useHeadersInDisplayName = true)
  void hasValue_throwsAssertionErrorForNullActual(final String expectedValue, final String expectedExceptionMessage) {
    final var actual = (SomeStringType) null;
    final var stringTypeAssert = StringTypeAssert.assertThat(actual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> stringTypeAssert.hasValue(expectedValue))
        .withMessage(expectedExceptionMessage);
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      ACTUAL_VALUE | EXPECTED_VALUE | EXPECTED_EXCEPTION_MESSAGE
      null         | null           |
      some-value   | some-value     |
      null         | some-value     | '\nExpected actual of type:  SomeStringType\nto have value:  "some-value"\nbut value was:  null'
      some-value   | null           | '\nExpected actual of type:  SomeStringType\nto have value:  null\nbut value was:  "some-value"'
      some-value   | other-value    | '\nExpected actual of type:  SomeStringType\nto have value:  "other-value"\nbut value was:  "some-value"'
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void hasValue_behavesAsExpected(
      final String actualValue, final String expectedValue,
      final String expectedExceptionMessage) {

    final var actual = new SomeStringType(actualValue);
    final var stringTypeAssert = StringTypeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {

      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(() -> stringTypeAssert.hasValue(expectedValue))
          .withMessage(expectedExceptionMessage);

    } else {

      final var hasValueResult = stringTypeAssert.hasValue(expectedValue);
      assertThat(hasValueResult)
          .isInstanceOf(StringTypeAssert.class)
          .isSameAs(stringTypeAssert);
    }
  }

  @Test
  void isNull_doesNotThrowAssertionErrorForNullActual() {
    final var actual = (SomeStringType) null;
    final var stringTypeAssert = StringTypeAssert.assertThat(actual);

    assertThatNoException()
        .isThrownBy(stringTypeAssert::isNull);
  }

  @Test
  void isNull_throwsAssertionErrorForNonNullActual() {
    final var actual = new SomeStringType(null);
    final var stringTypeAssert = StringTypeAssert.assertThat(actual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(stringTypeAssert::isNull)
        .withMessage(
            "\nExpected actual of type:  SomeStringType\nto be:  null\nbut was non-null with an internal value:  null\nConsider using '.isNullOrHasNullValue()' if that is what you want to test.");
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      ACTUAL_VALUE | EXPECTED_EXCEPTION_MESSAGE
      null         | `\nExpected actual of type:  SomeStringType\nto be:  null\nbut was non-null with an internal value:  null\nConsider using '.isNullOrHasNullValue()' if that is what you want to test.`
      some-value   | `\nExpected actual of type:  SomeStringType\nto be:  null\nbut was non-null with an internal value:  "some-value"`
      """, delimiter = '|', quoteCharacter = '`', nullValues = "null", useHeadersInDisplayName = true)
  void isNull_throwAssertionErrorForNonNullActual(final String value, final String expectedExceptionMessage) {

    final var actual = new SomeStringType(value);
    final var stringTypeAssert = StringTypeAssert.assertThat(actual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(stringTypeAssert::isNull)
        .withMessage(expectedExceptionMessage);
  }

  @Test
  void isNotNull_throwsAssertionErrorForNullActual() {
    final var actual = (SomeStringType) null;
    final var stringTypeAssert = StringTypeAssert.assertThat(actual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(stringTypeAssert::isNotNull)
        .withMessage("\nExpected actual of type:  <? extends StringType>\nto not be:  null\nbut was: null");
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      ACTUAL_VALUE
      null
      some-value
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void isNotNull_doesNotThrowAssertionErrorForNonNullActual(final String value) {

    final var actual = new SomeStringType(value);
    final var stringTypeAssert = StringTypeAssert.assertThat(actual);

    assertThatNoException()
        .isThrownBy(stringTypeAssert::isNotNull);
  }

  @Test
  void hasNullValue_throwsExceptionForNullActual() {
    final var actual = (SomeStringType) null;
    final var stringTypeAssert = StringTypeAssert.assertThat(actual);
    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(stringTypeAssert::hasNullValue)
        .withMessage("\nExpected actual of type:  <? extends StringType>\nto not be null and have value:   null\nbut actual was:  null.\nConsider using '.isNullOrHasNullValue()' if that is what you want to test.");
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      ACTUAL_VALUE | EXPECTED_EXCEPTION_MESSAGE
      null         |
      ''           | '\nExpected actual of type:  SomeStringType\nto have value:  null\nbut value was:  "" (empty)'
      '  '         | '\nExpected actual of type:  SomeStringType\nto have value:  null\nbut value was:  "  " (blank)'
      some-value   | '\nExpected actual of type:  SomeStringType\nto have value:  null\nbut value was:  "some-value"'
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void hasNullValue_behavesAsExpected(
      final String actualValue, final String expectedExceptionMessage) {

    final var actual = new SomeStringType(actualValue);
    final var stringTypeAssert = StringTypeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {
      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(stringTypeAssert::hasNullValue)
          .withMessage(expectedExceptionMessage);
    } else {
      assertThatNoException()
          .isThrownBy(stringTypeAssert::hasNullValue);
    }
  }

  @Test
  void isNullOrHasNullValue_throwsAssertionErrorForNonNullActual() {
    final var actual = (SomeStringType) null;
    final var stringTypeAssert = StringTypeAssert.assertThat(actual);

    assertThatNoException()
        .isThrownBy(stringTypeAssert::isNullOrHasNullValue);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      ACTUAL_VALUE | EXPECTED_EXCEPTION_MESSAGE
      null         |
      ''           | '\nExpected actual of type: SomeStringType\nto be null or have an internal value of null\nbut was:  "" (empty)'
      '  '         | '\nExpected actual of type: SomeStringType\nto be null or have an internal value of null\nbut was:  "  " (blank)'
      some-value   | '\nExpected actual of type: SomeStringType\nto be null or have an internal value of null\nbut was:  "some-value"'
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void isNullOrHasNullValue_behavesAsExpected(
      final String actualValue, final String expectedExceptionMessage) {

    final var actual = new SomeStringType(actualValue);
    final var stringTypeAssert = StringTypeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {
      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(stringTypeAssert::isNullOrHasNullValue)
          .withMessage(expectedExceptionMessage);
    } else {
      assertThatNoException()
          .isThrownBy(stringTypeAssert::isNullOrHasNullValue);
    }
  }

  @Test
  void isNotNullAndHasNonNullValue_throwsAssertionErrorForNullActual() {
    final var actual = (SomeStringType) null;
    final var stringTypeAssert = StringTypeAssert.assertThat(actual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(stringTypeAssert::isNotNullAndHasNonNullValue)
        .withMessage("\nExpected actual of type:  <? extends StringType>\nto not be:  null\nbut was:  null");
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      ACTUAL_VALUE | EXPECTED_EXCEPTION_MESSAGE
      null         | '\nExpected actual of type:  SomeStringType\nto not have value: null\nbut value was:  null'
      ''           |
      '  '         |
      some-value   |
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void isNotNullAndHasNonNullValue_behavesAsExpected(
      final String actualValue, final String expectedExceptionMessage) {

    final var actual = new SomeStringType(actualValue);
    final var stringTypeAssert = StringTypeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {
      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(stringTypeAssert::isNotNullAndHasNonNullValue)
          .withMessage(expectedExceptionMessage);
    } else {
      assertThatNoException()
          .isThrownBy(stringTypeAssert::isNotNullAndHasNonNullValue);
    }
  }

  private static final class SomeStringType extends StringType {

    @Serial
    private static final long serialVersionUID = 6673260854256023521L;

    SomeStringType(final String value) {
      super(value);
    }
  }
}
