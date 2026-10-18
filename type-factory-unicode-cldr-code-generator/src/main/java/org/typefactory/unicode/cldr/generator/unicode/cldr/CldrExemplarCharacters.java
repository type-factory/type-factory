/*
 * Copyright © 2021-2026 Evan Toliopoulos (typefactory.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.typefactory.unicode.cldr.generator.unicode.cldr;

import static java.util.Objects.requireNonNullElse;

import java.util.List;

public record CldrExemplarCharacters(
    String raw,
    List<Integer> codePoints,
    List<String> strings) {

  public CldrExemplarCharacters {
    raw = requireNonNullElse(raw, "");
    codePoints = List.copyOf(requireNonNullElse(codePoints, List.of()));
    strings = List.copyOf(requireNonNullElse(strings, List.of()));
  }

  private static final CldrExemplarCharacters EMPTY =
      new CldrExemplarCharacters("", List.of(), List.of());

  public static CldrExemplarCharacters empty() {
    return EMPTY;
  }

  public boolean isEmpty() {
    return EMPTY.equals(this);
  }

  @Override
  public String toString() {
    final StringBuilder s = new StringBuilder();
    if (!codePoints.isEmpty()) {
      s.append("[ ");
      for (Integer codePoint : codePoints) {
        if (codePoint == '\n' || codePoint == '\r' || codePoint == '\t') {
          s.append(String.format("\\u%04X", codePoint));
        } else {
          s.appendCodePoint(codePoint);
        }
        s.append(' ');
      }
      s.append(']');
    }
    if (!strings.isEmpty()) {
      if (s.length() > 1) {
        s.append('|');
      }
      for (String string : strings) {
        s.append(string);
        s.append('|');
      }
      s.setLength(s.length() - 1); // Remove the last '|'
    }
    return s.toString();
  }

  public String codePointsAsString() {
    final int [] codePointsArray = codePoints.stream().mapToInt(Integer::intValue).toArray();
    final String codePointsString = new String(codePointsArray, 0, codePointsArray.length);
    return codePointsString;
  }
}
