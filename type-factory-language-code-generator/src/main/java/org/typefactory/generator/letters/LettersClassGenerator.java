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
package org.typefactory.generator.letters;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static org.typefactory.generator.letters.JavadocFragments.LANGUAGE_ALPHABET_INCLUDED_JAVADOC;
import static org.typefactory.generator.letters.LettersData.LETTERS_JAPANESE_JA_HANI;

import com.ibm.icu.text.UnicodeSet;
import com.ibm.icu.text.UnicodeSet.EntryRange;
import com.ibm.icu.util.ULocale;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.typefactory.generator.unicodedata.UnicodeGroupData;
import org.typefactory.impl.HashedRangedSubsetWrapper;
import org.typefactory.impl.InternalSubsetUtils;
import org.typefactory.impl.OptimalHashedRangedSubsetWrapper;
import org.typefactory.impl.SubsetWrapper;

public class LettersClassGenerator {

  private static final Logger logger = Logger.getLogger(LettersClassGenerator.class.getName());

  private static final String LINE_SEPARATOR = System.lineSeparator();

  private final String licenseHeader;
  private final File outputDirectory;

  public LettersClassGenerator(
      final String licenseHeader,
      final File outputDirectory,
      final UnicodeGroupData unicodeGroupData) {
    this.licenseHeader = licenseHeader;
    this.outputDirectory = outputDirectory;
  }

