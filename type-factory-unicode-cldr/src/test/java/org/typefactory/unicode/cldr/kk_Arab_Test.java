/*
 * Copyright © 2021-2026 Evan Toliopoulos (typefactory.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.typefactory.unicode.cldr;

import static org.typefactory.assertions.TypeFactoryAssertions.assertThat;
import static org.typefactory.assertions.TypeFactoryAssertions.assertThatExceptionOfType;
import static org.typefactory.assertions.TypeFactoryAssertions.assertThatNoException;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.annotation.processing.Generated;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.typefactory.Subset;

/**
 * Unit tests for the Kazakh (Arabic) language 'kk_Arab' resource bundle as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file of unit tests for the Kazakh (Arabic) language 'kk_Arab' resource bundle is generated
        from the Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
class kk_Arab_Test extends kk_Test {

  @Test
  void defaultConstructor_successfullyCreatesInstance() {

    final var instance = new kk_Arab();

    assertThat(instance)
        .isInstanceOf(kk_Arab.class)
        .isInstanceOf(root.class)
        .isInstanceOf(AbstractCldrResourceBundle.class)
        .isInstanceOf(ResourceBundle.class);

    assertThat(instance.resourceBundleName()).isEqualTo("org.typefactory.unicode.cldr_kk_Arab");

    assertThatNoException().isThrownBy(() -> instance.getStandardSubset());
    assertThatNoException().isThrownBy(() -> instance.getAuxiliarySubset());
    assertThatNoException().isThrownBy(() -> instance.getPunctuationSubset());
    assertThatNoException().isThrownBy(() -> instance.getDecimalDigitsSubset());

    assertThat(instance.getKeys())
        .isInstanceOf(java.util.Enumeration.class)
        .satisfies(enumeration -> {
          assertThat(enumeration.nextElement()).isEqualTo(AbstractCldrResourceBundle.STANDARD_CHARACTERS);
          assertThat(enumeration.nextElement()).isEqualTo(AbstractCldrResourceBundle.AUXILIARY_CHARACTERS);
          assertThat(enumeration.nextElement()).isEqualTo(AbstractCldrResourceBundle.PUNCTUATION_CHARACTERS);
          assertThat(enumeration.nextElement()).isEqualTo(AbstractCldrResourceBundle.DECIMAL_DIGITS);
          assertThat(enumeration.hasMoreElements()).isFalse();
        });

    assertThatNoException().isThrownBy(() -> instance.getObject(AbstractCldrResourceBundle.STANDARD_CHARACTERS));
    assertThatNoException().isThrownBy(() -> instance.getObject(AbstractCldrResourceBundle.AUXILIARY_CHARACTERS));
    assertThatNoException().isThrownBy(() -> instance.getObject(AbstractCldrResourceBundle.PUNCTUATION_CHARACTERS));
    assertThatNoException().isThrownBy(() -> instance.getObject(AbstractCldrResourceBundle.DECIMAL_DIGITS));

    assertThatExceptionOfType(MissingResourceException.class)
        .isThrownBy(() -> instance.getObject("nonexistent_key"))
        .withMessage("Cannot load locale data for key 'nonexistent_key' from resource org.typefactory.unicode.cldr_kk_Arab");
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.unicode.cldr.AbstractCldrResourceBundle_Test#constructorTestArguments")
  void constructor_successfullyCreatesInstanceWithParameters(
      final Subset standardSubset,
      final Subset auxiliarySubset,
      final Subset punctuationSubset,
      final Subset decimalDigitsSubset) {

    final var instance = new kk_Arab(standardSubset, auxiliarySubset, punctuationSubset, decimalDigitsSubset);

    assertThat(instance)
        .isInstanceOf(kk_Arab.class)
        .isInstanceOf(root.class)
        .isInstanceOf(AbstractCldrResourceBundle.class)
        .isInstanceOf(ResourceBundle.class);

    assertThat(instance.resourceBundleName()).isEqualTo("org.typefactory.unicode.cldr_kk_Arab");

    assertThatNoException().isThrownBy(() -> instance.getStandardSubset());
    assertThatNoException().isThrownBy(() -> instance.getAuxiliarySubset());
    assertThatNoException().isThrownBy(() -> instance.getPunctuationSubset());
    assertThatNoException().isThrownBy(() -> instance.getDecimalDigitsSubset());

    assertThat(instance.getKeys())
        .isInstanceOf(java.util.Enumeration.class)
        .satisfies(enumeration -> {
          assertThat(enumeration.nextElement()).isEqualTo(AbstractCldrResourceBundle.STANDARD_CHARACTERS);
          assertThat(enumeration.nextElement()).isEqualTo(AbstractCldrResourceBundle.AUXILIARY_CHARACTERS);
          assertThat(enumeration.nextElement()).isEqualTo(AbstractCldrResourceBundle.PUNCTUATION_CHARACTERS);
          assertThat(enumeration.nextElement()).isEqualTo(AbstractCldrResourceBundle.DECIMAL_DIGITS);
          assertThat(enumeration.hasMoreElements()).isFalse();
        });

    assertThatNoException().isThrownBy(() -> instance.getObject(AbstractCldrResourceBundle.STANDARD_CHARACTERS));
    assertThatNoException().isThrownBy(() -> instance.getObject(AbstractCldrResourceBundle.AUXILIARY_CHARACTERS));
    assertThatNoException().isThrownBy(() -> instance.getObject(AbstractCldrResourceBundle.PUNCTUATION_CHARACTERS));
    assertThatNoException().isThrownBy(() -> instance.getObject(AbstractCldrResourceBundle.DECIMAL_DIGITS));

    assertThatExceptionOfType(MissingResourceException.class)
        .isThrownBy(() -> instance.getObject("nonexistent_key"))
        .withMessage("Cannot load locale data for key 'nonexistent_key' from resource org.typefactory.unicode.cldr_kk_Arab");

    if (standardSubset != null) assertThat(instance.getStandardSubset()).isSameAs(standardSubset);
    if (auxiliarySubset != null) assertThat(instance.getAuxiliarySubset()).isSameAs(auxiliarySubset);
    if (punctuationSubset != null) assertThat(instance.getPunctuationSubset()).isSameAs(punctuationSubset);
    if (decimalDigitsSubset != null) assertThat(instance.getDecimalDigitsSubset()).isSameAs(decimalDigitsSubset);
  }

  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000621   | 0x000621
      0x000627   | 0x000628
      0x00062a   | 0x00062a
      0x00062c   | 0x00062d
      0x00062f   | 0x00062f
      0x000631   | 0x000634
      0x000639   | 0x000639
      0x000641   | 0x000646
      0x000648   | 0x00064a
      0x00067e   | 0x00067e
      0x000686   | 0x000686
      0x0006ad   | 0x0006ad
      0x0006af   | 0x0006af
      0x0006be   | 0x0006be
      0x0006c6   | 0x0006c7
      0x0006cb   | 0x0006cb
      0x0006d5   | 0x0006d5
      """)
  void getStandardSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new kk_Arab().getStandardSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000021   | 0x000023
      0x000026   | 0x00002a
      0x00002d   | 0x00002f
      0x00003a   | 0x00003a
      0x000040   | 0x000040
      0x00005b   | 0x00005b
      0x00005d   | 0x00005d
      0x00007b   | 0x00007b
      0x00007d   | 0x00007d
      0x0000a7   | 0x0000a7
      0x0000ab   | 0x0000ab
      0x0000bb   | 0x0000bb
      0x00060c   | 0x00060c
      0x00061b   | 0x00061b
      0x00061f   | 0x00061f
      0x002010   | 0x002011
      0x002013   | 0x002014
      0x002018   | 0x002019
      0x00201c   | 0x00201d
      0x002026   | 0x002026
      """)
  void getPunctuationSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new kk_Arab().getPunctuationSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


}
