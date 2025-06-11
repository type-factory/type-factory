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
