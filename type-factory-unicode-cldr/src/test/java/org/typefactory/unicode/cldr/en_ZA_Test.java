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
 * Unit tests for the English (South Africa) language 'en_ZA' resource bundle as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file of unit tests for the English (South Africa) language 'en_ZA' resource bundle is generated
        from the Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
class en_ZA_Test extends en_Test {

  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x0000c0   | 0x0000c2
      0x0000c4   | 0x0000cf
      0x0000d1   | 0x0000d4
      0x0000d6   | 0x0000d6
      0x0000d8   | 0x0000dc
      0x0000e0   | 0x0000e2
      0x0000e4   | 0x0000ef
      0x0000f1   | 0x0000f4
      0x0000f6   | 0x0000f6
      0x0000f8   | 0x0000fc
      0x0000ff   | 0x0000ff
      0x000100   | 0x000103
      0x000112   | 0x000115
      0x00012a   | 0x00012d
      0x00014c   | 0x00014f
      0x000152   | 0x000153
      0x000160   | 0x000161
      0x00016a   | 0x00016d
      0x000178   | 0x000178
      0x001e12   | 0x001e13
      0x001e3c   | 0x001e3d
      0x001e44   | 0x001e45
      0x001e4a   | 0x001e4b
      0x001e70   | 0x001e71
      """)
  void getAuxiliarySubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new en_ZA().getAuxiliarySubset();

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

    final var subset = new en_ZA().getDecimalDigitsSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


}
