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

import static org.typefactory.assertions.TypeFactoryAssertions.assertThat;

import java.lang.reflect.Constructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.InvalidValueException.ParserMessageCode;
import org.typefactory.InvalidValueException.ParserMessageCodeArgKeys;
import org.typefactory.MessageCode;

class ParserMessageCodeImplTest {

  // ─── constructor ──────────────────────────────────────────────────────────

  @ParameterizedTest(name = "[{index}] code={0}, defaultMessage={1}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      CODE  | DEFAULT_MESSAGE | EXPECTED_CODE | EXPECTED_DEFAULT_MESSAGE
      null  | null            | ''            | ''
      null  | ''              | ''            | ''
      null  | '  '            | ''            | ''
      null  | AABB            | ''            | ''
      ''    | null            | ''            | ''
      ''    | ''              | ''            | ''
      ''    | '  '            | ''            | ''
      ''    | AABB            | ''            | ''
      '  '  | null            | ''            | ''
      '  '  | ''              | ''            | ''
      '  '  | '  '            | ''            | ''
      '  '  | AABB            | ''            | ''
      AABB  | null            | AABB          | ''
      AABB  | ''              | AABB          | ''
      AABB  | '  '            | AABB          | ''
      AABB  | CCDD            | AABB          | CCDD
      """)
  void constructor_instantiatesAsExpected(
      final String code, final String defaultMessage,
      final String expectedCode, final String expectedDefaultMessage) {

    final var actual = new ParserMessageCodeImpl(code, defaultMessage);

    assertThat(actual.code()).isEqualTo(expectedCode);
    assertThat(actual.defaultMessage()).isEqualTo(expectedDefaultMessage);
  }

  // ─── instanceof ParserMessageCode ─────────────────────────────────────────

  @Test
  void constructor_implementsParserMessageCode() {
    final var actual = new ParserMessageCodeImpl("AA", "BB");

    assertThat(actual).isInstanceOf(ParserMessageCode.class);
    assertThat(actual).isInstanceOf(MessageCode.class);
  }

  // ─── equals(Object) ───────────────────────────────────────────────────────

  @Test
  void equals_object_withSameInstance_returnsTrue() {
    final var actual = new ParserMessageCodeImpl("AA", "BB");

    assertThat(actual.equals((Object) actual)).isTrue();
  }

  @Test
  void equals_object_withNullObject_returnsFalse() {
    final var actual = new ParserMessageCodeImpl("AA", "BB");

    assertThat(actual.equals((Object) null)).isFalse();
  }

  @Test
  void equals_object_withNonMessageCodeObject_returnsFalse() {
    final var actual = new ParserMessageCodeImpl("AA", "BB");

    assertThat(actual.equals((Object) "AA")).isFalse();
  }

  @Test
  void equals_object_withPlainMessageCodeObject_returnsFalse() {
    final var actual = new ParserMessageCodeImpl("AA", "BB");
    final Object plainMessageCode = MessageCode.of("AA", "BB");

    assertThat(actual.equals(plainMessageCode)).isFalse();
  }

  @Test
  void equals_object_withParserMessageCodeObject_returnsTrue() {
    final var actual = new ParserMessageCodeImpl("AA", "BB");
    final Object other = new ParserMessageCodeImpl("AA", "CC");

    assertThat(actual.equals(other)).isTrue();
  }

  @Test
  void equals_object_withParserMessageCodeObjectDifferentCode_returnsFalse() {
    final var actual = new ParserMessageCodeImpl("AA", "BB");
    final Object other = new ParserMessageCodeImpl("XX", "BB");

    assertThat(actual.equals(other)).isFalse();
  }

  // ─── equals(MessageCode) ──────────────────────────────────────────────────

  @Test
  void equals_messageCode_withNull_returnsFalse() {
    final var actual = new ParserMessageCodeImpl("AA", "BB");

    assertThat(actual.equals((MessageCode) null)).isFalse();
  }

  @Test
  void equals_messageCode_withPlainMessageCode_returnsFalse() {
    final var actual = new ParserMessageCodeImpl("AA", "BB");
    final MessageCode plainMessageCode = MessageCode.of("AA", "BB");

    assertThat(actual.equals(plainMessageCode)).isFalse();
  }

  @Test
  void equals_messageCode_withParserMessageCodeSameCode_returnsTrue() {
    final var actual = new ParserMessageCodeImpl("AA", "BB");
    final MessageCode other = new ParserMessageCodeImpl("AA", "CC");

    assertThat(actual.equals(other)).isTrue();
  }

  @Test
  void equals_messageCode_withParserMessageCodeDifferentCode_returnsFalse() {
    final var actual = new ParserMessageCodeImpl("AA", "BB");
    final MessageCode other = new ParserMessageCodeImpl("XX", "BB");

    assertThat(actual.equals(other)).isFalse();
  }

  // ─── equals(ParserMessageCode) ────────────────────────────────────────────

  @Test
  void equals_parserMessageCode_withNull_returnsFalse() {
    final var actual = new ParserMessageCodeImpl("AA", "BB");

    assertThat(actual.equals((ParserMessageCode) null)).isFalse();
  }

  @ParameterizedTest(name = "[{index}] code1={0}, code2={2}, expected={4}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      CODE_1 | DEFAULT_MESSAGE_1 | CODE_2 | DEFAULT_MESSAGE_2 | EXPECTED
      null   | null              | null   | null              | true
      null   | ''                | null   | ''                | true
      null   | '  '              | null   | '  '              | true
      null   | AABB              | null   | AABB              | true
      null   | AABB              | null   | CCDD              | true
      ''     | null              | ''     | null              | true
      '  '   | null              | '  '   | null              | true
      AABB   | null              | AABB   | null              | true
      AABB   | AABB              | AABB   | AABB              | true
      AABB   | CCDD              | AABB   | CCDD              | true
      AABB   | CCDD              | AABB   | EEFF              | true
      AABB   | null              | CCDD   | null              | false
      AABB   | AABB              | CCDD   | AABB              | false
      """)
  void equals_parserMessageCode_returnsAsExpected(
      final String code1, final String defaultMessage1,
      final String code2, final String defaultMessage2,
      final boolean expected) {

    final var actual = new ParserMessageCodeImpl(code1, defaultMessage1);
    final var other = new ParserMessageCodeImpl(code2, defaultMessage2);

    assertThat(actual.equals(other)).isEqualTo(expected);
  }

