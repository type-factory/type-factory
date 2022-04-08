package land.artli.easytype;

import static land.artli.easytype.RangedSubsetUtils.EMPTY_CHAR_ARRAY;
import static land.artli.easytype.RangedSubsetUtils.EMPTY_INT_ARRAY;
import static land.artli.easytype.RangedSubsetUtils.EMPTY_LONG_ARRAY;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

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
