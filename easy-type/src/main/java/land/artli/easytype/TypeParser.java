package land.artli.easytype;

import static java.lang.Character.isWhitespace;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import land.artli.easytype.TypeParser.CodePointConversions.CodePointConversionsBuilder;
import land.artli.easytype.TypeParser.RangedSubset.RangedSubsetBuilder;

public class TypeParser {

  private static final char[] EMPTY_CHAR_ARRAY = new char[0];
  private static final int[] EMPTY_INT_ARRAY = new int[0];
  private static final long[] EMPTY_LONG_ARRAY = new long[0];


  private final Class<? extends CharType<?>> targetClass;
  private final String errorMessage;
  private final TargetCase targetCase;
  private final WhiteSpace whiteSpace;
  private int[] convertWhiteSpaceToCodePoints;
  private final NullHandling nullHandling;
  private final int minNumberOfCodePoints;
  private final int maxNumberOfCodePoints;
  private final long acceptedCategories; // bit values, currently int be enough but new categories have been defined.
  private final Subset acceptedCodePoints;
  private final CodePointConversions codePointConversions;

  private TypeParser(
      final Class<? extends CharType<?>> targetClass,
      final String errorMessage,
      final TargetCase targetCase,
      final WhiteSpace whiteSpace,
      final int[] convertWhiteSpaceToCodePoints,
      final NullHandling nullHandling,
      final int minNumberOfCodePoints, final int maxNumberOfCodePoints,
      final long acceptedCategories,
      final Subset acceptedCodePoints,
      final CodePointConversions codePointConversions) {
    this.targetClass = targetClass;
    this.errorMessage = errorMessage;
    this.targetCase = targetCase;
    this.whiteSpace = whiteSpace;
    this.convertWhiteSpaceToCodePoints = convertWhiteSpaceToCodePoints;
    this.nullHandling = nullHandling;
    this.minNumberOfCodePoints = minNumberOfCodePoints;
    this.maxNumberOfCodePoints = maxNumberOfCodePoints;
    this.acceptedCategories = acceptedCategories;
    this.acceptedCodePoints = acceptedCodePoints;
    this.codePointConversions = codePointConversions;
  }

  public static TypeParserBuilder builder(final Class<? extends CharType<?>> targetClass) {
    return new TypeParserBuilder(targetClass);
  }

  public <T extends CharType<?>> T parse(final CharSequence value, Function<String, T> constructorOrFactoryMethod) throws ParseException {
    return constructorOrFactoryMethod.apply(parse(value));
  }

  public String parse(final CharSequence value) {
    if (value == null) {
      return switch (nullHandling) {
        case PRESERVE_NULL_AND_EMPTY, CONVERT_EMPTY_TO_NULL -> null;
        case CONVERT_NULL_TO_EMPTY -> "";
      };
    }
    final int length = value.length();
    final int endIndex = endIndexIgnoringTrailingWhitespace(value);
    final int startIndex = startIndexIgnoringLeadingWhitespace(value, endIndex);
    int[] result = new int[Math.min(length, codePointConversions.maxToCodePointsLength)];
    if ((endIndex - startIndex) == 0) {
      return switch (nullHandling) {
        case PRESERVE_NULL_AND_EMPTY, CONVERT_NULL_TO_EMPTY -> "";
        case CONVERT_EMPTY_TO_NULL -> null;
      };
    }
    int i = startIndex;
    int k = 0;
    char ch;
    int codePoint;
    int[] toCodePoints;
    final int[] reusableSingleCodePointArray = new int[1];
    CodePointConversion codePointConversion;

    while (i < length) {
      ch = value.charAt(i);
      if (Character.isSurrogate(ch)) {
        if (++i < length) {
          codePoint = Character.toCodePoint(ch, value.charAt(++i));
        } else {
          throw InvalidTypeValueException.forInvalidCodePoint(errorMessage, targetClass, value, i, ch);
        }
      } else {
        codePoint = ch;
      }

      if (Character.isWhitespace(codePoint)) {
        switch (whiteSpace) {
          case FORBID_WHITESPACE:
            throw InvalidTypeValueException.forInvalidCodePoint(errorMessage, targetClass, value, i, ch);
          case PRESERVE_WHITESPACE:
            result = appendCodePoint(result, k++, codePoint);
            while (++i < length && Character.isWhitespace(codePoint = value.charAt(i))) {
              result = appendCodePoint(result, k++, codePoint);
            }
            break;
          case PRESERVE_AND_CONVERT_WHITESPACE:
            for (int j = 0; j < convertWhiteSpaceToCodePoints.length; ++j) {
              result = appendCodePoint(result, k++, convertWhiteSpaceToCodePoints[j]);
            }
            while (++i < length && Character.isWhitespace(codePoint = value.charAt(i))) {
              for (int j = 0; j < convertWhiteSpaceToCodePoints.length; ++j) {
                result = appendCodePoint(result, k++, convertWhiteSpaceToCodePoints[j]);
              }
            }
            break;
          case NORMALIZE_WHITESPACE:
            result = appendCodePoint(result, k++, ' ');
            while (++i < length && Character.isWhitespace(codePoint = value.charAt(i))) {
              // ignore extra whitespace;
            }
            break;
          case NORMALIZE_AND_CONVERT_WHITESPACE:
            for (int j = 0; j < convertWhiteSpaceToCodePoints.length; ++j) {
              result = appendCodePoint(result, k++, convertWhiteSpaceToCodePoints[j]);
            }
            while (++i < length && Character.isWhitespace(codePoint = value.charAt(i))) {
              // ignore extra whitespace;
            }
            break;
          case REMOVE_WHITESPACE:
            while (++i < length && Character.isWhitespace(codePoint = value.charAt(i))) {
              // ignore extra whitespace;
            }
            break;
        }
      }
      if (!isAcceptedCodePoint(codePoint)) {
        throw InvalidTypeValueException.forInvalidCodePoint(errorMessage, targetClass, value, i, codePoint);
      }
      toCodePoints = codePointConversions.convertCodePoint(codePoint, reusableSingleCodePointArray);
      for (int j = 0; j < toCodePoints.length; ++j) {
        codePoint = toCodePoints[j];
        if (k >= maxNumberOfCodePoints) {
          throw InvalidTypeValueException.forValueTooLong(errorMessage, targetClass, value, maxNumberOfCodePoints);
        }
//        codePointConversion = codePointConversions.getCodePointConversion(codePoint);
//        codePoint = codePointConversion == null ? codePoint : (char) codePointConversion.toCodePoints[0];
        if (k == result.length) {
          result = Arrays.copyOf(result, result.length + Math.max(16, toCodePoints.length));
        }
        result = appendCodePoint(result, k++,
            switch (targetCase) {
              case PRESERVE_CASE -> codePoint;
              case TO_LOWER_CASE -> Character.toLowerCase(codePoint);
              case TO_UPPER_CASE -> Character.toUpperCase(codePoint);
              case TO_TITLE_CASE -> k == 1 ? Character.toTitleCase(codePoint) : Character.toLowerCase(codePoint);
            });
      }
      ++i;
    }
    if (k < minNumberOfCodePoints) {
      throw InvalidTypeValueException.forValueTooShort(errorMessage, targetClass, value, minNumberOfCodePoints);
    }
    return new String(result, 0, k);
  }

  private boolean isAcceptedCodePoint(final int codePoint) {
    return (acceptedCategories > 0L && (acceptedCategories & (0x1L << Character.getType(codePoint))) > 0L)
        || acceptedCodePoints.isInSubset(codePoint);
  }

  private static int[] appendCodePoint(int[] result, final int index, final int codePoint) {
    if (index == result.length) {
      result = Arrays.copyOf(result, result.length + 16);
    }
    result[index] = codePoint;
    return result;
  }

