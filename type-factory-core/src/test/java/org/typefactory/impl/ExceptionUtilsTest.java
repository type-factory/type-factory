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

import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.InvalidValueException;
import org.typefactory.MessageCode;
import org.typefactory.impl.ParserMessageCodeImpl.ParserMessageCodeArgKeys;

class ExceptionUtilsTest {

  final MessageCode MESSAGE_CODE = MessageCode.of("some.message.code", "Some default message");

  @Test
  void forValueTooShort() {
    final var someValue = "someValue";
    final var minLength = someValue.length() + 1;
    final var actual = ExceptionUtils.forValueTooShort(MESSAGE_CODE, SomeClass.class, someValue, minLength);
    assertThat(actual)
        .isInstanceOf(InvalidValueException.class)
        .satisfies(exception -> {
          assertThat(exception.getMessage()).
              isEqualTo(MESSAGE_CODE.defaultMessage() + ". Invalid value - too short, minimum length is 10.");
          assertThat(exception.getParserErrorProperties())
              .containsEntry(ParserMessageCodeArgKeys.MIN_LENGTH, minLength);
        });
  }

  @Test
  void forValueTooLong() {
    final var someValue = "someValue";
    final var maxLength = someValue.length() - 1;
    final var actual = ExceptionUtils.forValueTooLong(MESSAGE_CODE, SomeClass.class, someValue, maxLength);
    assertThat(actual)
        .isInstanceOf(InvalidValueException.class)
        .satisfies(exception -> {
          assertThat(exception.getMessage()).
              isEqualTo(MESSAGE_CODE.defaultMessage() + ". Invalid value - too long, maximum length is 8.");
          assertThat(exception.getParserErrorProperties())
              .containsEntry(ParserMessageCodeArgKeys.MAX_LENGTH, maxLength);
        });
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      ` `  | some value                          | U+0020 | Some default message. Invalid value - invalid white-space character U+0020.
      `\b` | some value \b with backspace        | U+0008 | Some default message. Invalid value - invalid control character U+0008.
      '    | some 'value' with single quotes     | '      | Some default message. Invalid value - invalid quote character "'".
      e    | some value                          | e      | Some default message. Invalid value - invalid character 'e'.
      🈂    | some value 🈂 triple byte codepoint | 🈂      | Some default message. Invalid value - invalid character '🈂'.
      """, delimiter = '|', quoteCharacter = '`')
  void forInvalidCodePoint(
      final String invalidCodePointAsString,
      final String value,
      final String expectedPropertyValue,
      final String expectedErrorMessage) {

    final var invalidCodePoint = invalidCodePointAsString.codePoints().toArray()[0];
    final var actual = ExceptionUtils.forInvalidCodePoint(MESSAGE_CODE, SomeClass.class, value, invalidCodePoint);
    assertThat(actual)
        .isInstanceOf(InvalidValueException.class)
        .satisfies(exception -> {
          assertThat(exception.getMessage()).
              isEqualTo(expectedErrorMessage);
          assertThat(exception.getParserErrorProperties())
              .containsEntry(ParserMessageCodeArgKeys.INVALID_CODE_POINT, expectedPropertyValue);
        });
  }

  @Test
  void forValueNotMatchRegex() {
    final var someValue = "someValue";
    final var regex = Pattern.compile("some-regex");
    final var actual = ExceptionUtils.forValueNotMatchRegex(MESSAGE_CODE, SomeClass.class, someValue, regex);
    assertThat(actual)
        .isInstanceOf(InvalidValueException.class)
        .satisfies(exception -> {
          assertThat(exception.getMessage()).
              isEqualTo(MESSAGE_CODE.defaultMessage() + ". Invalid value - does not match regular-expression pattern some-regex");
          assertThat(exception.getParserErrorProperties())
              .containsEntry(ParserMessageCodeArgKeys.REGEX_PATTERN, regex.toString());
        });
  }

  @Test
  void forValueNotValidUsingCustomValidation() {
    final var someValue = "someValue";
    final var cause = new Exception("some exception");
    final var actual = ExceptionUtils.forValueNotValidUsingCustomValidation(MESSAGE_CODE, SomeClass.class, someValue, cause);
    assertThat(actual)
        .isInstanceOf(InvalidValueException.class)
        .satisfies(exception -> assertThat(exception.getMessage())
            .isEqualTo(MESSAGE_CODE.defaultMessage() + ". Invalid value - does not pass custom validation criteria."));
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      ` `  | U+0020
      `\b` | U+0008
      '    | U+0027
      e    | U+0065
      🈂    | U+01F202
      """, delimiter = '|', quoteCharacter = '`')
  void unicodeHexCode(
      final String codePointAsString,
      final String expectedValue) {

    final var codePoint = codePointAsString.codePoints().toArray()[0];
    final var actual = ExceptionUtils.unicodeHexCode(codePoint);
    assertThat(actual).isEqualTo(expectedValue);
  }

  private static class SomeClass {

  }
}
