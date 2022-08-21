package org.datatypeproject.impl;

import static org.datatypeproject.impl.Constants.BYTE_MASK;
import static org.datatypeproject.impl.Constants.EMPTY_INT_ARRAY;
import static org.datatypeproject.impl.Constants.EMPTY_LONG_ARRAY;
import static org.datatypeproject.impl.RangedSubsetUtils.getInclusiveFrom;
import static org.datatypeproject.impl.RangedSubsetUtils.getInclusiveTo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.datatypeproject.CodePointRange;

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
   *   char[ ][ ][ ] singleByteCodePointRangesByBlock
   * </pre>
   */
  private final char[][][] singleByteCodePointRangesByBlock;

  private final int rangesSize;

  private final int codePointsSize;

  HashedRangedSubsetImpl(
      final char[][] blockKeys,
      final char[][][] singleByteCodePointRangesByBlock,
      final int rangesSize,
      final int codePointsSize) {
    this.blockKeys = blockKeys;
    this.singleByteCodePointRangesByBlock = singleByteCodePointRangesByBlock;
    this.rangesSize = rangesSize;
    this.codePointsSize = codePointsSize;
  }

  @Override
  public boolean isEmpty() {
    return blockKeys.length == 0;
  }

  @Override
  public boolean contains(final int codePoint) {
    final char blockKey = (char)((codePoint >> 8) & 0xFFFF);
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
    final char[] singleByteCodePointRanges = singleByteCodePointRangesByBlock[hashIndex][hashBucketIndex];
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
      char [] tempBlockKeySet = new char[256];
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
      Arrays.sort(tempBlockKeySet);
      blockKeySet = tempBlockKeySet;
    }
    return Arrays.copyOf(blockKeySet, blockKeySet.length);
  }

  @Override
  public char[][] getBlockKeys() {
    return blockKeys;
  }

  @Override
  public char[][][] getSingleByteCodePointRangesByBlock() {
    return singleByteCodePointRangesByBlock;
  }

  @Override
  public Iterable<CodePointRange> ranges() {
    return new CodePointRangeIterable();
  }

  private class CodePointRangeIterable implements Iterable<CodePointRange> {

    @Override
    public Iterator<CodePointRange> iterator() {
      return new CodePointRangeIterator();
    }
  }

  private class CodePointRangeIterator implements Iterator<CodePointRange> {

    private int keySetIndex;
    private int hashIndex;
    private int hashBucketIndex;
    private int codePointRangeIndex;
    private CodePointRangeImpl result = new CodePointRangeImpl();

    @Override
    public boolean hasNext() {
      if (keySetIndex == blockKeySet.length) {
        return false;
      }
      final int blockKey = blockKeySet[keySetIndex];
      hashIndex = (0x7FFF_FFFF & blockKey) % blockKeys.length;
      final char[] availableBlockKeys = blockKeys[hashIndex];
      while (hashBucketIndex < availableBlockKeys.length && blockKey != availableBlockKeys[hashBucketIndex]) {
        ++hashBucketIndex;
      }
      return hashBucketIndex != availableBlockKeys.length
          && codePointRangeIndex < singleByteCodePointRangesByBlock[hashIndex][hashBucketIndex].length;
    }

    @Override
    public CodePointRange next() {
      if (hasNext() && codePointRangeIndex < singleByteCodePointRangesByBlock[hashIndex][hashBucketIndex].length) {
        result.inclusiveFrom = getInclusiveFrom(singleByteCodePointRangesByBlock[hashIndex][hashBucketIndex][codePointRangeIndex]);
        result.inclusiveTo = getInclusiveTo(singleByteCodePointRangesByBlock[hashIndex][hashBucketIndex][codePointRangeIndex]);
        codePointRangeIndex++;
        if (codePointRangeIndex == singleByteCodePointRangesByBlock[hashIndex][hashBucketIndex].length) {
          keySetIndex++;
          hashIndex = 0;
          hashBucketIndex = 0;
          codePointRangeIndex = 0;
        }
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
