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
 * Provides Type Factory subsets for the Polish as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Polish language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class pl extends root {

  public pl() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected pl(
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
   * <p>The standard characters for the Polish language as defined by the
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
          0x52_55, //  R S T U
          0x57_57, //  W
          0x59_5a, //  Y Z
          0x61_70, //  a b c d e f g h i j k l m n o p
          0x72_75, //  r s t u
          0x77_77, //  w
          0x79_7a, //  y z
          0xd3_d3, //  Ó
          0xf3_f3, //  ó
      },
      new int[]{
          0x0104_0107, //  Ą ą Ć ć
          0x0118_0119, //  Ę ę
          0x0141_0144, //  Ł ł Ń ń
          0x015a_015b, //  Ś ś
          0x0179_017c, //  Ź ź Ż ż
      },
      15, 64);


  /**
   * <p>The auxiliary characters for the Polish language as defined by the
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
          0x53_53, //  S
          0x56_56, //  V
          0x58_58, //  X
          0x71_71, //  q
          0x76_76, //  v
          0x78_78, //  x
          0xc0_c0, //  À
          0xc2_c2, //  Â
          0xc4_cb, //  Ä Å Æ Ç È É Ê Ë
          0xce_cf, //  Î Ï
          0xd4_d4, //  Ô
          0xd6_d6, //  Ö
          0xd9_d9, //  Ù
          0xdb_dc, //  Û Ü
          0xdf_e0, //  ß à
          0xe2_e2, //  â
          0xe4_eb, //  ä å æ ç è é ê ë
          0xee_ef, //  î ï
          0xf4_f4, //  ô
          0xf6_f6, //  ö
          0xf9_f9, //  ù
          0xfb_fc, //  û ü
          0xff_ff, //  ÿ
      },
      new int[]{
          0x0152_0153, //  Œ œ
          0x0178_0178, //  Ÿ
      },
      26, 46);


  /**
   * <p>The punctuation characters for the Polish language as defined by the
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
          0x25_2a, //  % & ' ( ) *
          0x2c_2f, //  , - . /
          0x3a_3b, //  : ;
          0x3f_40, //  ? @
          0x5b_5b, //  [
          0x5d_5d, //  ]
          0x7b_7b, //  {
          0x7d_7e, //  } ~
          0xa7_a7, //  §
          0xab_ab, //  «
          0xb0_b0, //  °
          0xbb_bb, //  »
      },
      new int[]{
          0x2010_2011, //  ‐ ‑
          0x2013_2014, //  – —
          0x201d_201e, //  ” „
          0x2020_2021, //  † ‡
          0x2026_2026, //  …
          0x2032_2033, //  ′ ″
      },
      19, 37);


  /**
   * <p>The decimal digit characters for the Polish language as defined by the
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
