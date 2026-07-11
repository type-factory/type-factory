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
 * Provides Type Factory subsets for the Corsican as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Corsican language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class co extends root {

  public co() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected co(
          final Subset standardSubset,
          final Subset auxiliarySubset,
          final Subset punctuationSubset,
          final Subset decimalDigitsSubset) {
    super(
        standardSubset == null ? STANDARD_CHARACTERS_SUBSET : standardSubset,
        auxiliarySubset == null ? AUXILIARY_CHARACTERS_SUBSET : auxiliarySubset,
        punctuationSubset == null ? PUNCTUATION_CHARACTERS_SUBSET : punctuationSubset,
        decimalDigitsSubset == null ? DECIMAL_DIGITS_SUBSET : decimalDigitsSubset);
  }

  /**
   * <p>The Locale represented by this resource bundle for the Corsican language.</p>
   *
   * <p>Language tag: {@code "co"}</p>
   */
  static final Locale LOCALE = new Locale.Builder()
          .setLanguage("co")
          .setScript("")
          .setRegion("")
          .setVariant("")
          .build();

  /**
   * <p>The standard characters for the Corsican language as defined by the
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
          0x41_4a, //  A B C D E F G H I J
          0x4c_56, //  L M N O P Q R S T U V
          0x5a_5a, //  Z
          0x61_6a, //  a b c d e f g h i j
          0x6c_76, //  l m n o p q r s t u v
          0x7a_7a, //  z
          0xc0_c0, //  À
          0xc8_c8, //  È
          0xcc_cc, //  Ì
          0xcf_cf, //  Ï
          0xd2_d2, //  Ò
          0xd9_d9, //  Ù
          0xdc_dc, //  Ü
          0xe0_e0, //  à
          0xe8_e8, //  è
          0xec_ec, //  ì
          0xef_ef, //  ï
          0xf2_f2, //  ò
          0xf9_f9, //  ù
          0xfc_fc, //  ü
      },
      20, 58);


  /**
   * <p>The auxiliary characters for the Corsican language as defined by the
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
          0x4b_4b, //  K
          0x57_59, //  W X Y
          0x6b_6b, //  k
          0x77_79, //  w x y
          0xc2_c2, //  Â
          0xc6_c7, //  Æ Ç
          0xc9_cb, //  É Ê Ë
          0xce_ce, //  Î
          0xd1_d1, //  Ñ
          0xd4_d4, //  Ô
          0xda_db, //  Ú Û
          0xe2_e2, //  â
          0xe6_e7, //  æ ç
          0xe9_eb, //  é ê ë
          0xee_ee, //  î
          0xf1_f1, //  ñ
          0xf4_f4, //  ô
          0xfa_fb, //  ú û
          0xff_ff, //  ÿ
      },
      new int[]{
          0x0152_0153, //  Œ œ
          0x0178_0178, //  Ÿ
      },
      21, 34);


  /**
   * <p>The punctuation characters for the Corsican language as defined by the
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
          0x5b_5b, //  [
          0x5d_5d, //  ]
          0xa7_a7, //  §
          0xab_ab, //  «
          0xbb_bb, //  »
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
      17, 34);


  /**
   * <p>The decimal digit characters for the Corsican language as defined by the
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
