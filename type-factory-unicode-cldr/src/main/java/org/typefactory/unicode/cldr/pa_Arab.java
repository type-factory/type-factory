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
 * Provides Type Factory subsets for the Punjabi language (Arabic script) as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class pa_Arab extends AbstractCldrResourceBundle {

  public pa_Arab() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Punjabi language (Arabic script) as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x0621_0622, //  ء آ
          0x0624_0624, //  ؤ
          0x0626_0628, //  ئ ا ب
          0x062a_063a, //  ت ث ج ح خ د ذ ر ز س ش ص ض ط ظ ع غ
          0x0641_0642, //  ف ق
          0x0644_0648, //  ل م ن ه و
          0x064f_064f, //  ُ
          0x0679_0679, //  ٹ
          0x067e_067e, //  پ
          0x0686_0686, //  چ
          0x0688_0688, //  ڈ
          0x0691_0691, //  ڑ
          0x0698_0698, //  ژ
          0x06a9_06a9, //  ک
          0x06af_06af, //  گ
          0x06ba_06ba, //  ں
          0x06be_06be, //  ھ
          0x06c1_06c1, //  ہ
          0x06cc_06cc, //  ی
          0x06d2_06d2, //  ے
      },
      20, 44);


  /**
   * <p>The auxiliary characters for the Punjabi language (Arabic script) as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x0623_0623, //  أ
          0x0629_0629, //  ة
          0x067a_067d, //  ٺ ٻ ټ ٽ
          0x200e_200f, //  ‎ ‏
      },
      4, 8);


  /**
   * <p>The punctuation characters for the Punjabi language (Arabic script) as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.emptySubset();


}
