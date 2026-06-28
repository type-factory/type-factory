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
 * Provides Type Factory subsets for the Finnish language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class fi extends AbstractCldrResourceBundle {

  public fi() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Finnish language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x41_5a, //  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
          0x61_7a, //  a b c d e f g h i j k l m n o p q r s t u v w x y z
          0xc4_c5, //  Ä Å
          0xd6_d6, //  Ö
          0xe4_e5, //  ä å
          0xf6_f6, //  ö
      },
        new int[]{
          0x0160_0161, //  Š š
          0x017d_017e, //  Ž ž
      },
      8, 62);


  /**
   * <p>The auxiliary characters for the Finnish language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x49_49, //  I
          0xc0_c3, //  À Á Â Ã
          0xc6_cb, //  Æ Ç È É Ê Ë
          0xcd_d5, //  Í Î Ï Ð Ñ Ò Ó Ô Õ
          0xd8_e3, //  Ø Ù Ú Û Ü Ý Þ ß à á â ã
          0xe6_eb, //  æ ç è é ê ë
          0xed_f5, //  í î ï ð ñ ò ó ô õ
          0xf8_ff, //  ø ù ú û ü ý þ ÿ
      },
        new int[]{
          0x0100_0107, //  Ā ā Ă ă Ą ą Ć ć
          0x010a_0113, //  Ċ ċ Č č Ď ď Đ đ Ē ē
          0x0116_011b, //  Ė ė Ę ę Ě ě
          0x011e_011f, //  Ğ ğ
          0x0122_0123, //  Ģ ģ
          0x0126_0127, //  Ħ ħ
          0x012a_012b, //  Ī ī
          0x012e_0131, //  Į į İ ı
          0x0136_0137, //  Ķ ķ
          0x0139_013e, //  Ĺ ĺ Ļ ļ Ľ ľ
          0x0141_0148, //  Ł ł Ń ń Ņ ņ Ň ň
          0x014a_014b, //  Ŋ ŋ
          0x0150_0155, //  Ő ő Œ œ Ŕ ŕ
          0x0158_015f, //  Ř ř Ś ś Ŝ ŝ Ş ş
          0x0162_0167, //  Ţ ţ Ť ť Ŧ ŧ
          0x016a_016b, //  Ū ū
          0x016e_0173, //  Ů ů Ű ű Ų ų
          0x0178_017c, //  Ÿ Ź ź Ż ż
          0x01b7_01b7, //  Ʒ
          0x01e4_01e9, //  Ǥ ǥ Ǧ ǧ Ǩ ǩ
          0x01ee_01ef, //  Ǯ ǯ
          0x0218_021b, //  Ș ș Ț ț
          0x021e_021f, //  Ȟ ȟ
          0x0292_0292, //  ʒ
      },
      32, 158);


  /**
   * <p>The punctuation characters for the Finnish language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x21_21, //  !
          0x23_23, //  #
          0x26_26, //  &
          0x28_2a, //  ( ) *
          0x2c_2f, //  , - . /
          0x3a_3b, //  : ;
          0x3f_40, //  ? @
          0x5b_5d, //  [ \ ]
          0xa7_a7, //  §
          0xbb_bb, //  »
      },
        new int[]{
          0x2010_2011, //  ‐ ‑
          0x2013_2013, //  –
          0x2019_2019, //  ’
          0x201d_201d, //  ”
          0x2026_2026, //  …
      },
      15, 25);


}
