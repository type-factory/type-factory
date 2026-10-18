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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

final class CldrExemplarCharactersReader {

  private static final Logger logger = Logger.getLogger(CldrExemplarCharactersReader.class.getName());

  private static final String TYPE_STANDARD = "standard";
  private static final String TYPE_AUXILIARY = "auxiliary";
  private static final String TYPE_PUNCTUATION = "punctuation";

  private CldrExemplarCharactersReader() {
    // don't instantiate me
  }

  static Map<String, CldrExemplarCharacters> readLocaleExemplarCharacters(final InputStream localeXmlInputStream) {
    final Map<String, CldrExemplarCharacters> exemplarCharactersByType = new LinkedHashMap<>();

    if (localeXmlInputStream == null) {
      return exemplarCharactersByType;
    }

    try {
      final var documentBuilder = DocumentBuilderFactory.newInstance();
      documentBuilder.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
      documentBuilder.setExpandEntityReferences(false);
      documentBuilder.setNamespaceAware(false);
      documentBuilder.setXIncludeAware(false);

      final var builder = documentBuilder.newDocumentBuilder();
      builder.setEntityResolver((publicId, systemId) -> new InputSource(new StringReader("")));

      final var document = builder.parse(localeXmlInputStream);
      final NodeList exemplarCharacterNodes = document.getElementsByTagName("exemplarCharacters");

      for (int i = 0; i < exemplarCharacterNodes.getLength(); ++i) {
        final Element exemplarCharacterElement = (Element) exemplarCharacterNodes.item(i);
        final String rawType = exemplarCharacterElement.getAttribute("type");
        final String type = rawType.isBlank() ? TYPE_STANDARD : rawType;
        final String exemplarCharactersText = exemplarCharacterElement.getTextContent();

        switch (type) {
          case TYPE_STANDARD, TYPE_AUXILIARY, TYPE_PUNCTUATION -> exemplarCharactersByType.put(type, parseExemplarCharacters(exemplarCharactersText));
          default -> {
            // Ignore other exemplar character types such as index and numbers.
          }
        }
      }
    } catch (final SAXException | IOException | RuntimeException | javax.xml.parsers.ParserConfigurationException e) {
      logger.log(Level.SEVERE, e, () -> "Cannot read exemplar characters");
      throw new IllegalStateException("Cannot read exemplar characters", e);
    }

    exemplarCharactersByType.putIfAbsent(TYPE_STANDARD, CldrExemplarCharacters.empty());
    exemplarCharactersByType.putIfAbsent(TYPE_AUXILIARY, CldrExemplarCharacters.empty());
    exemplarCharactersByType.putIfAbsent(TYPE_PUNCTUATION, CldrExemplarCharacters.empty());

    return exemplarCharactersByType;
  }

  static Map<String, CldrExemplarCharacters> readLocaleExemplarCharacters(final File localeXmlFile) {
    try (final InputStream inputStream = new java.io.FileInputStream(localeXmlFile)) {
      return readLocaleExemplarCharacters(inputStream);
    } catch (final IOException e) {
      logger.log(Level.SEVERE, e, () -> "Cannot read exemplar characters from " + localeXmlFile);
      throw new IllegalStateException("Cannot read exemplar characters from " + localeXmlFile, e);
    }
  }

