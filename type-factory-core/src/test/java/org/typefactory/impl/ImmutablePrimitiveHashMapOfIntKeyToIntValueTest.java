package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * The {@link ImmutablePrimitiveHashMapOfIntKeyToIntValue} class
 * is tested almost entirely via the {@link PrimitiveHashMapOfIntKeyToIntValueTest} class.
 *
 * The extra tests in this class are to force execution of the missing code paths.
 */
class ImmutablePrimitiveHashMapOfIntKeyToIntValueTest {

  @Test
  void isEmpty_returnsTrue() {

    final int[] keySet = new int[0];
    final int[][] keys = new int[0][0];
    final int[][] values = new int[0][0];
    final ImmutablePrimitiveHashMapOfIntKeyToIntValue map = new ImmutablePrimitiveHashMapOfIntKeyToIntValue(keySet, keys, values);

    assertThat(map.isEmpty()).isTrue();
  }

}
