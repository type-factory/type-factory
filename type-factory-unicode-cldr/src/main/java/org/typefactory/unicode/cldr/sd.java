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
 * Provides Type Factory subsets for the Sindhi as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Sindhi language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class sd extends root {

  public sd() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected sd(
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
   * <p>The Locale represented by this resource bundle for the Sindhi language.</p>
   *
   * <p>Language tag: {@code "sd"}</p>
   */
  static final Locale LOCALE = new Locale.Builder()
          .setLanguage("sd")
          .setScript("")
          .setRegion("")
          .setVariant("")
          .build();

  /**
   * <p>The standard characters for the Sindhi language as defined by the
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
          0x0621_0622, //  ء آ
          0x0627_0628, //  ا ب
          0x062a_063a, //  ت ث ج ح خ د ذ ر ز س ش ص ض ط ظ ع غ
          0x0641_0642, //  ف ق
          0x0644_0648, //  ل م ن ه و
          0x064a_064a, //  ي
          0x067a_067b, //  ٺ ٻ
          0x067d_0680, //  ٽ پ ٿ ڀ
          0x0683_0684, //  ڃ ڄ
          0x0686_0687, //  چ ڇ
          0x068a_068a, //  ڊ
          0x068c_068d, //  ڌ ڍ
          0x068f_068f, //  ڏ
          0x0699_0699, //  ڙ
          0x06a6_06a6, //  ڦ
          0x06a9_06aa, //  ک ڪ
          0x06af_06af, //  گ
          0x06b1_06b1, //  ڱ
          0x06b3_06b3, //  ڳ
          0x06bb_06bb, //  ڻ
          0x06be_06be, //  ھ
      },
      21, 52);


  /**
   * <p>The auxiliary characters for the Sindhi language as defined by the
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
          0x0626_0626, //  ئ
          0x064e_0650, //  َ ُ ِ
      },
      2, 4);


  /**
   * <p>The punctuation characters for the Sindhi language as defined by the
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
          0x28_29, //  ( )
          0x2d_2d, //  -
          0x2f_2f, //  /
          0x3a_3a, //  :
          0x5b_5b, //  [
          0x5d_5d, //  ]
      },
      new int[]{
          0x060c_060c, //  ،
          0x061b_061b, //  ؛
          0x061f_061f, //  ؟
          0x066d_066d, //  ٭
          0x2011_2011, //  ‑
          0x2018_2019, //  ‘ ’
          0x201d_201d, //  ”
          0x2026_2026, //  …
      },
      15, 18);


  /**
   * <p>The decimal digit characters for the Sindhi language as defined by the
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
