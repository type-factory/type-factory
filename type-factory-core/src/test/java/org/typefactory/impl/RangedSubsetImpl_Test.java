package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.Subset.CodePointRange;
import org.typefactory.testutils.RangesAsCharArrayConverter;
import org.typefactory.testutils.RangesAsIntegerArrayConverter;
import org.typefactory.testutils.RangesAsLongArrayConverter;

class RangedSubsetImpl_Test {

  @ParameterizedTest
  @CsvSource(textBlock = """
      0x30_3F                            | 1 | 16
      0x30_3F, 0x50_5F                   | 2 | 32
      0x30_3F, 0x50_5F, 0x61_61          | 3 | 33
      0x30_3F, 0x50_5F, 0x61_61, 0x63_64 | 4 | 35
      """, delimiter = '|')
  void constructor_singleByteRangeInstantiatesAsExpected(
      @ConvertWith(RangesAsCharArrayConverter.class) final char[] singleByteCodePointRanges,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges) {

    final var actual = new RangedSubsetImpl(singleByteCodePointRanges, numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);

    // Test the constructor
    assertThat(actual.getSingleByteCodePointRanges()).containsExactly(singleByteCodePointRanges);
    assertThat(actual.numberOfCodePointRanges()).isEqualTo(numberOfCodePointRanges);
    assertThat(actual.numberOfCodePointsInCodePointRanges()).isEqualTo(numberOfCodePointsInCodePointRanges);
    assertThat(actual.isEmpty()).isFalse();

    // Sanity check that test-data is okay.
    assertThat(numberOfCodePointRanges).isEqualTo(singleByteCodePointRanges.length);
    assertThat(numberOfCodePointsInCodePointRanges).isEqualTo(SubsetUtils.numberOfCodePointsInRanges(singleByteCodePointRanges));

    for (char range : singleByteCodePointRanges) {
      final var inclusiveFrom = SubsetUtils.getInclusiveFrom(range);
      final var inclusiveTo = SubsetUtils.getInclusiveTo(range);
      assertThat(actual.contains(inclusiveFrom)).isTrue();
      assertThat(actual.contains(inclusiveFrom - 1)).isFalse();
      assertThat(actual.contains(inclusiveTo)).isTrue();
      assertThat(actual.contains(inclusiveTo + 1)).isFalse();
    }
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      0x3000_300F                                            | 1 | 16
      0x3000_300F, 0x5000_500F                               | 2 | 32
      0x3000_300F, 0x5000_500F, 0x6001_0x6001                | 3 | 33
      0x3000_300F, 0x5000_500F, 0x6001_0x6001, 0x6003_0x6004 | 4 | 35
      """, delimiter = '|')
  void constructor_doubleByteRangeInstantiatesAsExpected(
      @ConvertWith(RangesAsIntegerArrayConverter.class) final int[] doubleByteCodePointRanges,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges) {

    final var actual = new RangedSubsetImpl(doubleByteCodePointRanges, numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);

    // Test the constructor
    assertThat(actual.getDoubleByteCodePointRanges()).containsExactly(doubleByteCodePointRanges);
    assertThat(actual.numberOfCodePointRanges()).isEqualTo(numberOfCodePointRanges);
    assertThat(actual.numberOfCodePointsInCodePointRanges()).isEqualTo(numberOfCodePointsInCodePointRanges);

    // Sanity check that test-data is okay.
    assertThat(numberOfCodePointRanges).isEqualTo(doubleByteCodePointRanges.length);
    assertThat(numberOfCodePointsInCodePointRanges).isEqualTo(SubsetUtils.numberOfCodePointsInRanges(doubleByteCodePointRanges));

    for (int range : doubleByteCodePointRanges) {
      final var inclusiveFrom = SubsetUtils.getInclusiveFrom(range);
      final var inclusiveTo = SubsetUtils.getInclusiveTo(range);
      assertThat(actual.contains(inclusiveFrom)).isTrue();
      assertThat(actual.contains(inclusiveFrom - 1)).isFalse();
      assertThat(actual.contains(inclusiveTo)).isTrue();
      assertThat(actual.contains(inclusiveTo + 1)).isFalse();
    }
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      0x00300000_0030000F                                                                | 1 | 16
      0x00300000_0030000F, 0x00500000_0050000F                                           | 2 | 32
      0x00300000_0030000F, 0x00500000_0050000F, 0x00600001_00600001                      | 3 | 33
      0x00300000_0030000F, 0x00500000_0050000F, 0x00600001_00600001, 0x00600003_00600004 | 4 | 35
      """, delimiter = '|')
  void constructor_tripleByteRangeInstantiatesAsExpected(
      @ConvertWith(RangesAsLongArrayConverter.class) final long[] tripleByteCodePointRanges,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges) {

    final var actual = new RangedSubsetImpl(tripleByteCodePointRanges, numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);

    // Test the constructor
    assertThat(actual.getTripleByteCodePointRanges()).containsExactly(tripleByteCodePointRanges);
    assertThat(actual.numberOfCodePointRanges()).isEqualTo(numberOfCodePointRanges);
    assertThat(actual.numberOfCodePointsInCodePointRanges()).isEqualTo(numberOfCodePointsInCodePointRanges);

    // Sanity check that test-data is okay.
    assertThat(numberOfCodePointRanges).isEqualTo(tripleByteCodePointRanges.length);
    assertThat(numberOfCodePointsInCodePointRanges).isEqualTo(SubsetUtils.numberOfCodePointsInRanges(tripleByteCodePointRanges));

    for (long range : tripleByteCodePointRanges) {
      final var inclusiveFrom = SubsetUtils.getInclusiveFrom(range);
      final var inclusiveTo = SubsetUtils.getInclusiveTo(range);
      assertThat(actual.contains(inclusiveFrom)).isTrue();
      assertThat(actual.contains(inclusiveFrom - 1)).isFalse();
      assertThat(actual.contains(inclusiveTo)).isTrue();
      assertThat(actual.contains(inclusiveTo + 1)).isFalse();
    }
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      0x30_3F                            | 0x3000_300F                                            | 2 | 32
      0x30_3F, 0x50_5F                   | 0x3000_300F, 0x5000_500F                               | 4 | 64
      0x30_3F, 0x50_5F, 0x61_61          | 0x3000_300F, 0x5000_500F, 0x6001_0x6001                | 6 | 66
      0x30_3F, 0x50_5F, 0x61_61, 0x63_64 | 0x3000_300F, 0x5000_500F, 0x6001_0x6001, 0x6003_0x6004 | 8 | 70
      """, delimiter = '|')
  void constructor_singleAndDoubleByteRangeInstantiatesAsExpected(
      @ConvertWith(RangesAsCharArrayConverter.class) final char[] singleByteCodePointRanges,
      @ConvertWith(RangesAsIntegerArrayConverter.class) final int[] doubleByteCodePointRanges,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges) {

    final var actual = new RangedSubsetImpl(
        singleByteCodePointRanges, doubleByteCodePointRanges,
        numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);

    // Test the constructor
    assertThat(actual.getSingleByteCodePointRanges()).containsExactly(singleByteCodePointRanges);
    assertThat(actual.getDoubleByteCodePointRanges()).containsExactly(doubleByteCodePointRanges);
    assertThat(actual.numberOfCodePointRanges()).isEqualTo(numberOfCodePointRanges);
    assertThat(actual.numberOfCodePointsInCodePointRanges()).isEqualTo(numberOfCodePointsInCodePointRanges);

    // Sanity check that test-data is okay.
    assertThat(numberOfCodePointRanges).isEqualTo(
        singleByteCodePointRanges.length
            + doubleByteCodePointRanges.length);
    assertThat(numberOfCodePointsInCodePointRanges).isEqualTo(
        SubsetUtils.numberOfCodePointsInRanges(singleByteCodePointRanges)
            + SubsetUtils.numberOfCodePointsInRanges(doubleByteCodePointRanges));
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      0x30_3F                            | 0x00300000_0030000F                                                                | 2 | 32
      0x30_3F, 0x50_5F                   | 0x00300000_0030000F, 0x00500000_0050000F                                           | 4 | 64
      0x30_3F, 0x50_5F, 0x61_61          | 0x00300000_0030000F, 0x00500000_0050000F, 0x00600001_00600001                      | 6 | 66
      0x30_3F, 0x50_5F, 0x61_61, 0x63_64 | 0x00300000_0030000F, 0x00500000_0050000F, 0x00600001_00600001, 0x00600003_00600004 | 8 | 70
      """, delimiter = '|')
  void constructor_singleAndTripleByteRangeInstantiatesAsExpected(
      @ConvertWith(RangesAsCharArrayConverter.class) final char[] singleByteCodePointRanges,
      @ConvertWith(RangesAsLongArrayConverter.class) final long[] tripleByteCodePointRanges,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges) {

    final var actual = new RangedSubsetImpl(
        singleByteCodePointRanges, tripleByteCodePointRanges,
        numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);

    // Test the constructor
    assertThat(actual.getSingleByteCodePointRanges()).containsExactly(singleByteCodePointRanges);
    assertThat(actual.getTripleByteCodePointRanges()).containsExactly(tripleByteCodePointRanges);
    assertThat(actual.numberOfCodePointRanges()).isEqualTo(numberOfCodePointRanges);
    assertThat(actual.numberOfCodePointsInCodePointRanges()).isEqualTo(numberOfCodePointsInCodePointRanges);

    // Sanity check that test-data is okay.
    assertThat(numberOfCodePointRanges).isEqualTo(
        singleByteCodePointRanges.length
            + tripleByteCodePointRanges.length);
    assertThat(numberOfCodePointsInCodePointRanges).isEqualTo(
        SubsetUtils.numberOfCodePointsInRanges(singleByteCodePointRanges)
            + SubsetUtils.numberOfCodePointsInRanges(tripleByteCodePointRanges));
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      0x3000_300F                                            | 0x00300000_0030000F                                                                | 2 | 32
      0x3000_300F, 0x5000_500F                               | 0x00300000_0030000F, 0x00500000_0050000F                                           | 4 | 64
      0x3000_300F, 0x5000_500F, 0x6001_0x6001                | 0x00300000_0030000F, 0x00500000_0050000F, 0x00600001_00600001                      | 6 | 66
      0x3000_300F, 0x5000_500F, 0x6001_0x6001, 0x6003_0x6004 | 0x00300000_0030000F, 0x00500000_0050000F, 0x00600001_00600001, 0x00600003_00600004 | 8 | 70
      """, delimiter = '|')
  void constructor_doubleAndTripleByteRangeInstantiatesAsExpected(
      @ConvertWith(RangesAsIntegerArrayConverter.class) final int[] doubleByteCodePointRanges,
      @ConvertWith(RangesAsLongArrayConverter.class) final long[] tripleByteCodePointRanges,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges) {

    final var actual = new RangedSubsetImpl(
        doubleByteCodePointRanges, tripleByteCodePointRanges,
        numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);

    // Test the constructor
    assertThat(actual.getDoubleByteCodePointRanges()).containsExactly(doubleByteCodePointRanges);
    assertThat(actual.getTripleByteCodePointRanges()).containsExactly(tripleByteCodePointRanges);
    assertThat(actual.numberOfCodePointRanges()).isEqualTo(numberOfCodePointRanges);
    assertThat(actual.numberOfCodePointsInCodePointRanges()).isEqualTo(numberOfCodePointsInCodePointRanges);

    // Sanity check that test-data is okay.
    assertThat(numberOfCodePointRanges).isEqualTo(
        doubleByteCodePointRanges.length
            + tripleByteCodePointRanges.length);
    assertThat(numberOfCodePointsInCodePointRanges).isEqualTo(
        SubsetUtils.numberOfCodePointsInRanges(doubleByteCodePointRanges)
            + SubsetUtils.numberOfCodePointsInRanges(tripleByteCodePointRanges));
  }

  @Test
  void isEmpty_returnsTrue() {
    final var actual = new RangedSubsetImpl(new char[0], 0, 0);
    assertThat(actual.isEmpty()).isTrue();
  }

  @Test
  void isEmpty_returnsFalse() {
    final var actual = new RangedSubsetImpl(new char[]{0x30_32}, 1, 3);
    assertThat(actual.isEmpty()).isFalse();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      0x30_3F                   | 0x3000_300F                             | 0x00300000_0030000F                                           | [0x30_3f],[0x3000_300f],[0x300000_30000f]
      0x30_3F, 0x50_5F          | 0x3000_300F, 0x5000_500F                | 0x00300000_0030000F, 0x00500000_0050000F                      | [0x30_3f,0x50_5f],[0x3000_300f,0x5000_500f],[0x300000_30000f,0x500000_50000f]
      0x30_3F, 0x50_5F, 0x61_61 | 0x3000_300F, 0x5000_500F, 0x6001_0x6001 | 0x00300000_0030000F, 0x00500000_0050000F, 0x00600001_00600001 | [0x30_3f,0x50_5f,0x61_61],[0x3000_300f,0x5000_500f,0x6001_6001],[0x300000_30000f,0x500000_50000f,0x600001_600001]
      """, delimiter = '|')
  void toString(
      @ConvertWith(RangesAsCharArrayConverter.class) final char[] singleByteCodePointRanges,
      @ConvertWith(RangesAsIntegerArrayConverter.class) final int[] doubleByteCodePointRanges,
      @ConvertWith(RangesAsLongArrayConverter.class) final long[] tripleByteCodePointRanges,
      final String expected) {

    final var actual = new RangedSubsetImpl(
        singleByteCodePointRanges,
        doubleByteCodePointRanges,
        tripleByteCodePointRanges,
        -1, -1);

    // Test the constructor
    assertThat(actual).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      0x30_3F                            | 1 | 16
      0x30_3F, 0x50_5F                   | 2 | 32
      0x30_3F, 0x50_5F, 0x61_61          | 3 | 33
      0x30_3F, 0x50_5F, 0x61_61, 0x63_64 | 4 | 35
      """, delimiter = '|')
  void ranges_returnsSingleByteCodePointRanges(
      @ConvertWith(RangesAsCharArrayConverter.class) final char[] singleByteCodePointRanges,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges) {

    final List<CodePointRange> expectedRanges = new ArrayList<>();
    for (char range : singleByteCodePointRanges) {
      expectedRanges.add(new CodePointRange(SubsetUtils.getInclusiveFrom(range), SubsetUtils.getInclusiveTo(range)));
    }

    final var subset = new RangedSubsetImpl(singleByteCodePointRanges, numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);
    final var actual = subset.ranges();

    // Test the constructor
    assertThat(actual)
        .isNotEmpty()
        .flatMap(CodePointRange::copy).containsAll(expectedRanges);
  }
  @ParameterizedTest
  @CsvSource(textBlock = """
      0x3000_300F                                            | 1 | 16
      0x3000_300F, 0x5000_500F                               | 2 | 32
      0x3000_300F, 0x5000_500F, 0x6001_0x6001                | 3 | 33
      0x3000_300F, 0x5000_500F, 0x6001_0x6001, 0x6003_0x6004 | 4 | 35
      """, delimiter = '|')
  void ranges_returnsDoubleByteCodePointRanges(
      @ConvertWith(RangesAsIntegerArrayConverter.class) final int[] doubleByteCodePointRanges,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges) {

    final List<CodePointRange> expectedRanges = new ArrayList<>();
    for (int range : doubleByteCodePointRanges) {
      expectedRanges.add(new CodePointRange(SubsetUtils.getInclusiveFrom(range), SubsetUtils.getInclusiveTo(range)));
    }

    final var subset = new RangedSubsetImpl(doubleByteCodePointRanges, numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);
    final var actual = subset.ranges();

    // Test the constructor
    assertThat(actual)
        .isNotEmpty()
        .flatMap(CodePointRange::copy).containsAll(expectedRanges);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      0x00300000_0030000F                                                                | 1 | 16
      0x00300000_0030000F, 0x00500000_0050000F                                           | 2 | 32
      0x00300000_0030000F, 0x00500000_0050000F, 0x00600001_00600001                      | 3 | 33
      0x00300000_0030000F, 0x00500000_0050000F, 0x00600001_00600001, 0x00600003_00600004 | 4 | 35
      """, delimiter = '|')
  void ranges_returnsTripleByteCodePointRanges(
      @ConvertWith(RangesAsLongArrayConverter.class) final long[] tripleByteCodePointRanges,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges) {

    final List<CodePointRange> expectedRanges = new ArrayList<>();
    for (long range : tripleByteCodePointRanges) {
      expectedRanges.add(new CodePointRange(SubsetUtils.getInclusiveFrom(range), SubsetUtils.getInclusiveTo(range)));
    }

    final var subset = new RangedSubsetImpl(tripleByteCodePointRanges, numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);
    final var actual = subset.ranges();

    // Test the constructor
    assertThat(actual)
        .isNotEmpty()
        .flatMap(CodePointRange::copy).containsAll(expectedRanges);
  }

  @Test
  void ranges_iteratorRemove_throwsException() {
    final var subset = new RangedSubsetImpl(new char[0], -1, -1);

    final var ranges = subset.ranges();
    assertThat(ranges).isNotNull();

    final var iterator = ranges.iterator();
    assertThat(iterator).isNotNull();

    assertThatExceptionOfType(UnsupportedOperationException.class)
        .isThrownBy(iterator::remove);
  }
  @Test
  void ranges_iteratorNext_throwsException() {
    final var subset = new RangedSubsetImpl(new char[0], -1, -1);

    final var ranges = subset.ranges();
    assertThat(ranges).isNotNull();

    final var iterator = ranges.iterator();
    assertThat(iterator).isNotNull();

    assertThatExceptionOfType(NoSuchElementException.class)
        .isThrownBy(iterator::next);
  }
}
