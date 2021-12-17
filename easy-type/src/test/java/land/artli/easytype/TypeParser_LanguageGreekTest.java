package land.artli.easytype;

import land.artli.easytype.AbstractTypeParserTest.SomeType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TypeParser_LanguageGreekTest {

  @ParameterizedTest
  @ValueSource(strings = {
      "Αρκούδα", // Greek 'Bear'
      "Καμηλοπάρδαλη", // Greek 'Giraffe'
      "Μαϊμού", // Greek 'Monkey'
  })
  void should_parse_accepting_only_greek_letters(final String value) {

    final TypeParser typeParser =
        TypeParser.builder(SomeType.class)
            .errorMessage("Must be made up of Greek letters only.")
            .toCharacterNormalizationFormNFC()
            .acceptLanguage(Language.LETTERS_GREEK_EL)
            .normalizeWhitespace()
            .build();
    Assertions.assertThat(typeParser.parse(value)).hasToString(value);
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "Αρκoύδα", // Greek 'Bear' with English 'o'
      "Kαμηλοπάρδαλη", // Greek 'Giraffe' with English 'K'
      "Mαϊμού", // Greek 'Monkey' with English 'M'
  })
  void should_throw_exception_with_non_greek_letters(final String value) {

    final TypeParser typeParser =
        TypeParser.builder(SomeType.class)
            .errorMessage("Must be made up of Greek letters only.")
            .toCharacterNormalizationFormNFC()
            .acceptLanguage(Language.LETTERS_GREEK_EL)
            .normalizeWhitespace()
            .build();

    Assertions.assertThatExceptionOfType(InvalidTypeValueException.class)
        .isThrownBy(() -> typeParser.parse(value))
        .withMessage("Must be made up of Greek letters only.");
  }

}
