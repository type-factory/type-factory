package org.typefactory.impl;

import org.assertj.core.api.Assertions;
import org.typefactory.InvalidValueException;
import org.typefactory.TypeParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TypeParser_CharacterRangeTest extends AbstractTypeParserTest {
  
  @ParameterizedTest
  @CsvSource(value = {
      "Café au lait|Invalid value - invalid character 'é'.",
      "Apple-tart|Invalid value - invalid character '-'.",
      "Apple tart with a really very long name|Invalid value - invalid white-space character U+0020.",
  }, delimiter = '|')
  void should_throw_exception_when_invalid_character(final String value, final String expectedParserErrorMessage) {

    final TypeParser typeParser =
        TypeParser.builder()
            .errorMessage("Some type must be alpha characters.")
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThatThrownBy(() -> typeParser.parseToString(value))
        .isInstanceOf(InvalidValueException.class)
        .hasMessageMatching("Some type must be alpha characters. Invalid value - invalid (white-space |control )?character ('[^']{1,2}'|U\\+[0-9A-F]{4,6}).")
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedParserErrorMessage);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "Τίγρη|Invalid value - invalid character 'ί'.", // Greek iota-with-tonos (diacritic) is not in the accepted character range
  }, delimiter = '|')
  void greek_range_should_throw_exception_when_invalid_character(final String value, final String expectedParserErrorMessage) {

    final TypeParser typeParser =
        TypeParser.builder()
            .errorMessage("Some type must be alpha characters.")
            // These Greek alphabet character ranges don't include characters with diacritics
            .acceptCharRange('α', 'ω')
            .acceptCharRange('Α', 'Ω')
            .build();

    Assertions.assertThatThrownBy(() -> typeParser.parseToString(value))
        .isInstanceOf(InvalidValueException.class)
        .hasMessageMatching("Some type must be alpha characters. Invalid value - invalid (white-space |control )?character ('[^']{1,2}'|U\\+[0-9A-F]{4,6}).")
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedParserErrorMessage);
  }
}
