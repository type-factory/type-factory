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
 * Provides Type Factory subsets for the Azerbaijani language (Cyrillic script) as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class az_Cyrl extends CldrResourceBundle {

  public az_Cyrl() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Azerbaijani language (Cyrillic script) as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x0408_0408, //  Ј
          0x0410_0425, //  А Б В Г Д Е Ж З И Й К Л М Н О П Р С Т У Ф Х
          0x0427_0428, //  Ч Ш
          0x042b_042b, //  Ы
          0x0430_0445, //  а б в г д е ж з и й к л м н о п р с т у ф х
          0x0447_0448, //  ч ш
          0x044b_044b, //  ы
          0x0458_0458, //  ј
          0x0492_0493, //  Ғ ғ
          0x049c_049d, //  Ҝ ҝ
          0x04ae_04af, //  Ү ү
          0x04b8_04bb, //  Ҹ ҹ Һ һ
          0x04d8_04d9, //  Ә ә
          0x04e8_04e9, //  Ө ө
      },
      14, 66);


  /**
   * <p>The auxiliary characters for the Azerbaijani language (Cyrillic script) as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x0426_0426, //  Ц
          0x0429_042a, //  Щ Ъ
          0x042c_042f, //  Ь Э Ю Я
          0x0446_0446, //  ц
          0x0449_044a, //  щ ъ
          0x044c_044f, //  ь э ю я
      },
      6, 14);


  /**
   * <p>The punctuation characters for the Azerbaijani language (Cyrillic script) as defined by the
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
