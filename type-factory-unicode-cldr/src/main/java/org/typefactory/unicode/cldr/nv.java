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
 * Provides Type Factory subsets for the Navajo language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class nv extends AbstractCldrResourceBundle {

  public nv() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Navajo language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x41_45, //  A B C D E
          0x47_4f, //  G H I J K L M N O
          0x53_54, //  S T
          0x57_5a, //  W X Y Z
          0x61_65, //  a b c d e
          0x67_6f, //  g h i j k l m n o
          0x73_74, //  s t
          0x77_7b, //  w x y z {
          0x7d_7d, //  }
          0xc1_c1, //  Á
          0xc9_c9, //  É
          0xcd_cd, //  Í
          0xd3_d3, //  Ó
          0xe1_e1, //  á
          0xe9_e9, //  é
          0xed_ed, //  í
          0xf3_f3, //  ó
      },
        new int[]{
          0x0104_0105, //  Ą ą
          0x0118_0119, //  Ę ę
          0x012e_012f, //  Į į
          0x0141_0142, //  Ł ł
          0x01ea_01eb, //  Ǫ ǫ
          0x0301_0301, //  ́
          0x2019_2019, //  ’
      },
      24, 62);


  /**
   * <p>The auxiliary characters for the Navajo language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.emptySubset();


  /**
   * <p>The punctuation characters for the Navajo language as defined by the
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
          0xa7_a7, //  §
      },
        new int[]{
          0x2010_2011, //  ‐ ‑
          0x2013_2014, //  – —
          0x2018_2019, //  ‘ ’
          0x201c_201d, //  “ ”
          0x2020_2021, //  † ‡
          0x2026_2026, //  …
          0x2032_2033, //  ′ ″
      },
      15, 32);


}
