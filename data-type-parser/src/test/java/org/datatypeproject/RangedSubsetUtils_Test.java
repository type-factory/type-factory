package org.datatypeproject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.datatypeproject.Constants.EMPTY_CHAR_ARRAY;
import static org.datatypeproject.Constants.EMPTY_INT_ARRAY;
import static org.datatypeproject.Constants.EMPTY_LONG_ARRAY;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class RangedSubsetUtils_Test {

  @ParameterizedTest
  @CsvSource(value = {
      " 0x00_00 | 0x00 ",
      " 0x00_22 | 0x00 ",
      " 0x3F_A1 | 0x3F ",
      " 0xFE_FF | 0xFE ",
      " 0xFF_FF | 0xFF ",
  }, delimiter = '|')
  void getInclusiveFrom_extractsUpper8BitsFrom16BitCharValue(
      @ConvertWith(SingleByteRangeConverter.class) final char range, final int expected) {

    final int actual = RangedSubsetUtils.getInclusiveFrom(range);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      " 0x00_00 | 0x00 ",
      " 0x00_22 | 0x22 ",
      " 0x3F_A1 | 0xA1 ",
      " 0xFE_FF | 0xFF ",
      " 0xFF_FF | 0xFF ",
  }, delimiter = '|')
  void getInclusiveTo_extractsLower8BitsFrom16BitCharValue(
      @ConvertWith(SingleByteRangeConverter.class) final char range, final int expected) {

    final int actual = RangedSubsetUtils.getInclusiveTo(range);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      " 0x0000_0000 | 0x0000 ",
      " 0x0000_2233 | 0x0000 ",
      " 0x3F00_A100 | 0x3F00 ",
      " 0xFFFE_FFFF | 0xFFFE ",
      " 0xFFFF_FFFF | 0xFFFF ",
  }, delimiter = '|')
  void getInclusiveFrom_extractsUpper16BitsFrom32BitCharValue(
      @ConvertWith(DoubleByteRangeConverter.class) final int range, final int expected) {

    final int actual = RangedSubsetUtils.getInclusiveFrom(range);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      " 0x0000_0000 | 0x0000 ",
      " 0x0000_2233 | 0x2233 ",
      " 0x3F00_A100 | 0xA100 ",
      " 0xFFFE_FFFF | 0xFFFF ",
      " 0xFFFF_FFFF | 0xFFFF ",
  }, delimiter = '|')
  void getInclusiveTo_extractsLower16BitsFrom32BitCharValue(
      @ConvertWith(DoubleByteRangeConverter.class) final int range, final int expected) {

    final int actual = RangedSubsetUtils.getInclusiveTo(range);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      " 0x00000000_00000000 | 0x00000000 ",
      " 0x00012222_00013333 | 0x00012222 ",
      " 0x003FAAAA_00BB9999 | 0x003FAAAA ",
      " 0x00FFFFEE_00FFFFFF | 0x00FFFFEE ",
      " 0x00FFFFFF_00FFFFFF | 0x00FFFFFF ",
  }, delimiter = '|')
  void getInclusiveFrom_extractsUpper32BitsFrom64BitCharValue(
      @ConvertWith(TripleByteRangeConverter.class) final long range, final int expected) {

    final int actual = RangedSubsetUtils.getInclusiveFrom(range);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      " 0x00000000_00000000 | 0x00000000 ",
      " 0x00012222_00013333 | 0x00013333 ",
      " 0x003FAAAA_00BB9999 | 0x00BB9999 ",
      " 0x00FFFFEE_00FFFFFF | 0x00FFFFFF ",
      " 0x00FFFFFF_00FFFFFF | 0x00FFFFFF ",
  }, delimiter = '|')
  void getInclusiveTo_extractsLower32BitsFrom64BitCharValue(
      @ConvertWith(TripleByteRangeConverter.class) final long range, final int expected) {

    final int actual = RangedSubsetUtils.getInclusiveTo(range);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      " 0x00 | 0x00 | 0x00_00 ",
      " 0x00 | 0x22 | 0x00_22 ",
      " 0x3F | 0xA1 | 0x3F_A1 ",
      " 0xFE | 0xFF | 0xFE_FF ",
      " 0xFF | 0xFF | 0xFF_FF ",
  }, delimiter = '|')
  void rangeToChar_creates16BitCodePointRangeFromTwo8BitCodePoints(
      final int inclusiveFrom, final int inclusiveTo,
      @ConvertWith(SingleByteRangeConverter.class) final char expected) {

    final char actual = RangedSubsetUtils.rangeToChar(inclusiveFrom, inclusiveTo);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      " 0x0000 | 0x0000 | 0x0000_0000 ",
      " 0x0000 | 0x2233 | 0x0000_2233 ",
      " 0x3F00 | 0xA100 | 0x3F00_A100 ",
      " 0xFFFE | 0xFFFF | 0xFFFE_FFFF ",
      " 0xFFFF | 0xFFFF | 0xFFFF_FFFF ",
  }, delimiter = '|')
  void rangeToInt_creates32BitCodePointRangeFromTwo16BitCodePoints(
      final int inclusiveFrom, final int inclusiveTo,
      @ConvertWith(DoubleByteRangeConverter.class) final int expected) {

    final int actual = RangedSubsetUtils.rangeToInt(inclusiveFrom, inclusiveTo);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      " 0x00000000 | 0x00000000 | 0x00000000_00000000 ",
      " 0x00012222 | 0x00013333 | 0x00012222_00013333 ",
      " 0x003FAAAA | 0x00BB9999 | 0x003FAAAA_00BB9999 ",
      " 0x00FFFFEE | 0x00FFFFFF | 0x00FFFFEE_00FFFFFF ",
      " 0x00FFFFFF | 0x00FFFFFF | 0x00FFFFFF_00FFFFFF ",
  }, delimiter = '|')
  void rangeToLong_creates64BitCodePointRangeFromTwo24BitCodePoints(
      final int inclusiveFrom, final int inclusiveTo,
      @ConvertWith(TripleByteRangeConverter.class) final long expected) {

    final long actual = RangedSubsetUtils.rangeToLong(inclusiveFrom, inclusiveTo);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void defaultIfNullOrEmpty_returnsAsExpectedForCharArray() {

    final char [] nullArray = null;
    final char [] emptyArray = new char[0];
    final char [] sampleArray = new char [] {'A', 'B'};

    assertThat(RangedSubsetUtils.defaultIfNullOrEmpty(nullArray, null)).isNull();
    assertThat(RangedSubsetUtils.defaultIfNullOrEmpty(nullArray, EMPTY_CHAR_ARRAY)).isSameAs(EMPTY_CHAR_ARRAY);
    assertThat(RangedSubsetUtils.defaultIfNullOrEmpty(nullArray, sampleArray)).isSameAs(sampleArray);

    assertThat(RangedSubsetUtils.defaultIfNullOrEmpty(emptyArray, null)).isNull();
    assertThat(RangedSubsetUtils.defaultIfNullOrEmpty(emptyArray, EMPTY_CHAR_ARRAY)).isSameAs(EMPTY_CHAR_ARRAY);
    assertThat(RangedSubsetUtils.defaultIfNullOrEmpty(emptyArray, sampleArray)).isSameAs(sampleArray);

    assertThat(RangedSubsetUtils.defaultIfNullOrEmpty(sampleArray, null)).isSameAs(sampleArray);
    assertThat(RangedSubsetUtils.defaultIfNullOrEmpty(sampleArray, EMPTY_CHAR_ARRAY)).isSameAs(sampleArray);
  }

  @Test
  void defaultIfNullOrEmpty_returnsAsExpectedForIntArray() {

    final int [] nullArray = null;
    final int [] emptyArray = new int[0];
    final int [] sampleArray = new int [] {'A', 'B'};

    assertThat(RangedSubsetUtils.defaultIfNullOrEmpty(nullArray, null)).isNull();
    assertThat(RangedSubsetUtils.defaultIfNullOrEmpty(nullArray, EMPTY_INT_ARRAY)).isSameAs(EMPTY_INT_ARRAY);
    assertThat(RangedSubsetUtils.defaultIfNullOrEmpty(nullArray, sampleArray)).isSameAs(sampleArray);

    assertThat(RangedSubsetUtils.defaultIfNullOrEmpty(emptyArray, null)).isNull();
    assertThat(RangedSubsetUtils.defaultIfNullOrEmpty(emptyArray, EMPTY_INT_ARRAY)).isSameAs(EMPTY_INT_ARRAY);
    assertThat(RangedSubsetUtils.defaultIfNullOrEmpty(emptyArray, sampleArray)).isSameAs(sampleArray);

    assertThat(RangedSubsetUtils.defaultIfNullOrEmpty(sampleArray, null)).isSameAs(sampleArray);
    assertThat(RangedSubsetUtils.defaultIfNullOrEmpty(sampleArray, EMPTY_INT_ARRAY)).isSameAs(sampleArray);
  }

  @Test
  void defaultIfNullOrEmpty_returnsAsExpectedForLongArray() {

    final long [] nullArray = null;
    final long [] emptyArray = new long[0];
    final long [] sampleArray = new long [] {'A', 'B'};

    assertThat(RangedSubsetUtils.defaultIfNullOrEmpty(nullArray, null)).isNull();
    assertThat(RangedSubsetUtils.defaultIfNullOrEmpty(nullArray, EMPTY_LONG_ARRAY)).isSameAs(EMPTY_LONG_ARRAY);
    assertThat(RangedSubsetUtils.defaultIfNullOrEmpty(nullArray, sampleArray)).isSameAs(sampleArray);

    assertThat(RangedSubsetUtils.defaultIfNullOrEmpty(emptyArray, null)).isNull();
    assertThat(RangedSubsetUtils.defaultIfNullOrEmpty(emptyArray, EMPTY_LONG_ARRAY)).isSameAs(EMPTY_LONG_ARRAY);
    assertThat(RangedSubsetUtils.defaultIfNullOrEmpty(emptyArray, sampleArray)).isSameAs(sampleArray);

    assertThat(RangedSubsetUtils.defaultIfNullOrEmpty(sampleArray, null)).isSameAs(sampleArray);
    assertThat(RangedSubsetUtils.defaultIfNullOrEmpty(sampleArray, EMPTY_LONG_ARRAY)).isSameAs(sampleArray);
  }

  @Test
  void aggregateCodePointRangeData_handlesNullArguments() {
    assertThat(RangedSubsetUtils.aggregateCodePointRangeData(null, null, null))
        .isNotNull()
        .isEmpty();
  }

  @Test
  void aggregateCodePointRangeData_handlesEmptyArguments() {
    assertThat(RangedSubsetUtils.aggregateCodePointRangeData(EMPTY_CHAR_ARRAY, EMPTY_INT_ARRAY, EMPTY_LONG_ARRAY))
        .isNotNull()
        .isEmpty();
  }

  @Test
  void aggregateCodePointRangeData_handlesCodePointRanges() {
    final char [] singleByteRanges = new char[]{0x21_22, 0x30_44};
    final int [] doubleByteRanges = new int[]{0x1122_1133, 0xAA11_BB00};
    final long [] tripleByteRanges = new long[]{0x00445500_00446600L, 0x00BB1100_00BB2200L};
    assertThat(RangedSubsetUtils.aggregateCodePointRangeData(singleByteRanges, doubleByteRanges, tripleByteRanges))
        .isNotNull()
        .containsExactly(
            new ImmutableCodePointRange(0x21, 0x22),
            new ImmutableCodePointRange(0x30, 0x44),
            new ImmutableCodePointRange(0x1122, 0x1133),
            new ImmutableCodePointRange(0xAA11, 0xBB00),
            new ImmutableCodePointRange(0x00445500, 0x00446600),
            new ImmutableCodePointRange(0x00BB1100, 0x00BB2200));
  }

  @ParameterizedTest
  @ValueSource(ints = {
      0x30,
      0x35,
      0x44,
      0x1133,
      0x00112233,
  })
  void contains_returnFalseForNullInputRanges(final int codePoint) {
    assertThat(RangedSubsetUtils.contains(codePoint, null, null, null))
        .isFalse();
  }

  @ParameterizedTest
  @ValueSource(ints = {
      0x30,
      0x35,
      0x44,
      0x1133,
      0x00112233,
  })
  void contains_returnFalseForEmptyInputRanges(final int codePoint) {
    assertThat(RangedSubsetUtils.contains(codePoint, EMPTY_CHAR_ARRAY, EMPTY_INT_ARRAY, EMPTY_LONG_ARRAY))
        .isFalse();
  }

  @ParameterizedTest
  @ValueSource(ints = {
      0x21,
      0x22,
      0x30,
      0x35,
      0x44,
      0x1122,
      0x1130,
      0x1133,
      0xAA11,
      0xAA99,
      0xBB00,
      0x00445500,
      0x00445900,
      0x00446600,
      0x00BB1100,
      0x00BB1199,
      0x00BB2200
  })
  void contains_returnTrueForCodePointsInclusivelyWithinRanges(final int codePoint) {
    final char [] singleByteRanges = new char[]{0x21_22, 0x30_44};
    final int [] doubleByteRanges = new int[]{0x1122_1133, 0xAA11_BB00};
    final long [] tripleByteRanges = new long[]{0x00445500_00446600L, 0x00BB1100_00BB2200L};

    assertThat(RangedSubsetUtils.contains(codePoint, singleByteRanges, doubleByteRanges, tripleByteRanges))
        .isTrue();
  }

  @ParameterizedTest
  @ValueSource(ints = {
      0x00,
      0x20,
      0x23,
      0x45,
      0x1121,
      0x1134,
      0xAA10,
      0xBB01,
      0x00445499,
      0x00446601,
      0x00BB1099,
      0x00BB2201,
      0x00FFFFFF,
  })
  void contains_returnFalseForCodePointsOutsideOfRanges(final int codePoint) {
    final char [] singleByteRanges = new char[]{0x21_22, 0x30_44};
    final int [] doubleByteRanges = new int[]{0x1122_1133, 0xAA11_BB00};
    final long [] tripleByteRanges = new long[]{0x00445500_00446600L, 0x00BB1100_00BB2200L};

    assertThat(RangedSubsetUtils.contains(codePoint, singleByteRanges, doubleByteRanges, tripleByteRanges))
        .isFalse();
  }

  static class SingleByteRangeConverter implements ArgumentConverter {

    @Override
    public Object convert(Object source, ParameterContext context) throws ArgumentConversionException {
      try {
        final String value = ((String) source).replaceFirst("0x", "").replaceFirst("_", "");
        return (char) Integer.parseInt(value, 16);
      } catch (Exception e) {
        throw new IllegalArgumentException("The argument should be a string defining a range in format `0x00_00`: " + source, e);
      }
    }
  }

  static class DoubleByteRangeConverter implements ArgumentConverter {

    @Override
    public Object convert(Object source, ParameterContext context) throws ArgumentConversionException {
      try {
        final String value = ((String) source).replaceFirst("0x", "").replaceFirst("_", "");
        return (int) Long.parseLong(value, 16);
      } catch (Exception e) {
        throw new IllegalArgumentException("The argument should be a string defining a range in format `0x0000_0000`: " + source, e);
      }
    }
  }

  static class TripleByteRangeConverter implements ArgumentConverter {

    @Override
    public Object convert(Object source, ParameterContext context) throws ArgumentConversionException {
      try {
        final String value = ((String) source).replaceFirst("0x", "").replaceFirst("_", "");
        return Long.parseLong(value, 16);
      } catch (Exception e) {
        throw new IllegalArgumentException("The argument should be a string defining a range in format `0x00000000_00000000`: " + source, e);
      }
    }
  }
}