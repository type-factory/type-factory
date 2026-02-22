package org.typefactory.assertions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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

  private static final String U_08X = "U+%08X";
  private static final String U_06X = "U+%06X";
  private static final String U_04X = "U+%04X";

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
          codePointToString(expectedCharacter));
    }
    return myself;
  }

  public SELF containsAllCharacters(final char... expectedCharacters) {
    isNotEmpty();
    final var subsetContainsResult = new SubsetContainsResult(actual, expectedCharacters);
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
          codePointToString(expectedCodePoint));
    }
    return myself;
  }

  public SELF containsAllCodePoints(final int... expectedCodePoints) {
    isNotEmpty();
    final var subsetContainsResult = new SubsetContainsResult(actual, expectedCodePoints);
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
    final Set<Category> actualSubsetCategories = getActualSubsetCategories();
    if (!actualSubsetCategories.contains(expectedCategory)) {
      throw failure("%nExpected actual Subset:  %nto contain category:  %s%nbut no such category was found in the Subset.",
          expectedCategory);
    }
    return myself;
  }

  public SELF containsExactlyCategories(final Category... expectedCategories) {
    isNotNull();
    final Set<Category> actualSubsetCategories = getActualSubsetCategories();
    final Set<Category> contained = new HashSet<>();
    final Set<Category> notContained = new HashSet<>();
    for (Category expectedCategory : expectedCategories) {
      if (actualSubsetCategories.contains(expectedCategory)) {
        contained.add(expectedCategory);
      } else {
        notContained.add(expectedCategory);
      }
    }
    if (!notContained.isEmpty()) {
      throw failureWithActualExpected(
          actualSubsetCategories, List.of(expectedCategories),
          "Subset did not contain exactly the expected categories\nContained only: %s\nDid not contain %s",
          contained.stream().map(Enum::name).collect(Collectors.joining(", ")),
          notContained.stream().map(Enum::name).collect(Collectors.joining(", ")));
    }
    return myself;
  }

  private Set<Category> getActualSubsetCategories() {
    final HashSet<Category> result = new HashSet<>();
    if (actual instanceof SubsetWithCategories subsetWithCategories) {
      final long subsetCategoryBitFlags = subsetWithCategories.unicodeCategoryBitFlags();
      for (Category category : Category.values()) {
        if ((subsetCategoryBitFlags & category.bitMask) == category.bitMask) {
          result.add(category);
        }
      }
    }
    return result;
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

  protected static String codePointToString(final int codePoint) {
    if (!Character.isDefined(codePoint)) {
      if (codePoint > 0xFFFFFFL) {
        return String.format(U_08X, codePoint);
      } else if (codePoint > 0xFFFF) {
        return String.format(U_06X, codePoint);
      } else {
        return String.format(U_04X, codePoint);
      }
    }

    if (Category.codePointIsInOneOfTheCategories(codePoint, Category.getCategoryBitFlags(
        Category.CONTROL,
        Category.FORMAT,
        Category.SPACE_SEPARATOR,
        Category.LINE_SEPARATOR,
        Category.PARAGRAPH_SEPARATOR))) {
      return codePoint > 0xFFFF
          ? String.format(U_06X, codePoint)
          : String.format(U_04X, codePoint);
    }
    if (codePoint > 0xFFFF) {
      return Character.toString(codePoint);
    } else {
      if (Character.isHighSurrogate((char) codePoint) || Character.isLowSurrogate((char) codePoint)) {
        return String.format(U_04X, codePoint);
      } else {
        return Character.toString(codePoint);
      }
    }
  }

  protected static final class SubsetContainsResult {

    final String expectedCodePoints;
    final String foundInSubset;
    final String notFoundInSubset;

    SubsetContainsResult(final Subset actual, char... expectedCharacters) {
      this(actual, charArrayToCodePointArray(expectedCharacters));
    }

    SubsetContainsResult(final Subset actual, int... expectedCodepoints) {
      final var expectedStringBuilder = new StringBuilder();
      final var foundStringBuilder = new StringBuilder();
      final var notFoundStringBuilder = new StringBuilder();
      if (expectedCodepoints != null) {
        for (int expectedCodepoint : expectedCodepoints) {
          final var codePointAsString = codePointToString(expectedCodepoint);
          expectedStringBuilder.append(codePointAsString).append(",");
          if (actual.contains(expectedCodepoint)) {
            foundStringBuilder.append(codePointAsString).append(",");
          } else {
            notFoundStringBuilder.append(codePointAsString).append(",");
          }
        }
      }
      if (!expectedStringBuilder.isEmpty()) {
        expectedStringBuilder.setLength(expectedStringBuilder.length() - 1);
      }
      if (!foundStringBuilder.isEmpty()) {
        foundStringBuilder.setLength(foundStringBuilder.length() - 1);
      }
      if (!notFoundStringBuilder.isEmpty()) {
        notFoundStringBuilder.setLength(notFoundStringBuilder.length() - 1);
      }
      this.expectedCodePoints = expectedStringBuilder.toString();
      this.foundInSubset = foundStringBuilder.toString();
      this.notFoundInSubset = notFoundStringBuilder.toString();
    }
  }

}
