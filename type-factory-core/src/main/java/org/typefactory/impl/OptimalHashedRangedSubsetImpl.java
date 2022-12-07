/*
   Copyright 2021-2022 Evan Toliopoulos (typefactory.org)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package org.typefactory.impl;

import static org.typefactory.impl.Constants.BYTE_MASK;
import static org.typefactory.impl.Constants.EMPTY_INT_ARRAY;
import static org.typefactory.impl.Constants.EMPTY_LONG_ARRAY;
import static org.typefactory.impl.SubsetUtils.getInclusiveFrom;
import static org.typefactory.impl.SubsetUtils.getInclusiveTo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

class OptimalHashedRangedSubsetImpl implements OptimalHashedRangedSubset {

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
   *   char[ ][ ] codePointRangesByBlock
   * </pre>
   */
  private final char[][] codePointRangesByBlock;

  private final int numberOfCodePointRanges;

  private final int numberOfCodePointsInCodePointRanges;

  OptimalHashedRangedSubsetImpl(
      final char[] blockKeys,
      final char[][] codePointRangesByBlock,
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
    final int availableBlockKey = blockKeys[hashIndex];
    if (availableBlockKey == 0xFFFF || blockKey != availableBlockKey) {
      return false;
    }
    final char[] singleByteCodePointRanges = codePointRangesByBlock[hashIndex];
    return SubsetUtils.contains(
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
        if (tempBlockKeySetSize == tempBlockKeySet.length) {
          tempBlockKeySet = Arrays.copyOf(tempBlockKeySet, tempBlockKeySet.length + 128);
        }
        if (blockKeys[hashIndex] != 0xFFFF) {
          tempBlockKeySet[tempBlockKeySetSize++] = blockKeys[hashIndex];
        }
      }
      tempBlockKeySet = Arrays.copyOf(tempBlockKeySet, tempBlockKeySetSize);
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
  public char[][] getCodePointRangesByBlock() {
    return codePointRangesByBlock;
  }

  @Override
  public Iterable<CodePointRange> ranges() {
    return new CodePointRangeIterable();
  }

  private class CodePointRangeIterable implements Iterable<CodePointRange> {

    @Override
    public Iterator<CodePointRange> iterator() {
      return isEmpty()
          ? new EmptyCodePointRangeIterator()
          : new CodePointRangeIterator(getBlockKeySet());
    }
  }

  private class CodePointRangeIterator implements Iterator<CodePointRange> {

    private final CodePointRange result = new CodePointRange(0, 0);
    private final char[] blockKeySet;
    private int blockKeySetIndex;
    private int blockKey;
    private int codePointBlock;
    private int hashIndex;
    private int codePointRangeIndex;

    public CodePointRangeIterator(final char[] blockKeySet) {
      this.blockKeySet = blockKeySet;
      this.blockKeySetIndex = 0;
      this.blockKey = this.blockKeySet[blockKeySetIndex];
      this.codePointBlock = blockKey << 8;
      this.hashIndex = (0x7FFF_FFFF & blockKey) % blockKeys.length;
      this.codePointRangeIndex = 0;
    }

    @Override
    public boolean hasNext() {
      if (codePointRangeIndex == codePointRangesByBlock[hashIndex].length) {
        blockKeySetIndex++;
        if (blockKeySetIndex == blockKeySet.length) {
          --blockKeySetIndex;
          return false;
        }
        this.blockKey = this.blockKeySet[blockKeySetIndex];
        this.codePointBlock = blockKey << 8;
        this.hashIndex = (0x7FFF_FFFF & blockKey) % blockKeys.length;
        this.codePointRangeIndex = 0;
      }
      return true;
    }

    @Override
    public CodePointRange next() {
      if (hasNext()) {
        result.inclusiveFrom = codePointBlock | getInclusiveFrom(codePointRangesByBlock[hashIndex][codePointRangeIndex]);
        result.inclusiveTo = codePointBlock | getInclusiveTo(codePointRangesByBlock[hashIndex][codePointRangeIndex]);
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
