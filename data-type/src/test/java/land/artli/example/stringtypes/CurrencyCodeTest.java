package land.artli.example.stringtypes;

import org.assertj.core.api.Assertions;
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
    Assertions.assertThatObject(CurrencyCode.of(value)).hasToString(expected);
  }
}