  /**
   * Return the end-index of the last char-character ignoring trailing whitespace
   *
   * @param value the char sequence to scan for first relevant character
   * @return the end-index of the last char-character ignoring trailing whitespace
   */
  private static int endIndexIgnoringTrailingWhitespace(final CharSequence value) {
    if (value == null) {
      return 0;
    }
    int endIndex = value.length() - 1;
    // Find end index of char-sequence ignoring trailing whitespace.
    while (endIndex >= 0 && isWhitespace(value.charAt(endIndex))) {
      --endIndex;
    }
    return ++endIndex;
  }

  /**
   * Return the index of first character that is not whitespace
   *
   * @param value    the char sequence to scan for first relevant character
   * @param endIndex the index at which to stop progressing through the char-sequence
   * @return the index of first character that is not whitespace
   */
  private static int startIndexIgnoringLeadingWhitespace(final CharSequence value, final int endIndex) {
    if (value == null || endIndex == 0) {
      return 0;
    }
    int startIndex = 0;
    while (startIndex < endIndex && isWhitespace(value.charAt(startIndex))) {
      ++startIndex;
    }
    return startIndex;
  }

  public static class TypeParserBuilder {

    private final Class<? extends CharType<?>> targetClass;
    private String errorMessage;
    private WhiteSpace whiteSpace = WhiteSpace.FORBID_WHITESPACE;
    private int[] convertWhiteSpaceToCodePoints;
    private NullHandling nullHandling = NullHandling.PRESERVE_NULL_AND_EMPTY;
    private int minNumberOfCodePoints = 0;
    private int maxNumberOfCodePoints = 64;
    private TargetCase targetCase = TargetCase.PRESERVE_CASE;
    private long acceptedCategories = 0; // bit values, currently int be enough but new categories have been defined.
    private final RangedSubsetBuilder codePointRangesBuilder = RangedSubset.builder();
    private final CodePointConversionsBuilder codePointConversionsBuilder = CodePointConversions.builder();
    private final List<TypeParserBuilder> logicalOr = new ArrayList<>();

    TypeParserBuilder(final Class<? extends CharType<?>> targetClass) {
      this.targetClass = targetClass;
    }

    public TypeParserBuilder errorMessage(final String errorMessage) {
      this.errorMessage = errorMessage;
      return this;
    }

    public TypeParserBuilder minNumberOfCodePoints(final int min) {
      minNumberOfCodePoints = Math.max(min, 0);
      return this;
    }

    public TypeParserBuilder maxNumberOfCodePoints(final int max) {
      maxNumberOfCodePoints = max;
      return this;
    }

    public TypeParserBuilder fixedNumberOfCodePoints(final int size) {
      minNumberOfCodePoints(size);
      maxNumberOfCodePoints(size);
      return this;
    }

    public TypeParserBuilder preserveNullAndEmpty() {
      nullHandling = NullHandling.PRESERVE_NULL_AND_EMPTY;
      return this;
    }

    public TypeParserBuilder convertNullToEmpty() {
      nullHandling = NullHandling.CONVERT_NULL_TO_EMPTY;
      return this;
    }

    public TypeParserBuilder convertEmptyToNull() {
      nullHandling = NullHandling.CONVERT_EMPTY_TO_NULL;
      return this;
    }

    public TypeParserBuilder preserveCase() {
      targetCase = TargetCase.PRESERVE_CASE;
      return this;
    }

    public TypeParserBuilder toUpperCase() {
      targetCase = TargetCase.TO_UPPER_CASE;
      return this;
    }

    public TypeParserBuilder toLowerCase() {
      targetCase = TargetCase.TO_LOWER_CASE;
      return this;
    }

    public TypeParserBuilder toTitleCase() {
      targetCase = TargetCase.TO_TITLE_CASE;
      return this;
    }

    public TypeParserBuilder acceptChar(final char ch) {
      codePointRangesBuilder.addChar(ch);
      return this;
    }

    public TypeParserBuilder acceptChars(final char... chars) {
      codePointRangesBuilder.addChars(chars);
      return this;
    }

    public TypeParserBuilder acceptCharRange(final char inclusiveFrom, final char inclusiveTo) {
      codePointRangesBuilder.addCharRange(inclusiveFrom, inclusiveTo);
      return this;
    }

    public TypeParserBuilder acceptCodePoint(final int codePoint) {
      codePointRangesBuilder.addCodePoint(codePoint);
      return this;
    }

    public TypeParserBuilder acceptCodePoints(final int... codePoints) {
      codePointRangesBuilder.addCodePoints(codePoints);
      return this;
    }

    public TypeParserBuilder acceptCodePointRange(final char inclusiveFrom, final char inclusiveTo) {
      codePointRangesBuilder.addCodePointRange(inclusiveFrom, inclusiveTo);
      return this;
    }

    public TypeParserBuilder convertChar(final char fromChar, final char toChar) {
      codePointConversionsBuilder.addCharConversion(fromChar, toChar);
      return this;
    }

    public TypeParserBuilder convertChar(final char fromChar, final CharSequence toCharSequence) {
      codePointConversionsBuilder.addCharConversion(fromChar, toCharSequence);
      return this;
    }


    public TypeParserBuilder convertCodePoint(final int fromCodePoint, final int toCodePoint) {
      codePointConversionsBuilder.addCodePointConversion(fromCodePoint, toCodePoint);
      return this;
    }

    public TypeParserBuilder convertCodePoint(final int fromCodePoint, final CharSequence toCharSequence) {
      codePointConversionsBuilder.addCodePointConversion(fromCodePoint, toCharSequence);
      return this;
    }

    public TypeParserBuilder acceptUnicodeCategory(final Category category) {
      acceptedCategories |= category.bitMask;
      return this;
    }

    public TypeParserBuilder acceptAllDashes() {
      acceptUnicodeCategory(Category.DASH_PUNCTUATION);
//      codePointRangesBuilder.addRangedSubset(Category.DASH_PUNCTUATION);
      return this;
    }

    public TypeParserBuilder convertAllDashesToHyphen() {
      return convertAllDashesTo((int) '-');
    }

    public TypeParserBuilder convertAllDashesTo(final char toChar) {
      return convertAllDashesTo((int) toChar);
    }

    public TypeParserBuilder convertAllDashesTo(final int toCodePoint) {
      codePointConversionsBuilder.addCategoryConversion(Category.DASH_PUNCTUATION, toCodePoint);
      return this;
    }

    public TypeParserBuilder convertAllDashesTo(final CharSequence toCharSequence) {
      codePointConversionsBuilder.addCategoryConversion(Category.DASH_PUNCTUATION, toCharSequence);
      return this;
    }

    public TypeParserBuilder removeAllWhitespace() {
      whiteSpace = WhiteSpace.REMOVE_WHITESPACE;
      return this;
    }

    public TypeParserBuilder normalizeWhitespace() {
      whiteSpace = WhiteSpace.NORMALIZE_WHITESPACE;
      return this;
    }

    public TypeParserBuilder normalizeAndConvertWhitespaceTo(final char toChar) {
      whiteSpace = WhiteSpace.NORMALIZE_AND_CONVERT_WHITESPACE;
      convertWhiteSpaceToCodePoints = new int[]{toChar};
      return this;
    }

    public TypeParserBuilder normalizeAndConvertWhitespaceTo(final int toCodePoint) {
      whiteSpace = WhiteSpace.NORMALIZE_AND_CONVERT_WHITESPACE;
      convertWhiteSpaceToCodePoints = new int[]{toCodePoint};
      return this;
    }

