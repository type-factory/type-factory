/*
   Copyright 2021-2022 Evan Toliopoulos (typefactory.org)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OΖ ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.ShortTypeParser;

class ShortTypeParserImpl_parseArbitraryRadixTest {

  @ParameterizedTest
  @CsvSource(textBlock = """
         VALUE  | EXPECTED | COMMENTS
         -8000  |   -32768 | Short.MIN_VALUE
         -7ΖΖΖ  |   -32767 | Short.MIN_VALUE + 1
          -ΓΔΖ  |    -3295 | A number utilising non-numeric radix digits
      \u221220  |      -32 | Math-minus thirty-two
           -1Ζ  |      -31 | Hyphen-minus thirty-one
           -10  |      -16 | Hyphen-minus sixteen
            -Ζ  |      -15 | Hyphen-minus fifteen
            -9  |       -9 | Hyphen-minus nine
       \u22121  |       -1 | Math-minus One
             0  |        0 | Zero
             1  |        1 | One
            +1  |        1 | Plus One
             9  |        9 | Nine
             Ζ  |       15 | Fifteen
            10  |       16 | Sixteen
           +10  |       16 | Plus sixteen
            1Ζ  |       31 | Thirty-one
         '  1Ζ' |       31 | Whitespace thirty-one
            20  |       32 | Thirty-two
          +ΓΔΖ  |     3295 | A number utilising non-numeric radix digits
          7ΖΖΕ  |    32766 | Short.MAX_VALUE - 1
          7ΖΖΖ  |    32767 | Short.MAX_VALUE
         +7ζζζ  |    32767 | Plus Short.MAX_VALUE
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parseToShort_allowCustomBaseNumbersForRadix16UsingGreekLetters(
      final String value, final short expected, final String ignoredComments) {

    final var shortTypeParser = ShortTypeParser.builder()
        .minValueInclusive(Short.MIN_VALUE)
        .maxValueInclusive(Short.MAX_VALUE)
        .allowCustomBaseNumbers('0','1','2','3','4','5','6','7','8','9','Α','Β','Γ','Δ','Ε','Ζ')
        .caseInsensitive()
        .ignoreAllWhitespace()
        .build();

    final var actual = shortTypeParser.parseToShort(value);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
         VALUE  | EXPECTED | COMMENTS
       -😞😀😀 |       -4 | Minus Four
         -😞😞 |       -3 | Minus three
         -😞😀 |       -2 | Minus two
           -😞 |       -1 | Minus one
            😀 |        0 | Zero
            😞 |        1 | One
          😞😀 |        2 | Two
         +😞😞 |        3 | Three
        😞😀😀 |        4 | Four
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parseToShort_allowCustomBaseNumbersForRadix2UsingEmoji(
      final String value, final short expected, final String ignoredComments) {

    final var shortTypeParser = ShortTypeParser.builder()
        .minValueInclusive((short) -4)
        .maxValueInclusive((short) 4)
        .allowCustomBaseNumbers(0x01F600, 0x01F61E) // 😀😞
        .ignoreAllWhitespace()
        .build();

    final var actual = shortTypeParser.parseToShort(value);
    assertThat(actual).isEqualTo(expected);
  }
}