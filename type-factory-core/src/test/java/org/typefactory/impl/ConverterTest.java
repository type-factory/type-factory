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
import org.typefactory.Subset;
import org.typefactory.Subset.SubsetBuilder;
import org.typefactory.impl.Converter.ConverterResults;
import org.typefactory.testutils.CodePointArrayConverter;
import org.typefactory.testutils.CodePointConverter;

class ConverterTest {

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

    final var converter = new ConverterBuilder()
        .addCategoryConversion(category, toChar)
        .build();

    final String actual = convert(input, converter);

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

    final var converter = new ConverterBuilder()
        .addCategoryConversion(category, toCodePoint)
        .build();

    final String actual = convert(input, converter);

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

    final var converter = new ConverterBuilder()
        .addCategoryConversion(category, toCharSequence)
        .build();

    final String actual = convert(input, converter);

    assertThat(actual).isEqualTo(expected);
  }

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
  void addCharConversion_fromCharToCharReturnsAsExpected(
      final String input,
      final char fromChar,
      final char toChar,
      final String expected) {

    final var converter = new ConverterBuilder()
        .addCharConversion(fromChar, toChar)
        .build();

    final String actual = convert(input, converter);

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
  void addCharConversion_fromCharToCharSequenceReturnsAsExpected(
      final String input,
      final char fromChar,
      final String toCharSequence,
      final String expected) {

    final var converter = new ConverterBuilder()
        .addCharConversion(fromChar, toCharSequence)
        .build();

    final String actual = convert(input, converter);

    assertThat(actual).isEqualTo(expected);
  }

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

    final var converter = new ConverterBuilder()
        .addCodePointConversion(fromCodePoint, toCodePoint)
        .build();

    final String actual = convert(input, converter);

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

    final var converter = new ConverterBuilder()
        .addCodePointConversion(fromCodePoint, toCharSequence)
        .build();

    final String actual = convert(input, converter);

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
  void addCodePointConversions_fromSubsetToCharReturnsAsExpected(
      final String input,
      @ConvertWith(CodePointArrayConverter.class) final int[] fromCodePoints,
      final char toChar,
      final String expected) {

    final var subset = Subset.builder()
        .includeCodePoints(fromCodePoints)
        .build();

    final var converter = new ConverterBuilder()
        .addCodePointConversions(subset, toChar)
        .build();

    final String actual = convert(input, converter);

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

    final var converter = new ConverterBuilder()
        .addCodePointConversions(subset, toCodePoint)
        .build();

    final String actual = convert(input, converter);

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

    final var converter = new ConverterBuilder()
        .addCodePointConversions(subset, toCharSequence)
        .build();

    final String actual = convert(input, converter);

    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      " abcdefg | abc | xyz | xyzdefg ",
      " abcdefg | bcd | xyz | axyzefg ",
      " abcdefg | efg | xyz | abcdxyz ",
  }, delimiter = '|')
  void addCharSequenceConversion_fromCharSequenceCharSequenceToCharSequenceReturnsAsExpected(
      final String input, 
      final String fromCharSequence, 
      final String toCharSequence, 
      final String expected) {

    final Converter converter = Converter.builder()
        .addCharSequenceConversion(fromCharSequence, toCharSequence)
        .build();

    final String actual = convert(input, converter);

    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      " semi-trailer    | s          | d           |              |           |                 |       | demi-trailer        ",
      " semi-trailer    | s          | d           | semi-trailer | lorry     |                 |       | lorry               ",
      " semi-trailer    | semi       | articulated |              |           |                 |       | articulated-trailer ",
      " semi-trailer    | semi       | articulated | semi-trailer | lorry     |                 |       | lorry               ",
      " semi-trailer    | semi       | articulated | trailer      | vehicle   |                 |       | articulated-vehicle ",
      " semi-trailer    | semi       | articulated | trailer      | vehicle   | semi-trailer    | lorry | lorry               ",
      " full stop light | full stop  | period      |              |           |                 |       | period light        ",
      " full stop light | full stop  | period      | l            | n         |                 |       | period night        ",
      " full stop light | stop light | red light   |              |           |                 |       | full red light      ",
      " full stop light | full stop  | period      | stop light   | red light |                 |       | period light        ",
      " full stop light | full stop  | period      | stop light   | red light | full stop light | skid  | skid                ",
  }, delimiter = '|')
  void convertIsSuccessful(
      final String input,
      final String fromCharSequence1, final String toCharSequence1,
      final String fromCharSequence2, final String toCharSequence2,
      final String fromCharSequence3, final String toCharSequence3,
      final String expected) {

    final Converter converter = Converter.builder()
        .addCharSequenceConversion(fromCharSequence1, toCharSequence1)
        .addCharSequenceConversion(fromCharSequence2, toCharSequence2)
        .addCharSequenceConversion(fromCharSequence3, toCharSequence3)
        .build();

    final String actual = convert(input, converter);

    assertThat(actual).isEqualTo(expected);
  }


  private String convert(final String input, Converter converter) {
    final int[] codePoints = input.codePoints().toArray();
    final StringBuilder s = new StringBuilder();
    final ConverterResults converterResults = converter.createConverterResults();
    for (int i = 0; i < codePoints.length; ++i) {
      int codePoint = codePoints[i];
      s.appendCodePoint(codePoint);
      if (converter.isCodePointConversionRequired(codePoint, s.length() - 1, converterResults)) {
        s.setLength(converterResults.getConvertFromIndex());
        final int[] toCodePointSequence = converterResults.getConvertToCodePointSequence();
        for (int j = 0; j < toCodePointSequence.length; ++j) {
          s.appendCodePoint(toCodePointSequence[j]);
        }
      }
      ;
    }
    return s.toString();
  }
}
