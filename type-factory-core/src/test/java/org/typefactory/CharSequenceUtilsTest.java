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
