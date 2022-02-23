package land.artli.easytype;

import java.io.Serial;

public abstract class LongType<T extends LongType<T>> extends NumberType<Long, T> {

  @Serial
  private static final long serialVersionUID = -4358945076531290900L;

  protected LongType(final Long value) {
    super(value);
  }
}
