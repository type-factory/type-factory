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
package org.typefactory.language;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.typefactory.Subset.CodePointRange;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

class Subset_Japanese_ja_Hani_jinmeiyo_Test {

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
