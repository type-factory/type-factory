package land.artli.example.stringtypes;

import land.artli.easytype.StringType;
import land.artli.easytype.TypeParser;

public final class CountryCode extends StringType {

  public static final CountryCode EMPTY_COUNTRY_CODE = new CountryCode("");

  private static final TypeParser TYPE_PARSER =
      TypeParser.builder(CountryCode.class)
          .errorMessage("must be a 2-character ISO 3166-1 country value")
          .acceptLettersAtoZ()
          .fixedSizeNumberOfCodePoints(2)
          .toUpperCase()
          .preserveNullAndEmpty()
          .build();

  private CountryCode(final String value) {
    super(value);
  }

  public static CountryCode of(final CharSequence value) {
    return TYPE_PARSER.parseToStringType(value, CountryCode::new);
  }
}
