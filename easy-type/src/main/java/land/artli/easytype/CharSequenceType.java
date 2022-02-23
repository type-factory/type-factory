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

}
