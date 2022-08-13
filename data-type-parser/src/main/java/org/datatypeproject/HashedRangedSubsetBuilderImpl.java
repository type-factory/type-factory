package org.datatypeproject;

import static org.datatypeproject.Constants.BYTE_MASK;
import static org.datatypeproject.RangedSubsetUtils.categoriesSizeFromCategoriesFlags;
import static org.datatypeproject.RangedSubsetUtils.compactSingleByteCodePointRanges;
import static org.datatypeproject.RangedSubsetUtils.getInclusiveFrom;
import static org.datatypeproject.RangedSubsetUtils.getInclusiveTo;

import java.util.Arrays;
import java.util.Collection;

public class HashedRangedSubsetBuilderImpl implements HashedRangedSubsetBuilder {

  final HashedBlockRanges includes = new HashedBlockRanges();

  final HashedBlockRanges excludes = new HashedBlockRanges();

  /**
   * Each bit of the following value corresponds to a {@link Category} identified by the {@link Category#bitMask};
   */
  protected long includeUnicodeCategoryBitFlags;

  /**
   * Each bit of the following value corresponds to a {@link Category} identified by the {@link Category#bitMask};
   */
  protected long excludeUnicodeCategoryBitFlags;

  public HashedRangedSubsetBuilder includeUnicodeCategory(final Category category) {
    includeUnicodeCategoryBitFlags |= category.bitMask;
    return this;
  }

  public HashedRangedSubsetBuilder includeUnicodeCategories(final Category... categories) {
    if (categories != null) {
      for (Category category : categories) {
        includeUnicodeCategory(category);
      }
    }
    return this;
  }

  public HashedRangedSubsetBuilder excludeUnicodeCategory(final Category category) {
    excludeUnicodeCategoryBitFlags |= category.bitMask;
    return this;
  }

  public HashedRangedSubsetBuilder excludeUnicodeCategory(final Category... categories) {
    if (categories != null) {
      for (Category category : categories) {
        excludeUnicodeCategory(category);
      }
    }
    return this;
  }

  @Override
  public HashedRangedSubsetBuilder includeChar(char ch) {
    includes.addChar(ch);
    return this;
  }

  @Override
  public HashedRangedSubsetBuilder includeChars(char... chars) {
    includes.addChars(chars);
    return this;
  }

  @Override
  public HashedRangedSubsetBuilder includeCharRange(char inclusiveFrom, char inclusiveTo) {
    includes.addCharRange(inclusiveFrom, inclusiveTo);
    return this;
  }

  @Override
  public HashedRangedSubsetBuilder includeCodePoint(int codePoint) {
    includes.addCodePoint(codePoint);
    return this;
  }

  @Override
  public HashedRangedSubsetBuilder includeCodePoints(int... codePoints) {
    includes.addCodePoints(codePoints);
    return this;
  }

  @Override
  public HashedRangedSubsetBuilder includeCodePointRange(int inclusiveFrom, int inclusiveTo) {
    includes.addCodePointRange(inclusiveFrom, inclusiveTo);
    return this;
  }

  @Override
  public HashedRangedSubsetBuilder includeSubset(Subset subsets) {
    includes.addSubset(subsets);
    return this;
  }

  @Override
  public HashedRangedSubsetBuilder includeSubsets(Subset... subsets) {
    includes.addSubset(subsets);
    return this;
  }

  @Override
  public HashedRangedSubsetBuilder includeSubsets(Collection<Subset> subsets) {
    includes.addSubset(subsets);
    return this;
  }

  @Override
  public HashedRangedSubsetBuilder excludeChar(char ch) {
    excludes.addChar(ch);
    return this;
  }

  @Override
  public HashedRangedSubsetBuilder excludeChars(char... chars) {
    excludes.addChars(chars);
    return this;
  }

  @Override
  public HashedRangedSubsetBuilder excludeCharRange(char inclusiveFrom, char inclusiveTo) {
    excludes.addCharRange(inclusiveFrom, inclusiveTo);
    return this;
  }

