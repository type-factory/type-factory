/*
   Copyright 2021-2026 Evan Toliopoulos (typefactory.org)

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
 * Provides Type Factory subsets for the Kurdish (Arabic) as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Kurdish (Arabic) language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class ku_Arab extends ku {

  public ku_Arab() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected ku_Arab(
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
   * <p>The standard characters for the Kurdish (Arabic) language as defined by the
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
          0x0626_0628, //  ئ ا ب
          0x062a_062a, //  ت
          0x062c_062f, //  ج ح خ د
          0x0631_0634, //  ر ز س ش
          0x0639_063a, //  ع غ
          0x0641_0642, //  ف ق
          0x0644_0648, //  ل م ن ه و
          0x067e_067e, //  پ
          0x0686_0686, //  چ
          0x0695_0695, //  ڕ
          0x0698_0698, //  ژ
          0x06a4_06a4, //  ڤ
          0x06a9_06a9, //  ک
          0x06af_06af, //  گ
          0x06b5_06b5, //  ڵ
          0x06c6_06c6, //  ۆ
          0x06cc_06cc, //  ی
          0x06ce_06ce, //  ێ
          0x06d5_06d5, //  ە
      },
      19, 33);


  /**
   * <p>The auxiliary characters for the Kurdish (Arabic) language as defined by the
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
          0x0621_0625, //  ء آ أ ؤ إ
          0x0629_0629, //  ة
          0x062b_062b, //  ث
          0x0630_0630, //  ذ
          0x0635_0638, //  ص ض ط ظ
          0x0643_0643, //  ك
          0x0649_0652, //  ى ي ً ٌ ٍ َ ُ ِ ّ ْ
          0x06be_06be, //  ھ
          0x200c_200f, //  ‌ ‍ ‎ ‏
      },
      9, 28);


  /**
   * <p>The punctuation characters for the Kurdish (Arabic) language as defined by the
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
          0x28_2a, //  ( ) *
          0x2c_2f, //  , - . /
          0x3a_3a, //  :
          0x5b_5b, //  [
          0x5d_5d, //  ]
          0x7b_7b, //  {
          0x7d_7d, //  }
          0xab_ab, //  «
          0xbb_bb, //  »
      },
      new int[]{
          0x060c_060c, //  ،
          0x061b_061b, //  ؛
          0x061f_061f, //  ؟
          0x2010_2011, //  ‐ ‑
          0x2018_2019, //  ‘ ’
          0x201c_201d, //  “ ”
          0x2022_2022, //  •
          0x2026_2026, //  …
          0x2039_203a, //  ‹ ›
          0x2212_2212, //  −
          0xfd3f_fd3f, //  ﴿
      },
      21, 31);


  /**
   * <p>The decimal digit characters for the Kurdish (Arabic) language as defined by the
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
          0x0660_0669, //  ٠ ١ ٢ ٣ ٤ ٥ ٦ ٧ ٨ ٩
      },
      2, 20);


}
