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
 * Provides Type Factory subsets for the Lakota language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class lkt extends CldrResourceBundle {

  public lkt() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Lakota language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x41_42, //  A B
          0x45_45, //  E
          0x47_49, //  G H I
          0x4b_50, //  K L M N O P
          0x53_55, //  S T U
          0x57_57, //  W
          0x59_5a, //  Y Z
          0x61_62, //  a b
          0x65_65, //  e
          0x67_69, //  g h i
          0x6b_70, //  k l m n o p
          0x73_75, //  s t u
          0x77_77, //  w
          0x79_7a, //  y z
          0xc1_c1, //  Á
          0xc9_c9, //  É
          0xcd_cd, //  Í
          0xd3_d3, //  Ó
          0xda_da, //  Ú
          0xe1_e1, //  á
          0xe9_e9, //  é
          0xed_ed, //  í
          0xf3_f3, //  ó
          0xfa_fa, //  ú
      },
        new int[]{
          0x010c_010d, //  Č č
          0x014a_014b, //  Ŋ ŋ
          0x0160_0161, //  Š š
          0x017d_017e, //  Ž ž
          0x01e6_01e7, //  Ǧ ǧ
          0x021e_021f, //  Ȟ ȟ
          0x02bc_02bc, //  ʼ
      },
      31, 59);


  /**
   * <p>The auxiliary characters for the Lakota language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x43_44, //  C D
          0x46_46, //  F
          0x4a_4a, //  J
          0x51_52, //  Q R
          0x56_56, //  V
          0x58_58, //  X
          0x63_64, //  c d
          0x66_66, //  f
          0x6a_6a, //  j
          0x71_72, //  q r
          0x76_76, //  v
          0x78_78, //  x
      },
      12, 16);


  /**
   * <p>The punctuation characters for the Lakota language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x21_23, //  ! " #
          0x26_26, //  &
          0x28_2a, //  ( ) *
          0x2c_2f, //  , - . /
          0x3a_3b, //  : ;
          0x3f_40, //  ? @
          0x5b_5b, //  [
          0x5d_5d, //  ]
      },
        new int[]{
          0x2010_2011, //  ‐ ‑
          0x2013_2014, //  – —
          0x201c_201d, //  “ ”
      },
      11, 23);


}
