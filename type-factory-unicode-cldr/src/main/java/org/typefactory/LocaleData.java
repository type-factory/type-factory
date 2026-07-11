package org.typefactory;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.typefactory.unicode.cldr.AbstractCldrResourceBundle;

public interface LocaleData {

  String BASE_NAME_ORG_TYPEFACTORY_UNICODE_CLDR = "org.typefactory.unicode.cldr";

  Subset standardCharactersSubset();

  Subset auxiliaryCharactersSubset();

  Subset punctuationCharactersSubset();

  Subset decimalDigitsSubset();

  static LocaleData getForLocale(final Locale locale) throws MissingResourceException {

    try {
      final var resourceBundle = ResourceBundle.getBundle(BASE_NAME_ORG_TYPEFACTORY_UNICODE_CLDR, locale, new LocaleDataControl());

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

    final var className = BASE_NAME_ORG_TYPEFACTORY_UNICODE_CLDR + '.' + locale.toLanguageTag().replaceAll("\\-+", "_");

    throw new MissingResourceException("No LocaleData exists for locale '" + locale.toLanguageTag() + "'", className, "");
  }
}

