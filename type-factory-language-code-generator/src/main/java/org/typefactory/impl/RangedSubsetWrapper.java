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

import static org.typefactory.impl.RangedSubsetImpl.EMPTY_SUBSET;

import org.typefactory.Category;

public class RangedSubsetWrapper implements SubsetWrapper, RangedSubset {

  private final RangedSubsetImpl wrapped;

  public RangedSubsetWrapper(final RangedSubsetImpl wrapped) {
    this.wrapped = wrapped == null ? EMPTY_SUBSET : wrapped;
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
  public boolean includes(Category category) {
    return wrapped.includes(category);
  }

  @Override
  public Iterable<CodePointRange> ranges() {
    return wrapped.ranges();
  }

  @Override
  public int numberOfCodePointRanges() {
    return wrapped.numberOfCodePointRanges();
  }

  @Override
  public int numberOfCodePointsInCodePointRanges() {
    return wrapped.numberOfCodePointsInCodePointRanges();
  }

  @Override
  public char[] getSingleByteCodePointRanges() {
    return wrapped.getSingleByteCodePointRanges();
  }

  @Override
  public int[] getDoubleByteCodePointRanges() {
    return wrapped.getDoubleByteCodePointRanges();
  }

  @Override
  public long[] getTripleByteCodePointRanges() {
    return wrapped.getTripleByteCodePointRanges();
  }
}
