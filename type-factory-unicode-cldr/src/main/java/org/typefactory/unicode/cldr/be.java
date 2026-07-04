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
 * Provides Type Factory subsets for the Belarusian language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class be extends AbstractCldrResourceBundle {

  public be() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Belarusian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x0401_0401, //  Ё
          0x0406_0406, //  І
          0x040e_040e, //  Ў
          0x0410_0417, //  А Б В Г Д Е Ж З
          0x0419_0428, //  Й К Л М Н О П Р С Т У Ф Х Ц Ч Ш
          0x042b_0437, //  Ы Ь Э Ю Я а б в г д е ж з
          0x0439_0448, //  й к л м н о п р с т у ф х ц ч ш
          0x044b_044f, //  ы ь э ю я
          0x0451_0451, //  ё
          0x0456_0456, //  і
          0x045e_045e, //  ў
      },
      11, 64);


  /**
   * <p>The auxiliary characters for the Belarusian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x0301_0301, //  ́
          0x0401_0401, //  Ё
          0x0406_0406, //  І
          0x0410_0410, //  А
          0x0415_0415, //  Е
          0x041e_041e, //  О
          0x0423_0423, //  У
          0x042b_042b, //  Ы
          0x042d_0430, //  Э Ю Я а
          0x0435_0435, //  е
          0x043e_043e, //  о
          0x0443_0443, //  у
          0x044b_044b, //  ы
          0x044d_044f, //  э ю я
          0x0451_0451, //  ё
          0x0456_0456, //  і
      },
      16, 21);


  /**
   * <p>The punctuation characters for the Belarusian language as defined by the
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
          0xab_ab, //  «
          0xbb_bb, //  »
      },
        new int[]{
          0x2011_2011, //  ‑
      },
      12, 16);


}
