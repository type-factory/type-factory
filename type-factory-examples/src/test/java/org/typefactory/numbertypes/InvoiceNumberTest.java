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

class InvoiceNumberTest {

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = "  ")
  void of_shouldReturnNull(final String value) {
    final InvoiceNumber actual = InvoiceNumber.of(value);
    assertThatObject(actual).isNull();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      100020030         | 100020030
      999999999         | 999999999
      1000-200-30       | 100020030
      9999-999-99       | 999999999
      ' 1111-222-33 '   | 111122233
      '  1111-222-33  ' | 111122233
      """, delimiter = '|')
  void of_shouldCreateInvoiceNumberInstancesAsExpected(final String value, final Integer expected) {
    final InvoiceNumber actual = InvoiceNumber.of(value);
    assertThatObject(actual)
        .isNotNull()
        .hasToString(String.valueOf(expected));
    assertThatObject(actual.value()).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      0            | Invalid value - too short, minimum length is 9.
      00000000     | Invalid value - too short, minimum length is 9.
      0000-00-00   | Invalid value - too short, minimum length is 9.
      0000.00.00   | Invalid value - invalid character . U+002E FULL STOP.
      0000_00_00   | Invalid value - invalid character _ U+005F LOW LINE.
      0000000000   | Invalid value - too long, maximum length is 9.
      9999999999   | Invalid value - too long, maximum length is 9.
      0000-000-000 | Invalid value - too long, maximum length is 9.
      00000000A    | Invalid value - invalid character A U+0041 LATIN CAPITAL LETTER A.
      A            | Invalid value - invalid character A U+0041 LATIN CAPITAL LETTER A.
      """, delimiter = '|')
  void of_shouldThrowExceptionForInvalidValues(final String value, final String expectedExceptionMessage) {
    assertThatThrownBy(() -> InvoiceNumber.of(value))
        .isInstanceOf(InvalidValueException.class)
        .hasMessage("must be a 9-digit invoice-number. " + expectedExceptionMessage)
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedExceptionMessage);
  }
}
