package land.artli.easytype;

import static java.lang.Character.isWhitespace;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import land.artli.easytype.TypeParser.CodePointConversions.CodePointConversionsBuilder;
import land.artli.easytype.TypeParser.CodePointRanges.CodePointRangesBuilder;
import land.artli.easytype.Unicode.Category;
import land.artli.easytype.Unicode.Other;
import land.artli.easytype.Unicode.RangedSubset;
import land.artli.easytype.Unicode.Subset;

public class TypeParser {

  private final Class<? extends CharType> targetClass;
  private final String errorMessage;
  private final TargetCase targetCase;
  private final WhiteSpace whiteSpace;
  private final NullHandling nullHandling;
  private final int minNumberOfCodePoints;
  private final int maxNumberOfCodePoints;
  private final Subset acceptedCodePoints;
  private final CodePointConversions codePointConversions;

  private TypeParser(
      final Class<? extends CharType> targetClass,
      final String errorMessage,
      final TargetCase targetCase,
      final WhiteSpace whiteSpace,
      final NullHandling nullHandling,
      final int minNumberOfCodePoints, final int maxNumberOfCodePoints,
      final Subset acceptedCodePoints,
      final CodePointConversions codePointConversions) {
    this.targetClass = targetClass;
    this.errorMessage = errorMessage;
    this.targetCase = targetCase;
    this.whiteSpace = whiteSpace;
    this.nullHandling = nullHandling;
    this.minNumberOfCodePoints = minNumberOfCodePoints;
    this.maxNumberOfCodePoints = maxNumberOfCodePoints;
    this.acceptedCodePoints = acceptedCodePoints;
    this.codePointConversions = codePointConversions;
  }

  public static TypeParserBuilder builder(final Class<? extends CharType> targetClass) {
    return new TypeParserBuilder(targetClass);
  }

  public <T extends CharType<?>> T parse(final CharSequence value, Function<String, T> constructorOrFactoryMethod) throws ParseException {
    return constructorOrFactoryMethod.apply(parse(value));
  }

