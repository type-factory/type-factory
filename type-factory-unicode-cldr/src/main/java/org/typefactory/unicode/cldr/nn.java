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
 * Provides Type Factory subsets for the Norwegian Nynorsk language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class nn extends CldrResourceBundle {

  public nn() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Norwegian Nynorsk language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x41_5a, //  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
          0x61_7a, //  a b c d e f g h i j k l m n o p q r s t u v w x y z
          0xc0_c0, //  À
          0xc5_c6, //  Å Æ
          0xc9_c9, //  É
          0xd2_d4, //  Ò Ó Ô
          0xd8_d8, //  Ø
          0xe0_e0, //  à
          0xe5_e6, //  å æ
          0xe9_e9, //  é
          0xf2_f4, //  ò ó ô
          0xf8_f8, //  ø
      },
      12, 68);


  /**
   * <p>The auxiliary characters for the Norwegian Nynorsk language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0xc1_c1, //  Á
          0xc3_c4, //  Ã Ä
          0xc7_c8, //  Ç È
          0xca_ca, //  Ê
          0xcd_cd, //  Í
          0xd1_d1, //  Ñ
          0xd6_d6, //  Ö
          0xda_da, //  Ú
          0xdc_dc, //  Ü
          0xe1_e1, //  á
          0xe3_e4, //  ã ä
          0xe7_e8, //  ç è
          0xea_ea, //  ê
          0xed_ed, //  í
          0xf1_f1, //  ñ
          0xf6_f6, //  ö
          0xfa_fa, //  ú
          0xfc_fc, //  ü
      },
        new int[]{
          0x010c_010d, //  Č č
          0x0110_0111, //  Đ đ
          0x0143_0144, //  Ń ń
          0x014a_014b, //  Ŋ ŋ
          0x0160_0161, //  Š š
          0x0166_0167, //  Ŧ ŧ
          0x017d_017e, //  Ž ž
          0x01cd_01ce, //  Ǎ ǎ
      },
      26, 38);


  /**
   * <p>The punctuation characters for the Norwegian Nynorsk language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x21_21, //  !
          0x23_23, //  #
          0x28_2a, //  ( ) *
          0x2c_2f, //  , - . /
          0x3a_3b, //  : ;
          0x3f_40, //  ? @
          0x5b_5d, //  [ \ ]
          0x7b_7b, //  {
          0x7d_7d, //  }
          0xa7_a7, //  §
          0xab_ab, //  «
          0xbb_bb, //  »
          0xbf_bf, //  ¿
      },
        new int[]{
          0x2011_2011, //  ‑
          0x2013_2013, //  –
          0x2018_2019, //  ‘ ’
          0x201c_201d, //  “ ”
          0x2020_2021, //  † ‡
          0x2026_2026, //  …
          0x2032_2033, //  ′ ″
      },
      20, 33);


}
