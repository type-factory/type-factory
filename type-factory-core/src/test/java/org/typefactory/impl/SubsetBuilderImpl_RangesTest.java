/*
   Copyright 2021-2022 Evan Toliopoulos (typefactory.org)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.logging.Logger;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.Subset.CodePointRange;
import org.typefactory.impl.SubsetBuilderImpl.Ranges;
import org.typefactory.testutils.CodePointRangeArrayConverter;
import org.typefactory.testutils.RangesAsCharArrayConverter;
import org.typefactory.testutils.RangesAsIntegerArrayConverter;
import org.typefactory.testutils.RangesAsLongArrayConverter;

class SubsetBuilderImpl_RangesTest {

  static final Logger logger = Logger.getLogger(SubsetBuilderImpl_RangesTest.class.getName());

  @ParameterizedTest
  @CsvSource(textBlock = """
      0x30_3F          | 0x30_3F          |
      0x30_3F          | 0x29_40          |

      0x30_3F          | 0x30_30          | 0x31_3F
      0x30_3F          | 0x30_31          | 0x32_3F
      0x30_3F          | 0x29_30          | 0x31_3F
      0x30_3F          | 0x29_31          | 0x32_3F

      0x30_3F          | 0x3F_3F          | 0x30_3E
      0x30_3F          | 0x3F_40          | 0x30_3E
      0x30_3F          | 0x3E_3F          | 0x30_3D
      0x30_3F          | 0x3E_40          | 0x30_3D

      0x30_3F          | 0x31_31          | 0x30_30, 0x32_3F
      0x30_3F          | 0x3E_3E          | 0x30_3D, 0x3F_3F
      0x30_3F          | 0x34_38          | 0x30_33, 0x39_3F

      0x30_3F, 0x50_5F | 0x30_5F          |
      0x30_3F, 0x50_5F | 0x29_60          |
      0x30_3F, 0x50_5F | 0x40_4F          | 0x30_3F, 0x50_5F

      0x30_3F, 0x50_5F | 0x30_54          | 0x55_5F
      0x30_3F, 0x50_5F | 0x38_5F          | 0x30_37
      0x30_3F, 0x50_5F | 0x38_54          | 0x30_37, 0x55_5F

      0x30_3F, 0x50_5F | 0x31_31          | 0x30_30, 0x32_3F, 0x50_5F
      0x30_3F, 0x50_5F | 0x3E_3E          | 0x30_3D, 0x3F_3F, 0x50_5F
      0x30_3F, 0x50_5F | 0x34_38          | 0x30_33, 0x39_3F, 0x50_5F

      0x30_3F, 0x50_5F | 0x51_51          | 0x30_3F, 0x50_50, 0x52_5F
      0x30_3F, 0x50_5F | 0x5E_5E          | 0x30_3F, 0x50_5D, 0x5F_5F
      0x30_3F, 0x50_5F | 0x54_58          | 0x30_3F, 0x50_53, 0x59_5F

      0x30_3F, 0x50_5F | 0x31_31, 0x51_51 | 0x30_30, 0x32_3F, 0x50_50, 0x52_5F
      0x30_3F, 0x50_5F | 0x3E_3E, 0x5E_5E | 0x30_3D, 0x3F_3F, 0x50_5D, 0x5F_5F
      0x30_3F, 0x50_5F | 0x34_38, 0x54_58 | 0x30_33, 0x39_3F, 0x50_53, 0x59_5F
      """, delimiter = '|')
  void removeCodePointRange_returnsAsExpectedForSingleByteRanges(
      @ConvertWith(CodePointRangeArrayConverter.class) final CodePointRange[] initialRanges,
      @ConvertWith(CodePointRangeArrayConverter.class) final CodePointRange[] rangesToRemove,
      @ConvertWith(RangesAsCharArrayConverter.class) final char[] expectedRanges) {

    final var ranges = new Ranges();
    for (CodePointRange range : initialRanges) {
      ranges.addCodePointRange(range.inclusiveFrom, range.inclusiveTo);
    }
    logger.fine("Initial ranges - " + ranges);

    for (CodePointRange range : rangesToRemove) {
      ranges.removeCodePointRange(range.inclusiveFrom, range.inclusiveTo);
    }
    logger.fine("Ranges after remove - " + ranges);

    final var actual = ranges.copyOfSingleByteCodePointRanges();

    assertThat(actual)
        .hasSameSizeAs(expectedRanges)
        .containsExactlyInAnyOrder(expectedRanges);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      0x3000_3FFF              | 0x3000_3FFF              |
      0x3000_3FFF              | 0x2FFF_4000              |

      0x3000_3FFF              | 0x3000_3000              | 0x3001_3FFF
      0x3000_3FFF              | 0x3000_31FF              | 0x3200_3FFF
      0x3000_3FFF              | 0x2FFF_3000              | 0x3001_3FFF
      0x3000_3FFF              | 0x2FFF_3001              | 0x3002_3FFF

      0x3000_3FFF              | 0x3FFF_3FFF              | 0x3000_3FFE
      0x3000_3FFF              | 0x3FFF_4000              | 0x3000_3FFE
      0x3000_3FFF              | 0x3FFE_3FFF              | 0x3000_3FFD
      0x3000_3FFF              | 0x3FFE_4000              | 0x3000_3FFD

      0x3000_3FFF              | 0x3001_3001              | 0x3000_3000, 0x3002_3FFF
      0x3000_3FFF              | 0x300E_300E              | 0x3000_300D, 0x300F_3FFF
      0x3000_3FFF              | 0x3004_3008              | 0x3000_3003, 0x3009_3FFF

      0x3000_3FFF, 0x5000_5FFF | 0x3000_5FFF              |
      0x3000_3FFF, 0x5000_5FFF | 0x2FFF_6000              |
      0x3000_3FFF, 0x5000_5FFF | 0x4000_4FFF              | 0x3000_3FFF, 0x5000_5FFF

      0x3000_3FFF, 0x5000_5FFF | 0x3000_5004              | 0x5005_5FFF
      0x3000_3FFF, 0x5000_5FFF | 0x3008_5FFF              | 0x3000_3007
      0x3000_3FFF, 0x5000_5FFF | 0x3008_5004              | 0x3000_3007, 0x5005_5FFF

      0x3000_3FFF, 0x5000_5FFF | 0x3001_3001              | 0x3000_3000, 0x3002_3FFF, 0x5000_5FFF
      0x3000_3FFF, 0x5000_5FFF | 0x300E_300E              | 0x3000_300D, 0x300F_3FFF, 0x5000_5FFF
      0x3000_3FFF, 0x5000_5FFF | 0x3004_3008              | 0x3000_3003, 0x3009_3FFF, 0x5000_5FFF

      0x3000_3FFF, 0x5000_5FFF | 0x5001_5001              | 0x3000_3FFF, 0x5000_5000, 0x5002_5FFF
      0x3000_3FFF, 0x5000_5FFF | 0x500E_500E              | 0x3000_3FFF, 0x5000_500D, 0x500F_5FFF
      0x3000_3FFF, 0x5000_5FFF | 0x5004_5008              | 0x3000_3FFF, 0x5000_5003, 0x5009_5FFF

      0x3000_3FFF, 0x5000_5FFF | 0x3001_3001, 0x5001_5001 | 0x3000_3000, 0x3002_3FFF, 0x5000_5000, 0x5002_5FFF
      0x3000_3FFF, 0x5000_5FFF | 0x300E_300E, 0x500E_500E | 0x3000_300D, 0x300F_3FFF, 0x5000_500D, 0x500F_5FFF
      0x3000_3FFF, 0x5000_5FFF | 0x3004_3008, 0x5004_5008 | 0x3000_3003, 0x3009_3FFF, 0x5000_5003, 0x5009_5FFF
      """, delimiter = '|')
  void removeCodePointRange_returnsAsExpectedForDoubleByteRanges(
      @ConvertWith(CodePointRangeArrayConverter.class) final CodePointRange[] initialRanges,
      @ConvertWith(CodePointRangeArrayConverter.class) final CodePointRange[] rangesToRemove,
      @ConvertWith(RangesAsIntegerArrayConverter.class) final int[] expectedRanges) {

    final var ranges = new Ranges();
    for (CodePointRange range : initialRanges) {
      ranges.addCodePointRange(range.inclusiveFrom, range.inclusiveTo);
    }
    logger.fine("Initial ranges - " + ranges);

    for (CodePointRange range : rangesToRemove) {
      ranges.removeCodePointRange(range.inclusiveFrom, range.inclusiveTo);
    }
    logger.fine("Ranges after remove - " + ranges);

    final var actual = ranges.copyOfDoubleByteCodePointRanges();

    assertThat(actual)
        .hasSameSizeAs(expectedRanges)
        .containsExactlyInAnyOrder(expectedRanges);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      0x00300000_003FFFFF                      | 0x00300000_003FFFFF                      |
      0x00300000_003FFFFF                      | 0x002FFFFF_00400000                      |

      0x00300000_003FFFFF                      | 0x00300000_00300000                      | 0x00300001_003FFFFF
      0x00300000_003FFFFF                      | 0x00300000_0031FFFF                      | 0x00320000_003FFFFF
      0x00300000_003FFFFF                      | 0x002FFFFF_00300000                      | 0x00300001_003FFFFF
      0x00300000_003FFFFF                      | 0x002FFFFF_00300001                      | 0x00300002_003FFFFF

      0x00300000_003FFFFF                      | 0x003FFFFF_003FFFFF                      | 0x00300000_003FFFFE
      0x00300000_003FFFFF                      | 0x003FFFFF_00400000                      | 0x00300000_003FFFFE
      0x00300000_003FFFFF                      | 0x003FFFFE_003FFFFF                      | 0x00300000_003FFFFD
      0x00300000_003FFFFF                      | 0x003FFFFE_00400000                      | 0x00300000_003FFFFD

      0x00300000_003FFFFF                      | 0x00300001_00300001                      | 0x00300000_00300000, 0x00300002_003FFFFF
      0x00300000_003FFFFF                      | 0x0030000E_0030000E                      | 0x00300000_0030000D, 0x0030000F_003FFFFF
      0x00300000_003FFFFF                      | 0x00300004_00300008                      | 0x00300000_00300003, 0x00300009_003FFFFF

      0x00300000_003FFFFF, 0x00500000_005FFFFF | 0x00300000_005FFFFF                      |
      0x00300000_003FFFFF, 0x00500000_005FFFFF | 0x002FFFFF_00600000                      |
      0x00300000_003FFFFF, 0x00500000_005FFFFF | 0x00400000_004FFFFF                      | 0x00300000_003FFFFF, 0x00500000_005FFFFF

      0x00300000_003FFFFF, 0x00500000_005FFFFF | 0x00300000_00500004                      | 0x00500005_005FFFFF
      0x00300000_003FFFFF, 0x00500000_005FFFFF | 0x00300008_005FFFFF                      | 0x00300000_00300007
      0x00300000_003FFFFF, 0x00500000_005FFFFF | 0x00300008_00500004                      | 0x00300000_00300007, 0x00500005_005FFFFF

      0x00300000_003FFFFF, 0x00500000_005FFFFF | 0x00300001_00300001                      | 0x00300000_00300000, 0x00300002_003FFFFF, 0x00500000_005FFFFF
      0x00300000_003FFFFF, 0x00500000_005FFFFF | 0x0030000E_0030000E                      | 0x00300000_0030000D, 0x0030000F_003FFFFF, 0x00500000_005FFFFF
      0x00300000_003FFFFF, 0x00500000_005FFFFF | 0x00300004_00300008                      | 0x00300000_00300003, 0x00300009_003FFFFF, 0x00500000_005FFFFF

      0x00300000_003FFFFF, 0x00500000_005FFFFF | 0x00500001_00500001                      | 0x00300000_003FFFFF, 0x00500000_00500000, 0x00500002_005FFFFF
      0x00300000_003FFFFF, 0x00500000_005FFFFF | 0x0050000E_0050000E                      | 0x00300000_003FFFFF, 0x00500000_0050000D, 0x0050000F_005FFFFF
      0x00300000_003FFFFF, 0x00500000_005FFFFF | 0x00500004_00500008                      | 0x00300000_003FFFFF, 0x00500000_00500003, 0x00500009_005FFFFF

      0x00300000_003FFFFF, 0x00500000_005FFFFF | 0x00300001_00300001, 0x00500001_00500001 | 0x00300000_00300000, 0x00300002_003FFFFF, 0x00500000_00500000, 0x00500002_005FFFFF
      0x00300000_003FFFFF, 0x00500000_005FFFFF | 0x0030000E_0030000E, 0x0050000E_0050000E | 0x00300000_0030000D, 0x0030000F_003FFFFF, 0x00500000_0050000D, 0x0050000F_005FFFFF
      0x00300000_003FFFFF, 0x00500000_005FFFFF | 0x00300004_00300008, 0x00500004_00500008 | 0x00300000_00300003, 0x00300009_003FFFFF, 0x00500000_00500003, 0x00500009_005FFFFF
      """, delimiter = '|')
  void removeCodePointRange_returnsAsExpectedForTripleByteRanges(
      @ConvertWith(CodePointRangeArrayConverter.class) final CodePointRange[] initialRanges,
      @ConvertWith(CodePointRangeArrayConverter.class) final CodePointRange[] rangesToRemove,
      @ConvertWith(RangesAsLongArrayConverter.class) final long[] expectedRanges) {

    final var ranges = new Ranges();
    for (CodePointRange range : initialRanges) {
      ranges.addCodePointRange(range.inclusiveFrom, range.inclusiveTo);
    }
    logger.fine("Initial ranges - " + ranges);

    for (CodePointRange range : rangesToRemove) {
      ranges.removeCodePointRange(range.inclusiveFrom, range.inclusiveTo);
    }
    logger.fine("Ranges after remove - " + ranges);

    final var actual = ranges.copyOfTripleByteCodePointRanges();

    assertThat(actual)
        .hasSameSizeAs(expectedRanges)
        .containsExactlyInAnyOrder(expectedRanges);
  }
}
