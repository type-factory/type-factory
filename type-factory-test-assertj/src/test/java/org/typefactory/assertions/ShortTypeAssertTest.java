package org.typefactory.assertions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.io.Serial;
import org.assertj.core.api.AbstractComparableAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.ShortType;

class ShortTypeAssertTest {

  @Test
  void shortTypeAssert_hasCustomAssertionMethods() {
    final var someActual = new SomeShortType((short)2222);
    final var shortTypeAssert = ShortTypeAssert.assertThat(someActual);
    assertThat(shortTypeAssert.getClass())
        .hasPublicMethods("hasValue", "hasNullValue",
            "isNull", "isNullOrHasNullValue",
            "isNotNull", "isNotNullAndHasNonNullValue");
  }

  @Test
  void assertThat_returnsShortTypeAssertInstance() {
    final var someActual = new SomeShortType((short)3333);
    final var shortTypeAssert = ShortTypeAssert.assertThat(someActual);
    assertThat(shortTypeAssert)
        .isInstanceOf(ShortTypeAssert.class)
        .isInstanceOf(AbstractShortTypeAssert.class)
        .isInstanceOf(AbstractComparableAssert.class);
  }

  @Test
  void valueOfActual_returnsNullForNullActual() {
    final var someActual = (SomeShortType) null;
    final var shortTypeAssert = ShortTypeAssert.assertThat(someActual);
    assertThat(shortTypeAssert.valueOfActual()).isNull();
  }

  @Test
  void valueOfActual_returnsNullForShortTypeWithNullValue() {
    final var someActual = new SomeShortType(null);
    final var shortTypeAssert = ShortTypeAssert.assertThat(someActual);
    assertThat(shortTypeAssert.valueOfActual()).isNull();
  }

  @Test
  void actualHasNullValue_returnsFalseForNullActual() {
    final var someActual = (SomeShortType) null;
    final var shortTypeAssert = ShortTypeAssert.assertThat(someActual);
    assertThat(shortTypeAssert.actualHasNullValue()).isFalse();
  }

  @Test
  void actualHasNullValue_returnsTrueForForShortTypeWithNullValue() {
    final var someActual = new SomeShortType(null);
    final var shortTypeAssert = ShortTypeAssert.assertThat(someActual);
    assertThat(shortTypeAssert.actualHasNullValue()).isTrue();
  }

  @Test
  void classNameOfActual_returnsExtendsShortTypeForNullActual() {
    final var someActual = (SomeShortType) null;
    final var shortTypeAssert = ShortTypeAssert.assertThat(someActual);
    assertThat(shortTypeAssert.classNameOfActual()).isEqualTo("<? extends ShortType>");
  }

  @Test
  void valueOfActual_returnsClassNameForActualInstanceWithNullValue() {
    final var someActual = new SomeShortType(null);
    final var shortTypeAssert = ShortTypeAssert.assertThat(someActual);
    assertThat(shortTypeAssert.classNameOfActual()).isEqualTo(SomeShortType.class.getSimpleName());
  }

