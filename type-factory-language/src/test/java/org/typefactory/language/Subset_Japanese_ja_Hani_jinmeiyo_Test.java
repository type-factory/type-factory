package org.typefactory.language;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.typefactory.Subset.CodePointRange;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class Subset_Japanese_ja_Hani_jinmeiyo_Test {

  static final List JAPANESE_JA_HANI_X_JINMEIYO_RANGES = new ArrayList();

  static {
    Letters.JAPANESE_ja_Hani_x_jinmeiyo.ranges().forEach(codePointRange ->
        JAPANESE_JA_HANI_X_JINMEIYO_RANGES.add(codePointRange.copy()));
  }

  @ParameterizedTest
  @CsvFileSource(
      resources = "/letters/japanese-jinmeiyo.psv",
  delimiter = '|')
  void contains_shouldReturnTrueForJapaneseJaHaniJinmeiyoSubset(final int unicode, final char ch) {
    assertThat(Letters.JAPANESE_ja_Hani_x_jinmeiyo.contains(unicode)).isTrue();
    assertThat(Letters.JAPANESE_ja_Hani_x_jinmeiyo.contains(ch)).isTrue();
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
