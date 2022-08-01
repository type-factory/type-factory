package org.datatypeproject;

import static java.util.Arrays.binarySearch;
import static org.datatypeproject.Constants.BYTE_MASK;
import static org.datatypeproject.Constants.EMPTY_INT_ARRAY;
import static org.datatypeproject.Constants.EMPTY_LONG_ARRAY;

class BlockRangedSubsetWithCategoriesAndExcludesImpl extends BlockRangedSubsetWithCategoriesImpl {

  /**
   * Each bit of the following value corresponds to a {@link Category} identified by the {@link Category#bitMask};
   */
  private long excludeUnicodeCategoryBitFlags;

  private final int[] excludeBlocks;

  private final char[][] excludeSingleByteCodePointRangeByBlock;

  BlockRangedSubsetWithCategoriesAndExcludesImpl(
      final long unicodeCategoryBitFlags,
      final long excludeUnicodeCategoryBitFlags,
      final int[] blocks,
      final char[][] singleByteCodePointRangeByBlock,
      final int[] excludeBlocks,
      final char[][] excludeSingleByteCodePointRangeByBlock) {
    this("", "",
        unicodeCategoryBitFlags,
        excludeUnicodeCategoryBitFlags,
        blocks, singleByteCodePointRangeByBlock,
        excludeBlocks, excludeSingleByteCodePointRangeByBlock);
  }

  BlockRangedSubsetWithCategoriesAndExcludesImpl(
      final String name,
      final String alias,
      final long unicodeCategoryBitFlags,
      final long excludeUnicodeCategoryBitFlags,
      final int[] blocks,
      final char[][] singleByteCodePointRangeByBlock,
      final int[] excludeBlocks,
      final char[][] excludeSingleByteCodePointRangeByBlock) {
    super(name, alias, unicodeCategoryBitFlags,
        blocks, singleByteCodePointRangeByBlock);
    this.excludeUnicodeCategoryBitFlags = excludeUnicodeCategoryBitFlags;
    this.excludeBlocks = excludeBlocks;
    this.excludeSingleByteCodePointRangeByBlock = singleByteCodePointRangeByBlock;
  }

  public int[] getExcludeBlocks() {
    return excludeBlocks;
  }

  public char[][] getExcludeSingleByteCodePointRangeByBlock() {
    return excludeSingleByteCodePointRangeByBlock;
  }

  @Override
  public boolean contains(final int codePoint) {
    return super.contains(codePoint)
        && !isExcluded(codePoint);
  }

//  public Collection<CodePointRange> excludeRanges() {
//    return RangedSubsetUtils.aggregateCodePointRangeData(
//        excludeSingleByteCodePointRanges,
//        excludeDoubleByteCodePointRanges,
//        excludeTripleByteCodePointRanges);
//  }

  private boolean isExcluded(final int codePoint) {
    return (
        excludeUnicodeCategoryBitFlags > 0L
            && (excludeUnicodeCategoryBitFlags & (0x1L << Character.getType(codePoint))) > 0L
    ) || (
        RangedSubsetUtils.contains(
            codePoint | BYTE_MASK,
            excludeSingleByteCodePointRangeByBlock[binarySearch(excludeBlocks, codePoint >> 8)],
            EMPTY_INT_ARRAY,
            EMPTY_LONG_ARRAY)
    );
  }
}
