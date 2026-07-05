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
 * Provides Type Factory subsets for the Yoruba language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class yo_BJ extends AbstractCldrResourceBundle {

  public yo_BJ() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Yoruba language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x41_42, //  A B
          0x44_50, //  D E F G H I J K L M N O P
          0x52_55, //  R S T U
          0x57_57, //  W
          0x59_59, //  Y
          0x61_62, //  a b
          0x64_70, //  d e f g h i j k l m n o p
          0x72_75, //  r s t u
          0x77_77, //  w
          0x79_79, //  y
          0x7b_7b, //  {
          0x7d_7d, //  }
          0xc0_c1, //  À Á
          0xc8_c9, //  È É
          0xcc_cd, //  Ì Í
          0xd2_d3, //  Ò Ó
          0xd9_da, //  Ù Ú
          0xe0_e1, //  à á
          0xe8_e9, //  è é
          0xec_ed, //  ì í
          0xf2_f3, //  ò ó
          0xf9_fa, //  ù ú
      },
        new int[]{
          0x0186_0186, //  Ɔ
          0x0190_0190, //  Ɛ
          0x0254_0254, //  ɔ
          0x025b_025b, //  ɛ
          0x0300_0301, //  ̀ ́
      },
      27, 70);


  /**
   * <p>The auxiliary characters for the Yoruba language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.emptySubset();


  /**
   * <p>The punctuation characters for the Yoruba language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.emptySubset();


}
