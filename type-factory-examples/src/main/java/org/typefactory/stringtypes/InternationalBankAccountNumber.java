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

import java.io.Serial;
import java.util.regex.Pattern;
import org.typefactory.ErrorCode;
import org.typefactory.StringType;
import org.typefactory.TypeParser;

public final class InternationalBankAccountNumber extends StringType {

  @Serial
  private static final long serialVersionUID = -1348055921186956330L;

  public static final InternationalBankAccountNumber EMPTY_IBAN = new InternationalBankAccountNumber("");
  private static final Pattern VALID_IBAN_PATTERN = Pattern.compile("[A-Z]{2}+[0-9]{2}+[0-9A-Z]{1,30}+");

  private static final ErrorCode ERROR_CODE =
      ErrorCode.of("invalid.international.bank.account.number", "must be a valid 5..34 character International Bank Account Number (IBAN)");

  private static final TypeParser TYPE_PARSER = TypeParser.builder()
      .errorCode(ERROR_CODE)
      .acceptLettersAtoZ()
      .acceptDigits0to9()
      .minSize(5)
      .maxSize(34)
      .removeAllWhitespace()
      .removeAllChars('.', '-', '–', '—') // period, dash, endash, emdash
      .toUpperCase()
      .matchesRegex(VALID_IBAN_PATTERN)
      .customValidator(InternationalBankAccountNumber::isValidIBAN)
      .preserveNullAndEmpty()
      .build();

  private InternationalBankAccountNumber(final String value) {
    super(value);
  }

  public static InternationalBankAccountNumber of(final CharSequence value) {
    return TYPE_PARSER.parseToStringType(value, InternationalBankAccountNumber::new);
  }

  private static final long MAX = 999999999;
  private static final long MODULUS = 97;
  private static final int MAX_ALPHANUMERIC_VALUE = 35;

  private static boolean isValidIBAN(final String value) {
    final int valueLength = value.length();
    long total = 0;
    for (int i = 0; i < valueLength; ++i) {
      final int numericValue = Character.getNumericValue(value.charAt((i + 4) % valueLength));
      if (numericValue < 0 || numericValue > MAX_ALPHANUMERIC_VALUE) {
        return false;
      }
      total = (numericValue > 9 ? total * 100 : total * 10) + numericValue;
      if (total > MAX) {
        total = total % MODULUS;
      }
    }
    return (total % MODULUS) == 1;
  }
}
