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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.typefactory.InvalidValueException;
import org.typefactory.ShortType;
import org.typefactory.ShortTypeParser;

class ShortTypeParserImpl_ofTest {

  static final ShortTypeParser INCLUSIVE_SHORT_TYPE_PARSER = ShortTypeParser.builder()
      .minValueInclusive(Short.MIN_VALUE)
      .maxValueInclusive(Short.MAX_VALUE)
      .build();

  static final ShortTypeParser EXCLUSIVE_SHORT_TYPE_PARSER = ShortTypeParser.builder()
      .minValueExclusive(Short.MIN_VALUE)
      .maxValueExclusive(Short.MAX_VALUE)
      .build();

  @Test
  void of_nullShouldReturnNull_whenParsingToShort() {
    final var shortTypeParser = ShortTypeParser.builder().build();

    assertThat(shortTypeParser.of((Short) null)).isNull();
    assertThat(shortTypeParser.of((Integer) null)).isNull();
    assertThat(shortTypeParser.of((Long) null)).isNull();
    assertThat(shortTypeParser.of((BigInteger) null)).isNull();
  }

  @Test
  void of_nullShouldReturnNull_whenParsingToShortType() {
    final var shortTypeParser = ShortTypeParser.builder().build();

    assertThat(shortTypeParser.of((Short) null, SomeShortType::new)).isNull();
    assertThat(shortTypeParser.of((Integer) null, SomeShortType::new)).isNull();
    assertThat(shortTypeParser.of((Long) null, SomeShortType::new)).isNull();
    assertThat(shortTypeParser.of((BigInteger) null, SomeShortType::new)).isNull();
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideShortValuesToTestInclusiveShortParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingShortToShort(
      final Short value, final Short expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(INCLUSIVE_SHORT_TYPE_PARSER.of(value)).isEqualTo(expectedValue);
      assertThat(INCLUSIVE_SHORT_TYPE_PARSER.of(value.shortValue())).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_SHORT_TYPE_PARSER.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_SHORT_TYPE_PARSER.of(value.shortValue())))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideShortValuesToTestExclusiveShortParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingShortToShort(
      final Short value, final Short expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(EXCLUSIVE_SHORT_TYPE_PARSER.of(value)).isEqualTo(expectedValue);
      assertThat(EXCLUSIVE_SHORT_TYPE_PARSER.of(value.shortValue())).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_SHORT_TYPE_PARSER.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_SHORT_TYPE_PARSER.of(value.shortValue())))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideShortValuesToTestInclusiveShortParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingShortToShortType(
      final Short value, final Short expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(INCLUSIVE_SHORT_TYPE_PARSER.of(value, SomeShortType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_SHORT_TYPE_PARSER.of(value, SomeShortType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideShortValuesToTestExclusiveShortParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingShortToShortType(
      final Short value, final Short expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(EXCLUSIVE_SHORT_TYPE_PARSER.of(value, SomeShortType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_SHORT_TYPE_PARSER.of(value, SomeShortType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideIntegerValuesToTestInclusiveShortParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingIntegerToShort(
      final Integer value, final Short expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(INCLUSIVE_SHORT_TYPE_PARSER.of(value)).isEqualTo(expectedValue);
      assertThat(INCLUSIVE_SHORT_TYPE_PARSER.of(value.intValue())).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_SHORT_TYPE_PARSER.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_SHORT_TYPE_PARSER.of(value.intValue())))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideIntegerValuesToTestExclusiveShortParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingIntegerToShort(
      final Integer value, final Short expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(EXCLUSIVE_SHORT_TYPE_PARSER.of(value)).isEqualTo(expectedValue);
      assertThat(EXCLUSIVE_SHORT_TYPE_PARSER.of(value.intValue())).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_SHORT_TYPE_PARSER.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_SHORT_TYPE_PARSER.of(value.intValue())))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideIntegerValuesToTestInclusiveShortParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingIntegerToShortType(
      final Integer value, final Short expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(INCLUSIVE_SHORT_TYPE_PARSER.of(value, SomeShortType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_SHORT_TYPE_PARSER.of(value, SomeShortType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideIntegerValuesToTestExclusiveShortParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingIntegerToShortType(
      final Integer value, final Short expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(EXCLUSIVE_SHORT_TYPE_PARSER.of(value, SomeShortType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_SHORT_TYPE_PARSER.of(value, SomeShortType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideLongValuesToTestInclusiveShortParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingLongToShort(
      final Long value, final Short expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(INCLUSIVE_SHORT_TYPE_PARSER.of(value)).isEqualTo(expectedValue);
      assertThat(INCLUSIVE_SHORT_TYPE_PARSER.of(value.longValue())).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_SHORT_TYPE_PARSER.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_SHORT_TYPE_PARSER.of(value.longValue())))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideLongValuesToTestExclusiveShortParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingLongToShort(
      final Long value, final Short expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(EXCLUSIVE_SHORT_TYPE_PARSER.of(value)).isEqualTo(expectedValue);
      assertThat(EXCLUSIVE_SHORT_TYPE_PARSER.of(value.longValue())).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_SHORT_TYPE_PARSER.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_SHORT_TYPE_PARSER.of(value.longValue())))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideLongValuesToTestInclusiveShortParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingLongToShortType(
      final Long value, final Short expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(INCLUSIVE_SHORT_TYPE_PARSER.of(value, SomeShortType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_SHORT_TYPE_PARSER.of(value, SomeShortType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideLongValuesToTestExclusiveShortParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingLongToShortType(
      final Long value, final Short expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(EXCLUSIVE_SHORT_TYPE_PARSER.of(value, SomeShortType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_SHORT_TYPE_PARSER.of(value, SomeShortType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }


  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideBigIntegerValuesToTestInclusiveShortParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingBigIntegerToShort(
      final BigInteger value, final Short expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(INCLUSIVE_SHORT_TYPE_PARSER.of(value)).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_SHORT_TYPE_PARSER.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideBigIntegerValuesToTestExclusiveShortParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingBigIntegerToShort(
      final BigInteger value, final Short expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(EXCLUSIVE_SHORT_TYPE_PARSER.of(value)).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_SHORT_TYPE_PARSER.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideBigIntegerValuesToTestInclusiveShortParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingBigIntegerToShortType(
      final BigInteger value, final Short expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(INCLUSIVE_SHORT_TYPE_PARSER.of(value, SomeShortType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> INCLUSIVE_SHORT_TYPE_PARSER.of(value, SomeShortType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideBigIntegerValuesToTestExclusiveShortParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingBigIntegerToShortType(
      final BigInteger value, final Short expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    if (expectedExceptionMessage == null) {
      assertThat(EXCLUSIVE_SHORT_TYPE_PARSER.of(value, SomeShortType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> EXCLUSIVE_SHORT_TYPE_PARSER.of(value, SomeShortType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }





  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideFloatValuesToTestInclusiveShortParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingFloatToShort(
      final Float value, final RoundingMode roundingMode,
      final Short expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    final var typeParser = ShortTypeParser.builder()
        .minValueInclusive(Short.MIN_VALUE)
        .maxValueInclusive(Short.MAX_VALUE)
        .roundingMode(roundingMode)
        .build();

    if (expectedExceptionMessage == null) {
      assertThat(typeParser.of(value)).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> typeParser.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideFloatValuesToTestExclusiveShortParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingFloatToShort(
      final Float value, final RoundingMode roundingMode,
      final Short expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    final var typeParser = ShortTypeParser.builder()
        .minValueExclusive(Short.MIN_VALUE)
        .maxValueExclusive(Short.MAX_VALUE)
        .roundingMode(roundingMode)
        .build();

    if (expectedExceptionMessage == null) {
      assertThat(typeParser.of(value)).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> typeParser.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideFloatValuesToTestInclusiveShortParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingFloatToShortType(
      final Float value, final RoundingMode roundingMode,
      final Short expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    final var typeParser = ShortTypeParser.builder()
        .minValueInclusive(Short.MIN_VALUE)
        .maxValueInclusive(Short.MAX_VALUE)
        .roundingMode(roundingMode)
        .build();

    if (expectedExceptionMessage == null) {
      assertThat(typeParser.of(value, SomeShortType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> typeParser.of(value, SomeShortType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideFloatValuesToTestExclusiveShortParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingFloatToShortType(
      final Float value, final RoundingMode roundingMode,
      final Short expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    final var typeParser = ShortTypeParser.builder()
        .minValueExclusive(Short.MIN_VALUE)
        .maxValueExclusive(Short.MAX_VALUE)
        .roundingMode(roundingMode)
        .build();

    if (expectedExceptionMessage == null) {
      assertThat(typeParser.of(value, SomeShortType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> typeParser.of(value, SomeShortType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }





  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideDoubleValuesToTestInclusiveShortParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingDoubleToShort(
      final Double value, final RoundingMode roundingMode,
      final Short expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    final var typeParser = ShortTypeParser.builder()
        .minValueInclusive(Short.MIN_VALUE)
        .maxValueInclusive(Short.MAX_VALUE)
        .roundingMode(roundingMode)
        .build();

    if (expectedExceptionMessage == null) {
      assertThat(typeParser.of(value)).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> typeParser.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideDoubleValuesToTestExclusiveShortParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingDoubleToShort(
      final Double value, final RoundingMode roundingMode,
      final Short expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    final var typeParser = ShortTypeParser.builder()
        .minValueExclusive(Short.MIN_VALUE)
        .maxValueExclusive(Short.MAX_VALUE)
        .roundingMode(roundingMode)
        .build();

    if (expectedExceptionMessage == null) {
      assertThat(typeParser.of(value)).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> typeParser.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideDoubleValuesToTestInclusiveShortParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingDoubleToShortType(
      final Double value, final RoundingMode roundingMode,
      final Short expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    final var typeParser = ShortTypeParser.builder()
        .minValueInclusive(Short.MIN_VALUE)
        .maxValueInclusive(Short.MAX_VALUE)
        .roundingMode(roundingMode)
        .build();

    if (expectedExceptionMessage == null) {
      assertThat(typeParser.of(value, SomeShortType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> typeParser.of(value, SomeShortType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideDoubleValuesToTestExclusiveShortParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingDoubleToShortType(
      final Double value, final RoundingMode roundingMode,
      final Short expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    final var typeParser = ShortTypeParser.builder()
        .minValueExclusive(Short.MIN_VALUE)
        .maxValueExclusive(Short.MAX_VALUE)
        .roundingMode(roundingMode)
        .build();

    if (expectedExceptionMessage == null) {
      assertThat(typeParser.of(value, SomeShortType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> typeParser.of(value, SomeShortType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }






  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideBigDecimalValuesToTestInclusiveShortParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingBigDecimalToShort(
      final BigDecimal value, final RoundingMode roundingMode,
      final Short expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    final var typeParser = ShortTypeParser.builder()
        .minValueInclusive(Short.MIN_VALUE)
        .maxValueInclusive(Short.MAX_VALUE)
        .roundingMode(roundingMode)
        .build();

    if (expectedExceptionMessage == null) {
      assertThat(typeParser.of(value)).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> typeParser.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideBigDecimalValuesToTestExclusiveShortParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingBigDecimalToShort(
      final BigDecimal value, final RoundingMode roundingMode,
      final Short expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    final var typeParser = ShortTypeParser.builder()
        .minValueExclusive(Short.MIN_VALUE)
        .maxValueExclusive(Short.MAX_VALUE)
        .roundingMode(roundingMode)
        .build();

    if (expectedExceptionMessage == null) {
      assertThat(typeParser.of(value)).isEqualTo(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> typeParser.of(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideBigDecimalValuesToTestInclusiveShortParsers")
  void of_valueShouldSucceedWithInclusiveMinMax_whenParsingBigDecimalToShortType(
      final BigDecimal value, final RoundingMode roundingMode,
      final Short expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    final var typeParser = ShortTypeParser.builder()
        .minValueInclusive(Short.MIN_VALUE)
        .maxValueInclusive(Short.MAX_VALUE)
        .roundingMode(roundingMode)
        .build();

    if (expectedExceptionMessage == null) {
      assertThat(typeParser.of(value, SomeShortType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> typeParser.of(value, SomeShortType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideBigDecimalValuesToTestExclusiveShortParsers")
  void of_valueShouldSucceedWithExclusiveMinMax_whenParsingBigDecimalToShortType(
      final BigDecimal value, final RoundingMode roundingMode,
      final Short expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    final var typeParser = ShortTypeParser.builder()
        .minValueExclusive(Short.MIN_VALUE)
        .maxValueExclusive(Short.MAX_VALUE)
        .roundingMode(roundingMode)
        .build();

    if (expectedExceptionMessage == null) {
      assertThat(typeParser.of(value, SomeShortType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> typeParser.of(value, SomeShortType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }


  private static class SomeShortType extends ShortType {

    @Serial
    private static final long serialVersionUID = 1443000566308904061L;

    protected SomeShortType(final Short value) {
      super(value);
    }
  }

}
