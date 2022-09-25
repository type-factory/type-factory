package org.typefactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LongTypeTest {

  @Test
  void constructor_acceptsNull() {
    final LongType actual = new ConcreteLongType(null);
    final LongType actualOther = new ConcreteLongType(null);
    final LongType differentOther = new AnotherLongType(null);
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
    final LongType actual = new ConcreteLongType(value);
    final LongType actualOther = new ConcreteLongType(value);
    final LongType differentOther = new AnotherLongType(value);
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
        .isNotEqualTo(differentOther)
        .isNotEqualTo(nullOther);

    assertThat(actual.byteValue()).isEqualTo(value.byteValue());
    assertThat(actual.shortValue()).isEqualTo(value.shortValue());
    assertThat(actual.intValue()).isEqualTo(value.intValue());
    assertThat(actual.longValue()).isEqualTo(value.longValue());
    assertThat(actual.floatValue()).isEqualTo(value.floatValue());
    assertThat(actual.doubleValue()).isEqualTo(value.doubleValue());
  }

  static final class ConcreteLongType extends LongType {

    ConcreteLongType(Long value) {
      super(value);
    }
  }

  static final class AnotherLongType extends LongType {

    AnotherLongType(Long value) {
      super(value);
    }
  }

}
