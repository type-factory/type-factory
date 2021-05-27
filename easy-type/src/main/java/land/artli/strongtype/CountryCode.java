package land.artli.strongtype;

import java.text.ParseException;

public final class CountryCode extends CharType<CountryCode> {

  private static final TypeParser TYPE_PARSER =
      TypeParser.builder(CountryCode.class)
          .acceptCharRange('a', 'z')
          .acceptCharRange('A', 'Z')
          .toUpperCase()
          .build();

  private CountryCode(String value) {
    super(value);
  }

  public static CountryCode of(final CharSequence value) throws ParseException {
    return TYPE_PARSER.parse(value, CountryCode::new);
  }
}
