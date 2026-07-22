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
 * Unit tests for the Uzbek (Cyrillic) language 'uz_Cyrl' resource bundle as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file of unit tests for the Uzbek (Cyrillic) language 'uz_Cyrl' resource bundle is generated
        from the Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
class uz_Cyrl_Test extends uz_Test {

  @Test
  void defaultConstructor_successfullyCreatesInstance() {

    final var instance = new uz_Cyrl();

    assertThat(instance)
        .isInstanceOf(uz_Cyrl.class)
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

    final var instance = new uz_Cyrl(standardSubset, auxiliarySubset, punctuationSubset, decimalDigitsSubset);

    assertThat(instance)
        .isInstanceOf(uz_Cyrl.class)
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
      0x000401   | 0x000401
      0x00040e   | 0x00040e
      0x000410   | 0x000425
      0x000427   | 0x000428
      0x00042a   | 0x00042a
      0x00042d   | 0x000445
      0x000447   | 0x000448
      0x00044a   | 0x00044a
      0x00044d   | 0x00044f
      0x000451   | 0x000451
      0x00045e   | 0x00045e
      0x000492   | 0x000493
      0x00049a   | 0x00049b
      0x0004b2   | 0x0004b3
      """)
  void getStandardSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new uz_Cyrl().getStandardSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000426   | 0x000426
      0x000429   | 0x000429
      0x00042b   | 0x00042c
      0x000446   | 0x000446
      0x000449   | 0x000449
      0x00044b   | 0x00044c
      """)
  void getAuxiliarySubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new uz_Cyrl().getAuxiliarySubset();

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

    final var subset = new uz_Cyrl().getDecimalDigitsSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


}
