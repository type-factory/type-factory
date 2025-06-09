package org.typefactory.impl;

import org.typefactory.ParseResult;

/**
 *
 */
final class ParseResultImpl implements ParseResult {

  private String parsedValue;
  private PrimitiveSortedSetOfInt invalidCodePoints = new PrimitiveSortedSetOfInt();

  ParseResultImpl() {}

  @Override
  public boolean parsedValueWasValid() {
    return invalidCodePoints.size() == 0;
  }

  @Override
  public boolean parsedValueWasInvalid() {
    return invalidCodePoints.size() > 0;
  }

  /**
   * <p>Returns a valid parsed value when {@link #parsedValueWasValid()} is {@code true}. Otherwise, returns an invalid parsed value with all
   * invalid characters replaced with the Unicode Replacement Character � {@code (U+FFFD)} when {@link #parsedValueWasInvalid()} is {@code true}.</p>
   *
   * <p>For example, if the parser is configured to accept all Unicode letters and decimal digits, then for the following input values the
   * parsed value will be as shown below:</p>
   *
   * <pre>{@code
   * INPUT_VALUE | PARSED_VALUE
   * abc123      | abc123
   * abc123!     | abc123�
   * abc123!@#   | abc123���
   * }</pre>
   *
   * @return a valid parsed value when {@link #parsedValueWasValid()} is {@code true}. Otherwise, returns an invalid parsed value with all invalid
   * characters replaced with the Unicode Replacement Character � {@code (U+FFFD)} when {@link #parsedValueWasInvalid()} is {@code true}.
   */
  @Override
  public String parsedValue() {
    return parsedValue;
  }

  void setParsedValue(String parsedValue) {
    this.parsedValue = parsedValue;
  }

  void addInvalidCodePoint(int codePoint) {
    invalidCodePoints.add(codePoint);
  }

  @Override
  public int [] invalidCodePoints() {
    return invalidCodePoints.toArray();
  }

  @Override
  public String invalidCodePointsToString() {
    final StringBuilder s = new StringBuilder(256);
    s.append('[');
    for (int codePoint : invalidCodePoints.toArray()) {
      s.append(ExceptionUtils.unicodeHexCode(codePoint)).append(", ");
    }
    if (s.length() > 1) {
      s.setLength(s.length() - 2); // remove last ", "
    }
    s.append(']');
    return s.toString();
  }

  @Override
  public String toString() {
    final StringBuilder s = new StringBuilder(256);
    s.append("{parsedValue: ").append(parsedValue());
    if (parsedValueWasValid()) {
      s.append(", parsedValueWasValid: true");
    } else {
      s.append(", parsedValueWasValid: false, invalidCodePoints: [");
      for (int codePoint : invalidCodePoints.toArray()) {
        s.append(ExceptionUtils.unicodeHexCode(codePoint)).append(", ");
      }
      s.setLength(s.length() - 2);
      s.append(']');
    }
    s.append('}');
    return s.toString();
  }
}
