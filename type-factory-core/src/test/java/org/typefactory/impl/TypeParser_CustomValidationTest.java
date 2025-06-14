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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.InvalidValueException;
import org.typefactory.MessageCode;
import org.typefactory.TypeParser;

class TypeParser_CustomValidationTest extends AbstractTypeParserTest {

  @ParameterizedTest
  @CsvSource(textBlock = """
      - | ABC-012         | abc-012
      - | XYZ-789         | xyz-789
      . | Example.Com     | example.com
      . | www.example.com | www.example.com
      """, delimiter = '|')
  void parserWithCustomValidationShouldSuccessfullyParse(
      final String mustContain, final String value, final String expected) {

    final TypeParser typeParser =
        TypeParser.builder()
            .messageCode(MessageCode.of("must.conform.to.format", "Must conform to format."))
            .acceptLettersAtoZ()
            .acceptDigits0to9()
            .acceptChars('-', '.')
            .toLowerCase()
            .customValidator(s -> s.contains(mustContain))
            .build();

    final String actual = typeParser.parseToString(value);
    assertThat(actual).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      _ | ABC-ABC         | Invalid value - does not pass custom validation criteria.
      ~ | XYZ_789         | Invalid value - does not pass custom validation criteria.
      . | Example-Com     | Invalid value - does not pass custom validation criteria.
      . | www-example-com | Invalid value - does not pass custom validation criteria.
      """, delimiter = '|')
  void parserWithRegexShouldThrowExceptionWhileParsing(
      final String mustContain, final String value, final String expectedExceptionMessage) {

    final TypeParser typeParser =
        TypeParser.builder()
            .messageCode(MessageCode.of("must.conform.to.format", "Must conform to format."))
            .acceptLettersAtoZ()
            .acceptDigits0to9()
            .acceptChars('_', '-', '.')
            .toLowerCase()
            .customValidator(s -> s.contains(mustContain))
            .build();

    assertThatThrownBy(() -> typeParser.parseToString(value))
        .isInstanceOf(InvalidValueException.class)
        .hasMessage("Must conform to format. " + expectedExceptionMessage)
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedExceptionMessage);
  }
}
