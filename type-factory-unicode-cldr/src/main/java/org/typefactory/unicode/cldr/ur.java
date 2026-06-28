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
 * Provides Type Factory subsets for the Urdu language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class ur extends AbstractCldrResourceBundle {

  public ur() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Urdu language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x0621_0621, //  ء
          0x0627_0628, //  ا ب
          0x062a_063a, //  ت ث ج ح خ د ذ ر ز س ش ص ض ط ظ ع غ
          0x0641_0642, //  ف ق
          0x0644_0646, //  ل م ن
          0x0648_0648, //  و
          0x0679_0679, //  ٹ
          0x067e_067e, //  پ
          0x0686_0686, //  چ
          0x0688_0688, //  ڈ
          0x0691_0691, //  ڑ
          0x0698_0698, //  ژ
          0x06a9_06a9, //  ک
          0x06af_06af, //  گ
          0x06be_06be, //  ھ
          0x06c1_06c1, //  ہ
          0x06cc_06cc, //  ی
          0x06d2_06d2, //  ے
      },
      18, 38);


  /**
   * <p>The auxiliary characters for the Urdu language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x0600_0603, //  ؀ ؁ ؂ ؃
          0x0622_0624, //  آ أ ؤ
          0x0626_0626, //  ئ
          0x0629_0629, //  ة
          0x0647_0647, //  ه
          0x064a_0652, //  ي ً ٌ ٍ َ ُ ِ ّ ْ
          0x0654_0654, //  ٔ
          0x0656_0658, //  ٖ ٗ ٘
          0x0670_0670, //  ٰ
          0x067a_067d, //  ٺ ٻ ټ ٽ
          0x06ba_06ba, //  ں
          0x06c2_06c3, //  ۂ ۃ
          0x200c_200d, //  ‌ ‍
      },
      13, 33);


  /**
   * <p>The punctuation characters for the Urdu language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x28_29, //  ( )
          0x2e_2e, //  .
          0x3a_3a, //  :
          0x5b_5b, //  [
          0x5d_5d, //  ]
      },
        new int[]{
          0x060c_060d, //  ، ؍
          0x061b_061b, //  ؛
          0x061f_061f, //  ؟
          0x066b_066c, //  ٫ ٬
          0x06d4_06d4, //  ۔
      },
      10, 13);


}
