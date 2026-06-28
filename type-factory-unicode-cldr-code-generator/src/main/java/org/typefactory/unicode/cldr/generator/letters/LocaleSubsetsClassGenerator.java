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

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static org.typefactory.unicode.cldr.generator.letters.JavadocFragments.LANGUAGE_ALPHABET_INCLUDED_JAVADOC;

import com.ibm.icu.text.UnicodeSet;
import com.ibm.icu.text.UnicodeSet.EntryRange;
import com.ibm.icu.util.LocaleData;
import com.ibm.icu.util.ULocale;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.typefactory.Subset;
import org.typefactory.impl.HashedRangedSubsetWrapper;
import org.typefactory.impl.InternalSubsetUtils;
import org.typefactory.impl.OptimalHashedRangedSubsetWrapper;
import org.typefactory.impl.RangedSubsetWrapper;
import org.typefactory.impl.SubsetWrapper;
import org.typefactory.unicode.cldr.generator.unicodedata.UnicodeGroupData;

public class LocaleSubsetsClassGenerator {

  private static final Logger logger = Logger.getLogger(LocaleSubsetsClassGenerator.class.getName());

  private static final String LINE_SEPARATOR = System.lineSeparator();
  public static final String INDENT_08 = "        ";
  public static final String INDENT_10 = "          ";

  private final String licenseHeader;
  private final File outputDirectory;

  public LocaleSubsetsClassGenerator(
      final String licenseHeader,
      final File outputDirectory,
      final UnicodeGroupData unicodeGroupData) {
    this.licenseHeader = licenseHeader;
    this.outputDirectory = outputDirectory;
  }

  public static Set<ULocale> getLivingLanguageLocales() {
    final var baseLocales = new TreeSet<>(Comparator.comparing(ULocale::toString));
    final var availableLocales = ULocale.getAvailableLocales();

    for (ULocale locale : availableLocales) {
      if (isLivingLanguage(locale) && locale.getCountry().isEmpty()) {
        baseLocales.add(locale);
      }
    }
    return baseLocales;
  }

  public static boolean isLivingLanguage(final ULocale uLocale) {
    try {
      // Java's Locale built-in structure helps verify its recognition as an active linguistic standard
      final String iso3Language = uLocale.getISO3Language();

      // Languages without recognized 3-letter codes in modern Java are typically extinct/historical
      return iso3Language != null && !iso3Language.isEmpty();

    } catch (final Exception e) {
      // An exception or missing ISO3 mapping heavily implies it's a non-living (e.g., historical) tag
      return false;
    }
  }

  private static Subset subsetFromUnicodeSet(final UnicodeSet exemplars) {
    final var subsetBuilder = Subset.builder();
    for (var range : exemplars.ranges()) {
      subsetBuilder.includeCodePointRange(range.codepoint, range.codepointEnd);
    }
    for (var s : exemplars.strings()) {
      for (int cp : s.codePoints().toArray()) {
        final var scp = Character.toString(cp);
        subsetBuilder.includeCodePoint(cp);
      }
    }

    return subsetBuilder.build();
  }

  public void generateLanguageClass() {

    final var locales = getLivingLanguageLocales();

    for (var locale : locales) {

      final String localeLanguage = locale.getLanguage();
      final String localeCountry = locale.getCountry();
      final String localeVariant = locale.getVariant();
      final String localeScript = locale.getScript();
      final String localeLanguageTag = tokenize(locale.toLanguageTag());
      final String displayLanguage = tokenize(locale.getDisplayLanguage());
      final String enumName = String.format("%s_%s", displayLanguage.toUpperCase().replaceAll("\\W+", "_"), localeLanguageTag);

      final UnicodeSet unicodeSet = LocaleData.getExemplarSet(locale, UnicodeSet.ADD_CASE_MAPPINGS, LocaleData.ES_STANDARD);

      if ("Hani".equalsIgnoreCase(localeScript)) {
        createAlphabetCharactersTxt(locale, unicodeSet);
      }

      final String letterClassName = generateLettersClassForSingleLanguage(locale);
    }
  }

  private static String tokenize(final String locale) {
    return locale.replaceAll("\\W+", "_");
  }

