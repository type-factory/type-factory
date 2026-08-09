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
 * Unit tests for the Scottish Gaelic language 'gd' resource bundle as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file of unit tests for the Scottish Gaelic language 'gd' resource bundle is generated
        from the Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
class gd_Test extends root_Test {

  @Test
  void defaultConstructor_successfullyCreatesInstance() {

    final var instance = new gd();

    assertThat(instance)
        .isInstanceOf(gd.class)
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

    final var instance = new gd(standardSubset, auxiliarySubset, punctuationSubset, decimalDigitsSubset);

    assertThat(instance)
        .isInstanceOf(gd.class)
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
      0x000041   | 0x000049
      0x00004c   | 0x000050
      0x000052   | 0x000055
      0x000061   | 0x000069
      0x00006c   | 0x000070
      0x000072   | 0x000075
      0x0000c0   | 0x0000c0
      0x0000c8   | 0x0000c8
      0x0000cc   | 0x0000cc
      0x0000d2   | 0x0000d2
      0x0000d9   | 0x0000d9
      0x0000e0   | 0x0000e0
      0x0000e8   | 0x0000e8
      0x0000ec   | 0x0000ec
      0x0000f2   | 0x0000f2
      0x0000f9   | 0x0000f9
      """)
  void getStandardSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new gd().getStandardSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000049   | 0x00004b
      0x000051   | 0x000051
      0x000056   | 0x00005a
      0x00006a   | 0x00006b
      0x000071   | 0x000071
      0x000076   | 0x00007a
      0x0000c1   | 0x0000c7
      0x0000c9   | 0x0000cb
      0x0000cd   | 0x0000cf
      0x0000d1   | 0x0000d1
      0x0000d3   | 0x0000d4
      0x0000d6   | 0x0000d6
      0x0000d8   | 0x0000d8
      0x0000da   | 0x0000dc
      0x0000e1   | 0x0000e7
      0x0000e9   | 0x0000eb
      0x0000ed   | 0x0000ef
      0x0000f1   | 0x0000f1
      0x0000f3   | 0x0000f4
      0x0000f6   | 0x0000f6
      0x0000f8   | 0x0000f8
      0x0000fa   | 0x0000fc
      0x0000ff   | 0x0000ff
      0x000100   | 0x000103
      0x00010a   | 0x00010b
      0x000112   | 0x000115
      0x000120   | 0x000121
      0x00012a   | 0x00012d
      0x000131   | 0x000131
      0x000141   | 0x000142
      0x00014c   | 0x00014f
      0x000152   | 0x000153
      0x00015e   | 0x00015f
      0x00016a   | 0x00016d
      0x000178   | 0x000178
      0x000218   | 0x000219
      0x001e0a   | 0x001e0b
      0x001e1e   | 0x001e1f
      0x001e40   | 0x001e41
      0x001e56   | 0x001e57
      0x001e60   | 0x001e61
      0x001e6a   | 0x001e6b
      """)
  void getAuxiliarySubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new gd().getAuxiliarySubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000021   | 0x000023
      0x000025   | 0x00002a
      0x00002c   | 0x00002f
      0x00003a   | 0x00003b
      0x00003f   | 0x000040
      0x00005b   | 0x00005b
      0x00005d   | 0x00005d
      0x00007b   | 0x00007b
      0x00007d   | 0x00007d
      0x0000a1   | 0x0000a1
      0x0000a7   | 0x0000a7
      0x0000a9   | 0x0000a9
      0x0000ae   | 0x0000ae
      0x0000b0   | 0x0000b0
      0x0000b6   | 0x0000b7
      0x002010   | 0x002011
      0x002013   | 0x002014
      0x002018   | 0x002019
      0x00201c   | 0x00201d
      0x002020   | 0x002021
      0x002026   | 0x002027
      0x00204a   | 0x00204a
      0x002122   | 0x002122
      """)
  void getPunctuationSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new gd().getPunctuationSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


}
