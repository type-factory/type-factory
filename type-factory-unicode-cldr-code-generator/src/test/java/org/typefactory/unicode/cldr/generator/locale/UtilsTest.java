/*
 * Copyright © 2021-2026 Evan Toliopoulos (typefactory.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.typefactory.unicode.cldr.generator.locale;

import static org.assertj.core.api.Assertions.assertThat;

import com.ibm.icu.text.UnicodeSet;
import java.util.Locale;
import org.junit.jupiter.api.Test;

class UtilsTest {

  @Test
  void loadUnicodeSetFromFile_loadsAsExpected() {

    final UnicodeSet actual = Utils.loadUnicodeSetFromFile("japanese-ja-hani-jinmeiyo.psv");

    assertThat(actual.contains(0x004E11)).isTrue();
  }

  @Test
  void isIso639Language_distinguishesKnownLanguages() {
    assertThat(Utils.isIso639Language(Locale.forLanguageTag("af"))).isTrue();
    assertThat(Utils.isIso639Language(Locale.forLanguageTag("zz"))).isFalse();
    assertThat(Utils.isIso639Language(Locale.forLanguageTag(""))).isFalse();
  }


}