  public String parse(final CharSequence value) throws ParseException {
    if (value == null) {
      return switch (nullHandling) {
        case PRESERVE_NULL_AND_EMPTY, CONVERT_EMPTY_TO_NULL -> null;
        case CONVERT_NULL_TO_EMPTY -> "";
      };
    }
    final int length = value.length();
    final int endIndex = endIndexIgnoringTrailingWhitespace(value);
    final int startIndex = startIndexIgnoringLeadingWhitespace(value, endIndex);
    int[] result = new int[length + codePointConversions.maxToCodePointsLength * 4];
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
      // TODO handle case where surrogate is incomplete because ++i is greater than length
      codePoint = Character.isSurrogate(ch) ? Character.toCodePoint(ch, value.charAt(++i)) : (int) ch;

      if (acceptedCodePoints.isNotInSubset(codePoint)) {
        throw InvalidTypeValueException.forInvalidCodePoint(errorMessage, targetClass, value, i, codePoint);
      }
      if (Character.isWhitespace(codePoint)) {
        switch (whiteSpace) {
          case PRESERVE_WHITESPACE:
            result = appendCodePoint(result, k++, codePoint);
            while (++i < length && Character.isWhitespace(codePoint = value.charAt(i)) && acceptedCodePoints.isInSubset(codePoint)) {
              result = appendCodePoint(result, k++, codePoint);
            }
            break;
          case PRESERVE_AND_CONVERT_WHITESPACE:
            toCodePoints = codePointConversions.convertCodePoint(codePoint, reusableSingleCodePointArray);
            for (int j = 0; j < toCodePoints.length; ++j) {
              result = appendCodePoint(result, k++, toCodePoints[j]);
            }
            while (++i < length && Character.isWhitespace(codePoint = value.charAt(i)) && acceptedCodePoints.isInSubset(codePoint)) {
              toCodePoints = codePointConversions.convertCodePoint(codePoint, reusableSingleCodePointArray);
              for (int j = 0; j < toCodePoints.length; ++j) {
                result = appendCodePoint(result, k++, toCodePoints[j]);
              }
            }
            break;
          case NORMALIZE_WHITESPACE:
            result = appendCodePoint(result, k++, ' ');
            while (++i < length && Character.isWhitespace(codePoint = value.charAt(i)) && acceptedCodePoints.isInSubset(codePoint)) {
              // ignore extra whitespace;
            }
            break;
          case NORMALIZE_AND_CONVERT_WHITESPACE:
            toCodePoints = codePointConversions.convertCodePoint(codePoint, reusableSingleCodePointArray);
            for (int j = 0; j < toCodePoints.length; ++j) {
              result = appendCodePoint(result, k++, toCodePoints[j]);
            }
            while (++i < length && Character.isWhitespace(codePoint = value.charAt(i)) && acceptedCodePoints.isInSubset(codePoint)) {
              // ignore extra whitespace;
            }
            break;
          case REMOVE_WHITESPACE:
            while (++i < length && Character.isWhitespace(codePoint = value.charAt(i)) && acceptedCodePoints.isInSubset(codePoint)) {
              // ignore extra whitespace;
            }
            break;
        }
      }
      if (acceptedCodePoints.isNotInSubset(codePoint)) {
        throw InvalidTypeValueException.forInvalidCodePoint(errorMessage, targetClass, value, i, codePoint);
      }
      toCodePoints = codePointConversions.convertCodePoint(codePoint, reusableSingleCodePointArray);
      for (int j = 0; j < toCodePoints.length; ++j) {
        codePoint = toCodePoints[j];
        if (k >= maxNumberOfCodePoints) {
          throw InvalidTypeValueException.forValueTooLong(errorMessage, targetClass, value, maxNumberOfCodePoints);
        }
        codePointConversion = codePointConversions.getCodePointConversion(codePoint);
        codePoint = codePointConversion == null ? codePoint : (char) codePointConversion.toCodePoints[0];
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

    private final Class<? extends CharType> targetClass;
    private String errorMessage;
    private WhiteSpace whiteSpace = WhiteSpace.NORMALIZE_WHITESPACE;
    private NullHandling nullHandling = NullHandling.PRESERVE_NULL_AND_EMPTY;
    private int minNumberOfCodePoints = 0;
    private int maxNumberOfCodePoints = 64;
    private TargetCase targetCase = TargetCase.PRESERVE_CASE;
    private final CodePointRangesBuilder codePointRangesBuilder = CodePointRanges.builder();
    private final CodePointConversionsBuilder codePointConversionsBuilder = CodePointConversions.builder();
    private final List<TypeParserBuilder> logicalOr = new ArrayList<>();

    TypeParserBuilder(final Class<? extends CharType> targetClass) {
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

    public TypeParserBuilder acceptAllDashes() {
      codePointRangesBuilder.addAllDashes();
      return this;
    }

    public TypeParserBuilder convertAllDashesToHyphen() {
      return convertAllDashesTo((int) '-');
    }

    public TypeParserBuilder convertAllDashesTo(final char toChar) {
      return convertAllDashesTo((int) toChar);
    }

    public TypeParserBuilder convertAllDashesTo(final int toCodePoint) {
      codePointConversionsBuilder.addCodePointConversions(Category.DASH_PUNCTUATION, toCodePoint);
      return this;
    }

    public TypeParserBuilder convertAllDashesTo(final CharSequence toCharSequence) {
      codePointConversionsBuilder.addCodePointConversions(Category.DASH_PUNCTUATION, toCharSequence);
      return this;
    }

    public TypeParserBuilder acceptAllWhitespace() {
      codePointRangesBuilder.addAllWhiteSpace();
      return this;
    }

    public TypeParserBuilder acceptAndRemoveAllWhitespace() {
      acceptAllWhitespace(); // whitespace characters need to be accepted in the input otherwise exception thrown
      whiteSpace = WhiteSpace.REMOVE_WHITESPACE;
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
      codePointConversionsBuilder.addCodePointConversions(Other.WHITESPACE, toChar);
      return this;
    }

    public TypeParserBuilder normalizeAndConvertWhitespaceTo(final int toCodePoint) {
      whiteSpace = WhiteSpace.NORMALIZE_AND_CONVERT_WHITESPACE;
      codePointConversionsBuilder.addCodePointConversions(Other.WHITESPACE, toCodePoint);
      return this;
    }

    public TypeParserBuilder normalizeAndConvertWhitespaceTo(final CharSequence toCharSequence) {
      whiteSpace = WhiteSpace.NORMALIZE_AND_CONVERT_WHITESPACE;
      codePointConversionsBuilder.addCodePointConversions(Other.WHITESPACE, toCharSequence);
      return this;
    }

    public TypeParserBuilder preserveWhitespace() {
      whiteSpace = WhiteSpace.PRESERVE_WHITESPACE;
      return this;
    }

    public TypeParserBuilder preserveAndConvertWhitespaceTo(final char toChar) {
      whiteSpace = WhiteSpace.PRESERVE_AND_CONVERT_WHITESPACE;
      codePointConversionsBuilder.addCodePointConversions(Other.WHITESPACE, toChar);
      return this;
    }

    public TypeParserBuilder preserveAndConvertWhitespaceTo(final int toCodePoint) {
      whiteSpace = WhiteSpace.PRESERVE_AND_CONVERT_WHITESPACE;
      codePointConversionsBuilder.addCodePointConversions(Other.WHITESPACE, toCodePoint);
      return this;
    }

    public TypeParserBuilder preserveAndConvertWhitespaceTo(final CharSequence toCharSequence) {
      whiteSpace = WhiteSpace.PRESERVE_AND_CONVERT_WHITESPACE;
      codePointConversionsBuilder.addCodePointConversions(Other.WHITESPACE, toCharSequence);
      return this;
    }

    public TypeParser build() {
      return new TypeParser(
          targetClass, errorMessage, targetCase,
          whiteSpace, nullHandling,
          minNumberOfCodePoints, maxNumberOfCodePoints,
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

    final static int[] SLOT_SIZES = {0, 7, 11, 17, 37, 67, 101, 211, 401, 601, 809, 1009, 1511, 2003, 2503, 3001, 4001, 6007, 7919};

    final CodePointConversion[][] codePointConversions;

    final int maxToCodePointsLength;

    static CodePointConversionsBuilder builder() {
      return new CodePointConversionsBuilder();
    }

    private CodePointConversions(final CodePointConversion[] codePointConversions) {
      if (codePointConversions == null || codePointConversions.length == 0) {
        this.codePointConversions = null;
        this.maxToCodePointsLength = 0;
      } else {
        int slots = determineNumberOfSlots(codePointConversions);
        this.codePointConversions = new CodePointConversion[slots][];
        int maxToCodePointsLength = 0;
        for (CodePointConversion codePointConversion : codePointConversions) {
          maxToCodePointsLength = Math.max(maxToCodePointsLength, codePointConversion.toCodePoints.length);
          addConversion(this.codePointConversions, codePointConversion);
        }
        this.maxToCodePointsLength = maxToCodePointsLength;
      }
    }

    private static int determineNumberOfSlots(final CodePointConversion[] codePointConversions) {
      final int target = codePointConversions.length;

      int low = 0;
      int high = SLOT_SIZES.length - 1;
      while (low <= high) {
        int midA = (low + high) >>> 1;
        int midB = midA + 1;
        int midValA = SLOT_SIZES[midA];
        int midValB = midB < SLOT_SIZES.length ? SLOT_SIZES[midB] : midValA + 1;

        if (target > midValB) {
          low = midA + 1;
        } else if (target < midValA) {
          high = midA - 1;
        } else {
          return Math.max(7, midValA); // key found
        }
      }
      return Math.max(7, (int) (codePointConversions.length * 0.8)); // key not found.
    }


    private static void addConversion(
        final CodePointConversion[][] codePointConversions,
        final CodePointConversion codePointConversion) {
      final int slot = codePointConversion.fromCodePoint % codePointConversions.length;
      CodePointConversion[] buckets = codePointConversions[slot];
      if (buckets == null) {
        buckets = new CodePointConversion[4];
        codePointConversions[slot] = buckets;
      }
      int i = 0;
      for (; i < buckets.length && buckets[i] != null; ++i) {
        if (buckets[i].fromCodePoint == codePointConversion.fromCodePoint) {
          buckets[i] = codePointConversion;
          return;
        }
      }
      if (i == buckets.length) {
        buckets = Arrays.copyOf(buckets, buckets.length + 4);
        codePointConversions[slot] = buckets;
      }
      buckets[i] = codePointConversion;
    }

    CodePointConversion getCodePointConversion(final char fromChar) {
      return getCodePointConversion((int) fromChar);
    }

    CodePointConversion getCodePointConversion(final int fromCodePoint) {
      if (codePointConversions == null) {
        return null;
      }
      final int slot = fromCodePoint % codePointConversions.length;
      CodePointConversion[] buckets = codePointConversions[slot];
      if (buckets == null) {
        return null;
      }
      for (int i = 0; i < buckets.length && buckets[i] != null; ++i) {
        if (buckets[i].fromCodePoint == fromCodePoint) {
          return buckets[i];
        }
      }
      return null;
    }

    int[] convertCodePoint(final int fromCodePoint, final int[] useThisArrayWhenNoConversionRequired) {
      final CodePointConversion codePointConversion = getCodePointConversion(fromCodePoint);
      if (codePointConversion == null) {
        useThisArrayWhenNoConversionRequired[0] = fromCodePoint;
        return useThisArrayWhenNoConversionRequired;
      }
      return codePointConversion.toCodePoints;
    }

    static class CodePointConversionsBuilder {

      CodePointConversion[] codePointConversions = new CodePointConversion[8];
      private int endIndex = 0;

      private CodePointConversionsBuilder() {
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
          final char[] singleByteCodePointRanges = subset.getSingleByteCodePointRanges();
          for (int i = 0; i < singleByteCodePointRanges.length; ++i) {
            final int inclusiveFrom = RangedSubset.getInclusiveFrom(singleByteCodePointRanges[i]);
            final int inclusiveTo = RangedSubset.getInclusiveTo(singleByteCodePointRanges[i]);
            for (int j = inclusiveFrom; j <= inclusiveTo; ++j) {
              addCodePointConversion(j, toCodePoints);
            }
          }
          final int[] doubleByteCodePointRanges = subset.getDoubleByteCodePointRanges();
          for (int i = 0; i < doubleByteCodePointRanges.length; ++i) {
            final int inclusiveFrom = RangedSubset.getInclusiveFrom(doubleByteCodePointRanges[i]);
            final int inclusiveTo = RangedSubset.getInclusiveTo(doubleByteCodePointRanges[i]);
            for (int j = inclusiveFrom; j <= inclusiveTo; ++j) {
              addCodePointConversion(j, toCodePoints);
            }
          }
          final long[] tripleByteCodePointRanges = subset.getTripleByteCodePointRanges();
          for (int i = 0; i < tripleByteCodePointRanges.length; ++i) {
            final int inclusiveFrom = RangedSubset.getInclusiveFrom(tripleByteCodePointRanges[i]);
            final int inclusiveTo = RangedSubset.getInclusiveTo(tripleByteCodePointRanges[i]);
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
        if (endIndex == codePointConversions.length) {
          codePointConversions = Arrays.copyOf(codePointConversions, Math.round(codePointConversions.length * 1.5F));
        }
        codePointConversions[endIndex] = new CodePointConversion(fromCodePoint, toCodePoints);
        endIndex++;
      }

      CodePointConversions build() {
        return new CodePointConversions(Arrays.copyOf(codePointConversions, endIndex));
      }
    }
  }

  static class CodePointRanges implements RangedSubset {
    private final char [] singleByteCodePointRanges;
    private final int [] doubleByteCodePointRanges;
    private final long [] tripleByteCodePointRanges;

    private CodePointRanges(
        final char [] singleByteCodePointRanges,
        final int [] doubleByteCodePointRanges,
        final long [] tripleByteCodePointRanges) {
      this.singleByteCodePointRanges = singleByteCodePointRanges;
      this.doubleByteCodePointRanges = doubleByteCodePointRanges;
      this.tripleByteCodePointRanges = tripleByteCodePointRanges;
    }

    static CodePointRangesBuilder builder() {
      return new CodePointRangesBuilder();
    }

    public char[] getSingleByteCodePointRanges() {
      return singleByteCodePointRanges;
    }

    public int[] getDoubleByteCodePointRanges() {
      return doubleByteCodePointRanges;
    }

    public long[] getTripleByteCodePointRanges() {
      return tripleByteCodePointRanges;
    }

    boolean isAcceptedCodePoint(final int codePoint) {
      return getCodePointRange(codePoint) != Long.MIN_VALUE;
    }

    boolean isNotAcceptedCodePoint(final int codePoint) {
      return !isAcceptedCodePoint(codePoint);
    }

    private long getCodePointRange(final int codePoint) {
      if (codePoint > 0xffff) {
        if (tripleByteCodePointRanges.length == 0 ||
            codePoint < RangedSubset.getInclusiveFrom(tripleByteCodePointRanges[0]) ||
            codePoint > RangedSubset.getInclusiveTo(tripleByteCodePointRanges[tripleByteCodePointRanges.length - 1])) {
          return Long.MIN_VALUE; // not found, outside of range
        }
        // Try to find using a binary search
        int low = 0;
        int high = tripleByteCodePointRanges.length - 1;
        while (low <= high) {
          final int mid = (low + high) >>> 1;
          final long midVal = tripleByteCodePointRanges[mid];
          final int inclusiveFrom = RangedSubset.getInclusiveFrom(midVal);
          final int inclusiveTo = RangedSubset.getInclusiveTo(midVal);
          if (codePoint > inclusiveTo) {
            low = mid + 1;
          } else if (codePoint < inclusiveFrom) {
            high = mid - 1;
          } else if (codePoint >= inclusiveFrom && codePoint <= inclusiveTo) {
            return midVal; // found
          } else {
            return Long.MIN_VALUE; // not found
          }
        }
      } else {
        if (doubleByteCodePointRanges.length == 0 ||
            codePoint < RangedSubset.getInclusiveFrom(doubleByteCodePointRanges[0]) ||
            codePoint > RangedSubset.getInclusiveTo(doubleByteCodePointRanges[doubleByteCodePointRanges.length - 1])) {
          return Long.MIN_VALUE; // not found, outside of range
        }
        // Try to find using a binary search
        int low = 0;
        int high = doubleByteCodePointRanges.length - 1;
        while (low <= high) {
          final int mid = (low + high) >>> 1;
          final int inclusiveFrom = RangedSubset.getInclusiveFrom(doubleByteCodePointRanges[mid]);
          final int inclusiveTo = RangedSubset.getInclusiveTo(doubleByteCodePointRanges[mid]);
          if (codePoint > inclusiveTo) {
            low = mid + 1;
          } else if (codePoint < inclusiveFrom) {
            high = mid - 1;
          } else if (codePoint >= inclusiveFrom && codePoint <= inclusiveTo) {
            return RangedSubset.rangeToLong(inclusiveFrom, inclusiveTo); // found
          } else {
            return Long.MIN_VALUE; // not found
          }
        }
      }
      return Long.MIN_VALUE; // not found
    }


    static class CodePointRangesBuilder {

      private char [] singleByteCodePointRanges = new char[16];
      private int [] doubleByteCodePointRanges = new int[16];
      private long [] tripleByteCodePointRanges = new long[4];
      private int singleByteEndIndex = 0;
      private int doubleByteEndIndex = 0;
      private int tripleByteEndIndex = 0;

      void addAllWhiteSpace() {
        addSubset(Other.WHITESPACE);
      }

      void addAllDashes() {
        addSubset(Category.DASH_PUNCTUATION);
      }

      void addSubset(final RangedSubset subset) {
        if (subset != null) {
          final char[] singleByteCodePointRanges = subset.getSingleByteCodePointRanges();
          ensureDoubleByteCapacity(singleByteCodePointRanges.length);
          for (int i = 0; i < singleByteCodePointRanges.length; ++i) {
            addCodePointRange(
                RangedSubset.getInclusiveFrom(singleByteCodePointRanges[i]),
                RangedSubset.getInclusiveTo(singleByteCodePointRanges[i]));
          }
          final int[] doubleByteCodePointRanges = subset.getDoubleByteCodePointRanges();
          ensureDoubleByteCapacity(doubleByteCodePointRanges.length);
          for (int i = 0; i < doubleByteCodePointRanges.length; ++i) {
            addCodePointRange(
                RangedSubset.getInclusiveFrom(doubleByteCodePointRanges[i]),
                RangedSubset.getInclusiveTo(doubleByteCodePointRanges[i]));
          }
          final long[] tripleByteCodePointRanges = subset.getTripleByteCodePointRanges();
          ensureTripleByteCapacity(tripleByteCodePointRanges.length);
          for (int i = 0; i < tripleByteCodePointRanges.length; ++i) {
            addCodePointRange(
                RangedSubset.getInclusiveFrom(tripleByteCodePointRanges[i]),
                RangedSubset.getInclusiveTo(tripleByteCodePointRanges[i]));
          }
        }
      }

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

      void addCodePoints(final int ... codePoints) {
        if (codePoints != null) {
          ensureDoubleByteCapacity(codePoints.length);
          for (int i = 0; i < codePoints.length; ++i) {
            addCodePoint(codePoints[i]);
          }
        }
      }

      void addCodePointRanges(final char [] codePointRanges) {
        if (codePointRanges != null) {
          ensureSingleByteCapacity(codePointRanges.length);
          for (int i = 0; i < codePointRanges.length; ++i) {
            addCodePointRange(RangedSubset.getInclusiveFrom(codePointRanges[i]), RangedSubset.getInclusiveTo(codePointRanges[i]));
          }
        }
      }

      void addCodePointRanges(final int [] codePointRanges) {
        if (codePointRanges != null) {
          ensureDoubleByteCapacity(codePointRanges.length);
          for (int i = 0; i < codePointRanges.length; ++i) {
            addCodePointRange(RangedSubset.getInclusiveFrom(codePointRanges[i]), RangedSubset.getInclusiveTo(codePointRanges[i]));
          }
        }
      }

      void addCodePointRanges(final long [] codePointRanges) {
        if (codePointRanges != null) {
          ensureTripleByteCapacity(codePointRanges.length);
          for (int i = 0; i < codePointRanges.length; ++i) {
            addCodePointRange(RangedSubset.getInclusiveFrom(codePointRanges[i]), RangedSubset.getInclusiveTo(codePointRanges[i]));
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

      private static void unsignedIntegerBubbleSort(final int [] values, int fromIndex, int toIndex) {
        int temp;
        for (int i = fromIndex; i < toIndex; i++) {
          for (int j = fromIndex; j < toIndex - i - 1; j++) {
            if ((0x00000000_ffffffffL & (long)values[j]) > (0x00000000_ffffffffL & (long)values[j + 1])) {
              // swap the elements
              temp = values[j];
              values[j] = values[j + 1];
              values[j + 1] = temp;
            }
          }
        }
      }

      CodePointRanges build() {
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
            previousInclusiveFrom =RangedSubset. getInclusiveFrom(doubleByteCodePointRanges[i]);
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
        return new CodePointRanges(
            Arrays.copyOf(singleByteCodePointRanges, singleByteEndIndex),
            Arrays.copyOf(doubleByteCodePointRanges, doubleByteEndIndex),
            Arrays.copyOf(tripleByteCodePointRanges, tripleByteEndIndex));
      }
    }
  }


  enum TargetCase {
    PRESERVE_CASE,
    TO_UPPER_CASE,
    TO_LOWER_CASE,
    TO_TITLE_CASE,
  }

  enum WhiteSpace {
    PRESERVE_WHITESPACE,
    PRESERVE_AND_CONVERT_WHITESPACE,
    NORMALIZE_WHITESPACE,
    NORMALIZE_AND_CONVERT_WHITESPACE,
    REMOVE_WHITESPACE,
  }

  enum NullHandling {
    PRESERVE_NULL_AND_EMPTY,
    CONVERT_NULL_TO_EMPTY,
    CONVERT_EMPTY_TO_NULL;
  }

}
