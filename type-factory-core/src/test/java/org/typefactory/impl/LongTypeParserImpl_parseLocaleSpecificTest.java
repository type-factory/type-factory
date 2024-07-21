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

import static java.util.function.Predicate.not;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.Serial;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.typefactory.LongType;
import org.typefactory.LongTypeParser;

class LongTypeParserImpl_parseLocaleSpecificTest {


  static final long[] LONG_VALUES = new long[]{
      Long.MIN_VALUE,
      Long.MIN_VALUE + 1L,
      Integer.MIN_VALUE - 1L,
      Integer.MIN_VALUE,
      Integer.MIN_VALUE + 1L,
      Short.MIN_VALUE - 1L,
      Short.MIN_VALUE,
      Short.MIN_VALUE + 1L,
      -9L,
      -1L,
      0L,
      1L,
      9L,
      Short.MAX_VALUE - 1L,
      Short.MAX_VALUE,
      Short.MAX_VALUE + 1L,
      Integer.MAX_VALUE - 1L,
      Integer.MAX_VALUE,
      Integer.MAX_VALUE + 1L,
      Long.MAX_VALUE - 1L,
      Long.MAX_VALUE,
  };

  static final String LOCALE_SPECIFIC_LONG_TEST_CASES = """
      LOCALE |                      VALUE |             EXPECTED
      en-US  | -9,223,372,036,854,775,808 | -9223372036854775808
      en-US  |                 -3,111,222 |             -3111222
      en-US  |                     -3,111 |                -3111
      en-US  |                    -3,,222 |                -3222
      en-US  |                     -0,111 |                 -111
      en-US  |                      -,222 |                 -222
      en-US  |                        -,0 |                    0
      en-US  |                       ,333 |                  333
      en-US  |                      +,333 |                  333
      en-US  |                      0,444 |                  444
      en-US  |                     +1,444 |                 1444
      en-US  |                  1,444,555 |              1444555
      en-US  |  9,223,372,036,854,775,807 |  9223372036854775807

      en-AU  | -9,223,372,036,854,775,808 | -9223372036854775808
      en-AU  |                 -3,111,222 |             -3111222
      en-AU  |                     -3,111 |                -3111
      en-AU  |                    -3,,222 |                -3222
      en-AU  |                     -0,111 |                 -111
      en-AU  |                      -,222 |                 -222
      en-AU  |                        -,0 |                    0
      en-AU  |                       ,333 |                  333
      en-AU  |                      +,333 |                  333
      en-AU  |                      0,444 |                  444
      en-AU  |                     +1,444 |                 1444
      en-AU  |                  1,444,555 |              1444555
      en-AU  |  9,223,372,036,854,775,807 |  9223372036854775807

      de-DE  | -9.223.372.036.854.775.808 | -9223372036854775808
      de-DE  |                 -3.111.222 |             -3111222
      de-DE  |                     -3.111 |                -3111
      de-DE  |                    -3..222 |                -3222
      de-DE  |                     -0.111 |                 -111
      de-DE  |                      -.222 |                 -222
      de-DE  |                        -.0 |                    0
      de-DE  |                       .333 |                  333
      de-DE  |                      +.333 |                  333
      de-DE  |                      0.444 |                  444
      de-DE  |                     +1.444 |                 1444
      de-DE  |                  1.444.555 |              1444555
      de-DE  |  9.223.372.036.854.775.807 |  9223372036854775807

      # contains mixture of ordinary apostrophes as well as right single quotation marks
      de-CH  | -9’223’372’036’854’775’808 | -9223372036854775808
      de-CH  |                 -3'111'222 |             -3111222
      de-CH  |                 -3’111’222 |             -3111222
      de-CH  |                 -3'111’222 |             -3111222
      de-CH  |                     -3’111 |                -3111
      de-CH  |                    -3'’222 |                -3222
      de-CH  |                     -0’111 |                 -111
      de-CH  |                      -'222 |                 -222
      de-CH  |                        -'0 |                    0
      de-CH  |                       ’333 |                  333
      de-CH  |                      +'333 |                  333
      de-CH  |                      0'444 |                  444
      de-CH  |                     +1’444 |                 1444
      de-CH  |                  1’444'555 |              1444555
      de-CH  |  9’223’372’036’854’775’807 |  9223372036854775807

      # contains mixture of ordinary spaces as well as narrow no-break spaces
      fr-CH  | -9 223 372 036 854 775 808 | -9223372036854775808
      fr-CH  |            -3\u202F111 222 |             -3111222
      fr-CH  |                -3\u202F111 |                -3111
      fr-CH  |                    -3  222 |                -3222
      fr-CH  |                     -0 111 |                 -111
      fr-CH  |                      - 222 |                 -222
      fr-CH  |                        - 0 |                    0
      fr-CH  |                        333 |                  333
      fr-CH  |                      + 333 |                  333
      fr-CH  |                      0 444 |                  444
      fr-CH  |                +1\u202F444 |                 1444
      fr-CH  |             1\u202F444 555 |              1444555
      fr-CH  |  9 223 372 036 854 775 807 |  9223372036854775807
      """;

