/*
 * Copyright 2021-2022 Evan Toliopoulos (typefactory.org)
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
package org.typefactory.assertions;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.Serial;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.CharSequenceType;
import org.typefactory.IntegerType;
import org.typefactory.InvalidValueException;
import org.typefactory.LongType;
import org.typefactory.MessageCode;
import org.typefactory.ShortType;
import org.typefactory.StringType;
import org.typefactory.Subset;

class BDDAssertionsTest {

  // ─── protected constructor ────────────────────────────────────────────────

  @Test
  void constructor_isInstantiable() {
    assertThat(new BDDAssertions()).isNotNull();
  }

  // ─── then(IntegerType) ────────────────────────────────────────────────────

  @Test
  void then_withNullIntegerType_returnsIntegerTypeAssert() {
    assertThat(BDDAssertions.then((IntegerType) null))
        .isNotNull()
        .isInstanceOf(IntegerTypeAssert.class);
  }

  @Test
  void then_withNonNullIntegerType_returnsIntegerTypeAssert() {
    assertThat(BDDAssertions.then(new SomeIntegerType(42)))
        .isNotNull()
        .isInstanceOf(IntegerTypeAssert.class);
  }

  // ─── then(InvalidValueException) ─────────────────────────────────────────

  @Test
  void then_withNullInvalidValueException_returnsInvalidValueExceptionAssert() {
    assertThat(BDDAssertions.then((InvalidValueException) null))
        .isNotNull()
        .isInstanceOf(InvalidValueExceptionAssert.class);
  }

  @Test
  void then_withNonNullInvalidValueException_returnsInvalidValueExceptionAssert() {
    assertThat(BDDAssertions.then(InvalidValueException.builder()
        .errorCode(MessageCode.of("some.error", "Some error."))
        .invalidValue("some bad value")
        .build()))
        .isNotNull()
        .isInstanceOf(InvalidValueExceptionAssert.class);
  }

  // ─── then(LongType) ───────────────────────────────────────────────────────

  @Test
  void then_withNullLongType_returnsLongTypeAssert() {
    assertThat(BDDAssertions.then((LongType) null))
        .isNotNull()
        .isInstanceOf(LongTypeAssert.class);
  }

  @Test
  void then_withNonNullLongType_returnsLongTypeAssert() {
    assertThat(BDDAssertions.then(new SomeLongType(42L)))
        .isNotNull()
        .isInstanceOf(LongTypeAssert.class);
  }

  // ─── then(MessageCode) ────────────────────────────────────────────────────

  @Test
  void then_withNullMessageCode_returnsMessageCodeAssert() {
    assertThat(BDDAssertions.then((MessageCode) null))
        .isNotNull()
        .isInstanceOf(MessageCodeAssert.class);
  }

  @ParameterizedTest(name = "[{index}] messageCode={0}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      MESSAGE_CODE
      some.message.code
      another.message.code
      error.validation.failed
      """)
  void then_withNonNullMessageCode_returnsMessageCodeAssert(final String messageCode) {
    assertThat(BDDAssertions.then(MessageCode.of(messageCode, "Some message.")))
        .isNotNull()
        .isInstanceOf(MessageCodeAssert.class);
  }

  // ─── then(ShortType) ──────────────────────────────────────────────────────

  @Test
  void then_withNullShortType_returnsShortTypeAssert() {
    assertThat(BDDAssertions.then((ShortType) null))
        .isNotNull()
        .isInstanceOf(ShortTypeAssert.class);
  }

  @Test
  void then_withNonNullShortType_returnsShortTypeAssert() {
    assertThat(BDDAssertions.then(new SomeShortType((short) 7)))
        .isNotNull()
        .isInstanceOf(ShortTypeAssert.class);
  }

  // ─── then(StringType) ─────────────────────────────────────────────────────

  @Test
  void then_withNullStringType_returnsStringTypeAssert() {
    assertThat(BDDAssertions.then((StringType) null))
        .isNotNull()
        .isInstanceOf(StringTypeAssert.class);
  }

  @ParameterizedTest(name = "[{index}] value={0}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALUE
      hello
      world
      foo
      """)
  void then_withNonNullStringType_returnsStringTypeAssert(final String value) {
    assertThat(BDDAssertions.then(new SomeStringType(value)))
        .isNotNull()
        .isInstanceOf(StringTypeAssert.class);
  }

  // ─── then(CharSequenceType) ───────────────────────────────────────────────

  @Test
  void then_withNullCharSequenceType_returnsCharSequenceTypeAssert() {
    assertThat(BDDAssertions.then((SomeCharSequenceType) null))
        .isNotNull();
  }

  @ParameterizedTest(name = "[{index}] value={0}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALUE
      hello
      world
      foo
      """)
  void then_withNonNullCharSequenceType_returnsCharSequenceTypeAssert(final String value) {
    final SomeCharSequenceType actual = new SomeCharSequenceType(value);
    assertThat(BDDAssertions.then(actual))
        .isNotNull()
        .isInstanceOf(CharSequenceTypeAssert.class);
  }

  // ─── then(Subset) ─────────────────────────────────────────────────────────

  @Test
  void then_withNullSubset_returnsSubsetAssert() {
    assertThat(BDDAssertions.then((Subset) null))
        .isNotNull()
        .isInstanceOf(SubsetAssert.class);
  }

  @Test
  void then_withNonNullSubset_returnsSubsetAssert() {
    assertThat(BDDAssertions.then(Subset.builder().includeChar('a').build()))
        .isNotNull()
        .isInstanceOf(SubsetAssert.class);
  }

  // ─── inner helper types ───────────────────────────────────────────────────

  private static final class SomeShortType extends ShortType {

    @Serial
    private static final long serialVersionUID = -1842193583997443860L;

    SomeShortType(final Short value) {
      super(value);
    }
  }

  private static final class SomeIntegerType extends IntegerType {

    @Serial
    private static final long serialVersionUID = -1842193583997443860L;

    SomeIntegerType(final Integer value) {
      super(value);
    }
  }

  private static final class SomeLongType extends LongType {

    @Serial
    private static final long serialVersionUID = -1226102666776406773L;

    SomeLongType(final Long value) {
      super(value);
    }
  }

  private static final class SomeStringType extends StringType {

    @Serial
    private static final long serialVersionUID = 6673260854256023521L;

    SomeStringType(final String value) {
      super(value);
    }
  }

  private record SomeCharSequenceType(String value)
      implements CharSequenceType<SomeCharSequenceType> {
  }
}
