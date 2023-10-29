package org.typefactory;

import java.io.Serial;

public class TypeParserBuilderException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 877160253922884601L;

  public TypeParserBuilderException(String message) {
    super(message);
  }
}
