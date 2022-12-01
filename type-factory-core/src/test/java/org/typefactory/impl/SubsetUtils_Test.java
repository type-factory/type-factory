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
import static org.typefactory.impl.Constants.EMPTY_CHAR_ARRAY;
import static org.typefactory.impl.Constants.EMPTY_INT_ARRAY;
import static org.typefactory.impl.Constants.EMPTY_LONG_ARRAY;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.typefactory.Subset.CodePointRange;

class SubsetUtils_Test {

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

    final int actual = SubsetUtils.getInclusiveFrom(range);
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

    final int actual = SubsetUtils.getInclusiveTo(range);
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

    final int actual = SubsetUtils.getInclusiveFrom(range);
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

    final int actual = SubsetUtils.getInclusiveTo(range);
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

    final int actual = SubsetUtils.getInclusiveFrom(range);
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

    final int actual = SubsetUtils.getInclusiveTo(range);
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

    final char actual = SubsetUtils.rangeToChar(inclusiveFrom, inclusiveTo);
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

    final int actual = SubsetUtils.rangeToInt(inclusiveFrom, inclusiveTo);
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

    final long actual = SubsetUtils.rangeToLong(inclusiveFrom, inclusiveTo);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void defaultIfNullOrEmpty_returnsAsExpectedForCharArray() {

    final char[] nullArray = null;
    final char[] emptyArray = new char[0];
    final char[] sampleArray = new char[]{'A', 'B'};

    assertThat(SubsetUtils.defaultIfNullOrEmpty(nullArray, null)).isNull();
    assertThat(SubsetUtils.defaultIfNullOrEmpty(nullArray, EMPTY_CHAR_ARRAY)).isSameAs(EMPTY_CHAR_ARRAY);
    assertThat(SubsetUtils.defaultIfNullOrEmpty(nullArray, sampleArray)).isSameAs(sampleArray);

    assertThat(SubsetUtils.defaultIfNullOrEmpty(emptyArray, null)).isNull();
    assertThat(SubsetUtils.defaultIfNullOrEmpty(emptyArray, EMPTY_CHAR_ARRAY)).isSameAs(EMPTY_CHAR_ARRAY);
    assertThat(SubsetUtils.defaultIfNullOrEmpty(emptyArray, sampleArray)).isSameAs(sampleArray);

    assertThat(SubsetUtils.defaultIfNullOrEmpty(sampleArray, null)).isSameAs(sampleArray);
    assertThat(SubsetUtils.defaultIfNullOrEmpty(sampleArray, EMPTY_CHAR_ARRAY)).isSameAs(sampleArray);
  }

  @Test
  void defaultIfNullOrEmpty_returnsAsExpectedForIntArray() {

    final int[] nullArray = null;
    final int[] emptyArray = new int[0];
    final int[] sampleArray = new int[]{'A', 'B'};

    assertThat(SubsetUtils.defaultIfNullOrEmpty(nullArray, null)).isNull();
    assertThat(SubsetUtils.defaultIfNullOrEmpty(nullArray, EMPTY_INT_ARRAY)).isSameAs(EMPTY_INT_ARRAY);
    assertThat(SubsetUtils.defaultIfNullOrEmpty(nullArray, sampleArray)).isSameAs(sampleArray);

    assertThat(SubsetUtils.defaultIfNullOrEmpty(emptyArray, null)).isNull();
    assertThat(SubsetUtils.defaultIfNullOrEmpty(emptyArray, EMPTY_INT_ARRAY)).isSameAs(EMPTY_INT_ARRAY);
    assertThat(SubsetUtils.defaultIfNullOrEmpty(emptyArray, sampleArray)).isSameAs(sampleArray);

    assertThat(SubsetUtils.defaultIfNullOrEmpty(sampleArray, null)).isSameAs(sampleArray);
    assertThat(SubsetUtils.defaultIfNullOrEmpty(sampleArray, EMPTY_INT_ARRAY)).isSameAs(sampleArray);
  }

