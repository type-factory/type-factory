package org.datatypeproject.impl;

import org.datatypeproject.Subset;

interface HashedRangedSubset extends Subset {

  /**
   * Creates a {@link HashedRangedSubsetBuilder} to create {@link HashedRangedSubset} instances. This method shadows/hides the
   * {@link Subset#builder()} method.
   *
   * <p>Package-scoped because we should only be invoking this as an implementation detail.</p>
   *
   * @return A {@link HashedRangedSubsetBuilder} to create {@link HashedRangedSubset} instances.
   */
  static HashedRangedSubsetBuilder builder() {
    return new HashedRangedSubsetBuilderImpl();
  }

  char[] getBlockKeySet();

  char[][] getBlockKeys();

  char[][][] getSingleByteCodePointRangesByBlock();

  @Override
  default boolean isEmpty() {
    return getBlockKeys() == null || getBlockKeys().length == 0;
  }
}
