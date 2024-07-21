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

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.InvalidValueException;
import org.typefactory.LongTypeParser;

class LongTypeParserImpl_parseWhitespaceTest {

  @ParameterizedTest
  @CsvSource(textBlock = """
                           VALUE   |             EXPECTED | COMMENTS
      -9 223 372 036 854 775 808   | -9223372036854775808 | Long.MIN_VALUE
      -9 223 372 036 854 775 807   | -9223372036854775807 | Long.MIN_VALUE + 1
                            -2 0   |                  -20 | Hyphen-minus twenty
                       \u22121 9   |                  -19 | Math-minus nineteen
                        '   -1 8 ' |                  -18 | Whitespace Hyphen-minus eighteen
                        '  - 1 7 ' |                  -17 | Whitespace Hyphen-minus seventeen
                            -1 0   |                  -10 | Hyphen-minus ten
                             1 0   |                   10 | Ten
                            +1 0   |                   10 | Plus ten
                        '  + 1 7 ' |                   17 | Whitespace plus seventeen
                        '    1 8 ' |                   18 | Whitespace eighteen
                             1 9   |                   19 | Nineteen
                             2 0   |                   20 | Twenty
       9 223 372 036 854 775 806   |  9223372036854775806 | Long.MAX_VALUE -1
       9 223 372 036 854 775 807   |  9223372036854775807 | Long.MAX_VALUE
      +9 223 372 036 854 775 807   |  9223372036854775807 | Plus Long.MAX_VALUE
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parseToLong_base10IgnoreAllWhitespace(
      final String value, final long expected, final String ignoredComments) {

    final var longTypeParser = LongTypeParser.builder()
        .minValueInclusive(Long.MIN_VALUE)
        .maxValueInclusive(Long.MAX_VALUE)
        .allowBase10Numbers()
        .ignoreAllWhitespace()
        .build();

    final var actual = longTypeParser.parse(value);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
                           VALUE   | EXPECTED_MESSAGE
      -9 223 372 036 854 775 808   | Invalid value - invalid white-space character U+0020 SPACE.
      -9 223 372 036 854 775 807   | Invalid value - invalid white-space character U+0020 SPACE.
                            -2 0   | Invalid value - invalid white-space character U+0020 SPACE.
                       \u22121 9   | Invalid value - invalid white-space character U+0020 SPACE.
                        '   -1 8 ' | Invalid value - invalid white-space character U+0020 SPACE.
                        '  - 1 7 ' | Invalid value - invalid white-space character U+0020 SPACE.
                            -1 0   | Invalid value - invalid white-space character U+0020 SPACE.
                             1 0   | Invalid value - invalid white-space character U+0020 SPACE.
                            +1 0   | Invalid value - invalid white-space character U+0020 SPACE.
                        '  + 1 7 ' | Invalid value - invalid white-space character U+0020 SPACE.
                        '    1 8 ' | Invalid value - invalid white-space character U+0020 SPACE.
                             1 9   | Invalid value - invalid white-space character U+0020 SPACE.
                             2 0   | Invalid value - invalid white-space character U+0020 SPACE.
       9 223 372 036 854 775 806   | Invalid value - invalid white-space character U+0020 SPACE.
       9 223 372 036 854 775 807   | Invalid value - invalid white-space character U+0020 SPACE.
      +9 223 372 036 854 775 807   | Invalid value - invalid white-space character U+0020 SPACE.
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parseToLong_base10ForbidWhitespace(
      final String value, final String expectedMessage) {

    final var longTypeParser = LongTypeParser.builder()
        .minValueInclusive(Long.MIN_VALUE)
        .maxValueInclusive(Long.MAX_VALUE)
        .allowBase10Numbers()
        .forbidWhitespace()
        .build();

    assertThatExceptionOfType(InvalidValueException.class)
        .isThrownBy(() -> longTypeParser.parse(value))
        .withMessageStartingWith(expectedMessage);
  }

}
