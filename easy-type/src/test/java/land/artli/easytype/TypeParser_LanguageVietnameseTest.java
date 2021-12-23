package land.artli.easytype;

import land.artli.easytype.AbstractTypeParserTest.SomeType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TypeParser_LanguageVietnameseTest {

  @ParameterizedTest
  @ValueSource(strings = {
      "Con gấu", // Vietnamese 'Bear'
      "Con hươu cao cổ", // Vietnamese 'Giraffe'
      "Con khỉ", // Vietnamese 'Monkey'
  })
  void should_parse_accepting_only_vietnamese_letters(final String value) {

    final TypeParser typeParser =
        TypeParser.builder(SomeType.class)
            .errorMessage("Must be made up of Vietnamese letters only.")
            .toCharacterNormalizationFormNFC()
            .acceptLanguage(Language.LETTERS_VIETNAMESE_VI)
            .normalizeWhitespace()
            .build();
    Assertions.assertThat(typeParser.parse(value)).hasToString(value);
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "Cοn gấu", // Vietnamese 'Bear' with Greek 'ο' omicron
      "Con hươυ cao cổ", // Vietnamese 'Giraffe' with Greek 'υ' upsilon
      "Con Κhỉ", // Vietnamese 'Monkey' with Greek 'Κ' kappa
  })
  void should_throw_exception_with_non_vietnamese_letters(final String value) {

    final TypeParser typeParser =
        TypeParser.builder(SomeType.class)
            .errorMessage("Must be made up of Vietnamese letters only.")
            .toCharacterNormalizationFormNFC()
            .acceptLanguage(Language.LETTERS_VIETNAMESE_VI)
            .normalizeWhitespace()
            .build();

    Assertions.assertThatExceptionOfType(InvalidTypeValueException.class)
        .isThrownBy(() -> typeParser.parse(value))
        .withMessage("Must be made up of Vietnamese letters only.");
  }

}
