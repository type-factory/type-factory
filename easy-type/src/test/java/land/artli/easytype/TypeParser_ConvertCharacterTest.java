package land.artli.easytype;

import java.text.ParseException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TypeParser_ConvertCharacterTest extends AbstractTypeParserTest {

  @ParameterizedTest
  @CsvSource(value = {
      "11052|11052",
      "7IOSZ|11052",
      "OIZS-ABCD|0125-ABCD",
      "OIZS_ABCD|0125-ABCD",
      "OIZS ABCD|0125-ABCD",
      "0123-4567-89AB-CDEF-GHIJ-KLMN-OPQR-STUV-WXYZ|0123-4561-89AB-CDEF-GH1J-KLMN-0PQR-5TUV-WXY2",
  }, delimiter = '|')
  void should_parse_preserving_case(final String value, final String expected) throws ParseException {

    final TypeParser typeParser =
        TypeParser.builder(SomeType.class)
            .minNumberOfCodePoints(4)
            .maxNumberOfCodePoints(44)
            .toUpperCase()
            .convertChar('7', '1')
            .convertChar('I', '1')
            .convertChar('O', '0')
            .convertChar('S', '5')
            .convertChar('Z', '2')
            .convertChar('_', '-')
            .normalizeAndConvertWhitespaceTo('-')
            .acceptHyphenAndConvertAllDashesToHyphen()
            .acceptCharRange('0', '6')
            .acceptCharRange('8', '9')
            .acceptCharRange('A', 'H')
            .acceptCharRange('J', 'N')
            .acceptCharRange('P', 'R')
            .acceptCharRange('T', 'Y')
            .build();

    Assertions.assertThat(typeParser.parse(value)).hasToString(expected);
  }

}