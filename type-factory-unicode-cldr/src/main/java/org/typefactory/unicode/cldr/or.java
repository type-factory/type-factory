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
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.typefactory.Subset;
import org.typefactory.impl.Factory;

/**
 * Provides Type Factory subsets for the Odia as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Odia language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class or extends root {

  public or() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected or(
          final Subset standardSubset,
          final Subset auxiliarySubset,
          final Subset punctuationSubset,
          final Subset decimalDigitsSubset) {
    super(
        Objects.requireNonNullElse(standardSubset, STANDARD_CHARACTERS_SUBSET),
        Objects.requireNonNullElse(auxiliarySubset, AUXILIARY_CHARACTERS_SUBSET),
        Objects.requireNonNullElse(punctuationSubset, PUNCTUATION_CHARACTERS_SUBSET),
        Objects.requireNonNullElse(decimalDigitsSubset, DECIMAL_DIGITS_SUBSET));
  }

  /**
   * <p>The Locale represented by this resource bundle for the Odia language.</p>
   *
   * <p>Language tag: {@code "or"}</p>
   */
  static final Locale LOCALE = new Locale.Builder()
          .setLanguage("or")
          .setScript("")
          .setRegion("")
          .setVariant("")
          .build();

  /**
   * <p>The standard characters for the Odia language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the standard characters
   *    are inherited from the superclass.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

      new char[]{
          0x7b_7b, //  {
          0x7d_7d, //  }
      },
      new int[]{
          0x0b01_0b03, //  ଁ ଂ ଃ
          0x0b05_0b0b, //  ଅ ଆ ଇ ଈ ଉ ଊ ଋ
          0x0b0f_0b10, //  ଏ ଐ
          0x0b13_0b28, //  ଓ ଔ କ ଖ ଗ ଘ ଙ ଚ ଛ ଜ ଝ ଞ ଟ ଠ ଡ ଢ ଣ ତ ଥ ଦ ଧ ନ
          0x0b2a_0b30, //  ପ ଫ ବ ଭ ମ ଯ ର
          0x0b32_0b33, //  ଲ ଳ
          0x0b35_0b39, //  ଵ ଶ ଷ ସ ହ
          0x0b3c_0b3c, //  ଼
          0x0b3e_0b43, //  ା ି ୀ ୁ ୂ ୃ
          0x0b47_0b48, //  େ ୈ
          0x0b4b_0b4d, //  ୋ ୌ ୍
          0x0b5f_0b5f, //  ୟ
          0x0b71_0b71, //  ୱ
      },
      15, 64);


  /**
   * <p>The auxiliary characters for the Odia language as defined by the
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
          0x200c_200d, //  ‌ ‍
      },
      1, 2);


  /**
   * <p>The punctuation characters for the Odia language as defined by the
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
          0xa7_a7, //  §
      },
      new int[]{
          0x0964_0964, //  ।
          0x2010_2011, //  ‐ ‑
          0x2013_2014, //  – —
          0x2018_2019, //  ‘ ’
          0x201c_201d, //  “ ”
          0x2020_2021, //  † ‡
          0x2026_2026, //  …
          0x2032_2033, //  ′ ″
      },
      16, 33);


  /**
   * <p>The decimal digit characters for the Odia language as defined by the
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
      new int[]{
          0x0b66_0b6f, //  ୦ ୧ ୨ ୩ ୪ ୫ ୬ ୭ ୮ ୯
      },
      2, 20);


}
