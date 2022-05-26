package land.artli.easytype.generator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import land.artli.easytype.generator.Unicode.RangedSubset;
import land.artli.easytype.generator.Unicode.RangedSubset.RangedSubsetBuilder;
import org.unicode.ns._2003.ucd._1.Block;
import org.unicode.ns._2003.ucd._1.Blocks;
import org.unicode.ns._2003.ucd._1.CodePoint;
import org.unicode.ns._2003.ucd._1.Group;
import org.unicode.ns._2003.ucd._1.Repertoire;
import org.unicode.ns._2003.ucd._1.Ucd;

public class UnicodeGroupDataLoader {

  private final String unicodeAllGroupedXmlFileName;
  private final Ucd ucd;
  private final TreeMap<String, Block> blocksByTheirAbbreviation;
  private final TreeMap<String, RangedSubset> blockRangedSubsets;
  private final TreeMap<String, RangedSubset> scriptRangedSubsets;
  private final TreeMap<UnicodeCategory, RangedSubset> categoryRangedSubsets;
  private final RangedSubset whitespaceRangedSubset;

  public UnicodeGroupDataLoader(final String unicodeAllGroupedXmlFileName) {
    this.unicodeAllGroupedXmlFileName = unicodeAllGroupedXmlFileName;
    this.ucd = loadUnicodeContentFromXml(unicodeAllGroupedXmlFileName);

    final List<Block> blocks = getBlocks();
    final BlocksByTheirAbbreviation tempBlocksByTheirAbbreviation = new BlocksByTheirAbbreviation();
    final MapOfRangedSubsetBuilders<String> blockRangedSubsetBuilders = new MapOfRangedSubsetBuilders<>();
    final MapOfRangedSubsetBuilders<String> scriptRangedSubsetBuilders = new MapOfRangedSubsetBuilders<>();
    final MapOfRangedSubsetBuilders<UnicodeCategory> categoryRangedSubsetBuilders = new MapOfRangedSubsetBuilders<>();
    final RangedSubsetBuilder whitespaceRangedSubsetBuilder = RangedSubset.builder();
    for (UnicodeCodePoint c : getUnicodeCodePoints()) {
      if (c.getCodePoint() != null) {
        tempBlocksByTheirAbbreviation.add(c, blocks);
        blockRangedSubsetBuilders.putUnicodeCodePoint(c.getBlock(), c);
        scriptRangedSubsetBuilders.putUnicodeCodePoint(c.getScript(), c);
        categoryRangedSubsetBuilders.putUnicodeCodePoint(UnicodeCategory.of(c.getGeneralCategory()), c);
        if (c.isWhitespace()) {
          if (c.hasCodePoint()) {
            whitespaceRangedSubsetBuilder.addCodePoint(c.getCodePoint());
          } else if (c.hasCodePointRange()) {
            whitespaceRangedSubsetBuilder.addCodePointRange(c.getFirstCodePoint(), c.getLastCodePoint());
          }
        }
      }
    }
    this.blocksByTheirAbbreviation = tempBlocksByTheirAbbreviation.entrySet().stream()
        .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> a, TreeMap::new));
    this.blockRangedSubsets = blockRangedSubsetBuilders.entrySet().stream()
        .collect(Collectors.toMap(Entry::getKey, e -> e.getValue().build(), (a, b) -> a, TreeMap::new));
    this.scriptRangedSubsets = scriptRangedSubsetBuilders.entrySet().stream()
        .collect(Collectors.toMap(Entry::getKey, e -> e.getValue().build(), (a, b) -> a, TreeMap::new));
    this.categoryRangedSubsets = categoryRangedSubsetBuilders.entrySet().stream()
        .collect(Collectors.toMap(Entry::getKey, e -> e.getValue().build(), (a, b) -> a, TreeMap::new));
    this.whitespaceRangedSubset = whitespaceRangedSubsetBuilder.build();
  }

  public SortedMap<String, Block> getBlocksByTheirAbbreviation() {
    return blocksByTheirAbbreviation;
  }

  public SortedMap<String, RangedSubset> getBlockRangedSubsets() {
    return blockRangedSubsets;
  }

  public SortedMap<String, RangedSubset> getScriptRangedSubsets() {
    return scriptRangedSubsets;
  }

  public SortedMap<UnicodeCategory, RangedSubset> getCategoryRangedSubsets() {
    return categoryRangedSubsets;
  }

  public RangedSubset getWhitespaceRangedSubset() {
    return whitespaceRangedSubset;
  }

  private static Ucd loadUnicodeContentFromXml(final String unicodeAllGroupedXmlFileName) {
    System.out.println("trying to load " + unicodeAllGroupedXmlFileName);
    try (final InputStream resourceStream = new FileInputStream(unicodeAllGroupedXmlFileName)) {
      final JAXBContext jaxbContext = JAXBContext.newInstance(Ucd.class);
      final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      return (Ucd) jaxbUnmarshaller.unmarshal(resourceStream);
    } catch (final IOException | JAXBException e) {
      throw new GeneratorException(Error.CANNOT_LOAD_UNICODE_ALL_GROUPED_XML_FILE_NAME, unicodeAllGroupedXmlFileName, e);
    }
  }

  private Repertoire getRepertoire() {
    final List<Repertoire> repertoires = ucd.getDescriptionsAndRepertoiresAndBlocks().stream()
        .filter(o -> o instanceof Repertoire ||
            (o instanceof JAXBElement && Repertoire.class.isAssignableFrom(((JAXBElement<?>) o).getDeclaredType())))
        .map(o -> o instanceof Repertoire ? o : ((JAXBElement<?>) o).getValue())
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

  private List<Group> getGroups() {
    final List<Group> groups = getRepertoire().getCodePointsAndGroups().stream()
        .filter(o -> o instanceof Group ||
            (o instanceof JAXBElement && Group.class.isAssignableFrom(((JAXBElement<?>) o).getDeclaredType())))
        .map(o -> o instanceof Group ? o : ((JAXBElement<?>) o).getValue())
        .map(Group.class::cast)
        .collect(Collectors.toList());
    if (groups.isEmpty()) {
      throw new GeneratorException(Error.THERE_ARE_NO_GROUP_ELEMENTS_IN_THE_FILE, unicodeAllGroupedXmlFileName);
    }
    return groups;
  }

  private List<UnicodeCodePoint> getUnicodeCodePoints() {
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

  private List<Block> getBlocks() {
    final List<Blocks> blocksElements = ucd.getDescriptionsAndRepertoiresAndBlocks().stream()
        .filter(o -> o instanceof Blocks ||
            (o instanceof JAXBElement && Blocks.class.isAssignableFrom(((JAXBElement<?>) o).getDeclaredType())))
        .map(o -> o instanceof Blocks ? o : ((JAXBElement<?>) o).getValue())
        .map(Blocks.class::cast)
        .collect(Collectors.toList());

    if (blocksElements.isEmpty()) {
      throw new GeneratorException(Error.THERE_ARE_NO_BLOCKS_ELEMENTS_IN_THE_FILE, unicodeAllGroupedXmlFileName);
    }
    if (blocksElements.size() > 1) {
      throw new GeneratorException(Error.THERE_ARE_TOO_MANY_BLOCKS_ELEMENTS_IN_THE_FILE, unicodeAllGroupedXmlFileName);
    }
    return blocksElements.get(0).getBlocks();
  }

  private static class BlocksByTheirAbbreviation extends HashMap<String, Block> {
    public void add(final UnicodeCodePoint ucp, final List<Block> blocks) {
      if (ucp.getBlock() == null || ucp.getBlock().isBlank() || containsKey(ucp.getBlock())) {
        return;
      }
      if (ucp.hasCodePoint()) {
        for (Block block : blocks) {
          if (ucp.getCodePoint() >= Integer.parseInt(block.getFirstCp(), 16)
              && ucp.getCodePoint() <= Integer.parseInt(block.getLastCp(), 16)) {
            put(ucp.getBlock(), block);
          }
        }
      } else if (ucp.hasCodePointRange()) {
        for (Block block : blocks) {
          if (ucp.getFirstCodePoint() >= Integer.parseInt(block.getFirstCp(), 16)
              && ucp.getLastCodePoint() <= Integer.parseInt(block.getLastCp(), 16)) {
            put(ucp.getBlock(), block);
          }
        }
      }
    }
  }

  private static class MapOfRangedSubsetBuilders<T> extends HashMap<T, RangedSubsetBuilder> {

    @Override
    public RangedSubsetBuilder get(final Object key) {
      RangedSubsetBuilder builder = super.get(key);
      if (builder == null) {
        builder = RangedSubset.builder();
        put((T) key, builder);
      }
      return builder;
    }

    public void putUnicodeCodePoint(final T key, final UnicodeCodePoint ucp) {
      if (key != null) {
        final RangedSubsetBuilder builder = get(key);
        if (ucp.hasCodePoint()) {
          builder.addCodePoint(ucp.getCodePoint());
        } else if (ucp.hasCodePointRange()) {
          builder.addCodePointRange(ucp.getFirstCodePoint(), ucp.getLastCodePoint());
        }
      }
    }
  }
}
