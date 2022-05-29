package org.datatypeproject.recordtypes;

import org.datatypeproject.RecordType;
import org.datatypeproject.TypeParser;

public record CurrencyCode(String value) implements RecordType {

  public CurrencyCode(String value) {
    this.value = TYPE_PARSER.parseToString(value);
  }

  @Override
  public String toString() {
    return value();
  }

  private static final TypeParser TYPE_PARSER =
      TypeParser.builder(CurrencyCode.class)
          .errorMessage("must be a 3-character ISO 4217 currency code")
          .acceptLettersAtoZ()
          .fixedSize(3)
          .toUpperCase()
          .build();
}
