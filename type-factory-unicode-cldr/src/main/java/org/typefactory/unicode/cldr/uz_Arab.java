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
 * Provides Type Factory subsets for the Uzbek (Arabic) as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Uzbek (Arabic) language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class uz_Arab extends uz {

  public uz_Arab() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected uz_Arab(
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
   * <p>The Locale represented by this resource bundle for the Uzbek (Arabic) language.</p>
   *
   * <p>Language tag: {@code "uz-Arab"}</p>
   */
  static final Locale LOCALE = new Locale.Builder()
          .setLanguage("uz")
          .setScript("Arab")
          .setRegion("")
          .setVariant("")
          .build();

  /**
   * <p>The standard characters for the Uzbek (Arabic) language as defined by the
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
          0x0621_0624, //  ء آ أ ؤ
          0x0626_063a, //  ئ ا ب ة ت ث ج ح خ د ذ ر ز س ش ص ض ط ظ ع غ
          0x0641_0642, //  ف ق
          0x0644_0648, //  ل م ن ه و
          0x064b_0652, //  ً ٌ ٍ َ ُ ِ ّ ْ
          0x0654_0654, //  ٔ
          0x0670_0670, //  ٰ
          0x067e_067e, //  پ
          0x0686_0686, //  چ
          0x0698_0698, //  ژ
          0x06a9_06a9, //  ک
          0x06af_06af, //  گ
          0x06c7_06c7, //  ۇ
          0x06c9_06c9, //  ۉ
          0x06cc_06cc, //  ی
      },
      15, 50);


  /**
   * <p>The auxiliary characters for the Uzbek (Arabic) language as defined by the
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
          0x064a_064a, //  ي
          0x067c_067c, //  ټ
          0x0681_0681, //  ځ
          0x0685_0685, //  څ
          0x0689_0689, //  ډ
          0x0693_0693, //  ړ
          0x0696_0696, //  ږ
          0x069a_069a, //  ښ
          0x06ab_06ab, //  ګ
          0x06bc_06bc, //  ڼ
          0x06cd_06cd, //  ۍ
          0x06d0_06d0, //  ې
          0x200c_200f, //  ‌ ‍ ‎ ‏
      },
      13, 16);


  /**
   * <p>The punctuation characters for the Uzbek (Arabic) language as defined by the
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
   * <p>The decimal digit characters for the Uzbek (Arabic) language as defined by the
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
