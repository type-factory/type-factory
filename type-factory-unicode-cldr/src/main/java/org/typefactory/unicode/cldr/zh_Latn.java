/*
 * Copyright © 2021-2026 Evan Toliopoulos (typefactory.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.typefactory.unicode.cldr;

import javax.annotation.processing.Generated;
import org.typefactory.Subset;
import org.typefactory.impl.Factory;

/**
 * Provides Type Factory subsets for the Chinese (Latin) as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Chinese (Latin) language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class zh_Latn extends zh {

  public zh_Latn() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected zh_Latn(
          final Subset standardSubset,
          final Subset auxiliarySubset,
          final Subset punctuationSubset,
          final Subset decimalDigitsSubset) {
    super(
        defaultIfNull(standardSubset, STANDARD_CHARACTERS_SUBSET),
        defaultIfNull(auxiliarySubset, AUXILIARY_CHARACTERS_SUBSET),
        defaultIfNull(punctuationSubset, PUNCTUATION_CHARACTERS_SUBSET),
        defaultIfNull(decimalDigitsSubset, DECIMAL_DIGITS_SUBSET));
  }

  /**
   * <p>The standard characters for the Chinese (Latin) language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the standard characters
   *    are inherited from the superclass.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.optimalHashedRangedSubset(

      // Optimised hashing has one less level of indirection.
      // Hash-buckets contain 0..1 keys – 0xffff indicates an empty hash-bucket.
      //
      //       ┌─ hashIndex - an index to the hash-bucket which has at most one key
      //       │
      //  char[ ] blockKeys
      new char[ ] {

        0x0000, 0x0001, 0x001e, 0x0003  },

      // Optimised hashing has one less level of indirection.
      //
      //       ┌──── hashIndex           - an index to the hash-bucket
      //       │  ┌─ codePointRangeIndex - an index to the range within the array of ranges
      //       │  │
      //  char[ ][ ] codePointRanges
      new char[ ][ ] {

        { // 0x0000__ codePoint ranges
          0x41_55, 0x57_5a, 0x61_75, 0x77_7b, 0x7d_7d, 0xc0_c1, 0xc8_ca, 0xcc_cd,  // A B C D E F G H I J K L M N O P Q R S T
                                                                                   // U W X Y Z a b c d e f g h i j k l m n o
                                                                                   // p q r s t u w x y z { } À Á È É Ê Ì Í
          0xd2_d3, 0xd9_da, 0xdc_dc, 0xe0_e1, 0xe8_ea, 0xec_ed, 0xf2_f3, 0xf9_fa,  // Ò Ó Ù Ú Ü à á è é ê ì í ò ó ù ú
          0xfc_fc },                                                               // ü
        { // 0x0001__ codePoint ranges
          0x00_01, 0x08_09, 0x12_13, 0x1a_1b, 0x2a_2b, 0x43_44, 0x47_48, 0x4a_4d,  // Ā ā Ĉ ĉ Ē ē Ě ě Ī ī Ń ń Ň ň Ŋ ŋ Ō ō
          0x5c_5d, 0x6a_6b, 0xcd_dc, 0xf8_f9 },                                    // Ŝ ŝ Ū ū Ǎ ǎ Ǐ ǐ Ǒ ǒ Ǔ ǔ Ǖ ǖ Ǘ ǘ Ǚ ǚ Ǜ ǜ
                                                                                   // Ǹ ǹ
        { // 0x001e__ codePoint ranges
          0x3e_3f, 0x90_91, 0xbe_c1 },                                             // Ḿ ḿ Ẑ ẑ Ế ế Ề ề
        { // 0x0003__ codePoint ranges
          0x00_00, 0x04_04, 0x0c_0c } },                                           // ̀ ̄ ̌
        // number of code-point ranges
        35,
        // number of code-points
        127);


  /**
   * <p>The auxiliary characters for the Chinese (Latin) language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the auxiliary characters
   *    are inherited from the superclass.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

      new char[]{
          0x56_56, //  V
          0x76_76, //  v
      },
      2, 2);


  /**
   * <p>The punctuation characters for the Chinese (Latin) language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the punctuation characters
   *    are inherited from the superclass.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

      new char[]{
          0x21_22, //  ! "
          0x27_27, //  '
          0x2c_2e, //  , - .
          0x3f_3f, //  ?
          0xb7_b7, //  ·
      },
      new int[]{
          0x2011_2011, //  ‑
      },
      6, 9);


  /**
   * <p>The decimal digit characters for the Chinese (Latin) language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the decimal digit characters from the {@code <exemplarCharacters type="numbers">}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the decimal digit characters
   *    are inherited from the superclass.</p>
   */
  static final Subset DECIMAL_DIGITS_SUBSET = Factory.rangedSubset(

      new char[]{
          0x30_39, //  0 1 2 3 4 5 6 7 8 9
      },
      1, 10);


}
