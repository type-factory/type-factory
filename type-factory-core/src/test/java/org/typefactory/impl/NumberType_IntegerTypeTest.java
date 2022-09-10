package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.typefactory.IntegerType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NumberType_IntegerTypeTest {

  @Test
  void constructor_acceptsNull() {
    final IntegerType actual = new SomeIntegerType(null);
    final IntegerType actualOther = new SomeIntegerType(null);
    final IntegerType nullOther = null;

    assertThat(actual.isNull()).isTrue();

    assertThat(actual)
        .hasToString("")
        .hasSameHashCodeAs(actual)
        .hasSameHashCodeAs(actualOther)
        .isEqualTo(actual)
        .isEqualTo(actualOther)
        .isEqualByComparingTo(actual)
        .isEqualByComparingTo(actualOther)
        .isNotEqualTo(nullOther);

    assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual::byteValue);
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual::shortValue);
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual::intValue);
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual::longValue);
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual::floatValue);
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual::doubleValue);
  }

  @ParameterizedTest
  @ValueSource(ints = {
      0,
      1,
      -1,
      Integer.MIN_VALUE + 1,
      Integer.MIN_VALUE,
      Integer.MAX_VALUE - 1,
      Integer.MAX_VALUE,
  })
  void constructor_acceptsValues(final Integer value) {
    final IntegerType actual = new SomeIntegerType(value);
    final IntegerType actualOther = new SomeIntegerType(value);
    final IntegerType nullOther = null;

    assertThat(actual.isNull()).isFalse();

    assertThat(actual)
        .hasToString(String.valueOf(value))
        .hasSameHashCodeAs(actual)
        .hasSameHashCodeAs(actualOther)
        .isEqualTo(actual)
        .isEqualTo(actualOther)
        .isEqualByComparingTo(actual)
        .isEqualByComparingTo(actualOther)
        .isNotEqualTo(nullOther);

    assertThat(actual.byteValue()).isEqualTo(value.byteValue());
    assertThat(actual.shortValue()).isEqualTo(value.shortValue());
    assertThat(actual.intValue()).isEqualTo(value.intValue());
    assertThat(actual.longValue()).isEqualTo(value.longValue());
    assertThat(actual.floatValue()).isEqualTo(value.floatValue());
    assertThat(actual.doubleValue()).isEqualTo(value.doubleValue());
  }

  private static final class SomeIntegerType extends IntegerType {

    public SomeIntegerType(Integer value) {
      super(value);
    }
  }
}
