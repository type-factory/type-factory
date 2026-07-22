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
 * Provides Type Factory subsets for the Western Frisian as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Western Frisian language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class fy extends root {

  public fy() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected fy(
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
   * <p>The standard characters for the Western Frisian language as defined by the
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
          0x41_50, //  A B C D E F G H I J K L M N O P
          0x52_57, //  R S T U V W
          0x59_5a, //  Y Z
          0x61_70, //  a b c d e f g h i j k l m n o p
          0x72_77, //  r s t u v w
          0x79_7a, //  y z
          0xc0_c2, //  À Á Â
          0xc4_c4, //  Ä
          0xc8_cb, //  È É Ê Ë
          0xcd_cd, //  Í
          0xcf_cf, //  Ï
          0xd3_d4, //  Ó Ô
          0xd6_d6, //  Ö
          0xda_dd, //  Ú Û Ü Ý
          0xe0_e2, //  à á â
          0xe4_e4, //  ä
          0xe8_eb, //  è é ê ë
          0xed_ed, //  í
          0xef_ef, //  ï
          0xf3_f4, //  ó ô
          0xf6_f6, //  ö
          0xfa_fd, //  ú û ü ý
      },
      new int[]{
          0x0301_0301, //  ́
      },
      23, 83);


  /**
   * <p>The auxiliary characters for the Western Frisian language as defined by the
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
          0xc6_c6, //  Æ
          0xd2_d2, //  Ò
          0xd9_d9, //  Ù
          0xe6_e6, //  æ
          0xf2_f2, //  ò
          0xf9_f9, //  ù
      },
      6, 6);


  /**
   * <p>The punctuation characters for the Western Frisian language as defined by the
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


  /**
   * <p>The decimal digit characters for the Western Frisian language as defined by the
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
