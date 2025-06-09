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
package org.typefactory.language;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.typefactory.InvalidValueException;
import org.typefactory.MessageCode;
import org.typefactory.TypeParser;

class TypeParser_LanguageFrenchTest extends AbstractTypeParserTest {

  static final TypeParser TYPE_PARSER = TypeParser.builder()
      .acceptSubset(Letters.FRENCH_fr)
      .toCharacterNormalizationFormNFC()
      .convertAllDashesToHyphen()
      .acceptChar('\'')
      .normalizeWhitespace()
      .messageCode(MessageCode.of(
          "must.be.french.letters.only",
          "Must be made up of characters in the French "
              + "alphabet, hyphens, apostrophes or spaces only."))
      .build();

  @ParameterizedTest
  @ValueSource(strings = {
      "l'huître",     // French 'Oyster'
      "le porc-épic", // French 'Porcupine'
      "le léopard",   // French 'Leopard'
  })
  void should_parse_accepting_only_french_letters(final String value) {
    Assertions.assertThat(TYPE_PARSER.parseToString(value)).hasToString(value);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE        | ERROR_DESCRIPTION                      | COMMENT
      l'hυître     | 'υ' U+03C5 GREEK SMALL LETTER UPSILON. | French 'Oyster' with Greek 'υ' upsilon
      le pοrc-épic | 'ο' U+03BF GREEK SMALL LETTER OMICRON. | French 'Porcupine' with Greek 'ο' omicron
      le léoρard   | 'ρ' U+03C1 GREEK SMALL LETTER RHO.     | French 'Leopard' with Greek 'ρ' rho
      """, delimiter = '|', useHeadersInDisplayName = true)
  void should_throw_exception_with_non_french_letters(final String value, final String expectedErrorDescription) {
    assertThatExceptionOfType(InvalidValueException.class)
        .isThrownBy(() -> TYPE_PARSER.parseToString(value))
        .withMessage("Must be made up of characters in the French alphabet, hyphens, apostrophes or spaces only. Invalid value - invalid character "
            + expectedErrorDescription);
  }

}
