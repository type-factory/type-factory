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

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.typefactory.Subset.CodePointRange;

class HashedRangedSubsetImplTest {

  @Test
  void empty_returnsTrue() {

    final var actual = new HashedRangedSubsetImpl(
        new char[0][],
        new char[0][][],
        0,
        0);

    assertThat(actual.isEmpty()).isTrue();
    assertThat(actual.isNotEmpty()).isFalse();
  }

  @ParameterizedTest
  @EnumSource(TestSource.class)
  void empty_returnsFalse(final TestSource testSource) {

    final var actual = new HashedRangedSubsetImpl(
        testSource.blockKeys,
        testSource.codePointRanges,
        testSource.numberOfCodePointRanges,
        testSource.numberOfCodePointsInCodePointRanges);

    assertThat(actual.isEmpty()).isFalse();
    assertThat(actual.isNotEmpty()).isTrue();
  }

  @ParameterizedTest
  @EnumSource(TestSource.class)
  void contains_returnsAsExpected(final TestSource testSource) {

    final var actual = new HashedRangedSubsetImpl(
        testSource.blockKeys,
        testSource.codePointRanges,
        testSource.numberOfCodePointRanges,
        testSource.numberOfCodePointsInCodePointRanges);

    for (char ch : testSource.expectedContainsCharacters) {
      assertThat(actual.contains(ch)).isTrue();
    }

    for (char ch : testSource.expectedDoesNotContainCharacters) {
      assertThat(actual.contains(ch)).isFalse();
    }
  }

  @ParameterizedTest
  @EnumSource(TestSource.class)
  void getBlockKeySet_containsExpectedBlockKeys(final TestSource testSource) {

    final var actual = new HashedRangedSubsetImpl(
        testSource.blockKeys,
        testSource.codePointRanges,
        testSource.numberOfCodePointRanges,
        testSource.numberOfCodePointsInCodePointRanges);

    assertThat(actual.getBlockKeySet())
        .hasSameSizeAs(testSource.expectedBlockKeys)
        .containsOnly(testSource.expectedBlockKeys);
  }

  @ParameterizedTest
  @EnumSource(TestSource.class)
  void ranges_containsExpectedCodepoints(final TestSource testSource) {

    final var actual = new HashedRangedSubsetImpl(
        testSource.blockKeys,
        testSource.codePointRanges,
        testSource.numberOfCodePointRanges,
        testSource.numberOfCodePointsInCodePointRanges);

    assertThat(actual.ranges())
        .flatMap(CodePointRange::copy)
        .containsAll(testSource.expectedCodePointRanges())
        .doesNotContainAnyElementsOf(testSource.expectedDoesNotContainCharacters());
  }

  @ParameterizedTest
  @EnumSource(TestSource.class)
  void getBlockKeys_returnsAsExpected(final TestSource testSource) {

    final var actual = new HashedRangedSubsetImpl(
        testSource.blockKeys,
        testSource.codePointRanges,
        testSource.numberOfCodePointRanges,
        testSource.numberOfCodePointsInCodePointRanges);

    assertThat(actual.getBlockKeys())
        .isDeepEqualTo(testSource.blockKeys);
  }

  @ParameterizedTest
  @EnumSource(TestSource.class)
  void getCodePointRangesByBlock_returnsAsExpected(final TestSource testSource) {

    final var actual = new HashedRangedSubsetImpl(
        testSource.blockKeys,
        testSource.codePointRanges,
        testSource.numberOfCodePointRanges,
        testSource.numberOfCodePointsInCodePointRanges);

    assertThat(actual.getCodePointRangesByBlock())
        .isDeepEqualTo(testSource.codePointRanges);
  }

  @ParameterizedTest
  @EnumSource(TestSource.class)
  void numberOfCodePointRanges_returnsAsExpected(final TestSource testSource) {

    final var actual = new HashedRangedSubsetImpl(
        testSource.blockKeys,
        testSource.codePointRanges,
        testSource.numberOfCodePointRanges,
        testSource.numberOfCodePointsInCodePointRanges);

    assertThat(actual.numberOfCodePointRanges())
        .isEqualTo(testSource.numberOfCodePointRanges);
  }

