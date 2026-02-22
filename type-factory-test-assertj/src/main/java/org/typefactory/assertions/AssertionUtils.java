package org.typefactory.assertions;

import org.typefactory.Category;
import org.typefactory.CharSequenceUtils;

final class AssertionUtils {

  private AssertionUtils() {
    // don't instantiate me
  }

  static final String NULL_VALUE = "null";
  static final String EMPTY_VALUE = "\"\" (empty)";

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
    if (CharSequenceUtils.isBlank(value)) {
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

}
