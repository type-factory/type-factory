/*
 * Copyright © 2021-2026 Evan Toliopoulos (typefactory.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
      [a]                  | [a]
      [a, b]               | [ab]
      [a, b, c]            | [a-c]
      [a, b, d]            | [abd]
      [Σ, Τ]               | [ΣΤ]
      [Σ, Τ, Ω]            | [ΣΤΩ]
      [🈂, 😀]             | [🈂😀]
      [a, b, Σ, Τ]         | [abΣΤ]
      [a, b, 🈂, 😀]       | [ab🈂😀]
      [Σ, Τ, 🈂, 😀]       | [ΣΤ🈂😀]
      [a, b, Σ, Τ, 🈂, 😀] | [abΣΤ🈂😀]
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