  static CldrExemplarCharacters parseExemplarCharacters(final String exemplarCharactersText) {
    if (exemplarCharactersText == null || exemplarCharactersText.isBlank()) {
      return CldrExemplarCharacters.empty();
    }

    String content = exemplarCharactersText.trim();
    if (content.startsWith("[") && content.endsWith("]")) {
      content = content.substring(1, content.length() - 1);
    }

    if (content.isEmpty()) {
      return CldrExemplarCharacters.empty();
    }

    content = StringEscapeUtils.unescapeXml(content);

    final LinkedHashSet<Integer> codePoints = new LinkedHashSet<>();
    final LinkedHashSet<String> strings = new LinkedHashSet<>();

    final int[] contentCodePoints = content.codePoints().toArray();

    for (int i = 0; i < contentCodePoints.length; ++i) {
      boolean escapedCodePoint = false;
      int codePoint = contentCodePoints[i];
      if (codePoint == ' ') {
        continue; // skip whitespace
      }

      if (codePoint == '\\' && (i + 1 < contentCodePoints.length)) {
        final int escapeEndIndex = skipEscape(contentCodePoints, i);
        codePoint = decodeEscapedCodePoint(contentCodePoints, i, escapeEndIndex);
        i = escapeEndIndex - 1; // move to the last character of the escape sequence
        escapedCodePoint = true;
      }

      if (codePoint == '{' && !escapedCodePoint) {
        // Handle string tokens, e.g. {ch}, {ll}, {dz}, {o\:}, {n\-}, {l̃}, {i̇̃}, {\\\-}, {&lt;LRM&gt;}, {&lt;&gt;\\|/#\&amp;} etc.
        boolean cldrExemplarStringTerminated = false;
        final StringBuilder token = new StringBuilder();
        for (++i; cldrExemplarStringTerminated || i < contentCodePoints.length; ++i) {
          if (contentCodePoints[i] == '\\' && i + 1 < contentCodePoints.length) {
            token.appendCodePoint(contentCodePoints[++i]); // skip the escape
          } else if (contentCodePoints[i] == '}') {
            cldrExemplarStringTerminated = true;
            break; // end of token
          } else {
            token.appendCodePoint(contentCodePoints[i]);
          }
        }
        if (!cldrExemplarStringTerminated) {
          throw new IllegalArgumentException("Unterminated CLDR exemplar string: " + exemplarCharactersText);
        }
        addCasedVariants(token.toString(), codePoints, strings);
        continue;
      }

      final boolean nextCodePointIsModifierOrCombiningMark = (i + 1 < contentCodePoints.length) && isCombiningMark(contentCodePoints[i + 1]);
      if (nextCodePointIsModifierOrCombiningMark) {
        final StringBuilder token = new StringBuilder();
        token.appendCodePoint(codePoint);
        while (i + 1 < contentCodePoints.length && isCombiningMark(contentCodePoints[i + 1])) {
          token.appendCodePoint(contentCodePoints[++i]);
        }
        addCasedVariants(token.toString(), codePoints, strings);
        continue;
      }

      final StringBuilder token = new StringBuilder();
      token.appendCodePoint(codePoint);
      addCasedVariants(token.toString(), codePoints, strings);
    }
    return new CldrExemplarCharacters(exemplarCharactersText, List.copyOf(codePoints), List.copyOf(strings));
  }

  static int skipWhitespace(final String content, final int startIndex) {
    int index = startIndex;
    while (index < content.length() && Character.isWhitespace(content.charAt(index))) {
      index++;
    }
    return index;
  }

  static String decodeToken(final String token) {
    final StringBuilder decoded = new StringBuilder();
    for (int index = 0; index < token.length(); ) {
      final char current = token.charAt(index);
      if (current == '\\' && index + 1 < token.length()) {
        final int nextIndex = skipEscape(token, index);
        decoded.appendCodePoint(decodeEscapedCodePoint(token, index, nextIndex));
        index = nextIndex;
        continue;
      }
      final int codePoint = token.codePointAt(index);
      decoded.appendCodePoint(codePoint);
      index += Character.charCount(codePoint);
    }
    return decoded.toString();
  }

  static List<String> splitIntoUnits(final String decodedToken) {
    final List<String> units = new ArrayList<>();
    final StringBuilder currentUnit = new StringBuilder();

    for (int index = 0; index < decodedToken.length(); ) {
      final int codePoint = decodedToken.codePointAt(index);
      if (!currentUnit.isEmpty() && !isCombiningMark(codePoint)) {
        units.add(currentUnit.toString());
        currentUnit.setLength(0);
      }
      currentUnit.appendCodePoint(codePoint);
      index += Character.charCount(codePoint);
    }

    if (!currentUnit.isEmpty()) {
      units.add(currentUnit.toString());
    }
    return units;
  }

  static void addCasedVariants(
      final String value,
      final LinkedHashSet<Integer> codePoints,
      final LinkedHashSet<String> strings) {

    final String lowerCased = value;
    final String upperCased = value.toUpperCase(Locale.ROOT);

    addCasedVariant(lowerCased, codePoints, strings);
    if (!lowerCased.equals(upperCased)) {
      addCasedVariant(upperCased, codePoints, strings);
    }
  }

  static void addCasedVariant(
      final String value,
      final LinkedHashSet<Integer> codePoints,
      final LinkedHashSet<String> strings) {

    if (value.isEmpty()) {
      return;
    }

    final int[] codePointArray = value.codePoints().toArray();
    if (codePointArray.length == 1) {
      codePoints.add(codePointArray[0]);
    } else {
      strings.add(value);
    }
  }

