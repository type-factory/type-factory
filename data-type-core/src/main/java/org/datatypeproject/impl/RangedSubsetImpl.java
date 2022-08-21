package org.datatypeproject.impl;

import static org.datatypeproject.impl.Constants.EMPTY_CHAR_ARRAY;
import static org.datatypeproject.impl.Constants.EMPTY_INT_ARRAY;
import static org.datatypeproject.impl.Constants.EMPTY_LONG_ARRAY;
import static org.datatypeproject.impl.RangedSubsetUtils.defaultIfNullOrEmpty;
import static org.datatypeproject.impl.RangedSubsetUtils.getInclusiveFrom;
import static org.datatypeproject.impl.RangedSubsetUtils.getInclusiveTo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.datatypeproject.CodePointRange;

class RangedSubsetImpl implements RangedSubset {

  private final char[] singleByteCodePointRanges;
  private final int[] doubleByteCodePointRanges;
  private final long[] tripleByteCodePointRanges;

  private final int rangesSize;

  private final int codePointsSize;


  RangedSubsetImpl(
      final char[] singleByteCodePointRanges,
      final int rangesSize,
      final int codePointsSize) {
    this(singleByteCodePointRanges, EMPTY_INT_ARRAY, EMPTY_LONG_ARRAY,
        rangesSize, codePointsSize);
  }

  RangedSubsetImpl(
      final int[] doubleByteCodePointRanges,
      final int rangesSize,
      final int codePointsSize) {
    this(EMPTY_CHAR_ARRAY, doubleByteCodePointRanges, EMPTY_LONG_ARRAY,
        rangesSize, codePointsSize);
  }

  RangedSubsetImpl(
      final long[] tripleByteCodePointRanges,
      final int rangesSize,
      final int codePointsSize) {
    this(EMPTY_CHAR_ARRAY, EMPTY_INT_ARRAY, tripleByteCodePointRanges,
        rangesSize, codePointsSize);
  }

  RangedSubsetImpl(
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges,
      final int rangesSize,
      final int codePointsSize) {
    this(singleByteCodePointRanges, doubleByteCodePointRanges, EMPTY_LONG_ARRAY,
        rangesSize, codePointsSize);
  }

  RangedSubsetImpl(
      final char[] singleByteCodePointRanges,
      final long[] tripleByteCodePointRanges,
      final int rangesSize,
      final int codePointsSize) {
    this(singleByteCodePointRanges, EMPTY_INT_ARRAY, tripleByteCodePointRanges,
        rangesSize, codePointsSize);
  }

  RangedSubsetImpl(
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges,
      final int rangesSize,
      final int codePointsSize) {
    this(EMPTY_CHAR_ARRAY, doubleByteCodePointRanges, tripleByteCodePointRanges,
        rangesSize, codePointsSize);
  }

  RangedSubsetImpl(
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges,
      final int rangesSize,
      final int codePointsSize) {
    this.singleByteCodePointRanges = defaultIfNullOrEmpty(singleByteCodePointRanges, EMPTY_CHAR_ARRAY);
    this.doubleByteCodePointRanges = defaultIfNullOrEmpty(doubleByteCodePointRanges, EMPTY_INT_ARRAY);
    this.tripleByteCodePointRanges = defaultIfNullOrEmpty(tripleByteCodePointRanges, EMPTY_LONG_ARRAY);
    this.rangesSize = rangesSize;
    this.codePointsSize = codePointsSize;
  }

  @Override
  public boolean isEmpty() {
    return rangesSize == 0 && codePointsSize == 0;
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
    s.append('[');
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

    private int singleByteIndex = 0;
    private int doubleByteIndex = 0;
    private int tripleByteIndex = 0;
    private CodePointRangeImpl result = new CodePointRangeImpl();

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
