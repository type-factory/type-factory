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
 * Unit tests for the Lithuanian language 'lt' resource bundle as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file of unit tests for the Lithuanian language 'lt' resource bundle is generated
        from the Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
class lt_Test extends root_Test {

  @Test
  void defaultConstructor_successfullyCreatesInstance() {

    final var instance = new lt();

    assertThat(instance)
        .isInstanceOf(lt.class)
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

    final var instance = new lt(standardSubset, auxiliarySubset, punctuationSubset, decimalDigitsSubset);

    assertThat(instance)
        .isInstanceOf(lt.class)
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
      0x000041   | 0x000050
      0x000052   | 0x000056
      0x000059   | 0x00005a
      0x000061   | 0x000070
      0x000072   | 0x000076
      0x000079   | 0x00007a
      0x000104   | 0x000105
      0x00010c   | 0x00010d
      0x000116   | 0x000119
      0x00012e   | 0x00012f
      0x000160   | 0x000161
      0x00016a   | 0x00016b
      0x000172   | 0x000173
      0x00017d   | 0x00017e
      """)
  void getStandardSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new lt().getStandardSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000043   | 0x000044
      0x000048   | 0x00004a
      0x00004c   | 0x00004d
      0x000051   | 0x000052
      0x000057   | 0x000058
      0x00005a   | 0x00005a
      0x000063   | 0x000064
      0x000068   | 0x00006a
      0x00006c   | 0x00006d
      0x000071   | 0x000072
      0x000077   | 0x000078
      0x00007a   | 0x00007b
      0x00007d   | 0x00007d
      0x0000c0   | 0x0000c1
      0x0000c3   | 0x0000c3
      0x0000c8   | 0x0000c9
      0x0000cc   | 0x0000cd
      0x0000d1   | 0x0000d3
      0x0000d5   | 0x0000d5
      0x0000d9   | 0x0000da
      0x0000e0   | 0x0000e1
      0x0000e3   | 0x0000e3
      0x0000e8   | 0x0000e9
      0x0000ec   | 0x0000ed
      0x0000f1   | 0x0000f3
      0x0000f5   | 0x0000f5
      0x0000f9   | 0x0000fa
      0x000104   | 0x000105
      0x000116   | 0x000119
      0x000128   | 0x000129
      0x00012e   | 0x00012f
      0x000168   | 0x00016b
      0x000172   | 0x000173
      0x00017d   | 0x00017e
      0x000300   | 0x000301
      0x000303   | 0x000303
      0x000307   | 0x000307
      0x001ebc   | 0x001ebd
      """)
  void getAuxiliarySubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new lt().getAuxiliarySubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000021   | 0x000021
      0x000028   | 0x000029
      0x00002c   | 0x00002e
      0x00003a   | 0x00003b
      0x00003f   | 0x00003f
      0x00005b   | 0x00005b
      0x00005d   | 0x00005d
      0x00007b   | 0x00007b
      0x00007d   | 0x00007d
      0x002010   | 0x002011
      0x002013   | 0x002014
      0x00201c   | 0x00201c
      0x00201e   | 0x00201e
      0x002026   | 0x002026
      """)
  void getPunctuationSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new lt().getPunctuationSubset();

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

    final var subset = new lt().getDecimalDigitsSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }


}