  @Override
  public HashedRangedSubsetBuilder excludeCodePoint(int codePoint) {
    excludes.addCodePoint(codePoint);
    return this;
  }

  @Override
  public HashedRangedSubsetBuilder excludeCodePoints(int... codePoints) {
    excludes.addCodePoints(codePoints);
    return this;
  }

  @Override
  public HashedRangedSubsetBuilder excludeCodePointRange(int inclusiveFrom, int inclusiveTo) {
    excludes.addCodePointRange(inclusiveFrom, inclusiveTo);
    return this;
  }

  @Override
  public HashedRangedSubsetBuilder excludeSubset(Subset subsets) {
    excludes.addSubset(subsets);
    return this;
  }

  @Override
  public HashedRangedSubsetBuilder excludeSubsets(Subset... subsets) {
    excludes.addSubset(subsets);
    return this;
  }

  @Override
  public HashedRangedSubsetBuilder excludeSubsets(Collection<Subset> subsets) {
    excludes.addSubset(subsets);
    return this;
  }

  private boolean containsUnicodeCategories() {
    return includeUnicodeCategoryBitFlags > 0;
  }

  private boolean containsExcludes() {
    return excludes.isNotEmpty() || excludeUnicodeCategoryBitFlags > 0;
  }

  private void compactCategories() {
    // remove excluded categories
    includeUnicodeCategoryBitFlags &= ~excludeUnicodeCategoryBitFlags;
  }

  @Override
  public HashedRangedSubset build() {
    excludes.compact();
    includes.removeCodePointRanges(excludes);
    includes.removeCodepointCategories(excludeUnicodeCategoryBitFlags);
    includes.compact();
    compactCategories();

    if (containsUnicodeCategories()) {
      return new HashedRangedSubsetWithCategoriesImpl(
          includeUnicodeCategoryBitFlags,
          includes.blockKeys,
          includes.codePointRangesByBlock,
          includes.rangesSize,
          includes.codePointsSize,
          categoriesSizeFromCategoriesFlags(includeUnicodeCategoryBitFlags));
    }

    return new HashedRangedSubsetImpl(
        includes.blockKeys,
        includes.codePointRangesByBlock,
        includes.rangesSize,
        includes.codePointsSize);
  }

  static class HashedBlockRanges {

    final static int INITIAL_BUCKET_CAPACITY = 4;

    final static int INITIAL_RANGE_CAPACITY = 32;

    final static int INITIAL_KEY_SET_CAPACITY = 32;

    /**
     * <p>The set of unique sorted block-keys in ascending order. This enables iteration of the
     * code-point ranges in order from lowest to highest.</p>
     */
    private int[] blockKeySet = new int[INITIAL_KEY_SET_CAPACITY];

    /**
     * <p>The hashed block-keys. A block-key is the two most significant bytes of a three-byte code-point.</p>
     *
     * <p>In the code of this class the two-dimensional indexes will be referred to as:</p>
     * <pre>
     *       ┌──── hashIndex       - an index to the hash-bucket
     *       │  ┌─ hashBucketIndex - an index to the key within the hash-bucket
     *       │  │
     *   int[ ][ ] blockKeys
     * </pre>
     */
    private char[][] blockKeys = new char[INITIAL_BUCKET_CAPACITY][];

    /**
     * <p>The hashed single-byte code-point ranges. The ranges are actually the least significant bytes of the
     * inclusive-from and inclusive-to code-points combined into a two-byte char.</p>
     *
     * <p>In the code of this class the three-dimensional indexes will be referred to as:</p>
     * <pre>
     *        ┌─────── hashIndex           - an index to the hash-bucket
     *        │  ┌──── hashBucketIndex     - an index to the key within the hash-bucket
     *        │  │  ┌─ codePointRangeIndex - an index to the range within the array of ranges
     *        │  │  │
     *   char[ ][ ][ ] codePointRangesByBlock
     * </pre>
     */
    private char[][][] codePointRangesByBlock = new char[INITIAL_BUCKET_CAPACITY][][];

    /**
     * <p>The current size (count) of block-keys in the blockKeySet.</p>
     */
    private int blockKeySetSize = 0;

