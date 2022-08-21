package org.datatypeproject;

import java.io.Serial;

public abstract class IntegerType extends NumberType<Integer, IntegerType> {

  @Serial
  private static final long serialVersionUID = 1083186934667437011L;

  protected IntegerType(final Integer value) {
    super(value);
  }
}
