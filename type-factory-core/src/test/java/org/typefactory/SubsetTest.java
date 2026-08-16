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
package org.typefactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.typefactory.assertions.TypeFactoryAssertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SubsetTest {

  @Test
  void static_of_usesBuilderToCreateNewSubsetWhenPassedArray() {

    final Subset subset1 = Subset.builder().includeChars('A', 'B', 'C').build();
    final Subset subset2 = Subset.builder().includeChars('X', 'Y', 'Z').build();

    final Subset actual = Subset.of(subset1, subset2);

    assertThat(actual.contains('A')).isTrue();
    assertThat(actual.contains('B')).isTrue();
    assertThat(actual.contains('C')).isTrue();
    assertThat(actual.contains('X')).isTrue();
    assertThat(actual.contains('Y')).isTrue();
    assertThat(actual.contains('Z')).isTrue();
  }

  @Test
  void static_of_usesBuilderToCreateNewSubsetWhenPassedIterable() {

    final Subset subset1 = Subset.builder().includeChars('A', 'B', 'C').build();
    final Subset subset2 = Subset.builder().includeChars('X', 'Y', 'Z').build();

    final Subset actual = Subset.of(List.of(subset1, subset2));

    assertThat(actual.contains('A')).isTrue();
    assertThat(actual.contains('B')).isTrue();
    assertThat(actual.contains('C')).isTrue();
    assertThat(actual.contains('X')).isTrue();
    assertThat(actual.contains('Y')).isTrue();
    assertThat(actual.contains('Z')).isTrue();
  }

  @Test
  void toPattern_shouldRenderEmptySubsetAsAnEmptyCharacterClass() {
    final Subset actual = Subset.builder().build();

    assertThat(actual).isEmpty();
    assertThat(actual.toPattern()).isEqualTo("[]");
  }

  @ParameterizedTest(name = "{0}")
  @CsvSource(
      delimiter = '|',
      useHeadersInDisplayName = true,
      textBlock = """
          scenario                      | subsetCharacters     | expectedPattern
          single character              | A                    | [A]
          single space character        | ' '                  | [\\u0020]
          single newline character      | '\t'                 | [\\u0009]
          single control character      | '\u000B'             | [\\u000b]
          two-character range           | AB                   | [AB]
          escaped consecutive range     | '\u001F\u0020'       | [\\u001f\\u0020]
          escaped range                 | '\u001E\u001F\u0020' | [\\u001e-\\u0020]
          three-character range         | ABC                  | [A-C]
          two three-character ranges    | ABCabc               | [A-Ca-c]
          mixed ranges and characters   | ABCHabch             | [A-CHa-ch]
          mixed ranges                  | ABC abc\txyz         | [\\u0009\\u0020A-Ca-cx-z]
          """)
  void toPattern_shouldRenderExpectedPatterns(
      final String scenario, final String subsetCharacters, final String expectedPattern) {

    final Subset actual = Subset.builder()
        .includeCodePoints(subsetCharacters.codePoints().toArray())
        .build();

    assertThat(actual).isNotEmpty();
    assertThat(actual.toPattern()).isEqualTo(expectedPattern);
  }



}
