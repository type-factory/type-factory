package org.typefactory;

import static org.typefactory.assertions.Assertions.assertThat;

import java.lang.reflect.Constructor;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TypesTest {

  // ─── Types private constructor ────────────────────────────────────────────

  @Test
  void types_privateConstructor_isInstantiableViaReflection() throws Exception {
    final Constructor<Types> constructor = Types.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    final var instance = constructor.newInstance();

    assertThat(instance).isNotNull();
  }

  // ─── isNull(CharSequenceType) ─────────────────────────────────────────────

  @Test
  void isNull_charSequenceType_withNull_returnsTrue() {
    assertThat(Types.isNull((SomeCharSequenceType) null)).isTrue();
  }

  @Test
  void isNull_charSequenceType_withNullValue_returnsTrue() {
    assertThat(Types.isNull(new SomeCharSequenceType(null))).isTrue();
  }

  @Test
  void isNull_charSequenceType_withNonNullValue_returnsFalse() {
    assertThat(Types.isNull(new SomeCharSequenceType("abc"))).isFalse();
  }

  // ─── isNull(StringType) ───────────────────────────────────────────────────

  @Test
  void isNull_stringType_withNull_returnsTrue() {
    assertThat(Types.isNull((SomeStringType) null)).isTrue();
  }

  @Test
  void isNull_stringType_withNullValue_returnsTrue() {
    assertThat(Types.isNull(new SomeStringType(null))).isTrue();
  }

  @Test
  void isNull_stringType_withNonNullValue_returnsFalse() {
    assertThat(Types.isNull(new SomeStringType("abc"))).isFalse();
  }

  // ─── isEmpty(CharSequenceType) ────────────────────────────────────────────

  @Test
  void isEmpty_charSequenceType_withNull_returnsTrue() {
    assertThat(Types.isEmpty((SomeCharSequenceType) null)).isTrue();
  }

  @ParameterizedTest(name = "[{index}] value={0}, expected={1}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      VALUE | EXPECTED
      null  | true
      ''    | true
      '  '  | false
      abc   | false
      """)
  void isEmpty_charSequenceType_returnsAsExpected(final String value, final boolean expected) {
    assertThat(Types.isEmpty(new SomeCharSequenceType(value))).isEqualTo(expected);
  }

  // ─── isEmpty(StringType) ──────────────────────────────────────────────────

  @Test
  void isEmpty_stringType_withNull_returnsTrue() {
    assertThat(Types.isEmpty((SomeStringType) null)).isTrue();
  }

  @ParameterizedTest(name = "[{index}] value={0}, expected={1}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      VALUE | EXPECTED
      null  | true
      ''    | true
      '  '  | false
      abc   | false
      """)
  void isEmpty_stringType_returnsAsExpected(final String value, final boolean expected) {
    assertThat(Types.isEmpty(new SomeStringType(value))).isEqualTo(expected);
  }

// ─── isBlank(CharSequence) ────────────────────────────────────────────────

  @Test
  void isBlank_charSequence_withNull_returnsTrue() {
    assertThat(Types.isBlank((CharSequence) null)).isTrue();
  }

  @ParameterizedTest(name = "[{index}] value={0}, expected={1}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      VALUE        | EXPECTED
      ''           | true
      ' '          | true
      '   '        | true
      '\t'         | true
      ' \t '       | true
      '\r'         | true
      a            | false
      abc          | false
      ' a '        | false
      """)
  void isBlank_charSequence_string_returnsAsExpected(final String value, final boolean expected) {
    // explicitly treat as CharSequence
    final var cs = value == null ? null : value;
    assertThat(Types.isBlank(cs)).isEqualTo(expected);
  }

  @ParameterizedTest(name = "[{index}] value={0}, expected={1}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      VALUE        | EXPECTED
      ''           | true
      ' '          | true
      '   '        | true
      '\t'         | true
      ' \t '       | true
      '\r'         | true
      a            | false
      abc          | false
      ' a '        | false
      """)
  void isBlank_charSequence_nonStringCharSequence_returnsAsExpected(final String value, final boolean expected) {
    // Wrap in a non-String CharSequence to exercise the char-by-char loop
    final CharSequence cs = value == null ? null : new StringBuilder(value);
    assertThat(Types.isBlank(cs)).isEqualTo(expected);
  }

  @Test
  void isBlank_charSequence_validNonWhitespaceSurrogatePair_returnsFalse() {
    final StringBuilder cs = new StringBuilder();
    // U+1F600 GRINNING FACE — a non-whitespace supplementary character
    cs.appendCodePoint(0x1F600); // high + low surrogate
    assertThat(Types.isBlank((CharSequence) cs)).isFalse();
  }

  @Test
  void isBlank_charSequence_highSurrogateFollowedByNonLowSurrogate_returnsFalse() {
    final StringBuilder cs = new StringBuilder();
    // Orphan high surrogate followed by a regular character — invalid sequence,
    // method treats it as non-blank
    cs.append('\uD83D'); // high surrogate
    cs.append('a'); // regular char, not a low surrogate
    assertThat(Types.isBlank((CharSequence) cs)).isFalse();
  }

  @Test
  void isBlank_charSequence_highSurrogateAtEndOfSequence_returnsFalse() {
    final StringBuilder cs = new StringBuilder();
    // Orphan high surrogate at end — no following low surrogate
    cs.append(' '); // space char
    cs.append('\uD83D');  // then a dangling high surrogate
    assertThat(Types.isBlank((CharSequence) cs)).isFalse();
  }

  @Test
  void isBlank_charSequence_orphanLowSurrogate_returnsFalse() {
    final StringBuilder cs = new StringBuilder();
    // Low surrogate without a preceding high surrogate
    cs.append('\uDC00'); // orphan low surrogate
    assertThat(Types.isBlank((CharSequence) cs)).isFalse();
  }

  // ─── isBlank(CharSequenceType) ────────────────────────────────────────────

  @Test
  void isBlank_charSequenceType_withNull_returnsTrue() {
    assertThat(Types.isBlank((SomeCharSequenceType) null)).isTrue();
  }

  @ParameterizedTest(name = "[{index}] value={0}, expected={1}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      VALUE  | EXPECTED
      null   | true
      ''     | true
      '  '   | true
      ' \t ' | true
      ' \r ' | true
      abc    | false
      """)
  void isBlank_charSequenceType_returnsAsExpected(final String value, final boolean expected) {
    assertThat(Types.isBlank(new SomeCharSequenceType(value))).isEqualTo(expected);
  }

  // ─── isBlank(StringType) ──────────────────────────────────────────────────

  @Test
  void isBlank_stringType_withNull_returnsTrue() {
    assertThat(Types.isBlank((SomeStringType) null)).isTrue();
  }

  @ParameterizedTest(name = "[{index}] value={0}, expected={1}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      VALUE  | EXPECTED
      null   | true
      ''     | true
      '  '   | true
      ' \t ' | true
      ' \r ' | true
      abc    | false
      """)
  void isBlank_stringType_returnsAsExpected(final String value, final boolean expected) {
    assertThat(Types.isBlank(new SomeStringType(value))).isEqualTo(expected);
  }

  // ─── defaultIfEmpty(CharSequenceType) ────────────────────────────────────

  @Test
  void defaultIfEmpty_charSequenceType_withNullValue_returnsDefault() {
    final var defaultValue = new SomeCharSequenceType("default");
    assertThat(Types.defaultIfEmpty((SomeCharSequenceType) null, defaultValue))
        .isSameAs(defaultValue);
  }

  @ParameterizedTest(name = "[{index}] value={0}, expected={2}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      VALUE   | DEFAULT | EXPECTED
      null    | default | default
      ''      | default | default
      '  '    | default | '  '
      ' \t '  | default | ' \t '
      ' \r '  | default | ' \r '
      abc     | default | abc
      """)
  void defaultIfEmpty_charSequenceType_returnsAsExpected(
      final String value, final String defaultVal, final String expected) {
    final var result = Types.defaultIfEmpty(
        new SomeCharSequenceType(value), new SomeCharSequenceType(defaultVal));

    assertThat(result.value()).isEqualTo(expected);
  }

  // ─── defaultIfEmpty(StringType) ───────────────────────────────────────────

  @Test
  void defaultIfEmpty_stringType_withNullValue_returnsDefault() {
    final var defaultValue = new SomeStringType("default");
    assertThat(Types.defaultIfEmpty((SomeStringType) null, defaultValue))
        .isSameAs(defaultValue);
  }

  @ParameterizedTest(name = "[{index}] value={0}, expected={2}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      VALUE   | DEFAULT | EXPECTED
      null    | default | default
      ''      | default | default
      '  '    | default | '  '
      ' \t '  | default | ' \t '
      ' \r '  | default | ' \r '
      abc     | default | abc
      """)
  void defaultIfEmpty_stringType_returnsAsExpected(
      final String value, final String defaultVal, final String expected) {
    final var result = Types.defaultIfEmpty(
        new SomeStringType(value), new SomeStringType(defaultVal));

    assertThat(result.value()).isEqualTo(expected);
  }

  // ─── defaultIfBlank(CharSequenceType) ────────────────────────────────────

  @Test
  void defaultIfBlank_charSequenceType_withNullValue_returnsDefault() {
    final var defaultValue = new SomeCharSequenceType("default");
    assertThat(Types.defaultIfBlank((SomeCharSequenceType) null, defaultValue))
        .isSameAs(defaultValue);
  }

  @ParameterizedTest(name = "[{index}] value={0}, expected={2}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      VALUE   | DEFAULT | EXPECTED
      null    | default | default
      ''      | default | default
      '  '    | default | default
      ' \t '  | default | default
      ' \r '  | default | default
      abc     | default | abc
      """)
  void defaultIfBlank_charSequenceType_returnsAsExpected(
      final String value, final String defaultVal, final String expected) {
    final var result = Types.defaultIfBlank(
        new SomeCharSequenceType(value), new SomeCharSequenceType(defaultVal));

    assertThat(result.value()).isEqualTo(expected);
  }

  // ─── defaultIfBlank(StringType) ───────────────────────────────────────────

  @Test
  void defaultIfBlank_stringType_withNullValue_returnsDefault() {
    final var defaultValue = new SomeStringType("default");
    assertThat(Types.defaultIfBlank((SomeStringType) null, defaultValue))
        .isSameAs(defaultValue);
  }

  @ParameterizedTest(name = "[{index}] value={0}, expected={2}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      VALUE   | DEFAULT | EXPECTED
      null    | default | default
      ''      | default | default
      '  '    | default | default
      ' \t '  | default | default
      ' \r '  | default | default
      abc     | default | abc
      """)
  void defaultIfBlank_stringType_returnsAsExpected(
      final String value, final String defaultVal, final String expected) {
    final var result = Types.defaultIfBlank(
        new SomeStringType(value), new SomeStringType(defaultVal));

    assertThat(result.value()).isEqualTo(expected);
  }

  // ─── CS (CaseSensistive) — equals(CharSequenceType) ──────────────────────

  @Test
  void cs_equals_charSequenceType_withBothNull_returnsTrue() {
    assertThat(Types.CS.equals((SomeCharSequenceType) null, (SomeCharSequenceType) null)).isTrue();
  }

  @Test
  void cs_equals_charSequenceType_withSameInstance_returnsTrue() {
    final var value = new SomeCharSequenceType("abc");
    assertThat(Types.CS.equals(value, value)).isTrue();
  }

  @ParameterizedTest(name = "[{index}] value1={0}, value2={1}, expected={2}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      VALUE_1 | VALUE_2 | EXPECTED
      null    | null    | true
      null    | abc     | false
      abc     | null    | false
      abc     | abc     | true
      abc     | ABC     | false
      abc     | xyz     | false
      """)
  void cs_equals_charSequenceType_returnsAsExpected(
      final String value1, final String value2, final boolean expected) {

    final var v1 = value1 == null ? null : new SomeCharSequenceType(value1);
    final var v2 = value2 == null ? null : new SomeCharSequenceType(value2);

    assertThat(Types.CS.equals(v1, v2)).isEqualTo(expected);
  }

  // ─── CS (CaseSensistive) — equals(StringType) ────────────────────────────

  @Test
  void cs_equals_stringType_withBothNull_returnsTrue() {
    assertThat(Types.CS.equals((SomeStringType) null, (SomeStringType) null)).isTrue();
  }

  @Test
  void cs_equals_stringType_withSameInstance_returnsTrue() {
    final var value = new SomeStringType("abc");
    assertThat(Types.CS.equals(value, value)).isTrue();
  }

  @ParameterizedTest(name = "[{index}] value1={0}, value2={1}, expected={2}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      VALUE_1 | VALUE_2 | EXPECTED
      null    | null    | true
      null    | abc     | false
      abc     | null    | false
      abc     | abc     | true
      abc     | ABC     | false
      abc     | xyz     | false
      """)
  void cs_equals_stringType_returnsAsExpected(
      final String value1, final String value2, final boolean expected) {

    final var v1 = value1 == null ? null : new SomeStringType(value1);
    final var v2 = value2 == null ? null : new SomeStringType(value2);

    assertThat(Types.CS.equals(v1, v2)).isEqualTo(expected);
  }

  // ─── CS (CaseSensistive) — equalsAny(CharSequenceType, varargs) ───────────

  @Test
  void cs_equalsAny_charSequenceType_varargs_withNullOtherValues_returnsFalse() {
    assertThat(Types.CS.equalsAny(new SomeCharSequenceType("abc"), (SomeCharSequenceType[]) null))
        .isFalse();
  }

  @ParameterizedTest(name = "[{index}] value={0}, expected={1}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALUE | EXPECTED
      abc   | true
      ABC   | false
      xyz   | false
      """)
  void cs_equalsAny_charSequenceType_varargs_returnsAsExpected(
      final String value, final boolean expected) {
    assertThat(Types.CS.equalsAny(
        new SomeCharSequenceType(value),
        new SomeCharSequenceType("aaa"),
        new SomeCharSequenceType("abc"),
        new SomeCharSequenceType("zzz")))
        .isEqualTo(expected);
  }

  // ─── CS (CaseSensistive) — equalsAny(StringType, varargs) ────────────────

  @Test
  void cs_equalsAny_stringType_varargs_withNullOtherValues_returnsFalse() {
    assertThat(Types.CS.equalsAny(new SomeStringType("abc"), (SomeStringType[]) null))
        .isFalse();
  }

  @ParameterizedTest(name = "[{index}] value={0}, expected={1}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALUE | EXPECTED
      abc   | true
      ABC   | false
      xyz   | false
      """)
  void cs_equalsAny_stringType_varargs_returnsAsExpected(
      final String value, final boolean expected) {
    assertThat(Types.CS.equalsAny(
        new SomeStringType(value),
        new SomeStringType("aaa"),
        new SomeStringType("abc"),
        new SomeStringType("zzz")))
        .isEqualTo(expected);
  }

  // ─── CS (CaseSensistive) — equalsAny(CharSequenceType, Collection) ────────

  @Test
  void cs_equalsAny_charSequenceType_collection_withNullCollection_returnsFalse() {
    assertThat(Types.CS.equalsAny(new SomeCharSequenceType("abc"), (java.util.Collection<SomeCharSequenceType>) null))
        .isFalse();
  }

  @ParameterizedTest(name = "[{index}] value={0}, expected={1}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALUE | EXPECTED
      abc   | true
      ABC   | false
      xyz   | false
      """)
  void cs_equalsAny_charSequenceType_collection_returnsAsExpected(
      final String value, final boolean expected) {
    assertThat(Types.CS.equalsAny(
        new SomeCharSequenceType(value),
        List.of(new SomeCharSequenceType("aaa"),
            new SomeCharSequenceType("abc"),
            new SomeCharSequenceType("zzz"))))
        .isEqualTo(expected);
  }

  // ─── CS (CaseSensistive) — equalsAny(StringType, Collection) ─────────────

  @Test
  void cs_equalsAny_stringType_collection_withNullCollection_returnsFalse() {
    assertThat(Types.CS.equalsAny(new SomeStringType("abc"), (java.util.Collection<SomeStringType>) null))
        .isFalse();
  }

  @ParameterizedTest(name = "[{index}] value={0}, expected={1}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALUE | EXPECTED
      abc   | true
      ABC   | false
      xyz   | false
      """)
  void cs_equalsAny_stringType_collection_returnsAsExpected(
      final String value, final boolean expected) {
    assertThat(Types.CS.equalsAny(
        SomeStringType.of(value),
        List.<SomeStringType>of(
            SomeStringType.of("aaa"),
            SomeStringType.of("abc"),
            SomeStringType.of("zzz"))))
        .isEqualTo(expected);
  }

  // ─── CI (CaseInsensitive) — equals(CharSequenceType) ────────────────────

  @Test
  void ci_equals_charSequenceType_withBothNull_returnsTrue() {
    assertThat(Types.CI.equals((SomeCharSequenceType) null, (SomeCharSequenceType) null)).isTrue();
  }

  @Test
  void ci_equals_charSequenceType_withSameInstance_returnsTrue() {
    final var value = new SomeCharSequenceType("abc");
    assertThat(Types.CI.equals(value, value)).isTrue();
  }

  @ParameterizedTest(name = "[{index}] value1={0}, value2={1}, expected={2}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      VALUE_1 | VALUE_2 | EXPECTED
      null    | null    | true
      null    | abc     | false
      abc     | null    | false
      abc     | abc     | true
      abc     | ABC     | true
      ABC     | abc     | true
      abc     | xyz     | false
      """)
  void ci_equals_charSequenceType_returnsAsExpected(
      final String value1, final String value2, final boolean expected) {

    final var v1 = value1 == null ? null : new SomeCharSequenceType(value1);
    final var v2 = value2 == null ? null : new SomeCharSequenceType(value2);

    assertThat(Types.CI.equals(v1, v2)).isEqualTo(expected);
  }

  // ─── CI (CaseInsensitive) — equals(StringType) ───────────────────────────

  @Test
  void ci_equals_stringType_withBothNull_returnsTrue() {
    assertThat(Types.CI.equals((SomeStringType) null, (SomeStringType) null)).isTrue();
  }

  @Test
  void ci_equals_stringType_withSameInstance_returnsTrue() {
    final var value = new SomeStringType("abc");
    assertThat(Types.CI.equals(value, value)).isTrue();
  }

  @ParameterizedTest(name = "[{index}] value1={0}, value2={1}, expected={2}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      VALUE_1 | VALUE_2 | EXPECTED
      null    | null    | true
      null    | abc     | false
      abc     | null    | false
      abc     | abc     | true
      abc     | ABC     | true
      ABC     | abc     | true
      abc     | xyz     | false
      """)
  void ci_equals_stringType_returnsAsExpected(
      final String value1, final String value2, final boolean expected) {

    final var v1 = value1 == null ? null : new SomeStringType(value1);
    final var v2 = value2 == null ? null : new SomeStringType(value2);

    assertThat(Types.CI.equals(v1, v2)).isEqualTo(expected);
  }

  // ─── CI (CaseInsensitive) — equalsAny(CharSequenceType, varargs) ─────────

  @Test
  void ci_equalsAny_charSequenceType_varargs_withNullOtherValues_returnsFalse() {
    assertThat(Types.CI.equalsAny(new SomeCharSequenceType("abc"), (SomeCharSequenceType[]) null))
        .isFalse();
  }

  @ParameterizedTest(name = "[{index}] value={0}, expected={1}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALUE | EXPECTED
      abc   | true
      ABC   | true
      xyz   | false
      """)
  void ci_equalsAny_charSequenceType_varargs_returnsAsExpected(
      final String value, final boolean expected) {
    assertThat(Types.CI.equalsAny(
        new SomeCharSequenceType(value),
        new SomeCharSequenceType("aaa"),
        new SomeCharSequenceType("abc"),
        new SomeCharSequenceType("zzz")))
        .isEqualTo(expected);
  }

  // ─── CI (CaseInsensitive) — equalsAny(StringType, varargs) ──────────────

  @Test
  void ci_equalsAny_stringType_varargs_withNullOtherValues_returnsFalse() {
    assertThat(Types.CI.equalsAny(new SomeStringType("abc"), (SomeStringType[]) null))
        .isFalse();
  }

  @ParameterizedTest(name = "[{index}] value={0}, expected={1}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALUE | EXPECTED
      abc   | true
      ABC   | true
      xyz   | false
      """)
  void ci_equalsAny_stringType_varargs_returnsAsExpected(
      final String value, final boolean expected) {
    assertThat(Types.CI.equalsAny(
        new SomeStringType(value),
        new SomeStringType("aaa"),
        new SomeStringType("abc"),
        new SomeStringType("zzz")))
        .isEqualTo(expected);
  }

  // ─── CI (CaseInsensitive) — equalsAny(CharSequenceType, Collection) ──────

  @Test
  void ci_equalsAny_charSequenceType_collection_withNullCollection_returnsFalse() {
    assertThat(Types.CI.equalsAny(new SomeCharSequenceType("abc"), (java.util.Collection<SomeCharSequenceType>) null))
        .isFalse();
  }

  @ParameterizedTest(name = "[{index}] value={0}, expected={1}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALUE | EXPECTED
      abc   | true
      ABC   | true
      xyz   | false
      """)
  void ci_equalsAny_charSequenceType_collection_returnsAsExpected(
      final String value, final boolean expected) {
    assertThat(Types.CI.equalsAny(
        new SomeCharSequenceType(value),
        List.of(new SomeCharSequenceType("aaa"),
            new SomeCharSequenceType("abc"),
            new SomeCharSequenceType("zzz"))))
        .isEqualTo(expected);
  }

  // ─── CI (CaseInsensitive) — equalsAny(StringType, Collection) ───────────

  @Test
  void ci_equalsAny_stringType_collection_withNullCollection_returnsFalse() {
    assertThat(Types.CI.equalsAny(new SomeStringType("abc"), (java.util.Collection<SomeStringType>) null))
        .isFalse();
  }

  @ParameterizedTest(name = "[{index}] value={0}, expected={1}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      VALUE | EXPECTED
      abc   | true
      ABC   | true
      xyz   | false
      """)
  void ci_equalsAny_stringType_collection_returnsAsExpected(
      final String value, final boolean expected) {
    assertThat(Types.CI.equalsAny(
        new SomeStringType(value),
        List.<SomeStringType>of(
            new SomeStringType("aaa"),
            new SomeStringType("abc"),
            new SomeStringType("zzz"))))
        .isEqualTo(expected);
  }

  // ─── CS (CaseSensitive) — compare(CharSequenceType) ─────────────────────

  @Test
  void cs_compare_charSequenceType_withBothNull_returnsZero() {
    assertThat(Types.CS.compare((SomeCharSequenceType) null, (SomeCharSequenceType) null)).isZero();
  }

  @Test
  void cs_compare_charSequenceType_withSameInstance_returnsZero() {
    final var value = new SomeCharSequenceType("abc");
    assertThat(Types.CS.compare(value, value)).isZero();
  }

  @ParameterizedTest(name = "[{index}] value1={0}, value2={1}, expectedSign={2}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      VALUE_1 | VALUE_2 | EXPECTED_SIGN
      null    | null    | zero
      null    | abc     | negative
      abc     | null    | positive
      abc     | abc     | zero
      abc     | abd     | negative
      abd     | abc     | positive
      abc     | ABC     | positive
      ABC     | abc     | negative
      abc     | abcd    | negative
      abcd    | abc     | positive
      """)
  void cs_compare_charSequenceType_returnsAsExpected(
      final String value1, final String value2, final String expectedSign) {
    final var v1 = value1 == null ? null : new SomeCharSequenceType(value1);
    final var v2 = value2 == null ? null : new SomeCharSequenceType(value2);
    final var result = Types.CS.compare(v1, v2);
    switch (expectedSign) {
      case "negative" -> assertThat(result).isNegative();
      case "positive" -> assertThat(result).isPositive();
      default -> assertThat(result).isZero();
    }
  }

  // ─── CS (CaseSensitive) — compare(StringType) ────────────────────────────

  @Test
  void cs_compare_stringType_withBothNull_returnsZero() {
    assertThat(Types.CS.compare((SomeStringType) null, (SomeStringType) null)).isZero();
  }

  @Test
  void cs_compare_stringType_withSameInstance_returnsZero() {
    final var value = new SomeStringType("abc");
    assertThat(Types.CS.compare(value, value)).isZero();
  }

  @ParameterizedTest(name = "[{index}] value1={0}, value2={1}, expectedSign={2}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      VALUE_1 | VALUE_2 | EXPECTED_SIGN
      null    | null    | zero
      null    | abc     | negative
      abc     | null    | positive
      abc     | abc     | zero
      abc     | abd     | negative
      abd     | abc     | positive
      abc     | ABC     | positive
      ABC     | abc     | negative
      abc     | abcd    | negative
      abcd    | abc     | positive
      """)
  void cs_compare_stringType_returnsAsExpected(
      final String value1, final String value2, final String expectedSign) {
    final var v1 = value1 == null ? null : new SomeStringType(value1);
    final var v2 = value2 == null ? null : new SomeStringType(value2);
    final var result = Types.CS.compare(v1, v2);
    switch (expectedSign) {
      case "negative" -> assertThat(result).isNegative();
      case "positive" -> assertThat(result).isPositive();
      default -> assertThat(result).isZero();
    }
  }

  // ─── CI (CaseInsensitive) — compare(CharSequenceType) ────────────────────

  @Test
  void ci_compare_charSequenceType_withBothNull_returnsZero() {
    assertThat(Types.CI.compare((SomeCharSequenceType) null, (SomeCharSequenceType) null)).isZero();
  }

  @Test
  void ci_compare_charSequenceType_withSameInstance_returnsZero() {
    final var value = new SomeCharSequenceType("abc");
    assertThat(Types.CI.compare(value, value)).isZero();
  }

  @ParameterizedTest(name = "[{index}] value1={0}, value2={1}, expectedSign={2}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      VALUE_1 | VALUE_2 | EXPECTED_SIGN
      null    | null    | zero
      null    | abc     | negative
      abc     | null    | positive
      abc     | abc     | zero
      abc     | ABC     | zero
      ABC     | abc     | zero
      abc     | abd     | negative
      abd     | abc     | positive
      abc     | abcd    | negative
      abcd    | abc     | positive
      """)
  void ci_compare_charSequenceType_returnsAsExpected(
      final String value1, final String value2, final String expectedSign) {
    final var v1 = value1 == null ? null : new SomeCharSequenceType(value1);
    final var v2 = value2 == null ? null : new SomeCharSequenceType(value2);
    final var result = Types.CI.compare(v1, v2);
    switch (expectedSign) {
      case "negative" -> assertThat(result).isNegative();
      case "positive" -> assertThat(result).isPositive();
      default -> assertThat(result).isZero();
    }
  }

  // ─── CI (CaseInsensitive) — compare(StringType) ──────────────────────────

  @Test
  void ci_compare_stringType_withBothNull_returnsZero() {
    assertThat(Types.CI.compare((SomeStringType) null, (SomeStringType) null)).isZero();
  }

  @Test
  void ci_compare_stringType_withSameInstance_returnsZero() {
    final var value = new SomeStringType("abc");
    assertThat(Types.CI.compare(value, value)).isZero();
  }

  @ParameterizedTest(name = "[{index}] value1={0}, value2={1}, expectedSign={2}")
  @CsvSource(delimiter = '|', nullValues = "null", useHeadersInDisplayName = true, textBlock = """
      VALUE_1 | VALUE_2 | EXPECTED_SIGN
      null    | null    | zero
      null    | abc     | negative
      abc     | null    | positive
      abc     | abc     | zero
      abc     | ABC     | zero
      ABC     | abc     | zero
      abc     | abd     | negative
      abd     | abc     | positive
      abc     | abcd    | negative
      abcd    | abc     | positive
      """)
  void ci_compare_stringType_returnsAsExpected(
      final String value1, final String value2, final String expectedSign) {
    final var v1 = value1 == null ? null : new SomeStringType(value1);
    final var v2 = value2 == null ? null : new SomeStringType(value2);
    final var result = Types.CI.compare(v1, v2);
    switch (expectedSign) {
      case "negative" -> assertThat(result).isNegative();
      case "positive" -> assertThat(result).isPositive();
      default -> assertThat(result).isZero();
    }
  }

  // ─── Some concrete classes ───────────────────────────────────────────────────

  /**
   * Minimal {@link CharSequenceType}.
   */
  private record SomeCharSequenceType(String value) implements CharSequenceType<SomeCharSequenceType> {

    @Override
    public String toString() {
      return value == null ? "" : value;
    }
  }

  /**
   * Minimal {@link StringType}.
   */
  private static final class SomeStringType extends StringType {

    private SomeStringType(final String value) {
      super(value);
    }

    public static SomeStringType of(final String value) {
      return new SomeStringType(value);
    }
  }
}
