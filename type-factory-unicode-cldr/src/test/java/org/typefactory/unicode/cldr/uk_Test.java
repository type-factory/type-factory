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
 * Unit tests for the Ukrainian language 'uk' resource bundle as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file of unit tests for the Ukrainian language 'uk' resource bundle is generated
        from the Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
class uk_Test extends root_Test {

  @Test
  void defaultConstructor_successfullyCreatesInstance() {

    final var instance = new uk();

    assertThat(instance)
        .isInstanceOf(uk.class)
        .isInstanceOf(root.class)
        .isInstanceOf(AbstractCldrResourceBundle.class)
        .isInstanceOf(ResourceBundle.class);

    assertThat(instance.resourceBundleName()).isEqualTo("org.typefactory.unicode.cldr_uk");

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
        .withMessage("Cannot load locale data for key 'nonexistent_key' from resource org.typefactory.unicode.cldr_uk");
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.unicode.cldr.AbstractCldrResourceBundle_Test#constructorTestArguments")
  void constructor_successfullyCreatesInstanceWithParameters(
      final Subset standardSubset,
      final Subset auxiliarySubset,
      final Subset punctuationSubset,
      final Subset decimalDigitsSubset) {

    final var instance = new uk(standardSubset, auxiliarySubset, punctuationSubset, decimalDigitsSubset);

    assertThat(instance)
        .isInstanceOf(uk.class)
        .isInstanceOf(root.class)
        .isInstanceOf(AbstractCldrResourceBundle.class)
        .isInstanceOf(ResourceBundle.class);

    assertThat(instance.resourceBundleName()).isEqualTo("org.typefactory.unicode.cldr_uk");

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
        .withMessage("Cannot load locale data for key 'nonexistent_key' from resource org.typefactory.unicode.cldr_uk");

    if (standardSubset != null) assertThat(instance.getStandardSubset()).isSameAs(standardSubset);
    if (auxiliarySubset != null) assertThat(instance.getAuxiliarySubset()).isSameAs(auxiliarySubset);
    if (punctuationSubset != null) assertThat(instance.getPunctuationSubset()).isSameAs(punctuationSubset);
    if (decimalDigitsSubset != null) assertThat(instance.getDecimalDigitsSubset()).isSameAs(decimalDigitsSubset);
  }

  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x0002bc   | 0x0002bc
      0x000404   | 0x000404
      0x000406   | 0x000407
      0x000410   | 0x000429
      0x00042c   | 0x00042c
      0x00042e   | 0x000449
      0x00044c   | 0x00044c
      0x00044e   | 0x00044f
      0x000454   | 0x000454
      0x000456   | 0x000457
      0x000490   | 0x000491
      """)
  void getStandardSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new uk().getStandardSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000301   | 0x000301
      0x000401   | 0x000401
      0x000404   | 0x000404
      0x000406   | 0x000407
      0x000410   | 0x000410
      0x000415   | 0x000415
      0x000418   | 0x000418
      0x00041e   | 0x00041e
      0x000423   | 0x000423
      0x00042a   | 0x00042b
      0x00042d   | 0x000430
      0x000435   | 0x000435
      0x000438   | 0x000438
      0x00043e   | 0x00043e
      0x000443   | 0x000443
      0x00044a   | 0x00044b
      0x00044d   | 0x00044f
      0x000451   | 0x000451
      0x000454   | 0x000454
      0x000456   | 0x000457
      """)
  void getAuxiliarySubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new uk().getAuxiliarySubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000021   | 0x000022
      0x000027   | 0x00002a
      0x00002c   | 0x00002f
      0x00003a   | 0x00003b
      0x00003f   | 0x000040
      0x00005b   | 0x00005d
      0x00007b   | 0x00007b
      0x00007d   | 0x00007d
      0x0000a7   | 0x0000a7
      0x0000ab   | 0x0000ab
      0x0000bb   | 0x0000bb
      0x002011   | 0x002011
      0x002013   | 0x002013
      0x002019   | 0x002019
      0x00201c   | 0x00201c
      0x00201e   | 0x00201e
      0x002116   | 0x002116
      """)
  void getPunctuationSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new uk().getPunctuationSubset();

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

    final var subset = new uk().getDecimalDigitsSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


}
