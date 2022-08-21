package org.datatypeproject.impl;

import org.datatypeproject.Category;

class OptimalHashedRangedSubsetWithCategoriesImpl extends OptimalHashedRangedSubsetImpl {

  /**
   * Each bit of the following value corresponds to a {@link Category} identified by the {@link Category#bitMask};
   */
  private final long unicodeCategoryBitFlags;

  private final int categoriesSize;

  OptimalHashedRangedSubsetWithCategoriesImpl(
      final long unicodeCategoryBitFlags,
      final char[] blocks,
      final char[][] codePointRangesByBlock,
      final int rangesSize,
      final int codePointsSize,
      final int categoriesSize) {
    super(blocks, codePointRangesByBlock, rangesSize, codePointsSize);
    this.unicodeCategoryBitFlags = unicodeCategoryBitFlags;
    this.categoriesSize = categoriesSize;
  }

  @Override
  public boolean isEmpty() {
    return categoriesSize == 0 && super.isEmpty();
  }

  @Override
  public boolean contains(final int codePoint) {
    return super.contains(codePoint)
        || (unicodeCategoryBitFlags > 0L
        && (unicodeCategoryBitFlags & (0x1L << Character.getType(codePoint))) > 0L);
  }
}