    /**
     * <p>The current size (count) of block-keys in the hash-bucket.</p>
     *
     * <p>In the code of this class the index will be referred to as:</p>
     * <pre>
     *       ┌─────── hashIndex - an index to the hash-bucket
     *       │
     *   int[ ] hashBucketSizes
     * </pre>
     */
    private int[] hashBucketSizes = new int[INITIAL_BUCKET_CAPACITY];

    /**
     * <p>The current size (count) of single-byte code-point ranges within each array of ranges.</p>
     *
     * <p>In the code of this class the two-dimensional indexes will be referred to as:</p>
     * <pre>
     *        ┌─────── hashIndex     - an index to the hash-bucket
     *        │  ┌──── hashBucketIndex - an index to the key within the hash-bucket
     *        │  │
     *   char[ ][ ][ ] codePointRangeSizes
     * </pre>
     */
    private int[][] codePointRangesSizes = new int[INITIAL_BUCKET_CAPACITY][];

    private int rangesSize = 0;

    private int codePointsSize = 0;

    boolean isEmpty() {
      return blockKeySetSize == 0;
    }

    boolean isNotEmpty() {
      return !isEmpty();
    }

    void addChar(final char ch) {
      addCharRange(ch, ch);
    }

    void addChars(final char... chars) {
      if (chars != null) {
        for (int i = 0; i < chars.length; ++i) {
          addChar(chars[i]);
        }
      }
    }

    void addCharRange(final char inclusiveFrom, final char inclusiveTo) {
      addCodePointRange(inclusiveFrom & 0x0000_ffff, inclusiveTo & 0x0000_ffff);
    }

    void addCodePoint(final int codePoint) {
      addCodePointRange(codePoint, codePoint);
    }

    void addCodePoints(final int... codePoints) {
      if (codePoints != null) {
        for (int i = 0; i < codePoints.length; ++i) {
          addCodePoint(codePoints[i]);
        }
      }
    }

    void addCodePointRange(int inclusiveFrom, int inclusiveTo) {

      if (inclusiveFrom > inclusiveTo) {
        final int temp = inclusiveFrom;
        inclusiveFrom = inclusiveTo;
        inclusiveTo = temp;
      }

      final char blockKeyFrom = (char)((inclusiveFrom >> 8) & 0xFF);
      final char blockKeyTo = (char)((inclusiveTo >> 8) & 0xFF);
      for (char blockKey = blockKeyFrom; blockKey <= blockKeyTo; ++blockKey) {
        // Insert block key into sorted blockKeySet
        final int blockKeySetIndex = blockKeySetSize == 0 ? 0 : Arrays.binarySearch(blockKeySet, 0, blockKeySetSize, blockKey);
        if (blockKeySetSize == 0 || blockKeySet[blockKeySetIndex] != blockKey) {
          blockKeySetSize++;
          ensureBlockKeySetCapacity();
          System.arraycopy(
              blockKeySet, blockKeySetIndex,
              blockKeySet, blockKeySetIndex + 1,
              blockKeySet.length - blockKeySetIndex - 1);
          blockKeySet[blockKeySetIndex] = blockKey;
        }
        // Add code-point range into hash map
        final int hashIndex = (blockKey & 0x7FFFFFFF) % blockKeys.length;
        final int from = blockKey == blockKeyFrom ? inclusiveFrom & BYTE_MASK : 0x00;
        final int to = blockKey == blockKeyTo ? inclusiveTo & BYTE_MASK : 0xFF;
        final char codePointRange = RangedSubsetUtils.rangeToChar(from, to);
        final char[] hashBucket = blockKeys[hashIndex];
        int hashBucketIndex = 0;
        while (hashBucketIndex < hashBucketSizes[hashIndex]
            && hashBucketIndex < hashBucket.length
            && hashBucket[hashBucketIndex] != blockKey) {
          ++hashBucketIndex;
        }
        if (hashBucketIndex == hashBucketSizes[hashIndex]) {
          hashBucketSizes[hashIndex]++;
        }
        ensureHashBucketCapacity(hashIndex);
        ensureCodePointRangesCapacity(hashIndex, hashBucketIndex);
        final int codePointRangeIndex = codePointRangesSizes[hashIndex][hashBucketIndex];
        blockKeys[hashIndex][hashBucketIndex] = blockKey;
        codePointRangesByBlock[hashIndex][hashBucketIndex][codePointRangeIndex] = codePointRange;
        codePointRangesSizes[hashIndex][hashBucketIndex]++;
      }
    }

