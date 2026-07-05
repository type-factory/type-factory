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
 * Provides Type Factory subsets for the Persian language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class fa_AF extends AbstractCldrResourceBundle {

  public fa_AF() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Persian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.emptySubset();


  /**
   * <p>The auxiliary characters for the Persian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x0625_0625, //  إ
          0x0643_0643, //  ك
          0x0649_064a, //  ى ي
          0x064e_0650, //  َ ُ ِ
          0x0652_0652, //  ْ
          0x0656_0656, //  ٖ
          0x0670_0670, //  ٰ
          0x067c_067c, //  ټ
          0x0681_0681, //  ځ
          0x0685_0685, //  څ
          0x0689_0689, //  ډ
          0x0693_0693, //  ړ
          0x0696_0696, //  ږ
          0x069a_069a, //  ښ
          0x06ab_06ab, //  ګ
          0x06bc_06bc, //  ڼ
          0x200c_200f, //  ‌ ‍ ‎ ‏
      },
      17, 23);


  /**
   * <p>The punctuation characters for the Persian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.emptySubset();


}
