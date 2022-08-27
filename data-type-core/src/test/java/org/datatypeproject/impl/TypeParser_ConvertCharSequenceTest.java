package org.datatypeproject.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import org.datatypeproject.TypeParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TypeParser_ConvertCharSequenceTest extends AbstractTypeParserTest {

  @ParameterizedTest
  @CsvSource(value = {
      " One red elephant   | red   | blue | One blue elephant ",
      " One pink elephant  | pink  | blue | One blue elephant ",
      " One white elephant | white | blue | One blue elephant ",
      " One full stop      | full  | stop | One stop stop      ",
  }, delimiter = '|')
  void should_parse_preserving_case(
      final String input,
      final String convertFrom, final String convertTo,
      final String expected) throws ParseException {

    final TypeParser typeParser =
        TypeParser.builder()
            .normalizeWhitespace()
            .convertChar(convertFrom, convertTo)
            .acceptLettersAtoZ()
            .build();

    assertThat(typeParser.parseToString(input)).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      " semi-trailer    | s          | d           |              |           |                 |       | demi-trailer        ",
      " semi-trailer    | s          | d           | semi-trailer | lorry     |                 |       | lorry               ",
      " semi-trailer    | semi       | articulated |              |           |                 |       | articulated-trailer ",
      " semi-trailer    | semi       | articulated | semi-trailer | lorry     |                 |       | lorry               ",
      " semi-trailer    | semi       | articulated | trailer      | vehicle   |                 |       | articulated-vehicle ",
      " semi-trailer    | semi       | articulated | trailer      | vehicle   | semi-trailer    | lorry | lorry               ",
      " full stop light | full stop  | period      |              |           |                 |       | period light        ",
      " full stop light | full stop  | period      | l            | n         |                 |       | period night        ",
      " full stop light | stop light | red light   |              |           |                 |       | full red light      ",
      " full stop light | full stop  | period      | stop light   | red light |                 |       | period light        ",
      " full stop light | full stop  | period      | stop light   | red light | full stop light | skid  | skid                ",
  }, delimiter = '|')
  void should_parse_preserving_case(
      final String input,
      final String convertFrom1, final String convertTo1,
      final String convertFrom2, final String convertTo2,
      final String convertFrom3, final String convertTo3,
      final String expected) throws ParseException {

    final TypeParser typeParser =
        TypeParser.builder()
            .normalizeWhitespace()
            .convertChar(convertFrom1, convertTo1)
            .convertChar(convertFrom2, convertTo2)
            .convertChar(convertFrom3, convertTo3)
            .acceptLettersAtoZ()
            .acceptChar('-')
            .build();

    assertThat(typeParser.parseToString(input)).hasToString(expected);
  }

}
