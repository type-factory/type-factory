package land.artli.easytype;

import land.artli.easytype.RangedSubsetImpl.RangedSubsetBuilderImpl;

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
}
