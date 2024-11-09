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
import static org.typefactory.impl.PrimitiveHashMapOfIntKeyToIntValueBuilder.DEFAULT_INITIAL_CAPACITY;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

class PrimitiveHashMapOfIntKeyToIntValueTest {

  private static final Logger logger = Logger.getLogger(PrimitiveHashMapOfIntKeyToIntValueTest.class.getName());

  @Test
  void isEmpty_returnsTrue() {
    final var mapBuilder = PrimitiveHashMapOfIntKeyToIntValue.builder();
    assertThat(mapBuilder.isEmpty()).isTrue();

    final var map = mapBuilder.build();
    assertThat(map.isEmpty()).isTrue();
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
    final var mapBuilder = PrimitiveHashMapOfIntKeyToIntValue.builder();
    mapBuilder.put(key, value);

    assertThat(mapBuilder.isEmpty()).isFalse();
    assertThat(mapBuilder.size()).isEqualTo(1);

    final var map = mapBuilder.build();
    assertThat(map.isEmpty()).isFalse();
    assertThat(map.size()).isEqualTo(1);
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
    final var mapBuilder = PrimitiveHashMapOfIntKeyToIntValue.builder();
    mapBuilder.put(key, value);

    assertThat(mapBuilder.contains(key)).isTrue();
    for (int i = -1001; i <= 1001; ++i) {
      if (i != key) {
        assertThat(mapBuilder.contains(i)).isFalse();
      }
    }

    final var map = mapBuilder.build();
    assertThat(map.contains(key)).isTrue();
    for (int i = -1001; i <= 1001; ++i) {
      if (i != key) {
        assertThat(map.contains(i)).isFalse();
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
    final var mapBuilder = PrimitiveHashMapOfIntKeyToIntValue.builder();
    mapBuilder.put(key, value);

    assertThat(mapBuilder.get(key)).isEqualTo(value);
    for (int i = -1001; i <= 1001; ++i) {
      if (i != key) {
        assertThat(mapBuilder.get(i)).isEqualTo(Integer.MIN_VALUE);
      }
    }

    final var map = mapBuilder.build();
    assertThat(map.get(key)).isEqualTo(value);
    for (int i = -1001; i <= 1001; ++i) {
      if (i != key) {
        assertThat(map.get(i)).isEqualTo(Integer.MIN_VALUE);
      }
    }
  }

  @Test
  void put_canOverride() {
    final var key = 131;
    final var expected1 = 333;
    final var expected2 = 444;
    final var mapBuilder = PrimitiveHashMapOfIntKeyToIntValue.builder();

    mapBuilder.put(key, expected1);
    assertThat(mapBuilder.keySet()).containsExactly(key);
    assertThat(mapBuilder.get(key)).isEqualTo(expected1);

    mapBuilder.put(key, expected2);
    assertThat(mapBuilder.keySet()).containsExactly(key);
    assertThat(mapBuilder.get(key)).isEqualTo(expected2);

    mapBuilder.put(key, 0);
    assertThat(mapBuilder.keySet()).containsExactly(key);
    assertThat(mapBuilder.get(key)).isZero();
  }

  @Test
  void remove_ExistingKey_ReturnsValueAndRemovesEntry() {
    final var mapBuilder = PrimitiveHashMapOfIntKeyToIntValue.builder();
    int key = 10;
    int value = 20;
    mapBuilder.put(key, value);

    int removedValue = mapBuilder.remove(key);

    assertThat(removedValue).isEqualTo(value);
    assertThat(mapBuilder.contains(key)).isFalse();

    final var map = mapBuilder.build();
    assertThat(map.contains(key)).isFalse();
  }

  @Test
  void remove_NonExistingKey_ReturnsMinValue() {
    final var mapBuilder = PrimitiveHashMapOfIntKeyToIntValue.builder();
    int nonExistingKey = 15;

    int result = mapBuilder.remove(nonExistingKey);

    assertThat(result).isEqualTo(Integer.MIN_VALUE);
  }

  @Test
  void remove_ExistingKey_DecreasesSize() {
    final var mapBuilder = PrimitiveHashMapOfIntKeyToIntValue.builder();
    mapBuilder.put(1, 100);
    mapBuilder.put(2, 200);

    mapBuilder.remove(1);

    assertThat(mapBuilder.size()).isEqualTo(1);

    final var map = mapBuilder.build();
    assertThat(map.size()).isEqualTo(1);
  }

  @Test
  void remove_ExistingKey_AffectsContainsMethod() {
    final var mapBuilder = PrimitiveHashMapOfIntKeyToIntValue.builder();
    int key = 5;
    mapBuilder.put(key, 50);

    mapBuilder.remove(key);

    assertThat(mapBuilder.contains(key)).isFalse();

    final var map = mapBuilder.build();
    assertThat(map.contains(key)).isFalse();
  }

  @Test
  void toString_returnsEmptyForEmptyMap() {
    final var mapBuilder = PrimitiveHashMapOfIntKeyToIntValue.builder();
    final var actual = mapBuilder.toString();
    assertThat(actual).isEmpty();
  }

  @Test
  void toString_returnsStringForOneEntry() {
    final var mapBuilder = PrimitiveHashMapOfIntKeyToIntValue.builder();
    mapBuilder.put('a', 'z');
    final var actual1 = mapBuilder.toString();
    assertThat(actual1).isEqualTo("0x00000061 ⟶ 0x0000007a, a ⟶ z");

    final var map = mapBuilder.build();
    final var actual2 = map.toString();
    assertThat(actual2).isEqualTo(actual1);
  }

  @Test
  void toString_returnsStringForTwoEntries() {
    final var mapBuilder = PrimitiveHashMapOfIntKeyToIntValue.builder();
    mapBuilder.put('a', 'w');
    mapBuilder.put('b', 'x');
    mapBuilder.put('c', 'y');
    mapBuilder.put('c', Integer.MIN_VALUE);
    mapBuilder.put('d', 'z');
    final var actual1 = mapBuilder.toString();
    assertThat(actual1).isEqualTo("""
        0x00000061 ⟶ 0x00000077, a ⟶ w
        0x00000062 ⟶ 0x00000078, b ⟶ x
        0x00000064 ⟶ 0x0000007a, d ⟶ z""");

    final var map = mapBuilder.build();
    final var actual2 = map.toString();
    assertThat(actual2).isEqualTo(actual1);
  }

  @Test
  void toString_returnsStringForMultipleEntriesWithAnExplicitRemove() {
    final var mapBuilder = PrimitiveHashMapOfIntKeyToIntValue.builder();
    mapBuilder.put('a', 'w');
    mapBuilder.put('b', 'x');
    mapBuilder.put('c', 'y');
    mapBuilder.remove('c');
    mapBuilder.put('d', 'z');
    final var actual1 = mapBuilder.toString();
    assertThat(actual1).isEqualTo("""
        0x00000061 ⟶ 0x00000077, a ⟶ w
        0x00000062 ⟶ 0x00000078, b ⟶ x
        0x00000064 ⟶ 0x0000007a, d ⟶ z""");

    final var map = mapBuilder.build();
    final var actual2 = map.toString();
    assertThat(actual2).isEqualTo(actual1);
  }

  @Test
  void toString_returnsStringForMultipleEntriesWithAnImpliedRemove() {
    final var mapBuilder = PrimitiveHashMapOfIntKeyToIntValue.builder();
    mapBuilder.put('a', 'w');
    mapBuilder.put('b', 'x');
    mapBuilder.put('c', 'y');
    // Set value to Integer.MIN_VALUE implies a remove because Integer.MIN_VALUE is a sentinel value for no-value
    mapBuilder.put('c', Integer.MIN_VALUE);
    mapBuilder.put('d', 'z');
    final var actual1 = mapBuilder.toString();
    assertThat(actual1).isEqualTo("""
        0x00000061 ⟶ 0x00000077, a ⟶ w
        0x00000062 ⟶ 0x00000078, b ⟶ x
        0x00000064 ⟶ 0x0000007a, d ⟶ z""");

    final var map = mapBuilder.build();
    final var actual2 = map.toString();
    assertThat(actual2).isEqualTo(actual1);
  }

  @Test
  void toDataStructureString_ShouldReturnEmptyString_WhenNoEntries() {
    final var mapBuilder = PrimitiveHashMapOfIntKeyToIntValue.builder();
    final var actual = mapBuilder.toDataStructureString();
    assertThat(actual).isEqualTo("""
        int[][] keys = new int[][]{
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
        };
        int[][] values = new int[][]{
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
        };
        """);
  }

  @Test
  void toDataStructureString_ShouldReturnCorrectStringRepresentation() {
    final var mapBuilder = PrimitiveHashMapOfIntKeyToIntValue.builder();
    mapBuilder.put('a', 'w');
    mapBuilder.put('b', 'x');
    mapBuilder.put('c', 'y');
    // Set value to Integer.MIN_VALUE implies a remove because Integer.MIN_VALUE is a sentinel value for no-value
    mapBuilder.put('c', Integer.MIN_VALUE);
    mapBuilder.put('d', 'z');
    final var actual = mapBuilder.toDataStructureString();
    assertThat(actual).isEqualTo("""
        int[][] keys = new int[][]{
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            new int[]{0x00000061},
            new int[]{0x00000062},
            new int[]{},
            new int[]{0x00000064},
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
        };
        int[][] values = new int[][]{
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            new int[]{0x00000077},
            new int[]{0x00000078},
            new int[]{},
            new int[]{0x0000007a},
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
        };
        """);
  }

  @ParameterizedTest
  @EnumSource(PrimitiveHashMapOfIntKeyToIntValueTest.TestSource.class)
  void setContainsAllValuesSorted(PrimitiveHashMapOfIntKeyToIntValueTest.TestSource testSource) {

    final var mapBuilder = PrimitiveHashMapOfIntKeyToIntValue.builder();
    for (Entry<Integer, Integer> entry : testSource.map.entrySet()) {
      mapBuilder.put(entry.getKey(), entry.getValue());
    }

    assertThat(mapBuilder.size()).isEqualTo(testSource.expectedSize);
    assertThat(mapBuilder.isEmpty()).isEqualTo(testSource.expectedIsEmpty);
    assertThat(mapBuilder.keySet()).containsExactly(testSource.expectedKeys);

    for (Entry<Integer, Integer> entry : testSource.map.entrySet()) {
      assertThat(mapBuilder.contains(entry.getKey())).isTrue();
      assertThat(mapBuilder.get(entry.getKey())).isEqualTo(entry.getValue());
    }

    for (int i = 0; i < mapBuilder.size() * 4; ++i) {
      if (!testSource.map.containsKey(i)) {
        assertThat(mapBuilder.contains(i)).isFalse();
        assertThat(mapBuilder.get(i)).isEqualTo(Integer.MIN_VALUE);
      }
    }

    final var map = mapBuilder.build();
    assertThat(map).isInstanceOf(testSource.expectedInstanceType);
    logger.fine("\n\nmap instanceof: " + map.getClass().getSimpleName() + "\n" + map.toString() + "\n" + map.toDataStructureString());

    assertThat(map.toString()).isEqualTo(mapBuilder.toString());

    assertThat(map.size()).isEqualTo(testSource.expectedSize);
    assertThat(map.isEmpty()).isEqualTo(testSource.expectedIsEmpty);
    assertThat(map.keySet()).containsExactly(testSource.expectedKeys);

    for (Entry<Integer, Integer> entry : testSource.map.entrySet()) {
      assertThat(map.contains(entry.getKey())).isTrue();
      assertThat(map.get(entry.getKey())).isEqualTo(entry.getValue());
    }

    for (int i = 0; i < map.size() * 4; ++i) {
      if (!testSource.map.containsKey(i)) {
        assertThat(map.contains(i)).isFalse();
        assertThat(map.get(i)).isEqualTo(Integer.MIN_VALUE);
      }
    }
  }

  private enum TestSource {

    ZERO_ENTRY(OptimalImmutablePrimitiveHashMapOfIntKeyToIntValue.class,
        Map.of()),

    ONE_ENTRY(OptimalImmutablePrimitiveHashMapOfIntKeyToIntValue.class,
        Map.of(3, (int) 'a')),

    TWO_ENTRIES(OptimalImmutablePrimitiveHashMapOfIntKeyToIntValue.class,
        Map.of(3, (int) 'a', 131, (int) 'b')),

    THREE_ENTRIES(OptimalImmutablePrimitiveHashMapOfIntKeyToIntValue.class,
        Map.of(3, (int) 'a', 131, (int) 'b', 191, (int) 'c')),

    FOURTEEN_ENTRIES(OptimalImmutablePrimitiveHashMapOfIntKeyToIntValue.class,
        Map.<Integer, Integer>ofEntries(
            entry(179, (int) 'a'), entry(191, (int) 'b'), entry(193, (int) 'c'), entry(211, (int) 'd'), entry(229, (int) 'e'),
            entry(283, (int) 'f'), entry(307, (int) 'g'), entry(311, (int) 'h'), entry(331, (int) 'i'), entry(349, (int) 'j'),
            entry(419, (int) 'k'), entry(431, (int) 'l'), entry(433, (int) 'm'), entry(449, (int) 'n'))),

    FIFTEEN_ENTRIES(OptimalImmutablePrimitiveHashMapOfIntKeyToIntValue.class,
        Map.<Integer, Integer>ofEntries(
            entry(179, (int) 'a'), entry(191, (int) 'b'), entry(193, (int) 'c'), entry(211, (int) 'd'), entry(229, (int) 'e'),
            entry(283, (int) 'f'), entry(307, (int) 'g'), entry(311, (int) 'h'), entry(331, (int) 'i'), entry(349, (int) 'j'),
            entry(419, (int) 'k'), entry(431, (int) 'l'), entry(433, (int) 'm'), entry(449, (int) 'n'), entry(463, (int) 'o'))),

    SIXTEEN_ENTRIES(OptimalImmutablePrimitiveHashMapOfIntKeyToIntValue.class,
        Map.<Integer, Integer>ofEntries(
            entry(179, (int) 'a'), entry(191, (int) 'b'), entry(193, (int) 'c'), entry(211, (int) 'd'), entry(229, (int) 'e'),
            entry(283, (int) 'f'), entry(307, (int) 'g'), entry(311, (int) 'h'), entry(331, (int) 'i'), entry(349, (int) 'j'),
            entry(419, (int) 'k'), entry(431, (int) 'l'), entry(433, (int) 'm'), entry(449, (int) 'n'), entry(463, (int) 'o'),
            entry(547, (int) 'A'))),

    TWENTY_ENTRIES_IN_SAME_BUCKET(OptimalImmutablePrimitiveHashMapOfIntKeyToIntValue.class,
        Map.<Integer, Integer>ofEntries(
            entry(0 * DEFAULT_INITIAL_CAPACITY, (int) 'b'),
            entry(1 * DEFAULT_INITIAL_CAPACITY, (int) 'c'),
            entry(2 * DEFAULT_INITIAL_CAPACITY, (int) 'd'),
            entry(3 * DEFAULT_INITIAL_CAPACITY, (int) 'e'),
            entry(4 * DEFAULT_INITIAL_CAPACITY, (int) 'f'),
            entry(5 * DEFAULT_INITIAL_CAPACITY, (int) 'g'),
            entry(6 * DEFAULT_INITIAL_CAPACITY, (int) 'h'),
            entry(7 * DEFAULT_INITIAL_CAPACITY, (int) 'i'),
            entry(8 * DEFAULT_INITIAL_CAPACITY, (int) 'j'),
            entry(9 * DEFAULT_INITIAL_CAPACITY, (int) 'k'),
            entry(10 * DEFAULT_INITIAL_CAPACITY, (int) 'l'),
            entry(11 * DEFAULT_INITIAL_CAPACITY, (int) 'm'),
            entry(12 * DEFAULT_INITIAL_CAPACITY, (int) 'n'),
            entry(13 * DEFAULT_INITIAL_CAPACITY, (int) 'o'),
            entry(14 * DEFAULT_INITIAL_CAPACITY, (int) 'p'),
            entry(15 * DEFAULT_INITIAL_CAPACITY, (int) 'q'),
            entry(16 * DEFAULT_INITIAL_CAPACITY, (int) 'r'),
            entry(17 * DEFAULT_INITIAL_CAPACITY, (int) 's'),
            entry(18 * DEFAULT_INITIAL_CAPACITY, (int) 't'),
            entry(19 * DEFAULT_INITIAL_CAPACITY, (int) 'u'))),

    THIRTY_ENTRIES(OptimalImmutablePrimitiveHashMapOfIntKeyToIntValue.class,
        Map.<Integer, Integer>ofEntries(
            entry(179, (int) 'a'), entry(191, (int) 'b'), entry(193, (int) 'c'), entry(211, (int) 'd'), entry(229, (int) 'e'),
            entry(283, (int) 'f'), entry(307, (int) 'g'), entry(311, (int) 'h'), entry(331, (int) 'i'), entry(349, (int) 'j'),
            entry(419, (int) 'k'), entry(431, (int) 'l'), entry(433, (int) 'm'), entry(449, (int) 'n'), entry(463, (int) 'o'),
            entry(547, (int) 'A'), entry(563, (int) 'B'), entry(569, (int) 'C'), entry(587, (int) 'D'), entry(601, (int) 'E'),
            entry(661, (int) 'F'), entry(677, (int) 'G'), entry(683, (int) 'H'), entry(709, (int) 'I'), entry(733, (int) 'J'),
            entry(811, (int) 'K'), entry(823, (int) 'L'), entry(827, (int) 'M'), entry(853, (int) 'N'), entry(863, (int) 'O'))),

    SIXTY_ENTRIES(ImmutablePrimitiveHashMapOfIntKeyToIntValue.class,
        Map.<Integer, Integer>ofEntries(
            entry(179, (int) 'a'), entry(191, (int) 'b'), entry(193, (int) 'c'), entry(211, (int) 'd'), entry(229, (int) 'e'),
            entry(283, (int) 'f'), entry(307, (int) 'g'), entry(311, (int) 'h'), entry(331, (int) 'i'), entry(349, (int) 'j'),
            entry(419, (int) 'k'), entry(431, (int) 'l'), entry(433, (int) 'm'), entry(449, (int) 'n'), entry(463, (int) 'o'),
            entry(547, (int) 'A'), entry(563, (int) 'B'), entry(569, (int) 'C'), entry(587, (int) 'D'), entry(601, (int) 'E'),
            entry(661, (int) 'F'), entry(677, (int) 'G'), entry(683, (int) 'H'), entry(709, (int) 'I'), entry(733, (int) 'J'),
            entry(811, (int) 'K'), entry(823, (int) 'L'), entry(827, (int) 'M'), entry(853, (int) 'N'), entry(863, (int) 'O'),

            entry(2179, 367), entry(3191, 435), entry(4193, 535), entry(5211, 635), entry(6229, 735),
            entry(2283, 378), entry(3307, 444), entry(4311, 544), entry(5331, 644), entry(6349, 744),
            entry(2419, 379), entry(3431, 456), entry(4433, 556), entry(5449, 656), entry(6463, 756),
            entry(2547, 383), entry(3563, 467), entry(4569, 567), entry(5587, 667), entry(6601, 767),
            entry(2661, 392), entry(3677, 478), entry(4683, 578), entry(5709, 678), entry(6733, 778),
            entry(2811, 399), entry(3823, 479), entry(4827, 579), entry(5853, 679), entry(6863, 779))),

    NINETY_ENTRIES(ImmutablePrimitiveHashMapOfIntKeyToIntValue.class,
        Map.<Integer, Integer>ofEntries(
            entry(179, (int) 'a'), entry(191, (int) 'b'), entry(193, (int) 'c'), entry(211, (int) 'd'), entry(229, (int) 'e'),
            entry(283, (int) 'f'), entry(307, (int) 'g'), entry(311, (int) 'h'), entry(331, (int) 'i'), entry(349, (int) 'j'),
            entry(419, (int) 'k'), entry(431, (int) 'l'), entry(433, (int) 'm'), entry(449, (int) 'n'), entry(463, (int) 'o'),
            entry(547, (int) 'A'), entry(563, (int) 'B'), entry(569, (int) 'C'), entry(587, (int) 'D'), entry(601, (int) 'E'),
            entry(661, (int) 'F'), entry(677, (int) 'G'), entry(683, (int) 'H'), entry(709, (int) 'I'), entry(733, (int) 'J'),
            entry(811, (int) 'K'), entry(823, (int) 'L'), entry(827, (int) 'M'), entry(853, (int) 'N'), entry(863, (int) 'O'),

            entry(2179, 367), entry(3191, 435), entry(4193, 535), entry(5211, 635), entry(6229, 735),
            entry(2283, 378), entry(3307, 444), entry(4311, 544), entry(5331, 644), entry(6349, 744),
            entry(2419, 379), entry(3431, 456), entry(4433, 556), entry(5449, 656), entry(6463, 756),
            entry(2547, 383), entry(3563, 467), entry(4569, 567), entry(5587, 667), entry(6601, 767),
            entry(2661, 392), entry(3677, 478), entry(4683, 578), entry(5709, 678), entry(6733, 778),
            entry(2811, 399), entry(3823, 479), entry(4827, 579), entry(5853, 679), entry(6863, 779),

            entry(12179, 1367), entry(13191, 1435), entry(14193, 1535), entry(15211, 1635), entry(16229, 1735),
            entry(12283, 1378), entry(13307, 1444), entry(14311, 1544), entry(15331, 1644), entry(16349, 1744),
            entry(12419, 1379), entry(13431, 1456), entry(14433, 1556), entry(15449, 1656), entry(16463, 1756),
            entry(12547, 1383), entry(13563, 1467), entry(14569, 1567), entry(15587, 1667), entry(16601, 1767),
            entry(12661, 1392), entry(13677, 1478), entry(14683, 1578), entry(15709, 1678), entry(16733, 1778),
            entry(12811, 1399), entry(13823, 1479), entry(14827, 1579), entry(15853, 1679), entry(16863, 1779))),
    ;
    private final Class<? extends PrimitiveHashMapOfIntKeyToIntValue> expectedInstanceType;
    private final Map<Integer, Integer> map;
    private final int expectedSize;
    private final boolean expectedIsEmpty;
    private final int[] expectedKeys;


    TestSource(
        final Class<? extends PrimitiveHashMapOfIntKeyToIntValue> expectedInstanceType,
        final Map<Integer, Integer> map) {

      final Map<Integer, Integer> temp = new HashMap<>();
      for (Entry<Integer, Integer> entry : map.entrySet()) {
        temp.put(entry.getKey(), entry.getValue());
      }
      this.expectedInstanceType = expectedInstanceType;
      this.map = Map.copyOf(temp);
      this.expectedSize = map.size();
      this.expectedIsEmpty = map.isEmpty();
      this.expectedKeys = map.keySet().stream().mapToInt(Integer::intValue).sorted().toArray();
    }
  }
}
