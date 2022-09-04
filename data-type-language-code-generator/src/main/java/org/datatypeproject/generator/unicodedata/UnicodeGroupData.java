package org.datatypeproject.generator.unicodedata;

import com.ibm.icu.text.UnicodeSet;
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
import org.unicode.ns._2003.ucd._1.Block;
import org.unicode.ns._2003.ucd._1.Blocks;
import org.unicode.ns._2003.ucd._1.CodePoint;
import org.unicode.ns._2003.ucd._1.Group;
import org.unicode.ns._2003.ucd._1.Repertoire;
import org.unicode.ns._2003.ucd._1.Ucd;

public class UnicodeGroupData {

  public static final UnicodeGroupData INSTANCE = new UnicodeGroupData();
  private final String unicodeAllGroupedXmlFileName;
  private final Ucd ucd;
  private final TreeMap<String, Block> blocksByTheirAbbreviation;
  private final TreeMap<String, UnicodeSet> blockUnicodeSets;
  private final TreeMap<String, UnicodeSet> scriptUnicodeSets;
  private final TreeMap<UnicodeCategory, UnicodeSet> categoryUnicodeSets;
  private final UnicodeSet whitespaceUnicodeSet;

  private final UnicodeSet jSourceSubset;
  private final UnicodeSet kSourceSubset;
  private final UnicodeSet gSourceSubset;
  private final UnicodeSet tSourceSubset;

