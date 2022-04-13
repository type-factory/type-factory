package land.artli.easytype;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
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
    assertThat(subset.doesNotContain((int)'9')).isEqualTo(!containsValue);
  }
}
