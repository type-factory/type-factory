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

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.typefactory.TypeParser;

class TypeParser_NullHandlingTest extends AbstractTypeParserTest {
  
  @ParameterizedTest
  @NullSource
  void parseToString_withPreserveNullAndEmpty(final String value) {

    final TypeParser typeParser =
        TypeParser.builder()
            .preserveNullAndEmpty()
            .build();

    assertThat(typeParser.parseToString(value))
        .isNull();
  }

  @ParameterizedTest
  @EmptySource
  @ValueSource(strings = "  ")
  void parseToString_withPreserveNullAndEmptyAndPreserveWhitespace(final String value) {

    final TypeParser typeParser =
        TypeParser.builder()
            .preserveNullAndEmpty()
            .preserveWhitespace()
            .build();

    assertThat(typeParser.parseToString(value))
        .isNotNull()
        .isEqualTo(value);
  }

  @ParameterizedTest
  @EmptySource
  @ValueSource(strings = "  ")
  void parseToString_withPreserveNullAndEmptyAndRemoveAllWhitespace(final String value) {

    final TypeParser typeParser =
        TypeParser.builder()
            .preserveNullAndEmpty()
            .removeAllWhitespace()
            .build();

    assertThat(typeParser.parseToString(value))
        .isNotNull()
        .isEmpty();
  }

  @ParameterizedTest
  @NullSource
  void parseToString_withConvertNullToEmpty(final String value) {

    final TypeParser typeParser =
        TypeParser.builder()
            .convertNullToEmpty()
            .build();

    assertThat(typeParser.parseToString(value))
        .isNotNull()
        .isEmpty();
  }

  @ParameterizedTest
  @EmptySource
  @ValueSource(strings = "  ")
  void parseToString_withConvertNullToEmptyAndPreserveWhitespace(final String value) {

    final TypeParser typeParser =
        TypeParser.builder()
            .convertNullToEmpty()
            .preserveWhitespace()
            .build();

    assertThat(typeParser.parseToString(value))
        .isNotNull()
        .isEqualTo(value);
  }

  @ParameterizedTest
  @EmptySource
  @ValueSource(strings = "  ")
  void parseToString_withConvertNullToEmptyAndRemoveAllWhitespace(final String value) {

    final TypeParser typeParser =
        TypeParser.builder()
            .convertNullToEmpty()
            .removeAllWhitespace()
            .build();

    assertThat(typeParser.parseToString(value))
        .isNotNull()
        .isEmpty();
  }


  @ParameterizedTest
  @NullAndEmptySource
  void parseToString_withConvertEmptyToNull(final String value) {

    final TypeParser typeParser =
        TypeParser.builder()
            .convertEmptyToNull()
            .build();

    assertThat(typeParser.parseToString(value))
        .isNull();
  }

  @ParameterizedTest
  @ValueSource(strings = "  ")
  void parseToString_withConvertEmptyToNullAndPreserveWhitespace(final String value) {

    final TypeParser typeParser =
        TypeParser.builder()
            .convertEmptyToNull()
            .preserveWhitespace()
            .build();

    assertThat(typeParser.parseToString(value))
        .isNotNull()
        .isEqualTo(value);
  }

  @ParameterizedTest
  @ValueSource(strings = "  ")
  void parseToString_withConvertEmptyToNullAndRemoveAllWhitespace(final String value) {

    final TypeParser typeParser =
        TypeParser.builder()
            .convertEmptyToNull()
            .removeAllWhitespace()
            .build();

    assertThat(typeParser.parseToString(value))
        .isNull();
  }

}
