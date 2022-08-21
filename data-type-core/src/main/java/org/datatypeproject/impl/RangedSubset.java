package org.datatypeproject.impl;

import org.datatypeproject.Subset;

/**
 * <p>Provides a way of collecting a set of code-points by capturing only the extremities of the code-point ranges in the set.
 * The code-point ranges are stored as single-byte, double-byte and triple-byte code point ranges.</p>
 *
 * <p>Package-scoped because we may replace with another implementation if this implementation is sub-optimal.</p>
 */
interface RangedSubset extends Subset {

  /**
   * Creates a {@link RangedSubsetBuilder} to create {@link RangedSubset} instances. This method shadows/hides the {@link Subset#builder()} method.
   *
   * <p>Package-scoped because we should only be invoking this as an implementation detail.</p>
   *
   * @return A {@link RangedSubsetBuilder} to create {@link RangedSubset} instances.
   */
  static RangedSubsetBuilder builder() {
    return new RangedSubsetBuilderImpl();
  }

  char[] getSingleByteCodePointRanges();

  int[] getDoubleByteCodePointRanges();

  long[] getTripleByteCodePointRanges();

  default boolean isEmpty() {
    return (getSingleByteCodePointRanges() == null || getSingleByteCodePointRanges().length == 0)
        && (getDoubleByteCodePointRanges() == null || getDoubleByteCodePointRanges().length == 0)
        && (getTripleByteCodePointRanges() == null || getTripleByteCodePointRanges().length == 0);
  }

  @Override
  default boolean contains(final int codePoint) {
    return RangedSubsetUtils.contains(
        codePoint,
        getSingleByteCodePointRanges(),
        getDoubleByteCodePointRanges(),
        getTripleByteCodePointRanges());
  }
}
