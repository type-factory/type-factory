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
import org.typefactory.MessageCode;
import org.typefactory.InvalidValueException;
import org.typefactory.TypeParser;

class TypeParser_MinMaxLengthTest extends AbstractTypeParserTest {
  

  @ParameterizedTest
  @ValueSource(strings = {
      "Fish",
      "Bird",
  })
  void should_parse_to_fixed_size(final String value) {

    final TypeParser typeParser =
        TypeParser.builder()
            .fixedSize(4)
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).isEqualTo(value);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "Ox|Invalid value - too short, minimum length is 4.",
      "Cat|Invalid value - too short, minimum length is 4.",
  }, delimiter = '|')
  void should_throw_exception_when_too_small(final String value, final String expectedParserErrorMessage) {

    final TypeParser typeParser =
        TypeParser.builder()
            .messageCode(MessageCode.of("some.type.must.be.4.alpha.characters", "Some type must be 4 alpha characters."))
            .fixedSize(4)
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThatThrownBy(() -> typeParser.parseToString(value))
        .isInstanceOf(InvalidValueException.class)
        .hasMessage("Some type must be 4 alpha characters. Invalid value - too short, minimum length is 4.")
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedParserErrorMessage);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "Tiger|Invalid value - too long, maximum length is 4.",
      "Donkey|Invalid value - too long, maximum length is 4.",
      "Mammoth|Invalid value - too long, maximum length is 4.",
  }, delimiter = '|')
  void should_throw_exception_when_too_large(final String value, final String expectedParserErrorMessage) {

    final TypeParser typeParser =
        TypeParser.builder()
            .messageCode(MessageCode.of("some.type.must.be.4.alpha.characters", "Some type must be 4 alpha characters."))
            .fixedSize(4)
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThatThrownBy(() -> typeParser.parseToString(value))
        .isInstanceOf(InvalidValueException.class)
        .hasMessage("Some type must be 4 alpha characters. Invalid value - too long, maximum length is 4.")
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedParserErrorMessage);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE | MIN_LENGTH | CHARACTERS_TO_REMOVE | EXPECTED_VALUE
      -     | 0          | -                    | ''
      --    | 0          | -                    | ''
      -A-   | 1          | -                    | A
      A-B   | 2          | -                    | AB
      A--B  | 2          | -                    | AB
      A-B-  | 2          | -                    | AB
      -A-B- | 2          | -                    | AB
      _     | 0          | _                    | ''
      __    | 0          | _                    | ''
      A_B   | 2          | _                    | AB
      """, delimiter = '|', useHeadersInDisplayName = true)
  void minlength_handleWhenCharactersRemoved(
      final String value, final int minSize, final char characterToRemove, final String expectedValue) {

    final TypeParser typeParser =
        TypeParser.builder()
            .messageCode(MessageCode.of("some.error.message", "Some error message."))
            .minSize(minSize)
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .acceptChars('-', '_')
            .removeAllChars(characterToRemove)
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).isEqualTo(expectedValue);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE | MIN_LENGTH | CHARACTERS_TO_REMOVE | EXPECTED_MESSAGE
      -     | 1          | -                    | Invalid value - too short, minimum length is 1.
      --    | 1          | -                    | Invalid value - too short, minimum length is 1.
      A-B   | 3          | -                    | Invalid value - too short, minimum length is 3.
      A--B  | 3          | -                    | Invalid value - too short, minimum length is 3.
      A-B-  | 3          | -                    | Invalid value - too short, minimum length is 3.
      -A-B- | 3          | -                    | Invalid value - too short, minimum length is 3.
      _     | 1          | _                    | Invalid value - too short, minimum length is 1.
      __    | 1          | _                    | Invalid value - too short, minimum length is 1.
      A_B   | 3          | _                    | Invalid value - too short, minimum length is 3.
      """, delimiter = '|', useHeadersInDisplayName = true)
  void minlength_handleWhenCharactersRemovedAndThrowsExceptionWhenTooSmall(
      final String value, final int minSize, final char characterToRemove, final String expectedParserErrorMessage) {

    final TypeParser typeParser =
        TypeParser.builder()
            .messageCode(MessageCode.of("some.error.message", "Some error message."))
            .minSize(minSize)
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .acceptChars('-', '_')
            .removeAllChars(characterToRemove)
            .build();

    Assertions.assertThatThrownBy(() -> typeParser.parseToString(value))
        .isInstanceOf(InvalidValueException.class)
        .hasMessage("Some error message. " + expectedParserErrorMessage)
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedParserErrorMessage);
  }

}
