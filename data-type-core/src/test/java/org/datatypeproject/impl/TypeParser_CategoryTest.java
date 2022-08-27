package org.datatypeproject.impl;

import org.assertj.core.api.Assertions;
import org.datatypeproject.Category;
import org.datatypeproject.InvalidDataTypeValueException;
import org.datatypeproject.TypeParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TypeParser_CategoryTest extends AbstractTypeParserTest {

  @ParameterizedTest
  @CsvSource(value = {
      "CAT",
      "DEER",
      "ΤΙΓΡΗ", // Greek
      "ЖИРАФА", // Russian
  }, delimiter = '|')
  void should_parse_accepting_only_uppercase_letters(final String value) {

    final TypeParser typeParser =
        TypeParser.builder()
            .acceptUnicodeCategory(Category.UPPERCASE_LETTER)
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(value);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "Cat    | Invalid value - invalid character 'a'.",
      "Deer   | Invalid value - invalid character 'e'.",
      "Τίγρη  | Invalid value - invalid character 'ί'.", // Greek
      "Жирафа | Invalid value - invalid character 'и'.", // Russian
  }, delimiter = '|')
  void should_throw_exception_when_value_contains_lowercase_letters(
      final String value, final String expectedParserErrorMessage) {

    final TypeParser typeParser =
        TypeParser.builder()
            .errorMessage("Some type must be uppercase alpha characters.")
            .acceptUnicodeCategory(Category.UPPERCASE_LETTER)
            .build();

    Assertions.assertThatThrownBy(() -> typeParser.parseToString(value))
        .isInstanceOf(InvalidDataTypeValueException.class)
        .hasMessage("Some type must be uppercase alpha characters. " + expectedParserErrorMessage)
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedParserErrorMessage);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "cat",
      "deer",
      "τίγρη", // Greek
      "жирафа", // Russian
  }, delimiter = '|')
  void should_parse_accepting_only_lowercase_letters(
      final String value) {

    final TypeParser typeParser =
        TypeParser.builder()
            .acceptUnicodeCategory(Category.LOWERCASE_LETTER)
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(value);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "Cat|Invalid value - invalid character 'C'.",
      "Deer|Invalid value - invalid character 'D'.",
      "Τίγρη|Invalid value - invalid character 'Τ'.", // Greek
      "Жирафа|Invalid value - invalid character 'Ж'.", // Russian
  }, delimiter = '|')
  void should_throw_exception_when_value_contains_uppercase_letters(
      final String value, final String expectedParserErrorMessage) {

    final TypeParser typeParser =
        TypeParser.builder()
            .errorMessage("Some type must be uppercase alpha characters.")
            .acceptUnicodeCategory(Category.LOWERCASE_LETTER)
            .build();

    Assertions.assertThatThrownBy(() -> typeParser.parseToString(value))
        .isInstanceOf(InvalidDataTypeValueException.class)
        .hasMessage("Some type must be uppercase alpha characters. " + expectedParserErrorMessage)
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedParserErrorMessage);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "Cat",
      "Deer",
      "Τίγρη", // Greek
      "Жирафа", // Russian
  }, delimiter = '|')
  void should_parse_accepting_only_letters(
      final String value) {

    final TypeParser typeParser =
        TypeParser.builder()
            .acceptUnicodeCategory(Category.LETTER)
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(value);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "Cat\uD83D\uDC08|Invalid value - invalid character '\uD83D\uDC08'.", // 🐈 cat emoji
      "Deer\uD83E\uDD8C|Invalid value - invalid character '\uD83E\uDD8C'.", // 🦌 deer emoji
      "Τίγρη\uD83D\uDC05|Invalid value - invalid character '\uD83D\uDC05'.", // Greek with 🐅 tiger emoji
      "Жирафа\uD83E\uDD92|Invalid value - invalid character '\uD83E\uDD92'.", // Russian with 🦒 giraffe emoji
  }, delimiter = '|')
  void should_throw_exception_when_value_contains_letters(
      final String value, final String expectedParserErrorMessage) {

    final TypeParser typeParser =
        TypeParser.builder()
            .errorMessage("Some type must be uppercase alpha characters.")
            .acceptUnicodeCategory(Category.LETTER)
            .build();

    Assertions.setMaxStackTraceElementsDisplayed(16);

    Assertions.assertThatThrownBy(() -> typeParser.parseToString(value))
        .isInstanceOf(InvalidDataTypeValueException.class)
        .hasMessage("Some type must be uppercase alpha characters. " + expectedParserErrorMessage)
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedParserErrorMessage);
  }

}
