package land.artli.easytype;

import java.text.ParseException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TypeParser_DashPunctuationTest extends AbstractTypeParserTest {

  @ParameterizedTest
  @CsvSource(value = {
      "Water-slide|Water-slide",
      "Water–slide|Water-slide",
      "Water—slide|Water-slide",
  }, delimiter = '|')
  void should_parse_converting_all_dashes_to_hyphen(final String value, final String expected) throws ParseException {

    final TypeParser typeParser =
        TypeParser.builder(SomeType.class)
            .acceptAllDashes()
            .convertAllDashesToHyphen()
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "Water-slide|Water_slide",
      "Water–slide|Water_slide",
      "Water—slide|Water_slide",
  }, delimiter = '|')
  void should_parse_converting_all_dashes_to_underscore(final String value, final String expected) throws ParseException {

    final TypeParser typeParser =
        TypeParser.builder(SomeType.class)
            .acceptAllDashes()
            .convertAllDashesTo('_')
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "Water-slide|Water===slide",
      "Water–slide|Water===slide",
      "Water—slide|Water===slide",
  }, delimiter = '|')
  void should_parse_converting_all_dashes_to_char_sequence(final String value, final String expected) throws ParseException {

    final TypeParser typeParser =
        TypeParser.builder(SomeType.class)
            .acceptAllDashes()
            .convertAllDashesTo("===")
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(expected);
  }
}
