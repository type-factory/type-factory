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
import static org.typefactory.assertions.Assertions.assertThatExceptionOfType;
import static org.typefactory.assertions.Assertions.assertThatObject;
import static org.typefactory.assertions.Assertions.catchThrowableOfType;

import java.io.Serial;
import java.math.RoundingMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.typefactory.IntegerType;
import org.typefactory.IntegerTypeParser;
import org.typefactory.InvalidValueException;

class IntegerTypeParserImpl_parseTest {

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {" ", "  ", "   "})
  void parse_shouldReturnNull(final String value) {
    final var typeParser = IntegerTypeParser.builder()
        .ignoreAllWhitespace()
        .build();

    final var actual = typeParser.parse(value);
    assertThatObject(actual).isNull();
  }

  @ParameterizedTest
  @ValueSource(strings = {" ", "  ", "   "})
  void parse_whitespaceShouldThrowException(final String value) {
    final var typeParser = IntegerTypeParser.builder()
        .forbidWhitespace()
        .build();

    assertThatExceptionOfType(InvalidValueException.class)
        .isThrownBy(() -> typeParser.parse(value))
        .withMessageStartingWith("Invalid value - invalid white-space character U+0020 SPACE.");
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideIntegralStringValuesToTestInclusiveIntegerParsers")
  void parse_integralStringValues_usingIntegerParserWithInclusiveLimits(
      final String value, final Integer expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    final IntegerTypeParser typeParser = IntegerTypeParser.builder()
        .minValueInclusive(Integer.MIN_VALUE)
        .maxValueInclusive(Integer.MAX_VALUE)
        .forbidWhitespace()
        .build();

    if (expectedExceptionMessage == null) {
      assertThat(typeParser.parse(value)).isEqualTo(expectedValue);
      assertThat(typeParser.parse(value, SomeIntegerType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> typeParser.parse(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> typeParser.parse(value, SomeIntegerType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideIntegralStringValuesToTestExclusiveIntegerParsers")
  void parse_integralStringValues_usingIntegerParserWithExclusiveLimits(
      final String value, final Integer expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    final IntegerTypeParser typeParser = IntegerTypeParser.builder()
        .minValueExclusive(Integer.MIN_VALUE)
        .maxValueExclusive(Integer.MAX_VALUE)
        .forbidWhitespace()
        .build();

    if (expectedExceptionMessage == null) {
      assertThat(typeParser.parse(value)).isEqualTo(expectedValue);
      assertThat(typeParser.parse(value, SomeIntegerType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> typeParser.parse(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> typeParser.parse(value, SomeIntegerType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideDecimalStringValuesToTestInclusiveIntegerParsers")
  void parse_decimalStringValues_usingIntegerParserWithInclusiveLimits(
      final String value, final RoundingMode roundingMode,
      final Integer expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    final IntegerTypeParser typeParser = IntegerTypeParser.builder()
        .minValueInclusive(Integer.MIN_VALUE)
        .maxValueInclusive(Integer.MAX_VALUE)
        .roundingMode(roundingMode)
        .forbidWhitespace()
        .build();

    if (expectedExceptionMessage == null) {
      assertThat(typeParser.parse(value)).isEqualTo(expectedValue);
      assertThat(typeParser.parse(value, SomeIntegerType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> typeParser.parse(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> typeParser.parse(value, SomeIntegerType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.testutils.NumericValueScenarios#provideDecimalStringValuesToTestExclusiveIntegerParsers")
  void parse_decimalStringValues_usingIntegerParserWithExclusiveLimits(
      final String value, final RoundingMode roundingMode,
      final Integer expectedValue, final String expectedExceptionMessage, final String expectedInvalidValue) {

    final IntegerTypeParser typeParser = IntegerTypeParser.builder()
        .minValueExclusive(Integer.MIN_VALUE)
        .maxValueExclusive(Integer.MAX_VALUE)
        .roundingMode(roundingMode)
        .forbidWhitespace()
        .build();

    if (expectedExceptionMessage == null) {
      assertThat(typeParser.parse(value)).isEqualTo(expectedValue);
      assertThat(typeParser.parse(value, SomeIntegerType::new)).hasValue(expectedValue);
    } else {
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> typeParser.parse(value)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
      assertThat(catchThrowableOfType(InvalidValueException.class, () -> typeParser.parse(value, SomeIntegerType::new)))
          .hasMessage(expectedExceptionMessage)
          .hasInvalidValue(expectedInvalidValue);
    }
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      INCLUSIVE_MIN | INCLUSIVE_MAX |       VALUE | EXPECTED_MESSAGE
        -2147483648 |    2147483647 | -2147483649 | Invalid value - must be greater than or equal to -2,147,483,648.
        -2147483648 |    2147483647 |  2147483648 | Invalid value - must be less than or equal to 2,147,483,647.
                  0 |             0 |          -1 | Invalid value - must be greater than or equal to 0.
                  0 |             0 |           1 | Invalid value - must be less than or equal to 0.
                 -1 |             1 |          -2 | Invalid value - must be greater than or equal to -1.
                 -1 |             1 |           2 | Invalid value - must be less than or equal to 1.
                -20 |           -10 |         -21 | Invalid value - must be greater than or equal to -20.
                -20 |           -10 |          -9 | Invalid value - must be less than or equal to -10.
                 10 |            20 |           9 | Invalid value - must be greater than or equal to 10.
                 10 |            20 |          21 | Invalid value - must be less than or equal to 20.
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parse_outsideInclusiveMinMaxThrowsException(
      final int min, final int max,
      final String value, final String expectedMessage) {

    final IntegerTypeParser typeParser = IntegerTypeParser.builder()
        .minValueInclusive(min)
        .maxValueInclusive(max)
        .allowBase10Numbers()
        .ignoreAllWhitespace()
        .build();

    assertThat(catchThrowableOfType(InvalidValueException.class, () -> typeParser.parse(value, SomeIntegerType::new)))
        .hasMessage(expectedMessage)
        .hasInvalidValue(String.valueOf(value));
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      EXCLUSIVE_MIN | EXCLUSIVE_MAX |       VALUE | EXPECTED_MESSAGE
        -2147483648 |    2147483647 | -2147483649 | Invalid value - must be greater than -2,147,483,648.
        -2147483648 |    2147483647 | -2147483648 | Invalid value - must be greater than -2,147,483,648.
        -2147483648 |    2147483647 |  2147483648 | Invalid value - must be less than 2,147,483,647.
        -2147483648 |    2147483647 |  2147483649 | Invalid value - must be less than 2,147,483,647.
                  0 |             0 |          -1 | Invalid value - must be greater than 0.
                  0 |             0 |           0 | Invalid value - must be greater than 0.
                  0 |             0 |           1 | Invalid value - must be less than 0.
                 -1 |             1 |          -1 | Invalid value - must be greater than -1.
                 -1 |             1 |           1 | Invalid value - must be less than 1.
                -20 |           -10 |         -20 | Invalid value - must be greater than -20.
                -20 |           -10 |         -21 | Invalid value - must be greater than -20.
                -20 |           -10 |         -10 | Invalid value - must be less than -10.
                -20 |           -10 |          -9 | Invalid value - must be less than -10.
                 10 |            20 |          10 | Invalid value - must be greater than 10.
                 10 |            20 |           9 | Invalid value - must be greater than 10.
                 10 |            20 |          20 | Invalid value - must be less than 20.
                 10 |            20 |          21 | Invalid value - must be less than 20.
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parse_outsideExclusiveMinMaxThrowsException(
      final int min, final int max,
      final String value, final String expectedMessage) {

    final IntegerTypeParser typeParser = IntegerTypeParser.builder()
        .minValueExclusive(min)
        .maxValueExclusive(max)
        .allowBase10Numbers()
        .ignoreAllWhitespace()
        .build();

    assertThat(catchThrowableOfType(InvalidValueException.class, () -> typeParser.parse(value, SomeIntegerType::new)))
        .hasMessage(expectedMessage)
        .hasInvalidValue(String.valueOf(value));
  }

  private static class SomeIntegerType extends IntegerType {

    @Serial
    private static final long serialVersionUID = 1443000566308904061L;

    protected SomeIntegerType(final Integer value) {
      super(value);
    }
  }

}
