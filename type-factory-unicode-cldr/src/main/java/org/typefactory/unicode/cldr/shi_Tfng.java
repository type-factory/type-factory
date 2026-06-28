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
 * Provides Type Factory subsets for the Tachelhit language (Tifinagh script) as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class shi_Tfng extends AbstractCldrResourceBundle {

  public shi_Tfng() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Tachelhit language (Tifinagh script) as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x2d30_2d31, //  ⴰ ⴱ
          0x2d33_2d33, //  ⴳ
          0x2d37_2d37, //  ⴷ
          0x2d39_2d39, //  ⴹ
          0x2d3b_2d3d, //  ⴻ ⴼ ⴽ
          0x2d40_2d40, //  ⵀ
          0x2d43_2d45, //  ⵃ ⵄ ⵅ
          0x2d47_2d47, //  ⵇ
          0x2d49_2d4a, //  ⵉ ⵊ
          0x2d4d_2d4f, //  ⵍ ⵎ ⵏ
          0x2d53_2d56, //  ⵓ ⵔ ⵕ ⵖ
          0x2d59_2d5c, //  ⵙ ⵚ ⵛ ⵜ
          0x2d5f_2d5f, //  ⵟ
          0x2d61_2d63, //  ⵡ ⵢ ⵣ
          0x2d65_2d65, //  ⵥ
      },
      15, 31);


  /**
   * <p>The auxiliary characters for the Tachelhit language (Tifinagh script) as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.emptySubset();


  /**
   * <p>The punctuation characters for the Tachelhit language (Tifinagh script) as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x21_21, //  !
          0x28_29, //  ( )
          0x2c_2e, //  , - .
          0x3a_3b, //  : ;
          0x3f_3f, //  ?
          0x5b_5b, //  [
          0x5d_5d, //  ]
          0x7b_7b, //  {
          0x7d_7d, //  }
      },
        new int[]{
          0x2010_2011, //  ‐ ‑
      },
      10, 15);


}
