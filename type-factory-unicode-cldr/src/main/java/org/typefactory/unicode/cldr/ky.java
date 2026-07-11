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

import java.util.Locale;
import javax.annotation.processing.Generated;
import org.typefactory.Subset;
import org.typefactory.impl.Factory;

/**
 * Provides Type Factory subsets for the Kyrgyz as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Kyrgyz language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class ky extends root {

  public ky() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected ky(
          final Subset standardSubset,
          final Subset auxiliarySubset,
          final Subset punctuationSubset,
          final Subset decimalDigitsSubset) {
    super(
        standardSubset == null ? STANDARD_CHARACTERS_SUBSET : standardSubset,
        auxiliarySubset == null ? AUXILIARY_CHARACTERS_SUBSET : auxiliarySubset,
        punctuationSubset == null ? PUNCTUATION_CHARACTERS_SUBSET : punctuationSubset,
        decimalDigitsSubset == null ? DECIMAL_DIGITS_SUBSET : decimalDigitsSubset);
  }

  /**
   * <p>The Locale represented by this resource bundle for the Kyrgyz language.</p>
   *
   * <p>Language tag: {@code "ky"}</p>
   */
  static final Locale LOCALE = new Locale.Builder()
          .setLanguage("ky")
          .setScript("")
          .setRegion("")
          .setVariant("")
          .build();

  /**
   * <p>The standard characters for the Kyrgyz language as defined by the
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
          0x0401_0401, //  Ё
          0x0410_0411, //  А Б
          0x0413_0423, //  Г Д Е Ж З И Й К Л М Н О П Р С Т У
          0x0425_0425, //  Х
          0x0427_0428, //  Ч Ш
          0x042a_042b, //  Ъ Ы
          0x042d_0431, //  Э Ю Я а б
          0x0433_0443, //  г д е ж з и й к л м н о п р с т у
          0x0445_0445, //  х
          0x0447_0448, //  ч ш
          0x044a_044b, //  ъ ы
          0x044d_044f, //  э ю я
          0x0451_0451, //  ё
          0x04a2_04a3, //  Ң ң
          0x04ae_04af, //  Ү ү
          0x04e8_04e9, //  Ө ө
      },
      16, 62);


  /**
   * <p>The auxiliary characters for the Kyrgyz language as defined by the
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
          0x0412_0412, //  В
          0x0424_0424, //  Ф
          0x0426_0426, //  Ц
          0x0429_0429, //  Щ
          0x042c_042c, //  Ь
          0x0432_0432, //  в
          0x0444_0444, //  ф
          0x0446_0446, //  ц
          0x0449_0449, //  щ
          0x044c_044c, //  ь
      },
      10, 10);


  /**
   * <p>The punctuation characters for the Kyrgyz language as defined by the
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
          0x21_23, //  ! " #
          0x26_2a, //  & ' ( ) *
          0x2c_2f, //  , - . /
          0x3a_3b, //  : ;
          0x3f_40, //  ? @
          0x5b_5b, //  [
          0x5d_5d, //  ]
          0x7b_7b, //  {
          0x7d_7d, //  }
          0xa7_a7, //  §
          0xab_ab, //  «
          0xbb_bb, //  »
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
      19, 32);


  /**
   * <p>The decimal digit characters for the Kyrgyz language as defined by the
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
