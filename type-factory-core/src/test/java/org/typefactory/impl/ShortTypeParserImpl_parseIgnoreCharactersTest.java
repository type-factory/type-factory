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

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.ShortTypeParser;

class ShortTypeParserImpl_parseIgnoreCharactersTest {

  @ParameterizedTest
  @CsvSource(textBlock = """
            VALUE   |    EXPECTED | COMMENTS
           -32768   |      -32768 | Short.MIN_VALUE
              -20   |         -20 | hyphen-minus twenty
         \u221219   |         -19 | math-minus nineteen
      '  \u279618 ' |         -18 | Whitespace heavy-minus eighteen
          '  - 17 ' |         -17 | Whitespace hyphen-minus seventeen
               -0   |           0 | hyphen-minus zero
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parseToShort_allowLeadingNegativeSignParseAsExpected(
      final String value, final short expected, final String ignoredComments) {

    final var shortTypeParser = ShortTypeParser.builder()
        .minValueInclusive(Short.MIN_VALUE)
        .maxValueInclusive(Short.MAX_VALUE)
        .allowBase10Numbers()
        .allowLeadingNegativeSign()
        .ignoreAllWhitespace()
        .build();

    final var actual = shortTypeParser.parseToShort(value);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
            VALUE   |    EXPECTED | COMMENTS
           -32767   |       32767 | Short.MIN_VALUE
              -20   |          20 | hyphen-minus twenty
         \u221219   |          19 | math-minus nineteen
      '  \u279618 ' |          18 | Whitespace heavy-minus eighteen
          '  - 17 ' |          17 | Whitespace hyphen-minus seventeen
               -0   |           0 | hyphen-minus zero
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parseToShort_ignoreLeadingNegativeSignParseAsExpected(
      final String value, final short expected, final String ignoredComments) {

    final var shortTypeParser = ShortTypeParser.builder()
        .minValueInclusive(Short.MIN_VALUE)
        .maxValueInclusive(Short.MAX_VALUE)
        .allowBase10Numbers()
        .ignoreLeadingNegativeSign()
        .ignoreAllWhitespace()
        .build();

    final var actual = shortTypeParser.parseToShort(value);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
            VALUE   | EXPECTED | COMMENTS
               +0   |        0 | Plus Zero
               +1   |        1 | Plus One
         \u279510   |       10 | heavy plus ten
          '  + 17 ' |       17 | Whitespace plus seventeen
      '  \u279518 ' |       18 | heavy plus whitespace eighteen
            32767   |    32767 | Short.MAX_VALUE
           +32767   |    32767 | Plus Short.MAX_VALUE
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parseToShort_allowLeadingPositiveSignParseAsExpected(
      final String value, final short expected, final String ignoredComments) {

    final var shortTypeParser = ShortTypeParser.builder()
        .minValueInclusive(Short.MIN_VALUE)
        .maxValueInclusive(Short.MAX_VALUE)
        .allowBase10Numbers()
        .allowLeadingPositiveSign()
        .ignoreAllWhitespace()
        .build();

    final var actual = shortTypeParser.parseToShort(value);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
            VALUE   | EXPECTED | COMMENTS
               +0   |        0 | Plus Zero
               +1   |        1 | Plus One
         \u279510   |       10 | heavy plus ten
          '  + 17 ' |       17 | Whitespace plus seventeen
      '  \u279518 ' |       18 | heavy plus whitespace eighteen
            32767   |    32767 | Short.MAX_VALUE
           +32767   |    32767 | Plus Short.MAX_VALUE
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parseToShort_ignoreLeadingPositiveSignParseAsExpected(
      final String value, final short expected, final String ignoredComments) {

    final var shortTypeParser = ShortTypeParser.builder()
        .minValueInclusive(Short.MIN_VALUE)
        .maxValueInclusive(Short.MAX_VALUE)
        .allowBase10Numbers()
        .ignoreLeadingPositiveSign()
        .ignoreAllWhitespace()
        .build();

    final var actual = shortTypeParser.parseToShort(value);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
         VALUE   | EXPECTED | COMMENTS
      -3-27-67   |    32767 | Short.MIN_VALUE + 1
           -20   |       20 | Ignore hyphen-minus twenty
      \u221219   |       19 | Ignore ignore math-minus nineteen
       '   -18 ' |       18 | Whitespace ignore hyphen-minus eighteen
       '  - 17 ' |       17 | Whitespace ignore hyphen-minus seventeen
           -10   |       10 | Ignore hyphen-minus ten
       \u22129   |        9 | Ignore math-minus nine
            -1   |        1 | Ignore hyphen-minus One
             0   |        0 | Zero
             1   |        1 | One
            +1   |        1 | Plus One
             9   |        9 | Nine
            10   |       10 | Ten
           +10   |       10 | Plus ten
       '  + 17 ' |       17 | Whitespace plus seventeen
       '    18 ' |       18 | Whitespace eighteen
            19   |       19 | Nineteen
            20   |       20 | Twenty
       3-27-67   |    32767 | Short.MAX_VALUE
      +3-27-67   |    32767 | Plus Short.MAX_VALUE
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parseToShort_ignoreAllDashesAndHyphensParseAsExpected(
      final String value, final short expected, final String ignoredComments) {

    final var shortTypeParser = ShortTypeParser.builder()
        .minValueInclusive(Short.MIN_VALUE)
        .maxValueInclusive(Short.MAX_VALUE)
        .allowBase10Numbers()
        .ignoreAllDashesAndHyphens()
        .ignoreAllWhitespace()
        .build();

    final var actual = shortTypeParser.parseToShort(value);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
         VALUE   | EXPECTED | COMMENTS
      -3-27-68   |   -32768 | Short.MIN_VALUE
           -20   |      -20 | Hyphen-minus twenty
      \u221219   |      -19 | Math-minus nineteen
       '   -18 ' |      -18 | Whitespace Hyphen-minus eighteen
       '  - 17 ' |      -17 | Whitespace Hyphen-minus seventeen
           -10   |      -10 | Hyphen-minus ten
       \u22129   |       -9 | Math-minus nine
            -1   |       -1 | Hyphen-minus One
             0   |        0 | Zero
             1   |        1 | One
            +1   |        1 | Plus One
             9   |        9 | Nine
            10   |       10 | Ten
           +10   |       10 | Plus ten
       '  + 17 ' |       17 | Whitespace plus seventeen
       '    18 ' |       18 | Whitespace eighteen
            19   |       19 | Nineteen
            20   |       20 | Twenty
       3-27-67   |    32767 | Short.MAX_VALUE
      +3-27-67   |    32767 | Plus Short.MAX_VALUE
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parseToShort_ignoreAllDashesAndHyphensExceptLeadingNegativeSignParseAsExpected(
      final String value, final short expected, final String ignoredComments) {

    final var shortTypeParser = ShortTypeParser.builder()
        .minValueInclusive(Short.MIN_VALUE)
        .maxValueInclusive(Short.MAX_VALUE)
        .allowBase10Numbers()
        .ignoreAllDashesAndHyphensExceptLeadingNegativeSign()
        .ignoreAllWhitespace()
        .build();

    final var actual = shortTypeParser.parseToShort(value);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
           VALUE    | EXPECTED | COMMENTS
        -3_27_68    |   -32768 | Short.MIN_VALUE
      __\u221219    |      -19 | Math-minus nineteen
         ' __-18 '  |      -18 | Whitespace Hyphen-minus eighteen
         '  -_17_'  |      -17 | Whitespace Hyphen-minus seventeen
             -10    |      -10 | Hyphen-minus ten
       _\u2212_9    |       -9 | Math-minus nine
              -1    |       -1 | Hyphen-minus One
              _0_   |        0 | Zero
           __  1 __ |        1 | One
             _+1_   |        1 | Plus One
          3_27_67   |    32767 | Short.MAX_VALUE
         +3_27_67   |    32767 | Plus Short.MAX_VALUE
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parseToShort_ignoreAllOccurrencesOfCharParseAsExpected(
      final String value, final short expected, final String ignoredComments) {

    final var shortTypeParser = ShortTypeParser.builder()
        .minValueInclusive(Short.MIN_VALUE)
        .maxValueInclusive(Short.MAX_VALUE)
        .allowBase10Numbers()
        .ignoreAllOccurrencesOfChar('_')
        .ignoreAllWhitespace()
        .build();

    final var actual = shortTypeParser.parseToShort(value);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
          VALUE    | EXPECTED | COMMENTS
       -3*27*68    |   -32768 | Short.MIN_VALUE
           -_20_   |      -20 | Hyphen-minus twenty
      _\u221219_   |      -19 | Math-minus nineteen
            **0**  |        0 | Zero
           **10**  |       10 | Ten
            ~10~   |       10 | Plus ten
        ~~~+ 17~~~ |       17 | plus seventeen
        3_27~67    |    32767 | Short.MAX_VALUE
       +3~27_67    |    32767 | Plus Short.MAX_VALUE
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parseToShort_ignoreAllOccurrencesOfCharsParseAsExpected(
      final String value, final short expected, final String ignoredComments) {

    final var shortTypeParser = ShortTypeParser.builder()
        .minValueInclusive(Short.MIN_VALUE)
        .maxValueInclusive(Short.MAX_VALUE)
        .allowBase10Numbers()
        .ignoreAllOccurrencesOfChars('_', '~', '*')
        .ignoreAllWhitespace()
        .build();

    final var actual = shortTypeParser.parseToShort(value);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
           VALUE    | EXPECTED | COMMENTS
        -3_27_68    |   -32768 | Short.MIN_VALUE
      __\u221219    |      -19 | Math-minus nineteen
         ' __-18 '  |      -18 | Whitespace Hyphen-minus eighteen
         '  -_17_'  |      -17 | Whitespace Hyphen-minus seventeen
             -10    |      -10 | Hyphen-minus ten
       _\u2212_9    |       -9 | Math-minus nine
              -1    |       -1 | Hyphen-minus One
              _0_   |        0 | Zero
           __  1 __ |        1 | One
             _+1_   |        1 | Plus One
          3_27_67   |    32767 | Short.MAX_VALUE
         +3_27_67   |    32767 | Plus Short.MAX_VALUE
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parseToShort_ignoreAllOccurrencesOfCodePointParseAsExpected(
      final String value, final short expected, final String ignoredComments) {

    final var shortTypeParser = ShortTypeParser.builder()
        .minValueInclusive(Short.MIN_VALUE)
        .maxValueInclusive(Short.MAX_VALUE)
        .allowBase10Numbers()
        .ignoreAllOccurrencesOfCodePoint('_')
        .ignoreAllWhitespace()
        .build();

    final var actual = shortTypeParser.parseToShort(value);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
          VALUE    | EXPECTED | COMMENTS
       -3*27*68    |   -32768 | Short.MIN_VALUE
           -_20_   |      -20 | Hyphen-minus twenty
      _\u221219_   |      -19 | Math-minus nineteen
            **0**  |        0 | Zero
           **10**  |       10 | Ten
            ~10~   |       10 | Plus ten
        ~~~+ 17~~~ |       17 | plus seventeen
        3_27~67    |    32767 | Short.MAX_VALUE
       +3~27_67    |    32767 | Plus Short.MAX_VALUE
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parseToShort_ignoreAllOccurrencesOfCodePointsParseAsExpected(
      final String value, final short expected, final String ignoredComments) {

    final var shortTypeParser = ShortTypeParser.builder()
        .minValueInclusive(Short.MIN_VALUE)
        .maxValueInclusive(Short.MAX_VALUE)
        .allowBase10Numbers()
        .ignoreAllOccurrencesOfCodePoints('_', '~', '*')
        .ignoreAllWhitespace()
        .build();

    final var actual = shortTypeParser.parseToShort(value);
    assertThat(actual).isEqualTo(expected);
  }

}