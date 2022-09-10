package org.typefactory.impl;

import java.text.ParseException;
import org.assertj.core.api.Assertions;
import org.typefactory.InvalidValueException;
import org.typefactory.TypeParser;
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
        TypeParser.builder()
            .fixedSize(4)
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(value);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "Ox|Invalid value - too short, minimum length is 4.",
      "Cat|Invalid value - too short, minimum length is 4.",
  }, delimiter = '|')
  void should_throw_exception_when_too_small(final String value, final String expectedParserErrorMessage) {

    final TypeParser typeParser =
        TypeParser.builder()
            .errorMessage("Some type must be 4 alpha characters.")
            .fixedSize(4)
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThatThrownBy(() -> typeParser.parseToString(value))
        .isInstanceOf(InvalidValueException.class)
        .hasMessage("Some type must be 4 alpha characters. Invalid value - too short, minimum length is 4.")
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedParserErrorMessage);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "Tiger|Invalid value - too long, maximum length is 4.",
      "Donkey|Invalid value - too long, maximum length is 4.",
      "Mammoth|Invalid value - too long, maximum length is 4.",
  }, delimiter = '|')
  void should_throw_exception_when_too_large(final String value, final String expectedParserErrorMessage) {

    final TypeParser typeParser =
        TypeParser.builder()
            .errorMessage("Some type must be 4 alpha characters.")
            .fixedSize(4)
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    Assertions.assertThatThrownBy(() -> typeParser.parseToString(value))
        .isInstanceOf(InvalidValueException.class)
        .hasMessage("Some type must be 4 alpha characters. Invalid value - too long, maximum length is 4.")
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedParserErrorMessage);
  }

}
