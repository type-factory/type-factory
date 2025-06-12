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

import static org.typefactory.assertions.Assertions.assertThat;
import static org.typefactory.assertions.Assertions.catchThrowableOfType;

import java.io.Serial;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.typefactory.IntegerType;
import org.typefactory.IntegerTypeParser;
import org.typefactory.InvalidValueException;
import org.typefactory.assertions.Assertions;

class IntegerTypeParserImpl_ofTest {

  static final IntegerTypeParser INCLUSIVE_INTEGER_TYPE_PARSER = IntegerTypeParser.builder()
      .minValueInclusive(Integer.MIN_VALUE)
      .maxValueInclusive(Integer.MAX_VALUE)
      .build();

  static final IntegerTypeParser EXCLUSIVE_INTEGER_TYPE_PARSER = IntegerTypeParser.builder()
      .minValueExclusive(Integer.MIN_VALUE)
      .maxValueExclusive(Integer.MAX_VALUE)
      .build();

  @Test
  void of_nullShouldReturnNull_whenParsingToInteger() {
    final var integerTypeParser = IntegerTypeParser.builder().build();

    assertThat(integerTypeParser.of((Short) null)).isNull();
    assertThat(integerTypeParser.of((Integer) null)).isNull();
    assertThat(integerTypeParser.of((Long) null)).isNull();
    assertThat(integerTypeParser.of((BigInteger) null)).isNull();
  }

