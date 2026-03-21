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

class AssertionsTest {

  // ─── protected constructor ────────────────────────────────────────────────

  @Test
  void constructor_isInstantiable() {
    assertThat(new Assertions()).isNotNull();
  }

  // ─── assertThat(IntegerType) ──────────────────────────────────────────────

  @Test
  void assertThat_withNullIntegerType_returnsIntegerTypeAssert() {
    assertThat(Assertions.assertThat((IntegerType) null))
        .isNotNull()
        .isInstanceOf(IntegerTypeAssert.class);
  }

  @Test
  void assertThat_withNonNullIntegerType_returnsIntegerTypeAssert() {
    assertThat(Assertions.assertThat(new SomeIntegerType(42)))
        .isNotNull()
        .isInstanceOf(IntegerTypeAssert.class);
  }

  // ─── assertThat(InvalidValueException) ───────────────────────────────────

  @Test
  void assertThat_withNullInvalidValueException_returnsInvalidValueExceptionAssert() {
    assertThat(Assertions.assertThat((InvalidValueException) null))
        .isNotNull()
        .isInstanceOf(InvalidValueExceptionAssert.class);
  }

  @Test
  void assertThat_withNonNullInvalidValueException_returnsInvalidValueExceptionAssert() {
    assertThat(Assertions.assertThat(InvalidValueException.builder()
            .errorCode(MessageCode.of("some.error", "Some error."))
            .invalidValue("some bad value")
            .build()))
        .isNotNull()
        .isInstanceOf(InvalidValueExceptionAssert.class);
  }

  // ─── assertThat(LongType) ─────────────────────────────────────────────────

  @Test
  void assertThat_withNullLongType_returnsLongTypeAssert() {
    assertThat(Assertions.assertThat((LongType) null))
        .isNotNull()
        .isInstanceOf(LongTypeAssert.class);
  }

  @Test
  void assertThat_withNonNullLongType_returnsLongTypeAssert() {
    assertThat(Assertions.assertThat(new SomeLongType(42L)))
        .isNotNull()
        .isInstanceOf(LongTypeAssert.class);
  }

  // ─── assertThat(MessageCode) ──────────────────────────────────────────────

  @Test
  void assertThat_withNullMessageCode_returnsMessageCodeAssert() {
    assertThat(Assertions.assertThat((MessageCode) null))
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
  void assertThat_withNonNullMessageCode_returnsMessageCodeAssert(final String messageCode) {
    assertThat(Assertions.assertThat(MessageCode.of("some.error", "Some error.")))
        .isNotNull()
        .isInstanceOf(MessageCodeAssert.class);
  }

  // ─── assertThat(ShortType) ────────────────────────────────────────────────

  @Test
  void assertThat_withNullShortType_returnsShortTypeAssert() {
    assertThat(Assertions.assertThat((ShortType) null))
        .isNotNull()
        .isInstanceOf(ShortTypeAssert.class);
  }

  @Test
  void assertThat_withNonNullShortType_returnsShortTypeAssert() {
    assertThat(Assertions.assertThat(new SomeShortType((short) 7)))
        .isNotNull()
        .isInstanceOf(ShortTypeAssert.class);
  }

  // ─── assertThat(StringType) ───────────────────────────────────────────────

  @Test
  void assertThat_withNullStringType_returnsStringTypeAssert() {
    assertThat(Assertions.assertThat((StringType) null))
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
  void assertThat_withNonNullStringType_returnsStringTypeAssert(final String value) {
    assertThat(Assertions.assertThat(new SomeStringType(value)))
        .isNotNull()
        .isInstanceOf(StringTypeAssert.class);
  }

  // ─── assertThat(CharSequenceType) ─────────────────────────────────────────

  @Test
  void assertThat_withNullCharSequenceType_returnsCharSequenceTypeAssert() {
    assertThat(Assertions.assertThat((SomeCharSequenceType) null))
        .isNotNull();
  }

  @ParameterizedTest(name = "[{index}] value={0}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALUE
      hello
      world
      foo
      """)
  void assertThat_withNonNullCharSequenceType_returnsCharSequenceTypeAssert(final String value) {
    final SomeCharSequenceType actual = new SomeCharSequenceType(value);
    assertThat(Assertions.assertThat(actual))
        .isNotNull()
        .isInstanceOf(CharSequenceTypeAssert.class);
  }

  // ─── assertThat(Subset) ───────────────────────────────────────────────────

  @Test
  void assertThat_withNullSubset_returnsSubsetAssert() {
    assertThat(Assertions.assertThat((Subset) null))
        .isNotNull()
        .isInstanceOf(SubsetAssert.class);
  }

  @Test
  void assertThat_withNonNullSubset_returnsSubsetAssert() {
    assertThat(Assertions.assertThat(Subset.builder().includeChar('a').build()))
        .isNotNull()
        .isInstanceOf(SubsetAssert.class);
  }

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
