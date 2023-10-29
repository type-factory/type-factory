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
import org.typefactory.IntegerTypeParser;

class IntegerTypeParserImpl_parseLocaleSpecificTest {

  @ParameterizedTest
  @CsvSource(textBlock = """
      LOCALE |      VALUE | EXPECTED
      en-US  | -3,111,222 | -3111222
      en-US  |     -3,111 |    -3111
      en-US  |    -3,,222 |    -3222
      en-US  |     -0,111 |     -111
      en-US  |      -,222 |     -222
      en-US  |        -,0 |        0
      en-US  |       ,333 |      333
      en-US  |      +,333 |      333
      en-US  |      0,444 |      444
      en-US  |     +1,444 |     1444
      en-US  |  1,444,555 |  1444555

      en-AU  | -3,111,222 | -3111222
      en-AU  |     -3,111 |    -3111
      en-AU  |    -3,,222 |    -3222
      en-AU  |     -0,111 |     -111
      en-AU  |      -,222 |     -222
      en-AU  |        -,0 |        0
      en-AU  |       ,333 |      333
      en-AU  |      +,333 |      333
      en-AU  |      0,444 |      444
      en-AU  |     +1,444 |     1444
      en-AU  |  1,444,555 |  1444555

      de-DE  | -3.111.222 | -3111222
      de-DE  |     -3.111 |    -3111
      de-DE  |    -3..222 |    -3222
      de-DE  |     -0.111 |     -111
      de-DE  |      -.222 |     -222
      de-DE  |        -.0 |        0
      de-DE  |       .333 |      333
      de-DE  |      +.333 |      333
      de-DE  |      0.444 |      444
      de-DE  |     +1.444 |     1444
      de-DE  |  1.444.555 |  1444555

      de-CH  | -3'111'222 | -3111222
      de-CH  | -3’111’222 | -3111222
      de-CH  | -3'111’222 | -3111222
      de-CH  |     -3'111 |    -3111
      de-CH  |    -3'’222 |    -3222
      de-CH  |     -0’111 |     -111
      de-CH  |      -'222 |     -222
      de-CH  |        -'0 |        0
      de-CH  |       ’333 |      333
      de-CH  |      +'333 |      333
      de-CH  |      0'444 |      444
      de-CH  |     +1'444 |     1444
      de-CH  |  1’444'555 |  1444555

      fr-CH  | -3 111 222 | -3111222
      fr-CH  |     -3 111 |    -3111
      fr-CH  |    -3  222 |    -3222
      fr-CH  |     -0 111 |     -111
      fr-CH  |      - 222 |     -222
      fr-CH  |        - 0 |        0
      fr-CH  |        333 |      333
      fr-CH  |      + 333 |      333
      fr-CH  |      0 444 |      444
      fr-CH  |     +1 444 |     1444
      fr-CH  |  1 444 555 |  1444555
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parseToInteger_acceptsLocaleSpecificGroupingSeparator(
      final String localeTag, final String value, final int expected) {

    final var locale = Locale.forLanguageTag(localeTag);

    final var integerTypeParser = IntegerTypeParser.builder()
        .defaultLocale(locale)
        .minValueInclusive(Integer.MIN_VALUE)
        .maxValueInclusive(Integer.MAX_VALUE)
        .allowBase10Numbers()
        .ignoreAllWhitespace()
        .build();

    final var actual = integerTypeParser.parseToInteger(value);
    assertThat(actual).isEqualTo(expected);
  }
}
