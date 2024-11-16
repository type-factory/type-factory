package org.typefactory.impl;

class StringBuilderUtils {

  private StringBuilderUtils() {
    // Prevent instantiation.
  }

  static void appendHexWithZeroPadding(final StringBuilder s, final int value) {
    final var hexValue = Integer.toHexString(value);
    switch (hexValue.length()) {
      case 1 -> s.append("0x0000000").append(hexValue);
      case 2 -> s.append("0x000000").append(hexValue);
      case 3 -> s.append("0x00000").append(hexValue);
      case 4 -> s.append("0x0000").append(hexValue);
      case 5 -> s.append("0x000").append(hexValue);
      case 6 -> s.append("0x00").append(hexValue);
      case 7 -> s.append("0x0").append(hexValue);
      default -> s.append("0x").append(hexValue);
    }
  }
}
