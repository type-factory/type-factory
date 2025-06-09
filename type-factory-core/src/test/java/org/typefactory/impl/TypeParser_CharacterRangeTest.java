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
import org.typefactory.MessageCode;
import org.typefactory.InvalidValueException;
import org.typefactory.TypeParser;

class TypeParser_CharacterRangeTest extends AbstractTypeParserTest {
  
  @ParameterizedTest
  @CsvSource(value = {
      "Café au lait                            | Invalid value - invalid character 'é' U+00E9 LATIN SMALL LETTER E WITH ACUTE.",
      "Apple-tart                              | Invalid value - invalid character '-' U+002D HYPHEN-MINUS.",
      "Apple tart with a really very long name | Invalid value - invalid white-space character U+0020 SPACE.",
  }, delimiter = '|')
  void should_throw_exception_when_invalid_character(final String value, final String expectedParserErrorMessage) {

    final TypeParser typeParser =
        TypeParser.builder()
            .messageCode(MessageCode.of("some.type.must.be.alpha.characters", "Some type must be alpha characters."))
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThatThrownBy(() -> typeParser.parseToString(value))
        .isInstanceOf(InvalidValueException.class)
        .hasMessage("Some type must be alpha characters. " + expectedParserErrorMessage)
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedParserErrorMessage);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "Τίγρη | Invalid value - invalid character 'ί' U+03AF GREEK SMALL LETTER IOTA WITH TONOS.", // Greek iota-with-tonos (diacritic) is not in the accepted character range
  }, delimiter = '|')
  void greek_range_should_throw_exception_when_invalid_character(final String value, final String expectedParserErrorMessage) {

    final TypeParser typeParser =
        TypeParser.builder()
            .messageCode(MessageCode.of("some.type.must.be.alpha.characters", "Some type must be alpha characters."))
            // These Greek alphabet character ranges don't include characters with diacritics
            .acceptCharRange('α', 'ω')
            .acceptCharRange('Α', 'Ω')
            .build();

    Assertions.assertThatThrownBy(() -> typeParser.parseToString(value))
        .isInstanceOf(InvalidValueException.class)
        .hasMessage("Some type must be alpha characters. " + expectedParserErrorMessage)
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedParserErrorMessage);
  }
}
