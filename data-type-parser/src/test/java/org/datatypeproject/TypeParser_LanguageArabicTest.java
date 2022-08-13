package org.datatypeproject;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TypeParser_LanguageArabicTest extends AbstractTypeParserTest {

  @ParameterizedTest
  @ValueSource(strings = {
      "جاموس", // Arabic 'Buffalo'
      "حصان", // Arabic 'Horse'
      "قنديل البحر", // Arabic 'Jellyfish'
  })
  void should_parse_accepting_only_arabic_letters(final String value) {

    final TypeParser typeParser =
        TypeParser.builder(SomeType.class)
            .errorMessage("Must be made up of Arabic letters only.")
            .toCharacterNormalizationFormNFC()
            .acceptSubset(Letters.ARABIC_ar)
            .normalizeWhitespace()
            .build();
    Assertions.assertThat(typeParser.parseToString(value)).hasToString(value);
  }


}
