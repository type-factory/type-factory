package land.artli.easytype;

import land.artli.easytype.AbstractTypeParserTest.SomeType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TypeParser_LanguageArabicTest {

  @ParameterizedTest
  @CsvSource(value = {
      "جاموس", // Arabic 'Buffalo'
      "حصان", // Arabic 'Horse'
      "قنديل البحر", // Arabic 'Jellyfish'
  }, delimiter = '|')
  void should_parse_accepting_only_arabic_letters(final String value) {

    final TypeParser typeParser =
        TypeParser.builder(SomeType.class)
            .errorMessage("Must be made up of Arabic letters only.")
            .acceptLanguage(Language.LETTERS_ARABIC)
            .normalizeWhitespace()
            .build();
    Assertions.assertThat(typeParser.parse(value)).hasToString(value);
  }


}
