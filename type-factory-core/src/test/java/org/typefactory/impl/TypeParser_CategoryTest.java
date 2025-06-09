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
import org.typefactory.Category;
import org.typefactory.InvalidValueException;
import org.typefactory.MessageCode;
import org.typefactory.TypeParser;

class TypeParser_CategoryTest extends AbstractTypeParserTest {

  @ParameterizedTest
  @CsvSource(value = {
      "CAT",
      "DEER",
      "ΤΙΓΡΗ", // Greek
      "ЖИРАФА", // Russian
  }, delimiter = '|')
  void should_parse_accepting_only_uppercase_letters(final String value) {

    final TypeParser typeParser =
        TypeParser.builder()
            .acceptUnicodeCategory(Category.UPPERCASE_LETTER)
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).isEqualTo(value);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      Cat    | Invalid value - invalid character 'a' U+0061 LATIN SMALL LETTER A.
      Deer   | Invalid value - invalid character 'e' U+0065 LATIN SMALL LETTER E.
      Τίγρη  | Invalid value - invalid character 'ί' U+03AF GREEK SMALL LETTER IOTA WITH TONOS.
      Жирафа | Invalid value - invalid character 'и' U+0438 CYRILLIC SMALL LETTER I.
      """, delimiter = '|')
  void should_throw_exception_when_value_contains_lowercase_letters(
      final String value, final String expectedParserErrorMessage) {

    final TypeParser typeParser =
        TypeParser.builder()
            .messageCode(MessageCode.of("some.type.must.be.uppercase.alpha.characters", "Some type must be uppercase alpha characters."))
            .acceptUnicodeCategory(Category.UPPERCASE_LETTER)
            .build();

    Assertions.assertThatThrownBy(() -> typeParser.parseToString(value))
        .isInstanceOf(InvalidValueException.class)
        .hasMessage("Some type must be uppercase alpha characters. " + expectedParserErrorMessage)
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedParserErrorMessage);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "cat",
      "deer",
      "τίγρη", // Greek
      "жирафа", // Russian
  }, delimiter = '|')
  void should_parse_accepting_only_lowercase_letters(
      final String value) {

    final TypeParser typeParser =
        TypeParser.builder()
            .acceptUnicodeCategory(Category.LOWERCASE_LETTER)
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).isEqualTo(value);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      Cat    | Invalid value - invalid character 'C' U+0043 LATIN CAPITAL LETTER C.
      Deer   | Invalid value - invalid character 'D' U+0044 LATIN CAPITAL LETTER D.
      Τίγρη  | Invalid value - invalid character 'Τ' U+03A4 GREEK CAPITAL LETTER TAU.
      Жирафа | Invalid value - invalid character 'Ж' U+0416 CYRILLIC CAPITAL LETTER ZHE.
      """, delimiter = '|')
  void should_throw_exception_when_value_contains_uppercase_letters(
      final String value, final String expectedParserErrorMessage) {

    final TypeParser typeParser =
        TypeParser.builder()
            .messageCode(MessageCode.of("some.type.must.be.uppercase.alpha.characters", "Some type must be uppercase alpha characters."))
            .acceptUnicodeCategory(Category.LOWERCASE_LETTER)
            .build();

    Assertions.assertThatThrownBy(() -> typeParser.parseToString(value))
        .isInstanceOf(InvalidValueException.class)
        .hasMessage("Some type must be uppercase alpha characters. " + expectedParserErrorMessage)
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedParserErrorMessage);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "Cat",
      "Deer",
      "Τίγρη", // Greek
      "Жирафа", // Russian
  }, delimiter = '|')
  void should_parse_accepting_only_letters(
      final String value) {

    final TypeParser typeParser =
        TypeParser.builder()
            .acceptUnicodeCategory(Category.LETTER)
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).isEqualTo(value);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      Cat\uD83D\uDC08    | Invalid value - invalid character '\uD83D\uDC08' U+01F408 CAT.          | 🐈 cat emoji
      Deer\uD83E\uDD8C   | Invalid value - invalid character '\uD83E\uDD8C' U+01F98C DEER.         | 🦌 deer emoji
      Τίγρη\uD83D\uDC05  | Invalid value - invalid character '\uD83D\uDC05' U+01F405 TIGER.        | 🐅 tiger emoji
      Жирафа\uD83E\uDD92 | Invalid value - invalid character '\uD83E\uDD92' U+01F992 GIRAFFE FACE. | 🦒 giraffe emoji
      """, delimiter = '|')
  void should_throw_exception_when_value_contains_letters(
      final String value, final String expectedParserErrorMessage, final String ignoredDescription) {

    final TypeParser typeParser =
        TypeParser.builder()
            .messageCode(MessageCode.of("some.type.must.be.uppercase.alpha.characters", "Some type must be uppercase alpha characters."))
            .acceptUnicodeCategory(Category.LETTER)
            .build();

    Assertions.setMaxStackTraceElementsDisplayed(16);

    Assertions.assertThatThrownBy(() -> typeParser.parseToString(value))
        .isInstanceOf(InvalidValueException.class)
        .hasMessage("Some type must be uppercase alpha characters. " + expectedParserErrorMessage)
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedParserErrorMessage);
  }

}
