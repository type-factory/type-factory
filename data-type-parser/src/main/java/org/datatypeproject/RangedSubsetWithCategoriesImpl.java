package org.datatypeproject;

class RangedSubsetWithCategoriesImpl extends RangedSubsetImpl {

  /**
   * Each bit of the following value corresponds to a {@link Category} identified by the {@link Category#bitMask};
   */
  private final long unicodeCategoryBitFlags;

  private final int categoriesSize;

  RangedSubsetWithCategoriesImpl(
      final long includeUnicodeCategoryBitFlags,
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges,
      final int rangesSize,
      final int codePointsSize,
      final int categoriesSize) {
    this("", "",
        includeUnicodeCategoryBitFlags,
        singleByteCodePointRanges, doubleByteCodePointRanges, tripleByteCodePointRanges,
        rangesSize, codePointsSize, categoriesSize);
  }

  RangedSubsetWithCategoriesImpl(
      final String name,
      final String alias,
      final long unicodeCategoryBitFlags,
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges,
      final int rangesSize,
      final int codePointsSize,
      final int categoriesSize) {
    super(name, alias, singleByteCodePointRanges, doubleByteCodePointRanges, tripleByteCodePointRanges,
        rangesSize, codePointsSize);
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
