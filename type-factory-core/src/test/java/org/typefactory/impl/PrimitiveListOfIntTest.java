package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.testutils.IntArrayConverter;

public class PrimitiveListOfIntTest {

  @Test
  void addAll_ShouldHandleNullList() {
    PrimitiveListOfInt list = new PrimitiveListOfInt();
    assertThat(list.addAll((int[])null)).isFalse();
    assertThat(list.toArray()).isEmpty();
  }

  @Test
  void addAll_ShouldHandleEmptyList() {
    PrimitiveListOfInt list = new PrimitiveListOfInt();
    assertThat(list.addAll(Constants.EMPTY_INT_ARRAY)).isFalse();
    assertThat(list.toArray()).isEmpty();
  }

  @ParameterizedTest
  @CsvSource(delimiter = '|', textBlock = """
      1 | 2 | 3 | 2 | [1,2,3,2]
      4 | 6 | 5 | 6 | [4,6,5,6]
  """)
  void addAll_ShouldAddAllElements(int value1, int value2, int value3, int value4, @ConvertWith(IntArrayConverter.class) int[] expected) {
    final PrimitiveListOfInt list = new PrimitiveListOfInt();
    assertThat(list.add(value1)).isTrue();
    assertThat(list.add(value2)).isTrue();
    assertThat(list.add(value3)).isTrue();
    assertThat(list.add(value4)).isTrue();
    assertThat(list.toArray()).containsExactly(expected);
  }

  @ParameterizedTest
  @CsvSource(delimiter = '|', textBlock = """
      [2,1,3] | [2,1,3]
      [4,5,6] | [4,5,6]
  """)
  void addAll_ShouldAddAllMultipleElements(@ConvertWith(IntArrayConverter.class) int[] values, @ConvertWith(IntArrayConverter.class) int[] expected) {
    PrimitiveListOfInt list = new PrimitiveListOfInt();
    assertThat(list.addAll(values)).isTrue();
    assertThat(list.toArray()).containsExactly(expected);
  }

  @ParameterizedTest
  @CsvSource(delimiter = '|', textBlock = """
      [2,1,3] | 0 | 2 | [1,3]
      [4,5,6] | 1 | 5 | [4,6]
  """)
  void remove_ShouldRemoveElementAtIndex(
      @ConvertWith(IntArrayConverter.class) int[] values,
      int removeIndex, int expectedRemoved,
      @ConvertWith(IntArrayConverter.class) int[] expected) {

    final PrimitiveListOfInt list = new PrimitiveListOfInt();
    list.addAll(values);
    final int removed = list.remove(removeIndex);
    assertThat(removed).isEqualTo(expectedRemoved);
    assertThat(list.toArray()).containsExactly(expected);
  }

  @ParameterizedTest
  @CsvSource(delimiter = '|', textBlock = """
      [2,1,3] | 0 | 2
      [4,5,6] | 2 | 6
  """)
  void get_ShouldReturnElementAtIndex(@ConvertWith(IntArrayConverter.class) int[] values, int getIndex, int expected) {
    final PrimitiveListOfInt list = new PrimitiveListOfInt();
    list.addAll(values);
    assertThat(list.get(getIndex)).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(delimiter = '|', textBlock = """
      [2,1,3] | 3
      [4,5,6] | 3
  """)
  void get_ShouldThrowException_WhenIndexIsOutOfBounds(@ConvertWith(IntArrayConverter.class) int[] values, int getIndex) {
    final PrimitiveListOfInt list = new PrimitiveListOfInt();
    list.addAll(values);
    assertThatThrownBy(() -> list.get(getIndex))
        .isInstanceOf(IndexOutOfBoundsException.class);
  }

  @ParameterizedTest
  @CsvSource(delimiter = '|', textBlock = """
      [3,1,2] | [1,2,3]
      [6,4,5] | [4,5,6]
  """)
  void sort_ShouldSortElements(@ConvertWith(IntArrayConverter.class) int[] values, @ConvertWith(IntArrayConverter.class) int[] expected) {
    final PrimitiveListOfInt list = new PrimitiveListOfInt();
    list.addAll(values);
    list.sort();
    assertThat(list.toArray()).containsExactly(expected);
  }

  @ParameterizedTest
  @CsvSource(delimiter = '|', textBlock = """
      [3,1,2,5,6,1]
      [6,4,5,10,15,12]
  """)
  void toArray_ShouldReturnArray(@ConvertWith(IntArrayConverter.class) int[] values) {
    PrimitiveListOfInt list = new PrimitiveListOfInt();
    list.addAll(values);
    assertThat(list.toArray()).containsExactly(values);
  }
}