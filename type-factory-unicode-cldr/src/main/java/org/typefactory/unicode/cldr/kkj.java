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
 * Provides Type Factory subsets for the Kako language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class kkj extends AbstractCldrResourceBundle {

  public kkj() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Kako language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x41_50, //  A B C D E F G H I J K L M N O P
          0x52_57, //  R S T U V W
          0x59_59, //  Y
          0x61_70, //  a b c d e f g h i j k l m n o p
          0x72_77, //  r s t u v w
          0x79_79, //  y
          0xc0_c2, //  À Á Â
          0xc8_ca, //  È É Ê
          0xcc_ce, //  Ì Í Î
          0xd2_d4, //  Ò Ó Ô
          0xd9_db, //  Ù Ú Û
          0xe0_e2, //  à á â
          0xe8_ea, //  è é ê
          0xec_ee, //  ì í î
          0xf2_f4, //  ò ó ô
          0xf9_fb, //  ù ú û
      },
        new int[]{
          0x014a_014b, //  Ŋ ŋ
          0x0181_0181, //  Ɓ
          0x0186_0186, //  Ɔ
          0x018a_018a, //  Ɗ
          0x0190_0190, //  Ɛ
          0x0253_0254, //  ɓ ɔ
          0x0257_0257, //  ɗ
          0x025b_025b, //  ɛ
          0x0300_0302, //  ̀ ́ ̂
          0x0327_0327, //  ̧
      },
      26, 90);


  /**
   * <p>The auxiliary characters for the Kako language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x51_51, //  Q
          0x58_58, //  X
          0x5a_5a, //  Z
          0x71_71, //  q
          0x78_78, //  x
          0x7a_7a, //  z
      },
      6, 6);


  /**
   * <p>The punctuation characters for the Kako language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x21_21, //  !
          0x28_2a, //  ( ) *
          0x2c_2c, //  ,
          0x2e_2e, //  .
          0x3a_3a, //  :
          0x3f_3f, //  ?
          0xab_ab, //  «
          0xbb_bb, //  »
      },
        new int[]{
          0x2018_2018, //  ‘
          0x201c_201d, //  “ ”
          0x2026_2026, //  …
          0x2039_203a, //  ‹ ›
      },
      12, 16);


}
