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
package org.typefactory.unicode.cldr.generator.unicode.cldr;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.JarURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class UnicodeCldrHelper {

  private static final Logger logger = Logger.getLogger(UnicodeCldrHelper.class.getName());

  private static final String CLDR_MAIN_RESOURCE_DIRECTORY = "cldr-common/common/main";

  private UnicodeCldrHelper() {
  }

  public static List<String> getCldrLocaleXmlFilePaths() {
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

  public static Document parseCldrLocaleXmlDocument(final InputStream xmlInputStream) {
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

  public static CldrLocaleXmlDocument getCldrLocaleXmlDocument(final String resourceName) {
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
}
