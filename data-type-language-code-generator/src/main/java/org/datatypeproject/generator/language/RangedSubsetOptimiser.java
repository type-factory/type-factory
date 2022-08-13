package org.datatypeproject.generator.language;

import com.ibm.icu.text.UnicodeSet;
import com.ibm.icu.text.UnicodeSet.EntryRange;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

class RangedSubsetOptimiser {

  /**
   * <p>The set of unique sorted block-keys in ascending order. This enables iteration of the
   * code-point ranges in order from lowest to highest.</p>
   */
  private final char[] blockKeySet;
  private final int minHashBuckets;
  private final int maxHashBuckets;
  private final HashMapStats[] hashMapStats;
  private final HashMapStats optimalHashMapStats;
  private final int optimalNumberOfHashBuckets;

  private final HashedRangedSubsetData hashedRangedSubsetData;

  private final OptimalHashedRangedSubsetData optimalHashedRangedSubsetData;

  public static class HashedRangedSubsetData {

    /**
     * <p>The hashed block-keys. A block-key is the two most significant bytes of a three-byte code-point.</p>
     *
     * <p>In the code of this class the two-dimensional indexes will be referred to as:</p>
     * <pre>
     *        ┌──── hashIndex     - an index to the hash-bucket
     *        │  ┌─ hashBucketIndex - an index to the key within the hash-bucket
     *        │  │
     *   char[ ][ ] blockKeys
     * </pre>
     */
    private final char[][] blockKeys;

    /**
     * <p>The hashed inclusive-from values of the code-point ranges.</p>
     *
     * <p>In the code of this class the three-dimensional indexes will be referred to as:</p>
     * <pre>
     *       ┌─────── hashIndex           - an index to the hash-bucket
     *       │  ┌──── hashBucketIndex       - an index to the key within the hash-bucket
     *       │  │  ┌─ codePointRangeIndex - an index to the from-codepoint within the array of inclusive-from code-points
     *       │  │  │
     *   int[ ][ ][ ] inclusiveFroms
     * </pre>
     */
    private final int[][][] inclusiveFroms;

    /**
     * <p>The hashed inclusive-to values of the code-point ranges.</p>
     *
     * <p>In the code of this class the three-dimensional indexes will be referred to as:</p>
     * <pre>
     *       ┌─────── hashIndex           - an index to the hash-bucket
     *       │  ┌──── hashBucketIndex       - an index to the key within the hash-bucket
     *       │  │  ┌─ codePointRangeIndex - an index to the to-codepoint within the array of inclusive-to code-points
     *       │  │  │
     *   int[ ][ ][ ] inclusiveFroms
     * </pre>
     */
    private final int[][][] inclusiveTos;

    private int rangesSize = 0;
    private int codePointsSize = 0;

    public HashedRangedSubsetData(final int optimalNumberOfHashBuckets) {
      blockKeys = new char[optimalNumberOfHashBuckets][];
      inclusiveFroms = new int[optimalNumberOfHashBuckets][][];
      inclusiveTos = new int[optimalNumberOfHashBuckets][][];
    }

    public char[][] getBlockKeys() {
      return blockKeys;
    }

    public int[][][] getInclusiveFroms() {
      return inclusiveFroms;
    }

    public int[][][] getInclusiveTos() {
      return inclusiveTos;
    }

    public int getRangesSize() {
      return rangesSize;
    }

    public int getCodePointsSize() {
      return codePointsSize;
    }

