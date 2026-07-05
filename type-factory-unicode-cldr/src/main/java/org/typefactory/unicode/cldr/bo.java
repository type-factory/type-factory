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
 * Provides Type Factory subsets for the Tibetan language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class bo extends root {

  public bo() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  protected bo(
          final Subset standardSubset,
          final Subset auxiliarySubset,
          final Subset punctuationSubset) {
    super(
        standardSubset == null ? STANDARD_CHARACTERS_SUBSET : standardSubset,
        auxiliarySubset == null ? AUXILIARY_CHARACTERS_SUBSET : auxiliarySubset,
        punctuationSubset == null ? PUNCTUATION_CHARACTERS_SUBSET : punctuationSubset);
  }

  /**
   * <p>The standard characters for the Tibetan language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x0f40_0f42, //  ཀ ཁ ག
          0x0f44_0f47, //  ང ཅ ཆ ཇ
          0x0f49_0f4c, //  ཉ ཊ ཋ ཌ
          0x0f4e_0f51, //  ཎ ཏ ཐ ད
          0x0f53_0f56, //  ན པ ཕ བ
          0x0f58_0f5b, //  མ ཙ ཚ ཛ
          0x0f5d_0f68, //  ཝ ཞ ཟ འ ཡ ར ལ ཤ ཥ ས ཧ ཨ
          0x0f6a_0f6a, //  ཪ
          0x0f71_0f72, //  ཱ ི
          0x0f74_0f74, //  ུ
          0x0f77_0f77, //  ཷ
          0x0f79_0f80, //  ཹ ེ ཻ ོ ཽ ཾ ཿ ྀ
          0x0f84_0f84, //  ྄
          0x0f90_0f92, //  ྐ ྑ ྒ
          0x0f94_0f97, //  ྔ ྕ ྖ ྗ
          0x0f99_0f9c, //  ྙ ྚ ྛ ྜ
          0x0f9e_0fa1, //  ྞ ྟ ྠ ྡ
          0x0fa3_0fa6, //  ྣ ྤ ྥ ྦ
          0x0fa8_0fab, //  ྨ ྩ ྪ ྫ
          0x0fad_0fb8, //  ྭ ྮ ྯ ྰ ྱ ྲ ླ ྴ ྵ ྶ ྷ ྸ
          0x0fba_0fbc, //  ྺ ྻ ྼ
      },
      21, 87);


  /**
   * <p>The auxiliary characters for the Tibetan language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x0f00_0f00, //  ༀ
      },
      1, 1);


  /**
   * <p>The punctuation characters for the Tibetan language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x3a_3a, //  :
      },
        new int[]{
          0x0f0b_0f0b, //  ་
          0x0f0d_0f0d, //  །
      },
      3, 3);


}