  @Test
  void hasValue_AcceptsPrimitiveShort() {
    final var actual = new SomeShortType((short) 1234);
    final var shortTypeAssert = ShortTypeAssert.assertThat(actual);

    final var hasValueResult = shortTypeAssert.hasValue((short) 1234);
    assertThat(hasValueResult)
        .isInstanceOf(ShortTypeAssert.class)
        .isSameAs(shortTypeAssert);
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      EXPECTED_VALUE | EXPECTED_EXCEPTION_MESSAGE
      null           | `\nExpected actual of type:  <? extends ShortType>\nto be an instance with value:  null\nbut actual was:  null\nConsider using '.isNullOrHasNullValue()' if that is what you want to test.`
      -1             | `\nExpected actual of type:  <? extends ShortType>\nto be an instance with value:  -1\nbut actual was:  null`
      0              | `\nExpected actual of type:  <? extends ShortType>\nto be an instance with value:  0\nbut actual was:  null`
      2222           | `\nExpected actual of type:  <? extends ShortType>\nto be an instance with value:  2222\nbut actual was:  null`
      """, delimiter = '|', quoteCharacter = '`', nullValues = "null", useHeadersInDisplayName = true)
  void hasValue_throwsAssertionErrorForNullActual(final Short expectedValue, final String expectedExceptionMessage) {
    final var actual = (SomeShortType) null;
    final var shortTypeAssert = ShortTypeAssert.assertThat(actual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> shortTypeAssert.hasValue(expectedValue))
        .withMessage(expectedExceptionMessage);
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      VALUE | EXPECTED_VALUE | EXPECTED_EXCEPTION_MESSAGE
      null  | null           |
      1222  | 1222           |
      null  | 1444           | '\nExpected actual of type:  SomeShortType\nto have value:  1444\nbut value was:  null'
      1555  | null           | '\nExpected actual of type:  SomeShortType\nto have value:  null\nbut value was:  1555'
      1666  | 1777           | '\nExpected actual of type:  SomeShortType\nto have value:  1777\nbut value was:  1666'
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void hasValue_behavesAsExpected(
      final Short value, final Short expectedValue,
      final String expectedExceptionMessage) {

    final var actual = new SomeShortType(value);
    final var shortTypeAssert = ShortTypeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {

      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(() -> shortTypeAssert.hasValue(expectedValue))
          .withMessage(expectedExceptionMessage);

    } else {

      final var hasValueResult = shortTypeAssert.hasValue(expectedValue);
      assertThat(hasValueResult)
          .isInstanceOf(ShortTypeAssert.class)
          .isSameAs(shortTypeAssert);
    }
  }

  @Test
  void isNull_doesNotThrowAssertionErrorForNullActual() {
    final var actual = (SomeShortType) null;
    final var shortTypeAssert = ShortTypeAssert.assertThat(actual);

    assertThatNoException()
        .isThrownBy(shortTypeAssert::isNull);
  }

  @Test
  void isNull_throwsAssertionErrorForNonNullActual() {
    final var actual = new SomeShortType(null);
    final var shortTypeAssert = ShortTypeAssert.assertThat(actual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(shortTypeAssert::isNull)
        .withMessage(
            "\nExpected actual of type:  SomeShortType\nto be:  null\nbut was non-null with an internal value:  null\nConsider using '.isNullOrHasNullValue()' if that is what you want to test.");
  }


  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      VALUE | EXPECTED_EXCEPTION_MESSAGE
      null  | `\nExpected actual of type:  SomeShortType\nto be:  null\nbut was non-null with an internal value:  null\nConsider using '.isNullOrHasNullValue()' if that is what you want to test.`
      1222  | `\nExpected actual of type:  SomeShortType\nto be:  null\nbut was non-null with an internal value:  1222`
      """, delimiter = '|', nullValues = "null", quoteCharacter = '`', useHeadersInDisplayName = true)
  void isNull_behavesAsExpected(
      final Short value,final String expectedExceptionMessage) {

    final var actual = new SomeShortType(value);
    final var shortTypeAssert = ShortTypeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {
      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(shortTypeAssert::isNull)
          .withMessage(expectedExceptionMessage);
    } else {
      assertThatNoException()
          .isThrownBy(shortTypeAssert::isNull);
    }
  }

