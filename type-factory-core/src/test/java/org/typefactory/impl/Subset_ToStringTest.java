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
import org.typefactory.Subset;
import org.typefactory.testutils.CodePointArrayConverter;

class Subset_ToStringTest {

  @ParameterizedTest
  @CsvSource(textBlock = """
      [a]                  | [0x61_61]
      [a, b]               | [0x61_62]
      [a, b, d]            | [0x61_62,0x64_64]
      [Σ, Τ]               | [0x3a3_3a4]
      [Σ, Τ, Ω]            | [0x3a3_3a4,0x3a9_3a9]
      [🈂, 😀]             | [0x1f202_1f202,0x1f600_1f600]
      [a, b, Σ, Τ]         | [0x61_62],[0x3a3_3a4]
      [a, b, 🈂, 😀]       | [0x61_62],[0x1f202_1f202,0x1f600_1f600]
      [Σ, Τ, 🈂, 😀]       | [0x3a3_3a4],[0x1f202_1f202,0x1f600_1f600]
      [a, b, Σ, Τ, 🈂, 😀] | [0x61_62],[0x3a3_3a4],[0x1f202_1f202,0x1f600_1f600]
      """, delimiter = '|')
  void toString_returnAsExpected(
      @ConvertWith(CodePointArrayConverter.class) final int[] codePoints,
      final String expectedToStringValue) {

    final var subsetBuilder = Subset.builder();
    for (final int codePoint : codePoints) {
      subsetBuilder.includeCodePoint(codePoint);
    }

    final var subset = subsetBuilder.build();

    assertThat(subset).hasToString(expectedToStringValue);
  }
}
