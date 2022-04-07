package land.artli.easytype;

class RangedSubsetWithCategoriesImpl extends RangedSubsetImpl {

  /**
   * Each bit of the following value corresponds to a {@link Category} identified by the {@link Category#bitMask};
   */
  private long unicodeCategoryBitFlags;

  RangedSubsetWithCategoriesImpl(
      final long includeUnicodeCategoryBitFlags,
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges) {
    this("", "", includeUnicodeCategoryBitFlags,
        singleByteCodePointRanges, doubleByteCodePointRanges, tripleByteCodePointRanges);
  }

  RangedSubsetWithCategoriesImpl(
      final String name,
      final String alias,
      final long unicodeCategoryBitFlags,
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges) {
    super(name, alias, singleByteCodePointRanges, doubleByteCodePointRanges, tripleByteCodePointRanges);
    this.unicodeCategoryBitFlags = unicodeCategoryBitFlags;
  }

  public boolean contains(final int codePoint) {
    return super.contains(codePoint)
        || (unicodeCategoryBitFlags > 0L
        && (unicodeCategoryBitFlags & (0x1L << Character.getType(codePoint))) > 0L);
  }
}
