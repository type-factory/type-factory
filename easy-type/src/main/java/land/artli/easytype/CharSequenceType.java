package land.artli.easytype;

import java.util.Comparator;
import java.util.Objects;

public interface CharSequenceType<T extends CharSequenceType<T>> extends Type<String, T>, Comparable<T>, CharSequence {

  default boolean isBlank() {
    return isNull() || value().isBlank();
  }

  @Override
  default int length() {
    return isNull() ? 0 : value().length();
  }

  @Override
  default char charAt(int index) {
    if (isNull()) {
      throw new StringIndexOutOfBoundsException("String index out of range: " + index);
    }
    return value().charAt(index);
  }

  @Override
  default CharSequence subSequence(int start, int end) {
    if (isNull()) {
      throw new StringIndexOutOfBoundsException(
          "begin " + start + ", end " + end + ", length " + length());
    }
    return value().subSequence(start, end);
  }

  @Override
  default int compareTo(T o) {
    return Objects.compare(value(), (o == null ? null : o.value()), Comparator.nullsFirst(Comparator.naturalOrder()));
  }

  /**
   * <p>A null-safe method to return the value of this type as a string.</p>
   *
   * <p>A {@code null} will be returned for a null value.</p>
   *
   * @param type the data-type value that want as a string value.
   * @param <T> the data-type class type.
   * @return the data-type value as a string or {@code null} if the specified {@code type} argument was null;
   */
  static <T extends CharSequenceType<T>> String toString(T type) {
    return type == null ? null : type.value();
  }
}
