package org.datatypeproject.impl;

import static org.datatypeproject.impl.Constants.BYTE_MASK;
import static org.datatypeproject.impl.Constants.EMPTY_INT_ARRAY;
import static org.datatypeproject.impl.Constants.EMPTY_LONG_ARRAY;
import static org.datatypeproject.impl.RangedSubsetUtils.getInclusiveFrom;
import static org.datatypeproject.impl.RangedSubsetUtils.getInclusiveTo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

class HashedRangedSubsetImpl implements HashedRangedSubset {

  /**
   * <p>The set of unique sorted block-keys in ascending order. This enables iteration of the
   * code-point ranges in order from lowest to highest.</p>
   */
  private char[] blockKeySet = null;

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

  private final int numberOfCodePointRanges;

  private final int numberOfCodePointsInCodePointRanges;

  HashedRangedSubsetImpl(
      final char[][] blockKeys,
      final char[][][] codePointRangesByBlock,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges) {
    this.blockKeys = blockKeys;
    this.codePointRangesByBlock = codePointRangesByBlock;
    this.numberOfCodePointRanges = numberOfCodePointRanges;
    this.numberOfCodePointsInCodePointRanges = numberOfCodePointsInCodePointRanges;
  }

  @Override
  public boolean isEmpty() {
    return numberOfCodePointRanges == 0 && numberOfCodePointsInCodePointRanges == 0;
  }

  @Override
  public int numberOfCodePointRanges() {
    return numberOfCodePointRanges;
  }

  @Override
  public int numberOfCodePointsInCodePointRanges() {
    return numberOfCodePointsInCodePointRanges;
  }

  @Override
  public boolean contains(final int codePoint) {
    final char blockKey = (char) ((codePoint >> 8) & 0xFFFF);
    final int hashIndex = (blockKey & 0x7FFFFFFF) % blockKeys.length;
    final char[] availableBlockKeys = blockKeys[hashIndex];
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
    final char[] singleByteCodePointRanges = codePointRangesByBlock[hashIndex][hashBucketIndex];
    return RangedSubsetUtils.contains(
        codePoint & BYTE_MASK,
        singleByteCodePointRanges,
        EMPTY_INT_ARRAY,
        EMPTY_LONG_ARRAY);
  }

  @Override
  public char[] getBlockKeySet() {
    if (blockKeySet == null) {
      int tempBlockKeySetSize = 0;
      char[] tempBlockKeySet = new char[256];
      for (int hashIndex = 0; hashIndex < blockKeys.length; ++hashIndex) {
        if (blockKeys[hashIndex] != null) {
          for (int hashBucketIndex = 0; hashBucketIndex < blockKeys[hashIndex].length; ++hashBucketIndex) {
            if (tempBlockKeySetSize == tempBlockKeySet.length) {
              tempBlockKeySet = Arrays.copyOf(tempBlockKeySet, tempBlockKeySet.length + 128);
            }
            tempBlockKeySet[tempBlockKeySetSize++] = blockKeys[hashIndex][hashBucketIndex];
          }
        }
      }
      blockKeySet = Arrays.copyOf(tempBlockKeySet, tempBlockKeySetSize);
      Arrays.sort(blockKeySet);
    }
    // return a copy so as not to jeopardise the state of our internal array.
    return Arrays.copyOf(blockKeySet, blockKeySet.length);
  }

  @Override
  public char[][] getBlockKeys() {
    return blockKeys;
  }

  @Override
  public char[][][] getCodePointRangesByBlock() {
    return codePointRangesByBlock;
  }

  @Override
  public Iterable<CodePointRange> ranges() {
    return new CodePointRangeIterable();
  }

  private class CodePointRangeIterable implements Iterable<CodePointRange> {

    @Override
    public Iterator<CodePointRange> iterator() {
      return new CodePointRangeIterator(getBlockKeySet());
    }
  }

  private class CodePointRangeIterator implements Iterator<CodePointRange> {

    private final CodePointRange result = new CodePointRange(0, 0);
    private final char[] blockKeySet;
    private int blockKeySetIndex;
    private int blockKey;
    private int codePointBlock;
    private int hashIndex;
    private int hashBucketIndex;
    private int codePointRangeIndex;

    public CodePointRangeIterator(final char[] blockKeySet) {
      this.blockKeySet = blockKeySet;
      this.blockKeySetIndex = 0;
      this.blockKey = this.blockKeySet[blockKeySetIndex];
      this.codePointBlock = blockKey << 8;
      this.hashIndex = (0x7FFF_FFFF & blockKey) % blockKeys.length;
      this.hashBucketIndex = 0;
      this.codePointRangeIndex = 0;
    }

    @Override
    public boolean hasNext() {
      if (codePointRangeIndex == codePointRangesByBlock[hashIndex][hashBucketIndex].length) {
        ++hashBucketIndex;
        if (hashBucketIndex == codePointRangesByBlock[hashIndex].length) {
          blockKeySetIndex++;
          if (blockKeySetIndex == blockKeySet.length) {
            return false;
          }
          blockKey = this.blockKeySet[blockKeySetIndex];
          codePointBlock = blockKey << 8;
          hashIndex = (0x7FFF_FFFF & blockKey) % blockKeys.length;
          hashBucketIndex = 0;
        }
        codePointRangeIndex = 0;
      }
      return true;
    }

    @Override
    public CodePointRange next() {
      if (hasNext()) {
        result.inclusiveFrom = codePointBlock | getInclusiveFrom(codePointRangesByBlock[hashIndex][hashBucketIndex][codePointRangeIndex]);
        result.inclusiveTo = codePointBlock | getInclusiveTo(codePointRangesByBlock[hashIndex][hashBucketIndex][codePointRangeIndex]);
        codePointRangeIndex++;
      } else {
        throw new NoSuchElementException();
      }
      return result;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
}