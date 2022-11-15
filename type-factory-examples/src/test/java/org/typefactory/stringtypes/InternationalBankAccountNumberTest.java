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

import static org.assertj.core.api.Assertions.assertThatObject;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.typefactory.InvalidValueException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class InternationalBankAccountNumberTest {

  @ParameterizedTest
  @CsvSource(value = {
      "BE71 0961 2345 6769                     | BE71096123456769                 ",
      "be71 0961 2345 6769                     | BE71096123456769                 ",
      "BE71.0961.2345.6769                     | BE71096123456769                 ",
      "BE71-0961-2345-6769                     | BE71096123456769                 ",
      "BE71–0961–2345–6769                     | BE71096123456769                 ",
      "BE71—0961—2345—6769                     | BE71096123456769                 ",
      "BR15 0000 0000 0000 1093 2840 814 P2    | BR1500000000000010932840814P2    ",
      "FR76 3000 6000 0112 3456 7890 189       | FR7630006000011234567890189      ",
      "DE91 1000 0000 0123 4567 89             | DE91100000000123456789           ",
      "GR96 0810 0010 0000 0123 4567 890       | GR9608100010000001234567890      ",
      "MU43 BOMM 0101 1234 5678 9101 000 MUR   | MU43BOMM0101123456789101000MUR   ",
      "mu43 bomm 0101 1234 5678 9101 000 mur   | MU43BOMM0101123456789101000MUR   ",
      "PK70 BANK 0000 1234 5678 9000           | PK70BANK0000123456789000         ",
      "pl10 1050 0099 7603 1234 5678 9123      | PL10105000997603123456789123     ",
      "pt50 0033 0000 5013 1901 229 05         | PT50003300005013190122905        ",
      "ro09 bcyp 0000 0012 3456 7890           | RO09BCYP0000001234567890         ",
      "lc14 bosl 1234 5678 9012 3456 7890 1234 | LC14BOSL123456789012345678901234 ",
      "SA44 2000 0001 2345 6789 1234           | SA4420000001234567891234         ",
      "SK08 0900 0000 0001 2312 3123           | SK0809000000000123123123         ",
      "ES79 2100 0813 6101 2345 6789           | ES7921000813610123456789         ",
      "CH56 0483 5012 3456 7800 9              | CH5604835012345678009            ",
      "GB98 MIDL 0700 9312 3456 78             | GB98MIDL07009312345678           ",
  }, delimiter = '|')
  void shouldSuccessfullyParseValidValues(final String value, final String expected) {
    final InternationalBankAccountNumber actual = InternationalBankAccountNumber.of(value);
    assertThatObject(actual).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "FR76 3000 6000 0112 3456 7890 1890 9999 1111 | Invalid value - too long, maximum length is 34.                          ",
      "B171 0961 2345 6769                          | Invalid value - does not match pattern [A-Z]{2}+[0-9]{2}+[0-9A-Z]{1,30}+ ",
      "BRA5 0000 0000 0000 1093 2840 814 P2         | Invalid value - does not match pattern [A-Z]{2}+[0-9]{2}+[0-9A-Z]{1,30}+ ",
      "FR77 3000 6000 0112 3456 7890 189            | Invalid value - does not pass custom validation criteria.                ",
  }, delimiter = '|')
  void shouldThrowExceptionForInvalidValues(final String value, final String expectedExceptionMessage) {
    assertThatThrownBy(() -> InternationalBankAccountNumber.of(value))
        .isInstanceOf(InvalidValueException.class)
        .hasMessage("must be a valid 5..34 character International Bank Account Number (IBAN). " + expectedExceptionMessage)
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedExceptionMessage);

  }
}
