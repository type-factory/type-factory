package org.datatypeproject.generator.letters;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static org.datatypeproject.generator.letters.JavadocFragments.LANGUAGE_ALPHABET_INCLUDED_JAVADOC;
import static org.datatypeproject.generator.letters.LettersData.LETTERS_JAPANESE_JA_HANI;

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

public class LettersClassGenerator {

  private static final Logger logger = Logger.getLogger(LettersClassGenerator.class.getName());

  private static final String LINE_SEPARATOR = System.lineSeparator();

  private final File outputDirectory;
  private final UnicodeGroupData unicodeGroupData;

  public LettersClassGenerator(final File outputDirectory, final UnicodeGroupData unicodeGroupData) {
    this.outputDirectory = outputDirectory;
    this.unicodeGroupData = unicodeGroupData;
  }

  public void generateLanguageClass() {

    final StringBuilder s = new StringBuilder();

    s.append("""
        package org.datatypeproject.language;
                
        import java.util.HashMap;
        import java.util.Locale;
        import javax.annotation.processing.Generated;
        import org.datatypeproject.Subset;
        import org.datatypeproject.impl.Factory;
                
        @Generated(
            comments = "This file is generated from data in the LanguageData class in the data-type-letters-code-generator module.",
            value = "org.datatypeproject:data-type-letters-code-generator")     
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

    for (LettersData lettersData : LettersData.values()) {
      final ULocale locale = lettersData.getLocale();
      final String localeLanguage = locale.getLanguage();
      final String localeCountry = locale.getCountry();
      final String localeVariant = locale.getVariant();
      final String localeScript = locale.getScript();
      final String localeLanguageTag = locale.toLanguageTag().replaceAll("[\s_-]+", "_");
      final String displayLanguage = locale.getDisplayLanguage().replaceAll("[\s_-]+", "_");
      final String enumName = String.format("%s_%s", displayLanguage.toUpperCase(), localeLanguageTag);
      final String lettersClassName = String.format("Letters_%s_%s", displayLanguage, localeLanguageTag);

      s.append(LINE_SEPARATOR);
      appendJavadoc(s, lettersData, enumName);
      if ("Hani".equalsIgnoreCase(localeScript)) {
        createAlphabetCharactersTxt(lettersData);
      }

      s.append(String.format("  public static final Subset %s = registerSubset(", enumName));
      if (locale.getScript() == null || locale.getScript().isBlank()) {
        s.append(String.format("%n      new Locale(\"%s\", \"%s\", \"%s\"),",
            localeLanguage, localeCountry, localeVariant));
      } else {
        s.append(String.format("%n      new Locale.Builder().setLanguage(\"%s\").setRegion(\"%s\").setVariant(\"%s\").setScript(\"%s\").build(),",
            localeLanguage, localeCountry, localeVariant, localeScript));
      }

      switch (lettersData) {
        case LETTERS_JAPANESE_JA_JINMEIYO:
        case LETTERS_JAPANESE_JA_JOYO:
        case LETTERS_JAPANESE_JA_JSOURCE:
          final String letterClassName = generateLettersClassForSingleLanguage(lettersData);
          s.append(LINE_SEPARATOR).append("      ").append(letterClassName).append(".SUBSET);");
          break;
        default:
          s.append(LINE_SEPARATOR).append("      Factory.rangedSubset(");
          final Sizes singleByteSizes = appendCodepointArrayRanges(s, lettersData, 0x00, 0xFF, "char", "0x%02x_%02x");
          final Sizes doubleByteSizes = appendCodepointArrayRanges(s, lettersData, 0x0100, 0xFFFF, "int", "0x%04x_%04x");
          final Sizes tripleByteSizes = appendCodepointArrayRanges(s, lettersData, 0x00010000, MAX_VALUE, "long", "0x%08x_%08xL");
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

  private String generateLettersClassForSingleLanguage(final LettersData lettersData) {
    final ULocale locale = lettersData.getLocale();
    final String localeLanguageTag = locale.toLanguageTag().replaceAll("[\s_-]+", "_");
    final String displayLanguage = locale.getDisplayLanguage().replaceAll("[\s_-]+", "_");
    final String lettersClassName = String.format("Letters_%s_%s", displayLanguage, localeLanguageTag);
    final SubsetOptimiser subsetOptimiser = new SubsetOptimiser(lettersData.getUnicodeSet());

    final StringBuilder s = new StringBuilder();

    s.append(String.format("""
        package org.datatypeproject.language;
                
        import javax.annotation.processing.Generated;
        import org.datatypeproject.Subset;
        import org.datatypeproject.impl.Factory;
                
        @Generated(
            comments = "This file is generated from data in the LanguageData class in the data-type-letters-code-generator module.",
            value = "org.datatypeproject:data-type-letters-code-generator")     
        class %s {
                
        """, lettersClassName));

    if (subsetOptimiser.containsHashBucketsWithMultipleKeys()) {
      s.append("  static final Subset SUBSET = Factory.hashedRangedSubset(").append(LINE_SEPARATOR);
      appendHashedBlockRangedSubset(s, lettersData, subsetOptimiser);
    } else {
      s.append("  static final Subset SUBSET = Factory.optimalHashedRangedSubset(").append(LINE_SEPARATOR);
      appendOptimalHashedBlockRangedSubset(s, lettersData, subsetOptimiser);
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
      final LettersData lettersData,
      final int rangeStart,
      final int rangeEnd,
      final String rangeArrayType,
      final String rangeFormat) {

    final UnicodeSet unicodeSet = lettersData.getUnicodeSet();
    final String indentedRangeFormat = "          " + rangeFormat + ", // ";
    if (unicodeSet == null || unicodeSet.isEmpty()) {
      return new Sizes(0, 0);
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
          if (lettersData == LETTERS_JAPANESE_JA_HANI) {
            s.append(LINE_SEPARATOR)
                .append("      // See Javadoc for full set of unicode code points in the following ranges.");
          }
          s.append(LINE_SEPARATOR).append("        new ").append(rangeArrayType).append("[]{").append(LINE_SEPARATOR);
          arrayStarted = true;
        }
        s.append(String.format(indentedRangeFormat, max(rangeStart, from), min(rangeEnd, to)));
        rangesSize++;
        codePointsSize += (to - from + 1);
        if (lettersData == LETTERS_JAPANESE_JA_HANI) {
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
      final LettersData lettersData,
      final String enumName) {

    if (lettersData.hasJavadoc()) {
      s.append(LINE_SEPARATOR).append("  /**");
      final String javadocLineStart = LINE_SEPARATOR + "   * ";
      for (String javadoc : lettersData.getJavadoc()) {
        s.append(LINE_SEPARATOR).append("   * ");
        s.append(javadoc.replace(LINE_SEPARATOR, javadocLineStart));
        s.append(LINE_SEPARATOR).append("   *");
        if (LANGUAGE_ALPHABET_INCLUDED_JAVADOC.equals(javadoc)) {
          appendAlphabetCharactersJavadoc(s, lettersData, enumName);
          s.append(LINE_SEPARATOR).append("   *");
        }
      }
      s.append("/").append(LINE_SEPARATOR);
    }
  }

  @SuppressWarnings("java:S3776")
  private static void appendAlphabetCharactersJavadoc(
      final StringBuilder s,
      final LettersData lettersData,
      final String enumName) {

    final UnicodeSet unicodeSet = lettersData.getUnicodeSet();

    if (unicodeSet == null || unicodeSet.isEmpty()) {
      return;
    }
    switch (lettersData) {
      case LETTERS_JAPANESE_JA_HANI:
      case LETTERS_JAPANESE_JA_JINMEIYO:
      case LETTERS_JAPANESE_JA_JSOURCE:
        s.append(LINE_SEPARATOR).append("   * <p>There are too many unicode code-points (characters) in this set to display here. See separate ");
        s.append(LINE_SEPARATOR).append("   *    <a href='./doc-files/").append(enumName).append(".txt'>").append(enumName)
            .append(" documentation file</a>");
        s.append(LINE_SEPARATOR).append("   *    for a complete list of the unicode code-points in this set.</p>");
        break;
      default:
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
        break;
    }
  }

  private static void appendHashedBlockRangedSubset(
      final StringBuilder s,
      final LettersData lettersData,
      final SubsetOptimiser subsetOptimiser) {

    final var hashedRangedSubsetData = subsetOptimiser.getHashedRangedSubsetData();
    final char[][] keys = hashedRangedSubsetData.getBlockKeys();
    final int[][][] inclusiveFroms = hashedRangedSubsetData.getInclusiveFroms();
    final int[][][] inclusiveTos = hashedRangedSubsetData.getInclusiveTos();
    s.append(LINE_SEPARATOR).append("      // Hash-buckets with 0..n keys – null indicates an empty hash-bucket.");
    s.append(LINE_SEPARATOR).append("      //");
    s.append(LINE_SEPARATOR).append("      //       ┌──── hashIndex       - an index to the hash-bucket");
    s.append(LINE_SEPARATOR).append("      //       │  ┌─ hashBucketIndex - an index to the key within the hash-bucket");
    s.append(LINE_SEPARATOR).append("      //       │  │");
    s.append(LINE_SEPARATOR).append("      //  char[ ][ ] blockKeys");
    s.append(LINE_SEPARATOR).append("      new char[ ][ ] {");
    for (int i = 0; i < keys.length; ++i) {
      final char[] buckets = keys[i];
      if (i % 8 == 0) {
        s.append(LINE_SEPARATOR).append("        ");
      }
      final StringBuilder temp = new StringBuilder();
      if (buckets == null || buckets.length == 0) {
        temp.append(" null           ");
      } else {
        switch (buckets.length) {
          case 1:
            temp.append(String.format("{0x%04x}        ", (int) buckets[0]));
            break;
          case 2:
            temp.append(String.format("{0x%04x, 0x%04x}", (int) buckets[0], (int) buckets[1]));
            break;
          default:
            temp.append("{");
            for (int j = 0; j < buckets.length; ++j) {
              temp.append(String.format("0x%04x, ", (int) buckets[j]));
            }
            temp.append("}");
            break;
        }
      }
      s.append(String.format("%-16s, ", temp));
    }
    s.setLength(s.length() - 2);
    s.append("  },");
    s.append(LINE_SEPARATOR);
    s.append(LINE_SEPARATOR).append("      //       ┌─────── hashIndex           - an index to the hash-bucket");
    s.append(LINE_SEPARATOR).append("      //       │  ┌──── hashBucketIndex     - an index to the key within the hash-bucket");
    s.append(LINE_SEPARATOR).append("      //       │  │  ┌─ codePointRangeIndex - an index to the range within the array of ranges");
    s.append(LINE_SEPARATOR).append("      //       │  │  │");
    s.append(LINE_SEPARATOR).append("      //  char[ ][ ][ ] codePointRanges");
    s.append(LINE_SEPARATOR).append("      new char[ ][ ][ ] {");
    for (int i = 0; i < keys.length; ++i) {
      final char[] keyBuckets = keys[i];
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
      final LettersData lettersData,
      final SubsetOptimiser subsetOptimiser) {

    final var optimalHashedRangedSubsetData = subsetOptimiser.getOptimalHashedRangedSubsetData();
    final char[] keys = optimalHashedRangedSubsetData.getBlockKeys();
    final int[][] inclusiveFroms = optimalHashedRangedSubsetData.getInclusiveFroms();
    final int[][] inclusiveTos = optimalHashedRangedSubsetData.getInclusiveTos();
    s.append(LINE_SEPARATOR).append("      // Hash-buckets with 0..1 keys – 0xffff indicates an empty hash-bucket.");
    s.append(LINE_SEPARATOR).append("      //");
    s.append(LINE_SEPARATOR).append("      //       ┌─ hashIndex - an index to the hash-bucket which has at most one key");
    s.append(LINE_SEPARATOR).append("      //       │");
    s.append(LINE_SEPARATOR).append("      //  char[ ] blockKeys");
    s.append(LINE_SEPARATOR).append("      new char[ ] {");
    for (int i = 0; i < keys.length; ++i) {
      final int key = keys[i];
      if (i % 8 == 0) {
        s.append(LINE_SEPARATOR).append("        ");
      }
      s.append(String.format("0x%04x, ", key));
    }
    s.setLength(s.length() - 2);
    s.append("  },");
    s.append(LINE_SEPARATOR);
    s.append(LINE_SEPARATOR).append("      //       ┌──── hashIndex           - an index to the hash-bucket");
    s.append(LINE_SEPARATOR).append("      //       │  ┌─ codePointRangeIndex - an index to the range within the array of ranges");
    s.append(LINE_SEPARATOR).append("      //       │  │");
    s.append(LINE_SEPARATOR).append("      //  char[ ][ ] codePointRanges");
    s.append(LINE_SEPARATOR).append("      new char[ ][ ] {");
    for (int i = 0; i < keys.length; ++i) {
      final int key = keys[i];
      final int[] froms = inclusiveFroms[i];
      final int[] tos = inclusiveTos[i];
      if (key == 0xFFFF) {
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

  private void createAlphabetCharactersTxt(
      final LettersData lettersData) {

    final UnicodeSet unicodeSet = lettersData.getUnicodeSet();

    final String headerLine = "===================================================================================================";

    final StringBuilder s = new StringBuilder();

    if (unicodeSet == null || unicodeSet.isEmpty()) {
      return;
    }

    int headingStart = s.length();
    s.append(lettersData.getTargetEnumName());
    s.append(LINE_SEPARATOR).append(headerLine, 0, s.length() - headingStart);
    s.append(LINE_SEPARATOR).append(LINE_SEPARATOR);

    headingStart = s.length();
    s.append("Characters include in the ").append(lettersData.getTargetEnumName()).append(" ");
    s.append(lettersData.getLocaleLanguageTag()).append(" set");
    s.append(LINE_SEPARATOR).append(headerLine, 0, s.length() - headingStart);

    s.append(LINE_SEPARATOR).append(LINE_SEPARATOR);
    s.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    for (EntryRange range : unicodeSet.ranges()) {
      final int from = range.codepoint;
      final int to = range.codepointEnd;
      if (to > from) {
        for (int c = from, i = 0; c <= to; ++c, ++i) {
          if (i % 32 == 0) {
            s.append(LINE_SEPARATOR);
            s.append(String.format("%06x..%06x  ", c, Math.min(to, c + 31)));
          }
          s.append(' ').appendCodePoint(c);
        }
      } else {
        s.append(LINE_SEPARATOR);
        s.append(String.format("%06x          ", from));
        s.append(' ').appendCodePoint(from);
      }
    }

    s.append(LINE_SEPARATOR).append(LINE_SEPARATOR);
    s.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    s.append(LINE_SEPARATOR).append(LINE_SEPARATOR);

    final File docFilesDirectory = new File(outputDirectory + File.separator + "doc-files");
    final File filePath = new File(docFilesDirectory + File.separator + lettersData.getTargetEnumName() + ".txt");
    docFilesDirectory.mkdirs();
    try (final FileWriter fileWriter = new FileWriter(filePath)) {
      fileWriter.append(s.toString());
      fileWriter.flush();
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }
}
