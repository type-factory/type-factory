package land.artli.easytype;

import java.util.Collections;
import java.util.List;

public interface Subset {

  boolean isInSubset(final int codePoint);

  default boolean isInSubset(final char ch) {
    return isInSubset((int) ch);
  }

  default boolean isNotInSubset(final int codePoint) {
    return !isInSubset(codePoint);
  }

  default boolean isNotInSubset(final char ch) {
    return !isInSubset((int) ch);
  }

  static CompositeSubset of(final Subset... subsets) {
    if (subsets == null || subsets.length == 0) {
      return Collections::emptyList;
    }
    return () -> List.of(subsets);
  }
}
