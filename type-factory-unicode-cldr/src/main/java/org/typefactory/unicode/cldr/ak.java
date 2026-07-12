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
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.typefactory.Subset;
import org.typefactory.impl.Factory;

/**
 * Provides Type Factory subsets for the Akan as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Akan language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class ak extends root {

  public ak() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected ak(
          final Subset standardSubset,
          final Subset auxiliarySubset,
          final Subset punctuationSubset,
          final Subset decimalDigitsSubset) {
    super(
        Objects.requireNonNullElse(standardSubset, STANDARD_CHARACTERS_SUBSET),
        Objects.requireNonNullElse(auxiliarySubset, AUXILIARY_CHARACTERS_SUBSET),
        Objects.requireNonNullElse(punctuationSubset, PUNCTUATION_CHARACTERS_SUBSET),
        Objects.requireNonNullElse(decimalDigitsSubset, DECIMAL_DIGITS_SUBSET));
  }

  /**
   * <p>The Locale represented by this resource bundle for the Akan language.</p>
   *
   * <p>Language tag: {@code "ak"}</p>
   */
  static final Locale LOCALE = new Locale.Builder()
          .setLanguage("ak")
          .setScript("")
          .setRegion("")
          .setVariant("")
          .build();

  /**
   * <p>The standard characters for the Akan language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the standard characters
   *    are inherited from the superclass.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

      new char[]{
          0x41_42, //  A B
          0x44_49, //  D E F G H I
          0x4b_50, //  K L M N O P
          0x52_55, //  R S T U
          0x57_57, //  W
          0x59_59, //  Y
          0x61_62, //  a b
          0x64_69, //  d e f g h i
          0x6b_70, //  k l m n o p
          0x72_75, //  r s t u
          0x77_77, //  w
          0x79_79, //  y
      },
      new int[]{
          0x0186_0186, //  Ɔ
          0x0190_0190, //  Ɛ
          0x0254_0254, //  ɔ
          0x025b_025b, //  ɛ
      },
      16, 44);


  /**
   * <p>The auxiliary characters for the Akan language as defined by the
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
          0x43_43, //  C
          0x4a_4a, //  J
          0x51_51, //  Q
          0x56_56, //  V
          0x5a_5a, //  Z
          0x63_63, //  c
          0x6a_6a, //  j
          0x71_71, //  q
          0x76_76, //  v
          0x7a_7a, //  z
          0xc1_c1, //  Á
          0xc3_c4, //  Ã Ä
          0xc9_c9, //  É
          0xcb_cb, //  Ë
          0xcd_cd, //  Í
          0xd3_d3, //  Ó
          0xd6_d6, //  Ö
          0xdc_dc, //  Ü
          0xe1_e1, //  á
          0xe3_e4, //  ã ä
          0xe9_e9, //  é
          0xeb_eb, //  ë
          0xed_ed, //  í
          0xf3_f3, //  ó
          0xf6_f6, //  ö
          0xfc_fc, //  ü
      },
      26, 28);


  /**
   * <p>The punctuation characters for the Akan language as defined by the
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
   * <p>The decimal digit characters for the Akan language as defined by the
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
