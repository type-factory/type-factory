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
 * Provides Type Factory subsets for the Kwasio language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class nmg extends AbstractCldrResourceBundle {

  public nmg() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Kwasio language as defined by the
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
        0x0000, 0x0001, 0x0002, 0x0003  },

      //       ┌──── hashIndex           - an index to the hash-bucket
      //       │  ┌─ codePointRangeIndex - an index to the range within the array of ranges
      //       │  │
      //  char[ ][ ] codePointRanges
      new char[ ][ ] {
        { // 0x0000__ codePoint ranges
          0x41_50, 0x52_57, 0x59_59, 0x61_70, 0x72_77, 0x79_79, 0xc1_c2, 0xc4_c4,  // A B C D E F G H I J K L M N O P R S T U
                                                                                   // V W Y a b c d e f g h i j k l m n o p r
                                                                                   // s t u v w y Á Â Ä
          0xc9_ca, 0xcd_cf, 0xd3_d4, 0xd6_d6, 0xda_db, 0xe1_e2, 0xe4_e4, 0xe9_ea,  // É Ê Í Î Ï Ó Ô Ö Ú Û á â ä é ê
          0xed_ef, 0xf3_f4, 0xf6_f6, 0xfa_fb },                                    // í î ï ó ô ö ú û
        { // 0x0001__ codePoint ranges
          0x00_01, 0x12_13, 0x1a_1b, 0x2a_2b, 0x43_44, 0x4a_4d, 0x54_55, 0x6a_6b,  // Ā ā Ē ē Ě ě Ī ī Ń ń Ŋ ŋ Ō ō Ŕ ŕ Ū ū
          0x81_81, 0x86_86, 0x8e_8e, 0x90_90, 0xcd_d4, 0xdd_dd },                  // Ɓ Ɔ Ǝ Ɛ Ǎ ǎ Ǐ ǐ Ǒ ǒ Ǔ ǔ ǝ
        { // 0x0002__ codePoint ranges
          0x53_54, 0x5b_5b },                                                      // ɓ ɔ ɛ
        { // 0x0003__ codePoint ranges
          0x01_02, 0x04_04, 0x0c_0c } },                                           // ́ ̂ ̄ ̌
        // number of code-point ranges
        39,
        // number of code-points
        110);


  /**
   * <p>The auxiliary characters for the Kwasio language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x51_51, //  Q
          0x58_58, //  X
          0x5a_5a, //  Z
          0x71_71, //  q
          0x78_78, //  x
          0x7a_7a, //  z
      },
      6, 6);


  /**
   * <p>The punctuation characters for the Kwasio language as defined by the
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
