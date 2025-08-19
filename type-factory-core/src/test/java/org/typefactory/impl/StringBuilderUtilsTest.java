package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StringBuilderUtilsTest {

  @ParameterizedTest
  @CsvSource(textBlock = """
               1 | 0x00000001
              15 | 0x0000000f
             255 | 0x000000ff
            4095 | 0x00000fff
           65535 | 0x0000ffff
         1048575 | 0x000fffff
        16777215 | 0x00ffffff
       268435455 | 0x0fffffff
              -1 | 0xffffffff
             -15 | 0xfffffff1
            -255 | 0xffffff01
           -4095 | 0xfffff001
          -65535 | 0xffff0001
        -1048575 | 0xfff00001
       -16777215 | 0xff000001
      -268435455 | 0xf0000001
  """, delimiter = '|')
  void appendHexWithZeroPadding_ShouldAppendExpectedHexValue(int value, String expected) {

    final StringBuilder sb = new StringBuilder();
    StringBuilderUtils.appendHexWithZeroPadding(sb, value);
    final var actual = sb.toString();

    assertThat(actual).isEqualTo(expected);
  }

}
