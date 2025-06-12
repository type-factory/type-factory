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
package org.typefactory;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.io.Serializable;
import java.util.Collection;
import org.typefactory.impl.Factory;

/**
 * <p>A subset of code-points from the full set of Unicode code-points.</p>
 */
public interface Subset {

  /**
   * <p>Creates a {@link SubsetBuilder} to create {@link Subset} instances.</p>
   *
   * @return A {@link SubsetBuilder} to create {@link Subset} instances.
   */
  static SubsetBuilder builder() {
    return Factory.subsetBuilder();
  }

  /**
   * <p>Creates a {@link SubsetBuilder} initialised with the set of code-points within this {@link Subset}.</p>
   *
   * @return a {@link SubsetBuilder} initialised with the set of code-points within this {@link Subset}..
   */
  default SubsetBuilder toBuilder() {
    return builder().includeSubset(this);
  }

  /**
   * Returns {@code true} if this subset contains no code-point ranges, that is, it is empty. Returns {@code false otherwise}.
   *
   * @return {@code true} if this subset contains no code-point ranges, that is, it is empty. Returns {@code false otherwise}.
   */
  boolean isEmpty();

  /**
   * Returns {@code true} if this subset contains one or more code-point ranges, that is, it is not-empty. Returns {@code false otherwise}.
   *
   * @return {@code true} if this subset contains one or more code-point ranges, that is, it is not-empty. Returns {@code false otherwise}.
   */
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
   * Returns {@code true} if this subset does not contain the specified {@code codePoint}. That is, the code-point is not inclusively within one of
   * code-point ranges in this subset. Returns {@code false otherwise}.
   *
   * @param codePoint the code-point whose lack of presence in this subset is to be tested
   * @return {@code true} if this subset does not contain the specified {@code codePoint}. Returns {@code false otherwise}.
   */
  default boolean doesNotContain(final int codePoint) {
    return !contains(codePoint);
  }

  /**
   * Returns {@code true} if this subset does not contain the specified {@code ch}. That is, the code-point is not inclusively within one of
   * code-point ranges in this subset. Returns {@code false otherwise}.
   *
   * @param ch the character whose lack of presence in this subset is to be tested
   * @return {@code true} if this subset does not contain the specified {@code ch}. Returns {@code false otherwise}.
   */
  default boolean doesNotContain(final char ch) {
    return !contains((int) ch);
  }

  boolean includes(final Category category);

  /**
   * <p>An iterable of code-point ranges in this subset.</p>
   *
   * <p><b>Note:</b> The iterable {@link CodePointRange} instance is reused with each iteration.
   * Use {@link CodePointRange#copy()} if you need to keep references to each of the code-point ranges.</p>
   *
   * @return an iterable of the code-point ranges in this subset. Note that the iterable {@link CodePointRange} instance is reused with each
   * iteration.
   * @see CodePointRange#copy()
   */
  Iterable<CodePointRange> ranges();

  /**
   * Returns the number of code-point ranges in this subset.
   *
   * @return the number of code-point ranges in this subset.
   */
  int numberOfCodePointRanges();

  /**
   * <p>Returns the number of code-points that are contained in all the code-point ranges.</p>
   *
   * <p><b>Note</b>, depending on the implementation of the Subset, there may be extra code-points contained in
   * the subset that are not necessarily contained within the set of code-point-ranges.</p>
   *
   * @return the number of code-points that are contained in all the code-point ranges.
   */
  int numberOfCodePointsInCodePointRanges();

  /**
   * <p>Creates a new <em>immutable</em> subset of code-point ranges from the combined code-point ranges of all the provided subsets.
   * This is a “union” of all the provided subsets.</p>
   *
   * <p>And empty subset will be returned if the provided {@code subsets} argument is {@code null}, an empty array,
   * or all the provided subsets are also null or empty.</p>
   *
   * @param subsets the subsets containing code-point ranges that you wish to combine into a single subset.
   * @return a new subset of code-point ranges from the combined code-point ranges of all the provided subsets.
   */
  static Subset of(final Subset... subsets) {
    return builder()
        .includeSubsets(subsets)
        .build();
  }

  /**
   * <p>Creates a new <em>immutable</em> subset of code-point ranges from the combined code-point ranges of all the provided subsets.
   * This is a “union” of all the provided subsets.</p>
   *
   * <p>And empty subset will be returned if the provided {@code subsets} argument is {@code null}, an empty collection,
   * or all the provided subsets in the collection are also null or empty.</p>
   *
   * @param subsets the collection of subsets containing code-point ranges that you wish to combine into a single subset.
   * @return a new subset of code-point ranges from the combined code-point ranges of all the provided subsets.
   */
  static Subset of(final Collection<Subset> subsets) {
    return builder()
        .includeSubsets(subsets)
        .build();
  }

  interface SubsetBuilder {

    SubsetBuilder includeChar(final char ch);

    SubsetBuilder includeChars(final char... chars);

    SubsetBuilder includeCharRange(final char inclusiveFrom, final char inclusiveTo);

    SubsetBuilder includeCodePoint(final int codePoint);

    SubsetBuilder includeCodePoints(final int... codePoints);

    SubsetBuilder includeCodePointRange(int inclusiveFrom, int inclusiveTo);

    SubsetBuilder includeSubset(final Subset subset);

    SubsetBuilder includeSubsets(final Subset... subsets);

