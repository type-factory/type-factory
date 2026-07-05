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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CldrExemplarCharactersReaderTest {

  @ParameterizedTest
  @CsvSource(delimiter = '|', nullValues = "<null>", textBlock = """
      <null> | true
      ''     | true
      '   '  | true
      abc    | false
      """)
  void parseExemplarCharacters_returnsEmptyForNullOrBlank(final String exemplarCharactersText, final boolean expectedEmpty) {
    final CldrExemplarCharacters actual = CldrExemplarCharactersReader.parseExemplarCharacters(exemplarCharactersText);

    assertThat(actual.isEmpty()).isEqualTo(expectedEmpty);
  }

  @Test
  void parseExemplarCharacters_parsesBracketedTokensWithStringsAndCodePoints() {
    final CldrExemplarCharacters actual = CldrExemplarCharactersReader.parseExemplarCharacters("[eéèêë {ch} ΐ]");

    assertThat(actual.codePoints())
        .contains((int) 'e', (int) 'E', (int) 'é', (int) 'É', (int) 'è', (int) 'È', (int) 'ê', (int) 'Ê', (int) 'ë', (int) 'Ë', (int) 'ΐ');
    assertThat(actual.strings()).contains("ch", "CH", "Ϊ́");
  }

  @Test
  void parseExemplarCharacters_splitsPlainLettersIntoCodePoints() {
    final CldrExemplarCharacters actual = CldrExemplarCharactersReader.parseExemplarCharacters("[ab]");

    assertThat(actual.codePoints()).contains((int) 'a', (int) 'A', (int) 'b', (int) 'B');
    assertThat(actual.strings()).isEmpty();
  }

  @ParameterizedTest
  @CsvSource(delimiter = '|', textBlock = """
      0 | 3
      1 | 3
      2 | 3
      3 | 3
      4 | 4
      5 | 5
      6 | 6
      """)
  void skipWhitespace_skipsWhitespaceCharacters(final int startIndex, final int expectedIndex) {
    assertThat(CldrExemplarCharactersReader.skipWhitespace(" \t\nabc", startIndex)).isEqualTo(expectedIndex);
  }

  @Test
  void decodeToken_decodesEscapesAndLiteralCharacters() {
    assertThat(CldrExemplarCharactersReader.decodeToken("abc")).isEqualTo("abc");
    assertThat(CldrExemplarCharactersReader.decodeToken("\\" + "u0041")).isEqualTo("A");
    assertThat(CldrExemplarCharactersReader.decodeToken("\\" + "U0001F600")).isEqualTo("😀");
    assertThat(CldrExemplarCharactersReader.decodeToken("\\" + "x{263A}")).isEqualTo("☺");
    assertThat(CldrExemplarCharactersReader.decodeToken("\\" + "q")).isEqualTo("q");
  }

  @ParameterizedTest
  @CsvSource(delimiter = '|', textBlock = """
      a        | a
      ä       | ä
      äb      | ä, b
      ä́      | ä́
      """)
  void splitIntoUnits_groupsCombiningMarksWithTheirBaseCharacter(final String decodedToken, final String expectedUnitsCsv) {
    assertThat(CldrExemplarCharactersReader.splitIntoUnits(decodedToken))
        .isEqualTo(List.of(expectedUnitsCsv.split(", ")));
  }

  @ParameterizedTest
  @CsvSource(delimiter = '|', textBlock = """
      0x0301 | true
      0x20DD | true
      0x0061 | false
      """)
  void isCombiningMark_detectsCombiningCategories(final int codePoint, final boolean expected) {
    assertThat(CldrExemplarCharactersReader.isCombiningMark(codePoint)).isEqualTo(expected);
  }

  @Test
  void addCasedVariants_addsSingleCodePointsAndStrings() {
    final LinkedHashSet<Integer> codePoints = new LinkedHashSet<>();
    final LinkedHashSet<String> strings = new LinkedHashSet<>();

    CldrExemplarCharactersReader.addCasedVariants("a", codePoints, strings);
    CldrExemplarCharactersReader.addCasedVariants("ΐ", codePoints, strings);
    CldrExemplarCharactersReader.addCasedVariants("ab", codePoints, strings);

    assertThat(codePoints).contains((int) 'a', (int) 'A', (int) 'ΐ');
    assertThat(strings).contains("ab", "AB", "Ϊ́");
  }

  @Test
  void addCasedVariant_ignoresBlankInput() {
    final LinkedHashSet<Integer> codePoints = new LinkedHashSet<>();
    final LinkedHashSet<String> strings = new LinkedHashSet<>();

    CldrExemplarCharactersReader.addCasedVariant("", codePoints, strings);

    assertThat(codePoints).isEmpty();
    assertThat(strings).isEmpty();
  }

  @Test
  void decodeEscapedCodePoint_decodesUnicodeFormsAndFallback() {
    assertThat(CldrExemplarCharactersReader.decodeEscapedCodePoint("\\" + "u0041", 0, 6)).isEqualTo(65);
    assertThat(CldrExemplarCharactersReader.decodeEscapedCodePoint("\\" + "U0001F600", 0, 10)).isEqualTo(128512);
    assertThat(CldrExemplarCharactersReader.decodeEscapedCodePoint("\\" + "x{263A}", 0, 8)).isEqualTo(9786);
    assertThat(CldrExemplarCharactersReader.decodeEscapedCodePoint("\\" + "q", 0, 2)).isEqualTo(113);
    assertThat(CldrExemplarCharactersReader.decodeEscapedCodePoint("\\" + "u12", 0, 2)).isEqualTo(117);
  }

  @Test
  void skipEscape_advancesPastUnicodeAndFallbackEscapes() {
    assertThat(CldrExemplarCharactersReader.skipEscape("\\" + "u0041", 0)).isEqualTo(6);
    assertThat(CldrExemplarCharactersReader.skipEscape("\\" + "U0001F600", 0)).isEqualTo(10);
    assertThat(CldrExemplarCharactersReader.skipEscape("\\" + "x{263A}", 0)).isEqualTo(8);
    assertThat(CldrExemplarCharactersReader.skipEscape("\\" + "q", 0)).isEqualTo(2);
    assertThat(CldrExemplarCharactersReader.skipEscape("\\" + "u12", 0)).isEqualTo(2);
  }

  @Test
  void readLocaleExemplarCharacters_returnsEmptyMapForNullInputStream() {
    assertThat(CldrExemplarCharactersReader.readLocaleExemplarCharacters((java.io.InputStream) null))
        .isEmpty();
  }

  @Test
  void readLocaleExemplarCharacters_readsRelevantEntriesFromStream() {
    final ByteArrayInputStream inputStream =
        new ByteArrayInputStream(
            """
            <?xml version="1.0" encoding="UTF-8" ?>
            <ldml>
              <characters>
                <exemplarCharacters>[eé {ch} ΐ]</exemplarCharacters>
                <exemplarCharacters type="auxiliary">[ΐ]</exemplarCharacters>
                <exemplarCharacters type="punctuation">[!]</exemplarCharacters>
                <exemplarCharacters type="index">[ignored]</exemplarCharacters>
              </characters>
            </ldml>
            """.getBytes(StandardCharsets.UTF_8));

    final Map<String, CldrExemplarCharacters> actual = CldrExemplarCharactersReader.readLocaleExemplarCharacters(inputStream);

    assertThat(actual).containsKeys("standard", "auxiliary", "punctuation");
    assertThat(actual.get("standard").codePoints()).contains((int) 'e', (int) 'E', (int) 'é', (int) 'É', (int) 'ΐ');
    assertThat(actual.get("standard").strings()).contains("ch", "CH");
    assertThat(actual.get("auxiliary").codePoints()).contains((int) 'ΐ');
    assertThat(actual.get("punctuation").codePoints()).contains((int) '!');
  }

  @Test
  void readLocaleExemplarCharacters_defaultsMissingTypesToEmpty() {
    final ByteArrayInputStream inputStream =
        new ByteArrayInputStream(
            """
            <?xml version="1.0" encoding="UTF-8" ?>
            <ldml>
              <characters>
                <exemplarCharacters type="auxiliary">[ΐ]</exemplarCharacters>
              </characters>
            </ldml>
            """.getBytes(StandardCharsets.UTF_8));

    final Map<String, CldrExemplarCharacters> actual = CldrExemplarCharactersReader.readLocaleExemplarCharacters(inputStream);

    assertThat(actual.get("standard").isEmpty()).isTrue();
    assertThat(actual.get("punctuation").isEmpty()).isTrue();
    assertThat(actual.get("auxiliary").codePoints()).contains(0x0390);
  }

  @Test
  void readLocaleExemplarCharacters_wrapsMalformedXmlInIllegalStateException() {
    final ByteArrayInputStream inputStream =
        new ByteArrayInputStream("<ldml><characters>".getBytes(StandardCharsets.UTF_8));

    assertThatThrownBy(() -> CldrExemplarCharactersReader.readLocaleExemplarCharacters(inputStream))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("Cannot read exemplar characters");
  }

  @Test
  void readLocaleExemplarCharacters_readsFromFile() throws Exception {
    final File localeXml = File.createTempFile("cldr-exemplar", ".xml");
    localeXml.deleteOnExit();
    Files.writeString(
        localeXml.toPath(),
        """
        <?xml version="1.0" encoding="UTF-8" ?>
        <ldml>
          <characters>
            <exemplarCharacters>[eé {ch}]</exemplarCharacters>
          </characters>
        </ldml>
        """,
        StandardCharsets.UTF_8);

    final Map<String, CldrExemplarCharacters> actual = CldrExemplarCharactersReader.readLocaleExemplarCharacters(localeXml);

    assertThat(actual.get("standard").codePoints()).contains((int) 'e', (int) 'E', (int) 'é', (int) 'É');
    assertThat(actual.get("standard").strings()).contains("ch", "CH");
    assertThat(actual.get("auxiliary").isEmpty()).isTrue();
    assertThat(actual.get("punctuation").isEmpty()).isTrue();
  }

  @Test
  void readLocaleExemplarCharacters_fromFileWrapsMissingFileAsIllegalStateException() {
    final File missingFile = Path.of("target", "missing-" + System.nanoTime() + ".xml").toFile();

    assertThatThrownBy(() -> CldrExemplarCharactersReader.readLocaleExemplarCharacters(missingFile))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("Cannot read exemplar characters from " + missingFile);
  }
}
