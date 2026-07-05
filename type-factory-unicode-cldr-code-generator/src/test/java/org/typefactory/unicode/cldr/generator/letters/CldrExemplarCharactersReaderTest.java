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

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class CldrExemplarCharactersReaderTest {

  @Test
  void parseExemplarCharactersText_parsesRangesStringsAndEscapes() {
    final CldrExemplarCharacters actual =
        CldrExemplarCharactersReader.parseExemplarCharacters("[a {ch} b-d \\u200E\\u200F \\-]");

    assertThat(actual.ranges())
        .extracting(CldrExemplarCharacters.Range::inclusiveFrom)
        .containsExactly((int) 'a', (int) 'b', (int) '-');

    assertThat(actual.ranges())
        .extracting(CldrExemplarCharacters.Range::inclusiveTo)
        .containsExactly((int) 'a', (int) 'd', (int) '-');

    assertThat(actual.strings())
        .contains("ch", "\u200E\u200F");
  }

  @Test
  void readLocaleExemplarCharacters_readsRelevantEntries(@TempDir final Path tempDir) throws Exception {
    final Path localeXml = tempDir.resolve("be_TARASK.xml");
    Files.writeString(
        localeXml,
        """
        <?xml version="1.0" encoding="UTF-8" ?>
        <!DOCTYPE ldml SYSTEM "../../common/dtd/ldml.dtd">
        <ldml>
          <identity>
            <language type="be"/>
          </identity>
          <characters>
            <exemplarCharacters>[а {дж} б]</exemplarCharacters>
            <exemplarCharacters type="auxiliary">[г]</exemplarCharacters>
            <exemplarCharacters type="punctuation">[\\-]</exemplarCharacters>
            <exemplarCharacters type="index">[А]</exemplarCharacters>
          </characters>
        </ldml>
        """);

    final Map<String, CldrExemplarCharacters> actual = CldrExemplarCharactersReader.readLocaleExemplarCharacters(localeXml.toFile());

    assertThat(actual.get("standard").strings()).contains("дж");
    assertThat(actual.get("standard").ranges())
        .extracting(CldrExemplarCharacters.Range::inclusiveFrom)
        .contains((int) 'а', (int) 'б');
    assertThat(actual.get("auxiliary").ranges())
        .extracting(CldrExemplarCharacters.Range::inclusiveFrom)
        .containsExactly((int) 'г');
    assertThat(actual.get("punctuation").ranges())
        .extracting(CldrExemplarCharacters.Range::inclusiveFrom)
        .containsExactly((int) '-');
  }

  @Test
  void readLocaleExemplarCharacters_readsRealCldrXmlWhenAvailable() {
    final File xmlFile = Path.of("target", "classes", "cldr-common", "common", "main", "be_TARASK.xml").toFile();
    org.junit.jupiter.api.Assumptions.assumeTrue(xmlFile.isFile(), "CLDR XML should be unpacked during generate-resources");

    final Map<String, CldrExemplarCharacters> actual = CldrExemplarCharactersReader.readLocaleExemplarCharacters(xmlFile);

    assertThat(actual.get("standard").strings()).contains("дж", "дз");
  }
}
