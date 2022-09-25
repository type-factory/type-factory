package org.typefactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ShortTypeTest {

  @Test
  void constructor_acceptsNull() {
    final ShortType actual = new ConcreteShortType(null);
    final ShortType actualOther = new ConcreteShortType(null);
    final ShortType differentOther = new AnotherShortType(null);
    final ShortType nullOther = null;

    assertThat(actual.isNull()).isTrue();

    assertThat(actual)
        .hasToString("")
        .hasSameHashCodeAs(actual)
        .hasSameHashCodeAs(actualOther)
        .isEqualTo(actual)
        .isEqualTo(actualOther)
        .isEqualByComparingTo(actual)
        .isEqualByComparingTo(actualOther)
        .isNotEqualTo(differentOther)
        .isNotEqualTo(nullOther);

    assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual::byteValue);
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual::shortValue);
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual::intValue);
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual::longValue);
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual::floatValue);
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual::doubleValue);
  }

  @ParameterizedTest
  @ValueSource(shorts = {
      0,
      1,
      -1,
      Short.MIN_VALUE + 1,
      Short.MIN_VALUE,
      Short.MAX_VALUE - 1,
      Short.MAX_VALUE,
  })
  void constructor_acceptsValues(final Short value) {
    final ShortType actual = new ConcreteShortType(value);
    final ShortType actualOther = new ConcreteShortType(value);
    final ShortType differentOther = new AnotherShortType(value);
    final ShortType nullOther = null;

    assertThat(actual.isNull()).isFalse();

    assertThat(actual)
        .hasToString(String.valueOf(value))
        .hasSameHashCodeAs(actual)
        .hasSameHashCodeAs(actualOther)
        .isEqualTo(actual)
        .isEqualTo(actualOther)
        .isEqualByComparingTo(actual)
        .isEqualByComparingTo(actualOther)
        .isNotEqualTo(differentOther)
        .isNotEqualTo(nullOther);
  }

  static final class ConcreteShortType extends ShortType {

    ConcreteShortType(Short value) {
      super(value);
    }
  }

  static final class AnotherShortType extends ShortType {

    AnotherShortType(Short value) {
      super(value);
    }
  }

}
