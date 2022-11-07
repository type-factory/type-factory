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
import org.typefactory.language.Letters;
import org.typefactory.TypeParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TypeParser_LanguageArabicTest extends AbstractTypeParserTest {

  @ParameterizedTest
  @ValueSource(strings = {
      "جاموس", // Arabic 'Buffalo'
      "حصان", // Arabic 'Horse'
      "قنديل البحر", // Arabic 'Jellyfish'
  })
  void should_parse_accepting_only_arabic_letters(final String value) {

    final TypeParser typeParser =
        TypeParser.builder()
            .errorMessage("Must be made up of Arabic letters only.")
            .toCharacterNormalizationFormNFC()
            .acceptSubset(Letters.ARABIC_ar)
            .normalizeWhitespace()
            .build();
    Assertions.assertThat(typeParser.parseToString(value)).hasToString(value);
  }


}
