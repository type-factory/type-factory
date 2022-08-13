package org.datatypeproject;

interface OptimalHashedRangedSubset extends Subset {

  /**
   * Creates a {@link HashedRangedSubsetBuilder} to create {@link OptimalHashedRangedSubset} instances. This method shadows/hides the
   * {@link Subset#builder()} method.
   *
   * <p>Package-scoped because we should only be invoking this as an implementation detail.</p>
   *
   * @return A {@link HashedRangedSubsetBuilder} to create {@link OptimalHashedRangedSubset} instances.
   */
  static HashedRangedSubsetBuilder builder() {
    return new HashedRangedSubsetBuilderImpl();
  }

  int[] getBlockKeySet();

  int[] getBlockKeys();

  char[][] getSingleByteCodePointRangesByBlock();
}
