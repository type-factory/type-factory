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

import static org.assertj.core.api.Assertions.assertThatObject;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.typefactory.LongType;
import org.typefactory.TypeParser;

class TypeParserImpl_LongTypeTest {

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = "  ")
  void parseToLongType_withPreserveNullAndEmpty_returnsNullForNullValue(final String value) {

    final var parser = TypeParser.builder()
        .preserveNullAndEmpty()
        .removeAllWhitespace()
        .build();

    final var actual = parser.parseToLongType(value, SomeLongType::new);

    assertThatObject(actual).isNull();
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = "  ")
  void parseToLongType_withConvertEmptyToNull_returnsNullForNullValue(final String value) {

    final var parser = TypeParser.builder()
        .convertEmptyToNull()
        .removeAllWhitespace()
        .build();

    final var actual = parser.parseToLongType(value, SomeLongType::new);

    assertThatObject(actual).isNull();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      -9223372036854775808 | -9223372036854775808 | -9223372036854775808
      -2147483648          | -2147483648          | -2147483648
      -32768               | -32768               | -32768
      -55 66               | -5566                | -5566
      -44                  | -44                  | -44
      -1                   | -1                   | -1
      0                    | 0                    | 0
      ' 0 '                | 0                    | 0
      1                    | 1                    | 1
      11                   | 11                   | 11
      ' 22 '               | 22                   | 22
      33 44                | 3344                 | 3344
      32767                | 32767                | 32767
      2147483647           | 2147483647           | 2147483647
      9223372036854775807  | 9223372036854775807  | 9223372036854775807
      
      """, delimiter = '|', nullValues = "null")
  void parseToLongType_withPreserveNullAndEmpty_returnsAsExpected(
      final String value, final Long expectedValue, final String expectedString) {

    final var parser = TypeParser.builder()
        .preserveNullAndEmpty()
        .removeAllWhitespace()
        .acceptHyphenAndConvertAllDashesToHyphen()
        .acceptDigits0to9()
        .build();

    final var actual = parser.parseToLongType(value, SomeLongType::new);

    assertThatObject(actual).isNotNull();
    assertThatObject(actual.value()).isEqualTo(expectedValue);
    assertThatObject(actual).hasToString(expectedString);
  }

  static class SomeLongType extends LongType {
    public SomeLongType(Long value) {
      super(value);
    }
  }
}
