package org.typefactory.language;

import org.assertj.core.api.Assertions;
import org.typefactory.InvalidValueException;
import org.typefactory.TypeParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TypeParser_LanguageGreekTest extends AbstractTypeParserTest {

  static final TypeParser TYPE_PARSER =
      TypeParser.builder()
          .errorMessage("Must be made up of Greek letters only.")
          .toCharacterNormalizationFormNFC()
          .acceptSubset(Letters.GREEK_el)
          .normalizeWhitespace()
          .build();

  @ParameterizedTest
  @ValueSource(strings = {
      "Αρκούδα", // Greek 'Bear'
      "Καμηλοπάρδαλη", // Greek 'Giraffe'
      "Μαϊμού", // Greek 'Monkey'
  })
  void should_parse_accepting_only_greek_letters(final String value) {
    Assertions.assertThat(TYPE_PARSER.parseToString(value)).hasToString(value);
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "Αρκoύδα", // Greek 'Bear' with English 'o'
      "Kαμηλοπάρδαλη", // Greek 'Giraffe' with English 'K'
      "Mαϊμού", // Greek 'Monkey' with English 'M'
  })
  void should_throw_exception_with_non_greek_letters(final String value) {
    Assertions.assertThatExceptionOfType(InvalidValueException.class)
        .isThrownBy(() -> TYPE_PARSER.parseToString(value))
        .withMessageMatching("Must be made up of Greek letters only. Invalid value - invalid character '[^']+'.");
  }

}