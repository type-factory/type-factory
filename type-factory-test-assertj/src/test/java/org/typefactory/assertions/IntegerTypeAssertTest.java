package org.typefactory.assertions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.io.Serial;
import org.assertj.core.api.AbstractComparableAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.IntegerType;

class IntegerTypeAssertTest {

  @Test
  void integerTypeAssert_hasCustomAssertionMethods() {
    final var someActual = new SomeIntegerType(2222);
    final var integerTypeAssert = IntegerTypeAssert.assertThat(someActual);
    assertThat(integerTypeAssert.getClass())
        .hasPublicMethods("hasValue", "hasNullValue",
            "isNull", "isNullOrHasNullValue",
            "isNotNull", "isNotNullAndHasNonNullValue");
  }

  @Test
  void assertThat_returnsIntegerTypeAssertInstance() {
    final var someActual = new SomeIntegerType(3333);
    final var integerTypeAssert = IntegerTypeAssert.assertThat(someActual);
    assertThat(integerTypeAssert)
        .isInstanceOf(IntegerTypeAssert.class)
        .isInstanceOf(AbstractIntegerTypeAssert.class)
        .isInstanceOf(AbstractComparableAssert.class);
  }

  @Test
  void valueOfActual_returnsNullForNullActual() {
    final var someActual = (SomeIntegerType) null;
    final var integerTypeAssert = IntegerTypeAssert.assertThat(someActual);
    assertThat(integerTypeAssert.valueOfActual()).isNull();
  }

  @Test
  void valueOfActual_returnsNullForIntegerTypeWithNullValue() {
    final var someActual = new SomeIntegerType(null);
    final var integerTypeAssert = IntegerTypeAssert.assertThat(someActual);
    assertThat(integerTypeAssert.valueOfActual()).isNull();
  }

  @Test
  void actualHasNullValue_returnsFalseForNullActual() {
    final var someActual = (SomeIntegerType) null;
    final var integerTypeAssert = IntegerTypeAssert.assertThat(someActual);
    assertThat(integerTypeAssert.actualHasNullValue()).isFalse();
  }

  @Test
  void actualHasNullValue_returnsTrueForForIntegerTypeWithNullValue() {
    final var someActual = new SomeIntegerType(null);
    final var integerTypeAssert = IntegerTypeAssert.assertThat(someActual);
    assertThat(integerTypeAssert.actualHasNullValue()).isTrue();
  }

  @Test
  void classNameOfActual_returnsExtendsIntegerTypeForNullActual() {
    final var someActual = (SomeIntegerType) null;
    final var integerTypeAssert = IntegerTypeAssert.assertThat(someActual);
    assertThat(integerTypeAssert.classNameOfActual()).isEqualTo("<? extends IntegerType>");
  }

