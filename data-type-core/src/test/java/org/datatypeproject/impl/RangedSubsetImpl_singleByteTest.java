package org.datatypeproject.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.datatypeproject.Subset;
import org.datatypeproject.impl.RangedSubset;
import org.datatypeproject.impl.RangedSubsetImpl;
import org.datatypeproject.impl.RangedSubsetUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

class RangedSubsetImpl_singleByteTest {

  @ParameterizedTest
  @ValueSource(strings = {
      "\u0000",
      "\u00FF",
      "A",
      "a",
      "Z",
      "z",
      "0",
      "9",
      "!",
      "?",
  })
  void includeChar_singleByteTest(final char value) {

    final Subset actual = RangedSubset.builder()
        .includeChar(value)
        .build();

    assertThat(actual).isNotNull().isExactlyInstanceOf(RangedSubsetImpl.class);
    assertThat(actual.isEmpty()).isFalse();
    assertThat(actual.isNotEmpty()).isTrue();
    assertThat(actual.contains(value)).isTrue();

    final RangedSubset rangedSubset = (RangedSubset)actual;
    assertThat(rangedSubset.getSingleByteCodePointRanges()).containsOnly(RangedSubsetUtils.rangeToChar(value, value));
    assertThat(rangedSubset.getDoubleByteCodePointRanges()).isEmpty();
    assertThat(rangedSubset.getTripleByteCodePointRanges()).isEmpty();
  }

  enum SingleByteTestSource {
    SINGLE_CHARACTER(
        new char[] {'\u0021'},
        new char[] {0x21_21}),
    MULTIPLE_ADJACENT_CHARACTERS(
        new char[] {'\u0021', '\u0022', '\u0023'},
        new char[] {0x21_23}),
    MULTIPLE_NON_ADJACENT_CHARACTERS(
        new char[] {'\u0021', '\u0033', '\u0039'},
        new char[] {0x21_21, 0x33_33, 0x39_39}),
    MULTIPLE_NON_ADJACENT_CHARACTERS_NOT_IN_ORDER(
        new char[] {'\u00FF', '\u0021', '\u0044', '\u0039', '\u0000'},
        new char[] {0x00_00, 0x21_21, 0x39_39, 0x44_44, 0xFF_FF}),
    ;
    final char [] chars;
    final char [] expectedRanges;

    SingleByteTestSource(char[] chars, char[] expectedRanges) {
      this.chars = chars;
      this.expectedRanges = expectedRanges;
    }
  }

  @ParameterizedTest
  @EnumSource(SingleByteTestSource.class)
  void includeChars_singleByteTest(final SingleByteTestSource testSource) {

    final Subset actual = RangedSubset.builder()
        .includeChars(testSource.chars)
        .build();

    assertThat(actual).isNotNull().isExactlyInstanceOf(RangedSubsetImpl.class);
    assertThat(actual.isEmpty()).isFalse();
    assertThat(actual.isNotEmpty()).isTrue();

    for (char ch : testSource.chars) {
      assertThat(actual.contains(ch)).isTrue();
    }

    final RangedSubset rangedSubset = (RangedSubset)actual;
    assertThat(rangedSubset.getSingleByteCodePointRanges()).containsOnly(testSource.expectedRanges);
    assertThat(rangedSubset.getDoubleByteCodePointRanges()).isEmpty();
    assertThat(rangedSubset.getTripleByteCodePointRanges()).isEmpty();
  }
}
