package org.typefactory.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.TypeParser;

class TypeParser_RemoveCharacterTest extends AbstractTypeParserTest {

  @ParameterizedTest
  @CsvSource(value = {
      " 1234-ABCD   | -   | 1234ABCD ",
      " 12 34-AB_CD | - _ | 1234ABCD ",
      " 12.34-AB.CD | -.  | 1234ABCD ",
      " 12/34-AB/CD | -/  | 1234ABCD ",
  }, delimiter = '|')
  void typeParser_shouldRemoveCharacters(final String value, final String charactersToRemove, final String expected) {

    final TypeParser typeParser =
        TypeParser.builder()
            .minSize(1)
            .maxSize(50)
            .toUpperCase()
            .removeAllChars(charactersToRemove.toCharArray())
            .acceptDigits0to9()
            .acceptLettersAtoZ()
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).hasToString(expected);
  }

}
