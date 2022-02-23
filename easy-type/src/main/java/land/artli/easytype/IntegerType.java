package land.artli.easytype;

import java.io.Serial;

public abstract class IntegerType<T extends IntegerType<T>> extends NumberType<Integer, T> {

  @Serial
  private static final long serialVersionUID = 1083186934667437011L;

  protected IntegerType(final Integer value) {
    super(value);
  }
}
