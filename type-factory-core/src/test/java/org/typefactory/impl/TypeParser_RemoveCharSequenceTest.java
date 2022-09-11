package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.TypeParser;

class TypeParser_RemoveCharSequenceTest extends AbstractTypeParserTest {

  @ParameterizedTest
  @CsvSource(value = {
      " One red elephant     | red         | One elephant ",
      " One big red elephant | big red     | One elephant ",
      " One big red elephant | 'big ,red ' | One elephant ",
  },  delimiter = '|')
  void typeParser_shouldRemoveCharSequence(
      final String input,
      final String toRemove,
      final String expected) {

    final String[] charSequencesToRemove = toRemove.split(",");

    final TypeParser typeParser =
        TypeParser.builder()
            .normalizeWhitespace()
            .removeAllCharSequences(charSequencesToRemove)
            .acceptLettersAtoZ()
            .build();

    assertThat(typeParser.parseToString(input)).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      " semi-trailer    | -          |               | semitrailer ",
      " semi-trailer    | semi-      |               | trailer     ",
      " semi-trailer    | -trailer   |               | semi        ",
      " semi-trailer    | semi       |  trailer      | -           ",
      " full stop light | full       |  stop         | light       ",
      " full stop light | stop       |  light        | full        ",
      " full stop light | full       |  full stop    | light       ",
      " full stop light | stop       |  stop light   | full        ",
  }, delimiter = '|')
  void should_parse_preserving_case(
      final String input,
      final String toRemove1,
      final String toRemove2,
      final String expected) {

    final TypeParser typeParser =
        TypeParser.builder()
            .normalizeWhitespace()
            .removeAllCharSequences(toRemove1)
            .removeAllCharSequences(toRemove2)
            .acceptLettersAtoZ()
            .acceptChar('-')
            .build();

    assertThat(typeParser.parseToString(input)).hasToString(expected);
  }

}
