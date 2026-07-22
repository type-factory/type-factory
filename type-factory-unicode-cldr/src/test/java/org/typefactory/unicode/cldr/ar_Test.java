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
 * Unit tests for the Arabic language 'ar' resource bundle as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file of unit tests for the Arabic language 'ar' resource bundle is generated
        from the Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
class ar_Test extends root_Test {

  @Test
  void defaultConstructor_successfullyCreatesInstance() {

    final var instance = new ar();

    assertThat(instance)
        .isInstanceOf(ar.class)
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

    final var instance = new ar(standardSubset, auxiliarySubset, punctuationSubset, decimalDigitsSubset);

    assertThat(instance)
        .isInstanceOf(ar.class)
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
      0x000621   | 0x00063a
      0x000641   | 0x000652
      0x000670   | 0x000670
      """)
  void getStandardSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new ar().getStandardSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000640   | 0x000640
      0x00066f   | 0x00066f
      0x00067e   | 0x00067e
      0x000686   | 0x000686
      0x000698   | 0x000698
      0x00069c   | 0x00069c
      0x0006a2   | 0x0006a2
      0x0006a4   | 0x0006a5
      0x0006a7   | 0x0006a9
      0x0006af   | 0x0006af
      0x0006cc   | 0x0006cc
      0x00200c   | 0x00200f
      """)
  void getAuxiliarySubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new ar().getAuxiliarySubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000021   | 0x000022
      0x000027   | 0x000029
      0x00002d   | 0x00002e
      0x00003a   | 0x00003a
      0x00005b   | 0x00005b
      0x00005d   | 0x00005d
      0x0000ab   | 0x0000ab
      0x0000bb   | 0x0000bb
      0x00060c   | 0x00060c
      0x00061b   | 0x00061b
      0x00061f   | 0x00061f
      0x002010   | 0x002011
      0x002013   | 0x002014
      0x002026   | 0x002026
      """)
  void getPunctuationSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new ar().getPunctuationSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000030   | 0x000039
      0x000660   | 0x000669
      """)
  void getDecimalDigitsSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new ar().getDecimalDigitsSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


}
