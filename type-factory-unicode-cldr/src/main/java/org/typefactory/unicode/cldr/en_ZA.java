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
 * Provides Type Factory subsets for the English (South Africa) as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the English (South Africa) language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class en_ZA extends en {

  public en_ZA() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected en_ZA(
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
   * <p>The Locale represented by this resource bundle for the English (South Africa) language.</p>
   *
   * <p>Language tag: {@code "en-ZA"}</p>
   */
  static final Locale LOCALE = new Locale.Builder()
          .setLanguage("en")
          .setScript("")
          .setRegion("ZA")
          .setVariant("")
          .build();

  /**
   * <p>The standard characters for the English (South Africa) language as defined by the
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
   * <p>The auxiliary characters for the English (South Africa) language as defined by the
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
          0xc0_c2, //  À Á Â
          0xc4_cf, //  Ä Å Æ Ç È É Ê Ë Ì Í Î Ï
          0xd1_d4, //  Ñ Ò Ó Ô
          0xd6_d6, //  Ö
          0xd8_dc, //  Ø Ù Ú Û Ü
          0xe0_e2, //  à á â
          0xe4_ef, //  ä å æ ç è é ê ë ì í î ï
          0xf1_f4, //  ñ ò ó ô
          0xf6_f6, //  ö
          0xf8_fc, //  ø ù ú û ü
          0xff_ff, //  ÿ
      },
      new int[]{
          0x0100_0103, //  Ā ā Ă ă
          0x0112_0115, //  Ē ē Ĕ ĕ
          0x012a_012d, //  Ī ī Ĭ ĭ
          0x014c_014f, //  Ō ō Ŏ ŏ
          0x0152_0153, //  Œ œ
          0x0160_0161, //  Š š
          0x016a_016d, //  Ū ū Ŭ ŭ
          0x0178_0178, //  Ÿ
          0x1e12_1e13, //  Ḓ ḓ
          0x1e3c_1e3d, //  Ḽ ḽ
          0x1e44_1e45, //  Ṅ ṅ
          0x1e4a_1e4b, //  Ṋ ṋ
          0x1e70_1e71, //  Ṱ ṱ
      },
      24, 86);


  /**
   * <p>The punctuation characters for the English (South Africa) language as defined by the
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
   * <p>The decimal digit characters for the English (South Africa) language as defined by the
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
      1, 10);


}
