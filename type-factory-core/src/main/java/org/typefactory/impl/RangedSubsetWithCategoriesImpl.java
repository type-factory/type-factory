package org.typefactory.impl;

import org.typefactory.Category;
import org.typefactory.SubsetWithCategories;

class RangedSubsetWithCategoriesImpl extends RangedSubsetImpl implements SubsetWithCategories {

  /**
   * Each bit of the following value corresponds to a {@link Category} identified by the {@link Category#bitMask};
   */
  private final long unicodeCategoryBitFlags;

  private final int numberOfUnicodeCategories;

  RangedSubsetWithCategoriesImpl(
      final long unicodeCategoryBitFlags,
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges,
      final int numberOfUnicodeCategories) {
    super(singleByteCodePointRanges, doubleByteCodePointRanges, tripleByteCodePointRanges,
        numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);
    this.unicodeCategoryBitFlags = unicodeCategoryBitFlags;
    this.numberOfUnicodeCategories = numberOfUnicodeCategories;
  }

  @Override
  public boolean isEmpty() {
    return numberOfUnicodeCategories == 0 && super.isEmpty();
  }

  @Override
  public boolean contains(final int codePoint) {
    return super.contains(codePoint)
        || (unicodeCategoryBitFlags > 0L
        && (unicodeCategoryBitFlags & (0x1L << Character.getType(codePoint))) > 0L);
  }

  @Override
  public long unicodeCategoryBitFlags() {
    return unicodeCategoryBitFlags;
  }

  @Override
  public int numberOfUnicodeCategories() {
    return numberOfUnicodeCategories;
  }
}
