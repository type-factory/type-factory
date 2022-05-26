package land.artli.easytype;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TypeParser_LanguageFrenchTest extends AbstractTypeParserTest {

  static final TypeParser TYPE_PARSER = TypeParser.builder(SomeType.class)
      .errorMessage("Must be made up of French letters only.")
      .toCharacterNormalizationFormNFC()
      .acceptLanguage(Language.FRENCH_fr)
      .convertAllDashesToHyphen()
      .acceptChar('\'')
      .normalizeWhitespace()
      .build();

  @ParameterizedTest
  @ValueSource(strings = {
      "l'huître", // French 'Oyster'
      "le porc-épic", // French 'Porcupine'
      "le léopard", // French 'Leopard'
  })
  void should_parse_accepting_only_french_letters(final String value) {
    Assertions.assertThat(TYPE_PARSER.parseToString(value)).hasToString(value);
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "l'hυître", // French 'Oyster' with Greek 'υ' upsilon
      "le pοrc-épic", // French 'Porcupine' with Greek 'ο' omicron
      "le léoρard", // French 'Leopard' with Greek 'ρ' rho
  })
  void should_throw_exception_with_non_french_letters(final String value) {
    Assertions.assertThatExceptionOfType(InvalidTypeValueException.class)
        .isThrownBy(() -> TYPE_PARSER.parseToString(value))
        .withMessage("Must be made up of French letters only.");
  }

}
