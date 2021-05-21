package evantoli.type;

import java.util.Objects;

public abstract class Type implements CharSequence {

  private final String value;

  protected Type(final String value) {
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
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || !this.getClass().isAssignableFrom(o.getClass())) {
      return false;
    }
    return Objects.equals(value, ((Type)o).value);
  }

  @Override
  public int hashCode() {
    return value == null ? 0 : value.hashCode();
  }
}
