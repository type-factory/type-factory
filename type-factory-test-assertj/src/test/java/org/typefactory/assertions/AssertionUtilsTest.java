package org.typefactory.assertions;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.Serializable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AssertionUtilsTest {

  @Test
  void isEmpty_returnsTrueForNullArray() {
    final Serializable[] value = null;
    assertThat(AssertionUtils.isEmpty(value)).isTrue();
    assertThat(AssertionUtils.isNotEmpty(value)).isFalse();
  }

  @Test
  void isEmpty_returnsTrueForEmptyArray() {
    final Serializable[] value = new Serializable[0];
    assertThat(AssertionUtils.isEmpty(value)).isTrue();
    assertThat(AssertionUtils.isNotEmpty(value)).isFalse();
  }

  @Test
  void isEmpty_returnsFalseForNonEmptyArray() {
    final Serializable[] value = new Serializable[]{"test"};
    assertThat(AssertionUtils.isEmpty(value)).isFalse();
    assertThat(AssertionUtils.isNotEmpty(value)).isTrue();
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      VALUE_1    | VALUE_2     | EXPECTED
      null       | null        | true
      null       | ''          | false
      null       | ' '         | false
      null       | some-value  | false
      ''         | null        | false
      ''         | ''          | true
      ''         | ' '         | false
      ''         | some-value  | false
      ' '        | null        | false
      ' '        | ''          | false
      ' '        | ' '         | true
      ' '        | some-value  | false
      some-value | null        | false
      some-value | ''          | false
      some-value | ' '         | false
      some-value | diff-value  | false
      some-value | some-value  | true
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void equals_returnsExpectedResult(
      final String value1, final String value2, final boolean expected) {

    assertThat(AssertionUtils.equals(value1, value2)).isEqualTo(expected);
    assertThat(AssertionUtils.notEquals(value1, value2)).isNotEqualTo(expected);

    final var charSequenceValue1 = SomeCharSequenceType.of(value1);
    final var charSequenceValue2 = SomeCharSequenceType.of(value2);

    assertThat(AssertionUtils.equals(charSequenceValue1, charSequenceValue2)).isEqualTo(expected);
    assertThat(AssertionUtils.notEquals(charSequenceValue1, charSequenceValue2)).isNotEqualTo(expected);
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
   VALUE  | EXPECTED
   _null_ | 'null'
   ''     | '"" (empty)'
   ' '    | '" " (blank)'
   '\t'   | '"\t" (blank)'
   'abc'  | '"abc"'
   ' a '  | '" a "'
   """, delimiter = '|', nullValues = "_null_", useHeadersInDisplayName = true)
  void valueOf_returnsExpectedRepresentation(final String value, final String expected) {
   assertThat(AssertionUtils.valueOf(value)).isEqualTo(expected);
  }

  private record SomeCharSequenceType(String value) implements CharSequence {
    @Override
    public int length() {
      return value.length();
    }

    @Override
    public char charAt(int index) {
      return value.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
      return value.subSequence(start, end);
    }
    static SomeCharSequenceType of(String value) {
      return value == null ? null : new SomeCharSequenceType(value);
    }
  }
}
