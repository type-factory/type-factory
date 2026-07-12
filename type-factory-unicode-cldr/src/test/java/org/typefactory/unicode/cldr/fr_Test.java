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
package org.typefactory.unicode.cldr;

import static org.typefactory.assertions.TypeFactoryAssertions.assertThat;

import javax.annotation.processing.Generated;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Unit tests for the French language 'fr' resource bundle as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file of unit tests for the French language 'fr' resource bundle is generated
        from the Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
class fr_Test extends root_Test {

  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000041   | 0x00005a
      0x000061   | 0x00007a
      0x0000c0   | 0x0000c0
      0x0000c2   | 0x0000c2
      0x0000c6   | 0x0000cb
      0x0000ce   | 0x0000cf
      0x0000d4   | 0x0000d4
      0x0000d9   | 0x0000d9
      0x0000db   | 0x0000dc
      0x0000e0   | 0x0000e0
      0x0000e2   | 0x0000e2
      0x0000e6   | 0x0000eb
      0x0000ee   | 0x0000ef
      0x0000f4   | 0x0000f4
      0x0000f9   | 0x0000f9
      0x0000fb   | 0x0000fc
      0x0000ff   | 0x0000ff
      0x000152   | 0x000153
      0x000178   | 0x000178
      """)
  void getStandardSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new fr().getStandardSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000053   | 0x000053
      0x0000c1   | 0x0000c1
      0x0000c3   | 0x0000c5
      0x0000cc   | 0x0000cd
      0x0000d1   | 0x0000d3
      0x0000d5   | 0x0000d6
      0x0000d8   | 0x0000d8
      0x0000da   | 0x0000da
      0x0000df   | 0x0000df
      0x0000e1   | 0x0000e1
      0x0000e3   | 0x0000e5
      0x0000ec   | 0x0000ed
      0x0000f1   | 0x0000f3
      0x0000f5   | 0x0000f6
      0x0000f8   | 0x0000f8
      0x0000fa   | 0x0000fa
      0x000100   | 0x000101
      0x000106   | 0x000107
      0x000112   | 0x000113
      0x00012a   | 0x00012b
      0x000132   | 0x000133
      0x000158   | 0x000159
      0x000160   | 0x000161
      0x00017f   | 0x00017f
      0x0001d3   | 0x0001d4
      """)
  void getAuxiliarySubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new fr().getAuxiliarySubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000021   | 0x000023
      0x000026   | 0x000026
      0x000028   | 0x00002a
      0x00002c   | 0x00002f
      0x00003a   | 0x00003b
      0x00003f   | 0x000040
      0x00005b   | 0x00005b
      0x00005d   | 0x00005d
      0x0000a7   | 0x0000a7
      0x0000ab   | 0x0000ab
      0x0000bb   | 0x0000bb
      0x002010   | 0x002011
      0x002013   | 0x002014
      0x002019   | 0x002019
      0x00201c   | 0x00201d
      0x002020   | 0x002021
      0x002026   | 0x002026
      """)
  void getPunctuationSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new fr().getPunctuationSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000030   | 0x000039
      """)
  void getDecimalDigitsSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new fr().getDecimalDigitsSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


}
