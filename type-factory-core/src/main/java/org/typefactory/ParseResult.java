package org.typefactory;

public interface ParseResult {

  String parsedValue();

  boolean parsedValueWasValid();

  boolean parsedValueWasInvalid();

  int [] invalidCodePoints();

  String invalidCodePointsToString();
}
