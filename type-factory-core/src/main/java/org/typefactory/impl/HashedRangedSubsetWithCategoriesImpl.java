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

import org.typefactory.Category;
import org.typefactory.SubsetWithCategories;

class HashedRangedSubsetWithCategoriesImpl extends HashedRangedSubsetImpl implements SubsetWithCategories {

  /**
   * Each bit of the following value corresponds to a {@link Category} identified by the {@link Category#bitMask};
   */
  private final long unicodeCategoryBitFlags;

  private final int numberOfUnicodeCategories;

  HashedRangedSubsetWithCategoriesImpl(
      final long unicodeCategoryBitFlags,
      final char[][] blocks,
      final char[][][] codePointRangesByBlock,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges,
      final int numberOfUnicodeCategories) {
    super(blocks, codePointRangesByBlock, numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);
    this.unicodeCategoryBitFlags = unicodeCategoryBitFlags;
    this.numberOfUnicodeCategories = numberOfUnicodeCategories;
  }

  @Override
  public boolean contains(final int codePoint) {
    return super.contains(codePoint)
        || (unicodeCategoryBitFlags > 0L
        && (unicodeCategoryBitFlags & (0x1L << Character.getType(codePoint))) > 0L);
  }

  @Override
  public long unicodeCategoryBitFlags() {
    return unicodeCategoryBitFlags;
  }

  @Override
  public int numberOfUnicodeCategories() {
    return numberOfUnicodeCategories;
  }
}
