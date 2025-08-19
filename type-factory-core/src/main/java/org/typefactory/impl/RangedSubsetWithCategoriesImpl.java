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

class RangedSubsetWithCategoriesImpl extends RangedSubsetImpl implements SubsetWithCategories {

  /**
   * Each bit of the following value corresponds to a {@link Category} identified by the {@link Category#bitMask};
   */
  private final long unicodeCategoryBitFlags;

  private final int numberOfUnicodeCategories;

  RangedSubsetWithCategoriesImpl(
      final long unicodeCategoryBitFlags,
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges,
      final int numberOfUnicodeCategories) {
    super(singleByteCodePointRanges, doubleByteCodePointRanges, tripleByteCodePointRanges,
        numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);
    this.unicodeCategoryBitFlags = unicodeCategoryBitFlags;
    this.numberOfUnicodeCategories = numberOfUnicodeCategories;
  }

  @Override
  public boolean isEmpty() {
    return numberOfUnicodeCategories == 0 && super.isEmpty();
  }

  @Override
  public boolean contains(final int codePoint) {
    return super.contains(codePoint) || Category.codePointIsInOneOfTheCategories(codePoint, unicodeCategoryBitFlags);
  }

  @Override
  public boolean includes(final Category category) {
    return Category.categoryBitFlagsIncludesCategory(category,unicodeCategoryBitFlags);
  }

  @Override
  public long unicodeCategoryBitFlags() {
    return unicodeCategoryBitFlags;
  }
}