    SubsetBuilder includeSubsets(final Iterable<Subset> subsets);

    SubsetBuilder includeUnicodeCategory(final Category category);

    SubsetBuilder includeUnicodeCategories(final Category... categories);

    SubsetBuilder excludeChar(final char ch);

    SubsetBuilder excludeChars(final char... chars);

    SubsetBuilder excludeCharRange(final char inclusiveFrom, final char inclusiveTo);

    SubsetBuilder excludeCodePoint(final int codePoint);

    SubsetBuilder excludeCodePoints(final int... codePoints);

    SubsetBuilder excludeCodePointRange(int inclusiveFrom, int inclusiveTo);

    SubsetBuilder excludeSubset(final Subset subset);

    SubsetBuilder excludeSubsets(final Subset... subset);

    SubsetBuilder excludeSubsets(final Collection<Subset> subsets);

    SubsetBuilder excludeUnicodeCategory(final Category category);

    SubsetBuilder excludeUnicodeCategory(final Category... categories);

    Subset build();
  }

  /**
   * <p>A mutable tuple for use by the {@link Subset#ranges()} method which returns an {@code Iterable<CodePointRange>}
   * which provides the code-point range via its public {@link #inclusiveFrom} and {@link #inclusiveTo} member variables.</p>
   *
   * <p><b>Note:</b> a single {@link CodePointRange} instance is reused with each iteration of the {@code Iterable<CodePointRange>}.
   * Use {@link CodePointRange#copy()} if you need to keep separate instance references to each of the code-point ranges.</p>
   */
  final class CodePointRange implements Comparable<CodePointRange>, Serializable {

    /**
     * The inclusive start code-point of the code-point range. This value will be modified with each
     * iteration of the {@code Iterable<CodePointRange>}.
     */
    @SuppressWarnings("java:S1104") // 'Class variable fields should not have public accessibility' - I want this to be accessible and mutable
    public int inclusiveFrom;

    /**
     * The inclusive end code-point of the code-point range. This value will be modified with each
     * iteration of the {@code Iterable<CodePointRange>}.
     */
    @SuppressWarnings("java:S1104") // 'Class variable fields should not have public accessibility' - I want this to be accessible and mutable
    public int inclusiveTo;

    public CodePointRange(final char inclusiveFrom, final char inclusiveTo) {
      this((int) inclusiveFrom, (int) inclusiveTo);
    }

    public CodePointRange(final int inclusiveFrom, final int inclusiveTo) {
      this.inclusiveFrom = min(inclusiveFrom, inclusiveTo);
      this.inclusiveTo = max(inclusiveFrom, inclusiveTo);
    }

    public CodePointRange copy() {
      return new CodePointRange(inclusiveFrom, inclusiveTo);
    }

    /**
     * Returns {@code true} if this code-point range inclusively contains the specified {@code codePoint}.
     *
     * @param codePoint the code-point that you wish to confirm is inclusively within the code-point range.
     * @return {@code true} if this code-point range inclusively contains the specified {@code codePoint}.
     */
    public boolean contains(final int codePoint) {
      return inclusiveFrom <= codePoint && codePoint <= inclusiveTo;
    }

    /**
     * Returns {@code true} if the specified {@code codePoint} is outside the code-point range.
     * This is a convenience method and returns:
     * <pre>
     *   !contains(int)
     * </pre>
     *
     * @param codePoint the code-point that you wish to confirm is outside the code-point range.
     * @return {@code true} if the specified {@code codePoint} is outside the code-point range.
     */
    public boolean doesNotContain(final int codePoint) {
      return !contains(codePoint);
    }

    /**
     * A {@code CodePointRange} instances are equal if and only if both their inclusive-from and inclusive-to code-points are equal.
     *
     * @param o the other {@code CodePointRange} instance to compare this instance to.
     * @return {@code true} if this {@code CodePointRange} instance and the other instance {@code o} have both their inclusive-from and inclusive-to
     * code-points equal.
     */
    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o == null) {
        return false;
      }
      if (this.getClass() != o.getClass()) {
        return false;
      }
      final CodePointRange other = (CodePointRange) o;
      return inclusiveFrom == other.inclusiveFrom && inclusiveTo == other.inclusiveTo;
    }

    @Override
    public int hashCode() {
      return inclusiveFrom * 109 + inclusiveTo;
    }

    /**
     * Null-safe comparison of this {@code CodePointRange} instance to another first comparing the {@code inclusiveFrom} value and then comparing the
     * {@code inclusiveTo} value.
     *
     * @param o the other {@code CodePointRange} instance to compare to. May be {@code null}.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object when first
     * comparing the {@code inclusiveFrom} value and then comparing the {@code inclusiveTo} value. Returns {@code 1} if the other object is
     * {@code null}.
     */
    public int compareTo(final CodePointRange o) {
      if (o == null) {
        return 1;
      }
      int result = inclusiveFrom - o.inclusiveFrom;
      if (result != 0) {
        return result;
      }
      return inclusiveTo - o.inclusiveTo;
    }

    /**
     * Returns the code-point range as a hexadecimal range in the format: {@code 0x00000000..0x00000000}
     *
     * @return the code-point range as a hexadecimal range in the format: {@code 0x00000000..0x00000000}
     */
    public String toString() {
      return String.format("%c..%c (0x%08x..0x%08x)", inclusiveFrom, inclusiveTo, inclusiveFrom, inclusiveTo);
    }
  }
}
