package org.typefactory.impl;

class StringBuilderUtils {

  private StringBuilderUtils() {
    // Prevent instantiation.
  }

  static void appendHexWithZeroPadding(final StringBuilder s, final int value) {
    if (value < 0) {
      s.append("0x");
    } else if (value < 0x0000_0010) {
      s.append("0x0000000");
    } else if (value < 0x0000_0100) {
      s.append("0x000000");
    } else if (value < 0x0000_1000) {
      s.append("0x00000");
    } else if (value < 0x0001_0000) {
      s.append("0x0000");
    } else if (value < 0x0010_0000) {
      s.append("0x000");
    } else if (value < 0x0100_0000) {
      s.append("0x00");
    } else if (value < 0x1000_0000) {
      s.append("0x0");
    } else {
      s.append("0x");
    }
    s.append(Integer.toHexString(value));
  }
}
