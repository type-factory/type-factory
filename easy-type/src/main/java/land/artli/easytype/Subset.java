package land.artli.easytype;

import java.util.Collection;

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

  class CodePointRange {

    public final int inclusiveFrom;
    public final int inclusiveTo;

    public CodePointRange(final int inclusiveFrom, final int inclusiveTo) {
      this.inclusiveFrom = inclusiveFrom;
      this.inclusiveTo = inclusiveTo;
    }
  }
}
