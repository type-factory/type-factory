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
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class EmptyCodePointRangeIteratorTest {

  @Test
  void hasNext_returnsFalse() {
    final var iterator = new EmptyCodePointRangeIterator();
    assertThat(iterator.hasNext()).isFalse();
  }

  @Test
  void next_throwsException() {
    final var iterator = new EmptyCodePointRangeIterator();
    assertThatExceptionOfType(NoSuchElementException.class)
        .isThrownBy(iterator::next);
  }

  @Test
  void remove_throwsException() {
    final var iterator = new EmptyCodePointRangeIterator();
    assertThatExceptionOfType(UnsupportedOperationException.class)
        .isThrownBy(iterator::remove);
  }
}
