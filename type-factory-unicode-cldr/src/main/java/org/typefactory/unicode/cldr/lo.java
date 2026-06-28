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
 * Provides Type Factory subsets for the Lao language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class lo extends AbstractCldrResourceBundle {

  public lo() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Lao language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x0e81_0e82, //  ກ ຂ
          0x0e84_0e84, //  ຄ
          0x0e87_0e88, //  ງ ຈ
          0x0e8a_0e8a, //  ຊ
          0x0e8d_0e8d, //  ຍ
          0x0e94_0e97, //  ດ ຕ ຖ ທ
          0x0e99_0e9f, //  ນ ບ ປ ຜ ຝ ພ ຟ
          0x0ea1_0ea3, //  ມ ຢ ຣ
          0x0ea5_0ea5, //  ລ
          0x0ea7_0ea7, //  ວ
          0x0eaa_0eab, //  ສ ຫ
          0x0ead_0eb9, //  ອ ຮ ຯ ະ ັ າ ຳ ິ ີ ຶ ື ຸ ູ
          0x0ebb_0ebd, //  ົ ຼ ຽ
          0x0ec0_0ec4, //  ເ ແ ໂ ໃ ໄ
          0x0ec6_0ec6, //  ໆ
          0x0ec8_0ecd, //  ່ ້ ໊ ໋ ໌ ໍ
          0x0edc_0edd, //  ໜ ໝ
      },
      17, 55);


  /**
   * <p>The auxiliary characters for the Lao language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x0ed0_0ed9, //  ໐ ໑ ໒ ໓ ໔ ໕ ໖ ໗ ໘ ໙
          0x200b_200b, //  ​
      },
      2, 11);


  /**
   * <p>The punctuation characters for the Lao language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
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


}