  private UnicodeGroupData() {
    this.unicodeAllGroupedXmlFileName = "ucd.all.grouped.xml";
    this.ucd = loadUnicodeContentFromXml();

    final List<Block> blocks = getBlocks();
    final BlocksByTheirAbbreviation tempBlocksByTheirAbbreviation = new BlocksByTheirAbbreviation();
    final MapOfUnicodeSets<String> blockUnicodeSets = new MapOfUnicodeSets<>();
    final MapOfUnicodeSets<String> scriptUnicodeSets = new MapOfUnicodeSets<>();
    final MapOfUnicodeSets<UnicodeCategory> categoryUnicodeSets = new MapOfUnicodeSets<>();
    final UnicodeSet whitespaceUnicodeSet = new UnicodeSet();
    final UnicodeSet jSourceSubsetBuilder = new UnicodeSet();
    final UnicodeSet kSourceSubsetBuilder = new UnicodeSet();
    final UnicodeSet gSourceSubsetBuilder = new UnicodeSet();
    final UnicodeSet tSourceSubsetBuilder = new UnicodeSet();
    for (UnicodeCodePoint c : getUnicodeCodePoints()) {
      if (c.getCodePoint() != null) {
        tempBlocksByTheirAbbreviation.add(c, blocks);
        blockUnicodeSets.putUnicodeCodePoint(c.getBlock(), c);
        scriptUnicodeSets.putUnicodeCodePoint(c.getScript(), c);
        categoryUnicodeSets.putUnicodeCodePoint(UnicodeCategory.of(c.getGeneralCategory()), c);
        handleWhitespace(whitespaceUnicodeSet, c);
        handleJSource(jSourceSubsetBuilder, c);
        handleKSource(kSourceSubsetBuilder, c);
        handleGSource(gSourceSubsetBuilder, c);
        handleTSource(tSourceSubsetBuilder, c);
      }
    }
    this.blocksByTheirAbbreviation = tempBlocksByTheirAbbreviation.entrySet().stream()
        .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> a, TreeMap::new));
    this.blockUnicodeSets = blockUnicodeSets.entrySet().stream()
        .collect(Collectors.toMap(Entry::getKey, e -> e.getValue().compact().freeze(), (a, b) -> a, TreeMap::new));
    this.scriptUnicodeSets = scriptUnicodeSets.entrySet().stream()
        .collect(Collectors.toMap(Entry::getKey, e -> e.getValue().compact().freeze(), (a, b) -> a, TreeMap::new));
    this.categoryUnicodeSets = categoryUnicodeSets.entrySet().stream()
        .collect(Collectors.toMap(Entry::getKey, e -> e.getValue().compact().freeze(), (a, b) -> a, TreeMap::new));
    this.whitespaceUnicodeSet = whitespaceUnicodeSet.compact().freeze();
    this.jSourceSubset = jSourceSubsetBuilder.compact().freeze();
    this.kSourceSubset = kSourceSubsetBuilder.compact().freeze();
    this.gSourceSubset = gSourceSubsetBuilder.compact().freeze();
    this.tSourceSubset = tSourceSubsetBuilder.compact().freeze();
  }

  private void handleJSource(UnicodeSet UnicodeSet, UnicodeCodePoint c) {
    if (c.hasJSource()) {
      if (c.hasCodePoint()) {
        UnicodeSet.add(c.getCodePoint());
      } else if (c.hasCodePointRange()) {
        UnicodeSet.add(c.getFirstCodePoint(), c.getLastCodePoint());
      }
    }
  }

  private void handleKSource(UnicodeSet UnicodeSet, UnicodeCodePoint c) {
    if (c.hasKSource()) {
      if (c.hasCodePoint()) {
        UnicodeSet.add(c.getCodePoint());
      } else if (c.hasCodePointRange()) {
        UnicodeSet.add(c.getFirstCodePoint(), c.getLastCodePoint());
      }
    }
  }

  private void handleGSource(UnicodeSet UnicodeSet, UnicodeCodePoint c) {
    if (c.hasGSource()) {
      if (c.hasCodePoint()) {
        UnicodeSet.add(c.getCodePoint());
      } else if (c.hasCodePointRange()) {
        UnicodeSet.add(c.getFirstCodePoint(), c.getLastCodePoint());
      }
    }
  }

  private void handleTSource(UnicodeSet UnicodeSet, UnicodeCodePoint c) {
    if (c.hasGSource()) {
      if (c.hasCodePoint()) {
        UnicodeSet.add(c.getCodePoint());
      } else if (c.hasCodePointRange()) {
        UnicodeSet.add(c.getFirstCodePoint(), c.getLastCodePoint());
      }
    }
  }

  private void handleWhitespace(UnicodeSet UnicodeSet, UnicodeCodePoint c) {
    if (c.isWhitespace()) {
      if (c.hasCodePoint()) {
        UnicodeSet.add(c.getCodePoint());
      } else if (c.hasCodePointRange()) {
        UnicodeSet.add(c.getFirstCodePoint(), c.getLastCodePoint());
      }
    }
  }

  public SortedMap<String, Block> getBlocksByTheirAbbreviation() {
    return blocksByTheirAbbreviation;
  }

  public SortedMap<String, UnicodeSet> getBlockUnicodeSets() {
    return blockUnicodeSets;
  }

  public SortedMap<String, UnicodeSet> getScriptUnicodeSets() {
    return scriptUnicodeSets;
  }

  public SortedMap<UnicodeCategory, UnicodeSet> getCategoryUnicodeSets() {
    return categoryUnicodeSets;
  }

  public UnicodeSet getWhitespaceUnicodeSet() {
    return whitespaceUnicodeSet;
  }

  public UnicodeSet getJSourceSubset() {
    return jSourceSubset;
  }

  public UnicodeSet getKSourceSubset() {
    return kSourceSubset;
  }

  public UnicodeSet getGSourceSubset() {
    return gSourceSubset;
  }

  public UnicodeSet getTSourceSubset() {
    return tSourceSubset;
  }

  private Ucd loadUnicodeContentFromXml() {
    System.out.println("trying to load " + unicodeAllGroupedXmlFileName);
    try (final InputStream resourceStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(unicodeAllGroupedXmlFileName)) {
      final JAXBContext jaxbContext = JAXBContext.newInstance(Ucd.class);
      final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      return (Ucd) jaxbUnmarshaller.unmarshal(resourceStream);
    } catch (final IOException | JAXBException e) {
      throw new UnicodeException(Error.CANNOT_LOAD_UNICODE_ALL_GROUPED_XML_FILE_NAME, unicodeAllGroupedXmlFileName, e);
    }
  }

  private Repertoire getRepertoire() {
    final List<Repertoire> repertoires = ucd.getDescriptionsAndRepertoiresAndBlocks().stream()
        .filter(o -> o instanceof Repertoire ||
            (o instanceof JAXBElement && Repertoire.class.isAssignableFrom(((JAXBElement<?>) o).getDeclaredType())))
        .map(o -> o instanceof Repertoire ? o : ((JAXBElement<?>) o).getValue())
        .map(Repertoire.class::cast)
        .toList();
    if (repertoires.isEmpty()) {
      throw new UnicodeException(Error.THERE_ARE_NO_REPERTOIRE_ELEMENTS_IN_THE_FILE, unicodeAllGroupedXmlFileName);
    }
    if (repertoires.size() > 1) {
      throw new UnicodeException(Error.THERE_ARE_TOO_MANY_REPERTOIRE_ELEMENTS_IN_THE_FILE, unicodeAllGroupedXmlFileName);
    }
    return repertoires.get(0);
  }

  private List<Group> getGroups() {
    final List<Group> groups = getRepertoire().getCodePointsAndGroups().stream()
        .filter(o -> o instanceof Group ||
            (o instanceof JAXBElement && Group.class.isAssignableFrom(((JAXBElement<?>) o).getDeclaredType())))
        .map(o -> o instanceof Group ? o : ((JAXBElement<?>) o).getValue())
        .map(Group.class::cast)
        .toList();
    if (groups.isEmpty()) {
      throw new UnicodeException(Error.THERE_ARE_NO_GROUP_ELEMENTS_IN_THE_FILE, unicodeAllGroupedXmlFileName);
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
        .toList();
    if (unicodeCodePoints.isEmpty()) {
      throw new UnicodeException(Error.THERE_ARE_NO_GROUP_ELEMENTS_IN_THE_FILE, unicodeAllGroupedXmlFileName);
    }
    return unicodeCodePoints;
  }

  private List<Block> getBlocks() {
    final List<Blocks> blocksElements = ucd.getDescriptionsAndRepertoiresAndBlocks().stream()
        .filter(o -> o instanceof Blocks ||
            (o instanceof JAXBElement && Blocks.class.isAssignableFrom(((JAXBElement<?>) o).getDeclaredType())))
        .map(o -> o instanceof Blocks ? o : ((JAXBElement<?>) o).getValue())
        .map(Blocks.class::cast)
        .toList();

    if (blocksElements.isEmpty()) {
      throw new UnicodeException(Error.THERE_ARE_NO_BLOCKS_ELEMENTS_IN_THE_FILE, unicodeAllGroupedXmlFileName);
    }
    if (blocksElements.size() > 1) {
      throw new UnicodeException(Error.THERE_ARE_TOO_MANY_BLOCKS_ELEMENTS_IN_THE_FILE, unicodeAllGroupedXmlFileName);
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

  private static class MapOfUnicodeSets<T> extends HashMap<T, UnicodeSet> {

    @Override
    public UnicodeSet get(final Object key) {
      UnicodeSet builder = super.get(key);
      if (builder == null) {
        builder = new UnicodeSet();
        put((T) key, builder);
      }
      return builder;
    }

    public void putUnicodeCodePoint(final T key, final UnicodeCodePoint ucp) {
      if (key != null) {
        final UnicodeSet builder = get(key);
        if (ucp.hasCodePoint()) {
          builder.add(ucp.getCodePoint());
        } else if (ucp.hasCodePointRange()) {
          builder.add(ucp.getFirstCodePoint(), ucp.getLastCodePoint());
        }
      }
    }
  }
}
