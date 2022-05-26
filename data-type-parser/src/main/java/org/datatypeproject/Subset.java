package org.datatypeproject;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

/**
 * <p>A subset of code-points from the full set of Unicode code-points.</p>
 *
 *
 */
public interface Subset {

  /**
   * The code-point ranges in this subset.
   *
   * @return a collection of the code-point ranges in this subset.
   */
  Collection<CodePointRange> ranges();

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
   * Returns {@code true} if this subset contains the specified {@code codePoint}. That is, the code-point falls inclusively within
   * one of code-point ranges in this subset. Returns {@code false otherwise}.
   *
   * @param codePoint the code-point whose presence in this subset is to be tested
   * @return {@code true} if this subset contains the specified {@code codePoint}. Returns {@code false otherwise}.
   */
  boolean contains(final int codePoint);

  /**
   * Returns {@code true} if this subset contains the specified {@code ch}. That is, the code-point falls inclusively within
   * one of code-point ranges in this subset. Returns {@code false otherwise}.
   *
   * @param ch the character whose presence in this subset is to be tested
   * @return {@code true} if this subset contains the specified {@code ch}. Returns {@code false otherwise}.
   */
  default boolean contains(final char ch) {
    return contains((int) ch);
  }

  /**
   * Returns {@code true} if this subset does not contain the specified {@code codePoint}. That is, the code-point is not inclusively within
   * one of code-point ranges in this subset. Returns {@code false otherwise}.
   *
   * @param codePoint the code-point whose lack of presence in this subset is to be tested
   * @return {@code true} if this subset does not contain the specified {@code codePoint}. Returns {@code false otherwise}.
   */
  default boolean doesNotContain(final int codePoint) {
    return !contains(codePoint);
  }

  /**
   * Returns {@code true} if this subset does not contain the specified {@code ch}. That is, the code-point is not inclusively within
   * one of code-point ranges in this subset. Returns {@code false otherwise}.
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
    return new RangedSubsetBuilderImpl();
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
        .includeSubset(subsets)
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
        .includeSubset(subsets)
        .build();
  }

  /**
   * An immutable code-point range – a tuple of the inclusive-from and inclusive-to code-points.
   */
  final class CodePointRange implements Comparable<CodePointRange>, Serializable {

    @Serial
    private static final long serialVersionUID = -7777682911722362584L;

    /**
     * The inclusive-from code-point of the range.
     */
    public final int inclusiveFrom;

    /**
     * The inclusive-to code-point of the range.
     */
    public final int inclusiveTo;

    /**
     * Create an immutable code-point range.
     *
     * @param inclusiveFrom the inclusive-from code-point value of the range.
     * @param inclusiveTo   the inclusive-to code-point value of the range.
     */
    public CodePointRange(final int inclusiveFrom, final int inclusiveTo) {
      this.inclusiveFrom = inclusiveFrom;
      this.inclusiveTo = inclusiveTo;
    }

    /**
     * <p>Returns the inclusive-from code-point of the range.</p>
     *
     * <p>The {@link #inclusiveFrom} member variable can be accessed directly – it is immutable, {@code public final}. This getter-method
     * has been provided for frameworks that expect this format for properties.</p>
     *
     * @return the inclusive-from code-point of the range.
     */
    public int getInclusiveFrom() {
      return inclusiveFrom;
    }

    /**
     * <p>Returns the inclusive-to code-point of the range.</p>
     *
     * <p>The {@link #inclusiveTo} member variable can be accessed directly – it is immutable, {@code public final}. This getter-method
     * has been provided for frameworks that expect this format for properties.</p>
     *
     * @return the inclusive-to code-point of the range.
     */
    public int getInclusiveTo() {
      return inclusiveTo;
    }

    /**
     * A {@code CodePointRange} instances are equal if and only if both their inclusive-from and inclusive-to code-points are equal.
     *
     * @param o the other {@code CodePointRange} instance to compare this instance to.
     * @return {@code true} if this {@code CodePointRange} instance and the other instance {@code o} have both their inclusive-from and inclusive-to
     * code-points are equal.
     */
    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof CodePointRange)) {
        return false;
      }
      final CodePointRange that = (CodePointRange) o;
      return inclusiveFrom == that.inclusiveFrom && inclusiveTo == that.inclusiveTo;
    }

    @Override
    public int hashCode() {
      return Objects.hash(inclusiveFrom, inclusiveTo);
    }

    /**
     * Null-safe comparison of this {@code CodePointRange} instance to another first comparing the {@code inclusiveFrom} value and then comparing the
     * {@code inclusiveTo} value.
     *
     * @param o the other {@code CodePointRange} instance to compare to. May be {@code null}.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object when first
     * comparing the {@code inclusiveFrom} value and then comparing the {@code inclusiveTo} value. Returns {@code 1} if the other object is {@code
     * null}.
     */
    @Override
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
    @Override
    public String toString() {
      return String.format("0x%08x..0x%08x", inclusiveFrom, inclusiveTo);
    }
  }
}
