package org.datatypeproject;

import java.util.Collection;
import org.datatypeproject.impl.Factory;

/**
 * <p>A subset of code-points from the full set of Unicode code-points.</p>
 */
public interface Subset {

  /**
   * <p>An iterable of code-point ranges in this subset.</p>
   *
   * <p><b>Note:</b> The iterable {@link CodePointRange} instance is reused with each iteration.
   * Use {@link CodePointRange#copy()} if you need to keep references to each of the code-point ranges.</p>
   *
   * @return an iterable of the code-point ranges in this subset. Note that the iterable {@link CodePointRange} instance is reused with each iteration.
   * @see CodePointRange#copy()
   */
  Iterable<CodePointRange> ranges();

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

  /**
   * <p>Creates a {@link SubsetBuilder} initialised with the set of code-points within this {@link Subset}.</p>
   *
   * @return a {@link SubsetBuilder} initialised with the set of code-points within this {@link Subset}..
   */
  default SubsetBuilder toBuilder() {
    return builder().includeSubset(this);
  }

  /**
   * <p>Creates a {@link SubsetBuilder} to create {@link Subset} instances.</p>
   *
   * @return A {@link SubsetBuilder} to create {@link Subset} instances.
   */
  static SubsetBuilder builder() {
    return Factory.subsetBuilder();
  }

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

    SubsetBuilder includeSubsets(final Collection<Subset> subsets);

    SubsetBuilder excludeChar(final char ch);

    SubsetBuilder excludeChars(final char... chars);

    SubsetBuilder excludeCharRange(final char inclusiveFrom, final char inclusiveTo);

    SubsetBuilder excludeCodePoint(final int codePoint);

    SubsetBuilder excludeCodePoints(final int... codePoints);

    SubsetBuilder excludeCodePointRange(int inclusiveFrom, int inclusiveTo);

    SubsetBuilder excludeSubset(final Subset subset);

    SubsetBuilder excludeSubsets(final Subset... subset);

    SubsetBuilder excludeSubsets(final Collection<Subset> subsets);

    Subset build();
  }
}
