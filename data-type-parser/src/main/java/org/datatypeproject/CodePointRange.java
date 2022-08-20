package org.datatypeproject;

import java.io.Serializable;

public interface CodePointRange extends Comparable<CodePointRange>, Serializable {

  int getInclusiveFrom();

  int getInclusiveTo();

  boolean contains(final int codePoint);

  CodePointRange copy();

  static CodePointRange of(final int inclusiveFrom, final int inclusiveTo) {
    return new CodePointRangeImpl(inclusiveFrom, inclusiveTo);
  }
}
