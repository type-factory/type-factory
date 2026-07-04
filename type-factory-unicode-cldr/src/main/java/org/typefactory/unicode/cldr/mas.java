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
 * Provides Type Factory subsets for the Masai language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class mas extends AbstractCldrResourceBundle {

  public mas() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Masai language as defined by the
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
          0x41_45, 0x47_50, 0x52_55, 0x57_57, 0x59_59, 0x61_65, 0x67_70, 0x72_75,  // A B C D E G H I J K L M N O P R S T U W
                                                                                   // Y a b c d e g h i j k l m n o p r s t u
          0x77_77, 0x79_79, 0xc0_c2, 0xc8_ca, 0xcc_ce, 0xd2_d4, 0xd9_db, 0xe0_e2,  // w y À Á Â È É Ê Ì Í Î Ò Ó Ô Ù Ú Û à á â
          0xe8_ea, 0xec_ee, 0xf2_f4, 0xf9_fb },                                    // è é ê ì í î ò ó ô ù ú û
        { // 0x0001__ codePoint ranges
          0x00_01, 0x12_13, 0x2a_2b, 0x4a_4d, 0x6a_6b, 0x86_86, 0x90_90, 0x97_97 },// Ā ā Ē ē Ī ī Ŋ ŋ Ō ō Ū ū Ɔ Ɛ Ɨ
        { // 0x0002__ codePoint ranges
          0x44_44, 0x54_54, 0x5b_5b, 0x68_68, 0x89_89 },                           // Ʉ ɔ ɛ ɨ ʉ
        { // 0x0003__ codePoint ranges
          0x01_01 } },                                                             // ́
        // number of code-point ranges
        34,
        // number of code-points
        93);


  /**
   * <p>The auxiliary characters for the Masai language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x46_46, //  F
          0x51_51, //  Q
          0x56_56, //  V
          0x58_58, //  X
          0x5a_5a, //  Z
          0x66_66, //  f
          0x71_71, //  q
          0x76_76, //  v
          0x78_78, //  x
          0x7a_7a, //  z
      },
      10, 10);


  /**
   * <p>The punctuation characters for the Masai language as defined by the
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
