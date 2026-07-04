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
 * Provides Type Factory subsets for the Breton language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class br extends AbstractCldrResourceBundle {

  public br() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Breton language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x41_50, //  A B C D E F G H I J K L M N O P
          0x52_5a, //  R S T U V W X Y Z
          0x61_70, //  a b c d e f g h i j k l m n o p
          0x72_7a, //  r s t u v w x y z
          0xca_ca, //  Ê
          0xd1_d1, //  Ñ
          0xd9_d9, //  Ù
          0xea_ea, //  ê
          0xf1_f1, //  ñ
          0xf9_f9, //  ù
      },
        new int[]{
          0x02bc_02bc, //  ʼ
      },
      11, 57);


  /**
   * <p>The auxiliary characters for the Breton language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x43_43, //  C
          0x51_51, //  Q
          0x63_63, //  c
          0x71_71, //  q
          0xc0_c9, //  À Á Â Ã Ä Å Æ Ç È É
          0xcb_cf, //  Ë Ì Í Î Ï
          0xd2_d4, //  Ò Ó Ô
          0xd6_d6, //  Ö
          0xd8_d8, //  Ø
          0xda_dc, //  Ú Û Ü
          0xe0_e9, //  à á â ã ä å æ ç è é
          0xeb_ef, //  ë ì í î ï
          0xf2_f4, //  ò ó ô
          0xf6_f6, //  ö
          0xf8_f8, //  ø
          0xfa_fc, //  ú û ü
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
      24, 74);


  /**
   * <p>The punctuation characters for the Breton language as defined by the
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
          0x2011_2011, //  ‑
      },
      10, 14);


}
