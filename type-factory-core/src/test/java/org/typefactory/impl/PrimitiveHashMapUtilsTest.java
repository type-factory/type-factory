package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PrimitiveHashMapUtilsTest {

  @Test
  void toDataStructureStringForMapsOfIntKeyToIntValue_nonOptimalHashMap() {

    final int[][] keys = new int[][]{
        new int[]{0x1, 0x2, 0x3},
        null,
        new int[]{0x4, 0x5, 0x6},
        new int[0],
        new int[]{0x80000000, 0x80, 0x81, 0x82, 0x83, 0x84, 0x85, 0x86, 0x87, 0x88, 0x89}
    };
    final int[][] values = new int[][]{
        new int[]{0x11, 0x22, 0x33},
        null,
        new int[]{0x44, 0x55, 0x66},
        new int[]{},
        new int[]{0x00000000, 0x00000090, 0x00000091, 0x00000092, 0x00000093, 0x00000094, 0x00000095, 0x00000096, 0x00000097, 0x00000098, 0x00000099}
    };

    final var actual = PrimitiveHashMapUtils.toDataStructureStringForMapsOfIntKeyToIntValue(keys, values);

    assertThat(actual).isEqualTo("""
        int[][] keys = new int[][]{
            new int[]{0x00000001, 0x00000002, 0x00000003},
            null,
            new int[]{0x00000004, 0x00000005, 0x00000006},
            new int[]{},
            new int[]{0x80000000, 0x00000080, 0x00000081, 0x00000082, 0x00000083, 0x00000084, 0x00000085, 0x00000086,
                      0x00000087, 0x00000088, 0x00000089},
        };
        int[][] values = new int[][]{
            new int[]{0x00000011, 0x00000022, 0x00000033},
            null,
            new int[]{0x00000044, 0x00000055, 0x00000066},
            new int[]{},
            new int[]{0x00000000, 0x00000090, 0x00000091, 0x00000092, 0x00000093, 0x00000094, 0x00000095, 0x00000096,
                      0x00000097, 0x00000098, 0x00000099},
        };
        """);
  }

  @Test
  void toDataStructureStringForMapsOfIntKeyToIntValue_optimalHashMap_fewEntries() {

    final int[] keys = new int[]{0x1, 0x2, 0x80000000, 0x3};
    final int[] values = new int[]{0x11, 0x22, 0x00, 0x33};

    final var actual = PrimitiveHashMapUtils.toDataStructureStringForMapsOfIntKeyToIntValue(keys, values);

    assertThat(actual).isEqualTo("""
        int[] keys = new int[]{
            0x00000001, 0x00000002, 0x80000000, 0x00000003};
        int[] values = new int[]{
            0x00000011, 0x00000022, 0x00000000, 0x00000033};
        """);
  }

  @Test
  void toDataStructureStringForMapsOfIntKeyToIntValue_optimalHashMap_severalEntries() {

    final int[] keys = new int[]{0x1, 0x2, 0x80000000, 0x3, 0x4, 0x5, 0x6, 0x7, 0x8, 0x80000000, 0x9};
    final int[] values = new int[]{0x11, 0x22, 0x00, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88, 0x00, 0x99};

    final var actual = PrimitiveHashMapUtils.toDataStructureStringForMapsOfIntKeyToIntValue(keys, values);

    assertThat(actual).isEqualTo("""
        int[] keys = new int[]{
            0x00000001, 0x00000002, 0x80000000, 0x00000003, 0x00000004, 0x00000005, 0x00000006, 0x00000007,
            0x00000008, 0x80000000, 0x00000009};
        int[] values = new int[]{
            0x00000011, 0x00000022, 0x00000000, 0x00000033, 0x00000044, 0x00000055, 0x00000066, 0x00000077,
            0x00000088, 0x00000000, 0x00000099};
        """);
  }
}
