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

class TypeParser_LanguageGreekTest extends AbstractTypeParserTest {

  static final TypeParser TYPE_PARSER =
      TypeParser.builder()
          .messageCode(MessageCode.of("must.be.greek.letters.only", "Must be made up of Greek letters only."))
          .toCharacterNormalizationFormNFC()
          .acceptSubset(Letters.GREEK_el)
          .normalizeWhitespace()
          .build();

  @ParameterizedTest
  @ValueSource(strings = {
      "Αρκούδα", // Greek 'Bear'
      "Καμηλοπάρδαλη", // Greek 'Giraffe'
      "Μαϊμού", // Greek 'Monkey'
  })
  void should_parse_accepting_only_greek_letters(final String value) {
    Assertions.assertThat(TYPE_PARSER.parseToString(value)).hasToString(value);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE            | ERROR_DESCRIPTION                | COMMENT
      Αρκoύδα          | o U+006F LATIN SMALL LETTER O.   | Greek 'Bear' with English 'o'
      Kαμηλοπάρδαλη    | K U+004B LATIN CAPITAL LETTER K. | Greek 'Giraffe' with English 'K'
      Mαϊμού           | M U+004D LATIN CAPITAL LETTER M. | Greek 'Monkey' with English 'M'
      """, delimiter = '|', useHeadersInDisplayName = true)
  void should_throw_exception_with_non_greek_letters(final String value, final String expectedErrorDescription) {
    Assertions.assertThatExceptionOfType(InvalidValueException.class)
        .isThrownBy(() -> TYPE_PARSER.parseToString(value))
        .withMessage("Must be made up of Greek letters only. Invalid value - invalid character " + expectedErrorDescription);
  }

}
