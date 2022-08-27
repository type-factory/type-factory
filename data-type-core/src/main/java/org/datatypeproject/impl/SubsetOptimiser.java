package org.datatypeproject.impl;

import java.util.Arrays;
import java.util.Comparator;
import org.datatypeproject.Subset;
import org.datatypeproject.Subset.CodePointRange;

class SubsetOptimiser {

  /**
   * <p>The set of unique sorted block-keys in ascending order. This enables iteration of the
   * code-point ranges in order from lowest to highest.</p>
   */
  private final BlockKeyStats blockKeyStats;
  private final int minHashBuckets;
  private final int maxHashBuckets;
  private final HashMapStats[] hashMapStats;
  private final HashMapStats optimalHashMapStats;
  private final int optimalNumberOfHashBuckets;

  private final Subset optimisedSubset;

  public SubsetOptimiser(final Iterable<CodePointRange> ranges) {
    blockKeyStats = getBlockKeysStats(ranges);
    minHashBuckets = Math.max(3, (int) Math.floor(blockKeyStats.blockKeys.length * 0.7D));
    maxHashBuckets = Math.max(7, (int) Math.ceil(blockKeyStats.blockKeys.length * 2.5D));
    hashMapStats = calculatePossibleHashMapStats(blockKeyStats.blockKeys, minHashBuckets, maxHashBuckets);
    optimalHashMapStats = hashMapStats[0];
    optimalNumberOfHashBuckets = optimalHashMapStats.getNumberOfHashBuckets();

    if (optimalHashMapStats.containsHashBucketsWithMultipleKeys()) {
      final HashedRangedSubsetData hashedRangedSubsetData = new HashedRangedSubsetData(optimalNumberOfHashBuckets);
      hashedRangedSubsetData.optimiseHashMap(ranges, optimalHashMapStats);
      optimisedSubset = new HashedRangedSubsetImpl(
          hashedRangedSubsetData.blockKeys,
          hashedRangedSubsetData.codePointRangesByBlock,
          hashedRangedSubsetData.numberOfCodePointRanges,
          hashedRangedSubsetData.codePointsSize);
    } else {
      final OptimalHashedRangedSubsetData optimalHashedRangedSubsetData = new OptimalHashedRangedSubsetData(optimalNumberOfHashBuckets);
      optimalHashedRangedSubsetData.optimiseHashMap(ranges, optimalHashMapStats);
      optimisedSubset = new OptimalHashedRangedSubsetImpl(
          optimalHashedRangedSubsetData.blockKeys,
          optimalHashedRangedSubsetData.codePointRangesByBlock,
          optimalHashedRangedSubsetData.numberOfCodePointRanges,
          optimalHashedRangedSubsetData.codePointsSize);
    }
  }


  public Subset getOptimisedSubset() {
    return optimisedSubset;
  }

  public boolean containsHashBucketsWithMultipleKeys() {
    return optimalHashMapStats.containsHashBucketsWithMultipleKeys();
  }

  public char[] getSortedBlockKeySet() {
    return blockKeyStats.blockKeys;
  }

  /**
   * A tuple to be returned by {@link #calculatePossibleHashMapStats} containing the full set of block-keys and also how many code-point ranges there
   * are for each block key.
   */

  /**
   * Returns a {@link #calculatePossibleHashMapStats} tuple containing the full set of block-keys and also how many code-point ranges there are for
   * each block key.
   *
   * @param ranges the code-point ranges that we want stats from.
   * @return a {@link #calculatePossibleHashMapStats} tuple containing the full set of block-keys and also how many code-point ranges there are for
   * each block key.
   */
  private static BlockKeyStats getBlockKeysStats(final Iterable<CodePointRange> ranges) {
    char[] blockKeys = new char[255];
    int[] codePointRangesSize = new int[255];
    int i = -1;
    for (CodePointRange codePointRange : ranges) {
      final char blockKeyFrom = (char) ((codePointRange.inclusiveFrom >> 8) & 0xFFFF);
      final char blockKeyTo = (char) ((codePointRange.inclusiveTo >> 8) & 0xFFFF);
      for (char blockKey = blockKeyFrom; blockKey <= blockKeyTo; ++blockKey) {
        if (i < 0 || blockKey != blockKeys[i]) {
          ++i;
          if (i == blockKeys.length) {
            blockKeys = Arrays.copyOf(blockKeys, blockKeys.length + 255);
            codePointRangesSize = Arrays.copyOf(codePointRangesSize, codePointRangesSize.length + 255);
          }
          blockKeys[i] = blockKey;
          codePointRangesSize[i]++;
        } else if (blockKey != blockKeyTo) {
          codePointRangesSize[i]++;
        }
      }
    }
    ++i;
    return new BlockKeyStats(
        Arrays.copyOf(blockKeys, i),
        Arrays.copyOf(codePointRangesSize, i));
  }

