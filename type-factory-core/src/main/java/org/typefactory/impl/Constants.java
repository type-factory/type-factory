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

  static final char REPLACEMENT_CHARACTER = '\uFFFD';

  static final int BYTE_MASK = 0xFF;

  static final String[] EMPTY_STRING_ARRAY = new String[0];
  static final char[] EMPTY_CHAR_ARRAY = new char[0];
  static final int[] EMPTY_INT_ARRAY = new int[0];
  static final long[] EMPTY_LONG_ARRAY = new long[0];

  static final String SYSTEM_LINE_SEPARATOR = System.lineSeparator();

  /**
   * <p>The Unicode code point for a line separator.</p>
   */
  static final int UNICODE_LINE_SEPARATOR = '\u2028';

  /**
   * <p>The Unicode code point for a paragraph separator.</p>
   */
  static final int UNICODE_PARAGRAPH_SEPARATOR = '\u2029';

}
