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

class SoftAssertionsTest {

  // ─── constructor ──────────────────────────────────────────────────────────

  @Test
  void constructor_isInstantiable() {
    assertThat(new SoftAssertions()).isNotNull();
  }

  // ─── assertThat(IntegerType) ──────────────────────────────────────────────

  @Test
  void assertThat_withNullIntegerType_returnsIntegerTypeAssert() {
    final SoftAssertions softAssertions = new SoftAssertions();
    assertThat(softAssertions.assertThat((IntegerType) null))
        .isNotNull()
        .isInstanceOf(IntegerTypeAssert.class);
    softAssertions.assertAll();
  }

  @Test
  void assertThat_withNonNullIntegerType_returnsIntegerTypeAssert() {
    final SoftAssertions softAssertions = new SoftAssertions();
    assertThat(softAssertions.assertThat(new SomeIntegerType(42)))
        .isNotNull()
        .isInstanceOf(IntegerTypeAssert.class);
    softAssertions.assertAll();
  }

  // ─── assertThat(InvalidValueException) ───────────────────────────────────

  @Test
  void assertThat_withNullInvalidValueException_returnsInvalidValueExceptionAssert() {
    final SoftAssertions softAssertions = new SoftAssertions();
    assertThat(softAssertions.assertThat((InvalidValueException) null))
        .isNotNull()
        .isInstanceOf(InvalidValueExceptionAssert.class);
    softAssertions.assertAll();
  }

  @Test
  void assertThat_withNonNullInvalidValueException_returnsInvalidValueExceptionAssert() {
    final SoftAssertions softAssertions = new SoftAssertions();
    assertThat(softAssertions.assertThat(InvalidValueException.builder()
        .errorCode(MessageCode.of("some.error", "Some error."))
        .invalidValue("some bad value")
        .build()))
        .isNotNull()
        .isInstanceOf(InvalidValueExceptionAssert.class);
    softAssertions.assertAll();
  }

  // ─── assertThat(LongType) ─────────────────────────────────────────────────

  @Test
  void assertThat_withNullLongType_returnsLongTypeAssert() {
    final SoftAssertions softAssertions = new SoftAssertions();
    assertThat(softAssertions.assertThat((LongType) null))
        .isNotNull()
        .isInstanceOf(LongTypeAssert.class);
    softAssertions.assertAll();
  }

  @Test
  void assertThat_withNonNullLongType_returnsLongTypeAssert() {
    final SoftAssertions softAssertions = new SoftAssertions();
    assertThat(softAssertions.assertThat(new SomeLongType(42L)))
        .isNotNull()
        .isInstanceOf(LongTypeAssert.class);
    softAssertions.assertAll();
  }

  // ─── assertThat(MessageCode) ──────────────────────────────────────────────

  @Test
  void assertThat_withNullMessageCode_returnsMessageCodeAssert() {
    final SoftAssertions softAssertions = new SoftAssertions();
    assertThat(softAssertions.assertThat((MessageCode) null))
        .isNotNull()
        .isInstanceOf(MessageCodeAssert.class);
    softAssertions.assertAll();
  }

  @ParameterizedTest(name = "[{index}] messageCode={0}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      MESSAGE_CODE
      some.message.code
      another.message.code
      error.validation.failed
      """)
  void assertThat_withNonNullMessageCode_returnsMessageCodeAssert(final String messageCode) {
    final SoftAssertions softAssertions = new SoftAssertions();
    assertThat(softAssertions.assertThat(MessageCode.of(messageCode, "Some message.")))
        .isNotNull()
        .isInstanceOf(MessageCodeAssert.class);
    softAssertions.assertAll();
  }

  // ─── assertThat(ShortType) ────────────────────────────────────────────────

  @Test
  void assertThat_withNullShortType_returnsShortTypeAssert() {
    final SoftAssertions softAssertions = new SoftAssertions();
    assertThat(softAssertions.assertThat((ShortType) null))
        .isNotNull()
        .isInstanceOf(ShortTypeAssert.class);
    softAssertions.assertAll();
  }

  @Test
  void assertThat_withNonNullShortType_returnsShortTypeAssert() {
    final SoftAssertions softAssertions = new SoftAssertions();
    assertThat(softAssertions.assertThat(new SomeShortType((short) 7)))
        .isNotNull()
        .isInstanceOf(ShortTypeAssert.class);
    softAssertions.assertAll();
  }

  // ─── assertThat(StringType) ───────────────────────────────────────────────

  @Test
  void assertThat_withNullStringType_returnsStringTypeAssert() {
    final SoftAssertions softAssertions = new SoftAssertions();
    assertThat(softAssertions.assertThat((StringType) null))
        .isNotNull()
        .isInstanceOf(StringTypeAssert.class);
    softAssertions.assertAll();
  }

  @ParameterizedTest(name = "[{index}] value={0}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALUE
      hello
      world
      foo
      """)
  void assertThat_withNonNullStringType_returnsStringTypeAssert(final String value) {
    final SoftAssertions softAssertions = new SoftAssertions();
    assertThat(softAssertions.assertThat(new SomeStringType(value)))
        .isNotNull()
        .isInstanceOf(StringTypeAssert.class);
    softAssertions.assertAll();
  }

  // ─── assertThat(CharSequenceType) ─────────────────────────────────────────

  @Test
  void assertThat_withNullCharSequenceType_returnsCharSequenceTypeAssert() {
    final SoftAssertions softAssertions = new SoftAssertions();
    assertThat(softAssertions.assertThat((SomeCharSequenceType) null))
        .isNotNull();
    softAssertions.assertAll();
  }

  @ParameterizedTest(name = "[{index}] value={0}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALUE
      hello
      world
      foo
      """)
  void assertThat_withNonNullCharSequenceType_returnsCharSequenceTypeAssert(final String value) {
    final SoftAssertions softAssertions = new SoftAssertions();
    final SomeCharSequenceType actual = new SomeCharSequenceType(value);
    assertThat(softAssertions.assertThat(actual))
        .isNotNull()
        .isInstanceOf(CharSequenceTypeAssert.class);
    softAssertions.assertAll();
  }

  // ─── assertThat(Subset) ───────────────────────────────────────────────────

  @Test
  void assertThat_withNullSubset_returnsSubsetAssert() {
    final SoftAssertions softAssertions = new SoftAssertions();
    assertThat(softAssertions.assertThat((Subset) null))
        .isNotNull()
        .isInstanceOf(SubsetAssert.class);
    softAssertions.assertAll();
  }

  @Test
  void assertThat_withNonNullSubset_returnsSubsetAssert() {
    final SoftAssertions softAssertions = new SoftAssertions();
    assertThat(softAssertions.assertThat(Subset.builder().includeChar('a').build()))
        .isNotNull()
        .isInstanceOf(SubsetAssert.class);
    softAssertions.assertAll();
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
