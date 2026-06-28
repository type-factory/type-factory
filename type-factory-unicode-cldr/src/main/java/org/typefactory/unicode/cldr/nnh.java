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
 * Provides Type Factory subsets for the Ngiemboon language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class nnh extends AbstractCldrResourceBundle {

  public nnh() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Ngiemboon language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x41_50, //  A B C D E F G H I J K L M N O P
          0x53_57, //  S T U V W
          0x59_5a, //  Y Z
          0x61_70, //  a b c d e f g h i j k l m n o p
          0x73_77, //  s t u v w
          0x79_7a, //  y z
          0xc0_c2, //  À Á Â
          0xc8_ca, //  È É Ê
          0xcc_cd, //  Ì Í
          0xd2_d4, //  Ò Ó Ô
          0xd9_db, //  Ù Ú Û
          0xe0_e2, //  à á â
          0xe8_ea, //  è é ê
          0xec_ed, //  ì í
          0xf2_f4, //  ò ó ô
          0xf9_fb, //  ù ú û
          0xff_ff, //  ÿ
      },
        new int[]{
          0x011a_011b, //  Ě ě
          0x0143_0144, //  Ń ń
          0x014a_014b, //  Ŋ ŋ
          0x0178_0178, //  Ÿ
          0x0186_0186, //  Ɔ
          0x0190_0190, //  Ɛ
          0x01cd_01ce, //  Ǎ ǎ
          0x01d1_01d4, //  Ǒ ǒ Ǔ ǔ
          0x0244_0244, //  Ʉ
          0x0254_0254, //  ɔ
          0x025b_025b, //  ɛ
          0x0289_0289, //  ʉ
          0x02bc_02bc, //  ʼ
          0x1e3e_1e3f, //  Ḿ ḿ
          0x1e84_1e85, //  Ẅ ẅ
      },
      32, 99);


  /**
   * <p>The auxiliary characters for the Ngiemboon language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x51_52, //  Q R
          0x58_58, //  X
          0x71_72, //  q r
          0x78_78, //  x
      },
      4, 6);


  /**
   * <p>The punctuation characters for the Ngiemboon language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x21_21, //  !
          0x27_27, //  '
          0x2c_2c, //  ,
          0x2e_2e, //  .
          0x3a_3b, //  : ;
          0x3f_3f, //  ?
          0xab_ab, //  «
          0xbb_bb, //  »
      },
        new int[]{
          0x2018_2019, //  ‘ ’
      },
      9, 11);


}
