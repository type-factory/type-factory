package org.datatypeproject;

import static org.datatypeproject.Constants.BYTE_MASK;
import static org.datatypeproject.Constants.EMPTY_INT_ARRAY;
import static org.datatypeproject.Constants.EMPTY_LONG_ARRAY;
import static org.datatypeproject.RangedSubsetUtils.getInclusiveFrom;
import static org.datatypeproject.RangedSubsetUtils.getInclusiveTo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

class OptimalHashedRangedSubsetImpl implements OptimalHashedRangedSubset {

  private final String name;

  private final String alias;

  /**
   * <p>The set of unique sorted block-keys in ascending order. This enables iteration of the
   * code-point ranges in order from lowest to highest.</p>
   */
  private int[] blockKeySet = null;

  /**
   * <p>The hashed block-keys. A block-key is the two most significant bytes of a three-byte code-point.</p>
   *
   * <p>In the code of this class the two-dimensional indexes will be referred to as:</p>
   * <pre>
   *        ┌─ hashIndex - an index to the hash-bucket which has at most one key
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
   *        ┌──── hashIndex           - an index to the hash-bucket
   *        │  ┌─ codePointRangeIndex - an index to the range within the array of ranges
   *        │  │
   *   char[ ][ ] singleByteCodePointRangesByBlock
   * </pre>
   */
  private final char[][] singleByteCodePointRangesByBlock;

  private final int rangesSize;

  private final int codePointsSize;

  OptimalHashedRangedSubsetImpl(
      final char[] blockKeys,
      final char[][] singleByteCodePointRangesByBlock,
      final int rangesSize,
      final int codePointsSize) {
    this("", "", blockKeys, singleByteCodePointRangesByBlock,
        rangesSize, codePointsSize);
  }

  OptimalHashedRangedSubsetImpl(
      final String name,
      final String alias,
      final char[] blockKeys,
      final char[][] singleByteCodePointRangesByBlock,
      final int rangesSize,
      final int codePointsSize) {
    this.name = name;
    this.alias = alias;
    this.blockKeys = blockKeys;
    this.singleByteCodePointRangesByBlock = singleByteCodePointRangesByBlock;
    this.rangesSize = rangesSize;
    this.codePointsSize = codePointsSize;
  }

  @Override
  public boolean isEmpty() {
    return rangesSize == 0 && codePointsSize == 0;
  }

  @Override
  public boolean contains(final int codePoint) {
    final char blockKey = (char)((codePoint >> 8) & 0xFFFF);
    final int hashIndex = (blockKey & 0x7FFFFFFF) % blockKeys.length;
    final int availableBlockKey = blockKeys[hashIndex];
    if (availableBlockKey < 0 || blockKey != availableBlockKey) {
      return false;
    }
    final char[] singleByteCodePointRanges = singleByteCodePointRangesByBlock[hashIndex];
    return RangedSubsetUtils.contains(
        codePoint & BYTE_MASK,
        singleByteCodePointRanges,
        EMPTY_INT_ARRAY,
        EMPTY_LONG_ARRAY);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getAlias() {
    return alias;
  }

  @Override
  public int[] getBlockKeySet() {
    if (blockKeySet == null) {
      int tempBlockKeySetSize = 0;
      int [] tempBlockKeySet = new int[256];
      for (int hashIndex = 0; hashIndex < blockKeys.length; ++hashIndex) {
        if (tempBlockKeySetSize == tempBlockKeySet.length) {
          tempBlockKeySet = Arrays.copyOf(tempBlockKeySet, tempBlockKeySet.length + 128);
        }
        if (blockKeys[hashIndex] >= 0) {
          tempBlockKeySet[tempBlockKeySetSize++] = blockKeys[hashIndex];
        }
      }
      Arrays.sort(tempBlockKeySet);
      blockKeySet = tempBlockKeySet;
    }
    return Arrays.copyOf(blockKeySet, blockKeySet.length);
  }

  @Override
  public char[] getBlockKeys() {
    return blockKeys;
  }

  @Override
  public char[][] getSingleByteCodePointRangesByBlock() {
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
    private int codePointRangeIndex;
    private MutableCodePointRange result = new MutableCodePointRange();

    @Override
    public boolean hasNext() {
      if (keySetIndex == blockKeySet.length) {
        return false;
      }
      final int blockKey = blockKeySet[keySetIndex];
      hashIndex = (0x7FFF_FFFF & blockKey) % blockKeys.length;
      return codePointRangeIndex < singleByteCodePointRangesByBlock[hashIndex].length;
    }

    @Override
    public CodePointRange next() {
      if (hasNext() && codePointRangeIndex < singleByteCodePointRangesByBlock[hashIndex].length) {
        result.inclusiveFrom = getInclusiveFrom(singleByteCodePointRangesByBlock[hashIndex][codePointRangeIndex]);
        result.inclusiveTo = getInclusiveTo(singleByteCodePointRangesByBlock[hashIndex][codePointRangeIndex]);
        codePointRangeIndex++;
        if (codePointRangeIndex == singleByteCodePointRangesByBlock[hashIndex].length) {
          keySetIndex++;
          hashIndex = 0;
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
