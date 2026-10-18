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

public class CompositeSubsetWrapper implements SubsetWrapper {

  private final CompositeSubsetImpl wrapped;

  public CompositeSubsetWrapper(final CompositeSubsetImpl wrapped) {
    this.wrapped = wrapped == null
        ? new CompositeSubsetImpl(CodePointSubset.EMPTY, StringSubset.EMPTY, CategorySubset.EMPTY)
        : wrapped;
  }

  public CodePointSubsetWrapper getCodePointSubset() {
    final CodePointSubset codePointSubset = wrapped.getCodePointSubset();
    if (codePointSubset instanceof RangedSubsetImpl rangedSubset) {
      return new RangedSubsetWrapper(rangedSubset);
    } else if (codePointSubset instanceof HashedRangedSubsetImpl hashedRangedSubset) {
      return new HashedRangedSubsetWrapper(hashedRangedSubset);
    } else if (codePointSubset instanceof OptimalHashedRangedSubsetImpl optimalHashedRangedSubset) {
      return new OptimalHashedRangedSubsetWrapper(optimalHashedRangedSubset);
    } else {
      return null;
    }
  }

  @Override
  public boolean isEmpty() {
    return wrapped.isEmpty();
  }

  @Override
  public boolean contains(int codePoint) {
    return wrapped.contains(codePoint);
  }

  @Override
  public Iterable<CodePointRange> ranges() {
    return wrapped.ranges();
  }

  @Override
  public Iterable<Category> categories() {
    return wrapped.categories();
  }

  @Override
  public Iterable<String> strings() {
    return wrapped.strings();
  }

  @Override
  public boolean containsString(CharSequence charSequence) {
    return wrapped.containsString(charSequence);
  }

  @Override
  public int containsString(CharSequence charSequence, int startingAtIndex) {
    return wrapped.containsString(charSequence, startingAtIndex);
  }

  @Override
  public int rangesSize() {
    return wrapped.rangesSize();
  }

  @Override
  public int categoriesSize() {
    return wrapped.categoriesSize();
  }

  @Override
  public int stringsSize() {
    return wrapped.stringsSize();
  }

  @Override
  public int numberOfCodePointsInCodePointRanges() {
    return wrapped.numberOfCodePointsInCodePointRanges();
  }

  @Override
  public String toString() {
    return wrapped.toString();
  }

  @Override
  public String toPattern() {
    return wrapped.toPattern();
  }
}

