package org.datatypeproject.generator.letters;

import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.text.UnicodeSet;
import com.ibm.icu.text.UnicodeSet.EntryRange;
import org.datatypeproject.generator.unicodedata.UnicodeGroupData;

class CJKUtils {

  static UnicodeSet japaneseJSourceSet = null;

  static UnicodeSet getCJKLetters() {
    final UnicodeSet cjkRanges = new UnicodeSet();
    cjkRanges.add(0x4E00, 0x62FF);    // CJK Unified Ideographs
    cjkRanges.add(0x6300, 0x77FF);    // CJK Unified Ideographs
    cjkRanges.add(0x7800, 0x8CFF);    // CJK Unified Ideographs
    cjkRanges.add(0x8D00, 0x9FFF);    // CJK Unified Ideographs
    cjkRanges.add(0x3400, 0x4DBF);    // CJK Unified Ideographs Extension A
    cjkRanges.add(0x20000, 0x215FF);  // CJK Unified Ideographs Extension B
    cjkRanges.add(0x21600, 0x230FF);  // CJK Unified Ideographs Extension B
    cjkRanges.add(0x23100, 0x245FF);  // CJK Unified Ideographs Extension B
    cjkRanges.add(0x24600, 0x260FF);  // CJK Unified Ideographs Extension B
    cjkRanges.add(0x26100, 0x275FF);  // CJK Unified Ideographs Extension B
    cjkRanges.add(0x27600, 0x290FF);  // CJK Unified Ideographs Extension B
    cjkRanges.add(0x29100, 0x2A6DF);  // CJK Unified Ideographs Extension B
    cjkRanges.add(0x2A700, 0x2B73F);  // CJK Unified Ideographs Extension C
    cjkRanges.add(0x2B740, 0x2B81F);  // CJK Unified Ideographs Extension D
    cjkRanges.add(0x2B820, 0x2CEAF);  // CJK Unified Ideographs Extension E
    cjkRanges.add(0x2CEB0, 0x2EBEF);  // CJK Unified Ideographs Extension F
    cjkRanges.add(0x30000, 0x3134F);  // CJK Unified Ideographs Extension G
    cjkRanges.add(0xF900, 0xFAFF);    // CJK Compatibility Ideographs

    cjkRanges.compact();
    cjkRanges.freeze();

    final UnicodeSet cjkLetters = new UnicodeSet();

    for (EntryRange entryRange : cjkRanges.ranges()) {
      for (int codePoint = entryRange.codepoint; codePoint <= entryRange.codepointEnd; ++codePoint) {
        if (UCharacter.isLetter(codePoint)) {
          cjkLetters.add(codePoint);
        }
      }
    }
    cjkLetters.compact();
    cjkRanges.freeze();
    return cjkLetters;
  }

  static UnicodeSet getJapaneseJSourceLetters() {
    if (japaneseJSourceSet == null) {
      final UnicodeGroupData unicodeGroupData = UnicodeGroupData.INSTANCE;
      final UnicodeSet result = new UnicodeSet();
      for (EntryRange range : unicodeGroupData.getJSourceSubset().ranges()) {
        for (int codePoint = range.codepoint; codePoint <= range.codepointEnd; ++codePoint) {
          if (UCharacter.isLetter(codePoint)) {
            result.add(codePoint);
          }
        }
      }
      japaneseJSourceSet = result.compact().freeze();
    }
    return japaneseJSourceSet;
  }

}