    public TypeParserBuilder normalizeAndConvertWhitespaceTo(final CharSequence toCharSequence) {
      whiteSpace = WhiteSpace.NORMALIZE_AND_CONVERT_WHITESPACE;
      convertWhiteSpaceToCodePoints = toCharSequence.codePoints().toArray();
      return this;
    }

    public TypeParserBuilder preserveWhitespace() {
      whiteSpace = WhiteSpace.PRESERVE_WHITESPACE;
      return this;
    }

    public TypeParserBuilder preserveAndConvertWhitespaceTo(final char toChar) {
      whiteSpace = WhiteSpace.PRESERVE_AND_CONVERT_WHITESPACE;
      convertWhiteSpaceToCodePoints = new int[]{toChar};
      return this;
    }

    public TypeParserBuilder preserveAndConvertWhitespaceTo(final int toCodePoint) {
      whiteSpace = WhiteSpace.PRESERVE_AND_CONVERT_WHITESPACE;
      convertWhiteSpaceToCodePoints = new int[]{toCodePoint};
      return this;
    }

    public TypeParserBuilder preserveAndConvertWhitespaceTo(final CharSequence toCharSequence) {
      whiteSpace = WhiteSpace.PRESERVE_AND_CONVERT_WHITESPACE;
      convertWhiteSpaceToCodePoints = toCharSequence.codePoints().toArray();
      return this;
    }

    public TypeParser build() {
      return new TypeParser(
          targetClass, errorMessage, targetCase,
          whiteSpace, convertWhiteSpaceToCodePoints,
          nullHandling,
          minNumberOfCodePoints, maxNumberOfCodePoints,
          acceptedCategories,
          codePointRangesBuilder.build(),
          codePointConversionsBuilder.build());
    }
  }

  private static class CodePointConversion {

    static final Comparator<CodePointConversion> CODE_POINT_COMPARATOR =
        Comparator.nullsFirst(Comparator
            .comparing(CodePointConversion::getFromCodePoint)
            .thenComparing((o1, o2) -> Arrays.compare(o1.getToCodePoints(), o2.getToCodePoints())));

    final int fromCodePoint;
    final int[] toCodePoints;

    public CodePointConversion(int fromCodePoint, int[] toCodePoints) {
      this.fromCodePoint = fromCodePoint;
      this.toCodePoints = toCodePoints;
    }

    public int getFromCodePoint() {
      return fromCodePoint;
    }

    public int[] getToCodePoints() {
      return toCodePoints;
    }
  }

  static class CodePointConversions {

    /**
     * Hash-map of Character categories to code-point arrays:
     */
    final IntToIntArrayHashMap categoryConversionToCodePoint;

    /**
     * Hash-map of code-points to code-point arrays:
     */
    final IntToIntArrayHashMap codePointConversions;

    final int maxToCodePointsLength;

    static CodePointConversionsBuilder builder() {
      return new CodePointConversionsBuilder();
    }

    private CodePointConversions(
        final IntToIntArrayHashMap categoryConversionToCodePoint,
        final IntToIntArrayHashMap codePointConversions) {
      this.categoryConversionToCodePoint = categoryConversionToCodePoint;
      this.codePointConversions = codePointConversions;
      int max = 0;
      if (categoryConversionToCodePoint != null && !categoryConversionToCodePoint.isEmpty()) {
        max = Math.max(max, categoryConversionToCodePoint.getMaxValueArrayLength());
      }
      if (codePointConversions != null && !codePointConversions.isEmpty()) {
        max = Math.max(max, codePointConversions.getMaxValueArrayLength());
      }
      this.maxToCodePointsLength = max;
    }

    int[] getCodePointConversion(final int fromCodePoint) {
      if (categoryConversionToCodePoint != null) {
        final int[] toCodePoints = categoryConversionToCodePoint.get(Character.getType(fromCodePoint));
        if (toCodePoints != null) {
          return toCodePoints;
        }
      }
      if (codePointConversions != null) {
        final int[] toCodePoints = codePointConversions.get(fromCodePoint);
        if (toCodePoints != null) {
          return toCodePoints;
        }
      }
      return null;
    }

    int[] convertCodePoint(final int fromCodePoint, final int[] useThisArrayWhenNoConversionRequired) {
      int[] toCodePoints = getCodePointConversion(fromCodePoint);
      if (toCodePoints == null) {
        useThisArrayWhenNoConversionRequired[0] = fromCodePoint;
        return useThisArrayWhenNoConversionRequired;
      }
      return toCodePoints;
    }

    /**
     * A builder to create code-point conversions
     */
    static class CodePointConversionsBuilder {

      private IntToIntArrayHashMap categoryConversionToCodePoint = new IntToIntArrayHashMap();

      private IntToIntArrayHashMap codePointConversions = new IntToIntArrayHashMap();

      private int endIndex = 0;

      private CodePointConversionsBuilder() {
      }

      public CodePointConversionsBuilder addCategoryConversion(final Category category, final char toChar) {
        return addCategoryConversion(category, new int[]{toChar});
      }

      public CodePointConversionsBuilder addCategoryConversion(final Category category, final int toCodePoint) {
        return addCategoryConversion(category, new int[]{toCodePoint});
      }

      public CodePointConversionsBuilder addCategoryConversion(final Category category, final CharSequence toCharSequence) {
        return addCategoryConversion(category, toCharSequence.codePoints().toArray());
      }

      public CodePointConversionsBuilder addCategoryConversion(final Category category, final int[] toCodePoints) {
        for (int characterCategory : category.characterCategories) {
          categoryConversionToCodePoint.put(characterCategory, toCodePoints);
        }
        return this;
      }

      void addCharConversion(final char fromChar, final char toChar) {
        addCodePointConversion(fromChar, new int[]{(int) toChar});
      }

      void addCharConversions(final char[] fromChars, final char toChar) {
        if (fromChars != null) {
          for (int i = 0; i < fromChars.length; ++i) {
            addCodePointConversion(fromChars[i], new int[]{(int) toChar});
          }
        }
      }

      void addCharConversion(final char fromChar, final CharSequence toCharSequence) {
        addCodePointConversion(fromChar, toCharSequence.codePoints().toArray());
      }

      void addCodePointConversion(final int fromCodePoint, final int toCodePoint) {
        addCodePointConversion(fromCodePoint, new int[]{toCodePoint});
      }

      void addCodePointConversions(final RangedSubset subset, final CharSequence toCharSequence) {
        addCodePointConversions(subset, toCharSequence.codePoints().toArray());
      }

      void addCodePointConversions(final RangedSubset subset, final char toChar) {
        addCodePointConversions(subset, new int[]{toChar});
      }

      void addCodePointConversions(final RangedSubset subset, final int toCodePoint) {
        addCodePointConversions(subset, new int[]{toCodePoint});
      }

      void addCodePointConversions(final RangedSubset subset, final int[] toCodePoints) {

        if (subset != null) {
          final char[] singleByteRangedSubset = subset.getSingleByteCodePointRanges();
          for (char c : singleByteRangedSubset) {
            final int inclusiveFrom = RangedSubset.getInclusiveFrom(c);
            final int inclusiveTo = RangedSubset.getInclusiveTo(c);
            for (int j = inclusiveFrom; j <= inclusiveTo; ++j) {
              addCodePointConversion(j, toCodePoints);
            }
          }
          final int[] doubleByteRangedSubset = subset.getDoubleByteCodePointRanges();
          for (int c : doubleByteRangedSubset) {
            final int inclusiveFrom = RangedSubset.getInclusiveFrom(c);
            final int inclusiveTo = RangedSubset.getInclusiveTo(c);
            for (int j = inclusiveFrom; j <= inclusiveTo; ++j) {
              addCodePointConversion(j, toCodePoints);
            }
          }
          final long[] tripleByteRangedSubset = subset.getTripleByteCodePointRanges();
          for (long c : tripleByteRangedSubset) {
            final int inclusiveFrom = RangedSubset.getInclusiveFrom(c);
            final int inclusiveTo = RangedSubset.getInclusiveTo(c);
            for (int j = inclusiveFrom; j <= inclusiveTo; ++j) {
              addCodePointConversion(j, toCodePoints);
            }
          }
        }
      }

