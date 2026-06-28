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
 * Provides Type Factory subsets for the Italian language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class it extends CldrResourceBundle {

  public it() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Italian language as defined by the
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
          0xc8_c9, //  È É
          0xcc_cc, //  Ì
          0xd2_d2, //  Ò
          0xd9_d9, //  Ù
          0xe0_e0, //  à
          0xe8_e9, //  è é
          0xec_ec, //  ì
          0xf2_f2, //  ò
          0xf9_f9, //  ù
      },
      12, 64);


  /**
   * <p>The auxiliary characters for the Italian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0xaa_aa, //  ª
          0xba_ba, //  º
          0xc1_c7, //  Á Â Ã Ä Å Æ Ç
          0xca_cb, //  Ê Ë
          0xcd_cf, //  Í Î Ï
          0xd1_d1, //  Ñ
          0xd3_d6, //  Ó Ô Õ Ö
          0xd8_d8, //  Ø
          0xda_dc, //  Ú Û Ü
          0xdf_df, //  ß
          0xe1_e7, //  á â ã ä å æ ç
          0xea_eb, //  ê ë
          0xed_ef, //  í î ï
          0xf1_f1, //  ñ
          0xf3_f6, //  ó ô õ ö
          0xf8_f8, //  ø
          0xfa_fc, //  ú û ü
          0xff_ff, //  ÿ
      },
        new int[]{
          0x0152_0153, //  Œ œ
          0x0178_0178, //  Ÿ
      },
      20, 49);


  /**
   * <p>The punctuation characters for the Italian language as defined by the
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
          0x7b_7b, //  {
          0x7d_7d, //  }
          0xab_ab, //  «
          0xbb_bb, //  »
      },
        new int[]{
          0x2011_2011, //  ‑
          0x2014_2014, //  —
          0x2019_2019, //  ’
          0x201c_201d, //  “ ”
          0x2026_2026, //  …
      },
      16, 28);


}
