package org.typefactory.docs;

import static com.ibm.icu.text.UnicodeSet.ADD_CASE_MAPPINGS;
import static com.ibm.icu.util.LocaleData.ES_STANDARD;
import static org.assertj.core.api.Assertions.assertThat;

import com.ibm.icu.text.UnicodeSet;
import com.ibm.icu.util.LocaleData;
import com.ibm.icu.util.ULocale;
import java.util.regex.Pattern;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class Icu4jPatternTests {


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      NAME       | LOCALE | ICU4J_PATTERN                       | MATCHES   | NOTES
      Nicholas   | en-EN  | [A-Za-z]+                           | true      | ✅ English name
      Ŋʅʗƕᴑꝲɐƨ   | en-EN  | [A-Za-z]+                           | false     | ✅ Not English letters
      Νichοlas   | en-EN  | [A-Za-z]+                           | false     | ✅ Homoglyph, English & Greek letters
      Νικόλαος   | el-GR  | [ΆΈ-ΊΌΎ-ΡΣ-ώ{Ϊ́}{Ϋ́}{ΐ}{ΰ}]+          | true      | ✅ Greek name
      ͶͱϏϖϡϫϙϟ   | el-GR  | [ΆΈ-ΊΌΎ-ΡΣ-ώ{Ϊ́}{Ϋ́}{ΐ}{ΰ}]+          | false     | ✅ Not Greek letters
      Νι{κόλ}αος | el-GR  | [ΆΈ-ΊΌΎ-ΡΣ-ώ{Ϊ́}{Ϋ́}{ΐ}{ΰ}]+          | true      | ❌ Invalid pattern and curly braces passed
      François   | fr-FR  | [A-Za-zÀÂÆ-ËÎÏÔÙÛÜàâæ-ëîïôùûüÿŒœŸ]+ | true      | ✅ French name
      Begoña     | fr-FR  | [A-Za-zÀÂÆ-ËÎÏÔÙÛÜàâæ-ëîïôùûüÿŒœŸ]+ | false     | ✅ Spanish name with ñ
      """)
  void alphabetRegexCreatedFromIcu4jLocaleExemplarSet(
      final String name, final String localeTag,
      final String expectedIcu4jPattern, final boolean expectedMatch) {

    final var locale = ULocale.forLanguageTag(localeTag);
    final var alphabetSet = LocaleData.getExemplarSet(locale, ADD_CASE_MAPPINGS, ES_STANDARD);
    final var pattern = Pattern.compile(alphabetSet.toPattern(false) + "+");
    final var matcher = pattern.matcher(name);

    assertThat(pattern).hasToString(expectedIcu4jPattern);
    assertThat(matcher.matches()).isEqualTo(expectedMatch);

    System.out.printf("%s matches %s = %b%n", name, pattern, matcher.matches());

    // "Ι ◌̈ ◌́" “ ” (U+202F)
    //([ΆΈ-ΊΌΎ-ΡΣ-ώ]|Ϊ́|Ϋ́|ΐ|ΰ)+
  }

  @ParameterizedTest
  @CsvSource(delimiter = '┃', useHeadersInDisplayName = true, textBlock = """
      NAME       ┃ LOCALE ┃ ICU4J_PATTERN                         ┃ MATCHES   ┃ NOTES
      Nicholas   ┃ en-EN  ┃ ([A-Za-z])+                           ┃ true      ┃ ✅ English name
      Ŋʅʗƕᴑꝲɐƨ   ┃ en-EN  ┃ ([A-Za-z])+                           ┃ false     ┃ ✅ Not English letters
      Νichοlas   ┃ en-EN  ┃ ([A-Za-z])+                           ┃ false     ┃ ✅ Homoglyph, English & Greek letters
      Νικόλαος   ┃ el-GR  ┃ ([ΆΈ-ΊΌΎ-ΡΣ-ώ]|Ϊ́|Ϋ́|ΐ|ΰ)+              ┃ true      ┃ ✅ Greek name
      ͶͱϏϖϡϫϙϟ   ┃ el-GR  ┃ ([ΆΈ-ΊΌΎ-ΡΣ-ώ]|Ϊ́|Ϋ́|ΐ|ΰ)+              ┃ false     ┃ ✅ Not Greek letters
      Νι{κόλ}αος ┃ el-GR  ┃ ([ΆΈ-ΊΌΎ-ΡΣ-ώ]|Ϊ́|Ϋ́|ΐ|ΰ)+              ┃ false     ┃ ✅ Valid pattern and curly braces caught
      François   ┃ fr-FR  ┃ ([A-Za-zÀÂÆ-ËÎÏÔÙÛÜàâæ-ëîïôùûüÿŒœŸ])+ ┃ true      ┃ ✅ French name
      Begoña     ┃ fr-FR  ┃ ([A-Za-zÀÂÆ-ËÎÏÔÙÛÜàâæ-ëîïôùûüÿŒœŸ])+ ┃ false     ┃ ✅ Spanish name with ñ
      """)
  void alphabetRegexManuallyCreatedFromIcu4jLocaleExemplarSet(
      final String name, final String localeTag,
      final String expectedManualPattern, final boolean expectedMatch) {

    final var locale = ULocale.forLanguageTag(localeTag);
    final var alphabetSet = LocaleData.getExemplarSet(locale, UnicodeSet.ADD_CASE_MAPPINGS, LocaleData.ES_STANDARD);

    // Manually create the regex pattern from the ICU4J exemplar set
    final var regex = new StringBuilder();
    regex.append('(');
    // Add the codepoint ranges to the regex pattern
    if (alphabetSet.getRangeCount() > 0) {
      regex.append('[');
      for (var range : alphabetSet.ranges()) {
        regex.append(escapeCodePointIfRequired(range.codepoint));
        if (range.codepoint < range.codepointEnd) {
          if (range.codepoint + 1 != range.codepointEnd) {
            regex.append('-');
          }
          regex.append(escapeCodePointIfRequired(range.codepointEnd));
        }
      }
      regex.append(']');
    }
    // Add the codepoint strings to the regex pattern
    if (alphabetSet.hasStrings()) {
      for (var s : alphabetSet.strings()) {
        regex.append('|').append(s);
      }
    }
    regex.append(")+");

    // Finally, we can create the Java Pattern with a valid regex
    final var pattern = Pattern.compile(regex.toString());
    final var matcher = pattern.matcher(name);

    assertThat(pattern).hasToString(expectedManualPattern);
    assertThat(matcher.matches()).isEqualTo(expectedMatch);
  }

  static String escapeCodePointIfRequired(final int codePoint) {
    if ((Character.isAlphabetic(codePoint) || Character.isDigit(codePoint))
        && Character.getType(codePoint) != Character.MODIFIER_LETTER) {
      return Character.toString(codePoint);
    } else if (codePoint > 0xFFFF) {
      return String.format("\\x{%06X}", codePoint);
    } else {
      return String.format("\\u%04X", codePoint);
    }
  }

}
