package land.artli.easytype;

import java.util.Collection;
import land.artli.easytype.RangedSubsetImpl.RangedSubsetBuilderImpl;

public interface Subset {

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
   * <p>Creates a {@link SubsetBuilder} to create {@link Subset} instances.</p>
   *
   * <p>Package-scoped because we should only be invoking this as an implementation detail.</p>
   *
   * @return A {@link RangedSubsetBuilder} to create {@link RangedSubset} instances.
   */
  static SubsetBuilder builder() {
    return new RangedSubsetBuilderImpl();
  }

  static Subset of(final Subset... subsets) {
    return new CompositeSubsetImpl(subsets);
  }

  static Subset of(final Collection<Subset> subsets) {
    return new CompositeSubsetImpl(subsets);
  }
}