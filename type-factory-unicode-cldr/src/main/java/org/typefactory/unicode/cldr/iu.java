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

import java.util.Locale;
import javax.annotation.processing.Generated;
import org.typefactory.Subset;
import org.typefactory.impl.Factory;

/**
 * Provides Type Factory subsets for the Inuktitut as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Inuktitut language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class iu extends root {

  public iu() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected iu(
          final Subset standardSubset,
          final Subset auxiliarySubset,
          final Subset punctuationSubset,
          final Subset decimalDigitsSubset) {
    super(
        standardSubset == null ? STANDARD_CHARACTERS_SUBSET : standardSubset,
        auxiliarySubset == null ? AUXILIARY_CHARACTERS_SUBSET : auxiliarySubset,
        punctuationSubset == null ? PUNCTUATION_CHARACTERS_SUBSET : punctuationSubset,
        decimalDigitsSubset == null ? DECIMAL_DIGITS_SUBSET : decimalDigitsSubset);
  }

  /**
   * <p>The Locale represented by this resource bundle for the Inuktitut language.</p>
   *
   * <p>Language tag: {@code "iu"}</p>
   */
  static final Locale LOCALE = new Locale.Builder()
          .setLanguage("iu")
          .setScript("")
          .setRegion("")
          .setVariant("")
          .build();

  /**
   * <p>The standard characters for the Inuktitut language as defined by the
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

        0x0015, 0x0016, 0x0014  },

      // Optimised hashing has one less level of indirection.
      //
      //       ┌──── hashIndex           - an index to the hash-bucket
      //       │  ┌─ codePointRangeIndex - an index to the range within the array of ranges
      //       │  │
      //  char[ ][ ] codePointRanges
      new char[ ][ ] {

        { // 0x0015__ codePoint ranges
          0x05_05, 0x28_2b, 0x2d_2e, 0x3e_3e, 0x46_49, 0x4b_4c, 0x50_50, 0x55_5a,  // ᔅ ᔨ ᔩ ᔪ ᔫ ᔭ ᔮ ᔾ ᕆ ᕇ ᕈ ᕉ ᕋ ᕌ ᕐ ᕕ ᕖ ᕗ ᕘ ᕙ
                                                                                   // ᕚ
          0x5d_5d, 0x7f_83, 0x85_85, 0x8f_8f, 0x91_96, 0xa0_a6 },                  // ᕝ ᕿ ᖀ ᖁ ᖂ ᖃ ᖅ ᖏ ᖑ ᖒ ᖓ ᖔ ᖕ ᖖ ᖠ ᖡ ᖢ ᖣ ᖤ ᖥ
                                                                                   // ᖦ
        { // 0x0016__ codePoint ranges
          0x71_76 },                                                               // ᙱ ᙲ ᙳ ᙴ ᙵ ᙶ
        { // 0x0014__ codePoint ranges
          0x03_06, 0x0a_0b, 0x31_34, 0x38_39, 0x49_49, 0x4e_51, 0x55_56, 0x66_66,  // ᐃ ᐄ ᐅ ᐆ ᐊ ᐋ ᐱ ᐲ ᐳ ᐴ ᐸ ᐹ ᑉ ᑎ ᑏ ᑐ ᑑ ᑕ ᑖ ᑦ
          0x6d_70, 0x72_73, 0x83_83, 0x8b_8e, 0x90_91, 0xa1_a1, 0xa5_a8, 0xaa_ab,  // ᑭ ᑮ ᑯ ᑰ ᑲ ᑳ ᒃ ᒋ ᒌ ᒍ ᒎ ᒐ ᒑ ᒡ ᒥ ᒦ ᒧ ᒨ ᒪ ᒫ
          0xbb_bb, 0xc2_c5, 0xc7_c8, 0xd0_d0, 0xd5_d8, 0xda_db, 0xea_ea, 0xef_f2,  // ᒻ ᓂ ᓃ ᓄ ᓅ ᓇ ᓈ ᓐ ᓕ ᓖ ᓗ ᓘ ᓚ ᓛ ᓪ ᓯ ᓰ ᓱ ᓲ
          0xf4_f5 } },                                                             // ᓴ ᓵ
        // number of code-point ranges
        40,
        // number of code-points
        109);


  /**
   * <p>The auxiliary characters for the Inuktitut language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the auxiliary characters
   *    are inherited from the superclass.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = null;


  /**
   * <p>The punctuation characters for the Inuktitut language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the punctuation characters
   *    are inherited from the superclass.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = null;


  /**
   * <p>The decimal digit characters for the Inuktitut language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the decimal digit characters from the {@code <exemplarCharacters type="numbers">}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the decimal digit characters
   *    are inherited from the superclass.</p>
   */
  static final Subset DECIMAL_DIGITS_SUBSET = null;


}
