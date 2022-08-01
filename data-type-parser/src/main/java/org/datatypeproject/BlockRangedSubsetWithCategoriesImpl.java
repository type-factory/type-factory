package org.datatypeproject;

class BlockRangedSubsetWithCategoriesImpl extends BlockRangedSubsetImpl {

  /**
   * Each bit of the following value corresponds to a {@link Category} identified by the {@link Category#bitMask};
   */
  private long unicodeCategoryBitFlags;

  BlockRangedSubsetWithCategoriesImpl(
      final long includeUnicodeCategoryBitFlags,
      final int[] blocks,
      final char[][] singleByteCodePointRangeByBlock) {
    this("", "",
        includeUnicodeCategoryBitFlags,
        blocks, singleByteCodePointRangeByBlock);
  }

  BlockRangedSubsetWithCategoriesImpl(
      final String name,
      final String alias,
      final long unicodeCategoryBitFlags,
      final int[] blocks,
      final char[][] singleByteCodePointRangeByBlock) {
    super(name, alias, blocks, singleByteCodePointRangeByBlock);
    this.unicodeCategoryBitFlags = unicodeCategoryBitFlags;
  }

  @Override
  public boolean contains(final int codePoint) {
    return super.contains(codePoint)
        || (unicodeCategoryBitFlags > 0L
        && (unicodeCategoryBitFlags & (0x1L << Character.getType(codePoint))) > 0L);
  }
}
