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
import java.io.InputStream;
import java.io.StringReader;
import java.net.JarURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import org.typefactory.unicode.cldr.generator.unicodedata.UnicodeGroupData;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

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

  static List<String> getCldrLocaleXmlFilePaths() {
    final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    final URL cldrMainResourceDirectory = classLoader.getResource(CLDR_MAIN_RESOURCE_DIRECTORY);
    if (cldrMainResourceDirectory == null) {
      logger.warning(() -> "Cannot locate CLDR resource directory " + CLDR_MAIN_RESOURCE_DIRECTORY);
      return List.of();
    }

    try {
      return switch (cldrMainResourceDirectory.getProtocol()) {
        case "file" -> {
          final Path cldrMainResourceDirectoryPath = Path.of(cldrMainResourceDirectory.toURI());
          try (var localeXmlFileStream = Files.list(cldrMainResourceDirectoryPath)) {
            yield localeXmlFileStream
                .filter(path -> path.getFileName().toString().endsWith(".xml"))
                .map(path -> CLDR_MAIN_RESOURCE_DIRECTORY + "/" + cldrMainResourceDirectoryPath.relativize(path))
                .sorted()
                .toList();
          }
        }
        case "jar" -> {
          final JarURLConnection connection = (JarURLConnection) cldrMainResourceDirectory.openConnection();
          final String entryPrefix = connection.getEntryName().endsWith("/")
              ? connection.getEntryName()
              : connection.getEntryName() + "/";

          try (JarFile jarFile = connection.getJarFile()) {
            yield jarFile.stream()
                .map(ZipEntry::getName)
                .filter(entryName -> entryName.startsWith(entryPrefix))
                .filter(entryName -> !entryName.equals(entryPrefix))
                .filter(entryName -> entryName.endsWith(".xml"))
                .filter(entryName -> !entryName.substring(entryPrefix.length()).contains("/"))
                .sorted()
                .toList();
          }
        }
        default -> {
          logger.warning(() -> "Unsupported CLDR resource protocol " + cldrMainResourceDirectory.getProtocol());
          yield List.of();
        }
      };
    } catch (final Exception e) {
      logger.severe(() -> "Cannot read CLDR locale list from resource directory " + CLDR_MAIN_RESOURCE_DIRECTORY);
      throw new RuntimeException(e);
    }
  }

  static Document parseCldrLocaleXmlDocument(final InputStream xmlInputStream) {
    try {
      final var documentBuilder = DocumentBuilderFactory.newInstance();
      documentBuilder.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
      documentBuilder.setExpandEntityReferences(false);
      documentBuilder.setNamespaceAware(false);
      documentBuilder.setXIncludeAware(false);

      final var builder = documentBuilder.newDocumentBuilder();
      builder.setEntityResolver((publicId, systemId) -> new InputSource(new StringReader("")));

      return builder.parse(xmlInputStream);
    } catch (final SAXException | IOException | RuntimeException | javax.xml.parsers.ParserConfigurationException e) {
      logger.log(Level.SEVERE, e, () -> "Cannot read exemplar characters");
      throw new IllegalStateException("Cannot read exemplar characters", e);
    }
  }

  static CldrLocaleXmlDocument getCldrLocaleXmlDocument(final String resourceName) {
    final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    try (final InputStream inputStream = classLoader.getResourceAsStream(resourceName)) {
      if (inputStream == null) {
        throw new IllegalStateException("Cannot load CLDR locale resource from resource name " + resourceName);
      }
      final var document = parseCldrLocaleXmlDocument(inputStream);
      return new CldrLocaleXmlDocument(resourceName, document);
    } catch (final Exception e) {
      logger.severe(() -> "Cannot load CLDR locale resource from resource name " + resourceName);
      throw new RuntimeException(e);
    }
  }

  public static boolean isIso639Language(final Locale locale) {
    try {
      // TODO: The ISO-639 list has the 2-letter languages. Should we also consider 3-letter languages?

      // We're using Java's Locale built-in ISO-639 locale knowledge to help us determine
      // whether we want to process this locale.
      final String language = locale.getLanguage();
      return !language.isEmpty() && ISO_639_LANGUAGES.contains(locale.getLanguage());

    } catch (final Exception e) {
      return false;
    }
  }

  public void generateLanguageClass() {
    final List<String> cldrLocaleXmlFilePaths = getCldrLocaleXmlFilePaths();
    for (final String resourceName : cldrLocaleXmlFilePaths) {

      final CldrLocaleXmlDocument cldrLocaleXmlDocument = getCldrLocaleXmlDocument(resourceName);
      final Locale locale = cldrLocaleXmlDocument.getLocale();

      if (!isIso639Language(locale) && !cldrLocaleXmlDocument.isRootCldrResource()) {
        continue;
      }

      if (cldrLocaleXmlDocument.getStandardExemplarCharacters().isEmpty()
          && cldrLocaleXmlDocument.getAuxiliaryExemplarCharacters().isEmpty()
          && cldrLocaleXmlDocument.getPunctuationExemplarCharacters().isEmpty()
          && cldrLocaleXmlDocument.isNotForLocale(CldrLocaleXmlDocument.NORWEGIAN_BOKMAL_LOCALE)
          && cldrLocaleXmlDocument.isNotForLocale(CldrLocaleXmlDocument.NORWEGIAN_NYNORSK_LOCALE)) {
        continue;
      }

//      final String localeScript = locale.getScript();
//      if ("Hani".equalsIgnoreCase(localeScript)) {
//        createAlphabetCharactersTxt(locale, cldrLocaleXmlDocument.getStandardExemplarCharacters());
//      }

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
