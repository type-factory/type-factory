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
package org.typefactory.recordtypes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatObject;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.typefactory.InvalidValueException;

class CountryCodeTest {

  @Test
  void emptyCountryCodeIsEmpty() {
    assertThatObject(CountryCode.EMPTY_COUNTRY_CODE).isNotNull();
    assertThat(CountryCode.EMPTY_COUNTRY_CODE.isEmpty()).isTrue();
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = "  ")
  void of_shouldReturnEmpty(final String value) {
    final CountryCode actual = new CountryCode(value);
    assertThat((CharSequence) actual).isEmpty();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      null     | null | ''
      ''       | ''   | ''
      '  '     | ''   | ''
      AU       | AU   | AU
      ' AU '   | AU   | AU
      '  AU  ' | AU   | AU
      uS       | US   | US
      Nz       | NZ   | NZ
      de       | DE   | DE
      """, delimiter = '|', nullValues = "null")
  void of_shouldCreateCountryCodeInstancesAsExpected(
      final String value, final String expectedValue, final String expectedString) {

    final CountryCode actual = new CountryCode(value);

    assertThat(actual.value()).isEqualTo(expectedValue);
    assertThatObject(actual).hasToString(expectedString);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      A   | Invalid value - too short, minimum length is 2.
      AUS | Invalid value - too long, maximum length is 2.
      USA | Invalid value - too long, maximum length is 2.
      61  | Invalid value - invalid character 6 U+0036 DIGIT SIX.
      """, delimiter = '|')
  void of_shouldThrowExceptionForInvalidValues(final String value, final String expectedExceptionMessage) {
    assertThatThrownBy(() -> new CountryCode(value))
        .isInstanceOf(InvalidValueException.class)
        .hasMessage("must be a 2-character ISO 3166-1 alpha country code. " + expectedExceptionMessage)
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedExceptionMessage);
  }
}
