package org.typefactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class CharSequenceTypeTest {

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {
      "       ",
      " \t    ",
      " \t \n "})
  void isBlank_returnsTrueWhenUnderlyingValueIsBlankString(final String value) {
    final var actual = new ConcreteCharSequenceType(value);
    assertThat(actual.isBlank()).isTrue();
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "       a ",
      " \t    a ",
      " \t \n a ",
      "       \uD800", // char is surrogate without final char
      "       \uD800\uDC82 ", // 𐂂
      "       \uD83D\uDE00 ", // 😀
  })
  void isBlank_returnsFalseWhenUnderlyingValueIsNonBlankString(final String value) {
    final var actual = new ConcreteCharSequenceType(value);
    assertThat(actual.isBlank()).isFalse();
  }

  @ParameterizedTest
  @NullAndEmptySource
  void length_returnsZeroForNullAndEmpty(final String value) {
    final var actual = new ConcreteCharSequenceType(value);
    assertThat(actual.length()).isZero();
  }

  @ParameterizedTest
  @CsvSource(value = {
      "a|1",
      "ab|2",
      "abc|3",
      "abc\uD800\uDC82|5", // 𐂂
      "abc\uD83D\uDE00|5", // 😀
  }, delimiter = '|')
  void length_returnsNonZeroValues(final String value, final int expectedLength) {
    final var actual = new ConcreteCharSequenceType(value);
    assertThat(actual.length()).isEqualTo(expectedLength);
  }

  @ParameterizedTest
  @NullAndEmptySource
  void charAt_throwsExceptionForNullAndEmpty(final String value) {
    final var actual = new ConcreteCharSequenceType(value);
    assertThrows(StringIndexOutOfBoundsException.class, () -> actual.charAt(0));
  }

  @ParameterizedTest
  @CsvSource(value = {
      "a|0|a",
      "ab|1|b",
      "abc|2|c",
      "abc\uD800\uDC82|3|\uD800", // 𐂂
      "abc\uD83D\uDE00|4|\uDE00", // 😀
  }, delimiter = '|')
  void charAt_returnsExpectedCharacter(
      final String value,
      final int index,
      final char expectedChar) {

    final var actual = new ConcreteCharSequenceType(value);
    assertThat(actual.charAt(index)).isEqualTo(expectedChar);
  }

  @ParameterizedTest
  @NullAndEmptySource
  void subSequence_throwsExceptionForNullAndEmpty(final String value) {
    final var actual = new ConcreteCharSequenceType(value);
    assertThrows(StringIndexOutOfBoundsException.class, () -> actual.subSequence(0, 1));
  }

  @ParameterizedTest
  @CsvSource(value = {
      "a|0|1|a",
      "ab|1|2|b",
      "abc|0|2|ab",
      "abc|1|3|bc",
      "abc\uD800\uDC82|3|5|\uD800\uDC82", // 𐂂
      "abc\uD83D\uDE00|3|5|\uD83D\uDE00", // 😀
  }, delimiter = '|')
  void subSequence_returnsExpectedCharSequence(
      final String value,
      final int startIndex,
      final int endIndex,
      final CharSequence expectedCharSequence) {

    final var actual = new ConcreteCharSequenceType(value);
    assertThat(actual.subSequence(startIndex, endIndex)).isEqualTo(expectedCharSequence);
  }

  @Test
  void compareTo_returnsZeroWhenBothContainNullValues() {
    final var actual1 = new ConcreteCharSequenceType(null);
    final var actual2 = new ConcreteCharSequenceType(null);
    assertThat(actual1.compareTo(actual2)).isZero();
  }

  @Test
  void compareTo_returnsZeroWhenOtherObjectIsNullAndThisContainsNull() {
    final var actual1 = new ConcreteCharSequenceType(null);
    assertThat(actual1.compareTo(null)).isZero();
  }

  @ParameterizedTest
  @EmptySource
  @ValueSource(strings = {
      " ",
      "a",
      "ab",
      "abc"})
  void compareTo_returnsOneWhenOtherObjectIsNullAndThisContainsValue(final String value) {
    final var actual1 = new ConcreteCharSequenceType(value);
    assertThat(actual1.compareTo(null)).isOne();
  }

  @ParameterizedTest
  @EmptySource
  @ValueSource(strings = {
      " ",
      "a",
      "ab",
      "abc"})
  void compareTo_returnsOneWhenOtherObjectContainsNullAndThisContainsValue(final String value) {
    final var actual1 = new ConcreteCharSequenceType(value);
    final var actual2 = new ConcreteCharSequenceType(null);
    assertThat(actual1.compareTo(actual2)).isOne();
  }

  @ParameterizedTest
  @EmptySource
  @ValueSource(strings = {
      " ",
      "a",
      "ab",
      "abc"})
  void compareTo_returnsNegativeOneWhenOtherObjectHasValueAndThisContainsNull(final String value) {
    final var actual1 = new ConcreteCharSequenceType(null);
    final var actual2 = new ConcreteCharSequenceType(value);
    assertThat(actual1.compareTo(actual2)).isNegative();
  }

  @ParameterizedTest
  @CsvSource(value = {
      " a   | a   ",
      " bb  | bb  ",
      " abc | abc ",
  }, delimiter = '|')
  void compareTo_returnsZero(final String value1, final String value2) {
    final var actual1 = new ConcreteCharSequenceType(value1);
    final var actual2 = new ConcreteCharSequenceType(value2);
    assertThat(actual1.compareTo(actual2)).isZero();
  }

  @ParameterizedTest
  @CsvSource(value = {
      " b   | a   ",
      " bc  | ab  ",
      " acc | abc "
  }, delimiter = '|')
  void compareTo_returnsPositive(final String value1, final String value2) {
    final var actual1 = new ConcreteCharSequenceType(value1);
    final var actual2 = new ConcreteCharSequenceType(value2);
    assertThat(actual1.compareTo(actual2)).isPositive();
  }

  @ParameterizedTest
  @CsvSource(value = {
      " a   | b   ",
      " ab  | bc  ",
      " abc | acc "
  }, delimiter = '|')
  void compareTo_returnsNegative(final String value1, final String value2) {
    final var actual1 = new ConcreteCharSequenceType(value1);
    final var actual2 = new ConcreteCharSequenceType(value2);
    assertThat(actual1.compareTo(actual2)).isNegative();
  }

  @ParameterizedTest
  @CsvSource(value = {
      " a   | a   ",
      " bb  | bb  ",
      " abc | abc ",
  }, delimiter = '|')
  void compareTo_returnsZeroForCustomCharSequenceValues(final String value1, final String value2) {
    final var actual1 = new ConcreteCharSequenceType(new CustomCharSequence(value1));
    final var actual2 = new ConcreteCharSequenceType(new CustomCharSequence(value2));
    assertThat(actual1.compareTo(actual2)).isZero();
  }

  @ParameterizedTest
  @CsvSource(value = {
      " aa   | a   ",
      " b    | a   ",
      " bc   | ab  ",
      " acc  | abc ",
      " abcd | abc ",
  }, delimiter = '|')
  void compareTo_returnsPositiveForCustomCharSequenceValues(final String value1, final String value2) {
    final var actual1 = new ConcreteCharSequenceType(new CustomCharSequence(value1));
    final var actual2 = new ConcreteCharSequenceType(new CustomCharSequence(value2));
    assertThat(actual1.compareTo(actual2)).isPositive();
  }

  @ParameterizedTest
  @CsvSource(value = {
      " aa   | a   ",
      " b    | a   ",
      " bc   | ab  ",
      " acc  | abc ",
      " abcd | abc ",
  }, delimiter = '|')
  void compareTo_returnsPositiveForForDifferingInternalTypes(final String value1, final String value2) {
    {
      final var actual1 = new ConcreteCharSequenceType(value1); // string internal type
      final var actual2 = new ConcreteCharSequenceType(new CustomCharSequence(value2)); // custom internal type
      assertThat(actual1.compareTo(actual2)).isPositive();
    }
    {
      final var actual1 = new ConcreteCharSequenceType(new CustomCharSequence(value1)); // custom internal type
      final var actual2 = new ConcreteCharSequenceType(value2); // string internal type
      assertThat(actual1.compareTo(actual2)).isPositive();
    }
  }

  @ParameterizedTest
  @CsvSource(value = {
      " a   | aa   ",
      " a   | b    ",
      " ab  | bc   ",
      " abc | acc  ",
      " abc | abcd ",
  }, delimiter = '|')
  void compareTo_returnsNegativeForCustomCharSequenceValues(final String value1, final String value2) {
    final var actual1 = new ConcreteCharSequenceType(new CustomCharSequence(value1));
    final var actual2 = new ConcreteCharSequenceType(new CustomCharSequence(value2));
    assertThat(actual1.compareTo(actual2)).isNegative();
  }

  @ParameterizedTest
  @CsvSource(value = {
      " a   | aa   ",
      " a   | b    ",
      " ab  | bc   ",
      " abc | acc  ",
      " abc | abcd ",
  }, delimiter = '|')
  void compareTo_returnsNegativeForDifferingInternalTypes(final String value1, final String value2) {
    {
      final var actual1 = new ConcreteCharSequenceType(value1); // string internal type
      final var actual2 = new ConcreteCharSequenceType(new CustomCharSequence(value2)); // custom internal type
      assertThat(actual1.compareTo(actual2)).isNegative();
    }
    {
      final var actual1 = new ConcreteCharSequenceType(new CustomCharSequence(value1)); // custom internal type
      final var actual2 = new ConcreteCharSequenceType(value2); // string internal type
      assertThat(actual1.compareTo(actual2)).isNegative();
    }
  }

  static class ConcreteCharSequenceType implements CharSequenceType<ConcreteCharSequenceType> {

    final CharSequence value;

    public ConcreteCharSequenceType(final CharSequence value) {
      this.value = value;
    }

    @Override
    public CharSequence value() {
      return value;
    }
  }

  static class CustomCharSequence implements CharSequence {

    final char [] chars;

    CustomCharSequence(final char [] chars) {
      this.chars = chars;
    }

    CustomCharSequence(final String value) {
      this(value.toCharArray());
    }

    @Override
    public int length() {
      return chars.length;
    }

    @Override
    public char charAt(int index) {
      return chars[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
      final char [] temp = new char[end-start];
      System.arraycopy(chars, start, temp, 0, end-start);
      return new CustomCharSequence(temp);
    }
  }
}
