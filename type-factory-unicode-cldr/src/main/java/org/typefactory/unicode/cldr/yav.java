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
 * Provides Type Factory subsets for the Yangben language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class yav extends CldrResourceBundle {

  public yav() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Yangben language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x41_46, //  A B C D E F
          0x48_49, //  H I
          0x4b_50, //  K L M N O P
          0x53_57, //  S T U V W
          0x59_59, //  Y
          0x61_66, //  a b c d e f
          0x68_69, //  h i
          0x6b_70, //  k l m n o p
          0x73_77, //  s t u v w
          0x79_79, //  y
          0xc0_c2, //  À Á Â
          0xc8_c9, //  È É
          0xcc_ce, //  Ì Í Î
          0xd2_d4, //  Ò Ó Ô
          0xd9_db, //  Ù Ú Û
          0xe0_e2, //  à á â
          0xe8_e9, //  è é
          0xec_ee, //  ì í î
          0xf2_f4, //  ò ó ô
          0xf9_fb, //  ù ú û
      },
        new int[]{
          0x0100_0101, //  Ā ā
          0x012a_012b, //  Ī ī
          0x014a_014d, //  Ŋ ŋ Ō ō
          0x016a_016b, //  Ū ū
          0x0186_0186, //  Ɔ
          0x0190_0190, //  Ɛ
          0x01cd_01ce, //  Ǎ ǎ
          0x01d1_01d4, //  Ǒ ǒ Ǔ ǔ
          0x0254_0254, //  ɔ
          0x025b_025b, //  ɛ
      },
      30, 88);


  /**
   * <p>The auxiliary characters for the Yangben language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x47_47, //  G
          0x4a_4a, //  J
          0x51_52, //  Q R
          0x58_58, //  X
          0x5a_5a, //  Z
          0x67_67, //  g
          0x6a_6a, //  j
          0x71_72, //  q r
          0x78_78, //  x
          0x7a_7a, //  z
      },
      10, 12);


  /**
   * <p>The punctuation characters for the Yangben language as defined by the
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
