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

import static org.assertj.core.api.Assertions.assertThatObject;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CurrencyCodeTest {

  @ParameterizedTest
  @CsvSource(value = {
      "AUD|AUD",
      "uSd|USD",
      "NzD|NZD",
      "eur|EUR",
  }, delimiter = '|')
  void should_parse_valid_values(final String value, final String expected) {
    final CurrencyCode actual = new CurrencyCode(value);
    assertThatObject(actual).hasToString(expected);
  }
}
