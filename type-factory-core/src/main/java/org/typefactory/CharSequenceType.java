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
package org.typefactory;

public interface CharSequenceType<T extends CharSequenceType<T>> extends Type<CharSequence, T>, Comparable<T>, CharSequence {

  /**
   * <p>Returns {@code true} if the CharSequenceType value is {@link CharSequenceType#isEmpty() empty} or
   * contains only {@link Character#isWhitespace(int) white-space} code points. Otherwise returns {@code false}.</p>
   *
   * @return {@code true} if the CharSequenceType value is {@link CharSequenceType#isEmpty() empty} or contains only
   * {@link Character#isWhitespace(int) white-space} code points. Otherwise returns {@code false}.
   * @see CharSequenceType#isEmpty()
   * @see Character#isWhitespace(int)
   */
  default boolean isBlank() {
    return isNull() || CharSequenceUtils.isBlank(value());
  }

  static <T extends CharSequenceType<T>> boolean isEmpty(final T value) {
    return value == null || value.isEmpty();
  }

  static <T extends CharSequenceType<T>> T defaultIfEmpty(final T value, final T defaultValue) {
    return isEmpty(value) ? defaultValue : value;
  }

  static <T extends CharSequenceType<T>> boolean isBlank(final T value) {
    return value == null || value.isBlank();
  }

  static <T extends CharSequenceType<T>> T defaultIfBlank(final T value, final T defaultValue) {
    return isBlank(value) ? defaultValue : value;
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

  default int compareToIgnoreCase(T o) {
    if (this == o) {
      return 0;
    }
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
      return vs1.compareToIgnoreCase(vs2);
    }
    final int l1 = v1.length();
    final int l2 = v2.length();
    int i1 = 0;
    int i2 = 0;
    char c1;
    char c2;
    while (i1 < l1 && i2 < l2) {
      c1 = Character.toUpperCase(v1.charAt(i1++));
      c2 = Character.toUpperCase(v2.charAt(i2++));
      if (c1 != c2) {
        c1 = Character.toLowerCase(c1);
        c2 = Character.toLowerCase(c2);
        if (c1 != c2) {
          return c1 - c2;
        }
      }
    }
    if (i1 < l1) {
      return 1;
    }
    if (i2 < l2) {
      return -1;
    }
    return 0;
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
    if (this == o) {
      return 0;
    }
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
    int result;
    int i1 = 0;
    int i2 = 0;
    while (i1 < l1 && i2 < l2) {
      result = v1.charAt(i1++) - v2.charAt(i2++);
      if (result != 0) {
        return result;
      }
    }
    if (i1 < l1) {
      return 1;
    }
    if (i2 < l2) {
      return -1;
    }
    return 0;
  }

  default boolean equalsIgnoreCase(final T o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return this.isNull();
    }
    if (this.getClass() != o.getClass()) {
      return false;
    }
    return this.compareToIgnoreCase(o) == 0;
  }

  static <T extends CharSequenceType<T>> boolean equalsIgnoreCase(final T value1, final T value2) {
    if (value1 != null) {
      return value1.equalsIgnoreCase(value2);
    }
    return Type.isNull(value2);
  }
}
