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

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.TypeParser;

class TypeParser_RemoveCharacterTest extends AbstractTypeParserTest {

  @ParameterizedTest
  @CsvSource(value = {
      " 1234-ABCD   | -   | 1234ABCD ",
      " 12 34-AB_CD | - _ | 1234ABCD ",
      " 12.34-AB.CD | -.  | 1234ABCD ",
      " 12/34-AB/CD | -/  | 1234ABCD ",
  }, delimiter = '|')
  void typeParser_shouldRemoveCharacters(final String value, final String charactersToRemove, final String expected) {

    final TypeParser typeParser =
        TypeParser.builder()
            .minSize(1)
            .maxSize(50)
            .toUpperCase()
            .removeAllChars(charactersToRemove.toCharArray())
            .acceptDigits0to9()
            .acceptLettersAtoZ()
            .build();

    Assertions.assertThat(typeParser.parseToString(value)).isEqualTo(expected);
  }

}
