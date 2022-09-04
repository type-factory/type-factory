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
  private final HashMapStats[] hashMapStats;
  private final HashMapStats optimalHashMapStats;
  private final int optimalNumberOfHashBuckets;

  private final Subset optimisedSubset;

  public SubsetOptimiser(final Iterable<CodePointRange> ranges) {
    blockKeyStats = BlockKeyStats.of(ranges);
    hashMapStats = HashMapStats.of(blockKeyStats);
    System.out.println("\nHashMapStats:\n=============\n" + this.toString());
    optimalHashMapStats = hashMapStats[0];

    optimalNumberOfHashBuckets = optimalHashMapStats.getNumberOfHashBuckets();

    if (optimalHashMapStats.containsHashBucketsWithMultipleKeys()) {
      final HashedRangedSubsetData hashedRangedSubsetData = new HashedRangedSubsetData(optimalNumberOfHashBuckets);
      hashedRangedSubsetData.optimiseHashMap(ranges, optimalHashMapStats);
      optimisedSubset = new HashedRangedSubsetImpl(
          hashedRangedSubsetData.blockKeys,
          hashedRangedSubsetData.codePointRangesByBlock,
          hashedRangedSubsetData.numberOfCodePointRanges,
          hashedRangedSubsetData.numberOfCodePointsInCodePointRanges);
    } else {
      final OptimalHashedRangedSubsetData optimalHashedRangedSubsetData = new OptimalHashedRangedSubsetData(optimalNumberOfHashBuckets);
      optimalHashedRangedSubsetData.optimiseHashMap(ranges, optimalHashMapStats);
      optimisedSubset = new OptimalHashedRangedSubsetImpl(
          optimalHashedRangedSubsetData.blockKeys,
          optimalHashedRangedSubsetData.codePointRangesByBlock,
          optimalHashedRangedSubsetData.numberOfCodePointRanges,
          optimalHashedRangedSubsetData.numberOfCodePointsInCodePointRanges);
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



  @Override
  public String toString() {
    final StringBuilder s = new StringBuilder();
    s.append("\n|=========|========|=======|========|=========|========|==========|=======|=============|");
    s.append("\n|         |    hash buckets containing...     |        |     memory required (bytes)    |");
    s.append("\n|  # hash |-----------------------------------|  sort  |--------------------------------|");
    s.append("\n| buckets | 0 keys | 1 key | 2 keys | 3+ keys | weight | obj refs |  data | total bytes |");
    s.append("\n|=========|========|=======|========|=========|========|==========|=======|=============|");
    for (HashMapStats stats : hashMapStats) {
      s.append(String.format("%n| %7d | %6d | %5d | %6d | %7d | %6d | %8d | %5d | %11d |",
          stats.getNumberOfHashBuckets(),
          stats.getCountOfHashBucketsWith0Keys(),
          stats.getCountOfHashBucketsWith1Key(),
          stats.getCountOfHashBucketsWith2Keys(),
          stats.getCountOfHashBucketsWith3OrMoreKeys(),
          stats.getWeight(),
          stats.getByteSizeOfArrayReferences(),
          stats.getByteSizeOfBlockKeyData() + stats.getByteSizeOfCodePointRangeData(),
          stats.getTotalBytes()));
    }
    s.append("\n|=========|========|=======|========|=========|========|==========|=======|=============|");
    return s.toString();
  }

  protected static class BlockKeyStats {

    final char[] blockKeys;
    final int[] codePointRangesSize;

    final int byteSizeOfBlockKeyData;

    final int byteSizeOfCodePointRangeData;

    final int hashcode;

    protected BlockKeyStats(
        final char[] blockKeys,
        final int[] codePointRangesSize,
        final int byteSizeOfBlockKeyData,
        final int byteSizeOfCodePointRangeData,
        final int hashcode) {
      this.blockKeys = blockKeys;
      this.codePointRangesSize = codePointRangesSize;
      this.byteSizeOfBlockKeyData = byteSizeOfBlockKeyData;
      this.byteSizeOfCodePointRangeData = byteSizeOfCodePointRangeData;
      this.hashcode = hashcode;
    }


    /**
     * Returns {@link BlockKeyStats} containing the full set of block-keys, how many code-point ranges there are for each block key, as well as some
     * memory stats describing the bytes that will be used to hold the block key data and the code point ranges data.
     *
     * @param ranges the code-point ranges that we want stats from.
     * @return a {@link BlockKeyStats} tuple containing the full set of block-keys, how many code-point ranges there are for, and some memory usage
     * stats. each block key.
     */
    protected static BlockKeyStats of(final Iterable<CodePointRange> ranges) {
      int hashcode = 0;
      int byteSizeOfBlockKeyData = 0;
      int byteSizeOfCodePointRangeData = 0;
      char[] blockKeys = new char[255];
      int[] codePointRangesSize = new int[255];
      int i = -1;
      for (CodePointRange codePointRange : ranges) {
        hashcode = 31 * hashcode + codePointRange.inclusiveFrom;
        hashcode = 31 * hashcode + codePointRange.inclusiveTo;
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
            byteSizeOfBlockKeyData += 2;       // 2 byte block key
            byteSizeOfCodePointRangeData += 2; // 2 byte code point range
          } else if (blockKey != blockKeyTo) {
            codePointRangesSize[i]++;
            byteSizeOfCodePointRangeData += 2; // 2 byte code point range
          }
        }
      }
      ++i;
      return new BlockKeyStats(
          Arrays.copyOf(blockKeys, i),
          Arrays.copyOf(codePointRangesSize, i),
          byteSizeOfBlockKeyData,
          byteSizeOfCodePointRangeData,
          hashcode);
    }

  }

  private static class HashMapStats {

    static final Comparator<HashMapStats> HASH_MAP_STATS_COMPARATOR =
//        Comparator.comparing(HashMapStats::getWeight);
        Comparator.comparing(HashMapStats::getTotalBytes);

    private final BlockKeyStats blockKeyStats;

    private final int numberOfHashBuckets;
    private final int[] hashBucketCounts;
    private int countOfHashBucketsWith0Keys;
    private int countOfHashBucketsWith1Key = 0;
    private int countOfHashBucketsWith2Keys = 0;
    private int countOfHashBucketsWith3OrMoreKeys = 0;
    private int byteSizeOfArrayReferences = 0;


    private HashMapStats(
        final int numberOfHashBuckets,
        final BlockKeyStats blockKeyStats) {
      this.numberOfHashBuckets = numberOfHashBuckets;
      this.blockKeyStats = blockKeyStats;
      this.hashBucketCounts = new int[numberOfHashBuckets];
      this.countOfHashBucketsWith0Keys = numberOfHashBuckets;
      for (int blockKey : blockKeyStats.blockKeys) {
        addKey(blockKey);
      }
      byteSizeOfArrayReferences = 2 * 8; // 2 * 8 byte array object references for blockKeys and codePointRanges arrays.
      if (containsHashBucketsWithMultipleKeys()) {
        byteSizeOfArrayReferences += numberOfHashBuckets * 8;   // number of hash-bucket * 8 byte array object references for blockKeys
        byteSizeOfArrayReferences += numberOfHashBuckets * 8;   // number of hash-bucket * 8 byte array object references for codePointRanges
        for (int i = 0; i < hashBucketCounts.length; ++i) {
          byteSizeOfArrayReferences +=
              hashBucketCounts[i] * 8; // number of codePointRange arrays * 8 byte array object references for codePointRanges
        }
      } else {
        byteSizeOfArrayReferences += numberOfHashBuckets * 8;   // number of hash-bucket * 8 byte array object references for codePointRanges
      }
    }

    /**
     * A tuple to be returned by {@link #of} containing the full set of block-keys and also how many code-point ranges there
     * are for each block key.
     */
    private static HashMapStats[] of(final BlockKeyStats blockKeyStats) {
      final int numberOfBlockKeys = blockKeyStats.blockKeys.length;
      final int minHashBuckets = Math.max(3, (int) Math.floor(numberOfBlockKeys * 0.7D));
      final int maxHashBuckets = Math.max(7, (int) Math.ceil(numberOfBlockKeys * 2.6D));
      HashMapStats[] result = new HashMapStats[maxHashBuckets - minHashBuckets + 1];
      boolean foundOptimalHashMap = false;
      int resultIndex = 0;
      // Try and find an optimal number of hash buckets with every hash bucket containing at most one key.
      for (int j = numberOfBlockKeys; j < maxHashBuckets; ++j, ++resultIndex) {
        result[resultIndex] = new HashMapStats(j, blockKeyStats);
        if (!result[resultIndex].containsHashBucketsWithMultipleKeys()) {
          result = Arrays.copyOf(result, resultIndex + 1);
          foundOptimalHashMap = true;
          break;
        }
      }
      if (!foundOptimalHashMap) {
        // If no optimal hash map available then continue gathering stats on hash map with smaller number of buckets.
        for (int j = minHashBuckets; j < numberOfBlockKeys; ++j, ++resultIndex) {
          result[resultIndex] = new HashMapStats(j, blockKeyStats);
        }
      }
      Arrays.sort(result, HashMapStats.HASH_MAP_STATS_COMPARATOR);
      return result;
    }


    private void addKey(final int key) {
      int hashIndex = (key & 0x7FFFFFFF) % hashBucketCounts.length;
      hashBucketCounts[hashIndex]++;
      switch (hashBucketCounts[hashIndex]) {
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
          (containsHashBucketsWithMultipleKeys() ? 4L : 1L) // 4 x   performance penalty for having hash buckets with multiple keys
              * (numberOfHashBuckets * 4L                   // x 4   space penalty for too many hash buckets
              + countOfHashBucketsWith0Keys * 16L           // x 16  space penalty for having hash buckets with no keys
              + countOfHashBucketsWith1Key                  // x 1   as-is
              + countOfHashBucketsWith2Keys * 128L          // x 128 performance penalty for iterating over multiple keys
              + countOfHashBucketsWith3OrMoreKeys * 512L);  // x 512 performance penalty for iterating over multiple keys
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

    public int getByteSizeOfBlockKeyData() {
      return blockKeyStats.byteSizeOfBlockKeyData;
    }

    public int getByteSizeOfCodePointRangeData() {
      return blockKeyStats.byteSizeOfCodePointRangeData;
    }

    public int getByteSizeOfArrayReferences() {
      return byteSizeOfArrayReferences;
    }

    public int getTotalBytes() {
      return byteSizeOfArrayReferences + blockKeyStats.byteSizeOfBlockKeyData + blockKeyStats.byteSizeOfCodePointRangeData;
    }

    public int getHashCode() {
      return blockKeyStats.hashcode;
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
    private int numberOfCodePointsInCodePointRanges = 0;

    public HashedRangedSubsetData(final int optimalNumberOfHashBuckets) {
      blockKeys = new char[optimalNumberOfHashBuckets][];
      codePointRangesByBlock = new char[optimalNumberOfHashBuckets][][];
    }

    public char[][] getBlockKeys() {
      return blockKeys;
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
          codePointRangesByBlock[hashIndex][hashBucketIndex][codePointRangesSize] = SubsetUtils.rangeToChar(inclusiveFrom, inclusiveTo);
          codePointRangesSizes[hashIndex][hashBucketIndex]++;
          numberOfCodePointRanges++;
          numberOfCodePointsInCodePointRanges += inclusiveTo - inclusiveFrom + 1;
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
    private int numberOfCodePointsInCodePointRanges = 0;

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
      return numberOfCodePointsInCodePointRanges;
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
          codePointRangesByBlock[hashIndex][codePointRangesSize] = SubsetUtils.rangeToChar(inclusiveFrom, inclusiveTo);
          codePointRangesSizes[hashIndex]++;
          numberOfCodePointRanges++;
          numberOfCodePointsInCodePointRanges += (inclusiveTo - inclusiveFrom + 1);
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
