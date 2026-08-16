/*
 * Copyright © 2021-2026 Evan Toliopoulos (typefactory.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.typefactory.unicode.cldr;

import javax.annotation.processing.Generated;
import org.typefactory.Subset;
import org.typefactory.impl.Factory;

/**
 * Provides Type Factory subsets for the Dzongkha as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Dzongkha language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class dz extends root {

  public dz() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected dz(
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
   * <p>The standard characters for the Dzongkha language as defined by the
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
          0x0f40_0f42, //  ཀ ཁ ག
          0x0f44_0f47, //  ང ཅ ཆ ཇ
          0x0f49_0f49, //  ཉ
          0x0f4f_0f51, //  ཏ ཐ ད
          0x0f53_0f56, //  ན པ ཕ བ
          0x0f58_0f5b, //  མ ཙ ཚ ཛ
          0x0f5d_0f64, //  ཝ ཞ ཟ འ ཡ ར ལ ཤ
          0x0f66_0f68, //  ས ཧ ཨ
          0x0f72_0f72, //  ི
          0x0f74_0f74, //  ུ
          0x0f7a_0f7a, //  ེ
          0x0f7c_0f7c, //  ོ
          0x0f90_0f92, //  ྐ ྑ ྒ
          0x0f94_0f94, //  ྔ
          0x0f97_0f97, //  ྗ
          0x0f99_0f99, //  ྙ
          0x0f9f_0fa1, //  ྟ ྠ ྡ
          0x0fa3_0fa6, //  ྣ ྤ ྥ ྦ
          0x0fa8_0fab, //  ྨ ྩ ྪ ྫ
          0x0fad_0fad, //  ྭ
          0x0fb1_0fb3, //  ྱ ྲ ླ
          0x0fb5_0fb7, //  ྵ ྶ ྷ
      },
      22, 58);


  /**
   * <p>The auxiliary characters for the Dzongkha language as defined by the
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
          0x0f4a_0f4c, //  ཊ ཋ ཌ
          0x0f4e_0f4e, //  ཎ
          0x0f65_0f65, //  ཥ
          0x0f7b_0f7b, //  ཻ
          0x0f7d_0f7e, //  ཽ ཾ
          0x0f80_0f80, //  ྀ
          0x0f84_0f84, //  ྄
          0x0f9a_0f9c, //  ྚ ྛ ྜ
          0x0f9e_0f9e, //  ྞ
          0x0fba_0fbc, //  ྺ ྻ ྼ
      },
      10, 17);


  /**
   * <p>The punctuation characters for the Dzongkha language as defined by the
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
          0x0f04_0f06, //  ༄ ༅ ༆
          0x0f08_0f0a, //  ༈ ༉ ༊
          0x0f0c_0f12, //  ༌ ། ༎ ༏ ༐ ༑ ༒
          0x0f14_0f14, //  ༔
          0x0f34_0f34, //  ༴
          0x0f36_0f36, //  ༶
          0x0f3c_0f3d, //  ༼ ༽
          0x0fbe_0fbf, //  ྾ ྿
          0x0fd0_0fd4, //  ࿐ ࿑ ࿒ ࿓ ࿔
          0x2010_2011, //  ‐ ‑
          0x2013_2014, //  – —
          0x2018_2019, //  ‘ ’
          0x201c_201d, //  “ ”
          0x2020_2021, //  † ‡
          0x2026_2026, //  …
      },
      23, 55);


  /**
   * <p>The decimal digit characters for the Dzongkha language as defined by the
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
          0x0f20_0f29, //  ༠ ༡ ༢ ༣ ༤ ༥ ༦ ༧ ༨ ༩
      },
      2, 20);


}
