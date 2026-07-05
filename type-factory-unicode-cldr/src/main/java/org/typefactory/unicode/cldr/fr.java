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
 * Provides Type Factory subsets for the French language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class fr extends root {

  public fr() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  protected fr(
          final Subset standardSubset,
          final Subset auxiliarySubset,
          final Subset punctuationSubset) {
    super(
        standardSubset == null ? STANDARD_CHARACTERS_SUBSET : standardSubset,
        auxiliarySubset == null ? AUXILIARY_CHARACTERS_SUBSET : auxiliarySubset,
        punctuationSubset == null ? PUNCTUATION_CHARACTERS_SUBSET : punctuationSubset);
  }

  /**
   * <p>The standard characters for the French language as defined by the
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
          0xc6_cb, //  Æ Ç È É Ê Ë
          0xce_cf, //  Î Ï
          0xd4_d4, //  Ô
          0xd9_d9, //  Ù
          0xdb_dc, //  Û Ü
          0xe0_e0, //  à
          0xe2_e2, //  â
          0xe6_eb, //  æ ç è é ê ë
          0xee_ef, //  î ï
          0xf4_f4, //  ô
          0xf9_f9, //  ù
          0xfb_fc, //  û ü
          0xff_ff, //  ÿ
      },
        new int[]{
          0x0152_0153, //  Œ œ
          0x0178_0178, //  Ÿ
      },
      19, 84);


  /**
   * <p>The auxiliary characters for the French language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x53_53, //  S
          0xc1_c1, //  Á
          0xc3_c5, //  Ã Ä Å
          0xcc_cd, //  Ì Í
          0xd1_d3, //  Ñ Ò Ó
          0xd5_d6, //  Õ Ö
          0xd8_d8, //  Ø
          0xda_da, //  Ú
          0xdf_df, //  ß
          0xe1_e1, //  á
          0xe3_e5, //  ã ä å
          0xec_ed, //  ì í
          0xf1_f3, //  ñ ò ó
          0xf5_f6, //  õ ö
          0xf8_f8, //  ø
          0xfa_fa, //  ú
      },
        new int[]{
          0x0100_0101, //  Ā ā
          0x0106_0107, //  Ć ć
          0x0112_0113, //  Ē ē
          0x012a_012b, //  Ī ī
          0x0132_0133, //  Ĳ ĳ
          0x0158_0159, //  Ř ř
          0x0160_0161, //  Š š
          0x017f_017f, //  ſ
          0x01d3_01d4, //  Ǔ ǔ
      },
      25, 45);


  /**
   * <p>The punctuation characters for the French language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x21_23, //  ! " #
          0x26_26, //  &
          0x28_2a, //  ( ) *
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
          0x2019_2019, //  ’
          0x201c_201d, //  “ ”
          0x2020_2021, //  † ‡
          0x2026_2026, //  …
      },
      17, 30);


}
