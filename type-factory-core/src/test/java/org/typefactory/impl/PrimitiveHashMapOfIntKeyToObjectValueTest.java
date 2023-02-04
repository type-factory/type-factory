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
import static org.typefactory.impl.PrimitiveHashMapOfIntKeyToObjectValue.INITIAL_CAPACITY;

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

  @Test
  void put_canOverrideWithNullValue() {
    final var actual = new PrimitiveHashMapOfIntKeyToObjectValue();

    actual.put(131, "aaa");
    assertThat(actual.keySet()).containsExactly(131);
    assertThat(actual.get(131)).isNotNull().isEqualTo("aaa");

    actual.put(131, null);
    assertThat(actual.keySet()).containsExactly(131);
    assertThat(actual.get(131)).isNull();
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
    assertThat(actual.keySet()).containsExactly(testSource.expectedKeys);

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

    TEN_ENTRIES_IN_SAME_BUCKET(Map.ofEntries(
        entry(0 * INITIAL_CAPACITY, "ab"),
        entry(1 * INITIAL_CAPACITY, "ac"),
        entry(2 * INITIAL_CAPACITY, "ad"),
        entry(3 * INITIAL_CAPACITY, "ae"),
        entry(4 * INITIAL_CAPACITY, "af"),
        entry(5 * INITIAL_CAPACITY, "bb"),
        entry(6 * INITIAL_CAPACITY, "bc"),
        entry(7 * INITIAL_CAPACITY, "bd"),
        entry(8 * INITIAL_CAPACITY, "be"),
        entry(9 * INITIAL_CAPACITY, "bf"))),

    FOURTEEN_ENTRIES(Map.ofEntries(
        entry(179, "ab"), entry(191, "ac"), entry(193, "ad"), entry(211, "ae"), entry(229, "af"),
        entry(283, "bb"), entry(307, "bc"), entry(311, "bd"), entry(331, "be"), entry(349, "bf"),
        entry(419, "cb"), entry(431, "cc"), entry(433, "cd"), entry(449, "ce"))),

    FIFTEEN_ENTRIES(Map.ofEntries(
        entry(179, "ab"), entry(191, "ac"), entry(193, "ad"), entry(211, "ae"), entry(229, "af"),
        entry(283, "bb"), entry(307, "bc"), entry(311, "bd"), entry(331, "be"), entry(349, "bf"),
        entry(419, "cb"), entry(431, "cc"), entry(433, "cd"), entry(449, "ce"), entry(463, "cf"))),

    SIXTEEN_ENTRIES(Map.ofEntries(
        entry(179, "ab"), entry(191, "ac"), entry(193, "ad"), entry(211, "ae"), entry(229, "af"),
        entry(283, "bb"), entry(307, "bc"), entry(311, "bd"), entry(331, "be"), entry(349, "bf"),
        entry(419, "cb"), entry(431, "cc"), entry(433, "cd"), entry(449, "ce"), entry(463, "cf"),
        entry(547, "db"))),

    THIRTY_ENTRIES(Map.ofEntries(
        entry(179, "ab"), entry(191, "ac"), entry(193, "ad"), entry(211, "ae"), entry(229, "af"),
        entry(283, "bb"), entry(307, "bc"), entry(311, "bd"), entry(331, "be"), entry(349, "bf"),
        entry(419, "cb"), entry(431, "cc"), entry(433, "cd"), entry(449, "ce"), entry(463, "cf"),
        entry(547, "db"), entry(563, "dc"), entry(569, "dd"), entry(587, "de"), entry(601, "df"),
        entry(661, "eb"), entry(677, "ec"), entry(683, "ed"), entry(709, "ee"), entry(733, "ef"),
        entry(811, "fb"), entry(823, "fc"), entry(827, "fd"), entry(853, "fe"), entry(863, "ff"))),
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