  private static Stream<Arguments> provideLocaleSpecificValuesCreatedByJavaNumberFormat() {
    final Locale[] locales = NumberFormat.getAvailableLocales();
    Arrays.sort(locales, Comparator.comparing(Locale::toString));
    return Arrays.stream(locales)
        .filter(not(locale -> locale.getCountry().isEmpty()))
        .filter(locale -> NumberFormat.getNumberInstance(locale) instanceof DecimalFormat)
        .flatMap(locale -> {
          final NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
          return Arrays.stream(LONG_VALUES)
              .mapToObj(value -> {
                final var formatted = numberFormat.format(value);
                return Arguments.of(locale, formatted, value);
              });
        });
  }

  private static Stream<Arguments> provideLocaleSpecificValuesCreatedByJavaLongToString() {
    final Locale[] locales = NumberFormat.getAvailableLocales();
    Arrays.sort(locales, Comparator.comparing(Locale::toString));
    return Arrays.stream(locales)
        .filter(not(locale -> locale.getCountry().isEmpty()))
        .filter(locale -> NumberFormat.getNumberInstance(locale) instanceof DecimalFormat)
        .flatMap(locale ->
            Arrays.stream(LONG_VALUES)
                .mapToObj(value -> {
                  final var formatted = Long.toString(value);
                  return Arguments.of(locale, formatted, value);
                }));
  }

  private static Stream<Arguments> provideLocaleSpecificValuesCreatedByJavaStringFormat() {
    final Locale[] locales = NumberFormat.getAvailableLocales();
    Arrays.sort(locales, Comparator.comparing(Locale::toString));
    return Arrays.stream(locales)
        .filter(not(locale -> locale.getCountry().isEmpty()))
        .filter(locale -> NumberFormat.getNumberInstance(locale) instanceof DecimalFormat)
        .flatMap(locale ->
            Arrays.stream(LONG_VALUES)
                .mapToObj(value -> {
                  final var formatted = String.format(locale, "%d", value);
                  return Arguments.of(locale, formatted, value);
                }));
  }

  @ParameterizedTest
  @MethodSource({
      "provideLocaleSpecificValuesCreatedByJavaNumberFormat",
      "provideLocaleSpecificValuesCreatedByJavaLongToString",
      "provideLocaleSpecificValuesCreatedByJavaStringFormat"})
  void parse_localeInParser(final Locale locale, final String value, final long expected) {

    final var longTypeParser = LongTypeParser.builder()
        .defaultLocale(locale)
        .minValueInclusive(Long.MIN_VALUE)
        .maxValueInclusive(Long.MAX_VALUE)
        .allowBase10Numbers()
        .forbidWhitespace()
        .build();

    final var actual = longTypeParser.parse(value);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(
      textBlock = LOCALE_SPECIFIC_LONG_TEST_CASES,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parse_localeSpecifiedInTypeParser(
      final String localeTag, final String value, final long expected) {

    final var locale = Locale.forLanguageTag(localeTag);

    final var longTypeParser = LongTypeParser.builder()
        .defaultLocale(locale)
        .minValueInclusive(Long.MIN_VALUE)
        .maxValueInclusive(Long.MAX_VALUE)
        .allowBase10Numbers()
        .build();

    final var actual = longTypeParser.parse(value);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(
      textBlock = LOCALE_SPECIFIC_LONG_TEST_CASES,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parseToType_localeSpecifiedInTypeParser(
      final String localeTag, final String value, final long expected) {

    final var locale = Locale.forLanguageTag(localeTag);

    final var longTypeParser = LongTypeParser.builder()
        .defaultLocale(locale)
        .minValueInclusive(Long.MIN_VALUE)
        .maxValueInclusive(Long.MAX_VALUE)
        .allowBase10Numbers()
        .build();

    final var actual = longTypeParser.parse(value, LongTypeParserImpl_parseLocaleSpecificTest.SomeLongType::new);
    assertThat(actual)
        .isInstanceOf(LongTypeParserImpl_parseLocaleSpecificTest.SomeLongType.class)
        .isEqualTo(new LongTypeParserImpl_parseLocaleSpecificTest.SomeLongType(expected));
  }

  @ParameterizedTest
  @CsvSource(
      textBlock = LOCALE_SPECIFIC_LONG_TEST_CASES,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parse_localeSpecifiedInParseMethod(
      final String localeTag, final String value, final long expected) {

    final var locale = Locale.forLanguageTag(localeTag);

    final var longTypeParser = LongTypeParser.builder()
        .minValueInclusive(Long.MIN_VALUE)
        .maxValueInclusive(Long.MAX_VALUE)
        .allowBase10Numbers()
        .build();

    final var actual = longTypeParser.parse(value, locale);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(
      textBlock = LOCALE_SPECIFIC_LONG_TEST_CASES,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void parseToType_localeSpecifiedInParseMethod(
      final String localeTag, final String value, final long expected) {

    final var locale = Locale.forLanguageTag(localeTag);

    final var longTypeParser = LongTypeParser.builder()
        .minValueInclusive(Long.MIN_VALUE)
        .maxValueInclusive(Long.MAX_VALUE)
        .allowBase10Numbers()
        .build();

    final var actual = longTypeParser.parse(value, locale, LongTypeParserImpl_parseLocaleSpecificTest.SomeLongType::new);
    assertThat(actual)
        .isInstanceOf(LongTypeParserImpl_parseLocaleSpecificTest.SomeLongType.class)
        .isEqualTo(new LongTypeParserImpl_parseLocaleSpecificTest.SomeLongType(expected));
  }

  static class SomeLongType extends LongType {

    @Serial
    private static final long serialVersionUID = 689906374498730311L;

    SomeLongType(final long value) {
      super(value);
    }
  }
}
