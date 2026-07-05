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
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.typefactory.unicode.cldr.generator.unicodedata.UnicodeGroupData;

public class UnicodeCldrHelper {

  private static final Logger logger = Logger.getLogger(UnicodeCldrHelper.class.getName());

  private static final String CLDR_MAIN_RESOURCE_DIRECTORY = "cldr-common/common/main";
  private static final Set<String> ISO_639_LANGUAGES = Set.of(Locale.getISOLanguages());

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

  static List<Path> getCldrLocaleXmlFilePaths() {

    final URL cldrMainResourceDirectory = Thread.currentThread().getContextClassLoader().getResource(CLDR_MAIN_RESOURCE_DIRECTORY);
    if (cldrMainResourceDirectory == null) {
      logger.warning(() -> "Cannot locate CLDR resource directory " + CLDR_MAIN_RESOURCE_DIRECTORY);
      return List.of();
    }

    try {
      final Path cldrMainResourceDirectoryPath = Path.of(cldrMainResourceDirectory.toURI());
      try (var localeXmlFileStream = Files.list(cldrMainResourceDirectoryPath)) {
        return localeXmlFileStream
            .filter(path -> path.getFileName().toString().endsWith(".xml"))
            .toList();
      }
    } catch (final Exception e) {
      logger.severe(() -> "Cannot read CLDR locale list from resource directory " + CLDR_MAIN_RESOURCE_DIRECTORY);
      throw new RuntimeException(e);
    }
  }

  static CldrLocaleXmlDocument getCldrLocaleParsedXmlDocument(final Path path) {
    try (final var inputStream = path.toFile().toURI().toURL().openStream()) {
      return new CldrLocaleXmlDocument(inputStream);
    } catch (final Exception e) {
      logger.severe(() -> "Cannot load CLDR locale resource from path " + path);
      throw new RuntimeException(e);
    }
  }

  public static boolean isIso639Language(final Locale locale) {
    try {
      // We're using Java's Locale built-in ISO-639 locale knowledge to help us determine
      // whether we want to process this locale.
      final String language = locale.getLanguage();
      return !language.isEmpty() && ISO_639_LANGUAGES.contains(locale.getLanguage());

    } catch (final Exception e) {
      return false;
    }
  }

  public void generateLanguageClass() {
    final List<Path> cldrLocaleXmlFilePaths = getCldrLocaleXmlFilePaths();
    for (final Path path : cldrLocaleXmlFilePaths) {

      final CldrLocaleXmlDocument cldrLocaleXmlDocument = getCldrLocaleParsedXmlDocument(path);
      final Locale locale = cldrLocaleXmlDocument.getLocale();

      if (!isIso639Language(locale)) {
        continue;
      }

      if (cldrLocaleXmlDocument.getStandardExemplarCharacters().isEmpty()
          && cldrLocaleXmlDocument.getAuxiliaryExemplarCharacters().isEmpty()
          && cldrLocaleXmlDocument.getPunctuationExemplarCharacters().isEmpty()) {
        continue;
      }

      final String localeScript = locale.getScript();
      if ("Hani".equalsIgnoreCase(localeScript)) {
        createAlphabetCharactersTxt(locale, cldrLocaleXmlDocument.getStandardExemplarCharacters());
      }

      cldrResourceBundleClassGenerator.generateLocaleDataResourceBundleClass(cldrLocaleXmlDocument);
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

    for (int i = 0; i < exemplarCharacters.codePoints().size(); ++i) {
      final int codePoint = exemplarCharacters.codePoints().get(i);
      if (i % 32 == 0) {
        s.append(System.lineSeparator());
        s.append(String.format("%06x  ", codePoint));
      }
      s.append(' ').appendCodePoint(codePoint);
    }

    if (!exemplarCharacters.strings().isEmpty()) {
      s.append(System.lineSeparator()).append(System.lineSeparator());
      s.append("Strings").append(System.lineSeparator());
      for (String string : exemplarCharacters.strings()) {
        s.append(string).append(System.lineSeparator());
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
