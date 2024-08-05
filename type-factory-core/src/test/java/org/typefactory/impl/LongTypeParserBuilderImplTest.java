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

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.LongTypeParser;
import org.typefactory.TypeParserBuilderException;
import org.typefactory.testutils.CodePointArrayConverter;

class LongTypeParserBuilderImplTest {

  @ParameterizedTest
  @CsvSource(textBlock = """
      CUSTOM_BASE_CODEPOINTS | EXPECTED_MESSAGE
      [0, 0]                 | Custom numeric-base character-set must not contain duplicates – check both your custom base character-set and case-sensitivity of the parser.
      [0, 1, 1]              | Custom numeric-base character-set must not contain duplicates – check both your custom base character-set and case-sensitivity of the parser.
      [0, 1, 1, 2]           | Custom numeric-base character-set must not contain duplicates – check both your custom base character-set and case-sensitivity of the parser.
      [0, 1, 2, 2]           | Custom numeric-base character-set must not contain duplicates – check both your custom base character-set and case-sensitivity of the parser.
      [a, a]                 | Custom numeric-base character-set must not contain duplicates – check both your custom base character-set and case-sensitivity of the parser.
      [a, b, b]              | Custom numeric-base character-set must not contain duplicates – check both your custom base character-set and case-sensitivity of the parser.
      [a, b, b, c]           | Custom numeric-base character-set must not contain duplicates – check both your custom base character-set and case-sensitivity of the parser.
      [a, b, c, c]           | Custom numeric-base character-set must not contain duplicates – check both your custom base character-set and case-sensitivity of the parser.
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void setCustomBaseNumbers_duplicateBaseCharactersThrowsException(
      @ConvertWith(CodePointArrayConverter.class) final int[] customBaseCodePoints,
      final String expectedMessage) {

    final var builder = LongTypeParser.builder()
        .setCustomRadix(customBaseCodePoints);

    assertThatExceptionOfType(TypeParserBuilderException.class)
        .isThrownBy(builder::build)
        .withMessageStartingWith(expectedMessage);
  }

}
