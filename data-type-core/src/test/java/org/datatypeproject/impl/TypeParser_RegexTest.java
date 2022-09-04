package org.datatypeproject.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.regex.Pattern;
import org.datatypeproject.InvalidValueException;
import org.datatypeproject.TypeParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TypeParser_RegexTest extends AbstractTypeParserTest {

  @ParameterizedTest
  @CsvSource(value = {
      "[a-z]{3}-[0-9]{3} | ABC-012         | abc-012         ",
      "[a-z]{3}-[0-9]{3} | XYZ-789         | xyz-789         ",
      "\\w+(?:\\.\\w+)*  | COM             | com             ",
      "\\w+(?:\\.\\w+)*  | Example.Com     | example.com     ",
      "\\w+(?:\\.\\w+)*  | www.example.com | www.example.com ",
  }, delimiter = '|')
  void parserWithRegexShouldSuccessfullyParse(
      final Pattern regex, final String value, final String expected) {

    final TypeParser typeParser =
        TypeParser.builder()
            .errorMessage("Must conform to format.")
            .acceptLettersAtoZ()
            .acceptDigits0to9()
            .acceptChars('-', '.')
            .toLowerCase()
            .matchesRegex(regex)
            .build();

    final String actual = typeParser.parseToString(value);
    assertThat(actual).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "[a-z]{3}-[0-9]{3} | ABC-ABC         | Invalid value - does not match pattern [a-z]{3}-[0-9]{3} ",
      "[a-z]{3}-[0-9]{3} | XYZ_789         | Invalid value - does not match pattern [a-z]{3}-[0-9]{3} ",
      "\\w+(?:\\.\\w+)*  | Example-Com     | Invalid value - does not match pattern \\w+(?:\\.\\w+)*  ",
      "\\w+(?:\\.\\w+)*  | www-example.com | Invalid value - does not match pattern \\w+(?:\\.\\w+)*  ",
  }, delimiter = '|')
  void parserWithRegexShouldThrowExceptionWhileParsing(
      final Pattern regex, final String value, final String expectedExceptionMessage) {

    final TypeParser typeParser =
        TypeParser.builder()
            .errorMessage("Must conform to format.")
            .acceptLettersAtoZ()
            .acceptDigits0to9()
            .acceptChars('_', '-', '.')
            .toLowerCase()
            .matchesRegex(regex)
            .build();

    assertThatThrownBy(() -> typeParser.parseToString(value))
        .isInstanceOf(InvalidValueException.class)
        .hasMessage("Must conform to format. Invalid value - does not match pattern " + regex.toString())
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedExceptionMessage);
  }
}
