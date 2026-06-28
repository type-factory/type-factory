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
 * Provides Type Factory subsets for the Telugu language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class te extends CldrResourceBundle {

  public te() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Telugu language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x0c01_0c03, //  ఁ ం ః
          0x0c05_0c0c, //  అ ఆ ఇ ఈ ఉ ఊ ఋ ఌ
          0x0c0e_0c10, //  ఎ ఏ ఐ
          0x0c12_0c28, //  ఒ ఓ ఔ క ఖ గ ఘ ఙ చ ఛ జ ఝ ఞ ట ఠ డ ఢ ణ త థ ద ధ న
          0x0c2a_0c33, //  ప ఫ బ భ మ య ర ఱ ల ళ
          0x0c35_0c39, //  వ శ ష స హ
          0x0c3e_0c44, //  ా ి ీ ు ూ ృ ౄ
          0x0c46_0c48, //  ె ే ై
          0x0c4a_0c4d, //  ొ ో ౌ ్
          0x0c55_0c56, //  ౕ ౖ
          0x0c60_0c61, //  ౠ ౡ
      },
      11, 70);


  /**
   * <p>The auxiliary characters for the Telugu language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x0c66_0c6f, //  ౦ ౧ ౨ ౩ ౪ ౫ ౬ ౭ ౮ ౯
          0x200c_200d, //  ‌ ‍
      },
      2, 12);


  /**
   * <p>The punctuation characters for the Telugu language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x21_23, //  ! " #
          0x26_29, //  & ' ( )
          0x2c_2e, //  , - .
          0x3a_3b, //  : ;
          0x3f_3f, //  ?
          0x5b_5b, //  [
          0x5d_5d, //  ]
          0x7b_7b, //  {
          0x7d_7d, //  }
      },
        new int[]{
          0x2011_2011, //  ‑
          0x2018_2019, //  ‘ ’
          0x201c_201d, //  “ ”
      },
      12, 22);


}
