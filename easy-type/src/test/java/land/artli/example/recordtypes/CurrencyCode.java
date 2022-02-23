package land.artli.example.recordtypes;

import land.artli.easytype.TypeParser;

public record CurrencyCode(CharSequence code) {

  public CurrencyCode(CharSequence code) {
    this.code = TYPE_PARSER.parseToString(code);
  }

  private static final TypeParser TYPE_PARSER =
      TypeParser.builder(CurrencyCode.class)
          .errorMessage("must be a 3-character ISO 4217 currency value")
          .acceptLettersAtoZ()
          .fixedSizeNumberOfCodePoints(3)
          .toUpperCase()
          .build();
}
