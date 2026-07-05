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
package org.typefactory.unicode.cldr.generator.letters;

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
    return raw;
  }
}
