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
 * Provides Type Factory subsets for the Sicilian language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class scn extends AbstractCldrResourceBundle {

  public scn() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Sicilian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x41_4a, //  A B C D E F G H I J
          0x4c_56, //  L M N O P Q R S T U V
          0x5a_5a, //  Z
          0x61_6a, //  a b c d e f g h i j
          0x6c_76, //  l m n o p q r s t u v
          0x7a_7a, //  z
          0xc0_c0, //  À
          0xc2_c2, //  Â
          0xc8_c8, //  È
          0xca_ca, //  Ê
          0xcc_cc, //  Ì
          0xce_ce, //  Î
          0xd2_d2, //  Ò
          0xd4_d4, //  Ô
          0xd9_d9, //  Ù
          0xdb_db, //  Û
          0xe0_e0, //  à
          0xe2_e2, //  â
          0xe8_e8, //  è
          0xea_ea, //  ê
          0xec_ec, //  ì
          0xee_ee, //  î
          0xf2_f2, //  ò
          0xf4_f4, //  ô
          0xf9_f9, //  ù
          0xfb_fb, //  û
      },
        new int[]{
          0x1e0c_1e0d, //  Ḍ ḍ
      },
      27, 66);


  /**
   * <p>The auxiliary characters for the Sicilian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x4b_4b, //  K
          0x57_59, //  W X Y
          0x6b_6b, //  k
          0x77_79, //  w x y
          0xc7_c7, //  Ç
          0xc9_c9, //  É
          0xcb_cb, //  Ë
          0xe7_e7, //  ç
          0xe9_e9, //  é
          0xeb_eb, //  ë
      },
        new int[]{
          0x0110_0111, //  Đ đ
          0x0160_0161, //  Š š
          0x018f_018f, //  Ə
          0x0259_0259, //  ə
          0x1e24_1e25, //  Ḥ ḥ
      },
      15, 22);


  /**
   * <p>The punctuation characters for the Sicilian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x21_23, //  ! " #
          0x26_2a, //  & ' ( ) *
          0x2c_2f, //  , - . /
          0x3a_3b, //  : ;
          0x3f_40, //  ? @
          0x5b_5b, //  [
          0x5d_5d, //  ]
          0xa7_a7, //  §
      },
        new int[]{
          0x2010_2011, //  ‐ ‑
          0x2013_2014, //  – —
          0x2018_2019, //  ‘ ’
          0x201c_201d, //  “ ”
          0x2020_2021, //  † ‡
          0x2026_2026, //  …
          0x2032_2033, //  ′ ″
      },
      15, 32);


}