    private void ensureBlockKeySetCapacity() {
      if (blockKeySet.length == blockKeySetSize) {
        blockKeySet = Arrays.copyOf(blockKeySet, blockKeySet.length + INITIAL_KEY_SET_CAPACITY);
      }
    }

    private void ensureHashBucketCapacity(final int hashIndex) {
      if (blockKeys[hashIndex] == null) {
        blockKeys[hashIndex] = new char[INITIAL_BUCKET_CAPACITY];
        codePointRangesByBlock[hashIndex] = new char[INITIAL_BUCKET_CAPACITY][];
        codePointRangesSizes[hashIndex] = new int[INITIAL_BUCKET_CAPACITY];
      } else if (blockKeys[hashIndex].length == hashBucketSizes[hashIndex]) {
        blockKeys[hashIndex] = Arrays.copyOf(
            blockKeys[hashIndex],
            blockKeys[hashIndex].length + INITIAL_BUCKET_CAPACITY);
        codePointRangesByBlock[hashIndex] = Arrays.copyOf(
            codePointRangesByBlock[hashIndex],
            codePointRangesByBlock[hashIndex].length + INITIAL_BUCKET_CAPACITY);
        codePointRangesSizes[hashIndex] = Arrays.copyOf(
            codePointRangesSizes[hashIndex],
            codePointRangesSizes[hashIndex].length + INITIAL_BUCKET_CAPACITY);
      }
    }

    private void ensureCodePointRangesCapacity(final int hashIndex, final int hashBucketIndex) {
      if (codePointRangesByBlock[hashIndex][hashBucketIndex] == null) {
        codePointRangesByBlock[hashIndex][hashBucketIndex] = new char[INITIAL_RANGE_CAPACITY];
      } else if (codePointRangesByBlock[hashIndex][hashBucketIndex].length == codePointRangesSizes[hashIndex][hashBucketIndex]) {
        codePointRangesByBlock[hashIndex][hashBucketIndex] = Arrays.copyOf(
            codePointRangesByBlock[hashIndex][hashBucketIndex],
            codePointRangesByBlock[hashIndex][hashBucketIndex].length + INITIAL_RANGE_CAPACITY);
      }
    }

    void removeCodePointRanges(final HashedBlockRanges hashedBlockRanges) {
      for (int hashIndex = 0; hashIndex < hashedBlockRanges.blockKeys.length; ++hashIndex) {
        final char[] hashBucket = hashedBlockRanges.blockKeys[hashIndex];
        if (hashBucket != null) {
          for (int hashBucketIndex = 0; hashBucketIndex < hashBucket.length; ++hashBucketIndex) {
            final int block = hashBucket[hashBucketIndex] << 8;
            final char[] codePointRanges = hashedBlockRanges.codePointRangesByBlock[hashIndex][hashBucketIndex];
            if (codePointRanges != null) {
              for (int codePointRangeIndex = 0; codePointRangeIndex < codePointRanges.length; ++codePointRangeIndex) {
                final int inclusiveFrom = block | getInclusiveFrom(codePointRanges[codePointRangeIndex]);
                final int inclusiveTo = block | getInclusiveTo(codePointRanges[codePointRangeIndex]);
                removeCodePointRange(inclusiveFrom, inclusiveTo);
              }
            }
          }
        }
      }
    }

    void removeCodePoint(final int codePoint) {
      removeCodePointRange(codePoint, codePoint);
    }

