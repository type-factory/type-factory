package org.typefactory.impl;

import org.typefactory.StringType;

public abstract class AbstractTypeParserTest {

  static class SomeType extends StringType {

    private SomeType(final String value) {
      super(value);
    }
  }

}