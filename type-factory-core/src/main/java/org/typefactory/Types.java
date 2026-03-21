package org.typefactory;

import java.util.Collection;
import java.util.Objects;

public final class Types {

  private Types() {
    // don't instantiate me
  }

  public static final CaseSensistive CS = new CaseSensistive();
  public static final CaseInsensistive CI = new CaseInsensistive();

  public static <T extends CharSequenceType<T>> boolean isNull(final T type) {
    return type == null || type.isNull();
  }
  public static <T extends StringType> boolean isNull(final T type) {
    return type == null || type.isNull();
  }

  public static <T extends CharSequenceType<T>> boolean isEmpty(final T value) {
    return value == null || value.isEmpty();
  }
  public static <T extends StringType> boolean isEmpty(final T value) {
    return value == null || value.isEmpty();
  }

  public static <T extends CharSequenceType<T>> T defaultIfEmpty(final T value, final T defaultValue) {
    return isEmpty(value) ? defaultValue : value;
  }
  public static <T extends StringType> T defaultIfEmpty(final T value, final T defaultValue) {
    return isEmpty(value) ? defaultValue : value;
  }

  public static <T extends CharSequenceType<T>> boolean isBlank(final T value) {
    return value == null || value.isBlank();
  }
  public static <T extends StringType> boolean isBlank(final T value) {
    return value == null || value.isBlank();
  }

  public static boolean isBlank(final CharSequence value) {
    if (value == null || value.isEmpty()){
      return true;
    }
    if (value instanceof String s) {
      return s.isBlank();
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
            // We shouldn't get here since this character should be a low surrogate following a high surrogate,
            // but if we do, consider a high surrogate without a low surrogate to not be a blank character.
            return false;
          }
        } else {
          // We shouldn't get here because it means the character sequence ends with a high surrogate
          // without a low surrogate, but if we do, consider a high surrogate without a low surrogate
          // to not be a blank character.
          return false;
        }
      } else if (Character.isLowSurrogate(ch)) {
        // We shouldn't get here because there was no preceding high surrogate, but if we do,
        // consider a low surrogate without a high surrogate to not be a blank character.
        return false;
      } else {
        if (ch != ' ' && ch != '\t' && !Character.isWhitespace(ch)) {
          return false;
        }
      }
    }
    return true;
  }

  static <T extends CharSequenceType<T>> T defaultIfBlank(final T value, final T defaultValue) {
    return isBlank(value) ? defaultValue : value;
  }
  static <T extends StringType> T defaultIfBlank(final T value, final T defaultValue) {
    return isBlank(value) ? defaultValue : value;
  }

  public static final class CaseSensistive extends AbstractCharSequenceTypes {
    public CaseSensistive() {
      super();
    }

    @Override
    public <T extends CharSequenceType<T>> boolean equals(final T value1, final T value2) {
      return Objects.equals(value1, value2);
    }

    @Override
    public <T extends StringType> boolean equals(final T value1, final T value2) {
      return Objects.equals(value1, value2);
    }
  }

  public static final class CaseInsensistive extends AbstractCharSequenceTypes {
    public CaseInsensistive() {
      super();
    }

    @Override
    public <T extends CharSequenceType<T>> boolean equals(final T value1, final T value2) {
      if (value1 == value2) { // both null or both reference the same object
        return true;
      }
      return value1 != null && value1.equalsIgnoreCase(value2);
    }

    @Override
    public <T extends StringType> boolean equals(final T value1, final T value2) {
      if (value1 == value2) { // both null or both reference the same object
        return true;
      }
      return value1 != null && value1.equalsIgnoreCase(value2);
    }
  }

  private static abstract class AbstractCharSequenceTypes {

    protected AbstractCharSequenceTypes() {
    }

    abstract public <T extends CharSequenceType<T>> boolean equals(final T value1, final T value2);

    abstract public <T extends StringType> boolean equals(final T value1, final T value2);

    @SafeVarargs
    public final <T extends CharSequenceType<T>> boolean equalsAny(final T value, final T ... otherValues) {
      if (otherValues == null) {
        return false;
      }
      for (T otherValue : otherValues) {
        if (equals(value, otherValue)) {
          return true;
        }
      }
      return false;
    }

    @SafeVarargs
    public final <T extends StringType> boolean equalsAny(final T value, final T ... otherValues) {
      if (otherValues == null) {
        return false;
      }
      for (T otherValue : otherValues) {
        if (equals(value, otherValue)) {
          return true;
        }
      }
      return false;
    }

    public final <T extends CharSequenceType<T>, C extends Collection<T>> boolean equalsAny(final T value, final C otherValues) {
      if (otherValues == null) {
        return false;
      }
      for (T otherValue : otherValues) {
        if (equals(value, otherValue)) {
          return true;
        }
      }
      return false;
    }

    public final <T extends StringType, C extends Collection<T>> boolean equalsAny(final T value, final C otherValues) {
      if (otherValues == null) {
        return false;
      }
      for (T otherValue : otherValues) {
        if (equals(value, otherValue)) {
          return true;
        }
      }
      return false;
    }

  }
}
