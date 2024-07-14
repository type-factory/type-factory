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

import java.math.BigInteger;
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
    assertThatObject(integerTypeParser.of((BigInteger) null)).isNull();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      TYPE       |                VALUE |             EXPECTED
      Short      |               -32768 |               -32768
      Short      |                32767 |                32767
      Short      |                    0 |                    0
      Integer    |          -2147483648 |          -2147483648
      Integer    |           2147483647 |           2147483647
      Integer    |                    0 |                    0
      Long       |          -2147483648 |          -2147483648
      Long       |           2147483647 |           2147483647
      Long       |                    0 |                    0
      BigInteger |          -2147483648 |          -2147483648
      BigInteger |           2147483647 |           2147483647
      BigInteger |                    0 |                    0
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void of_valueShouldSucceedWithInclusiveMinMax(
      final String type, final int value, final int expected) {

    final var integerTypeParser = IntegerTypeParser.builder()
        .minValueInclusive(Integer.MIN_VALUE)
        .maxValueInclusive(Integer.MAX_VALUE)
        .build();

    switch (type) {
      case "Short":
        assertThat(integerTypeParser.of((short) value)).isEqualTo(expected);
        assertThat(integerTypeParser.of(Short.valueOf((short) value))).isEqualTo(expected);
        // fall-thru
      case "Integer":
        assertThat(integerTypeParser.of(value)).isEqualTo(expected);
        assertThat(integerTypeParser.of(Integer.valueOf(value))).isEqualTo(expected);
        // fall-thru
      case "Long":
        assertThat(integerTypeParser.of((long) value)).isEqualTo(expected);
        assertThat(integerTypeParser.of(Long.valueOf(value))).isEqualTo(expected);
        // fall-thru
      case "BigInteger":
        assertThat(integerTypeParser.of(BigInteger.valueOf(value))).isEqualTo(expected);
        break;
    }
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      TYPE       |                VALUE |             EXPECTED
      Short      |               -32767 |               -32767
      Short      |                32766 |                32766
      Short      |                    0 |                    0
      Integer    |          -2147483647 |          -2147483647
      Integer    |           2147483646 |           2147483646
      Integer    |                    0 |                    0
      Long       |          -2147483647 |          -2147483647
      Long       |           2147483646 |           2147483646
      Long       |                    0 |                    0
      BigInteger |          -2147483647 |          -2147483647
      BigInteger |           2147483646 |           2147483646
      BigInteger |                    0 |                    0
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void of_valueShouldSucceedWithExclusiveMinMax(
      final String type, final int value, final int expected) {

    final var integerTypeParser = IntegerTypeParser.builder()
        .minValueExclusive(Integer.MIN_VALUE)
        .maxValueExclusive(Integer.MAX_VALUE)
        .build();

    switch (type) {
      case "Short":
        assertThat(integerTypeParser.of((short) value)).isEqualTo(expected);
        assertThat(integerTypeParser.of(Short.valueOf((short) value))).isEqualTo(expected);
        // fall-thru
      case "Integer":
        assertThat(integerTypeParser.of(value)).isEqualTo(expected);
        assertThat(integerTypeParser.of(Integer.valueOf(value))).isEqualTo(expected);
        // fall-thru
      case "Long":
        assertThat(integerTypeParser.of((long) value)).isEqualTo(expected);
        assertThat(integerTypeParser.of(Long.valueOf(value))).isEqualTo(expected);
        // fall-thru
      case "BigInteger":
        assertThat(integerTypeParser.of(BigInteger.valueOf(value))).isEqualTo(expected);
        break;
    }
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
             INCLUSIVE_MIN |       INCLUSIVE_MAX | VALUE_TYPE |                VALUE | EXPECTED_MESSAGE
               -2147483648 |          2147483647 | Long       |          -2147483649 | Invalid value - must be greater than or equal to -2,147,483,648.
               -2147483648 |          2147483647 | Long       |           2147483648 | Invalid value - must be less than or equal to 2,147,483,647.
               -2147483647 |          2147483647 | Integer    |          -2147483648 | Invalid value - must be greater than or equal to -2,147,483,647.
               -2147483648 |          2147483646 | Integer    |           2147483647 | Invalid value - must be less than or equal to 2,147,483,646.
                    -32768 |               32767 | Integer    |               -32769 | Invalid value - must be greater than or equal to -32,768.
                    -32768 |               32767 | Integer    |                32768 | Invalid value - must be less than or equal to 32,767.
                    -32767 |               32767 | Short      |               -32768 | Invalid value - must be greater than or equal to -32,767.
                    -32768 |               32766 | Short      |                32767 | Invalid value - must be less than or equal to 32,766.
                         0 |                   0 | Short      |                   -1 | Invalid value - must be greater than or equal to 0.
                         0 |                   0 | Short      |                    1 | Invalid value - must be less than or equal to 0.
                        -1 |                   1 | Short      |                   -2 | Invalid value - must be greater than or equal to -1.
                        -1 |                   1 | Short      |                    2 | Invalid value - must be less than or equal to 1.
                       -20 |                 -10 | Short      |                  -21 | Invalid value - must be greater than or equal to -20.
                       -20 |                 -10 | Short      |                   -9 | Invalid value - must be less than or equal to -10.
                        10 |                  20 | Short      |                    9 | Invalid value - must be greater than or equal to 10.
                        10 |                  20 | Short      |                   21 | Invalid value - must be less than or equal to 20.
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void of_outsideInclusiveMinMaxThrowsException(
      final long min, final long max,
      final String valueType, final BigInteger value,
      final String expectedMessage) {

    final var integerTypeParser = IntegerTypeParser.builder()
        .minValueInclusive(min)
        .maxValueInclusive(max)
        .build();

    switch (valueType) {
      case "Short":
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> integerTypeParser.of(value.shortValue()))
            .withMessageStartingWith(expectedMessage);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> integerTypeParser.of(Short.valueOf(value.shortValue())))
            .withMessageStartingWith(expectedMessage);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> integerTypeParser.of(value.shortValue()))
            .withMessageStartingWith(expectedMessage);
        // fall-thru
      case "Integer":
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> integerTypeParser.of(value.intValue()))
            .withMessageStartingWith(expectedMessage);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> integerTypeParser.of(Integer.valueOf(value.intValue())))
            .withMessageStartingWith(expectedMessage);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> integerTypeParser.of(value.intValue()))
            .withMessageStartingWith(expectedMessage);
        // fall-thru
      case "Long":
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> integerTypeParser.of(value.longValue()))
            .withMessageStartingWith(expectedMessage);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> integerTypeParser.of(Long.valueOf(value.longValue())))
            .withMessageStartingWith(expectedMessage);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> integerTypeParser.of(value.longValue()))
            .withMessageStartingWith(expectedMessage);
        // fall-thru
      case "BigInteger":
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> integerTypeParser.of(value))
            .withMessageStartingWith(expectedMessage);
        break;
    }
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
             EXCLUSIVE_MIN |       EXCLUSIVE_MAX | VALUE_TYPE |                VALUE | EXPECTED_MESSAGE
               -2147483648 |          2147483647 | Long       |          -2147483648 | Invalid value - must be greater than -2,147,483,648.
               -2147483648 |          2147483647 | Long       |           2147483647 | Invalid value - must be less than 2,147,483,647.
               -2147483647 |          2147483647 | Integer    |          -2147483647 | Invalid value - must be greater than -2,147,483,647.
               -2147483648 |          2147483646 | Integer    |           2147483646 | Invalid value - must be less than 2,147,483,646.
                    -32768 |               32767 | Integer    |               -32768 | Invalid value - must be greater than -32,768.
                    -32768 |               32767 | Integer    |                32767 | Invalid value - must be less than 32,767.
                    -32767 |               32767 | Short      |               -32767 | Invalid value - must be greater than -32,767.
                    -32768 |               32766 | Short      |                32766 | Invalid value - must be less than 32,766.
                         0 |                   0 | Short      |                   -1 | Invalid value - must be greater than 0.
                         0 |                   0 | Short      |                    1 | Invalid value - must be less than 0.
                        -1 |                   1 | Short      |                   -2 | Invalid value - must be greater than -1.
                        -1 |                   1 | Short      |                    2 | Invalid value - must be less than 1.
                       -20 |                 -10 | Short      |                  -21 | Invalid value - must be greater than -20.
                       -20 |                 -10 | Short      |                   -9 | Invalid value - must be less than -10.
                        10 |                  20 | Short      |                    9 | Invalid value - must be greater than 10.
                        10 |                  20 | Short      |                   21 | Invalid value - must be less than 20.
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void of_outsideExclusiveMinMaxThrowsException(
      final long min, final long max,
      final String valueType, final BigInteger value,
      final String expectedMessage) {

    final var integerTypeParser = IntegerTypeParser.builder()
        .minValueExclusive(min)
        .maxValueExclusive(max)
        .build();

    switch (valueType) {
      case "Short":
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> integerTypeParser.of(value.shortValue()))
            .withMessageStartingWith(expectedMessage);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> integerTypeParser.of(Short.valueOf(value.shortValue())))
            .withMessageStartingWith(expectedMessage);
        // fall-thru
      case "Integer":
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> integerTypeParser.of(value.intValue()))
            .withMessageStartingWith(expectedMessage);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> integerTypeParser.of(Integer.valueOf(value.intValue())))
            .withMessageStartingWith(expectedMessage);
        // fall-thru
      case "Long":
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> integerTypeParser.of(value.longValue()))
            .withMessageStartingWith(expectedMessage);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> integerTypeParser.of(Long.valueOf(value.longValue())))
            .withMessageStartingWith(expectedMessage);
        // fall-thru
      case "BigInteger":
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> integerTypeParser.of(value))
            .withMessageStartingWith(expectedMessage);
        break;
    }
  }

}
