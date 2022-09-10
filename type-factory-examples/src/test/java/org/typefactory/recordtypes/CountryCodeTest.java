package org.typefactory.recordtypes;

import static org.assertj.core.api.Assertions.assertThatObject;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CountryCodeTest {

  @ParameterizedTest
  @CsvSource(value = {
      "AU|AU",
      "uS|US",
      "Nz|NZ",
      "de|DE",
  }, delimiter = '|')
  void should_parse_valid_values(final String value, final String expected) {
    final CountryCode actual = new CountryCode(value);
    assertThatObject(actual).hasToString(expected);
  }
}
