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
 * Provides Type Factory subsets for the Shan language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class shn extends AbstractCldrResourceBundle {

  public shn() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Shan language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x1004_1004, //  င
          0x1010_1011, //  တ ထ
          0x1015_1015, //  ပ
          0x1019_101e, //  မ ယ ရ လ ဝ သ
          0x1022_1022, //  ဢ
          0x102d_1031, //  ိ ီ ု ူ ေ
          0x1035_1036, //  ဵ ံ
          0x1038_1038, //  း
          0x103a_103d, //  ် ျ ြ ွ
          0x1062_1062, //  ၢ
          0x1075_1078, //  ၵ ၶ ၷ ၸ
          0x107a_108a, //  ၺ ၻ ၼ ၽ ၾ ၿ ႀ ႁ ႂ ႃ ႄ ႅ ႆ ႇ ႈ ႉ ႊ
          0x109e_109f, //  ႞ ႟
          0xa9e0_a9e0, //  ꧠ
          0xa9e3_a9e5, //  ꧣ ꧤ ꧥ
          0xaa61_aa61, //  ꩡ
          0xaa66_aa6a, //  ꩦ ꩧ ꩨ ꩩ ꩪ
          0xaa6e_aa6e, //  ꩮ
      },
      18, 58);


  /**
   * <p>The auxiliary characters for the Shan language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x1028_1028, //  ဨ
          0x1033_1034, //  ဳ ဴ
          0x1040_1049, //  ၀ ၁ ၂ ၃ ၄ ၅ ၆ ၇ ၈ ၉
          0x1050_105a, //  ၐ ၑ ၒ ၓ ၔ ၕ ၖ ၗ ၘ ၙ ၚ
          0x1065_1065, //  ၥ
          0x1090_1099, //  ႐ ႑ ႒ ႓ ႔ ႕ ႖ ႗ ႘ ႙
      },
      6, 35);


  /**
   * <p>The punctuation characters for the Shan language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x23_23, //  #
          0x28_2a, //  ( ) *
          0x2d_2d, //  -
          0x2f_2f, //  /
          0x40_40, //  @
          0x5b_5b, //  [
          0x5d_5d, //  ]
          0x7b_7b, //  {
          0x7d_7d, //  }
      },
        new int[]{
          0x104a_104b, //  ၊ ။
          0x2010_2011, //  ‐ ‑
          0x2013_2014, //  – —
          0x2018_2019, //  ‘ ’
          0x201c_201d, //  “ ”
          0x2026_2026, //  …
      },
      15, 22);


}
