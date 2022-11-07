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
import org.junit.jupiter.params.provider.CsvFileSource;

class TypeParser_LanguageJapaneseJinmeiyoTest extends AbstractTypeParserTest {

  static final TypeParser TYPE_PARSER =
      TypeParser.builder()
          .errorMessage("Must be made up of Japanese Jinmeiyō letters only.")
          .preserveCharacterNormalizationForm()
          .acceptSubset(Letters.JAPANESE_ja_Hani_x_jinmeiyo)
          .normalizeWhitespace()
          .build();

  @ParameterizedTest
  @CsvFileSource(
      resources = "/letters/japanese-jinmeiyo.psv",
      delimiter = '|',
      numLinesToSkip = 1)
  void should_parse_accepting_only_japanese_jinmeiyo_characters(final int unicode, final String value) {
    Assertions.assertThat(TYPE_PARSER.parseToString(new String(new int[]{unicode}, 0, 1))).hasToString(value);
  }

}
