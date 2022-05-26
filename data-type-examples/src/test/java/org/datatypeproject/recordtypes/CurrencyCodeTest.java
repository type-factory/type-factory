package org.datatypeproject.recordtypes;

import static org.assertj.core.api.Assertions.assertThatObject;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CurrencyCodeTest {

  @ParameterizedTest
  @CsvSource(value = {
      "AUD|AUD",
      "uSd|USD",
      "NzD|NZD",
      "eur|EUR",
  }, delimiter = '|')
  void should_parse_valid_values(final String value, final String expected) {
    final CurrencyCode actual = new CurrencyCode(value);
    assertThatObject(actual).hasToString(expected);
  }
}
