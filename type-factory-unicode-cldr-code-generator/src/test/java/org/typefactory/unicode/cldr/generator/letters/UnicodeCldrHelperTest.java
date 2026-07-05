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
import java.util.Locale;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class UnicodeCldrHelperTest {

  @Test
  void getLivingLanguageLocales_filtersCountryLocalesAndNonLivingLanguages(@TempDir final Path tempDir) throws Exception {
    Files.writeString(tempDir.resolve("af.xml"), "<ldml/>");
    Files.writeString(tempDir.resolve("be_TARASK.xml"), "<ldml/>");
    Files.writeString(tempDir.resolve("cs_CZ.xml"), "<ldml/>");
    Files.writeString(tempDir.resolve("root.xml"), "<ldml/>");

    final Set<Locale> actual = UnicodeCldrHelper.getLivingLanguageLocales(tempDir.toFile());

    assertThat(actual)
        .contains(Locale.forLanguageTag("af"), Locale.forLanguageTag("be-TARASK"))
        .doesNotContain(Locale.forLanguageTag("cs-CZ"));
  }
}
