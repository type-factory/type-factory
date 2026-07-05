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
 * Provides Type Factory subsets for the Hausa language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class ha extends root {

  public ha() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  protected ha(
          final Subset standardSubset,
          final Subset auxiliarySubset,
          final Subset punctuationSubset) {
    super(
        standardSubset == null ? STANDARD_CHARACTERS_SUBSET : standardSubset,
        auxiliarySubset == null ? AUXILIARY_CHARACTERS_SUBSET : auxiliarySubset,
        punctuationSubset == null ? PUNCTUATION_CHARACTERS_SUBSET : punctuationSubset);
  }

  /**
   * <p>The standard characters for the Hausa language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x41_4f, //  A B C D E F G H I J K L M N O
          0x52_55, //  R S T U
          0x57_57, //  W
          0x59_5a, //  Y Z
          0x61_6f, //  a b c d e f g h i j k l m n o
          0x72_75, //  r s t u
          0x77_77, //  w
          0x79_7a, //  y z
      },
        new int[]{
          0x0181_0181, //  Ɓ
          0x018a_018a, //  Ɗ
          0x0198_0199, //  Ƙ ƙ
          0x01b3_01b4, //  Ƴ ƴ
          0x0253_0253, //  ɓ
          0x0257_0257, //  ɗ
          0x02bc_02bc, //  ʼ
      },
      15, 53);


  /**
   * <p>The auxiliary characters for the Hausa language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x50_52, //  P Q R
          0x56_56, //  V
          0x58_58, //  X
          0x70_72, //  p q r
          0x76_76, //  v
          0x78_78, //  x
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
          0x0303_0303, //  ̃
      },
      17, 41);


  /**
   * <p>The punctuation characters for the Hausa language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x21_22, //  ! "
          0x27_29, //  ' ( )
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
          0x2018_2019, //  ‘ ’
          0x201c_201d, //  “ ”
          0x2032_2033, //  ′ ″
      },
      13, 22);


}
