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
 * Provides Type Factory subsets for the Ngomba language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class jgo extends AbstractCldrResourceBundle {

  public jgo() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Ngomba language as defined by the
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
        0x0000, 0x0001, 0x0002, 0x0003, 0xffff, 0xffff, 0x001e, 0x00a7  },

      //       ┌──── hashIndex           - an index to the hash-bucket
      //       │  ┌─ codePointRangeIndex - an index to the range within the array of ranges
      //       │  │
      //  char[ ][ ] codePointRanges
      new char[ ][ ] {
        { // 0x0000__ codePoint ranges
          0x41_44, 0x46_4e, 0x50_50, 0x53_57, 0x59_5a, 0x61_64, 0x66_6e, 0x70_70,  // A B C D F G H I J K L M N P S T U V W Y
                                                                                   // Z a b c d f g h i j k l m n p
          0x73_77, 0x79_7a, 0xc1_c2, 0xcd_ce, 0xda_db, 0xe1_e2, 0xed_ee, 0xfa_fb },// s t u v w y z Á Â Í Î Ú Û á â í î ú û
        { // 0x0001__ codePoint ranges
          0x43_44, 0x4a_4b, 0x86_86, 0x90_90, 0xcd_d0, 0xd3_d4, 0xf8_f9 },         // Ń ń Ŋ ŋ Ɔ Ɛ Ǎ ǎ Ǐ ǐ Ǔ ǔ Ǹ ǹ
        { // 0x0002__ codePoint ranges
          0x44_44, 0x54_54, 0x5b_5b, 0x89_89 },                                    // Ʉ ɔ ɛ ʉ
        { // 0x0003__ codePoint ranges
          0x00_02, 0x04_04, 0x08_08, 0x0c_0c },                                    // ̀ ́ ̂ ̄ ̈ ̌
          null, null,
        { // 0x001e__ codePoint ranges
          0x3e_3f, 0x84_85 },                                                      // Ḿ ḿ Ẅ ẅ
        { // 0x00a7__ codePoint ranges
          0x8b_8c } },                                                             // Ꞌ ꞌ
        // number of code-point ranges
        34,
        // number of code-points
        84);


  /**
   * <p>The auxiliary characters for the Ngomba language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x45_45, //  E
          0x4f_4f, //  O
          0x51_52, //  Q R
          0x58_58, //  X
          0x65_65, //  e
          0x6f_6f, //  o
          0x71_72, //  q r
          0x78_78, //  x
      },
      8, 10);


  /**
   * <p>The punctuation characters for the Ngomba language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x21_21, //  !
          0x2c_2e, //  , - .
          0x3a_3b, //  : ;
          0x3f_3f, //  ?
          0xab_ab, //  «
          0xbb_bb, //  »
      },
        new int[]{
          0x2011_2011, //  ‑
          0x2039_203a, //  ‹ ›
      },
      8, 12);


}
