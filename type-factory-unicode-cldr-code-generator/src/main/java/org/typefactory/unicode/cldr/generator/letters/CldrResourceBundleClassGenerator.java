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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.typefactory.StringFormatter;
import org.typefactory.impl.HashedRangedSubsetWrapper;
import org.typefactory.impl.InternalSubsetUtils;
import org.typefactory.impl.OptimalHashedRangedSubsetWrapper;
import org.typefactory.impl.RangedSubsetWrapper;
import org.typefactory.impl.SubsetWrapper;

public class CldrResourceBundleClassGenerator {

  private static final Logger logger = Logger.getLogger(CldrResourceBundleClassGenerator.class.getName());

  private static final String LINE_SEPARATOR = System.lineSeparator();
  public static final String INDENT_08 = "        ";
  public static final String INDENT_10 = "          ";

  private final String licenseHeader;
  private final File outputDirectory;

  public CldrResourceBundleClassGenerator(
      final String licenseHeader,
      final File outputDirectory) {
    this.licenseHeader = licenseHeader;
    this.outputDirectory = outputDirectory;
  }

  public String generateLocaleDataResourceBundleClass(
      final CldrLocaleXmlDocument cldrLocaleXmlDocument) {

    final Locale locale = cldrLocaleXmlDocument.getLocale();
    final String lettersClassName = tokenize(locale.toLanguageTag());
    logger.info(() -> "Creating subset for " + lettersClassName);

    final String language = locale.getDisplayLanguage();

    final String script = locale.getDisplayScript().isEmpty()
        ? ""
        : " (" + locale.getDisplayScript() + " script)";

    final Optional<SubsetWrapper> standardCharactersSubset =
        cldrLocaleXmlDocument.getStandardExemplarCharacters().map(SubsetWrapper::optimisedSubset);

    final Optional<SubsetWrapper> auxiliaryCharactersSubset =
        cldrLocaleXmlDocument.getAuxiliaryExemplarCharacters().map(SubsetWrapper::optimisedSubset);

    final Optional<SubsetWrapper> punctuationCharactersSubset =
        cldrLocaleXmlDocument.getPunctuationExemplarCharacters().map(SubsetWrapper::optimisedSubset);

    final String extendsClass = cldrLocaleXmlDocument.extendsClassName();

    final StringFormatter s = new StringFormatter()
        .append(licenseHeader)
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
            public class %s extends %s {
            
              public %s() {
                super(
                    STANDARD_CHARACTERS_SUBSET,
                    AUXILIARY_CHARACTERS_SUBSET,
                    PUNCTUATION_CHARACTERS_SUBSET);
              }
            
              protected %s(
                      final Subset standardSubset,
                      final Subset auxiliarySubset,
                      final Subset punctuationSubset) {
                super(
                    standardSubset == null ? STANDARD_CHARACTERS_SUBSET : standardSubset,
                    auxiliarySubset == null ? AUXILIARY_CHARACTERS_SUBSET : auxiliarySubset,
                    punctuationSubset == null ? PUNCTUATION_CHARACTERS_SUBSET : punctuationSubset);
              }
            
            """, language, script, lettersClassName, extendsClass, lettersClassName, lettersClassName))
        .append(String.format("""
              /**
               * <p>The standard characters for the %s language%s as defined by the
               *    Unicode Common Locale Data Repository (CLDR).</p>
               *
               * <p>These are the characters in the {@code <exemplarCharacters>}
               *    element in the CLDR dataset.</p>
               */
            """, language, script))
        .append("  static final Subset STANDARD_CHARACTERS_SUBSET = ")
        .apply(appendSubset(locale, standardCharactersSubset))
        .appendNewline()
        .append(String.format("""
              /**
               * <p>The auxiliary characters for the %s language%s as defined by the
               *    Unicode Common Locale Data Repository (CLDR).</p>
               *
               * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
               *    element in the CLDR dataset.</p>
               */
            """, language, script))
        .append("  static final Subset AUXILIARY_CHARACTERS_SUBSET = ")
        .apply(appendSubset(locale, auxiliaryCharactersSubset))
        .appendNewline()
        .append(String.format("""
              /**
               * <p>The punctuation characters for the %s language%s as defined by the
               *    Unicode Common Locale Data Repository (CLDR).</p>
               *
               * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
               *    element in the CLDR dataset.</p>
               */
            """, language, script))
        .append("  static final Subset PUNCTUATION_CHARACTERS_SUBSET = ")
        .apply(appendSubset(locale, punctuationCharactersSubset))
        .appendNewline()
        .append('}')
        .appendNewline();

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
      final StringFormatter s,
      final Locale locale,
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
              s.appendNewline().append("      // See Javadoc for full set of unicode code points in the following ranges.");
              break;
            default:
              break;
          }
          s.appendNewline().append("        new ").append(rangeArrayType).append("[]{").appendNewline();
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
            s.append(" ...").appendNewline();
            break;
          default:
            for (int c = from, i = 1; c <= to; ++c, ++i) {
              s.append(' ').appendCodePoint(c);
              if (i % 30 == 0 && to - c > 2) {
                s.appendNewline().append(indent).append("// ");
              }
            }
            s.appendNewline();
            break;
        }
      }
    }
    if (arrayStarted) {
      s.append("      },");
    }
    return new Sizes(numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);
  }

  private static Consumer<StringFormatter> appendSubset(
      final Locale locale,
      final Optional<SubsetWrapper> optionalSubsetWrapper) {

    if (optionalSubsetWrapper.isEmpty()) {
      return sf -> appendNullSubset().accept(sf);
    }

    final var subsetWrapper = optionalSubsetWrapper.get();

    if (subsetWrapper.isEmpty()) {
      return sf -> appendEmptySubset().accept(sf);
    } else if (subsetWrapper instanceof RangedSubsetWrapper rangedSubsetWrapper) {
      return sf -> appendRangedSubset(locale, rangedSubsetWrapper).accept(sf);
    } else if (subsetWrapper instanceof HashedRangedSubsetWrapper hashedRangedSubsetWrapper) {
      return sf -> appendHashedBlockRangedSubset(hashedRangedSubsetWrapper).accept(sf);
    } else if (subsetWrapper instanceof OptimalHashedRangedSubsetWrapper optimalHashedRangedSubsetWrapper) {
      return sf -> appendOptimalHashedBlockRangedSubset(optimalHashedRangedSubsetWrapper).accept(sf);
    } else {
      return sf -> {
      };
    }
  }

  private static Consumer<StringFormatter> appendNullSubset() {
    return sf ->
        sf.append("null;")
            .appendNewline()
            .appendNewline();
  }

  private static Consumer<StringFormatter> appendEmptySubset() {
    return sf ->
        sf.append("Factory.emptySubset();")
            .appendNewline()
            .appendNewline();
  }

  private static Consumer<StringFormatter> appendRangedSubset(
      final Locale locale,
      final RangedSubsetWrapper rangedSubsetWrapper) {

    return sf -> {

      sf.append("Factory.rangedSubset(")
          .appendNewline();

      final Sizes singleByteSizes = appendCodepointArrayRanges(sf, locale, rangedSubsetWrapper, 0x00, 0xFF, "char", "0x%02x_%02x");
      final Sizes doubleByteSizes = appendCodepointArrayRanges(sf, locale, rangedSubsetWrapper, 0x0100, 0xFFFF, "int", "0x%04x_%04x");
      final Sizes tripleByteSizes = appendCodepointArrayRanges(sf, locale, rangedSubsetWrapper, 0x00010000, MAX_VALUE, "long", "0x%08x_%08xL");

      sf.appendNewline()
          .appendPadding(6)
          .append(singleByteSizes.numberOfCodePointRanges
                  + doubleByteSizes.numberOfCodePointRanges
                  + tripleByteSizes.numberOfCodePointRanges).append(", ")
          .append(singleByteSizes.numberOfCodePointsInCodePointRanges
                  + doubleByteSizes.numberOfCodePointsInCodePointRanges
                  + tripleByteSizes.numberOfCodePointsInCodePointRanges)
          .append(");")
          .appendNewline()
          .appendNewline();
    };
  }

  private static Consumer<StringFormatter> appendHashedBlockRangedSubset(
      final HashedRangedSubsetWrapper hashedRangedSubsetWrapper) {

    final StringFormatter c = new StringFormatter();

    final char[][] keys = hashedRangedSubsetWrapper.getBlockKeys();
    final char[][][] codePointRangesByBlock = hashedRangedSubsetWrapper.getCodePointRangesByBlock();

    return s -> {

      s.append("Factory.hashedRangedSubset(")
          .appendNewline()
          .appendNewline()
          .append("""
                    // Hash-buckets with 0..n keys – null indicates an empty hash-bucket.
                    //
                    //       ┌──── hashIndex       - an index to the hash-bucket
                    //       │  ┌─ hashBucketIndex - an index to the key within the hash-bucket
                    //       │  │
                    //  char[ ][ ] blockKeys
                    new char[ ][ ] {
              """);
      for (int hashIndex = 0; hashIndex < keys.length; ++hashIndex) {
        final char[] buckets = keys[hashIndex];
        if (hashIndex % 8 == 0) {
          s.appendNewline().appendPadding(8);
        }
        final StringFormatter temp = new StringFormatter();
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
      s.setLength(s.length() - 2)
          .append("  },")
          .appendNewline()
          .appendNewline()
          .append("""
                    //       ┌─────── hashIndex           - an index to the hash-bucket
                    //       │  ┌──── hashBucketIndex     - an index to the key within the hash-bucket
                    //       │  │  ┌─ codePointRangeIndex - an index to the range within the array of ranges
                    //       │  │  │
                    //  char[ ][ ][ ] codePointRanges
                    new char[ ][ ][ ] {
              """);
      for (int hashIndex = 0; hashIndex < keys.length; ++hashIndex) {
        final char[] keyBuckets = keys[hashIndex];
        if (keyBuckets == null) {
          s.appendNewline().append("        null,");
        } else {
          s.appendNewline().append("        {");
          for (int hashBucketIndex = 0; hashBucketIndex < keyBuckets.length; ++hashBucketIndex) {
            int charCount = 0;
            final int key = keyBuckets[hashBucketIndex];
            if (hashBucketIndex > 0) {
              s.appendNewline().appendPadding(9);
            }
            s.append(String.format(" // 0x%04x__ codePoint ranges", key));
            s.appendNewline().appendPadding(10);
            final char[] codePointRanges = codePointRangesByBlock[hashIndex][hashBucketIndex];
            s.append("{");

            for (int codePointRangeIndex = 0; codePointRangeIndex < codePointRanges.length; ++codePointRangeIndex) {
              if (codePointRangeIndex > 0 && codePointRangeIndex % 8 == 0) {
                s.append(" //").append(c).appendNewline().append("           ");
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
                  c.appendNewline().appendPadding(83).append("//");
                }
                c.append(' ').appendCodePoint(codePoint);
              }
            }
            s.setLength(s.length() - 2);
            s.append("},");
            if (hashBucketIndex < keyBuckets.length - 1) {
              s.appendPaddingToDistanceFromLastLineSeparator(83).append("//").append(c);
              c.setLength(0);
            }
          }
          s.setLength(s.length() - 1);
          s.append(" }, ");
          if (hashIndex < keys.length - 1) {
            s.appendPaddingToDistanceFromLastLineSeparator(83).append("//").append(c);
            c.setLength(0);
          }
        }
      }
      s.setLength(s.length() - 2)
          .append(" },")
          .appendPaddingToDistanceFromLastLineSeparator(83).append("//").append(c)
          .appendNewline().append("        // number of code-point ranges")
          .appendNewline().appendPadding(8).append(hashedRangedSubsetWrapper.numberOfCodePointRanges()).append(",")
          .appendNewline().append("        // number of code-points")
          .appendNewline().appendPadding(8).append(hashedRangedSubsetWrapper.numberOfCodePointsInCodePointRanges())
          .append(");")
          .appendNewline()
          .appendNewline();
      c.setLength(0);
    };
  }

  private static Consumer<StringFormatter> appendOptimalHashedBlockRangedSubset(
      final OptimalHashedRangedSubsetWrapper optimalHashedRangedSubsetWrapper) {

    final StringFormatter c = new StringFormatter();

    final char[] keys = optimalHashedRangedSubsetWrapper.getBlockKeys();
    final char[][] codePointRangesByBlock = optimalHashedRangedSubsetWrapper.getCodePointRangesByBlock();

    return s -> {

      s.append("Factory.optimalHashedRangedSubset(")
          .appendNewline()
          .appendNewline()
          .append("""
                    // Hash-buckets with 0..1 keys – 0xffff indicates an empty hash-bucket.
                    //
                    //       ┌─ hashIndex - an index to the hash-bucket which has at most one key
                    //       │
                    //  char[ ] blockKeys
                    new char[ ] {
              """);
      for (int hashIndex = 0; hashIndex < keys.length; ++hashIndex) {
        final int key = keys[hashIndex];
        if (hashIndex % 8 == 0) {
          s.appendNewline().appendPadding(8);
        }
        s.append(String.format("0x%04x, ", key));
      }
      s.setLength(s.length() - 2)
          .append("  },")
          .appendNewline()
          .appendNewline()
          .append("""
                    //       ┌──── hashIndex           - an index to the hash-bucket
                    //       │  ┌─ codePointRangeIndex - an index to the range within the array of ranges
                    //       │  │
                    //  char[ ][ ] codePointRanges
                    new char[ ][ ] {
              """);
      int contiguousEmptyBucketCount = 0;
      for (int hashIndex = 0; hashIndex < keys.length; ++hashIndex) {
        int charCount = 0;
        final int key = keys[hashIndex];
        final char[] codePointRanges = codePointRangesByBlock[hashIndex];
        if (key == 0xFFFF) {
          if (contiguousEmptyBucketCount++ % 12 == 0) {
            s.appendNewline().appendPadding(9);
          }
          s.append(" null,");
        } else {
          contiguousEmptyBucketCount = 0;
          s.appendNewline().append("        {");
          s.append(String.format(" // 0x%04x__ codePoint ranges", key));
          s.appendNewline().appendPadding(10);
          for (int codePointRangeIndex = 0; codePointRangeIndex < codePointRanges.length; ++codePointRangeIndex) {
            if (codePointRangeIndex > 0 && codePointRangeIndex % 8 == 0) {
              s.append(" //").append(c).appendNewline().appendPadding(10);
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
                c.appendNewline().appendPadding(83).append("//");
              }
              c.append(' ').appendCodePoint(codePoint);
            }
          }
          s.setLength(s.length() - 2);
          s.append(" },");
          if (hashIndex < keys.length - 1) {
            s.appendPaddingToDistanceFromLastLineSeparator(83).append("//").append(c);
            c.setLength(0);
          }
        }
      }
      s.setLength(s.length() - 1)
          .append(" },")
          .appendPaddingToDistanceFromLastLineSeparator(83).append("//").append(c)
          .appendNewline().append("        // number of code-point ranges")
          .appendNewline().appendPadding(8).append(optimalHashedRangedSubsetWrapper.numberOfCodePointRanges()).append(",")
          .appendNewline().append("        // number of code-points")
          .appendNewline().appendPadding(8).append(optimalHashedRangedSubsetWrapper.numberOfCodePointsInCodePointRanges())
          .append(");")
          .appendNewline()
          .appendNewline();
      c.setLength(0);
    };
  }
}