  @Test
  void defaultIfNullOrEmpty_returnsAsExpectedForLongArray() {

    final long[] nullArray = null;
    final long[] emptyArray = new long[0];
    final long[] sampleArray = new long[]{'A', 'B'};

    assertThat(SubsetUtils.defaultIfNullOrEmpty(nullArray, null)).isNull();
    assertThat(SubsetUtils.defaultIfNullOrEmpty(nullArray, EMPTY_LONG_ARRAY)).isSameAs(EMPTY_LONG_ARRAY);
    assertThat(SubsetUtils.defaultIfNullOrEmpty(nullArray, sampleArray)).isSameAs(sampleArray);

    assertThat(SubsetUtils.defaultIfNullOrEmpty(emptyArray, null)).isNull();
    assertThat(SubsetUtils.defaultIfNullOrEmpty(emptyArray, EMPTY_LONG_ARRAY)).isSameAs(EMPTY_LONG_ARRAY);
    assertThat(SubsetUtils.defaultIfNullOrEmpty(emptyArray, sampleArray)).isSameAs(sampleArray);

    assertThat(SubsetUtils.defaultIfNullOrEmpty(sampleArray, null)).isSameAs(sampleArray);
    assertThat(SubsetUtils.defaultIfNullOrEmpty(sampleArray, EMPTY_LONG_ARRAY)).isSameAs(sampleArray);
  }

  @Test
  void aggregateCodePointRangeData_handlesNullArguments() {
    assertThat(SubsetUtils.aggregateCodePointRangeData(null, null, null))
        .isNotNull()
        .isEmpty();
  }

  @Test
  void aggregateCodePointRangeData_handlesEmptyArguments() {
    assertThat(SubsetUtils.aggregateCodePointRangeData(EMPTY_CHAR_ARRAY, EMPTY_INT_ARRAY, EMPTY_LONG_ARRAY))
        .isNotNull()
        .isEmpty();
  }

