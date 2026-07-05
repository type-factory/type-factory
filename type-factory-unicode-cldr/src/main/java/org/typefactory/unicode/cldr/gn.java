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
 * Provides Type Factory subsets for the Guarani language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class gn extends root {

  public gn() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  protected gn(
          final Subset standardSubset,
          final Subset auxiliarySubset,
          final Subset punctuationSubset) {
    super(
        standardSubset == null ? STANDARD_CHARACTERS_SUBSET : standardSubset,
        auxiliarySubset == null ? AUXILIARY_CHARACTERS_SUBSET : auxiliarySubset,
        punctuationSubset == null ? PUNCTUATION_CHARACTERS_SUBSET : punctuationSubset);
  }

  /**
   * <p>The standard characters for the Guarani language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x41_45, //  A B C D E
          0x47_50, //  G H I J K L M N O P
          0x52_56, //  R S T U V
          0x59_59, //  Y
          0x61_65, //  a b c d e
          0x67_70, //  g h i j k l m n o p
          0x72_76, //  r s t u v
          0x79_79, //  y
          0x7b_7b, //  {
          0x7d_7d, //  }
          0xc3_c3, //  Ã
          0xd1_d1, //  Ñ
          0xd5_d5, //  Õ
          0xe3_e3, //  ã
          0xf1_f1, //  ñ
          0xf5_f5, //  õ
      },
        new int[]{
          0x0128_0129, //  Ĩ ĩ
          0x0168_0169, //  Ũ ũ
          0x02bc_02bc, //  ʼ
          0x0303_0303, //  ̃
          0x1ebc_1ebd, //  Ẽ ẽ
          0x1ef8_1ef9, //  Ỹ ỹ
      },
      22, 60);


  /**
   * <p>The auxiliary characters for the Guarani language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x42_44, //  B C D
          0x46_46, //  F
          0x51_51, //  Q
          0x57_58, //  W X
          0x5a_5a, //  Z
          0x62_64, //  b c d
          0x66_66, //  f
          0x71_71, //  q
          0x77_78, //  w x
          0x7a_7a, //  z
      },
      10, 16);


  /**
   * <p>The punctuation characters for the Guarani language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = null;


}
