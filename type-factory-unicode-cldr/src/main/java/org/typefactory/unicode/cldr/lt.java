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
 * Provides Type Factory subsets for the Lithuanian language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class lt extends CldrResourceBundle {

  public lt() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Lithuanian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x41_50, //  A B C D E F G H I J K L M N O P
          0x52_56, //  R S T U V
          0x59_5a, //  Y Z
          0x61_70, //  a b c d e f g h i j k l m n o p
          0x72_76, //  r s t u v
          0x79_7a, //  y z
      },
        new int[]{
          0x0104_0105, //  Ą ą
          0x010c_010d, //  Č č
          0x0116_0119, //  Ė ė Ę ę
          0x012e_012f, //  Į į
          0x0160_0161, //  Š š
          0x016a_016b, //  Ū ū
          0x0172_0173, //  Ų ų
          0x017d_017e, //  Ž ž
      },
      14, 64);


  /**
   * <p>The auxiliary characters for the Lithuanian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x51_51, //  Q
          0x57_58, //  W X
          0x71_71, //  q
          0x77_78, //  w x
          0xc0_c1, //  À Á
          0xc3_c3, //  Ã
          0xc8_c9, //  È É
          0xcc_cd, //  Ì Í
          0xd1_d3, //  Ñ Ò Ó
          0xd5_d5, //  Õ
          0xd9_da, //  Ù Ú
          0xe0_e1, //  à á
          0xe3_e3, //  ã
          0xe8_e9, //  è é
          0xec_ed, //  ì í
          0xf1_f3, //  ñ ò ó
          0xf5_f5, //  õ
          0xf9_fa, //  ù ú
      },
        new int[]{
          0x0128_0129, //  Ĩ ĩ
          0x0168_0169, //  Ũ ũ
          0x1ebc_1ebd, //  Ẽ ẽ
      },
      21, 38);


  /**
   * <p>The punctuation characters for the Lithuanian language as defined by the
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
          0x2013_2014, //  – —
          0x201c_201c, //  “
          0x201e_201e, //  „
          0x2026_2026, //  …
      },
      14, 20);


}
