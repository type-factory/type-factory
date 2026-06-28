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
 * Provides Type Factory subsets for the Kabyle language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class kab extends CldrResourceBundle {

  public kab() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Kabyle language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x41_4e, //  A B C D E F G H I J K L M N
          0x50_55, //  P Q R S T U
          0x57_5a, //  W X Y Z
          0x61_6e, //  a b c d e f g h i j k l m n
          0x70_75, //  p q r s t u
          0x77_7a, //  w x y z
      },
        new int[]{
          0x010c_010d, //  Č č
          0x0190_0190, //  Ɛ
          0x0194_0194, //  Ɣ
          0x01e6_01e7, //  Ǧ ǧ
          0x025b_025b, //  ɛ
          0x0263_0263, //  ɣ
          0x1e0c_1e0d, //  Ḍ ḍ
          0x1e24_1e25, //  Ḥ ḥ
          0x1e5a_1e5b, //  Ṛ ṛ
          0x1e62_1e63, //  Ṣ ṣ
          0x1e6c_1e6d, //  Ṭ ṭ
          0x1e92_1e93, //  Ẓ ẓ
      },
      18, 68);


  /**
   * <p>The auxiliary characters for the Kabyle language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x4f_4f, //  O
          0x56_56, //  V
          0x6f_6f, //  o
          0x76_76, //  v
      },
      4, 4);


  /**
   * <p>The punctuation characters for the Kabyle language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x21_21, //  !
          0x28_29, //  ( )
          0x2c_2e, //  , - .
          0x3a_3b, //  : ;
          0x3f_3f, //  ?
          0x5b_5b, //  [
          0x5d_5d, //  ]
          0x7b_7b, //  {
          0x7d_7d, //  }
      },
        new int[]{
          0x2010_2011, //  ‐ ‑
      },
      10, 15);


}
