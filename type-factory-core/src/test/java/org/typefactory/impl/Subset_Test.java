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
import java.util.Collection;
import java.util.List;
import org.typefactory.Subset.CodePointRange;
import org.typefactory.Subset;
import org.typefactory.Subset.SubsetBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Subset_Test {

  @ParameterizedTest
  @ValueSource(booleans = {
      false,
      true
  })
  void isNotEmpty_returnOppositeOfIsEmpty(final boolean isEmptyValue) {
    final Subset subset = new Subset() {
      @Override
      public Collection<CodePointRange> ranges() {
        return null;
      }

      @Override
      public boolean isEmpty() {
        return isEmptyValue;
      }

      @Override
      public boolean contains(int codePoint) {
        return false;
      }

      @Override
      public int numberOfCodePointRanges() {
        return 0;
      }

      @Override
      public int numberOfCodePointsInCodePointRanges() {
        return 0;
      }
    };
    assertThat(subset.isEmpty()).isEqualTo(isEmptyValue);
    assertThat(subset.isNotEmpty()).isEqualTo(!isEmptyValue);
  }

  @ParameterizedTest
  @ValueSource(booleans = {
      true,
      false
  })
  void contains_char_returnConsistentWithContainsInt(final boolean containsValue) {
    final Subset subset = new Subset() {
      @Override
      public Collection<CodePointRange> ranges() {
        return null;
      }

      @Override
      public boolean isEmpty() {
        return false;
      }

      @Override
      public boolean contains(int codePoint) {
        return containsValue;
      }

      @Override
      public int numberOfCodePointRanges() {
        return 0;
      }

      @Override
      public int numberOfCodePointsInCodePointRanges() {
        return 0;
      }
    };
    assertThat(subset.contains('A')).isEqualTo(containsValue);
    assertThat(subset.contains('9')).isEqualTo(containsValue);
  }

  @ParameterizedTest
  @ValueSource(booleans = {
      true,
      false
  })
  void doesNotContain_returnOppositeOfContains(final boolean containsValue) {
    final Subset subset = new Subset() {
      @Override
      public Collection<CodePointRange> ranges() {
        return null;
      }

      @Override
      public boolean isEmpty() {
        return false;
      }

      @Override
      public boolean contains(int codePoint) {
        return containsValue;
      }

      @Override
      public int numberOfCodePointRanges() {
        return 0;
      }

      @Override
      public int numberOfCodePointsInCodePointRanges() {
        return 0;
      }
    };
    assertThat(subset.doesNotContain('A')).isEqualTo(!containsValue);
    assertThat(subset.doesNotContain((int) '9')).isEqualTo(!containsValue);
  }

  @Test
  void builder_returnsSubsetBuilder() {
    final Subset.SubsetBuilder actual = Subset.builder();
    assertThat(actual)
        .isNotNull()
        .isInstanceOf(SubsetBuilder.class)
        .isExactlyInstanceOf(SubsetBuilderImpl.class);
  }

  @Test
  void toBuilder_returnsEmptySubsetWhenTheInitialSubsetIsItselfEmpty() {

    final Subset subset = new Subset() {
      @Override
      public Collection<CodePointRange> ranges() {
        return null;
      }

      @Override
      public boolean isEmpty() {
        return true;
      }

      @Override
      public boolean contains(int codePoint) {
        return false;
      }

      @Override
      public int numberOfCodePointRanges() {
        return 0;
      }

      @Override
      public int numberOfCodePointsInCodePointRanges() {
        return 0;
      }
    };

    final Subset actual = subset.toBuilder().build();
    assertThat(actual.isEmpty()).isTrue();
  }

  @Test
  void toBuilder_returnsSubsetWithSameRangesAsTheInitialSubset() {

    final Subset subset = Subset.builder().includeChars('A', 'B', 'C', 'X', 'Y', 'Z').build();
    assertThat(subset.isNotEmpty()).isTrue();

    final Subset actual = subset.toBuilder().build();
    assertThat(actual.isNotEmpty()).isTrue();

    final List<CodePointRange> actualCodePointRanges = new ArrayList<>();
    actual.ranges().forEach(codePointRange -> actualCodePointRanges.add(codePointRange.copy()));

    assertThat(actualCodePointRanges)
        .isNotEmpty()
        .hasSameSizeAs(subset.ranges())
        .hasSize(2)
        .containsExactly(
            new CodePointRange('A', 'C'),
            new CodePointRange('X', 'Z')
        );
  }
}