  @ParameterizedTest
  @EnumSource(TestSource.class)
  void numberOfCodePointsInCodePointRanges_returnsAsExpected(final TestSource testSource) {

    final var actual = new HashedRangedSubsetImpl(
        testSource.blockKeys,
        testSource.codePointRanges,
        testSource.numberOfCodePointRanges,
        testSource.numberOfCodePointsInCodePointRanges);

    assertThat(actual.numberOfCodePointsInCodePointRanges())
        .isEqualTo(testSource.numberOfCodePointsInCodePointRanges);
  }

  @ParameterizedTest
  @EnumSource(TestSource.class)
  void ranges_iteratorRemove_throwsException(final TestSource testSource) {

    final var subset = new HashedRangedSubsetImpl(
        testSource.blockKeys,
        testSource.codePointRanges,
        testSource.numberOfCodePointRanges,
        testSource.numberOfCodePointsInCodePointRanges);

    final var ranges = subset.ranges();
    assertThat(ranges).isNotNull();

    final var iterator = ranges.iterator();
    assertThat(iterator).isNotNull();

    assertThatExceptionOfType(UnsupportedOperationException.class)
        .isThrownBy(iterator::remove);
  }

  @ParameterizedTest
  @EnumSource(TestSource.class)
  void ranges_iteratorNext_throwsExceptionWhenNoMoreElementsExist(final TestSource testSource) {

    final var subset = new HashedRangedSubsetImpl(
        testSource.blockKeys,
        testSource.codePointRanges,
        testSource.numberOfCodePointRanges,
        testSource.numberOfCodePointsInCodePointRanges);

    final var ranges = subset.ranges();
    assertThat(ranges).isNotNull();

    final var iterator = ranges.iterator();
    assertThat(iterator).isNotNull();

    // iterate over elements until none are left.
    while (iterator.hasNext()) {
      assertThat(iterator.next()).isNotNull();
    }

    assertThatExceptionOfType(NoSuchElementException.class)
        .isThrownBy(iterator::next);
  }

  @Test
  void ranges_iteratorNext_throwsExceptionForEmptySubset() {

    final var subset = new HashedRangedSubsetImpl(
        new char[0][],
        new char[0][][],
        0,
        0);

    final var ranges = subset.ranges();
    assertThat(ranges).isNotNull();

    final var iterator = ranges.iterator();
    assertThat(iterator).isNotNull();

    assertThatExceptionOfType(NoSuchElementException.class)
        .isThrownBy(iterator::next);
  }


  enum TestSource {
    SINGLE_BLOCK(
        new char[][]{ // blockKeys
            {0x0005}},
        new char[][][]{ // codePointRanges
            {{0x51_53, 0x55_55, 0x57_59}}},
        3,
        7,
        new char[]{0x0005},
        new int[]{ // expectedCodePointRanges
            0x0551_0553, 0x0555_0555, 0x0557_0559},
        new char[]{ // expectedContainsCharacters
            '\u0551', '\u0552', '\u0553', '\u0555', '\u0557', '\u0559', '\u0558'},
        new char[]{ // expectedDoesNotContainCharacters
            '\u0550', '\u0554', '\u0556', '\u0560'}),
    MULTIPLE_BLOCKS(
        new char[][]{ // blockKeys
            {0x0000}, null, {0x0002}, null, null, {0x0005}},
        new char[][][]{ // codePointRanges
            {{0x35_36, 0x38_38}},
            null,
            {{0x42_42}},
            null, null,
            {{0x51_53, 0x55_55, 0x57_59}}},
        6,
        11,
        new char[]{0x0000, 0x0002, 0x0005},
        new int[]{ // expectedCodePointRanges
            0x0035_0036, 0x0038_0038, 0x0242_0242, 0x0551_0553, 0x0555_0555, 0x0557_0559},
        new char[]{ // expectedContainsCharacters
            '\u0035', '\u0036', '\u0038', '\u0242', '\u0551', '\u0552', '\u0553', '\u0555', '\u0557', '\u0559', '\u0558'},
        new char[]{ // expectedDoesNotContainCharacters
            '\u0034', '\u0037', '\u0037', '\u0039', '\u0155', '\u0241', '\u0243', '\u0366', '\u0550', '\u0554', '\u0556', '\u0560'}),

