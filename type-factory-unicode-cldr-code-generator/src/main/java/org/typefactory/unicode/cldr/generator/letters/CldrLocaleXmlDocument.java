package org.typefactory.unicode.cldr.generator.letters;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class CldrLocaleXmlDocument {

  private static final Logger logger = Logger.getLogger(CldrExemplarCharactersReader.class.getName());

  private final Document document;

  public CldrLocaleXmlDocument(final InputStream xmlInputStream) {
    try {
      final var documentBuilder = DocumentBuilderFactory.newInstance();
      documentBuilder.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
      documentBuilder.setExpandEntityReferences(false);
      documentBuilder.setNamespaceAware(false);
      documentBuilder.setXIncludeAware(false);

      final var builder = documentBuilder.newDocumentBuilder();
      builder.setEntityResolver((publicId, systemId) -> new InputSource(new StringReader("")));

      document = builder.parse(xmlInputStream);
    } catch (final SAXException | IOException | RuntimeException | javax.xml.parsers.ParserConfigurationException e) {
      logger.log(Level.SEVERE, e, () -> "Cannot read exemplar characters");
      throw new IllegalStateException("Cannot read exemplar characters", e);
    }
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

  public CldrExemplarCharacters getStandardExemplarCharacters() {
    final var node = getNode(document, "//exemplarCharacters[not(@type)]");
    final var value = node.map(Node::getTextContent).orElse("");
    return CldrExemplarCharactersReader.parseExemplarCharacters(value);
  }

  public CldrExemplarCharacters getAuxiliaryExemplarCharacters() {
    final var node = getNode(document, "//exemplarCharacters[@type='auxiliary']");
    final var value =  node.map(Node::getTextContent).orElse("");
    return CldrExemplarCharactersReader.parseExemplarCharacters(value);
  }

  public CldrExemplarCharacters getPunctuationExemplarCharacters() {
    final var node = getNode(document, "//exemplarCharacters[@type='punctuation']");
    final var value =  node.map(Node::getTextContent).orElse("");
    return CldrExemplarCharactersReader.parseExemplarCharacters(value);
  }
}
