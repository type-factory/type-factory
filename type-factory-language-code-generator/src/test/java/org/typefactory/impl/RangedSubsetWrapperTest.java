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

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.typefactory.Subset.CodePointRange;

class RangedSubsetWrapperTest {

  @Test
  void constructor_returnsEmptySubsetForNull() {
    final var subsetWrapper = new RangedSubsetWrapper(null);
    assertThat(subsetWrapper.isEmpty()).isTrue();
  }

  @Test
  void isEmpty_returnsTrue() {
    final var subsetWrapper = new RangedSubsetWrapper(RangedSubsetImpl.EMPTY_SUBSET);
    assertThat(subsetWrapper.isEmpty()).isTrue();
  }

  @Test
  void isEmpty_returnsFalse() {
    final var rangedSubset = new RangedSubsetImpl(new char[]{0x33_34}, 1, 2);
    final var subsetWrapper = new RangedSubsetWrapper(rangedSubset);
    assertThat(subsetWrapper.isEmpty()).isFalse();
  }

  @Test
  void contains_returnsTrue() {
    final var rangedSubset = new RangedSubsetImpl(new char[]{0x33_34}, 1, 2);
    final var subsetWrapper = new RangedSubsetWrapper(rangedSubset);
    assertThat(subsetWrapper.contains(0x33)).isTrue();
  }

  @Test
  void contains_returnsFalse() {
    final var rangedSubset = new RangedSubsetImpl(new char[]{0x33_34}, 1, 2);
    final var subsetWrapper = new RangedSubsetWrapper(rangedSubset);
    assertThat(subsetWrapper.contains(0x32)).isFalse();
  }

  @Test
  void ranges_returnsAsExpected() {
    final var rangedSubset = new RangedSubsetImpl(new char[]{0x33_34, 0x46_48}, 2, 5);
    final var subsetWrapper = new RangedSubsetWrapper(rangedSubset);

    final var actual = subsetWrapper.ranges();
    assertThat(actual)
        .isNotNull()
        .hasSize(2);

    final List<CodePointRange> actualCodePointRanges = new ArrayList<>();
    actual.forEach(codePointRange -> actualCodePointRanges.add(codePointRange.copy()));

    assertThat(actualCodePointRanges)
        .isNotEmpty()
        .hasSameSizeAs(actual)
        .containsExactly(
            new CodePointRange(0x33, 0x34),
            new CodePointRange(0x46, 0x48)
        );
  }

  @Test
  void numberOfCodePointRanges_returnsAsExpected() {
    final var rangedSubset = new RangedSubsetImpl(new char[]{0x33_34}, 1, 2);
    final var subsetWrapper = new RangedSubsetWrapper(rangedSubset);
    assertThat(subsetWrapper.numberOfCodePointRanges()).isEqualTo(rangedSubset.numberOfCodePointRanges());
  }

  @Test
  void numberOfCodePointsInCodePointRanges_returnsAsExpected() {
    final var rangedSubset = new RangedSubsetImpl(new char[]{0x33_34}, 1, 2);
    final var subsetWrapper = new RangedSubsetWrapper(rangedSubset);
    assertThat(subsetWrapper.numberOfCodePointsInCodePointRanges()).isEqualTo(rangedSubset.numberOfCodePointsInCodePointRanges());
  }

  @Test
  void getSingleByteCodePointRanges_returnsAsExpected() {
    final var rangedSubset = new RangedSubsetImpl(new char[]{0x33_34}, 1, 2);
    final var subsetWrapper = new RangedSubsetWrapper(rangedSubset);
    assertThat(subsetWrapper.getSingleByteCodePointRanges()).containsExactly((char)0x33_34);
  }

  @Test
  void getDoubleByteCodePointRanges_returnsAsExpected() {
    final var rangedSubset = new RangedSubsetImpl(new int[]{0x3333_3334}, 1, 2);
    final var subsetWrapper = new RangedSubsetWrapper(rangedSubset);
    assertThat(subsetWrapper.getDoubleByteCodePointRanges()).containsExactly(0x3333_3334);
  }

  @Test
  void getTripleByteCodePointRanges_returnsAsExpected() {
    final var rangedSubset = new RangedSubsetImpl(new long[]{0x00103333_00103334L}, 1, 2);
    final var subsetWrapper = new RangedSubsetWrapper(rangedSubset);
    assertThat(subsetWrapper.getTripleByteCodePointRanges()).containsExactly(0x00103333_00103334L);
  }
}
