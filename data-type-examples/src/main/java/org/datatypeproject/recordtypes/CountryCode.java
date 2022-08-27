package org.datatypeproject.recordtypes;

import org.datatypeproject.RecordType;
import org.datatypeproject.TypeParser;

public record CountryCode(String value) implements RecordType {

  public CountryCode(final String value) {
    this.value = TYPE_PARSER.parseToString(value);
  }

  @Override
  public String toString() {
    return value();
  }

  private static final TypeParser TYPE_PARSER =
      TypeParser.builder()
          .errorMessage("must be a 2-character ISO 3166-1 country code")
          .convertNullToEmpty()
          .acceptLettersAtoZ()
          .fixedSize(2)
          .toUpperCase()
          .build();

  public static final CountryCode EMPTY_COUNTRY_CODE = new CountryCode("");
}
