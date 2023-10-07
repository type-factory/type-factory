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

abstract class AbstractTypeParser_parseCorruptCharSequenceTest {

  /**
   * The Caucasian Albanian Letter Alt (U+10530) is a supplementary code point in Unicode and requires two Java chars in UTF-16
   */
  protected static final String CAUCASIAN_ALBANIAN_LETTER_ALT = "𐔰";

  /**
   * The Caucasian Albanian Letter Bet (U+10531) is a supplementary code point in Unicode and requires two Java chars in UTF-16
   */
  protected static final String CAUCASIAN_ALBANIAN_LETTER_BET = "𐔱"; // The Caucasian Albanian Letter Bet (U+10531) requires two chars in UTF-16
  protected static final String CAUCASIAN_ALBANIAN_LETTER_GIM = "𐔲"; // The Caucasian Albanian Letter Gim (U+10532) requires two chars in UTF-16
  protected static final String CAUCASIAN_ALBANIAN_LETTER_DAT = "𐔳"; // The Caucasian Albanian Letter Dat (U+10533) requires two chars in UTF-16
  protected static final String CAUCASIAN_ALBANIAN_LETTER_CAR = "𐕂"; // The Caucasian Albanian Letter Car (U+10542) requires two chars in UTF-16
  protected static final char CAUCASIAN_ALBANIAN_LETTER_CAR_HIGH_SURROGATE_CHAR = CAUCASIAN_ALBANIAN_LETTER_CAR.charAt(0);
  protected static final char CAUCASIAN_ALBANIAN_LETTER_CAR_LOW_SURROGATE_CHAR = CAUCASIAN_ALBANIAN_LETTER_CAR.charAt(1);
  protected enum CorruptCharSequence implements CharSequence {

    SEQUENCE_WITH_ONLY_HIGH_SURROGATE_WITHOUT_LOW_SURROGATE(
        INVALID_VALUE_HIGH_SURROGATE_WITHOUT_LOW_SURROGATE.defaultMessage().replace("{0}", "[U0-9A-F+]+"),
        CAUCASIAN_ALBANIAN_LETTER_CAR_HIGH_SURROGATE_CHAR),

    SEQUENCE_WITH_ONLY_LOW_SURROGATE_WITHOUT_HIGH_SURROGATE(
        INVALID_VALUE_LOW_SURROGATE_WITHOUT_HIGH_SURROGATE.defaultMessage().replace("{0}", "[U0-9A-F+]+"),
        CAUCASIAN_ALBANIAN_LETTER_CAR_LOW_SURROGATE_CHAR),

    SEQUENCE_STARTING_WITH_HIGH_SURROGATE_WITHOUT_LOW_SURROGATE(
        INVALID_VALUE_HIGH_SURROGATE_WITHOUT_LOW_SURROGATE.defaultMessage().replace("{0}", "[U0-9A-F+]+"),
        CAUCASIAN_ALBANIAN_LETTER_CAR_HIGH_SURROGATE_CHAR,
        CAUCASIAN_ALBANIAN_LETTER_ALT.charAt(0),
        CAUCASIAN_ALBANIAN_LETTER_ALT.charAt(1)),

    SEQUENCE_STARTING_WITH_LOW_SURROGATE_WITHOUT_HIGH_SURROGATE(
        INVALID_VALUE_LOW_SURROGATE_WITHOUT_HIGH_SURROGATE.defaultMessage().replace("{0}", "[U0-9A-F+]+"),
        CAUCASIAN_ALBANIAN_LETTER_CAR_LOW_SURROGATE_CHAR,
        CAUCASIAN_ALBANIAN_LETTER_ALT.charAt(0),
        CAUCASIAN_ALBANIAN_LETTER_ALT.charAt(1)),

    SEQUENCE_CONTAINING_HIGH_SURROGATE_WITHOUT_LOW_SURROGATE(
        INVALID_VALUE_HIGH_SURROGATE_WITHOUT_LOW_SURROGATE.defaultMessage().replace("{0}", "[U0-9A-F+]+"),
        CAUCASIAN_ALBANIAN_LETTER_BET.charAt(0),
        CAUCASIAN_ALBANIAN_LETTER_BET.charAt(1),
        CAUCASIAN_ALBANIAN_LETTER_CAR_HIGH_SURROGATE_CHAR,
        CAUCASIAN_ALBANIAN_LETTER_GIM.charAt(0),
        CAUCASIAN_ALBANIAN_LETTER_GIM.charAt(1)),

    SEQUENCE_CONTAINING_LOW_SURROGATE_WITHOUT_HIGH_SURROGATE(
        INVALID_VALUE_LOW_SURROGATE_WITHOUT_HIGH_SURROGATE.defaultMessage().replace("{0}", "[U0-9A-F+]+"),
        CAUCASIAN_ALBANIAN_LETTER_BET.charAt(0),
        CAUCASIAN_ALBANIAN_LETTER_BET.charAt(1),
        CAUCASIAN_ALBANIAN_LETTER_CAR_LOW_SURROGATE_CHAR,
        CAUCASIAN_ALBANIAN_LETTER_GIM.charAt(0),
        CAUCASIAN_ALBANIAN_LETTER_GIM.charAt(1)),

    SEQUENCE_ENDING_WITH_HIGH_SURROGATE_WITHOUT_LOW_SURROGATE(
        INVALID_VALUE_HIGH_SURROGATE_WITHOUT_LOW_SURROGATE.defaultMessage().replace("{0}", "[U0-9A-F+]+"),
        CAUCASIAN_ALBANIAN_LETTER_DAT.charAt(0),
        CAUCASIAN_ALBANIAN_LETTER_DAT.charAt(1),
        CAUCASIAN_ALBANIAN_LETTER_CAR_HIGH_SURROGATE_CHAR),

    SEQUENCE_ENDING_WITH_LOW_SURROGATE_WITHOUT_HIGH_SURROGATE(
        INVALID_VALUE_LOW_SURROGATE_WITHOUT_HIGH_SURROGATE.defaultMessage().replace("{0}", "[U0-9A-F+]+"),
        CAUCASIAN_ALBANIAN_LETTER_DAT.charAt(0),
        CAUCASIAN_ALBANIAN_LETTER_DAT.charAt(1),
        CAUCASIAN_ALBANIAN_LETTER_CAR_LOW_SURROGATE_CHAR),
    ;

    private final char [] chars;

    public final String expectedErrorMessage;

    CorruptCharSequence(final String expectedErrorMessage, final char... chars) {
      this.expectedErrorMessage = expectedErrorMessage;
      this.chars = chars;
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
