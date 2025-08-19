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
import org.junit.jupiter.params.provider.EnumSource;
import org.typefactory.IntegerTypeParser;
import org.typefactory.InvalidValueException;

class IntegerTypeParserImpl_parseCorruptCharSequenceTest extends AbstractTypeParserTest {

  @ParameterizedTest
  @EnumSource(CorruptCharSequence.class)
  void parse_throwsExceptionWhenHighOrLowSurrogateIsMissing(final CorruptCharSequence corruptCharSequence) {

    final var integerTypeParser = IntegerTypeParser.builder()
        .minValueExclusive(Integer.MIN_VALUE)
        .maxValueExclusive(Integer.MAX_VALUE)
        // Using the Caucasian Albanian alphabet U+10530..U+10563 as numbers to a radix of the alphabet size.
        // Each of these letters require two chars in Java UTF-8
        .allowCustomBaseNumbers(CAUCASIAN_ALBANIAN_ALPHABET_CODE_POINTS)
        .ignoreAllWhitespace()
        .build();

    assertThatExceptionOfType(InvalidValueException.class)
        .isThrownBy(() -> integerTypeParser.parse(corruptCharSequence))
        .withMessage(corruptCharSequence.getExpectedErrorMessage());
  }
}
