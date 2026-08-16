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
 * Unit tests for the Chinese (Latin) language 'zh_Latn' resource bundle as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file of unit tests for the Chinese (Latin) language 'zh_Latn' resource bundle is generated
        from the Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
class zh_Latn_Test extends zh_Test {

  @Test
  void defaultConstructor_successfullyCreatesInstance() {

    final var instance = new zh_Latn();

    assertThat(instance)
        .isInstanceOf(zh_Latn.class)
        .isInstanceOf(root.class)
        .isInstanceOf(AbstractCldrResourceBundle.class)
        .isInstanceOf(ResourceBundle.class);

    assertThat(instance.resourceBundleName()).isEqualTo("org.typefactory.unicode.cldr_zh_Latn");

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
        .withMessage("Cannot load locale data for key 'nonexistent_key' from resource org.typefactory.unicode.cldr_zh_Latn");
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.unicode.cldr.AbstractCldrResourceBundle_Test#constructorTestArguments")
  void constructor_successfullyCreatesInstanceWithParameters(
      final Subset standardSubset,
      final Subset auxiliarySubset,
      final Subset punctuationSubset,
      final Subset decimalDigitsSubset) {

    final var instance = new zh_Latn(standardSubset, auxiliarySubset, punctuationSubset, decimalDigitsSubset);

    assertThat(instance)
        .isInstanceOf(zh_Latn.class)
        .isInstanceOf(root.class)
        .isInstanceOf(AbstractCldrResourceBundle.class)
        .isInstanceOf(ResourceBundle.class);

    assertThat(instance.resourceBundleName()).isEqualTo("org.typefactory.unicode.cldr_zh_Latn");

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
        .withMessage("Cannot load locale data for key 'nonexistent_key' from resource org.typefactory.unicode.cldr_zh_Latn");

    if (standardSubset != null) assertThat(instance.getStandardSubset()).isSameAs(standardSubset);
    if (auxiliarySubset != null) assertThat(instance.getAuxiliarySubset()).isSameAs(auxiliarySubset);
    if (punctuationSubset != null) assertThat(instance.getPunctuationSubset()).isSameAs(punctuationSubset);
    if (decimalDigitsSubset != null) assertThat(instance.getDecimalDigitsSubset()).isSameAs(decimalDigitsSubset);
  }

  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000041   | 0x000055
      0x000057   | 0x00005a
      0x000061   | 0x000075
      0x000077   | 0x00007b
      0x00007d   | 0x00007d
      0x0000c0   | 0x0000c1
      0x0000c8   | 0x0000ca
      0x0000cc   | 0x0000cd
      0x0000d2   | 0x0000d3
      0x0000d9   | 0x0000da
      0x0000dc   | 0x0000dc
      0x0000e0   | 0x0000e1
      0x0000e8   | 0x0000ea
      0x0000ec   | 0x0000ed
      0x0000f2   | 0x0000f3
      0x0000f9   | 0x0000fa
      0x0000fc   | 0x0000fc
      0x000100   | 0x000101
      0x000108   | 0x000109
      0x000112   | 0x000113
      0x00011a   | 0x00011b
      0x00012a   | 0x00012b
      0x000143   | 0x000144
      0x000147   | 0x000148
      0x00014a   | 0x00014d
      0x00015c   | 0x00015d
      0x00016a   | 0x00016b
      0x0001cd   | 0x0001dc
      0x0001f8   | 0x0001f9
      0x000300   | 0x000300
      0x000304   | 0x000304
      0x00030c   | 0x00030c
      0x001e3e   | 0x001e3f
      0x001e90   | 0x001e91
      0x001ebe   | 0x001ec1
      """)
  void getStandardSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new zh_Latn().getStandardSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000056   | 0x000056
      0x000076   | 0x000076
      """)
  void getAuxiliarySubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new zh_Latn().getAuxiliarySubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000021   | 0x000022
      0x000027   | 0x000027
      0x00002c   | 0x00002e
      0x00003f   | 0x00003f
      0x0000b7   | 0x0000b7
      0x002011   | 0x002011
      """)
  void getPunctuationSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new zh_Latn().getPunctuationSubset();

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

    final var subset = new zh_Latn().getDecimalDigitsSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


}