  @Test
  void isNotNull_throwsAssertionErrorForNullActual() {
    final var actual = (SomeShortType) null;
    final var shortTypeAssert = ShortTypeAssert.assertThat(actual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(shortTypeAssert::isNotNull)
        .withMessage("\nExpected actual of type:  <? extends ShortType>\nto not be:  null\nbut was: null");
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      VALUE | EXPECTED_EXCEPTION_MESSAGE
      null  |
      1222  |
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void isNotNull_behavesAsExpected(
      final Short value, final String expectedExceptionMessage) {

    final var actual = new SomeShortType(value);
    final var shortTypeAssert = ShortTypeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {
      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(shortTypeAssert::isNotNull)
          .withMessage(expectedExceptionMessage);
    } else {
      assertThatNoException()
          .isThrownBy(shortTypeAssert::isNotNull);
    }
  }

  @Test
  void hasNullValue_throwsExceptionForNullActual() {
    final var actual = (SomeShortType) null;
    final var shortTypeAssert = ShortTypeAssert.assertThat(actual);
    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(shortTypeAssert::hasNullValue)
        .withMessage("\nExpected actual of type:  <? extends ShortType>\nto not be null and have value:   null\nbut actual was:  null.\nConsider using '.isNullOrHasNullValue()' if that is what you want to test.");
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      VALUE | EXPECTED_EXCEPTION_MESSAGE
      null  |
      -1    | '\nExpected actual of type:  SomeShortType\nto have value:  null\nbut value was:  -1'
      0     | '\nExpected actual of type:  SomeShortType\nto have value:  null\nbut value was:  0'
      1122  | '\nExpected actual of type:  SomeShortType\nto have value:  null\nbut value was:  1122'
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void hasNullValue_behavesAsExpected(
      final Short value, final String expectedExceptionMessage) {

    final var actual = new SomeShortType(value);
    final var shortTypeAssert = ShortTypeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {
      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(shortTypeAssert::hasNullValue)
          .withMessage(expectedExceptionMessage);
    } else {
      assertThatNoException()
          .isThrownBy(shortTypeAssert::hasNullValue);
    }
  }

  @Test
  void isNullOrHasNullValue_throwsAssertionErrorForNonNullActual() {
    final var actual = (SomeShortType) null;
    final var shortTypeAssert = ShortTypeAssert.assertThat(actual);

    assertThatNoException()
        .isThrownBy(shortTypeAssert::isNullOrHasNullValue);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE | EXPECTED_EXCEPTION_MESSAGE
      null  |
      -1    | '\nExpected actual of type: SomeShortType\nto be null or have an internal value of null\nbut was:  -1'
      0     | '\nExpected actual of type: SomeShortType\nto be null or have an internal value of null\nbut was:  0'
      1122  | '\nExpected actual of type: SomeShortType\nto be null or have an internal value of null\nbut was:  1122'
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void isNullOrHasNullValue_behavesAsExpected(
      final Short value, final String expectedExceptionMessage) {

    final var actual = new SomeShortType(value);
    final var shortTypeAssert = ShortTypeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {
      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(shortTypeAssert::isNullOrHasNullValue)
          .withMessage(expectedExceptionMessage);
    } else {
      assertThatNoException()
          .isThrownBy(shortTypeAssert::isNullOrHasNullValue);
    }
  }

  @Test
  void isNotNullAndHasNonNullValue_throwsAssertionErrorForNullActual() {
    final var actual = (SomeShortType) null;
    final var shortTypeAssert = ShortTypeAssert.assertThat(actual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(shortTypeAssert::isNotNullAndHasNonNullValue)
        .withMessage("\nExpected actual of type:  <? extends ShortType>\nto not be:  null\nbut was:  null");
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE | EXPECTED_EXCEPTION_MESSAGE
      null  | '\nExpected actual of type:  SomeShortType\nto not have value: null\nbut value was:  null'
      -1    |
      0     |
      1122  |
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void isNotNullAndHasNonNullValue_behavesAsExpected(
      final Short value, final String expectedExceptionMessage) {

    final var actual = new SomeShortType(value);
    final var shortTypeAssert = ShortTypeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {
      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(shortTypeAssert::isNotNullAndHasNonNullValue)
          .withMessage(expectedExceptionMessage);
    } else {
      assertThatNoException()
          .isThrownBy(shortTypeAssert::isNotNullAndHasNonNullValue);
    }
  }

  private static final class SomeShortType extends ShortType {

    @Serial
    private static final long serialVersionUID = -1842193583997443860L;

    SomeShortType(final Short value) {
      super(value);
    }
  }
}