  private String generateLettersClassForSingleLanguage(final ULocale locale) {

    final String localeLanguageTag = tokenize(locale.toLanguageTag());
    final String lettersClassName = String.format("%s", localeLanguageTag);
    logger.info(() -> "Creating subset for " + lettersClassName);

    final String language = locale.getDisplayLanguage();

    final String script = locale.getDisplayScript().isEmpty()
        ? ""
        : " (" + locale.getDisplayScript() + " script)";

    final UnicodeSet standardCharactersUnicodeSet = LocaleData.getExemplarSet(locale, UnicodeSet.ADD_CASE_MAPPINGS, LocaleData.ES_STANDARD);
    final UnicodeSet auxiliaryCharactersUnicodeSet = LocaleData.getExemplarSet(locale, UnicodeSet.ADD_CASE_MAPPINGS, LocaleData.ES_AUXILIARY);
    final UnicodeSet punctuationCharactersUnicodeSet = LocaleData.getExemplarSet(locale, UnicodeSet.ADD_CASE_MAPPINGS, LocaleData.ES_PUNCTUATION);

    final StringBuilder s = new StringBuilder();

    s.append(licenseHeader)
        .append(String.format("""
            package org.typefactory.unicode.cldr;
            
            import javax.annotation.processing.Generated;
            import org.typefactory.Subset;
            import org.typefactory.impl.Factory;
            
            /**
             * Provides Type Factory subsets for the %s language%s as defined
             * by the Unicode Common Locale Data Repository (CLDR).
             */
            @Generated(
                comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
                value = "org.typefactory:type-factory-unicode-cldr-code-generator")
            public final class %s extends AbstractCldrResourceBundle {
            
              public %s() {
                super(
                    STANDARD_CHARACTERS_SUBSET,
                    AUXILIARY_CHARACTERS_SUBSET,
                    PUNCTUATION_CHARACTERS_SUBSET);
              }
            
            """, language, script, lettersClassName, lettersClassName));

    s.append(String.format("""
            /**
             * <p>The standard characters for the %s language%s as defined by the
             *    Unicode Common Locale Data Repository (CLDR).</p>
             *
             * <p>These are the characters in the {@code <exemplarCharacters>}
             *    element in the CLDR dataset.</p>
             */
          """, language, script));

    final SubsetWrapper standardCharactersSubset = SubsetWrapper.optimisedSubset(standardCharactersUnicodeSet);

    if (standardCharactersSubset instanceof RangedSubsetWrapper rangedSubsetWrapper) {
      s.append("  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(").append(LINE_SEPARATOR);
      appendRangedSubset(s, locale, rangedSubsetWrapper);
    } else if (standardCharactersSubset instanceof HashedRangedSubsetWrapper hashedRangedSubsetWrapper) {
      s.append("  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.hashedRangedSubset(").append(LINE_SEPARATOR);
      appendHashedBlockRangedSubset(s, hashedRangedSubsetWrapper);
    } else if (standardCharactersSubset instanceof OptimalHashedRangedSubsetWrapper optimalHashedRangedSubsetWrapper) {
      s.append("  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.optimalHashedRangedSubset(").append(LINE_SEPARATOR);
      appendOptimalHashedBlockRangedSubset(s, optimalHashedRangedSubsetWrapper);
    }
    s.append(");").append(LINE_SEPARATOR).append(LINE_SEPARATOR).append(LINE_SEPARATOR);


    s.append(String.format("""
            /**
             * <p>The auxiliary characters for the %s language%s as defined by the
             *    Unicode Common Locale Data Repository (CLDR).</p>
             *
             * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
             *    element in the CLDR dataset.</p>
             */
          """, language, script));

    final SubsetWrapper auxiliaryCharactersSubset = SubsetWrapper.optimisedSubset(auxiliaryCharactersUnicodeSet);

    if (auxiliaryCharactersSubset.isEmpty()) {
      s.append("  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.emptySubset();")
          .append(LINE_SEPARATOR).append(LINE_SEPARATOR).append(LINE_SEPARATOR);
    } else {
      if (auxiliaryCharactersSubset instanceof RangedSubsetWrapper rangedSubsetWrapper) {
        s.append("  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(").append(LINE_SEPARATOR);
        appendRangedSubset(s, locale, rangedSubsetWrapper);
      } else if (auxiliaryCharactersSubset instanceof HashedRangedSubsetWrapper hashedRangedSubsetWrapper) {
        s.append("  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.hashedRangedSubset(").append(LINE_SEPARATOR);
        appendHashedBlockRangedSubset(s, hashedRangedSubsetWrapper);
      } else if (auxiliaryCharactersSubset instanceof OptimalHashedRangedSubsetWrapper optimalHashedRangedSubsetWrapper) {
        s.append("  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.optimalHashedRangedSubset(").append(LINE_SEPARATOR);
        appendOptimalHashedBlockRangedSubset(s, optimalHashedRangedSubsetWrapper);
      }
      s.append(");").append(LINE_SEPARATOR).append(LINE_SEPARATOR).append(LINE_SEPARATOR);
    }


    s.append(String.format("""
            /**
             * <p>The punctuation characters for the %s language%s as defined by the
             *    Unicode Common Locale Data Repository (CLDR).</p>
             *
             * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
             *    element in the CLDR dataset.</p>
             */
          """, language, script));

    final SubsetWrapper punctuationCharactersSubset = SubsetWrapper.optimisedSubset(punctuationCharactersUnicodeSet);

    if (punctuationCharactersSubset.isEmpty()) {
      s.append("  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.emptySubset();")
          .append(LINE_SEPARATOR).append(LINE_SEPARATOR).append(LINE_SEPARATOR);
    } else {
      if (punctuationCharactersSubset instanceof RangedSubsetWrapper rangedSubsetWrapper) {
        s.append("  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(").append(LINE_SEPARATOR);
        appendRangedSubset(s, locale, rangedSubsetWrapper);
      } else if (punctuationCharactersSubset instanceof HashedRangedSubsetWrapper hashedRangedSubsetWrapper) {
        s.append("  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.hashedRangedSubset(").append(LINE_SEPARATOR);
        appendHashedBlockRangedSubset(s, hashedRangedSubsetWrapper);
      } else if (punctuationCharactersSubset instanceof OptimalHashedRangedSubsetWrapper optimalHashedRangedSubsetWrapper) {
        s.append("  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.optimalHashedRangedSubset(").append(LINE_SEPARATOR);
        appendOptimalHashedBlockRangedSubset(s, optimalHashedRangedSubsetWrapper);
      }
      s.append(");").append(LINE_SEPARATOR).append(LINE_SEPARATOR).append(LINE_SEPARATOR);
    }

    s.append("""
        }
        """);

    try (final FileWriter fileWriter = new FileWriter(
        outputDirectory + File.separator + lettersClassName + ".java")) {
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

  private static Sizes appendCodepointArrayRanges(
      final StringBuilder s,
      final ULocale locale,
      final RangedSubsetWrapper subset,
      final int rangeStart,
      final int rangeEnd,
      final String rangeArrayType,
      final String rangeFormat) {

    final String indentedRangeFormat = INDENT_10 + rangeFormat + ", // ";
    if (subset == null || subset.isEmpty()) {
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
    for (var range : subset.ranges()) {
      final int from = range.inclusiveFrom;
      final int to = range.inclusiveTo;
      if (from <= rangeEnd && to >= rangeStart) {
        if (!arrayStarted) {
          switch (locale.getCountry()) {
            case "ja", "zh":
              s.append(LINE_SEPARATOR).append("      // See Javadoc for full set of unicode code points in the following ranges.");
              break;
            default:
              break;
          }
          s.append(LINE_SEPARATOR).append("        new ").append(rangeArrayType).append("[]{").append(LINE_SEPARATOR);
          arrayStarted = true;
        }
        s.append(String.format(indentedRangeFormat, max(rangeStart, from), min(rangeEnd, to)));
        numberOfCodePointRanges++;
        numberOfCodePointsInCodePointRanges += (to - from + 1);
        switch (locale.getCountry()) {
          case "ja", "zh":
            for (int c = from; c <= min(to, from + 20); ++c) {
              s.append(' ').appendCodePoint(c);
            }
            s.append(" ...").append(LINE_SEPARATOR);
            break;
          default:
            for (int c = from, i = 1; c <= to; ++c, ++i) {
              s.append(' ').appendCodePoint(c);
              if (i % 30 == 0 && to - c > 2) {
                s.append(LINE_SEPARATOR).append(indent).append("// ");
              }
            }
            s.append(LINE_SEPARATOR);
            break;
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
      final ULocale locale,
      final UnicodeSet unicodeSet,
      final String... javadocBlocks) {

    if (javadocBlocks.length > 0) {
      s.append(LINE_SEPARATOR).append("  /**");
      final String javadocLineStart = LINE_SEPARATOR + "   * ";
      for (String javadoc : javadocBlocks) {
        s.append(LINE_SEPARATOR).append("   * ");
        s.append(javadoc.replace(LINE_SEPARATOR, javadocLineStart));
        s.append(LINE_SEPARATOR).append("   *");
        if (LANGUAGE_ALPHABET_INCLUDED_JAVADOC.equals(javadoc)) {
          appendAlphabetCharactersJavadoc(s, locale, unicodeSet);
          s.append(LINE_SEPARATOR).append("   *");
        }
      }
      s.append("/").append(LINE_SEPARATOR);
    }
  }

  private static void appendAlphabetCharactersJavadoc(
      final StringBuilder s,
      final ULocale locale,
      final UnicodeSet unicodeSet) {

    if (unicodeSet == null || unicodeSet.isEmpty()) {
      return;
    }
    switch (locale.getLanguage()) {
      case "ja", "zh", "yue" -> {
        s.append(LINE_SEPARATOR).append("   * <p>There are too many unicode code-points (characters) in this set to display here. See separate ");
        s.append(LINE_SEPARATOR).append("   *    <a href='./doc-files/").append(locale.getDisplayName()).append(".txt'>")
            .append(locale.getDisplayName()).append(" documentation file</a>");
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


//  @SuppressWarnings("java:S3776")
//  private static void appendAlphabetCharactersJavadoc(
//      final StringBuilder s,
//      final UnicodeSet unicodeSet,
//      final String enumName) {
//
//    if (unicodeSet == null || unicodeSet.isEmpty()) {
//      return;
//    }
//    switch (lettersData) {
//      case LETTERS_JAPANESE_JA_HANI, LETTERS_JAPANESE_JA_JINMEIYO, LETTERS_JAPANESE_JA_JSOURCE -> {
//        s.append(LINE_SEPARATOR).append("   * <p>There are too many unicode code-points (characters) in this set to display here. See separate ");
//        s.append(LINE_SEPARATOR).append("   *    <a href='./doc-files/").append(enumName).append(".txt'>").append(enumName)
//            .append(" documentation file</a>");
//        s.append(LINE_SEPARATOR).append("   *    for a complete list of the unicode code-points in this set.</p>");
//      }
//      default -> {
//        s.append(LINE_SEPARATOR).append("   * <pre>");
//        for (EntryRange range : unicodeSet.ranges()) {
//          final int from = range.codepoint;
//          final int to = range.codepointEnd;
//          if (range.codepointEnd > range.codepoint) {
//            s.append(LINE_SEPARATOR);
//            s.append(String.format("   *    %04X..%04X  ", from, to));
//            for (int c = from, i = 1; c <= to; ++c, ++i) {
//              s.append(' ').appendCodePoint(c);
//              if (i % 30 == 0 && to - c > 2) {
//                s.append(LINE_SEPARATOR).append("   *                ");
//              }
//            }
//          } else {
//            s.append(LINE_SEPARATOR);
//            s.append(String.format("   *    %04X        ", from));
//            for (int c = from; c <= to; ++c) {
//              s.append(' ').appendCodePoint(c);
//            }
//          }
//        }
//        s.append(LINE_SEPARATOR).append("   * </pre>");
//      }
//    }
//  }

  private static void appendRangedSubset(
      final StringBuilder s,
      final ULocale locale,
      final RangedSubsetWrapper rangedSubsetWrapper) {
//    s.append(LINE_SEPARATOR).append("      Factory.rangedSubset(");
    final Sizes singleByteSizes = appendCodepointArrayRanges(s, locale, rangedSubsetWrapper, 0x00, 0xFF, "char", "0x%02x_%02x");
    final Sizes doubleByteSizes = appendCodepointArrayRanges(s, locale, rangedSubsetWrapper, 0x0100, 0xFFFF, "int", "0x%04x_%04x");
    final Sizes tripleByteSizes = appendCodepointArrayRanges(s, locale, rangedSubsetWrapper, 0x00010000, MAX_VALUE, "long", "0x%08x_%08xL");
    s.append(LINE_SEPARATOR).append("      ");
    s.append(singleByteSizes.numberOfCodePointRanges
             + doubleByteSizes.numberOfCodePointRanges
             + tripleByteSizes.numberOfCodePointRanges).append(", ");
    s.append(singleByteSizes.numberOfCodePointsInCodePointRanges
             + doubleByteSizes.numberOfCodePointsInCodePointRanges
             + tripleByteSizes.numberOfCodePointsInCodePointRanges);
//    s.append("));");
  }

  private static void appendHashedBlockRangedSubset(
      final StringBuilder s,
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
        s.append(LINE_SEPARATOR).append(INDENT_08);
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
          s.append(LINE_SEPARATOR).append(INDENT_10);
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
    s.append(LINE_SEPARATOR).append(INDENT_08).append(hashedRangedSubsetWrapper.numberOfCodePointRanges()).append(",");
    s.append(LINE_SEPARATOR).append("        // number of code-points");
    s.append(LINE_SEPARATOR).append(INDENT_08).append(hashedRangedSubsetWrapper.numberOfCodePointsInCodePointRanges());
  }

  private static void appendOptimalHashedBlockRangedSubset(
      final StringBuilder s,
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
        s.append(LINE_SEPARATOR).append(INDENT_08);
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
        s.append(LINE_SEPARATOR).append(INDENT_10);
        for (int codePointRangeIndex = 0; codePointRangeIndex < codePointRanges.length; ++codePointRangeIndex) {
          if (codePointRangeIndex > 0 && codePointRangeIndex % 8 == 0) {
            s.append(LINE_SEPARATOR).append(INDENT_10);
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
    s.append(LINE_SEPARATOR).append(INDENT_08).append(optimalHashedRangedSubsetWrapper.numberOfCodePointRanges()).append(",");
    s.append(LINE_SEPARATOR).append("        // number of code-points");
    s.append(LINE_SEPARATOR).append(INDENT_08).append(optimalHashedRangedSubsetWrapper.numberOfCodePointsInCodePointRanges());
  }

  private void createAlphabetCharactersTxt(
      final ULocale locale,
      final UnicodeSet unicodeSet) {

    final String headerLine = "===================================================================================================";

    final StringBuilder s = new StringBuilder();

    if (unicodeSet == null || unicodeSet.isEmpty()) {
      return;
    }

    final var localeDisplayLanguage = locale.getDisplayLanguage().toUpperCase().replaceAll("\\W+", "_");
    final var localeLanguageTag = locale.toLanguageTag().replaceAll("\\W+", "_");
    final var localeName = String.format("%s_%s", localeDisplayLanguage, localeLanguageTag);

    int headingStart = s.length();
    s.append(localeName);
    s.append(LINE_SEPARATOR).append(headerLine, 0, s.length() - headingStart);
    s.append(LINE_SEPARATOR).append(LINE_SEPARATOR);

    headingStart = s.length();
    s.append("Characters include in the ").append(localeName).append(" ");
    s.append(localeLanguageTag).append(" set");
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
    final File filePath = new File(docFilesDirectory + File.separator + localeName + ".txt");
    docFilesDirectory.mkdirs();
    try (final FileWriter fileWriter = new FileWriter(filePath)) {
      fileWriter.append(s.toString());
      fileWriter.flush();
    } catch (final IOException e) {
      logger.log(Level.SEVERE, e, () -> "Cannot write file to " + filePath);
    }
  }
}
