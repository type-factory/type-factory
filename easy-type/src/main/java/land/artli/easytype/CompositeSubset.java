package land.artli.easytype;

import java.util.Collection;

interface CompositeSubset extends Subset {

  Collection<Subset> getSubsets();

  @Override
  default boolean contains(final int codePoint) {
    if (getSubsets() == null || getSubsets().isEmpty()) {
      return false;
    }
    for (Subset subset : getSubsets()) {
      if (subset.contains(codePoint)) {
        return true;
      }
    }
    return false;
  }

  @Override
  default boolean isEmpty() {
    if (getSubsets() == null || getSubsets().isEmpty()) {
      return true;
    }
    for (Subset subset : getSubsets()) {
      if (subset.isNotEmpty()) {
        return false;
      }
    }
    return true;
  }
}
