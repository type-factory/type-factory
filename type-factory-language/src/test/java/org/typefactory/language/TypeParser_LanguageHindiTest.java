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
package org.typefactory.language;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.typefactory.InvalidValueException;
import org.typefactory.MessageCode;
import org.typefactory.TypeParser;

class TypeParser_LanguageHindiTest extends AbstractTypeParserTest {

  static final TypeParser TYPE_PARSER =
      TypeParser.builder()
          .messageCode(MessageCode.of("must.be.hindi.letters.only", "Must be made up of Hindi letters only."))
          .toCharacterNormalizationFormNFC()
          .acceptSubset(Letters.HINDI_hi)
          .normalizeWhitespace()
          .build();

  @ParameterizedTest
  @ValueSource(strings = {
      "खरगोश", // Hindi 'Rabbit'
      "मधुमक्खी", // Hindi 'Bee'
      "डॉल्फिन", // Hindi 'Dolphin'
      "जिराफ़", // Hindi 'Giraffe
      "उल्लू", // Hindi 'Owl
      "बाघ", // Hindi 'Tiger
      "कबूतर", // Hindi 'Pigeon'
  })
  void should_parse_accepting_only_hindi_letters(final String value) {
    Assertions.assertThat(TYPE_PARSER.parseToString(value)).hasToString(value);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE            | ERROR_DESCRIPTION            | COMMENT
      \uD805\uDE13ल्लू   | 𑘓 U+011613 MODI LETTER CA.   | Hindi 'उल्लू' (Owl) with '𑘓' (ca) from the Modi script instead of 'उ' (u) from the Devanagari script
      बा\uD805\uDE11    | 𑘑 U+011611 MODI LETTER GHA.  | Hindi 'बाघ' (Tiger) with '𑘑' (gha) from the Modi script instead of 'घ' (gha) from the Devanagari script
      कबू\uD805\uDE1Dर   | 𑘝 U+01161D MODI LETTER TA.   | Hindi 'कबूतर' (Pigeon) with '𑘝' (ta) from the Modi script instead of 'त' (ta) from the Devanagari script
      """, delimiter = '|', useHeadersInDisplayName = true)
  void should_throw_exception_with_non_hindi_letters(final String value, final String expectedErrorDescription) {
    Assertions.assertThatExceptionOfType(InvalidValueException.class)
        .isThrownBy(() -> TYPE_PARSER.parseToString(value))
        .withMessage("Must be made up of Hindi letters only. Invalid value - invalid character " + expectedErrorDescription);
  }

}
