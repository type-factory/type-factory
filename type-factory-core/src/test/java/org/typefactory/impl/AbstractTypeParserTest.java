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
package org.typefactory.impl;

import static org.typefactory.InvalidValueException.ParserMessageCode.INVALID_VALUE_HIGH_SURROGATE_WITHOUT_LOW_SURROGATE;
import static org.typefactory.InvalidValueException.ParserMessageCode.INVALID_VALUE_LOW_SURROGATE_WITHOUT_HIGH_SURROGATE;

import org.typefactory.StringType;

abstract class AbstractTypeParserTest {

  /**
   * A concrete custom {@link StringType} type that can be used in unit tests.
   */
  static class SomeType extends StringType {

    private SomeType(final String value) {
      super(value);
    }
  }

  /**
   * The Caucasian Albanian alphabet is made up entirely of letters that are
   * <a href="https://www.unicode.org/glossary/#supplementary_code_point">supplementary code points</a>
   * in Unicode with each letter requiring two Java chars in UTF-16 strings.
   *
   * @see <a href="https://www.unicode.org/glossary/#supplementary_code_point">Supplementary code point</a>
   * @see <a href="https://www.unicode.org/glossary/#surrogate_code_point">Surrogate code point</a>
   * @see <a href="https://www.unicode.org/glossary/#surrogate_pair">Surrogate pair</a>
   * @see <a href="https://www.unicode.org/glossary/#high_surrogate_code_unit">High-surrogate code unit</a>
   * @see <a href="https://www.unicode.org/glossary/#low_surrogate_code_unit">Low-surrogate code unit</a>
   */
  protected static final int [] CAUCASIAN_ALBANIAN_ALPHABET_CODE_POINTS =
      "𐔰𐔱𐔲𐔳𐔴𐔵𐔶𐔷𐔸𐔹𐔺𐔻𐔼𐔽𐔾𐔿𐕀𐕁𐕂𐕃𐕄𐕅𐕆𐕇𐕈𐕉𐕊𐕋𐕌𐕍𐕎𐕏𐕐𐕑𐕒𐕓𐕔𐕕𐕖𐕗𐕘𐕙𐕚𐕛𐕜𐕝𐕞𐕟𐕠𐕡𐕢𐕣".codePoints().toArray();

  /**
   * The Caucasian Albanian Letter Alt 𐔰 (U+10530) is a
   * <a href="https://www.unicode.org/glossary/#supplementary_code_point">supplementary code point</a>
   * in Unicode and requires two Java chars in UTF-16 strings.
   *
   * @see <a href="https://www.unicode.org/glossary/#supplementary_code_point">Supplementary code point</a>
   * @see <a href="https://www.unicode.org/glossary/#surrogate_code_point">Surrogate code point</a>
   * @see <a href="https://www.unicode.org/glossary/#surrogate_pair">Surrogate pair</a>
   * @see <a href="https://www.unicode.org/glossary/#high_surrogate_code_unit">High-surrogate code unit</a>
   * @see <a href="https://www.unicode.org/glossary/#low_surrogate_code_unit">Low-surrogate code unit</a>
   */
  protected static final String CAUCASIAN_ALBANIAN_LETTER_ALT = "𐔰";

  /**
   * The Caucasian Albanian Letter Bet 𐔱 (U+10531) is a
   * <a href="https://www.unicode.org/glossary/#supplementary_code_point">supplementary code point</a>
   * in Unicode and requires two Java chars in UTF-16 strings.
   *
   * @see <a href="https://www.unicode.org/glossary/#supplementary_code_point">Supplementary code point</a>
   * @see <a href="https://www.unicode.org/glossary/#surrogate_code_point">Surrogate code point</a>
   * @see <a href="https://www.unicode.org/glossary/#surrogate_pair">Surrogate pair</a>
   * @see <a href="https://www.unicode.org/glossary/#high_surrogate_code_unit">High-surrogate code unit</a>
   * @see <a href="https://www.unicode.org/glossary/#low_surrogate_code_unit">Low-surrogate code unit</a>
   */
  protected static final String CAUCASIAN_ALBANIAN_LETTER_BET = "𐔱";

  /**
   * The Caucasian Albanian Letter Gim 𐔲 (U+10532) is a
   * <a href="https://www.unicode.org/glossary/#supplementary_code_point">supplementary code point</a>
   * in Unicode and requires two Java chars in UTF-16 strings.
   *
   * @see <a href="https://www.unicode.org/glossary/#supplementary_code_point">Supplementary code point</a>
   * @see <a href="https://www.unicode.org/glossary/#surrogate_code_point">Surrogate code point</a>
   * @see <a href="https://www.unicode.org/glossary/#surrogate_pair">Surrogate pair</a>
   * @see <a href="https://www.unicode.org/glossary/#high_surrogate_code_unit">High-surrogate code unit</a>
   * @see <a href="https://www.unicode.org/glossary/#low_surrogate_code_unit">Low-surrogate code unit</a>
   */
  protected static final String CAUCASIAN_ALBANIAN_LETTER_GIM = "𐔲";

