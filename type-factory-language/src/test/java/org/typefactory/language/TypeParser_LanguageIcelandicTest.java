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

class TypeParser_LanguageIcelandicTest extends AbstractTypeParserTest {

  static final TypeParser TYPE_PARSER =
      TypeParser.builder()
          .messageCode(MessageCode.of("must.be.icelandic.letters.only", "Must be made up of Icelandic letters only."))
          .toCharacterNormalizationFormNFC()
          .acceptSubset(Letters.ICELANDIC_is)
          .normalizeWhitespace()
          .build();

  @ParameterizedTest
  @ValueSource(strings = {
      "björn", // Icelandic 'Bear'
      "mörður", // Icelandic 'Marten'
      "hvítabjörninn", // Icelandic 'Polar Bear'
  })
  void should_parse_accepting_only_icelandic_letters(final String value) {
    Assertions.assertThat(TYPE_PARSER.parseToString(value)).hasToString(value);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE         | ERROR_DESCRIPTION                            | COMMENT
      björη         | η U+03B7 GREEK SMALL LETTER ETA.             | Icelandic 'Bear' with Greek 'η' ita
      mörðυr        | υ U+03C5 GREEK SMALL LETTER UPSILON.         | Icelandic 'Marten' with Greek 'υ' upsilon
      hvίtabjörninn | ί U+03AF GREEK SMALL LETTER IOTA WITH TONOS. | Icelandic 'Polar Bear' with Greek 'ί' iοta
      """, delimiter = '|', useHeadersInDisplayName = true)
  void should_throw_exception_with_non_icelandic_letters(final String value, final String expectedErrorDescription) {
    Assertions.assertThatExceptionOfType(InvalidValueException.class)
        .isThrownBy(() -> TYPE_PARSER.parseToString(value))
        .withMessage("Must be made up of Icelandic letters only. Invalid value - invalid character " + expectedErrorDescription);
  }

}
