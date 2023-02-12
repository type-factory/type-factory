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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TypeParserImpl_Test {

  @Test
  void inclusiveStartIndexIgnoringLeadingWhitespace_throwsExceptionIfCodePointsArgIsNull() {
    assertThatExceptionOfType(NullPointerException.class)
        .isThrownBy(() -> TypeParserImpl.inclusiveStartIndexIgnoringLeadingWhitespace(null, 1));
  }

  @Test
  void exclusiveEndIndexIgnoringTrailingWhitespace_throwsExceptionIfCodePointsArgIsNull() {
    assertThatExceptionOfType(NullPointerException.class)
        .isThrownBy(() -> TypeParserImpl.exclusiveEndIndexIgnoringTrailingWhitespace(null, 1));
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      ''                | 0 | 0
      '  '              | 0 | 0
      'AA'              | 0 | 2
      'BB '             | 0 | 2
      ' CC'             | 1 | 3
      ' DD '            | 1 | 3
      '  EE  '          | 2 | 4
      '   FF   '        | 3 | 5
      ' GG HH '         | 1 | 6
      '\tJJ JJ\t'       | 1 | 6
      ' \tKK KK\t '     | 2 | 7
      ' \nLL LL\n '     | 2 | 7
      ' \n\tMM MM\t\n ' | 3 | 8
      """, delimiter = '|', nullValues = "null")
  void inclusiveStartIndexIgnoringLeadingWhitespace_exclusiveEndIndexIgnoringTrailingWhitespace_returnsAsExpected(
      final String value, final int expectedStartIndex, final int expectedEndIndex) {

    final var codePoints = value.codePoints().toArray();
    final var codePointsLength = codePoints.length;
    final var exclusiveEndIndex = TypeParserImpl.exclusiveEndIndexIgnoringTrailingWhitespace(codePoints, codePointsLength);
    final var inclusiveStartIndex = TypeParserImpl.inclusiveStartIndexIgnoringLeadingWhitespace(codePoints, exclusiveEndIndex);

    assertThatObject(inclusiveStartIndex).isEqualTo(expectedStartIndex);
    assertThatObject(exclusiveEndIndex).isEqualTo(expectedEndIndex);
  }

}
