package org.typefactory.impl;

import java.util.Comparator;

public class LongComparator implements Comparator<Long> {

  public static final LongComparator LONG_COMPARATOR = new LongComparator();

  @Override
  public int compare(final Long o1, final Long o2) {
    return Comparator.<Long>nullsFirst(Comparator.naturalOrder()).compare(o1, o2);
  }
}