      void addCodePointConversion(final int fromCodePoint, final CharSequence toCharSequence) {
        addCodePointConversion(fromCodePoint, toCharSequence.codePoints().toArray());
      }

      void addCodePointConversion(final int fromCodePoint, final int[] toCodePoints) {
        codePointConversions.put(fromCodePoint, toCodePoints);
      }

      CodePointConversions build() {
        return new CodePointConversions(
            categoryConversionToCodePoint.isEmpty() ? null : categoryConversionToCodePoint,
            codePointConversions.isEmpty() ? null : codePointConversions);
      }
    }
  }

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

  static class RangedSubset implements Subset {

    protected final String name;
    protected final String alias;
    protected final char[] singleByteCodePointRanges;
    protected final int[] doubleByteCodePointRanges;
    protected final long[] tripleByteCodePointRanges;
    protected final int size;

    protected RangedSubset(
        final char[] singleByteCodePointRanges,
        final int[] doubleByteCodePointRanges,
        final long[] tripleByteCodePointRanges) {
      this("", "", singleByteCodePointRanges, doubleByteCodePointRanges, tripleByteCodePointRanges);
    }

    private RangedSubset(
        final String name,
        final String alias,
        final char[] singleByteCodePointRanges,
        final int[] doubleByteCodePointRanges,
        final long[] tripleByteCodePointRanges) {
      this.name = name;
      this.alias = alias;
      this.singleByteCodePointRanges = singleByteCodePointRanges == null ? EMPTY_CHAR_ARRAY : singleByteCodePointRanges;
      this.doubleByteCodePointRanges = doubleByteCodePointRanges == null ? EMPTY_INT_ARRAY : doubleByteCodePointRanges;
      this.tripleByteCodePointRanges = tripleByteCodePointRanges == null ? EMPTY_LONG_ARRAY : tripleByteCodePointRanges;
      this.size = this.singleByteCodePointRanges.length
          + this.doubleByteCodePointRanges.length
          + this.tripleByteCodePointRanges.length;
    }

    public static RangedSubsetBuilder builder() {
      return new RangedSubsetBuilder();
    }

    int size() {
      return size;
    }

    boolean isEmpty() {
      return size() == 0;
    }

    boolean isNotEmpty() {
      return !isEmpty();
    }

    /**
     * Returns an array of 2-byte code-point ranges stored as char values. Each range comprises an inclusive-from value and an inclusive-to value. The
     * most-significant 8 bits hold the inclusive-from value. The least-significant 8 bits hold the inclusive-to value. The returned array must be
     * sorted from low-to-high.
     */
    char[] getSingleByteCodePointRanges() {
      // Protect out data by returning a copy
      return Arrays.copyOf(singleByteCodePointRanges, singleByteCodePointRanges.length);
    }

    /**
     * Returns an array of 2-byte code-point ranges stored as integer values. Each range comprises an inclusive-from value and an inclusive-to value.
     * The most-significant 16 bits hold the inclusive-from value. The least-significant 16 bits hold the inclusive-to value. The returned array must
     * be sorted from low-to-high as unsigned-integers.
     */
    int[] getDoubleByteCodePointRanges() {
      // Protect out data by returning a copy
      return Arrays.copyOf(doubleByteCodePointRanges, doubleByteCodePointRanges.length);
    }

    /**
     * Returns an array of 3-byte code-point ranges stored as long values. Each range comprises an inclusive-from value and an inclusive-to value. The
     * most-significant 32 bits hold the inclusive-from value. The least-significant 32 bits hold the inclusive-to value. The returned array must be
     * sorted from low-to-high.
     */
    long[] getTripleByteCodePointRanges() {
      // Protect out data by returning a copy
      return Arrays.copyOf(tripleByteCodePointRanges, tripleByteCodePointRanges.length);
    }

    public String getName() {
      return name;
    }

    public String getAlias() {
      return alias;
    }

    public boolean isInSubset(final int codePoint) {
      if (codePoint < 0x100) {
        if (singleByteCodePointRanges.length == 0 ||
            codePoint < getInclusiveFrom(singleByteCodePointRanges[0]) ||
            codePoint > getInclusiveTo(singleByteCodePointRanges[singleByteCodePointRanges.length - 1])) {
          return false; // not found, outside of range
        }
        // Try to find using a binary search
        int low = 0;
        int high = singleByteCodePointRanges.length - 1;
        while (low <= high) {
          final int mid = (low + high) >>> 1; // divide by 2
          final int inclusiveFrom = getInclusiveFrom(singleByteCodePointRanges[mid]);
          final int inclusiveTo = getInclusiveTo(singleByteCodePointRanges[mid]);
          if (codePoint > inclusiveTo) {
            low = mid + 1;
          } else if (codePoint < inclusiveFrom) {
            high = mid - 1;
          } else {
            return true; // found
          }
        }
      } else if (codePoint < 0x10000) {
        if (doubleByteCodePointRanges.length == 0 ||
            codePoint < getInclusiveFrom(doubleByteCodePointRanges[0]) ||
            codePoint > getInclusiveTo(doubleByteCodePointRanges[doubleByteCodePointRanges.length - 1])) {
          return false; // not found, outside of range
        }
        // Try to find using a binary search
        int low = 0;
        int high = doubleByteCodePointRanges.length - 1;
        while (low <= high) {
          final int mid = (low + high) >>> 1; // divide by 2
          final int inclusiveFrom = getInclusiveFrom(doubleByteCodePointRanges[mid]);
          final int inclusiveTo = getInclusiveTo(doubleByteCodePointRanges[mid]);
          if (codePoint > inclusiveTo) {
            low = mid + 1;
          } else if (codePoint < inclusiveFrom) {
            high = mid - 1;
          } else {
            return true; // found
          }
        }
      } else {
        if (tripleByteCodePointRanges.length == 0 ||
            codePoint < getInclusiveFrom(tripleByteCodePointRanges[0]) ||
            codePoint > getInclusiveTo(tripleByteCodePointRanges[tripleByteCodePointRanges.length - 1])) {
          return false; // not found, outside of range
        }
        // Try to find using a binary search
        int low = 0;
        int high = tripleByteCodePointRanges.length - 1;
        while (low <= high) {
          final int mid = (low + high) >>> 1; // divide by 2
          final long midVal = tripleByteCodePointRanges[mid];
          final int inclusiveFrom = getInclusiveFrom(midVal);
          final int inclusiveTo = getInclusiveTo(midVal);
          if (codePoint > inclusiveTo) {
            low = mid + 1;
          } else if (codePoint < inclusiveFrom) {
            high = mid - 1;
          } else {
            return true; // found
          }
        }
      }
      return false; // not found
    }

    @Override
    public String toString() {
      final StringBuilder s = new StringBuilder();
      s.append(name).append('[');
      for (char singleByteCodePointRange : singleByteCodePointRanges) {
        s.append(Integer.toString(getInclusiveFrom(singleByteCodePointRange), 16)).append('.').append('.')
            .append(Integer.toString(getInclusiveTo(singleByteCodePointRange), 16)).append(',');
      }
      for (int doubleByteCodePointRange : doubleByteCodePointRanges) {
        s.append(Integer.toString(getInclusiveFrom(doubleByteCodePointRange), 16)).append('.').append('.')
            .append(Integer.toString(getInclusiveTo(doubleByteCodePointRange), 16)).append(',');
      }
      for (long tripleByteCodePointRange : tripleByteCodePointRanges) {
        s.append(Integer.toString(getInclusiveFrom(tripleByteCodePointRange), 16)).append('.').append('.')
            .append(Integer.toString(getInclusiveTo(tripleByteCodePointRange), 16)).append(',');
      }
      s.setLength(s.length() - 1); // remove final comma
      s.append(']');
      return s.toString();
    }

