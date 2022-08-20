package org.datatypeproject;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class TypeParser_LanguageJapaneseJinmeiyoTest extends AbstractTypeParserTest {

  static final TypeParser TYPE_PARSER =
      TypeParser.builder(SomeType.class)
          .errorMessage("Must be made up of Japanese Jinmeiyō letters only.")
          .preserveCharacterNormalizationForm()
          .acceptSubset(Letters.JAPANESE_ja_Hani_x_jinmeiyo)
          .normalizeWhitespace()
          .build();

  @ParameterizedTest
  @CsvFileSource(
      resources = "/japanese-jinmeiyo.psv",
      delimiter = '|',
      numLinesToSkip = 1)
  void should_parse_accepting_only_japanese_jinmeiyo_characters(final int unicode, final String value) {
    Assertions.assertThat(TYPE_PARSER.parseToString(new String(new int[]{unicode}, 0, 1))).hasToString(value);
  }

}
