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

import javax.annotation.processing.Generated;
import org.typefactory.Subset;
import org.typefactory.impl.Factory;

/**
 * Provides Type Factory subsets for the Ewondo language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class ewo extends AbstractCldrResourceBundle {

  public ewo() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Ewondo language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x41_42, //  A B
          0x44_49, //  D E F G H I
          0x4b_50, //  K L M N O P
          0x52_57, //  R S T U V W
          0x59_5a, //  Y Z
          0x61_62, //  a b
          0x64_69, //  d e f g h i
          0x6b_70, //  k l m n o p
          0x72_77, //  r s t u v w
          0x79_7a, //  y z
          0xc0_c2, //  À Á Â
          0xc8_ca, //  È É Ê
          0xcc_ce, //  Ì Í Î
          0xd2_d4, //  Ò Ó Ô
          0xd9_db, //  Ù Ú Û
          0xe0_e2, //  à á â
          0xe8_ea, //  è é ê
          0xec_ee, //  ì í î
          0xf2_f4, //  ò ó ô
          0xf9_fb, //  ù ú û
      },
        new int[]{
          0x011a_011b, //  Ě ě
          0x0143_0144, //  Ń ń
          0x014a_014b, //  Ŋ ŋ
          0x0186_0186, //  Ɔ
          0x018f_0190, //  Ə Ɛ
          0x01cd_01d4, //  Ǎ ǎ Ǐ ǐ Ǒ ǒ Ǔ ǔ
          0x01f8_01f9, //  Ǹ ǹ
          0x0254_0254, //  ɔ
          0x0259_0259, //  ə
          0x025b_025b, //  ɛ
          0x0300_0302, //  ̀ ́ ̂
          0x030c_030c, //  ̌
      },
      32, 100);


  /**
   * <p>The auxiliary characters for the Ewondo language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x43_43, //  C
          0x4a_4a, //  J
          0x51_51, //  Q
          0x58_58, //  X
          0x63_63, //  c
          0x6a_6a, //  j
          0x71_71, //  q
          0x78_78, //  x
      },
      8, 8);


  /**
   * <p>The punctuation characters for the Ewondo language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x21_21, //  !
          0x28_29, //  ( )
          0x2c_2e, //  , - .
          0x3a_3b, //  : ;
          0x3f_3f, //  ?
          0x5b_5b, //  [
          0x5d_5d, //  ]
          0x7b_7b, //  {
          0x7d_7d, //  }
      },
        new int[]{
          0x2010_2011, //  ‐ ‑
      },
      10, 15);


}
