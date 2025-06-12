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
  @EmptySource
  @ValueSource(strings = {" ", "   ", "\t\t", "\n\n", "\r\r", "\t\n\r"})
  void of_shouldReturnEmpty(final String value) {
    final CurrencyCode actual = CurrencyCode.of(value);
    assertThat((CharSequence) actual).isNull();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE     | EXPECTED-value() | EXPECTED-toString()
      AUD       | AUD              | AUD
      ' aud '   | AUD              | AUD
      '\tAUD\t' | AUD              | AUD
      uSd       | USD              | USD
      Nzd       | NZD              | NZD
      eur       | EUR              | EUR
      """,
      delimiter = '|', useHeadersInDisplayName = true)
  void of_shouldCreateCurrencyCodeInstancesAsExpected(
      final String value, final String expectedValue, final String expectedToString) {

    final CurrencyCode actual = CurrencyCode.of(value);

    assertThat(actual.value()).isEqualTo(expectedValue);
    assertThatObject(actual).hasToString(expectedToString);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      AU   | Invalid value - too short, minimum length is 3.
      AUSD | Invalid value - too long, maximum length is 3.
      USAD | Invalid value - too long, maximum length is 3.
      61D  | Invalid value - invalid character '6' U+0036 DIGIT SIX.
      """, delimiter = '|')
  void of_shouldThrowExceptionForInvalidValues(
      final String value, final String expectedExceptionMessage) {

    assertThatThrownBy(() -> CurrencyCode.of(value))
        .isInstanceOf(InvalidValueException.class)
        .hasMessage(CurrencyCode.ERROR_MESSAGE.message() + ". " + expectedExceptionMessage)
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedExceptionMessage)
        .hasFieldOrPropertyWithValue("messageCode", CurrencyCode.ERROR_MESSAGE.code());
  }
}
