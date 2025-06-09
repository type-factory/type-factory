package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ParseResultImplTest extends AbstractTypeParserTest {


  @Test
  void parseResultTest() {

    final var parseResult = new ParseResultImpl();
    parseResult.setParsedValue("hello");
    parseResult.addInvalidCodePoint('\n');
    parseResult.addInvalidCodePoint('\b');
    parseResult.addInvalidCodePoint(' ');
    parseResult.addInvalidCodePoint('a');
    parseResult.addInvalidCodePoint('\'');
    parseResult.addInvalidCodePoint('\u200B');

    assertThat(parseResult.parsedValue()).isEqualTo("hello");
    assertThat(parseResult.parsedValueWasValid()).isFalse();
    assertThat(parseResult.parsedValueWasInvalid()).isTrue();
    assertThat(parseResult.invalidCodePointsToString()).isEqualTo("[U+0008 BACKSPACE, U+000A LINE FEED (LF), U+0020 SPACE, ' U+0027 APOSTROPHE, 'a' U+0061 LATIN SMALL LETTER A, U+200B ZERO WIDTH SPACE]");
    assertThat(parseResult.toString()).isEqualTo("{parsedValue: hello, parsedValueWasValid: false, invalidCodePoints: [U+0008 BACKSPACE, U+000A LINE FEED (LF), U+0020 SPACE, ' U+0027 APOSTROPHE, 'a' U+0061 LATIN SMALL LETTER A, U+200B ZERO WIDTH SPACE]}");
  }

}
