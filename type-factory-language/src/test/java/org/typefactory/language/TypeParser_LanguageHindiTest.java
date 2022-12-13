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
import org.junit.jupiter.params.provider.ValueSource;
import org.typefactory.ErrorCode;
import org.typefactory.InvalidValueException;
import org.typefactory.TypeParser;

class TypeParser_LanguageHindiTest extends AbstractTypeParserTest {

  static final TypeParser TYPE_PARSER =
      TypeParser.builder()
          .errorCode(ErrorCode.of("must.be.hindi.letters.only", "Must be made up of Hindi letters only."))
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
  @ValueSource(strings = {
      "\uD805\uDE13ल्लू", // Hindi 'उल्लू' (Owl) with '𑘓' (ca) from the Modi script instead 'उ' (u) from the Devanagari script
      "बा\uD805\uDE11", // Hindi 'बाघ' (Tiger) with '𑘑' (gha) from the Modi script instead 'घ' (gha) from the Devanagari script
      "कबू\uD805\uDE1Dर", // Hindi 'कबूतर' (Pigeon) with '𑘝' (ta) from the Modi script instead 'त' (ta) from the Devanagari script
  })
  void should_throw_exception_with_non_hindi_letters(final String value) {
    Assertions.assertThatExceptionOfType(InvalidValueException.class)
        .isThrownBy(() -> TYPE_PARSER.parseToString(value))
        .withMessageMatching("Must be made up of Hindi letters only. Invalid value - invalid character '[^']+'.");
  }

}
