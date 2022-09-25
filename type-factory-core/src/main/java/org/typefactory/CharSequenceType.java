package org.typefactory;

public interface CharSequenceType<T extends CharSequenceType<T>> extends Type<CharSequence, T>, Comparable<T>, CharSequence {

  default boolean isBlank() {
    if (isNull() || value().isEmpty()) {
      return true;
    }
    final CharSequence value = value();
    final int length = value.length();
    int i = 0;
    while (i < length) {
      final char ch = value().charAt(i++);
      if ((Character.isSurrogate(ch)
          && ++i < length
          && !Character.isWhitespace(Character.toCodePoint(ch, value.charAt(i))))
          || !Character.isWhitespace(ch)) {
        return false;
      }
    }
    return true;
  }

  @Override
  default int length() {
    return isNull() ? 0 : value().length();
  }

  @Override
  default char charAt(int index) {
    if (isEmpty()) {
      throw new StringIndexOutOfBoundsException("String index out of range: " + index);
    }
    return value().charAt(index);
  }

  @Override
  default CharSequence subSequence(int start, int end) {
    if (isEmpty()) {
      throw new StringIndexOutOfBoundsException(
          "begin " + start + ", end " + end + ", length " + length());
    }
    return value().subSequence(start, end);
  }

  @Override
  default int compareTo(T o) {
    if (o == null) {
      if (isNull()) {
        return 0;
      }
      return 1;
    }
    final CharSequence v1 = value();
    final CharSequence v2 = o.value();
    if (v1 == null) {
      if (v2 == null) {
        return 0;
      }
      return -1;
    }
    if (v2 == null) {
      return 1;
    }
    if (v1 instanceof String vs1 && v2 instanceof String vs2) {
      return vs1.compareTo(vs2);
    }
    final int l1 = v1.length();
    final int l2 = v2.length();
    int result = 0;
    int i1 = 0;
    int i2 = 0;
    while (i1 < l1 && i2 < l2) {
      result = v1.charAt(i1++) - v2.charAt(i2++);
      if (result != 0) {
        return result;
      }
    }
    if (i1 < l1 ) {
      return 1;
    }
    if (i2 < l2 ) {
      return -1;
    }
    return 0;
  }
}
