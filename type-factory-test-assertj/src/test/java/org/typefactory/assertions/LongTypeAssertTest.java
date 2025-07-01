package org.typefactory.assertions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.io.Serial;
import org.assertj.core.api.AbstractComparableAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.LongType;

class LongTypeAssertTest {

  @Test
  void longTypeAssert_hasCustomAssertionMethods() {
    final var someActual = new SomeLongType(2222L);
    final var longTypeAssert = LongTypeAssert.assertThat(someActual);
    assertThat(longTypeAssert.getClass())
        .hasPublicMethods("hasValue", "hasNullValue",
            "isNull", "isNullOrHasNullValue",
            "isNotNull", "isNotNullAndHasNonNullValue");
  }

  @Test
  void assertThat_returnsLongTypeAssertInstance() {
    final var someActual = new SomeLongType(3333L);
    final var longTypeAssert = LongTypeAssert.assertThat(someActual);
    assertThat(longTypeAssert)
        .isInstanceOf(LongTypeAssert.class)
        .isInstanceOf(AbstractLongTypeAssert.class)
        .isInstanceOf(AbstractComparableAssert.class);
  }

  @Test
  void valueOfActual_returnsNullForNullActual() {
    final var someActual = (SomeLongType) null;
    final var longTypeAssert = LongTypeAssert.assertThat(someActual);
    assertThat(longTypeAssert.valueOfActual()).isNull();
  }

  @Test
  void valueOfActual_returnsNullForLongTypeWithNullValue() {
    final var someActual = new SomeLongType(null);
    final var longTypeAssert = LongTypeAssert.assertThat(someActual);
    assertThat(longTypeAssert.valueOfActual()).isNull();
  }

  @Test
  void actualHasNullValue_returnsFalseForNullActual() {
    final var someActual = (SomeLongType) null;
    final var longTypeAssert = LongTypeAssert.assertThat(someActual);
    assertThat(longTypeAssert.actualHasNullValue()).isFalse();
  }

  @Test
  void actualHasNullValue_returnsTrueForForLongTypeWithNullValue() {
    final var someActual = new SomeLongType(null);
    final var longTypeAssert = LongTypeAssert.assertThat(someActual);
    assertThat(longTypeAssert.actualHasNullValue()).isTrue();
  }

  @Test
  void classNameOfActual_returnsExtendsLongTypeForNullActual() {
    final var someActual = (SomeLongType) null;
    final var longTypeAssert = LongTypeAssert.assertThat(someActual);
    assertThat(longTypeAssert.classNameOfActual()).isEqualTo("<? extends LongType>");
  }

  @Test
  void valueOfActual_returnsClassNameForActualInstanceWithNullValue() {
    final var someActual = new SomeLongType(null);
    final var longTypeAssert = LongTypeAssert.assertThat(someActual);
    assertThat(longTypeAssert.classNameOfActual()).isEqualTo(SomeLongType.class.getSimpleName());
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      EXPECTED_VALUE | EXPECTED_EXCEPTION_MESSAGE
      null           | `\nExpected actual of type:  <? extends LongType>\nto be an instance with value:  null\nbut actual was:  null\nConsider using '.isNullOrHasNullValue()' if that is what you want to test.`
      -1             | `\nExpected actual of type:  <? extends LongType>\nto be an instance with value:  -1\nbut actual was:  null`
      0              | `\nExpected actual of type:  <? extends LongType>\nto be an instance with value:  0\nbut actual was:  null`
      2222           | `\nExpected actual of type:  <? extends LongType>\nto be an instance with value:  2222\nbut actual was:  null`
      """, delimiter = '|', quoteCharacter = '`', nullValues = "null", useHeadersInDisplayName = true)
  void hasValue_throwsAssertionErrorForNullActual(final Long expectedValue, final String expectedExceptionMessage) {
    final var actual = (SomeLongType) null;
    final var longTypeAssert = LongTypeAssert.assertThat(actual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> longTypeAssert.hasValue(expectedValue))
        .withMessage(expectedExceptionMessage);
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      VALUE | EXPECTED_VALUE | EXPECTED_EXCEPTION_MESSAGE
      null  | null           |
      1222  | 1222           |
      null  | 1444           | '\nExpected actual of type:  SomeLongType\nto have value:  1444\nbut value was:  null'
      1555  | null           | '\nExpected actual of type:  SomeLongType\nto have value:  null\nbut value was:  1555'
      1666  | 1777           | '\nExpected actual of type:  SomeLongType\nto have value:  1777\nbut value was:  1666'
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void hasValue_behavesAsExpected(
      final Long value, final Long expectedValue,
      final String expectedExceptionMessage) {

    final var actual = new SomeLongType(value);
    final var longTypeAssert = LongTypeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {

      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(() -> longTypeAssert.hasValue(expectedValue))
          .withMessage(expectedExceptionMessage);

    } else {

      final var hasValueResult = longTypeAssert.hasValue(expectedValue);
      assertThat(hasValueResult)
          .isInstanceOf(LongTypeAssert.class)
          .isSameAs(longTypeAssert);
    }
  }

  @Test
  void isNull_doesNotThrowAssertionErrorForNullActual() {
    final var actual = (SomeLongType) null;
    final var longTypeAssert = LongTypeAssert.assertThat(actual);

    assertThatNoException()
        .isThrownBy(longTypeAssert::isNull);
  }

  @Test
  void isNull_throwsAssertionErrorForNonNullActual() {
    final var actual = new SomeLongType(null);
    final var longTypeAssert = LongTypeAssert.assertThat(actual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(longTypeAssert::isNull)
        .withMessage(
            "\nExpected actual of type:  SomeLongType\nto be:  null\nbut was non-null with an internal value:  null\nConsider using '.isNullOrHasNullValue()' if that is what you want to test.");
  }


  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      VALUE | EXPECTED_EXCEPTION_MESSAGE
      null  | `\nExpected actual of type:  SomeLongType\nto be:  null\nbut was non-null with an internal value:  null\nConsider using '.isNullOrHasNullValue()' if that is what you want to test.`
      1222  | `\nExpected actual of type:  SomeLongType\nto be:  null\nbut was non-null with an internal value:  1222`
      """, delimiter = '|', nullValues = "null", quoteCharacter = '`', useHeadersInDisplayName = true)
  void isNull_behavesAsExpected(
      final Long value,final String expectedExceptionMessage) {

    final var actual = new SomeLongType(value);
    final var longTypeAssert = LongTypeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {
      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(longTypeAssert::isNull)
          .withMessage(expectedExceptionMessage);
    } else {
      assertThatNoException()
          .isThrownBy(longTypeAssert::isNull);
    }
  }

