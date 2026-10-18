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

import org.typefactory.Category;
import org.typefactory.Subset;

public class EmptySubsetImpl implements Subset {

  public static final Subset INSTANCE = new EmptySubsetImpl();

  private EmptySubsetImpl() {
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
  public Iterable<Category> categories() {
    return Constants.EMPTY_CATEGORY_ITERABLE;
  }

  @Override
  public Iterable<String> strings() {
    return Constants.EMPTY_STRING_ITERABLE;
  }

  @Override
  public boolean containsString(CharSequence charSequence) {
    return false;
  }

  @Override
  public int containsString(CharSequence charSequence, int startingAtIndex) {
    return startingAtIndex;
  }

  @Override
  public int rangesSize() {
    return 0;
  }

  @Override
  public int categoriesSize() {
    return 0;
  }

  @Override
  public int stringsSize() {
    return 0;
  }

  @Override
  public int numberOfCodePointRanges() {
    return 0;
  }

  @Override
  public int numberOfCodePointsInCodePointRanges() {
    return 0;
  }

  @Override
  public String toPattern() {
    return SubsetUtils.toPattern(
        ranges(),
        categories(),
        strings());
  }

  @Override
  public String toString() {
    return SubsetUtils.toString(
        ranges(),
        categories(),
        strings());
  }
}
