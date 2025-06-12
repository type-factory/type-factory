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

class TypeParser_LanguageVietnameseTest extends AbstractTypeParserTest {

  static final TypeParser TYPE_PARSER =
      TypeParser.builder()
          .messageCode(MessageCode.of("must.be.vietnamese.letters.only", "Must be made up of Vietnamese letters only."))
          .toCharacterNormalizationFormNFC()
          .acceptSubset(Letters.VIETNAMESE_vi)
          .normalizeWhitespace()
          .build();

  @ParameterizedTest
  @ValueSource(strings = {
      "Con gấu", // Vietnamese 'Bear'
      "Con hươu cao cổ", // Vietnamese 'Giraffe'
      "Con khỉ", // Vietnamese 'Monkey'
  })
  void should_parse_accepting_only_vietnamese_letters(final String value) {
    Assertions.assertThat(TYPE_PARSER.parseToString(value)).hasToString(value);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE           | ERROR_DESCRIPTION                    | COMMENT
      Cοn gấu         | ο U+03BF GREEK SMALL LETTER OMICRON. | Vietnamese 'Bear' with Greek 'ο' omicron
      Con hươυ cao cổ | υ U+03C5 GREEK SMALL LETTER UPSILON. | Vietnamese 'Giraffe' with Greek 'υ' upsilon
      Con Κhỉ         | Κ U+039A GREEK CAPITAL LETTER KAPPA. | Vietnamese 'Monkey' with Greek 'Κ' kappa
      """, delimiter = '|', useHeadersInDisplayName = true)
  void should_throw_exception_with_non_vietnamese_letters(final String value, final String expectedErrorDescription) {
    Assertions.assertThatExceptionOfType(InvalidValueException.class)
        .isThrownBy(() -> TYPE_PARSER.parseToString(value))
        .withMessage("Must be made up of Vietnamese letters only. Invalid value - invalid character " + expectedErrorDescription);
  }

}
