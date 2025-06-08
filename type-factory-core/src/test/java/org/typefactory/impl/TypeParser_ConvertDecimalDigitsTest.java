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
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.TypeParser;
import org.typefactory.TypeParserBuilderException;

public class TypeParser_ConvertDecimalDigitsTest {

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      ZERO_DIGIT | VALUE      |  EXPECTED
      0          | 0123456789 |  0123456789
      0          | 9876543210 |  9876543210
      0          | 01234ABCDE |  01234ABCDE
      0          | ０１２３４５６７８９ | 0123456789
      0          | ０１２３４ＡＢＣＤＥ | 01234ＡＢＣＤＥ
      1          | 0123456789 | TypeParserBuilderException
      A          | 0123456789 | TypeParserBuilderException
      ०          | 0123456789 | ०१२३४५६७८९
      ൦          | 0123456789 | ൦൧൨൩൪൫൬൭൮൯
      ०          | ०१२३४५६७८९  | ०१२३४५६७८९
      0          | ०१२३४५६७८९  | 0123456789
      ०          | ०१२३४56789  | ०१२३४५६७८९
      0          | ०१२३४56789  | 0123456789
      १          | ०१२३४५६७८९  | TypeParserBuilderException
      अ          | ०१२३४५६७८९  | TypeParserBuilderException
      0          | ൦൧൨൩൪൫൬൭൮൯ | 0123456789
      ൦          | ൦൧൨൩൪൫൬൭൮൯ | ൦൧൨൩൪൫൬൭൮൯
      ൦          | ൦൧൨൩൪56789  | ൦൧൨൩൪൫൬൭൮൯
      0          | ൦൧൨൩൪56789  | 0123456789
      ൩         | ൦൧൨൩൪൫൬൭൮൯ | TypeParserBuilderException
      ഖ         | ൦൧൨൩൪൫൬൭൮൯ | TypeParserBuilderException
      """, delimiter = '|', useHeadersInDisplayName = true)
  public void testConvertDecimalDigits(
      final char zeroDigit,
      final String value,
      final String expected) {

    final var parserBuilder = TypeParser.builder()
        .acceptAllUnicodeLetters()
        .acceptAllUnicodeDecimalDigits();

    if ("TypeParserBuilderException".equalsIgnoreCase(expected)) {

      assertThatExceptionOfType(TypeParserBuilderException.class)
          .isThrownBy(() -> parserBuilder.convertAnyDecimalDigitsToDigitsStartingWithZeroDigit(zeroDigit))
          .withMessageContaining("An invalid zero digit has been configured for the type parser – the zero digit character must represent zero and the subsequent nine characters must be valid unicode decimal digits.");

    } else {

      final var parser = parserBuilder
          .convertAnyDecimalDigitsToDigitsStartingWithZeroDigit(zeroDigit)
          .build();

      final var actual = parser.parseToString(value);
      assertThat(actual).isEqualTo(expected);
    }
  }
}
