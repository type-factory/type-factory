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
      TypeParser.builder(CountryCode.class)
          .errorMessage("must be a 2-character ISO 3166-1 country code")
          .convertNullToEmpty()
          .acceptLettersAtoZ()
          .fixedSizeNumberOfCodePoints(2)
          .toUpperCase()
          .build();
}