  @Test
  void aggregateCodePointRangeData_handlesCodePointRanges() {
    final char[] singleByteRanges = new char[]{0x21_22, 0x30_44};
    final int[] doubleByteRanges = new int[]{0x1122_1133, 0xAA11_BB00};
    final long[] tripleByteRanges = new long[]{0x00445500_00446600L, 0x00BB1100_00BB2200L};
    assertThat(SubsetUtils.aggregateCodePointRangeData(singleByteRanges, doubleByteRanges, tripleByteRanges))
        .isNotNull()
        .containsExactly(
            new CodePointRange(0x21, 0x22),
            new CodePointRange(0x30, 0x44),
            new CodePointRange(0x1122, 0x1133),
            new CodePointRange(0xAA11, 0xBB00),
            new CodePointRange(0x00445500, 0x00446600),
            new CodePointRange(0x00BB1100, 0x00BB2200));
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
    assertThat(SubsetUtils.contains(codePoint, null, null, null))
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
    assertThat(SubsetUtils.contains(codePoint, EMPTY_CHAR_ARRAY, EMPTY_INT_ARRAY, EMPTY_LONG_ARRAY))
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
    final char[] singleByteRanges = new char[]{0x21_22, 0x30_44};
    final int[] doubleByteRanges = new int[]{0x1122_1133, 0xAA11_BB00};
    final long[] tripleByteRanges = new long[]{0x00445500_00446600L, 0x00BB1100_00BB2200L};

    assertThat(SubsetUtils.contains(codePoint, singleByteRanges, doubleByteRanges, tripleByteRanges))
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
    final char[] singleByteRanges = new char[]{0x21_22, 0x30_44};
    final int[] doubleByteRanges = new int[]{0x1122_1133, 0xAA11_BB00};
    final long[] tripleByteRanges = new long[]{0x00445500_00446600L, 0x00BB1100_00BB2200L};

    assertThat(SubsetUtils.contains(codePoint, singleByteRanges, doubleByteRanges, tripleByteRanges))
        .isFalse();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      0x20_2F, 0x3A_45, 0xE1_F1 | 0 | 0x3A_45, 0xE1_F1
      0x20_2F, 0x3A_45, 0xE1_F1 | 1 | 0x20_2F, 0xE1_F1
      0x20_2F, 0x3A_45, 0xE1_F1 | 2 | 0x20_2F, 0x3A_45
      """, delimiter = '|')
  void removeSingleByteElement_removesElement(final String rangesStr, final int indexOfElementToRemove, final String expectedRangesStr) {
    final char[] ranges = convertStringOfCharValuesToCharArray(rangesStr);
    final char[] expectedRanges = convertStringOfCharValuesToCharArray(expectedRangesStr);

    final int newLength = SubsetUtils.removeSingleByteElement(ranges, ranges.length, indexOfElementToRemove);
    final char[] actual = Arrays.copyOf(ranges, newLength);
    assertThat(actual)
        .hasSize(2)
        .containsExactly(expectedRanges);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      0x2000_2F00, 0x3A00_4500, 0x7100_F100 | 0 | 0x3A00_4500, 0x7100_F100
      0x2000_2F00, 0x3A00_4500, 0x7100_F100 | 1 | 0x2000_2F00, 0x7100_F100
      0x2000_2F00, 0x3A00_4500, 0x7100_F100 | 2 | 0x2000_2F00, 0x3A00_4500
      """, delimiter = '|')
  void removeDoubleByteElement_removesElement(final String rangesStr, final int indexOfElementToRemove, final String expectedRangesStr) {
    final int[] ranges = convertStringOfIntValuesToIntArray(rangesStr);
    final int[] expectedRanges = convertStringOfIntValuesToIntArray(expectedRangesStr);

    final int newLength = SubsetUtils.removeDoubleByteElement(ranges, ranges.length, indexOfElementToRemove);
    final int[] actual = Arrays.copyOf(ranges, newLength);
    assertThat(actual)
        .hasSize(2)
        .containsExactly(expectedRanges);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      0x20001111_2F001111, 0x3A002222_45002222, 0x71003333_F1003333 | 0 | 0x3A002222_45002222, 0x71003333_F1003333
      0x20001111_2F001111, 0x3A002222_45002222, 0x71003333_F1003333 | 1 | 0x20001111_2F001111, 0x71003333_F1003333
      0x20001111_2F001111, 0x3A002222_45002222, 0x71003333_F1003333 | 2 | 0x20001111_2F001111, 0x3A002222_45002222
      """, delimiter = '|')
  void removeTripleByteElement_removesElement(final String rangesStr, final int indexOfElementToRemove, final String expectedRangesStr) {
    final long[] ranges = convertStringOfLongValuesToLongArray(rangesStr);
    final long[] expectedRanges = convertStringOfLongValuesToLongArray(expectedRangesStr);

    final int newLength = SubsetUtils.removeTripleByteElement(ranges, ranges.length, indexOfElementToRemove);
    final long[] actual = Arrays.copyOf(ranges, newLength);
    assertThat(actual)
        .hasSize(2)
        .containsExactly(expectedRanges);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      0x00_30, 0x31_32, 0x33_44 | 0x00_44
      0x00_30, 0x29_32, 0x31_44 | 0x00_44
      0x00_30, 0x32_32, 0x33_44 | 0x00_30, 0x32_44
      0x32_33, 0x22_44,         | 0x22_44
      0x32_33, 0x35_37, 0x22_44 | 0x22_44
      """, delimiter = '|')
  void compactSingleByteCodePointRanges_coalescesRangesAsExpected(final String rangesStr, final String expectedRangesStr){
    final char[] ranges = convertStringOfCharValuesToCharArray(rangesStr);
    final char[] expectedRanges = convertStringOfCharValuesToCharArray(expectedRangesStr);

    final int newLength = SubsetUtils.compactSingleByteCodePointRanges(ranges, ranges.length);
    final char[] actual = Arrays.copyOf(ranges, newLength);
    assertThat(actual).containsExactly(expectedRanges);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      0x0000_30FF, 0x3100_32FF, 0x3300_4400 | 0x0000_4400
      0x0000_3000, 0x2900_3200, 0x3100_4400 | 0x0000_4400
      0x0000_3000, 0x3200_32FF, 0x3300_4400 | 0x0000_3000, 0x3200_4400
      0x3200_3300, 0x2200_4400,             | 0x2200_4400
      0x3200_3300, 0x3500_3700, 0x2200_4400 | 0x2200_4400
      """, delimiter = '|')
  void compactDoubleByteCodePointRanges_coalescesRangesAsExpected(final String rangesStr, final String expectedRangesStr){
    final int[] ranges = convertStringOfIntValuesToIntArray(rangesStr);
    final int[] expectedRanges = convertStringOfIntValuesToIntArray(expectedRangesStr);

    final int newLength = SubsetUtils.compactDoubleByteCodePointRanges(ranges, ranges.length);
    final int[] actual = Arrays.copyOf(ranges, newLength);
    assertThat(actual).containsExactly(expectedRanges);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      0x00000000_30FFFFFF, 0x31000000_32FFFFFF, 0x33000000_44000000 | 0x00000000_44000000
      0x00000000_30000000, 0x29000000_32000000, 0x31000000_44000000 | 0x00000000_44000000
      0x00000000_30000000, 0x32000000_32FFFFFF, 0x33000000_44000000 | 0x00000000_30000000, 0x32000000_44000000
      0x32000000_33000000, 0x22000000_44000000,                     | 0x22000000_44000000
      0x32000000_33000000, 0x35000000_37000000, 0x22000000_44000000 | 0x22000000_44000000
      """, delimiter = '|')
  void compactTripleByteCodePointRanges_coalescesRangesAsExpected(final String rangesStr, final String expectedRangesStr){
    final long[] ranges = convertStringOfLongValuesToLongArray(rangesStr);
    final long[] expectedRanges = convertStringOfLongValuesToLongArray(expectedRangesStr);

    final int newLength = SubsetUtils.compactTripleByteCodePointRanges(ranges, ranges.length);
    final long[] actual = Arrays.copyOf(ranges, newLength);
    assertThat(actual).containsExactly(expectedRanges);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      0000_0000_0000_0000_0000_0000_0000_0000 | 0
      0000_0000_0000_0000_0000_0000_0000_0001 | 1
      1000_0000_0000_0000_0000_0000_0000_0000 | 1
      0000_0000_0000_0000_0000_0010_0000_0000 | 1
      0000_0000_0000_0000_0000_0010_0000_0001 | 2
      0000_0000_1000_0000_0000_0010_0000_0001 | 3
      1111_1111_1111_1111_1111_1111_1111_1111 | 32
      """, delimiter = '|')
  void numberOfUnicodeCategoriesFromCategoriesFlags_(final String categoryBitFlagsStr, final int expectedNumberOfCategories){
    final long categoryBitFlags = Long.parseLong(categoryBitFlagsStr.replace("_", ""), 2);
    final int actual = SubsetUtils.numberOfUnicodeCategoriesFromCategoriesFlags(categoryBitFlags);
    assertThat(actual).isEqualTo(expectedNumberOfCategories);
  }

  char[] convertStringOfCharValuesToCharArray(final String charArrayStr) {
    final int[] ints = Arrays.stream(charArrayStr
            .replace("0x", "")
            .replace("_", "")
            .split(",\s*+"))
        .mapToInt(i -> Integer.parseInt(i, 16))
        .toArray();
    final char[] chars = new char[ints.length];
    for (int i = 0; i < ints.length; ++i) {
      chars[i] = (char) ints[i];
    }
    return chars;
  }

  int[] convertStringOfIntValuesToIntArray(final String charArrayStr) {
    return Arrays.stream(charArrayStr
            .replace("0x", "")
            .replace("_", "")
            .split(",\s*+"))
        .mapToInt(i -> Integer.parseInt(i, 16))
        .toArray();
  }

  long[] convertStringOfLongValuesToLongArray(final String charArrayStr) {
    return Arrays.stream(charArrayStr
            .replace("0x", "")
            .replace("_", "")
            .split(",\s*+"))
        .mapToLong(i -> Long.parseLong(i, 16))
        .toArray();
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
