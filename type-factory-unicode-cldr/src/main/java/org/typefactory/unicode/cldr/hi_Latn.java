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
 * Provides Type Factory subsets for the Hindi (Latin) as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Hindi (Latin) language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class hi_Latn extends hi {

  public hi_Latn() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected hi_Latn(
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
   * <p>The Locale represented by this resource bundle for the Hindi (Latin) language.</p>
   *
   * <p>Language tag: {@code "hi-Latn"}</p>
   */
  static final Locale LOCALE = new Locale.Builder()
          .setLanguage("hi")
          .setScript("Latn")
          .setRegion("")
          .setVariant("")
          .build();

  /**
   * <p>The standard characters for the Hindi (Latin) language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the standard characters
   *    are inherited from the superclass.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = null;


  /**
   * <p>The auxiliary characters for the Hindi (Latin) language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the auxiliary characters
   *    are inherited from the superclass.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

      new char[]{
          0x4c_4d, //  L M
          0x52_52, //  R
          0x6c_6d, //  l m
          0x72_72, //  r
          0x7b_7b, //  {
          0x7d_7d, //  }
          0xd1_d1, //  Ñ
          0xf1_f1, //  ñ
      },
      new int[]{
          0x0100_0101, //  Ā ā
          0x0112_0115, //  Ē ē Ĕ ĕ
          0x012a_012b, //  Ī ī
          0x014c_014f, //  Ō ō Ŏ ŏ
          0x015a_015b, //  Ś ś
          0x016a_016b, //  Ū ū
          0x0304_0304, //  ̄
          0x0310_0310, //  ̐
          0x0325_0325, //  ̥
          0x1e0c_1e0d, //  Ḍ ḍ
          0x1e24_1e25, //  Ḥ ḥ
          0x1e36_1e37, //  Ḷ ḷ
          0x1e40_1e41, //  Ṁ ṁ
          0x1e44_1e47, //  Ṅ ṅ Ṇ ṇ
          0x1e5a_1e5b, //  Ṛ ṛ
          0x1e62_1e63, //  Ṣ ṣ
          0x1e6c_1e6d, //  Ṭ ṭ
      },
      25, 47);


  /**
   * <p>The punctuation characters for the Hindi (Latin) language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the punctuation characters
   *    are inherited from the superclass.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = null;


  /**
   * <p>The decimal digit characters for the Hindi (Latin) language as defined by the
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
