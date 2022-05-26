package org.datatypeproject.stringtypes;

import org.datatypeproject.StringType;
import org.datatypeproject.TypeParser;

public final class IataAirportCode extends StringType {

  public static final IataAirportCode EMPTY_IATA_AIRPORT_CODE = new IataAirportCode("");

  private static final TypeParser TYPE_PARSER =
      TypeParser.builder(IataAirportCode.class)
          .errorMessage("must be a 3-character IATA airport value")
          .acceptLettersAtoZ()
          .fixedSizeNumberOfCodePoints(3)
          .toUpperCase()
          .convertNullToEmpty()
          .build();

  private IataAirportCode(final String value) {
    super(value);
  }

  public static IataAirportCode of(final CharSequence value) {
    return TYPE_PARSER.parseToStringType(value, IataAirportCode::new);
  }
}
