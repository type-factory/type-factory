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

import javax.annotation.processing.Generated;
import org.typefactory.Subset;
import org.typefactory.impl.Factory;

/**
 * Provides Type Factory subsets for the Chinese (Latin) as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Chinese (Latin) language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class zh_Latn extends zh {

  public zh_Latn() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected zh_Latn(
          final Subset standardSubset,
          final Subset auxiliarySubset,
          final Subset punctuationSubset,
          final Subset decimalDigitsSubset) {
    super(
        defaultIfNull(standardSubset, STANDARD_CHARACTERS_SUBSET),
        defaultIfNull(auxiliarySubset, AUXILIARY_CHARACTERS_SUBSET),
        defaultIfNull(punctuationSubset, PUNCTUATION_CHARACTERS_SUBSET),
        defaultIfNull(decimalDigitsSubset, DECIMAL_DIGITS_SUBSET));
  }

  /**
   * <p>The standard characters for the Chinese (Latin) language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the standard characters
   *    are inherited from the superclass.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

      new char[]{
          0x41_55, //  A B C D E F G H I J K L M N O P Q R S T U
          0x57_5a, //  W X Y Z
          0x61_75, //  a b c d e f g h i j k l m n o p q r s t u
          0x77_7a, //  w x y z
          0xc0_c1, //  À Á
          0xc8_ca, //  È É Ê
          0xcc_cd, //  Ì Í
          0xd2_d3, //  Ò Ó
          0xd9_da, //  Ù Ú
          0xdc_dc, //  Ü
          0xe0_e1, //  à á
          0xe8_ea, //  è é ê
          0xec_ed, //  ì í
          0xf2_f3, //  ò ó
          0xf9_fa, //  ù ú
          0xfc_fc, //  ü
      },
      new int[]{
          0x0100_0101, //  Ā ā
          0x0108_0109, //  Ĉ ĉ
          0x0112_0113, //  Ē ē
          0x011a_011b, //  Ě ě
          0x012a_012b, //  Ī ī
          0x0143_0144, //  Ń ń
          0x0147_0148, //  Ň ň
          0x014a_014d, //  Ŋ ŋ Ō ō
          0x015c_015d, //  Ŝ ŝ
          0x016a_016b, //  Ū ū
          0x01cd_01dc, //  Ǎ ǎ Ǐ ǐ Ǒ ǒ Ǔ ǔ Ǖ ǖ Ǘ ǘ Ǚ ǚ Ǜ ǜ
          0x01f8_01f9, //  Ǹ ǹ
          0x1e3e_1e3f, //  Ḿ ḿ
          0x1e90_1e91, //  Ẑ ẑ
          0x1ebe_1ec1, //  Ế ế Ề ề
      },
      31, 122);


  /**
   * <p>The auxiliary characters for the Chinese (Latin) language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the auxiliary characters
   *    are inherited from the superclass.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

      new char[]{
          0x56_56, //  V
          0x76_76, //  v
      },
      2, 2);


  /**
   * <p>The punctuation characters for the Chinese (Latin) language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the punctuation characters
   *    are inherited from the superclass.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

      new char[]{
          0x21_22, //  ! "
          0x27_27, //  '
          0x2c_2e, //  , - .
          0x3f_3f, //  ?
          0xb7_b7, //  ·
      },
      new int[]{
          0x2011_2011, //  ‑
      },
      6, 9);


  /**
   * <p>The decimal digit characters for the Chinese (Latin) language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the decimal digit characters from the {@code <exemplarCharacters type="numbers">}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the decimal digit characters
   *    are inherited from the superclass.</p>
   */
  static final Subset DECIMAL_DIGITS_SUBSET = Factory.rangedSubset(

      new char[]{
          0x30_39, //  0 1 2 3 4 5 6 7 8 9
      },
      1, 10);


}
