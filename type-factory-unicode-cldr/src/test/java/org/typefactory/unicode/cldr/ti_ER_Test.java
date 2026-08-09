/*
   Copyright 2021-2026 Evan Toliopoulos (typefactory.org)

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
import static org.typefactory.assertions.TypeFactoryAssertions.assertThatNoException;

import java.util.ResourceBundle;
import javax.annotation.processing.Generated;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.typefactory.Subset;

/**
 * Unit tests for the Tigrinya (Eritrea) language 'ti_ER' resource bundle as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file of unit tests for the Tigrinya (Eritrea) language 'ti_ER' resource bundle is generated
        from the Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
class ti_ER_Test extends ti_Test {

  @Test
  void defaultConstructor_successfullyCreatesInstance() {

    final var instance = new ti_ER();

    assertThat(instance)
        .isInstanceOf(ti_ER.class)
        .isInstanceOf(root.class)
        .isInstanceOf(AbstractCldrResourceBundle.class)
        .isInstanceOf(ResourceBundle.class);

    assertThatNoException().isThrownBy(() -> instance.getStandardSubset());
    assertThatNoException().isThrownBy(() -> instance.getAuxiliarySubset());
    assertThatNoException().isThrownBy(() -> instance.getPunctuationSubset());
    assertThatNoException().isThrownBy(() -> instance.getDecimalDigitsSubset());
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.unicode.cldr.AbstractCldrResourceBundle_Test#constructorTestArguments")
  void constructor_successfullyCreatesInstanceWithParameters(
      final Subset standardSubset,
      final Subset auxiliarySubset,
      final Subset punctuationSubset,
      final Subset decimalDigitsSubset) {

    final var instance = new ti_ER(standardSubset, auxiliarySubset, punctuationSubset, decimalDigitsSubset);

    assertThat(instance)
        .isInstanceOf(ti_ER.class)
        .isInstanceOf(root.class)
        .isInstanceOf(AbstractCldrResourceBundle.class)
        .isInstanceOf(ResourceBundle.class);

    assertThatNoException().isThrownBy(() -> instance.getStandardSubset());
    assertThatNoException().isThrownBy(() -> instance.getAuxiliarySubset());
    assertThatNoException().isThrownBy(() -> instance.getPunctuationSubset());
    assertThatNoException().isThrownBy(() -> instance.getDecimalDigitsSubset());

    if (standardSubset != null) assertThat(instance.getStandardSubset()).isSameAs(standardSubset);
    if (auxiliarySubset != null) assertThat(instance.getAuxiliarySubset()).isSameAs(auxiliarySubset);
    if (punctuationSubset != null) assertThat(instance.getPunctuationSubset()).isSameAs(punctuationSubset);
    if (decimalDigitsSubset != null) assertThat(instance.getDecimalDigitsSubset()).isSameAs(decimalDigitsSubset);
  }

  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x001200   | 0x001206
      0x001208   | 0x00121f
      0x001228   | 0x001246
      0x001248   | 0x001248
      0x00124a   | 0x00124d
      0x001250   | 0x001256
      0x001258   | 0x001258
      0x00125a   | 0x00125d
      0x001260   | 0x001286
      0x001288   | 0x001288
      0x00128a   | 0x00128d
      0x001290   | 0x0012ae
      0x0012b0   | 0x0012b0
      0x0012b2   | 0x0012b5
      0x0012b8   | 0x0012be
      0x0012c0   | 0x0012c0
      0x0012c2   | 0x0012c5
      0x0012c8   | 0x0012ce
      0x0012d0   | 0x0012d6
      0x0012d8   | 0x0012ee
      0x0012f0   | 0x0012f7
      0x001300   | 0x00130e
      0x001310   | 0x001310
      0x001312   | 0x001315
      0x001320   | 0x00132f
      0x001338   | 0x00133f
      0x001348   | 0x001357
      0x00135f   | 0x00137c
      """)
  void getStandardSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new ti_ER().getStandardSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x001207   | 0x001207
      0x001220   | 0x001227
      0x001247   | 0x001247
      0x001287   | 0x001287
      0x0012af   | 0x0012af
      0x0012cf   | 0x0012cf
      0x0012ef   | 0x0012ef
      0x0012f8   | 0x0012ff
      0x00130f   | 0x00130f
      0x001318   | 0x00131f
      0x001340   | 0x001347
      0x001358   | 0x00135a
      0x001380   | 0x001399
      0x002d80   | 0x002d96
      0x002da0   | 0x002da6
      0x002da8   | 0x002dae
      0x002db0   | 0x002db6
      0x002db8   | 0x002dbe
      0x002dc0   | 0x002dc6
      0x002dc8   | 0x002dce
      0x002dd0   | 0x002dd6
      0x002dd8   | 0x002dde
      """)
  void getAuxiliarySubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new ti_ER().getAuxiliarySubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


}
