package land.artli.strongtype;

import java.text.ParseException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class TypeParserTest {

  @ParameterizedTest
  @CsvSource(value = {
      "CAT|CAT",
      "Dog|Dog",
      "newT|newT",
      "snAke|snAke",
  }, delimiter = '|')
  void should_parse_preserving_case(final String value, final String expected) throws ParseException {

    final TypeParser typeParser =
        TypeParser.builder(CharType.class)
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .preserveCase()
            .build();

    Assertions.assertThat(typeParser.parse(value)).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "CAT|cat",
      "Dog|dog",
      "newT|newt",
      "snAke|snake",
  }, delimiter = '|')
  void should_parse_to_lower_case(final String value, final String expected) throws ParseException {

    final TypeParser typeParser =
        TypeParser.builder(CharType.class)
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .toLowerCase()
            .build();

    Assertions.assertThat(typeParser.parse(value)).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "CAT|CAT",
      "Dog|DOG",
      "newT|NEWT",
      "snAke|SNAKE",
  }, delimiter = '|')
  void should_parse_to_upper_case(final String value, final String expected) throws ParseException {

    final TypeParser typeParser =
        TypeParser.builder(CharType.class)
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .toUpperCase()
            .build();

    Assertions.assertThat(typeParser.parse(value)).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "CAT|Cat",
      "Dog|Dog",
      "newT|Newt",
      "snAke|Snake",
  }, delimiter = '|')
  void should_parse_to_title_case(final String value, final String expected) throws ParseException {

    final TypeParser typeParser =
        TypeParser.builder(CharType.class)
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .toTitleCase()
            .build();

    Assertions.assertThat(typeParser.parse(value)).hasToString(expected);
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "Fish",
      "Bird",
  })
  void should_parse_to_fixed_size(final String value) throws ParseException {

    final TypeParser typeParser =
        TypeParser.builder(CharType.class)
            .fixedNumberOfCodePoints(4)
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThat(typeParser.parse(value)).hasToString(value);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "Flood \t Plains|FloodPlains",
      "Deep \s  \t  \s Valley|DeepValley",
  }, delimiter = '|')
  void should_parse_and_remove_all_whitespace(final String value, final String expected) throws ParseException {

    final TypeParser typeParser =
        TypeParser.builder(CharType.class)
            .acceptAndRemoveAllWhitespace()
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
        TypeParser.builder(CharType.class)
            .removeAllWhitespace()
            .acceptChars(' ', '\t')
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThat(typeParser.parse(value)).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "Flood \t Plains|Invalid CharType value - invalid white-space character '\\u0009'.",
      "Deep \s  \t  \s Valley|Invalid CharType value - invalid white-space character '\\u0009'.",
  }, delimiter = '|')
  void should_throw_exception_when_removing_whitespace_and_whitespace_not_accepted(
      final String value, final String expectedMessage) {

    final TypeParser typeParser =
        TypeParser.builder(CharType.class)
            .removeAllWhitespace()
            .acceptChars(' ') // we are not accepting tabs '\t'
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThatThrownBy(() -> typeParser.parse(value))
        .isInstanceOf(InvalidTypeValueException.class)
        .hasMessage(expectedMessage);
  }


  @ParameterizedTest
  @CsvSource(value = {
      "Flood \t Plains|Flood Plains",
      "Deep \s  \t  \s Valley|Deep Valley",
  }, delimiter = '|')
  void should_parse_to_normalized_whitespace(final String value, final String expected) throws ParseException {

    final TypeParser typeParser =
        TypeParser.builder(CharType.class)
            .acceptAllWhitespace()
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
        TypeParser.builder(CharType.class)
            .acceptAllWhitespace()
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
        TypeParser.builder(CharType.class)
            .acceptAllWhitespace()
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
        TypeParser.builder(CharType.class)
            .acceptAllWhitespace()
            .preserveAndConvertWhitespaceTo(convertTo)
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThat(typeParser.parse(value)).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "Ox|Invalid CharType value - too short, min length must be '4'.",
      "Cat|Invalid CharType value - too short, min length must be '4'.",
  }, delimiter = '|')
  void should_throw_exception_when_too_small(final String value, final String expectedMessage) {

    final TypeParser typeParser =
        TypeParser.builder(CharType.class)
            .errorMessage("Some type must be 4 alpha characters.")
            .fixedNumberOfCodePoints(4)
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThatThrownBy(() -> typeParser.parse(value))
        .isInstanceOf(InvalidTypeValueException.class)
        .hasMessage(expectedMessage)
        .hasFieldOrPropertyWithValue("localizedMessage", "Some type must be 4 alpha characters.");
  }

  @ParameterizedTest
  @CsvSource(value = {
      "Tiger|Invalid CharType value - too long, max length must be '4'.",
      "Donkey|Invalid CharType value - too long, max length must be '4'.",
      "Mammoth|Invalid CharType value - too long, max length must be '4'.",
  }, delimiter = '|')
  void should_throw_exception_when_too_large(final String value, final String expectedMessage) {

    final TypeParser typeParser =
        TypeParser.builder(CharType.class)
            .errorMessage("Some type must be 4 alpha characters.")
            .fixedNumberOfCodePoints(4)
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThatThrownBy(() -> typeParser.parse(value))
        .isInstanceOf(InvalidTypeValueException.class)
        .hasMessage(expectedMessage)
        .hasFieldOrPropertyWithValue("localizedMessage", "Some type must be 4 alpha characters.");
  }

  @ParameterizedTest
  @CsvSource(value = {
      "Café au lait|Invalid CharType value - invalid character 'é'.",
      "Apple-tart|Invalid CharType value - invalid character '-'.",
      "Apple-tart-with-a-really-very-long-name|Invalid CharType value - invalid character '-'.",
  }, delimiter = '|')
  void should_throw_exception_when_invalid_character(final String value, final String expectedMessage) {

    final TypeParser typeParser =
        TypeParser.builder(CharType.class)
            .errorMessage("Some type must be 16 alpha characters.")
            .fixedNumberOfCodePoints(16)
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThatThrownBy(() -> typeParser.parse(value))
        .isInstanceOf(InvalidTypeValueException.class)
        .hasMessage(expectedMessage)
        .hasFieldOrPropertyWithValue("localizedMessage", "Some type must be 16 alpha characters.");
  }
}
