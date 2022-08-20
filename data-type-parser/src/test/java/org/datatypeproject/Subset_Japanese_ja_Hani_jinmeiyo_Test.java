package org.datatypeproject;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class Subset_Japanese_ja_Hani_jinmeiyo_Test {

  static final List JAPANESE_JA_HANI_JINMEIYO_RANGES = new ArrayList();

  static {
    Letters.JAPANESE_ja_Hani_x_jinmeiyo.ranges().forEach(codePointRange ->
        JAPANESE_JA_HANI_JINMEIYO_RANGES.add(codePointRange.copy()));
  }

  @ParameterizedTest
  @CsvFileSource(
      resources = "/japanese-jinmeiyo.txt")
  void contains_shouldReturnTrueForJapaneseJaHaniJinmeiyoSubset(final char value) {
    assertThat(Letters.JAPANESE_ja_Hani_x_jinmeiyo.contains(value)).isTrue();
  }

  @ParameterizedTest
  @ValueSource(chars = {
      '丑', '丞', '乃', '之', '乎', '也', '云', '亘'
  })
  void ranges_(final char value) {

    assertThat(Letters.JAPANESE_ja_Hani_x_jinmeiyo.ranges())
        .map(CodePointRange::copy)
        .filteredOn(codePointRange -> codePointRange.contains(value))
        .hasSize(1);
  }

}
