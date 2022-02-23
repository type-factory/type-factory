package land.artli.easytype;

import java.io.Serial;
import java.util.Objects;

public abstract class StringType<T extends StringType<T>> implements CharSequenceType<T> {

  @Serial
  private static final long serialVersionUID = -1263119529456254274L;

  private final String value;

  protected StringType(final String value) {
    this.value = value;
  }

  @Override
  public String value() {
    return value;
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
    return Objects.equals(value, ((StringType<T>) o).value);
  }

  @Override
  public int hashCode() {
    return value == null ? 0 : value.hashCode();
  }
}