  static boolean isCombiningMarkOrModifierLetter(final int codePoint) {
    return isCombiningMark(codePoint) || isModifierLetter(codePoint);
  }

  static boolean isModifierLetter(final int codePoint) {
    return Character.getType(codePoint) == Character.MODIFIER_LETTER;
  }

  static boolean isCombiningMark(final int codePoint) {
    return switch (Character.getType(codePoint)) {
      case Character.NON_SPACING_MARK,
           Character.COMBINING_SPACING_MARK,
           Character.ENCLOSING_MARK -> true;
      default -> false;
    };
  }

  static int decodeEscapedCodePoint(final String token, final int escapeStart, final int escapeEnd) {
    final char escapeType = token.charAt(escapeStart + 1);
    if (escapeType == 'u' && escapeEnd - escapeStart >= 6) {
      return Integer.parseInt(token.substring(escapeStart + 2, escapeStart + 6), 16);
    }
    if (escapeType == 'U' && escapeEnd - escapeStart >= 10) {
      return Integer.parseInt(token.substring(escapeStart + 2, escapeStart + 10), 16);
    }
    if (escapeType == 'x' && escapeStart + 2 < token.length() && token.charAt(escapeStart + 2) == '{') {
      final int closingBraceIndex = token.indexOf('}', escapeStart + 3);
      if (closingBraceIndex > 0) {
        return Integer.parseInt(token.substring(escapeStart + 3, closingBraceIndex), 16);
      }
    }
    return token.codePointAt(escapeStart + 1);
  }

  static int decodeEscapedCodePoint(final int [] codePoints, final int escapeStart, final int escapeEnd) {
    final int escapeType = codePoints[escapeStart + 1];
    if (escapeType == 'n') {
      return '\n';
    }
    if (escapeType == 'r') {
      return '\r';
    }
    if (escapeType == 't') {
      return '\t';
    }
    if (escapeType == 'u' && escapeEnd - escapeStart >= 6) {
      return Integer.parseInt(new String(codePoints, escapeStart + 2, 4), 16);
    }
    if (escapeType == 'U' && escapeEnd - escapeStart >= 10) {
      return Integer.parseInt(new String(codePoints, escapeStart + 2, 8), 16);
    }
    if (escapeType == 'x' && escapeStart + 2 < codePoints.length && codePoints[escapeStart + 2] == '{') {
      final int closingBraceIndex = ArrayUtils.indexOf(codePoints, '}', escapeStart + 3);
      if (closingBraceIndex > 0) {
        return Integer.parseInt(new String(codePoints, escapeStart + 3, closingBraceIndex - escapeStart - 3), 16);
      }
    }
    return codePoints[escapeStart + 1];
  }

  static int skipEscape(final String token, final int escapeStart) {
    final char escapeType = token.charAt(escapeStart + 1);
    if (escapeType == 'n' || escapeType == 'r' || escapeType == 't') {
      return escapeStart + 2;
    }
    if (escapeType == 'u' && escapeStart + 6 <= token.length()) {
      return escapeStart + 6;
    }
    if (escapeType == 'U' && escapeStart + 10 <= token.length()) {
      return escapeStart + 10;
    }
    if (escapeType == 'x' && escapeStart + 2 < token.length() && token.charAt(escapeStart + 2) == '{') {
      final int closingBraceIndex = token.indexOf('}', escapeStart + 3);
      if (closingBraceIndex > 0) {
        return closingBraceIndex + 1;
      }
    }
    return escapeStart + 2;
  }


  static int skipEscape(final int [] codePoints, final int escapeStart) {
    final int escapeType = codePoints[escapeStart + 1];
    if (escapeType == 'u' && escapeStart + 6 <= codePoints.length) {
      return escapeStart + 6;
    }
    if (escapeType == 'U' && escapeStart + 10 <= codePoints.length) {
      return escapeStart + 10;
    }
    if (escapeType == 'x' && escapeStart + 2 < codePoints.length && codePoints[escapeStart + 2] == '{') {
      final int closingBraceIndex = ArrayUtils.indexOf(codePoints, '}', escapeStart + 3);
      if (closingBraceIndex > 0) {
        return closingBraceIndex + 1;
      }
    }
    return escapeStart + 2;
  }
}
