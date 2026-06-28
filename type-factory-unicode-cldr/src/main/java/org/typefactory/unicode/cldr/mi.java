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
 * Provides Type Factory subsets for the Māori language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class mi extends CldrResourceBundle {

  public mi() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Māori language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x41_41, //  A
          0x45_45, //  E
          0x48_49, //  H I
          0x4b_4b, //  K
          0x4d_50, //  M N O P
          0x52_52, //  R
          0x54_55, //  T U
          0x57_57, //  W
          0x61_61, //  a
          0x65_65, //  e
          0x68_69, //  h i
          0x6b_6b, //  k
          0x6d_70, //  m n o p
          0x72_72, //  r
          0x74_75, //  t u
          0x77_77, //  w
      },
        new int[]{
          0x0100_0101, //  Ā ā
          0x0112_0113, //  Ē ē
          0x012a_012b, //  Ī ī
          0x014c_014d, //  Ō ō
          0x016a_016b, //  Ū ū
      },
      21, 36);


  /**
   * <p>The auxiliary characters for the Māori language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x42_44, //  B C D
          0x46_47, //  F G
          0x4a_4a, //  J
          0x4c_4c, //  L
          0x51_51, //  Q
          0x53_53, //  S
          0x56_56, //  V
          0x58_5a, //  X Y Z
          0x62_64, //  b c d
          0x66_67, //  f g
          0x6a_6a, //  j
          0x6c_6c, //  l
          0x71_71, //  q
          0x73_73, //  s
          0x76_76, //  v
          0x78_7a, //  x y z
      },
      16, 26);


  /**
   * <p>The punctuation characters for the Māori language as defined by the
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