  private static HashMapStats[] calculatePossibleHashMapStats(final char[] blockKeys, final int minHashBuckets, final int maxHashBuckets) {
    final HashMapStats[] result = new HashMapStats[maxHashBuckets - minHashBuckets + 1];
    for (int i = 0, j = minHashBuckets; i < result.length; ++i, ++j) {
      result[i] = new HashMapStats(j, blockKeys);
    }
    Arrays.sort(result, HashMapStats.HASH_MAP_STATS_COMPARATOR);
    return result;
  }


  @Override
  public String toString() {
    final StringBuilder s = new StringBuilder();
    s.append("\nweight  buckets  0-keys  1-key  2-keys  3+keys");
    for (HashMapStats stats : hashMapStats) {
      s.append(String.format("%n%5d  %7d   %6d  %5d  %6d  %6d",
          stats.getWeight(),
          stats.getNumberOfHashBuckets(),
          stats.getCountOfHashBucketsWith0Keys(),
          stats.getCountOfHashBucketsWith1Key(),
          stats.getCountOfHashBucketsWith2Keys(),
          stats.getCountOfHashBucketsWith3OrMoreKeys()));
    }
    return s.toString();
  }

  private static class BlockKeyStats {

    final char[] blockKeys;
    final int[] codePointRangesSize;

    public BlockKeyStats(final char[] blockKeys, final int[] codePointRangesSize) {
      this.blockKeys = blockKeys;
      this.codePointRangesSize = codePointRangesSize;
    }
  }

  private static class HashMapStats {

    static final Comparator<HashMapStats> HASH_MAP_STATS_COMPARATOR =
        Comparator.comparing(HashMapStats::getWeight);

    private final int numberOfHashBuckets;
    private final int[] hashBucketCounts;
    private int countOfHashBucketsWith0Keys;
    private int countOfHashBucketsWith1Key = 0;
    private int countOfHashBucketsWith2Keys = 0;
    private int countOfHashBucketsWith3OrMoreKeys = 0;

    private HashMapStats(
        final int numberOfHashBuckets,
        final char[] blockKeys) {
      this.numberOfHashBuckets = numberOfHashBuckets;
      this.hashBucketCounts = new int[numberOfHashBuckets];
      this.countOfHashBucketsWith0Keys = numberOfHashBuckets;
      for (int blockKey : blockKeys) {
        addKey(blockKey);
      }
    }

    private void addKey(final int key) {
      int index = (key & 0x7FFFFFFF) % hashBucketCounts.length;
      hashBucketCounts[index]++;
      switch (hashBucketCounts[index]) {
        case 1:
          countOfHashBucketsWith1Key++;
          countOfHashBucketsWith0Keys--;
          break;
        case 2:
          countOfHashBucketsWith2Keys++;
          countOfHashBucketsWith1Key--;
          break;
        case 3:
          countOfHashBucketsWith3OrMoreKeys++;
          countOfHashBucketsWith2Keys--;
          break;
        default:
          // do nothing – already subtracted from countOfHashBucketsWith2Keys when it got to 3
          break;
      }
    }

    private long getWeight() {
      return
          (containsHashBucketsWithMultipleKeys() ? numberOfHashBuckets * 16L : 0L)
              // x 16 conditional penalty for too many slots         // x 16  penalty for too many slots
              + countOfHashBucketsWith0Keys * 4L          // x 4   penalty for wasting space
              + countOfHashBucketsWith1Key                // x 1   as-is
              + countOfHashBucketsWith2Keys * 128L         // x 256 performance penalty for iterating over multiple keys
              + countOfHashBucketsWith3OrMoreKeys * 512L; // x 512 performance penalty for iterating over multiple keys
    }

    private boolean containsHashBucketsWithMultipleKeys() {
      return countOfHashBucketsWith2Keys > 0
          || countOfHashBucketsWith3OrMoreKeys > 0;
    }

    private int getNumberOfHashBuckets() {
      return numberOfHashBuckets;
    }

    private int[] getHashBucketCounts() {
      return hashBucketCounts;
    }

    private int getCountOfHashBucketsWith0Keys() {
      return countOfHashBucketsWith0Keys;
    }

    private int getCountOfHashBucketsWith1Key() {
      return countOfHashBucketsWith1Key;
    }

    private int getCountOfHashBucketsWith2Keys() {
      return countOfHashBucketsWith2Keys;
    }

    public int getCountOfHashBucketsWith3OrMoreKeys() {
      return countOfHashBucketsWith3OrMoreKeys;
    }
  }

  public static class HashedRangedSubsetData {

