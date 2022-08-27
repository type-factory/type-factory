package org.datatypeproject.impl;

import org.datatypeproject.Subset;

interface OptimalHashedRangedSubset extends Subset {

  char[] getBlockKeySet();

  char[] getBlockKeys();

  char[][] getSingleByteCodePointRangesByBlock();
}
