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
 * Provides Type Factory subsets for the Tajik language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class tg extends AbstractCldrResourceBundle {

  public tg() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Tajik language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x0401_0401, //  Ё
          0x0410_0425, //  А Б В Г Д Е Ж З И Й К Л М Н О П Р С Т У Ф Х
          0x0427_0428, //  Ч Ш
          0x042a_042a, //  Ъ
          0x042d_0445, //  Э Ю Я а б в г д е ж з и й к л м н о п р с т у ф х
          0x0447_0448, //  ч ш
          0x044a_044a, //  ъ
          0x044d_044f, //  э ю я
          0x0451_0451, //  ё
          0x0492_0493, //  Ғ ғ
          0x049a_049b, //  Қ қ
          0x04b2_04b3, //  Ҳ ҳ
          0x04b6_04b7, //  Ҷ ҷ
          0x04e2_04e3, //  Ӣ ӣ
          0x04ee_04ef, //  Ӯ ӯ
      },
      15, 70);


  /**
   * <p>The auxiliary characters for the Tajik language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x0426_0426, //  Ц
          0x0429_0429, //  Щ
          0x042b_042c, //  Ы Ь
          0x0446_0446, //  ц
          0x0449_0449, //  щ
          0x044b_044c, //  ы ь
      },
      6, 8);


  /**
   * <p>The punctuation characters for the Tajik language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x21_23, //  ! " #
          0x26_2a, //  & ' ( ) *
          0x2c_2f, //  , - . /
          0x3a_3b, //  : ;
          0x3f_40, //  ? @
          0x5b_5b, //  [
          0x5d_5d, //  ]
          0xa7_a7, //  §
          0xab_ab, //  «
          0xbb_bb, //  »
      },
        new int[]{
          0x2010_2011, //  ‐ ‑
          0x2013_2014, //  – —
          0x2018_2019, //  ‘ ’
          0x201c_201d, //  “ ”
          0x2020_2021, //  † ‡
          0x2026_2026, //  …
          0x2032_2033, //  ′ ″
      },
      17, 34);


}
