/*
 * Copyright © 2021-2026 Evan Toliopoulos (typefactory.org)
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
package org.typefactory.impl;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static org.typefactory.Category.MARK;
import static org.typefactory.Category.MODIFIER_LETTER;
import static org.typefactory.Category.codePointIsInOneOfTheCategories;

import java.lang.Character.UnicodeScript;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import org.typefactory.Category;
import org.typefactory.Subset.CodePointRange;

final class SubsetUtils {

  private SubsetUtils() {
    // don't instantiate me
  }

  /**
   * Category bit flags for control and format characters
   */
  static final long SPACE_CONTROL_AND_FORMAT_CATEGORY_BIT_FLAGS =
      Category.getCategoryBitFlags(
          Category.CONTROL,
          Category.FORMAT,
          Category.SPACE_SEPARATOR,
          Category.LINE_SEPARATOR,
          Category.PARAGRAPH_SEPARATOR);

  static final long MODIFIER_AND_MARK_CATEGORY_BIT_FLAGS =
      Category.getCategoryBitFlags(
          MODIFIER_LETTER,
          MARK);

  static final char DOTTED_CIRCLE = '\u25CC';
  static final char NBSP = '\u00A0';

  // Set of scripts managed by complex shaping engines that auto-inject Unicode Dotted Circle ◌ (U+25CC)
  static final Set<UnicodeScript> COMPLEX_FONT_SHAPED_SCRIPTS = Set.of(

      // Indic / South Asian Scripts (Dedicated Indic Engine)
      // These Abugida scripts feature complex consonant conjuncts, split vowel signs (matras)
      // that reorder visually before preceding letters, and automatic fallback base injection (U+25CC).
      Character.UnicodeScript.DEVANAGARI,
      Character.UnicodeScript.BENGALI,
      Character.UnicodeScript.GURMUKHI,
      Character.UnicodeScript.GUJARATI,
      Character.UnicodeScript.ORIYA, // (Odia)
      Character.UnicodeScript.TAMIL,
      Character.UnicodeScript.TELUGU,
      Character.UnicodeScript.KANNADA,
      Character.UnicodeScript.MALAYALAM,
      Character.UnicodeScript.SINHALA,

      // Cursive & Right-To-Left Scripts (Arabic Engine)
      // These scripts require dynamic joining states (isolated, initial, medial, final glyph forms)
      // based on adjacent code points, along with mark positioning.
      Character.UnicodeScript.ARABIC,
      Character.UnicodeScript.SYRIAC,
      Character.UnicodeScript.NKO,
      Character.UnicodeScript.MONGOLIAN, // (Vertical cursive joining)
      Character.UnicodeScript.THAANA,
      Character.UnicodeScript.ADLAM,
      Character.UnicodeScript.HANIFI_ROHINGYA,
      Character.UnicodeScript.SOGDIAN,
      Character.UnicodeScript.OLD_SOGDIAN,
      Character.UnicodeScript.MANICHAEAN,
      Character.UnicodeScript.MANDAIC,
      Character.UnicodeScript.CHORASMIAN,
      Character.UnicodeScript.ELYMAIC,
      Character.UnicodeScript.SAMARITAN,

      // Southeast Asian & Specialized Scripts
      // Scripts with unique, highly specialized rules for vertical stacking,
      // cluster formation, or non-linear vowel positioning:
      Character.UnicodeScript.THAI, // (Logical-to-visual mark stacking)
      Character.UnicodeScript.LAO,
      Character.UnicodeScript.KHMER, // (Subjoined consonants and reordering)
      Character.UnicodeScript.MYANMAR, // (Kinzi and medial reordering)
      Character.UnicodeScript.TIBETAN, // (Vertical glyph stacking)
      Character.UnicodeScript.HANGUL, // (Jamo-to-syllable block composition)
      Character.UnicodeScript.HEBREW, // (Niqqud and Cantillation mark positioning)

      // Southeast Asian, Indonesian & Philippine
      // Universal Shaping Engine (USE) uses a generalized finite-state machine to shape all
      // remaining modern, regional, and historical complex scripts
      Character.UnicodeScript.BALINESE,
      Character.UnicodeScript.BATAK,
      Character.UnicodeScript.BUGINESE,
      Character.UnicodeScript.BUHID,
      Character.UnicodeScript.CHAM,
      Character.UnicodeScript.HANUNOO,
      Character.UnicodeScript.JAVANESE,
      Character.UnicodeScript.KAYAH_LI,
      Character.UnicodeScript.LEPCHA,
      Character.UnicodeScript.LIMBU,
      Character.UnicodeScript.MAKASAR,
      Character.UnicodeScript.NEW_TAI_LUE,
      Character.UnicodeScript.REJANG,
      Character.UnicodeScript.SUNDANESE,
      Character.UnicodeScript.TAGALOG, //(Baybayin)
      Character.UnicodeScript.TAGBANWA,
      Character.UnicodeScript.TAI_LE,
      Character.UnicodeScript.TAI_THAM,
      Character.UnicodeScript.TAI_VIET,

      // Historical & Regional South Asian / Himalayan
      Character.UnicodeScript.AHOM,
      Character.UnicodeScript.BHAIKSUKI,
      Character.UnicodeScript.BRAHMI,
      Character.UnicodeScript.CHAKMA,
      Character.UnicodeScript.DIVES_AKURU,
      Character.UnicodeScript.DOGRA,
      Character.UnicodeScript.GRANTHA,
      Character.UnicodeScript.GUNJALA_GONDI,
      Character.UnicodeScript.KAITHI,
      Character.UnicodeScript.KHOJKI,
      Character.UnicodeScript.KHUDAWADI,
      Character.UnicodeScript.MAHAJANI,
      Character.UnicodeScript.MARCHEN,
      Character.UnicodeScript.MASARAM_GONDI,
      Character.UnicodeScript.MEETEI_MAYEK,
      Character.UnicodeScript.MODI,
      Character.UnicodeScript.MULTANI,
      Character.UnicodeScript.NANDINAGARI,
      Character.UnicodeScript.NEWA,
      Character.UnicodeScript.SHARADA,
      Character.UnicodeScript.SIDDHAM,
      Character.UnicodeScript.SORA_SOMPENG,
      Character.UnicodeScript.SOYOMBO,
      Character.UnicodeScript.SYLOTI_NAGRI,
      Character.UnicodeScript.TAKRI,
      Character.UnicodeScript.TIRHUTA,
      Character.UnicodeScript.ZANABAZAR_SQUARE,

      // Hieroglyphic & Complex Joiner Scripts
      Character.UnicodeScript.ANATOLIAN_HIEROGLYPHS,
      Character.UnicodeScript.DUPLOYAN,
      Character.UnicodeScript.EGYPTIAN_HIEROGLYPHS
  );

  /**
   * <p>Extracts the 8-bit 'inclusive-from' value from a code-point range comprised of two 8-bit values stored in a {@code char} primitive. The
   * 'inclusive-from' is stored in the 8 most-significant bits of the {@code char} primitive.</p>
   *
   * <p>The complement to this method is the {@link #rangeToChar(int, int)} to create a code-point range.</p>
   *
   * @param codePointRange a code-point range specifying two 8-bit values representing an 'inclusive-from' and an 'inclusive-to'. The 'inclusive-from'
   *                       occupies the 8 most-significant bits and the 'inclusive-to' occupies the 8 least significant bits.
   * @return the 8-bit 'inclusive-from' code-point value as an {@code int} primitive.
   * @see #rangeToChar(int, int)
   */
  static int getInclusiveFrom(final char codePointRange) {
    return codePointRange >>> 8;
  }

  /**
   * <p>Extracts the 8-bit 'inclusive-to' value from a code-point range comprised of two 8-bit values stored in a {@code char} primitive. The
   * 'inclusive-to' is stored in the 8 least-significant bits of the {@code char} primitive.</p>
   *
   * <p>The complement to this method is the {@link #rangeToChar(int, int)} to create a code-point range.</p>
   *
   * @param codePointRange a code-point range specifying two 8-bit values representing an 'inclusive-from' and an 'inclusive-to'. The 'inclusive-from'
   *                       occupies the 8 most-significant bits and the 'inclusive-to' occupies the 8 least significant bits.
   * @return the 8-bit 'inclusive-to' code-point value as an {@code int} primitive.
   * @see #rangeToChar(int, int)
   */
  static int getInclusiveTo(final char codePointRange) {
    return codePointRange & 0x00_ff;
  }

  /**
   * <p>Extracts the 16-bit 'inclusive-from' value from a code-point range comprised of two 16-bit values stored in an {@code int} primitive. The
   * 'inclusive-from' is stored in the 16 most-significant bits of the {@code int} primitive.</p>
   *
   * <p>The complement to this method is the {@link #rangeToInt(int, int)} to create a code-point range.</p>
   *
   * <p>Note: you may may be wondering if this is possible since an {@code int} primitive is a 'signed' value in Java. Let go of that and think
   * of the {@code int} primitive as a house for data. We are never using the {@code int} directly, we are only ever using the two smaller values
   * stored within it.</p>
   *
   * @param codePointRange a code-point range specifying two 16-bit values representing an 'inclusive-from' and an 'inclusive-to'. The
   *                       'inclusive-from' occupies the 16 most-significant bits and the 'inclusive-to' occupies the 16 least significant bits.
   * @return the 16-bit 'inclusive-from' code-point value as an {@code int} primitive.
   * @see #rangeToInt(int, int)
   */
  static int getInclusiveFrom(final int codePointRange) {
    return codePointRange >>> 16;
  }

  /**
   * <p>Extracts the 16-bit 'inclusive-to' value from a code-point range comprised of two 16-bit values stored in a {@code int} primitive. The
   * 'inclusive-to' is stored in the 16 least-significant bits of the {@code int} primitive.</p>
   *
   * <p>The complement to this method is the {@link #rangeToInt(int, int)} to create a code-point range.</p>
   *
   * <p>Note: you may may be wondering if this is possible since an {@code int} primitive is a 'signed' value in Java. Let go of that and think
   * of the {@code int} primitive as a house for data. We are never using the {@code int} directly, we are only ever using the two smaller values
   * stored within it.</p>
   *
   * @param codePointRange a code-point range specifying two 16-bit values representing an 'inclusive-from' and an 'inclusive-to'. The
   *                       'inclusive-from' occupies the 16 most-significant bits and the 'inclusive-to' occupies the 16 least significant bits.
   * @return the 16-bit 'inclusive-to' code-point value as an {@code int} primitive.
   * @see #rangeToInt(int, int)
   */
  static int getInclusiveTo(final int codePointRange) {
    return codePointRange & 0x0000_ffff;
  }

  /**
   * <p>Extracts the 24-bit 'inclusive-from' value from a code-point range comprised of two 24-bit values stored in an {@code long} primitive. The
   * 'inclusive-from' is stored in the 32 most-significant bits of the {@code long} primitive.</p>
   *
   * <p>The complement to this method is the {@link #rangeToLong(int, int)} to create a code-point range.</p>
   *
   * <p>Note: you may may be wondering if this is possible since a {@code long} primitive is a 'signed' value in Java. Let go of that and think
   * of the {@code long} primitive as a house for data. Our range values are only 24 bits and not 32 bits, so we will never have to worry about the
   * sign bit - it will always be zero.</p>
   *
   * @param codePointRange a code-point range specifying two 24-bit values representing an 'inclusive-from' and an 'inclusive-to'. The
   *                       'inclusive-from' occupies the 32 most-significant bits and the 'inclusive-to' occupies the 32 least significant bits.
   * @return the 24-bit 'inclusive-from' code-point value as an {@code int} primitive.
   * @see #rangeToLong(int, int)
   */
  static int getInclusiveFrom(final long codePointRange) {
    return (int) (codePointRange >>> 32);
  }

  /**
   * <p>Extracts the 24-bit 'inclusive-to' value from a code-point range comprised of two 24-bit values stored in a {@code long} primitive. The
   * 'inclusive-to' is stored in the 32 least-significant bits of the {@code long} primitive.</p>
   *
   * <p>The complement to this method is the {@link #rangeToLong(int, int)} to create a code-point range.</p>
   *
   * <p>Note: you may may be wondering if this is possible since a {@code long} primitive is a 'signed' value in Java. Let go of that and think
   * of the {@code long} primitive as a house for data. Our range values are only 24 bits and not 32 bits, so we will never have to worry about the
   * sign bit - it will always be zero.</p>
   *
   * @param codePointRange a code-point range specifying two 24-bit values representing an 'inclusive-from' and an 'inclusive-to'. The
   *                       'inclusive-from' occupies the 32 most-significant bits and the 'inclusive-to' occupies the 32 least significant bits.
   * @return the 24-bit 'inclusive-to' code-point value as an {@code int} primitive.
   * @see #rangeToLong(int, int)
   */
  static int getInclusiveTo(final long codePointRange) {
    return (int) (codePointRange & 0x00000000_ffffffffL);
  }

  /**
   * <p>Converts an inclusive-from to inclusive-to code-point range to a 16-bit {@code char} value.
   * It will automatically correct the range if the {@code inclusiveFrom} and {@code inclusiveTo} values have accidentally been reversed.</p>
   *
   * <p>The complement to this method are the {@link #getInclusiveFrom(char)} and {@link #getInclusiveTo(char)} methods
   * to extract the individual values from from the range.</p>
   *
   * @param inclusiveFrom the inclusive 'from' code-point.
   * @param inclusiveTo   the inclusive 'to' code-point.
   * @return the inclusive-from to inclusive-to code-point range as a 16-bit {@code char} value.
   */
  static char rangeToChar(final int inclusiveFrom, final int inclusiveTo) {
    return (char) ((min(inclusiveFrom, inclusiveTo) << 8) | (max(inclusiveFrom, inclusiveTo) & 0x00_ff));
  }

  /**
   * <p>Converts an inclusive-from to inclusive-to code-point range to a 32-bit {@code int} value.
   * It will automatically correct the range if the {@code inclusiveFrom} and {@code inclusiveTo} values have accidentally been reversed.</p>
   *
   * <p>The complement to this method are the {@link #getInclusiveFrom(int)} and {@link #getInclusiveTo(int)} methods
   * to extract the individual values from from the range.</p>
   *
   * @param inclusiveFrom the inclusive 'from' code-point.
   * @param inclusiveTo   the inclusive 'to' code-point.
   * @return the inclusive-from to inclusive-to code-point range as a 32-bit {@code int} value.
   */
  static int rangeToInt(final int inclusiveFrom, final int inclusiveTo) {
    return (min(inclusiveFrom, inclusiveTo) << 16) | (max(inclusiveFrom, inclusiveTo) & 0x0000_ffff);
  }

  /**
   * <p>Converts an inclusive-from to inclusive-to code-point range to a 64-bit {@code long} value.
   * It will automatically correct the range if the {@code inclusiveFrom} and {@code inclusiveTo} values have accidentally been reversed.</p>
   *
   * <p>The complement to this method are the {@link #getInclusiveFrom(long)} and {@link #getInclusiveTo(long)} methods
   * to extract the individual values from from the range.</p>
   *
   * @param inclusiveFrom the inclusive 'from' code-point.
   * @param inclusiveTo   the inclusive 'to' code-point.
   * @return the inclusive-from to inclusive-to code-point range as a 64-bit {@code int} value.
   */
  static long rangeToLong(final int inclusiveFrom, final int inclusiveTo) {
    return ((long) min(inclusiveFrom, inclusiveTo) << 32) | max(inclusiveFrom, inclusiveTo);
  }

  static int numberOfCodePointsInRanges(final char[] singleByteCodePointRanges) {
    if (singleByteCodePointRanges == null) {
      return 0;
    }
    int count = 0;
    for (char range : singleByteCodePointRanges) {
      count += getInclusiveTo(range) - getInclusiveFrom(range) + 1;
    }
    return count;
  }

  static int numberOfCodePointsInRanges(final int[] doubleByteCodePointRanges) {
    if (doubleByteCodePointRanges == null) {
      return 0;
    }
    int count = 0;
    for (int range : doubleByteCodePointRanges) {
      count += getInclusiveTo(range) - getInclusiveFrom(range) + 1;
    }
    return count;
  }

  static int numberOfCodePointsInRanges(final long[] tripleByteCodePointRanges) {
    if (tripleByteCodePointRanges == null) {
      return 0;
    }
    int count = 0;
    for (long range : tripleByteCodePointRanges) {
      count += getInclusiveTo(range) - getInclusiveFrom(range) + 1;
    }
    return count;
  }

  static char[] defaultIfNullOrEmpty(final char[] array, final char[] defaultArray) {
    return array == null || array.length == 0 ? defaultArray : array;
  }

  static int[] defaultIfNullOrEmpty(final int[] array, final int[] defaultArray) {
    return array == null || array.length == 0 ? defaultArray : array;
  }

  static long[] defaultIfNullOrEmpty(final long[] array, final long[] defaultArray) {
    return array == null || array.length == 0 ? defaultArray : array;
  }

  /**
   * Returns a collection of {@link CodePointRange} objects represent code-point ranges from the raw range data stored in the three specified arrays
   * of primitives.
   *
   * @param singleByteCodePointRanges An array of {@code char} primitives with each value containing a code-point range specifying two 8-bit values
   *                                  representing an 'inclusive-from' and an 'inclusive-to'. The 'inclusive-from' occupies the 8 most-significant
   *                                  bits and the 'inclusive-to' occupies the 8 least significant bits.
   * @param doubleByteCodePointRanges An array of {@code int} primitives with each value containing a code-point range specifying two 16-bit values
   *                                  representing an 'inclusive-from' and an 'inclusive-to'. The 'inclusive-from' occupies the 16 most-significant
   *                                  bits and the 'inclusive-to' occupies the 16 least significant bits.
   * @param tripleByteCodePointRanges An array of {@code long} primitives with each value containing a code-point range specifying two 24-bit values
   *                                  representing an 'inclusive-from' and an 'inclusive-to'. The 'inclusive-from' occupies the 32 most-significant
   *                                  bits and the 'inclusive-to' occupies the 32 least significant bits.
   * @return a collection of {@link CodePointRange} objects representing code-point ranges.
   */
  static Collection<CodePointRange> aggregateCodePointRangeData(
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges) {

    final ArrayList<CodePointRange> result = new ArrayList<>();
    if (singleByteCodePointRanges != null) {
      for (char range : singleByteCodePointRanges) {
        result.add(new CodePointRange(getInclusiveFrom(range), getInclusiveTo(range)));
      }
    }
    if (doubleByteCodePointRanges != null) {
      for (int range : doubleByteCodePointRanges) {
        result.add(new CodePointRange(getInclusiveFrom(range), getInclusiveTo(range)));
      }
    }
    if (tripleByteCodePointRanges != null) {
      for (long range : tripleByteCodePointRanges) {
        result.add(new CodePointRange(getInclusiveFrom(range), getInclusiveTo(range)));
      }
    }
    return result;
  }

  static boolean contains(
      final int codePoint,
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges) {

    if (codePoint < 0x100) {
      if (singleByteCodePointRanges == null ||
          singleByteCodePointRanges.length == 0 ||
          codePoint < getInclusiveFrom(singleByteCodePointRanges[0]) ||
          codePoint > getInclusiveTo(singleByteCodePointRanges[singleByteCodePointRanges.length - 1])) {
        return false; // not found, outside of range
      }
      // Try to find using a binary search
      int low = 0;
      int high = singleByteCodePointRanges.length - 1;
      while (low <= high) {
        final int mid = (low + high) >>> 1; // divide by 2
        final int inclusiveFrom = getInclusiveFrom(singleByteCodePointRanges[mid]);
        final int inclusiveTo = getInclusiveTo(singleByteCodePointRanges[mid]);
        if (codePoint > inclusiveTo) {
          low = mid + 1;
        } else if (codePoint < inclusiveFrom) {
          high = mid - 1;
        } else {
          return true; // found
        }
      }
    } else if (codePoint < 0x10000) {
      if (doubleByteCodePointRanges == null ||
          doubleByteCodePointRanges.length == 0 ||
          codePoint < getInclusiveFrom(doubleByteCodePointRanges[0]) ||
          codePoint > getInclusiveTo(doubleByteCodePointRanges[doubleByteCodePointRanges.length - 1])) {
        return false; // not found, outside of range
      }
      // Try to find using a binary search
      int low = 0;
      int high = doubleByteCodePointRanges.length - 1;
      while (low <= high) {
        final int mid = (low + high) >>> 1; // divide by 2
        final int inclusiveFrom = getInclusiveFrom(doubleByteCodePointRanges[mid]);
        final int inclusiveTo = getInclusiveTo(doubleByteCodePointRanges[mid]);
        if (codePoint > inclusiveTo) {
          low = mid + 1;
        } else if (codePoint < inclusiveFrom) {
          high = mid - 1;
        } else {
          return true; // found
        }
      }
    } else {
      if (tripleByteCodePointRanges == null ||
          tripleByteCodePointRanges.length == 0 ||
          codePoint < getInclusiveFrom(tripleByteCodePointRanges[0]) ||
          codePoint > getInclusiveTo(tripleByteCodePointRanges[tripleByteCodePointRanges.length - 1])) {
        return false; // not found, outside of range
      }
      // Try to find using a binary search
      int low = 0;
      int high = tripleByteCodePointRanges.length - 1;
      while (low <= high) {
        final int mid = (low + high) >>> 1; // divide by 2
        final long midVal = tripleByteCodePointRanges[mid];
        final int inclusiveFrom = getInclusiveFrom(midVal);
        final int inclusiveTo = getInclusiveTo(midVal);
        if (codePoint > inclusiveTo) {
          low = mid + 1;
        } else if (codePoint < inclusiveFrom) {
          high = mid - 1;
        } else {
          return true; // found
        }
      }
    }
    return false; // not found
  }

  static void unsignedIntegerBubbleSort(final int[] values, int fromIndex, int toIndex) {
    int temp;
    for (int i = fromIndex; i < toIndex; i++) {
      for (int j = fromIndex; j < toIndex - i - 1; j++) {
        if ((0x00000000_FFFFFFFFL & values[j]) > (0x00000000_FFFFFFFFL & values[j + 1])) {
          // swap the elements
          temp = values[j];
          values[j] = values[j + 1];
          values[j + 1] = temp;
        }
      }
    }
  }

  static int compactSingleByteCodePointRanges(final char[] singleByteCodePointRanges, final int length) {

    Arrays.sort(singleByteCodePointRanges, 0, length);
    int resultLength = length;
    int previousInclusiveFrom;
    int previousInclusiveTo;
    int currentInclusiveFrom;
    int currentInclusiveTo;
    int i = 0;
    int j = 1;
    while (j < resultLength) {
      previousInclusiveFrom = getInclusiveFrom(singleByteCodePointRanges[i]);
      previousInclusiveTo = getInclusiveTo(singleByteCodePointRanges[i]);
      currentInclusiveFrom = getInclusiveFrom(singleByteCodePointRanges[j]);
      currentInclusiveTo = getInclusiveTo(singleByteCodePointRanges[j]);
      if (previousInclusiveTo >= currentInclusiveFrom) {
        if (previousInclusiveTo <= currentInclusiveTo) {
          singleByteCodePointRanges[i] = rangeToChar(previousInclusiveFrom, currentInclusiveTo);
        }
        resultLength = removeSingleByteElement(singleByteCodePointRanges, resultLength, j);
      } else if ((previousInclusiveTo + 1) == currentInclusiveFrom) {
        singleByteCodePointRanges[i] = rangeToChar(previousInclusiveFrom, currentInclusiveTo);
        resultLength = removeSingleByteElement(singleByteCodePointRanges, resultLength, j);
      } else {
        ++i;
        ++j;
      }
    }
    return resultLength;
  }

  static int compactDoubleByteCodePointRanges(final int[] doubleByteCodePointRanges, final int length) {

    unsignedIntegerBubbleSort(doubleByteCodePointRanges, 0, length);
    int resultLength = length;
    int previousInclusiveFrom;
    int previousInclusiveTo;
    int currentInclusiveFrom;
    int currentInclusiveTo;
    int i = 0;
    int j = 1;
    while (j < resultLength) {
      previousInclusiveFrom = getInclusiveFrom(doubleByteCodePointRanges[i]);
      previousInclusiveTo = getInclusiveTo(doubleByteCodePointRanges[i]);
      currentInclusiveFrom = getInclusiveFrom(doubleByteCodePointRanges[j]);
      currentInclusiveTo = getInclusiveTo(doubleByteCodePointRanges[j]);
      if (previousInclusiveTo >= currentInclusiveFrom) {
        if (previousInclusiveTo <= currentInclusiveTo) {
          doubleByteCodePointRanges[i] = rangeToInt(previousInclusiveFrom, currentInclusiveTo);
        }
        resultLength = removeDoubleByteElement(doubleByteCodePointRanges, resultLength, j);
      } else if ((previousInclusiveTo + 1) == currentInclusiveFrom) {
        doubleByteCodePointRanges[i] = rangeToInt(previousInclusiveFrom, currentInclusiveTo);
        resultLength = removeDoubleByteElement(doubleByteCodePointRanges, resultLength, j);
      } else {
        ++i;
        ++j;
      }
    }
    return resultLength;
  }

  static int compactTripleByteCodePointRanges(final long[] tripleByteCodePointRanges, final int length) {

    Arrays.sort(tripleByteCodePointRanges, 0, length);
    int resultLength = length;
    int previousInclusiveFrom;
    int previousInclusiveTo;
    int currentInclusiveFrom;
    int currentInclusiveTo;
    int i = 0;
    int j = 1;
    while (j < resultLength) {
      previousInclusiveFrom = getInclusiveFrom(tripleByteCodePointRanges[i]);
      previousInclusiveTo = getInclusiveTo(tripleByteCodePointRanges[i]);
      currentInclusiveFrom = getInclusiveFrom(tripleByteCodePointRanges[j]);
      currentInclusiveTo = getInclusiveTo(tripleByteCodePointRanges[j]);
      if (previousInclusiveTo >= currentInclusiveFrom) {
        if (previousInclusiveTo <= currentInclusiveTo) {
          tripleByteCodePointRanges[i] = rangeToLong(previousInclusiveFrom, currentInclusiveTo);
        }
        resultLength = removeTripleByteElement(tripleByteCodePointRanges, resultLength, j);
      } else if ((previousInclusiveTo + 1) == currentInclusiveFrom) {
        tripleByteCodePointRanges[i] = rangeToLong(previousInclusiveFrom, currentInclusiveTo);
        resultLength = removeTripleByteElement(tripleByteCodePointRanges, resultLength, j);
      } else {
        ++i;
        ++j;
      }
    }
    return resultLength;
  }

  static int removeSingleByteElement(final char[] singleByteCodePointRanges, final int length, final int indexOfElementToRemove) {
    System.arraycopy(
        singleByteCodePointRanges,
        indexOfElementToRemove + 1,
        singleByteCodePointRanges,
        indexOfElementToRemove,
        singleByteCodePointRanges.length - indexOfElementToRemove - 1);
    return length - 1;
  }

  static int removeDoubleByteElement(final int[] doubleByteCodePointRanges, final int length, final int indexOfElementToRemove) {
    System.arraycopy(
        doubleByteCodePointRanges,
        indexOfElementToRemove + 1,
        doubleByteCodePointRanges,
        indexOfElementToRemove,
        doubleByteCodePointRanges.length - indexOfElementToRemove - 1);
    return length - 1;
  }

  static int removeTripleByteElement(final long[] tripleByteCodePointRanges, final int length, final int indexOfElementToRemove) {
    System.arraycopy(
        tripleByteCodePointRanges,
        indexOfElementToRemove + 1,
        tripleByteCodePointRanges,
        indexOfElementToRemove,
        tripleByteCodePointRanges.length - indexOfElementToRemove - 1);
    return length - 1;
  }

  /**
   * Count how many categories have been specified using category bit flags.
   *
   * @param categoryFlags the bit flags representing the Unicode character categories
   * @return the number of categories that have been specified using category bit flags.
   */
  static int numberOfUnicodeCategoriesFromCategoriesFlags(final long categoryFlags) {
    int numberOfUnicodeCategories = 0;
    // Count how many categories have been specified.
    long flags = categoryFlags;
    for (int i = 0; i < 64; ++i) {
      if ((flags & 0x01) == 0x01) {
        ++numberOfUnicodeCategories;
      }
      flags = flags >> 1;
    }
    return numberOfUnicodeCategories;
  }

  static String toString(
      final Iterable<CodePointRange> ranges,
      final Iterable<Category> categories,
      final Iterable<String> strings) {
    return toPattern(ranges, categories, strings, false);
  }

  static String toPattern(
      final Iterable<CodePointRange> ranges,
      final Iterable<Category> categories,
      final Iterable<String> strings) {
    return toPattern(ranges, categories, strings, true);
  }

  private static String toPattern(
      final Iterable<CodePointRange> ranges,
      final Iterable<Category> categories,
      final Iterable<String> strings,
      final boolean targetJavaPattern) {

    final var rangesIterator = Objects.requireNonNullElse(ranges, Constants.EMPTY_CODE_POINT_RANGE_ITERABLE).iterator();
    final var categoriesIterator = Objects.requireNonNullElse(categories, Constants.EMPTY_CATEGORY_ITERABLE).iterator();
    final var stringsIterator = Objects.requireNonNullElse(strings, Constants.EMPTY_STRING_ITERABLE).iterator();

    if (!rangesIterator.hasNext() && !categoriesIterator.hasNext() && !stringsIterator.hasNext()) {
      return "";
    }

    final var s = new StringBuilder();
    if (stringsIterator.hasNext() && (rangesIterator.hasNext() || categoriesIterator.hasNext())) {
      s.append('(');
    }
    if (rangesIterator.hasNext() || categoriesIterator.hasNext()) {
      s.append('[');
      while (rangesIterator.hasNext()) {
        final var range = rangesIterator.next();
        switch (range.inclusiveTo - range.inclusiveFrom) {
          case 0:
            appendCodePoint(s, range.inclusiveFrom, targetJavaPattern);
            break;
          case 1:
            appendCodePoint(s, range.inclusiveFrom, targetJavaPattern);
            appendCodePoint(s, range.inclusiveTo, targetJavaPattern);
            break;
          default:
            appendCodePoint(s, range.inclusiveFrom, targetJavaPattern);
            s.append('-');
            appendCodePoint(s, range.inclusiveTo, targetJavaPattern);
            break;
        }
      }
      while (categoriesIterator.hasNext()) {
        final var category = categoriesIterator.next();
        s.append("\\p{").append(category.getAbbreviation()).append('}');
      }
      s.append(']');
    }
    if (stringsIterator.hasNext()) {
      if (!s.isEmpty()) {
        s.append('|');
      }
      while (stringsIterator.hasNext()) {
        s.append(stringsIterator.next()).append('|');
      }
      if (s.charAt(s.length() - 1) == '|') {
        s.setLength(s.length() - 1);
      }
    }
    if (s.charAt(0) == '(') {
      s.append(')');
    }
    return s.toString();
  }

  private static void appendCodePoint(final StringBuilder sb, final int cp, final boolean targetJavaPattern) {
    if (targetJavaPattern) {
      appendCodePointForToPattern(sb, cp);
    } else {
      appendCodePointForToString(sb, cp);
    }
  }

  private static void appendCodePointForToPattern(final StringBuilder sb, final int cp) {
    if (codePointIsInOneOfTheCategories(cp, SPACE_CONTROL_AND_FORMAT_CATEGORY_BIT_FLAGS)
        || cp > Character.MAX_CODE_POINT
        || codePointIsInOneOfTheCategories(cp, MODIFIER_AND_MARK_CATEGORY_BIT_FLAGS)
        || isRightToLeft(cp)) {
      appendEscapedCodePoint(sb, cp);
    } else {
      if (codepointIsRegexMetaCharacter(cp)) {
        sb.append('\\');
      }
      sb.appendCodePoint(cp);
    }
  }

  private static boolean codepointIsRegexMetaCharacter(final int cp) {
    return switch(cp) {
      case '\\', '^', '$', '.', '|', '?', '*', '+', '(', ')', '[', ']', '{', '}' -> true;
      default -> false;
    };
  }

  private static void appendCodePointForToString(final StringBuilder sb, final int cp) {
    if (codePointIsInOneOfTheCategories(cp, SPACE_CONTROL_AND_FORMAT_CATEGORY_BIT_FLAGS) || cp > Character.MAX_CODE_POINT) {
      if (!sb.isEmpty() && isRightToLeft(sb.codePointBefore(sb.length()))) {
        sb.append('\u200E'); // Left-to-right mark (U+200E) to prevent right-to-left text from affecting the display of the escaped code point
      }
      appendEscapedCodePoint(sb, cp);
    } else if (codePointIsInOneOfTheCategories(cp, MODIFIER_AND_MARK_CATEGORY_BIT_FLAGS)) {
      final Character.UnicodeScript script = Character.UnicodeScript.of(cp);
      if (COMPLEX_FONT_SHAPED_SCRIPTS.contains(script) || cp == 'ـ') { // Arabic Tatweel ـ (U+0640) is a modifier that does not need a Dotted Circle ◌ (U+25CC) to break combining, but it is a complex script
        // For complex scripts, if the preceding character is neither a hyphen nor an open-square-bracket,
        // we prepend a NBSP to break combining. The font-shaping engines will auto-inject
        // the Dotted Circle ◌ (U+25CC).
        var previousChar = sb.isEmpty() ? 0 : sb.charAt(sb.length() - 1);
        if (previousChar == '-' || previousChar == '[') {
          sb.appendCodePoint(cp);
        } else {
          sb.append(NBSP).appendCodePoint(cp);
        }
      } else {
        // For simple scripts (for example, Latin, Greek, Cyrillic, Common), we explicitly prepend
        // the Dotted Circle ◌ (U+25CC) so the modifier or mark doesn't attach to the preceding character
        sb.append(DOTTED_CIRCLE).appendCodePoint(cp);
      }
    } else {
      sb.appendCodePoint(cp);
    }
  }


  private static void appendEscapedCodePoint(final StringBuilder sb, final int cp) {
    if (cp < 0x10) {
      sb.append("\\u000").append(Integer.toString(cp, 16));
    } else if (cp < 0x100) {
      sb.append("\\u00").append(Integer.toString(cp, 16));
    } else if (cp < 0x1000) {
      sb.append("\\u0").append(Integer.toString(cp, 16));
    } else if (cp < 0x10000) {
      sb.append("\\u").append(Integer.toString(cp, 16));
    } else {
      sb.append("\\x{").append(Integer.toString(cp, 16)).append('}');
    }
  }

  private static boolean isRightToLeft(final int codePoint) {
    byte directionality = Character.getDirectionality(codePoint);

    return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT ||
           directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC ||
           directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING ||
           directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE ||
           directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ISOLATE;
  }


}
