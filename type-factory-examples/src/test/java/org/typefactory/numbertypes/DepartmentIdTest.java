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

class DepartmentIdTest {

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = "  ")
  void of_shouldReturnNull(final String value) {
    final DepartmentId actual = DepartmentId.of(value);
    assertThatObject(actual).isNull();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      1000       | 1000
      ' 1001 '   | 1001
      '  1002  ' | 1002
      9999       | 9999
      """, delimiter = '|')
  void of_shouldCreateDepartmentIdInstancesAsExpected(final String value, final Short expected) {
    final DepartmentId actual = DepartmentId.of(value);
    assertThatObject(actual)
        .isNotNull()
        .hasToString(String.valueOf(expected));
    assertThatObject(actual.value()).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      0     | Invalid value - must be greater than or equal to 1,000.
      000   | Invalid value - must be greater than or equal to 1,000.
      111   | Invalid value - must be greater than or equal to 1,000.
      00000 | Invalid value - must be greater than or equal to 1,000.
      10000 | Invalid value - must be less than or equal to 9,999.
      99999 | Invalid value - must be less than or equal to 9,999.
      A     | Invalid value - invalid character A U+0041 LATIN CAPITAL LETTER A.
      AAAA  | Invalid value - invalid character A U+0041 LATIN CAPITAL LETTER A.
      111B  | Invalid value - invalid character B U+0042 LATIN CAPITAL LETTER B.
      """, delimiter = '|')
  void of_shouldThrowExceptionForInvalidValues(final String value, final String expectedExceptionMessage) {
    assertThatThrownBy(() -> DepartmentId.of(value))
        .isInstanceOf(InvalidValueException.class)
        .hasMessage("must be a 4-digit department-id. " + expectedExceptionMessage)
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedExceptionMessage);
  }
}
