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
import static org.typefactory.assertions.TypeFactoryAssertions.assertThatNoException;

import java.util.ResourceBundle;
import javax.annotation.processing.Generated;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.typefactory.Subset;

/**
 * Unit tests for the Persian (Afghanistan) language 'fa_AF' resource bundle as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file of unit tests for the Persian (Afghanistan) language 'fa_AF' resource bundle is generated
        from the Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
class fa_AF_Test extends fa_Test {

  @Test
  void defaultConstructor_successfullyCreatesInstance() {

    final var instance = new fa_AF();

    assertThat(instance)
        .isInstanceOf(fa_AF.class)
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

    final var instance = new fa_AF(standardSubset, auxiliarySubset, punctuationSubset, decimalDigitsSubset);

    assertThat(instance)
        .isInstanceOf(fa_AF.class)
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
      0x000625   | 0x000625
      0x000643   | 0x000643
      0x000649   | 0x00064a
      0x00064e   | 0x000650
      0x000652   | 0x000652
      0x000656   | 0x000656
      0x000670   | 0x000670
      0x00067c   | 0x00067c
      0x000681   | 0x000681
      0x000685   | 0x000685
      0x000689   | 0x000689
      0x000693   | 0x000693
      0x000696   | 0x000696
      0x00069a   | 0x00069a
      0x0006ab   | 0x0006ab
      0x0006bc   | 0x0006bc
      0x00200c   | 0x00200f
      """)
  void getAuxiliarySubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new fa_AF().getAuxiliarySubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


}
