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
 * Provides Type Factory subsets for the Metaʼ language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class mgo extends AbstractCldrResourceBundle {

  public mgo() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Metaʼ language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x41_42, //  A B
          0x44_47, //  D E F G
          0x49_4b, //  I J K
          0x4d_50, //  M N O P
          0x52_55, //  R S T U
          0x57_57, //  W
          0x59_5a, //  Y Z
          0x61_62, //  a b
          0x64_67, //  d e f g
          0x69_6b, //  i j k
          0x6d_70, //  m n o p
          0x72_75, //  r s t u
          0x77_77, //  w
          0x79_7a, //  y z
          0xc0_c0, //  À
          0xc8_c8, //  È
          0xcc_cc, //  Ì
          0xd2_d2, //  Ò
          0xd9_d9, //  Ù
          0xe0_e0, //  à
          0xe8_e8, //  è
          0xec_ec, //  ì
          0xf2_f2, //  ò
          0xf9_f9, //  ù
      },
        new int[]{
          0x014a_014b, //  Ŋ ŋ
          0x0186_0186, //  Ɔ
          0x018f_018f, //  Ə
          0x0254_0254, //  ɔ
          0x0259_0259, //  ə
          0x02bc_02bc, //  ʼ
      },
      30, 57);


  /**
   * <p>The auxiliary characters for the Metaʼ language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x43_43, //  C
          0x48_48, //  H
          0x4c_4c, //  L
          0x51_51, //  Q
          0x56_56, //  V
          0x58_58, //  X
          0x63_63, //  c
          0x68_68, //  h
          0x6c_6c, //  l
          0x71_71, //  q
          0x76_76, //  v
          0x78_78, //  x
      },
      12, 12);


  /**
   * <p>The punctuation characters for the Metaʼ language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x21_22, //  ! "
          0x27_27, //  '
          0x2c_2c, //  ,
          0x2e_2e, //  .
          0x3a_3b, //  : ;
          0x3f_3f, //  ?
      },
        new int[]{
          0x2018_2019, //  ‘ ’
          0x201c_201d, //  “ ”
      },
      8, 12);


}
