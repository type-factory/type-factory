package land.artli.easytype;

import java.util.Collection;

interface CompositeSubset extends Subset {

  Collection<Subset> getSubsets();

  default boolean isInSubset(final int codePoint) {
    for (Subset subset : getSubsets()) {
      if (subset.isInSubset(codePoint)) {
        return true;
      }
    }
    return false;
  }
}
