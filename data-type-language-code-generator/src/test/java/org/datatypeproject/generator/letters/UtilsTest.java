package org.datatypeproject.generator.letters;

import com.ibm.icu.text.UnicodeSet;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UtilsTest {

  @Test
  void loadUnicodeSetFromFile_loadsAsExpected() {

    final UnicodeSet actual = Utils.loadUnicodeSetFromFile("japanese-ja-hani-jinmeiyo.psv");

    Assertions.assertThat(actual.contains(0x004E11)).isTrue();
  }
}