  public void generateLanguageClass() {

    final StringBuilder s = new StringBuilder();

    s.append(licenseHeader)
        .append("""
        package org.typefactory.language;
                
        import java.util.HashMap;
        import java.util.Locale;
        import javax.annotation.processing.Generated;
        import org.typefactory.Subset;
        import org.typefactory.impl.Factory;
                
        @Generated(
            comments = "This file is generated from data in the LanguageData class in the type-factory-language-code-generator module.",
            value = "org.typefactory:type-factory-language-code-generator") 
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
      final String localeLanguageTag = tokenize(locale.toLanguageTag());
      final String displayLanguage = tokenize(locale.getDisplayLanguage());
      final String enumName = String.format("%s_%s", displayLanguage.toUpperCase(), localeLanguageTag);

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
        case LETTERS_JAPANESE_JA_JINMEIYO, LETTERS_JAPANESE_JA_JOYO, LETTERS_JAPANESE_JA_JSOURCE:
          final String letterClassName = generateLettersClassForSingleLanguage(lettersData);
          s.append(LINE_SEPARATOR).append("      ").append(letterClassName).append(".SUBSET);");
          break;
        default:
          s.append(LINE_SEPARATOR).append("      Factory.rangedSubset(");
          final Sizes singleByteSizes = appendCodepointArrayRanges(s, lettersData, 0x00, 0xFF, "char", "0x%02x_%02x");
          final Sizes doubleByteSizes = appendCodepointArrayRanges(s, lettersData, 0x0100, 0xFFFF, "int", "0x%04x_%04x");
          final Sizes tripleByteSizes = appendCodepointArrayRanges(s, lettersData, 0x00010000, MAX_VALUE, "long", "0x%08x_%08xL");
          s.append(LINE_SEPARATOR).append("      ");
          s.append(singleByteSizes.numberOfCodePointRanges
              + doubleByteSizes.numberOfCodePointRanges
              + tripleByteSizes.numberOfCodePointRanges).append(", ");
          s.append(singleByteSizes.numberOfCodePointsInCodePointRanges
              + doubleByteSizes.numberOfCodePointsInCodePointRanges
              + tripleByteSizes.numberOfCodePointsInCodePointRanges);
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

  private static String tokenize(final String locale) {
    return locale.replaceAll("[\\s_-]+", "_");
  }

  private String generateLettersClassForSingleLanguage(final LettersData lettersData) {
    final ULocale locale = lettersData.getLocale();
    final String localeLanguageTag = tokenize(locale.toLanguageTag());
    final String displayLanguage = tokenize(locale.getDisplayLanguage());
    final String lettersClassName = String.format("Letters_%s_%s", displayLanguage, localeLanguageTag);
    System.out.println("\n\nCreating subset for " + lettersClassName);
    final SubsetWrapper subsetWrapper = SubsetWrapper.optimisedSubset(lettersData.getUnicodeSet());

    final StringBuilder s = new StringBuilder();

    s.append(licenseHeader)
        .append(String.format("""
        package org.typefactory.language;
                
        import javax.annotation.processing.Generated;
        import org.typefactory.Subset;
        import org.typefactory.impl.Factory;
                
        @Generated(
            comments = "This file is generated from data in the LanguageData class in the type-factory-language-code-generator module.",
            value = "org.typefactory:type-factory-language-code-generator")     
        class %s {
                
        """, lettersClassName));

    if (subsetWrapper instanceof HashedRangedSubsetWrapper hashedRangedSubsetWrapper) {
      s.append("  static final Subset SUBSET = Factory.hashedRangedSubset(").append(LINE_SEPARATOR);
      appendHashedBlockRangedSubset(s, lettersData, hashedRangedSubsetWrapper);
    } else if (subsetWrapper instanceof OptimalHashedRangedSubsetWrapper optimalHashedRangedSubsetWrapper) {
      s.append("  static final Subset SUBSET = Factory.optimalHashedRangedSubset(").append(LINE_SEPARATOR);
      appendOptimalHashedBlockRangedSubset(s, lettersData, optimalHashedRangedSubsetWrapper);
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

    public final int numberOfCodePointRanges;
    public final int numberOfCodePointsInCodePointRanges;

    public Sizes(final int numberOfCodePointRanges, final int numberOfCodePointsInCodePointRanges) {
      this.numberOfCodePointRanges = numberOfCodePointRanges;
      this.numberOfCodePointsInCodePointRanges = numberOfCodePointsInCodePointRanges;
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
    int numberOfCodePointRanges = 0;
    int numberOfCodePointsInCodePointRanges = 0;
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
        numberOfCodePointRanges++;
        numberOfCodePointsInCodePointRanges += (to - from + 1);
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
    return new Sizes(numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);
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
      case LETTERS_JAPANESE_JA_HANI, LETTERS_JAPANESE_JA_JINMEIYO, LETTERS_JAPANESE_JA_JSOURCE -> {
        s.append(LINE_SEPARATOR).append("   * <p>There are too many unicode code-points (characters) in this set to display here. See separate ");
        s.append(LINE_SEPARATOR).append("   *    <a href='./doc-files/").append(enumName).append(".txt'>").append(enumName)
            .append(" documentation file</a>");
        s.append(LINE_SEPARATOR).append("   *    for a complete list of the unicode code-points in this set.</p>");
      }
      default -> {
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
  }

  private static void appendHashedBlockRangedSubset(
      final StringBuilder s,
      final LettersData lettersData,
      final HashedRangedSubsetWrapper hashedRangedSubsetWrapper) {

    final char[][] keys = hashedRangedSubsetWrapper.getBlockKeys();
    final char[][][] codePointRangesByBlock = hashedRangedSubsetWrapper.getCodePointRangesByBlock();
    s.append(LINE_SEPARATOR).append("      // Hash-buckets with 0..n keys – null indicates an empty hash-bucket.");
    s.append(LINE_SEPARATOR).append("      //");
    s.append(LINE_SEPARATOR).append("      //       ┌──── hashIndex       - an index to the hash-bucket");
    s.append(LINE_SEPARATOR).append("      //       │  ┌─ hashBucketIndex - an index to the key within the hash-bucket");
    s.append(LINE_SEPARATOR).append("      //       │  │");
    s.append(LINE_SEPARATOR).append("      //  char[ ][ ] blockKeys");
    s.append(LINE_SEPARATOR).append("      new char[ ][ ] {");
    for (int hashIndex = 0; hashIndex < keys.length; ++hashIndex) {
      final char[] buckets = keys[hashIndex];
      if (hashIndex % 8 == 0) {
        s.append(LINE_SEPARATOR).append("        ");
      }
      final StringBuilder temp = new StringBuilder();
      if (buckets == null || buckets.length == 0) {
        temp.append(" null           ");
      } else {
        switch (buckets.length) {
          case 1 -> temp.append(String.format("{0x%04x}        ", (int) buckets[0]));
          case 2 -> temp.append(String.format("{0x%04x, 0x%04x}", (int) buckets[0], (int) buckets[1]));
          default -> {
            temp.append("{");
            for (char bucket : buckets) {
              temp.append(String.format("0x%04x, ", (int) bucket));
            }
            temp.append("}");
          }
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
    for (int hashIndex = 0; hashIndex < keys.length; ++hashIndex) {
      final char[] keyBuckets = keys[hashIndex];
      if (keyBuckets == null) {
        s.append(LINE_SEPARATOR).append("        null,");
      } else {
        s.append(LINE_SEPARATOR).append("        {");
        for (int hashBucketIndex = 0; hashBucketIndex < keyBuckets.length; ++hashBucketIndex) {
          final int key = keyBuckets[hashBucketIndex];
          if (hashBucketIndex > 0) {
            s.append(LINE_SEPARATOR).append("         ");
          }
          s.append(String.format(" // 0x%04x__ codePoint ranges", key));
          s.append(LINE_SEPARATOR).append("          ");
          final char[] codePointRanges = codePointRangesByBlock[hashIndex][hashBucketIndex];
          s.append("{");
          for (int codePointRangeIndex = 0; codePointRangeIndex < codePointRanges.length; ++codePointRangeIndex) {
            if (codePointRangeIndex > 0 && codePointRangeIndex % 8 == 0) {
              s.append(LINE_SEPARATOR).append("           ");
            }
            final int from = InternalSubsetUtils.getInclusiveFrom(codePointRanges[codePointRangeIndex]);
            final int to = InternalSubsetUtils.getInclusiveTo(codePointRanges[codePointRangeIndex]);
            s.append(String.format("0x%02x_%02x, ", from & 0xFF, to & 0xFF));
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
    s.append(LINE_SEPARATOR).append("        ").append(hashedRangedSubsetWrapper.numberOfCodePointRanges()).append(",");
    s.append(LINE_SEPARATOR).append("        // number of code-points");
    s.append(LINE_SEPARATOR).append("        ").append(hashedRangedSubsetWrapper.numberOfCodePointsInCodePointRanges());
  }

  private static void appendOptimalHashedBlockRangedSubset(
      final StringBuilder s,
      final LettersData lettersData,
      final OptimalHashedRangedSubsetWrapper optimalHashedRangedSubsetWrapper) {

    final char[] keys = optimalHashedRangedSubsetWrapper.getBlockKeys();
    final char[][] codePointRangesByBlock = optimalHashedRangedSubsetWrapper.getCodePointRangesByBlock();
    s.append(LINE_SEPARATOR).append("      // Hash-buckets with 0..1 keys – 0xffff indicates an empty hash-bucket.");
    s.append(LINE_SEPARATOR).append("      //");
    s.append(LINE_SEPARATOR).append("      //       ┌─ hashIndex - an index to the hash-bucket which has at most one key");
    s.append(LINE_SEPARATOR).append("      //       │");
    s.append(LINE_SEPARATOR).append("      //  char[ ] blockKeys");
    s.append(LINE_SEPARATOR).append("      new char[ ] {");
    for (int hashIndex = 0; hashIndex < keys.length; ++hashIndex) {
      final int key = keys[hashIndex];
      if (hashIndex % 8 == 0) {
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
    int contiguousEmptyBucketCount = 0;
    for (int hashIndex = 0; hashIndex < keys.length; ++hashIndex) {
      final int key = keys[hashIndex];
      final char[] codePointRanges = codePointRangesByBlock[hashIndex];
      if (key == 0xFFFF) {
        if (contiguousEmptyBucketCount++ % 12 == 0) {
          s.append(LINE_SEPARATOR).append("         ");
        }
        s.append(" null,");
      } else {
        contiguousEmptyBucketCount = 0;
        s.append(LINE_SEPARATOR).append("        {");
        s.append(String.format(" // 0x%04x__ codePoint ranges", key));
        s.append(LINE_SEPARATOR).append("          ");
        for (int codePointRangeIndex = 0; codePointRangeIndex < codePointRanges.length; ++codePointRangeIndex) {
          if (codePointRangeIndex > 0 && codePointRangeIndex % 8 == 0) {
            s.append(LINE_SEPARATOR).append("          ");
          }
          final int from = InternalSubsetUtils.getInclusiveFrom(codePointRanges[codePointRangeIndex]);
          final int to = InternalSubsetUtils.getInclusiveTo(codePointRanges[codePointRangeIndex]);
          s.append(String.format("0x%02x_%02x, ", from & 0xFF, to & 0xFF));
        }
        s.setLength(s.length() - 2);
        s.append("},");
      }
    }
    s.setLength(s.length() - 1);
    s.append("},");
    s.append(LINE_SEPARATOR).append("        // number of code-point ranges");
    s.append(LINE_SEPARATOR).append("        ").append(optimalHashedRangedSubsetWrapper.numberOfCodePointRanges()).append(",");
    s.append(LINE_SEPARATOR).append("        // number of code-points");
    s.append(LINE_SEPARATOR).append("        ").append(optimalHashedRangedSubsetWrapper.numberOfCodePointsInCodePointRanges());
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
