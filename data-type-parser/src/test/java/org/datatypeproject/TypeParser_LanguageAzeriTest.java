package org.datatypeproject;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TypeParser_LanguageAzeriTest extends AbstractTypeParserTest {

  static final TypeParser TYPE_PARSER =
      TypeParser.builder(SomeType.class)
          .errorMessage("Must be made up of Azeri Latin letters only.")
          .toCharacterNormalizationFormNFC()
          .acceptSubset(Letters.AZERBAIJANI_az_Latn)
          .normalizeWhitespace()
          .build();

  @ParameterizedTest
  @ValueSource(strings = {
      "Eşşək", // Azeri 'Donkey'
      "Kəpənək", // Azeri 'Butterfly'
      "Zürafə", // Azeri 'Girrafe'
  })
  void should_parse_accepting_only_azeri_latin_letters(final String value) {
    Assertions.assertThat(TYPE_PARSER.parseToString(value)).hasToString(value);
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "Εşşək", // Azeri 'Donkey' with Greek 'Ε' epsilon
      "Κəpənək", // Azeri 'Butterfly' with Greek 'Κ' kappa
      "Ζürafə", // Azeri 'Girrafe' with Greek 'Ζ' zita
  })
  void should_throw_exception_with_non_azeri_latin_letters(final String value) {
    Assertions.assertThatExceptionOfType(InvalidDataTypeValueException.class)
        .isThrownBy(() -> TYPE_PARSER.parseToString(value))
        .withMessage("Must be made up of Azeri Latin letters only.");
  }

}
