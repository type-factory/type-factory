package land.artli.easytype;

import land.artli.easytype.AbstractTypeParserTest.SomeType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TypeParser_LanguageAzeriTest {

  @ParameterizedTest
  @ValueSource(strings = {
      "eşşək", // Azeri 'Donkey'
      "kəpənək", // Azeri 'Butterfly'
      "zürafə", // Azeri 'Girrafe'
  })
  void should_parse_accepting_only_azeri_latin_letters(final String value) {

    final TypeParser typeParser =
        TypeParser.builder(SomeType.class)
            .errorMessage("Must be made up of Azeri Latin letters only.")
            .toCharacterNormalizationFormNFC()
            .acceptLanguage(Language.LETTERS_AZERI_AZ_LATN)
            .normalizeWhitespace()
            .build();
    Assertions.assertThat(typeParser.parse(value)).hasToString(value);
  }


}
