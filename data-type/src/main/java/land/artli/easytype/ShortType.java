package land.artli.easytype;

import java.io.Serial;

public abstract class ShortType extends NumberType<Short, ShortType> {

  @Serial
  private static final long serialVersionUID = -4177490021343200284L;

  protected ShortType(final Short value) {
    super(value);
  }
}
