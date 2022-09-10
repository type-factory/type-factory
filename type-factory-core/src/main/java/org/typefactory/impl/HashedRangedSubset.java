package org.typefactory.impl;

import org.typefactory.Subset;

interface HashedRangedSubset extends Subset {

  char[] getBlockKeySet();

  char[][] getBlockKeys();

  char[][][] getCodePointRangesByBlock();
}
