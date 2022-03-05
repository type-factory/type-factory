package land.artli.easytype.generator.language;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static land.artli.easytype.generator.language.JavadocFragments.LANGUAGE_ALPHABET_INCLUDED_JAVADOC;
import static land.artli.easytype.generator.language.LanguageData.LETTERS_JAPANESE_JA_Hani;

import com.ibm.icu.text.UnicodeSet;
import com.ibm.icu.text.UnicodeSet.EntryRange;
import com.ibm.icu.util.ULocale;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

  public static void main(String... args) {

    final String outputDirectory = args[0];

    final StringBuilder s = new StringBuilder();

    s.append("""
        package land.artli.easytype;
                
        import java.util.Locale;
        import javax.annotation.processing.Generated;
                
        @Generated(
            comments = "This file is generated from data in the LanguageData class in the easy-type-language-generator module.",
            value = "land.artli:easy-type-language-generator")       
        public interface Language extends Subset {""");

    for (LanguageData languageData : LanguageData.values()) {
      final UnicodeSet unicodeSet = languageData.getUnicodeSet();
      final ULocale locale = languageData.getLocale();
      final String localeLanguage = locale.getLanguage();
      final String localeCountry = locale.getCountry();
      final String localeVariant = locale.getVariant();
      final String localeScript = locale.getScript();
      final String localeLanguageTag = locale.toLanguageTag().replaceAll("[\s_-]+", "_");
      final String displayLanguage = locale.getDisplayLanguage().replaceAll("[\s_-]+", "_");
      final String enumName = String.format("%s_%s", displayLanguage.toUpperCase(), localeLanguageTag);

      s.append("\n");
      appendJavadoc(s, languageData, enumName);
      if ("Hani".equalsIgnoreCase(localeScript)) {
        createAlphabetCharactersHtml(languageData, outputDirectory);
      }

      s.append(String.format("  Language %s = new LanguageImpl(", enumName));
      if (locale.getScript() == null || locale.getScript().isBlank()) {
        s.append(String.format("%n      new Locale(\"%s\", \"%s\", \"%s\"),",
            localeLanguage, localeCountry, localeVariant));
      } else {
        s.append(String.format("%n      new Locale.Builder().setLanguage(\"%s\").setRegion(\"%s\").setVariant(\"%s\").setScript(\"%s\").build(),",
            localeLanguage, localeCountry, localeVariant, localeScript));
      }

      appendCodepointArrayRanges(s, languageData, 0x00, 0xFF, "char", "0x%02x_%02x");
      appendCodepointArrayRanges(s, languageData, 0x0100, 0xFFFF, "int", "0x%04x_%04x");
      appendCodepointArrayRanges(s, languageData, 0x00010000, MAX_VALUE, "long", "0x%08x_%08xL");
      s.setLength(s.length() - 1);
      s.append(");");
    }

    s.append('\n');
    s.append("""
        }
        """);

    try (final FileWriter fileWriter = new FileWriter(outputDirectory + File.separator + "Language.java")) {
      fileWriter.append(s.toString());
      fileWriter.flush();
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

  private static void appendCodepointArrayRanges(
      final StringBuilder s,
      final LanguageData languageData,
      final int rangeStart,
      final int rangeEnd,
      final String rangeArrayType,
      final String rangeFormat) {

    final UnicodeSet unicodeSet = languageData.getUnicodeSet();

    final String indentedRangeFormat = "          " + rangeFormat + ", // ";
    if (unicodeSet == null || unicodeSet.isEmpty()) {
      return;
    }
    boolean arrayStarted = false;
    final String indent = switch (rangeArrayType) {
      case "char" -> new String(new char[19]).replace('\0', ' ');
      case "int" -> new String(new char[23]).replace('\0', ' ');
      case "long" -> new String(new char[32]).replace('\0', ' ');
      default -> "";
    };
    for (EntryRange range : unicodeSet.ranges()) {
      final int from = range.codepoint;
      final int to = range.codepointEnd;
      if (from <= rangeEnd && to >= rangeStart) {
        if (!arrayStarted) {
          if (languageData == LETTERS_JAPANESE_JA_Hani) {
            s.append("\n      // See Javadoc for full set of unicode code points in the following ranges.");
          }
          s.append("\n      new ").append(rangeArrayType).append("[]{\n");
          arrayStarted = true;
        }
        s.append(String.format(indentedRangeFormat, max(rangeStart, from), min(rangeEnd, to)));
        if (languageData == LETTERS_JAPANESE_JA_Hani) {
          for (int c = from; c <= min(to, from + 20); ++c) {
            s.append(' ').appendCodePoint(c);
          }
          s.append(" ...\n");
        } else {
          for (int c = from, i = 1; c <= to; ++c, ++i) {
            s.append(' ').appendCodePoint(c);
            if (i % 30 == 0 && to - c > 2) {
              s.append('\n').append(indent).append("// ");
            }
          }
          s.append("\n");
        }
      }
    }
    if (arrayStarted) {
      s.append("      },");
    }
  }

  private static void appendJavadoc(
      final StringBuilder s,
      final LanguageData languageData,
      final String enumName) {

    if (languageData.hasJavadoc()) {
      s.append("\n  /**");
      for (String javadoc : languageData.getJavadoc()) {
        s.append("\n   * ");
        s.append(javadoc.replace("\n", "\n   * "));
        s.append("\n   *");
        if (LANGUAGE_ALPHABET_INCLUDED_JAVADOC.equals(javadoc)) {
          appendAlphabetCharactersJavadoc(s, languageData, enumName);
          s.append("\n   *");
        }
      }
      s.append("/\n");
    }
  }

  private static void appendAlphabetCharactersJavadoc(
      final StringBuilder s,
      final LanguageData languageData,
      final String enumName) {

    final UnicodeSet unicodeSet = languageData.getUnicodeSet();

    if (unicodeSet == null || unicodeSet.isEmpty()) {
      return;
    }
    if (languageData == LETTERS_JAPANESE_JA_Hani) {
      s.append("\n   * <p>There are too many unicode code-points in this set to display here. See separate ");
      s.append("\n   *    <a href='./doc-files/").append(enumName).append(".html'>JAPANESE_ja_Hani</a>");
      s.append("\n   *    file for a complete list of the unicode code-points or in this language set.</p>");
    } else {
      s.append("\n   * <pre>");
      for (EntryRange range : unicodeSet.ranges()) {
        final int from = range.codepoint;
        final int to = range.codepointEnd;
        if (range.codepointEnd > range.codepoint) {
          s.append('\n');
          s.append(String.format("   *    %04X..%04X  ", from, to));
          for (int c = from, i = 1; c <= to; ++c, ++i) {
            s.append(' ').appendCodePoint(c);
            if (i % 30 == 0 && to - c > 2) {
              s.append("\n   *                ");
            }
          }
        } else {
          s.append('\n');
          s.append(String.format("   *    %04X        ", from));
          for (int c = from; c <= to; ++c) {
            s.append(' ').appendCodePoint(c);
          }
        }
      }
      s.append("\n   * </pre>");
    }
  }

  private static void createAlphabetCharactersHtml(
      final LanguageData languageData,
      final String outputDirectory) {

    final UnicodeSet unicodeSet = languageData.getUnicodeSet();

    final StringBuilder s = new StringBuilder();

    if (unicodeSet == null || unicodeSet.isEmpty()) {
      return;
    }
    s.append(String.format("""
            <html>
            <head>
            </head>
            <body>
            <h1>%s</h1>
            <h1>Characters include in the %s %s set.</h1>
            <pre>""",
        languageData.getTargetEnumName(),
        languageData.getLocaleDisplayLanguage(),
        languageData.getLocaleLanguageTag()));

    for (EntryRange range : unicodeSet.ranges()) {
      final int from = range.codepoint;
      final int to = range.codepointEnd;
      s.append('\n');
      s.append("\n-------------------------\n");
      s.append(String.format("%04X..%04X  Unicode Range ", from, to));
      s.append("\n-------------------------");
      if (range.codepointEnd > range.codepoint) {
        for (int c = from, i = 0; c <= to; ++c, ++i) {
          if (i % 32 == 0 && to - c > 0) {
            s.append('\n');
            s.append(String.format("%04X..%04X  ", from + i, from + i + 31));
          }
          s.append(' ').appendCodePoint(c);
        }
      } else {
        s.append('\n');
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
