/*
   Copyright 2021-2022 Evan Toliopoulos (typefactory.org)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package org.typefactory.unicode.cldr.generator.letters;

import com.ibm.icu.text.UnicodeSet;
import com.ibm.icu.text.UnicodeSet.EntryRange;
import com.ibm.icu.util.LocaleData;
import com.ibm.icu.util.ULocale;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.typefactory.unicode.cldr.generator.unicodedata.UnicodeGroupData;

public class UnicodeCldrResourceBundleClassGenerator {

  private static final Logger logger = Logger.getLogger(UnicodeCldrResourceBundleClassGenerator.class.getName());

  private final File outputDirectory;
  private final LocaleDataResourceBundleCodeGenerator localeDataResourceBundleCodeGenerator;

  public UnicodeCldrResourceBundleClassGenerator(
      final String licenseHeader,
      final File outputDirectory,
      final UnicodeGroupData unicodeGroupData) {
    this.outputDirectory = outputDirectory;
    this.localeDataResourceBundleCodeGenerator =
        new LocaleDataResourceBundleCodeGenerator(licenseHeader, outputDirectory);
  }

  public static Set<ULocale> getLivingLanguageLocales() {
    final var baseLocales = new TreeSet<>(Comparator.comparing(ULocale::toString));
    final var availableLocales = ULocale.getAvailableLocales();

    for (ULocale locale : availableLocales) {
      if (isLivingLanguage(locale) && locale.getCountry().isEmpty()) {
        baseLocales.add(locale);
      }
    }
    return baseLocales;
  }

  public static boolean isLivingLanguage(final ULocale uLocale) {
    try {
      // Java's Locale built-in structure helps verify its recognition as an active linguistic standard
      final String iso3Language = uLocale.getISO3Language();

      // Languages without recognized 3-letter codes in modern Java are typically extinct/historical
      return iso3Language != null && !iso3Language.isEmpty();

    } catch (final Exception e) {
      // An exception or missing ISO3 mapping heavily implies it's a non-living (e.g., historical) tag
      return false;
    }
  }

  public void generateLanguageClass() {
    final var locales = getLivingLanguageLocales();

    for (var locale : locales) {
      final String localeScript = locale.getScript();

      final UnicodeSet standardCharactersUnicodeSet =
          LocaleData.getExemplarSet(locale, UnicodeSet.ADD_CASE_MAPPINGS, LocaleData.ES_STANDARD);

      final UnicodeSet auxiliaryCharactersUnicodeSet =
          LocaleData.getExemplarSet(locale, UnicodeSet.ADD_CASE_MAPPINGS, LocaleData.ES_AUXILIARY);

      final UnicodeSet punctuationCharactersUnicodeSet =
          LocaleData.getExemplarSet(locale, UnicodeSet.ADD_CASE_MAPPINGS, LocaleData.ES_PUNCTUATION);

      if ("Hani".equalsIgnoreCase(localeScript)) {
        createAlphabetCharactersTxt(locale, standardCharactersUnicodeSet);
      }

      localeDataResourceBundleCodeGenerator.generateLocaleDataResourceBundleClass(
          locale,
          standardCharactersUnicodeSet,
          auxiliaryCharactersUnicodeSet,
          punctuationCharactersUnicodeSet);
    }
  }

  private void createAlphabetCharactersTxt(
      final ULocale locale,
      final UnicodeSet unicodeSet) {

    final String headerLine = "===================================================================================================";

    final StringBuilder s = new StringBuilder();

    if (unicodeSet == null || unicodeSet.isEmpty()) {
      return;
    }

    final var localeDisplayLanguage = locale.getDisplayLanguage().toUpperCase().replaceAll("\\W+", "_");
    final var localeLanguageTag = locale.toLanguageTag().replaceAll("\\W+", "_");
    final var localeName = String.format("%s_%s", localeDisplayLanguage, localeLanguageTag);

    int headingStart = s.length();
    s.append(localeName);
    s.append(System.lineSeparator()).append(headerLine, 0, s.length() - headingStart);
    s.append(System.lineSeparator()).append(System.lineSeparator());

    headingStart = s.length();
    s.append("Characters include in the ").append(localeName).append(" ");
    s.append(localeLanguageTag).append(" set");
    s.append(System.lineSeparator()).append(headerLine, 0, s.length() - headingStart);

    s.append(System.lineSeparator()).append(System.lineSeparator());
    s.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    for (EntryRange range : unicodeSet.ranges()) {
      final int from = range.codepoint;
      final int to = range.codepointEnd;
      if (to > from) {
        for (int c = from, i = 0; c <= to; ++c, ++i) {
          if (i % 32 == 0) {
            s.append(System.lineSeparator());
            s.append(String.format("%06x..%06x  ", c, Math.min(to, c + 31)));
          }
          s.append(' ').appendCodePoint(c);
        }
      } else {
        s.append(System.lineSeparator());
        s.append(String.format("%06x          ", from));
        s.append(' ').appendCodePoint(from);
      }
    }

    s.append(System.lineSeparator()).append(System.lineSeparator());
    s.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    s.append(System.lineSeparator()).append(System.lineSeparator());

    final File docFilesDirectory = new File(outputDirectory + File.separator + "doc-files");
    final File filePath = new File(docFilesDirectory + File.separator + localeName + ".txt");
    docFilesDirectory.mkdirs();
    try (final FileWriter fileWriter = new FileWriter(filePath)) {
      fileWriter.append(s.toString());
      fileWriter.flush();
    } catch (final IOException e) {
      logger.log(Level.SEVERE, e, () -> "Cannot write file to " + filePath);
    }
  }
}
