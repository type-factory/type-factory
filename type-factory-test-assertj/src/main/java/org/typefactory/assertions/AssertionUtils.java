package org.typefactory.assertions;

import static org.typefactory.Category.codePointIsInOneOfTheCategories;

import org.typefactory.Category;
import org.typefactory.CharSequenceUtils;

final class AssertionUtils {

  private AssertionUtils() {
    // don't instantiate me
  }

  private static final long SPACE_CONTROL_AND_FORMAT_CATEGORY_BIT_FLAGS =
      Category.getCategoryBitFlags(
          Category.CONTROL,
          Category.FORMAT,
          Category.SPACE_SEPARATOR,
          Category.LINE_SEPARATOR,
          Category.PARAGRAPH_SEPARATOR);

  static final String NULL_VALUE = "null";
  static final String EMPTY_VALUE = "\"\" (empty)";

  static boolean notEquals(final CharSequence a, final CharSequence b) {
    return !equals(a, b);
  }

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

  static String unicodeHexCode(final int codePoint) {
    if (Character.isDefined(codePoint)) {
      if (codePointIsInOneOfTheCategories(codePoint, SPACE_CONTROL_AND_FORMAT_CATEGORY_BIT_FLAGS)) {
        return codePoint > 0xFFFF
            ? String.format("U+%06X %s", codePoint, Character.getName(codePoint))
            : String.format("U+%04X %s", (short) codePoint, Character.getName(codePoint));
      }
      if (Character.isHighSurrogate((char) codePoint)) {
        return String.format("U+%04X HIGH SURROGATE", (short) codePoint);
      }
      if (Character.isLowSurrogate((char) codePoint)) {
        return String.format("U+%04X LOW SURROGATE", (short) codePoint);
      }
      return codePoint > 0xFFFF
          ? String.format("%c U+%06X %s", codePoint, codePoint, Character.getName(codePoint))
          : String.format("%c U+%04X %s", codePoint, (short) codePoint, Character.getName(codePoint));
    }

    if (!Character.isValidCodePoint(codePoint)) {
      return codePoint > 0xFFFFFFL
          ? String.format("U+%08X NOT A UNICODE CHARACTER", codePoint)
          : String.format("U+%06X NOT A UNICODE CHARACTER", codePoint);
    }

    return codePoint > 0xFFFF
        ? String.format("U+%06X UNASSIGNED UNICODE CHARACTER", codePoint)
        : String.format("U+%04X UNASSIGNED UNICODE CHARACTER", codePoint);
  }
}
