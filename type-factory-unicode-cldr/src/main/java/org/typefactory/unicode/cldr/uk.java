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
 * Provides Type Factory subsets for the Ukrainian language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class uk extends CldrResourceBundle {

  public uk() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Ukrainian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x02bc_02bc, //  ʼ
          0x0404_0404, //  Є
          0x0406_0407, //  І Ї
          0x0410_0429, //  А Б В Г Д Е Ж З И Й К Л М Н О П Р С Т У Ф Х Ц Ч Ш Щ
          0x042c_042c, //  Ь
          0x042e_0449, //  Ю Я а б в г д е ж з и й к л м н о п р с т у ф х ц ч ш щ
          0x044c_044c, //  ь
          0x044e_044f, //  ю я
          0x0454_0454, //  є
          0x0456_0457, //  і ї
          0x0490_0491, //  Ґ ґ
      },
      11, 67);


  /**
   * <p>The auxiliary characters for the Ukrainian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x0401_0401, //  Ё
          0x042a_042b, //  Ъ Ы
          0x042d_042d, //  Э
          0x044a_044b, //  ъ ы
          0x044d_044d, //  э
          0x0451_0451, //  ё
      },
      6, 8);


  /**
   * <p>The punctuation characters for the Ukrainian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x21_22, //  ! "
          0x27_2a, //  ' ( ) *
          0x2c_2f, //  , - . /
          0x3a_3b, //  : ;
          0x3f_40, //  ? @
          0x5b_5d, //  [ \ ]
          0x7b_7b, //  {
          0x7d_7d, //  }
          0xa7_a7, //  §
          0xab_ab, //  «
          0xbb_bb, //  »
      },
        new int[]{
          0x2011_2011, //  ‑
          0x2013_2013, //  –
          0x2019_2019, //  ’
          0x201c_201c, //  “
          0x201e_201e, //  „
          0x2116_2116, //  №
      },
      17, 28);


}