  @Test
  void valueOfActual_returnsClassNameForActualInstanceWithNullValue() {
    final var someActual = new SomeIntegerType(null);
    final var integerTypeAssert = IntegerTypeAssert.assertThat(someActual);
    assertThat(integerTypeAssert.classNameOfActual()).isEqualTo(SomeIntegerType.class.getSimpleName());
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      EXPECTED_VALUE | EXPECTED_EXCEPTION_MESSAGE
      null           | `\nExpected actual of type:  <? extends IntegerType>\nto be an instance with value:  null\nbut actual was:  null\nConsider using '.isNullOrHasNullValue()' if that is what you want to test.`
      -1             | `\nExpected actual of type:  <? extends IntegerType>\nto be an instance with value:  -1\nbut actual was:  null`
      0              | `\nExpected actual of type:  <? extends IntegerType>\nto be an instance with value:  0\nbut actual was:  null`
      2222           | `\nExpected actual of type:  <? extends IntegerType>\nto be an instance with value:  2222\nbut actual was:  null`
      """, delimiter = '|', quoteCharacter = '`', nullValues = "null", useHeadersInDisplayName = true)
  void hasValue_throwsAssertionErrorForNullActual(final Integer expectedValue, final String expectedExceptionMessage) {
    final var actual = (SomeIntegerType) null;
    final var integerTypeAssert = IntegerTypeAssert.assertThat(actual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> integerTypeAssert.hasValue(expectedValue))
        .withMessage(expectedExceptionMessage);
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      VALUE | EXPECTED_VALUE | EXPECTED_EXCEPTION_MESSAGE
      null  | null           |
      1222  | 1222           |
      null  | 1444           | '\nExpected actual of type:  SomeIntegerType\nto have value:  1444\nbut value was:  null'
      1555  | null           | '\nExpected actual of type:  SomeIntegerType\nto have value:  null\nbut value was:  1555'
      1666  | 1777           | '\nExpected actual of type:  SomeIntegerType\nto have value:  1777\nbut value was:  1666'
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void hasValue_behavesAsExpected(
      final Integer value, final Integer expectedValue,
      final String expectedExceptionMessage) {

    final var actual = new SomeIntegerType(value);
    final var integerTypeAssert = IntegerTypeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {

      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(() -> integerTypeAssert.hasValue(expectedValue))
          .withMessage(expectedExceptionMessage);

    } else {

      final var hasValueResult = integerTypeAssert.hasValue(expectedValue);
      assertThat(hasValueResult)
          .isInstanceOf(IntegerTypeAssert.class)
          .isSameAs(integerTypeAssert);
    }
  }

  @Test
  void isNull_doesNotThrowAssertionErrorForNullActual() {
    final var actual = (SomeIntegerType) null;
    final var integerTypeAssert = IntegerTypeAssert.assertThat(actual);

    assertThatNoException()
        .isThrownBy(integerTypeAssert::isNull);
  }

  @Test
  void isNull_throwsAssertionErrorForNonNullActual() {
    final var actual = new SomeIntegerType(null);
    final var integerTypeAssert = IntegerTypeAssert.assertThat(actual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(integerTypeAssert::isNull)
        .withMessage(
            "\nExpected actual of type:  SomeIntegerType\nto be:  null\nbut was non-null with an internal value:  null\nConsider using '.isNullOrHasNullValue()' if that is what you want to test.");
  }


  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      VALUE | EXPECTED_EXCEPTION_MESSAGE
      null  | `\nExpected actual of type:  SomeIntegerType\nto be:  null\nbut was non-null with an internal value:  null\nConsider using '.isNullOrHasNullValue()' if that is what you want to test.`
      1222  | `\nExpected actual of type:  SomeIntegerType\nto be:  null\nbut was non-null with an internal value:  1222`
      """, delimiter = '|', nullValues = "null", quoteCharacter = '`', useHeadersInDisplayName = true)
  void isNull_behavesAsExpected(
      final Integer value,final String expectedExceptionMessage) {

    final var actual = new SomeIntegerType(value);
    final var integerTypeAssert = IntegerTypeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {
      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(integerTypeAssert::isNull)
          .withMessage(expectedExceptionMessage);
    } else {
      assertThatNoException()
          .isThrownBy(integerTypeAssert::isNull);
    }
  }

