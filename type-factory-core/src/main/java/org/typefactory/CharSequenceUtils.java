package org.typefactory;

import org.typefactory.impl.ExceptionUtils;

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
      final char ch = value.charAt(i++);
      if (Character.isHighSurrogate(ch)) {
        if (i < length) {
          final char lowCh = value.charAt(i++);
          if (Character.isLowSurrogate(lowCh)) {
            if (!Character.isWhitespace(Character.toCodePoint(ch, lowCh))) {
              return false;
            }
          } else {
            // Consider a high surrogate without a low surrogate to not be a blank character
            return false;
          }
        } else {
          // Consider a high surrogate without a low surrogate to not be a blank character
          return false;
        }
      } else if (Character.isLowSurrogate(ch)) {
        // Consider a low surrogate without a high surrogate to not be a blank character
        return false;
      } else {
        if (ch != ' ' && ch != '\t' && !Character.isWhitespace(ch)) {
          return false;
        }
      }
    }
    return true;
  }
}