  @Test
  void of_nullShouldReturnNull_whenParsingToIntegerType() {
    final var integerTypeParser = IntegerTypeParser.builder().build();

    assertThat(integerTypeParser.of((Short) null, SomeIntegerType::new)).isNull();
    assertThat(integerTypeParser.of((Integer) null, SomeIntegerType::new)).isNull();
    assertThat(integerTypeParser.of((Long) null, SomeIntegerType::new)).isNull();
    assertThat(integerTypeParser.of((BigInteger) null, SomeIntegerType::new)).isNull();
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideShortValuesToTestInclusiveIntegerParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingToInteger(
      final Short value, final Integer expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      org.typefactory.assertions.Assertions.assertThat(INCLUSIVE_INTEGER_TYPE_PARSER.of(value)).isEqualTo(expectedValue);
      Assertions.assertThat(INCLUSIVE_INTEGER_TYPE_PARSER.of(value.shortValue())).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_INTEGER_TYPE_PARSER.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_INTEGER_TYPE_PARSER.of(value.shortValue())))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideShortValuesToTestExclusiveIntegerParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingToInteger(
      final Short value, final Integer expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      Assertions.assertThat(EXCLUSIVE_INTEGER_TYPE_PARSER.of(value)).isEqualTo(expectedValue);
      Assertions.assertThat(EXCLUSIVE_INTEGER_TYPE_PARSER.of(value.shortValue())).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_INTEGER_TYPE_PARSER.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_INTEGER_TYPE_PARSER.of(value.shortValue())))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideShortValuesToTestInclusiveIntegerParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingToIntegerType(
      final Short value, final Integer expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(INCLUSIVE_INTEGER_TYPE_PARSER.of(value, SomeIntegerType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_INTEGER_TYPE_PARSER.of(value, SomeIntegerType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideShortValuesToTestExclusiveIntegerParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingToIntegerType(
      final Short value, final Integer expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(EXCLUSIVE_INTEGER_TYPE_PARSER.of(value, SomeIntegerType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_INTEGER_TYPE_PARSER.of(value, SomeIntegerType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideIntegerValuesToTestInclusiveIntegerParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingToInteger(
      final Integer value, final Integer expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      Assertions.assertThat(INCLUSIVE_INTEGER_TYPE_PARSER.of(value)).isEqualTo(expectedValue);
      Assertions.assertThat(INCLUSIVE_INTEGER_TYPE_PARSER.of(value.intValue())).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_INTEGER_TYPE_PARSER.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_INTEGER_TYPE_PARSER.of(value.intValue())))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideIntegerValuesToTestExclusiveIntegerParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingToInteger(
      final Integer value, final Integer expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      Assertions.assertThat(EXCLUSIVE_INTEGER_TYPE_PARSER.of(value)).isEqualTo(expectedValue);
      Assertions.assertThat(EXCLUSIVE_INTEGER_TYPE_PARSER.of(value.intValue())).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_INTEGER_TYPE_PARSER.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_INTEGER_TYPE_PARSER.of(value.intValue())))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideIntegerValuesToTestInclusiveIntegerParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingToIntegerType(
      final Integer value, final Integer expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(INCLUSIVE_INTEGER_TYPE_PARSER.of(value, SomeIntegerType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_INTEGER_TYPE_PARSER.of(value, SomeIntegerType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideIntegerValuesToTestExclusiveIntegerParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingToIntegerType(
      final Integer value, final Integer expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(EXCLUSIVE_INTEGER_TYPE_PARSER.of(value, SomeIntegerType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_INTEGER_TYPE_PARSER.of(value, SomeIntegerType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideLongValuesToTestInclusiveIntegerParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingToInteger(
      final Long value, final Integer expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      Assertions.assertThat(INCLUSIVE_INTEGER_TYPE_PARSER.of(value)).isEqualTo(expectedValue);
      Assertions.assertThat(INCLUSIVE_INTEGER_TYPE_PARSER.of(value.longValue())).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_INTEGER_TYPE_PARSER.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_INTEGER_TYPE_PARSER.of(value.longValue())))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideLongValuesToTestExclusiveIntegerParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingToInteger(
      final Long value, final Integer expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      Assertions.assertThat(EXCLUSIVE_INTEGER_TYPE_PARSER.of(value)).isEqualTo(expectedValue);
      Assertions.assertThat(EXCLUSIVE_INTEGER_TYPE_PARSER.of(value.longValue())).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_INTEGER_TYPE_PARSER.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_INTEGER_TYPE_PARSER.of(value.longValue())))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideLongValuesToTestInclusiveIntegerParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingToIntegerType(
      final Long value, final Integer expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(INCLUSIVE_INTEGER_TYPE_PARSER.of(value, SomeIntegerType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_INTEGER_TYPE_PARSER.of(value, SomeIntegerType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideLongValuesToTestExclusiveIntegerParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingToIntegerType(
      final Long value, final Integer expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(EXCLUSIVE_INTEGER_TYPE_PARSER.of(value, SomeIntegerType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_INTEGER_TYPE_PARSER.of(value, SomeIntegerType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }



  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideBigIntegerValuesToTestInclusiveIntegerParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingToInteger(
      final BigInteger value, final Integer expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      Assertions.assertThat(INCLUSIVE_INTEGER_TYPE_PARSER.of(value)).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_INTEGER_TYPE_PARSER.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideBigIntegerValuesToTestExclusiveIntegerParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingToInteger(
      final BigInteger value, final Integer expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      Assertions.assertThat(EXCLUSIVE_INTEGER_TYPE_PARSER.of(value)).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_INTEGER_TYPE_PARSER.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideBigIntegerValuesToTestInclusiveIntegerParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingToIntegerType(
      final BigInteger value, final Integer expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(INCLUSIVE_INTEGER_TYPE_PARSER.of(value, SomeIntegerType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_INTEGER_TYPE_PARSER.of(value, SomeIntegerType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideBigIntegerValuesToTestExclusiveIntegerParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingToIntegerType(
      final BigInteger value, final Integer expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(EXCLUSIVE_INTEGER_TYPE_PARSER.of(value, SomeIntegerType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_INTEGER_TYPE_PARSER.of(value, SomeIntegerType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  private static class SomeIntegerType extends IntegerType {

    @Serial
    private static final long serialVersionUID = -3796418749066791916L;

    protected SomeIntegerType(final Integer value) {
      super(value);
    }
  }

}
