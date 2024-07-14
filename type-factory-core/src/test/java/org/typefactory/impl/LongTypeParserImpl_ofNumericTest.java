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
import org.typefactory.LongTypeParser;

class LongTypeParserImpl_ofNumericTest {

  @Test
  void of_nullShouldReturnNull() {
    final var longTypeParser = LongTypeParser.builder().build();

    assertThatObject(longTypeParser.of((Short) null)).isNull();
    assertThatObject(longTypeParser.of((Integer) null)).isNull();
    assertThatObject(longTypeParser.of((Long) null)).isNull();
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
      Long    | -9223372036854775808 | -9223372036854775808
      Long    |  9223372036854775807 |  9223372036854775807
      Long    |                    0 |                    0
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void of_valueShouldSucceed(
      final String type, final long value, final long expected) {

    final var longTypeParser = LongTypeParser.builder().build();

    switch (type) {
      case "Short":
        assertThat(longTypeParser.of((short) value)).isEqualTo(expected);
        assertThat(longTypeParser.of(Short.valueOf((short) value))).isEqualTo(expected);
        // fall-thru
      case "Integer":
        assertThat(longTypeParser.of((int) value)).isEqualTo(expected);
        assertThat(longTypeParser.of(Integer.valueOf((int) value))).isEqualTo(expected);
        // fall-thru
      case "Long":
      default:
        assertThat(longTypeParser.of(value)).isEqualTo(expected);
        assertThat(longTypeParser.of(Long.valueOf(value))).isEqualTo(expected);
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

    final var longTypeParser = LongTypeParser.builder()
        .minValueInclusive(Short.MIN_VALUE + 1)
        .maxValueInclusive(Short.MAX_VALUE - 1)
        .build();

    switch (type) {
      case "Short":
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> longTypeParser.of((short) value))
            .withMessageStartingWith(expectedMessage);
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> longTypeParser.of(Short.valueOf((short) value)))
            .withMessageStartingWith(expectedMessage);
        // fall-thru
      case "Integer":
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> longTypeParser.of((int) value))
            .withMessageStartingWith(expectedMessage);
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> longTypeParser.of(Integer.valueOf((int) value)))
            .withMessageStartingWith(expectedMessage);
        // fall-thru
      case "Long":
      default:
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> longTypeParser.of(value))
            .withMessageStartingWith(expectedMessage);
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> longTypeParser.of(Long.valueOf(value)))
            .withMessageStartingWith(expectedMessage);
        break;
    }
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      TYPE       |        INCLUSIVE_MIN |       INCLUSIVE_MAX |                VALUE | EXPECTED_MESSAGE
      BigInteger | -9223372036854775808 | 9223372036854775807 | -9223372036854775809 | Invalid value - must be greater than or equal to -9,223,372,036,854,775,808.
      BigInteger | -9223372036854775808 | 9223372036854775807 |  9223372036854775808 | Invalid value - must be less than or equal to 9,223,372,036,854,775,807.
      Long       | -9223372036854775807 | 9223372036854775807 | -9223372036854775808 | Invalid value - must be greater than or equal to -9,223,372,036,854,775,807.
      Long       | -9223372036854775808 | 9223372036854775806 |  9223372036854775807 | Invalid value - must be less than or equal to 9,223,372,036,854,775,806.
      Long       |          -2147483648 |          2147483647 |          -2147483649 | Invalid value - must be greater than or equal to -2,147,483,648.
      Long       |          -2147483648 |          2147483647 |           2147483648 | Invalid value - must be less than or equal to 2,147,483,647.
      Integer    |          -2147483647 |          2147483647 |          -2147483648 | Invalid value - must be greater than or equal to -2,147,483,647.
      Integer    |          -2147483648 |          2147483646 |           2147483647 | Invalid value - must be less than or equal to 2,147,483,646.
      Integer    |               -32768 |               32767 |               -32769 | Invalid value - must be greater than or equal to -32,768.
      Integer    |               -32768 |               32767 |                32768 | Invalid value - must be less than or equal to 32,767.
      Short      |               -32767 |               32767 |               -32768 | Invalid value - must be greater than or equal to -32,767.
      Short      |               -32768 |               32766 |                32767 | Invalid value - must be less than or equal to 32,766.
      Short      |                    0 |                   0 |                   -1 | Invalid value - must be greater than or equal to 0.
      Short      |                    0 |                   0 |                    1 | Invalid value - must be less than or equal to 0.
      Short      |                   -1 |                   1 |                   -2 | Invalid value - must be greater than or equal to -1.
      Short      |                   -1 |                   1 |                    2 | Invalid value - must be less than or equal to 1.
      Short      |                  -20 |                 -10 |                  -21 | Invalid value - must be greater than or equal to -20.
      Short      |                  -20 |                 -10 |                   -9 | Invalid value - must be less than or equal to -10.
      Short      |                   10 |                  20 |                    9 | Invalid value - must be greater than or equal to 10.
      Short      |                   10 |                  20 |                   21 | Invalid value - must be less than or equal to 20.
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void of_outsideInclusiveMinMaxThrowsException(
      final String type,
      final long min, final long max,
      final BigInteger value, final String expectedMessage) {

    final var longTypeParser = LongTypeParser.builder()
        .minValueInclusive(min)
        .maxValueInclusive(max)
        .build();

    switch (type) {
      case "Short":
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> longTypeParser.of(value.shortValue()))
            .withMessageStartingWith(expectedMessage);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> longTypeParser.of(Short.valueOf(value.shortValue())))
            .withMessageStartingWith(expectedMessage);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> longTypeParser.of(value.shortValue()))
            .withMessageStartingWith(expectedMessage);
        // fall-thru
      case "Integer":
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> longTypeParser.of(value.intValue()))
            .withMessageStartingWith(expectedMessage);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> longTypeParser.of(Integer.valueOf(value.intValue())))
            .withMessageStartingWith(expectedMessage);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> longTypeParser.of(value.intValue()))
            .withMessageStartingWith(expectedMessage);
        // fall-thru
      case "Long":
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> longTypeParser.of(value.longValue()))
            .withMessageStartingWith(expectedMessage);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> longTypeParser.of(Long.valueOf(value.longValue())))
            .withMessageStartingWith(expectedMessage);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> longTypeParser.of(value.longValue()))
            .withMessageStartingWith(expectedMessage);
        // fall-thru
      case "BigInteger":
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> longTypeParser.of(value))
            .withMessageStartingWith(expectedMessage);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> longTypeParser.of(value))
            .withMessageStartingWith(expectedMessage);
        break;
    }
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      TYPE       |        INCLUSIVE_MIN |       INCLUSIVE_MAX |                VALUE | EXPECTED_MESSAGE
      BigInteger | -9223372036854775808 | 9223372036854775807 | -9223372036854775808 | Invalid value - must be greater than -9,223,372,036,854,775,808.
      BigInteger | -9223372036854775808 | 9223372036854775807 |  9223372036854775807 | Invalid value - must be less than 9,223,372,036,854,775,807.
      Long       | -9223372036854775807 | 9223372036854775807 | -9223372036854775807 | Invalid value - must be greater than -9,223,372,036,854,775,807.
      Long       | -9223372036854775808 | 9223372036854775806 |  9223372036854775806 | Invalid value - must be less than 9,223,372,036,854,775,806.
      Long       |          -2147483648 |          2147483647 |          -2147483648 | Invalid value - must be greater than -2,147,483,648.
      Long       |          -2147483648 |          2147483647 |           2147483647 | Invalid value - must be less than 2,147,483,647.
      Integer    |          -2147483647 |          2147483647 |          -2147483647 | Invalid value - must be greater than -2,147,483,647.
      Integer    |          -2147483648 |          2147483646 |           2147483646 | Invalid value - must be less than 2,147,483,646.
      Integer    |               -32768 |               32767 |               -32768 | Invalid value - must be greater than -32,768.
      Integer    |               -32768 |               32767 |                32767 | Invalid value - must be less than 32,767.
      Short      |               -32767 |               32767 |               -32767 | Invalid value - must be greater than -32,767.
      Short      |               -32768 |               32766 |                32766 | Invalid value - must be less than 32,766.
      Short      |                    0 |                   0 |                   -1 | Invalid value - must be greater than 0.
      Short      |                    0 |                   0 |                    1 | Invalid value - must be less than 0.
      Short      |                   -1 |                   1 |                   -2 | Invalid value - must be greater than -1.
      Short      |                   -1 |                   1 |                    2 | Invalid value - must be less than 1.
      Short      |                  -20 |                 -10 |                  -21 | Invalid value - must be greater than -20.
      Short      |                  -20 |                 -10 |                   -9 | Invalid value - must be less than -10.
      Short      |                   10 |                  20 |                    9 | Invalid value - must be greater than 10.
      Short      |                   10 |                  20 |                   21 | Invalid value - must be less than 20.
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void of_outsideExclusiveMinMaxThrowsException(
      final String type,
      final long min, final long max,
      final BigInteger value, final String expectedMessage) {

    final var longTypeParser = LongTypeParser.builder()
        .minValueExclusive(min)
        .maxValueExclusive(max)
        .build();

    switch (type) {
      case "Short":
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> longTypeParser.of(value.shortValue()))
            .withMessageStartingWith(expectedMessage);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> longTypeParser.of(Short.valueOf(value.shortValue())))
            .withMessageStartingWith(expectedMessage);
        // fall-thru
      case "Integer":
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> longTypeParser.of(value.intValue()))
            .withMessageStartingWith(expectedMessage);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> longTypeParser.of(Integer.valueOf(value.intValue())))
            .withMessageStartingWith(expectedMessage);
        // fall-thru
      case "Long":
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> longTypeParser.of(value.longValue()))
            .withMessageStartingWith(expectedMessage);

        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> longTypeParser.of(Long.valueOf(value.longValue())))
            .withMessageStartingWith(expectedMessage);
        // fall-thru
      case "BigInteger":
        assertThatExceptionOfType(InvalidValueException.class)
            .isThrownBy(() -> longTypeParser.of(value))
            .withMessageStartingWith(expectedMessage);
        break;
    }
  }

}
