package land.artli.easytype;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TypeParser_LanguageHindiTest extends AbstractTypeParserTest {

  static final TypeParser TYPE_PARSER =
      TypeParser.builder(SomeType.class)
          .errorMessage("Must be made up of Hindi letters only.")
          .toCharacterNormalizationFormNFC()
          .acceptLanguage(Language.HINDI_hi)
          .normalizeWhitespace()
          .build();

  @ParameterizedTest
  @ValueSource(strings = {
      "खरगोश", // Hindi 'Rabbit'
      "मधुमक्खी", // Hindi 'Bee'
      "डॉल्फिन", // Hindi 'Dolphin'
      "जिराफ़", // Hindi 'Giraffe
      "उल्लू", // Hindi 'Owl
      "बाघ", // Hindi 'Tiger
      "कबूतर", // Hindi 'Pigeon'
  })
  void should_parse_accepting_only_hindi_letters(final String value) {
    Assertions.assertThat(TYPE_PARSER.parse(value)).hasToString(value);
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "\uD805\uDE13ल्लू", // Hindi 'उल्लू' (Owl) with '𑘓' (ca) from the Modi script instead 'उ' (u) from the Devanagari script
      "बा\uD805\uDE11", // Hindi 'बाघ' (Tiger) with '𑘑' (gha) from the Modi script instead 'घ' (gha) from the Devanagari script
      "कबू\uD805\uDE1Dर", // Hindi 'कबूतर' (Pigeon) with '𑘝' (ta) from the Modi script instead 'त' (ta) from the Devanagari script
  })
  void should_throw_exception_with_non_hindi_letters(final String value) {
    Assertions.assertThatExceptionOfType(InvalidTypeValueException.class)
        .isThrownBy(() -> TYPE_PARSER.parse(value))
        .withMessage("Must be made up of Hindi letters only.");
  }

}