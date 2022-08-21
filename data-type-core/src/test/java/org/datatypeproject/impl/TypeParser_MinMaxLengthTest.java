package org.datatypeproject.impl;

import java.text.ParseException;
import org.assertj.core.api.Assertions;
import org.datatypeproject.InvalidDataTypeValueException;
import org.datatypeproject.TypeParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class TypeParser_MinMaxLengthTest extends AbstractTypeParserTest {
  

  @ParameterizedTest
  @ValueSource(strings = {
      "Fish",
      "Bird",
  })
  void should_parse_to_fixed_size(final String value) throws ParseException {

    final TypeParser typeParser =
        TypeParser.builder(SomeType.class)
            .fixedSize(4)
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(value);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "Ox|Invalid SomeType value - too short, min length must be '4'.",
      "Cat|Invalid SomeType value - too short, min length must be '4'.",
  }, delimiter = '|')
  void should_throw_exception_when_too_small(final String value, final String expectedParserErrorMessage) {

    final TypeParser typeParser =
        TypeParser.builder(SomeType.class)
            .errorMessage("Some type must be 4 alpha characters.")
            .fixedSize(4)
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThatThrownBy(() -> typeParser.parseToString(value))
        .isInstanceOf(InvalidDataTypeValueException.class)
        .hasMessage("Some type must be 4 alpha characters.")
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedParserErrorMessage);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "Tiger|Invalid SomeType value - too long, max length must be '4'.",
      "Donkey|Invalid SomeType value - too long, max length must be '4'.",
      "Mammoth|Invalid SomeType value - too long, max length must be '4'.",
  }, delimiter = '|')
  void should_throw_exception_when_too_large(final String value, final String expectedParserErrorMessage) {

    final TypeParser typeParser =
        TypeParser.builder(SomeType.class)
            .errorMessage("Some type must be 4 alpha characters.")
            .fixedSize(4)
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThatThrownBy(() -> typeParser.parseToString(value))
        .isInstanceOf(InvalidDataTypeValueException.class)
        .hasMessage("Some type must be 4 alpha characters.")
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedParserErrorMessage);
  }

}
