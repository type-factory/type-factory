package land.artli.easytype;

import java.text.ParseException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class TypeParser_WhitespaceTest extends AbstractTypeParserTest {
  
  @ParameterizedTest
  @CsvSource(value = {
      "Flood \t Plains|FloodPlains",
      "Deep \s  \t  \s Valley|DeepValley",
  }, delimiter = '|')
  void should_parse_and_remove_all_whitespace(final String value, final String expected) throws ParseException {

    final TypeParser typeParser =
        TypeParser.builder(SomeType.class)
            .removeAllWhitespace()
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThat(typeParser.parse(value)).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "Flood \t Plains|FloodPlains",
      "Deep \s  \t  \s Valley|DeepValley",
  }, delimiter = '|')
  void should_parse_and_remove_all_accepted_whitespace(final String value, final String expected) throws ParseException {

    final TypeParser typeParser =
        TypeParser.builder(SomeType.class)
            .removeAllWhitespace()
            .acceptChars(' ', '\t')
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThat(typeParser.parse(value)).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "Flood \t Plains|Flood Plains",
      "Deep \s  \t  \s Valley|Deep Valley",
  }, delimiter = '|')
  void should_parse_to_normalized_whitespace(final String value, final String expected) throws ParseException {

    final TypeParser typeParser =
        TypeParser.builder(SomeType.class)
            .normalizeWhitespace()
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThat(typeParser.parse(value)).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "-|Flood \t Plains|Flood-Plains",
      "-|Deep \s  \t  \s Valley|Deep-Valley",
      "_|Flood \t Plains|Flood_Plains",
      "_|Deep \s  \t  \s Valley|Deep_Valley",
      "~|Flood \t Plains|Flood~Plains",
      "~|Deep \s  \t  \s Valley|Deep~Valley",
  }, delimiter = '|')
  void should_parse_to_normalized_and_converted_whitespace(
      final char convertTo, final String value, final String expected) throws ParseException {

    final TypeParser typeParser =
        TypeParser.builder(SomeType.class)
            .normalizeAndConvertWhitespaceTo(convertTo)
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThat(typeParser.parse(value)).hasToString(expected);
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "Flood \t Plains",
      "Deep \s  \t  \s Valley",
  })
  void should_parse_to_preserved_whitespace(final String value) throws ParseException {

    final TypeParser typeParser =
        TypeParser.builder(SomeType.class)
            .preserveWhitespace()
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThat(typeParser.parse(value)).hasToString(value);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "-|Flood \t Plains|Flood---Plains",
      "-|Deep \s \t \s Valley|Deep-------Valley",
      "_|Flood \t Plains|Flood___Plains",
      "_|Deep \s \t \s Valley|Deep_______Valley",
      "~|Flood \t Plains|Flood~~~Plains",
      "~|Deep \s \t \s Valley|Deep~~~~~~~Valley",
  }, delimiter = '|')
  void should_parse_to_preserved_and_converted_whitespace(
      final char convertTo, final String value, final String expected) throws ParseException {

    final TypeParser typeParser =
        TypeParser.builder(SomeType.class)
            .preserveAndConvertWhitespaceTo(convertTo)
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThat(typeParser.parse(value)).hasToString(expected);
  }
}