    static int getInclusiveFrom(final char codePointRange) {
      return codePointRange >>> 8;
    }

    static int getInclusiveTo(final char codePointRange) {
      return codePointRange & 0x00_ff;
    }

    static int getInclusiveFrom(final int codePointRange) {
      return codePointRange >>> 16;
    }

    static int getInclusiveTo(final int codePointRange) {
      return codePointRange & 0x0000_ffff;
    }

    static int getInclusiveFrom(final long codePointRange) {
      return (int) (codePointRange >>> 32);
    }

    static int getInclusiveTo(final long codePointRange) {
      return (int) (codePointRange & 0x00000000_ffffffffL);
    }

    static char rangeToChar(final int inclusiveFrom, final int inclusiveTo) {
      return (char) ((inclusiveFrom << 8) | (inclusiveTo & 0x00_ff));
    }

    static int rangeToInt(final int inclusiveFrom, final int inclusiveTo) {
      return (inclusiveFrom << 16) | (inclusiveTo & 0x0000_ffff);
    }

    static long rangeToLong(final int inclusiveFrom, final int inclusiveTo) {
      return (((long) inclusiveFrom) << 32) | inclusiveTo;
    }

    static class RangedSubsetBuilder {

      private char[] singleByteCodePointRanges = new char[16];
      private int[] doubleByteCodePointRanges = new int[16];
      private long[] tripleByteCodePointRanges = new long[16];
      private int singleByteEndIndex = 0;
      private int doubleByteEndIndex = 0;
      private int tripleByteEndIndex = 0;

      void addChar(final char ch) {
        addCharRange(ch, ch);
      }

      void addChars(final char... chars) {
        if (chars != null) {
          ensureDoubleByteCapacity(chars.length);
          for (int i = 0; i < chars.length; ++i) {
            addChar(chars[i]);
          }
        }
      }

      void addCharRange(final char inclusiveFrom, final char inclusiveTo) {
        addCodePointRange(inclusiveFrom & 0x0000_ffff, inclusiveTo & 0x0000_ffff);
      }

      void addCodePoint(final int codePoint) {
        addCodePointRange(codePoint, codePoint);
      }

      void addCodePoints(final int... codePoints) {
        if (codePoints != null) {
          ensureDoubleByteCapacity(codePoints.length);
          for (int i = 0; i < codePoints.length; ++i) {
            addCodePoint(codePoints[i]);
          }
        }
      }

      void addRangedSubset(final RangedSubset subset) {
        if (subset != null && subset.isNotEmpty()) {
          final char[] singleByteRanges = subset.getSingleByteCodePointRanges();
          ensureSingleByteCapacity(singleByteRanges.length);
          for (char c : singleByteRanges) {
            addCodePointRange(
                RangedSubset.getInclusiveFrom(c),
                RangedSubset.getInclusiveTo(c));
          }
          final int[] doubleByteRanges = subset.getDoubleByteCodePointRanges();
          ensureDoubleByteCapacity(doubleByteRanges.length);
          for (int j : doubleByteRanges) {
            addCodePointRange(
                RangedSubset.getInclusiveFrom(j),
                RangedSubset.getInclusiveTo(j));
          }
          final long[] tripleByteRanges = subset.getTripleByteCodePointRanges();
          ensureTripleByteCapacity(tripleByteRanges.length);
          for (long l : tripleByteRanges) {
            addCodePointRange(
                RangedSubset.getInclusiveFrom(l),
                RangedSubset.getInclusiveTo(l));
          }
        }
      }

      void addCodePointRange(int inclusiveFrom, int inclusiveTo) {
        if (inclusiveTo > 0xffff) {
          if (inclusiveFrom > 0xffff) {
            ensureTripleByteCapacity();
            tripleByteCodePointRanges[tripleByteEndIndex] = RangedSubset.rangeToLong(inclusiveFrom, inclusiveTo);
            ++tripleByteEndIndex;
            return;
          } else {
            ensureTripleByteCapacity();
            tripleByteCodePointRanges[tripleByteEndIndex] = RangedSubset.rangeToLong(0x10000, inclusiveTo);
            ++tripleByteEndIndex;
            inclusiveTo = 0xffff;
          }
        }

        if (inclusiveTo > 0xff) {
          if (inclusiveFrom > 0xff) {
            ensureDoubleByteCapacity();
            doubleByteCodePointRanges[doubleByteEndIndex] = RangedSubset.rangeToInt(inclusiveFrom, inclusiveTo);
            ++doubleByteEndIndex;
            return;
          } else {
            ensureDoubleByteCapacity();
            doubleByteCodePointRanges[doubleByteEndIndex] = RangedSubset.rangeToInt(0x100, inclusiveTo);
            ++doubleByteEndIndex;
            inclusiveTo = 0xff;
          }
        }

        ensureSingleByteCapacity();
        singleByteCodePointRanges[singleByteEndIndex] = RangedSubset.rangeToChar(inclusiveFrom, inclusiveTo);
        ++singleByteEndIndex;
      }

      private void removeSingleByteElement(final int index) {
        System.arraycopy(singleByteCodePointRanges, index + 1, singleByteCodePointRanges, index, singleByteCodePointRanges.length - index - 1);
        --singleByteEndIndex;
      }

      private void removeDoubleByteElement(final int index) {
        System.arraycopy(doubleByteCodePointRanges, index + 1, doubleByteCodePointRanges, index, doubleByteCodePointRanges.length - index - 1);
        --doubleByteEndIndex;
      }

      private void removeTripleByteElement(final int index) {
        System.arraycopy(tripleByteCodePointRanges, index + 1, tripleByteCodePointRanges, index, tripleByteCodePointRanges.length - index - 1);
        --tripleByteEndIndex;
      }

      private void ensureSingleByteCapacity() {
        if (singleByteEndIndex == singleByteCodePointRanges.length) {
          ensureSingleByteCapacity(16);
        }
      }

      private void ensureSingleByteCapacity(final int amount) {
        if ((singleByteCodePointRanges.length - singleByteEndIndex) < amount) {
          singleByteCodePointRanges = Arrays.copyOf(singleByteCodePointRanges, singleByteCodePointRanges.length + amount);
        }
      }

      private void ensureDoubleByteCapacity() {
        if (doubleByteEndIndex == doubleByteCodePointRanges.length) {
          ensureDoubleByteCapacity(16);
        }
      }

      private void ensureDoubleByteCapacity(final int amount) {
        if ((doubleByteCodePointRanges.length - doubleByteEndIndex) < amount) {
          doubleByteCodePointRanges = Arrays.copyOf(doubleByteCodePointRanges, doubleByteCodePointRanges.length + amount);
        }
      }

      private void ensureTripleByteCapacity() {
        if (tripleByteEndIndex == tripleByteCodePointRanges.length) {
          ensureTripleByteCapacity(16);
        }
      }

      private void ensureTripleByteCapacity(final int amount) {
        if ((tripleByteCodePointRanges.length - tripleByteEndIndex) < amount) {
          tripleByteCodePointRanges = Arrays.copyOf(tripleByteCodePointRanges, tripleByteCodePointRanges.length + amount);
        }
      }

