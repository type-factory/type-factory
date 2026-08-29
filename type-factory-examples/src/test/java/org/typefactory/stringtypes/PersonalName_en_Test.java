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
package org.typefactory.stringtypes;

import static org.assertj.core.api.Assertions.assertThatObject;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.typefactory.InvalidValueException;

class PersonalName_en_Test {

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = "  ")
  void of_shouldReturnNull(final String value) {
    final var actual = PersonalName_en.of(value);
    assertThatObject(actual).isNull();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      RAW_VALUE                  | EXPECTED_VALUE
      John                       | John
      ' John '                   | John
      '  John   Brown  '         | John Brown
      Marie-Claire               | Marie-Claire
      '\t Irene \t  Curie \t'    | Irene Curie
      Francois                   | Francois
      'Anne\tMarie'              | Anne Marie
      O’Malley                   | O'Malley
      John‐Paul                  | John-Paul
      John‑Paul                  | John-Paul
      John–Paul                  | John-Paul
      """, delimiter = '|', useHeadersInDisplayName = true)
  void of_shouldCreatePersonalNameInstancesAsExpected(final String value, final String expected) {

    final var actual = PersonalName_en.of(value);

    Assertions.assertThat(actual.value()).isEqualTo(expected);
    assertThatObject(actual).hasToString(expected);
  }

  @Test
  void of_shouldAcceptNamesUpToSixtyCharacters() {

    final var value = "A".repeat(60);
    final var actual = PersonalName_en.of(value);
    assertThatObject(actual).hasToString(value);
  }

  @Test
  void of_shouldNotAcceptNamesOverSixtyCharacters() {

    final var longName = "Marie-Claire Marie-Claire Marie-Claire Marie-Claire Marie-Claire";

    assertThatThrownBy(() -> PersonalName_en.of(longName))
        .isInstanceOf(InvalidValueException.class)
        .hasMessage(PersonalName_en.ERROR_MESSAGE.defaultMessage() + " Invalid value - too long, maximum length is 60.")
        .hasFieldOrPropertyWithValue("parserErrorMessage", "Invalid value - too long, maximum length is 60.");
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      RAW_VALUE    | EXPECTED_EXCEPTION_MESSAGE
      John.        | Invalid value - invalid character . U+002E FULL STOP.
      John@        | Invalid value - invalid character @ U+0040 COMMERCIAL AT.
      John2        | Invalid value - invalid character 2 U+0032 DIGIT TWO.
      Marie-Сlaire | Invalid value - invalid character С U+0421 CYRILLIC CAPITAL LETTER ES.
      Αllen        | Invalid value - invalid character Α U+0391 GREEK CAPITAL LETTER ALPHA.
      """, delimiter = '|', useHeadersInDisplayName = true)
  void of_shouldThrowExceptionForInvalidValues(final String value, final String expectedExceptionMessage) {

    assertThatThrownBy(() -> PersonalName_en.of(value))
        .isInstanceOf(InvalidValueException.class)
        .hasMessage(PersonalName_en.ERROR_MESSAGE.defaultMessage() + " " + expectedExceptionMessage)
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedExceptionMessage);
  }
}
