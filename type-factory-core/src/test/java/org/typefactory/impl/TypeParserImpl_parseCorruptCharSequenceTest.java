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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.typefactory.InvalidValueException;
import org.typefactory.TypeParser;

class TypeParserImpl_parseCorruptCharSequenceTest extends AbstractTypeParserTest {

  static final TypeParser TYPE_PARSER = TypeParser.builder()
      // Accepting code points in the Caucasian Albanian alphabet U+10530..U+10563.
      // Each of these letters requires two chars in Java UTF-16.
      // Note that the test values are all Caucasian Albanian char-sequences.
      .acceptCodePoints(CAUCASIAN_ALBANIAN_ALPHABET_CODE_POINTS)
      .removeAllWhitespace()
      .build();

  @ParameterizedTest
  @EnumSource(CorruptCharSequence.class)
  void parseToString_throwsExceptionWhenHighOrLowSurrogateIsMissing(final CorruptCharSequence corruptCharSequence) {
    assertThatExceptionOfType(InvalidValueException.class)
        .isThrownBy(() -> TYPE_PARSER.parseToString(corruptCharSequence))
        .withMessage(corruptCharSequence.getExpectedErrorMessage());
  }

  @ParameterizedTest
  @EnumSource(CorruptCharSequence.class)
  void parseToStringReplacingInvalidCharacters_replacesOrphanHighOrLowSurrogates(final CorruptCharSequence corruptCharSequence) {
    final var parseResult = TYPE_PARSER.parseToStringReplacingInvalidCharacters(corruptCharSequence);
    assertThat(parseResult).isNotNull();
    assertThat(parseResult.parsedValueWasValid()).isFalse();
    assertThat(parseResult.parsedValueWasInvalid()).isTrue();
    assertThat(parseResult.parsedValue()).isEqualTo(corruptCharSequence.getExpectedValueWithInvalidCharacterReplacement());
  }

  @ParameterizedTest
  @EnumSource(CorruptCharSequence.class)
  void parseToStringRemovingInvalidCharacters_removesOrphanHighOrLowSurrogates(final CorruptCharSequence corruptCharSequence) {
    final var parseResult = TYPE_PARSER.parseToStringRemovingInvalidCharacters(corruptCharSequence);
    assertThat(parseResult).isNotNull();
    assertThat(parseResult.parsedValueWasValid()).isFalse();
    assertThat(parseResult.parsedValueWasInvalid()).isTrue();
    assertThat(parseResult.parsedValue()).isEqualTo(corruptCharSequence.getExpectedValueWithInvalidCharacterRemoval());
  }
}
