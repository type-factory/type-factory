package land.artli.example.recordtypes;

import land.artli.easytype.RecordType;
import land.artli.easytype.TypeParser;

public record CountryCode(String value) implements RecordType {

  public CountryCode(String value) {
    this.value = TYPE_PARSER.parseToString(value);
  }

  private static final TypeParser TYPE_PARSER =
      TypeParser.builder(CountryCode.class)
          .errorMessage("must be a 2-character ISO 3166-1 country value")
          .convertNullToEmpty()
          .acceptLettersAtoZ()
          .fixedSizeNumberOfCodePoints(2)
          .toUpperCase()
          .build();
}
