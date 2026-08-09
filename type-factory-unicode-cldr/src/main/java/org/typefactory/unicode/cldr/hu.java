/*
   Copyright 2021-2026 Evan Toliopoulos (typefactory.org)

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
 * Provides Type Factory subsets for the Hungarian as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Hungarian language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class hu extends root {

  public hu() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected hu(
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
   * <p>The standard characters for the Hungarian language as defined by the
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
          0x41_50, //  A B C D E F G H I J K L M N O P
          0x52_56, //  R S T U V
          0x59_5a, //  Y Z
          0x61_70, //  a b c d e f g h i j k l m n o p
          0x72_76, //  r s t u v
          0x79_7a, //  y z
          0xc1_c1, //  Á
          0xc9_c9, //  É
          0xcd_cd, //  Í
          0xd3_d3, //  Ó
          0xd6_d6, //  Ö
          0xda_da, //  Ú
          0xdc_dc, //  Ü
          0xe1_e1, //  á
          0xe9_e9, //  é
          0xed_ed, //  í
          0xf3_f3, //  ó
          0xf6_f6, //  ö
          0xfa_fa, //  ú
          0xfc_fc, //  ü
      },
      new int[]{
          0x0150_0151, //  Ő ő
          0x0170_0171, //  Ű ű
      },
      22, 64);


  /**
   * <p>The auxiliary characters for the Hungarian language as defined by the
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
          0x51_51, //  Q
          0x57_59, //  W X Y
          0x71_71, //  q
          0x77_79, //  w x y
          0xc0_c0, //  À
          0xc2_c8, //  Â Ã Ä Å Æ Ç È
          0xca_cc, //  Ê Ë Ì
          0xce_cf, //  Î Ï
          0xd1_d2, //  Ñ Ò
          0xd4_d4, //  Ô
          0xd8_d9, //  Ø Ù
          0xdb_db, //  Û
          0xe0_e0, //  à
          0xe2_e8, //  â ã ä å æ ç è
          0xea_ec, //  ê ë ì
          0xee_ef, //  î ï
          0xf1_f2, //  ñ ò
          0xf4_f4, //  ô
          0xf8_f9, //  ø ù
          0xfb_fb, //  û
          0xff_ff, //  ÿ
      },
      new int[]{
          0x0100_0103, //  Ā ā Ă ă
          0x0112_0115, //  Ē ē Ĕ ĕ
          0x012a_012d, //  Ī ī Ĭ ĭ
          0x014c_014f, //  Ō ō Ŏ ŏ
          0x0152_0153, //  Œ œ
          0x016a_016d, //  Ū ū Ŭ ŭ
          0x0178_0178, //  Ÿ
      },
      28, 70);


  /**
   * <p>The punctuation characters for the Hungarian language as defined by the
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
          0x7b_7b, //  {
          0x7d_7e, //  } ~
          0xa7_a7, //  §
          0xab_ab, //  «
          0xbb_bb, //  »
      },
      new int[]{
          0x2011_2011, //  ‑
          0x2013_2013, //  –
          0x2019_2019, //  ’
          0x201d_201e, //  ” „
          0x2026_2026, //  …
          0x2052_2052, //  ⁒
          0x27e8_27e9, //  ⟨ ⟩
      },
      19, 33);


  /**
   * <p>The decimal digit characters for the Hungarian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the decimal digit characters from the {@code <exemplarCharacters type="numbers">}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the decimal digit characters
   *    are inherited from the superclass.</p>
   */
  static final Subset DECIMAL_DIGITS_SUBSET = Factory.rangedSubset(

      new char[]{
          0x30_39, //  0 1 2 3 4 5 6 7 8 9
      },
      1, 10);


}