  // ─── hashCode ─────────────────────────────────────────────────────────────

  @ParameterizedTest(name = "[{index}] code1={0}, code2={2}, expectedSameHash={4}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      CODE_1 | DEFAULT_MESSAGE_1 | CODE_2 | DEFAULT_MESSAGE_2 | EXPECTED_SAME_HASH
      null   | null              | null   | null              | true
      null   | AABB              | null   | CCDD              | true
      AABB   | CCDD              | AABB   | EEFF              | true
      AABB   | CCDD              | CCDD   | AABB              | false
      """)
  void hashCode_returnsAsExpected(
      final String code1, final String defaultMessage1,
      final String code2, final String defaultMessage2,
      final boolean expectedSameHash) {

    final var actual1 = new ParserMessageCodeImpl(code1, defaultMessage1);
    final var actual2 = new ParserMessageCodeImpl(code2, defaultMessage2);

    if (expectedSameHash) {
      assertThat(actual1).hasSameHashCodeAs(actual2);
    } else {
      assertThat(actual1).doesNotHaveSameHashCodeAs(actual2);
    }
  }

  // ─── toString ─────────────────────────────────────────────────────────────

  @ParameterizedTest(name = "[{index}] code={0}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      CODE  | DEFAULT_MESSAGE | EXPECTED_TO_STRING
      null  | null            | ''
      null  | AABB            | ''
      ''    | null            | ''
      AABB  | null            | AABB
      AABB  | CCDD            | AABB
      """)
  void toString_returnsCode(
      final String code, final String defaultMessage, final String expectedToString) {

    final var actual = new ParserMessageCodeImpl(code, defaultMessage);

    assertThat(actual.toString()).isEqualTo(expectedToString);
  }

  // ─── ParserMessageCodeArgKeys inner class ─────────────────────────────────

  @Test
  void parserMessageCodeArgKeys_privateConstructor_isInstantiableViaReflection() throws Exception {
    final Constructor<ParserMessageCodeArgKeys> constructor =
        ParserMessageCodeArgKeys.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    final var instance = constructor.newInstance();

    assertThat(instance).isNotNull();
  }

  @ParameterizedTest(name = "[{index}] field={0}, expectedValue={1}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      FIELD_NAME                    | EXPECTED_VALUE
      MIN_LENGTH                    | minLength
      MAX_LENGTH                    | maxLength
      INVALID_CHARACTER_DESCRIPTION | invalidCharacterDescription
      REGEX_PATTERN                 | regexPattern
      """)
  void parserMessageCodeArgKeys_constants_haveExpectedValues(
      final String fieldName, final String expectedValue) throws Exception {

    final var field = ParserMessageCodeArgKeys.class.getDeclaredField(fieldName);
    field.setAccessible(true);

    assertThat((String) field.get(null))
        .isNotNull()
        .isNotBlank()
        .isEqualTo(expectedValue);
  }
}
