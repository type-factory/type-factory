package land.artli.strongtype;

import java.text.ParseException;
import org.assertj.core.api.Assertions;
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
  void should_parse_valid_values(final String value, final String expected) throws ParseException {
    Assertions.assertThat(CountryCode.of(value)).hasToString(expected);
  }
}
