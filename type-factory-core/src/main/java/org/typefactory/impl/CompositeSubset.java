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

interface CompositeSubset extends Subset {

}

final class CompositeSubsetImpl implements CompositeSubset {

  private final CodePointSubset codePointSubset;
  private final StringSubset stringSubset;
  private final CategorySubset categorySubset;

  public CompositeSubsetImpl(
      CodePointSubset codePointSubset,
      StringSubset stringSubset,
      CategorySubset categorySubset) {
    this.codePointSubset = codePointSubset;
    this.stringSubset = stringSubset;
    this.categorySubset = categorySubset;
  }

  public CodePointSubset getCodePointSubset() {
    return codePointSubset;
  }

  @Override
  public boolean isEmpty() {
    return codePointSubset.isEmpty() && stringSubset.isEmpty() && categorySubset.isEmpty();
  }

  @Override
  public boolean contains(final int codePoint) {
    return codePointSubset.contains(codePoint) || categorySubset.contains(codePoint);
  }

  @Override
  public Iterable<CodePointRange> ranges() {
    return codePointSubset.ranges();
  }

  @Override
  public int rangesSize() {
    return codePointSubset.rangesSize();
  }

  @Override
  public int numberOfCodePointsInCodePointRanges() {
    return codePointSubset.numberOfCodePointsInCodePointRanges();
  }

  @Override
  public Iterable<String> strings() {
    return stringSubset.strings();
  }

  @Override
  public int stringsSize() {
    return stringSubset.stringsSize();
  }

  @Override
  public Iterable<Category> categories() {
    return categorySubset.categories();
  }

  @Override
  public int categoriesSize() {
    return categorySubset.unicodeCategoriesSize();
  }

  @Override
  public boolean containsString(final CharSequence charSequence) {
    return stringSubset.containsString(charSequence);
  }

  @Override
  public int containsString(final CharSequence charSequence, final int startingAtIndex) {
    return stringSubset.containsString(charSequence, startingAtIndex);
  }


  @Override
  public String toPattern() {
    return SubsetUtils.toPattern(
        codePointSubset.ranges(),
        categorySubset.categories(),
        stringSubset.strings());
  }

  @Override
  public String toString() {
    return SubsetUtils.toString(
        codePointSubset.ranges(),
        categorySubset.categories(),
        stringSubset.strings());
  }
}