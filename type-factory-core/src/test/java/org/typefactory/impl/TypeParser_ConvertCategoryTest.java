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
import org.typefactory.Category;
import org.typefactory.TypeParser;
import org.typefactory.testutils.CodePointConverter;

class TypeParser_ConvertCategoryTest {

  @ParameterizedTest
  @CsvSource(textBlock = """
      1       | LETTER               | z | 1
      a       | LETTER               | z | z
      abcdefg | LETTER               | z | zzzzzzz
      abc-efg | LETTER               | z | zzz-zzz
      abc-123 | LETTER               | z | zzz-123
      1       | UPPERCASE_LETTER     | z | 1
      a       | UPPERCASE_LETTER     | z | a
      A       | UPPERCASE_LETTER     | z | z
      AbcdefG | UPPERCASE_LETTER     | z | zbcdefz
      aBc-eFg | UPPERCASE_LETTER     | z | azc-ezg
      aBc-123 | UPPERCASE_LETTER     | z | azc-123
      1       | DECIMAL_DIGIT_NUMBER | z | z
      a       | DECIMAL_DIGIT_NUMBER | z | a
      A       | DECIMAL_DIGIT_NUMBER | z | A
      Abcd123 | DECIMAL_DIGIT_NUMBER | z | Abcdzzz
      aBc-eFg | DECIMAL_DIGIT_NUMBER | z | aBc-eFg
      aBc-123 | DECIMAL_DIGIT_NUMBER | z | aBc-zzz
      """, delimiter = '|')
  void addCategoryConversion_convertCategoryToCharReturnsAsExpected(
      final String input,
      final Category category,
      final char toChar,
      final String expected) {

    final var typeParser = TypeParser.builder()
        .acceptCodePoints(input.codePoints().toArray())
        .convertAnyInCategory(category, toChar)
        .build();

    final String actual = typeParser.parseToString(input);

    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      1       | LETTER               | z | 1
      a       | LETTER               | z | z
      abcdefg | LETTER               | z | zzzzzzz
      abc-efg | LETTER               | z | zzz-zzz
      abc-123 | LETTER               | z | zzz-123
      1       | UPPERCASE_LETTER     | z | 1
      a       | UPPERCASE_LETTER     | z | a
      A       | UPPERCASE_LETTER     | z | z
      AbcdefG | UPPERCASE_LETTER     | z | zbcdefz
      aBc-eFg | UPPERCASE_LETTER     | z | azc-ezg
      aBc-123 | UPPERCASE_LETTER     | z | azc-123
      1       | DECIMAL_DIGIT_NUMBER | z | z
      a       | DECIMAL_DIGIT_NUMBER | z | a
      A       | DECIMAL_DIGIT_NUMBER | z | A
      Abcd123 | DECIMAL_DIGIT_NUMBER | z | Abcdzzz
      aBc-eFg | DECIMAL_DIGIT_NUMBER | z | aBc-eFg
      aBc-123 | DECIMAL_DIGIT_NUMBER | z | aBc-zzz
      """, delimiter = '|')
  void addCategoryConversion_convertCategoryToCodePointReturnsAsExpected(
      final String input,
      final Category category,
      @ConvertWith(CodePointConverter.class) final int toCodePoint,
      final String expected) {

    final var typeParser = TypeParser.builder()
        .acceptCodePoints(input.codePoints().toArray())
        .convertAnyInCategory(category, toCodePoint)
        .build();

    final String actual = typeParser.parseToString(input);

    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      1       | LETTER               | zZ | 1
      a       | LETTER               | zZ | zZ
      abcdefg | LETTER               | zZ | zZzZzZzZzZzZzZ
      abc-efg | LETTER               | zZ | zZzZzZ-zZzZzZ
      abc-123 | LETTER               | zZ | zZzZzZ-123
      1       | UPPERCASE_LETTER     | zZ | 1
      a       | UPPERCASE_LETTER     | zZ | a
      A       | UPPERCASE_LETTER     | zZ | zZ
      AbcdefG | UPPERCASE_LETTER     | zZ | zZbcdefzZ
      aBc-eFg | UPPERCASE_LETTER     | zZ | azZc-ezZg
      aBc-123 | UPPERCASE_LETTER     | zZ | azZc-123
      1       | DECIMAL_DIGIT_NUMBER | zZ | zZ
      a       | DECIMAL_DIGIT_NUMBER | zZ | a
      A       | DECIMAL_DIGIT_NUMBER | zZ | A
      Abcd123 | DECIMAL_DIGIT_NUMBER | zZ | AbcdzZzZzZ
      aBc-eFg | DECIMAL_DIGIT_NUMBER | zZ | aBc-eFg
      aBc-123 | DECIMAL_DIGIT_NUMBER | zZ | aBc-zZzZzZ
      """, delimiter = '|')
  void addCategoryConversion_convertCategoryToCharSequenceReturnsAsExpected(
      final String input, final Category category, final String toCharSequence, final String expected) {

    final var typeParser = TypeParser.builder()
        .acceptCodePoints(input.codePoints().toArray())
        .convertAnyInCategory(category, toCharSequence)
        .build();

    final String actual = typeParser.parseToString(input);

    assertThat(actual).isEqualTo(expected);
  }
}
