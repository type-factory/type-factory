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
//package org.typefactory.impl;
//
//import java.util.Arrays;
//import org.typefactory.Subset;
//
//public interface UnionSubset extends Subset {
//
//  static Subset of(final Subset ... subsets) {
//
//    final var filteredSubsets = Arrays.stream(subsets)
//        .filter(subset -> subset != null && !subset.isEmpty())
//        .toArray(Subset[]::new);
//
//    if (filteredSubsets.length == 0) {
//      return Subset.builder().build();
//    } else if (filteredSubsets.length == 1) {
//      return subsets[0];
//    } else if (filteredSubsets.length == 2) {
//      return new UnionSubset2Impl(subsets[0], subsets[1]);
//    } else {
//      return new UnionSubsetNImpl(filteredSubsets);
//    }
//  }
//
//  /**
//   * <p>An {@link Iterable} of the code-point ranges in this union subset. The code point ranges are not ordered. They will be returned in the order
//   * provided by each of the union subsets in turn. The ranges may overlap.</p>
//   *
//   * <p><b>Note:</b> The iterable {@link CodePointRange} instance is reused with each iteration.
//   * Use {@link CodePointRange#copy()} if you need to keep references to each of the code-point ranges.</p>
//   *
//   * @return an {@link Iterable} of the code-point ranges in this subset. Note that the iterable {@link CodePointRange} instance is reused with each
//   * iteration.
//   * @see CodePointRange#copy()
//   */
//  @Override
//  Iterable<CodePointRange> ranges();
//
//  class UnionSubset2Impl implements UnionSubset {
//
//    private final Subset subset1;
//    private final Subset subset2;
//
//    UnionSubset2Impl(final Subset subset1, final Subset subset2) {
//      if (subset1.numberOfCodePointsInCodePointRanges() >= subset2.numberOfCodePointsInCodePointRanges()) {
//        this.subset1 = subset1;
//        this.subset2 = subset2;
//      } else {
//        this.subset1 = subset2;
//        this.subset2 = subset1;
//      }
//    }
//
//    @Override
//    public boolean isEmpty() {
//      return subset1.isEmpty() && subset2.isEmpty();
//    }
//
//    @Override
//    public boolean contains(int codePoint) {
//      return subset1.contains(codePoint) || subset2.contains(codePoint);
//    }
//
//    @Override
//    public Iterable<CodePointRange> ranges() {
//      return new IterableCodePointRange2(subset1, subset2);
//    }
//
//    @Override
//    public boolean containsString(CharSequence charSequence) {
//      return false;
//    }
//
//    @Override
//    public int containsString(CharSequence charSequence, int startingAtIndex) {
//      return 0;
//    }
//
//    @Override
//    public int numberOfCodePointRanges() {
//      return 0;
//    }
//
//    @Override
//    public int numberOfCodePointsInCodePointRanges() {
//      return 0;
//    }
//
//    private static class IterableCodePointRange2 implements Iterable<CodePointRange> {
//
//      private final Subset subset1;
//      private final Subset subset2;
//
//      IterableCodePointRange2(final Subset subset1, final Subset subset2) {
//        this.subset1 = subset1;
//        this.subset2 = subset2;
//      }
//
//      @Override
//      public java.util.Iterator<CodePointRange> iterator() {
//        return new java.util.Iterator<>() {
//
//          private final java.util.Iterator<CodePointRange> iterator1 = subset1.ranges().iterator();
//          private final java.util.Iterator<CodePointRange> iterator2 = subset2.ranges().iterator();
//
//          @Override
//          public boolean hasNext() {
//            return iterator1.hasNext() || iterator2.hasNext();
//          }
//
//          @Override
//          public CodePointRange next() {
//            if (iterator1.hasNext()) {
//              return iterator1.next();
//            } else if (iterator2.hasNext()) {
//              return iterator2.next();
//            } else {
//              throw new java.util.NoSuchElementException();
//            }
//          }
//        };
//      }
//    }
//  }
//
//  class UnionSubsetNImpl implements UnionSubset {
//
//    private final Subset[] subsets;
//
//    UnionSubsetNImpl(final Subset[] subsets) {
//      this.subsets = subsets;
//    }
//
//    @Override
//    public boolean isEmpty() {
//      for (Subset subset : subsets) {
//        if (!subset.isEmpty()) {
//          return false;
//        }
//      }
//      return true;
//    }
//
//    @Override
//    public boolean contains(int codePoint) {
//      for (Subset subset : subsets) {
//        if (subset.contains(codePoint)) {
//          return true;
//        }
//      }
//      return false;
//    }
//
//    @Override
//    public Iterable<CodePointRange> ranges() {
//      return new IterableCodePointRangeN(subsets);
//    }
//
//    @Override
//    public int numberOfCodePointRanges() {
//      var count = 0;
//      for (Subset subset : subsets) {
//        count += subset.numberOfCodePointRanges();
//      }
//      return count;
//    }
//
//    @Override
//    public int numberOfCodePointsInCodePointRanges() {
//      return 0;
//    }
//
//    private static class IterableCodePointRangeN implements Iterable<CodePointRange> {
//
//      private final Subset[] subsets;
//
//      IterableCodePointRangeN(final Subset[] subsets) {
//        this.subsets = subsets;
//      }
//
//      @Override
//      public java.util.Iterator<CodePointRange> iterator() {
//        return new java.util.Iterator<>() {
//
//          private int currentSubsetIndex = 0;
//          private java.util.Iterator<CodePointRange> currentIterator = subsets.length > 0 ? subsets[0].ranges().iterator() : null;
//
//          @Override
//          public boolean hasNext() {
//            while (currentSubsetIndex < subsets.length) {
//              if (currentIterator.hasNext()) {
//                return true;
//              } else {
//                currentSubsetIndex++;
//                if (currentSubsetIndex < subsets.length) {
//                  currentIterator = subsets[currentSubsetIndex].ranges().iterator();
//                }
//              }
//            }
//            return false;
//          }
//
//          @Override
//          public CodePointRange next() {
//            if (!hasNext()) {
//              throw new java.util.NoSuchElementException();
//            }
//            return currentIterator.next();
//          }
//        };
//      }
//    }
//  }
//
//}
