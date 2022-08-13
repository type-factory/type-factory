package org.datatypeproject;

import java.io.Serializable;

public interface CodePointRange extends Comparable<CodePointRange>, Serializable {

  int getInclusiveFrom();

  int getInclusiveTo();

  CodePointRange copy();
}
