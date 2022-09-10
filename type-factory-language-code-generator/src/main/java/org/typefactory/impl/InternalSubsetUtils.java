package org.typefactory.impl;

public class InternalSubsetUtils {

  public static int getInclusiveFrom(final char codePointRange) {
    return SubsetUtils.getInclusiveFrom(codePointRange);
  }

  public static int getInclusiveTo(final char codePointRange) {
    return SubsetUtils.getInclusiveTo(codePointRange);
  }

}