      private static void unsignedIntegerBubbleSort(final int[] values, int fromIndex, int toIndex) {
        int temp;
        for (int i = fromIndex; i < toIndex; i++) {
          for (int j = fromIndex; j < toIndex - i - 1; j++) {
            if ((0x00000000_ffffffffL & (long) values[j]) > (0x00000000_ffffffffL & (long) values[j + 1])) {
              // swap the elements
              temp = values[j];
              values[j] = values[j + 1];
              values[j + 1] = temp;
            }
          }
        }
      }

      RangedSubset build() {
        {
          Arrays.sort(singleByteCodePointRanges, 0, singleByteEndIndex);
          int previousInclusiveFrom;
          int previousInclusiveTo;
          int currentInclusiveFrom;
          int currentInclusiveTo;
          int i = 0;
          int j = 1;
          while (j < singleByteEndIndex) {
            previousInclusiveFrom = RangedSubset.getInclusiveFrom(singleByteCodePointRanges[i]);
            previousInclusiveTo = RangedSubset.getInclusiveTo(singleByteCodePointRanges[i]);
            currentInclusiveFrom = RangedSubset.getInclusiveFrom(singleByteCodePointRanges[j]);
            currentInclusiveTo = RangedSubset.getInclusiveTo(singleByteCodePointRanges[j]);
            if (previousInclusiveTo >= currentInclusiveFrom) {
              if (previousInclusiveTo <= currentInclusiveTo) {
                singleByteCodePointRanges[i] = RangedSubset.rangeToChar(previousInclusiveFrom, currentInclusiveTo);
              }
              removeSingleByteElement(j);
            } else if ((previousInclusiveTo + 1) == currentInclusiveFrom) {
              singleByteCodePointRanges[i] = RangedSubset.rangeToChar(previousInclusiveFrom, currentInclusiveTo);
              removeSingleByteElement(j);
            } else {
              ++i;
              ++j;
            }
          }
        }
        {
          unsignedIntegerBubbleSort(doubleByteCodePointRanges, 0, doubleByteEndIndex);
          int previousInclusiveFrom;
          int previousInclusiveTo;
          int currentInclusiveFrom;
          int currentInclusiveTo;
          int i = 0;
          int j = 1;
          while (j < doubleByteEndIndex) {
            previousInclusiveFrom = RangedSubset.getInclusiveFrom(doubleByteCodePointRanges[i]);
            previousInclusiveTo = RangedSubset.getInclusiveTo(doubleByteCodePointRanges[i]);
            currentInclusiveFrom = RangedSubset.getInclusiveFrom(doubleByteCodePointRanges[j]);
            currentInclusiveTo = RangedSubset.getInclusiveTo(doubleByteCodePointRanges[j]);
            if (previousInclusiveTo >= currentInclusiveFrom) {
              if (previousInclusiveTo <= currentInclusiveTo) {
                doubleByteCodePointRanges[i] = RangedSubset.rangeToInt(previousInclusiveFrom, currentInclusiveTo);
              }
              removeDoubleByteElement(j);
            } else if ((previousInclusiveTo + 1) == currentInclusiveFrom) {
              doubleByteCodePointRanges[i] = RangedSubset.rangeToInt(previousInclusiveFrom, currentInclusiveTo);
              removeDoubleByteElement(j);
            } else {
              ++i;
              ++j;
            }
          }
        }
        {
          Arrays.sort(tripleByteCodePointRanges, 0, tripleByteEndIndex);
          int previousInclusiveFrom;
          int previousInclusiveTo;
          int currentInclusiveFrom;
          int currentInclusiveTo;
          int i = 0;
          int j = 1;
          while (j < tripleByteEndIndex) {
            previousInclusiveFrom = RangedSubset.getInclusiveFrom(tripleByteCodePointRanges[i]);
            previousInclusiveTo = RangedSubset.getInclusiveTo(tripleByteCodePointRanges[i]);
            currentInclusiveFrom = RangedSubset.getInclusiveFrom(tripleByteCodePointRanges[j]);
            currentInclusiveTo = RangedSubset.getInclusiveTo(tripleByteCodePointRanges[j]);
            if (previousInclusiveTo >= currentInclusiveFrom) {
              if (previousInclusiveTo <= currentInclusiveTo) {
                tripleByteCodePointRanges[i] = RangedSubset.rangeToLong(previousInclusiveFrom, currentInclusiveTo);
              }
              removeTripleByteElement(j);
            } else if ((previousInclusiveTo + 1) == currentInclusiveFrom) {
              tripleByteCodePointRanges[i] = RangedSubset.rangeToLong(previousInclusiveFrom, currentInclusiveTo);
              removeTripleByteElement(j);
            } else {
              ++i;
              ++j;
            }
          }
        }
        return new RangedSubset(
            Arrays.copyOf(singleByteCodePointRanges, singleByteEndIndex),
            Arrays.copyOf(doubleByteCodePointRanges, doubleByteEndIndex),
            Arrays.copyOf(tripleByteCodePointRanges, tripleByteEndIndex));
      }
    }
  }

  private static class IntToIntArrayHashMap {

    /**
     * The load factor for the hash map.
     */
    private static final float LOAD_FACTOR = 0.75F;

    /**
     * Use and internal struct-like class to ensure an atomic transfer after a rehash.
     */
    private static class HashTable {

      /**
       * 2-dimensional array for the keys which is aligned with the values array:
       * <ul>
       *   <li>first index/dimension to get the hash bucket containing the map keys.</li>
       *   <li>second index/dimension to get the key values.</li>
       * </ul>
       */
      private int[][] keys;

      /**
       * 2-dimensional array for the values which is aligned with the key array. It appears to be 3-dimensional but that is because the map-values are
       * actually int-arrays:
       * <ul>
       *   <li>first index/dimension to get the hash bucket containing the map values.</li>
       *   <li>second index/dimension to get the value objects which are int-arrays.</li>
       * </ul>
       */
      private int[][][] values;
    }

    /**
     * We will re-hash the map its size exceeds this threshold.  (The value of this field is (int)(capacity * loadFactor).)
     *
     * @serial
     */
    private int threshold;

    private HashTable hashTable;

    private int maxValueArrayLength = 0;

    private int size;

    IntToIntArrayHashMap() {
      final int initialCapacity = 20;
      this.hashTable = new HashTable();
      this.hashTable.keys = new int[initialCapacity][];
      this.hashTable.values = new int[initialCapacity][][];
      this.threshold = (int) (this.hashTable.keys.length * LOAD_FACTOR);

      threshold = (int) (initialCapacity * LOAD_FACTOR);
    }

    /**
     * Return the number of entries in this hash map.
     *
     * @return the number of keys in this hash map.
     */
    int size() {
      return size;
    }

    /**
     * Returns {@code true} if there are no entries in this hash map and {@code false} otherwise.
     *
     * @return {@code true} if there are no entries in this hash map and {@code false} otherwise.
     */
    boolean isEmpty() {
      return size == 0;
    }

    int getMaxValueArrayLength() {
      return maxValueArrayLength;
    }

    int[] get(final int key) {
      int hashIndex = (key & 0x7FFFFFFF) % hashTable.keys.length;
      int[] bucket = hashTable.keys[hashIndex];
      if (bucket != null) {
        for (int bucketIndex = 0; bucketIndex < bucket.length; ++bucketIndex) {
          if (bucket[bucketIndex] == key) {
            return hashTable.values[hashIndex][bucketIndex];
          }
        }
      }
      return null;
    }

    void put(final int key, final int[] value) {
      if (value == null) {
        return;
      }
      if (threshold < (int) (size * LOAD_FACTOR)) {
        rehash();
      }
      put(key, value, hashTable);
      maxValueArrayLength = Math.max(maxValueArrayLength, value.length);
      ++size;
    }

