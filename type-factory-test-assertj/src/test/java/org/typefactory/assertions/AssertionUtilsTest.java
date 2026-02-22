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
      VALUE_1    | VALUE_2    | EXPECTED
      null       | null       | true
      null       | ''         | false
      null       | ' '        | false
      null       | some-value | false
      ''         | null       | false
      ''         | ''         | true
      ''         | ' '        | false
      ''         | some-value | false
      ' '        | null       | false
      ' '        | ''         | false
      ' '        | ' '        | true
      ' '        | some-value | false
      some-value | null       | false
      some-value | ''         | false
      some-value | ' '        | false
      some-value | some-value | true
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void equals_returnsExpectedResult(
      final String value1, final String value2, final boolean expected) {
    assertThat(AssertionUtils.equals(value1, value2)).isEqualTo(expected);
    assertThat(AssertionUtils.notEquals(value1, value2)).isNotEqualTo(expected);
  }

  @ParameterizedTest(name = "[{index}] codePoint={0} -> {1}")
  @CsvSource(textBlock = """
      0x0020   | 'U+0020 SPACE'
      0x0009   | 'U+0009 CHARACTER TABULATION'
      0x000A   | 'U+000A LINE FEED (LF)'
      0x000D   | 'U+000D CARRIAGE RETURN (CR)'
      0x0041   | 'A U+0041 LATIN CAPITAL LETTER A'
      0x0061   | 'a U+0061 LATIN SMALL LETTER A'
      0x1F600  | '😀 U+01F600 GRINNING FACE'
      0x10FFFF | 'U+10FFFF UNASSIGNED UNICODE CHARACTER'
      0xD800   | 'U+D800 HIGH SURROGATE'
      0xDC00   | 'U+DC00 LOW SURROGATE'
      0xDBFF   | 'U+DBFF HIGH SURROGATE'
      0xDFFF   | 'U+DFFF LOW SURROGATE'
      """, delimiter = '|')
  void codePointToString_returnsExpectedFormat(int codePoint, String expected) {
    final String actual = AssertionUtils.codePointToString(codePoint);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void codePointToString_handlesControlCharacters() {
    final String actual = AssertionUtils.codePointToString(0x0000);
    assertThat(actual)
        .startsWith("U+0000")
        .contains("NULL");
  }

  @Test
  void codePointToString_handlesFormatCharacters() {
    final String actual = AssertionUtils.codePointToString(0x200E);
    assertThat(actual)
        .startsWith("U+200E")
        .contains("LEFT-TO-RIGHT MARK");
  }

  @Test
  void codePointToString_handlesInvalidCodePoint() {
    final String actual = AssertionUtils.codePointToString(0x110000);
    assertThat(actual)
        .startsWith("U+00110000")
        .contains("NOT A UNICODE CHARACTER");
  }

  @Test
  void codePointToString_handlesLargeInvalidCodePoint() {
    final String actual = AssertionUtils.codePointToString(0x1000000);
    assertThat(actual)
        .startsWith("U+01000000")
        .contains("NOT A UNICODE CHARACTER");
  }

  @Test
  void codePointToString_handlesUnassignedCharacter() {
    final String actual = AssertionUtils.codePointToString(0x0378);
    assertThat(actual)
        .startsWith("U+0378")
        .contains("UNASSIGNED UNICODE CHARACTER");
  }

  @Test
  void codePointToString_handlesUnassignedSupplementaryCharacter() {
    final String actual = AssertionUtils.codePointToString(0x3FFFF);
    assertThat(actual)
        .startsWith("U+03FFFF")
        .contains("UNASSIGNED UNICODE CHARACTER");
  }

  @Test
  void codePointToString_includesCharacterForPrintableCharacters() {
    final String actual = AssertionUtils.codePointToString(0x0041);
    assertThat(actual)
        .startsWith("A")
        .contains("U+0041")
        .contains("LATIN CAPITAL LETTER A");
  }

  @Test
  void codePointToString_includesCharacterForEmoji() {
    final String actual = AssertionUtils.codePointToString(0x1F600);
    assertThat(actual)
        .startsWith("😀")
        .contains("U+1F600")
        .contains("GRINNING FACE");
  }
  
  @ParameterizedTest(name = "[{index}] codePoint={0} -> {1}")
  @CsvSource(textBlock = """
      0x0020   | 'U+0020 SPACE'
      0x0009   | 'U+0009 CHARACTER TABULATION'
      0x000A   | 'U+000A LINE FEED (LF)'
      0x000D   | 'U+000D CARRIAGE RETURN (CR)'
      0x0041   | 'A U+0041 LATIN CAPITAL LETTER A'
      0x0061   | 'a U+0061 LATIN SMALL LETTER A'
      0x1F600  | '😀 U+01F600 GRINNING FACE'
      0x10FFFF | 'U+10FFFF UNASSIGNED UNICODE CHARACTER'
      0xD800   | 'U+D800 HIGH SURROGATE'
      0xDC00   | 'U+DC00 LOW SURROGATE'
      0xDBFF   | 'U+DBFF HIGH SURROGATE'
      0xDFFF   | 'U+DFFF LOW SURROGATE'
      """, delimiter = '|')
  void unicodeHexCode_returnsExpectedFormat(final int codePoint, final String expected) {
    final String actual = AssertionUtils.unicodeHexCode(codePoint);
    assertThat(actual).isEqualTo(expected);
  }
  
  @Test
  void unicodeHexCode_handlesControlCharacters() {
    final String actual = AssertionUtils.unicodeHexCode(0x0000);
    assertThat(actual)
        .startsWith("U+0000")
        .contains("NULL");
  }
  
  @Test
  void unicodeHexCode_handlesFormatCharacters() {
    final String actual = AssertionUtils.unicodeHexCode(0x200E);
    assertThat(actual)
        .startsWith("U+200E")
        .contains("LEFT-TO-RIGHT MARK");
  }
  
  @Test
  void unicodeHexCode_handlesInvalidCodePoint() {
    final String actual = AssertionUtils.unicodeHexCode(0x110000);
    assertThat(actual)
        .startsWith("U+00110000")
        .contains("NOT A UNICODE CHARACTER");
  }
  
  @Test
  void unicodeHexCode_handlesLargeInvalidCodePoint() {
    final String actual = AssertionUtils.unicodeHexCode(0x1000000);
    assertThat(actual)
        .startsWith("U+01000000")
        .contains("NOT A UNICODE CHARACTER");
  }
  
  @Test
  void unicodeHexCode_handlesUnassignedCharacter() {
    final String actual = AssertionUtils.unicodeHexCode(0x0378);
    assertThat(actual)
        .startsWith("U+0378")
        .contains("UNASSIGNED UNICODE CHARACTER");
  }
  
  @Test
  void unicodeHexCode_handlesUnassignedSupplementaryCharacter() {
    final String actual = AssertionUtils.unicodeHexCode(0x3FFFF);
    assertThat(actual)
        .startsWith("U+03FFFF")
        .contains("UNASSIGNED UNICODE CHARACTER");
  }
  
  @Test
  void unicodeHexCode_includesCharacterForPrintableCharacters() {
    final String actual = AssertionUtils.unicodeHexCode(0x0041);
    assertThat(actual)
        .startsWith("A")
        .contains("U+0041")
        .contains("LATIN CAPITAL LETTER A");
  }
  
  @Test
  void unicodeHexCode_includesCharacterForEmoji() {
    final String actual = AssertionUtils.unicodeHexCode(0x1F600);
    assertThat(actual)
        .startsWith("😀")
        .contains("U+1F600")
        .contains("GRINNING FACE");
  }
}
