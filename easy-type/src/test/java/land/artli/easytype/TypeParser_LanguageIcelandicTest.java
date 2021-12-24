package land.artli.easytype;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TypeParser_LanguageIcelandicTest extends AbstractTypeParserTest {

  static final TypeParser TYPE_PARSER =
      TypeParser.builder(SomeType.class)
          .errorMessage("Must be made up of Icelandic letters only.")
          .toCharacterNormalizationFormNFC()
          .acceptLanguage(Language.LETTERS_ICELANDIC_IS)
          .normalizeWhitespace()
          .build();

  @ParameterizedTest
  @ValueSource(strings = {
      "björn", // Icelandic 'Bear'
      "mörður", // Icelandic 'Marten'
      "hvítabjörninn", // Icelandic 'Polar Bear'
  })
  void should_parse_accepting_only_icelandic_letters(final String value) {
    Assertions.assertThat(TYPE_PARSER.parse(value)).hasToString(value);
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "björη", // Icelandic 'Bear' with Greek 'η' ita
      "mörðυr", // Icelandic 'Marten' with Greek 'υ' upsilon
      "hvίtabjörninn", // Icelandic 'Polar Bear' with Greek 'ί' iοta
  })
  void should_throw_exception_with_non_icelandic_letters(final String value) {
    Assertions.assertThatExceptionOfType(InvalidTypeValueException.class)
        .isThrownBy(() -> TYPE_PARSER.parse(value))
        .withMessage("Must be made up of Icelandic letters only.");
  }

}
