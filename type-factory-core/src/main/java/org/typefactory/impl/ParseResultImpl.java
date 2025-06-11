/*
   Copyright 2021-2022 Evan Toliopoulos (typefactory.org)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package org.typefactory.impl;

import org.typefactory.ParseResult;

/**
 *
 */
final class ParseResultImpl implements ParseResult {

  private String parsedValue;
  private final PrimitiveSortedSetOfInt invalidCodePoints = new PrimitiveSortedSetOfInt();

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
      s.append(", parsedValueWasValid: true, invalidCodePoints: []");
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
