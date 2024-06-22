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
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatObject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.IntegerTypeParser;
import org.typefactory.InvalidValueException;

class IntegerTypeParserImpl_ofNumericTest {

  @Test
  void of_nullShouldReturnNull() {
    final var integerTypeParser = IntegerTypeParser.builder().build();

    assertThatObject(integerTypeParser.of((Short) null)).isNull();
    assertThatObject(integerTypeParser.of((Integer) null)).isNull();
    assertThatObject(integerTypeParser.of((Long) null)).isNull();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      TYPE    |                VALUE |             EXPECTED
      Short   |               -32768 |               -32768
      Short   |                32767 |                32767
      Short   |                    0 |                    0
      Integer |          -2147483648 |          -2147483648
      Integer |           2147483647 |           2147483647
      Integer |                    0 |                    0
      Long    |          -2147483648 |          -2147483648
      Long    |           2147483647 |           2147483647
      Long    |                    0 |                    0
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void of_valueShouldSucceed(
      final String type, final int value, final int expected) {

    final Short shortPrimitiveValue = (short) value;
    final Short shortObjectValue = (short) value;
    final Integer integerPrimitiveValue = value;
    final Integer integerObjectValue = value;
    final Long longPrimitiveValue = (long) value;
    final Long longObjectValue = (long) value;

    final var integerTypeParser = IntegerTypeParser.builder().build();

    switch (type) {
      case "Short":
        assertThat(integerTypeParser.of(shortPrimitiveValue)).isEqualTo(expected);
        assertThat(integerTypeParser.of(shortObjectValue)).isEqualTo(expected);
        // fall-thru
      case "Integer":
        assertThat(integerTypeParser.of(integerPrimitiveValue)).isEqualTo(expected);
        assertThat(integerTypeParser.of(integerObjectValue)).isEqualTo(expected);
        // fall-thru
      case "Long":
      default:
        assertThat(integerTypeParser.of(longPrimitiveValue)).isEqualTo(expected);
        assertThat(integerTypeParser.of(longObjectValue)).isEqualTo(expected);
        break;
    }
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      TYPE    |                VALUE |  EXPECTED_MESSAGE
      Short   |               -32768 |  Invalid value - must be greater than or equal to -32,767.
      Short   |                32767 |  Invalid value - must be less than or equal to 32,766.
      Integer |          -2147483648 |  Invalid value - must be greater than or equal to -32,767.
      Integer |           2147483647 |  Invalid value - must be less than or equal to 32,766.
      Long    | -9223372036854775808 |  Invalid value - must be greater than or equal to -32,767.
      Long    |  9223372036854775807 |  Invalid value - must be less than or equal to 32,766.
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void of_valueShouldThrowException(
      final String type, final long value, final String expectedMessage) {

    final var integerTypeParser = IntegerTypeParser.builder()
        .minValueInclusive(Short.MIN_VALUE + 1)
        .maxValueInclusive(Short.MAX_VALUE - 1)
        .build();

    switch (type) {
      case "Short":
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> integerTypeParser.of((short) value))
            .withMessageStartingWith(expectedMessage);
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> integerTypeParser.of(Short.valueOf((short) value)))
            .withMessageStartingWith(expectedMessage);
        // fall-thru
      case "Integer":
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> integerTypeParser.of((int) value))
            .withMessageStartingWith(expectedMessage);
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> integerTypeParser.of(Integer.valueOf((int) value)))
            .withMessageStartingWith(expectedMessage);
        // fall-thru
      case "Long":
      default:
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> integerTypeParser.of(value))
            .withMessageStartingWith(expectedMessage);
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> integerTypeParser.of(Long.valueOf(value)))
            .withMessageStartingWith(expectedMessage);
        break;
    }
  }
}
