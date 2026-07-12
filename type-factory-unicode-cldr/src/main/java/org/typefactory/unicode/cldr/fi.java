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
 * Provides Type Factory subsets for the Finnish as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Finnish language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class fi extends root {

  public fi() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected fi(
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
   * <p>The standard characters for the Finnish language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the standard characters
   *    are inherited from the superclass.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

      new char[]{
          0x41_5a, //  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
          0x61_7a, //  a b c d e f g h i j k l m n o p q r s t u v w x y z
          0xc4_c5, //  Ä Å
          0xd6_d6, //  Ö
          0xe4_e5, //  ä å
          0xf6_f6, //  ö
      },
      new int[]{
          0x0160_0161, //  Š š
          0x017d_017e, //  Ž ž
      },
      8, 62);


  /**
   * <p>The auxiliary characters for the Finnish language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the auxiliary characters
   *    are inherited from the superclass.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.optimalHashedRangedSubset(

      // Optimised hashing has one less level of indirection.
      // Hash-buckets contain 0..1 keys – 0xffff indicates an empty hash-bucket.
      //
      //       ┌─ hashIndex - an index to the hash-bucket which has at most one key
      //       │
      //  char[ ] blockKeys
      new char[ ] {

        0x0000, 0x0001, 0x0002  },

      // Optimised hashing has one less level of indirection.
      //
      //       ┌──── hashIndex           - an index to the hash-bucket
      //       │  ┌─ codePointRangeIndex - an index to the range within the array of ranges
      //       │  │
      //  char[ ][ ] codePointRanges
      new char[ ][ ] {

        { // 0x0000__ codePoint ranges
          0x49_49, 0x53_53, 0xc0_c3, 0xc6_cb, 0xcd_d5, 0xd8_e3, 0xe6_eb, 0xed_f5,  // I S À Á Â Ã Æ Ç È É Ê Ë Í Î Ï Ð Ñ Ò Ó Ô
                                                                                   // Õ Ø Ù Ú Û Ü Ý Þ ß à á â ã æ ç è é ê ë í
                                                                                   // î ï ð ñ ò ó ô õ
          0xf8_ff },                                                               // ø ù ú û ü ý þ ÿ
        { // 0x0001__ codePoint ranges
          0x00_07, 0x0a_13, 0x16_1b, 0x1e_1f, 0x22_23, 0x26_27, 0x2a_2b, 0x2e_31,  // Ā ā Ă ă Ą ą Ć ć Ċ ċ Č č Ď ď Đ đ Ē ē Ė ė
                                                                                   // Ę ę Ě ě Ğ ğ Ģ ģ Ħ ħ Ī ī Į į İ ı
          0x36_37, 0x39_3e, 0x41_48, 0x4a_4b, 0x50_55, 0x58_5f, 0x62_67, 0x6a_6b,  // Ķ ķ Ĺ ĺ Ļ ļ Ľ ľ Ł ł Ń ń Ņ ņ Ň ň Ŋ ŋ Ő ő
                                                                                   // Œ œ Ŕ ŕ Ř ř Ś ś Ŝ ŝ Ş ş Ţ ţ Ť ť Ŧ ŧ Ū ū
          0x6e_73, 0x78_7c, 0xb7_b7, 0xe4_e9, 0xee_ef },                           // Ů ů Ű ű Ų ų Ÿ Ź ź Ż ż Ʒ Ǥ ǥ Ǧ ǧ Ǩ ǩ Ǯ ǯ
        { // 0x0002__ codePoint ranges
          0x18_1b, 0x1e_1f, 0x92_92 } },                                           // Ș ș Ț ț Ȟ ȟ ʒ
        // number of code-point ranges
        33,
        // number of code-points
        159);


  /**
   * <p>The punctuation characters for the Finnish language as defined by the
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
          0x21_21, //  !
          0x23_23, //  #
          0x26_26, //  &
          0x28_2a, //  ( ) *
          0x2c_2f, //  , - . /
          0x3a_3b, //  : ;
          0x3f_40, //  ? @
          0x5b_5d, //  [ \ ]
          0xa7_a7, //  §
          0xbb_bb, //  »
      },
      new int[]{
          0x2010_2011, //  ‐ ‑
          0x2013_2013, //  –
          0x2019_2019, //  ’
          0x201d_201d, //  ”
          0x2026_2026, //  …
      },
      15, 25);


  /**
   * <p>The decimal digit characters for the Finnish language as defined by the
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
