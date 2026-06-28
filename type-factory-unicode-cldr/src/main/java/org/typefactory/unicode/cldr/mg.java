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
 * Provides Type Factory subsets for the Malagasy language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class mg extends AbstractCldrResourceBundle {

  public mg() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Malagasy language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x41_42, //  A B
          0x44_50, //  D E F G H I J K L M N O P
          0x52_54, //  R S T
          0x56_56, //  V
          0x59_5a, //  Y Z
          0x61_62, //  a b
          0x64_70, //  d e f g h i j k l m n o p
          0x72_74, //  r s t
          0x76_76, //  v
          0x79_7a, //  y z
          0xc0_c0, //  À
          0xc8_c8, //  È
          0xcc_cc, //  Ì
          0xd2_d2, //  Ò
          0xd4_d4, //  Ô
          0xe0_e0, //  à
          0xe8_e8, //  è
          0xec_ec, //  ì
          0xf2_f2, //  ò
          0xf4_f4, //  ô
      },
        new int[]{
          0x1ef2_1ef3, //  Ỳ ỳ
      },
      21, 54);


  /**
   * <p>The auxiliary characters for the Malagasy language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x43_43, //  C
          0x51_51, //  Q
          0x55_55, //  U
          0x57_58, //  W X
          0x63_63, //  c
          0x71_71, //  q
          0x75_75, //  u
          0x77_78, //  w x
          0xc1_c3, //  Á Â Ã
          0xc9_cb, //  É Ê Ë
          0xce_cf, //  Î Ï
          0xd1_d1, //  Ñ
          0xd3_d3, //  Ó
          0xd6_d6, //  Ö
          0xdd_dd, //  Ý
          0xe1_e3, //  á â ã
          0xe9_eb, //  é ê ë
          0xee_ef, //  î ï
          0xf1_f1, //  ñ
          0xf3_f3, //  ó
          0xf6_f6, //  ö
          0xfd_fd, //  ý
          0xff_ff, //  ÿ
      },
        new int[]{
          0x0178_0178, //  Ÿ
      },
      24, 36);


  /**
   * <p>The punctuation characters for the Malagasy language as defined by the
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
          0x2010_2011, //  ‐ ‑
      },
      10, 15);


}
