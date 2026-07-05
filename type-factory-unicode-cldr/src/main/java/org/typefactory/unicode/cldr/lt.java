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
public class lt extends root {

  public lt() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  protected lt(
          final Subset standardSubset,
          final Subset auxiliarySubset,
          final Subset punctuationSubset) {
    super(
        standardSubset == null ? STANDARD_CHARACTERS_SUBSET : standardSubset,
        auxiliarySubset == null ? AUXILIARY_CHARACTERS_SUBSET : auxiliarySubset,
        punctuationSubset == null ? PUNCTUATION_CHARACTERS_SUBSET : punctuationSubset);
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
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.optimalHashedRangedSubset(

      // Hash-buckets with 0..1 keys – 0xffff indicates an empty hash-bucket.
      //
      //       ┌─ hashIndex - an index to the hash-bucket which has at most one key
      //       │
      //  char[ ] blockKeys
      new char[ ] {

        0x0000, 0x0001, 0x001e, 0x0003  },

      //       ┌──── hashIndex           - an index to the hash-bucket
      //       │  ┌─ codePointRangeIndex - an index to the range within the array of ranges
      //       │  │
      //  char[ ][ ] codePointRanges
      new char[ ][ ] {

        { // 0x0000__ codePoint ranges
          0x43_44, 0x48_4a, 0x4c_4d, 0x51_52, 0x57_58, 0x5a_5a, 0x63_64, 0x68_6a,  // C D H I J L M Q R W X Z c d h i j
          0x6c_6d, 0x71_72, 0x77_78, 0x7a_7b, 0x7d_7d, 0xc0_c1, 0xc3_c3, 0xc8_c9,  // l m q r w x z { } À Á Ã È É
          0xcc_cd, 0xd1_d3, 0xd5_d5, 0xd9_da, 0xe0_e1, 0xe3_e3, 0xe8_e9, 0xec_ed,  // Ì Í Ñ Ò Ó Õ Ù Ú à á ã è é ì í
          0xf1_f3, 0xf5_f5, 0xf9_fa },                                             // ñ ò ó õ ù ú
        { // 0x0001__ codePoint ranges
          0x04_05, 0x16_19, 0x28_29, 0x2e_2f, 0x68_6b, 0x72_73, 0x7d_7e },         // Ą ą Ė ė Ę ę Ĩ ĩ Į į Ũ ũ Ū ū Ų ų Ž ž
        { // 0x001e__ codePoint ranges
          0xbc_bd },                                                               // Ẽ ẽ
        { // 0x0003__ codePoint ranges
          0x00_01, 0x03_03, 0x07_07 } },                                           // ̀ ́ ̃ ̇
        // number of code-point ranges
        38,
        // number of code-points
        76);


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
