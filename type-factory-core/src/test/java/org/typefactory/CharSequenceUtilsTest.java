package org.typefactory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CharSequenceUtilsTest {

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {
      " ",
      "  ",
      "   ",
      " \t ",
      " \n  ",
      " \r   ",
  })
  void isBlank_shouldReturnTrue(final String value) {
    assertThat(CharSequenceUtils.isBlank(value)).isTrue();

    final var charSequence = value == null ? null : new ConcreteCharSequence(value.toCharArray());
    assertThat(CharSequenceUtils.isBlank(charSequence)).isTrue();
  }

  @ParameterizedTest
  @ValueSource(strings = {
      ".",
      "-",
      "a",
      "  .  ",
      "  -  ",
      "  a  ",
      "\uD83D\uDE00" // 😀 grinning face emoji
  })
  void isBlank_shouldReturnFalse(final String value) {

    assertThat(CharSequenceUtils.isBlank(value)).isFalse();

    final var charSequence = new ConcreteCharSequence(value.toCharArray());
    assertThat(CharSequenceUtils.isBlank(charSequence)).isFalse();
  }

  record ConcreteCharSequence(char[] characters) implements CharSequence {

    @Override
      public int length() {
        return characters == null ? 0 : characters.length;
      }

      @Override
      public char charAt(final int index) {
        if (index < 0 || index >= characters.length) {
          throw new IndexOutOfBoundsException(index);
        }
        return characters[index];
      }

      @Override
      public CharSequence subSequence(int start, int end) {
        throw new UnsupportedOperationException();
      }
    }
}