    MULTIPLE_BLOCKS_WITH_MULTIPLE_BLOCK_KEYS_IN_HASH_BUCKET(
        new char[][]{ // blockKeys
            {0x0000}, null, {0x0002, 0x0005}},
        new char[][][]{ // codePointRanges
            {{0x35_36, 0x38_38}},
            null,
            {{0x42_42}, {0x51_53, 0x55_55, 0x57_59}}},
        6,
        11,
        new char[]{0x0000, 0x0002, 0x0005},
        new int[]{ // expectedCodePointRanges
            0x0035_0036, 0x0038_0038, 0x0242_0242, 0x0551_0553, 0x0555_0555, 0x0557_0559},
        new char[]{ // expectedContainsCharacters
            '\u0035', '\u0036', '\u0038', '\u0242', '\u0551', '\u0552', '\u0553', '\u0555', '\u0557', '\u0559', '\u0558'},
        new char[]{ // expectedDoesNotContainCharacters
            '\u0034', '\u0037', '\u0037', '\u0039', '\u0155', '\u0241', '\u0243', '\u0366', '\u0550', '\u0554', '\u0556', '\u0560'}),

    MULTIPLE_BLOCKS_WITH_MULTIPLE_BLOCK_KEYS_IN_A_SINGLE_HASH_BUCKET(
        new char[][]{ // blockKeys
            {0x0000, 0x0002, 0x0005}},
        new char[][][]{ // codePointRanges
            {{0x35_36, 0x38_38}, {0x42_42}, {0x51_53, 0x55_55, 0x57_59}}},
        6,
        11,
        new char[]{0x0000, 0x0002, 0x0005},
        new int[]{ // expectedCodePointRanges
            0x0035_0036, 0x0038_0038, 0x0242_0242, 0x0551_0553, 0x0555_0555, 0x0557_0559},
        new char[]{ // expectedContainsCharacters
            '\u0035', '\u0036', '\u0038', '\u0242', '\u0551', '\u0552', '\u0553', '\u0555', '\u0557', '\u0559', '\u0558'},
        new char[]{ // expectedDoesNotContainCharacters
            '\u0034', '\u0037', '\u0037', '\u0039', '\u0155', '\u0241', '\u0243', '\u0366', '\u0550', '\u0554', '\u0556', '\u0560'}),
    ;

    final char[][] blockKeys;
    final char[][][] codePointRanges;
    final int numberOfCodePointRanges;
    final int numberOfCodePointsInCodePointRanges;

    final char[] expectedBlockKeys;
    final int[] expectedCodePointRanges;
    final char[] expectedContainsCharacters;
    final char[] expectedDoesNotContainCharacters;

    TestSource(
        final char[][] blockKeys,
        final char[][][] codePointRanges,
        final int numberOfCodePointRanges,
        final int numberOfCodePointsInCodePointRanges,
        final char[] expectedBlockKeys,
        final int[] expectedCodePointRanges,
        final char[] expectedContainsCharacters,
        final char[] expectedDoesNotContainCharacters) {
      this.blockKeys = blockKeys;
      this.codePointRanges = codePointRanges;
      this.numberOfCodePointRanges = numberOfCodePointRanges;
      this.numberOfCodePointsInCodePointRanges = numberOfCodePointsInCodePointRanges;
      this.expectedBlockKeys = expectedBlockKeys;
      this.expectedCodePointRanges = expectedCodePointRanges;
      this.expectedContainsCharacters = expectedContainsCharacters;
      this.expectedDoesNotContainCharacters = expectedDoesNotContainCharacters;
    }

    Iterable<CodePointRange> expectedCodePointRanges() {
      final List<CodePointRange> result = new ArrayList<>();
      for (int expectedRange : expectedCodePointRanges) {
        result.add(new CodePointRange(
            SubsetUtils.getInclusiveFrom(expectedRange),
            SubsetUtils.getInclusiveTo(expectedRange)));
      }
      return result;
    }

    Iterable<CodePointRange> expectedDoesNotContainCharacters() {
      final List<CodePointRange> result = new ArrayList<>();
      for (int expectedRange : expectedDoesNotContainCharacters) {
        result.add(new CodePointRange(
            SubsetUtils.getInclusiveFrom(expectedRange),
            SubsetUtils.getInclusiveTo(expectedRange)));
      }
      return result;
    }
  }

}
