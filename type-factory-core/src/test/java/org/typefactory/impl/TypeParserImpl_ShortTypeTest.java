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

import java.io.Serial;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.typefactory.ShortType;
import org.typefactory.TypeParser;

class TypeParserImpl_ShortTypeTest {

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
      -32768 | -32768 | -32768
      -55 66 | -5566  | -5566
      -44    | -44    | -44
      -1     | -1     | -1
      0      | 0      | 0
      ' 0 '  | 0      | 0
      1      | 1      | 1
      11     | 11     | 11
      ' 22 ' | 22     | 22
      33 44  | 3344   | 3344
      32767  | 32767  | 32767
      """, delimiter = '|', nullValues = "null")
  void parseToShortType_withPreserveNullAndEmpty_returnsAsExpected(
      final String value, final Short expectedValue, final String expectedString) {

    final var parser = TypeParser.builder()
        .preserveNullAndEmpty()
        .removeAllWhitespace()
        .acceptHyphenAndConvertAllDashesToHyphen()
        .acceptDigits0to9()
        .build();

    final var actual = parser.parseToShortType(value, SomeShortType::new);

    assertThatObject(actual).isNotNull();
    assertThatObject(actual.value()).isEqualTo(expectedValue);
    assertThatObject(actual).hasToString(expectedString);
  }

  static class SomeShortType extends ShortType {

    @Serial
    private static final long serialVersionUID = -4972685693079333504L;

    public SomeShortType(Short value) {
      super(value);
    }
  }
}
