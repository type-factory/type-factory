package org.datatypeproject.impl;

import org.datatypeproject.Subset;

interface HashedRangedSubset extends Subset {

  char[] getBlockKeySet();

  char[][] getBlockKeys();

  char[][][] getCodePointRangesByBlock();
}
