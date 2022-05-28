package org.datatypeproject;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TypeParser_CharacterRangeTest extends AbstractTypeParserTest {
  
  @ParameterizedTest
  @CsvSource(value = {
      "Café au lait|Invalid SomeType value - invalid character 'é'.",
      "Apple-tart|Invalid SomeType value - invalid character '-'.",
      "Apple tart with a really very long name|Invalid SomeType value - invalid white-space character '\\u0020'.",
  }, delimiter = '|')
  void should_throw_exception_when_invalid_character(final String value, final String expectedParserErrorMessage) {

    final TypeParser typeParser =
        TypeParser.builder(SomeType.class)
            .errorMessage("Some type must be alpha characters.")
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThatThrownBy(() -> typeParser.parseToString(value))
        .isInstanceOf(InvalidDataTypeValueException.class)
        .hasMessage("Some type must be alpha characters.")
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedParserErrorMessage);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "Τίγρη|Invalid SomeType value - invalid character 'ί'.", // Greek iota-with-tonos (diacritic) is not in the accepted character range
  }, delimiter = '|')
  void greek_range_should_throw_exception_when_invalid_character(final String value, final String expectedParserErrorMessage) {

    final TypeParser typeParser =
        TypeParser.builder(SomeType.class)
            .errorMessage("Some type must be alpha characters.")
            // These Greek alphabet character ranges don't include characters with diacritics
            .acceptCharRange('α', 'ω')
            .acceptCharRange('Α', 'Ω')
            .build();

    Assertions.assertThatThrownBy(() -> typeParser.parseToString(value))
        .isInstanceOf(InvalidDataTypeValueException.class)
        .hasMessage("Some type must be alpha characters.")
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedParserErrorMessage);
  }
}
