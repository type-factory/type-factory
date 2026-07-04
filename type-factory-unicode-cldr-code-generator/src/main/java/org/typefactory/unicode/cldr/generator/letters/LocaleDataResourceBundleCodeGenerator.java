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

import com.ibm.icu.text.UnicodeSet;
import com.ibm.icu.util.ULocale;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.typefactory.impl.HashedRangedSubsetWrapper;
import org.typefactory.impl.InternalSubsetUtils;
import org.typefactory.impl.OptimalHashedRangedSubsetWrapper;
import org.typefactory.impl.RangedSubsetWrapper;
import org.typefactory.impl.SubsetWrapper;

public class LocaleDataResourceBundleCodeGenerator {

  private static final Logger logger = Logger.getLogger(LocaleDataResourceBundleCodeGenerator.class.getName());

  private static final String LINE_SEPARATOR = System.lineSeparator();
  public static final String INDENT_08 = "        ";
  public static final String INDENT_10 = "          ";

  private final String licenseHeader;
  private final File outputDirectory;

  public LocaleDataResourceBundleCodeGenerator(
      final String licenseHeader,
      final File outputDirectory) {
    this.licenseHeader = licenseHeader;
    this.outputDirectory = outputDirectory;
  }

  public String generateLocaleDataResourceBundleClass(
      final ULocale locale,
      final UnicodeSet standardCharactersUnicodeSet,
      final UnicodeSet auxiliaryCharactersUnicodeSet,
      final UnicodeSet punctuationCharactersUnicodeSet) {

    final String localeLanguageTag = tokenize(locale.toLanguageTag());
    final String lettersClassName = String.format("%s", localeLanguageTag);
    logger.info(() -> "Creating subset for " + lettersClassName);

    final String language = locale.getDisplayLanguage();

    final String script = locale.getDisplayScript().isEmpty()
        ? ""
        : " (" + locale.getDisplayScript() + " script)";

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

  private static String tokenize(final String locale) {
    return locale.replaceAll("\\W+", "_");
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

  private static void appendRangedSubset(
      final StringBuilder s,
      final ULocale locale,
      final RangedSubsetWrapper rangedSubsetWrapper) {
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
  }

  private static void appendHashedBlockRangedSubset(
      final StringBuilder s,
      final HashedRangedSubsetWrapper hashedRangedSubsetWrapper) {

    final StringBuilder c = new StringBuilder();

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
          int charCount = 0;
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
              s.append(" //").append(c).append(LINE_SEPARATOR).append("           ");
              c.setLength(0);
              charCount = 0;
            }
            final int from = InternalSubsetUtils.getInclusiveFrom(codePointRanges[codePointRangeIndex]);
            final int to = InternalSubsetUtils.getInclusiveTo(codePointRanges[codePointRangeIndex]);
            s.append(String.format("0x%02x_%02x, ", from & 0xFF, to & 0xFF));
            final int codePointFrom = (key << 8) | (from & 0xFF);
            final int codePointTo = (key << 8) | (to & 0xFF);
            for (int codePoint = codePointFrom; codePoint <= codePointTo; ++codePoint, ++charCount) {
              if (charCount > 0 && charCount % 20 == 0) {
                c.append(LINE_SEPARATOR).append(indent(83)).append("//");
              }
              c.append(' ').appendCodePoint(codePoint);
            }
          }
          s.setLength(s.length() - 2);
          s.append("},");
          if (hashBucketIndex < keyBuckets.length - 1) {
            s.append(indent(84, s)).append("//").append(c);
            c.setLength(0);
          }
        }
        s.setLength(s.length() - 1);
        s.append(" }, ");
        if (hashIndex < keys.length - 1) {
          s.append(indent(84, s)).append("//").append(c);
          c.setLength(0);
        }
      }
    }
    s.setLength(s.length() - 2);
    s.append(" },");
    s.append(indent(84, s)).append("//").append(c);
    c.setLength(0);
    s.append(LINE_SEPARATOR).append("        // number of code-point ranges");
    s.append(LINE_SEPARATOR).append(INDENT_08).append(hashedRangedSubsetWrapper.numberOfCodePointRanges()).append(",");
    s.append(LINE_SEPARATOR).append("        // number of code-points");
    s.append(LINE_SEPARATOR).append(INDENT_08).append(hashedRangedSubsetWrapper.numberOfCodePointsInCodePointRanges());
  }

  private static void appendOptimalHashedBlockRangedSubset(
      final StringBuilder s,
      final OptimalHashedRangedSubsetWrapper optimalHashedRangedSubsetWrapper) {

    final StringBuilder c = new StringBuilder();

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
      int charCount = 0;
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
            s.append(" //").append(c).append(LINE_SEPARATOR).append(INDENT_10);
            c.setLength(0);
            charCount = 0;
          }
          final int from = InternalSubsetUtils.getInclusiveFrom(codePointRanges[codePointRangeIndex]);
          final int to = InternalSubsetUtils.getInclusiveTo(codePointRanges[codePointRangeIndex]);
          s.append(String.format("0x%02x_%02x, ", from & 0xFF, to & 0xFF));
          final int codePointFrom = (key << 8) | (from & 0xFF);
          final int codePointTo = (key << 8) | (to & 0xFF);
          for (int codePoint = codePointFrom; codePoint <= codePointTo; ++codePoint, ++charCount) {
            if (charCount > 0 && charCount % 20 == 0) {
              c.append(LINE_SEPARATOR).append(indent(83)).append("//");
            }
            c.append(' ').appendCodePoint(codePoint);
          }
        }
        s.setLength(s.length() - 2);
        s.append(" },");
        if (hashIndex < keys.length - 1) {
          s.append(indent(84, s)).append("//").append(c);
          c.setLength(0);
        }
      }
    }
    s.setLength(s.length() - 1);
    s.append(" },");
    s.append(indent(84, s)).append("//").append(c);
    c.setLength(0);
    s.append(LINE_SEPARATOR).append("        // number of code-point ranges");
    s.append(LINE_SEPARATOR).append(INDENT_08).append(optimalHashedRangedSubsetWrapper.numberOfCodePointRanges()).append(",");
    s.append(LINE_SEPARATOR).append("        // number of code-points");
    s.append(LINE_SEPARATOR).append(INDENT_08).append(optimalHashedRangedSubsetWrapper.numberOfCodePointsInCodePointRanges());
  }

  private static String indent(final int indentRequired) {
    return " ".repeat(indentRequired);
  }

  private static String indent(final int indentRequired, final StringBuilder s) {
    final int indent = indentRequired - s.length() + s.lastIndexOf(LINE_SEPARATOR);
    return " ".repeat(Math.max(0, indent));
  }
}