  @Test
  void isNotNull_throwsAssertionErrorForNullActual() {
    final var actual = (SomeLongType) null;
    final var longTypeAssert = LongTypeAssert.assertThat(actual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(longTypeAssert::isNotNull)
        .withMessage("\nExpected actual of type:  <? extends LongType>\nto not be:  null\nbut was: null");
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      VALUE | EXPECTED_EXCEPTION_MESSAGE
      null  |
      1222  |
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void isNotNull_behavesAsExpected(
      final Long value, final String expectedExceptionMessage) {

    final var actual = new SomeLongType(value);
    final var longTypeAssert = LongTypeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {
      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(longTypeAssert::isNotNull)
          .withMessage(expectedExceptionMessage);
    } else {
      assertThatNoException()
          .isThrownBy(longTypeAssert::isNotNull);
    }
  }

  @Test
  void hasNullValue_throwsExceptionForNullActual() {
    final var actual = (SomeLongType) null;
    final var longTypeAssert = LongTypeAssert.assertThat(actual);
    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(longTypeAssert::hasNullValue)
        .withMessage("\nExpected actual of type:  <? extends LongType>\nto not be null and have value:   null\nbut actual was:  null.\nConsider using '.isNullOrHasNullValue()' if that is what you want to test.");
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      VALUE | EXPECTED_EXCEPTION_MESSAGE
      null  |
      -1    | '\nExpected actual of type:  SomeLongType\nto have value:  null\nbut value was:  -1'
      0     | '\nExpected actual of type:  SomeLongType\nto have value:  null\nbut value was:  0'
      1122  | '\nExpected actual of type:  SomeLongType\nto have value:  null\nbut value was:  1122'
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void hasNullValue_behavesAsExpected(
      final Long value, final String expectedExceptionMessage) {

    final var actual = new SomeLongType(value);
    final var longTypeAssert = LongTypeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {
      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(longTypeAssert::hasNullValue)
          .withMessage(expectedExceptionMessage);
    } else {
      assertThatNoException()
          .isThrownBy(longTypeAssert::hasNullValue);
    }
  }

  @Test
  void isNullOrHasNullValue_throwsAssertionErrorForNonNullActual() {
    final var actual = (SomeLongType) null;
    final var longTypeAssert = LongTypeAssert.assertThat(actual);

    assertThatNoException()
        .isThrownBy(longTypeAssert::isNullOrHasNullValue);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE | EXPECTED_EXCEPTION_MESSAGE
      null  |
      -1    | '\nExpected actual of type: SomeLongType\nto be null or have an internal value of null\nbut was:  -1'
      0     | '\nExpected actual of type: SomeLongType\nto be null or have an internal value of null\nbut was:  0'
      1122  | '\nExpected actual of type: SomeLongType\nto be null or have an internal value of null\nbut was:  1122'
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void isNullOrHasNullValue_behavesAsExpected(
      final Long value, final String expectedExceptionMessage) {

    final var actual = new SomeLongType(value);
    final var longTypeAssert = LongTypeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {
      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(longTypeAssert::isNullOrHasNullValue)
          .withMessage(expectedExceptionMessage);
    } else {
      assertThatNoException()
          .isThrownBy(longTypeAssert::isNullOrHasNullValue);
    }
  }

  @Test
  void isNotNullAndHasNonNullValue_throwsAssertionErrorForNullActual() {
    final var actual = (SomeLongType) null;
    final var longTypeAssert = LongTypeAssert.assertThat(actual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(longTypeAssert::isNotNullAndHasNonNullValue)
        .withMessage("\nExpected actual of type:  <? extends LongType>\nto not be:  null\nbut was:  null");
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE | EXPECTED_EXCEPTION_MESSAGE
      null  | '\nExpected actual of type:  SomeLongType\nto not have value: null\nbut value was:  null'
      -1    |
      0     |
      1122  |
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void isNotNullAndHasNonNullValue_behavesAsExpected(
      final Long value, final String expectedExceptionMessage) {

    final var actual = new SomeLongType(value);
    final var longTypeAssert = LongTypeAssert.assertThat(actual);

    if (expectedExceptionMessage != null) {
      assertThatExceptionOfType(AssertionError.class)
          .isThrownBy(longTypeAssert::isNotNullAndHasNonNullValue)
          .withMessage(expectedExceptionMessage);
    } else {
      assertThatNoException()
          .isThrownBy(longTypeAssert::isNotNullAndHasNonNullValue);
    }
  }

  private static final class SomeLongType extends LongType {

    @Serial
    private static final long serialVersionUID = -1226102666776406773L;

    SomeLongType(final Long value) {
      super(value);
    }
  }
}