  /**
   * The Caucasian Albanian Letter Dat 𐔳 (U+10533) is a
   * <a href="https://www.unicode.org/glossary/#supplementary_code_point">supplementary code point</a>
   * in Unicode and requires two Java chars in UTF-16 strings.
   *
   * @see <a href="https://www.unicode.org/glossary/#supplementary_code_point">Supplementary code point</a>
   * @see <a href="https://www.unicode.org/glossary/#surrogate_code_point">Surrogate code point</a>
   * @see <a href="https://www.unicode.org/glossary/#surrogate_pair">Surrogate pair</a>
   * @see <a href="https://www.unicode.org/glossary/#high_surrogate_code_unit">High-surrogate code unit</a>
   * @see <a href="https://www.unicode.org/glossary/#low_surrogate_code_unit">Low-surrogate code unit</a>
   */
  protected static final String CAUCASIAN_ALBANIAN_LETTER_DAT = "𐔳";

  /**
   * The Caucasian Albanian Letter Car 𐕂 (U+10542) is a
   * <a href="https://www.unicode.org/glossary/#supplementary_code_point">supplementary code point</a>
   * in Unicode and requires two Java chars in UTF-16 strings.
   *
   * @see <a href="https://www.unicode.org/glossary/#supplementary_code_point">Supplementary code point</a>
   * @see <a href="https://www.unicode.org/glossary/#surrogate_code_point">Surrogate code point</a>
   * @see <a href="https://www.unicode.org/glossary/#surrogate_pair">Surrogate pair</a>
   * @see <a href="https://www.unicode.org/glossary/#high_surrogate_code_unit">High-surrogate code unit</a>
   * @see <a href="https://www.unicode.org/glossary/#low_surrogate_code_unit">Low-surrogate code unit</a>
   */
  protected static final String CAUCASIAN_ALBANIAN_LETTER_CAR = "𐕂";

  /**
   * The high-surrogate part of the Caucasian Albanian Letter Car 𐕂 (U+10542) which is a
   * <a href="https://www.unicode.org/glossary/#supplementary_code_point">supplementary code point</a>
   * in Unicode and requires two Java chars in UTF-16 strings.
   *
   * @see <a href="https://www.unicode.org/glossary/#supplementary_code_point">Supplementary code point</a>
   * @see <a href="https://www.unicode.org/glossary/#surrogate_code_point">Surrogate code point</a>
   * @see <a href="https://www.unicode.org/glossary/#surrogate_pair">Surrogate pair</a>
   * @see <a href="https://www.unicode.org/glossary/#high_surrogate_code_unit">High-surrogate code unit</a>
   * @see <a href="https://www.unicode.org/glossary/#low_surrogate_code_unit">Low-surrogate code unit</a>
   */
  protected static final char CAUCASIAN_ALBANIAN_LETTER_CAR_HIGH_SURROGATE_CHAR = CAUCASIAN_ALBANIAN_LETTER_CAR.charAt(0);

  /**
   * The low-surrogate part of the Caucasian Albanian Letter Car 𐕂 (U+10542) which is a
   * <a href="https://www.unicode.org/glossary/#supplementary_code_point">supplementary code point</a>
   * in Unicode and requires two Java chars in UTF-16 strings.
   *
   * @see <a href="https://www.unicode.org/glossary/#supplementary_code_point">Supplementary code point</a>
   * @see <a href="https://www.unicode.org/glossary/#surrogate_code_point">Surrogate code point</a>
   * @see <a href="https://www.unicode.org/glossary/#surrogate_pair">Surrogate pair</a>
   * @see <a href="https://www.unicode.org/glossary/#high_surrogate_code_unit">High-surrogate code unit</a>
   * @see <a href="https://www.unicode.org/glossary/#low_surrogate_code_unit">Low-surrogate code unit</a>
   */
  protected static final char CAUCASIAN_ALBANIAN_LETTER_CAR_LOW_SURROGATE_CHAR = CAUCASIAN_ALBANIAN_LETTER_CAR.charAt(1);

  /**
   * <p>An enum that can be used in parameterized unit tests where each enum value
   * is an instance of a corrupt {@link CharSequence} where a
   * <a href="https://www.unicode.org/glossary/#supplementary_code_point">supplementary code point</a>
   * is missing either a <a href="https://www.unicode.org/glossary/#high_surrogate_code_unit">high-surrogate code unit</a>
   * or a <a href="https://www.unicode.org/glossary/#low_surrogate_code_unit">low-surrogate code unit</a>.</p>
   *
   * <p>Each enum value also contains the expected error message pattern for the thrown
   * {@link org.typefactory.InvalidValueException}</p>
   */
  protected enum CorruptCharSequence implements CharSequence {

    SEQUENCE_WITH_ONLY_HIGH_SURROGATE_WITHOUT_LOW_SURROGATE(
        INVALID_VALUE_HIGH_SURROGATE_WITHOUT_LOW_SURROGATE.defaultMessage().replace("{0}", "U+D801 HIGH SURROGATE"),
        CAUCASIAN_ALBANIAN_LETTER_CAR_HIGH_SURROGATE_CHAR),

