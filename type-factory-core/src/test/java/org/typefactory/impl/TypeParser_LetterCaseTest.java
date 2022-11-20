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

class TypeParser_LetterCaseTest extends AbstractTypeParserTest {
  
  @ParameterizedTest
  @CsvSource(value = {
      "CAT|CAT",
      "Dog|Dog",
      "newT|newT",
      "snAke|snAke",
  }, delimiter = '|')
  void should_parse_preserving_case(final String value, final String expected) {

    final TypeParser typeParser =
        TypeParser.builder()
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .preserveCase()
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "CAT|cat",
      "Dog|dog",
      "newT|newt",
      "snAke|snake",
  }, delimiter = '|')
  void should_parse_to_lower_case(final String value, final String expected) {

    final TypeParser typeParser =
        TypeParser.builder()
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .toLowerCase()
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "CAT|CAT",
      "Dog|DOG",
      "newT|NEWT",
      "snAke|SNAKE",
  }, delimiter = '|')
  void should_parse_to_upper_case(final String value, final String expected) {

    final TypeParser typeParser =
        TypeParser.builder()
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .toUpperCase()
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "CAT|Cat",
      "Dog|Dog",
      "newT|Newt",
      "snAke|Snake",
  }, delimiter = '|')
  void should_parse_to_title_case(final String value, final String expected) {

    final TypeParser typeParser =
        TypeParser.builder()
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .toTitleCase()
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(expected);
  }
}
