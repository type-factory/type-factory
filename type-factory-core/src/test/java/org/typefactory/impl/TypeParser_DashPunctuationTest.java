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

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.TypeParser;

class TypeParser_DashPunctuationTest extends AbstractTypeParserTest {

  @ParameterizedTest
  @CsvSource(value = {
      "Water-slide|Water-slide",
      "Water–slide|Water-slide",
      "Water—slide|Water-slide",
  }, delimiter = '|')
  void convertAllDashesTo_hyphen_shouldParseAsExpected(final String value, final String expected) {

    final TypeParser typeParser =
        TypeParser.builder()
            .acceptAllDashes()
            .convertAllDashesToHyphen()
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    assertThat(typeParser.parseToString(value)).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "Water-slide|Water_slide",
      "Water–slide|Water_slide",
      "Water—slide|Water_slide",
  }, delimiter = '|')
  void convertAllDashesTo_char_shouldParseAsExpected(final String value, final String expected) {

    final TypeParser typeParser =
        TypeParser.builder()
            .acceptAllDashes()
            .convertAllDashesTo('_')
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    assertThat(typeParser.parseToString(value)).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      " ---         | \uD83D\uDE00\uD83D\uDE00\uD83D\uDE00   ", // 😀😀😀
      " -Z-Z-       | \uD83D\uDE00Z\uD83D\uDE00Z\uD83D\uDE00 ", // 😀Z😀Z😀
      " Water-slide | Water\uD83D\uDE00slide                 ", // Water😀slide
      " Water–slide | Water\uD83D\uDE00slide                 ", // Water😀slide
      " Water—slide | Water\uD83D\uDE00slide                 ", // Water😀slide
  }, delimiter = '|')
  void convertAllDashesTo_codePoint_shouldParseAsExpected(final String value, final String expected) {

    final TypeParser typeParser =
        TypeParser.builder()
            .acceptAllDashes()
            .convertAllDashesTo(0x01F600) // 😀 grinning face emoji codepoint
            .acceptLettersAtoZ()
            .build();

    assertThat(typeParser.parseToString(value)).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "Water-slide|Water===slide",
      "Water–slide|Water===slide",
      "Water—slide|Water===slide",
  }, delimiter = '|')
  void convertAllDashesTo_charSequence_shouldParseAsExpected(final String value, final String expected) {

    final TypeParser typeParser =
        TypeParser.builder()
            .acceptAllDashes()
            .convertAllDashesTo("===")
            .acceptCharRange('a', 'z')
            .acceptCharRange('A', 'Z')
            .build();

    assertThat(typeParser.parseToString(value)).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      " \u002D       | ''         ", // U+002D HYPHEN-MINUS
      " \u058A       | ''         ", // U+058A ARMENIAN HYPHEN
      " \u05BE       | ''         ", // U+05BE HEBREW PUNCTUATION MAQAF
      " \u1400       | ''         ", // U+1400 CANADIAN SYLLABICS HYPHEN
      " \u1806       | ''         ", // U+1806 MONGOLIAN TODO SOFT HYPHEN
      " \u2010       | ''         ", // U+2010 HYPHEN
      " \u2011       | ''         ", // U+2011 NON-BREAKING HYPHEN
      " \u2012       | ''         ", // U+2012 FIGURE DASH
      " \u2013       | ''         ", // U+2013 EN DASH
      " \u2014       | ''         ", // U+2014 EM DASH
      " \u2015       | ''         ", // U+2015 HORIZONTAL BAR
      " \u2E17       | ''         ", // U+2E17 DOUBLE OBLIQUE HYPHEN
      " \u2E1A       | ''         ", // U+2E1A HYPHEN WITH DIAERESIS
      " \u2E3A       | ''         ", // U+2E3A TWO-EM DASH
      " \u2E3B       | ''         ", // U+2E3B THREE-EM DASH
      " \u2E40       | ''         ", // U+2E40 DOUBLE HYPHEN
      " \u301C       | ''         ", // U+301C WAVE DASH
      " \u3030       | ''         ", // U+3030 WAVY DASH
      " \u30A0       | ''         ", // U+30A0 KATAKANA-HIRAGANA DOUBLE HYPHEN
      " \uFE31       | ''         ", // U+FE31 PRESENTATION FORM FOR VERTICAL EM DASH
      " \uFE32       | ''         ", // U+FE32 PRESENTATION FORM FOR VERTICAL EN DASH
      " \uFE58       | ''         ", // U+FE58 SMALL EM DASH
      " \uFE63       | ''         ", // U+FE63 SMALL HYPHEN-MINUS
      " \uFF0D       | ''         ", // U+FF0D FULLWIDTH HYPHEN-MINUS
      " \uD803\uDEAD | ''         ", // U+10EAD YEZIDI HYPHENATION MARK
      " -            | ''         ",
      " –            | ''         ",
      " —            | ''         ",
      " -a-b-        | ab         ", // hyphen
      " –a–b–        | ab         ", // en-dash
      " —a—b—        | ab         ", // em-dash
      " Water-slide  | Waterslide ", // hyphen
      " Water–slide  | Waterslide ", // en-dash
      " Water—slide  | Waterslide ", // em-dash
      }, delimiter = '|')
  void removeAllDashesAndHyphens_shouldParseAsExpected(final String value, final String expected) {

    final TypeParser typeParser =
        TypeParser.builder()
            .removeAllDashesAndHyphens()
            .acceptLettersAtoZ()
            .build();

    assertThat(typeParser.parseToString(value)).isEqualTo(expected);
  }

}
