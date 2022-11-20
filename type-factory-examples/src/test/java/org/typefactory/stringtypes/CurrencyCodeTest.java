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
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.typefactory.InvalidValueException;

class CurrencyCodeTest {

  @ParameterizedTest
  @NullSource
  void of_shouldReturnNull(final String value) {
    final CurrencyCode actual = CurrencyCode.of(value);
    assertThatObject(actual).isNull();
  }

  @ParameterizedTest
  @EmptySource
  @ValueSource(strings = "  ")
  void of_shouldReturnEmpty(final String value) {
    final CurrencyCode actual = CurrencyCode.of(value);
    assertThat((CharSequence) actual).isEmpty();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      AUD       | AUD
      ' AUD '   | AUD
      '  AUD  ' | AUD
      uSd       | USD
      NzD       | NZD
      eur       | EUR
      e u r     | EUR
      """, delimiter = '|')
  void of_shouldCreateCurrencyCodeInstancesAsExpected(final String value, final String expected) {
    final CurrencyCode actual = CurrencyCode.of(value);
    assertThatObject(actual).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      AU   | Invalid value - too short, minimum length is 3.
      AUSD | Invalid value - too long, maximum length is 3.
      USAD | Invalid value - too long, maximum length is 3.
      61D  | Invalid value - invalid character '6'.
      """, delimiter = '|')
  void of_shouldThrowExceptionForInvalidValues(final String value, final String expectedExceptionMessage) {
    assertThatThrownBy(() -> CurrencyCode.of(value))
        .isInstanceOf(InvalidValueException.class)
        .hasMessage("must be a 3-character ISO 4217 alpha currency code. " + expectedExceptionMessage)
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedExceptionMessage);
  }
}
