package org.datatypeproject.language;

import org.assertj.core.api.Assertions;
import org.datatypeproject.InvalidDataTypeValueException;
import org.datatypeproject.language.Letters;
import org.datatypeproject.TypeParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TypeParser_LanguageIcelandicTest extends AbstractTypeParserTest {

  static final TypeParser TYPE_PARSER =
      TypeParser.builder()
          .errorMessage("Must be made up of Icelandic letters only.")
          .toCharacterNormalizationFormNFC()
          .acceptSubset(Letters.ICELANDIC_is)
          .normalizeWhitespace()
          .build();

  @ParameterizedTest
  @ValueSource(strings = {
      "björn", // Icelandic 'Bear'
      "mörður", // Icelandic 'Marten'
      "hvítabjörninn", // Icelandic 'Polar Bear'
  })
  void should_parse_accepting_only_icelandic_letters(final String value) {
    Assertions.assertThat(TYPE_PARSER.parseToString(value)).hasToString(value);
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "björη", // Icelandic 'Bear' with Greek 'η' ita
      "mörðυr", // Icelandic 'Marten' with Greek 'υ' upsilon
      "hvίtabjörninn", // Icelandic 'Polar Bear' with Greek 'ί' iοta
  })
  void should_throw_exception_with_non_icelandic_letters(final String value) {
    Assertions.assertThatExceptionOfType(InvalidDataTypeValueException.class)
        .isThrownBy(() -> TYPE_PARSER.parseToString(value))
        .withMessageMatching("Must be made up of Icelandic letters only. Invalid value - invalid character '[^']+'.");
  }

}