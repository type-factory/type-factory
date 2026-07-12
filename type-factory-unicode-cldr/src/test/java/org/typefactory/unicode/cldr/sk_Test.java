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
 * Unit tests for the Slovak language 'sk' resource bundle as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file of unit tests for the Slovak language 'sk' resource bundle is generated
        from the Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
class sk_Test extends root_Test {

  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000041   | 0x00005a
      0x000061   | 0x00007a
      0x0000c1   | 0x0000c1
      0x0000c4   | 0x0000c4
      0x0000c9   | 0x0000c9
      0x0000cd   | 0x0000cd
      0x0000d3   | 0x0000d4
      0x0000da   | 0x0000da
      0x0000dd   | 0x0000dd
      0x0000e1   | 0x0000e1
      0x0000e4   | 0x0000e4
      0x0000e9   | 0x0000e9
      0x0000ed   | 0x0000ed
      0x0000f3   | 0x0000f4
      0x0000fa   | 0x0000fa
      0x0000fd   | 0x0000fd
      0x00010c   | 0x00010f
      0x000139   | 0x00013a
      0x00013d   | 0x00013e
      0x000147   | 0x000148
      0x000154   | 0x000155
      0x000160   | 0x000161
      0x000164   | 0x000165
      0x00017d   | 0x00017e
      """)
  void getStandardSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new sk().getStandardSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x0000c0   | 0x0000c0
      0x0000c2   | 0x0000c2
      0x0000c5   | 0x0000c8
      0x0000ca   | 0x0000cc
      0x0000ce   | 0x0000cf
      0x0000d1   | 0x0000d2
      0x0000d6   | 0x0000d6
      0x0000d8   | 0x0000d9
      0x0000db   | 0x0000dc
      0x0000e0   | 0x0000e0
      0x0000e2   | 0x0000e2
      0x0000e5   | 0x0000e8
      0x0000ea   | 0x0000ec
      0x0000ee   | 0x0000ef
      0x0000f1   | 0x0000f2
      0x0000f6   | 0x0000f6
      0x0000f8   | 0x0000f9
      0x0000fb   | 0x0000fc
      0x0000ff   | 0x0000ff
      0x000100   | 0x000103
      0x000112   | 0x000115
      0x00012a   | 0x00012d
      0x00014c   | 0x000153
      0x000158   | 0x000159
      0x00016a   | 0x00016d
      0x000170   | 0x000171
      0x000178   | 0x000178
      """)
  void getAuxiliarySubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new sk().getAuxiliarySubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000021   | 0x000021
      0x000026   | 0x000026
      0x000028   | 0x00002a
      0x00002c   | 0x00002f
      0x00003a   | 0x00003b
      0x00003f   | 0x000040
      0x00005b   | 0x00005b
      0x00005d   | 0x00005d
      0x0000a7   | 0x0000a7
      0x002010   | 0x002011
      0x002013   | 0x002013
      0x002018   | 0x002018
      0x00201a   | 0x00201a
      0x00201c   | 0x00201c
      0x00201e   | 0x00201e
      0x002026   | 0x002026
      """)
  void getPunctuationSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new sk().getPunctuationSubset();

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

    final var subset = new sk().getDecimalDigitsSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


}
