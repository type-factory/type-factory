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
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatObject;

import java.io.Serial;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.InvalidValueException;
import org.typefactory.StringType;
import org.typefactory.TypeParser;

class TypeParserImpl_StringType_PreserveNullAndEmptyTest {

  @Test
  void parseToStringType_withPreserveNullAndEmpty_returnsNullForNullValue() {

    final var parser = TypeParser.builder()
        .preserveNullAndEmpty()
        .build();

    final SomeStringType actual = parser.parseToStringType(null, SomeStringType::new);

    assertThatObject(actual).isNull();
  }

  @Test
  void parseToStringType_withPreserveNullAndEmpty_returnsEmptyForEmptyValue() {

    final var parser = TypeParser.builder()
        .preserveNullAndEmpty()
        .build();

    final StringType actual = parser.parseToStringType("", SomeStringType::new);

    assertThat((CharSequence) actual)
        .isNotNull()
        .isEmpty();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE   | EXPECTED_EXCEPTION_MESSAGE
      ' '     | Invalid value - invalid white-space character U+0020 SPACE.
      '\t'    | Invalid value - invalid white-space character U+0009 CHARACTER TABULATION.
      .       | Invalid value - invalid character . U+002E FULL STOP.
      0       | Invalid value - invalid character 0 U+0030 DIGIT ZERO.
      A       | Invalid value - invalid character A U+0041 LATIN CAPITAL LETTER A.
      ABCDEFG | Invalid value - invalid character A U+0041 LATIN CAPITAL LETTER A.
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void parseToStringType_withPreserveNullAndEmpty_throwsExceptionForNonEmptyValue(
      final String value, final String expectedExceptionMessage) {

    final var parser = TypeParser.builder()
        .preserveNullAndEmpty()
        .build();

    assertThatExceptionOfType(InvalidValueException.class)
        .isThrownBy(() -> parser.parseToStringType(value, SomeStringType::new))
        .withMessage(expectedExceptionMessage);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE   | EXPECTED_VALUE
      -       | -
      _       | _
      0       | 0
      A       | A
      ABCDEFG | ABCDEFG
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void parseToStringType_withPreserveNullAndEmpty_returnsAsExpected(
      final String value, final String expectedValue) {

    final var parser = TypeParser.builder()
        .preserveNullAndEmpty()
        .acceptLettersAtoZ()
        .acceptDigits0to9()
        .acceptChars('-', '_')
        .build();

    final SomeStringType actual = parser.parseToStringType(value, SomeStringType::new);

    assertThatObject(actual)
        .isNotNull()
        .hasToString(expectedValue)
        .isInstanceOf(StringType.class)
        .extracting(StringType::value)
        .isEqualTo(expectedValue);
  }

  @Test
  void parseToStringType_withPreserveNullAndEmpty_andForbidWhitespace_returnsNullForNullValue() {

    final var parser = TypeParser.builder()
        .preserveNullAndEmpty()
        .forbidWhitespace()
        .build();

    final SomeStringType actual = parser.parseToStringType(null, SomeStringType::new);

    assertThatObject(actual).isNull();
  }

  @Test
  void parseToStringType_withPreserveNullAndEmpty_andForbidWhitespace_returnsEmptyForEmptyValue() {

    final var parser = TypeParser.builder()
        .preserveNullAndEmpty()
        .forbidWhitespace()
        .build();

    final StringType actual = parser.parseToStringType("", SomeStringType::new);

    assertThat((CharSequence) actual)
        .isNotNull()
        .isEmpty();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE   | EXPECTED_EXCEPTION_MESSAGE
      ' '     | Invalid value - invalid white-space character U+0020 SPACE.
      '\t'    | Invalid value - invalid white-space character U+0009 CHARACTER TABULATION.
      .       | Invalid value - invalid character . U+002E FULL STOP.
      0       | Invalid value - invalid character 0 U+0030 DIGIT ZERO.
      A       | Invalid value - invalid character A U+0041 LATIN CAPITAL LETTER A.
      ABCDEFG | Invalid value - invalid character A U+0041 LATIN CAPITAL LETTER A.
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void parseToStringType_withPreserveNullAndEmpty_andForbidWhitespace_throwsExceptionForNonEmptyValue(
      final String value, final String expectedExceptionMessage) {

    final var parser = TypeParser.builder()
        .preserveNullAndEmpty()
        .forbidWhitespace()
        .build();

    assertThatExceptionOfType(InvalidValueException.class)
        .isThrownBy(() -> parser.parseToStringType(value, SomeStringType::new))
        .withMessage(expectedExceptionMessage);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE   | EXPECTED_VALUE
      -       | -
      _       | _
      0       | 0
      A       | A
      ABCDEFG | ABCDEFG
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void parseToStringType_withPreserveNullAndEmpty_andForbidWhitespace_returnsAsExpected(
      final String value, final String expectedValue) {

    final var parser = TypeParser.builder()
        .preserveNullAndEmpty()
        .forbidWhitespace()
        .acceptLettersAtoZ()
        .acceptDigits0to9()
        .acceptChars('-', '_')
        .build();

    final SomeStringType actual = parser.parseToStringType(value, SomeStringType::new);

    assertThatObject(actual)
        .isNotNull()
        .hasToString(expectedValue)
        .isInstanceOf(StringType.class)
        .extracting(StringType::value)
        .isEqualTo(expectedValue);
  }


  @Test
  void parseToStringType_withPreserveNullAndEmpty_andPreserveWhitespace_returnsNullForNullValue() {

    final var parser = TypeParser.builder()
        .preserveNullAndEmpty()
        .preserveWhitespace()
        .build();

    final SomeStringType actual = parser.parseToStringType(null, SomeStringType::new);

    assertThatObject(actual).isNull();
  }

  @Test
  void parseToStringType_withPreserveNullAndEmpty_andPreserveWhitespace_returnsEmptyForEmptyValue() {

    final var parser = TypeParser.builder()
        .preserveNullAndEmpty()
        .preserveWhitespace()
        .build();

    final StringType actual = parser.parseToStringType("", SomeStringType::new);

    assertThat((CharSequence) actual)
        .isNotNull()
        .isEmpty();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE   | EXPECTED_EXCEPTION_MESSAGE
      .       | Invalid value - invalid character . U+002E FULL STOP.
      0       | Invalid value - invalid character 0 U+0030 DIGIT ZERO.
      A       | Invalid value - invalid character A U+0041 LATIN CAPITAL LETTER A.
      ABCDEFG | Invalid value - invalid character A U+0041 LATIN CAPITAL LETTER A.
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void parseToStringType_withPreserveNullAndEmpty_andPreserveWhitespace_throwsExceptionForNonEmptyValue(
      final String value, final String expectedExceptionMessage) {

    final var parser = TypeParser.builder()
        .preserveNullAndEmpty()
        .preserveWhitespace()
        .build();

    assertThatExceptionOfType(InvalidValueException.class)
        .isThrownBy(() -> parser.parseToStringType(value, SomeStringType::new))
        .withMessage(expectedExceptionMessage);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE   | EXPECTED_VALUE
      ' '     | ' '
      '\t'    | '\t'
      -       | -
      _       | _
      0       | 0
      A       | A
      ABCDEFG | ABCDEFG
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void parseToStringType_withPreserveNullAndEmpty_andPreserveWhitespace_returnsAsExpected(
      final String value, final String expectedValue) {

    final var parser = TypeParser.builder()
        .preserveNullAndEmpty()
        .preserveWhitespace()
        .acceptLettersAtoZ()
        .acceptDigits0to9()
        .acceptChars('-', '_')
        .build();

    final SomeStringType actual = parser.parseToStringType(value, SomeStringType::new);

    assertThatObject(actual)
        .isNotNull()
        .hasToString(expectedValue)
        .isInstanceOf(StringType.class)
        .extracting(StringType::value)
        .isEqualTo(expectedValue);
  }


  @Test
  void parseToStringType_withPreserveNullAndEmpty_andRemoveAllWhitespace_returnsNullForNullValue() {

    final var parser = TypeParser.builder()
        .preserveNullAndEmpty()
        .removeAllWhitespace()
        .build();

    final SomeStringType actual = parser.parseToStringType(null, SomeStringType::new);

    assertThatObject(actual).isNull();
  }

  @Test
  void parseToStringType_withPreserveNullAndEmpty_andRemoveAllWhitespace_returnsEmptyForEmptyValue() {

    final var parser = TypeParser.builder()
        .preserveNullAndEmpty()
        .removeAllWhitespace()
        .build();

    final StringType actual = parser.parseToStringType("", SomeStringType::new);

    assertThat((CharSequence) actual)
        .isNotNull()
        .isEmpty();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE   | EXPECTED_EXCEPTION_MESSAGE
      .       | Invalid value - invalid character . U+002E FULL STOP.
      0       | Invalid value - invalid character 0 U+0030 DIGIT ZERO.
      A       | Invalid value - invalid character A U+0041 LATIN CAPITAL LETTER A.
      ABCDEFG | Invalid value - invalid character A U+0041 LATIN CAPITAL LETTER A.
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void parseToStringType_withPreserveNullAndEmpty_andRemoveAllWhitespace_throwsExceptionForNonEmptyValue(
      final String value, final String expectedExceptionMessage) {

    final var parser = TypeParser.builder()
        .preserveNullAndEmpty()
        .removeAllWhitespace()
        .build();

    assertThatExceptionOfType(InvalidValueException.class)
        .isThrownBy(() -> parser.parseToStringType(value, SomeStringType::new))
        .withMessage(expectedExceptionMessage);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE   | EXPECTED_VALUE
      ' '     | ''
      '\t'    | ''
      -       | -
      _       | _
      0       | 0
      A       | A
      ABCDEFG | ABCDEFG
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void parseToStringType_withPreserveNullAndEmpty_andRemoveAllWhitespace_returnsAsExpected(
      final String value, final String expectedValue) {

    final var parser = TypeParser.builder()
        .preserveNullAndEmpty()
        .removeAllWhitespace()
        .acceptLettersAtoZ()
        .acceptDigits0to9()
        .acceptChars('-', '_')
        .build();

    final SomeStringType actual = parser.parseToStringType(value, SomeStringType::new);

    assertThatObject(actual)
        .isNotNull()
        .hasToString(expectedValue)
        .isInstanceOf(StringType.class)
        .extracting(StringType::value)
        .isEqualTo(expectedValue);
  }

  private static class SomeStringType extends StringType {

    @Serial
    private static final long serialVersionUID = 8264211200304162063L;

    public SomeStringType(String value) {
      super(value);
    }
  }
}
