/*
 * Copyright © 2021-2026 Evan Toliopoulos (typefactory.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.typefactory.assertions;

import org.typefactory.Category;
import org.typefactory.Types;

final class AssertionUtils {

  private AssertionUtils() {
    // don't instantiate me
  }

  static final String NULL_VALUE = "null";
  static final String EMPTY_VALUE = "\"\" (empty)";

  static final String U_08X = "U+%08X";
  static final String U_06X = "U+%06X";
  static final String U_04X = "U+%04X";

  static final long SPACE_CONTROL_AND_FORMAT_CATEGORY_BIT_FLAGS =
      Category.getCategoryBitFlags(
          Category.CONTROL,
          Category.FORMAT,
          Category.SPACE_SEPARATOR,
          Category.LINE_SEPARATOR,
          Category.PARAGRAPH_SEPARATOR);



  static boolean equals(final CharSequence a, final CharSequence b) {
    if (a == b) {
      // Both are null or the same reference, so they are equal
      return true;
    }
    if (a == null || b == null) {
      // a is null or b is null, but not both, so they are not equal
      return false;
    }
    if (a.length() != b.length()) {
      return false;
    }
    for (int i = 0; i < a.length(); i++) {
      if (a.charAt(i) != b.charAt(i)) {
        return false;
      }
    }
    return true;
  }

  static boolean notEquals(final CharSequence a, final CharSequence b) {
    return !equals(a, b);
  }

  static String valueOf(final CharSequence value) {
    if (value == null) {
      return NULL_VALUE;
    }
    if (value.isEmpty()) {
      return EMPTY_VALUE;
    }
    if (Types.isBlank(value)) {
      return "\"" + value + "\" (blank)";
    }
    return "\"" + value + "\"";
  }

  static <T> boolean isEmpty(final T[] array) {
    return array == null || array.length == 0;
  }

  static <T> boolean isNotEmpty(final T[] array) {
    return !isEmpty(array);
  }

  static String codePointToStringOrHexCode(final int codePoint) {
    if (!Character.isDefined(codePoint)) {
      if (codePoint > 0xFFFFFFL) {
        return String.format(U_08X, codePoint);
      } else if (codePoint > 0xFFFF) {
        return String.format(U_06X, codePoint);
      } else {
        return String.format(U_04X, codePoint);
      }
    }

    if (Category.codePointIsInOneOfTheCategories(codePoint, SPACE_CONTROL_AND_FORMAT_CATEGORY_BIT_FLAGS)) {
      return codePoint > 0xFFFF
          ? String.format(U_06X, codePoint)
          : String.format(U_04X, codePoint);
    }
    if (codePoint > 0xFFFF) {
      return Character.toString(codePoint);
    } else {
      if (Character.isHighSurrogate((char) codePoint) || Character.isLowSurrogate((char) codePoint)) {
        return String.format(U_04X, codePoint);
      } else {
        return Character.toString(codePoint);
      }
    }
  }

}
