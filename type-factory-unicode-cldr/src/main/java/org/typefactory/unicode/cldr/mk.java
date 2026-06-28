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
 * Provides Type Factory subsets for the Macedonian language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class mk extends CldrResourceBundle {

  public mk() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Macedonian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x0403_0403, //  Ѓ
          0x0405_0405, //  Ѕ
          0x0408_040a, //  Ј Љ Њ
          0x040c_040c, //  Ќ
          0x040f_0418, //  Џ А Б В Г Д Е Ж З И
          0x041a_0428, //  К Л М Н О П Р С Т У Ф Х Ц Ч Ш
          0x0430_0438, //  а б в г д е ж з и
          0x043a_0448, //  к л м н о п р с т у ф х ц ч ш
          0x0453_0453, //  ѓ
          0x0455_0455, //  ѕ
          0x0458_045a, //  ј љ њ
          0x045c_045c, //  ќ
          0x045f_045f, //  џ
      },
      13, 62);


  /**
   * <p>The auxiliary characters for the Macedonian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x0400_0400, //  Ѐ
          0x040d_040d, //  Ѝ
          0x0450_0450, //  ѐ
          0x045d_045d, //  ѝ
      },
      4, 4);


  /**
   * <p>The punctuation characters for the Macedonian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x21_21, //  !
          0x27_29, //  ' ( )
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
          0x2013_2014, //  – —
          0x2018_2018, //  ‘
          0x201a_201a, //  ‚
          0x201c_201c, //  “
          0x201e_201e, //  „
          0x2026_2026, //  …
      },
      16, 23);


}
