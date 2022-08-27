package org.datatypeproject;

public interface SubsetWithCategories extends Subset {

  /**
   * Each bit of the following value corresponds to a {@link Category} identified by the {@link Category#bitMask};
   */
  long unicodeCategoryBitFlags();

  int numberOfUnicodeCategories();

}