    void removeCodePointRange(int removeInclusiveFrom, int removeInclusiveTo) {

      if (removeInclusiveFrom > removeInclusiveTo) {
        final int temp = removeInclusiveFrom;
        removeInclusiveFrom = removeInclusiveTo;
        removeInclusiveTo = temp;
      }

      final int blockKeyFrom = removeInclusiveFrom >> 8;
      final int blockKeyTo = removeInclusiveTo >> 8;
      for (int blockKey = blockKeyFrom; blockKey <= blockKeyTo; ++blockKey) {
        final int hashIndex = (blockKey & 0x7FFFFFFF) % blockKeys.length;
        final char[] hashBucket = blockKeys[hashIndex];
        int hashBucketIndex = 0;
        while (hashBucketIndex < hashBucketSizes[hashIndex]
            && hashBucketIndex < hashBucket.length
            && hashBucket[hashBucketIndex] != blockKey) {
          ++hashBucketIndex;
        }
        if (hashBucket[hashBucketIndex] == blockKey) {
          final int removeFrom = blockKey == blockKeyFrom ? removeInclusiveFrom & BYTE_MASK : 0x00;
          final int removeTo = blockKey == blockKeyTo ? removeInclusiveTo & BYTE_MASK : 0xFF;
          final char[] codePointRanges = codePointRangesByBlock[hashIndex][hashBucketIndex];
          if (codePointRanges != null) {
            final int codePointRangesSize = codePointRangesSizes[hashIndex][hashBucketIndex];
            for (int codePointRangesIndex = codePointRangesSize - 1; codePointRangesIndex >= 0; --codePointRangesIndex) {
              final char range = codePointRanges[codePointRangesIndex];
              final int inclusiveFrom = getInclusiveFrom(range);
              final int inclusiveTo = getInclusiveTo(range);
              if (removeFrom <= inclusiveTo && removeTo >= inclusiveTo) {
                removeArrayElement(codePointRanges, codePointRangesIndex);
                codePointRangesSizes[hashIndex][hashBucketIndex]--;
              } else if (inclusiveFrom < removeFrom && inclusiveTo > removeTo) {
                codePointRanges[codePointRangesIndex] = RangedSubsetUtils.rangeToChar(inclusiveFrom, removeFrom - 1);
                addCodePointRange(removeTo + 1, inclusiveTo);
              } else if (removeTo >= inclusiveFrom) {
                codePointRanges[codePointRangesIndex] = RangedSubsetUtils.rangeToChar(removeTo + 1, inclusiveTo);
              } else if (removeFrom <= inclusiveTo) {
                codePointRanges[codePointRangesIndex] = RangedSubsetUtils.rangeToChar(inclusiveTo, removeFrom - 1);
              }
            }
          }
        }
      }
    }

    void removeCodepointCategories(final long unicodeCategoryBitFlags) {
      if (unicodeCategoryBitFlags == 0L) {
        return;
      }
      int[] codepointsToRemove = new int[256];
      int codepointsToRemoveIndex = 0;

      for (int hashIndex = 0; hashIndex < blockKeys.length; ++hashIndex) {
        final char[] hashBucket = blockKeys[hashIndex];
        if (hashBucket != null) {
          for (int hashBucketIndex = 0; hashBucketIndex < hashBucket.length; ++hashBucketIndex) {
            final int block = hashBucket[hashBucketIndex] << 8;
            final char[] codePointRanges = codePointRangesByBlock[hashIndex][hashBucketIndex];
            if (codePointRanges != null) {
              for (int codePointRangeIndex = 0; codePointRangeIndex < codePointRanges.length; ++codePointRangeIndex) {
                final int inclusiveFrom = block | getInclusiveFrom(codePointRanges[codePointRangeIndex]);
                final int inclusiveTo = block | getInclusiveTo(codePointRanges[codePointRangeIndex]);
                for (int codePoint = inclusiveFrom; codePoint <= inclusiveTo; ++codePoint) {
                  if ((unicodeCategoryBitFlags & (0x1L << Character.getType(codePoint))) > 0L) {
                    if (codepointsToRemoveIndex == codepointsToRemove.length) {
                      codepointsToRemove = Arrays.copyOf(codepointsToRemove, codepointsToRemove.length + 256);
                    }
                    codepointsToRemove[codepointsToRemoveIndex++] = codePoint;
                  }
                }
              }
            }
          }
        }
      }

      for (int i = 0; i < codepointsToRemoveIndex; ++i) {
        removeCodePoint(codepointsToRemove[i]);
      }
    }

