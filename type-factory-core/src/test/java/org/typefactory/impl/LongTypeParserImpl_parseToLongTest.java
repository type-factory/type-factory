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

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.typefactory.InvalidValueException;
import org.typefactory.LongTypeParser;

class LongTypeParserImpl_parseToLongTest {

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {" ", "  ", "   "})
  void of_shouldReturnNull(final String value) {
    final var longTypeParser = LongTypeParser.builder()
        .allowBase10Numbers()
        .ignoreAllWhitespace()
        .build();

    final var actual = longTypeParser.parseToLong(value);
    assertThatObject(actual).isNull();
  }

  @ParameterizedTest
  @ValueSource(strings = {" ", "  ", "   "})
  void of_whitespaceShouldThrowException(final String value) {
    final var longTypeParser = LongTypeParser.builder()
        .allowBase10Numbers()
        .build();

    assertThatExceptionOfType(InvalidValueException.class)
        .isThrownBy(() -> longTypeParser.parseToLong(value))
        .withMessageStartingWith("Invalid value - invalid white-space character U+0020.");
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
                        VALUE |             EXPECTED | COMMENTS
      -1000000000000000000000 | -9223372036854775808 | Long.MIN_VALUE
       -777777777777777777777 | -9223372036854775807 | Long.MIN_VALUE + 1
                     \u221220 |                  -16 | Math-minus sixteen
                          -17 |                  -15 | Hyphen-minus fifteen
                          -11 |                   -9 | Hyphen-minus nine
                          -10 |                   -8 | Hyphen-minus eight
                           -7 |                   -7 | Hyphen-minus seven
                      \u22121 |                   -1 | Math-minus one
                           -1 |                   -1 | Minus One
                            0 |                    0 | Zero
                            1 |                    1 | One
                           +1 |                    1 | Plus One
                            7 |                    7 | Seven
                           10 |                    8 | Eight
                          +10 |                    8 | Plus eight
                           11 |                    9 | Nine
                           17 |                   15 | Fifteen
        777777777777777777776 |  9223372036854775806 | Long.MAX_VALUE - 1
        777777777777777777777 |  9223372036854775807 | Long.MAX_VALUE
       +777777777777777777777 |  9223372036854775807 | Plus Long.MAX_VALUE
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parseToLong_base8NumbersParseAsExpected(
      final String value, final long expected, final String ignoredComments) {

    final var longTypeParser = LongTypeParser.builder()
        .minValueInclusive(Long.MIN_VALUE)
        .maxValueInclusive(Long.MAX_VALUE)
        .allowBase8Numbers()
        .ignoreAllWhitespace()
        .build();

    final var actual = longTypeParser.parseToLong(value);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
                     VALUE   |             EXPECTED | COMMENTS
      -9223372036854775808   | -9223372036854775808 | Long.MIN_VALUE
      -9223372036854775807   | -9223372036854775807 | Long.MIN_VALUE + 1
                       -20   |                  -20 | Hyphen-minus twenty
                  \u221219   |                  -19 | Math-minus nineteen
                   '   -18 ' |                  -18 | Whitespace Hyphen-minus eighteen
                   '  - 17 ' |                  -17 | Whitespace Hyphen-minus seventeen
                       -10   |                  -10 | Hyphen-minus ten
                   \u22129   |                   -9 | Math-minus nine
                        -1   |                   -1 | Hyphen-minus One
                         0   |                    0 | Zero
                         1   |                    1 | One
                        +1   |                    1 | Plus One
                         9   |                    9 | Nine
                        10   |                   10 | Ten
                       +10   |                   10 | Plus ten
                   '  + 17 ' |                   17 | Whitespace plus seventeen
                   '    18 ' |                   18 | Whitespace eighteen
                        19   |                   19 | Nineteen
                        20   |                   20 | Twenty
       9223372036854775806   |  9223372036854775806 | Long.MAX_VALUE -1
       9223372036854775807   |  9223372036854775807 | Long.MAX_VALUE
      +9223372036854775807   |  9223372036854775807 | Plus Long.MAX_VALUE
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parseToLong_base10NumbersParseAsExpected(
      final String value, final long expected, final String ignoredComments) {

    final var longTypeParser = LongTypeParser.builder()
        .minValueInclusive(Long.MIN_VALUE)
        .maxValueInclusive(Long.MAX_VALUE)
        .allowBase10Numbers()
        .ignoreAllDashesAndHyphensExceptLeadingNegativeSign()
        .ignoreAllWhitespace()
        .build();

    final var actual = longTypeParser.parseToLong(value);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
                  VALUE  |             EXPECTED | COMMENTS
      -8000000000000000  | -9223372036854775808 | Long.MIN_VALUE
      -7FFFFFFFFFFFFFFF  | -9223372036854775807 | Long.MIN_VALUE + 1
               \u221220  |                  -32 | Math-minus thirty-two
                    -1F  |                  -31 | Hyphen-minus thirty-one
                    -10  |                  -16 | Hyphen-minus sixteen
                     -F  |                  -15 | Hyphen-minus fifteen
                     -9  |                   -9 | Hyphen-minus nine
                \u22121  |                   -1 | Math-minus One
                      0  |                    0 | Zero
                      1  |                    1 | One
                     +1  |                    1 | Plus One
                      9  |                    9 | Nine
                      F  |                   15 | Fifteen
                     10  |                   16 | Sixteen
                    +10  |                   16 | Plus sixteen
                     1F  |                   31 | Thirty-one
                  '  1F' |                   31 | Whitespace thirty-one
                     20  |                   32 | Thirty-two
       7FFFFFFFFFFFFFFE  |  9223372036854775806 | Long.MAX_VALUE - 1
       7FFFFFFFFFFFFFFF  |  9223372036854775807 | Long.MAX_VALUE
      +7fffffffffffffff  |  9223372036854775807 | Plus Long.MAX_VALUE
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parseToLong_base16NumbersParseAsExpected(
      final String value, final long expected, final String ignoredComments) {

    final var longTypeParser = LongTypeParser.builder()
        .minValueInclusive(Long.MIN_VALUE)
        .maxValueInclusive(Long.MAX_VALUE)
        .allowBase16Numbers()
        .ignoreAllWhitespace()
        .caseInsensitive()
        .build();

    final var actual = longTypeParser.parseToLong(value);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
               VALUE  |             EXPECTED | COMMENTS
      -8000000000000  | -9223372036854775808 | Long.MIN_VALUE
      -7VVVVVVVVVVVV  | -9223372036854775807 | Long.MIN_VALUE + 1
                 -20  |                  -64 | Hyphen-minus sixty-four
            \u22121V  |                  -63 | Math-minus sixty-three
                 -10  |                  -32 | Hyphen-minus thirty-two
             \u2212V  |                  -31 | Math-minus thirty-one
                  -9  |                   -9 | Hyphen-minus nine
                  -1  |                   -1 | Hyphen-minus One
                   0  |                    0 | Zero
                   1  |                    1 | One
                  +1  |                    1 | Plus One
                   9  |                    9 | Nine
                   V  |                   31 | Thirty-one
                  10  |                   32 | Thirty-two
                 +10  |                   32 | Plus thirty-two
                  1V  |                   63 | Sixty-three
               '  1V' |                   63 | Whitespace Sixty-three
                  20  |                   64 | Sixty-four
       7VVVVVVVVVVVU  |  9223372036854775806 | Long.MAX_VALUE - 1
       7VVVVVVVVVVVV  |  9223372036854775807 | Long.MAX_VALUE
      +7VVVVVVVVVVVV  |  9223372036854775807 | Plus Long.MAX_VALUE
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parseToLong_base32NumbersParseAsExpected(
      final String value, final long expected, final String ignoredComments) {

    final var longTypeParser = LongTypeParser.builder()
        .minValueInclusive(Long.MIN_VALUE)
        .maxValueInclusive(Long.MAX_VALUE)
        .allowBase32Numbers()
        .ignoreAllWhitespace()
        .caseInsensitive()
        .build();

    final var actual = longTypeParser.parseToLong(value);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
               VALUE  |             EXPECTED | COMMENTS
      -1Y2P0IJ32E8E8  | -9223372036854775808 | Long.MIN_VALUE
      -1Y2P0IJ32E8E7  | -9223372036854775807 | Long.MIN_VALUE + 1
                 -20  |                  -72 | Hyphen-minus seventy-two
            \u22121Z  |                  -71 | Math-minus seventy-one
                 -10  |                  -36 | Hyphen-minus thirty-six
             \u2212Z  |                  -35 | Math-minus thirty-five
                  -9  |                   -9 | Hyphen-minus nine
                  -1  |                   -1 | Hyphen-minus One
                   0  |                    0 | Zero
                   1  |                    1 | One
                  +1  |                    1 | Plus One
                   9  |                    9 | Nine
                   Z  |                   35 | Thirty-five
                  10  |                   36 | Thirty-six
                 +10  |                   36 | Plus thirty-six
                  1Z  |                   71 | Seventy-one
               '  1Z' |                   71 | Whitespace seventy-one
                  20  |                   72 | Seventy-two
       1Y2P0IJ32E8E6  |  9223372036854775806 | Long.MAX_VALUE - 1
       1Y2P0IJ32E8E7  |  9223372036854775807 | Long.MAX_VALUE
      +1Y2P0IJ32E8E7  |  9223372036854775807 | Plus Long.MAX_VALUE
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parseToLong_base36NumbersParseAsExpected(
      final String value, final long expected, final String ignoredComments) {

    final var longTypeParser = LongTypeParser.builder()
        .minValueInclusive(Long.MIN_VALUE)
        .maxValueInclusive(Long.MAX_VALUE)
        .allowBase36Numbers()
        .ignoreAllWhitespace()
        .caseInsensitive()
        .build();

    final var actual = longTypeParser.parseToLong(value);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
             VALUE  |             EXPECTED | COMMENTS
      -AzL8n0Y58m8  | -9223372036854775808 | Long.MIN_VALUE
      -AzL8n0Y58m7  | -9223372036854775807 | Long.MIN_VALUE + 1
               -20  |                 -124 | Hyphen-minus hundred-twenty-four
          \u22121z  |                 -123 | Math-minus hundred-twenty-three
               -10  |                  -62 | Hyphen-minus sixty-two
           \u2212z  |                  -61 | Math-minus sixty-one
                -9  |                   -9 | Hyphen-minus nine
                -1  |                   -1 | Hyphen-minus One
                 0  |                    0 | Zero
                 1  |                    1 | One
                +1  |                    1 | Plus One
                 9  |                    9 | Nine
                 z  |                   61 | sixty-one
                10  |                   62 | sixty-two
               +10  |                   62 | Plus sixty-two
                1z  |                  123 | Hundred-twenty-three
             '  1z' |                  123 | Whitespace hundred-twenty-three
                20  |                  124 | Hundred-twenty-four
       AzL8n0Y58m6  |  9223372036854775806 | Long.MAX_VALUE - 1
       AzL8n0Y58m7  |  9223372036854775807 | Long.MAX_VALUE
      +AzL8n0Y58m7  |  9223372036854775807 | Plus Long.MAX_VALUE
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parseToLong_base62NumbersParseAsExpected(
      final String value, final long expected, final String ignoredComments) {

    final var longTypeParser = LongTypeParser.builder()
        .minValueInclusive(Long.MIN_VALUE)
        .maxValueInclusive(Long.MAX_VALUE)
        .allowBase62Numbers()
        .ignoreAllWhitespace()
        .caseSensitive()
        .build();

    final var actual = longTypeParser.parseToLong(value);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
             INCLUSIVE_MIN |       INCLUSIVE_MAX |                VALUE | EXPECTED_MESSAGE
      -9223372036854775808 | 9223372036854775807 | -9223372036854775809 | Invalid value - must be greater than or equal to -9,223,372,036,854,775,808.
      -9223372036854775808 | 9223372036854775807 |  9223372036854775808 | Invalid value - must be less than or equal to 9,223,372,036,854,775,807.
                         0 |                   0 |                   -1 | Invalid value - must be greater than or equal to 0.
                         0 |                   0 |                    1 | Invalid value - must be less than or equal to 0.
                        -1 |                   1 |                   -2 | Invalid value - must be greater than or equal to -1.
                        -1 |                   1 |                    2 | Invalid value - must be less than or equal to 1.
                       -20 |                 -10 |                  -21 | Invalid value - must be greater than or equal to -20.
                       -20 |                 -10 |                   -9 | Invalid value - must be less than or equal to -10.
                        10 |                  20 |                    9 | Invalid value - must be greater than or equal to 10.
                        10 |                  20 |                   21 | Invalid value - must be less than or equal to 20.
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parseToLong_outsideInclusiveMinMaxThrowsException(
      final long min, final long max,
      final String value, final String expectedMessage) {

    final var longTypeParser = LongTypeParser.builder()
        .minValueInclusive(min)
        .maxValueInclusive(max)
        .allowBase10Numbers()
        .ignoreAllWhitespace()
        .build();

    assertThatExceptionOfType(InvalidValueException.class)
        .isThrownBy(() -> longTypeParser.parseToLong(value))
        .withMessageStartingWith(expectedMessage);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
             EXCLUSIVE_MIN |       EXCLUSIVE_MAX |                VALUE | EXPECTED_MESSAGE
      -9223372036854775808 | 9223372036854775807 | -9223372036854775809 | Invalid value - must be greater than -9,223,372,036,854,775,808.
      -9223372036854775808 | 9223372036854775807 | -9223372036854775808 | Invalid value - must be greater than -9,223,372,036,854,775,808.
      -9223372036854775808 | 9223372036854775807 |  9223372036854775808 | Invalid value - must be less than 9,223,372,036,854,775,807.
      -9223372036854775808 | 9223372036854775807 |  9223372036854775807 | Invalid value - must be less than 9,223,372,036,854,775,807.
                         0 |                   0 |                   -1 | Invalid value - must be greater than 0.
                         0 |                   0 |                    0 | Invalid value - must be greater than 0.
                         0 |                   0 |                    1 | Invalid value - must be less than 0.
                        -1 |                   1 |                   -1 | Invalid value - must be greater than -1.
                        -1 |                   1 |                    1 | Invalid value - must be less than 1.
                       -20 |                 -10 |                  -20 | Invalid value - must be greater than -20.
                       -20 |                 -10 |                  -21 | Invalid value - must be greater than -20.
                       -20 |                 -10 |                  -10 | Invalid value - must be less than -10.
                       -20 |                 -10 |                   -9 | Invalid value - must be less than -10.
                        10 |                  20 |                   10 | Invalid value - must be greater than 10.
                        10 |                  20 |                    9 | Invalid value - must be greater than 10.
                        10 |                  20 |                   20 | Invalid value - must be less than 20.
                        10 |                  20 |                   21 | Invalid value - must be less than 20.
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parseToLong_outsideExclusiveMinMaxThrowsException(
      final long min, final long max,
      final String value, final String expectedMessage) {

    final var longTypeParser = LongTypeParser.builder()
        .minValueExclusive(min)
        .maxValueExclusive(max)
        .allowBase10Numbers()
        .ignoreAllWhitespace()
        .build();

    assertThatExceptionOfType(InvalidValueException.class)
        .isThrownBy(() -> longTypeParser.parseToLong(value))
        .withMessageStartingWith(expectedMessage);
  }
}