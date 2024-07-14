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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.testutils.IntArrayConverter;

class PrimitiveSortedSetOfIntTest {

  @ParameterizedTest
  @CsvSource(textBlock = """
      [3]                                | [3]
      [3,  3]                            | [3]
      [2,  3]                            | [2, 3]
      [3,  2]                            | [2, 3]
      [2,  2,  3]                        | [2, 3]
      [2,  3,  3]                        | [2, 3]
      [2,  3, 90]                        | [2, 3, 90]
      [3,  2, 90]                        | [2, 3, 90]
      [3, 90,  2]                        | [2, 3, 90]
      [90, 2,  3]                        | [2, 3, 90]
      [90, 3,  2]                        | [2, 3, 90]
      [3, 88,  2, 90]                    | [2, 3, 88, 90]
      [3, 88, 44,  2, 90]                | [2, 3, 44, 88, 90]
      [3, 88, 44,  2, 90,  3]            | [2, 3, 44, 88, 90]
      [3, 88, 44, 88,  2, 90]            | [2, 3, 44, 88, 90]
      [3, 88, 44,  2,  2, 90]            | [2, 3, 44, 88, 90]
      [3, 88, 44,  2, 90, 90]            | [2, 3, 44, 88, 90]
      [3, 88, 44,  2, 90,  4]            | [2, 3,  4, 44, 88, 90]
      [3, 88, 44, 45,  2, 90,  4]        | [2, 3,  4, 44, 45, 88, 90]
      [3, 88, 44, 45, 55,  2, 90,  4]    | [2, 3,  4, 44, 45, 55, 88, 90]
      [3, 88, 44, 45, 55, 54,  2, 90, 4] | [2, 3,  4, 44, 45, 54, 55, 88, 90]
      """, delimiter = '|')
  void setContainsAllValuesSorted(
      @ConvertWith(IntArrayConverter.class) final int [] values,
      @ConvertWith(IntArrayConverter.class) final int [] expected) {

    final var primitiveSortedSetOfInt = new PrimitiveSortedSetOfInt();
    for (int value : values) {
      primitiveSortedSetOfInt.add(value);
    }

    assertThat(primitiveSortedSetOfInt.size()).isEqualTo(expected.length);

    assertThat(primitiveSortedSetOfInt.toArray())
        .hasSize(expected.length)
        .containsExactly(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      INITIAL_VALUES                     | VALUES_TO_REMOVE | EXPECTED
      [3]                                | [3]              | []
      [3,  3]                            | [3]              | []
      [2,  3]                            | [2]              | [3]
      [3,  2]                            | [3]              | [2]
      [2,  2,  3]                        | [2]              | [3]
      [2,  3,  3]                        | [3]              | [2]
      [2,  3, 90]                        | [3]              | [2, 90]
      [3,  2, 90]                        | [3]              | [2, 90]
      [3, 90,  2]                        | [3]              | [2, 90]
      [90, 2,  3]                        | [3]              | [2, 90]
      [90, 3,  2]                        | [3]              | [2, 90]
      [3, 88,  2, 90]                    | [90]             | [2, 3, 88]
      [3, 88, 44,  2, 90]                | [44]             | [2, 3, 88, 90]
      [3, 88, 44,  2, 90,  3]            | [44]             | [2, 3, 88, 90]
      [3, 88, 44, 88,  2, 90]            | [44]             | [2, 3, 88, 90]
      [3, 88, 44,  2,  2, 90]            | [44]             | [2, 3, 88, 90]
      [3, 88, 44,  2, 90, 90]            | [44]             | [2, 3, 88, 90]
      [3, 88, 44,  2, 90,  4]            | [44]             | [2, 3,  4, 88, 90]
      [3, 88, 44, 45,  2, 90,  4]        | [44, 88]         | [2, 3,  4, 45, 90]
      [3, 88, 44, 45, 55,  2, 90,  4]    | [44, 55]         | [2, 3,  4, 45, 88, 90]
      [3, 88, 44, 45, 55, 54,  2, 90, 4] | [44, 55]         | [2, 3,  4, 45, 54, 88, 90]
      """, delimiter = '|', useHeadersInDisplayName = true)
  void removeContainsAllRemainingValuesSorted(
      @ConvertWith(IntArrayConverter.class) final int [] values,
      @ConvertWith(IntArrayConverter.class) final int [] valuesToRemove,
      @ConvertWith(IntArrayConverter.class) final int [] expected) {

    final var primitiveSortedSetOfInt = new PrimitiveSortedSetOfInt();
    for (int value : values) {
      primitiveSortedSetOfInt.add(value);
    }
    for (int valueToRemove : valuesToRemove) {
      final boolean removeResult = primitiveSortedSetOfInt.remove(valueToRemove);
      assertThat(removeResult).isTrue();
    }

    assertThat(primitiveSortedSetOfInt.size()).isEqualTo(expected.length);

    assertThat(primitiveSortedSetOfInt.toArray())
        .hasSize(expected.length)
        .containsExactly(expected)
        .doesNotContain(valuesToRemove);
  }

  @Test
  void remove_NonExistingValue_ReturnsFalse() {
    final var set = new PrimitiveSortedSetOfInt();
    set.add(1);
    set.add(3);

    boolean result = set.remove(2);

    assertThat(result).isFalse();
    assertThat(set.toArray()).containsExactly(1, 3);
  }

}
