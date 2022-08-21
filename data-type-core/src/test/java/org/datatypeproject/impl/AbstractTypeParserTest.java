package org.datatypeproject.impl;

import org.datatypeproject.StringType;

public abstract class AbstractTypeParserTest {

  static class SomeType extends StringType {

    private SomeType(final String value) {
      super(value);
    }
  }

}
