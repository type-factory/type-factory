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

class PrimitiveHashMapOfIntKeyToIntArrayValueTest {

  @Test
  void toString_returnsEmptyForEmptyMap() {
    final var map = new PrimitiveHashMapOfIntKeyToIntArrayValue();
    final var actual = map.toString();
    assertThat(actual).isEmpty();
  }

  @Test
  void toString_returnsStringForOneEntry() {
    final var map = new PrimitiveHashMapOfIntKeyToIntArrayValue();
    map.put('a', "zz".codePoints().toArray());
    final var actual = map.toString();
    assertThat(actual).isEqualTo("0x61 [a] ⟶ zz");
  }

  @Test
  void toString_returnsStringForTwoEntries() {
    final var map = new PrimitiveHashMapOfIntKeyToIntArrayValue();
    map.put('a', "zz".codePoints().toArray());
    map.put('b', "yyy".codePoints().toArray());
    final var actual = map.toString();
    assertThat(actual).isEqualTo("""
        0x61 [a] ⟶ zz
        0x62 [b] ⟶ yyy""");
  }
}
