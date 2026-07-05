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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.typefactory.unicode.cldr.generator.unicodedata.UnicodeGroupData;

public class UnicodeCldrHelper {

  private static final Logger logger = Logger.getLogger(UnicodeCldrHelper.class.getName());

  private final File outputDirectory;
  private final CldrResourceBundleClassGenerator cldrResourceBundleClassGenerator;

  public UnicodeCldrHelper(
      final String licenseHeader,
      final File outputDirectory,
      final UnicodeGroupData unicodeGroupData) {
    this.outputDirectory = outputDirectory;
    this.cldrResourceBundleClassGenerator =
        new CldrResourceBundleClassGenerator(licenseHeader, outputDirectory);
  }

  static Set<Locale> getLivingLanguageLocales(final File cldrMainDirectory) {
    final var baseLocales = new TreeSet<>(Comparator.comparing(Locale::toLanguageTag));

    final File[] localeXmlFiles = cldrMainDirectory.listFiles((dir, name) -> name.endsWith(".xml"));
    if (localeXmlFiles == null) {
      return baseLocales;
    }

    for (final File localeXmlFile : localeXmlFiles) {
      final String localeFileName = localeXmlFile.getName();
      final String localeLanguageTag = localeFileName.substring(0, localeFileName.length() - 4).replace('_', '-');
      final Locale locale = Locale.forLanguageTag(localeLanguageTag);
      if (isLivingLanguage(locale) && locale.getCountry().isEmpty()) {
        baseLocales.add(locale);
      }
    }
    return baseLocales;
  }

  public static boolean isLivingLanguage(final Locale locale) {
    try {
      // Java's Locale built-in structure helps verify its recognition as an active linguistic standard
      final String iso3Language = locale.getISO3Language();

      // Languages without recognized 3-letter codes in modern Java are typically extinct/historical
      return iso3Language != null && !iso3Language.isEmpty();

    } catch (final Exception e) {
      // An exception or missing ISO3 mapping heavily implies it's a non-living (e.g., historical) tag
      return false;
    }
  }

  public void generateLanguageClass() {
    final File cldrMainDirectory = new File(Path.of("target", "classes", "cldr-common", "common", "main").toString());
    final Set<Locale> locales = getLivingLanguageLocales(cldrMainDirectory);

    for (final Locale locale : locales) {
      final String localeScript = locale.getScript();

      final File localeXmlFile = new File(cldrMainDirectory, locale.toString() + ".xml");
      final Map<String, CldrExemplarCharacters> exemplarCharactersByType =
          CldrExemplarCharactersReader.readLocaleExemplarCharacters(localeXmlFile);

      final CldrExemplarCharacters standardCharacters =
          exemplarCharactersByType.getOrDefault("standard", CldrExemplarCharacters.empty());
      final CldrExemplarCharacters auxiliaryCharacters =
          exemplarCharactersByType.getOrDefault("auxiliary", CldrExemplarCharacters.empty());
      final CldrExemplarCharacters punctuationCharacters =
          exemplarCharactersByType.getOrDefault("punctuation", CldrExemplarCharacters.empty());

      if ("Hani".equalsIgnoreCase(localeScript)) {
        createAlphabetCharactersTxt(locale, standardCharacters);
      }

      cldrResourceBundleClassGenerator.generateLocaleDataResourceBundleClass(
          locale,
          standardCharacters,
          auxiliaryCharacters,
          punctuationCharacters);
    }
  }

  private void createAlphabetCharactersTxt(
      final Locale locale,
      final CldrExemplarCharacters exemplarCharacters) {

    final String headerLine = "===================================================================================================";

    final StringBuilder s = new StringBuilder();

    if (exemplarCharacters == null || exemplarCharacters.isEmpty()) {
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

    for (CldrExemplarCharacters.Range range : exemplarCharacters.ranges()) {
      final int from = range.inclusiveFrom();
      final int to = range.inclusiveTo();
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
