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

import static org.typefactory.impl.Constants.EMPTY_CHAR_ARRAY;
import static org.typefactory.impl.Constants.EMPTY_INT_ARRAY;
import static org.typefactory.impl.Constants.EMPTY_LONG_ARRAY;
import static org.typefactory.impl.SubsetUtils.defaultIfNullOrEmpty;
import static org.typefactory.impl.SubsetUtils.getInclusiveFrom;
import static org.typefactory.impl.SubsetUtils.getInclusiveTo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

class RangedSubsetImpl implements RangedSubset {

  private final char[] singleByteCodePointRanges;
  private final int[] doubleByteCodePointRanges;
  private final long[] tripleByteCodePointRanges;

  private final int numberOfCodePointRanges;

  private final int numberOfCodePointsInCodePointRanges;


  RangedSubsetImpl(
      final char[] singleByteCodePointRanges,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges) {
    this(singleByteCodePointRanges, EMPTY_INT_ARRAY, EMPTY_LONG_ARRAY,
        numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);
  }

  RangedSubsetImpl(
      final int[] doubleByteCodePointRanges,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges) {
    this(EMPTY_CHAR_ARRAY, doubleByteCodePointRanges, EMPTY_LONG_ARRAY,
        numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);
  }

  RangedSubsetImpl(
      final long[] tripleByteCodePointRanges,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges) {
    this(EMPTY_CHAR_ARRAY, EMPTY_INT_ARRAY, tripleByteCodePointRanges,
        numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);
  }

  RangedSubsetImpl(
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges) {
    this(singleByteCodePointRanges, doubleByteCodePointRanges, EMPTY_LONG_ARRAY,
        numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);
  }

  RangedSubsetImpl(
      final char[] singleByteCodePointRanges,
      final long[] tripleByteCodePointRanges,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges) {
    this(singleByteCodePointRanges, EMPTY_INT_ARRAY, tripleByteCodePointRanges,
        numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);
  }

  RangedSubsetImpl(
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges) {
    this(EMPTY_CHAR_ARRAY, doubleByteCodePointRanges, tripleByteCodePointRanges,
        numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);
  }

  RangedSubsetImpl(
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges) {
    this.singleByteCodePointRanges = defaultIfNullOrEmpty(singleByteCodePointRanges, EMPTY_CHAR_ARRAY);
    this.doubleByteCodePointRanges = defaultIfNullOrEmpty(doubleByteCodePointRanges, EMPTY_INT_ARRAY);
    this.tripleByteCodePointRanges = defaultIfNullOrEmpty(tripleByteCodePointRanges, EMPTY_LONG_ARRAY);
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
    return SubsetUtils.contains(
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
    private CodePointRange result = new CodePointRange(0,0);

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
