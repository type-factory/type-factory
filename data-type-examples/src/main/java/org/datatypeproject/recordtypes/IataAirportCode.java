package org.datatypeproject.recordtypes;

import org.datatypeproject.RecordType;
import org.datatypeproject.TypeParser;

public record IataAirportCode(String value) implements RecordType {

  public IataAirportCode(String value) {
    this.value = TYPE_PARSER.parseToString(value);
  }

  @Override
  public String toString() {
    return value();
  }

  private static final TypeParser TYPE_PARSER =
      TypeParser.builder()
          .errorMessage("must be a 3-character IATA airport value")
          .acceptLettersAtoZ()
          .fixedSize(3)
          .toUpperCase()
          .build();
}
