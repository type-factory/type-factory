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
 * Provides Type Factory subsets for the Bosnian (Cyrillic) as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Bosnian (Cyrillic) language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class bs_Cyrl extends bs {

  public bs_Cyrl() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected bs_Cyrl(
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
   * <p>The Locale represented by this resource bundle for the Bosnian (Cyrillic) language.</p>
   *
   * <p>Language tag: {@code "bs-Cyrl"}</p>
   */
  static final Locale LOCALE = new Locale.Builder()
          .setLanguage("bs")
          .setScript("Cyrl")
          .setRegion("")
          .setVariant("")
          .build();

  /**
   * <p>The standard characters for the Bosnian (Cyrillic) language as defined by the
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
          0x0402_0402, //  Ђ
          0x0408_040b, //  Ј Љ Њ Ћ
          0x040f_0418, //  Џ А Б В Г Д Е Ж З И
          0x041a_0428, //  К Л М Н О П Р С Т У Ф Х Ц Ч Ш
          0x0430_0438, //  а б в г д е ж з и
          0x043a_0448, //  к л м н о п р с т у ф х ц ч ш
          0x0452_0452, //  ђ
          0x0458_045b, //  ј љ њ ћ
          0x045f_045f, //  џ
      },
      9, 60);


  /**
   * <p>The auxiliary characters for the Bosnian (Cyrillic) language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the auxiliary characters
   *    are inherited from the superclass.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = null;


  /**
   * <p>The punctuation characters for the Bosnian (Cyrillic) language as defined by the
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
   * <p>The decimal digit characters for the Bosnian (Cyrillic) language as defined by the
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
