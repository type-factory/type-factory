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
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ibm.icu.text.UnicodeSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.typefactory.Category;
import org.typefactory.Subset;
import org.typefactory.Subset.SubsetBuilder;

@ExtendWith(MockitoExtension.class)
class SubsetWrapperTest {

  @Test
  void optimisedSubset_throwsExceptionForUnknownSubsetType() {

    final var unicodeSet = new UnicodeSet('0', '9', 'A', 'Z', 'a', 'z');

    final var subsetBuilder = mock(SubsetBuilder.class);
    when(subsetBuilder.build()).thenReturn(new SomeSubset());

    try (final MockedStatic<Subset> subset = Mockito.mockStatic(Subset.class)) {

      subset.when(Subset::builder).thenReturn(subsetBuilder);

      assertThatExceptionOfType(SubsetException.class)
          .isThrownBy(() -> SubsetWrapper.optimisedSubset(unicodeSet))
          .withMessage("Unknown subset type - " + SomeSubset.class.getName());
    }
  }

  @Test
  void optimisedSubset_returnsRangedSubset() {
    final var unicodeSet = new UnicodeSet('0', '9', 'A', 'Z', 'a', 'z');
    final var actual = SubsetWrapper.optimisedSubset(unicodeSet);

    assertThat(actual)
        .isNotNull()
        .isInstanceOf(CompositeSubset.class)
        .isInstanceOf(CompositeSubsetWrapper.class);

    assertThat(actual.contains('a')).isTrue();
    assertThat(actual.contains('z')).isTrue();
    assertThat(actual.contains('A')).isTrue();
    assertThat(actual.contains('Z')).isTrue();
    assertThat(actual.contains('0')).isTrue();
    assertThat(actual.contains('9')).isTrue();
  }

  @Test
  void optimisedSubset_returnsCompositeSubset() {

    final var unicodeSet = new UnicodeSet('0', '9', 'A', 'Z', 'a', 'z');

    final var subsetBuilder = mock(SubsetBuilder.class);
    final var compositeSubset = mock(CompositeSubsetImpl.class);
    when(subsetBuilder.build()).thenReturn(compositeSubset);

    try (final MockedStatic<Subset> subset = Mockito.mockStatic(Subset.class)) {

      subset.when(Subset::builder).thenReturn(subsetBuilder);

      final var actual = SubsetWrapper.optimisedSubset(unicodeSet);

      assertThat(actual)
          .isNotNull()
          .isInstanceOf(CompositeSubset.class)
          .isInstanceOf(CompositeSubsetWrapper.class);
    }
  }


//  @Test
//  void optimisedSubset_returnsOptimalHashedRangedSubset() {
//
//    final var unicodeSet = new UnicodeSet('0', '9', 'A', 'Z', 'a', 'z');
//
//    final var subsetBuilder = mock(SubsetBuilder.class);
//    final var optimalHashedRangedSubset = mock(OptimalHashedRangedSubsetImpl.class);
//    when(subsetBuilder.build()).thenReturn(optimalHashedRangedSubset);
//
//    try (final MockedStatic<Subset> subset = Mockito.mockStatic(Subset.class)) {
//
//      subset.when(Subset::builder).thenReturn(subsetBuilder);
//
//      final var actual = SubsetWrapper.optimisedSubset(unicodeSet);
//
//      assertThat(actual)
//          .isNotNull()
//          .isInstanceOf(OptimalHashedRangedSubset.class)
//          .isInstanceOf(OptimalHashedRangedSubsetWrapper.class);
//    }
//  }

  static class SomeSubset implements Subset {

    @Override
    public boolean isEmpty() {
      return true;
    }

    @Override
    public boolean contains(int codePoint) {
      return false;
    }

    @Override
    public Iterable<CodePointRange> ranges() {
      return Constants.EMPTY_CODE_POINT_RANGE_ITERABLE;
    }

    @Override
    public Iterable<Category> categories() {
      return Constants.EMPTY_CATEGORY_ITERABLE;
    }

    @Override
    public Iterable<String> strings() {
      return Constants.EMPTY_STRING_ITERABLE;
    }

    @Override
    public boolean containsString(CharSequence charSequence) {
      return false;
    }

    @Override
    public int containsString(CharSequence charSequence, int startingAtIndex) {
      return startingAtIndex;
    }

    @Override
    public int rangesSize() {
      return 0;
    }

    @Override
    public int categoriesSize() {
      return 0;
    }

    @Override
    public int stringsSize() {
      return 0;
    }

    @Override
    public int numberOfCodePointsInCodePointRanges() {
      return 0;
    }

    @Override
    public String toPattern() {
      return SubsetUtils.toPattern(
          ranges(),
          categories(),
          strings());
    }
  }
}
