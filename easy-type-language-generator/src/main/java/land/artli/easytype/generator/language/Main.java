package land.artli.easytype.generator.language;

import static java.lang.Math.max;
import static java.lang.Math.min;

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
      final ULocale locale = languageData.getLocale();
      final String localeLanguage = locale.getLanguage();
      final String localeCountry = locale.getCountry();
      final String localeVariant = locale.getVariant();
      final String localeScript = locale.getScript();
      s.append("\n\n  ");
      s.append(String.format("Language %s_%s = new LanguageImpl(",
          locale.getDisplayLanguage().replaceAll("[\s_-]+", "_").toUpperCase(),
          locale.toLanguageTag().replaceAll("[\s_-]+", "_")));
      if (locale.getScript() == null || locale.getScript().isBlank()) {
        s.append(String.format("""
                %n    new Locale("%s", "%s", "%s"),""",
            localeLanguage, localeCountry, localeVariant));
      } else {
        s.append(String.format("""
                %n    new Locale.Builder().setLanguage("%s").setRegion("%s").setVariant("%s").setScript("%s").build(),""",
            localeLanguage, localeCountry, localeVariant, localeScript));
      }

      final UnicodeSet unicodeSet = languageData.getUnicodeSet();

      appendCharArrayRanges(s, unicodeSet);
      appendIntArrayRanges(s, unicodeSet);
      appendLongArrayRanges(s, unicodeSet);
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


  private static void appendCharArrayRanges(final StringBuilder s, final UnicodeSet unicodeSet) {
    if (unicodeSet == null || unicodeSet.isEmpty()) {
      return;
    }
    boolean arrayStarted = false;
    for (EntryRange range : unicodeSet.ranges()) {
      final int from = range.codepoint;
      final int to = range.codepointEnd;
      if (from <= 0xFF && to >= 0x00) {
        if (!arrayStarted) {
          s.append("\n    new char[] {\n");
          arrayStarted = true;
        }
        s.append(String.format("        0x%02x_%02x, // ", max(0x00, from), min(0xff, to)));
        for (char c = (char) from; c <= to; ++c) {
          s.append(String.format(" %c", c));
        }
        s.append("\n");
      }
    }
    if (arrayStarted) {
      s.append("      },");
    }
  }

  private static void appendIntArrayRanges(final StringBuilder s, final UnicodeSet unicodeSet) {
    if (unicodeSet == null || unicodeSet.isEmpty()) {
      return;
    }
    boolean arrayStarted = false;
    for (EntryRange range : unicodeSet.ranges()) {
      final int from = range.codepoint;
      final int to = range.codepointEnd;
      if (from <= 0xFFFF && to >= 0x0100) {
        if (!arrayStarted) {
          s.append("\n    new int[] {\n");
          arrayStarted = true;
        }
        s.append(String.format("        0x%04x_%04x, // ", max(0x0100, from), min(0xffff, to)));
        for (char c = (char) from; c <= to; ++c) {
          s.append(String.format(" %c", c));
        }
        s.append("\n");
      }
    }
    if (arrayStarted) {
      s.append("      },");
    }
  }

  private static void appendLongArrayRanges(final StringBuilder s, final UnicodeSet unicodeSet) {
    if (unicodeSet == null || unicodeSet.isEmpty()) {
      return;
    }
    boolean arrayStarted = false;
    for (EntryRange range : unicodeSet.ranges()) {
      final int from = range.codepoint;
      final int to = range.codepointEnd;
      if (from <= 0xFFFFFFFF && to >= 0x00010000) {
        if (!arrayStarted) {
          s.append("\n    new long[] {\n");
          arrayStarted = true;
        }
        s.append(String.format("        0x%08x_%08x, // ", max(0x000010000, from), min(0xffffffff, to)));
        for (char c = (char) from; c <= to; ++c) {
          s.append(String.format(" %c", c));
        }
        s.append("\n");
      }
    }
    if (arrayStarted) {
      s.append("      },");
    }
  }
}
