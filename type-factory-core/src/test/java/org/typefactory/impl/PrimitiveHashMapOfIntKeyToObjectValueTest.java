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

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class PrimitiveHashMapOfIntKeyToObjectValueTest {

  @Test
  void isEmpty_returnsTrue() {
    final var actual = new PrimitiveHashMapOfIntKeyToObjectValue();
    assertThat(actual.isEmpty()).isTrue();
  }

  @Test
  void isEmpty_returnsFalse() {
    final var actual = new PrimitiveHashMapOfIntKeyToObjectValue();
    actual.put(131, "aaa");

    assertThat(actual.isEmpty()).isFalse();
    assertThat(actual.size()).isEqualTo(1);
  }

  @Test
  void get_returnsAsExpected() {
    final var actual = new PrimitiveHashMapOfIntKeyToObjectValue();
    actual.put(131, "aaa");

    assertThat(actual.get(131)).isNotNull().isEqualTo("aaa");
    assertThat(actual.get(130)).isNull();
    assertThat(actual.get(132)).isNull();
  }

  @ParameterizedTest
  @EnumSource(TestSource.class)
  void setContainsAllValuesSorted(TestSource testSource) {

    final var actual = new PrimitiveHashMapOfIntKeyToObjectValue();
    for (Map.Entry<Integer, String> entry : testSource.map.entrySet()) {
      actual.put(entry.getKey(), entry.getValue());
    }

    assertThat(actual.size()).isEqualTo(testSource.expectedSize);
    assertThat(actual.isEmpty()).isEqualTo(testSource.expectedIsEmpty);
    assertThat(actual.keys()).containsExactly(testSource.expectedKeys);

    for (Map.Entry<Integer, String> entry : testSource.map.entrySet()) {
      assertThat(actual.get(entry.getKey()))
          .isNotNull()
          .isEqualTo(entry.getValue());
    }
  }

  private enum TestSource {

    ONE_ENTRY(Map.of(3, "AA")),

    TWO_ENTRIES(Map.of(3, "AA", 131, "BB")),

    THREE_ENTRIES(Map.of(3, "AA", 131, "BB", 191, "CC")),

    FOURTEEN_ENTRIES(Map.ofEntries(
        entry(179, "ab"), entry(191, "ac"), entry(193, "ad"), entry(211, "ae"), entry(229, "af"),
        entry(283, ""), entry(307, ""), entry(311, ""), entry(331, ""), entry(349, ""),
        entry(419, ""), entry(431, ""), entry(433, ""), entry(449, ""))),

    FIFTEEN_ENTRIES(Map.ofEntries(
        entry(179, "ab"), entry(191, "ac"), entry(193, "ad"), entry(211, "ae"), entry(229, "af"),
        entry(283, ""), entry(307, ""), entry(311, ""), entry(331, ""), entry(349, ""),
        entry(419, ""), entry(431, ""), entry(433, ""), entry(449, ""), entry(463, ""))),

    SIXTEEN_ENTRIES(Map.ofEntries(
        entry(179, "ab"), entry(191, "ac"), entry(193, "ad"), entry(211, "ae"), entry(229, "af"),
        entry(283, ""), entry(307, ""), entry(311, ""), entry(331, ""), entry(349, ""),
        entry(419, ""), entry(431, ""), entry(433, ""), entry(449, ""), entry(463, ""),
        entry(547, ""))),

    THIRTY_ENTRIES(Map.ofEntries(
        entry(179, "ab"), entry(191, "ac"), entry(193, "ad"), entry(211, "ae"), entry(229, "af"),
        entry(283, ""), entry(307, ""), entry(311, ""), entry(331, ""), entry(349, ""),
        entry(419, ""), entry(431, ""), entry(433, ""), entry(449, ""), entry(463, ""),
        entry(547, ""), entry(563, ""), entry(569, ""), entry(587, ""), entry(601, ""),
        entry(661, ""), entry(677, ""), entry(683, ""), entry(709, ""), entry(733, ""),
        entry(811, ""), entry(823, ""), entry(827, ""), entry(853, ""), entry(863, ""))),
    ;
    private final Map<Integer, String> map;
    private final int expectedSize;
    private final boolean expectedIsEmpty;
    private final int[] expectedKeys;


    TestSource(final Map<Integer, String> map) {
      this.map = Map.copyOf(map);
      this.expectedSize = map.size();
      this.expectedIsEmpty = map.isEmpty();
      this.expectedKeys = map.keySet().stream().mapToInt(Integer::intValue).sorted().toArray();
    }
  }
}
