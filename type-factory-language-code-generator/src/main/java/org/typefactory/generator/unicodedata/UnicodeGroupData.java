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
package org.typefactory.generator.unicodedata;

import com.ibm.icu.text.UnicodeSet;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class UnicodeGroupData {

  private static final Logger logger = Logger.getLogger(UnicodeGroupData.class.getName());

  public static final UnicodeGroupData INSTANCE = new UnicodeGroupData();

  /**
   * Represents a {@code <block>} element from the Unicode UCD XML, holding the hex
   * code-point range boundaries and the block name.
   */
  public record BlockEntry(String firstCp, String lastCp, String name) {}

  private final String unicodeAllGroupedXmlFileName;
  private final TreeMap<String, BlockEntry> blocksByTheirAbbreviation;
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

    final List<BlockEntry> blocks = new ArrayList<>();
    final List<UnicodeCodePoint> unicodeCodePoints = new ArrayList<>();
    loadUnicodeContentFromXml(blocks, unicodeCodePoints);

    final BlocksByTheirAbbreviation tempBlocksByTheirAbbreviation = new BlocksByTheirAbbreviation();
    final MapOfUnicodeSets<String> tempBlockUnicodeSets = new MapOfUnicodeSets<>();
    final MapOfUnicodeSets<String> tempScriptUnicodeSets = new MapOfUnicodeSets<>();
    final MapOfUnicodeSets<UnicodeCategory> tempCategoryUnicodeSets = new MapOfUnicodeSets<>();
    final UnicodeSet tempWhitespaceUnicodeSet = new UnicodeSet();
    final UnicodeSet jSourceSubsetBuilder = new UnicodeSet();
    final UnicodeSet kSourceSubsetBuilder = new UnicodeSet();
    final UnicodeSet gSourceSubsetBuilder = new UnicodeSet();
    final UnicodeSet tSourceSubsetBuilder = new UnicodeSet();
    for (UnicodeCodePoint c : unicodeCodePoints) {
      if (c.codePoint() != null) {
        tempBlocksByTheirAbbreviation.add(c, blocks);
        tempBlockUnicodeSets.putUnicodeCodePoint(c.block(), c);
        tempScriptUnicodeSets.putUnicodeCodePoint(c.script(), c);
        tempCategoryUnicodeSets.putUnicodeCodePoint(UnicodeCategory.of(c.generalCategory()), c);
        handleWhitespace(tempWhitespaceUnicodeSet, c);
        handleJSource(jSourceSubsetBuilder, c);
        handleKSource(kSourceSubsetBuilder, c);
        handleGSource(gSourceSubsetBuilder, c);
        handleTSource(tSourceSubsetBuilder, c);
      }
    }
    this.blocksByTheirAbbreviation = tempBlocksByTheirAbbreviation.entrySet().stream()
        .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> a, TreeMap::new));
    this.blockUnicodeSets = tempBlockUnicodeSets.entrySet().stream()
        .collect(Collectors.toMap(Entry::getKey, e -> e.getValue().compact().freeze(), (a, b) -> a, TreeMap::new));
    this.scriptUnicodeSets = tempScriptUnicodeSets.entrySet().stream()
        .collect(Collectors.toMap(Entry::getKey, e -> e.getValue().compact().freeze(), (a, b) -> a, TreeMap::new));
    this.categoryUnicodeSets = tempCategoryUnicodeSets.entrySet().stream()
        .collect(Collectors.toMap(Entry::getKey, e -> e.getValue().compact().freeze(), (a, b) -> a, TreeMap::new));
    this.whitespaceUnicodeSet = tempWhitespaceUnicodeSet.compact().freeze();
    this.jSourceSubset = jSourceSubsetBuilder.compact().freeze();
    this.kSourceSubset = kSourceSubsetBuilder.compact().freeze();
    this.gSourceSubset = gSourceSubsetBuilder.compact().freeze();
    this.tSourceSubset = tSourceSubsetBuilder.compact().freeze();
  }

  private void handleJSource(final UnicodeSet unicodeSet, final UnicodeCodePoint c) {
    if (c.hasJSource()) {
      if (c.hasCodePoint()) {
        unicodeSet.add(c.codePoint());
      } else if (c.hasCodePointRange()) {
        unicodeSet.add(c.getFirstCodePoint(), c.getLastCodePoint());
      }
    }
  }

  private void handleKSource(final UnicodeSet unicodeSet, final UnicodeCodePoint c) {
    if (c.hasKSource()) {
      if (c.hasCodePoint()) {
        unicodeSet.add(c.codePoint());
      } else if (c.hasCodePointRange()) {
        unicodeSet.add(c.getFirstCodePoint(), c.getLastCodePoint());
      }
    }
  }

  private void handleGSource(final UnicodeSet unicodeSet, final UnicodeCodePoint c) {
    if (c.hasGSource()) {
      if (c.hasCodePoint()) {
        unicodeSet.add(c.codePoint());
      } else if (c.hasCodePointRange()) {
        unicodeSet.add(c.getFirstCodePoint(), c.getLastCodePoint());
      }
    }
  }

  private void handleTSource(final UnicodeSet unicodeSet, final UnicodeCodePoint c) {
    if (c.hasTSource()) {
      if (c.hasCodePoint()) {
        unicodeSet.add(c.codePoint());
      } else if (c.hasCodePointRange()) {
        unicodeSet.add(c.getFirstCodePoint(), c.getLastCodePoint());
      }
    }
  }

  private void handleWhitespace(final UnicodeSet unicodeSet, final UnicodeCodePoint c) {
    if (c.isWhitespace()) {
      if (c.hasCodePoint()) {
        unicodeSet.add(c.codePoint());
      } else if (c.hasCodePointRange()) {
        unicodeSet.add(c.getFirstCodePoint(), c.getLastCodePoint());
      }
    }
  }

  public SortedMap<String, BlockEntry> getBlocksByTheirAbbreviation() {
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

  // -------------------------------------------------------------------------
  // XML parsing
  // -------------------------------------------------------------------------

  private void loadUnicodeContentFromXml(
      final List<BlockEntry> blocks,
      final List<UnicodeCodePoint> codePoints) {
    logger.info(() -> "trying to load " + unicodeAllGroupedXmlFileName);
    try (final InputStream resourceStream =
        Thread.currentThread().getContextClassLoader().getResourceAsStream(unicodeAllGroupedXmlFileName)) {
      parseXml(resourceStream, blocks, codePoints);
    } catch (final IOException | XMLStreamException e) {
      throw new UnicodeException(Error.CANNOT_LOAD_UNICODE_ALL_GROUPED_XML_FILE_NAME, unicodeAllGroupedXmlFileName, e);
    }
  }

  private void parseXml(
      final InputStream inputStream,
      final List<BlockEntry> blocks,
      final List<UnicodeCodePoint> codePoints) throws XMLStreamException {

    final XMLInputFactory factory = XMLInputFactory.newInstance();
    // Disable external entity processing to prevent XXE attacks
    factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
    factory.setProperty(XMLInputFactory.SUPPORT_DTD, Boolean.FALSE);

    final XMLStreamReader reader = factory.createXMLStreamReader(inputStream);

    boolean inRepertoire = false;
    boolean inBlocks = false;
    int repertoireCount = 0;
    int blocksCount = 0;
    int groupCount = 0;

    // Current <group> attributes used as defaults for child code-point elements
    String groupSc = null;
    String groupBlk = null;
    String groupGc = null;
    String groupWSpace = null;

    try {
      while (reader.hasNext()) {
        final int event = reader.next();

        if (event == XMLStreamConstants.START_ELEMENT) {
          final String localName = reader.getLocalName();

          switch (localName) {

            case "repertoire":
              inRepertoire = true;
              repertoireCount++;
              if (repertoireCount > 1) {
                throw new UnicodeException(
                    Error.THERE_ARE_TOO_MANY_REPERTOIRE_ELEMENTS_IN_THE_FILE,
                    unicodeAllGroupedXmlFileName);
              }
              break;

            case "blocks":
              inBlocks = true;
              blocksCount++;
              if (blocksCount > 1) {
                throw new UnicodeException(
                    Error.THERE_ARE_TOO_MANY_BLOCKS_ELEMENTS_IN_THE_FILE,
                    unicodeAllGroupedXmlFileName);
              }
              break;

            case "group":
              if (inRepertoire) {
                groupCount++;
                groupSc    = reader.getAttributeValue(null, "sc");
                groupBlk   = reader.getAttributeValue(null, "blk");
                groupGc    = reader.getAttributeValue(null, "gc");
                groupWSpace = reader.getAttributeValue(null, "WSpace");
              }
              break;

            case "char":
            case "reserved":
            case "noncharacter":
            case "surrogate":
              if (inRepertoire) {
                final String cpCp        = reader.getAttributeValue(null, "cp");
                final String cpFirstCp   = reader.getAttributeValue(null, "first-cp");
                final String cpLastCp    = reader.getAttributeValue(null, "last-cp");
                final String cpSc        = reader.getAttributeValue(null, "sc");
                final String cpBlk       = reader.getAttributeValue(null, "blk");
                final String cpGc        = reader.getAttributeValue(null, "gc");
                final String cpWSpace    = reader.getAttributeValue(null, "WSpace");
                final String cpJSource   = reader.getAttributeValue(null, "kIRG_JSource");
                final String cpKSource   = reader.getAttributeValue(null, "kIRG_KSource");
                final String cpGSource   = reader.getAttributeValue(null, "kIRG_GSource");
                final String cpTSource   = reader.getAttributeValue(null, "kIRG_TSource");

                codePoints.add(new UnicodeCodePoint(
                    cpCp,
                    cpFirstCp,
                    cpLastCp,
                    isNotBlank(cpSc)  ? cpSc  : groupSc,
                    isNotBlank(cpBlk) ? cpBlk : groupBlk,
                    isNotBlank(cpGc)  ? cpGc  : groupGc,
                    "Y".equals(cpWSpace) || "Y".equals(groupWSpace),
                    cpJSource,
                    cpKSource,
                    cpGSource,
                    cpTSource
                ));
              }
              break;

            case "block":
              if (inBlocks) {
                blocks.add(new BlockEntry(
                    reader.getAttributeValue(null, "first-cp"),
                    reader.getAttributeValue(null, "last-cp"),
                    reader.getAttributeValue(null, "name")
                ));
              }
              break;

            default:
              break;
          }

        } else if (event == XMLStreamConstants.END_ELEMENT) {
          final String localName = reader.getLocalName();
          switch (localName) {
            case "repertoire":
              inRepertoire = false;
              break;
            case "blocks":
              inBlocks = false;
              break;
            case "group":
              if (inRepertoire) {
                groupSc = null;
                groupBlk = null;
                groupGc = null;
                groupWSpace = null;
              }
              break;
            default:
              break;
          }
        }
      }
    } finally {
      reader.close();
    }

    // Post-parse validation
    if (repertoireCount == 0) {
      throw new UnicodeException(Error.THERE_ARE_NO_REPERTOIRE_ELEMENTS_IN_THE_FILE, unicodeAllGroupedXmlFileName);
    }
    if (groupCount == 0) {
      throw new UnicodeException(Error.THERE_ARE_NO_GROUP_ELEMENTS_IN_THE_FILE, unicodeAllGroupedXmlFileName);
    }
    if (blocksCount == 0) {
      throw new UnicodeException(Error.THERE_ARE_NO_BLOCKS_ELEMENTS_IN_THE_FILE, unicodeAllGroupedXmlFileName);
    }
  }

  private static boolean isNotBlank(final String value) {
    return value != null && !value.isBlank();
  }

  // -------------------------------------------------------------------------
  // Inner helpers
  // -------------------------------------------------------------------------

  private static class BlocksByTheirAbbreviation extends HashMap<String, BlockEntry> {
    public void add(final UnicodeCodePoint ucp, final List<BlockEntry> blocks) {
      if (ucp.block() == null || ucp.block().isBlank() || containsKey(ucp.block())) {
        return;
      }
      if (ucp.hasCodePoint()) {
        for (BlockEntry block : blocks) {
          if (ucp.codePoint() >= Integer.parseInt(block.firstCp(), 16)
              && ucp.codePoint() <= Integer.parseInt(block.lastCp(), 16)) {
            put(ucp.block(), block);
          }
        }
      } else if (ucp.hasCodePointRange()) {
        for (BlockEntry block : blocks) {
          if (ucp.getFirstCodePoint() >= Integer.parseInt(block.firstCp(), 16)
              && ucp.getLastCodePoint() <= Integer.parseInt(block.lastCp(), 16)) {
            put(ucp.block(), block);
          }
        }
      }
    }
  }

  private static class MapOfUnicodeSets<T> extends HashMap<T, UnicodeSet> {

    @Override
    @SuppressWarnings("unchecked")
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
          builder.add(ucp.codePoint());
        } else if (ucp.hasCodePointRange()) {
          builder.add(ucp.getFirstCodePoint(), ucp.getLastCodePoint());
        }
      }
    }
  }
}
