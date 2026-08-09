/*
   Copyright 2021-2026 Evan Toliopoulos (typefactory.org)

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
 * Provides Type Factory subsets for the Greek (Polytonic) as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Greek (Polytonic) language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class el_POLYTON extends el {

  public el_POLYTON() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected el_POLYTON(
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
   * <p>The standard characters for the Greek (Polytonic) language as defined by the
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
          0x0300_0301, //  ̀ ́
          0x0308_0308, //  ̈
          0x0313_0313, //  ̓
          0x0342_0342, //  ͂
          0x0386_0386, //  Ά
          0x0388_038a, //  Έ Ή Ί
          0x038c_038c, //  Ό
          0x038e_03a1, //  Ύ Ώ ΐ Α Β Γ Δ Ε Ζ Η Θ Ι Κ Λ Μ Ν Ξ Ο Π Ρ
          0x03a3_03ce, //  Σ Τ Υ Φ Χ Ψ Ω Ϊ Ϋ ά έ ή ί ΰ α β γ δ ε ζ η θ ι κ λ μ ν ξ ο π
                       //  ρ ς σ τ υ φ χ ψ ω ϊ ϋ ό ύ ώ
          0x1f00_1f15, //  ἀ ἁ ἂ ἃ ἄ ἅ ἆ ἇ Ἀ Ἁ Ἂ Ἃ Ἄ Ἅ Ἆ Ἇ ἐ ἑ ἒ ἓ ἔ ἕ
          0x1f18_1f1d, //  Ἐ Ἑ Ἒ Ἓ Ἔ Ἕ
          0x1f20_1f3f, //  ἠ ἡ ἢ ἣ ἤ ἥ ἦ ἧ Ἠ Ἡ Ἢ Ἣ Ἤ Ἥ Ἦ Ἧ ἰ ἱ ἲ ἳ ἴ ἵ ἶ ἷ Ἰ Ἱ Ἲ Ἳ Ἴ Ἵ Ἶ Ἷ
          0x1f42_1f44, //  ὂ ὃ ὄ
          0x1f4a_1f4c, //  Ὂ Ὃ Ὄ
          0x1f50_1f57, //  ὐ ὑ ὒ ὓ ὔ ὕ ὖ ὗ
          0x1f59_1f59, //  Ὑ
          0x1f5b_1f5b, //  Ὓ
          0x1f5d_1f5d, //  Ὕ
          0x1f5f_1f5f, //  Ὗ
          0x1f62_1f67, //  ὢ ὣ ὤ ὥ ὦ ὧ
          0x1f6a_1f70, //  Ὢ Ὣ Ὤ Ὥ Ὦ Ὧ ὰ
          0x1f72_1f72, //  ὲ
          0x1f74_1f74, //  ὴ
          0x1f76_1f76, //  ὶ
          0x1f78_1f78, //  ὸ
          0x1f7a_1f7a, //  ὺ
          0x1f7c_1f7c, //  ὼ
          0x1fb6_1fb6, //  ᾶ
          0x1fba_1fba, //  Ὰ
          0x1fc6_1fc6, //  ῆ
          0x1fc8_1fc8, //  Ὲ
          0x1fca_1fca, //  Ὴ
          0x1fd2_1fd2, //  ῒ
          0x1fd6_1fd7, //  ῖ ῗ
          0x1fda_1fda, //  Ὶ
          0x1fe2_1fe2, //  ῢ
          0x1fe6_1fe7, //  ῦ ῧ
          0x1fea_1fea, //  Ὺ
          0x1ff6_1ff6, //  ῶ
          0x1ff8_1ff8, //  Ὸ
          0x1ffa_1ffa, //  Ὼ
      },
      41, 187);


  /**
   * <p>The auxiliary characters for the Greek (Polytonic) language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the auxiliary characters
   *    are inherited from the superclass.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.emptySubset();


  /**
   * <p>The punctuation characters for the Greek (Polytonic) language as defined by the
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
   * <p>The decimal digit characters for the Greek (Polytonic) language as defined by the
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
