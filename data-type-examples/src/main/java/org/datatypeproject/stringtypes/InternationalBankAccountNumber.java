package org.datatypeproject.stringtypes;

import static java.lang.Character.getNumericValue;

import java.util.regex.Pattern;
import org.datatypeproject.StringType;
import org.datatypeproject.TypeParser;

public class InternationalBankAccountNumber extends StringType {

  public static final InternationalBankAccountNumber EMPTY_IBAN = new InternationalBankAccountNumber("");

  private static final TypeParser TYPE_PARSER =
      TypeParser.builder(InternationalBankAccountNumber.class)
          .errorMessage("must be a valid 5..34 character International Bank Account Number (IBAN)")
          .acceptLettersAtoZ()
          .acceptDigits0to9()
          .minSizeNumberOfCodePoints(5)
          .maxSizeNumberOfCodePoints(34)
          .removeAllWhitespace()
          .toUpperCase()
          .matchesRegex(Pattern.compile("[A-Z]{2}[0-9]{2}[0-9A-Z]{1,30}"))
          .withCustomValidation(InternationalBankAccountNumber::isValidIBAN)
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
      int numericValue = getNumericValue(value.charAt((i + 4) % valueLength));
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
