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

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.TypeParser;

class TypeParser_ReplaceInvalidCharactersTest {

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      VALUE                                             | IS_VALID_VALUE | INVALID_CODEPOINTS | EXPECTED_VALUE
      abcdefg                                           | true           |                    | abcdefg
      abc1234                                           | true           |                    | abc1234
      abc-123                                           | false          | -                  | abc�123
      123_abc                                           | false          | _                  | 123�abc
      $bcdefg                                           | false          | $                  | �bcdefg
      abcdef$                                           | false          | $                  | abcdef�
      abcd$fg                                           | false          | $                  | abcd�fg
      abcd$#%                                           | false          | $#%                | abcd���
      abc defg                                          | false          | ' '                | abc�defg
      $(".classname").each(function(){alert("Boo!");}); | false          | $()!".{};          | ����classname���each�function���alert��Boo�������
      """, delimiter = '|', useHeadersInDisplayName = true)
  void parseToStringReplacingInvalidCharacters_returnsAsExpected(
      final String value,
      final boolean isValidValue,
      final String expectedInvalidCodePoints,
      final String expectedValue) {

    final var typeParser = TypeParser.builder()
        .acceptAllUnicodeLetters()
        .acceptAllUnicodeDecimalDigits()
        .build();

    final var parseResult = typeParser.parseToStringReplacingInvalidCharacters(value);
    assertThat(parseResult).isNotNull();
    assertThat(parseResult.parsedValueWasValid()).isEqualTo(isValidValue);
    assertThat(parseResult.parsedValueWasInvalid()).isNotEqualTo(isValidValue);
    assertThat(parseResult.parsedValue()).isEqualTo(expectedValue);
    if (expectedInvalidCodePoints != null) {
      assertThat(parseResult.invalidCodePoints()).containsExactlyInAnyOrder(expectedInvalidCodePoints.codePoints().toArray());
    }
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      VALUE                                             | IS_VALID_VALUE | INVALID_CODEPOINTS | EXPECTED_VALUE
      abcdefg                                           | true           |                    | aBcdefg
      abc1234                                           | true           |                    | aBc1234
      abc-123                                           | false          | -                  | aBc�123
      123_abc                                           | false          | _                  | 123�aBc
      $bcdefg                                           | false          | $                  | �Bcdefg
      abcdef$                                           | false          | $                  | aBcdef�
      abcd$fg                                           | false          | $                  | aBcd�fg
      abcd$#%                                           | false          | $#%                | aBcd���
      abc defg                                          | false          | ' '                | aBc�defg
      $(".classname").each(function(){alert("Boo!");}); | false          | $()!".{};          | ����classname���each�function���alert��Boo�������
      """, delimiter = '|', useHeadersInDisplayName = true)
  void parseToStringReplacingInvalidCharacters_returnsAsExpectedWithCharacterConversion(
      final String value,
      final boolean isValidValue,
      final String expectedInvalidCodePoints,
      final String expectedValue) {

    final var typeParser = TypeParser.builder()
        .convertChar('b', 'B')
        .acceptAllUnicodeLetters()
        .acceptAllUnicodeDecimalDigits()
        .build();

    final var parseResult = typeParser.parseToStringReplacingInvalidCharacters(value);
    assertThat(parseResult).isNotNull();
    assertThat(parseResult.parsedValueWasValid()).isEqualTo(isValidValue);
    assertThat(parseResult.parsedValueWasInvalid()).isNotEqualTo(isValidValue);
    assertThat(parseResult.parsedValue()).isEqualTo(expectedValue);
    if (expectedInvalidCodePoints != null) {
      assertThat(parseResult.invalidCodePoints()).containsExactlyInAnyOrder(expectedInvalidCodePoints.codePoints().toArray());
    }
  }
}
