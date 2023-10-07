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
import org.typefactory.InvalidValueException;
import org.typefactory.TypeParser;

class TypeParserImpl_parseCorruptCharSequenceTest extends AbstractTypeParser_parseCorruptCharSequenceTest {

  @ParameterizedTest
  @EnumSource(CorruptCharSequence.class)
  void parseToString_throwsExceptionWhenHighOrLowSurrogateIsMissing(final CorruptCharSequence corruptCharSequence) {

    final var typeParser = TypeParser.builder()
        // Using the Caucasian Albanian alphabet U+10530..U+10563 as numbers to a radix of the alphabet size.
        // Each of these letters require two chars in Java UTF-8
        .acceptCodePoints("𐔰𐔱𐔲𐔳𐔴𐔵𐔶𐔷𐔸𐔹𐔺𐔻𐔼𐔽𐔾𐔿𐕀𐕁𐕂𐕃𐕄𐕅𐕆𐕇𐕈𐕉𐕊𐕋𐕌𐕍𐕎𐕏𐕐𐕑𐕒𐕓𐕔𐕕𐕖𐕗𐕘𐕙𐕚𐕛𐕜𐕝𐕞𐕟𐕠𐕡𐕢𐕣".codePoints().toArray())
        .removeAllWhitespace()
        .build();

    assertThatExceptionOfType(InvalidValueException.class)
        .isThrownBy(() -> typeParser.parseToString(corruptCharSequence))
        .withMessageMatching(corruptCharSequence.expectedErrorMessage);
  }
}
