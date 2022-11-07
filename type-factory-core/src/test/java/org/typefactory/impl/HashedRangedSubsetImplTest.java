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

import java.util.ArrayList;
import java.util.List;
import org.typefactory.Subset.CodePointRange;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class HashedRangedSubsetImplTest {

  @Test
  void empty_returnsTrue() {

    final HashedRangedSubset actual = new HashedRangedSubsetImpl(
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

    final HashedRangedSubset actual = new HashedRangedSubsetImpl(
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

    final HashedRangedSubset actual = new HashedRangedSubsetImpl(
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

    assertThat(actual.getBlockKeySet()).hasSize(3);
    assertThat(actual.getBlockKeySet()).containsOnly((char) 0x0000, (char) 0x0002, (char) 0x0004);
    assertThat(actual.ranges()).flatMap(CodePointRange::copy).containsAll(testSource.expectedCodePointRanges());
    assertThat(actual.ranges()).flatMap(CodePointRange::copy).doesNotContainAnyElementsOf(testSource.expectedDoesNotContainCharacters());
  }

  @ParameterizedTest
  @EnumSource(TestSource.class)
  void getBlockKeySet_containsExpectedBlockKeys(final TestSource testSource) {

    final HashedRangedSubset actual = new HashedRangedSubsetImpl(
        testSource.blockKeys,
        testSource.codePointRanges,
        testSource.numberOfCodePointRanges,
        testSource.numberOfCodePointsInCodePointRanges);

    assertThat(actual.getBlockKeySet()).hasSize(3);
    assertThat(actual.getBlockKeySet()).containsOnly((char) 0x0000, (char) 0x0002, (char) 0x0004);
  }

  @ParameterizedTest
  @EnumSource(TestSource.class)
  void ranges_containsExpectedCodepoints(final TestSource testSource) {

    final HashedRangedSubset actual = new HashedRangedSubsetImpl(
        testSource.blockKeys,
        testSource.codePointRanges,
        testSource.numberOfCodePointRanges,
        testSource.numberOfCodePointsInCodePointRanges);

    assertThat(actual.ranges()).flatMap(CodePointRange::copy).containsAll(testSource.expectedCodePointRanges());
    assertThat(actual.ranges()).flatMap(CodePointRange::copy).doesNotContainAnyElementsOf(testSource.expectedDoesNotContainCharacters());
  }

  enum TestSource {
    MULTIPLE_BLOCKS(
        new char[][]{ // blockKeys
            {0x0000}, null, {0x0002}, null, {0x0004}},
        new char[][][]{ // codePointRanges
            {{0x35_36, 0x38_38}},
            null,
            {{0x42_42}},
            null,
            {{0x51_53, 0x55_55, 0x57_59}}}, 
        6, 
        11,
        new int[]{ // expectedCodePointRanges
            0x0035_0036, 0x0038_0038, 0x0242_0242, 0x0451_0453, 0x0455_0455, 0x0457_0459},
        new char[]{ // expectedContainsCharacters
            '\u0035', '\u0036', '\u0038', '\u0242', '\u0451', '\u0452', '\u0453', '\u0455', '\u0457', '\u0459', '\u0458'},
        new char[]{ // expectedDoesNotContainCharacters
            '\u0034', '\u0037', '\u0037', '\u0039', '\u0155', '\u0241', '\u0243', '\u0366', '\u0450', '\u0454', '\u0456', '\u0460'}),
    ;

    final char[][] blockKeys;
    final char[][][] codePointRanges;
    final int numberOfCodePointRanges;
    final int numberOfCodePointsInCodePointRanges;

    final int[] expectedCodePointRanges;
    final char[] expectedContainsCharacters;
    final char[] expectedDoesNotContainCharacters;

    TestSource(
        final char[][] blockKeys,
        final char[][][] codePointRanges,
        final int numberOfCodePointRanges,
        final int numberOfCodePointsInCodePointRanges,
        final int[] expectedCodePointRanges,
        final char[] expectedContainsCharacters,
        final char[] expectedDoesNotContainCharacters) {
      this.blockKeys = blockKeys;
      this.codePointRanges = codePointRanges;
      this.numberOfCodePointRanges = numberOfCodePointRanges;
      this.numberOfCodePointsInCodePointRanges = numberOfCodePointsInCodePointRanges;
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
