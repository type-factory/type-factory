package org.typefactory.language;

import org.assertj.core.api.Assertions;
import org.typefactory.InvalidValueException;
import org.typefactory.TypeParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TypeParser_LanguageGermanTest extends AbstractTypeParserTest {

  static final TypeParser TYPE_PARSER =
      TypeParser.builder()
          .errorMessage("Must be made up of German letters only.")
          .toCharacterNormalizationFormNFC()
          .acceptSubset(Letters.GERMAN_de)
          .normalizeWhitespace()
          .build();

  @ParameterizedTest
  @ValueSource(strings = {
      "das Eichhörnchen", // German 'Squirrel'
      "der Hirsch", // German 'Deer'
      "die Weiße Taube", // German 'Dove'
  })
  void should_parse_accepting_only_german_letters(final String value) {
    Assertions.assertThat(TYPE_PARSER.parseToString(value)).hasToString(value);
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "das Εichhörnchen", // German 'Squirrel' with Greek 'Ε' epsilon
      "der Ηirsch", // German 'Deer' with Greek 'Η' ita
      "die Weiße Τaube", // German 'Dove' with Greek 'Τ' taf
  })
  void should_throw_exception_with_non_german_letters(final String value) {
    Assertions.assertThatExceptionOfType(InvalidValueException.class)
        .isThrownBy(() -> TYPE_PARSER.parseToString(value))
        .withMessageMatching("Must be made up of German letters only. Invalid value - invalid character '[^']+'.");
  }

}