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
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.InvalidValueException;
import org.typefactory.MessageCode;
import org.typefactory.impl.ParserMessageCodeImpl.ParserMessageCodeArgKeys;
import org.typefactory.testutils.CodePointArrayConverter;

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
      ` `  | some value                          | U+0020 SPACE                    | Some default message. Invalid value - invalid white-space character U+0020 SPACE.
      `\b` | some value \b with backspace        | U+0008 BACKSPACE                | Some default message. Invalid value - invalid control character U+0008 BACKSPACE.
      '    | some 'value' with single quotes     | ' U+0027 APOSTROPHE             | Some default message. Invalid value - invalid quote character ' U+0027 APOSTROPHE.
      e    | some value                          | e U+0065 LATIN SMALL LETTER E   | Some default message. Invalid value - invalid character e U+0065 LATIN SMALL LETTER E.
      🈂    | some value 🈂 triple byte codepoint | 🈂 U+01F202 SQUARED KATAKANA SA | Some default message. Invalid value - invalid character 🈂 U+01F202 SQUARED KATAKANA SA.
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
              .containsEntry(ParserMessageCodeArgKeys.INVALID_CHARACTER_DESCRIPTION, expectedPropertyValue);
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

  @Test
  void forMultipleDecimalPoints() {
    final var someValue = "23..89";
    final var actual = ExceptionUtils.forMultipleDecimalPoints(MESSAGE_CODE, SomeClass.class, someValue, '.');
    assertThat(actual)
        .isInstanceOf(InvalidValueException.class)
        .satisfies(exception -> assertThat(exception.getMessage())
            .isEqualTo(MESSAGE_CODE.defaultMessage() + ". Invalid value - invalid character or unexpected multiple decimal points found '.'."));
  }

  @Test
  void forDecimalPointNotPermittedForNonBaseTenNumbers() {
    final var someValue = "2AB.99F";
    final var actual = ExceptionUtils.forDecimalPointNotPermittedForNonBaseTenNumbers(MESSAGE_CODE, SomeClass.class, someValue, '.');
    assertThat(actual)
        .isInstanceOf(InvalidValueException.class)
        .satisfies(exception -> assertThat(exception.getMessage())
            .isEqualTo(MESSAGE_CODE.defaultMessage() + ". Invalid value - not expecting a fractional part, decimal point is not permitted for non base-10 numbers '.'."));
  }

  @ParameterizedTest
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      VALUE | DECIMAL_SEPARATORS | EXPECTED_MESSAGE
      1.23  | null               | Invalid value - expected a whole number with no decimal places and no decimal point character expected.
      3.45  | []                 | Invalid value - expected a whole number with no decimal places and no decimal point character expected.
      3.45  | [.]                | Invalid value - expected a whole number with no decimal places, or decimal places of zero, after the decimal point character '.'.
      """)
  void forExpectingWholeNumber_noDecimalSeparatorsProvided(
      final String value,
      @ConvertWith(CodePointArrayConverter.class) final int[] decimalSeparators,
      final String expectedMessage) {

    final var actual = ExceptionUtils.forExpectingWholeNumber(MESSAGE_CODE, SomeClass.class, value, decimalSeparators);
    assertThat(actual)
        .isInstanceOf(InvalidValueException.class)
        .satisfies(exception -> assertThat(exception.getMessage())
            .isEqualTo(MESSAGE_CODE.defaultMessage() + ". " + expectedMessage));
  }

  @Test
  void forValueMustBeGreaterThanMinValue() {
    final var someValue = 1;
    final var minValue = 2;
    final var actual = ExceptionUtils.forValueMustBeGreaterThanMinValue(MESSAGE_CODE, SomeClass.class, someValue, minValue);
    assertThat(actual)
        .isInstanceOf(InvalidValueException.class)
        .satisfies(exception -> assertThat(exception.getMessage())
            .isEqualTo(MESSAGE_CODE.defaultMessage() + ". Invalid value - must be greater than 2."));
  }

  @Test
  void forValueMustBeGreaterThanOrEqualToMinValue() {
    final var someValue = 1;
    final var minValue = 2;
    final var actual = ExceptionUtils.forValueMustBeGreaterThanOrEqualToMinValue(MESSAGE_CODE, SomeClass.class, someValue, minValue);
    assertThat(actual)
        .isInstanceOf(InvalidValueException.class)
        .satisfies(exception -> assertThat(exception.getMessage())
            .isEqualTo(MESSAGE_CODE.defaultMessage() + ". Invalid value - must be greater than or equal to 2."));
  }

  @Test
  void forValueMustBeLessThanMaxValue() {
    final var someValue = 3;
    final var maxValue = 2;
    final var actual = ExceptionUtils.forValueMustBeLessThanMaxValue(MESSAGE_CODE, SomeClass.class, someValue, maxValue);
    assertThat(actual)
        .isInstanceOf(InvalidValueException.class)
        .satisfies(exception -> assertThat(exception.getMessage())
            .isEqualTo(MESSAGE_CODE.defaultMessage() + ". Invalid value - must be less than 2."));
  }

  @Test
  void forValueMustBeLessThanOrEqualToMaxValue() {
    final var someValue = 3;
    final var maxValue = 2;
    final var actual = ExceptionUtils.forValueMustBeLessThanOrEqualToMaxValue(MESSAGE_CODE, SomeClass.class, someValue, maxValue);
    assertThat(actual)
        .isInstanceOf(InvalidValueException.class)
        .satisfies(exception -> assertThat(exception.getMessage())
            .isEqualTo(MESSAGE_CODE.defaultMessage() + ". Invalid value - must be less than or equal to 2."));
  }

  @Test
  void forHighSurrogateWithoutLowSurrogate() {
    final var someValue = "some value";
    final char highSurrogate = 0xD83D;
    final var actual = ExceptionUtils.forHighSurrogateWithoutLowSurrogate(MESSAGE_CODE, SomeClass.class, someValue, highSurrogate);
    assertThat(actual)
        .isInstanceOf(InvalidValueException.class)
        .satisfies(exception -> assertThat(exception.getMessage())
            .isEqualTo(MESSAGE_CODE.defaultMessage() + ". Invalid value - incomplete surrogate-pair - the low-surrogate code unit is missing for the high-surrogate code unit U+D83D HIGH SURROGATE."));
  }

  @Test
  void forLowSurrogateWithoutHighSurrogate() {
    final var someValue = "some value";
    final char lowSurrogate = 0xDE03;
    final var actual = ExceptionUtils.forLowSurrogateWithoutHighSurrogate(MESSAGE_CODE, SomeClass.class, someValue, lowSurrogate);
    assertThat(actual)
        .isInstanceOf(InvalidValueException.class)
        .satisfies(exception -> assertThat(exception.getMessage())
            .isEqualTo(MESSAGE_CODE.defaultMessage() + ". Invalid value - incomplete surrogate-pair - the high-surrogate code unit is missing for the low-surrogate code unit U+DE03 LOW SURROGATE."));
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      CODE_POINT | EXPECTED_VALUE                                                            | COMMENT
      0x0000     | Invalid value - invalid control character U+0000 NULL.                    | Control character
      0x0001     | Invalid value - invalid control character U+0001 START OF HEADING.        | Control character
      0x000C     | Invalid value - invalid white-space character U+000C FORM FEED (FF).      | Control character
      0x0008     | Invalid value - invalid control character U+0008 BACKSPACE.               | Control character
      0x0020     | Invalid value - invalid white-space character U+0020 SPACE.               | Space character
      0x2028     | Invalid value - invalid white-space character U+2028 LINE SEPARATOR.      | Space character
      0x2029     | Invalid value - invalid white-space character U+2029 PARAGRAPH SEPARATOR. | Space character
      0x00A0     | Invalid value - invalid white-space character U+00A0 NO-BREAK SPACE.      | Space character
      0x0027     | Invalid value - invalid quote character ' U+0027 APOSTROPHE.              | Quote character
      0x0065     | Invalid value - invalid character e U+0065 LATIN SMALL LETTER E.          | Latin character
      0x005A     | Invalid value - invalid character Z U+005A LATIN CAPITAL LETTER Z.        | Latin character
      0x01F202   | Invalid value - invalid character 🈂 U+01F202 SQUARED KATAKANA SA.         | Japanese codepoint requiring two 16-bit high and low surrogates
      0x0E0023   | Invalid value - invalid format character U+0E0023 TAG NUMBER SIGN.        | Codepoint in the Unicode Format Category requiring two 16-bit high and low surrogates
      0xD83D     | Invalid value - invalid character U+D83D HIGH SURROGATE.                  | Dangling high surrogate – 😃 is U+1F603 which has high surrogate U+D83D and low surrogate U+DE03
      0xDE03     | Invalid value - invalid character U+DE03 LOW SURROGATE.                   | Dangling low surrogate – 😃 is U+1F603 which has high surrogate U+D83D and low surrogate U+DE03
      0x00FFF8   | Invalid value - invalid character U+FFF8 UNASSIGNED UNICODE CHARACTER.    | Inside the Unicode range but not an unassigned codepoint
      0x0EFFF9   | Invalid value - invalid character U+0EFFF9 UNASSIGNED UNICODE CHARACTER.  | Inside the Unicode range but not an unassigned codepoint
      0x110000   | Invalid value - invalid character U+110000 NOT A UNICODE CHARACTER.       | Outside unicode range - max Unicode value is 0x10FFFF
      0x01000000 | Invalid value - invalid character U+01000000 NOT A UNICODE CHARACTER.     | Outside unicode range - max Unicode value is 0x10FFFF
      """, delimiter = '|', quoteCharacter = '`', useHeadersInDisplayName = true)
  void forInvalidCodePoint(
      final int codePoint,
      final String expectedMessage,
      final String ignoredComment) {

    final var someValue = "some value";
    final var actual = ExceptionUtils.forInvalidCodePoint(MESSAGE_CODE, SomeClass.class, someValue, codePoint);
    assertThat(actual)
        .isInstanceOf(InvalidValueException.class)
        .satisfies(exception -> {
          assertThat(exception.getMessage())
              .isEqualTo(MESSAGE_CODE.defaultMessage() + ". " + expectedMessage);
        });
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      CODE_POINT | EXPECTED_VALUE                        | COMMENT
      0x0000     | U+0000 NULL                           | Control character
      0x0001     | U+0001 START OF HEADING               | Control character
      0x000C     | U+000C FORM FEED (FF)                 | Control character
      0x0008     | U+0008 BACKSPACE                      | Control character
      0x0020     | U+0020 SPACE                          | Space character
      0x0027     | ' U+0027 APOSTROPHE                   | Quote character
      0x0065     | e U+0065 LATIN SMALL LETTER E         | Latin character
      0x005A     | Z U+005A LATIN CAPITAL LETTER Z       | Latin character
      0x01F202   | 🈂 U+01F202 SQUARED KATAKANA SA        | Japanese codepoint requiring two 16-bit high and low surrogates
      0x0E0023   | U+0E0023 TAG NUMBER SIGN              | Codepoint in the Unicode Format Category requiring two 16-bit high and low surrogates
      0xD83D     | U+D83D HIGH SURROGATE                 | Dangling high surrogate – 😃 is U+1F603 which has high surrogate U+D83D and low surrogate U+DE03
      0xDE03     | U+DE03 LOW SURROGATE                  | Dangling low surrogate – 😃 is U+1F603 which has high surrogate U+D83D and low surrogate U+DE03
      0x00FFF8   | U+FFF8 UNASSIGNED UNICODE CHARACTER   | Inside the Unicode range but not an unassigned codepoint
      0x0EFFF9   | U+0EFFF9 UNASSIGNED UNICODE CHARACTER | Inside the Unicode range but not an unassigned codepoint
      0x110000   | U+110000 NOT A UNICODE CHARACTER      | Outside unicode range - max Unicode value is 0x10FFFF
      0x01000000 | U+01000000 NOT A UNICODE CHARACTER    | Outside unicode range - max Unicode value is 0x10FFFF
      """, delimiter = '|', quoteCharacter = '`', useHeadersInDisplayName = true)
  void unicodeHexCode_handlesCodePoints(
      final int codePoint,
      final String expectedValue,
      final String ignoredComment) {

    final var actual = ExceptionUtils.unicodeHexCode(codePoint);
    assertThat(actual).isEqualTo(expectedValue);
  }


  private static class SomeClass {

  }
}