  @Test
  void isNotNull_throwsAssertionErrorForNullActual() {
    final var actual = (SomeIntegerType) null;
    final var integerTypeAssert = IntegerTypeAssert.assertThat(actual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(integerTypeAssert::isNotNull)
        .withMessage("\nExpected actual of type:  <? extends IntegerType>\nto not be:  null\nbut was: null");
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      VALUE | EXPECTED_EXCEPTION_MESSAGE
      null  |
      1222  |
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void isNotNull_behavesAsExpected(
      final Integer value, final String expectedExceptionMessage) {

    final var actual = new SomeIntegerType(value);
    final var integerTypeAssert = IntegerTypeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {
      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(integerTypeAssert::isNotNull)
          .withMessage(expectedExceptionMessage);
    } else {
      assertThatNoException()
          .isThrownBy(integerTypeAssert::isNotNull);
    }
  }

  @Test
  void hasNullValue_throwsExceptionForNullActual() {
    final var actual = (SomeIntegerType) null;
    final var integerTypeAssert = IntegerTypeAssert.assertThat(actual);
    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(integerTypeAssert::hasNullValue)
        .withMessage("\nExpected actual of type:  <? extends IntegerType>\nto not be null and have value:   null\nbut actual was:  null.\nConsider using '.isNullOrHasNullValue()' if that is what you want to test.");
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      VALUE | EXPECTED_EXCEPTION_MESSAGE
      null  |
      -1    | '\nExpected actual of type:  SomeIntegerType\nto have value:  null\nbut value was:  -1'
      0     | '\nExpected actual of type:  SomeIntegerType\nto have value:  null\nbut value was:  0'
      1122  | '\nExpected actual of type:  SomeIntegerType\nto have value:  null\nbut value was:  1122'
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void hasNullValue_behavesAsExpected(
      final Integer value, final String expectedExceptionMessage) {

    final var actual = new SomeIntegerType(value);
    final var integerTypeAssert = IntegerTypeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {
      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(integerTypeAssert::hasNullValue)
          .withMessage(expectedExceptionMessage);
    } else {
      assertThatNoException()
          .isThrownBy(integerTypeAssert::hasNullValue);
    }
  }

  @Test
  void isNullOrHasNullValue_throwsAssertionErrorForNonNullActual() {
    final var actual = (SomeIntegerType) null;
    final var integerTypeAssert = IntegerTypeAssert.assertThat(actual);

    assertThatNoException()
        .isThrownBy(integerTypeAssert::isNullOrHasNullValue);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE | EXPECTED_EXCEPTION_MESSAGE
      null  |
      -1    | '\nExpected actual of type: SomeIntegerType\nto be null or have an internal value of null\nbut was:  -1'
      0     | '\nExpected actual of type: SomeIntegerType\nto be null or have an internal value of null\nbut was:  0'
      1122  | '\nExpected actual of type: SomeIntegerType\nto be null or have an internal value of null\nbut was:  1122'
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void isNullOrHasNullValue_behavesAsExpected(
      final Integer value, final String expectedExceptionMessage) {

    final var actual = new SomeIntegerType(value);
    final var integerTypeAssert = IntegerTypeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {
      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(integerTypeAssert::isNullOrHasNullValue)
          .withMessage(expectedExceptionMessage);
    } else {
      assertThatNoException()
          .isThrownBy(integerTypeAssert::isNullOrHasNullValue);
    }
  }

  @Test
  void isNotNullAndHasNonNullValue_throwsAssertionErrorForNullActual() {
    final var actual = (SomeIntegerType) null;
    final var integerTypeAssert = IntegerTypeAssert.assertThat(actual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(integerTypeAssert::isNotNullAndHasNonNullValue)
        .withMessage("\nExpected actual of type:  <? extends IntegerType>\nto not be:  null\nbut was:  null");
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE | EXPECTED_EXCEPTION_MESSAGE
      null  | '\nExpected actual of type:  SomeIntegerType\nto not have value: null\nbut value was:  null'
      -1    |
      0     |
      1122  |
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void isNotNullAndHasNonNullValue_behavesAsExpected(
      final Integer value, final String expectedExceptionMessage) {

    final var actual = new SomeIntegerType(value);
    final var integerTypeAssert = IntegerTypeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {
      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(integerTypeAssert::isNotNullAndHasNonNullValue)
          .withMessage(expectedExceptionMessage);
    } else {
      assertThatNoException()
          .isThrownBy(integerTypeAssert::isNotNullAndHasNonNullValue);
    }
  }

  private static final class SomeIntegerType extends IntegerType {

    @Serial
    private static final long serialVersionUID = -1842193583997443860L;

    SomeIntegerType(final Integer value) {
      super(value);
    }
  }
}