    public void optimiseHashMap(final UnicodeSet unicodeSet, final HashMapStats optimalHashMapStats) {
      final int[] hashBucketCounts = optimalHashMapStats.getHashBucketCounts();
      final int[] hashBucketSizes = new int[hashBucketCounts.length];
      final int[][] codePointRangesSizes = new int[hashBucketCounts.length][];
      // Create the 1st-dimension arrays to the exact optimal size of hash-buckets.
      for (int hashIndex = 0; hashIndex < hashBucketCounts.length; ++hashIndex) {
        if (hashBucketCounts[hashIndex] == 0) {
          blockKeys[hashIndex] = null;
          inclusiveFroms[hashIndex] = null;
          inclusiveTos[hashIndex] = null;
        } else {
          blockKeys[hashIndex] = new char[hashBucketCounts[hashIndex]];
          inclusiveFroms[hashIndex] = new int[hashBucketCounts[hashIndex]][];
          inclusiveTos[hashIndex] = new int[hashBucketCounts[hashIndex]][];
          codePointRangesSizes[hashIndex] = new int[hashBucketCounts[hashIndex]];
        }
      }
      // Add all unicode code-point ranges to the hash-map of blocked code-point ranges.
      for (EntryRange entryRange : unicodeSet.ranges()) {
        final char blockKeyFrom = (char)((entryRange.codepoint >> 8) & 0xFFFF);
        final char blockKeyTo = (char)((entryRange.codepointEnd >> 8) & 0xFFFF);
        for (char blockKey = blockKeyFrom; blockKey <= blockKeyTo; ++blockKey) {
          final char inclusiveFrom = (char)(blockKey == blockKeyFrom ? (entryRange.codepoint & 0xFF) : 0x00);
          final int inclusiveTo = (char)(blockKey == blockKeyTo ? (entryRange.codepointEnd & 0xFF) : 0xFF);
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
          if (inclusiveFroms[hashIndex][hashBucketIndex] == null) {
            inclusiveFroms[hashIndex][hashBucketIndex] = new int[32];
          }
          if (inclusiveTos[hashIndex][hashBucketIndex] == null) {
            inclusiveTos[hashIndex][hashBucketIndex] = new int[32];
          }
          if (codePointRangesSizes[hashIndex][hashBucketIndex] == inclusiveFroms[hashIndex][hashBucketIndex].length) {
            inclusiveFroms[hashIndex][hashBucketIndex] = Arrays.copyOf(
                inclusiveFroms[hashIndex][hashBucketIndex],
                inclusiveFroms[hashIndex][hashBucketIndex].length + 32);
          }
          if (codePointRangesSizes[hashIndex][hashBucketIndex] == inclusiveTos[hashIndex][hashBucketIndex].length) {
            inclusiveTos[hashIndex][hashBucketIndex] = Arrays.copyOf(
                inclusiveTos[hashIndex][hashBucketIndex],
                inclusiveTos[hashIndex][hashBucketIndex].length + 32);
          }
          final int codePointRangesSize = codePointRangesSizes[hashIndex][hashBucketIndex];
          blockKeys[hashIndex][hashBucketIndex] = blockKey;
          inclusiveFroms[hashIndex][hashBucketIndex][codePointRangesSize] = inclusiveFrom;
          inclusiveTos[hashIndex][hashBucketIndex][codePointRangesSize] = inclusiveTo;
          codePointRangesSizes[hashIndex][hashBucketIndex]++;
          rangesSize++;
          codePointsSize += inclusiveTo - inclusiveFrom + 1;
        }
      }
      // Make copies of arrays to exact required sizes to minimize wasted space.
      for (int hashIndex = 0; hashIndex < blockKeys.length; ++hashIndex) {
        if (blockKeys[hashIndex] != null) {
          blockKeys[hashIndex] = Arrays.copyOf(blockKeys[hashIndex], hashBucketSizes[hashIndex]);
          inclusiveFroms[hashIndex] = Arrays.copyOf(inclusiveFroms[hashIndex], hashBucketSizes[hashIndex]);
          inclusiveTos[hashIndex] = Arrays.copyOf(inclusiveTos[hashIndex], hashBucketSizes[hashIndex]);
          for (int hashBucketIndex = 0; hashBucketIndex < inclusiveFroms[hashIndex].length; ++hashBucketIndex) {
            inclusiveFroms[hashIndex][hashBucketIndex] = Arrays.copyOf(
                inclusiveFroms[hashIndex][hashBucketIndex],
                codePointRangesSizes[hashIndex][hashBucketIndex]);
            inclusiveTos[hashIndex][hashBucketIndex] = Arrays.copyOf(
                inclusiveTos[hashIndex][hashBucketIndex],
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
     * <p>The hashed inclusive-from values of the code-point ranges.</p>
     *
     * <p>In the code of this class the three-dimensional indexes will be referred to as:</p>
     * <pre>
     *       ┌──── hashIndex           - an index to the hash-bucket
     *       │  ┌─ codePointRangeIndex - an index to the from-codepoint within the array of inclusive-from code-points
     *       │  │
     *   int[ ][ ] inclusiveFroms
     * </pre>
     */
    private final int[][] inclusiveFroms;

    /**
     * <p>The hashed inclusive-to values of the code-point ranges.</p>
     *
     * <p>In the code of this class the three-dimensional indexes will be referred to as:</p>
     * <pre>
     *       ┌──── hashIndex           - an index to the hash-bucket
     *       │  ┌─ codePointRangeIndex - an index to the to-codepoint within the array of inclusive-to code-points
     *       │  │
     *   int[ ][ ] inclusiveFroms
     * </pre>
     */
    private final int[][] inclusiveTos;

    private int rangesSize = 0;
    private int codePointsSize = 0;

    public OptimalHashedRangedSubsetData(final int optimalNumberOfHashBuckets) {
      blockKeys = new char[optimalNumberOfHashBuckets];
      inclusiveFroms = new int[optimalNumberOfHashBuckets][];
      inclusiveTos = new int[optimalNumberOfHashBuckets][];
    }


    public char[] getBlockKeys() {
      return blockKeys;
    }

    public int[][] getInclusiveFroms() {
      return inclusiveFroms;
    }

    public int[][] getInclusiveTos() {
      return inclusiveTos;
    }

    public int getRangesSize() {
      return rangesSize;
    }

    public int getCodePointsSize() {
      return codePointsSize;
    }

    private void optimiseHashMap(final UnicodeSet unicodeSet, final HashMapStats optimalHashMapStats) {
      final int[] hashBucketCounts = optimalHashMapStats.getHashBucketCounts();
      final int[] codePointRangesSizes = new int[hashBucketCounts.length];

      // Create the 1st-dimension arrays to the exact optimal size of hash-buckets.
      for (int hashIndex = 0; hashIndex < hashBucketCounts.length; ++hashIndex) {
        if (hashBucketCounts[hashIndex] == 0) {
          blockKeys[hashIndex] = 0xFFFF;
          inclusiveFroms[hashIndex] = null;
          inclusiveTos[hashIndex] = null;
        } else {
          blockKeys[hashIndex] = 0;
          inclusiveFroms[hashIndex] = new int[1];
          inclusiveTos[hashIndex] = new int[1];
        }
      }
      // Add all unicode code-point ranges to the hash-map of blocked code-point ranges.
      for (EntryRange entryRange : unicodeSet.ranges()) {
        final char blockKeyFrom = (char)((entryRange.codepoint >> 8) & 0xFFFF);
        final char blockKeyTo = (char)((entryRange.codepointEnd >> 8) & 0xFFFF);
        for (char blockKey = blockKeyFrom; blockKey <= blockKeyTo; ++blockKey) {
          final int inclusiveFrom = (char)(blockKey == blockKeyFrom ? (entryRange.codepoint & 0xFF) : 0x00);
          final int inclusiveTo = (char)(blockKey == blockKeyTo ? (entryRange.codepointEnd & 0xFF) : 0xFF);
          final int hashIndex = (blockKey & 0x7FFFFFFF) % blockKeys.length;
          if (inclusiveFroms[hashIndex] == null) {
            inclusiveFroms[hashIndex] = new int[32];
          }
          if (inclusiveTos[hashIndex] == null) {
            inclusiveTos[hashIndex] = new int[32];
          }
          if (codePointRangesSizes[hashIndex] == inclusiveFroms[hashIndex].length) {
            inclusiveFroms[hashIndex] = Arrays.copyOf(
                inclusiveFroms[hashIndex],
                inclusiveFroms[hashIndex].length + 32);
          }
          if (codePointRangesSizes[hashIndex] == inclusiveTos[hashIndex].length) {
            inclusiveTos[hashIndex] = Arrays.copyOf(
                inclusiveTos[hashIndex],
                inclusiveTos[hashIndex].length + 32);
          }
          final int codePointRangesSize = codePointRangesSizes[hashIndex];
          blockKeys[hashIndex] = blockKey;
          inclusiveFroms[hashIndex][codePointRangesSize] = inclusiveFrom;
          inclusiveTos[hashIndex][codePointRangesSize] = inclusiveTo;
          codePointRangesSizes[hashIndex]++;
          rangesSize++;
          codePointsSize += inclusiveTo - inclusiveFrom + 1;
        }
      }
      // Make copies of arrays to exact required sizes to minimize wasted space.
      for (int hashIndex = 0; hashIndex < blockKeys.length; ++hashIndex) {
        if (blockKeys[hashIndex] != 0xFFFF) {
          inclusiveFroms[hashIndex] = Arrays.copyOf(
              inclusiveFroms[hashIndex],
              codePointRangesSizes[hashIndex]);
          inclusiveTos[hashIndex] = Arrays.copyOf(
              inclusiveTos[hashIndex],
              codePointRangesSizes[hashIndex]);
        }
      }
    }
  }

  public RangedSubsetOptimiser(final UnicodeSet unicodeSet) {
    blockKeySet = getBlockKeys(unicodeSet);
    minHashBuckets = Math.max(3, (int) Math.floor(blockKeySet.length * 0.7D));
    maxHashBuckets = Math.max(7, (int) Math.ceil(blockKeySet.length * 2.2D));
    hashMapStats = calculatePossibleHashMapStats(blockKeySet, minHashBuckets, maxHashBuckets);
    optimalHashMapStats = hashMapStats[0];
    optimalNumberOfHashBuckets = optimalHashMapStats.getNumberOfHashBuckets();

    if (optimalHashMapStats.containsHashBucketsWithMultipleKeys()) {
      hashedRangedSubsetData = new HashedRangedSubsetData(optimalNumberOfHashBuckets);
      hashedRangedSubsetData.optimiseHashMap(unicodeSet, optimalHashMapStats);
      optimalHashedRangedSubsetData = null;
    } else {
      hashedRangedSubsetData = null;
      optimalHashedRangedSubsetData = new OptimalHashedRangedSubsetData(optimalNumberOfHashBuckets);
      optimalHashedRangedSubsetData.optimiseHashMap(unicodeSet, optimalHashMapStats);
    }

    System.out.println(this.toString());
  }

  public boolean containsHashBucketsWithMultipleKeys() {
    return optimalHashMapStats.containsHashBucketsWithMultipleKeys();
  }

  public char[] getSortedBlockKeySet() {
    return blockKeySet;
  }

  public HashedRangedSubsetData getHashedRangedSubsetData() {
    return hashedRangedSubsetData;
  }

  public OptimalHashedRangedSubsetData getOptimalHashedRangedSubsetData() {
    return optimalHashedRangedSubsetData;
  }

  private static char[] getBlockKeys(final UnicodeSet unicodeSet) {
    final SortedSet<Character> blockKeys = new TreeSet();
    for (EntryRange entryRange : unicodeSet.ranges()) {
      final char blockKeyFrom = (char)((entryRange.codepoint >> 8) & 0xFFFF);
      final char blockKeyTo = (char)((entryRange.codepointEnd >> 8) & 0xFFFF);
      for (char blockKey = blockKeyFrom; blockKey <= blockKeyTo; ++blockKey) {
        blockKeys.add(blockKey);
      }
    }
    final char [] result = new char [blockKeys.size()];
    int i = 0;
    final Iterator<Character> iter = blockKeys.iterator();
    while (iter.hasNext()) {
      result[i++] = iter.next().charValue();
    }
    return result;
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

}
