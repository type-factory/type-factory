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
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.testutils.CodePointSequenceConverter;

class TypeParserImpl_Test {

  @ParameterizedTest
  @CsvSource(textBlock = """
      ''             | 0 | 0
      ' '            | 0 | 0
      '  '           | 0 | 0
      '\t '          | 0 | 0
      '\t  '         | 0 | 0
      'A'            | 0 | 1
      'AA'           | 0 | 2
      'BB '          | 0 | 2
      'CC\t '        | 0 | 2
      ' DD '         | 1 | 3
      ' \tEE\t '     | 2 | 4
      '\t \tFF\t \t' | 3 | 5
      """, delimiter = '|', nullValues = "null")
  void endAndStartIndex_returnAsExpected(
      @ConvertWith(CodePointSequenceConverter.class) final int[] codePointSequence,
      final int expectedInclusiveStartIndex, final int expectedExclusiveEndIndex) {

    final var actualEndIndex = TypeParserImpl.exclusiveEndIndexIgnoringTrailingWhitespace(codePointSequence, codePointSequence.length);
    final var actualStartIndex = TypeParserImpl.inclusiveStartIndexIgnoringLeadingWhitespace(codePointSequence, actualEndIndex);

    assertThat(actualEndIndex).isEqualTo(expectedExclusiveEndIndex);
    assertThat(actualStartIndex).isEqualTo(expectedInclusiveStartIndex);
  }
}
