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
 * Provides Type Factory subsets for the Persian as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Persian language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class fa extends root {

  public fa() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected fa(
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
   * <p>The Locale represented by this resource bundle for the Persian language.</p>
   *
   * <p>Language tag: {@code "fa"}</p>
   */
  static final Locale LOCALE = new Locale.Builder()
          .setLanguage("fa")
          .setScript("")
          .setRegion("")
          .setVariant("")
          .build();

  /**
   * <p>The standard characters for the Persian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the standard characters
   *    are inherited from the superclass.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

      new int[]{
          0x0621_0624, //  ء آ أ ؤ
          0x0626_063a, //  ئ ا ب ة ت ث ج ح خ د ذ ر ز س ش ص ض ط ظ ع غ
          0x0641_0642, //  ف ق
          0x0644_0648, //  ل م ن ه و
          0x064b_064d, //  ً ٌ ٍ
          0x0651_0651, //  ّ
          0x0654_0654, //  ٔ
          0x067e_067e, //  پ
          0x0686_0686, //  چ
          0x0698_0698, //  ژ
          0x06a9_06a9, //  ک
          0x06af_06af, //  گ
          0x06cc_06cc, //  ی
      },
      13, 43);


  /**
   * <p>The auxiliary characters for the Persian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the auxiliary characters
   *    are inherited from the superclass.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

      new int[]{
          0x0625_0625, //  إ
          0x0640_0640, //  ـ
          0x0643_0643, //  ك
          0x0649_064a, //  ى ي
          0x064e_0650, //  َ ُ ِ
          0x0652_0652, //  ْ
          0x0656_0656, //  ٖ
          0x0670_0670, //  ٰ
          0x200c_200f, //  ‌ ‍ ‎ ‏
      },
      9, 15);


  /**
   * <p>The punctuation characters for the Persian language as defined by the
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
          0x28_2a, //  ( ) *
          0x2d_2f, //  - . /
          0x3a_3a, //  :
          0x5b_5d, //  [ \ ]
          0xab_ab, //  «
          0xbb_bb, //  »
      },
      new int[]{
          0x060c_060c, //  ،
          0x061b_061b, //  ؛
          0x061f_061f, //  ؟
          0x066b_066c, //  ٫ ٬
          0x2010_2011, //  ‐ ‑
          0x2026_2026, //  …
          0x2039_203a, //  ‹ ›
      },
      14, 23);


  /**
   * <p>The decimal digit characters for the Persian language as defined by the
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
      new int[]{
          0x06f0_06f9, //  ۰ ۱ ۲ ۳ ۴ ۵ ۶ ۷ ۸ ۹
      },
      2, 20);


}
