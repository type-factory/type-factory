package land.artli.easytype;

import land.artli.easytype.AbstractTypeParserTest.SomeType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TypeParser_LanguageArmenianTest {

  @ParameterizedTest
  @ValueSource(strings = {
      "ռնգեղջյուր", // Armenian 'rhinoceros'
      "թիթեռ", // Armenian 'butterfly'
      "կապիկ", // Armenian 'monkey'
  })
  void should_parse_accepting_only_armenian_letters(final String value) {

    final TypeParser typeParser =
        TypeParser.builder(SomeType.class)
            .errorMessage("Must be made up of Armenian letters only.")
            .toCharacterNormalizationFormNFC()
            .acceptLanguage(Language.LETTERS_ARMENIAN_HY)
            .normalizeWhitespace()
            .build();
    Assertions.assertThat(typeParser.parse(value)).hasToString(value);
  }


}