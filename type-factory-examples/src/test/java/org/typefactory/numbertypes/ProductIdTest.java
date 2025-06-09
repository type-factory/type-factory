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
package org.typefactory.numbertypes;

import static org.assertj.core.api.Assertions.assertThatObject;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.typefactory.InvalidValueException;

class ProductIdTest {

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = "  ")
  void of_shouldReturnNull(final String value) {
    final ProductId actual = ProductId.of(value);
    assertThatObject(actual).isNull();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      0000000000000000          | 0
      0000000000000001          | 1
      1000200030004000          | 1000200030004000
      1111222233334444          | 1111222233334444
      9999999999999999          | 9999999999999999
      0000 0000 0000 0000       | 0
      0000-0000-0000-0001       | 1
      2222 3333 4444 5555       | 2222333344445555
      9999 9999 9999 9999       | 9999999999999999
      ' 2222 3333 4444 5555 '   | 2222333344445555
      '  2222 3333 4444 5555  ' | 2222333344445555
      """, delimiter = '|')
  void of_shouldCreateProductIdInstancesAsExpected(final String value, final Long expected) {
    final ProductId actual = ProductId.of(value);
    assertThatObject(actual)
        .isNotNull()
        .hasToString(String.valueOf(expected));
    assertThatObject(actual.value()).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      0                     | Invalid value - too short, minimum length is 16.
      000000000000000       | Invalid value - too short, minimum length is 16.
      0000-0000-0000-000    | Invalid value - too short, minimum length is 16.
      0000.0000.0000.0000   | Invalid value - invalid character '.' U+002E FULL STOP.
      0000_0000_0000_0000   | Invalid value - invalid character '_' U+005F LOW LINE.
      00000000000000000     | Invalid value - too long, maximum length is 16.
      99999999999999999     | Invalid value - too long, maximum length is 16.
      0000-0000-0000-0000-0 | Invalid value - too long, maximum length is 16.
      00000000000000A       | Invalid value - invalid character 'A' U+0041 LATIN CAPITAL LETTER A.
      000000000000000A      | Invalid value - invalid character 'A' U+0041 LATIN CAPITAL LETTER A.
      0000000000000000A     | Invalid value - invalid character 'A' U+0041 LATIN CAPITAL LETTER A.
      A                     | Invalid value - invalid character 'A' U+0041 LATIN CAPITAL LETTER A.
      """, delimiter = '|')
  void of_shouldThrowExceptionForInvalidValues(final String value, final String expectedExceptionMessage) {
    assertThatThrownBy(() -> ProductId.of(value))
        .isInstanceOf(InvalidValueException.class)
        .hasMessage("must be a 16-digit product-id. " + expectedExceptionMessage)
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedExceptionMessage);
  }
}
