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
 * Unit tests for the Luxembourgish language 'lb' resource bundle as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file of unit tests for the Luxembourgish language 'lb' resource bundle is generated
        from the Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
class lb_Test extends root_Test {

  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000041   | 0x00005a
      0x000061   | 0x00007a
      0x0000c4   | 0x0000c4
      0x0000c9   | 0x0000c9
      0x0000cb   | 0x0000cb
      0x0000e4   | 0x0000e4
      0x0000e9   | 0x0000e9
      0x0000eb   | 0x0000eb
      """)
  void getStandardSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new lb().getStandardSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000049   | 0x000049
      0x000053   | 0x000053
      0x0000c0   | 0x0000c3
      0x0000c5   | 0x0000c8
      0x0000ca   | 0x0000ca
      0x0000cc   | 0x0000cf
      0x0000d1   | 0x0000d4
      0x0000d6   | 0x0000d6
      0x0000d8   | 0x0000dc
      0x0000df   | 0x0000e3
      0x0000e5   | 0x0000e8
      0x0000ea   | 0x0000ea
      0x0000ec   | 0x0000ef
      0x0000f1   | 0x0000f4
      0x0000f6   | 0x0000f6
      0x0000f8   | 0x0000fc
      0x0000ff   | 0x0000ff
      0x000100   | 0x000103
      0x000112   | 0x000115
      0x00011e   | 0x00011f
      0x00012a   | 0x00012d
      0x000130   | 0x000131
      0x00014c   | 0x00014f
      0x000152   | 0x000153
      0x00015e   | 0x00015f
      0x00016a   | 0x00016d
      0x000178   | 0x000178
      """)
  void getAuxiliarySubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new lb().getAuxiliarySubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000021   | 0x000023
      0x000026   | 0x00002a
      0x00002c   | 0x00002f
      0x00003a   | 0x00003b
      0x00003f   | 0x000040
      0x00005b   | 0x00005b
      0x00005d   | 0x00005d
      0x00007b   | 0x00007b
      0x00007d   | 0x00007d
      0x0000a7   | 0x0000a7
      0x0000ab   | 0x0000ab
      0x0000bb   | 0x0000bb
      0x002010   | 0x002011
      0x002013   | 0x002014
      0x002018   | 0x002018
      0x00201a   | 0x00201a
      0x00201c   | 0x00201c
      0x00201e   | 0x00201e
      0x002026   | 0x002026
      """)
  void getPunctuationSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new lb().getPunctuationSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


}
