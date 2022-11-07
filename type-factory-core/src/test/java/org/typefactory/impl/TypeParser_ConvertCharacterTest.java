/*
   Copyright 2021-2022 Evan Toliopoulos (typefactory.org)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package org.typefactory.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.TypeParser;

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
  void typeParser_shouldConvertCharacters(final String value, final String expected) {

    final TypeParser typeParser =
        TypeParser.builder()
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
