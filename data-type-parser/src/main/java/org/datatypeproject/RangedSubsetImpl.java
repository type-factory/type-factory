package org.datatypeproject;

import static org.datatypeproject.Constants.EMPTY_CHAR_ARRAY;
import static org.datatypeproject.Constants.EMPTY_INT_ARRAY;
import static org.datatypeproject.Constants.EMPTY_LONG_ARRAY;
import static org.datatypeproject.RangedSubsetUtils.defaultIfNullOrEmpty;
import static org.datatypeproject.RangedSubsetUtils.getInclusiveFrom;
import static org.datatypeproject.RangedSubsetUtils.getInclusiveTo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

class RangedSubsetImpl implements RangedSubset {

  private final String name;
  private final String alias;
  private final char[] singleByteCodePointRanges;
  private final int[] doubleByteCodePointRanges;
  private final long[] tripleByteCodePointRanges;

  RangedSubsetImpl(
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges) {
    this("", "",
        singleByteCodePointRanges, doubleByteCodePointRanges, tripleByteCodePointRanges);
  }

  RangedSubsetImpl(
      final String name,
      final String alias,
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges) {
    this.name = name;
    this.alias = alias;
    this.singleByteCodePointRanges = defaultIfNullOrEmpty(singleByteCodePointRanges, EMPTY_CHAR_ARRAY);
    this.doubleByteCodePointRanges = defaultIfNullOrEmpty(doubleByteCodePointRanges, EMPTY_INT_ARRAY);
    this.tripleByteCodePointRanges = defaultIfNullOrEmpty(tripleByteCodePointRanges, EMPTY_LONG_ARRAY);
  }

  @Override
  public boolean isEmpty() {
    return singleByteCodePointRanges.length == 0
        && doubleByteCodePointRanges.length == 0
        && tripleByteCodePointRanges.length == 0;
  }

  /**
   * Returns an array of 1-byte code-point ranges stored as char values. Each range comprises an inclusive-from value and an inclusive-to value. The
   * most-significant 8 bits hold the inclusive-from value. The least-significant 8 bits hold the inclusive-to value. The returned array must be
   * sorted from low-to-high.
   *
   * @return an array of 1-byte code-point ranges stored as char values.
   */
  @Override
  public char[] getSingleByteCodePointRanges() {
    // Protect out data by returning a copy
    return Arrays.copyOf(singleByteCodePointRanges, singleByteCodePointRanges.length);
  }

  /**
   * Returns an array of 2-byte code-point ranges stored as integer values. Each range comprises an inclusive-from value and an inclusive-to value.
   * The most-significant 16 bits hold the inclusive-from value. The least-significant 16 bits hold the inclusive-to value. The returned array must be
   * sorted from low-to-high as unsigned-integers.
   *
   * @return an array of 2-byte code-point ranges stored as integer values.
   */
  @Override
  public int[] getDoubleByteCodePointRanges() {
    // Protect out data by returning a copy
    return Arrays.copyOf(doubleByteCodePointRanges, doubleByteCodePointRanges.length);
  }

  /**
   * Returns an array of 4-byte code-point ranges stored as long values. Each range comprises an inclusive-from value and an inclusive-to value. The
   * most-significant 32 bits hold the inclusive-from value. The least-significant 32 bits hold the inclusive-to value. The returned array must be
   * sorted from low-to-high.
   *
   * @return an array of 4-byte code-point ranges stored as long values.
   */
  @Override
  public long[] getTripleByteCodePointRanges() {
    // Protect out data by returning a copy
    return Arrays.copyOf(tripleByteCodePointRanges, tripleByteCodePointRanges.length);
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
  public boolean contains(final int codePoint) {
    return RangedSubsetUtils.contains(
        codePoint,
        singleByteCodePointRanges,
        doubleByteCodePointRanges,
        tripleByteCodePointRanges);
  }

  @Override
  public String toString() {
    final StringBuilder s = new StringBuilder();
    s.append(name).append('[');
    for (char singleByteCodePointRange : singleByteCodePointRanges) {
      s.append(Integer.toString(getInclusiveFrom(singleByteCodePointRange), 16)).append('.').append('.')
          .append(Integer.toString(getInclusiveTo(singleByteCodePointRange), 16)).append(',');
    }
    for (int doubleByteCodePointRange : doubleByteCodePointRanges) {
      s.append(Integer.toString(getInclusiveFrom(doubleByteCodePointRange), 16)).append('.').append('.')
          .append(Integer.toString(getInclusiveTo(doubleByteCodePointRange), 16)).append(',');
    }
    for (long tripleByteCodePointRange : tripleByteCodePointRanges) {
      s.append(Integer.toString(getInclusiveFrom(tripleByteCodePointRange), 16)).append('.').append('.')
          .append(Integer.toString(getInclusiveTo(tripleByteCodePointRange), 16)).append(',');
    }
    s.setLength(s.length() - 1); // remove final comma
    s.append(']');
    return s.toString();
  }

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

    private int singleByteIndex;
    private int doubleByteIndex;
    private int tripleByteIndex;
    private MutableCodePointRange result = new MutableCodePointRange();

    @Override
    public boolean hasNext() {
      return singleByteIndex < singleByteCodePointRanges.length
          || doubleByteIndex < doubleByteCodePointRanges.length
          || tripleByteIndex < tripleByteCodePointRanges.length;
    }

    @Override
    public CodePointRange next() {
      if (singleByteIndex < singleByteCodePointRanges.length) {
        result.inclusiveFrom = getInclusiveFrom(singleByteCodePointRanges[singleByteIndex]);
        result.inclusiveTo = getInclusiveTo(singleByteCodePointRanges[singleByteIndex]);
        singleByteIndex++;
      } else if (doubleByteIndex < doubleByteCodePointRanges.length) {
        result.inclusiveFrom = getInclusiveFrom(doubleByteCodePointRanges[doubleByteIndex]);
        result.inclusiveTo = getInclusiveTo(doubleByteCodePointRanges[doubleByteIndex]);
        doubleByteIndex++;
      } else if (tripleByteIndex < tripleByteCodePointRanges.length) {
        result.inclusiveFrom = getInclusiveFrom(tripleByteCodePointRanges[tripleByteIndex]);
        result.inclusiveTo = getInclusiveTo(tripleByteCodePointRanges[tripleByteIndex]);
        tripleByteIndex++;
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