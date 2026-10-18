/*
 * Copyright © 2021-2026 Evan Toliopoulos (typefactory.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.typefactory.impl;

import java.util.Arrays;
import java.util.List;
import org.typefactory.Category;

interface CategorySubset {

  CategorySubset EMPTY = EmptyCategorySubsetImpl.INSTANCE;

  default boolean isEmpty() {
    return unicodeCategoriesSize() == 0;
  }

  int unicodeCategoriesSize();

  Iterable<Category> categories();

  /**
   * Each bit of the following value corresponds to a {@link Category} identified by the {@link Category#bitMask};
   */
  long unicodeCategoryBitFlags();

  boolean contains(final int codePoint);

  default String toPattern() {
    return SubsetUtils.toPattern(
        Constants.EMPTY_CODE_POINT_RANGE_ITERABLE,
        categories(),
        Constants.EMPTY_STRING_ITERABLE);
  }
}

final class EmptyCategorySubsetImpl implements CategorySubset {

  static final CategorySubset INSTANCE = new EmptyCategorySubsetImpl();

  private EmptyCategorySubsetImpl() {
  }

  @Override
  public int unicodeCategoriesSize() {
    return 0;
  }

  @Override
  public Iterable<Category> categories() {
    return Constants.EMPTY_CATEGORY_ITERABLE;
  }

  @Override
  public long unicodeCategoryBitFlags() {
    return 0L;
  }

  @Override
  public boolean contains(final int codePoint) {
    return false;
  }

  @Override
  public String toString() {
    return SubsetUtils.toString(
        Constants.EMPTY_CODE_POINT_RANGE_ITERABLE,
        Constants.EMPTY_CATEGORY_ITERABLE,
        Constants.EMPTY_STRING_ITERABLE);
  }
}

final class CategorySubsetImpl implements CategorySubset {

  private final Category[] categories;

  private final long unicodeCategoryBitFlags;

  CategorySubsetImpl(final Category[] categories) {
    // Defensive copy to ensure immutability
    this.categories = Arrays.copyOf(categories, categories.length);
    this.unicodeCategoryBitFlags = Category.getCategoryBitFlags(categories);
  }

  @Override
  public Iterable<Category> categories() {
    return List.of(categories);
  }

  @Override
  public int unicodeCategoriesSize() {
    return categories.length;
  }

  @Override
  public long unicodeCategoryBitFlags() {
    return unicodeCategoryBitFlags;
  }

  @Override
  public boolean contains(final int codePoint) {
    return (unicodeCategoryBitFlags & (0x1L << Character.getType(codePoint))) > 0L;
  }

  public String toString() {
    return SubsetUtils.toString(
        Constants.EMPTY_CODE_POINT_RANGE_ITERABLE,
        categories(),
        Constants.EMPTY_STRING_ITERABLE);
  }
}