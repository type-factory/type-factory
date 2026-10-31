/*
 * Copyright © 2021-2026 Evan Toliopoulos (typefactory.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatObject;

import java.text.Normalizer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.typefactory.Subset;
import org.typefactory.ShortType;
import org.typefactory.TypeParser;
import org.typefactory.testutils.CodePointSequenceConverter;

class TypeParserImpl_Test {

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = "  ")
  void parseToShortType_withPreserveNullAndEmpty_returnsNullForNullValue(final String value) {

    final var parser = TypeParser.builder()
        .preserveNullAndEmpty()
        .removeAllWhitespace()
        .build();

    final var actual = parser.parseToShortType(value, SomeShortType::new);

    assertThatObject(actual).isNull();
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = "  ")
  void parseToShortType_withConvertEmptyToNull_returnsNullForNullValue(final String value) {

    final var parser = TypeParser.builder()
        .convertEmptyToNull()
        .removeAllWhitespace()
        .build();

    final var actual = parser.parseToShortType(value, SomeShortType::new);

    assertThatObject(actual).isNull();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      ''             | 0 | 0
      ' '            | 0 | 0
      '  '           | 0 | 0
      '\t '          | 0 | 0
      '\t  '         | 0 | 0
      'A'            | 0 | 1
      'AA'           | 0 | 2
      'BB '          | 0 | 2
      'CC\t '        | 0 | 2
      ' DD '         | 1 | 3
      ' \tEE\t '     | 2 | 4
      '\t \tFF\t \t' | 3 | 5
      """, delimiter = '|', nullValues = "null")
  void parseToShortType_withPreserveNullAndEmpty_returnsAsExpected(
      @ConvertWith(CodePointSequenceConverter.class) final int[] codePointSequence,
      final int expectedInclusiveStartIndex, final int expectedExclusiveEndIndex) {

    final var actualEndIndex = TypeParserImpl.exclusiveEndIndexIgnoringTrailingWhitespace(codePointSequence, codePointSequence.length);
    final var actualStartIndex = TypeParserImpl.inclusiveStartIndexIgnoringLeadingWhitespace(codePointSequence, actualEndIndex);

    assertThat(actualEndIndex).isEqualTo(expectedExclusiveEndIndex);
    assertThat(actualStartIndex).isEqualTo(expectedInclusiveStartIndex);
  }

  @Test
  void parseToString_withConverterReturningNullCodePointSequence_returnsEmptyString() {
    final Converter converter = new Converter() {
      @Override
      public boolean isEmpty() {
        return false;
      }

      @Override
      public int getMaxConvertedLength() {
        return 0;
      }

      @Override
      public ConverterResults createConverterResults() {
        return new ConverterResults() {
          @Override
          public int getConvertFromIndex() {
            return 0;
          }

          @Override
          public int[] getConvertToCodePointSequence() {
            return null;
          }
        };
      }

      @Override
      public boolean codePointConversionIsRequired(
          final int currentCodePoint, final int currentIndex, final ConverterResults converterResults) {
        return true;
      }
    };

    final var parser = new TypeParserImpl(
        String.class,
        Factory.parserMessageCode("test", "test"),
        TargetCase.PRESERVE_CASE,
        WhiteSpace.FORBID_WHITESPACE,
        NullHandling.PRESERVE_NULL_AND_EMPTY,
        Normalizer.Form.NFC,
        0,
        64,
        -1,
        null,
        null,
        Subset.builder().includeCodePoint('a').build(),
        converter);

    assertThat(parser.parseToString("a")).isEmpty();
  }


  static class SomeShortType extends ShortType {
    public SomeShortType(Short value) {
      super(value);
    }
  }
}
