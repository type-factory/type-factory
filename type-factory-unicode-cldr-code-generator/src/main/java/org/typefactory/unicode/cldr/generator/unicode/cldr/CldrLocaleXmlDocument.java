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

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.typefactory.StringFormatter;
import org.typefactory.unicode.cldr.generator.locale.Utils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CldrLocaleXmlDocument {

  private static final Logger logger = Logger.getLogger(CldrExemplarCharactersReader.class.getName());

  public static final Locale NORWEGIAN_BOKMAL_LOCALE = new Locale.Builder().setLanguage("nb").build();
  public static final Locale NORWEGIAN_NYNORSK_LOCALE = new Locale.Builder().setLanguage("nn").build();

  private final String resourceName;
  private final Document document;

  public CldrLocaleXmlDocument(final String resourceName, final Document document) {
    this.resourceName = resourceName;
    this.document = document;
  }

  public boolean isForLocale(final Locale locale) {
    return getLocale().equals(locale);
  }

  public boolean isNotForLocale(final Locale locale) {
    return !isForLocale(locale);
  }

  static NodeList getNodes(final Document document, final String xpathExpression) throws XPathExpressionException {
    try {
      final XPath xPath = XPathFactory.newInstance().newXPath();
      return (NodeList) xPath.compile(xpathExpression).evaluate(document, XPathConstants.NODESET);
    } catch (final XPathExpressionException e) {
      logger.log(Level.SEVERE, e, () -> "Cannot evaluate XPath expression: " + xpathExpression);
      return null;
    }
  }

  static Optional<Node> getNode(final Document document, final String xpathExpression) throws IllegalStateException {
    try {
      final XPath xPath = XPathFactory.newInstance().newXPath();
      return Optional.ofNullable((Node) xPath.compile(xpathExpression).evaluate(document, XPathConstants.NODE));
    } catch (final XPathExpressionException e) {
      logger.log(Level.SEVERE, e, () -> "Cannot evaluate XPath expression: " + xpathExpression);
      return Optional.empty();
    }
  }

  private String getResourceName() {
    return resourceName.substring(resourceName.lastIndexOf('/') + 1);
  }

  public boolean isRootCldrResource() {
    return "root.xml".equalsIgnoreCase(getResourceName());
  }

  public String getResourceBundleClassName(){
    final Locale locale = getLocale();
    return Utils.tokenize(locale.toLanguageTag());
  }

  public String getResourceBundleTestClassName(){
    return getResourceBundleClassName() + "_Test";
  }

  public String getResourceBundleTestSuperClassName(){
    return getResourceBundleSuperClassName() + "_Test";
  }

  public String getResourceBundleSuperClassName() {
    final var locale = getLocale();
    final var language = locale.getLanguage();
    final var script = locale.getScript();
    final var region = locale.getCountry();
    final var variant = locale.getVariant();
    final var privateVariant = Objects.requireNonNullElse(locale.getExtension(Locale.PRIVATE_USE_EXTENSION), "");

    if (isRootCldrResource()) {
      return "AbstractCldrResourceBundle";
    }

    if (NORWEGIAN_BOKMAL_LOCALE.equals(locale) || NORWEGIAN_NYNORSK_LOCALE.equals(locale)) {
      // Special case for Norwegian Bokmål and Norwegian Nynorsk, which are both derived from the "no" locale.
      // See https://icu.unicode.org/design/norwegian-locales-changes-in-v39
      return "no";
    }

    final var s = new StringFormatter()
        .append(language)
        .when(!script.isEmpty(), sf -> sf.append("_" + script))
        .when(!region.isEmpty(), sf -> sf.append("_" + region))
        .when(!variant.isEmpty(), sf -> sf.append("_" + variant))
        .when(!privateVariant.isEmpty(), sf -> sf.append("_x_" + privateVariant));

    var index = s.lastIndexOf("_x_");
    if (index > 0) {
      return s.setLength(index).toString();
    }

    index = s.lastIndexOf("_");
    if (index > 0) {
      return s.setLength(index).toString();
    }

    return "root";
  }

  public Locale getLocale() {
    final var language = getNode(document, "/ldml/identity/language/@type");
    final var script = getNode(document, "/ldml/identity/script/@type");
    final var territory = getNode(document, "/ldml/identity/territory/@type");
    final var variant = getNode(document, "/ldml/identity/variant/@type");

    return new Locale.Builder()
        .setLanguage(language.map(Node::getTextContent).orElse(""))
        .setScript(script.map(Node::getTextContent).orElse(""))
        .setRegion(territory.map(Node::getTextContent).orElse(""))
        .setVariant(variant.map(Node::getTextContent).orElse(""))
        //.setExtension(Locale.PRIVATE_USE_EXTENSION, privateUseExtension)
        .build();
  }

  public Optional<CldrExemplarCharacters> getStandardExemplarCharacters() {
    final var optionalNode = getNode(document, "//exemplarCharacters[not(@type)]");
    return optionalNode.map(node -> CldrExemplarCharactersReader.parseExemplarCharacters(node.getTextContent()));
  }

  public Optional<CldrExemplarCharacters> getAuxiliaryExemplarCharacters() {
    final var optionalNode = getNode(document, "//exemplarCharacters[@type='auxiliary']");
    return optionalNode.map(node -> CldrExemplarCharactersReader.parseExemplarCharacters(node.getTextContent()));
  }

  public Optional<CldrExemplarCharacters> getPunctuationExemplarCharacters() {
    final var optionalNode = getNode(document, "//exemplarCharacters[@type='punctuation']");
    return optionalNode.map(node -> CldrExemplarCharactersReader.parseExemplarCharacters(node.getTextContent()));
  }

  public Optional<CldrExemplarCharacters> getNumbersExemplarCharacters() {
    final var optionalNode = getNode(document, "//exemplarCharacters[@type='numbers']");
    return optionalNode.map(node -> CldrExemplarCharactersReader.parseExemplarCharacters(node.getTextContent()));
  }

  public Optional<CldrExemplarCharacters> getDecimalDigitsExemplarCharacters() {
    final var optionalNode = getNode(document, "//exemplarCharacters[@type='numbers']");
    return optionalNode.map(node -> CldrExemplarCharactersReader.parseExemplarCharacters(node.getTextContent()));
  }
}
