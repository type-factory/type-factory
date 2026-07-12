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
 * Unit tests for the Inuktitut language 'iu' resource bundle as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file of unit tests for the Inuktitut language 'iu' resource bundle is generated
        from the Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
class iu_Test extends root_Test {

  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x001403   | 0x001406
      0x00140a   | 0x00140b
      0x001431   | 0x001434
      0x001438   | 0x001439
      0x001449   | 0x001449
      0x00144e   | 0x001451
      0x001455   | 0x001456
      0x001466   | 0x001466
      0x00146d   | 0x001470
      0x001472   | 0x001473
      0x001483   | 0x001483
      0x00148b   | 0x00148e
      0x001490   | 0x001491
      0x0014a1   | 0x0014a1
      0x0014a5   | 0x0014a8
      0x0014aa   | 0x0014ab
      0x0014bb   | 0x0014bb
      0x0014c2   | 0x0014c5
      0x0014c7   | 0x0014c8
      0x0014d0   | 0x0014d0
      0x0014d5   | 0x0014d8
      0x0014da   | 0x0014db
      0x0014ea   | 0x0014ea
      0x0014ef   | 0x0014f2
      0x0014f4   | 0x0014f5
      0x001505   | 0x001505
      0x001528   | 0x00152b
      0x00152d   | 0x00152e
      0x00153e   | 0x00153e
      0x001546   | 0x001549
      0x00154b   | 0x00154c
      0x001550   | 0x001550
      0x001555   | 0x00155a
      0x00155d   | 0x00155d
      0x00157f   | 0x001583
      0x001585   | 0x001585
      0x00158f   | 0x00158f
      0x001591   | 0x001596
      0x0015a0   | 0x0015a6
      0x001671   | 0x001676
      """)
  void getStandardSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new iu().getStandardSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


}
