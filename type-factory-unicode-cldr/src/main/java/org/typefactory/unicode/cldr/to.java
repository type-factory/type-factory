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
 * Provides Type Factory subsets for the Tongan language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class to extends AbstractCldrResourceBundle {

  public to() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Tongan language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x41_41, //  A
          0x45_49, //  E F G H I
          0x4b_50, //  K L M N O P
          0x53_56, //  S T U V
          0x61_61, //  a
          0x65_69, //  e f g h i
          0x6b_70, //  k l m n o p
          0x73_76, //  s t u v
          0xc1_c1, //  Á
          0xc9_c9, //  É
          0xcd_cd, //  Í
          0xd3_d3, //  Ó
          0xda_da, //  Ú
          0xe1_e1, //  á
          0xe9_e9, //  é
          0xed_ed, //  í
          0xf3_f3, //  ó
          0xfa_fa, //  ú
      },
        new int[]{
          0x0100_0101, //  Ā ā
          0x0112_0113, //  Ē ē
          0x012a_012b, //  Ī ī
          0x014c_014d, //  Ō ō
          0x016a_016b, //  Ū ū
          0x02bb_02bb, //  ʻ
      },
      24, 53);


  /**
   * <p>The auxiliary characters for the Tongan language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x42_44, //  B C D
          0x47_47, //  G
          0x4a_4a, //  J
          0x51_52, //  Q R
          0x57_5a, //  W X Y Z
          0x62_64, //  b c d
          0x67_67, //  g
          0x6a_6a, //  j
          0x71_72, //  q r
          0x77_7a, //  w x y z
          0xc0_c0, //  À
          0xc2_c2, //  Â
          0xc4_c8, //  Ä Å Æ Ç È
          0xca_cc, //  Ê Ë Ì
          0xce_cf, //  Î Ï
          0xd1_d2, //  Ñ Ò
          0xd4_d4, //  Ô
          0xd6_d6, //  Ö
          0xd8_d9, //  Ø Ù
          0xdb_dc, //  Û Ü
          0xe0_e0, //  à
          0xe2_e2, //  â
          0xe4_e8, //  ä å æ ç è
          0xea_ec, //  ê ë ì
          0xee_ef, //  î ï
          0xf1_f2, //  ñ ò
          0xf4_f4, //  ô
          0xf6_f6, //  ö
          0xf8_f9, //  ø ù
          0xfb_fc, //  û ü
          0xff_ff, //  ÿ
      },
        new int[]{
          0x0102_0103, //  Ă ă
          0x0114_0115, //  Ĕ ĕ
          0x012c_012d, //  Ĭ ĭ
          0x014e_014f, //  Ŏ ŏ
          0x0152_0153, //  Œ œ
          0x016c_016d, //  Ŭ ŭ
          0x0178_0178, //  Ÿ
      },
      38, 76);


  /**
   * <p>The punctuation characters for the Tongan language as defined by the
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
