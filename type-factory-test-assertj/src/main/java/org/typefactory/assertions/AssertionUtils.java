package org.typefactory.assertions;

import static org.typefactory.Category.codePointIsInOneOfTheCategories;

import org.typefactory.Category;

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
