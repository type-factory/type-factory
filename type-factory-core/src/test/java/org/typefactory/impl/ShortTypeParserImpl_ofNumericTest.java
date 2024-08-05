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
import org.typefactory.InvalidValueException;
import org.typefactory.ShortTypeParser;

class ShortTypeParserImpl_ofNumericTest {

  @Test
  void of_nullShouldReturnNull() {
    final var shortTypeParser = ShortTypeParser.builder().build();

    assertThatObject(shortTypeParser.of((Short) null)).isNull();
    assertThatObject(shortTypeParser.of((Integer) null)).isNull();
    assertThatObject(shortTypeParser.of((Long) null)).isNull();
    assertThatObject(shortTypeParser.of((BigInteger) null)).isNull();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      TYPE       |                VALUE |             EXPECTED
      Short      |               -32768 |               -32768
      Short      |                32767 |                32767
      Short      |                    0 |                    0
      Integer    |               -32768 |               -32768
      Integer    |                32767 |                32767
      Integer    |                    0 |                    0
      Long       |               -32768 |               -32768
      Long       |                32767 |                32767
      Long       |                    0 |                    0
      BigInteger |               -32768 |               -32768
      BigInteger |                32767 |                32767
      BigInteger |                    0 |                    0
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void of_valueShouldSucceedWithInclusiveMinMax(
      final String type, final short value, final short expected) {

    final var shortTypeParser = ShortTypeParser.builder()
        .minValueInclusive(Short.MIN_VALUE)
        .maxValueInclusive(Short.MAX_VALUE)
        .build();

    switch (type) {
      case "Short":
        assertThat(shortTypeParser.of(value)).isEqualTo(expected);
        assertThat(shortTypeParser.of(Short.valueOf(value))).isEqualTo(expected);
        // fall-thru
      case "Integer":
        assertThat(shortTypeParser.of((int)value)).isEqualTo(expected);
        assertThat(shortTypeParser.of(Integer.valueOf(value))).isEqualTo(expected);
        // fall-thru
      case "Long":
        assertThat(shortTypeParser.of((long)value)).isEqualTo(expected);
        assertThat(shortTypeParser.of(Long.valueOf(value))).isEqualTo(expected);
        // fall-thru
      case "BigInteger":
      default:
        assertThat(shortTypeParser.of(BigInteger.valueOf(value))).isEqualTo(expected);
        break;
    }
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      TYPE       |                VALUE |             EXPECTED
      Short      |               -32767 |               -32767
      Short      |                32766 |                32766
      Short      |                    0 |                    0
      Integer    |               -32767 |               -32767
      Integer    |                32766 |                32766
      Integer    |                    0 |                    0
      Long       |               -32767 |               -32767
      Long       |                32766 |                32766
      Long       |                    0 |                    0
      BigInteger |               -32767 |               -32767
      BigInteger |                32766 |                32766
      BigInteger |                    0 |                    0
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void of_valueShouldSucceedWithExclusiveMinMax(
      final String type, final short value, final short expected) {

    final var shortTypeParser = ShortTypeParser.builder()
        .minValueExclusive(Short.MIN_VALUE)
        .maxValueExclusive(Short.MAX_VALUE)
        .build();

    switch (type) {
      case "Short":
        assertThat(shortTypeParser.of(value)).isEqualTo(expected);
        assertThat(shortTypeParser.of(Short.valueOf(value))).isEqualTo(expected);
        // fall-thru
      case "Integer":
        assertThat(shortTypeParser.of((int)value)).isEqualTo(expected);
        assertThat(shortTypeParser.of(Integer.valueOf(value))).isEqualTo(expected);
        // fall-thru
      case "Long":
        assertThat(shortTypeParser.of((long)value)).isEqualTo(expected);
        assertThat(shortTypeParser.of(Long.valueOf(value))).isEqualTo(expected);
        // fall-thru
      case "BigInteger":
      default:
        assertThat(shortTypeParser.of(BigInteger.valueOf(value))).isEqualTo(expected);
        break;
    }
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
             INCLUSIVE_MIN |       INCLUSIVE_MAX | VALUE_TYPE |                VALUE | EXPECTED_MESSAGE
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

    final var shortTypeParser = ShortTypeParser.builder()
        .minValueInclusive(min)
        .maxValueInclusive(max)
        .build();

    switch (valueType) {
      case "Short":
        final var shortPrimitive = value.shortValue();
        final var shortObject = Integer.valueOf(shortPrimitive);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> shortTypeParser.of(shortPrimitive))
            .withMessageStartingWith(expectedMessage);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> shortTypeParser.of(shortObject))
            .withMessageStartingWith(expectedMessage);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> shortTypeParser.of(shortPrimitive))
            .withMessageStartingWith(expectedMessage);
        // fall-thru
      case "Integer":
        final var integerPrimitive = value.intValue();
        final var integerObject = Integer.valueOf(integerPrimitive);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> shortTypeParser.of(integerPrimitive))
            .withMessageStartingWith(expectedMessage);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> shortTypeParser.of(integerObject))
            .withMessageStartingWith(expectedMessage);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> shortTypeParser.of(integerPrimitive))
            .withMessageStartingWith(expectedMessage);
        // fall-thru
      case "Long":
        final var longPrimitive = value.longValue();
        final var longObject = Long.valueOf(longPrimitive);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> shortTypeParser.of(longPrimitive))
            .withMessageStartingWith(expectedMessage);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> shortTypeParser.of(longObject))
            .withMessageStartingWith(expectedMessage);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> shortTypeParser.of(longPrimitive))
            .withMessageStartingWith(expectedMessage);
        // fall-thru
      case "BigInteger":
      default:
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> shortTypeParser.of(value))
            .withMessageStartingWith(expectedMessage);
        break;
    }
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
             EXCLUSIVE_MIN |       EXCLUSIVE_MAX | VALUE_TYPE |                VALUE | EXPECTED_MESSAGE
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

    final var shortTypeParser = ShortTypeParser.builder()
        .minValueExclusive(min)
        .maxValueExclusive(max)
        .build();

    switch (valueType) {
      case "Short":
        final var shortPrimitive = value.shortValue();
        final var shortObject = Integer.valueOf(shortPrimitive);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> shortTypeParser.of(shortPrimitive))
            .withMessageStartingWith(expectedMessage);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> shortTypeParser.of(shortObject))
            .withMessageStartingWith(expectedMessage);
        // fall-thru
      case "Integer":
        final var integerPrimitive = value.intValue();
        final var integerObject = Integer.valueOf(integerPrimitive);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> shortTypeParser.of(integerPrimitive))
            .withMessageStartingWith(expectedMessage);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> shortTypeParser.of(integerObject))
            .withMessageStartingWith(expectedMessage);
        // fall-thru
      case "Long":
        final var longPrimitive = value.longValue();
        final var longObject = Long.valueOf(longPrimitive);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> shortTypeParser.of(longPrimitive))
            .withMessageStartingWith(expectedMessage);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> shortTypeParser.of(longObject))
            .withMessageStartingWith(expectedMessage);
        // fall-thru
      case "BigInteger":
      default:
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> shortTypeParser.of(value))
            .withMessageStartingWith(expectedMessage);
        break;
    }
  }

}
