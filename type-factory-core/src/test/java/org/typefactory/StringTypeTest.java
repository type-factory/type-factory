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
package org.typefactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatObject;

import java.io.Serial;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class StringTypeTest {

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {
      " ",
      "a",
      "ab",
      "abc"})
  void value_returnsTheValueThatItContains(final String value) {
    final var actual = new ConcreteStringType(value);
    assertThat(actual.value()).isEqualTo(value);
  }

  @Test
  void toString_returnsNullWhenTheValueThatItContainsIsNull() {
    final var actual = new ConcreteStringType(null);
    assertThatObject(actual).hasToString("");
  }

  @ParameterizedTest
  @EmptySource
  @ValueSource(strings = {
      " ",
      "a",
      "ab",
      "abc"})
  void toString_returnsTheValueThatItContains(final String value) {
    final var actual = new ConcreteStringType(value);
    assertThatObject(actual).hasToString(value);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {
      " ",
      "a",
      "ab",
      "abc"})
  void equals_returnsTrueWhenSameInstance(final String value) {
    final var actual1 = new ConcreteStringType(value);
    final var actual2 = actual1;
    assertThatObject(actual1).isEqualTo(actual2);
    assertThatObject(actual2).isEqualTo(actual1);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {
      " ",
      "a",
      "ab",
      "abc"})
  void equals_returnsFalseWhenOtherInstanceIsNull(final String value) {
    final var actual1 = new ConcreteStringType(value);
    final var actual2 = (ConcreteStringType) null;
    assertThatObject(actual1).isNotEqualTo(actual2);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {
      " ",
      "a",
      "ab",
      "abc"})
  void equals_returnsTrueWhenSameTypeWithSameValue(final String value) {
    final var actual1 = new ConcreteStringType(value);
    final var actual2 = new ConcreteStringType(value);
    assertThatObject(actual1).isEqualTo(actual2);
    assertThatObject(actual2).isEqualTo(actual1);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {
      " ",
      "a",
      "ab",
      "abc"})
  void equals_returnsFalseWhenDifferentTypeWithSameValue(final String value) {
    final var actual1 = new ConcreteStringType(value);
    final var actual2 = new AnotherStringType(value);
    assertThatObject(actual1).isNotEqualTo(actual2);
    assertThatObject(actual2).isNotEqualTo(actual1);
  }

  @ParameterizedTest
  @CsvSource(value = {
      " null  | empty ", // null versus empty
      " null  | ' '   ", // null versus space
      " empty | ' '   ", // empty versus space
      " ' '   | '  '  ", // space versus two-spaces
      " a     | b     ",
      " bb    | cc    ",
  }, delimiter = '|', nullValues = "null", emptyValue = "empty")
  void equals_returnsFalseWhenSameTypeWithDifferentValue(final String value1, final String value2) {
    final var actual1 = new ConcreteStringType(value1);
    final var actual2 = new ConcreteStringType(value2);
    assertThatObject(actual1).isNotEqualTo(actual2);
    assertThatObject(actual2).isNotEqualTo(actual1);
  }

  @ParameterizedTest
  @CsvSource(value = {
      " null  | empty ", // null versus empty
      " null  | ' '   ", // null versus space
      " empty | ' '   ", // empty versus space
      " ' '   | '  '  ", // space versus two-spaces
      " a     | b     ",
      " bb    | cc    ",
  }, delimiter = '|')
  void equals_returnsFalseWhenDifferentTypeWithDifferentValue(final String value1, final String value2) {
    final var actual1 = new ConcreteStringType(value1);
    final var actual2 = new AnotherStringType(value2);
    assertThatObject(actual1).isNotEqualTo(actual2);
    assertThatObject(actual2).isNotEqualTo(actual1);
  }

  @Test
  void equals_returnsZeroHashCodeWhenInternalValueIsNull() {
    final var actual = new ConcreteStringType(null);
    assertThat(actual.hashCode()).isZero();
  }

  @ParameterizedTest
  @EmptySource
  @ValueSource(strings = {
      " ",
      "a",
      "ab",
      "abc"})
  void equals_returnsSameHashCodeAsStringValue(final String value) {
    final var actual = new ConcreteStringType(value);
    assertThatObject(actual).hasSameHashCodeAs(value);
  }


  static class ConcreteStringType extends StringType {

    @Serial
    private static final long serialVersionUID = 3193956744499553359L;

    public ConcreteStringType(final String value) {
      super(value);
    }
  }

  static class AnotherStringType extends StringType {

    @Serial
    private static final long serialVersionUID = -3746497687089278183L;

    public AnotherStringType(final String value) {
      super(value);
    }
  }
}
