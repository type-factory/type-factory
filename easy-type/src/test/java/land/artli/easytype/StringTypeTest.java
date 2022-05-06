package land.artli.easytype;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatObject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StringTypeTest {

  @Test
  void constructor_acceptsNull() {
    final StringType actual = new SomeStringType(null);
    final StringType actualOther = new SomeStringType(null);
    final StringType nullOther = null;

    assertThat(actual.isNull()).isTrue();
    assertThat(actual.isBlank()).isTrue();
    assertThat(actual.isEmpty()).isTrue();
    assertThat(actual.compareTo(actual)).isZero();
    assertThat(actual.compareTo(actualOther)).isZero();

    assertThatObject(actual)
        .hasToString("")
        .hasSameHashCodeAs(actual)
        .hasSameHashCodeAs(actualOther)
        .isEqualTo(actual)
        .isEqualTo(actualOther)
        .isNotEqualTo(nullOther);
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "",
      " ",
      "A",
      "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
  })
  void constructor_acceptsValues(final String value) {
    final StringType actual = new SomeStringType(value);
    final StringType actualOther = new SomeStringType(value);
    final StringType nullOther = null;

    assertThat(actual.isNull()).isFalse();
    assertThat(actual.compareTo(actual)).isZero();
    assertThat(actual.compareTo(actualOther)).isZero();

    assertThatObject(actual)
        .hasToString(String.valueOf(value))
        .hasSameHashCodeAs(actual)
        .hasSameHashCodeAs(actualOther)
        .isEqualTo(actual)
        .isEqualTo(actualOther)
        .isNotEqualTo(nullOther);
  }

  private static final class SomeStringType extends StringType {

    public SomeStringType(String value) {
      super(value);
    }
  }
}
