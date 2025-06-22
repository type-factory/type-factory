package org.typefactory.assertions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.assertj.core.api.AbstractCharSequenceAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.CharSequenceType;

class CharSequenceTypeAssertTest {

  @Test
  void charSequenceTypeAssert_hasCustomAssertionMethods() {
    final var someActual = new SomeCharSequenceType("some-value");
    final var charSequenceTypeAssert = CharSequenceTypeAssert.assertThat(someActual);
    assertThat(charSequenceTypeAssert.getClass())
        .hasPublicMethods("hasValue", "hasNullValue",
            "isNull", "isNullOrHasNullValue",
            "isNotNull", "isNotNullAndHasNonNullValue");
  }

  @Test
  void assertThat_returnsCharSequenceTypeAssertInstance() {
    final var someActual = new SomeCharSequenceType("some-value");
    final var charSequenceTypeAssert = CharSequenceTypeAssert.assertThat(someActual);
    assertThat(charSequenceTypeAssert)
        .isInstanceOf(CharSequenceTypeAssert.class)
        .isInstanceOf(AbstractCharSequenceTypeAssert.class)
        .isInstanceOf(AbstractCharSequenceAssert.class);
  }

  @Test
  void valueOfActual_returnsNullForNullActual() {
    final var someActual = (SomeCharSequenceType) null;
    final var charSequenceTypeAssert = CharSequenceTypeAssert.assertThat(someActual);
    assertThat(charSequenceTypeAssert.valueOfActual()).isEqualTo("null");
  }

  @Test
  void valueOfActual_returnsNullForStringTypeWithNullValue() {
    final var someActual = new SomeCharSequenceType(null);
    final var charSequenceTypeAssert = CharSequenceTypeAssert.assertThat(someActual);
    assertThat(charSequenceTypeAssert.valueOfActual()).isEqualTo("null");
  }

  @Test
  void classNameOfActual_returnsExtendsStringTypeForNullActual() {
    final var someActual = (SomeCharSequenceType) null;
    final var charSequenceTypeAssert = CharSequenceTypeAssert.assertThat(someActual);
    assertThat(charSequenceTypeAssert.classNameOfActual()).isEqualTo("<? extends CharSequenceType>");
  }

