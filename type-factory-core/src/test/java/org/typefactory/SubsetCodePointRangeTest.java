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
import static org.assertj.core.api.Assertions.assertThatObject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.Subset.CodePointRange;

class SubsetCodePointRangeTest {

  @ParameterizedTest
  @CsvSource(value = {
      "a | a",
      "a | b",
      "a | z",
      "0 | 9",
  }, delimiter = '|')
  void constructorTestUsingChar(final char from, final char to) {
    final var actual = new CodePointRange(from, to);
    assertThat(actual.inclusiveFrom).isEqualTo(from);
    assertThat(actual.inclusiveTo).isEqualTo(to);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "0x0386 | 0x0386", //  Ά
      "0x0388 | 0x038a", //  Έ Ή Ί
      "0x038c | 0x038c", //  Ό
      "0x038e | 0x03a1", //  Ύ Ώ ΐ Α Β Γ Δ Ε Ζ Η Θ Ι Κ Λ Μ Ν Ξ Ο Π Ρ
      "0x03a3 | 0x03ce", //  Σ Τ Υ Φ Χ Ψ Ω Ϊ Ϋ ά έ ή ί ΰ α β γ δ ε ζ η θ ι κ λ μ ν ξ ο π
      //  ρ ς σ τ υ φ χ ψ ω ϊ ϋ ό ύ ώ
  }, delimiter = '|')
  void constructorTestUsingIntCodePoint(final int from, final int to) {
    final var actual = new CodePointRange(from, to);
    assertThat(actual.inclusiveFrom).isEqualTo(from);
    assertThat(actual.inclusiveTo).isEqualTo(to);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "a | a",
      "a | b",
      "a | z",
      "0 | 9",
  }, delimiter = '|')
  void copy_createsNewInstanceWithSameFromToRange(final char from, final char to) {
    final var actual1 = new CodePointRange(from, to);
    final var actual2 = actual1.copy();
    assertThat(actual1.inclusiveFrom).isEqualTo(actual2.inclusiveFrom);
    assertThat(actual1.inclusiveTo).isEqualTo(actual2.inclusiveTo);
    assertThat(actual2.inclusiveFrom).isEqualTo(actual1.inclusiveFrom);
    assertThat(actual2.inclusiveTo).isEqualTo(actual1.inclusiveTo);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "a | a | a ",
      "a | b | a ",
      "a | b | b ",
      "a | z | m ",
      "0 | 9 | 5 ",
  }, delimiter = '|')
  void contains_returnsTrueForCharactersInRange(final char from, final char to, final char contains) {
    final var actual = new CodePointRange(from, to);
    assertThat(actual.contains(contains)).isTrue();
  }

  @ParameterizedTest
  @CsvSource(value = {
      "a | a | b ",
      "a | b | c ",
      "a | z | A ",
      "0 | 8 | 9 ",
  }, delimiter = '|')
  void contains_returnsFalseForCharactersOutsideRange(final char from, final char to, final char contains) {
    final var actual = new CodePointRange(from, to);
    assertThat(actual.contains(contains)).isFalse();
  }

  @ParameterizedTest
  @CsvSource(value = {
      "a | a | b ",
      "a | b | c ",
      "a | z | A ",
      "0 | 8 | 9 ",
  }, delimiter = '|')
  void doesNotContain_returnsTrueForCharactersOutsideRange(final char from, final char to, final char doesNotContain) {
    final var actual = new CodePointRange(from, to);
    assertThat(actual.doesNotContain(doesNotContain)).isTrue();
  }

  @ParameterizedTest
  @CsvSource(value = {
      "a | a | a ",
      "a | b | a ",
      "a | b | b ",
      "a | z | m ",
      "0 | 9 | 5 ",
  }, delimiter = '|')
  void doesNotContain_returnsFalseForCharactersInRange(final char from, final char to, final char doesNotContain) {
    final var actual = new CodePointRange(from, to);
    assertThat(actual.doesNotContain(doesNotContain)).isFalse();
  }

  @Test
  void equals_returnsFalseWhenOtherObjectIsNull() {
    final var range1 = new CodePointRange('a', 'z');
    assertThatObject(range1).isNotEqualTo(null);
  }

  @Test
  void equals_returnsTrueWhenOtherObjectIsSameInstance() {
    final var range1 = new CodePointRange('a', 'z');
    final var range2 = range1;
    assertThatObject(range1).isEqualTo(range2);
    assertThatObject(range2).isEqualTo(range1);
  }

  @Test
  void equals_returnsFalseWhenOtherObjectIsDifferentClassType() {
    final var range1 = new CodePointRange('a', 'z');
    final var range2 = "az";
    assertThatObject(range1).isNotEqualTo(range2);
    assertThatObject(range2).isNotEqualTo(range1);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "a | a | a | a | true  ",
      "a | b | a | b | true  ",
      "a | z | a | z | true  ",
      "0 | 9 | 0 | 9 | true  ",
      "a | a | b | b | false ",
      "a | c | b | d | false ",
      "a | c | a | d | false ",
      "a | z | b | z | false ",
      "a | z | a | y | false ",
      "0 | 9 | 1 | 9 | false ",
      "0 | 9 | 0 | 8 | false ",
  }, delimiter = '|')
  void equals_returnsAsExpected(
      final char from1, final char to1,
      final char from2, final char to2,
      final boolean expected) {
    final var range1 = new CodePointRange(from1, to1);
    final var range2 = new CodePointRange(from2, to2);
    assertThat(range1.equals(range2)).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "a | a",
      "a | b",
      "a | z",
      "0 | 9",
  }, delimiter = '|')
  void hashCode_returnsAsExpected(final char from, final char to) {
    final var range1 = new CodePointRange(from, to);
    final var range2 = new CodePointRange(from, to);
    assertThatObject(range1).hasSameHashCodeAs(range2);
    assertThatObject(range2).hasSameHashCodeAs(range1);
    assertThatObject(range1.hashCode()).isEqualTo(from * 109 + to);
  }

  @Test
  void compareTo_returnsPositiveWhenOtherInstanceIsNull() {
    final var range = new CodePointRange('a', 'b');
    assertThat(range.compareTo(null)).isPositive();
  }

  @ParameterizedTest
  @CsvSource(value = {
      "a | b | a | a ",
      "a | z | a | m ",
      "b | b | a | a ",
      "b | z | a | z ",
  }, delimiter = '|')
  void compareTo_returnsAsExpected(
      final char from1, final char to1,
      final char from2, final char to2) {
    final var range1 = new CodePointRange(from1, to1);
    final var range2 = new CodePointRange(from2, to2);
    assertThat(range1.compareTo(range2)).isPositive();
    assertThat(range2.compareTo(range1)).isNegative();
  }

  @ParameterizedTest
  @CsvSource(value = {
      "a | a | a..a (0x00000061..0x00000061) ",
      "a | b | a..b (0x00000061..0x00000062) ",
      "a | z | a..z (0x00000061..0x0000007a) ",
      "0 | 9 | 0..9 (0x00000030..0x00000039) ",
      "Σ | ώ | Σ..ώ (0x000003a3..0x000003ce) ",
  }, delimiter = '|')
  void toString_returnsFormattedString(
      final char from1, final char to1,
      final String expected) {
    final var range1 = new CodePointRange(from1, to1);
    assertThatObject(range1).hasToString(expected);
  }
}
