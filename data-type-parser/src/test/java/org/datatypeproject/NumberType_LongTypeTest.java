package org.datatypeproject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NumberType_LongTypeTest {

  @Test
  void constructor_acceptsNull() {
    final LongType actual = new SomeLongType(null);
    final LongType actualOther = new SomeLongType(null);
    final LongType nullOther = null;

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
  @ValueSource(longs = {
      0,
      1,
      -1,
      Long.MIN_VALUE + 1,
      Long.MIN_VALUE,
      Long.MAX_VALUE - 1,
      Long.MAX_VALUE,
  })
  void constructor_acceptsValues(final Long value) {
    final LongType actual = new SomeLongType(value);
    final LongType actualOther = new SomeLongType(value);
    final LongType nullOther = null;

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

  private static final class SomeLongType extends LongType {

    public SomeLongType(Long value) {
      super(value);
    }
  }
}
