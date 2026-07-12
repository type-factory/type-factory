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
 * Unit tests for the Javanese language 'jv' resource bundle as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file of unit tests for the Javanese language 'jv' resource bundle is generated
        from the Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
class jv_Test extends root_Test {

  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000041   | 0x000045
      0x000047   | 0x000050
      0x000052   | 0x000055
      0x000057   | 0x000057
      0x000059   | 0x000059
      0x000061   | 0x000065
      0x000067   | 0x000070
      0x000072   | 0x000075
      0x000077   | 0x000077
      0x000079   | 0x000079
      0x0000c2   | 0x0000c2
      0x0000c5   | 0x0000c5
      0x0000c8   | 0x0000ca
      0x0000cc   | 0x0000cc
      0x0000d2   | 0x0000d2
      0x0000d9   | 0x0000d9
      0x0000e2   | 0x0000e2
      0x0000e5   | 0x0000e5
      0x0000e8   | 0x0000ea
      0x0000ec   | 0x0000ec
      0x0000f2   | 0x0000f2
      0x0000f9   | 0x0000f9
      """)
  void getStandardSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new jv().getStandardSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000046   | 0x000046
      0x000051   | 0x000051
      0x000056   | 0x000056
      0x000058   | 0x000058
      0x00005a   | 0x00005a
      0x000066   | 0x000066
      0x000071   | 0x000071
      0x000076   | 0x000076
      0x000078   | 0x000078
      0x00007a   | 0x00007a
      """)
  void getAuxiliarySubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new jv().getAuxiliarySubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


}
