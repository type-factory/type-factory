package land.artli.easytype;

import java.util.Collection;
import java.util.Objects;

public interface Subset {

  Collection<CodePointRange> ranges();

  boolean isEmpty();

  default boolean isNotEmpty() {
    return !isEmpty();
  }

  boolean contains(final int codePoint);

  default boolean contains(final char ch) {
    return contains((int) ch);
  }

  default boolean doesNotContain(final int codePoint) {
    return !contains(codePoint);
  }

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

  static Subset of(final Subset... subsets) {
    return builder()
        .includeSubset(subsets)
        .build();
  }

  static Subset of(final Collection<Subset> subsets) {
    return builder()
        .includeSubset(subsets)
        .build();
  }

  /**
   * An immutable code-point range – a tuple of the inclusive-from and inclusive-to code-points.
   */
  final class CodePointRange implements Comparable<CodePointRange> {

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
