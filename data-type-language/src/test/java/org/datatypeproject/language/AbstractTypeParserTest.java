package org.datatypeproject.language;

import org.datatypeproject.StringType;

public abstract class AbstractTypeParserTest {

  static class SomeType extends StringType {

    private SomeType(final String value) {
      super(value);
    }
  }

}
