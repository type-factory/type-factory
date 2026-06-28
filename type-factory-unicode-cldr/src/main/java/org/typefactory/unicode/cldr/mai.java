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
 * Provides Type Factory subsets for the Maithili language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class mai extends CldrResourceBundle {

  public mai() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Maithili language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x0902_0903, //  ं ः
          0x0915_0918, //  क ख ग घ
          0x091a_0928, //  च छ ज झ ञ ट ठ ड ढ ण त थ द ध न
          0x092a_0930, //  प फ ब भ म य र
          0x0932_0932, //  ल
          0x0935_0939, //  व श ष स ह
          0x093c_093c, //  ़
          0x093e_0942, //  ा ि ी ु ू
          0x0947_0948, //  े ै
          0x094b_094c, //  ो ौ
      },
      10, 44);


  /**
   * <p>The auxiliary characters for the Maithili language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x0905_090c, //  अ आ इ ई उ ऊ ऋ ऌ
          0x090f_0910, //  ए ऐ
          0x0913_0914, //  ओ औ
          0x0961_0961, //  ॡ
      },
      4, 13);


  /**
   * <p>The punctuation characters for the Maithili language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x21_23, //  ! " #
          0x26_2f, //  & ' ( ) * + , - . /
          0x3a_3b, //  : ;
          0x3f_40, //  ? @
          0x5b_5d, //  [ \ ]
          0x5f_60, //  _ `
          0x7b_7e, //  { | } ~
          0xa7_a7, //  §
      },
        new int[]{
          0x2011_2011, //  ‑
          0x2013_2014, //  – —
          0x2018_2019, //  ‘ ’
          0x201c_201d, //  “ ”
          0x2026_2026, //  …
          0x2032_2033, //  ′ ″
      },
      14, 37);


}
