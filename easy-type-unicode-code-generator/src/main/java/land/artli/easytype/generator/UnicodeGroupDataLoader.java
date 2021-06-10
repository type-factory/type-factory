package land.artli.easytype.generator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.unicode.ns._2003.ucd._1.CodePoint;
import org.unicode.ns._2003.ucd._1.Group;
import org.unicode.ns._2003.ucd._1.Repertoire;
import org.unicode.ns._2003.ucd._1.Ucd;

public class UnicodeGroupDataLoader {

  private final String unicodeAllGroupedXmlFileName;

  private final Ucd ucd;
  public UnicodeGroupDataLoader(final String unicodeAllGroupedXmlFileName) {
    this.unicodeAllGroupedXmlFileName = unicodeAllGroupedXmlFileName;
    ucd = loadUnicodeContentFromXml(unicodeAllGroupedXmlFileName);
  }

  public static Ucd loadUnicodeContentFromXml(final String unicodeAllGroupedXmlFileName)  {
    System.out.println("trying to load " + unicodeAllGroupedXmlFileName);
//    final URL resourceUrl = UnicodeGroupDataLoader.class.getResource(unicodeAllGroupedXmlFileName);
    try (final InputStream resourceStream = new FileInputStream(unicodeAllGroupedXmlFileName)) {
      final JAXBContext jaxbContext = JAXBContext.newInstance(Ucd.class);
      final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      return (Ucd) jaxbUnmarshaller.unmarshal(resourceStream);
    } catch (final IOException | JAXBException e) {
      throw new GeneratorException(Error.CANNOT_LOAD_UNICODE_ALL_GROUPED_XML_FILE_NAME, unicodeAllGroupedXmlFileName, e);
    }
  }

  public Repertoire getRepertoire() {
    final List<Repertoire> repertoires = ucd.getDescriptionsAndRepertoiresAndBlocks().stream()
        .filter(o -> o instanceof Repertoire ||
            (o instanceof JAXBElement && Repertoire.class.isAssignableFrom(((JAXBElement<?>)o).getDeclaredType())))
        .map(o -> o instanceof Repertoire ? o : ((JAXBElement<?>)o).getValue())
        .map(Repertoire.class::cast)
        .collect(Collectors.toList());
    if (repertoires.isEmpty()) {
      throw new GeneratorException(Error.THERE_ARE_NO_REPERTOIRE_ELEMENTS_IN_THE_FILE, unicodeAllGroupedXmlFileName);
    }
    if (repertoires.size() > 1) {
      throw new GeneratorException(Error.THERE_ARE_TOO_MANY_REPERTOIRE_ELEMENTS_IN_THE_FILE, unicodeAllGroupedXmlFileName);
    }
    return repertoires.get(0);
  }

  public List<Group> getGroups() {
    final List<Group> groups = getRepertoire().getCodePointsAndGroups().stream()
        .filter(o -> o instanceof Group ||
            (o instanceof JAXBElement && Group.class.isAssignableFrom(((JAXBElement<?>)o).getDeclaredType())))
        .map(o -> o instanceof Group ? o : ((JAXBElement<?>)o).getValue())
        .map(Group.class::cast)
        .collect(Collectors.toList());
    if (groups.isEmpty()) {
      throw new GeneratorException(Error.THERE_ARE_NO_GROUP_ELEMENTS_IN_THE_FILE, unicodeAllGroupedXmlFileName);
    }
    return groups;
  }

  public List<UnicodeCodePoint> getUnicodeCodePoints() {
    final List<UnicodeCodePoint> unicodeCodePoints = getGroups().stream()
        .flatMap(g -> g.getCodePoints().stream()
              .filter(o -> CodePoint.class.isAssignableFrom(o.getDeclaredType()))
              .map(JAXBElement::getValue)
              .map(CodePoint.class::cast)
              .map(c -> new UnicodeCodePoint(g, c)))
        .collect(Collectors.toList());
    if (unicodeCodePoints.isEmpty()) {
      throw new GeneratorException(Error.THERE_ARE_NO_GROUP_ELEMENTS_IN_THE_FILE, unicodeAllGroupedXmlFileName);
    }
    return unicodeCodePoints;
  }
}
