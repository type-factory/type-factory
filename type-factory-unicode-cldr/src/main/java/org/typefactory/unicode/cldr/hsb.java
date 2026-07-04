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
 * Provides Type Factory subsets for the Upper Sorbian language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class hsb extends AbstractCldrResourceBundle {

  public hsb() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Upper Sorbian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x41_5a, //  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
          0x61_7a, //  a b c d e f g h i j k l m n o p q r s t u v w x y z
          0xd3_d3, //  Ó
          0xf3_f3, //  ó
      },
        new int[]{
          0x0106_0107, //  Ć ć
          0x010c_010d, //  Č č
          0x011a_011b, //  Ě ě
          0x0141_0144, //  Ł ł Ń ń
          0x0158_0159, //  Ř ř
          0x0160_0161, //  Š š
          0x0179_017a, //  Ź ź
          0x017d_017e, //  Ž ž
      },
      12, 72);


  /**
   * <p>The auxiliary characters for the Upper Sorbian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x49_49, //  I
          0x53_53, //  S
          0x69_69, //  i
          0x73_73, //  s
          0xc0_cf, //  À Á Â Ã Ä Å Æ Ç È É Ê Ë Ì Í Î Ï
          0xd1_d2, //  Ñ Ò
          0xd4_d4, //  Ô
          0xd6_d6, //  Ö
          0xd8_dd, //  Ø Ù Ú Û Ü Ý
          0xdf_ef, //  ß à á â ã ä å æ ç è é ê ë ì í î ï
          0xf1_f2, //  ñ ò
          0xf4_f4, //  ô
          0xf6_f6, //  ö
          0xf8_fd, //  ø ù ú û ü ý
          0xff_ff, //  ÿ
      },
        new int[]{
          0x0100_0105, //  Ā ā Ă ă Ą ą
          0x010e_0119, //  Ď ď Đ đ Ē ē Ĕ ĕ Ė ė Ę ę
          0x011e_011f, //  Ğ ğ
          0x012a_012d, //  Ī ī Ĭ ĭ
          0x0130_0131, //  İ ı
          0x0139_013a, //  Ĺ ĺ
          0x013d_013e, //  Ľ ľ
          0x0147_0148, //  Ň ň
          0x014c_0155, //  Ō ō Ŏ ŏ Ő ő Œ œ Ŕ ŕ
          0x015a_015b, //  Ś ś
          0x015e_015f, //  Ş ş
          0x0164_0165, //  Ť ť
          0x016a_0171, //  Ū ū Ŭ ŭ Ů ů Ű ű
          0x0178_017c, //  Ÿ Ź ź Ż ż
          0x0307_0307, //  ̇
      },
      30, 120);


  /**
   * <p>The punctuation characters for the Upper Sorbian language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
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
          0x7b_7b, //  {
          0x7d_7d, //  }
          0xa7_a7, //  §
          0xab_ab, //  «
          0xbb_bb, //  »
      },
        new int[]{
          0x2010_2011, //  ‐ ‑
          0x2013_2014, //  – —
          0x2018_201a, //  ‘ ’ ‚
          0x201c_201c, //  “
          0x201e_201e, //  „
          0x2026_2026, //  …
      },
      18, 33);


}
