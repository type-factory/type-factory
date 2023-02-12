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
import org.typefactory.Subset;
import org.typefactory.TypeParser;
import org.typefactory.testutils.CodePointArrayConverter;
import org.typefactory.testutils.CodePointConverter;

class TypeParser_ConvertSubsetTest {

  @ParameterizedTest
  @CsvSource(textBlock = """
      1       | [a,2,Σ] | z | 1
      11      | [a,2,Σ] | z | 11
      12      | [a,2,Σ] | z | 1z
      13      | [a,2,Σ] | z | 13
      a       | [a,2,Σ] | z | z
      aa      | [a,2,Σ] | z | zz
      abcdef1 | [a,2,Σ] | z | zbcdef1
      abcdef2 | [a,2,Σ] | z | zbcdefz
      abc-aba | [a,2,Σ] | z | zbc-zbz
      abc-123 | [a,2,Σ] | z | zbc-1z3
      abc-ΔΣΩ | [a,2,Σ] | z | zbc-ΔzΩ
      abc-ΔΣ2 | [a,2,Σ] | z | zbc-Δzz
      1       | [1,2,Σ] | z | z
      11      | [1,2,Σ] | z | zz
      12      | [1,2,Σ] | z | zz
      13      | [1,2,Σ] | z | z3
      a       | [1,2,Σ] | z | a
      aa      | [1,2,Σ] | z | aa
      abcdef1 | [1,2,Σ] | z | abcdefz
      abcdef2 | [1,2,Σ] | z | abcdefz
      abc-aba | [1,2,Σ] | z | abc-aba
      abc-123 | [1,2,Σ] | z | abc-zz3
      abc-ΔΣΩ | [1,2,Σ] | z | abc-ΔzΩ
      abc-ΔΣ2 | [1,2,Σ] | z | abc-Δzz
      """, delimiter = '|')
  void addCodePointConversions_fromSubsetToCharReturnsAsExpected(
      final String input,
      @ConvertWith(CodePointArrayConverter.class) final int[] fromCodePoints,
      final char toChar,
      final String expected) {

    final var subset = Subset.builder()
        .includeCodePoints(fromCodePoints)
        .build();

    final var typeParser = TypeParser.builder()
        .acceptCodePoints(input.codePoints().toArray())
        .convertAnyInSubset(subset, toChar)
        .build();

    final String actual = typeParser.parseToString(input);

    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      1       | [a,2,Σ] | z | 1
      11      | [a,2,Σ] | z | 11
      12      | [a,2,Σ] | z | 1z
      13      | [a,2,Σ] | z | 13
      a       | [a,2,Σ] | z | z
      aa      | [a,2,Σ] | z | zz
      abcdef1 | [a,2,Σ] | z | zbcdef1
      abcdef2 | [a,2,Σ] | z | zbcdefz
      abc-aba | [a,2,Σ] | z | zbc-zbz
      abc-123 | [a,2,Σ] | z | zbc-1z3
      abc-ΔΣΩ | [a,2,Σ] | z | zbc-ΔzΩ
      abc-ΔΣ2 | [a,2,Σ] | z | zbc-Δzz
      1       | [1,2,Σ] | z | z
      11      | [1,2,Σ] | z | zz
      12      | [1,2,Σ] | z | zz
      13      | [1,2,Σ] | z | z3
      a       | [1,2,Σ] | z | a
      aa      | [1,2,Σ] | z | aa
      abcdef1 | [1,2,Σ] | z | abcdefz
      abcdef2 | [1,2,Σ] | z | abcdefz
      abc-aba | [1,2,Σ] | z | abc-aba
      abc-123 | [1,2,Σ] | z | abc-zz3
      abc-ΔΣΩ | [1,2,Σ] | z | abc-ΔzΩ
      abc-ΔΣ2 | [1,2,Σ] | z | abc-Δzz
      """, delimiter = '|')
  void addCodePointConversions_fromSubsetToCodePointReturnsAsExpected(
      final String input,
      @ConvertWith(CodePointArrayConverter.class) final int[] fromCodePoints,
      @ConvertWith(CodePointConverter.class) final int toCodePoint,
      final String expected) {

    final var subset = Subset.builder()
        .includeCodePoints(fromCodePoints)
        .build();

    final var typeParser = TypeParser.builder()
        .acceptCodePoints(input.codePoints().toArray())
        .convertAnyInSubset(subset, toCodePoint)
        .build();

    final String actual = typeParser.parseToString(input);

    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      1       | [a,2,Σ] | zΨ | 1
      11      | [a,2,Σ] | zΨ | 11
      12      | [a,2,Σ] | zΨ | 1zΨ
      13      | [a,2,Σ] | zΨ | 13
      a       | [a,2,Σ] | zΨ | zΨ
      aa      | [a,2,Σ] | zΨ | zΨzΨ
      abcdef1 | [a,2,Σ] | zΨ | zΨbcdef1
      abcdef2 | [a,2,Σ] | zΨ | zΨbcdefzΨ
      abc-aba | [a,2,Σ] | zΨ | zΨbc-zΨbzΨ
      abc-123 | [a,2,Σ] | zΨ | zΨbc-1zΨ3
      abc-ΔΣΩ | [a,2,Σ] | zΨ | zΨbc-ΔzΨΩ
      abc-ΔΣ2 | [a,2,Σ] | zΨ | zΨbc-ΔzΨzΨ
      1       | [1,2,Σ] | zΨ | zΨ
      11      | [1,2,Σ] | zΨ | zΨzΨ
      12      | [1,2,Σ] | zΨ | zΨzΨ
      13      | [1,2,Σ] | zΨ | zΨ3
      a       | [1,2,Σ] | zΨ | a
      aa      | [1,2,Σ] | zΨ | aa
      abcdef1 | [1,2,Σ] | zΨ | abcdefzΨ
      abcdef2 | [1,2,Σ] | zΨ | abcdefzΨ
      abc-aba | [1,2,Σ] | zΨ | abc-aba
      abc-123 | [1,2,Σ] | zΨ | abc-zΨzΨ3
      abc-ΔΣΩ | [1,2,Σ] | zΨ | abc-ΔzΨΩ
      abc-ΔΣ2 | [1,2,Σ] | zΨ | abc-ΔzΨzΨ
      """, delimiter = '|')
  void addCodePointConversions_fromSubsetToCharSequenceReturnsAsExpected(
      final String input,
      @ConvertWith(CodePointArrayConverter.class) final int[] fromCodePoints,
      final String toCharSequence,
      final String expected) {

    final var subset = Subset.builder()
        .includeCodePoints(fromCodePoints)
        .build();

    final var typeParser = TypeParser.builder()
        .acceptCodePoints(input.codePoints().toArray())
        .convertAnyInSubset(subset, toCharSequence)
        .build();

    final String actual = typeParser.parseToString(input);

    assertThat(actual).isEqualTo(expected);
  }

}
