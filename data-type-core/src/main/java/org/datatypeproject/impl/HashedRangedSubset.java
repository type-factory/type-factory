package org.datatypeproject.impl;

import org.datatypeproject.Subset;

interface HashedRangedSubset extends Subset {

  char[] getBlockKeySet();

  char[][] getBlockKeys();

  char[][][] getCodePointRangesByBlock();

  @Override
  default boolean isEmpty() {
    return getBlockKeys() == null || getBlockKeys().length == 0;
  }
}
