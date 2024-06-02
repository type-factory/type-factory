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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ParserMessageCodeImplTest {

  @ParameterizedTest
  @CsvSource(textBlock = """
      null | null | ''   | ''
      null | ''   | ''   | ''
      null | '  ' | ''   | ''
      null | AABB | ''   | AABB
      ''   | null | ''   | ''
      '  ' | null | ''   | ''
      AABB | null | AABB | ''
      AABB | CCDD | AABB | CCDD
      """, delimiter = '|', nullValues = "null")
  void constructor_instantiatesAsExpected(
      final String messageCode, final String defaultMessage,
      final String expectedMessageCode, final String expectedDefaultMessage) {

    final var actual = new ParserMessageCodeImpl(messageCode, defaultMessage);

    assertThat(actual.code())
        .isNotNull()
        .isEqualTo(expectedMessageCode);

    assertThat(actual.defaultMessage())
        .isNotNull()
        .isEqualTo(expectedDefaultMessage);
  }

  @Test
  void equal_returnsTrueForSameInstance() {

    final var actual = new ParserMessageCodeImpl("AA", "BB");

    assertThat(actual).isEqualTo(actual);
  }

  @Test
  void equal_returnsFalseForAnotherType() {

    final var actual = new ParserMessageCodeImpl("AA", "BB");
    final var notAMessageCode = (Object)"CC";

    assertThat(actual).isNotEqualTo(notAMessageCode);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      null | null | null | null | true
      null | ''   | null | ''   | true
      null | '  ' | null | '  ' | true
      null | AABB | null | AABB | true
      null | AABB | null | CCDD | false
      ''   | null | ''   | null | true
      '  ' | null | '  ' | null | true
      AABB | null | AABB | null | true
      AABB | null | CCDD | null | false
      AABB | AABB | AABB | AABB | true
      AABB | CCDD | AABB | CCDD | true
      """, delimiter = '|', nullValues = "null")
  void equalsAndHashCode_returnsAsExpected(
      final String messageCode1, final String defaultMessage1,
      final String messageCode2, final String defaultMessage2,
      final boolean expected) {

    final var actual1 = new ParserMessageCodeImpl(messageCode1, defaultMessage1);
    final var actual2 = new ParserMessageCodeImpl(messageCode2, defaultMessage2);

    assertThat(actual1.equals(actual2)).isEqualTo(expected);
    if (expected) {
      assertThat(actual1).hasSameHashCodeAs(actual2);
    } else {
      assertThat(actual1).doesNotHaveSameHashCodeAs(actual2);
    }
  }
}
