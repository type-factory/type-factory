package org.datatypeproject;

import static org.datatypeproject.Constants.BYTE_MASK;
import static org.datatypeproject.Constants.EMPTY_INT_ARRAY;
import static org.datatypeproject.Constants.EMPTY_LONG_ARRAY;

interface HashedRangedSubset extends Subset {

  /**
   * Creates a {@link HashedRangedSubsetBuilder} to create {@link HashedRangedSubset} instances. This method shadows/hides the
   * {@link Subset#builder()} method.
   *
   * <p>Package-scoped because we should only be invoking this as an implementation detail.</p>
   *
   * @return A {@link HashedRangedSubsetBuilder} to create {@link HashedRangedSubset} instances.
   */
  static HashedRangedSubsetBuilder builder() {
    return new HashedRangedSubsetBuilderImpl();
  }

  int[] getBlockKeySet();

  int[][] getBlockKeys();

  char[][][] getSingleByteCodePointRangesByBlock();

  @Override
  default boolean isEmpty() {
    return getBlockKeys() == null || getBlockKeys().length == 0;
  }

  @Override
  default boolean contains(final int codePoint) {
    final int blockKey = codePoint >> 8;
    final int hashIndex = (blockKey & 0x7FFFFFFF) % getBlockKeys().length;
    final int[] availableBlockKeys = getBlockKeys()[hashIndex];
    if (availableBlockKeys == null || availableBlockKeys.length == 0) {
      return false;
    }
    int hashBucketIndex = 0;
    while (hashBucketIndex < availableBlockKeys.length && blockKey != availableBlockKeys[hashBucketIndex]) {
      ++hashBucketIndex;
    }
    if (hashBucketIndex == availableBlockKeys.length) {
      return false;
    }
    final char[] singleByteCodePointRanges = getSingleByteCodePointRangesByBlock()[hashIndex][hashBucketIndex];
    return RangedSubsetUtils.contains(
        codePoint | BYTE_MASK,
        singleByteCodePointRanges,
        EMPTY_INT_ARRAY,
        EMPTY_LONG_ARRAY);
  }
}
