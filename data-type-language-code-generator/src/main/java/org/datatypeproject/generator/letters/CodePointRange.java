package org.datatypeproject.generator.letters;

class CodePointRange {

  final int fromCodePoint;
  final int toCodePoint;

  CodePointRange(int fromCodePoint, int toCodePoint) {
    this.fromCodePoint = fromCodePoint;
    this.toCodePoint = toCodePoint;
  }

  static CodePointRange range(char fromChar, char toChar) {
    return new CodePointRange(fromChar, toChar);
  }

  static CodePointRange range(int fromCodePoint, int toCodePoint) {
    return new CodePointRange(fromCodePoint, toCodePoint);
  }

}
