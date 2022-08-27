package org.datatypeproject.impl;

import java.text.ParseException;
import org.assertj.core.api.Assertions;
import org.datatypeproject.TypeParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TypeParser_LetterCaseTest extends AbstractTypeParserTest {
  
  @ParameterizedTest
  @CsvSource(value = {
      "CAT|CAT",
      "Dog|Dog",
      "newT|newT",
      "snAke|snAke",
  }, delimiter = '|')
  void should_parse_preserving_case(final String value, final String expected) throws ParseException {

    final TypeParser typeParser =
        TypeParser.builder()
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .preserveCase()
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(expected);
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
        TypeParser.builder()
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .toLowerCase()
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(expected);
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
        TypeParser.builder()
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .toUpperCase()
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(expected);
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
        TypeParser.builder()
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .toTitleCase()
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(expected);
  }
}
