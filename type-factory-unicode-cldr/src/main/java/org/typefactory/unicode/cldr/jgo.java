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
 * Provides Type Factory subsets for the Ngomba language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class jgo extends CldrResourceBundle {

  public jgo() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Ngomba language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x41_44, //  A B C D
          0x46_4e, //  F G H I J K L M N
          0x50_50, //  P
          0x53_57, //  S T U V W
          0x59_5a, //  Y Z
          0x61_64, //  a b c d
          0x66_6e, //  f g h i j k l m n
          0x70_70, //  p
          0x73_77, //  s t u v w
          0x79_7a, //  y z
          0xc1_c2, //  Á Â
          0xcd_ce, //  Í Î
          0xda_db, //  Ú Û
          0xe1_e2, //  á â
          0xed_ee, //  í î
          0xfa_fb, //  ú û
      },
        new int[]{
          0x0143_0144, //  Ń ń
          0x014a_014b, //  Ŋ ŋ
          0x0186_0186, //  Ɔ
          0x0190_0190, //  Ɛ
          0x01cd_01d0, //  Ǎ ǎ Ǐ ǐ
          0x01d3_01d4, //  Ǔ ǔ
          0x01f8_01f9, //  Ǹ ǹ
          0x0244_0244, //  Ʉ
          0x0254_0254, //  ɔ
          0x025b_025b, //  ɛ
          0x0289_0289, //  ʉ
          0x1e3e_1e3f, //  Ḿ ḿ
          0x1e84_1e85, //  Ẅ ẅ
          0xa78b_a78c, //  Ꞌ ꞌ
      },
      30, 78);


  /**
   * <p>The auxiliary characters for the Ngomba language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x45_45, //  E
          0x4f_4f, //  O
          0x51_52, //  Q R
          0x58_58, //  X
          0x65_65, //  e
          0x6f_6f, //  o
          0x71_72, //  q r
          0x78_78, //  x
      },
      8, 10);


  /**
   * <p>The punctuation characters for the Ngomba language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x21_21, //  !
          0x2c_2e, //  , - .
          0x3a_3b, //  : ;
          0x3f_3f, //  ?
          0xab_ab, //  «
          0xbb_bb, //  »
      },
        new int[]{
          0x2011_2011, //  ‑
          0x2039_203a, //  ‹ ›
      },
      8, 12);


}
