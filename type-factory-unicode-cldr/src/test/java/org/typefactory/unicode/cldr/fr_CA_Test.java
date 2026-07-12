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
 * Unit tests for the French (Canada) language 'fr_CA' resource bundle as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file of unit tests for the French (Canada) language 'fr_CA' resource bundle is generated
        from the Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
class fr_CA_Test extends fr_Test {

  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x0000c1   | 0x0000c1
      0x0000c3   | 0x0000c5
      0x0000cc   | 0x0000cd
      0x0000d1   | 0x0000d3
      0x0000d6   | 0x0000d6
      0x0000d8   | 0x0000d8
      0x0000da   | 0x0000da
      0x0000e1   | 0x0000e1
      0x0000e3   | 0x0000e5
      0x0000ec   | 0x0000ed
      0x0000f1   | 0x0000f3
      0x0000f6   | 0x0000f6
      0x0000f8   | 0x0000f8
      0x0000fa   | 0x0000fa
      0x000100   | 0x000101
      0x000112   | 0x000113
      0x00012a   | 0x00012b
      0x0001d3   | 0x0001d4
      """)
  void getAuxiliarySubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new fr_CA().getAuxiliarySubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


}
