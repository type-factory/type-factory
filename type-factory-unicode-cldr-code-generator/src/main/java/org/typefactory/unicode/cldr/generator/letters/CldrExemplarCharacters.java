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

import java.util.List;

public final class CldrExemplarCharacters {

  public record Range(int inclusiveFrom, int inclusiveTo) {
  }

  private static final CldrExemplarCharacters EMPTY = new CldrExemplarCharacters(List.of(), List.of());

  private final List<Range> ranges;
  private final List<String> strings;

  private CldrExemplarCharacters(final List<Range> ranges, final List<String> strings) {
    this.ranges = List.copyOf(ranges);
    this.strings = List.copyOf(strings);
  }

  public static CldrExemplarCharacters empty() {
    return EMPTY;
  }

  public static CldrExemplarCharacters of(final List<Range> ranges, final List<String> strings) {
    if ((ranges == null || ranges.isEmpty()) && (strings == null || strings.isEmpty())) {
      return EMPTY;
    }
    return new CldrExemplarCharacters(ranges == null ? List.of() : ranges, strings == null ? List.of() : strings);
  }

  public List<Range> ranges() {
    return ranges;
  }

  public List<String> strings() {
    return strings;
  }

  public boolean isEmpty() {
    return ranges.isEmpty() && strings.isEmpty();
  }
}