  @Test
  void valueOfActual_returnsClassNameForActualInstanceWithNullValue() {
    final var someActual = new SomeCharSequenceType(null);
    final var charSequenceTypeAssert = CharSequenceTypeAssert.assertThat(someActual);
    assertThat(charSequenceTypeAssert.classNameOfActual()).isEqualTo(SomeCharSequenceType.class.getSimpleName());
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      EXPECTED_VALUE | EXPECTED_EXCEPTION_MESSAGE
      null           | `\nExpected actual of type:  <? extends CharSequenceType>\nto be an instance with value:  null\nbut actual was:  null\nConsider using '.isNullOrHasNullValue()' if that is what you want to test.`
      ``             | `\nExpected actual of type:  <? extends CharSequenceType>\nto be an instance with value:  "" (empty)\nbut actual was:  null`
      `  `           | `\nExpected actual of type:  <? extends CharSequenceType>\nto be an instance with value:  "  " (blank)\nbut actual was:  null`
      some-value     | `\nExpected actual of type:  <? extends CharSequenceType>\nto be an instance with value:  "some-value"\nbut actual was:  null`
      """, delimiter = '|', quoteCharacter = '`', nullValues = "null", useHeadersInDisplayName = true)
  void hasValue_throwsAssertionErrorForNullActual(final String expectedValue, final String expectedExceptionMessage) {
    final var actual = (SomeCharSequenceType) null;
    final var charSequenceTypeAssert = CharSequenceTypeAssert.assertThat(actual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> charSequenceTypeAssert.hasValue(expectedValue))
        .withMessage(expectedExceptionMessage);
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      ACTUAL_VALUE | EXPECTED_VALUE | EXPECTED_EXCEPTION_MESSAGE
      null         | null           |
      some-value   | some-value     |
      null         | some-value     | '\nExpected actual of type:  SomeCharSequenceType\nto have value:  "some-value"\nbut value was:  null'
      some-value   | null           | '\nExpected actual of type:  SomeCharSequenceType\nto have value:  null\nbut value was:  "some-value"'
      some-value   | other-value    | '\nExpected actual of type:  SomeCharSequenceType\nto have value:  "other-value"\nbut value was:  "some-value"'
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void hasValue_behavesAsExpected(
      final String actualValue, final String expectedValue,
      final String expectedExceptionMessage) {

    final var actual = new SomeCharSequenceType(actualValue);
    final var charSequenceTypeAssert = CharSequenceTypeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {

      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(() -> charSequenceTypeAssert.hasValue(expectedValue))
          .withMessage(expectedExceptionMessage);

    } else {

      final var hasValueResult = charSequenceTypeAssert.hasValue(expectedValue);
      assertThat(hasValueResult)
          .isInstanceOf(CharSequenceTypeAssert.class)
          .isSameAs(charSequenceTypeAssert);
    }
  }

  @Test
  void isNull_doesNotThrowAssertionErrorForNullActual() {
    final var actual = (SomeCharSequenceType) null;
    final var charSequenceTypeAssert = CharSequenceTypeAssert.assertThat(actual);

    assertThatNoException()
        .isThrownBy(charSequenceTypeAssert::isNull);
  }

  @Test
  void isNull_throwsAssertionErrorForNonNullActual() {
    final var actual = new SomeCharSequenceType(null);
    final var charSequenceTypeAssert = CharSequenceTypeAssert.assertThat(actual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(charSequenceTypeAssert::isNull)
        .withMessage(
            "\nExpected actual of type:  SomeCharSequenceType\nto be:  null\nbut was non-null with an internal value:  null\nConsider using '.isNullOrHasNullValue()' if that is what you want to test.");
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      ACTUAL_VALUE | EXPECTED_EXCEPTION_MESSAGE
      null         | `\nExpected actual of type:  SomeCharSequenceType\nto be:  null\nbut was non-null with an internal value:  null\nConsider using '.isNullOrHasNullValue()' if that is what you want to test.`
      some-value   | `\nExpected actual of type:  SomeCharSequenceType\nto be:  null\nbut was non-null with an internal value:  "some-value"`
      """, delimiter = '|', quoteCharacter = '`', nullValues = "null", useHeadersInDisplayName = true)
  void isNull_throwAssertionErrorForNonNullActual(final String value, final String expectedExceptionMessage) {

    final var actual = new SomeCharSequenceType(value);
    final var charSequenceTypeAssert = CharSequenceTypeAssert.assertThat(actual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(charSequenceTypeAssert::isNull)
        .withMessage(expectedExceptionMessage);
  }

  @Test
  void isNotNull_throwsAssertionErrorForNullActual() {
    final var actual = (SomeCharSequenceType) null;
    final var charSequenceTypeAssert = CharSequenceTypeAssert.assertThat(actual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(charSequenceTypeAssert::isNotNull)
        .withMessage("\nExpected actual of type:  <? extends CharSequenceType>\nto not be:  null\nbut was: null");
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      ACTUAL_VALUE
      null
      some-value
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void isNotNull_doesNotThrowAssertionErrorForNonNullActual(final String value) {

    final var actual = new SomeCharSequenceType(value);
    final var charSequenceTypeAssert = CharSequenceTypeAssert.assertThat(actual);

    assertThatNoException()
        .isThrownBy(charSequenceTypeAssert::isNotNull);
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      ACTUAL_VALUE | EXPECTED_EXCEPTION_MESSAGE
      null         |
      ''           | '\nExpected actual of type:  SomeCharSequenceType\nto have value:  null\nbut value was:  "" (empty)'
      '  '         | '\nExpected actual of type:  SomeCharSequenceType\nto have value:  null\nbut value was:  "  " (blank)'
      some-value   | '\nExpected actual of type:  SomeCharSequenceType\nto have value:  null\nbut value was:  "some-value"'
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void hasNullValue_behavesAsExpected(
      final String actualValue, final String expectedExceptionMessage) {

    final var actual = new SomeCharSequenceType(actualValue);
    final var charSequenceTypeAssert = CharSequenceTypeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {
      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(charSequenceTypeAssert::hasNullValue)
          .withMessage(expectedExceptionMessage);
    } else {
      assertThatNoException()
          .isThrownBy(charSequenceTypeAssert::hasNullValue);
    }
  }

  @Test
  void isNullOrHasNullValue_throwsAssertionErrorForNonNullActual() {
    final var actual = (SomeCharSequenceType) null;
    final var charSequenceTypeAssert = CharSequenceTypeAssert.assertThat(actual);

    assertThatNoException()
        .isThrownBy(charSequenceTypeAssert::isNullOrHasNullValue);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      ACTUAL_VALUE | EXPECTED_EXCEPTION_MESSAGE
      null         |
      ''           | '\nExpected actual of type: SomeCharSequenceType\nto be null or have an internal value of null\nbut was:  "" (empty)'
      '  '         | '\nExpected actual of type: SomeCharSequenceType\nto be null or have an internal value of null\nbut was:  "  " (blank)'
      some-value   | '\nExpected actual of type: SomeCharSequenceType\nto be null or have an internal value of null\nbut was:  "some-value"'
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void isNullOrHasNullValue_behavesAsExpected(
      final String actualValue, final String expectedExceptionMessage) {

    final var actual = new SomeCharSequenceType(actualValue);
    final var charSequenceTypeAssert = CharSequenceTypeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {
      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(charSequenceTypeAssert::isNullOrHasNullValue)
          .withMessage(expectedExceptionMessage);
    } else {
      assertThatNoException()
          .isThrownBy(charSequenceTypeAssert::isNullOrHasNullValue);
    }
  }

  @Test
  void isNotNullAndHasNonNullValue_throwsAssertionErrorForNullActual() {
    final var actual = (SomeCharSequenceType) null;
    final var charSequenceTypeAssert = CharSequenceTypeAssert.assertThat(actual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(charSequenceTypeAssert::isNotNullAndHasNonNullValue)
        .withMessage("\nExpected actual of type:  <? extends CharSequenceType>\nto not be:  null\nbut was:  null");
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      ACTUAL_VALUE | EXPECTED_EXCEPTION_MESSAGE
      null         | '\nExpected actual of type:  SomeCharSequenceType\nto not have value: null\nbut value was:  null'
      ''           |
      '  '         |
      some-value   |
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void isNotNullAndHasNonNullValue_behavesAsExpected(
      final String actualValue, final String expectedExceptionMessage) {

    final var actual = new SomeCharSequenceType(actualValue);
    final var charSequenceTypeAssert = CharSequenceTypeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {
      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(charSequenceTypeAssert::isNotNullAndHasNonNullValue)
          .withMessage(expectedExceptionMessage);
    } else {
      assertThatNoException()
          .isThrownBy(charSequenceTypeAssert::isNotNullAndHasNonNullValue);
    }
  }

  private record SomeCharSequenceType(String value) implements CharSequenceType<SomeCharSequenceType> {
  }
}
