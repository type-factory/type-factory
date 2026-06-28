package org.typefactory.unicode.cldr.generator.icu4j;

import com.ibm.icu.text.UnicodeSet;
import com.ibm.icu.util.LocaleData;
import com.ibm.icu.util.ULocale;
import org.typefactory.Subset;

public class Main {
  public static void main(String[] args) {
    final var locales = new ULocale[] {
        new ULocale("el")
//        new ULocale("en"),  new ULocale("de"), new ULocale("es"), new ULocale("fr"),
//        new ULocale("it"),  new ULocale("el"), new ULocale("ko"), new ULocale("ja"),
    };
    for (var locale : locales) {
      // Get the main exemplar characters
      UnicodeSet exemplars = LocaleData.getExemplarSet(locale, UnicodeSet.ADD_CASE_MAPPINGS, LocaleData.ES_STANDARD);
      Subset subset = subsetFromUnicodeSet1(exemplars);
      System.out.println("\n\n");
//      System.out.println(subset.toString());
      System.out.println(subset.toPattern());
      System.out.println(exemplars.toPattern(false));
      System.out.println(toStringUnicodeSet(exemplars));

//      exemplars = LocaleData.getExemplarSet(locale, UnicodeSet.CASE_INSENSITIVE, LocaleData.ES_AUXILIARY);
//      subset = subsetFromUniocdeSet2(exemplars);
//      System.out.println("\n\n" + subset.toString());
//      System.out.println(subset.toPattern());
//      System.out.println(exemplars.toPattern(false));
    }
  }

  private static String toStringUnicodeSet(UnicodeSet exemplars) {
    Subset.builder();
    final var sb = new StringBuilder();
    for (var cp : exemplars.codePoints()) {
      sb.appendCodePoint(cp);
    }
    return sb.toString();
  }

  private static Subset subsetFromUnicodeSet1(UnicodeSet exemplars) {
    Subset.builder();
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

  private static Subset subsetFromUniocdeSet2(UnicodeSet exemplars) {
    Subset.builder();
    final var subsetBuilder = Subset.builder();
    for (int cp : exemplars.codePoints()) {
      subsetBuilder.includeCodePoint(cp);
    }
    for (var s : exemplars.strings()) {
      for (int cp : s.codePoints().toArray()) {
        final var scp = Character.toString(cp);
        subsetBuilder.includeCodePoint(cp);
      }
    }

    return subsetBuilder.build();
  }

}