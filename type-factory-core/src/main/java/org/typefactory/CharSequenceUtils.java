package org.typefactory;

public class CharSequenceUtils {

  private CharSequenceUtils() {
    // don't instantiate me
  }

  public static boolean isBlank(final CharSequence value) {
    if (value == null
        || value.isEmpty()
        || (value instanceof String str && str.isBlank())) {
      return true;
    }
    final int length = value.length();
    int i = 0;
    while (i < length) {
      int codePoint = value.charAt(i++);
      if (Character.isSurrogate((char) codePoint) && i < length) {
        codePoint = Character.toCodePoint((char) codePoint, value.charAt(i++));
      }
      if (!Character.isWhitespace(codePoint)) {
        return false;
      }
    }
    return true;
  }
}
