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

class TypeParser_DashPunctuationTest extends AbstractTypeParserTest {

  @ParameterizedTest
  @CsvSource(value = {
      "Water-slide|Water-slide",
      "Water–slide|Water-slide",
      "Water—slide|Water-slide",
  }, delimiter = '|')
  void should_parse_converting_all_dashes_to_hyphen(final String value, final String expected) {

    final TypeParser typeParser =
        TypeParser.builder()
            .acceptAllDashes()
            .convertAllDashesToHyphen()
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "Water-slide|Water_slide",
      "Water–slide|Water_slide",
      "Water—slide|Water_slide",
  }, delimiter = '|')
  void should_parse_converting_all_dashes_to_underscore(final String value, final String expected) {

    final TypeParser typeParser =
        TypeParser.builder()
            .acceptAllDashes()
            .convertAllDashesTo('_')
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "Water-slide|Water===slide",
      "Water–slide|Water===slide",
      "Water—slide|Water===slide",
  }, delimiter = '|')
  void should_parse_converting_all_dashes_to_char_sequence(final String value, final String expected) {

    final TypeParser typeParser =
        TypeParser.builder()
            .acceptAllDashes()
            .convertAllDashesTo("===")
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(expected);
  }
}
