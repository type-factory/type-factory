/*Copyright 2021-2022 Evan Toliopoulos (typefactory.org)

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
package org.typefactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Locale;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.testutils.StringArrayConverter;

class MessageCodeTest {

  // ─── of ───────────────────────────────────────────────────────────────────

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      CODE      | DEFAULT_MESSAGE | IS_EMPTY | EXPECTED_CODE | EXPECTED_DEFAULT_MESSAGE | EXPECTED_MESSAGE
      null      | null            | true     | ''            | ''                       | ''
      null      | ''              | true     | ''            | ''                       | ''
      null      | '  '            | true     | ''            | ''                       | ''
      null      | some message    | true     | ''            | ''                       | ''
      ''        | null            | true     | ''            | ''                       | ''
      ''        | ''              | true     | ''            | ''                       | ''
      '  '      | '  '            | true     | ''            | ''                       | ''
      '  '      | some message    | true     | ''            | ''                       | ''
      some.code | null            | false    | some.code     | ''                       | some.code
      some.code | ''              | false    | some.code     | ''                       | some.code
      some.code | '  '            | false    | some.code     | ''                       | some.code
      some.code | some message    | false    | some.code     | some message             | some message
      """)
  void of_returnsAsExpected(
      final String code, final String defaultMessage,
      final boolean expectedIsEmpty, final String expectedCode,
      final String expectedDefaultMessage, final String expectedMessage) {

    final var messageCode = MessageCode.of(code, defaultMessage);

    assertThat(messageCode.code()).isEqualTo(expectedCode);
    assertThat(messageCode.defaultMessage()).isEqualTo(expectedDefaultMessage);
    assertThat(messageCode.toString()).isEqualTo(expectedCode);

    if (expectedIsEmpty) {
      assertThat(messageCode.isEmpty()).isTrue();
      assertThat(messageCode.isBlank()).isTrue();
    } else {
      assertThat(messageCode.isEmpty()).isFalse();
      assertThat(messageCode.isBlank()).isFalse();
    }

    assertThat(messageCode.message()).isEqualTo(expectedMessage);
  }

  // ─── message() ────────────────────────────────────────────────────────────

  @ParameterizedTest
  @CsvSource(delimiter = '|', nullValues = "null", textBlock = """
      tt   | null                                        | tt
      uu   | ''                                          | uu
      vv   | '  '                                        | vv
      ww-w | some parser error message-W with value {0}. | some parser error message-W with value {0}.
      xx_x | some parser error message-X with value {0}. | some parser error message-X with value {0}.
      yyyy | some parser error message-Y with value {0}. | some parser error message-Y with value {0}.
      zzzz | some parser error message-Z with value {0}. | some parser error message-Z with value {0}.
      """)
  void message_returnsAsExpected(
      final String messageCodeString, final String messageCodeMessage,
      final String expectedMessage) {

    final var messageCode = MessageCode.of(messageCodeString, messageCodeMessage);

    assertThat(messageCode.message()).isEqualTo(expectedMessage);
  }

  // ─── message(Object[]) ────────────────────────────────────────────────────

  @ParameterizedTest
  @CsvSource(delimiter = '|', nullValues = "null", textBlock = """
      tt   | null                                        | []     | tt
      uu   | ''                                          | [null] | uu
      vv   | '  '                                        | [JJKK] | vv
      ww-w | some parser error message-W with value {0}. | null   | some parser error message-W with value {0}.
      xx_x | some parser error message-X with value {0}. | []     | some parser error message-X with value {0}.
      yyyy | some parser error message-Y with value {0}. | [null] | some parser error message-Y with value null.
      zzzz | some parser error message-Z with value {0}. | [JJKK] | some parser error message-Z with value JJKK.
      """)
  void messageWithArgs_returnsAsExpected(
      final String messageCodeString, final String messageCodeMessage,
      @ConvertWith(StringArrayConverter.class) final String[] errorMessageArgs,
      final String expectedMessage) {

    final var messageCode = MessageCode.of(messageCodeString, messageCodeMessage);

    assertThat(messageCode.message(errorMessageArgs)).isEqualTo(expectedMessage);
  }

  // ─── message(Locale) ──────────────────────────────────────────────────────

  @ParameterizedTest
  @CsvSource(delimiter = '|', nullValues = "null", textBlock = """
      yy | pp   | null                                        | pp
      yy | qq.q | ''                                          | qq.q
      yy | rr-r | '  '                                        | rr-r
      xx | ss   | some parser error message-S.                | some parser error message-S.
      xx | ww-w | some parser error message-W with value {0}. | some parser error message-W with value {0}.
      es | ss   | some parser error message-S.                | algún mensaje de error del analizador-S.
      es | ww-w | some parser error message-W with value {0}. | algún mensaje de error del analizador-W con valor {0}.
      de | ss   | some parser error message-S.                | einige Parser-Fehlermeldungen-S.
      de | ww-w | some parser error message-W with value {0}. | einige Parser-Fehlermeldungen-W mit Wert {0}.
      """)
  void messageWithLocale_returnsAsExpected(
      final String language,
      final String messageCodeString, final String messageCodeMessage,
      final String expectedMessage) {

    final var locale = new Locale(language);
    final var messageCode = MessageCode.of(messageCodeString, messageCodeMessage);

    assertThat(messageCode.message(locale)).isEqualTo(expectedMessage);
  }

  // ─── message(Locale, Object[]) ────────────────────────────────────────────

  @ParameterizedTest
  @CsvSource(delimiter = '|', nullValues = "null", textBlock = """
      yy | pp   | null                                        | null   | pp
      yy | qq.q | ''                                          | []     | qq.q
      xx | ww-w | some parser error message-W with value {0}. | [JJKK] | some parser error message-W with value JJKK.
      es | yyyy | some parser error message-Y with value {0}. | [null] | algún mensaje de error del analizador-Y con valor null.
      de | zzzz | some parser error message-Z with value {0}. | [JJKK] | einige Parser-Fehlermeldungen-Z mit Wert JJKK.
      """)
  void messageWithLocaleAndArgs_returnsAsExpected(
      final String language,
      final String messageCodeString, final String messageCodeMessage,
      @ConvertWith(StringArrayConverter.class) final String[] errorMessageArgs,
      final String expectedMessage) {

    final var locale = new Locale(language);
    final var messageCode = MessageCode.of(messageCodeString, messageCodeMessage);

    assertThat(messageCode.message(locale, errorMessageArgs)).isEqualTo(expectedMessage);
  }

  // ─── isBlank ──────────────────────────────────────────────────────────────

  @Test
  void isBlank_staticMethod_withNull_returnsTrue() {
    assertThat(MessageCode.isBlank(null)).isTrue();
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      CODE      | DEFAULT_MESSAGE | EXPECTED
      null      | null            | true
      null      | some message    | true
      ''        | null            | true
      '  '      | null            | true
      some.code | null            | false
      some.code | some message    | false
      """)
  void isBlank_instanceMethod_returnsAsExpected(
      final String code, final String defaultMessage, final boolean expected) {

    final var messageCode = new SomeMessageCode(code, defaultMessage);

    assertThat(messageCode.isBlank()).isEqualTo(expected);
    assertThat(MessageCode.isBlank(messageCode)).isEqualTo(expected);
  }

  // ─── length ───────────────────────────────────────────────────────────────

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      CODE      | EXPECTED_LENGTH
      null      | 0
      ''        | 0
      some.code | 9
      ab        | 2
      """)
  void length_returnsAsExpected(final String code, final int expectedLength) {
    final var messageCode = new SomeMessageCode(code, null);

    assertThat(messageCode.length()).isEqualTo(expectedLength);
  }

  // ─── charAt ───────────────────────────────────────────────────────────────

  @Test
  void charAt_withEmptyCode_throwsStringIndexOutOfBoundsException() {
    final var messageCode = new SomeMessageCode(null, null);

    assertThatExceptionOfType(StringIndexOutOfBoundsException.class)
        .isThrownBy(() -> messageCode.charAt(0))
        .withMessage("String index out of range: 0");
  }

  @ParameterizedTest(name = "[{index}] code={0}, index={1}, expected={2}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      CODE      | INDEX | EXPECTED_CHAR
      some.code | 0     | s
      some.code | 4     | .
      some.code | 8     | e
      ab        | 0     | a
      ab        | 1     | b
      """)
  void charAt_withNonEmptyCode_returnsCharAtIndex(
      final String code, final int index, final char expectedChar) {

    final var messageCode = new SomeMessageCode(code, null);

    assertThat(messageCode.charAt(index)).isEqualTo(expectedChar);
  }

  // ─── subSequence ──────────────────────────────────────────────────────────

  @Test
  void subSequence_withEmptyCode_throwsStringIndexOutOfBoundsException() {
    final var messageCode = new SomeMessageCode(null, null);

    assertThatExceptionOfType(StringIndexOutOfBoundsException.class)
        .isThrownBy(() -> messageCode.subSequence(0, 1))
        .withMessage("String index out of range: 0");
  }

  @ParameterizedTest(name = "[{index}] code={0}, start={1}, end={2}, expected={3}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      CODE      | START | END | EXPECTED_SUB_SEQUENCE
      some.code | 0     | 4   | some
      some.code | 5     | 9   | code
      some.code | 0     | 9   | some.code
      ab        | 0     | 1   | a
      """)
  void subSequence_withNonEmptyCode_returnsSubSequence(
      final String code, final int start, final int end, final String expectedSubSequence) {

    final var messageCode = new SomeMessageCode(code, null);

    assertThat(messageCode.subSequence(start, end).toString()).isEqualTo(expectedSubSequence);
  }

  // ─── compareTo ────────────────────────────────────────────────────────────

  @Test
  void compareTo_withSameInstance_returnsZero() {
    final var messageCode = new SomeMessageCode("some.code", null);

    assertThat(messageCode.compareTo(messageCode)).isZero();
  }

  @Test
  void compareTo_withNull_returnsPositive() {
    final var messageCode = new SomeMessageCode("some.code", null);

    assertThat(messageCode.compareTo(null)).isPositive();
  }

  @ParameterizedTest(name = "[{index}] code1={0}, code2={1}, expectedSign={2}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      CODE_1    | CODE_2    | EXPECTED_SIGN
      null      | null      | zero
      null      | aaa       | negative
      aaa       | null      | positive
      aaa       | aaa       | zero
      aaa       | bbb       | negative
      bbb       | aaa       | positive
      some.code | some.code | zero
      """)
  void compareTo_returnsAsExpected(
      final String code1, final String code2, final String expectedSign) {

    final var mc1 = new SomeMessageCode(code1, null);
    final var mc2 = new SomeMessageCode(code2, null);

    final int result = mc1.compareTo(mc2);

    switch (expectedSign) {
      case "zero"     -> assertThat(result).isZero();
      case "negative" -> assertThat(result).isNegative();
      case "positive" -> assertThat(result).isPositive();
    }
  }

  // ─── compare (static) ─────────────────────────────────────────────────────

  @Test
  void compare_static_withBothNull_returnsZero() {
    assertThat(MessageCode.compare(null, null)).isZero();
  }

  @Test
  void compare_static_withFirstNull_returnsNegative() {
    final var mc = new SomeMessageCode("aaa", null);
    assertThat(MessageCode.compare(null, mc)).isNegative();
  }

  @Test
  void compare_static_withSecondNull_returnsPositive() {
    final var mc = new SomeMessageCode("aaa", null);
    assertThat(MessageCode.compare(mc, null)).isPositive();
  }

  @ParameterizedTest(name = "[{index}] code1={0}, code2={1}, expectedSign={2}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      CODE_1    | CODE_2    | EXPECTED_SIGN
      null      | null      | zero
      aaa       | aaa       | zero
      aaa       | bbb       | negative
      bbb       | aaa       | positive
      """)
  void compare_static_returnsAsExpected(
      final String code1, final String code2, final String expectedSign) {

    final var mc1 = new SomeMessageCode(code1, null);
    final var mc2 = new SomeMessageCode(code2, null);

    final int result = MessageCode.compare(mc1, mc2);

    switch (expectedSign) {
      case "zero"     -> assertThat(result).isZero();
      case "negative" -> assertThat(result).isNegative();
      case "positive" -> assertThat(result).isPositive();
    }
  }

  // ─── equals(MessageCode) default method ───────────────────────────────────

  @Test
  void equals_messageCode_withNull_returnsFalse() {
    final var mc = new SomeMessageCode("some.code", null);

    assertThat(mc.equals((MessageCode) null)).isFalse();
  }

  @ParameterizedTest(name = "[{index}] code1={0}, code2={1}, expected={2}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      CODE_1    | CODE_2    | EXPECTED
      null      | null      | true
      ''        | ''        | true
      some.code | some.code | true
      some.code | other     | false
      null      | some.code | false
      some.code | null      | false
      """)
  void equals_messageCode_returnsAsExpected(
      final String code1, final String code2, final boolean expected) {

    final var mc1 = new SomeMessageCode(code1, null);
    final var mc2 = new SomeMessageCode(code2, null);

    assertThat(mc1.equals((MessageCode) mc2)).isEqualTo(expected);
  }

  // ─── equals(T,T) and notEquals(T,T) static ────────────────────────────────

  @Test
  void equals_static_withBothNull_returnsTrue() {
    assertThat(MessageCode.equals(null, null)).isTrue();
  }

  @Test
  void equals_static_withFirstNull_returnsFalse() {
    final var mc = new SomeMessageCode("aaa", null);
    assertThat(MessageCode.equals(null, mc)).isFalse();
  }

  @Test
  void equals_static_withSecondNull_returnsFalse() {
    final var mc = new SomeMessageCode("aaa", null);
    assertThat(MessageCode.equals(mc, null)).isFalse();
  }

  @ParameterizedTest(name = "[{index}] code1={0}, code2={1}, expected={2}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      CODE_1    | CODE_2    | EXPECTED_EQUALS
      some.code | some.code | true
      some.code | other     | false
      """)
  void equals_static_returnsAsExpected(
      final String code1, final String code2, final boolean expectedEquals) {

    final var mc1 = new SomeMessageCode(code1, null);
    final var mc2 = new SomeMessageCode(code2, null);

    assertThat(MessageCode.equals(mc1, mc2)).isEqualTo(expectedEquals);
    assertThat(MessageCode.notEquals(mc1, mc2)).isEqualTo(!expectedEquals);
  }

  // ─── hasSameCodeAs (deprecated) ───────────────────────────────────────────

  @Test
  void hasSameCodeAs_withNull_returnsFalse() {
    final var mc = new SomeMessageCode("some.code", null);
    assertThat(mc.hasSameCodeAs(null)).isFalse();
  }

  @ParameterizedTest(name = "[{index}] code1={0}, code2={1}, expected={2}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      CODE_1    | CODE_2    | EXPECTED
      null      | null      | true
      some.code | some.code | true
      some.code | other     | false
      """)
  void hasSameCodeAs_returnsAsExpected(
      final String code1, final String code2, final boolean expected) {

    final var mc1 = new SomeMessageCode(code1, null);
    final var mc2 = new SomeMessageCode(code2, null);

    assertThat(mc1.hasSameCodeAs(mc2)).isEqualTo(expected);
  }

  // ─── SomeMessageCode test double ──────────────────────────────────────────

  record SomeMessageCode(String code, String defaultMessage) implements MessageCode {

    @Override
    public boolean equals(final Object other) {
      if (other instanceof MessageCode mc) {
        return equals(mc);
      }
      return false;
    }

    @Override
    public int hashCode() {
      return java.util.Objects.hash(code);
    }

    @Override
    public String toString() {
      return code == null ? "" : code;
    }
  }
}
