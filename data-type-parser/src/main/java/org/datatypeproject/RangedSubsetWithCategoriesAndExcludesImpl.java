package org.datatypeproject;

import static org.datatypeproject.Constants.EMPTY_CHAR_ARRAY;
import static org.datatypeproject.Constants.EMPTY_INT_ARRAY;
import static org.datatypeproject.Constants.EMPTY_LONG_ARRAY;
import static org.datatypeproject.RangedSubsetUtils.defaultIfNullOrEmpty;

import java.util.Arrays;
import java.util.Collection;

class RangedSubsetWithCategoriesAndExcludesImpl extends RangedSubsetWithCategoriesImpl {

  /**
   * Each bit of the following value corresponds to a {@link Category} identified by the {@link Category#bitMask};
   */
  private long excludeUnicodeCategoryBitFlags;

  private final char[] excludeSingleByteCodePointRanges;
  private final int[] excludeDoubleByteCodePointRanges;
  private final long[] excludeTripleByteCodePointRanges;

  RangedSubsetWithCategoriesAndExcludesImpl(
      final long unicodeCategoryBitFlags,
      final long excludeUnicodeCategoryBitFlags,
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges,
      final char[] excludeSingleByteCodePointRanges,
      final int[] excludeDoubleByteCodePointRanges,
      final long[] excludeTripleByteCodePointRanges) {
    this("", "", unicodeCategoryBitFlags, excludeUnicodeCategoryBitFlags,
        singleByteCodePointRanges, doubleByteCodePointRanges, tripleByteCodePointRanges,
        excludeSingleByteCodePointRanges, excludeDoubleByteCodePointRanges, excludeTripleByteCodePointRanges);
  }

  RangedSubsetWithCategoriesAndExcludesImpl(
      final String name,
      final String alias,
      final long unicodeCategoryBitFlags,
      final long excludeUnicodeCategoryBitFlags,
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges,
      final char[] excludeSingleByteCodePointRanges,
      final int[] excludeDoubleByteCodePointRanges,
      final long[] excludeTripleByteCodePointRanges) {
    super(name, alias, unicodeCategoryBitFlags,
        singleByteCodePointRanges, doubleByteCodePointRanges, tripleByteCodePointRanges);
    this.excludeUnicodeCategoryBitFlags = excludeUnicodeCategoryBitFlags;
    this.excludeSingleByteCodePointRanges = defaultIfNullOrEmpty(excludeSingleByteCodePointRanges, EMPTY_CHAR_ARRAY);
    this.excludeDoubleByteCodePointRanges = defaultIfNullOrEmpty(excludeDoubleByteCodePointRanges, EMPTY_INT_ARRAY);
    this.excludeTripleByteCodePointRanges = defaultIfNullOrEmpty(excludeTripleByteCodePointRanges, EMPTY_LONG_ARRAY);
  }

  /**
   * <p>Returns an array of 1-byte excluded code-point ranges stored as char values. Each range comprises an inclusive-from value and an inclusive-to
   * value. The most-significant 8 bits hold the inclusive-from value. The least-significant 8 bits hold the inclusive-to value. The returned array
   * must be sorted from low-to-high.</p>
   *
   * <p>Excluded code-points are not considered part of the set. The {@link #contains(int)} will return false if the argument falls withing the set
   * of excluded code points.</p>
   *
   * @return an array of 1-byte excluded code-point ranges stored as char values.
   */
  public char[] getExcludeSingleByteCodePointRanges() {
    // Protect out data by returning a copy
    return Arrays.copyOf(excludeSingleByteCodePointRanges, excludeSingleByteCodePointRanges.length);
  }

  /**
   * <p>Returns an array of 2-byte excluded code-point ranges stored as integer values. Each range comprises an inclusive-from value and an
   * inclusive-to value. The most-significant 16 bits hold the inclusive-from value. The least-significant 16 bits hold the inclusive-to value. The
   * returned array must be sorted from low-to-high as unsigned-integers.</p>
   *
   * <p>Excluded code-points are not considered part of the set. The {@link #contains(int)} will return false if the argument falls withing the set
   * of excluded code points.</p>
   *
   * @return an array of 2-byte excluded code-point ranges stored as integer values.
   */
  public int[] getExcludeDoubleByteCodePointRanges() {
    // Protect out data by returning a copy
    return Arrays.copyOf(excludeDoubleByteCodePointRanges, excludeDoubleByteCodePointRanges.length);
  }

  /**
   * <p>Returns an array of 4-byte excluded code-point ranges stored as long values. Each range comprises an inclusive-from value and an inclusive-to
   * value. The most-significant 32 bits hold the inclusive-from value. The least-significant 32 bits hold the inclusive-to value. The returned array
   * must be sorted from low-to-high.</p>
   *
   * <p>Excluded code-points are not considered part of the set. The {@link #contains(int)} will return false if the argument falls withing the set
   * of excluded code points.</p>
   *
   * @return an array of 4-byte excluded code-point ranges stored as long values.
   */
  public long[] getExcludeTripleByteCodePointRanges() {
    // Protect out data by returning a copy
    return Arrays.copyOf(excludeTripleByteCodePointRanges, excludeTripleByteCodePointRanges.length);
  }

  @Override
  public boolean contains(final int codePoint) {
    return super.contains(codePoint)
        && !isExcluded(codePoint);
  }

  public Collection<CodePointRange> excludeRanges() {
    return RangedSubsetUtils.aggregateCodePointRangeData(
        excludeSingleByteCodePointRanges,
        excludeDoubleByteCodePointRanges,
        excludeTripleByteCodePointRanges);
  }

  private boolean isExcluded(final int codePoint) {
    return (
        excludeUnicodeCategoryBitFlags > 0L
            && (excludeUnicodeCategoryBitFlags & (0x1L << Character.getType(codePoint))) > 0L
    ) || (
        RangedSubsetUtils.contains(
            codePoint,
            excludeSingleByteCodePointRanges,
            excludeDoubleByteCodePointRanges,
            excludeTripleByteCodePointRanges)
    );
  }
}
