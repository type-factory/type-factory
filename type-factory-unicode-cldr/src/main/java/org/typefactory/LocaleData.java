package org.typefactory;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.typefactory.unicode.cldr.AbstractCldrResourceBundle;

public interface LocaleData {

  Subset standardSubset();

  Subset auxiliarySubset();

  Subset punctuationSubset();

  static LocaleData getForLocale(final Locale locale) throws MissingResourceException {

    try {
      final var resourceBundle = ResourceBundle.getBundle("org.typefactory.unicode.cldr", locale, new LocaleDataControl());

      if (resourceBundle instanceof AbstractCldrResourceBundle cldrResourceBundle) {
        return new LocaleDataImpl(
            cldrResourceBundle.getStandardSubset(),
            cldrResourceBundle.getAuxiliarySubset(),
            cldrResourceBundle.getPunctuationSubset());
      }
    } catch (final MissingResourceException ignored) {
      // Ignore and throw a new exception below
    }

    throw new MissingResourceException(
        "Cannot load locale data for org.typefactory.unicode.cldr." + locale.toLanguageTag(),
        "org.typefactory.unicode.cldr." + locale.toLanguageTag(), "");
  }
}