    private static void put(final int key, final int[] value, final HashTable hashTable) {
      int hashIndex = (key & 0x7FFFFFFF) % hashTable.keys.length;
      int[] bucket = hashTable.keys[hashIndex];
      if (bucket == null) {
        hashTable.keys[hashIndex] = new int[]{key};
        hashTable.values[hashIndex] = new int[][]{value};
      } else {
        int bucketIndex = 0;
        while (bucketIndex < bucket.length && bucket[bucketIndex] != key) {
          ++bucketIndex;
        }
        if (bucketIndex == bucket.length) {
          hashTable.keys[hashIndex] = Arrays.copyOf(hashTable.keys[hashIndex], bucketIndex + 1);
          hashTable.values[hashIndex] = Arrays.copyOf(hashTable.values[hashIndex], bucketIndex + 1);
        }
        hashTable.keys[hashIndex][bucketIndex] = key;
        hashTable.values[hashIndex][bucketIndex] = value;
      }
    }

    private void rehash() {
      final int newCapacity = (int) (hashTable.keys.length * 1.5F + 1);
      threshold = (int) (newCapacity * LOAD_FACTOR);
      final HashTable newHashTable = new HashTable();
      newHashTable.keys = new int[newCapacity][];
      newHashTable.values = new int[newCapacity][][];
      for (int i = 0; i < hashTable.keys.length; ++i) {
        if (hashTable.keys[i] != null) {
          for (int j = 0; j < hashTable.keys[i].length; ++j) {
            put(hashTable.keys[i][j], hashTable.values[i][j], newHashTable);
          }
        }
      }
      this.hashTable = newHashTable;
    }
  }

  enum NullHandling {
    PRESERVE_NULL_AND_EMPTY,
    CONVERT_NULL_TO_EMPTY,
    CONVERT_EMPTY_TO_NULL;
  }

  enum TargetCase {
    PRESERVE_CASE,
    TO_UPPER_CASE,
    TO_LOWER_CASE,
    TO_TITLE_CASE,
  }

  enum WhiteSpace {
    FORBID_WHITESPACE,
    PRESERVE_WHITESPACE,
    PRESERVE_AND_CONVERT_WHITESPACE,
    NORMALIZE_WHITESPACE,
    NORMALIZE_AND_CONVERT_WHITESPACE,
    REMOVE_WHITESPACE,
  }

  /**
   * Provides typesafe versions of the Unicode character categories defined in the {@link java.lang.Character Character} class for use with the {@link
   * TypeParser.TypeParserBuilder}. Once built the {@link TypeParser} will use a bit-mask for improved performance.
   *
   * @see Character#COMBINING_SPACING_MARK
   * @see Character#CONNECTOR_PUNCTUATION
   * @see Character#CONTROL
   * @see Character#CURRENCY_SYMBOL
   * @see Character#DASH_PUNCTUATION
   * @see Character#DECIMAL_DIGIT_NUMBER
   * @see Character#ENCLOSING_MARK
   * @see Character#END_PUNCTUATION
   * @see Character#FINAL_QUOTE_PUNCTUATION
   * @see Character#FORMAT
   * @see Character#INITIAL_QUOTE_PUNCTUATION
   * @see Character#LETTER_NUMBER
   * @see Character#LINE_SEPARATOR
   * @see Character#LOWERCASE_LETTER
   * @see Character#MATH_SYMBOL
   * @see Character#MODIFIER_LETTER
   * @see Character#MODIFIER_SYMBOL
   * @see Character#NON_SPACING_MARK
   * @see Character#OTHER_LETTER
   * @see Character#OTHER_NUMBER
   * @see Character#OTHER_PUNCTUATION
   * @see Character#OTHER_SYMBOL
   * @see Character#PARAGRAPH_SEPARATOR
   * @see Character#PRIVATE_USE
   * @see Character#SPACE_SEPARATOR
   * @see Character#START_PUNCTUATION
   * @see Character#SURROGATE
   * @see Character#TITLECASE_LETTER
   * @see Character#UNASSIGNED
   * @see Character#UPPERCASE_LETTER
   */
  enum Category {

    /**
     * Lu, Uppercase_Letter – an uppercase letter.
     *
     * @see Character#UPPERCASE_LETTER
     */
    UPPERCASE_LETTER(Character.UPPERCASE_LETTER, "Lu", "Uppercase_Letter"),

    /**
     * Ll, Lowercase_Letter – a lowercase letter.
     *
     * @see Character#LOWERCASE_LETTER
     */
    LOWERCASE_LETTER(Character.LOWERCASE_LETTER, "Ll", "Lowercase_Letter"),

    /**
     * Lt, Titlecase_Letter – a digraphic character, with first part uppercase.
     *
     * @see Character#TITLECASE_LETTER
     */
    TITLECASE_LETTER(Character.TITLECASE_LETTER, "Lt", "Titlecase_Letter"),

    /**
     * LC, Cased_Letter – Lu | Ll | Lt.
     */
    CASED_LETTER("LC", "Cased_Letter", UPPERCASE_LETTER, LOWERCASE_LETTER, TITLECASE_LETTER),

    /**
     * Lm, Modifier_Letter – a modifier letter.
     *
     * @see Character#MODIFIER_LETTER
     */
    MODIFIER_LETTER(Character.MODIFIER_LETTER, "Lm", "Modifier_Letter"),

    /**
     * Lo, Other_Letter – other letters, including syllables and ideographs.
     *
     * @see Character#OTHER_LETTER
     */
    OTHER_LETTER(Character.OTHER_LETTER, "Lo", "Other_Letter"),

    /**
     * L, Letter – Lu | Ll | Lt | Lm | Lo.
     */
    LETTER("L", "Letter", UPPERCASE_LETTER, LOWERCASE_LETTER, TITLECASE_LETTER,
        MODIFIER_LETTER, OTHER_LETTER),

    /**
     * Mn, Nonspacing_Mark – a nonspacing combining mark (zero advance width).
     *
     * @see Character#NON_SPACING_MARK
     */
    NON_SPACING_MARK(Character.NON_SPACING_MARK, "Mn", "Nonspacing_Mark"),

    /**
     * Mc, Spacing_Mark – a spacing combining mark (positive advance width).
     *
     * @see Character#COMBINING_SPACING_MARK
     */
    COMBINING_SPACING_MARK(Character.COMBINING_SPACING_MARK, "Mc", "Spacing_Mark"),

    /**
     * Me, Enclosing_Mark – an enclosing combining mark.
     *
     * @see Character#ENCLOSING_MARK
     */
    ENCLOSING_MARK(Character.ENCLOSING_MARK, "Me", "Enclosing_Mark"),

    /**
     * M, Mark – Mn | Mc | Me.
     */
    MARK("M", "Mark", NON_SPACING_MARK, COMBINING_SPACING_MARK, ENCLOSING_MARK),

    /**
     * Nd, Decimal_Number – a decimal digit.
     *
     * @see Character#DECIMAL_DIGIT_NUMBER
     */
    DECIMAL_DIGIT_NUMBER(Character.DECIMAL_DIGIT_NUMBER, "Nd", "Decimal_Number"),

    /**
     * Nl, Letter_Number – a letterlike numeric character.
     *
     * @see Character#LETTER_NUMBER
     */
    LETTER_NUMBER(Character.LETTER_NUMBER, "Nl", "Letter_Number"),

    /**
     * No, Other_Number – a numeric character of other type.
     *
     * @see Character#OTHER_NUMBER
     */
    OTHER_NUMBER(Character.OTHER_NUMBER, "No", "Other_Number"),

