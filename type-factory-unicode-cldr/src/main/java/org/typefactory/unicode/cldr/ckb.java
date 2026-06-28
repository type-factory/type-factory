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
 * Provides Type Factory subsets for the Central Kurdish language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class ckb extends AbstractCldrResourceBundle {

  public ckb() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Central Kurdish language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
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
   * <p>The auxiliary characters for the Central Kurdish language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
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
      },
      8, 24);


  /**
   * <p>The punctuation characters for the Central Kurdish language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x21_22, //  ! "
          0x27_29, //  ' ( )
          0x2d_2e, //  - .
          0x3a_3a, //  :
          0x5b_5b, //  [
          0x5d_5d, //  ]
          0xab_ab, //  «
          0xbb_bb, //  »
      },
        new int[]{
          0x060c_060c, //  ،
          0x061b_061b, //  ؛
          0x061f_061f, //  ؟
          0x2010_2011, //  ‐ ‑
          0x2013_2014, //  – —
          0x2026_2026, //  …
      },
      14, 20);


}
