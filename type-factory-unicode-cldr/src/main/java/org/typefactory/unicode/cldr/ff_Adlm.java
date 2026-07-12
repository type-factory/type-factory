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
 * Provides Type Factory subsets for the Fulah (Adlam) as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Fulah (Adlam) language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class ff_Adlm extends ff {

  public ff_Adlm() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected ff_Adlm(
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
   * <p>The Locale represented by this resource bundle for the Fulah (Adlam) language.</p>
   *
   * <p>Language tag: {@code "ff-Adlm"}</p>
   */
  static final Locale LOCALE = new Locale.Builder()
          .setLanguage("ff")
          .setScript("Adlm")
          .setRegion("")
          .setVariant("")
          .build();

  /**
   * <p>The standard characters for the Fulah (Adlam) language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the standard characters
   *    are inherited from the superclass.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

      new long[]{
          0x0001e900_0001e91bL, //  𞤀 𞤁 𞤂 𞤃 𞤄 𞤅 𞤆 𞤇 𞤈 𞤉 𞤊 𞤋 𞤌 𞤍 𞤎 𞤏 𞤐 𞤑 𞤒 𞤓 𞤔 𞤕 𞤖 𞤗 𞤘 𞤙 𞤚 𞤛
          0x0001e922_0001e93dL, //  𞤢 𞤣 𞤤 𞤥 𞤦 𞤧 𞤨 𞤩 𞤪 𞤫 𞤬 𞤭 𞤮 𞤯 𞤰 𞤱 𞤲 𞤳 𞤴 𞤵 𞤶 𞤷 𞤸 𞤹 𞤺 𞤻 𞤼 𞤽
          0x0001e944_0001e946L, //  𞥄 𞥅 𞥆
          0x0001e94b_0001e94bL, //  𞥋
      },
      4, 60);


  /**
   * <p>The auxiliary characters for the Fulah (Adlam) language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the auxiliary characters
   *    are inherited from the superclass.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

      new long[]{
          0x0001e91c_0001e921L, //  𞤜 𞤝 𞤞 𞤟 𞤠 𞤡
          0x0001e93e_0001e943L, //  𞤾 𞤿 𞥀 𞥁 𞥂 𞥃
      },
      2, 12);


  /**
   * <p>The punctuation characters for the Fulah (Adlam) language as defined by the
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
          0x25_25, //  %
          0x2d_2e, //  - .
      },
      new int[]{
          0x2011_2011, //  ‑
          0x2030_2030, //  ‰
      },
      new long[]{
          0x0001e95e_0001e95fL, //  𞥞 𞥟
      },
      5, 7);


  /**
   * <p>The decimal digit characters for the Fulah (Adlam) language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the decimal digit characters from the {@code <exemplarCharacters type="numbers">}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the decimal digit characters
   *    are inherited from the superclass.</p>
   */
  static final Subset DECIMAL_DIGITS_SUBSET = Factory.rangedSubset(

      new long[]{
          0x0001e950_0001e959L, //  𞥐 𞥑 𞥒 𞥓 𞥔 𞥕 𞥖 𞥗 𞥘 𞥙
      },
      1, 10);


}
