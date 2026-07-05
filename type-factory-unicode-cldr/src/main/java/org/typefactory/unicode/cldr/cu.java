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
 * Provides Type Factory subsets for the Church Slavic language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class cu extends root {

  public cu() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  protected cu(
          final Subset standardSubset,
          final Subset auxiliarySubset,
          final Subset punctuationSubset) {
    super(
        standardSubset == null ? STANDARD_CHARACTERS_SUBSET : standardSubset,
        auxiliarySubset == null ? AUXILIARY_CHARACTERS_SUBSET : auxiliarySubset,
        punctuationSubset == null ? PUNCTUATION_CHARACTERS_SUBSET : punctuationSubset);
  }

  /**
   * <p>The standard characters for the Church Slavic language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x0404_0407, //  Є Ѕ І Ї
          0x0410_042c, //  А Б В Г Д Е Ж З И Й К Л М Н О П Р С Т У Ф Х Ц Ч Ш Щ Ъ Ы Ь
          0x042e_042e, //  Ю
          0x0430_044c, //  а б в г д е ж з и й к л м н о п р с т у ф х ц ч ш щ ъ ы ь
          0x044e_044e, //  ю
          0x0454_0457, //  є ѕ і ї
          0x0460_0463, //  Ѡ ѡ Ѣ ѣ
          0x0466_0467, //  Ѧ ѧ
          0x046a_046b, //  Ѫ ѫ
          0x046e_0477, //  Ѯ ѯ Ѱ ѱ Ѳ ѳ Ѵ ѵ Ѷ ѷ
          0x047a_047f, //  Ѻ ѻ Ѽ ѽ Ѿ ѿ
          0x0482_0483, //  ҂ ҃
          0x0487_0487, //  ҇
          0x2de0_2dea, //  ⷠ ⷡ ⷢ ⷣ ⷤ ⷥ ⷦ ⷧ ⷨ ⷩ ⷪ
          0x2dec_2ded, //  ⷬ ⷭ
          0x2def_2def, //  ⷯ
          0x2df1_2df1, //  ⷱ
          0x2df4_2df4, //  ⷴ
          0x2e2f_2e2f, //  ⸯ
          0xa640_a641, //  Ꙁ ꙁ
          0xa64a_a64d, //  Ꙋ ꙋ Ꙍ ꙍ
          0xa656_a657, //  Ꙗ ꙗ
          0xa67d_a67d, //  ꙽
          0xa67f_a67f, //  ꙿ
      },
      24, 122);


  /**
   * <p>The auxiliary characters for the Church Slavic language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x040f_040f, //  Џ
          0x045f_045f, //  џ
          0x0464_0465, //  Ѥ ѥ
          0x0468_0469, //  Ѩ ѩ
          0x046c_046d, //  Ѭ ѭ
          0x0480_0481, //  Ҁ ҁ
          0x0484_0484, //  ҄
          0x0488_0489, //  ҈ ҉
          0x04a4_04a5, //  Ҥ ҥ
          0x200c_200d, //  ‌ ‍
          0x2deb_2deb, //  ⷫ
          0x2dee_2dee, //  ⷮ
          0x2df0_2df0, //  ⷰ
          0x2df2_2df3, //  ⷲ ⷳ
          0x2df5_2dff, //  ⷵ ⷶ ⷷ ⷸ ⷹ ⷺ ⷻ ⷼ ⷽ ⷾ ⷿ
          0xa642_a649, //  Ꙃ ꙃ Ꙅ ꙅ Ꙇ ꙇ Ꙉ ꙉ
          0xa64e_a655, //  Ꙏ ꙏ Ꙑ ꙑ Ꙓ ꙓ Ꙕ ꙕ
          0xa658_a672, //  Ꙙ ꙙ Ꙛ ꙛ Ꙝ ꙝ Ꙟ ꙟ Ꙡ ꙡ Ꙣ ꙣ Ꙥ ꙥ Ꙧ ꙧ Ꙩ ꙩ Ꙫ ꙫ Ꙭ ꙭ ꙮ ꙯ ꙰ ꙱ ꙲
          0xa674_a67b, //  ꙴ ꙵ ꙶ ꙷ ꙸ ꙹ ꙺ ꙻ
          0xa698_a69b, //  Ꚙ ꚙ Ꚛ ꚛ
          0xa69e_a69f, //  ꚞ ꚟ
          0xfe2e_fe2f, //  ︮ ︯
      },
      22, 92);


  /**
   * <p>The punctuation characters for the Church Slavic language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x21_21, //  !
          0x28_29, //  ( )
          0x2c_2f, //  , - . /
          0x3a_3b, //  : ;
          0x3f_3f, //  ?
          0x5f_5f, //  _
      },
        new int[]{
          0x2010_2011, //  ‐ ‑
          0x2013_2014, //  – —
          0x2e43_2e43, //  ⹃
          0xa673_a673, //  ꙳
          0xa67e_a67e, //  ꙾
      },
      11, 18);


}
