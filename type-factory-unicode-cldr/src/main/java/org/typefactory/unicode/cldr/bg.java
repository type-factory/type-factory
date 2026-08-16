/*
   Copyright 2021-2026 Evan Toliopoulos (typefactory.org)

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
 * Provides Type Factory subsets for the Bulgarian as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Bulgarian language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class bg extends root {

  public bg() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected bg(
          final Subset standardSubset,
          final Subset auxiliarySubset,
          final Subset punctuationSubset,
          final Subset decimalDigitsSubset) {
    super(
        defaultIfNull(standardSubset, STANDARD_CHARACTERS_SUBSET),
        defaultIfNull(auxiliarySubset, AUXILIARY_CHARACTERS_SUBSET),
        defaultIfNull(punctuationSubset, PUNCTUATION_CHARACTERS_SUBSET),
        defaultIfNull(decimalDigitsSubset, DECIMAL_DIGITS_SUBSET));
  }

  /**
   * <p>The standard characters for the Bulgarian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the standard characters
   *    are inherited from the superclass.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

      new int[]{
          0x0410_042a, //  А Б В Г Д Е Ж З И Й К Л М Н О П Р С Т У Ф Х Ц Ч Ш Щ Ъ
          0x042c_042c, //  Ь
          0x042e_044a, //  Ю Я а б в г д е ж з и й к л м н о п р с т у ф х ц ч ш щ ъ
          0x044c_044c, //  ь
          0x044e_044f, //  ю я
      },
      5, 60);


  /**
   * <p>The auxiliary characters for the Bulgarian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the auxiliary characters
   *    are inherited from the superclass.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

      new int[]{
          0x0300_0300, //  ̀
          0x0400_0401, //  Ѐ Ё
          0x040d_040d, //  Ѝ
          0x0410_0410, //  А
          0x041e_041e, //  О
          0x0423_0423, //  У
          0x042a_042b, //  Ъ Ы
          0x042d_0430, //  Э Ю Я а
          0x043e_043e, //  о
          0x0443_0443, //  у
          0x044a_044b, //  ъ ы
          0x044d_0451, //  э ю я ѐ ё
          0x045d_045d, //  ѝ
          0x0462_0463, //  Ѣ ѣ
          0x046a_046b, //  Ѫ ѫ
      },
      15, 27);


  /**
   * <p>The punctuation characters for the Bulgarian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the punctuation characters
   *    are inherited from the superclass.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

      new char[]{
          0x21_22, //  ! "
          0x27_2a, //  ' ( ) *
          0x2c_2f, //  , - . /
          0x3a_3b, //  : ;
          0x3f_40, //  ? @
          0x5b_5b, //  [
          0x5d_5d, //  ]
          0xa7_a7, //  §
      },
      new int[]{
          0x2010_2011, //  ‐ ‑
          0x2013_2014, //  – —
          0x2018_2018, //  ‘
          0x201a_201a, //  ‚
          0x201c_201c, //  “
          0x201e_201e, //  „
          0x2026_2026, //  …
          0x2033_2033, //  ″
          0x2116_2116, //  №
      },
      17, 28);


  /**
   * <p>The decimal digit characters for the Bulgarian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the decimal digit characters from the {@code <exemplarCharacters type="numbers">}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the decimal digit characters
   *    are inherited from the superclass.</p>
   */
  static final Subset DECIMAL_DIGITS_SUBSET = Factory.rangedSubset(

      new char[]{
          0x30_39, //  0 1 2 3 4 5 6 7 8 9
      },
      1, 10);


}
