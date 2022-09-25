package org.typefactory;

import java.io.Serial;
import java.util.Objects;

public abstract class StringType implements CharSequenceType<StringType> {

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

  /**
   * Returns the internal value as returned by {@link #value()} or empty string {@code ""} when internal value is {@code null}.
   *
   * @return the internal value as returned by {@link #value()} or empty string {@code ""} when internal value is {@code null}.
   */
  @Override
  public String toString() {
    return isNull() ? "" : value;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    return Objects.equals(value, ((StringType) o).value);
  }

  @Override
  public int hashCode() {
    return value == null ? 0 : value.hashCode();
  }
}
