package org.typefactory.impl;

import org.typefactory.Subset;

interface OptimalHashedRangedSubset extends Subset {

  char[] getBlockKeySet();

  char[] getBlockKeys();

  char[][] getCodePointRangesByBlock();
}
