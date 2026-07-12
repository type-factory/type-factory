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
 * Unit tests for the Vietnamese language 'vi' resource bundle as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file of unit tests for the Vietnamese language 'vi' resource bundle is generated
        from the Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
class vi_Test extends root_Test {

  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000041   | 0x000045
      0x000047   | 0x000049
      0x00004b   | 0x000056
      0x000058   | 0x000059
      0x000061   | 0x000065
      0x000067   | 0x000069
      0x00006b   | 0x000076
      0x000078   | 0x000079
      0x0000c0   | 0x0000c3
      0x0000c8   | 0x0000ca
      0x0000cc   | 0x0000cd
      0x0000d2   | 0x0000d5
      0x0000d9   | 0x0000da
      0x0000dd   | 0x0000dd
      0x0000e0   | 0x0000e3
      0x0000e8   | 0x0000ea
      0x0000ec   | 0x0000ed
      0x0000f2   | 0x0000f5
      0x0000f9   | 0x0000fa
      0x0000fd   | 0x0000fd
      0x000102   | 0x000103
      0x000110   | 0x000111
      0x000128   | 0x000129
      0x000168   | 0x000169
      0x0001a0   | 0x0001a1
      0x0001af   | 0x0001b0
      0x001ea0   | 0x001ef9
      """)
  void getStandardSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new vi().getStandardSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000046   | 0x000046
      0x00004a   | 0x00004a
      0x000057   | 0x000057
      0x00005a   | 0x00005a
      0x000066   | 0x000066
      0x00006a   | 0x00006a
      0x000077   | 0x000077
      0x00007a   | 0x00007a
      """)
  void getAuxiliarySubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new vi().getAuxiliarySubset();

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
      0x0000a7   | 0x0000a7
      0x002010   | 0x002011
      0x002013   | 0x002014
      0x002018   | 0x002019
      0x00201c   | 0x00201d
      0x002020   | 0x002021
      0x002026   | 0x002026
      0x002032   | 0x002033
      """)
  void getPunctuationSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new vi().getPunctuationSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


}
