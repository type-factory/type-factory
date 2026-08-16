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
 * Unit tests for the Finnish language 'fi' resource bundle as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file of unit tests for the Finnish language 'fi' resource bundle is generated
        from the Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
class fi_Test extends root_Test {

  @Test
  void defaultConstructor_successfullyCreatesInstance() {

    final var instance = new fi();

    assertThat(instance)
        .isInstanceOf(fi.class)
        .isInstanceOf(root.class)
        .isInstanceOf(AbstractCldrResourceBundle.class)
        .isInstanceOf(ResourceBundle.class);

    assertThat(instance.resourceBundleName()).isEqualTo("org.typefactory.unicode.cldr_fi");

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
        .withMessage("Cannot load locale data for key 'nonexistent_key' from resource org.typefactory.unicode.cldr_fi");
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.unicode.cldr.AbstractCldrResourceBundle_Test#constructorTestArguments")
  void constructor_successfullyCreatesInstanceWithParameters(
      final Subset standardSubset,
      final Subset auxiliarySubset,
      final Subset punctuationSubset,
      final Subset decimalDigitsSubset) {

    final var instance = new fi(standardSubset, auxiliarySubset, punctuationSubset, decimalDigitsSubset);

    assertThat(instance)
        .isInstanceOf(fi.class)
        .isInstanceOf(root.class)
        .isInstanceOf(AbstractCldrResourceBundle.class)
        .isInstanceOf(ResourceBundle.class);

    assertThat(instance.resourceBundleName()).isEqualTo("org.typefactory.unicode.cldr_fi");

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
        .withMessage("Cannot load locale data for key 'nonexistent_key' from resource org.typefactory.unicode.cldr_fi");

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
      0x0000c4   | 0x0000c5
      0x0000d6   | 0x0000d6
      0x0000e4   | 0x0000e5
      0x0000f6   | 0x0000f6
      0x000160   | 0x000161
      0x00017d   | 0x00017e
      """)
  void getStandardSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new fi().getStandardSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000049   | 0x000049
      0x000053   | 0x000053
      0x0000c0   | 0x0000c3
      0x0000c6   | 0x0000cb
      0x0000cd   | 0x0000d5
      0x0000d8   | 0x0000e3
      0x0000e6   | 0x0000eb
      0x0000ed   | 0x0000f5
      0x0000f8   | 0x0000ff
      0x000100   | 0x000107
      0x00010a   | 0x000113
      0x000116   | 0x00011b
      0x00011e   | 0x00011f
      0x000122   | 0x000123
      0x000126   | 0x000127
      0x00012a   | 0x00012b
      0x00012e   | 0x000131
      0x000136   | 0x000137
      0x000139   | 0x00013e
      0x000141   | 0x000148
      0x00014a   | 0x00014b
      0x000150   | 0x000155
      0x000158   | 0x00015f
      0x000162   | 0x000167
      0x00016a   | 0x00016b
      0x00016e   | 0x000173
      0x000178   | 0x00017c
      0x0001b7   | 0x0001b7
      0x0001e4   | 0x0001e9
      0x0001ee   | 0x0001ef
      0x000218   | 0x00021b
      0x00021e   | 0x00021f
      0x000292   | 0x000292
      """)
  void getAuxiliarySubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new fi().getAuxiliarySubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000021   | 0x000021
      0x000023   | 0x000023
      0x000026   | 0x000026
      0x000028   | 0x00002a
      0x00002c   | 0x00002f
      0x00003a   | 0x00003b
      0x00003f   | 0x000040
      0x00005b   | 0x00005d
      0x0000a7   | 0x0000a7
      0x0000bb   | 0x0000bb
      0x002010   | 0x002011
      0x002013   | 0x002013
      0x002019   | 0x002019
      0x00201d   | 0x00201d
      0x002026   | 0x002026
      """)
  void getPunctuationSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new fi().getPunctuationSubset();

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

    final var subset = new fi().getDecimalDigitsSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


}
