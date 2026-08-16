/*
 * Copyright © 2021-2026 Evan Toliopoulos (typefactory.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

