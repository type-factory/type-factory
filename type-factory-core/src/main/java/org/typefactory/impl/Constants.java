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

final class Constants {

  private Constants() {
  }

  static final String EMPTY_STRING = "";

  static final int BYTE_MASK = 0xFF;

  static final char[] EMPTY_CHAR_ARRAY = new char[0];
  static final int[] EMPTY_INT_ARRAY = new int[0];
  static final long[] EMPTY_LONG_ARRAY = new long[0];

  static final String LINE_SEPARATOR = System.lineSeparator();

  /**
   * The Unicode code point for the right single quotation mark character.
   * <p>
   * It is used by the de-CH locale as a grouping separator. An alternative could be an ordinary APOSTROPHE_SINGLE_QUOTATION_MARK (U+0027).
   *
   * @see #APOSTROPHE_SINGLE_QUOTATION_MARK
   */
  static final int RIGHT_SINGLE_QUOTATION_MARK = '\u2019';

  /**
   * The Unicode code point for the ordinary apostrophe character.
   * <p>
   * We allow it as an alternative to the de-CH locale grouping separator of RIGHT_SINGLE_QUOTATION_MARK (U+2019).
   *
   * @see #RIGHT_SINGLE_QUOTATION_MARK
   */
  static final int APOSTROPHE_SINGLE_QUOTATION_MARK = '\'';


  /**
   * The Unicode code point for the narrow no-break space character.
   * <p>
   * It is used by the fr-CH locale as a grouping separator. An alternative could be an ordinary SPACE (U+0020).
   *
   * @see #SPACE
   */
  static final int NARROW_NO_BREAK_SPACE = '\u202F';

  /**
   * The Unicode code point for the ordinary space character.
   * <p>
   * We allow it as an alternative to the fr-FR locale grouping separator of NARROW_NO_BREAK_SPACE (U+202F).
   *
   * @see #NARROW_NO_BREAK_SPACE
   */
  static final int SPACE = '\u0020';

  static final int HYPHEN_MINUS = '-';
  static final int MATH_MINUS = '\u2212';
  static final int HEAVY_MINUS = '\u2796';
  static final int[] MINUS_CODEPOINTS = new int[]{HYPHEN_MINUS, MATH_MINUS, HEAVY_MINUS};

  static final int PLUS = '+';
  static final int HEAVY_PLUS = '\u2795';
  static final int[] PLUS_CODEPOINTS = new int[]{PLUS, HEAVY_PLUS};

}
