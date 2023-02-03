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
import org.junit.jupiter.params.provider.ValueSource;
import org.typefactory.MessageCode;
import org.typefactory.TypeParser;

class TypeParser_LanguageArmenianTest extends AbstractTypeParserTest {

  @ParameterizedTest
  @ValueSource(strings = {
      "ռնգեղջյուր", // Armenian 'rhinoceros'
      "թիթեռ", // Armenian 'butterfly'
      "կապիկ", // Armenian 'monkey'
  })
  void should_parse_accepting_only_armenian_letters(final String value) {

    final TypeParser typeParser =
        TypeParser.builder()
            .messageCode(MessageCode.of("must.be.armenian.letters.only", "Must be made up of Armenian letters only."))
            .toCharacterNormalizationFormNFC()
            .acceptSubset(Letters.ARMENIAN_hy)
            .normalizeWhitespace()
            .build();
    Assertions.assertThat(typeParser.parseToString(value)).hasToString(value);
  }


}
