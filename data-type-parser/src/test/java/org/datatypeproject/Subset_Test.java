package org.datatypeproject;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.datatypeproject.Subset.CodePointRange;
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
    };
    assertThat(subset.doesNotContain('A')).isEqualTo(!containsValue);
    assertThat(subset.doesNotContain((int) '9')).isEqualTo(!containsValue);
  }

  @Test
  void builder_returnsRangedSubsetBuilder() {
    final SubsetBuilder actual = Subset.builder();
    assertThat(actual)
        .isNotNull()
        .isInstanceOf(RangedSubsetBuilder.class)
        .isExactlyInstanceOf(RangedSubsetBuilderImpl.class);
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
            new ImmutableCodePointRange('A', 'C'),
            new ImmutableCodePointRange('X', 'Z')
        );
  }
}
