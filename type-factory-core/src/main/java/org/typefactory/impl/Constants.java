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

  static final String SYSTEM_LINE_SEPARATOR = System.lineSeparator();

  static final int ARABIC_LETTER_MARK = '\u061c';
  static final int LEFT_TO_RIGHT_INDICATOR = '\u200e';
  static final int RIGHT_TO_LEFT_INDICATOR = '\u200f';

  /**
   * <p>The Unicode code point for a comma.</p>
   *
   * <p>Used by some locales as a numeric grouping separator (thousands separator) and other locals as a decimal separator (decimal point).</p>
   */
  static final int COMMA = '\u002c';

  /**
   * <p>The Unicode code point for a full stop.</p>
   *
   * <p>Used by some locales as a numeric grouping separator (thousands separator) and other locals as a decimal separator (decimal point).</p>
   */
  static final int FULL_STOP = '\u002e';

  /**
   * <p>The Unicode code point for a no-break space.</p>
   *
   * <p>Used by some locales as a numeric grouping separator (thousands separator).</p>
   */
  static final int NO_BREAK_SPACE = '\u00a0';

  /**
   * <p>The Unicode code point for an arabic comma.</p>
   *
   * <p>Used by some locales as a numeric grouping separator (thousands separator).</p>
   */
  static final int ARABIC_COMMA = '\u060c';

  /**
   * <p>The Unicode code point for an arabic decimal separator.</p>
   *
   * <p>Used by some locales as a numeric decimal separator (decimal point).</p>
   */
  static final int ARABIC_DECIMAL_SEPARATOR = '\u066b';

  /**
   * <p>The Unicode code point for an arabic thousands separator.</p>
   *
   * <p>Used by some locales as a numeric grouping separator (thousands separator).</p>
   */
  static final int ARABIC_THOUSANDS_SEPARATOR = '\u066c';

  /**
   * <p>The Unicode code point for a right single quotation mark.</p>
   *
   * <p>Used by some locales as a numeric grouping separator (thousands separator).</p>
   */
  static final int RIGHT_SINGLE_QUOTATION_MARK = '\u2019';

  /**
   * <p>The Unicode code point for a thin space.</p>
   *
   * <p>Lenient alternative as a numeric grouping separator (thousands separator).</p>
   */
  static final int THIN_SPACE = '\u2009';

  /**
   * <p>The Unicode code point for a narrow no-break space.</p>
   *
   * <p>Used by some locales as a numeric grouping separator (thousands separator).</p>
   */
  static final int NARROW_NO_BREAK_SPACE = '\u202f';


  /**
   * <p>The Unicode code point for a apostrophe / single quote character.</p>
   *
   * <p>Used by some locales as an alternative numeric grouping separator (thousands separator).</p>
   */
  static final int APOSTROPHE_SINGLE_QUOTATION_MARK = '\'';

  /**
   * <p>The Unicode code point for a line separator.</p>
   */
  static final int UNICODE_LINE_SEPARATOR = '\u2028';

  /**
   * <p>The Unicode code point for a paragraph separator.</p>
   */
  static final int UNICODE_PARAGRAPH_SEPARATOR = '\u2029';

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
