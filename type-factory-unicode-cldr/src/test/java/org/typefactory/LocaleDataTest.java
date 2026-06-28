package org.typefactory;

import static org.typefactory.assertions.TypeFactoryAssertions.assertThat;
import static org.typefactory.assertions.TypeFactoryAssertions.assertThatThrownBy;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.typefactory.unicode.cldr.CldrResourceBundle;
import org.typefactory.unicode.cldr.af;
import org.typefactory.unicode.cldr.ar;
import org.typefactory.unicode.cldr.az;
import org.typefactory.unicode.cldr.az_Cyrl;
import org.typefactory.unicode.cldr.az_Latn;
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
  void getInstance_shouldReturnLocaleDataForGeneratedBundles(
      final String localeTag,
      final Class<? extends CldrResourceBundle> expectedBundleType) {

    final Locale locale = Locale.forLanguageTag(localeTag);

    final LocaleData actual = LocaleData.getForLocale(locale);

    assertThat(actual.standardSubset()).isNotEmpty();
    assertThat(actual.auxiliarySubset()).isNotNull();
    assertThat(actual.punctuationSubset()).isNotNull();
  }

  @ParameterizedTest(name = "unsupported locale {0}")
  @MethodSource("unsupportedLocales")
  void getInstance_shouldThrowForNonGeneratedBundles(final String localeTag) {

    final Locale locale = Locale.forLanguageTag(localeTag);

    synchronized (LocaleDataTest.class) {
      Locale.setDefault(new Locale("zz", "ZZ"));

      assertThatThrownBy(() -> LocaleData.getForLocale(locale))
          .isInstanceOf(MissingResourceException.class)
          .hasMessage("Cannot load locale data for org.typefactory.unicode.cldr." + locale.toLanguageTag());
    }
  }

  static Stream<Arguments> supportedLocales() {
    return Stream.of(
        Arguments.of("af", af.class),
        Arguments.of("ar", ar.class),
        Arguments.of("ar-EG", ar.class),
        Arguments.of("az", az.class),
        Arguments.of("az-Arab", az.class),
        Arguments.of("az-Arab-IQ", az.class),
        Arguments.of("az-Cyrl", az_Cyrl.class),
        Arguments.of("az-Cyrl-AZ", az_Cyrl.class),
        Arguments.of("az-Latn", az_Latn.class),
        Arguments.of("az-Latn-AZ", az_Latn.class),
        Arguments.of("hy", hy.class));
  }

  static Stream<String> unsupportedLocales() {
    return Stream.of(Locale.ROOT.toLanguageTag(), "zz", "zz-ZZ");
  }
}
