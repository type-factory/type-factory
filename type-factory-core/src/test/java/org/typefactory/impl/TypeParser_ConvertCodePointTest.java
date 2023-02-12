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
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.TypeParser;
import org.typefactory.testutils.CodePointConverter;

class TypeParser_ConvertCodePointTest {

  @ParameterizedTest
  @CsvSource(textBlock = """
      1       | a | z | 1
      11      | a | z | 11
      12      | a | z | 12
      a       | a | z | z
      aa      | a | z | zz
      abcdef1 | a | z | zbcdef1
      abc-aba | a | z | zbc-zbz
      abc-123 | a | z | zbc-123
      1       | 1 | z | z
      11      | 1 | z | zz
      12      | 1 | z | z2
      a       | 1 | z | a
      aa      | 1 | z | aa
      abcdef1 | 1 | z | abcdefz
      abc-aba | 1 | z | abc-aba
      abc-123 | 1 | z | abc-z23
      """, delimiter = '|')
  void addCodePointConversion_fromCodePointToCodePointReturnsAsExpected(
      final String input,
      @ConvertWith(CodePointConverter.class) final int fromCodePoint,
      @ConvertWith(CodePointConverter.class) final int toCodePoint,
      final String expected) {

    final var typeParser = TypeParser.builder()
        .acceptCodePoints(input.codePoints().toArray())
        .convertCodePoint(fromCodePoint, toCodePoint)
        .build();

    final String actual = typeParser.parseToString(input);

    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      1       | a | zY | 1
      11      | a | zY | 11
      12      | a | zY | 12
      a       | a | zY | zY
      aa      | a | zY | zYzY
      abcdef1 | a | zY | zYbcdef1
      abc-aba | a | zY | zYbc-zYbzY
      abc-123 | a | zY | zYbc-123
      1       | 1 | zY | zY
      11      | 1 | zY | zYzY
      12      | 1 | zY | zY2
      a       | 1 | zY | a
      aa      | 1 | zY | aa
      abcdef1 | 1 | zY | abcdefzY
      abc-aba | 1 | zY | abc-aba
      abc-123 | 1 | zY | abc-zY23
      """, delimiter = '|')
  void addCodePointConversion_fromCodePointToCharSequenceReturnsAsExpected(
      final String input,
      @ConvertWith(CodePointConverter.class) final int fromCodePoint,
      final String toCharSequence,
      final String expected) {

    final var typeParser = TypeParser.builder()
        .acceptCodePoints(input.codePoints().toArray())
        .convertCodePoint(fromCodePoint, toCharSequence)
        .build();

    final String actual = typeParser.parseToString(input);

    assertThat(actual).isEqualTo(expected);
  }
}
