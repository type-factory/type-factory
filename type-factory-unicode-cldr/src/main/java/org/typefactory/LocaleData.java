package org.typefactory;

import static org.typefactory.LocaleDataControl.RESOURCE_BASE_NAME_ORG_TYPEFACTORY_UNICODE_CLDR;
import static org.typefactory.LocaleDataControl.RESOURCE_BUNDLE_CONTROL;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.typefactory.unicode.cldr.AbstractCldrResourceBundle;

public interface LocaleData {

  Subset standardCharactersSubset();

  Subset auxiliaryCharactersSubset();

  Subset punctuationCharactersSubset();

  Subset decimalDigitsSubset();

  static LocaleData getForLocale(final Locale locale) throws MissingResourceException {

    try {
      final var resourceBundle = ResourceBundle.getBundle(
          RESOURCE_BASE_NAME_ORG_TYPEFACTORY_UNICODE_CLDR, locale, RESOURCE_BUNDLE_CONTROL);

      if (resourceBundle instanceof AbstractCldrResourceBundle cldrResourceBundle) {
        return new LocaleDataImpl(
            cldrResourceBundle.getStandardSubset(),
            cldrResourceBundle.getAuxiliarySubset(),
            cldrResourceBundle.getPunctuationSubset(),
            cldrResourceBundle.getDecimalDigitsSubset());
      }
    } catch (final MissingResourceException ignored) {
      // Ignore and throw a new exception below
    }

    throw new MissingResourceException(
        "No LocaleData exists for locale '" + locale.toLanguageTag() + "'",
        resourceBundleClassName(locale),
        "");
  }

  private static String resourceBundleClassName(final Locale locale) {
    return RESOURCE_BASE_NAME_ORG_TYPEFACTORY_UNICODE_CLDR + '.' + locale.toLanguageTag().replaceAll("\\-+", "_");
  }
}

