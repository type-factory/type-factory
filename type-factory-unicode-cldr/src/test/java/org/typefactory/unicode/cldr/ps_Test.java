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
 * Unit tests for the Pashto language 'ps' resource bundle as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file of unit tests for the Pashto language 'ps' resource bundle is generated
        from the Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
class ps_Test extends root_Test {

  @Test
  void defaultConstructor_successfullyCreatesInstance() {

    final var instance = new ps();

    assertThat(instance)
        .isInstanceOf(ps.class)
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

    final var instance = new ps(standardSubset, auxiliarySubset, punctuationSubset, decimalDigitsSubset);

    assertThat(instance)
        .isInstanceOf(ps.class)
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
      0x000621   | 0x000624
      0x000626   | 0x00063a
      0x000641   | 0x000642
      0x000644   | 0x000648
      0x00064a   | 0x000652
      0x000654   | 0x000654
      0x000670   | 0x000670
      0x00067c   | 0x00067c
      0x00067e   | 0x00067e
      0x000681   | 0x000681
      0x000685   | 0x000686
      0x000689   | 0x000689
      0x000693   | 0x000693
      0x000696   | 0x000696
      0x000698   | 0x000698
      0x00069a   | 0x00069a
      0x0006a9   | 0x0006a9
      0x0006ab   | 0x0006ab
      0x0006af   | 0x0006af
      0x0006bc   | 0x0006bc
      0x0006cc   | 0x0006cd
      0x0006d0   | 0x0006d0
      """)
  void getStandardSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new ps().getStandardSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x0006d2   | 0x0006d2
      0x00200c   | 0x00200f
      """)
  void getAuxiliarySubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new ps().getAuxiliarySubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000021   | 0x000021
      0x000027   | 0x000029
      0x00002f   | 0x00002f
      0x00003a   | 0x00003b
      0x00005b   | 0x00005b
      0x00005d   | 0x00005d
      0x00007b   | 0x00007b
      0x00007d   | 0x00007d
      0x00060c   | 0x00060c
      0x0006d4   | 0x0006d4
      0x002018   | 0x002018
      """)
  void getPunctuationSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new ps().getPunctuationSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000030   | 0x000039
      0x0006f0   | 0x0006f9
      """)
  void getDecimalDigitsSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new ps().getDecimalDigitsSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


}
