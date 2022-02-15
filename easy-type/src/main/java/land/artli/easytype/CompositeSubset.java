package land.artli.easytype;

import java.util.Collection;

interface CompositeSubset extends Subset {

  Collection<Subset> getSubsets();

  default boolean contains(final int codePoint) {
    for (Subset subset : getSubsets()) {
      if (subset.contains(codePoint)) {
        return true;
      }
    }
    return false;
  }

  @Override
  default boolean isEmpty() {
    for (Subset subset : getSubsets()) {
      if (subset.isNotEmpty()) {
        return false;
      }
    }
    return true;
  }
}
