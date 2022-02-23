package land.artli.example.recordtypes;

import land.artli.easytype.TypeParser;

public record IataAirportCode(CharSequence code) {

  public IataAirportCode(CharSequence code) {
    this.code = TYPE_PARSER.parseToString(code);
  }

  private static final TypeParser TYPE_PARSER =
      TypeParser.builder(IataAirportCode.class)
          .errorMessage("must be a 3-character IATA airport value")
          .acceptLettersAtoZ()
          .fixedSizeNumberOfCodePoints(3)
          .toUpperCase()
          .build();
}
