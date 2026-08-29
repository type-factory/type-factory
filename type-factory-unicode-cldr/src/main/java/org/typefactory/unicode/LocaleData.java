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
package org.typefactory.unicode;

import static org.typefactory.unicode.LocaleDataControl.RESOURCE_BASE_NAME_ORG_TYPEFACTORY_UNICODE_CLDR;
import static org.typefactory.unicode.LocaleDataControl.RESOURCE_BUNDLE_CONTROL;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.typefactory.Subset;
import org.typefactory.unicode.cldr.AbstractCldrResourceBundle;

/**
 * <p>Provides access to locale-specific Unicode character subsets based on the CLDR (Common Locale Data Repository) data.</p>
 *
 * <p>This interface defines methods to retrieve the following character subsets for a given locale.</p>
 *
 * <ul>
 *   <li>Standard characters subset &ndash; the standard set of alphabet characters, or set of ideographs, for a language.</li>
 *   <li>Auxiliary characters subset &ndash; the set of characters that are not part of the standard alphabet but are used in the language, usually for loan words using the same script.</li>
 *   <li>Punctuation characters subset &ndash; the set of characters used for punctuation in the language.</li>
 *   <li>Decimal digits subset &ndash; the set of characters used for decimal digit numerals in the language.</li>
 * </ul>
 *
 * <p>To retrieve the character subsets for a specific locale, use the {@link LocaleData#getForLocale(Locale)} method:</p>
 *
 * <pre>{@code
 * LocaleData localeData = LocaleData.getForLocale(Locale.FRENCH);
 *
 * Subset standardSubset = localeData.standardCharactersSubset();
 * Subset auxiliarySubset = localeData.auxiliaryCharactersSubset();
 * Subset punctuationSubset = localeData.punctuationCharactersSubset();
 * Subset decimalDigitsSubset = localeData.decimalDigitsSubset();
 * }</pre>
 */
public interface LocaleData {

  /**
   * <p>Returns the standard set of alphabet characters, or set of ideographs, for the locale language. Defined in the Unicode CLDR
   * {@code <exemplarCharacters>} element for the locale.</p>
   *
   * @return the standard set of alphabet characters, or set of ideographs, for the locale language
   */
  Subset standardCharactersSubset();

  /**
   * <p>Returns the set of characters that are not part of the standard alphabet but are used in the locale language, usually for loan words using the same script. Defined in the Unicode CLDR
   * {@code <exemplarCharacters type="auxiliary">} element for the locale.</p>
   *
   * @return the set of characters that are not part of the standard alphabet but are used in the locale language
   */
  Subset auxiliaryCharactersSubset();

  /**
   * <p>Returns the set of characters used for punctuation in the locale language. Defined in the Unicode CLDR {@code <exemplarCharacters type="punctuation">} element for the locale.</p>
   *
   * @return the set of characters used for punctuation in the locale language
   */
  Subset punctuationCharactersSubset();

  /**
   * <p>Returns the set of characters used for decimal digits in the locale language. Defined in the Unicode CLDR {@code <exemplarCharacters type="numbers">} element for the locale.</p>
   *
   * <p>The {@code <exemplarCharacters type="numbers">} element contains more than this method returns as this method returns just the decimal digits.</p>
   *
   * @return the set of characters used for decimal digit numerals in the locale language
   */
  Subset decimalDigitsSubset();

  /**
   * <p>Returns the {@code LocaleData} instance for the specified {@code Locale}.</p>
   *
   * @param locale the {@code Locale} for which to retrieve the {@code LocaleData}
   * @return the {@code LocaleData} for the specified {@code Locale}
   * @throws MissingResourceException if no {@code LocaleData} exists for the specified {@code Locale}
   */
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

