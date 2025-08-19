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

import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.typefactory.assertions.Assertions.assertThat;

import java.io.Serial;
import java.math.BigInteger;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.typefactory.InvalidValueException;
import org.typefactory.LongType;
import org.typefactory.LongTypeParser;

class LongTypeParserImpl_ofTest {

  static final LongTypeParser INCLUSIVE_LONG_TYPE_PARSER = LongTypeParser.builder()
      .minValueInclusive(Long.MIN_VALUE)
      .maxValueInclusive(Long.MAX_VALUE)
      .build();

  static final LongTypeParser EXCLUSIVE_LONG_TYPE_PARSER = LongTypeParser.builder()
      .minValueExclusive(Long.MIN_VALUE)
      .maxValueExclusive(Long.MAX_VALUE)
      .build();

  @Test
  void of_nullShouldReturnNull_whenParsingToLong() {
    final var longTypeParser = LongTypeParser.builder().build();

    assertThat(longTypeParser.of((Short) null)).isNull();
    assertThat(longTypeParser.of((Integer) null)).isNull();
    assertThat(longTypeParser.of((Long) null)).isNull();
    assertThat(longTypeParser.of((BigInteger) null)).isNull();
  }

  @Test
  void of_nullShouldReturnNull_whenParsingToLongType() {
    final var longTypeParser = LongTypeParser.builder().build();

    assertThat(longTypeParser.of((Short) null, SomeLongType::new)).isNull();
    assertThat(longTypeParser.of((Integer) null, SomeLongType::new)).isNull();
    assertThat(longTypeParser.of((Long) null, SomeLongType::new)).isNull();
    assertThat(longTypeParser.of((BigInteger) null, SomeLongType::new)).isNull();
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideShortValuesToTestInclusiveLongParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingToLong(
      final Short value, final Long expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      Assertions.assertThat(INCLUSIVE_LONG_TYPE_PARSER.of(value)).isEqualTo(expectedValue);
      Assertions.assertThat(INCLUSIVE_LONG_TYPE_PARSER.of(value.shortValue())).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_LONG_TYPE_PARSER.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_LONG_TYPE_PARSER.of(value.shortValue())))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideShortValuesToTestExclusiveLongParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingToLong(
      final Short value, final Long expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      Assertions.assertThat(EXCLUSIVE_LONG_TYPE_PARSER.of(value)).isEqualTo(expectedValue);
      Assertions.assertThat(EXCLUSIVE_LONG_TYPE_PARSER.of(value.shortValue())).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_LONG_TYPE_PARSER.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_LONG_TYPE_PARSER.of(value.shortValue())))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideShortValuesToTestInclusiveLongParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingToLongType(
      final Short value, final Long expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(INCLUSIVE_LONG_TYPE_PARSER.of(value, SomeLongType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_LONG_TYPE_PARSER.of(value, SomeLongType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideShortValuesToTestExclusiveLongParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingToLongType(
      final Short value, final Long expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(EXCLUSIVE_LONG_TYPE_PARSER.of(value, SomeLongType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_LONG_TYPE_PARSER.of(value, SomeLongType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideIntegerValuesToTestInclusiveLongParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingToLong(
      final Integer value, final Long expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      Assertions.assertThat(INCLUSIVE_LONG_TYPE_PARSER.of(value)).isEqualTo(expectedValue);
      Assertions.assertThat(INCLUSIVE_LONG_TYPE_PARSER.of(value.intValue())).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_LONG_TYPE_PARSER.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_LONG_TYPE_PARSER.of(value.intValue())))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideIntegerValuesToTestExclusiveLongParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingToLong(
      final Integer value, final Long expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      Assertions.assertThat(EXCLUSIVE_LONG_TYPE_PARSER.of(value)).isEqualTo(expectedValue);
      Assertions.assertThat(EXCLUSIVE_LONG_TYPE_PARSER.of(value.intValue())).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_LONG_TYPE_PARSER.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_LONG_TYPE_PARSER.of(value.intValue())))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideIntegerValuesToTestInclusiveLongParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingToLongType(
      final Integer value, final Long expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(INCLUSIVE_LONG_TYPE_PARSER.of(value, SomeLongType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_LONG_TYPE_PARSER.of(value, SomeLongType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideIntegerValuesToTestExclusiveLongParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingToLongType(
      final Integer value, final Long expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(EXCLUSIVE_LONG_TYPE_PARSER.of(value, SomeLongType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_LONG_TYPE_PARSER.of(value, SomeLongType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideLongValuesToTestInclusiveLongParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingToLong(
      final Long value, final Long expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      Assertions.assertThat(INCLUSIVE_LONG_TYPE_PARSER.of(value)).isEqualTo(expectedValue);
      Assertions.assertThat(INCLUSIVE_LONG_TYPE_PARSER.of(value.longValue())).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_LONG_TYPE_PARSER.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_LONG_TYPE_PARSER.of(value.longValue())))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideLongValuesToTestExclusiveLongParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingToLong(
      final Long value, final Long expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      Assertions.assertThat(EXCLUSIVE_LONG_TYPE_PARSER.of(value)).isEqualTo(expectedValue);
      Assertions.assertThat(EXCLUSIVE_LONG_TYPE_PARSER.of(value.longValue())).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_LONG_TYPE_PARSER.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_LONG_TYPE_PARSER.of(value.longValue())))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideLongValuesToTestInclusiveLongParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingToLongType(
      final Long value, final Long expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(INCLUSIVE_LONG_TYPE_PARSER.of(value, SomeLongType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_LONG_TYPE_PARSER.of(value, SomeLongType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideLongValuesToTestExclusiveLongParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingToLongType(
      final Long value, final Long expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(EXCLUSIVE_LONG_TYPE_PARSER.of(value, SomeLongType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_LONG_TYPE_PARSER.of(value, SomeLongType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideBigIntegerValuesToTestInclusiveLongParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingToLong(
      final BigInteger value, final Long expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      Assertions.assertThat(INCLUSIVE_LONG_TYPE_PARSER.of(value)).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_LONG_TYPE_PARSER.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideBigIntegerValuesToTestExclusiveLongParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingToLong(
      final BigInteger value, final Long expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      Assertions.assertThat(EXCLUSIVE_LONG_TYPE_PARSER.of(value)).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_LONG_TYPE_PARSER.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideBigIntegerValuesToTestInclusiveLongParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingToLongType(
      final BigInteger value, final Long expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(INCLUSIVE_LONG_TYPE_PARSER.of(value, SomeLongType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_LONG_TYPE_PARSER.of(value, SomeLongType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideBigIntegerValuesToTestExclusiveLongParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingToLongType(
      final BigInteger value, final Long expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(EXCLUSIVE_LONG_TYPE_PARSER.of(value, SomeLongType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_LONG_TYPE_PARSER.of(value, SomeLongType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  private static class SomeLongType extends LongType {

    @Serial
    private static final long serialVersionUID = 2908753028297789833L;

    protected SomeLongType(final Long value) {
      super(value);
    }
  }

}
