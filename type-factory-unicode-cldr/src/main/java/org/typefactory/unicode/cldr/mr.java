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
 * Provides Type Factory subsets for the Marathi as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Marathi language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class mr extends root {

  public mr() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected mr(
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
   * <p>The standard characters for the Marathi language as defined by the
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
          0x0902_0903, //  ं ः
          0x0905_090c, //  अ आ इ ई उ ऊ ऋ ऌ
          0x090f_0911, //  ए ऐ ऑ
          0x0913_0928, //  ओ औ क ख ग घ ङ च छ ज झ ञ ट ठ ड ढ ण त थ द ध न
          0x092a_0930, //  प फ ब भ म य र
          0x0932_0933, //  ल ळ
          0x0935_0939, //  व श ष स ह
          0x093d_0943, //  ऽ ा ि ी ु ू ृ
          0x0945_0945, //  ॅ
          0x0947_0949, //  े ै ॉ
          0x094b_094d, //  ो ौ ्
          0x0950_0950, //  ॐ
      },
      12, 64);


  /**
   * <p>The auxiliary characters for the Marathi language as defined by the
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
          0x0901_0901, //  ँ
          0x090d_090d, //  ऍ
          0x0931_0931, //  ऱ
          0x093c_093c, //  ़
          0x0944_0944, //  ॄ
          0x200c_200d, //  ‌ ‍
      },
      6, 7);


  /**
   * <p>The punctuation characters for the Marathi language as defined by the
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
      },
      new int[]{
          0x2010_2011, //  ‐ ‑
          0x2013_2014, //  – —
          0x2018_2019, //  ‘ ’
          0x201c_201d, //  “ ”
          0x2026_2026, //  …
          0x2032_2033, //  ′ ″
      },
      13, 29);


  /**
   * <p>The decimal digit characters for the Marathi language as defined by the
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
          0x0966_096f, //  ० १ २ ३ ४ ५ ६ ७ ८ ९
      },
      2, 20);


}
