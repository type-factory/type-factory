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
 * Provides Type Factory subsets for the Slovak language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class sk extends root {

  public sk() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  protected sk(
          final Subset standardSubset,
          final Subset auxiliarySubset,
          final Subset punctuationSubset) {
    super(
        standardSubset == null ? STANDARD_CHARACTERS_SUBSET : standardSubset,
        auxiliarySubset == null ? AUXILIARY_CHARACTERS_SUBSET : auxiliarySubset,
        punctuationSubset == null ? PUNCTUATION_CHARACTERS_SUBSET : punctuationSubset);
  }

  /**
   * <p>The standard characters for the Slovak language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x41_5a, //  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
          0x61_7a, //  a b c d e f g h i j k l m n o p q r s t u v w x y z
          0xc1_c1, //  Á
          0xc4_c4, //  Ä
          0xc9_c9, //  É
          0xcd_cd, //  Í
          0xd3_d4, //  Ó Ô
          0xda_da, //  Ú
          0xdd_dd, //  Ý
          0xe1_e1, //  á
          0xe4_e4, //  ä
          0xe9_e9, //  é
          0xed_ed, //  í
          0xf3_f4, //  ó ô
          0xfa_fa, //  ú
          0xfd_fd, //  ý
      },
        new int[]{
          0x010c_010f, //  Č č Ď ď
          0x0139_013a, //  Ĺ ĺ
          0x013d_013e, //  Ľ ľ
          0x0147_0148, //  Ň ň
          0x0154_0155, //  Ŕ ŕ
          0x0160_0161, //  Š š
          0x0164_0165, //  Ť ť
          0x017d_017e, //  Ž ž
      },
      24, 86);


  /**
   * <p>The auxiliary characters for the Slovak language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0xc0_c0, //  À
          0xc2_c2, //  Â
          0xc5_c8, //  Å Æ Ç È
          0xca_cc, //  Ê Ë Ì
          0xce_cf, //  Î Ï
          0xd1_d2, //  Ñ Ò
          0xd6_d6, //  Ö
          0xd8_d9, //  Ø Ù
          0xdb_dc, //  Û Ü
          0xe0_e0, //  à
          0xe2_e2, //  â
          0xe5_e8, //  å æ ç è
          0xea_ec, //  ê ë ì
          0xee_ef, //  î ï
          0xf1_f2, //  ñ ò
          0xf6_f6, //  ö
          0xf8_f9, //  ø ù
          0xfb_fc, //  û ü
          0xff_ff, //  ÿ
      },
        new int[]{
          0x0100_0103, //  Ā ā Ă ă
          0x0112_0115, //  Ē ē Ĕ ĕ
          0x012a_012d, //  Ī ī Ĭ ĭ
          0x014c_0153, //  Ō ō Ŏ ŏ Ő ő Œ œ
          0x0158_0159, //  Ř ř
          0x016a_016d, //  Ū ū Ŭ ŭ
          0x0170_0171, //  Ű ű
          0x0178_0178, //  Ÿ
      },
      27, 66);


  /**
   * <p>The punctuation characters for the Slovak language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x21_21, //  !
          0x26_26, //  &
          0x28_2a, //  ( ) *
          0x2c_2f, //  , - . /
          0x3a_3b, //  : ;
          0x3f_40, //  ? @
          0x5b_5b, //  [
          0x5d_5d, //  ]
          0xa7_a7, //  §
      },
        new int[]{
          0x2010_2011, //  ‐ ‑
          0x2013_2013, //  –
          0x2018_2018, //  ‘
          0x201a_201a, //  ‚
          0x201c_201c, //  “
          0x201e_201e, //  „
          0x2026_2026, //  …
      },
      16, 24);


}
