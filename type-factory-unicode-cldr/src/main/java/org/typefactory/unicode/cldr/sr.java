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
 * Provides Type Factory subsets for the Serbian as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Serbian language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class sr extends root {

  public sr() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected sr(
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
   * <p>The Locale represented by this resource bundle for the Serbian language.</p>
   *
   * <p>Language tag: {@code "sr"}</p>
   */
  static final Locale LOCALE = new Locale.Builder()
          .setLanguage("sr")
          .setScript("")
          .setRegion("")
          .setVariant("")
          .build();

  /**
   * <p>The standard characters for the Serbian language as defined by the
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
          0x0402_0402, //  Ђ
          0x0408_040b, //  Ј Љ Њ Ћ
          0x040f_0418, //  Џ А Б В Г Д Е Ж З И
          0x041a_0428, //  К Л М Н О П Р С Т У Ф Х Ц Ч Ш
          0x0430_0438, //  а б в г д е ж з и
          0x043a_0448, //  к л м н о п р с т у ф х ц ч ш
          0x0452_0452, //  ђ
          0x0458_045b, //  ј љ њ ћ
          0x045f_045f, //  џ
      },
      9, 60);


  /**
   * <p>The auxiliary characters for the Serbian language as defined by the
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
          0x0302_0302, //  ̂
          0x0410_0410, //  А
          0x0415_0415, //  Е
          0x0418_0418, //  И
          0x041e_041e, //  О
          0x0423_0423, //  У
          0x0430_0430, //  а
          0x0435_0435, //  е
          0x0438_0438, //  и
          0x043e_043e, //  о
          0x0443_0443, //  у
      },
      11, 11);


  /**
   * <p>The punctuation characters for the Serbian language as defined by the
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
          0x21_21, //  !
          0x23_23, //  #
          0x28_2a, //  ( ) *
          0x2c_2f, //  , - . /
          0x3a_3b, //  : ;
          0x3f_3f, //  ?
          0x5b_5b, //  [
          0x5d_5d, //  ]
          0x7b_7b, //  {
          0x7d_7d, //  }
      },
      new int[]{
          0x2010_2011, //  ‐ ‑
          0x2013_2013, //  –
          0x2019_2019, //  ’
          0x201d_201e, //  ” „
          0x2026_2026, //  …
      },
      15, 23);


  /**
   * <p>The decimal digit characters for the Serbian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the decimal digit characters from the {@code <exemplarCharacters type="numbers">}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the decimal digit characters
   *    are inherited from the superclass.</p>
   */
  static final Subset DECIMAL_DIGITS_SUBSET = null;


}