    SEQUENCE_WITH_ONLY_LOW_SURROGATE_WITHOUT_HIGH_SURROGATE(
        INVALID_VALUE_LOW_SURROGATE_WITHOUT_HIGH_SURROGATE.defaultMessage().replace("{0}", "U+DD42 LOW SURROGATE"),
        CAUCASIAN_ALBANIAN_LETTER_CAR_LOW_SURROGATE_CHAR),

    SEQUENCE_STARTING_WITH_HIGH_SURROGATE_WITHOUT_LOW_SURROGATE(
        INVALID_VALUE_HIGH_SURROGATE_WITHOUT_LOW_SURROGATE.defaultMessage().replace("{0}", "U+D801 HIGH SURROGATE"),
        CAUCASIAN_ALBANIAN_LETTER_CAR_HIGH_SURROGATE_CHAR,
        CAUCASIAN_ALBANIAN_LETTER_ALT.charAt(0),
        CAUCASIAN_ALBANIAN_LETTER_ALT.charAt(1)),

    SEQUENCE_STARTING_WITH_LOW_SURROGATE_WITHOUT_HIGH_SURROGATE(
        INVALID_VALUE_LOW_SURROGATE_WITHOUT_HIGH_SURROGATE.defaultMessage().replace("{0}", "U+DD42 LOW SURROGATE"),
        CAUCASIAN_ALBANIAN_LETTER_CAR_LOW_SURROGATE_CHAR,
        CAUCASIAN_ALBANIAN_LETTER_ALT.charAt(0),
        CAUCASIAN_ALBANIAN_LETTER_ALT.charAt(1)),

    SEQUENCE_CONTAINING_HIGH_SURROGATE_WITHOUT_LOW_SURROGATE(
        INVALID_VALUE_HIGH_SURROGATE_WITHOUT_LOW_SURROGATE.defaultMessage().replace("{0}", "U+D801 HIGH SURROGATE"),
        CAUCASIAN_ALBANIAN_LETTER_BET.charAt(0),
        CAUCASIAN_ALBANIAN_LETTER_BET.charAt(1),
        CAUCASIAN_ALBANIAN_LETTER_CAR_HIGH_SURROGATE_CHAR,
        CAUCASIAN_ALBANIAN_LETTER_GIM.charAt(0),
        CAUCASIAN_ALBANIAN_LETTER_GIM.charAt(1)),

    SEQUENCE_CONTAINING_LOW_SURROGATE_WITHOUT_HIGH_SURROGATE(
        INVALID_VALUE_LOW_SURROGATE_WITHOUT_HIGH_SURROGATE.defaultMessage().replace("{0}", "U+DD42 LOW SURROGATE"),
        CAUCASIAN_ALBANIAN_LETTER_BET.charAt(0),
        CAUCASIAN_ALBANIAN_LETTER_BET.charAt(1),
        CAUCASIAN_ALBANIAN_LETTER_CAR_LOW_SURROGATE_CHAR,
        CAUCASIAN_ALBANIAN_LETTER_GIM.charAt(0),
        CAUCASIAN_ALBANIAN_LETTER_GIM.charAt(1)),

    SEQUENCE_ENDING_WITH_HIGH_SURROGATE_WITHOUT_LOW_SURROGATE(
        INVALID_VALUE_HIGH_SURROGATE_WITHOUT_LOW_SURROGATE.defaultMessage().replace("{0}", "U+D801 HIGH SURROGATE"),
        CAUCASIAN_ALBANIAN_LETTER_DAT.charAt(0),
        CAUCASIAN_ALBANIAN_LETTER_DAT.charAt(1),
        CAUCASIAN_ALBANIAN_LETTER_CAR_HIGH_SURROGATE_CHAR),

    SEQUENCE_ENDING_WITH_LOW_SURROGATE_WITHOUT_HIGH_SURROGATE(
        INVALID_VALUE_LOW_SURROGATE_WITHOUT_HIGH_SURROGATE.defaultMessage().replace("{0}", "U+DD42 LOW SURROGATE"),
        CAUCASIAN_ALBANIAN_LETTER_DAT.charAt(0),
        CAUCASIAN_ALBANIAN_LETTER_DAT.charAt(1),
        CAUCASIAN_ALBANIAN_LETTER_CAR_LOW_SURROGATE_CHAR),
    ;

    private final char [] chars;

    private final String expectedErrorMessage;

    CorruptCharSequence(final String expectedErrorMessage, final char... chars) {
      this.expectedErrorMessage = expectedErrorMessage;
      this.chars = chars;
    }

    public String getExpectedErrorMessage() {
      return expectedErrorMessage;
    }

    @Override
    public int length() {
      return chars.length;
    }

    @Override
    public char charAt(int index) {
      return chars[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
      return new String(chars, start, end - start);
    }
  }

}
