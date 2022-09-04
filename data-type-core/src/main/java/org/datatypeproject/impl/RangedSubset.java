package org.datatypeproject.impl;

import org.datatypeproject.Subset;

/**
 * <p>Provides a way of collecting a set of code-points by capturing only the extremities of the code-point ranges in the set.
 * The code-point ranges are stored as single-byte, double-byte and triple-byte code point ranges.</p>
 *
 * <p>Package-scoped because we may replace with another implementation if this implementation is sub-optimal.</p>
 */
interface RangedSubset extends Subset {

  char[] getSingleByteCodePointRanges();

  int[] getDoubleByteCodePointRanges();

  long[] getTripleByteCodePointRanges();

}
