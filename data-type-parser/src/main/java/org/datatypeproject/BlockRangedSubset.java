package org.datatypeproject;

import static org.datatypeproject.Constants.BYTE_MASK;
import static org.datatypeproject.Constants.EMPTY_INT_ARRAY;
import static org.datatypeproject.Constants.EMPTY_LONG_ARRAY;

import java.util.Arrays;

interface BlockRangedSubset extends Subset {

  /**
   * Creates a {@link BlockRangedSubsetBuilder} to create {@link BlockRangedSubset} instances. This method shadows/hides the {@link Subset#builder()}
   * method.
   *
   * <p>Package-scoped because we should only be invoking this as an implementation detail.</p>
   *
   * @return A {@link BlockRangedSubsetBuilder} to create {@link BlockRangedSubset} instances.
   */
  static BlockRangedSubsetBuilder builder() {
    return new BlockRangedSubsetBuilderImpl();
  }

  int[] getBlocks();

  char[][] getSingleByteCodePointRangeByBlock();

  @Override
  default boolean isEmpty() {
    return getBlocks() == null || getBlocks().length == 0;
  }

  @Override
  default boolean contains(final int codePoint) {
    final int blockKey = codePoint >> 8;
    final int index = Arrays.binarySearch(getBlocks(), blockKey);
    return RangedSubsetUtils.contains(
        codePoint | BYTE_MASK,
        getSingleByteCodePointRangeByBlock()[index],
        EMPTY_INT_ARRAY,
        EMPTY_LONG_ARRAY);
  }
}
