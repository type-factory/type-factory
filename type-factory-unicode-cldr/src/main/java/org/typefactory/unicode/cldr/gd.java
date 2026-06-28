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
 * Provides Type Factory subsets for the Scottish Gaelic language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class gd extends CldrResourceBundle {

  public gd() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Scottish Gaelic language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x41_49, //  A B C D E F G H I
          0x4c_50, //  L M N O P
          0x52_55, //  R S T U
          0x61_69, //  a b c d e f g h i
          0x6c_70, //  l m n o p
          0x72_75, //  r s t u
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
      16, 46);


  /**
   * <p>The auxiliary characters for the Scottish Gaelic language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.optimalHashedRangedSubset(

      // Hash-buckets with 0..1 keys – 0xffff indicates an empty hash-bucket.
      //
      //       ┌─ hashIndex - an index to the hash-bucket which has at most one key
      //       │
      //  char[ ] blockKeys
      new char[ ] {
        0x0000, 0x0001, 0x0002, 0xffff, 0xffff, 0xffff, 0x001e, 0xffff  },

      //       ┌──── hashIndex           - an index to the hash-bucket
      //       │  ┌─ codePointRangeIndex - an index to the range within the array of ranges
      //       │  │
      //  char[ ][ ] codePointRanges
      new char[ ][ ] {
        { // 0x0000__ codePoint ranges
          0x49_4b, 0x51_51, 0x56_5a, 0x6a_6b, 0x71_71, 0x76_7a, 0xc1_c7, 0xc9_cb, 
          0xcd_cf, 0xd1_d1, 0xd3_d4, 0xd6_d6, 0xd8_d8, 0xda_dc, 0xe1_e7, 0xe9_eb, 
          0xed_ef, 0xf1_f1, 0xf3_f4, 0xf6_f6, 0xf8_f8, 0xfa_fc, 0xff_ff},
        { // 0x0001__ codePoint ranges
          0x00_03, 0x0a_0b, 0x12_15, 0x20_21, 0x2a_2d, 0x31_31, 0x41_42, 0x4c_4f, 
          0x52_53, 0x5e_5f, 0x6a_6d, 0x78_78},
        { // 0x0002__ codePoint ranges
          0x18_19},
          null, null, null,
        { // 0x001e__ codePoint ranges
          0x0a_0b, 0x1e_1f, 0x40_41, 0x56_57, 0x60_61, 0x6a_6b},
          null},
        // number of code-point ranges
        42,
        // number of code-points
        106);


  /**
   * <p>The punctuation characters for the Scottish Gaelic language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x21_23, //  ! " #
          0x25_2a, //  % & ' ( ) *
          0x2c_2f, //  , - . /
          0x3a_3b, //  : ;
          0x3f_40, //  ? @
          0x5b_5b, //  [
          0x5d_5d, //  ]
          0x7b_7b, //  {
          0x7d_7d, //  }
          0xa1_a1, //  ¡
          0xa7_a7, //  §
          0xa9_a9, //  ©
          0xae_ae, //  ®
          0xb0_b0, //  °
          0xb6_b7, //  ¶ ·
      },
        new int[]{
          0x2010_2011, //  ‐ ‑
          0x2013_2014, //  – —
          0x2018_2019, //  ‘ ’
          0x201c_201d, //  “ ”
          0x2020_2021, //  † ‡
          0x2026_2027, //  … ‧
          0x204a_204a, //  ⁊
          0x2122_2122, //  ™
      },
      23, 42);


}
