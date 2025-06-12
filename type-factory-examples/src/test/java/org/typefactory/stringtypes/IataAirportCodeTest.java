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
package org.typefactory.stringtypes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatObject;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.typefactory.InvalidValueException;

class IataAirportCodeTest {

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = "  ")
  void of_shouldReturnEmpty(final String value) {
    final IataAirportCode actual = IataAirportCode.of(value);
    assertThat((CharSequence) actual).isEmpty();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      SYD       | SYD
      ' sYd '   | SYD
      '  sYd  ' | SYD
      Mel       | MEL
      bkk       | BKK
      jfk       | JFK
      CdG       | CDG
      """, delimiter = '|')
  void of_shouldCreateIataAirportCodeInstancesAsExpected(final String value, final String expected) {
    final IataAirportCode actual = IataAirportCode.of(value);
    assertThatObject(actual).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      SY   | Invalid value - too short, minimum length is 3.
      SYDA | Invalid value - too long, maximum length is 3.
      611  | Invalid value - invalid character 6 U+0036 DIGIT SIX.
      """, delimiter = '|')
  void of_shouldThrowExceptionForInvalidValues(final String value, final String expectedExceptionMessage) {
    assertThatThrownBy(() -> IataAirportCode.of(value))
        .isInstanceOf(InvalidValueException.class)
        .hasMessage("must be a 3-character IATA airport value. " + expectedExceptionMessage)
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedExceptionMessage);
  }
}
