package org.datatypeproject;

import static org.datatypeproject.Constants.BYTE_MASK;
import static org.datatypeproject.Constants.EMPTY_INT_ARRAY;
import static org.datatypeproject.Constants.EMPTY_LONG_ARRAY;
import static org.datatypeproject.RangedSubsetUtils.getInclusiveFrom;
import static org.datatypeproject.RangedSubsetUtils.getInclusiveTo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

class BlockRangedSubsetImpl implements BlockRangedSubset {

  private final String name;

  private final String alias;

  private final int[] blocks;

  private final char[][] singleByteCodePointRangesByBlock;

  BlockRangedSubsetImpl(
      final int[] blocks,
      final char[][] singleByteCodePointRangeByBlock) {
    this("", "", blocks, singleByteCodePointRangeByBlock);
  }

  BlockRangedSubsetImpl(
      final String name,
      final String alias,
      final int[] blocks,
      final char[][] singleByteCodePointRangeByBlock) {
    this.name = name;
    this.alias = alias;
    this.blocks = blocks;
    this.singleByteCodePointRangesByBlock = singleByteCodePointRangeByBlock;
  }

  @Override
  public boolean isEmpty() {
    return blocks.length == 0;
  }


  @Override
  public boolean contains(final int codePoint) {
    final int blockKey = codePoint >> 8;
    final int index = Arrays.binarySearch(blocks, blockKey);
    if (index < 0 || blocks[index] != blockKey) {
      return false;
    }
    return RangedSubsetUtils.contains(
        codePoint & BYTE_MASK,
        singleByteCodePointRangesByBlock[index],
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
  public int[] getBlocks() {
    return blocks;
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

    private int index;
    private int singleByteIndex;
    private MutableCodePointRange result = new MutableCodePointRange();

    @Override
    public boolean hasNext() {
      return index < blocks.length
      || (index == blocks.length && singleByteIndex < singleByteCodePointRangesByBlock[index].length);
    }

    @Override
    public CodePointRange next() {
      if (singleByteIndex < singleByteCodePointRangesByBlock[index].length) {
        result.inclusiveFrom = getInclusiveFrom(singleByteCodePointRangesByBlock[index][singleByteIndex]);
        result.inclusiveTo = getInclusiveTo(singleByteCodePointRangesByBlock[index][singleByteIndex]);
        singleByteIndex++;
        if (singleByteIndex == singleByteCodePointRangesByBlock[index].length) {
          index++;
          singleByteIndex = 0;
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
