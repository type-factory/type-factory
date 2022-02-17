package land.artli.easytype;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class CompositeSubsetImpl implements CompositeSubset {

  private final List<Subset> subsets;

  CompositeSubsetImpl(final Subset... subsets) {
    this((subsets == null || subsets.length == 0) ? null : List.of(subsets));
  }

  CompositeSubsetImpl(final Collection<Subset> subsets) {
    if (subsets == null || subsets.isEmpty()) {
      this.subsets = null;
      return;
    }
    ArrayList<Subset> list = null;
    final RangedSubsetBuilder rangedSubsetBuilder = RangedSubset.builder();
    for (Subset subset : subsets) {
      if (subset instanceof RangedSubset rangedSubset) {
        rangedSubsetBuilder.addRangedSubset(rangedSubset);
      } else {
        if (list == null) {
          list = new ArrayList<>();
        }
        list.add(subset);
      }
    }
    final RangedSubset rangedSubset = rangedSubsetBuilder.build();
    if (list == null || list.isEmpty()) {
      if (rangedSubset.isEmpty()) {
        this.subsets = null;
      } else {
        this.subsets = List.of(rangedSubset);
      }
    } else {
      if (rangedSubset.isNotEmpty()) {
        list.add(0, rangedSubsetBuilder.build());
      }
      this.subsets = Collections.unmodifiableList(list);
    }
  }

  @Override
  public List<Subset> getSubsets() {
    return subsets;
  }
}
