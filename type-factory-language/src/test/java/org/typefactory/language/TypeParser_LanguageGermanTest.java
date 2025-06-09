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

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.typefactory.InvalidValueException;
import org.typefactory.MessageCode;
import org.typefactory.TypeParser;

class TypeParser_LanguageGermanTest extends AbstractTypeParserTest {

  static final TypeParser TYPE_PARSER =
      TypeParser.builder()
          .messageCode(MessageCode.of("must.be.german.letters.only", "Must be made up of German letters only."))
          .toCharacterNormalizationFormNFC()
          .acceptSubset(Letters.GERMAN_de)
          .normalizeWhitespace()
          .build();

  @ParameterizedTest
  @ValueSource(strings = {
      "das Eichhörnchen", // German 'Squirrel'
      "der Hirsch", // German 'Deer'
      "die Weiße Taube", // German 'Dove'
  })
  void should_parse_accepting_only_german_letters(final String value) {
    Assertions.assertThat(TYPE_PARSER.parseToString(value)).hasToString(value);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE            | ERROR_DESCRIPTION                        | COMMENT
      das Εichhörnchen | 'Ε' U+0395 GREEK CAPITAL LETTER EPSILON. | German 'Squirrel' with Greek 'Ε' epsilon
      der Ηirsch       | 'Η' U+0397 GREEK CAPITAL LETTER ETA.     | German 'Deer' with Greek 'Η' ita
      die Weiße Τaube  | 'Τ' U+03A4 GREEK CAPITAL LETTER TAU.     | German 'Dove' with Greek 'Τ' taf
      """, delimiter = '|', useHeadersInDisplayName = true)
  void should_throw_exception_with_non_german_letters(final String value, final String expectedErrorDescription) {
    Assertions.assertThatExceptionOfType(InvalidValueException.class)
        .isThrownBy(() -> TYPE_PARSER.parseToString(value))
        .withMessage("Must be made up of German letters only. Invalid value - invalid character " + expectedErrorDescription);
  }

}
