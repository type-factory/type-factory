package org.datatypeproject.stringtypes;

import org.datatypeproject.StringType;
import org.datatypeproject.TypeParser;

public final class CurrencyCode extends StringType {

  public static final CurrencyCode EMPTY_CURRENCY_CODE = new CurrencyCode("");

  private static final TypeParser TYPE_PARSER =
      TypeParser.builder(CurrencyCode.class)
          .errorMessage("must be a 3-character ISO 4217 currency code")
          .acceptCharRange('a', 'z')
          .acceptCharRange('A', 'Z')
          .fixedSize(3)
          .removeAllWhitespace()
          .preserveNullAndEmpty()
          .toUpperCase()
          .build();

  private CurrencyCode(final String value) {
    super(value);
  }

  public static CurrencyCode of(final CharSequence value) {
    return TYPE_PARSER.parseToStringType(value, CurrencyCode::new);
  }

}