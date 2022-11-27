package org.typefactory;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class SubsetTest {

  @Test
  void static_of_usesBuilderToCreateNewSubsetWhenPassedArray() {

    final Subset subset1 = Subset.builder().includeChars('A', 'B', 'C').build();
    final Subset subset2 = Subset.builder().includeChars('X', 'Y', 'Z').build();

    final Subset actual = Subset.of(subset1, subset2);

    assertThat(actual.contains('A')).isTrue();
    assertThat(actual.contains('B')).isTrue();
    assertThat(actual.contains('C')).isTrue();
    assertThat(actual.contains('X')).isTrue();
    assertThat(actual.contains('Y')).isTrue();
    assertThat(actual.contains('Z')).isTrue();
  }

  @Test
  void static_of_usesBuilderToCreateNewSubsetWhenPassedIterable() {

    final Subset subset1 = Subset.builder().includeChars('A', 'B', 'C').build();
    final Subset subset2 = Subset.builder().includeChars('X', 'Y', 'Z').build();

    final Subset actual = Subset.of(List.of(subset1, subset2));

    assertThat(actual.contains('A')).isTrue();
    assertThat(actual.contains('B')).isTrue();
    assertThat(actual.contains('C')).isTrue();
    assertThat(actual.contains('X')).isTrue();
    assertThat(actual.contains('Y')).isTrue();
    assertThat(actual.contains('Z')).isTrue();
  }
}
