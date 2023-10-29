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
package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.ShortTypeParser;

class ShortTypeParserImpl_parseLocaleSpecificTest {

  @ParameterizedTest
  @CsvSource(textBlock = """
      LOCALE |      VALUE | EXPECTED
      en-US  |    -32,768 |   -32768
      en-US  |     -3,111 |    -3111
      en-US  |    -3,,222 |    -3222
      en-US  |     -0,111 |     -111
      en-US  |      -,222 |     -222
      en-US  |        -,0 |        0
      en-US  |       ,333 |      333
      en-US  |      +,333 |      333
      en-US  |      0,444 |      444
      en-US  |     +1,444 |     1444
      en-US  |     32,767 |    32767

      en-AU  |    -32,768 |   -32768
      en-AU  |     -3,111 |    -3111
      en-AU  |    -3,,222 |    -3222
      en-AU  |     -0,111 |     -111
      en-AU  |      -,222 |     -222
      en-AU  |        -,0 |        0
      en-AU  |       ,333 |      333
      en-AU  |      +,333 |      333
      en-AU  |      0,444 |      444
      en-AU  |     +1,444 |     1444
      en-AU  |     32,767 |    32767

      de-DE  |    -32.768 |   -32768
      de-DE  |     -3.111 |    -3111
      de-DE  |    -3..222 |    -3222
      de-DE  |     -0.111 |     -111
      de-DE  |      -.222 |     -222
      de-DE  |        -.0 |        0
      de-DE  |       .333 |      333
      de-DE  |      +.333 |      333
      de-DE  |      0.444 |      444
      de-DE  |     +1.444 |     1444
      de-DE  |     32.767 |    32767

      de-CH  |    -32'768 |   -32768
      de-CH  |    -32’768 |   -32768
      de-CH  |     -3'111 |    -3111
      de-CH  |    -3'’222 |    -3222
      de-CH  |     -0’111 |     -111
      de-CH  |      -'222 |     -222
      de-CH  |        -'0 |        0
      de-CH  |       ’333 |      333
      de-CH  |      +'333 |      333
      de-CH  |      0'444 |      444
      de-CH  |     +1'444 |     1444
      de-CH  |     32’767 |    32767

      fr-CH  |    -32 768 |   -32768
      fr-CH  |     -3 111 |    -3111
      fr-CH  |    -3  222 |    -3222
      fr-CH  |     -0 111 |     -111
      fr-CH  |      - 222 |     -222
      fr-CH  |        - 0 |        0
      fr-CH  |        333 |      333
      fr-CH  |      + 333 |      333
      fr-CH  |      0 444 |      444
      fr-CH  |     +1 444 |     1444
      fr-CH  |     32 767 |    32767
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parseToShort_acceptsLocaleSpecificGroupingSeparator(
      final String localeTag, final String value, final short expected) {

    final var locale = Locale.forLanguageTag(localeTag);

    final var shortTypeParser = ShortTypeParser.builder()
        .defaultLocale(locale)
        .minValueInclusive(Short.MIN_VALUE)
        .maxValueInclusive(Short.MAX_VALUE)
        .allowBase10Numbers()
        .ignoreAllWhitespace()
        .build();

    final var actual = shortTypeParser.parseToShort(value);
    assertThat(actual).isEqualTo(expected);
  }
}