    /**
     * <p>The hashed block-keys. A block-key is the two most significant bytes of a three-byte code-point.</p>
     *
     * <p>In the code of this class the two-dimensional indexes will be referred to as:</p>
     * <pre>
     *        ┌──── hashIndex       - an index to the hash-bucket
     *        │  ┌─ hashBucketIndex - an index to the key within the hash-bucket
     *        │  │
     *   char[ ][ ] blockKeys
     * </pre>
     */
    private final char[][] blockKeys;

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
    private final char[][][] codePointRangesByBlock;

    private int numberOfCodePointRanges = 0;
    private int codePointsSize = 0;

    public HashedRangedSubsetData(final int optimalNumberOfHashBuckets) {
      blockKeys = new char[optimalNumberOfHashBuckets][];
      codePointRangesByBlock = new char[optimalNumberOfHashBuckets][][];
      Arrays.hashCode(new char[0]);
      new char[0].hashCode();
    }

    public char[][] getBlockKeys() {
      return blockKeys;
    }

    public int getRangesSize() {
      return numberOfCodePointRanges;
    }

    public int getCodePointsSize() {
      return codePointsSize;
    }

    public void optimiseHashMap(final Iterable<CodePointRange> ranges, final HashMapStats optimalHashMapStats) {
      final int[] hashBucketCounts = optimalHashMapStats.getHashBucketCounts();
      final int[] hashBucketSizes = new int[hashBucketCounts.length];
      final int[][] codePointRangesSizes = new int[hashBucketCounts.length][];
      // Create the 1st-dimension arrays to the exact optimal size of hash-buckets.
      for (int hashIndex = 0; hashIndex < hashBucketCounts.length; ++hashIndex) {
        if (hashBucketCounts[hashIndex] == 0) {
          blockKeys[hashIndex] = null;
          codePointRangesByBlock[hashIndex] = null;
        } else {
          blockKeys[hashIndex] = new char[hashBucketCounts[hashIndex]];
          codePointRangesByBlock[hashIndex] = new char[hashBucketCounts[hashIndex]][];
          codePointRangesSizes[hashIndex] = new int[hashBucketCounts[hashIndex]];
        }
      }
      // Add all unicode code-point ranges to the hash-map of blocked code-point ranges.
      for (CodePointRange codePointRange : ranges) {
        final char blockKeyFrom = (char) ((codePointRange.inclusiveFrom >> 8) & 0xFFFF);
        final char blockKeyTo = (char) ((codePointRange.inclusiveTo >> 8) & 0xFFFF);
        for (char blockKey = blockKeyFrom; blockKey <= blockKeyTo; ++blockKey) {
          final char inclusiveFrom = (char) (blockKey == blockKeyFrom ? (codePointRange.inclusiveFrom & 0xFF) : 0x00);
          final char inclusiveTo = (char) (blockKey == blockKeyTo ? (codePointRange.inclusiveTo & 0xFF) : 0xFF);
          final int hashIndex = (blockKey & 0x7FFFFFFF) % blockKeys.length;
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
          if (codePointRangesByBlock[hashIndex][hashBucketIndex] == null) {
            codePointRangesByBlock[hashIndex][hashBucketIndex] = new char[32];
          }
          if (codePointRangesSizes[hashIndex][hashBucketIndex] == codePointRangesByBlock[hashIndex][hashBucketIndex].length) {
            codePointRangesByBlock[hashIndex][hashBucketIndex] = Arrays.copyOf(
                codePointRangesByBlock[hashIndex][hashBucketIndex],
                codePointRangesByBlock[hashIndex][hashBucketIndex].length + 32);
          }
          final int codePointRangesSize = codePointRangesSizes[hashIndex][hashBucketIndex];
          blockKeys[hashIndex][hashBucketIndex] = blockKey;
          codePointRangesByBlock[hashIndex][hashBucketIndex][codePointRangesSize] = RangedSubsetUtils.rangeToChar(inclusiveFrom, inclusiveTo);
          codePointRangesSizes[hashIndex][hashBucketIndex]++;
          numberOfCodePointRanges++;
          codePointsSize += inclusiveTo - inclusiveFrom + 1;
        }
      }
      // Make copies of arrays to exact required sizes to minimize wasted space.
      for (int hashIndex = 0; hashIndex < blockKeys.length; ++hashIndex) {
        if (blockKeys[hashIndex] != null) {
          blockKeys[hashIndex] = Arrays.copyOf(blockKeys[hashIndex], hashBucketSizes[hashIndex]);
          codePointRangesByBlock[hashIndex] = Arrays.copyOf(codePointRangesByBlock[hashIndex], hashBucketSizes[hashIndex]);
          for (int hashBucketIndex = 0; hashBucketIndex < codePointRangesByBlock[hashIndex].length; ++hashBucketIndex) {
            codePointRangesByBlock[hashIndex][hashBucketIndex] = Arrays.copyOf(
                codePointRangesByBlock[hashIndex][hashBucketIndex],
                codePointRangesSizes[hashIndex][hashBucketIndex]);
          }
        }
      }
    }
  }

