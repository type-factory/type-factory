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
import java.util.regex.Pattern;
import javax.annotation.processing.Generated;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.typefactory.Subset;

/**
 * Unit tests for the Tibetan language 'bo' resource bundle as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file of unit tests for the Tibetan language 'bo' resource bundle is generated
        from the Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
class bo_Test extends root_Test {

  @Test
  void defaultConstructor_successfullyCreatesInstance() {

    final var instance = new bo();

    assertThat(instance)
        .isInstanceOf(bo.class)
        .isInstanceOf(root.class)
        .isInstanceOf(AbstractCldrResourceBundle.class)
        .isInstanceOf(ResourceBundle.class);

    assertThat(instance.resourceBundleName()).isEqualTo("org.typefactory.unicode.cldr_bo");

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
        .withMessage("Cannot load locale data for key 'nonexistent_key' from resource org.typefactory.unicode.cldr_bo");
  }

  @ParameterizedTest
  @MethodSource("org.typefactory.unicode.cldr.AbstractCldrResourceBundle_Test#constructorTestArguments")
  void constructor_successfullyCreatesInstanceWithParameters(
      final Subset standardSubset,
      final Subset auxiliarySubset,
      final Subset punctuationSubset,
      final Subset decimalDigitsSubset) {

    final var instance = new bo(standardSubset, auxiliarySubset, punctuationSubset, decimalDigitsSubset);

    assertThat(instance)
        .isInstanceOf(bo.class)
        .isInstanceOf(root.class)
        .isInstanceOf(AbstractCldrResourceBundle.class)
        .isInstanceOf(ResourceBundle.class);

    assertThat(instance.resourceBundleName()).isEqualTo("org.typefactory.unicode.cldr_bo");

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
        .withMessage("Cannot load locale data for key 'nonexistent_key' from resource org.typefactory.unicode.cldr_bo");

    if (standardSubset != null) assertThat(instance.getStandardSubset()).isSameAs(standardSubset);
    if (auxiliarySubset != null) assertThat(instance.getAuxiliarySubset()).isSameAs(auxiliarySubset);
    if (punctuationSubset != null) assertThat(instance.getPunctuationSubset()).isSameAs(punctuationSubset);
    if (decimalDigitsSubset != null) assertThat(instance.getDecimalDigitsSubset()).isSameAs(decimalDigitsSubset);
  }

  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000f40   | 0x000f42
      0x000f44   | 0x000f47
      0x000f49   | 0x000f4c
      0x000f4e   | 0x000f51
      0x000f53   | 0x000f56
      0x000f58   | 0x000f5b
      0x000f5d   | 0x000f68
      0x000f6a   | 0x000f6a
      0x000f72   | 0x000f72
      0x000f74   | 0x000f74
      0x000f77   | 0x000f77
      0x000f79   | 0x000f7e
      0x000f80   | 0x000f80
      0x000f90   | 0x000f92
      0x000f94   | 0x000f97
      0x000f99   | 0x000f9c
      0x000f9e   | 0x000fa1
      0x000fa3   | 0x000fa6
      0x000fa8   | 0x000fab
      0x000fad   | 0x000fb8
      0x000fba   | 0x000fbc
      """)
  void getStandardSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new bo().getStandardSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }

  @Test
  void getStandardSubset_toString_returnsExpectedValue() {
    final var expected = "([ཀ-གང-ཇཉ-ཌཎ-དན-བམ-ཛཝ-ཨཪ ི ུ ཷ ཹ-ཾ ྀ ྐ-ྒ ྔ-ྗ ྙ-ྜ ྞ-ྡ ྣ-ྦ ྨ-ྫ ྭ-ྸ ྺ-ྼ]|ཀྵ|གྷ|ཌྷ|དྷ|བྷ|ཛྷ|ཱི|ཱུ|ཱྀ|྄ཿ|ྐྵ|ྒྷ|ྜྷ|ྡྷ|ྦྷ|ྫྷ|ྲྀ|ླྀ)";
    final var actual = new bo().getStandardSubset().toString();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void getStandardSubset_toPattern_returnsExpectedValue() {
    final var expected = "([ཀ-གང-ཇཉ-ཌཎ-དན-བམ-ཛཝ-ཨཪ\\u0f72\\u0f74\\u0f77\\u0f79-\\u0f7e\\u0f80\\u0f90-\\u0f92\\u0f94-\\u0f97\\u0f99-\\u0f9c\\u0f9e-\\u0fa1\\u0fa3-\\u0fa6\\u0fa8-\\u0fab\\u0fad-\\u0fb8\\u0fba-\\u0fbc]|ཀྵ|གྷ|ཌྷ|དྷ|བྷ|ཛྷ|ཱི|ཱུ|ཱྀ|྄ཿ|ྐྵ|ྒྷ|ྜྷ|ྡྷ|ྦྷ|ྫྷ|ྲྀ|ླྀ)";
    final var actual = new bo().getStandardSubset().toPattern();
    assertThat(actual).isEqualTo(expected);
    assertThatNoException().isThrownBy(() -> Pattern.compile(actual));
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000f00   | 0x000f00
      """)
  void getAuxiliarySubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new bo().getAuxiliarySubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }

  @Test
  void getAuxiliarySubset_toString_returnsExpectedValue() {
    final var expected = "[ༀ]";
    final var actual = new bo().getAuxiliarySubset().toString();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void getAuxiliarySubset_toPattern_returnsExpectedValue() {
    final var expected = "[ༀ]";
    final var actual = new bo().getAuxiliarySubset().toPattern();
    assertThat(actual).isEqualTo(expected);
    assertThatNoException().isThrownBy(() -> Pattern.compile(actual));
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x00003a   | 0x00003a
      0x000f0b   | 0x000f0b
      0x000f0d   | 0x000f0d
      """)
  void getPunctuationSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new bo().getPunctuationSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }

  @Test
  void getPunctuationSubset_toString_returnsExpectedValue() {
    final var expected = "[:་།]";
    final var actual = new bo().getPunctuationSubset().toString();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void getPunctuationSubset_toPattern_returnsExpectedValue() {
    final var expected = "[:་།]";
    final var actual = new bo().getPunctuationSubset().toPattern();
    assertThat(actual).isEqualTo(expected);
    assertThatNoException().isThrownBy(() -> Pattern.compile(actual));
  }


  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALID_FROM | VALID_TO
      0x000030   | 0x000039
      0x000f20   | 0x000f29
      """)
  void getDecimalDigitsSubset_containsExpectedCodePoints(
      final int validFromCodePoint,
      final int validToCodePoint) {

    final var subset = new bo().getDecimalDigitsSubset();

    assertThat(subset)
        .containsCodePoint(validFromCodePoint)
        .containsCodePoint(validToCodePoint)
        .containsCodePoint(validFromCodePoint + (validToCodePoint - validFromCodePoint) / 2);
  }

  @Test
  void getDecimalDigitsSubset_toString_returnsExpectedValue() {
    final var expected = "[0-9༠-༩]";
    final var actual = new bo().getDecimalDigitsSubset().toString();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void getDecimalDigitsSubset_toPattern_returnsExpectedValue() {
    final var expected = "[0-9༠-༩]";
    final var actual = new bo().getDecimalDigitsSubset().toPattern();
    assertThat(actual).isEqualTo(expected);
    assertThatNoException().isThrownBy(() -> Pattern.compile(actual));
  }


}
