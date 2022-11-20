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
import org.junit.jupiter.params.provider.ValueSource;
import org.typefactory.TypeParser;

class TypeParser_WhitespaceTest extends AbstractTypeParserTest {
  
  @ParameterizedTest
  @CsvSource(value = {
      " Flood \t Plains        | FloodPlains ",
      " Deep \s  \t  \s Valley | DeepValley  ",
  }, delimiter = '|')
  void should_parse_and_remove_all_whitespace(final String value, final String expected) {

    final TypeParser typeParser =
        TypeParser.builder()
            .removeAllWhitespace()
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      " Flood \t Plains        | FloodPlains ",
      " Deep \s  \t  \s Valley | DeepValley  ",
  }, delimiter = '|')
  void should_parse_and_remove_all_accepted_whitespace(final String value, final String expected) {

    final TypeParser typeParser =
        TypeParser.builder()
            .removeAllWhitespace()
            .acceptChars(' ', '\t')
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      " Flood \t Plains        | Flood Plains ",
      " Deep \s  \t  \s Valley | Deep Valley  ",
  }, delimiter = '|')
  void should_parse_to_normalized_whitespace(final String value, final String expected) {

    final TypeParser typeParser =
        TypeParser.builder()
            .normalizeWhitespace()
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      " - | Flood \t Plains        | Flood-Plains ",
      " - | Deep \s  \t  \s Valley | Deep-Valley  ",
      " _ | Flood \t Plains        | Flood_Plains ",
      " _ | Deep \s  \t  \s Valley | Deep_Valley  ",
      " ~ | Flood \t Plains        | Flood~Plains ",
      " ~ | Deep \s  \t  \s Valley | Deep~Valley  ",
  }, delimiter = '|')
  void should_parse_to_normalized_and_converted_whitespace(
      final char convertTo, final String value, final String expected) {

    final TypeParser typeParser =
        TypeParser.builder()
            .normalizeAndConvertWhitespaceTo(convertTo)
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      " .. | Flood \t Plains        | Flood..Plains ",
      " .. | Deep \s  \t  \s Valley | Deep..Valley  ",
      " <> | Flood \t Plains        | Flood<>Plains ",
      " <> | Deep \s  \t  \s Valley | Deep<>Valley  ",
  }, delimiter = '|')
  void should_parse_to_normalized_and_converted_whitespace(
      final String convertTo, final String value, final String expected) {

    final TypeParser typeParser =
        TypeParser.builder()
            .normalizeAndConvertWhitespaceTo(convertTo)
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(expected);
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "Flood \t Plains",
      "Deep \s  \t  \s Valley",
  })
  void should_parse_to_preserved_whitespace(final String value) {

    final TypeParser typeParser =
        TypeParser.builder()
            .preserveWhitespace()
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(value);
  }

  @ParameterizedTest
  @CsvSource(value = {
      " - | Flood \t Plains      | Flood---Plains    ",
      " - | Deep \s \t \s Valley | Deep-------Valley ",
      " _ | Flood \t Plains      | Flood___Plains    ",
      " _ | Deep \s \t \s Valley | Deep_______Valley ",
      " ~ | Flood \t Plains      | Flood~~~Plains    ",
      " ~ | Deep \s \t \s Valley | Deep~~~~~~~Valley ",
  }, delimiter = '|')
  void should_parse_to_preserved_and_converted_whitespace(
      final char convertTo, final String value, final String expected) {

    final TypeParser typeParser =
        TypeParser.builder()
            .preserveAndConvertWhitespaceTo(convertTo)
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      " .. | Flood \t Plains      | Flood......Plains        ",
      " .. | Deep \s \t \s Valley | Deep..............Valley ",
      " <> | Flood \t Plains      | Flood<><><>Plains        ",
      " <> | Deep \s \t \s Valley | Deep<><><><><><><>Valley ",
  }, delimiter = '|')
  void should_parse_to_preserved_and_converted_whitespace(
      final String convertTo, final String value, final String expected) {

    final TypeParser typeParser =
        TypeParser.builder()
            .preserveAndConvertWhitespaceTo(convertTo)
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(expected);
  }

}
