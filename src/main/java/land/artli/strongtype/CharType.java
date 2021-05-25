package land.artli.strongtype;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public abstract class CharType<T extends CharType> implements CharSequence, Comparable<T>, Serializable {

  @Serial
  private static final long serialVersionUID = -1263119529456254274L;

  private final String value;

  protected CharType(final String value) {
    this.value = value;
  }

  @Override
  public int length() {
    return value.length();
  }

  @Override
  public char charAt(int index) {
    return value.charAt(index);
  }

  @Override
  public CharSequence subSequence(int start, int end) {
    return value.subSequence(start, end);
  }

  @Override
  public String toString() {
    return value;
  }

  @Override
  public int compareTo(T o) {
    return o == null ? 1 : this.value.compareTo(((CharType<T>)o).value);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || !this.getClass().isAssignableFrom(o.getClass())) {
      return false;
    }
    return Objects.equals(value, ((CharType<T>)o).value);
  }

  @Override
  public int hashCode() {
    return value == null ? 0 : value.hashCode();
  }
}
