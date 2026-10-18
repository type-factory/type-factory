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

import org.typefactory.Subset.CodePointRange;

interface CodePointSubset {

  CodePointSubset EMPTY = EmptyCodePointSubsetImpl.INSTANCE;

  boolean isEmpty();

  default boolean isNotEmpty() {
    return !isEmpty();
  }

  /**
   * Returns {@code true} if this subset contains the specified {@code codePoint}. That is, the code-point falls inclusively within one of code-point
   * ranges in this subset. Returns {@code false otherwise}.
   *
   * @param codePoint the code-point whose presence in this subset is to be tested
   * @return {@code true} if this subset contains the specified {@code codePoint}. Returns {@code false otherwise}.
   */
  boolean contains(final int codePoint);

  /**
   * Returns {@code true} if this subset contains the specified {@code ch}. That is, the code-point falls inclusively within one of code-point ranges
   * in this subset. Returns {@code false otherwise}.
   *
   * @param ch the character whose presence in this subset is to be tested
   * @return {@code true} if this subset contains the specified {@code ch}. Returns {@code false otherwise}.
   */
  default boolean contains(final char ch) {
    return contains((int) ch);
  }

  /**
   * <p>An {@link Iterable} of code-point ranges in this subset.</p>
   *
   * <p><b>Note:</b> The iterable {@link CodePointRange} instance is reused with each iteration.
   * Use {@link CodePointRange#copy()} if you need to keep references to each of the code-point ranges.</p>
   *
   * @return an {@link Iterable} of the code-point ranges in this subset. Note that the iterable {@link CodePointRange} instance is reused with each
   * iteration.
   * @see CodePointRange#copy()
   */
  Iterable<CodePointRange> ranges();

  int rangesSize();

  default int numberOfCodePointRanges() {
    return rangesSize();
  }

  int numberOfCodePointsInCodePointRanges();

  default String toPattern() {
    return SubsetUtils.toPattern(
        ranges(),
        Constants.EMPTY_CATEGORY_ITERABLE,
        Constants.EMPTY_STRING_ITERABLE);
  }
}

final class EmptyCodePointSubsetImpl implements CodePointSubset {

  static final CodePointSubset INSTANCE = new EmptyCodePointSubsetImpl();

  private EmptyCodePointSubsetImpl() {
  }

  @Override
  public boolean isEmpty() {
    return true;
  }

  @Override
  public boolean contains(final int codePoint) {
    return false;
  }

  @Override
  public Iterable<CodePointRange> ranges() {
    return Constants.EMPTY_CODE_POINT_RANGE_ITERABLE;
  }

  @Override
  public int rangesSize() {
    return 0;
  }

  @Override
  public int numberOfCodePointsInCodePointRanges() {
    return 0;
  }

  @Override
  public String toString() {
    return SubsetUtils.toString(
        Constants.EMPTY_CODE_POINT_RANGE_ITERABLE,
        Constants.EMPTY_CATEGORY_ITERABLE,
        Constants.EMPTY_STRING_ITERABLE);
  }
}
