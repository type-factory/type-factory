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
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
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

  static Map<String, CldrExemplarCharacters> readLocaleExemplarCharacters(final File localeXmlFile) {
    final Map<String, CldrExemplarCharacters> exemplarCharactersByType = new LinkedHashMap<>();

    try {
      final var documentBuilder = DocumentBuilderFactory.newInstance();
      documentBuilder.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
      documentBuilder.setExpandEntityReferences(false);
      documentBuilder.setNamespaceAware(false);
      documentBuilder.setXIncludeAware(false);

      final var builder = documentBuilder.newDocumentBuilder();
      builder.setEntityResolver((publicId, systemId) -> new InputSource(new StringReader("")));

      final var document = builder.parse(localeXmlFile);
      final NodeList exemplarCharacterNodes = document.getElementsByTagName("exemplarCharacters");

      for (int i = 0; i < exemplarCharacterNodes.getLength(); ++i) {
        final Element exemplarCharacterElement = (Element) exemplarCharacterNodes.item(i);
        final String type = exemplarCharacterElement.getAttribute("type");
        final String exemplarCharactersText = exemplarCharacterElement.getTextContent();

        switch (type.isBlank() ? TYPE_STANDARD : type) {
          case TYPE_STANDARD, TYPE_AUXILIARY, TYPE_PUNCTUATION ->
              exemplarCharactersByType.put(
                  type.isBlank() ? TYPE_STANDARD : type,
                  parseExemplarCharacters(exemplarCharactersText));
          default -> {
            // Ignore other exemplar character types such as index and numbers.
          }
        }
      }
    } catch (final SAXException | IOException | RuntimeException | javax.xml.parsers.ParserConfigurationException e) {
      logger.log(Level.SEVERE, e, () -> "Cannot read exemplar characters from " + localeXmlFile);
      throw new IllegalStateException("Cannot read exemplar characters from " + localeXmlFile, e);
    }

    exemplarCharactersByType.putIfAbsent(TYPE_STANDARD, CldrExemplarCharacters.empty());
    exemplarCharactersByType.putIfAbsent(TYPE_AUXILIARY, CldrExemplarCharacters.empty());
    exemplarCharactersByType.putIfAbsent(TYPE_PUNCTUATION, CldrExemplarCharacters.empty());

    return exemplarCharactersByType;
  }

  static CldrExemplarCharacters parseExemplarCharacters(final String exemplarCharactersText) {
    if (exemplarCharactersText == null || exemplarCharactersText.isBlank()) {
      return CldrExemplarCharacters.empty();
    }

    String content = exemplarCharactersText.trim();
    if (content.startsWith("[") && content.endsWith("]")) {
      content = content.substring(1, content.length() - 1);
    }

    final List<CldrExemplarCharacters.Range> ranges = new ArrayList<>();
    final List<String> strings = new ArrayList<>();

    int index = 0;
    while (index < content.length()) {
      index = skipWhitespace(content, index);
      if (index >= content.length()) {
        break;
      }

      final char current = content.charAt(index);
      if (current == '{') {
        final int tokenStart = ++index;
        while (index < content.length() && content.charAt(index) != '}') {
          index++;
        }
        if (index >= content.length()) {
          throw new IllegalArgumentException("Unterminated CLDR exemplar string: " + exemplarCharactersText);
        }
        strings.add(decodeTokenToString(content.substring(tokenStart, index)));
        index++;
        continue;
      }

      final int tokenStart = index;
      while (index < content.length()
          && !Character.isWhitespace(content.charAt(index))
          && content.charAt(index) != '}') {
        index++;
      }

      final String token = content.substring(tokenStart, index);
      final int hyphenIndex = findUnescapedHyphen(token);

      if (hyphenIndex > 0 && hyphenIndex < token.length() - 1) {
        final List<Integer> leftCodePoints = decodeTokenToCodePoints(token.substring(0, hyphenIndex));
        final List<Integer> rightCodePoints = decodeTokenToCodePoints(token.substring(hyphenIndex + 1));
        if (leftCodePoints.size() == 1 && rightCodePoints.size() == 1) {
          ranges.add(new CldrExemplarCharacters.Range(leftCodePoints.get(0), rightCodePoints.get(0)));
          continue;
        }
      }

      final String decodedToken = decodeTokenToString(token);
      final List<Integer> codePoints = decodedToken.codePoints().boxed().toList();
      if (codePoints.size() == 1) {
        final int codePoint = codePoints.get(0);
        ranges.add(new CldrExemplarCharacters.Range(codePoint, codePoint));
      } else {
        strings.add(decodedToken);
      }
    }

    return CldrExemplarCharacters.of(ranges, strings);
  }

  private static int skipWhitespace(final String content, final int startIndex) {
    int index = startIndex;
    while (index < content.length() && Character.isWhitespace(content.charAt(index))) {
      index++;
    }
    return index;
  }

  private static int findUnescapedHyphen(final String token) {
    for (int index = 0; index < token.length(); ) {
      final char current = token.charAt(index);
      if (current == '\\' && index + 1 < token.length()) {
        index = skipEscape(token, index);
        continue;
      }
      if (current == '-') {
        return index;
      }
      index += Character.charCount(token.codePointAt(index));
    }
    return -1;
  }

  private static List<Integer> decodeTokenToCodePoints(final String token) {
    final List<Integer> codePoints = new ArrayList<>();
    for (int index = 0; index < token.length(); ) {
      final char current = token.charAt(index);
      if (current == '\\' && index + 1 < token.length()) {
        final int nextIndex = skipEscape(token, index);
        codePoints.add(decodeEscapedCodePoint(token, index, nextIndex));
        index = nextIndex;
        continue;
      }
      final int codePoint = token.codePointAt(index);
      codePoints.add(codePoint);
      index += Character.charCount(codePoint);
    }
    return codePoints;
  }

  private static String decodeTokenToString(final String token) {
    final StringBuilder decoded = new StringBuilder();
    for (final int codePoint : decodeTokenToCodePoints(token)) {
      decoded.appendCodePoint(codePoint);
    }
    return decoded.toString();
  }

  private static int decodeEscapedCodePoint(final String token, final int escapeStart, final int escapeEnd) {
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

  private static int skipEscape(final String token, final int escapeStart) {
    final char escapeType = token.charAt(escapeStart + 1);
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
}
