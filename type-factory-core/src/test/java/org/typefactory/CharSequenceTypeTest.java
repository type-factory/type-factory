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
import static org.assertj.core.api.Assertions.assertThatComparable;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.Serial;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CharSequenceTypeTest {

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {
      "       ",
      " \t    ",
      " \t \n "})
  void isBlank_returnsTrueWhenUnderlyingValueIsBlankString(final String value) {
    final var actual1 = new ConcreteCharSequenceType(value);
    final var actual2 = new ConcreteCharSequenceType(CustomCharSequence.of(value));
    assertThat(actual1.isBlank()).isTrue();
    assertThat(actual2.isBlank()).isTrue();
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
    final var actual1 = new ConcreteCharSequenceType(value);
    final var actual2 = new ConcreteCharSequenceType(CustomCharSequence.of(value));
    assertThat(actual1.isBlank()).isFalse();
    assertThat(actual2.isBlank()).isFalse();
  }

  @Test
  void static_isBlank_returnsTrueWhenPassedNull() {
    assertThat(CharSequenceType.isBlank(null)).isTrue();
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {
      "       ",
      " \t    ",
      " \t \n "})
  void static_isBlank_returnsTrueWhenUnderlyingValueIsBlankString(final String value) {
    final var actual1 = new ConcreteCharSequenceType(value);
    final var actual2 = new ConcreteCharSequenceType(CustomCharSequence.of(value));
    assertThat(CharSequenceType.isBlank(actual1)).isTrue();
    assertThat(CharSequenceType.isBlank(actual2)).isTrue();
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
  void static_isBlank_returnsFalseWhenUnderlyingValueIsNonBlankString(final String value) {
    final var actual1 = new ConcreteCharSequenceType(value);
    final var actual2 = new ConcreteCharSequenceType(CustomCharSequence.of(value));
    assertThat(CharSequenceType.isBlank(actual1)).isFalse();
    assertThat(CharSequenceType.isBlank(actual2)).isFalse();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      null      | someDefault | someDefault
      ''        | someDefault | someDefault
      '   '     | someDefault | someDefault
      a         | someDefault | a
      someValue | someDefault | someValue
      """, delimiter = '|', nullValues = "null")
  void static_defaultIfBlank_returnsAsExpected(
      final String valueStr, final String defaultValueStr, final String expectedValue) {
    final var actualValue = new ConcreteCharSequenceType(valueStr);
    final var defaultValue = new ConcreteCharSequenceType(defaultValueStr);
    final var actual = CharSequenceType.defaultIfBlank(actualValue, defaultValue);
    assertThat(actual.value()).isEqualTo(expectedValue);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      null      | someDefault | someDefault
      ''        | someDefault | someDefault
      '   '     | someDefault | '   '
      a         | someDefault | a
      someValue | someDefault | someValue
      """, delimiter = '|', nullValues = "null")
  void static_defaultIfEmpty_returnsAsExpected(
      final String valueStr, final String defaultValueStr, final String expectedValue) {
    final var actualValue = new ConcreteCharSequenceType(valueStr);
    final var defaultValue = new ConcreteCharSequenceType(defaultValueStr);
    final var actual = CharSequenceType.defaultIfEmpty(actualValue, defaultValue);
    assertThat(actual.value()).isEqualTo(expectedValue);
  }

  @Test
  void static_isEmpty_returnsTrueWhenPassedNull() {
    assertThat(CharSequenceType.isEmpty(null)).isTrue();
  }

  @ParameterizedTest
  @NullAndEmptySource
  void static_isEmpty_returnsTrueWhenUnderlyingValueIsNullOrEmptyString(final String value) {
    final var actual1 = new ConcreteCharSequenceType(value);
    final var actual2 = new ConcreteCharSequenceType(CustomCharSequence.of(value));
    assertThat(CharSequenceType.isEmpty(actual1)).isTrue();
    assertThat(CharSequenceType.isEmpty(actual2)).isTrue();
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "     ",
      "       a ",
      " \t    a ",
      " \t \n a ",
      "       \uD800", // char is surrogate without final char
      "       \uD800\uDC82 ", // 𐂂
      "       \uD83D\uDE00 ", // 😀
  })
  void static_isEmpty_returnsFalseWhenUnderlyingValueIsNonEmptyString(final String value) {
    final var actual1 = new ConcreteCharSequenceType(value);
    final var actual2 = new ConcreteCharSequenceType(CustomCharSequence.of(value));
    assertThat(CharSequenceType.isEmpty(actual1)).isFalse();
    assertThat(CharSequenceType.isEmpty(actual2)).isFalse();
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
    assertThatComparable(actual1).isEqualByComparingTo(actual2);
  }

  @Test
  void compareTo_returnsZeroWhenOtherObjectIsNullAndThisContainsNull() {
    final var actual1 = new ConcreteCharSequenceType(null);
    assertThatComparable(actual1).isEqualByComparingTo(null);
  }

  @Test
  void compareTo_returnsZeroWhenComparingSameInstance() {
    final var actual1 = new ConcreteCharSequenceType(null);
    assertThatComparable(actual1).isEqualByComparingTo(actual1);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {
      " ",
      "a",
      "abc ",
  })
  void compareTo_returnsZeroWhenComparingDifferentTypesWithSameInternalValue(final String value) {
    final var actual1 = new ConcreteCharSequenceType(value);
    final var actual2 = new SubclassOfConcreteCharSequenceType(value);
    // In general, I think it is bad practice to make a custom type that extends another custom type.
    // If they have some commonality, then have the custom types implement a common interface and make
    // both custom class types final.
    //
    // Nevertheless, for testing purposes I am capturing the scenario here because someone, somewhere,
    // will do it and I want test cases to exist to catch possible future regression.
    assertThatComparable(actual1).isEqualByComparingTo(actual2);
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
    assertThatComparable(actual1).isLessThan(actual2);
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
    assertThatComparable(actual1).isEqualByComparingTo(actual2);
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
    assertThatComparable(actual1).isGreaterThan(actual2);
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
    assertThatComparable(actual1).isLessThan(actual2);
  }

  @ParameterizedTest
  @CsvSource(value = {
      " a   | a   ",
      " bb  | bb  ",
      " abc | abc ",
  }, delimiter = '|')
  void compareTo_returnsZeroForCustomCharSequenceValues(final String value1, final String value2) {
    final var actual1 = new ConcreteCharSequenceType(CustomCharSequence.of(value1));
    final var actual2 = new ConcreteCharSequenceType(CustomCharSequence.of(value2));
    assertThatComparable(actual1).isEqualByComparingTo(actual2);
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
    final var actual1 = new ConcreteCharSequenceType(CustomCharSequence.of(value1));
    final var actual2 = new ConcreteCharSequenceType(CustomCharSequence.of(value2));
    assertThatComparable(actual1).isGreaterThan(actual2);
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
      final var actual2 = new ConcreteCharSequenceType(CustomCharSequence.of(value2)); // custom internal type
      assertThatComparable(actual1).isGreaterThan(actual2);
    }
    {
      final var actual1 = new ConcreteCharSequenceType(CustomCharSequence.of(value1)); // custom internal type
      final var actual2 = new ConcreteCharSequenceType(value2); // string internal type
      assertThatComparable(actual1).isGreaterThan(actual2);
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
    final var actual1 = new ConcreteCharSequenceType(CustomCharSequence.of(value1));
    final var actual2 = new ConcreteCharSequenceType(CustomCharSequence.of(value2));
    assertThatComparable(actual1).isLessThan(actual2);
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
      final var actual2 = new ConcreteCharSequenceType(CustomCharSequence.of(value2)); // custom internal type
      assertThatComparable(actual1).isLessThan(actual2);
    }
    {
      final var actual1 = new ConcreteCharSequenceType(CustomCharSequence.of(value1)); // custom internal type
      final var actual2 = new ConcreteCharSequenceType(value2); // string internal type
      assertThatComparable(actual1).isLessThan(actual2);
    }
  }


  @Test
  void compareToIgnoreCase_returnsZeroWhenBothContainNullValues() {
    final var actual1 = new ConcreteCharSequenceType(null);
    final var actual2 = new ConcreteCharSequenceType(null);
    assertThat(actual1.compareToIgnoreCase(actual2)).isZero();
  }

  @Test
  void compareToIgnoreCase_returnsZeroWhenOtherObjectIsNullAndThisContainsNull() {
    final var actual1 = new ConcreteCharSequenceType(null);
    assertThat(actual1.compareToIgnoreCase(null)).isZero();
  }

  @Test
  void compareToIgnoreCase_returnsZeroWhenComparingSameInstance() {
    final var actual1 = new ConcreteCharSequenceType(null);
    assertThat(actual1.compareToIgnoreCase(actual1)).isZero();
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {
      " ",
      "a",
      "abc ",
  })
  void compareToIgnoreCase_returnsZeroWhenComparingDifferentTypesWithSameInternalValue(final String value) {
    final var actual1 = new ConcreteCharSequenceType(value);
    final var actual2 = new SubclassOfConcreteCharSequenceType(value);
    // In general, I think it is bad practice to make a custom type that extends another custom type.
    // If they have some commonality, then have the custom types implement a common interface and make
    // both custom class types final.
    //
    // Nevertheless, for testing purposes I am capturing the scenario here because someone, somewhere,
    // will do it and I want test cases to exist to catch possible future regression.
    assertThat(actual1.compareToIgnoreCase(actual2)).isZero();
  }

  @ParameterizedTest
  @EmptySource
  @ValueSource(strings = {
      " ",
      "a",
      "ab",
      "abc"})
  void compareToIgnoreCase_returnsOneWhenOtherObjectIsNullAndThisContainsValue(final String value) {
    final var actual1 = new ConcreteCharSequenceType(value);
    assertThat(actual1.compareToIgnoreCase(null)).isOne();
  }

  @ParameterizedTest
  @EmptySource
  @ValueSource(strings = {
      " ",
      "a",
      "ab",
      "abc"})
  void compareToIgnoreCase_returnsOneWhenOtherObjectContainsNullAndThisContainsValue(final String value) {
    final var actual1 = new ConcreteCharSequenceType(value);
    final var actual2 = new ConcreteCharSequenceType(null);
    assertThat(actual1.compareToIgnoreCase(actual2)).isOne();
  }

  @ParameterizedTest
  @EmptySource
  @ValueSource(strings = {
      " ",
      "a",
      "ab",
      "abc"})
  void compareToIgnoreCase_returnsNegativeOneWhenOtherObjectHasValueAndThisContainsNull(final String value) {
    final var actual1 = new ConcreteCharSequenceType(null);
    final var actual2 = new ConcreteCharSequenceType(value);
    assertThat(actual1.compareToIgnoreCase(actual2)).isNegative();
  }

  @ParameterizedTest
  @CsvSource(value = {
      " a   | a   ",
      " a   | A   ",
      " A   | a   ",
      " A   | A   ",
      " bb  | bb  ",
      " bb  | BB  ",
      " BB  | bb  ",
      " BB  | BB  ",
      " abc | abc ",
      " ß   | ß   ",
      " I   | i   ",
      " İ   | i   ", // asymmetric characters
      " i   | I   ",
      " i   | İ   ", // asymmetric characters
      "\u0130 | \u0049 "
  }, delimiter = '|')
  void compareToIgnoreCase_returnsZero(final String value1, final String value2) {
    final var actual1 = new ConcreteCharSequenceType(value1);
    final var actual2 = new ConcreteCharSequenceType(value2);
    assertThat(actual1.compareToIgnoreCase(actual2)).isZero();
  }

  @ParameterizedTest
  @CsvSource(value = {
      " b   | a   ",
      " b   | A   ",
      " B   | a   ",
      " B   | A   ",
      " bc  | ab  ",
      " bc  | AB  ",
      " BC  | ab  ",
      " BC  | AB  ",
      " acc | abc ",
      " ß   | s   ",
  }, delimiter = '|')
  void compareToIgnoreCase_returnsPositive(final String value1, final String value2) {
    final var actual1 = new ConcreteCharSequenceType(value1);
    final var actual2 = new ConcreteCharSequenceType(value2);
    assertThat(actual1.compareToIgnoreCase(actual2)).isPositive();
  }

  @ParameterizedTest
  @CsvSource(value = {
      " a   | b   ",
      " a   | B   ",
      " A   | b   ",
      " A   | B   ",
      " ab  | bc  ",
      " ab  | BC  ",
      " AB  | bc  ",
      " AB  | BC  ",
      " abc | acc ",
      " s   | ß   ",
  }, delimiter = '|')
  void compareToIgnoreCase_returnsNegative(final String value1, final String value2) {
    final var actual1 = new ConcreteCharSequenceType(value1);
    final var actual2 = new ConcreteCharSequenceType(value2);
    assertThat(actual1.compareToIgnoreCase(actual2)).isNegative();
  }

  @ParameterizedTest
  @CsvSource(value = {
      " a   | a   ",
      " a   | A   ",
      " A   | a   ",
      " A   | A   ",
      " bb  | bb  ",
      " bb  | BB  ",
      " BB  | bb  ",
      " BB  | BB  ",
      " abc | abc ",
      " ß   | ß   ",
      " I   | i   ",
      " İ   | i   ", // asymmetric characters
      " i   | I   ",
      " i   | İ   ", // asymmetric characters
  }, delimiter = '|')
  void compareToIgnoreCase_returnsZeroForCustomCharSequenceValues(final String value1, final String value2) {
    final var actual1 = new ConcreteCharSequenceType(CustomCharSequence.of(value1));
    final var actual2 = new ConcreteCharSequenceType(CustomCharSequence.of(value2));
    assertThat(actual1.compareToIgnoreCase(actual2)).isZero();
  }

  @ParameterizedTest
  @CsvSource(value = {
      " aa   | a   ",
      " aa   | A   ",
      " AA   | a   ",
      " AA   | A   ",
      " b    | a   ",
      " bc   | ab  ",
      " bc   | AB  ",
      " BC   | ab  ",
      " BC   | AB  ",
      " acc  | abc ",
      " abcd | abc ",
      " s    | ß   ",
  }, delimiter = '|')
  void compareToIgnoreCase_returnsPositiveForCustomCharSequenceValues(final String value1, final String value2) {
    final var actual1 = new ConcreteCharSequenceType(CustomCharSequence.of(value1));
    final var actual2 = new ConcreteCharSequenceType(CustomCharSequence.of(value2));
    assertThat(actual1.compareToIgnoreCase(actual2)).isPositive();
  }

  @ParameterizedTest
  @CsvSource(value = {
      " aa   | a   ",
      " aa   | A   ",
      " AA   | a   ",
      " AA   | A   ",
      " bc   | ab  ",
      " acc  | abc ",
      " abcd | abc ",
      " ß    | s   ",
  }, delimiter = '|')
  void compareToIgnoreCase_returnsPositiveForForDifferingInternalTypes(final String value1, final String value2) {
    {
      final var actual1 = new ConcreteCharSequenceType(value1); // string internal type
      final var actual2 = new ConcreteCharSequenceType(CustomCharSequence.of(value2)); // custom internal type
      assertThat(actual1.compareToIgnoreCase(actual2)).isPositive();
    }
    {
      final var actual1 = new ConcreteCharSequenceType(CustomCharSequence.of(value1)); // custom internal type
      final var actual2 = new ConcreteCharSequenceType(value2); // string internal type
      assertThat(actual1.compareToIgnoreCase(actual2)).isPositive();
    }
  }

  @ParameterizedTest
  @CsvSource(value = {
      " a   | aa   ",
      " a   | AA   ",
      " A   | aa   ",
      " A   | AA   ",
      " a   | b    ",
      " a   | B    ",
      " A   | b    ",
      " A   | B    ",
      " ab  | bc   ",
      " ab  | BC   ",
      " AB  | bc   ",
      " AB  | BC   ",
      " abc | acc  ",
      " abc | abcd ",
  }, delimiter = '|')
  void compareToIgnoreCase_returnsNegativeForCustomCharSequenceValues(final String value1, final String value2) {
    final var actual1 = new ConcreteCharSequenceType(CustomCharSequence.of(value1));
    final var actual2 = new ConcreteCharSequenceType(CustomCharSequence.of(value2));
    assertThat(actual1.compareToIgnoreCase(actual2)).isNegative();
  }

  @ParameterizedTest
  @CsvSource(value = {
      " a   | aa   ",
      " a   | AA   ",
      " A   | aa   ",
      " A   | AA   ",
      " a   | b    ",
      " a   | B    ",
      " A   | b    ",
      " A   | B    ",
      " ab  | bc   ",
      " ab  | BC   ",
      " AB  | bc   ",
      " AB  | BC   ",
      " abc | acc  ",
      " abc | abcd ",
  }, delimiter = '|')
  void compareToIgnoreCase_returnsNegativeForDifferingInternalTypes(final String value1, final String value2) {
    {
      final var actual1 = new ConcreteCharSequenceType(value1); // string internal type
      final var actual2 = new ConcreteCharSequenceType(CustomCharSequence.of(value2)); // custom internal type
      assertThat(actual1.compareToIgnoreCase(actual2)).isNegative();
    }
    {
      final var actual1 = new ConcreteCharSequenceType(CustomCharSequence.of(value1)); // custom internal type
      final var actual2 = new ConcreteCharSequenceType(value2); // string internal type
      assertThat(actual1.compareToIgnoreCase(actual2)).isNegative();
    }
  }


  @Test
  void equalsIgnoreCase_returnsTrueWhenBothContainNullValues() {
    final var actual1 = new ConcreteCharSequenceType(null);
    final var actual2 = new ConcreteCharSequenceType(null);
    assertThat(actual1.equalsIgnoreCase(actual2)).isTrue();
  }

  @Test
  void equalsIgnoreCase_returnsTrueWhenOtherObjectIsNullAndThisContainsNull() {
    final var actual1 = new ConcreteCharSequenceType(null);
    assertThat(actual1.equalsIgnoreCase(null)).isTrue();
  }

  @Test
  void equalsIgnoreCase_returnsTrueWhenComparingSameInstance() {
    final var actual1 = new ConcreteCharSequenceType(null);
    assertThat(actual1.equalsIgnoreCase(actual1)).isTrue();
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {
      " ",
      "a",
      "abc ",
  })
  void equalsIgnoreCase_returnsFalseWhenComparingDifferentTypes(final String value) {
    final var actual1 = new ConcreteCharSequenceType(value);
    final var actual2 = new SubclassOfConcreteCharSequenceType(value);
    assertThat(actual1.equalsIgnoreCase(actual2)).isFalse();
  }

  @ParameterizedTest
  @EmptySource
  @ValueSource(strings = {
      " ",
      "a",
      "ab",
      "abc"})
  void equalsIgnoreCase_returnsFalseWhenOtherObjectIsNullAndThisContainsValue(final String value) {
    final var actual1 = new ConcreteCharSequenceType(value);
    assertThat(actual1.equalsIgnoreCase(null)).isFalse();
  }

  @ParameterizedTest
  @EmptySource
  @ValueSource(strings = {
      " ",
      "a",
      "ab",
      "abc"})
  void equalsIgnoreCase_returnsFalseWhenOtherObjectContainsNullAndThisContainsValue(final String value) {
    final var actual1 = new ConcreteCharSequenceType(value);
    final var actual2 = new ConcreteCharSequenceType(null);
    assertThat(actual1.equalsIgnoreCase(actual2)).isFalse();
  }

  @ParameterizedTest
  @EmptySource
  @ValueSource(strings = {
      " ",
      "a",
      "ab",
      "abc"})
  void equalsIgnoreCase_returnsFalseOneWhenOtherObjectHasValueAndThisContainsNull(final String value) {
    final var actual1 = new ConcreteCharSequenceType(null);
    final var actual2 = new ConcreteCharSequenceType(value);
    assertThat(actual1.equalsIgnoreCase(actual2)).isFalse();
  }

  @ParameterizedTest
  @CsvSource(value = {
      " a   | a   ",
      " a   | A   ",
      " A   | a   ",
      " A   | A   ",
      " bb  | bb  ",
      " bb  | BB  ",
      " BB  | bb  ",
      " BB  | BB  ",
      " abc | abc ",
  }, delimiter = '|')
  void equalsIgnoreCase_returnsTrue(final String value1, final String value2) {
    final var actual1 = new ConcreteCharSequenceType(value1);
    final var actual2 = new ConcreteCharSequenceType(value2);
    assertThat(actual1.equalsIgnoreCase(actual2)).isTrue();
  }

  @ParameterizedTest
  @CsvSource(value = {
      " b   | a   ",
      " b   | A   ",
      " B   | a   ",
      " B   | A   ",
      " bc  | ab  ",
      " bc  | AB  ",
      " BC  | ab  ",
      " BC  | AB  ",
      " acc | abc "
  }, delimiter = '|')
  void equalsIgnoreCase_returnsFalseWhenGreaterThan(final String value1, final String value2) {
    final var actual1 = new ConcreteCharSequenceType(value1);
    final var actual2 = new ConcreteCharSequenceType(value2);
    assertThat(actual1.equalsIgnoreCase(actual2)).isFalse();
  }

  @ParameterizedTest
  @CsvSource(value = {
      " a   | b   ",
      " a   | B   ",
      " A   | b   ",
      " A   | B   ",
      " ab  | bc  ",
      " ab  | BC  ",
      " AB  | bc  ",
      " AB  | BC  ",
      " abc | acc "
  }, delimiter = '|')
  void equalsIgnoreCase_returnsFalseWhenLessThan(final String value1, final String value2) {
    final var actual1 = new ConcreteCharSequenceType(value1);
    final var actual2 = new ConcreteCharSequenceType(value2);
    assertThat(actual1.equalsIgnoreCase(actual2)).isFalse();
  }

  @ParameterizedTest
  @CsvSource(value = {
      " a   | a   ",
      " a   | A   ",
      " A   | a   ",
      " A   | A   ",
      " bb  | bb  ",
      " bb  | BB  ",
      " BB  | bb  ",
      " BB  | BB  ",
      " abc | abc ",
  }, delimiter = '|')
  void equalsIgnoreCase_returnsTrueForCustomCharSequenceValues(final String value1, final String value2) {
    final var actual1 = new ConcreteCharSequenceType(CustomCharSequence.of(value1));
    final var actual2 = new ConcreteCharSequenceType(CustomCharSequence.of(value2));
    assertThat(actual1.equalsIgnoreCase(actual2)).isTrue();
  }

  @ParameterizedTest
  @CsvSource(value = {
      " aa   | a   ",
      " aa   | A   ",
      " AA   | a   ",
      " AA   | A   ",
      " b    | a   ",
      " bc   | ab  ",
      " bc   | AB  ",
      " BC   | ab  ",
      " BC   | AB  ",
      " acc  | abc ",
      " abcd | abc ",
  }, delimiter = '|')
  void equalsIgnoreCase_returnsFalseWhenGreaterThanForCustomCharSequenceValues(final String value1, final String value2) {
    final var actual1 = new ConcreteCharSequenceType(CustomCharSequence.of(value1));
    final var actual2 = new ConcreteCharSequenceType(CustomCharSequence.of(value2));
    assertThat(actual1.equalsIgnoreCase(actual2)).isFalse();
  }

  @ParameterizedTest
  @CsvSource(value = {
      " aa   | a   ",
      " aa   | A   ",
      " AA   | a   ",
      " AA   | A   ",
      " bc   | ab  ",
      " acc  | abc ",
      " abcd | abc ",
  }, delimiter = '|')
  void equalsIgnoreCase_returnsFalseWhenLessThanForForDifferingInternalTypes(final String value1, final String value2) {
    {
      final var actual1 = new ConcreteCharSequenceType(value1); // string internal type
      final var actual2 = new ConcreteCharSequenceType(CustomCharSequence.of(value2)); // custom internal type
      assertThat(actual1.equalsIgnoreCase(actual2)).isFalse();
    }
    {
      final var actual1 = new ConcreteCharSequenceType(CustomCharSequence.of(value1)); // custom internal type
      final var actual2 = new ConcreteCharSequenceType(value2); // string internal type
      assertThat(actual1.equalsIgnoreCase(actual2)).isFalse();
    }
  }

  @ParameterizedTest
  @CsvSource(value = {
      " a   | aa   ",
      " a   | AA   ",
      " A   | aa   ",
      " A   | AA   ",
      " a   | b    ",
      " a   | B    ",
      " A   | b    ",
      " A   | B    ",
      " ab  | bc   ",
      " ab  | BC   ",
      " AB  | bc   ",
      " AB  | BC   ",
      " abc | acc  ",
      " abc | abcd ",
  }, delimiter = '|')
  void equalsIgnoreCase_returnsFalseForCustomCharSequenceValues(final String value1, final String value2) {
    final var actual1 = new ConcreteCharSequenceType(CustomCharSequence.of(value1));
    final var actual2 = new ConcreteCharSequenceType(CustomCharSequence.of(value2));
    assertThat(actual1.equalsIgnoreCase(actual2)).isFalse();
  }

  @ParameterizedTest
  @CsvSource(value = {
      " a   | aa   ",
      " a   | AA   ",
      " A   | aa   ",
      " A   | AA   ",
      " a   | b    ",
      " a   | B    ",
      " A   | b    ",
      " A   | B    ",
      " ab  | bc   ",
      " ab  | BC   ",
      " AB  | bc   ",
      " AB  | BC   ",
      " abc | acc  ",
      " abc | abcd ",
  }, delimiter = '|')
  void equalsIgnoreCase_returnsFalseForDifferingInternalTypes(final String value1, final String value2) {
    {
      final var actual1 = new ConcreteCharSequenceType(value1); // string internal type
      final var actual2 = new ConcreteCharSequenceType(CustomCharSequence.of(value2)); // custom internal type
      assertThat(actual1.equalsIgnoreCase(actual2)).isFalse();
    }
    {
      final var actual1 = new ConcreteCharSequenceType(CustomCharSequence.of(value1)); // custom internal type
      final var actual2 = new ConcreteCharSequenceType(value2); // string internal type
      assertThat(actual1.equalsIgnoreCase(actual2)).isFalse();
    }
  }


  @Test
  void static_equalsIgnoreCase_returnsTrueWhenBothContainNullValues() {
    final var actual1 = new ConcreteCharSequenceType(null);
    final var actual2 = new ConcreteCharSequenceType(null);
    assertThat(CharSequenceType.equalsIgnoreCase(actual1, actual2)).isTrue();
    assertThat(CharSequenceType.equalsIgnoreCase(actual2, actual1)).isTrue();
  }

  @Test
  void static_equalsIgnoreCase_returnsTrueWhenOtherObjectIsNullAndThisContainsNull() {
    final var actual1 = new ConcreteCharSequenceType(null);
    assertThat(CharSequenceType.equalsIgnoreCase(actual1, null)).isTrue();
    assertThat(CharSequenceType.equalsIgnoreCase(null, actual1)).isTrue();
  }

  @Test
  void static_equalsIgnoreCase_returnsTrueWhenComparingSameInstance() {
    final var actual1 = new ConcreteCharSequenceType(null);
    assertThat(CharSequenceType.equalsIgnoreCase(actual1, actual1)).isTrue();
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {
      " ",
      "a",
      "abc ",
  })
  void static_equalsIgnoreCase_returnsFalseWhenComparingDifferentTypes(final String value) {
    final var actual1 = new ConcreteCharSequenceType(value);
    final var actual2 = new SubclassOfConcreteCharSequenceType(value);
    assertThat(actual1.equalsIgnoreCase(actual2)).isFalse();
  }

  @ParameterizedTest
  @EmptySource
  @ValueSource(strings = {
      " ",
      "a",
      "ab",
      "abc"})
  void static_equalsIgnoreCase_returnsFalseWhenOneObjectIsNullAndTheOtherContainsValue(final String value) {
    final var actual1 = new ConcreteCharSequenceType(value);
    assertThat(CharSequenceType.equalsIgnoreCase(actual1, null)).isFalse();
    assertThat(CharSequenceType.equalsIgnoreCase(null, actual1)).isFalse();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      a  | a  | true
      a  | A  | true
      A  | a  | true
      A  | A  | true
      a  | aa | false
      aa | a  | false
      aa | aa | true
      a  | b  | false
      a  | B  | false
      A  | b  | false
      A  | B  | false
      """, delimiter = '|')
  void static_equalsIgnoreCase_returnsAsExpected(
      final String value1, final String value2, final boolean expected) {
    final var actual1 = new ConcreteCharSequenceType(value1);
    final var actual2 = new ConcreteCharSequenceType(value2);
    assertThat(CharSequenceType.equalsIgnoreCase(actual1, actual2)).isEqualTo(expected);
  }


  static class ConcreteCharSequenceType implements CharSequenceType<ConcreteCharSequenceType> {

    @Serial
    private static final long serialVersionUID = 741198489576736117L;

    final CharSequence value;

    public ConcreteCharSequenceType(final CharSequence value) {
      this.value = value;
    }

    @Override
    public CharSequence value() {
      return value;
    }
  }

  /**
   * In general, I think it is bad practice to make a custom type that extends another custom type. If they have some commonality, then have the
   * custom types implement a common interface and make both custom class types final.
   * <p>
   * Nevertheless, for testing purposes I am doing it here because someone, somewhere, will do it and I want test cases to exist to catch possible
   * future regression.
   */
  static class SubclassOfConcreteCharSequenceType extends ConcreteCharSequenceType {

    @Serial
    private static final long serialVersionUID = 590171306926713641L;

    public SubclassOfConcreteCharSequenceType(final CharSequence value) {
      super(value);
    }
  }

  static class CustomCharSequence implements CharSequence {

    final char[] chars;

    CustomCharSequence(final char[] chars) {
      this.chars = chars;
    }

    CustomCharSequence(final String value) {
      this(value.toCharArray());
    }

    static CustomCharSequence of(final String value) {
      return value == null ? null : new CustomCharSequence(value);
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
      final char[] temp = new char[end - start];
      System.arraycopy(chars, start, temp, 0, end - start);
      return new CustomCharSequence(temp);
    }
  }
}
