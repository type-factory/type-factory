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
 * Provides Type Factory subsets for the Kashmiri as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Kashmiri language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class ks extends root {

  public ks() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected ks(
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
   * <p>The Locale represented by this resource bundle for the Kashmiri language.</p>
   *
   * <p>Language tag: {@code "ks"}</p>
   */
  static final Locale LOCALE = new Locale.Builder()
          .setLanguage("ks")
          .setScript("")
          .setRegion("")
          .setVariant("")
          .build();

  /**
   * <p>The standard characters for the Kashmiri language as defined by the
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
          0x0620_0624, //  ؠ ء آ أ ؤ
          0x0627_0628, //  ا ب
          0x062a_063a, //  ت ث ج ح خ د ذ ر ز س ش ص ض ط ظ ع غ
          0x0641_0642, //  ف ق
          0x0644_0646, //  ل م ن
          0x0648_0648, //  و
          0x0672_0672, //  ٲ
          0x0679_0679, //  ٹ
          0x067e_067e, //  پ
          0x0686_0686, //  چ
          0x0688_0688, //  ڈ
          0x0691_0691, //  ڑ
          0x0698_0698, //  ژ
          0x06a9_06a9, //  ک
          0x06af_06af, //  گ
          0x06ba_06ba, //  ں
          0x06be_06be, //  ھ
          0x06c1_06c1, //  ہ
          0x06c4_06c4, //  ۄ
          0x06c6_06c6, //  ۆ
          0x06cc_06cc, //  ی
          0x06d2_06d2, //  ے
      },
      22, 46);


  /**
   * <p>The auxiliary characters for the Kashmiri language as defined by the
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
          0x0626_0626, //  ئ
          0x064e_0650, //  َ ُ ِ
          0x0654_0657, //  ٔ ٕ ٖ ٗ
          0x065f_065f, //  ٟ
          0x200e_200f, //  ‎ ‏
      },
      5, 11);


  /**
   * <p>The punctuation characters for the Kashmiri language as defined by the
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
          0x2010_2011, //  ‐ ‑
          0x2013_2014, //  – —
          0x2018_2019, //  ‘ ’
          0x201c_201d, //  “ ”
          0x2020_2021, //  † ‡
          0x2026_2026, //  …
          0x2032_2033, //  ′ ″
      },
      15, 32);


  /**
   * <p>The decimal digit characters for the Kashmiri language as defined by the
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
          0x06f0_06f9, //  ۰ ۱ ۲ ۳ ۴ ۵ ۶ ۷ ۸ ۹
      },
      2, 20);


}
