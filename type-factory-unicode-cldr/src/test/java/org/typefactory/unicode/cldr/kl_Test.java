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
 * Unit tests for the Kalaallisut language 'kl' resource bundle as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file of unit tests for the Kalaallisut language 'kl' resource bundle is generated
        from the Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
class kl_Test extends root_Test {

  @Test
  void defaultConstructor_successfullyCreatesInstance() {

    final var instance = new kl();

    assertThat(instance)
        .isInstanceOf(kl.class)
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

    final var instance = new kl(standardSubset, auxiliarySubset, punctuationSubset, decimalDigitsSubset);

    assertThat(instance)
        .isInstanceOf(kl.class)
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
      0x000041   | 0x00005a
      0x000061   | 0x00007a
      0x0000c5   | 0x0000c6
      0x0000d8   | 0x0000d8
      0x0000e5   | 0x0000e6
      0x0000f8   | 0x0000f8
      """)
  void getStandardSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new kl().getStandardSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x0000c1   | 0x0000c3
      0x0000c9   | 0x0000ca
      0x0000cd   | 0x0000ce
      0x0000d4   | 0x0000d5
      0x0000da   | 0x0000db
      0x0000e1   | 0x0000e3
      0x0000e9   | 0x0000ea
      0x0000ed   | 0x0000ee
      0x0000f4   | 0x0000f5
      0x0000fa   | 0x0000fb
      0x000128   | 0x000129
      0x000138   | 0x000138
      0x000168   | 0x000169
      0x001ebc   | 0x001ebd
      """)
  void getAuxiliarySubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new kl().getAuxiliarySubset();

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

    final var subset = new kl().getDecimalDigitsSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


}
