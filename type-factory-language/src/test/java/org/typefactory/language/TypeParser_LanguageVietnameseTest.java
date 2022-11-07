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
import org.typefactory.InvalidValueException;
import org.typefactory.TypeParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TypeParser_LanguageVietnameseTest extends AbstractTypeParserTest {

  static final TypeParser TYPE_PARSER =
      TypeParser.builder()
          .errorMessage("Must be made up of Vietnamese letters only.")
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
  @ValueSource(strings = {
      "Cοn gấu", // Vietnamese 'Bear' with Greek 'ο' omicron
      "Con hươυ cao cổ", // Vietnamese 'Giraffe' with Greek 'υ' upsilon
      "Con Κhỉ", // Vietnamese 'Monkey' with Greek 'Κ' kappa
  })
  void should_throw_exception_with_non_vietnamese_letters(final String value) {
    Assertions.assertThatExceptionOfType(InvalidValueException.class)
        .isThrownBy(() -> TYPE_PARSER.parseToString(value))
        .withMessageMatching("Must be made up of Vietnamese letters only. Invalid value - invalid character '[^']+'.");
  }

}
