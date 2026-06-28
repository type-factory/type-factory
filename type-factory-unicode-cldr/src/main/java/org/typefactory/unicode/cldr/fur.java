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
 * Provides Type Factory subsets for the Friulian language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class fur extends AbstractCldrResourceBundle {

  public fur() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Friulian language as defined by the
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
          0xc2_c2, //  Â
          0xc7_c8, //  Ç È
          0xca_ca, //  Ê
          0xcc_cc, //  Ì
          0xce_ce, //  Î
          0xd2_d2, //  Ò
          0xd4_d4, //  Ô
          0xd9_d9, //  Ù
          0xdb_db, //  Û
          0xe0_e0, //  à
          0xe2_e2, //  â
          0xe7_e8, //  ç è
          0xea_ea, //  ê
          0xec_ec, //  ì
          0xee_ee, //  î
          0xf2_f2, //  ò
          0xf4_f4, //  ô
          0xf9_f9, //  ù
          0xfb_fb, //  û
      },
      22, 74);


  /**
   * <p>The auxiliary characters for the Friulian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0xc5_c5, //  Å
          0xc9_c9, //  É
          0xcb_cb, //  Ë
          0xcf_cf, //  Ï
          0xd1_d1, //  Ñ
          0xd3_d3, //  Ó
          0xdc_dc, //  Ü
          0xe5_e5, //  å
          0xe9_e9, //  é
          0xeb_eb, //  ë
          0xef_ef, //  ï
          0xf1_f1, //  ñ
          0xf3_f3, //  ó
          0xfc_fc, //  ü
      },
        new int[]{
          0x010c_010d, //  Č č
          0x011e_011f, //  Ğ ğ
          0x0160_0161, //  Š š
      },
      17, 20);


  /**
   * <p>The punctuation characters for the Friulian language as defined by the
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
