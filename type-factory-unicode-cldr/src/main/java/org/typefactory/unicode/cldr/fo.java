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
 * Provides Type Factory subsets for the Faroese as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Faroese language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class fo extends root {

  public fo() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected fo(
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
   * <p>The standard characters for the Faroese language as defined by the
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
          0x44_50, //  D E F G H I J K L M N O P
          0x52_56, //  R S T U V
          0x59_59, //  Y
          0x61_62, //  a b
          0x64_70, //  d e f g h i j k l m n o p
          0x72_76, //  r s t u v
          0x79_79, //  y
          0xc1_c1, //  Á
          0xc6_c6, //  Æ
          0xcd_cd, //  Í
          0xd0_d0, //  Ð
          0xd3_d3, //  Ó
          0xd8_d8, //  Ø
          0xda_da, //  Ú
          0xdd_dd, //  Ý
          0xe1_e1, //  á
          0xe6_e6, //  æ
          0xed_ed, //  í
          0xf0_f0, //  ð
          0xf3_f3, //  ó
          0xf8_f8, //  ø
          0xfa_fa, //  ú
          0xfd_fd, //  ý
      },
      24, 58);


  /**
   * <p>The auxiliary characters for the Faroese language as defined by the
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
          0x51_51, //  Q
          0x57_58, //  W X
          0x5a_5a, //  Z
          0x63_63, //  c
          0x71_71, //  q
          0x77_78, //  w x
          0x7a_7a, //  z
      },
      8, 10);


  /**
   * <p>The punctuation characters for the Faroese language as defined by the
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
          0x2013_2013, //  –
          0x2018_2019, //  ‘ ’
          0x201c_201d, //  “ ”
          0x2020_2020, //  †
          0x2026_2026, //  …
          0x2032_2033, //  ′ ″
      },
      15, 30);


  /**
   * <p>The decimal digit characters for the Faroese language as defined by the
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