    @SuppressWarnings("java:S3776")
    void addSubset(final Subset... subsets) {
      if (subsets != null) {
        for (Subset subset : subsets) {
          addSubset(subset);
        }
      }
    }

    void addSubset(final Collection<Subset> subsets) {
      if (subsets != null) {
        for (Subset subset : subsets) {
          addSubset(subset);
        }
      }
    }

    void addSubset(final Subset subset) {
      if (subset != null && subset.isNotEmpty()) {
        if (subset instanceof HashedRangedSubset hashedRangedSubset) { // TODO this should BlockedSubset
          // More efficiently adds the ranges
          addSubset(hashedRangedSubset);
        } else if (subset instanceof RangedSubset rangedSubset) {
          // More efficiently adds the ranges
          addSubset(rangedSubset);
        } else {
          for (CodePointRange range : subset.ranges()) {
            addCodePointRange(range.getInclusiveFrom(), range.getInclusiveTo());
          }
        }
      }
    }

    void addSubset(final RangedSubset subset) {
      if (subset == null || subset.isEmpty()) {
        return;
      }
      for (char c : subset.getSingleByteCodePointRanges()) {
        addCodePointRange(
            getInclusiveFrom(c),
            getInclusiveTo(c));
      }
      for (int j : subset.getDoubleByteCodePointRanges()) {
        addCodePointRange(
            getInclusiveFrom(j),
            getInclusiveTo(j));
      }
      for (long l : subset.getTripleByteCodePointRanges()) {
        addCodePointRange(
            getInclusiveFrom(l),
            getInclusiveTo(l));
      }
    }

    @SuppressWarnings({"java:S3776", "java:S1199"})
    void compact() {
      blockKeySet = Arrays.copyOf(blockKeySet, blockKeySetSize);
      for (int hashIndex = 0; hashIndex < blockKeys.length; ++hashIndex) {
        final char[] hashBucket = blockKeys[hashIndex];
        if (hashBucket != null) {
          final int hashBucketSize = hashBucketSizes[hashIndex];
          blockKeys[hashIndex] = Arrays.copyOf(blockKeys[hashIndex], hashBucketSize);
          codePointRangesByBlock[hashIndex] = Arrays.copyOf(codePointRangesByBlock[hashIndex], hashBucketSize);
          codePointRangesSizes[hashIndex] = Arrays.copyOf(codePointRangesSizes[hashIndex], hashBucketSize);
          for (int hashBucketIndex = 0; hashBucketIndex < hashBucketSize; ++hashBucketIndex) {
            // compact the code point ranges
            codePointRangesSizes[hashIndex][hashBucketIndex] = compactSingleByteCodePointRanges(
                codePointRangesByBlock[hashIndex][hashBucketIndex],
                codePointRangesSizes[hashIndex][hashBucketIndex]);
            // make the array the exact size
            codePointRangesByBlock[hashIndex][hashBucketIndex] = Arrays.copyOf(
                codePointRangesByBlock[hashIndex][hashBucketIndex],
                codePointRangesSizes[hashIndex][hashBucketIndex]);
            rangesSize += codePointRangesSizes[hashIndex][hashBucketIndex];
            for (int codePointRangeIndex = 0; codePointRangeIndex < codePointRangesByBlock[hashIndex][hashBucketIndex].length; ++codePointRangeIndex) {
              final char codePointRange = codePointRangesByBlock[hashIndex][hashBucketIndex][codePointRangeIndex];
              codePointsSize += getInclusiveTo(codePointRange) - getInclusiveFrom(codePointRange) + 1;
            }
          }
        }
      }
    }

    private void removeArrayElement(final char[] array, final int index) {
      System.arraycopy(array, index + 1, array, index, array.length - index - 1);
    }

  }


}
