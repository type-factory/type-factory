package org.typefactory.stringtypes;

import org.typefactory.StringType;
import org.typefactory.TypeParser;

public final class IataAirportCode extends StringType {

  public static final IataAirportCode EMPTY_IATA_AIRPORT_CODE = new IataAirportCode("");

  private static final TypeParser TYPE_PARSER =
      TypeParser.builder()
          .errorMessage("must be a 3-character IATA airport value")
          .acceptLettersAtoZ()
          .fixedSize(3)
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
