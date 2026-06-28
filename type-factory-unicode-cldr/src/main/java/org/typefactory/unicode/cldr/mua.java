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
 * Provides Type Factory subsets for the Mundang language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class mua extends CldrResourceBundle {

  public mua() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Mundang language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x41_50, //  A B C D E F G H I J K L M N O P
          0x52_57, //  R S T U V W
          0x59_5a, //  Y Z
          0x61_70, //  a b c d e f g h i j k l m n o p
          0x72_77, //  r s t u v w
          0x79_7a, //  y z
          0xc3_c3, //  Ã
          0xcb_cb, //  Ë
          0xd5_d5, //  Õ
          0xe3_e3, //  ã
          0xeb_eb, //  ë
          0xf5_f5, //  õ
      },
        new int[]{
          0x0128_0129, //  Ĩ ĩ
          0x014a_014b, //  Ŋ ŋ
          0x0181_0181, //  Ɓ
          0x018a_018a, //  Ɗ
          0x018e_018e, //  Ǝ
          0x01dd_01dd, //  ǝ
          0x0253_0253, //  ɓ
          0x0257_0257, //  ɗ
          0x1e7c_1e7d, //  Ṽ ṽ
      },
      21, 66);


  /**
   * <p>The auxiliary characters for the Mundang language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x51_51, //  Q
          0x58_58, //  X
          0x71_71, //  q
          0x78_78, //  x
      },
      4, 4);


  /**
   * <p>The punctuation characters for the Mundang language as defined by the
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
