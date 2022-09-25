package org.typefactory;

import java.io.Serializable;

public interface Type<V, T extends Type<V, T>> extends Serializable {

  /**
   * Returns the internal value as set in the constructor. Will return {@code null} when internal value is {@code null}.
   *
   * @return the internal value as set in the constructor. Will return {@code null} when internal value is {@code null}.
   */
  V value();

  static <T extends Type> boolean isNull(final T type) {
    return type == null || type.isNull();
  }

  /**
   * <p>Returns {@code true} if the result of {@link #value()} is {@code null}.
   * You should aim to not allow the result of {@link #value()} to be {@code null}.</p>
   *
   * <p>This exists because we can't stop a developer creating an actual type instance
   * and then setting the internal value to {@code null}.</p>
   *
   * @return {@code true} if the result of {@link #value()} is {@code null}, and {@code false} otherwise.
   */
  default boolean isNull() {
    return value() == null;
  }


}
