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
 * Provides Type Factory subsets for the Ewe language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class ee extends root {

  public ee() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  protected ee(
          final Subset standardSubset,
          final Subset auxiliarySubset,
          final Subset punctuationSubset) {
    super(
        standardSubset == null ? STANDARD_CHARACTERS_SUBSET : standardSubset,
        auxiliarySubset == null ? AUXILIARY_CHARACTERS_SUBSET : auxiliarySubset,
        punctuationSubset == null ? PUNCTUATION_CHARACTERS_SUBSET : punctuationSubset);
  }

  /**
   * <p>The standard characters for the Ewe language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.optimalHashedRangedSubset(

      // Hash-buckets with 0..1 keys – 0xffff indicates an empty hash-bucket.
      //
      //       ┌─ hashIndex - an index to the hash-bucket which has at most one key
      //       │
      //  char[ ] blockKeys
      new char[ ] {

        0x0000, 0x0001, 0x0002, 0x0003, 0xffff, 0xffff, 0x001e, 0xffff  },

      //       ┌──── hashIndex           - an index to the hash-bucket
      //       │  ┌─ codePointRangeIndex - an index to the range within the array of ranges
      //       │  │
      //  char[ ][ ] codePointRanges
      new char[ ][ ] {

        { // 0x0000__ codePoint ranges
          0x41_42, 0x44_49, 0x4b_50, 0x52_5a, 0x61_62, 0x64_69, 0x6b_70, 0x72_7b,  // A B D E F G H I K L M N O P R S T U V W
                                                                                   // X Y Z a b d e f g h i k l m n o p r s t
                                                                                   // u v w x y z {
          0x7d_7d, 0xc0_c1, 0xc3_c3, 0xc8_c9, 0xcc_cd, 0xd2_d3, 0xd5_d5, 0xd9_da,  // } À Á Ã È É Ì Í Ò Ó Õ Ù Ú
          0xe0_e1, 0xe3_e3, 0xe8_e9, 0xec_ed, 0xf2_f3, 0xf5_f5, 0xf9_fa },         // à á ã è é ì í ò ó õ ù ú
        { // 0x0001__ codePoint ranges
          0x28_29, 0x4a_4b, 0x68_69, 0x86_86, 0x89_89, 0x90_92, 0x94_94, 0xb2_b2 },// Ĩ ĩ Ŋ ŋ Ũ ũ Ɔ Ɖ Ɛ Ƒ ƒ Ɣ Ʋ
        { // 0x0002__ codePoint ranges
          0x54_54, 0x56_56, 0x5b_5b, 0x63_63, 0x8b_8b },                           // ɔ ɖ ɛ ɣ ʋ
        { // 0x0003__ codePoint ranges
          0x00_01, 0x03_03 },                                                      // ̀ ́ ̃
          null, null,
        { // 0x001e__ codePoint ranges
          0xbc_bd },                                                               // Ẽ ẽ
          null },                                                                  //
        // number of code-point ranges
        39,
        // number of code-points
        95);


  /**
   * <p>The auxiliary characters for the Ewe language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x43_43, //  C
          0x4a_4a, //  J
          0x51_51, //  Q
          0x63_63, //  c
          0x6a_6a, //  j
          0x71_71, //  q
          0xc2_c2, //  Â
          0xc4_c7, //  Ä Å Æ Ç
          0xca_cb, //  Ê Ë
          0xce_cf, //  Î Ï
          0xd1_d1, //  Ñ
          0xd4_d4, //  Ô
          0xd6_d6, //  Ö
          0xd8_d8, //  Ø
          0xdb_dc, //  Û Ü
          0xe2_e2, //  â
          0xe4_e7, //  ä å æ ç
          0xea_eb, //  ê ë
          0xee_ef, //  î ï
          0xf1_f1, //  ñ
          0xf4_f4, //  ô
          0xf6_f6, //  ö
          0xf8_f8, //  ø
          0xfb_fc, //  û ü
          0xff_ff, //  ÿ
      },
        new int[]{
          0x0100_0103, //  Ā ā Ă ă
          0x0114_0115, //  Ĕ ĕ
          0x012c_012d, //  Ĭ ĭ
          0x014e_014f, //  Ŏ ŏ
          0x0152_0153, //  Œ œ
          0x016c_016d, //  Ŭ ŭ
          0x0178_0178, //  Ÿ
      },
      32, 52);


  /**
   * <p>The punctuation characters for the Ewe language as defined by the
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
          0x7b_7b, //  {
          0x7d_7d, //  }
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
      17, 34);


}
