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

import java.io.Serial;
import java.util.Locale;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.ShortType;
import org.typefactory.ShortTypeParser;

class ShortTypeParserImpl_parseLocaleSpecificTest {

  static final String LOCALE_SPECIFIC_SHORT_TEST_CASES = """
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
      
      # contains mixture of ordinary apostrophes as well as right single quotation marks
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
      
      # contains mixture of ordinary spaces as well as narrow no-break spaces
      fr-CH  |     -32 768 |  -32768
      fr-CH  | -3\u202F111 |   -3111
      fr-CH  |     -3  222 |   -3222
      fr-CH  |      -0 111 |    -111
      fr-CH  |       - 222 |    -222
      fr-CH  |         - 0 |       0
      fr-CH  |         333 |     333
      fr-CH  |       + 333 |     333
      fr-CH  |       0 444 |     444
      fr-CH  | +1\u202F444 |    1444
      fr-CH  |      32 767 |   32767
      """;

  @ParameterizedTest
  @CsvSource(
      textBlock = LOCALE_SPECIFIC_SHORT_TEST_CASES,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parse_localeSpecifiedInTypeParser(
      final String localeTag, final String value, final short expected) {

    final var locale = Locale.forLanguageTag(localeTag);

    final var shortTypeParser = ShortTypeParser.builder()
        .defaultLocale(locale)
        .minValueInclusive(Short.MIN_VALUE)
        .maxValueInclusive(Short.MAX_VALUE)
        .allowBase10Numbers()
        .build();

    final var actual = shortTypeParser.parse(value);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(
      textBlock = LOCALE_SPECIFIC_SHORT_TEST_CASES,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parseToType_localeSpecifiedInTypeParser(
      final String localeTag, final String value, final short expected) {

    final var locale = Locale.forLanguageTag(localeTag);

    final var shortTypeParser = ShortTypeParser.builder()
        .defaultLocale(locale)
        .minValueInclusive(Short.MIN_VALUE)
        .maxValueInclusive(Short.MAX_VALUE)
        .allowBase10Numbers()
        .build();

    final var actual = shortTypeParser.parse(value, SomeShortType::new);
    assertThat(actual)
        .isInstanceOf(SomeShortType.class)
        .isEqualTo(new SomeShortType(expected));
  }

  @ParameterizedTest
  @CsvSource(
      textBlock = LOCALE_SPECIFIC_SHORT_TEST_CASES,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parse_localeSpecifiedInParseMethod(
      final String localeTag, final String value, final short expected) {

    final var locale = Locale.forLanguageTag(localeTag);

    final var shortTypeParser = ShortTypeParser.builder()
        .minValueInclusive(Short.MIN_VALUE)
        .maxValueInclusive(Short.MAX_VALUE)
        .allowBase10Numbers()
        .build();

    final var actual = shortTypeParser.parse(value, locale);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(
      textBlock = LOCALE_SPECIFIC_SHORT_TEST_CASES,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parseToType_localeSpecifiedInParseMethod(
      final String localeTag, final String value, final short expected) {

    final var locale = Locale.forLanguageTag(localeTag);

    final var shortTypeParser = ShortTypeParser.builder()
        .minValueInclusive(Short.MIN_VALUE)
        .maxValueInclusive(Short.MAX_VALUE)
        .allowBase10Numbers()
        .build();

    final var actual = shortTypeParser.parse(value, locale, SomeShortType::new);
    assertThat(actual)
        .isInstanceOf(SomeShortType.class)
        .isEqualTo(new SomeShortType(expected));
  }

  static class SomeShortType extends ShortType {

    @Serial
    private static final long serialVersionUID = -8866349817477970777L;

    SomeShortType(final short value) {
      super(value);
    }
  }
}
