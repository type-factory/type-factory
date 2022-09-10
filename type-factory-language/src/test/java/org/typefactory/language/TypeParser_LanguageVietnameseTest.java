package org.typefactory.language;

import org.assertj.core.api.Assertions;
import org.typefactory.InvalidValueException;
import org.typefactory.TypeParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TypeParser_LanguageVietnameseTest extends AbstractTypeParserTest {

  static final TypeParser TYPE_PARSER =
      TypeParser.builder()
          .errorMessage("Must be made up of Vietnamese letters only.")
          .toCharacterNormalizationFormNFC()
          .acceptSubset(Letters.VIETNAMESE_vi)
          .normalizeWhitespace()
          .build();

  @ParameterizedTest
  @ValueSource(strings = {
      "Con gấu", // Vietnamese 'Bear'
      "Con hươu cao cổ", // Vietnamese 'Giraffe'
      "Con khỉ", // Vietnamese 'Monkey'
  })
  void should_parse_accepting_only_vietnamese_letters(final String value) {
    Assertions.assertThat(TYPE_PARSER.parseToString(value)).hasToString(value);
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "Cοn gấu", // Vietnamese 'Bear' with Greek 'ο' omicron
      "Con hươυ cao cổ", // Vietnamese 'Giraffe' with Greek 'υ' upsilon
      "Con Κhỉ", // Vietnamese 'Monkey' with Greek 'Κ' kappa
  })
  void should_throw_exception_with_non_vietnamese_letters(final String value) {
    Assertions.assertThatExceptionOfType(InvalidValueException.class)
        .isThrownBy(() -> TYPE_PARSER.parseToString(value))
        .withMessageMatching("Must be made up of Vietnamese letters only. Invalid value - invalid character '[^']+'.");
  }

}
