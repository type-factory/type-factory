package org.typefactory;

import java.io.Serial;
import java.util.Comparator;
import java.util.Objects;

public abstract class NumberType<V extends Number & Comparable<V>, T extends NumberType<V, T>>
    extends Number
    implements Type<V, T>, Comparable<T> {

  @Serial
  private static final long serialVersionUID = 6270340472680306382L;

  private final V value;

  protected NumberType(final V value) {
    this.value = value;
  }

  @Override
  public V value() {
    return value;
  }

  @Override
  public String toString() {
    return isNull() ? "" : value.toString();
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    return Objects.equals(value, ((NumberType<V, T>) o).value);
  }

  @Override
  public int hashCode() {
    return value == null ? 0 : value.hashCode();
  }

  @Override
  public int compareTo(T o) {
    return Objects.compare(value(), (o == null ? null : o.value()), Comparator.nullsFirst(Comparator.naturalOrder()));
  }


  @Override
  public int intValue() {
    return value.intValue();
  }

  @Override
  public long longValue() {
    return value.longValue();
  }

  @Override
  public float floatValue() {
    return value.floatValue();
  }

  @Override
  public double doubleValue() {
    return value.doubleValue();
  }
}
