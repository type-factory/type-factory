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
 * Provides Type Factory subsets for the Nepali as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Nepali language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class ne extends root {

  public ne() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected ne(
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
   * <p>The Locale represented by this resource bundle for the Nepali language.</p>
   *
   * <p>Language tag: {@code "ne"}</p>
   */
  static final Locale LOCALE = new Locale.Builder()
          .setLanguage("ne")
          .setScript("")
          .setRegion("")
          .setVariant("")
          .build();

  /**
   * <p>The standard characters for the Nepali language as defined by the
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
          0x0901_0903, //  ँ ं ः
          0x0905_090d, //  अ आ इ ई उ ऊ ऋ ऌ ऍ
          0x090f_0911, //  ए ऐ ऑ
          0x0913_0928, //  ओ औ क ख ग घ ङ च छ ज झ ञ ट ठ ड ढ ण त थ द ध न
          0x092a_0930, //  प फ ब भ म य र
          0x0932_0933, //  ल ळ
          0x0935_0939, //  व श ष स ह
          0x093c_0943, //  ़ ऽ ा ि ी ु ू ृ
          0x0945_0945, //  ॅ
          0x0947_0949, //  े ै ॉ
          0x094b_094d, //  ो ौ ्
          0x0950_0950, //  ॐ
      },
      12, 67);


  /**
   * <p>The auxiliary characters for the Nepali language as defined by the
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
          0x0944_0944, //  ॄ
          0x200c_200d, //  ‌ ‍
      },
      2, 3);


  /**
   * <p>The punctuation characters for the Nepali language as defined by the
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
          0x27_29, //  ' ( )
          0x2c_2d, //  , -
          0x3b_3b, //  ;
          0x3f_3f, //  ?
          0x5b_5b, //  [
          0x5d_5d, //  ]
          0x7b_7b, //  {
          0x7d_7d, //  }
      },
      new int[]{
          0x0964_0964, //  ।
          0x2011_2011, //  ‑
          0x2014_2014, //  —
          0x2018_2019, //  ‘ ’
          0x201c_201d, //  “ ”
      },
      14, 20);


  /**
   * <p>The decimal digit characters for the Nepali language as defined by the
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
