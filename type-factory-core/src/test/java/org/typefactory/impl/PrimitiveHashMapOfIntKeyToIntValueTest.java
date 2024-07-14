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
import static org.typefactory.impl.PrimitiveHashMapOfIntKeyToIntValue.INITIAL_CAPACITY;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

class PrimitiveHashMapOfIntKeyToIntValueTest {

  @Test
  void isEmpty_returnsTrue() {
    final var actual = new PrimitiveHashMapOfIntKeyToIntValue();
    assertThat(actual.isEmpty()).isTrue();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
        KEY | VALUE
      -1000 |    99
       -100 |   -88
        -55 |    77
         55 |   -77
        100 |   -88
       1000 |    99
      """, delimiter = '|', useHeadersInDisplayName = true)
  void isEmpty_returnsFalse(final int key, final int value) {
    final var actual = new PrimitiveHashMapOfIntKeyToIntValue();
    actual.put(key, value);

    assertThat(actual.isEmpty()).isFalse();
    assertThat(actual.size()).isEqualTo(1);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
        KEY | VALUE
      -1000 |    99
       -100 |   -88
        -55 |    77
         55 |   -77
        100 |   -88
       1000 |    99
      """, delimiter = '|', useHeadersInDisplayName = true)
  void contains_returnsAsExpected(final int key, final int value) {
    final var actual = new PrimitiveHashMapOfIntKeyToIntValue();
    actual.put(key, value);

    assertThat(actual.contains(key)).isTrue();

    for (int i = -1001; i <= 1001; ++i) {
      if (i != key) {
        assertThat(actual.contains(i)).isFalse();
      }
    }
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
        KEY | VALUE
      -1000 |    99
       -100 |   -88
        -55 |    77
         55 |   -77
        100 |   -88
       1000 |    99
      """, delimiter = '|', useHeadersInDisplayName = true)
  void get_returnsAsExpected(final int key, final int value) {
    final var actual = new PrimitiveHashMapOfIntKeyToIntValue();
    actual.put(key, value);

    assertThat(actual.get(key)).isEqualTo(value);

    for (int i = -1001; i <= 1001; ++i) {
      if (i != key) {
        assertThat(actual.get(i)).isEqualTo(Integer.MIN_VALUE);
      }
    }
  }

  @Test
  void put_canOverride() {
    final var key = 131;
    final var expected1 = 333;
    final var expected2 = 444;
    final var actual = new PrimitiveHashMapOfIntKeyToIntValue();

    actual.put(key, expected1);
    assertThat(actual.keySet()).containsExactly(key);
    assertThat(actual.get(key)).isEqualTo(expected1);

    actual.put(key, expected2);
    assertThat(actual.keySet()).containsExactly(key);
    assertThat(actual.get(key)).isEqualTo(expected2);

    actual.put(key, 0);
    assertThat(actual.keySet()).containsExactly(key);
    assertThat(actual.get(key)).isZero();
  }

  @Test
  void toString_returnsEmptyForEmptyMap() {
    final var map = new PrimitiveHashMapOfIntKeyToIntValue();
    final var actual = map.toString();
    assertThat(actual).isEmpty();
  }

  @Test
  void toString_returnsStringForOneEntry() {
    final var map = new PrimitiveHashMapOfIntKeyToIntValue();
    map.put('a', 'z');
    final var actual = map.toString();
    assertThat(actual).isEqualTo("0x61 [a] ⟶ z");
  }

  @Test
  void toString_returnsStringForTwoEntries() {
    final var map = new PrimitiveHashMapOfIntKeyToIntValue();
    map.put('a', 'w');
    map.put('b', 'x');
    map.put('c', 'y');
    map.put('c', Integer.MIN_VALUE);
    map.put('d', 'z');
    final var actual = map.toString();
    assertThat(actual).isEqualTo("""
        0x61 [a] ⟶ w
        0x62 [b] ⟶ x
        0x64 [d] ⟶ z""");
  }

  @Test
  void toString_returnsStringForMultipleEntriesWithAnExplicitRemove() {
    final var map = new PrimitiveHashMapOfIntKeyToIntValue();
    map.put('a', 'w');
    map.put('b', 'x');
    map.put('c', 'y');
    map.remove('c');
    map.put('d', 'z');
    final var actual = map.toString();
    assertThat(actual).isEqualTo("""
        0x61 [a] ⟶ w
        0x62 [b] ⟶ x
        0x64 [d] ⟶ z""");
  }

  @Test
  void toString_returnsStringForMultipleEntriesWithAnImpliedRemove() {
    final var map = new PrimitiveHashMapOfIntKeyToIntValue();
    map.put('a', 'w');
    map.put('b', 'x');
    map.put('c', 'y');
    // Set value to Integer.MIN_VALUE implies a remove because Integer.MIN_VALUE is a sentinel value for no-value
    map.put('c', Integer.MIN_VALUE);
    map.put('d', 'z');
    final var actual = map.toString();
    assertThat(actual).isEqualTo("""
        0x61 [a] ⟶ w
        0x62 [b] ⟶ x
        0x64 [d] ⟶ z""");
  }

  @ParameterizedTest
  @EnumSource(PrimitiveHashMapOfIntKeyToIntValueTest.TestSource.class)
  void setContainsAllValuesSorted(PrimitiveHashMapOfIntKeyToIntValueTest.TestSource testSource) {

    final var actual = new PrimitiveHashMapOfIntKeyToIntValue();
    for (Entry<Integer, Integer> entry : testSource.map.entrySet()) {
      actual.put(entry.getKey(), entry.getValue());
    }

    assertThat(actual.size()).isEqualTo(testSource.expectedSize);
    assertThat(actual.isEmpty()).isEqualTo(testSource.expectedIsEmpty);
    assertThat(actual.keySet()).containsExactly(testSource.expectedKeys);

    for (Entry<Integer, Integer> entry : testSource.map.entrySet()) {
      assertThat(actual.contains(entry.getKey())).isTrue();
      assertThat(actual.get(entry.getKey())).isEqualTo(entry.getValue());
    }
  }

  private enum TestSource {

    ONE_ENTRY(Map.of(3, (int) 'a')),

    TWO_ENTRIES(Map.of(3, (int) 'a', 131, (int) 'b')),

    THREE_ENTRIES(Map.of(3, (int) 'a', 131, (int) 'b', 191, (int) 'c')),

    TEN_ENTRIES_IN_SAME_BUCKET(Map.ofEntries(
        entry(0 * INITIAL_CAPACITY, (int) 'b'),
        entry(1 * INITIAL_CAPACITY, (int) 'c'),
        entry(2 * INITIAL_CAPACITY, (int) 'd'),
        entry(3 * INITIAL_CAPACITY, (int) 'e'),
        entry(4 * INITIAL_CAPACITY, (int) 'f'),
        entry(5 * INITIAL_CAPACITY, (int) 'g'),
        entry(6 * INITIAL_CAPACITY, (int) 'h'),
        entry(7 * INITIAL_CAPACITY, (int) 'i'),
        entry(8 * INITIAL_CAPACITY, (int) 'j'),
        entry(9 * INITIAL_CAPACITY, (int) 'k'))),

    FOURTEEN_ENTRIES(Map.ofEntries(
        entry(179, (int) 'a'), entry(191, (int) 'b'), entry(193, (int) 'c'), entry(211, (int) 'd'), entry(229, (int) 'e'),
        entry(283, (int) 'f'), entry(307, (int) 'g'), entry(311, (int) 'h'), entry(331, (int) 'i'), entry(349, (int) 'j'),
        entry(419, (int) 'k'), entry(431, (int) 'l'), entry(433, (int) 'm'), entry(449, (int) 'n'))),

    FIFTEEN_ENTRIES(Map.ofEntries(
        entry(179, (int) 'a'), entry(191, (int) 'b'), entry(193, (int) 'c'), entry(211, (int) 'd'), entry(229, (int) 'e'),
        entry(283, (int) 'f'), entry(307, (int) 'g'), entry(311, (int) 'h'), entry(331, (int) 'i'), entry(349, (int) 'j'),
        entry(419, (int) 'k'), entry(431, (int) 'l'), entry(433, (int) 'm'), entry(449, (int) 'n'), entry(463, (int) 'o'))),

    SIXTEEN_ENTRIES(Map.ofEntries(
        entry(179, (int) 'a'), entry(191, (int) 'b'), entry(193, (int) 'c'), entry(211, (int) 'd'), entry(229, (int) 'e'),
        entry(283, (int) 'f'), entry(307, (int) 'g'), entry(311, (int) 'h'), entry(331, (int) 'i'), entry(349, (int) 'j'),
        entry(419, (int) 'k'), entry(431, (int) 'l'), entry(433, (int) 'm'), entry(449, (int) 'n'), entry(463, (int) 'o'),
        entry(547, (int) 'A'))),

    THIRTY_ENTRIES(Map.ofEntries(
        entry(179, (int) 'a'), entry(191, (int) 'b'), entry(193, (int) 'c'), entry(211, (int) 'd'), entry(229, (int) 'e'),
        entry(283, (int) 'f'), entry(307, (int) 'g'), entry(311, (int) 'h'), entry(331, (int) 'i'), entry(349, (int) 'j'),
        entry(419, (int) 'k'), entry(431, (int) 'l'), entry(433, (int) 'm'), entry(449, (int) 'n'), entry(463, (int) 'o'),
        entry(547, (int) 'A'), entry(563, (int) 'B'), entry(569, (int) 'C'), entry(587, (int) 'D'), entry(601, (int) 'E'),
        entry(661, (int) 'F'), entry(677, (int) 'G'), entry(683, (int) 'H'), entry(709, (int) 'I'), entry(733, (int) 'J'),
        entry(811, (int) 'K'), entry(823, (int) 'L'), entry(827, (int) 'M'), entry(853, (int) 'N'), entry(863, (int) 'O'))),
    ;
    private final Map<Integer, Integer> map;
    private final int expectedSize;
    private final boolean expectedIsEmpty;
    private final int[] expectedKeys;


    TestSource(final Map<Integer, Integer> map) {
      final Map<Integer, Integer> temp = new HashMap<>();
      for (Entry<Integer, Integer> entry : map.entrySet()) {
        temp.put(entry.getKey(), entry.getValue());
      }
      this.map = Map.copyOf(temp);
      this.expectedSize = map.size();
      this.expectedIsEmpty = map.isEmpty();
      this.expectedKeys = map.keySet().stream().mapToInt(Integer::intValue).sorted().toArray();
    }
  }
}
