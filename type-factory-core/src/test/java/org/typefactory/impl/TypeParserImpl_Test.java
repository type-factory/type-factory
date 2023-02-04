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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.StringType;
import org.typefactory.TypeParser;

class TypeParserImpl_Test {

  @Test
  void parseToStringType_withPreserveNullAndEmpty_returnsNullForNullValue() {

    final var parser = TypeParser.builder()
        .preserveNullAndEmpty()
        .build();

    final var actual = parser.parseToStringType(null, SomeStringType::new);

    assertThatObject(actual).isNull();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      ''   | ''   | ''
      ' '  | ' '  | ' '
      AA   | AA   | AA
      """, delimiter = '|', nullValues = "null")
  void parseToStringType_withPreserveNullAndEmpty_returnsAsExpected(
      final String value, final String expectedValue, final String expectedString) {

    final var parser = TypeParser.builder()
        .preserveNullAndEmpty()
        .preserveWhitespace()
        .acceptChars('A')
        .build();

    final var actual = parser.parseToStringType(value, SomeStringType::new);

    assertThatObject(actual).isNotNull();
    assertThatObject(actual.value()).isEqualTo(expectedValue);
    assertThatObject(actual).hasToString(expectedString);
  }

  static class SomeStringType extends StringType {
    public SomeStringType(String value) {
      super(value);
    }
  }
}
