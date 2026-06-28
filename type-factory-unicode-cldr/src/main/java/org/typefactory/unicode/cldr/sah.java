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
 * Provides Type Factory subsets for the Yakut language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class sah extends AbstractCldrResourceBundle {

  public sah() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Yakut language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x0410_0411, //  А Б
          0x0413_0414, //  Г Д
          0x0418_0423, //  И Й К Л М Н О П Р С Т У
          0x0425_0425, //  Х
          0x0427_0427, //  Ч
          0x042b_042b, //  Ы
          0x042d_042d, //  Э
          0x0430_0431, //  а б
          0x0433_0434, //  г д
          0x0438_0443, //  и й к л м н о п р с т у
          0x0445_0445, //  х
          0x0447_0447, //  ч
          0x044b_044b, //  ы
          0x044d_044d, //  э
          0x0494_0495, //  Ҕ ҕ
          0x04a4_04a5, //  Ҥ ҥ
          0x04ae_04af, //  Ү ү
          0x04ba_04bb, //  Һ һ
          0x04e8_04e9, //  Ө ө
      },
      19, 50);


  /**
   * <p>The auxiliary characters for the Yakut language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x0401_0401, //  Ё
          0x0412_0412, //  В
          0x0415_0417, //  Е Ж З
          0x0424_0424, //  Ф
          0x0426_0426, //  Ц
          0x0428_042a, //  Ш Щ Ъ
          0x042c_042c, //  Ь
          0x042e_042f, //  Ю Я
          0x0432_0432, //  в
          0x0435_0437, //  е ж з
          0x0444_0444, //  ф
          0x0446_0446, //  ц
          0x0448_044a, //  ш щ ъ
          0x044c_044c, //  ь
          0x044e_044f, //  ю я
          0x0451_0451, //  ё
      },
      16, 26);


  /**
   * <p>The punctuation characters for the Yakut language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x3a_3a, //  :
      },
      1, 1);


}