  public static class OptimalHashedRangedSubsetData {

    /**
     * <p>The hashed block-keys. A block-key is the two most significant bytes of a three-byte code-point.</p>
     *
     * <p>In the code of this class the two-dimensional indexes will be referred to as:</p>
     * <pre>
     *        ┌─ hashIndex - an index to the hash-bucket
     *        │
     *   char[ ] blockKeys
     * </pre>
     */
    private final char[] blockKeys;

    /**
     * <p>The hashed single-byte code-point ranges. The ranges are actually the least significant bytes of the
     * inclusive-from and inclusive-to code-points combined into a two-byte char.</p>
     *
     * <p>In the code of this class the three-dimensional indexes will be referred to as:</p>
     * <pre>
     *        ┌─────── hashIndex        - an index to the hash-bucket
     *        │  ┌─ codePointRangeIndex - an index to the range within the array of ranges
     *        │  │
     *   char[ ][ ] codePointRangesByBlock
     * </pre>
     */
    private final char[][] codePointRangesByBlock;

    private int numberOfCodePointRanges = 0;
    private int codePointsSize = 0;

    public OptimalHashedRangedSubsetData(final int optimalNumberOfHashBuckets) {
      blockKeys = new char[optimalNumberOfHashBuckets];
      codePointRangesByBlock = new char[optimalNumberOfHashBuckets][];
    }


    public char[] getBlockKeys() {
      return blockKeys;
    }

    public char[][] getCodePointRangesByBlock() {
      return codePointRangesByBlock;
    }

    public int getRangesSize() {
      return numberOfCodePointRanges;
    }

    public int getCodePointsSize() {
      return codePointsSize;
    }

    private void optimiseHashMap(final Iterable<CodePointRange> ranges, final HashMapStats optimalHashMapStats) {
      final int[] hashBucketCounts = optimalHashMapStats.getHashBucketCounts();
      final int[] codePointRangesSizes = new int[hashBucketCounts.length];

      // Create the 1st-dimension arrays to the exact optimal size of hash-buckets.
      for (int hashIndex = 0; hashIndex < hashBucketCounts.length; ++hashIndex) {
        if (hashBucketCounts[hashIndex] == 0) {
          blockKeys[hashIndex] = 0xFFFF;
          codePointRangesByBlock[hashIndex] = null;
        } else {
          blockKeys[hashIndex] = 0;
          codePointRangesByBlock[hashIndex] = new char[1];
        }
      }
      // Add all unicode code-point ranges to the hash-map of blocked code-point ranges.
      for (CodePointRange codePointRange : ranges) {
        final char blockKeyFrom = (char) ((codePointRange.inclusiveFrom >> 8) & 0xFFFF);
        final char blockKeyTo = (char) ((codePointRange.inclusiveTo >> 8) & 0xFFFF);
        for (char blockKey = blockKeyFrom; blockKey <= blockKeyTo; ++blockKey) {
          final int inclusiveFrom = (char) (blockKey == blockKeyFrom ? (codePointRange.inclusiveFrom & 0xFF) : 0x00);
          final int inclusiveTo = (char) (blockKey == blockKeyTo ? (codePointRange.inclusiveTo & 0xFF) : 0xFF);
          final int hashIndex = (blockKey & 0x7FFFFFFF) % blockKeys.length;
          if (codePointRangesByBlock[hashIndex] == null) {
            codePointRangesByBlock[hashIndex] = new char[32];
          }
          if (codePointRangesSizes[hashIndex] == codePointRangesByBlock[hashIndex].length) {
            codePointRangesByBlock[hashIndex] = Arrays.copyOf(
                codePointRangesByBlock[hashIndex],
                codePointRangesByBlock[hashIndex].length + 32);
          }
          final int codePointRangesSize = codePointRangesSizes[hashIndex];
          blockKeys[hashIndex] = blockKey;
          codePointRangesByBlock[hashIndex][codePointRangesSize] = RangedSubsetUtils.rangeToChar(inclusiveFrom, inclusiveTo);
          codePointRangesSizes[hashIndex]++;
          numberOfCodePointRanges++;
          codePointsSize += (inclusiveTo - inclusiveFrom + 1);
        }
      }
      // Make copies of arrays to exact required sizes to minimize wasted space.
      for (int hashIndex = 0; hashIndex < blockKeys.length; ++hashIndex) {
        if (blockKeys[hashIndex] != 0xFFFF) {
          codePointRangesByBlock[hashIndex] = Arrays.copyOf(
              codePointRangesByBlock[hashIndex],
              codePointRangesSizes[hashIndex]);
        }
      }
    }
  }

}
