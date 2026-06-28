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
 * Provides Type Factory subsets for the Cebuano language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class ceb extends CldrResourceBundle {

  public ceb() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Cebuano language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x41_42, //  A B
          0x44_45, //  D E
          0x47_49, //  G H I
          0x4b_50, //  K L M N O P
          0x52_55, //  R S T U
          0x57_57, //  W
          0x59_59, //  Y
          0x61_62, //  a b
          0x64_65, //  d e
          0x67_69, //  g h i
          0x6b_70, //  k l m n o p
          0x72_75, //  r s t u
          0x77_77, //  w
          0x79_79, //  y
      },
      14, 38);


  /**
   * <p>The auxiliary characters for the Cebuano language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x43_43, //  C
          0x46_46, //  F
          0x4a_4a, //  J
          0x51_51, //  Q
          0x56_56, //  V
          0x58_58, //  X
          0x5a_5a, //  Z
          0x63_63, //  c
          0x66_66, //  f
          0x6a_6a, //  j
          0x71_71, //  q
          0x76_76, //  v
          0x78_78, //  x
          0x7a_7a, //  z
          0xd1_d1, //  Ñ
          0xf1_f1, //  ñ
      },
      16, 16);


  /**
   * <p>The punctuation characters for the Cebuano language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x21_23, //  ! " #
          0x26_2a, //  & ' ( ) *
          0x2c_2f, //  , - . /
          0x3a_3b, //  : ;
          0x3f_40, //  ? @
          0x5b_5b, //  [
          0x5d_5d, //  ]
      },
        new int[]{
          0x2011_2011, //  ‑
          0x2018_2019, //  ‘ ’
          0x201c_201d, //  “ ”
          0x2026_2026, //  …
          0x2032_2033, //  ′ ″
      },
      12, 26);


}
