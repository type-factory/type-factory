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
 * Provides Type Factory subsets for the Vietnamese as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Vietnamese language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class vi extends root {

  public vi() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected vi(
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
   * <p>The Locale represented by this resource bundle for the Vietnamese language.</p>
   *
   * <p>Language tag: {@code "vi"}</p>
   */
  static final Locale LOCALE = new Locale.Builder()
          .setLanguage("vi")
          .setScript("")
          .setRegion("")
          .setVariant("")
          .build();

  /**
   * <p>The standard characters for the Vietnamese language as defined by the
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
          0x41_45, //  A B C D E
          0x47_49, //  G H I
          0x4b_56, //  K L M N O P Q R S T U V
          0x58_59, //  X Y
          0x61_65, //  a b c d e
          0x67_69, //  g h i
          0x6b_76, //  k l m n o p q r s t u v
          0x78_79, //  x y
          0xc0_c3, //  À Á Â Ã
          0xc8_ca, //  È É Ê
          0xcc_cd, //  Ì Í
          0xd2_d5, //  Ò Ó Ô Õ
          0xd9_da, //  Ù Ú
          0xdd_dd, //  Ý
          0xe0_e3, //  à á â ã
          0xe8_ea, //  è é ê
          0xec_ed, //  ì í
          0xf2_f5, //  ò ó ô õ
          0xf9_fa, //  ù ú
          0xfd_fd, //  ý
      },
      new int[]{
          0x0102_0103, //  Ă ă
          0x0110_0111, //  Đ đ
          0x0128_0129, //  Ĩ ĩ
          0x0168_0169, //  Ũ ũ
          0x01a0_01a1, //  Ơ ơ
          0x01af_01b0, //  Ư ư
          0x1ea0_1ef9, //  Ạ ạ Ả ả Ấ ấ Ầ ầ Ẩ ẩ Ẫ ẫ Ậ ậ Ắ ắ Ằ ằ Ẳ ẳ Ẵ ẵ Ặ ặ Ẹ ẹ Ẻ ẻ Ẽ ẽ
                       //  Ế ế Ề ề Ể ể Ễ ễ Ệ ệ Ỉ ỉ Ị ị Ọ ọ Ỏ ỏ Ố ố Ồ ồ Ổ ổ Ỗ ỗ Ộ ộ Ớ ớ
                       //  Ờ ờ Ở ở Ỡ ỡ Ợ ợ Ụ ụ Ủ ủ Ứ ứ Ừ ừ Ử ử Ữ ữ Ự ự Ỳ ỳ Ỵ ỵ Ỷ ỷ Ỹ ỹ
      },
      27, 178);


  /**
   * <p>The auxiliary characters for the Vietnamese language as defined by the
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
          0x46_46, //  F
          0x4a_4a, //  J
          0x57_57, //  W
          0x5a_5a, //  Z
          0x66_66, //  f
          0x6a_6a, //  j
          0x77_77, //  w
          0x7a_7a, //  z
      },
      8, 8);


  /**
   * <p>The punctuation characters for the Vietnamese language as defined by the
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
   * <p>The decimal digit characters for the Vietnamese language as defined by the
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
