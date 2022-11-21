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

import org.junit.jupiter.api.Test;
import org.typefactory.impl.SubsetBuilderImpl.SubsetOptimiser;

class SubsetBuilderImpl_SubsetOptimiserTest {

  @Test
  void toString_returnsAsExpectedForSmallSubset() {
    final RangedSubset subset = (RangedSubset) Factory.rangedSubset(
        new char[]{
            0x41_5a, //  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
            0x61_7a, //  a b c d e f g h i j k l m n o p q r s t u v w x y z
        },
        2, 52);

    final var subsetOptimiser = new SubsetOptimiser(subset);
    final String actual = subsetOptimiser.toString();
    assertThat(actual).isEqualTo("""
        |=============|=========|========|=======|========|=========|==========|=======|=============|
        |             |         |    hash buckets containing...     |     memory required (bytes)    |
        |             |  # hash |-----------------------------------|--------------------------------|
        | subset type | buckets | 0 keys | 1 key | 2 keys | 3+ keys | obj refs |  data | total bytes |
        |=============|=========|========|=======|========|=========|==========|=======|=============|
        |      Ranged |         |        |       |        |         |        8 |     4 |          12 |
        |=============|=========|========|=======|========|=========|==========|=======|=============|""");
  }

  @Test
  void toString_returnsAsExpectedForLargeSubset() {
    final RangedSubset subset = (RangedSubset) Factory.rangedSubset(
        new char[]{
            0x41_5a, //  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
            0x61_7a, //  a b c d e f g h i j k l m n o p q r s t u v w x y z
        },
        new int[]{
            0x5a01_5a01, 0x5a18_5a18, 0x5a20_5a20, 0x5a2f_5a2f, 0x5a46_5a46, 0x5a5a_5a5a, 0x5a66_5a66, 0x5a7f_5a7f,
            0x5a92_5a92, 0x5a9b_5a9b, 0x5ac1_5ac1, 0x5ac9_5ac9, 0x5acc_5acc, 0x5ae1_5ae1,
            0x5b22_5b22, 0x5b50_5b50, 0x5b54_5b54, 0x5b57_5b58, 0x5b5d_5b5d, 0x5b63_5b64, 0x5b66_5b66, 0x5b6b_5b6b,
            0x5b85_5b85, 0x5b87_5b89, 0x5b8c_5b8c, 0x5b97_5b9d, 0x5b9f_5b9f, 0x5ba2_5ba4, 0x5bae_5bae, 0x5bb0_5bb0,
            0x5bb3_5bb6, 0x5bb9_5bb9, 0x5bbf_5bbf, 0x5bc2_5bc2, 0x5bc4_5bc4, 0x5bc6_5bc6, 0x5bcc_5bcc, 0x5bd2_5bd2,
            0x5bdb_5bdb, 0x5bdd_5bdd, 0x5bdf_5bdf, 0x5be1_5be1, 0x5be7_5be7, 0x5be9_5be9, 0x5bee_5bee, 0x5bf8_5bf8,
            0x5bfa_5bfa, 0x5bfe_5bff,
            0x5c01_5c02, 0x5c04_5c04, 0x5c06_5c06, 0x5c09_5c0b, 0x5c0e_5c0f, 0x5c11_5c11, 0x5c1a_5c1a, 0x5c31_5c31,
            0x5c3a_5c40, 0x5c45_5c45, 0x5c48_5c48, 0x5c4a_5c4b, 0x5c55_5c55, 0x5c5e_5c5e, 0x5c64_5c65, 0x5c6f_5c6f,
            0x5c71_5c71, 0x5c90_5c90, 0x5ca1_5ca1, 0x5ca9_5ca9, 0x5cac_5cac, 0x5cb3_5cb3, 0x5cb8_5cb8, 0x5ce0_5ce1,
            0x5cf0_5cf0, 0x5cf6_5cf6,
            0x5d07_5d07, 0x5d0e_5d0e, 0x5d16_5d16, 0x5d29_5d29, 0x5d50_5d50, 0x5ddd_5dde, 0x5de1_5de1, 0x5de3_5de3,
            0x5de5_5de8, 0x5dee_5dee, 0x5df1_5df1, 0x5dfb_5dfb, 0x5dfe_5dfe,
        },
        -1, -1);

    final var subsetOptimiser = new SubsetOptimiser(subset);
    final String actual = subsetOptimiser.toString();
    assertThat(actual).isEqualTo("""
        |=============|=========|========|=======|========|=========|==========|=======|=============|
        |             |         |    hash buckets containing...     |     memory required (bytes)    |
        |             |  # hash |-----------------------------------|--------------------------------|
        | subset type | buckets | 0 keys | 1 key | 2 keys | 3+ keys | obj refs |  data | total bytes |
        |=============|=========|========|=======|========|=========|==========|=======|=============|
        | Opt. Hashed |       8 |      3 |     5 |      0 |       0 |       80 |    20 |         100 |
        |      Hashed |       5 |      1 |     3 |      1 |       0 |      136 |    20 |         156 |
        |      Hashed |       6 |      2 |     3 |      1 |       0 |      152 |    20 |         172 |
        |      Hashed |       7 |      3 |     3 |      1 |       0 |      168 |    20 |         188 |
        |      Ranged |         |        |       |        |         |       16 |   352 |         368 |
        |=============|=========|========|=======|========|=========|==========|=======|=============|""");
  }
}
