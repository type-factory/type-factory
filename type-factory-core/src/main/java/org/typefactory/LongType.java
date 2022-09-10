package org.typefactory;

import java.io.Serial;

public abstract class LongType extends NumberType<Long, LongType> {

  @Serial
  private static final long serialVersionUID = -4358945076531290900L;

  protected LongType(final Long value) {
    super(value);
  }
}
