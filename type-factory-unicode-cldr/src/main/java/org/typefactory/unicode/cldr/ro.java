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
 * Provides Type Factory subsets for the Romanian as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Romanian language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class ro extends root {

  public ro() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected ro(
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
   * <p>The Locale represented by this resource bundle for the Romanian language.</p>
   *
   * <p>Language tag: {@code "ro"}</p>
   */
  static final Locale LOCALE = new Locale.Builder()
          .setLanguage("ro")
          .setScript("")
          .setRegion("")
          .setVariant("")
          .build();

  /**
   * <p>The standard characters for the Romanian language as defined by the
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
          0x41_5a, //  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
          0x61_7a, //  a b c d e f g h i j k l m n o p q r s t u v w x y z
          0xc2_c2, //  Â
          0xce_ce, //  Î
          0xe2_e2, //  â
          0xee_ee, //  î
      },
      new int[]{
          0x0102_0103, //  Ă ă
          0x0218_021b, //  Ș ș Ț ț
      },
      8, 62);


  /**
   * <p>The auxiliary characters for the Romanian language as defined by the
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
          0xc0_c1, //  À Á
          0xc4_c5, //  Ä Å
          0xc7_cb, //  Ç È É Ê Ë
          0xd1_d1, //  Ñ
          0xd6_d6, //  Ö
          0xdc_dc, //  Ü
          0xe0_e1, //  à á
          0xe4_e5, //  ä å
          0xe7_eb, //  ç è é ê ë
          0xf1_f1, //  ñ
          0xf6_f6, //  ö
          0xfc_fc, //  ü
      },
      new int[]{
          0x015e_015f, //  Ş ş
          0x0162_0163, //  Ţ ţ
      },
      14, 28);


  /**
   * <p>The punctuation characters for the Romanian language as defined by the
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
          0x21_22, //  ! "
          0x27_2a, //  ' ( ) *
          0x2c_2f, //  , - . /
          0x3a_3b, //  : ;
          0x3f_40, //  ? @
          0x5b_5b, //  [
          0x5d_5d, //  ]
          0xab_ab, //  «
          0xbb_bb, //  »
      },
      new int[]{
          0x2010_2011, //  ‐ ‑
          0x2013_2014, //  – —
          0x2018_2018, //  ‘
          0x201c_201e, //  “ ” „
          0x2026_2026, //  …
      },
      14, 27);


  /**
   * <p>The decimal digit characters for the Romanian language as defined by the
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
