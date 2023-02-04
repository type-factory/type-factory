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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.Category;
import org.typefactory.testutils.RangesAsCharArrayConverter;

class RangedSubsetWithCategoriesImplTest {

  @Test
  void isEmpty_returnsTrue() {
    final var actual = new RangedSubsetWithCategoriesImpl(
        0,
        Constants.EMPTY_CHAR_ARRAY,
        Constants.EMPTY_INT_ARRAY,
        Constants.EMPTY_LONG_ARRAY,
        0,
        0,
        0);

    assertThat(actual.isEmpty()).isTrue();
  }

  @Test
  void isEmpty_returnsFalse() {
    final var actual = new RangedSubsetWithCategoriesImpl(
        1,
        Constants.EMPTY_CHAR_ARRAY,
        Constants.EMPTY_INT_ARRAY,
        Constants.EMPTY_LONG_ARRAY,
        0,
        0,
        1);

    assertThat(actual.isEmpty()).isFalse();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      UPPERCASE_LETTER |         | a | false
      UPPERCASE_LETTER |         | A | true
      UPPERCASE_LETTER | 0x60_7a | a | true
      LOWERCASE_LETTER |         | a | true
      LOWERCASE_LETTER |         | A | false
      LOWERCASE_LETTER | 0x60_7a | A | false
      """, delimiter = '|')
  void contains_returnsAsExpected(
      final Category category,
      @ConvertWith(RangesAsCharArrayConverter.class) final char[] singleByteCodePointRanges,
      final char containsChar,
      final boolean expected) {

    final var unicodeCategoryBitFlags = 0 | category.bitMask;
    final var actual = new RangedSubsetWithCategoriesImpl(
        unicodeCategoryBitFlags,
        singleByteCodePointRanges,
        Constants.EMPTY_INT_ARRAY,
        Constants.EMPTY_LONG_ARRAY,
        singleByteCodePointRanges.length,
        SubsetUtils.numberOfCodePointsInRanges(singleByteCodePointRanges),
        1);

    assertThat(actual.contains(containsChar)).isEqualTo(expected);
  }

  @Test
  void unicodeCategoryBitFlags_returnsAsExpected() {
    final var actual = new RangedSubsetWithCategoriesImpl(
        0x5,
        Constants.EMPTY_CHAR_ARRAY,
        Constants.EMPTY_INT_ARRAY,
        Constants.EMPTY_LONG_ARRAY,
        0,
        0,
        2);

    assertThat(actual.unicodeCategoryBitFlags()).isEqualTo(5);
  }

  @Test
  void numberOfUnicodeCategories_returnsAsExpected() {
    final var actual = new RangedSubsetWithCategoriesImpl(
        0x5,
        Constants.EMPTY_CHAR_ARRAY,
        Constants.EMPTY_INT_ARRAY,
        Constants.EMPTY_LONG_ARRAY,
        0,
        0,
        2);

    assertThat(actual.numberOfUnicodeCategories()).isEqualTo(2);
  }

}
