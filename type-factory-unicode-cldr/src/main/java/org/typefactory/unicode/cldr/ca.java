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

import java.util.Locale;
import javax.annotation.processing.Generated;
import org.typefactory.Subset;
import org.typefactory.impl.Factory;

/**
 * Provides Type Factory subsets for the Catalan as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Catalan language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class ca extends root {

  public ca() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected ca(
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
   * <p>The Locale represented by this resource bundle for the Catalan language.</p>
   *
   * <p>Language tag: {@code "ca"}</p>
   */
  static final Locale LOCALE = new Locale.Builder()
          .setLanguage("ca")
          .setScript("")
          .setRegion("")
          .setVariant("")
          .build();

  /**
   * <p>The standard characters for the Catalan language as defined by the
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
          0x41_5a, //  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
          0x61_7a, //  a b c d e f g h i j k l m n o p q r s t u v w x y z
          0xb7_b7, //  ·
          0xc0_c0, //  À
          0xc7_c9, //  Ç È É
          0xcd_cd, //  Í
          0xcf_cf, //  Ï
          0xd2_d3, //  Ò Ó
          0xda_da, //  Ú
          0xdc_dc, //  Ü
          0xe0_e0, //  à
          0xe7_e9, //  ç è é
          0xed_ed, //  í
          0xef_ef, //  ï
          0xf2_f3, //  ò ó
          0xfa_fa, //  ú
          0xfc_fc, //  ü
      },
      17, 73);


  /**
   * <p>The auxiliary characters for the Catalan language as defined by the
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
          0xba_ba, //  º
          0xc1_c6, //  Á Â Ã Ä Å Æ
          0xca_cc, //  Ê Ë Ì
          0xce_ce, //  Î
          0xd1_d1, //  Ñ
          0xd4_d4, //  Ô
          0xd6_d6, //  Ö
          0xd8_d9, //  Ø Ù
          0xdb_db, //  Û
          0xe1_e6, //  á â ã ä å æ
          0xea_ec, //  ê ë ì
          0xee_ee, //  î
          0xf1_f1, //  ñ
          0xf4_f4, //  ô
          0xf6_f6, //  ö
          0xf8_f9, //  ø ù
          0xfb_fb, //  û
          0xff_ff, //  ÿ
      },
      new int[]{
          0x0100_0103, //  Ā ā Ă ă
          0x0112_0115, //  Ē ē Ĕ ĕ
          0x012a_012d, //  Ī ī Ĭ ĭ
          0x013f_0140, //  Ŀ ŀ
          0x014c_014f, //  Ō ō Ŏ ŏ
          0x0152_0153, //  Œ œ
          0x016a_016d, //  Ū ū Ŭ ŭ
          0x0178_0178, //  Ÿ
      },
      26, 59);


  /**
   * <p>The punctuation characters for the Catalan language as defined by the
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
          0x21_23, //  ! " #
          0x26_2a, //  & ' ( ) *
          0x2c_2f, //  , - . /
          0x3a_3b, //  : ;
          0x3f_40, //  ? @
          0x5b_5d, //  [ \ ]
          0xa1_a1, //  ¡
          0xa7_a7, //  §
          0xab_ab, //  «
          0xbb_bb, //  »
          0xbf_bf, //  ¿
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
      18, 37);


  /**
   * <p>The decimal digit characters for the Catalan language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the decimal digit characters from the {@code <exemplarCharacters type="numbers">}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the decimal digit characters
   *    are inherited from the superclass.</p>
   */
  static final Subset DECIMAL_DIGITS_SUBSET = null;


}
