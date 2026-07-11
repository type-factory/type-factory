package org.typefactory;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.typefactory.assertions.TypeFactoryAssertions.assertThat;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.typefactory.unicode.cldr.AbstractCldrResourceBundle;
import org.typefactory.unicode.cldr.af;
import org.typefactory.unicode.cldr.ar;
import org.typefactory.unicode.cldr.az;
import org.typefactory.unicode.cldr.az_Arab;
import org.typefactory.unicode.cldr.az_Cyrl;
import org.typefactory.unicode.cldr.hy;

class LocaleDataTest {

  private Locale defaultLocale;

  @BeforeEach
  void rememberDefaultLocale() {
    defaultLocale = Locale.getDefault();
  }

  @AfterEach
  void restoreDefaultLocale() {
    Locale.setDefault(defaultLocale);
  }

  @ParameterizedTest(name = "supported locale {0}")
  @MethodSource("supportedLocales")
  void getForLocale_shouldReturnLocaleDataForGeneratedBundles(
      final String localeTag,
      final Class<? extends AbstractCldrResourceBundle> expectedBundleType) {

    final Locale locale = Locale.forLanguageTag(localeTag);

    final LocaleData actual = LocaleData.getForLocale(locale);

    assertThat(actual.standardCharactersSubset()).isNotEmpty();
    assertThat(actual.auxiliaryCharactersSubset()).isNotNull();
    assertThat(actual.punctuationCharactersSubset()).isNotNull();
    assertThat(actual.decimalDigitsSubset()).isNotNull();
  }

  @ParameterizedTest(name = "unsupported locale {0}")
  @MethodSource("unsupportedLocales")
  void getForLocale_shouldThrowForNonGeneratedBundles(final String localeTag) {

    final var locale = Locale.forLanguageTag(localeTag);
    final var missingResourceBundleClass =
        "org.typefactory.unicode.cldr."  + locale.toLanguageTag().replaceAll("\\-+", "_");

    synchronized (LocaleDataTest.class) {
      Locale.setDefault(new Locale("zz", "ZZ"));

      assertThatExceptionOfType(MissingResourceException.class)
          .isThrownBy(() -> LocaleData.getForLocale(locale))
          .withMessage("No LocaleData exists for locale '" + locale.toLanguageTag() + "'")
          .satisfies(missingResourceException ->
              assertThat(missingResourceException.getClassName()).isEqualTo(missingResourceBundleClass));
    }
  }

  static Stream<Arguments> supportedLocales() {
    return Stream.of(
        Arguments.of("af", af.class),
        Arguments.of("ar", ar.class),
        Arguments.of("ar-EG", ar.class),
        Arguments.of("az", az.class),
        Arguments.of("az-Arab", az.class),
        Arguments.of("az-Arab-IQ", az_Arab.class),
        Arguments.of("az-Cyrl", az_Cyrl.class),
        Arguments.of("az-Cyrl-AZ", az_Cyrl.class),
        Arguments.of("az-Latn", az.class),
        Arguments.of("az-Latn-AZ", az.class),
        Arguments.of("hy", hy.class));
  }

  static Stream<String> unsupportedLocales() {
    return Stream.of(
        Locale.ROOT.toLanguageTag(),
        "zz",
        "zz-ZZ");
  }
}
