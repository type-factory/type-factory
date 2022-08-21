package org.datatypeproject.impl;

import java.text.ParseException;
import org.assertj.core.api.Assertions;
import org.datatypeproject.TypeParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TypeParser_ConvertCharacterTest extends AbstractTypeParserTest {

  @ParameterizedTest
  @CsvSource(value = {
      "111052|111052",
      "7ILOSZ|111052",
      "OIZS-ABCD|0125-ABCD",
      "OIZS_ABCD|0125-ABCD",
      "OIZS ABCD|0125-ABCD",
      "0123-4567-89AB-CDEF-GHIJ-KLMN-OPQR-STUV-WXYZ|0123-4561-89AB-CDEF-GH1J-K1MN-0PQR-5TUV-WXY2",
  }, delimiter = '|')
  void should_parse_preserving_case(final String value, final String expected) throws ParseException {

    final TypeParser typeParser =
        TypeParser.builder(SomeType.class)
            .minSize(4)
            .maxSize(44)
            .toUpperCase()
            .convertChar('7', '1')
            .convertChar('I', '1')
            .convertChar('L', '1')
            .convertChar('O', '0')
            .convertChar('S', '5')
            .convertChar('Z', '2')
            .convertChar('_', '-')
            .normalizeAndConvertWhitespaceTo('-')
            .acceptHyphenAndConvertAllDashesToHyphen()
            .acceptDigits0to9()
            .acceptLettersAtoZ()
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(expected);
  }

}
