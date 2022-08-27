package org.datatypeproject.language;

import org.assertj.core.api.Assertions;
import org.datatypeproject.language.Letters;
import org.datatypeproject.TypeParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TypeParser_LanguageArmenianTest extends AbstractTypeParserTest {

  @ParameterizedTest
  @ValueSource(strings = {
      "ռնգեղջյուր", // Armenian 'rhinoceros'
      "թիթեռ", // Armenian 'butterfly'
      "կապիկ", // Armenian 'monkey'
  })
  void should_parse_accepting_only_armenian_letters(final String value) {

    final TypeParser typeParser =
        TypeParser.builder()
            .errorMessage("Must be made up of Armenian letters only.")
            .toCharacterNormalizationFormNFC()
            .acceptSubset(Letters.ARMENIAN_hy)
            .normalizeWhitespace()
            .build();
    Assertions.assertThat(typeParser.parseToString(value)).hasToString(value);
  }


}