    /**
     * N, Number – Nd | Nl | No.
     */
    NUMBER("N", "Number", DECIMAL_DIGIT_NUMBER, LETTER_NUMBER, OTHER_NUMBER),

    /**
     * Pc, Connector_Punctuation – a connecting punctuation mark, like a tie.
     *
     * @see Character#CONNECTOR_PUNCTUATION
     */
    CONNECTOR_PUNCTUATION(Character.CONNECTOR_PUNCTUATION, "Pc", "Connector_Punctuation"),

    /**
     * Pd, Dash_Punctuation – a dash or hyphen punctuation mark.
     *
     * @see Character#DASH_PUNCTUATION
     */
    DASH_PUNCTUATION(Character.DASH_PUNCTUATION, "Pd", "Dash_Punctuation"),

//    TODO In latest Unicode standard – support when available in JDK
//    /**
//     * Ps, Open_Punctuation – an opening punctuation mark (of a pair).
//     *
//     * @see Character#OPEN_PUNCTUATION
//     */
//    OPEN_PUNCTUATION(Character.OPEN_PUNCTUATION, "Ps", "Open_Punctuation"),
//
//    /**
//     * Pe, Close_Punctuation – a closing punctuation mark (of a pair).
//     *
//     * @see Character#CLOSE_PUNCTUATION
//     */
//    CLOSE_PUNCTUATION(Character.CLOSE_PUNCTUATION, "Pe", "Close_Punctuation"),

    /**
     * Pi, Initial_Punctuation – an initial quotation mark.
     *
     * @see Character#INITIAL_QUOTE_PUNCTUATION
     */
    INITIAL_QUOTE_PUNCTUATION(Character.INITIAL_QUOTE_PUNCTUATION, "Pi", "Initial_Punctuation"),

    /**
     * Pf, Final_Punctuation – a final quotation mark.
     *
     * @see Character#FINAL_QUOTE_PUNCTUATION
     */
    FINAL_QUOTE_PUNCTUATION(Character.FINAL_QUOTE_PUNCTUATION, "Pf", "Final_Punctuation"),

    /**
     * Po, Other_Punctuation – a punctuation mark of other type.
     *
     * @see Character#OTHER_PUNCTUATION
     */
    OTHER_PUNCTUATION(Character.OTHER_PUNCTUATION, "Po", "Other_Punctuation"),

    /**
     * P, Punctuation – Pc | Pd | Ps | Pe | Pi | Pf | Po.
     */
    PUNCTUATION("P", "Punctuation", CONNECTOR_PUNCTUATION, DASH_PUNCTUATION,
        /* OPEN_PUNCTUATION, CLOSE_PUNCTUATION */
        INITIAL_QUOTE_PUNCTUATION, FINAL_QUOTE_PUNCTUATION, OTHER_PUNCTUATION),

    /**
     * Sm, Math_Symbol – a symbol of mathematical use.
     *
     * @see Character#MATH_SYMBOL
     */
    MATH_SYMBOL(Character.MATH_SYMBOL, "Sm", "Math_Symbol"),

    /**
     * Sc, Currency_Symbol – a currency sign.
     *
     * @see Character#CURRENCY_SYMBOL
     */
    CURRENCY_SYMBOL(Character.CURRENCY_SYMBOL, "Sc", "Currency_Symbol"),

    /**
     * Sk, Modifier_Symbol – a non-letterlike modifier symbol.
     *
     * @see Character#MODIFIER_SYMBOL
     */
    MODIFIER_SYMBOL(Character.MODIFIER_SYMBOL, "Sk", "Modifier_Symbol"),

    /**
     * So, Other_Symbol – a symbol of other type.
     *
     * @see Character#OTHER_SYMBOL
     */
    OTHER_SYMBOL(Character.OTHER_SYMBOL, "So", "Other_Symbol"),

    /**
     * S, Symbol – Sm | Sc | Sk | So.
     */
    SYMBOL("S", "Symbol", MATH_SYMBOL, CURRENCY_SYMBOL, MODIFIER_SYMBOL, OTHER_SYMBOL),

    /**
     * Zs, Space_Separator – a space character (of various non-zero widths).
     *
     * @see Character#SPACE_SEPARATOR
     */
    SPACE_SEPARATOR(Character.SPACE_SEPARATOR, "Zs", "Space_Separator"),

    /**
     * Zl, Line_Separator – U+2028 LINE SEPARATOR only.
     *
     * @see Character#LINE_SEPARATOR
     */
    LINE_SEPARATOR(Character.LINE_SEPARATOR, "Zl", "Line_Separator"),

    /**
     * Zp, Paragraph_Separator – U+2029 PARAGRAPH SEPARATOR only.
     *
     * @see Character#PARAGRAPH_SEPARATOR
     */
    PARAGRAPH_SEPARATOR(Character.PARAGRAPH_SEPARATOR, "Zp", "Paragraph_Separator"),

    /**
     * Z, Separator – Zs | Zl | Zp.
     */
    SEPARATOR("Z", "Separator", SPACE_SEPARATOR, LINE_SEPARATOR, PARAGRAPH_SEPARATOR),

    /**
     * Cc, Control – a C0 or C1 control code.
     *
     * @see Character#CONTROL
     */
    CONTROL(Character.CONTROL, "Cc", "Control"),

    /**
     * Cf, Format – a format control character.
     *
     * @see Character#FORMAT
     */
    FORMAT(Character.FORMAT, "Cf", "Format"),

    /**
     * Cs, Surrogate – a surrogate code point.
     *
     * @see Character#SURROGATE
     */
    SURROGATE(Character.SURROGATE, "Cs", "Surrogate"),

    /**
     * Co, Private_Use – a private-use character.
     *
     * @see Character#PRIVATE_USE
     */
    PRIVATE_USE(Character.PRIVATE_USE, "Co", "Private_Use"),

    /**
     * Cn, Unassigned – a reserved unassigned code point or a noncharacter.
     *
     * @see Character#UNASSIGNED
     */
    UNASSIGNED(Character.UNASSIGNED, "Cn", "Unassigned"),

    /**
     * C, Other – Cc | Cf | Cs | Co | Cn.
     */
    OTHER("C", "Other", CONTROL, FORMAT, SURROGATE, PRIVATE_USE, UNASSIGNED);

    final int[] characterCategories;
    final String abbreviation;
    final String alias;
    final long bitMask;
    static final int MAX_CHARACTER_CATEGORY_VALUE = determineMaxCharacterValue();

    static int determineMaxCharacterValue() {
      int max = -1;
      for (Category category : values()) {
        for (int characterCategory : category.characterCategories) {
          max = Math.max(max, characterCategory);
        }
      }
      return max;
    }

    Category(final int characterCategory, final String abbreviation, final String alias) {
      this.characterCategories = new int[]{characterCategory};
      this.abbreviation = abbreviation;
      this.alias = alias;
      this.bitMask = 0x1 << characterCategory; // characterCategory values start from zero.
    }

    Category(final String abbreviation, final String alias, final Category... categories) {
      this.abbreviation = abbreviation;
      this.alias = alias;
      long tempBitMask = 0;
      int[] tempCharacterCategories = new int[categories.length];
      int i = 0;
      for (Category category : categories) {
        tempBitMask |= category.bitMask;
        if (i == tempCharacterCategories.length) {
          tempCharacterCategories = Arrays.copyOf(tempCharacterCategories, tempCharacterCategories.length + 8);
        }
        for (int characterCategory : category.characterCategories) {
          tempCharacterCategories[i++] = characterCategory;
        }
      }
      this.characterCategories = i < tempCharacterCategories.length
          ? Arrays.copyOf(tempCharacterCategories, --i) : tempCharacterCategories;
      this.bitMask = tempBitMask;
    }

  }
}
