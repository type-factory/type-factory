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

import static org.typefactory.assertions.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.MessageCode;

class MessageCodeImplTest {

  // ─── constructor ──────────────────────────────────────────────────────────

  @ParameterizedTest(name = "[{index}] code={0}, defaultMessage={1}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      CODE      | DEFAULT_MESSAGE | EXPECTED_CODE | EXPECTED_DEFAULT_MESSAGE
      null      | null            | ''            | ''
      null      | ''              | ''            | ''
      null      | '  '            | ''            | ''
      null      | some message    | ''            | ''
      ''        | null            | ''            | ''
      ''        | ''              | ''            | ''
      ''        | '  '            | ''            | ''
      ''        | some message    | ''            | ''
      '  '      | null            | ''            | ''
      '  '      | ''              | ''            | ''
      '  '      | '  '            | ''            | ''
      '  '      | some message    | ''            | ''
      some.code | null            | some.code     | ''
      some.code | ''              | some.code     | ''
      some.code | '  '            | some.code     | ''
      some.code | some message    | some.code     | some message
      """)
  void constructor_instantiatesAsExpected(
      final String code, final String defaultMessage,
      final String expectedCode, final String expectedDefaultMessage) {

    final var actual = new MessageCodeImpl(code, defaultMessage);

    assertThat(actual.code()).isEqualTo(expectedCode);
    assertThat(actual.defaultMessage()).isEqualTo(expectedDefaultMessage);
  }

  // ─── instanceof MessageCode ───────────────────────────────────────────────

  @Test
  void constructor_implementsMessageCode() {
    final var actual = new MessageCodeImpl("AA", "BB");

    assertThat(actual).isInstanceOf(MessageCode.class);
  }

  // ─── toString ─────────────────────────────────────────────────────────────

  @ParameterizedTest(name = "[{index}] code={0}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      CODE      | DEFAULT_MESSAGE | EXPECTED_TO_STRING
      null      | null            | ''
      null      | some message    | ''
      ''        | null            | ''
      some.code | null            | some.code
      some.code | some message    | some.code
      """)
  void toString_returnsCode(
      final String code, final String defaultMessage, final String expectedToString) {

    final var actual = new MessageCodeImpl(code, defaultMessage);

    assertThat(actual.toString()).isEqualTo(expectedToString);
  }

  // ─── equals(Object) ───────────────────────────────────────────────────────

  @Test
  void equals_object_withSameInstance_returnsTrue() {
    final var actual = new MessageCodeImpl("AA", "BB");

    assertThat(actual.equals((Object) actual)).isTrue();
  }

  @Test
  void equals_object_withNull_returnsFalse() {
    final var actual = new MessageCodeImpl("AA", "BB");

    assertThat(actual.equals((Object) null)).isFalse();
  }

  @Test
  void equals_object_withNonMessageCodeType_returnsFalse() {
    final var actual = new MessageCodeImpl("AA", "BB");

    assertThat(actual.equals((Object) "AA")).isFalse();
  }

  @Test
  void equals_object_withMessageCodeSameCode_returnsTrue() {
    final var actual = new MessageCodeImpl("AA", "BB");
    final Object other = new MessageCodeImpl("AA", "CC");

    assertThat(actual.equals(other)).isTrue();
  }

  @Test
  void equals_object_withMessageCodeDifferentCode_returnsFalse() {
    final var actual = new MessageCodeImpl("AA", "BB");
    final Object other = new MessageCodeImpl("XX", "BB");

    assertThat(actual.equals(other)).isFalse();
  }

  @ParameterizedTest(name = "[{index}] code1={0}, code2={2}, expected={4}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      CODE_1    | DEFAULT_MESSAGE_1 | CODE_2     | DEFAULT_MESSAGE_2 | EXPECTED
      null      | null              | null       | null              | true
      null      | ''                | null       | ''                | true
      null      | '  '              | null       | '  '              | true
      null      | some message      | null       | other message     | true
      ''        | null              | ''         | null              | true
      ''        | some message      | ''         | other message     | true
      '  '      | null              | '  '       | null              | true
      '  '      | some message      | '  '       | other message     | true
      some.code | null              | some.code  | null              | true
      some.code | some message      | some.code  | other message     | true
      some.code | null              | other.code | null              | false
      some.code | some message      | other.code | some message      | false
      null      | null              | some.code  | null              | false
      some.code | null              | null       | null              | false
      """)
  void equals_object_returnsAsExpected(
      final String code1, final String defaultMessage1,
      final String code2, final String defaultMessage2,
      final boolean expected) {

    final var actual = new MessageCodeImpl(code1, defaultMessage1);
    final Object other = new MessageCodeImpl(code2, defaultMessage2);

    assertThat(actual.equals(other)).isEqualTo(expected);
  }

  // ─── hashCode ─────────────────────────────────────────────────────────────

  @ParameterizedTest(name = "[{index}] code1={0}, code2={2}, expectedSameHash={4}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      CODE_1    | DEFAULT_MESSAGE_1 | CODE_2     | DEFAULT_MESSAGE_2 | EXPECTED_SAME_HASH
      null      | null              | null       | null              | true
      null      | some message      | null       | other message     | true
      some.code | some message      | some.code  | other message     | true
      some.code | some message      | other.code | some message      | false
      """)
  void hashCode_returnsAsExpected(
      final String code1, final String defaultMessage1,
      final String code2, final String defaultMessage2,
      final boolean expectedSameHash) {

    final var actual1 = new MessageCodeImpl(code1, defaultMessage1);
    final var actual2 = new MessageCodeImpl(code2, defaultMessage2);

    if (expectedSameHash) {
      assertThat(actual1).hasSameHashCodeAs(actual2);
    } else {
      assertThat(actual1.hashCode()).isNotEqualTo(actual2.hashCode());
    }
  }
}
