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

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatObject;

import java.io.Serial;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.typefactory.InvalidValueException;
import org.typefactory.StringType;
import org.typefactory.TypeParser;

class TypeParserImpl_StringType_ConvertEmptyToNullTest {


  @ParameterizedTest
  @NullAndEmptySource
  void parseToStringType_withConvertEmptyToNull_returnsNullForNullValue(final String value) {

    final var parser = TypeParser.builder()
        .convertEmptyToNull()
        .build();

    final SomeStringType actual = parser.parseToStringType(value, SomeStringType::new);

    assertThatObject(actual).isNull();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE   | EXPECTED_EXCEPTION_MESSAGE
      ' '     | Invalid value - invalid white-space character U+0020.
      '\t'    | Invalid value - invalid white-space character U+0009.
      .       | Invalid value - invalid character '.'.
      0       | Invalid value - invalid character '0'.
      A       | Invalid value - invalid character 'A'.
      ABCDEFG | Invalid value - invalid character 'A'.
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void parseToStringType_withConvertEmptyToNull_throwsExceptionForNonEmptyValue(
      final String value, final String expectedExceptionMessage) {

    final var parser = TypeParser.builder()
        .convertEmptyToNull()
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
  void parseToStringType_withConvertEmptyToNull_returnsAsExpected(
      final String value, final String expectedValue) {

    final var parser = TypeParser.builder()
        .convertEmptyToNull()
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

  @ParameterizedTest
  @NullAndEmptySource
  void parseToStringType_withConvertEmptyToNull_andForbidWhitespace_returnsNullForNullValue(final String value) {

    final var parser = TypeParser.builder()
        .convertEmptyToNull()
        .forbidWhitespace()
        .build();

    final SomeStringType actual = parser.parseToStringType(value, SomeStringType::new);

    assertThatObject(actual).isNull();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE   | EXPECTED_EXCEPTION_MESSAGE
      ' '     | Invalid value - invalid white-space character U+0020.
      '\t'    | Invalid value - invalid white-space character U+0009.
      .       | Invalid value - invalid character '.'.
      0       | Invalid value - invalid character '0'.
      A       | Invalid value - invalid character 'A'.
      ABCDEFG | Invalid value - invalid character 'A'.
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void parseToStringType_withConvertEmptyToNull_andForbidWhitespace_throwsExceptionForNonEmptyValue(
      final String value, final String expectedExceptionMessage) {

    final var parser = TypeParser.builder()
        .convertEmptyToNull()
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
  void parseToStringType_withConvertEmptyToNull_andForbidWhitespace_returnsAsExpected(
      final String value, final String expectedValue) {

    final var parser = TypeParser.builder()
        .convertEmptyToNull()
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


  @ParameterizedTest
  @NullAndEmptySource
  void parseToStringType_withConvertEmptyToNull_andPreserveWhitespace_returnsNullForNullValue(final String value) {

    final var parser = TypeParser.builder()
        .convertEmptyToNull()
        .preserveWhitespace()
        .build();

    final SomeStringType actual = parser.parseToStringType(value, SomeStringType::new);

    assertThatObject(actual).isNull();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE   | EXPECTED_EXCEPTION_MESSAGE
      .       | Invalid value - invalid character '.'.
      0       | Invalid value - invalid character '0'.
      A       | Invalid value - invalid character 'A'.
      ABCDEFG | Invalid value - invalid character 'A'.
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void parseToStringType_withConvertEmptyToNull_andPreserveWhitespace_throwsExceptionForNonEmptyValue(
      final String value, final String expectedExceptionMessage) {

    final var parser = TypeParser.builder()
        .convertEmptyToNull()
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
  void parseToStringType_withConvertEmptyToNull_andPreserveWhitespace_returnsAsExpected(
      final String value, final String expectedValue) {

    final var parser = TypeParser.builder()
        .convertEmptyToNull()
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

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {" ", "  ", "\t", "\r", "\n", "\r\n"})
  void parseToStringType_withConvertEmptyToNull_andRemoveAllWhitespace_returnsNullForNullValue(final String value) {

    final var parser = TypeParser.builder()
        .convertEmptyToNull()
        .removeAllWhitespace()
        .build();

    final SomeStringType actual = parser.parseToStringType(value, SomeStringType::new);

    assertThatObject(actual).isNull();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE   | EXPECTED_EXCEPTION_MESSAGE
      .       | Invalid value - invalid character '.'.
      0       | Invalid value - invalid character '0'.
      A       | Invalid value - invalid character 'A'.
      ABCDEFG | Invalid value - invalid character 'A'.
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void parseToStringType_withConvertEmptyToNull_andRemoveAllWhitespace_throwsExceptionForNonEmptyValue(
      final String value, final String expectedExceptionMessage) {

    final var parser = TypeParser.builder()
        .convertEmptyToNull()
        .removeAllWhitespace()
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
  void parseToStringType_withConvertEmptyToNull_andRemoveAllWhitespace_returnsAsExpected(
      final String value, final String expectedValue) {

    final var parser = TypeParser.builder()
        .convertEmptyToNull()
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
    private static final long serialVersionUID = 7890259072175007781L;

    public SomeStringType(String value) {
      super(value);
    }
  }
}