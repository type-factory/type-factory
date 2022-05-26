package org.datatypeproject.numbertypes;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ProductIdTest {

  @ParameterizedTest
  @CsvSource(value = {
      "1111222233334444|1111222233334444",
      "2222 3333 4444 5555|2222333344445555",
  }, delimiter = '|')
  void should_parse_valid_values(final String value, final Long expected) {
    Assertions.assertThat(ProductId.of(value)).isNotNull();
    Assertions.assertThat(ProductId.of(value).value()).isEqualTo(expected);
  }
}
