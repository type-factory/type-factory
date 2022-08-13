package org.datatypeproject.generator.language;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static org.datatypeproject.generator.language.JavadocFragments.LANGUAGE_ALPHABET_INCLUDED_JAVADOC;
import static org.datatypeproject.generator.language.LanguageData.LETTERS_JAPANESE_JA_HANI;

import com.ibm.icu.text.UnicodeSet;
import com.ibm.icu.text.UnicodeSet.EntryRange;
import com.ibm.icu.util.ULocale;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.datatypeproject.generator.unicodedata.UnicodeGroupData;

public class LanguageClassGenerator {

  private static final Logger logger = Logger.getLogger(LanguageClassGenerator.class.getName());

  private static final String LINE_SEPARATOR = System.lineSeparator();

  private final File outputDirectory;
  private final UnicodeGroupData unicodeGroupData;

  public LanguageClassGenerator(final File outputDirectory, final UnicodeGroupData unicodeGroupData) {
    this.outputDirectory = outputDirectory;
    this.unicodeGroupData = unicodeGroupData;
  }

  public void generateLanguageClass() {

    final StringBuilder s = new StringBuilder();

    s.append("""
        package org.datatypeproject;
                
        import java.util.Locale;
        import java.util.HashMap;
        import javax.annotation.processing.Generated;
                
        @Generated(
            comments = "This file is generated from data in the LanguageData class in the data-type-language-code-generator module.",
            value = "org.datatypeproject:data-type-language-code-generator")     
        public class Letters {
                
          private static final Index index = new Index();
          
          private Letters() {
            // don't instantiate
          }
          
          private static class Index {
            private HashMap<Locale, Subset> localeSubsets = new HashMap();
            private void put(final Locale locale, final Subset subset) {
              localeSubsets.put(locale, subset);
            }
            private Subset get(final Locale locale) {
              return localeSubsets.get(locale);
            }
          }
          
          private static Subset registerSubset(final Locale locale, final Subset subset) {
            index.put(locale, subset);
            return subset;
          }
        """);

    for (LanguageData languageData : LanguageData.values()) {
      final ULocale locale = languageData.getLocale();
      final String localeLanguage = locale.getLanguage();
      final String localeCountry = locale.getCountry();
      final String localeVariant = locale.getVariant();
      final String localeScript = locale.getScript();
      final String localeLanguageTag = locale.toLanguageTag().replaceAll("[\s_-]+", "_");
      final String displayLanguage = locale.getDisplayLanguage().replaceAll("[\s_-]+", "_");
      final String enumName = String.format("%s_%s", displayLanguage.toUpperCase(), localeLanguageTag);
      final String lettersClassName = String.format("Letters_%s_%s", displayLanguage, localeLanguageTag);

      s.append(LINE_SEPARATOR);
      appendJavadoc(s, languageData, enumName);
      if ("Hani".equalsIgnoreCase(localeScript)) {
        createAlphabetCharactersHtml(languageData);
      }

      s.append(String.format("  public static final Subset %s = registerSubset(", enumName));
      if (locale.getScript() == null || locale.getScript().isBlank()) {
        s.append(String.format("%n      new Locale(\"%s\", \"%s\", \"%s\"),",
            localeLanguage, localeCountry, localeVariant));
      } else {
        s.append(String.format("%n      new Locale.Builder().setLanguage(\"%s\").setRegion(\"%s\").setVariant(\"%s\").setScript(\"%s\").build(),",
            localeLanguage, localeCountry, localeVariant, localeScript));
      }

      switch (languageData) {
        case LETTERS_JAPANESE_JA_JSOURCE:
        case LETTERS_JAPANESE_JA_JINMEIYO:
          final String letterClassName = generateLettersClassForSingleLanguage(languageData);
          s.append(LINE_SEPARATOR).append("      ").append(letterClassName).append(".SUBSET);");
          break;
        default:
          s.append(LINE_SEPARATOR).append("      new RangedSubsetImpl(");
          final Sizes singleByteSizes = appendCodepointArrayRanges(s, languageData, 0x00, 0xFF, "char", "0x%02x_%02x");
          final Sizes doubleByteSizes = appendCodepointArrayRanges(s, languageData, 0x0100, 0xFFFF, "int", "0x%04x_%04x");
          final Sizes tripleByteSizes = appendCodepointArrayRanges(s, languageData, 0x00010000, MAX_VALUE, "long", "0x%08x_%08xL");
          s.append(LINE_SEPARATOR).append("      ");
          s.append(singleByteSizes.rangesSize + doubleByteSizes.rangesSize + tripleByteSizes.rangesSize).append(", ");
          s.append(singleByteSizes.codePointsSize + doubleByteSizes.codePointsSize + tripleByteSizes.codePointsSize);
          s.append("));");
          break;
      }
    }

    s.append(LINE_SEPARATOR);
    s.append("""
        }
        """);

    try (final FileWriter fileWriter = new FileWriter(outputDirectory + File.separator + "Letters.java")) {
      fileWriter.append(s.toString());
      fileWriter.flush();
    } catch (final IOException e) {
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  private String generateLettersClassForSingleLanguage(final LanguageData languageData) {
    final ULocale locale = languageData.getLocale();
    final String localeLanguageTag = locale.toLanguageTag().replaceAll("[\s_-]+", "_");
    final String displayLanguage = locale.getDisplayLanguage().replaceAll("[\s_-]+", "_");
    final String lettersClassName = String.format("Letters_%s_%s", displayLanguage, localeLanguageTag);
    final RangedSubsetOptimiser rangedSubsetOptimiser = new RangedSubsetOptimiser(languageData.getUnicodeSet());

    final StringBuilder s = new StringBuilder();

    s.append(String.format("""
        package org.datatypeproject;
                
        import javax.annotation.processing.Generated;
                
        @Generated(
            comments = "This file is generated from data in the LanguageData class in the data-type-language-code-generator module.",
            value = "org.datatypeproject:data-type-language-code-generator")     
        public class %s {
                
        """, lettersClassName));

    if (rangedSubsetOptimiser.containsHashBucketsWithMultipleKeys()) {
      s.append("  public static final Subset SUBSET = new HashedRangedSubsetImpl(").append(LINE_SEPARATOR);
      appendHashedBlockRangedSubset(s, languageData, rangedSubsetOptimiser);
    } else {
      s.append("  public static final Subset SUBSET = new OptimalHashedRangedSubsetImpl(").append(LINE_SEPARATOR);
      appendOptimalHashedBlockRangedSubset(s, languageData, rangedSubsetOptimiser);
    }
    s.append(");");

    s.append(LINE_SEPARATOR);
    s.append("""
        }
        """);

    try (final FileWriter fileWriter = new FileWriter(
        outputDirectory + /* TODO File.separator + "letters" + */ File.separator + lettersClassName + ".java")) {
      fileWriter.append(s.toString());
      fileWriter.flush();
    } catch (final IOException e) {
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
    return lettersClassName;
  }

  private static class Sizes {
    public final int rangesSize;
    public final int codePointsSize;

    public Sizes(final int rangesSize, final int codePointsSize) {
      this.rangesSize = rangesSize;
      this.codePointsSize = codePointsSize;
    }
  }
  private Sizes appendCodepointArrayRanges(
      final StringBuilder s,
      final LanguageData languageData,
      final int rangeStart,
      final int rangeEnd,
      final String rangeArrayType,
      final String rangeFormat) {

    final UnicodeSet unicodeSet = languageData.getUnicodeSet();
    final String indentedRangeFormat = "          " + rangeFormat + ", // ";
    if (unicodeSet == null || unicodeSet.isEmpty()) {
      return new Sizes(0,0);
    }
    boolean arrayStarted = false;
    final String indent = switch (rangeArrayType) {
      case "char" -> new String(new char[19]).replace('\0', ' ');
      case "int" -> new String(new char[23]).replace('\0', ' ');
      case "long" -> new String(new char[32]).replace('\0', ' ');
      default -> "";
    };
    int rangesSize = 0;
    int codePointsSize = 0;
    for (EntryRange range : unicodeSet.ranges()) {
      final int from = range.codepoint;
      final int to = range.codepointEnd;
      if (from <= rangeEnd && to >= rangeStart) {
        if (!arrayStarted) {
          if (languageData == LETTERS_JAPANESE_JA_HANI) {
            s.append(LINE_SEPARATOR)
                .append("      // See Javadoc for full set of unicode code points in the following ranges.");
          }
          s.append(LINE_SEPARATOR).append("        new ").append(rangeArrayType).append("[]{").append(LINE_SEPARATOR);
          arrayStarted = true;
        }
        s.append(String.format(indentedRangeFormat, max(rangeStart, from), min(rangeEnd, to)));
        rangesSize++;
        codePointsSize += (to - from + 1);
        if (languageData == LETTERS_JAPANESE_JA_HANI) {
          for (int c = from; c <= min(to, from + 20); ++c) {
            s.append(' ').appendCodePoint(c);
          }
          s.append(" ...").append(LINE_SEPARATOR);
        } else {
          for (int c = from, i = 1; c <= to; ++c, ++i) {
            s.append(' ').appendCodePoint(c);
            if (i % 30 == 0 && to - c > 2) {
              s.append(LINE_SEPARATOR).append(indent).append("// ");
            }
          }
          s.append(LINE_SEPARATOR);
        }
      }
    }
    if (arrayStarted) {
      s.append("      },");
    }
    return new Sizes(rangesSize, codePointsSize);
  }

  private static void appendJavadoc(
      final StringBuilder s,
      final LanguageData languageData,
      final String enumName) {

    if (languageData.hasJavadoc()) {
      s.append(LINE_SEPARATOR).append("  /**");
      final String javadocLineStart = LINE_SEPARATOR + "   * ";
      for (String javadoc : languageData.getJavadoc()) {
        s.append(LINE_SEPARATOR).append("   * ");
        s.append(javadoc.replace(LINE_SEPARATOR, javadocLineStart));
        s.append(LINE_SEPARATOR).append("   *");
        if (LANGUAGE_ALPHABET_INCLUDED_JAVADOC.equals(javadoc)) {
          appendAlphabetCharactersJavadoc(s, languageData, enumName);
          s.append(LINE_SEPARATOR).append("   *");
        }
      }
      s.append("/").append(LINE_SEPARATOR);
    }
  }

  @SuppressWarnings("java:S3776")
  private static void appendAlphabetCharactersJavadoc(
      final StringBuilder s,
      final LanguageData languageData,
      final String enumName) {

    final UnicodeSet unicodeSet = languageData.getUnicodeSet();

    if (unicodeSet == null || unicodeSet.isEmpty()) {
      return;
    }
    if (languageData == LETTERS_JAPANESE_JA_HANI) {
      s.append(LINE_SEPARATOR).append("   * <p>There are too many unicode code-points in this set to display here. See separate ");
      s.append(LINE_SEPARATOR).append("   *    <a href='./doc-files/").append(enumName).append(".html'>JAPANESE_ja_Hani</a>");
      s.append(LINE_SEPARATOR).append("   *    file for a complete list of the unicode code-points or in this language set.</p>");
    } else {
      s.append(LINE_SEPARATOR).append("   * <pre>");
      for (EntryRange range : unicodeSet.ranges()) {
        final int from = range.codepoint;
        final int to = range.codepointEnd;
        if (range.codepointEnd > range.codepoint) {
          s.append(LINE_SEPARATOR);
          s.append(String.format("   *    %04X..%04X  ", from, to));
          for (int c = from, i = 1; c <= to; ++c, ++i) {
            s.append(' ').appendCodePoint(c);
            if (i % 30 == 0 && to - c > 2) {
              s.append(LINE_SEPARATOR).append("   *                ");
            }
          }
        } else {
          s.append(LINE_SEPARATOR);
          s.append(String.format("   *    %04X        ", from));
          for (int c = from; c <= to; ++c) {
            s.append(' ').appendCodePoint(c);
          }
        }
      }
      s.append(LINE_SEPARATOR).append("   * </pre>");
    }
  }

  private static void appendHashedBlockRangedSubset(
      final StringBuilder s,
      final LanguageData languageData,
      final RangedSubsetOptimiser rangedSubsetOptimiser) {

    final var hashedRangedSubsetData = rangedSubsetOptimiser.getHashedRangedSubsetData();
    final int[][] keys = hashedRangedSubsetData.getBlockKeys();
    final int[][][] inclusiveFroms = hashedRangedSubsetData.getInclusiveFroms();
    final int[][][] inclusiveTos = hashedRangedSubsetData.getInclusiveTos();
    s.append(LINE_SEPARATOR).append("      // int [][] blockKeys");
    s.append(LINE_SEPARATOR).append("      new int[][] {");
    for (int i = 0; i < keys.length; ++i) {
      final int[] buckets = keys[i];
      if (i % 8 == 0) {
        s.append(LINE_SEPARATOR).append("        ");
      }
      final StringBuilder temp = new StringBuilder();
      if (buckets == null || buckets.length == 0) {
        temp.append(" null           ");
      } else {
        switch (buckets.length) {
          case 1:
            temp.append(String.format("{0x%04x}        ", buckets[0]));
            break;
          case 2:
            temp.append(String.format("{0x%04x, 0x%04x}", buckets[0], buckets[1]));
            break;
          default:
            temp.append("{");
            for (int j = 0; j < buckets.length; ++j) {
              temp.append(String.format("0x%04x, ", buckets[j]));
            }
            temp.append("}");
            break;
        }
      }
      s.append(String.format("%-16s, ", temp));
    }
    s.setLength(s.length() - 2);
    s.append("  },");
    s.append(LINE_SEPARATOR).append("      // char [][][] codePointRanges");
    s.append(LINE_SEPARATOR).append("      new char[][][] {");
    for (int i = 0; i < keys.length; ++i) {
      final int[] keyBuckets = keys[i];
      final int[][] fromBuckets = inclusiveFroms[i];
      final int[][] toBuckets = inclusiveTos[i];
      if (keyBuckets == null) {
        s.append(LINE_SEPARATOR).append("        null,");
      } else {
        s.append(LINE_SEPARATOR).append("        {");
        for (int j = 0; j < fromBuckets.length; ++j) {
          final int key = keyBuckets[j];
          if (j > 0) {
            s.append(LINE_SEPARATOR).append("         ");
          }
          s.append(String.format(" // 0x%04x__ codePoint ranges", key));
          s.append(LINE_SEPARATOR).append("          ");
          final int[] froms = fromBuckets[j];
          final int[] tos = toBuckets[j];
          s.append("{");
          for (int k = 0; k < froms.length; ++k) {
            if (k > 0 && k % 8 == 0) {
              s.append(LINE_SEPARATOR).append("           ");
            }
            final int from = froms[k];
            final int to = tos[k];
            s.append(String.format("0x%02x_%02x, ", from & 0xFF, to & 0xFF));
//            s.append(String.format("0x%06x_%06x, ", from, to));
          }
          s.setLength(s.length() - 2);
          s.append("},");
        }
        s.setLength(s.length() - 1);
        s.append("}, ");
      }
    }
    s.setLength(s.length() - 2);
    s.append("},");
    s.append(LINE_SEPARATOR).append("        // number of code-point ranges");
    s.append(LINE_SEPARATOR).append("        ").append(hashedRangedSubsetData.getRangesSize()).append(",");
    s.append(LINE_SEPARATOR).append("        // number of code-points");
    s.append(LINE_SEPARATOR).append("        ").append(hashedRangedSubsetData.getCodePointsSize());
  }

  private static void appendOptimalHashedBlockRangedSubset(
      final StringBuilder s,
      final LanguageData languageData,
      final RangedSubsetOptimiser rangedSubsetOptimiser) {

    final var optimalHashedRangedSubsetData = rangedSubsetOptimiser.getOptimalHashedRangedSubsetData();
    final int[] keys = optimalHashedRangedSubsetData.getBlockKeys();
    final int[][] inclusiveFroms = optimalHashedRangedSubsetData.getInclusiveFroms();
    final int[][] inclusiveTos = optimalHashedRangedSubsetData.getInclusiveTos();
    s.append(LINE_SEPARATOR).append("      // int [] blockKeys");
    s.append(LINE_SEPARATOR).append("      new int[] {");
    for (int i = 0; i < keys.length; ++i) {
      final int key = keys[i];
      if (i % 8 == 0) {
        s.append(LINE_SEPARATOR).append("        ");
      }
      s.append(String.format("0x%08x, ", key));
    }
    s.setLength(s.length() - 2);
    s.append("  },");
    s.append(LINE_SEPARATOR).append("      // char [][] codePointRanges");
    s.append(LINE_SEPARATOR).append("      new char[][] {");
    for (int i = 0; i < keys.length; ++i) {
      final int key = keys[i];
      final int[] froms = inclusiveFroms[i];
      final int[] tos = inclusiveTos[i];
      if (key < 0) {
        s.append(LINE_SEPARATOR).append("        null,");
      } else {
        s.append(LINE_SEPARATOR).append("        {");
        s.append(String.format(" // 0x%04x__ codePoint ranges", key));
        s.append(LINE_SEPARATOR).append("          ");
        for (int j = 0; j < froms.length; ++j) {
          if (j > 0 && j % 8 == 0) {
            s.append(LINE_SEPARATOR).append("          ");
          }
          final int from = froms[j];
          final int to = tos[j];
          s.append(String.format("0x%02x_%02x, ", from & 0xFF, to & 0xFF));
        }
        s.setLength(s.length() - 2);
        s.append("},");
      }
    }
    s.setLength(s.length() - 1);
    s.append("},");
    s.append(LINE_SEPARATOR).append("        // number of code-point ranges");
    s.append(LINE_SEPARATOR).append("        ").append(optimalHashedRangedSubsetData.getRangesSize()).append(",");
    s.append(LINE_SEPARATOR).append("        // number of code-points");
    s.append(LINE_SEPARATOR).append("        ").append(optimalHashedRangedSubsetData.getCodePointsSize());
  }

  public void organizeIntoBlockRanged(final UnicodeSet unicodeSet) {

    final Set<Integer> blockKeys = new TreeSet();
    for (EntryRange entryRange : unicodeSet.ranges()) {
      final int inclusiveFrom = entryRange.codepoint;
      final int inclusiveTo = entryRange.codepointEnd;
      final int blockKeyFrom = inclusiveFrom >> 8;
      final int blockKeyTo = inclusiveTo >> 8;
      for (int blockKey = blockKeyFrom; blockKey <= blockKeyTo; ++blockKey) {
        blockKeys.add(blockKey);
      }
    }
    final StringBuilder s = new StringBuilder();
    final HashSlotData hashSlotData = findOptimalNumberOfBlockKeyHashSlots(blockKeys);
    if (hashSlotData.hasMultipleKeysPerSlot()) {
      s.append(LINE_SEPARATOR).append("  // hashSlotData.getSlots().size() =").append(hashSlotData.getSlots().size());
      s.append(LINE_SEPARATOR).append("  final int [][] blockKeys = new int[][]{");
      for (Map.Entry<Integer, SortedSet<Integer>> slot : hashSlotData.getSlots().entrySet()) {
        s.append(LINE_SEPARATOR).append("    {");
        if (slot.getValue() == null || slot.getValue().isEmpty()) {
          s.append("      null, ");
        } else {
          for (int blockKey : slot.getValue()) {
            s.append(String.format("      0x%04X", blockKey)).append(", ");
          }
        }
        s.setLength(s.length() - 2);
        s.append("    },");
      }
      s.setLength(s.length() - 1);
      s.append(LINE_SEPARATOR).append("  }");
    } else {
      s.append(LINE_SEPARATOR).append("  // hashSlotData.getSlots().size() =").append(hashSlotData.getSlots().size());
      s.append(LINE_SEPARATOR).append("  final int [] blockKeys = new int[] {");
      for (Map.Entry<Integer, SortedSet<Integer>> slot : hashSlotData.getSlots().entrySet()) {
        if (slot.getValue() == null || slot.getValue().isEmpty()) {
          s.append(LINE_SEPARATOR).append("      null,");
        } else {
          int blockKey = slot.getValue().first();
          s.append(LINE_SEPARATOR).append(String.format("      0x%04X", blockKey)).append(",");
        }
      }
      s.setLength(s.length() - 1);
      s.append("    }");
      s.append(LINE_SEPARATOR).append("  }");
    }
    logger.info("Block language:\n" + s);
  }

  HashSlotData findOptimalNumberOfBlockKeyHashSlots(final Set<Integer> blockKeys) {
    final int blockKeyCount = blockKeys.size();
    final int start = Math.max(7, (int) Math.floor(blockKeyCount * 0.7D));
    final int end = Math.max(7, (int) Math.ceil(blockKeyCount * 2.1D));
    final List<HashSlotData> hashSlotDataList = new ArrayList<>();
    for (int i = start; i <= end; ++i) {
      final HashSlotData hashSlotData = new HashSlotData(i);
      for (int blockKey : blockKeys) {
        hashSlotData.addBlockKey(blockKey);
      }
      hashSlotDataList.add(hashSlotData);
    }
    hashSlotDataList.sort(Comparator.comparingDouble(HashSlotData::getWeight));
    final StringBuilder s = new StringBuilder();
    s.append(LINE_SEPARATOR).append("keys  weight  slots  0-keys  1-key  2-keys  3-keys  4-keys  5+keys");
    for (HashSlotData hashSlotData : hashSlotDataList) {
      s.append(LINE_SEPARATOR).append(hashSlotData);
    }
    logger.info("" + s);
    return hashSlotDataList.get(0);
  }

  private static class HashSlotData {

    private final int numberOfSlots;

    private final int[] hashSlots;

    private int countOfBlockKeys = 0;
    boolean hasMultipleKeysPerSlot = false;

    private final TreeMap<Integer, SortedSet<Integer>> slots;

    public HashSlotData(int numberOfSlots) {
      this.numberOfSlots = numberOfSlots;
      this.hashSlots = new int[numberOfSlots];
      this.slots = new TreeMap<>();
      for (int i = 0; i < numberOfSlots; ++i) {
        this.slots.put(i, new TreeSet<>());
      }
    }

    public int getNumberOfSlots() {
      return numberOfSlots;
    }

    public SortedMap<Integer, SortedSet<Integer>> getSlots() {
      return slots;
    }

    double getWeight() {
      int countOfHashSlotsWithNoKeys = 0;
      int countOfHashSlotsWithExactly1Key = 0;
      int countOfHashSlotsWithExactly2Keys = 0;
      int countOfHashSlotsWithExactly3Keys = 0;
      int countOfHashSlotsWithExactly4Keys = 0;
      int countOfHashSlotsWith5OrMoreKeys = 0;

      for (Map.Entry<Integer, SortedSet<Integer>> entry : slots.entrySet()) {
        switch (entry.getValue().size()) {
          case 0:
            countOfHashSlotsWithNoKeys++;
            break;
          case 1:
            countOfHashSlotsWithExactly1Key++;
            break;
          case 2:
            countOfHashSlotsWithExactly2Keys++;
            break;
          case 3:
            countOfHashSlotsWithExactly3Keys++;
            break;
          case 4:
            countOfHashSlotsWithExactly4Keys++;
            break;
          default:
            countOfHashSlotsWith5OrMoreKeys++;
            break;
        }
      }
      return 0
          // memory of keys 2D - array
//          + 8 // reference to 1st level keys array (8 bytes)
//          + 8 * numberOfSlots // reference to 2nd level keys arrays (may be null)
//          + 4D * countOfBlockKeys // 4 byte block keys stored in 2nd level keys arrays
//          + 8 // reference to 1st level values array (8 bytes)
//          + 8 * numberOfSlots // reference to 2nd level values arrays (may be null)
////              + 2D * numberOfCodePoints // 4 byte block keys stored in 2nd level keys arrays
//          + 2.0 * (
          + numberOfSlots * 16D // penalty for too many slots
          + countOfHashSlotsWithNoKeys * 4D // penalty for wasting space
          + countOfHashSlotsWithExactly1Key * 1D
          + countOfHashSlotsWithExactly2Keys * 32D // performance penalty for iterating over multiple keys
          + countOfHashSlotsWithExactly3Keys * 512D // performance penalty for iterating over multiple keys
          + countOfHashSlotsWithExactly4Keys * 1000D // performance penalty for iterating over multiple keys
          + countOfHashSlotsWith5OrMoreKeys * 10000D; // performance penalty for iterating over multiple keys
    }

    @Override
    public String toString() {
      int countOfHashSlotsWithNoKeys = 0;
      int countOfHashSlotsWithExactly1Key = 0;
      int countOfHashSlotsWithExactly2Keys = 0;
      int countOfHashSlotsWithExactly3Keys = 0;
      int countOfHashSlotsWithExactly4Keys = 0;
      int countOfHashSlotsWith5OrMoreKeys = 0;

      for (Map.Entry<Integer, SortedSet<Integer>> entry : slots.entrySet()) {
        switch (entry.getValue().size()) {
          case 0:
            countOfHashSlotsWithNoKeys++;
            break;
          case 1:
            countOfHashSlotsWithExactly1Key++;
            break;
          case 2:
            countOfHashSlotsWithExactly2Keys++;
            break;
          case 3:
            countOfHashSlotsWithExactly3Keys++;
            break;
          case 4:
            countOfHashSlotsWithExactly4Keys++;
            break;
          default:
            countOfHashSlotsWith5OrMoreKeys++;
            break;
        }
      }
      return String.format("%4d  %5d   %6d  %6d  %5d  %6d  %6d  %6d  %6d",
          countOfBlockKeys, numberOfSlots, (int) getWeight(),
          countOfHashSlotsWithNoKeys, countOfHashSlotsWithExactly1Key,
          countOfHashSlotsWithExactly2Keys, countOfHashSlotsWithExactly3Keys,
          countOfHashSlotsWithExactly4Keys, countOfHashSlotsWith5OrMoreKeys);
//
//      s.append("\nkeys   ").append(countOfBlockKeys);
//      s.append("\nslots  ").append(numberOfSlots);
//      s.append("\nweight ").append(getWeight());
//      s.append("\n0 keys ").append(countOfHashSlotsWithNoKeys);
//      s.append("\n1 keys ").append(countOfHashSlotsWithExactly1Key);
//      s.append("\n2 keys ").append(countOfHashSlotsWithExactly2Keys);
//      s.append("\n3 keys ").append(countOfHashSlotsWithExactly3Keys);
//      s.append("\n4 keys ").append(countOfHashSlotsWithExactly4Keys);
//      s.append("\n5 keys ").append(countOfHashSlotsWith5OrMoreKeys);
//      return s.toString();
    }

    boolean hasMultipleKeysPerSlot() {
      return hasMultipleKeysPerSlot;
    }

    public void addBlockKey(final int blockKey) {
      countOfBlockKeys++;
      final int hashSlot = blockKey % numberOfSlots;
      hashSlots[hashSlot]++;
      SortedSet<Integer> blockKeys = slots.get(hashSlot);
      blockKeys.add(blockKey);
      if (blockKeys.size() > 1) {
        hasMultipleKeysPerSlot = true;
      }
    }

  }

  private void createAlphabetCharactersHtml(
      final LanguageData languageData) {

    final UnicodeSet unicodeSet = languageData.getUnicodeSet();

    final StringBuilder s = new StringBuilder();

    if (unicodeSet == null || unicodeSet.isEmpty()) {
      return;
    }
    s.append(String.format("""
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <title>Characters &ndash; %s %s</title>
                <meta content="text/html; charset=utf-8" />
            </head>
            <body>
            <h1>%s</h1>
            <h1>Characters include in the %s %s set.</h1>
            <pre lang="%s">""",
        languageData.getTargetEnumName(),
        languageData.getLocaleDisplayLanguage(),
        languageData.getLocaleLanguageTag(),
        languageData.getLocaleDisplayLanguage(),
        languageData.getLocaleLanguageTag(),
        languageData.getLocale().getName()));

    for (EntryRange range : unicodeSet.ranges()) {
      final int from = range.codepoint;
      final int to = range.codepointEnd;
      s.append(LINE_SEPARATOR);
      s.append(LINE_SEPARATOR).append("-------------------------").append(LINE_SEPARATOR);
      s.append(String.format("%04X..%04X  Unicode Range ", from, to));
      s.append(LINE_SEPARATOR).append("-------------------------");
      if (range.codepointEnd > range.codepoint) {
        for (int c = from, i = 0; c <= to; ++c, ++i) {
          if (i % 32 == 0 && to - c > 0) {
            s.append(LINE_SEPARATOR);
            s.append(String.format("%04X..%04X  ", from + i, from + i + 31));
          }
          s.append(' ').appendCodePoint(c);
        }
      } else {
        s.append(LINE_SEPARATOR);
        s.append(String.format("%04X        ", from));
        for (int c = from; c <= to; ++c) {
          s.append(' ').appendCodePoint(c);
        }
      }
    }
    s.append("""
        </pre>
        </body>
        </html>""");

    final File docFilesDirectory = new File(outputDirectory + File.separator + "doc-files");
    final File filePath = new File(docFilesDirectory + File.separator + languageData.getTargetEnumName() + ".html");
    docFilesDirectory.mkdirs();
    try (final FileWriter fileWriter = new FileWriter(filePath)) {
      fileWriter.append(s.toString());
      fileWriter.flush();
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }
}
