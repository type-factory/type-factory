package org.typefactory.assertions;

import static java.lang.Math.max;
import static java.util.Comparator.comparing;
import static java.util.function.Predicate.not;
import static org.typefactory.assertions.AssertionUtils.codePointToStringOrHexCode;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.assertj.core.api.AbstractObjectAssert;
import org.typefactory.Category;
import org.typefactory.Subset;
import org.typefactory.impl.SubsetWithCategories;

/**
 * Abstract base class for {@link Subset} specific assertions.
 */
@SuppressWarnings("java:S119") // Generic parameter naming
public abstract class AbstractSubsetAssert<
    SELF extends AbstractSubsetAssert<SELF, ACTUAL>,
    ACTUAL extends Subset>
    extends AbstractObjectAssert<SELF, ACTUAL> {

  /**
   * Creates a new <code>{@link AbstractSubsetAssert}</code> to make assertions on actual Subset.
   *
   * @param actual the Subset we want to make assertions on.
   */
  protected AbstractSubsetAssert(ACTUAL actual, Class<SELF> selfType) {
    super(actual, selfType);
  }

  protected String classNameOfActual() {
    return actual == null ? "<? extends ShortType>" : actual.getClass().getSimpleName();
  }

  protected boolean actualIsNull() {
    return actual == null;
  }

  /**
   * Verifies that the actual Subset is empty.
   *
   * @return this assertion object.
   * @throws AssertionError - if the actual Subset is not empty.
   */
  public SELF isEmpty() {
    isNotNull();
    if (actual.isNotEmpty()) {
      throw failure("%nExpecting that actual Subset is empty but it is not.");
    }
    return myself;
  }

  /**
   * Verifies that the actual Subset is not empty.
   *
   * @return this assertion object.
   * @throws AssertionError - if the actual Subset is empty.
   */
  public SELF isNotEmpty() {
    isNotNull();
    if (actual.isEmpty()) {
      throw failure("%nExpecting that actual Subset is not empty but it is.");
    }
    return myself;
  }


  public SELF containsCharacter(final char expectedCharacter) {
    isNotEmpty();
    if (!actual.contains(expectedCharacter)) {
      throw failure("%nExpected actual Subset:  %nto contain character:  %s%nbut no such character was found in the Subset.",
          codePointToStringOrHexCode(expectedCharacter));
    }
    return myself;
  }

  public SELF containsAllCharacters(final char... expectedCharacters) {
    isNotEmpty();
    final var subsetContainsResult = new SubsetContainsCodePointResult(actual, expectedCharacters);
    if (!subsetContainsResult.notFoundInSubset.isEmpty()) {
      if (subsetContainsResult.foundInSubset.isEmpty()) {
        throw failure(
            "%nExpected actual Subset:  %nto contain all characters:  [%s]%nmissing expected characters:  [%s]",
            subsetContainsResult.expectedCodePoints,
            subsetContainsResult.notFoundInSubset);
      } else {
        throw failure(
            "%nExpected actual Subset:  %nto contain all characters:  [%s]%ncontained expected characters:  [%s]%nmissing expected characters:  [%s]",
            subsetContainsResult.expectedCodePoints,
            subsetContainsResult.foundInSubset,
            subsetContainsResult.notFoundInSubset);
      }
    }
    return myself;
  }

  public SELF containsCodePoint(final int expectedCodePoint) {
    isNotEmpty();
    if (!actual.contains(expectedCodePoint)) {
      throw failure("%nExpected actual Subset:  %nto contain code-point:  %s%nbut no such code-point was found in the Subset.",
          codePointToStringOrHexCode(expectedCodePoint));
    }
    return myself;
  }

  public SELF containsAllCodePoints(final int... expectedCodePoints) {
    isNotEmpty();
    final var subsetContainsResult = new SubsetContainsCodePointResult(actual, expectedCodePoints);
    if (!subsetContainsResult.notFoundInSubset.isEmpty()) {
      if (subsetContainsResult.foundInSubset.isEmpty()) {
        throw failure(
            "%nExpected actual Subset:  %nto contain all code-points:  [%s]%nmissing expected code-points:  [%s]",
            subsetContainsResult.expectedCodePoints,
            subsetContainsResult.notFoundInSubset);
      } else {
        throw failure(
            "%nExpected actual Subset:  %nto contain all code-points:  [%s]%ncontained expected code-points:  [%s]%nmissing expected code-points:  [%s]",
            subsetContainsResult.expectedCodePoints,
            subsetContainsResult.foundInSubset,
            subsetContainsResult.notFoundInSubset);
      }
    }
    return myself;
  }

  public SELF containsCategory(final Category expectedCategory) {
    isNotNull();
    final var subsetContainsResult = new SubsetContainsCategoryResult(actual, List.of(expectedCategory));
    if (!subsetContainsResult.expectedAndNotContained.isBlank()) {
      throw failure("%nExpected actual Subset:  %nto contain category:  %s%nbut no such category was found in the Subset.",
          expectedCategory);
    }
    return myself;
  }

  public SELF containsCategories(final Category... expectedCategories) {
    isNotNull();
    final var subsetContainsResult = new SubsetContainsCategoryResult(actual, List.of(expectedCategories));
    if (!subsetContainsResult.expectedAndNotContained.isBlank()) {
      final var message = Stream.of(
              String.format("%nExpected actual Subset:  "),
              subsetContainsResult.expectedCategories.isBlank()
                  ? ""
                  : String.format("%nto contain the categories:  [%s]", subsetContainsResult.expectedCategories),
              subsetContainsResult.expandedExpectedCategories.isBlank()
                  ? ""
                  : String.format("%nwhich expands to the categories:  [%s]", subsetContainsResult.expandedExpectedCategories),
              subsetContainsResult.expectedAndContained.isBlank()
                  ? ""
                  : String.format("%ncontained expected categories:  [%s]", subsetContainsResult.expectedAndContained),
              subsetContainsResult.expectedAndNotContained.isBlank()
                  ? ""
                  : String.format("%nmissing expected categories:  [%s]", subsetContainsResult.expectedAndNotContained))
          .filter(not(String::isBlank))
          .collect(Collectors.joining(""));

      throw failure(message);
    }
    return myself;
  }

  public SELF containsExactlyCategories(final Category... expectedCategories) {
    isNotNull();
    final var subsetContainsResult = new SubsetContainsCategoryResult(actual, List.of(expectedCategories));
    if (!subsetContainsResult.expectedAndNotContained.isBlank() || !subsetContainsResult.containedAndNotExpected.isBlank()) {
      final var message = Stream.of(
              String.format("%nExpected actual Subset:  "),
              subsetContainsResult.expectedCategories.isBlank()
                  ? ""
                  : String.format("%nto contain exactly the categories:  [%s]", subsetContainsResult.expectedCategories),
              subsetContainsResult.expandedExpectedCategories.isBlank()
                  ? ""
                  : String.format("%nwhich expands to the categories:  [%s]", subsetContainsResult.expandedExpectedCategories),
              subsetContainsResult.expectedAndContained.isBlank()
                  ? ""
                  : String.format("%ncontained expected categories:  [%s]", subsetContainsResult.expectedAndContained),
              subsetContainsResult.expectedAndNotContained.isBlank()
                  ? ""
                  : String.format("%nmissing expected categories:  [%s]", subsetContainsResult.expectedAndNotContained),
              subsetContainsResult.containedAndNotExpected.isBlank()
                  ? ""
                  : String.format("%ncontained categories that were not expected:  [%s]", subsetContainsResult.containedAndNotExpected))
          .filter(not(String::isBlank))
          .collect(Collectors.joining(""));

      throw failure(message);
    }
    return myself;
  }

  protected static int[] charArrayToCodePointArray(final char[] characterArray) {
    if (characterArray == null) {
      return new int[0];
    }
    final var result = new int[characterArray.length];
    for (int i = 0; i < result.length; i++) {
      result[i] = characterArray[i];
    }
    return result;
  }

  protected static final class SubsetContainsCodePointResult {

    final String expectedCodePoints;
    final String foundInSubset;
    final String notFoundInSubset;

    SubsetContainsCodePointResult(final Subset actual, char... expectedCharacters) {
      this(actual, charArrayToCodePointArray(expectedCharacters));
    }

    SubsetContainsCodePointResult(final Subset actual, int... expectedCodepoints) {
      final var expectedStringBuilder = new StringBuilder();
      final var foundStringBuilder = new StringBuilder();
      final var notFoundStringBuilder = new StringBuilder();
      if (expectedCodepoints != null) {
        for (int expectedCodepoint : expectedCodepoints) {
          final var codePointAsString = codePointToStringOrHexCode(expectedCodepoint);
          expectedStringBuilder.append(codePointAsString).append(",");
          if (actual.contains(expectedCodepoint)) {
            foundStringBuilder.append(codePointAsString).append(",");
          } else {
            notFoundStringBuilder.append(codePointAsString).append(",");
          }
        }
      }

      expectedStringBuilder.setLength(max(0, expectedStringBuilder.length() - 1));
      foundStringBuilder.setLength(max(0, foundStringBuilder.length() - 1));
      notFoundStringBuilder.setLength(max(0, notFoundStringBuilder.length() - 1));

      this.expectedCodePoints = expectedStringBuilder.toString();
      this.foundInSubset = foundStringBuilder.toString();
      this.notFoundInSubset = notFoundStringBuilder.toString();
    }
  }

  protected static final class SubsetContainsCategoryResult {

    final String expectedCategories;
    final String expandedExpectedCategories;
    final String expectedAndContained;
    final String expectedAndNotContained;
    final String containedAndNotExpected;

    SubsetContainsCategoryResult(final Subset actual, final List<Category> expectedCategories) {

      final var actualSubsetCategories = getActualSubsetCategories(actual);
      final var expectedCategoriesExpanded = getExpandedSubsetCategories(expectedCategories);

      final var expandedExpectedCategoriesStringBuilder = new StringBuilder();
      final var expectedAndContainedStringBuilder = new StringBuilder();
      final var expectedAndNotContainedStringBuilder = new StringBuilder();
      final var containedAndNotExpectedStringBuilder = new StringBuilder();

      for (Category expectedCategory : expectedCategoriesExpanded) {
        expandedExpectedCategoriesStringBuilder.append(expectedCategory).append(", ");
        if (actualSubsetCategories.contains(expectedCategory)) {
          expectedAndContainedStringBuilder.append(expectedCategory).append(", ");
        } else {
          expectedAndNotContainedStringBuilder.append(expectedCategory).append(", ");
        }
      }
      for (Category actualCategory : actualSubsetCategories) {
        if (!expectedCategoriesExpanded.contains(actualCategory)) {
          containedAndNotExpectedStringBuilder.append(actualCategory).append(", ");
        }
      }

      final var expectedAndExpandedExpectedCategoriesAreTheSame =
          expectedCategories.size() == expectedCategoriesExpanded.size()
          && new HashSet<>(expectedCategories).containsAll(expectedCategoriesExpanded);

      expandedExpectedCategoriesStringBuilder.setLength(max(0, expandedExpectedCategoriesStringBuilder.length() - 2));
      expectedAndContainedStringBuilder.setLength(max(0, expectedAndContainedStringBuilder.length() - 2));
      expectedAndNotContainedStringBuilder.setLength(max(0, expectedAndNotContainedStringBuilder.length() - 2));
      containedAndNotExpectedStringBuilder.setLength(max(0, containedAndNotExpectedStringBuilder.length() - 2));

      this.expectedCategories = expectedCategories.stream().map(Category::name).collect(Collectors.joining(", "));
      this.expandedExpectedCategories = expectedAndExpandedExpectedCategoriesAreTheSame ? "" : expandedExpectedCategoriesStringBuilder.toString();
      this.containedAndNotExpected = containedAndNotExpectedStringBuilder.toString();
      this.expectedAndContained = expectedAndContainedStringBuilder.toString();
      this.expectedAndNotContained = expectedAndNotContainedStringBuilder.toString();
    }

    private static SortedSet<Category> getActualSubsetCategories(final Subset subset) {
      final var result = new TreeSet<>(comparing(Category::ordinal));
      if (subset instanceof SubsetWithCategories subsetWithCategories) {
        final long subsetCategoryBitFlags = subsetWithCategories.unicodeCategoryBitFlags();
        for (Category category : Category.values()) {
          if (((subsetCategoryBitFlags & category.bitMask) == category.bitMask) && !category.isCompositeCategory()) {
            result.add(category);
          }
        }
      }
      return result;
    }

    private static SortedSet<Category> getExpandedSubsetCategories(final Collection<Category> fromCategories) {
      final var toCategories = new TreeSet<>(comparing(Category::ordinal));
      for (Category fromCategory : fromCategories) {
        for (Category category : Category.values()) {
          if (((fromCategory.bitMask & category.bitMask) == category.bitMask) && !category.isCompositeCategory()) {
            toCategories.add(category);
          }
        }
      }
      return toCategories;
    }
  }

}
