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
 * Provides Type Factory subsets for the Ngiemboon language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class nnh extends AbstractCldrResourceBundle {

  public nnh() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Ngiemboon language as defined by the
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
          0x41_50, 0x53_57, 0x59_5a, 0x61_70, 0x73_77, 0x79_7a, 0xc0_c2, 0xc8_ca,  // A B C D E F G H I J K L M N O P S T U V
                                                                                   // W Y Z a b c d e f g h i j k l m n o p s
                                                                                   // t u v w y z À Á Â È É Ê
          0xcc_cd, 0xd2_d4, 0xd9_db, 0xe0_e2, 0xe8_ea, 0xec_ed, 0xf2_f4, 0xf9_fb,  // Ì Í Ò Ó Ô Ù Ú Û à á â è é ê ì í ò ó ô ù
                                                                                   // ú û
          0xff_ff },                                                               // ÿ
        { // 0x0001__ codePoint ranges
          0x1a_1b, 0x43_44, 0x4a_4b, 0x78_78, 0x86_86, 0x90_90, 0xcd_ce, 0xd1_d4 },// Ě ě Ń ń Ŋ ŋ Ÿ Ɔ Ɛ Ǎ ǎ Ǒ ǒ Ǔ ǔ
        { // 0x0002__ codePoint ranges
          0x44_44, 0x54_54, 0x5b_5b, 0x89_89, 0xbc_bc },                           // Ʉ ɔ ɛ ʉ ʼ
        { // 0x0003__ codePoint ranges
          0x00_02, 0x0c_0c },                                                      // ̀ ́ ̂ ̌
          null, null,
        { // 0x001e__ codePoint ranges
          0x3e_3f, 0x84_85 },                                                      // Ḿ ḿ Ẅ ẅ
          null },                                                                  //
        // number of code-point ranges
        34,
        // number of code-points
        103);


  /**
   * <p>The auxiliary characters for the Ngiemboon language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x51_52, //  Q R
          0x58_58, //  X
          0x71_72, //  q r
          0x78_78, //  x
      },
      4, 6);


  /**
   * <p>The punctuation characters for the Ngiemboon language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x21_21, //  !
          0x27_27, //  '
          0x2c_2c, //  ,
          0x2e_2e, //  .
          0x3a_3b, //  : ;
          0x3f_3f, //  ?
          0xab_ab, //  «
          0xbb_bb, //  »
      },
        new int[]{
          0x2018_2019, //  ‘ ’
      },
      9, 11);


}
