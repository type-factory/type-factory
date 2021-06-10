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

public class TypeParser {

  private final Class<? extends CharType> targetClass;
  private final String errorMessage;
  private final TargetCase targetCase;
  private final WhiteSpace whiteSpace;
  private final NullHandling nullHandling;
  private final int minNumberOfCodePoints;
  private final int maxNumberOfCodePoints;
  private final CodePointRanges acceptedCodePointRanges;
  private final CodePointConversions codePointConversions;

  private TypeParser(
      final Class<? extends CharType> targetClass,
      final String errorMessage,
      final TargetCase targetCase,
      final WhiteSpace whiteSpace,
      final NullHandling nullHandling,
      final int minNumberOfCodePoints, final int maxNumberOfCodePoints,
      final CodePointRanges acceptedCodePointRanges,
      final CodePointConversions codePointConversions) {
    this.targetClass = targetClass;
    this.errorMessage = errorMessage;
    this.targetCase = targetCase;
    this.whiteSpace = whiteSpace;
    this.nullHandling = nullHandling;
    this.minNumberOfCodePoints = minNumberOfCodePoints;
    this.maxNumberOfCodePoints = maxNumberOfCodePoints;
    this.acceptedCodePointRanges = acceptedCodePointRanges;
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

      if (acceptedCodePointRanges.isNotAcceptedCodePoint(codePoint)) {
        throw InvalidTypeValueException.forInvalidCodePoint(errorMessage, targetClass, value, i, codePoint);
      }
      if (Character.isWhitespace(codePoint)) {
        switch (whiteSpace) {
          case PRESERVE_WHITESPACE:
            result = appendCodePoint(result, k++, codePoint);
            while (++i < length && Character.isWhitespace(codePoint = value.charAt(i)) && acceptedCodePointRanges.isAcceptedCodePoint(codePoint)) {
              result = appendCodePoint(result, k++, codePoint);
            }
            break;
          case PRESERVE_AND_CONVERT_WHITESPACE:
            toCodePoints = codePointConversions.convertCodePoint(codePoint, reusableSingleCodePointArray);
            for (int j = 0; j < toCodePoints.length; ++j) {
              result = appendCodePoint(result, k++, toCodePoints[j]);
            }
            while (++i < length && Character.isWhitespace(codePoint = value.charAt(i)) && acceptedCodePointRanges.isAcceptedCodePoint(codePoint)) {
              toCodePoints = codePointConversions.convertCodePoint(codePoint, reusableSingleCodePointArray);
              for (int j = 0; j < toCodePoints.length; ++j) {
                result = appendCodePoint(result, k++, toCodePoints[j]);
              }
            }
            break;
          case NORMALIZE_WHITESPACE:
            result = appendCodePoint(result, k++, ' ');
            while (++i < length && Character.isWhitespace(codePoint = value.charAt(i)) && acceptedCodePointRanges.isAcceptedCodePoint(codePoint)) {
              // ignore extra whitespace;
            }
            break;
          case NORMALIZE_AND_CONVERT_WHITESPACE:
            toCodePoints = codePointConversions.convertCodePoint(codePoint, reusableSingleCodePointArray);
            for (int j = 0; j < toCodePoints.length; ++j) {
              result = appendCodePoint(result, k++, toCodePoints[j]);
            }
            while (++i < length && Character.isWhitespace(codePoint = value.charAt(i)) && acceptedCodePointRanges.isAcceptedCodePoint(codePoint)) {
              // ignore extra whitespace;
            }
            break;
          case REMOVE_WHITESPACE:
            while (++i < length && Character.isWhitespace(codePoint = value.charAt(i)) && acceptedCodePointRanges.isAcceptedCodePoint(codePoint)) {
              // ignore extra whitespace;
            }
            break;
        }
      }
      if (acceptedCodePointRanges.isNotAcceptedCodePoint(codePoint)) {
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
      codePointConversionsBuilder.addCodePointConversions(DashPunctuationCharacters.getAllDashPunctuationCodePoints(), toCodePoint);
      return this;
    }

    public TypeParserBuilder convertAllDashesTo(final CharSequence toCharSequence) {
      codePointConversionsBuilder.addCodePointConversions(DashPunctuationCharacters.getAllDashPunctuationCodePoints(), toCharSequence);
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
      codePointConversionsBuilder.addCodePointConversions(WhiteSpaceCharacters.getAllWhiteSpaceCodePoints(), toChar);
      return this;
    }

    public TypeParserBuilder normalizeAndConvertWhitespaceTo(final int toCodePoint) {
      whiteSpace = WhiteSpace.NORMALIZE_AND_CONVERT_WHITESPACE;
      codePointConversionsBuilder.addCodePointConversions(WhiteSpaceCharacters.getAllWhiteSpaceCodePoints(), toCodePoint);
      return this;
    }

    public TypeParserBuilder normalizeAndConvertWhitespaceTo(final CharSequence toCharSequence) {
      whiteSpace = WhiteSpace.NORMALIZE_AND_CONVERT_WHITESPACE;
      codePointConversionsBuilder.addCodePointConversions(WhiteSpaceCharacters.getAllWhiteSpaceCodePoints(), toCharSequence);
      return this;
    }

    public TypeParserBuilder preserveWhitespace() {
      whiteSpace = WhiteSpace.PRESERVE_WHITESPACE;
      return this;
    }

    public TypeParserBuilder preserveAndConvertWhitespaceTo(final char toChar) {
      whiteSpace = WhiteSpace.PRESERVE_AND_CONVERT_WHITESPACE;
      codePointConversionsBuilder.addCodePointConversions(WhiteSpaceCharacters.getAllWhiteSpaceCodePoints(), toChar);
      return this;
    }

    public TypeParserBuilder preserveAndConvertWhitespaceTo(final int toCodePoint) {
      whiteSpace = WhiteSpace.PRESERVE_AND_CONVERT_WHITESPACE;
      codePointConversionsBuilder.addCodePointConversions(WhiteSpaceCharacters.getAllWhiteSpaceCodePoints(), toCodePoint);
      return this;
    }

    public TypeParserBuilder preserveAndConvertWhitespaceTo(final CharSequence toCharSequence) {
      whiteSpace = WhiteSpace.PRESERVE_AND_CONVERT_WHITESPACE;
      codePointConversionsBuilder.addCodePointConversions(WhiteSpaceCharacters.getAllWhiteSpaceCodePoints(), toCharSequence);
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

      void addCodePointConversions(final int[] fromCodePoints, final int toCodePoint) {
        if (fromCodePoints != null) {
          for (int i = 0; i < fromCodePoints.length; ++i) {
            addCodePointConversion(fromCodePoints[i], new int[]{toCodePoint});
          }
        }
      }

      void addCodePointConversions(final int[] fromCodePoints, final CharSequence toCharSequence) {
        if (fromCodePoints != null) {
          for (int i = 0; i < fromCodePoints.length; ++i) {
            addCodePointConversion(fromCodePoints[i], toCharSequence.codePoints().toArray());
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

  static class CodePointRanges {

    private final int [] lowerCodePointRanges;
    private final long [] upperCodePointRanges;

    private CodePointRanges(
        final int [] lowerCodePointRanges,
        final long [] upperCodePointRanges) {
      this.lowerCodePointRanges = lowerCodePointRanges;
      this.upperCodePointRanges = upperCodePointRanges;
    }

    static CodePointRangesBuilder builder() {
      return new CodePointRangesBuilder();
    }


    boolean isAcceptedCodePoint(final int codePoint) {
      return getCodePointRange(codePoint) != Long.MAX_VALUE;
    }

    boolean isNotAcceptedCodePoint(final int codePoint) {
      return !isAcceptedCodePoint(codePoint);
    }

    private long getCodePointRange(final int codePoint) {
      if (codePoint > 0xffff) {
        if (upperCodePointRanges.length == 0 ||
            codePoint < (int)(upperCodePointRanges[0] >>> 32) ||
            codePoint > (int)(upperCodePointRanges[upperCodePointRanges.length - 1])) {
          return Long.MAX_VALUE; // not found, outside of range
        }
        // Try to find using a binary search
        int low = 0;
        int high = upperCodePointRanges.length - 1;
        while (low <= high) {
          final int mid = (low + high) >>> 1;
          final long midVal = upperCodePointRanges[mid];
          final int inclusiveFrom = getInclusiveFrom(midVal);
          final int inclusiveTo = getInclusiveTo(midVal);
          if (codePoint > inclusiveTo) {
            low = mid + 1;
          } else if (codePoint < inclusiveFrom) {
            high = mid - 1;
          } else if (codePoint >= inclusiveFrom && codePoint <= inclusiveTo) {
            return midVal; // found
          } else {
            return Long.MAX_VALUE; // not found
          }
        }
      } else {
        if (lowerCodePointRanges.length == 0 ||
            codePoint < (lowerCodePointRanges[0] >>> 16) ||
            codePoint > (lowerCodePointRanges[lowerCodePointRanges.length - 1] & 0x0000ffff)) {
          return Long.MAX_VALUE; // not found, outside of range
        }
        // Try to find using a binary search
        int low = 0;
        int high = lowerCodePointRanges.length - 1;
        while (low <= high) {
          final int mid = (low + high) >>> 1;
          final int inclusiveFrom = getInclusiveFrom(lowerCodePointRanges[mid]);
          final int inclusiveTo = getInclusiveTo(lowerCodePointRanges[mid]);
          if (codePoint > inclusiveTo) {
            low = mid + 1;
          } else if (codePoint < inclusiveFrom) {
            high = mid - 1;
          } else if (codePoint >= inclusiveFrom && codePoint <= inclusiveTo) {
            return rangeToLong(inclusiveFrom, inclusiveTo); // found
          } else {
            return Long.MAX_VALUE; // not found
          }
        }
      }
      return Long.MAX_VALUE; // not found
    }

    private static char getInclusiveFrom(final int codePaintRange) {
      return (char)(codePaintRange >>> 16);
    }

    private static char getInclusiveTo(final int codePaintRange) {
      return (char)codePaintRange;
    }

    private static int getInclusiveFrom(final long codePaintRange) {
      return (int)(codePaintRange >>> 32);
    }

    private static int getInclusiveTo(final long codePaintRange) {
      return (int)codePaintRange;
    }

    private static int rangeToInt(final char inclusiveFrom, final char inclusiveTo) {
      return (((int)inclusiveFrom) << 16) | (int)inclusiveTo;
    }

    private static long rangeToLong(final int inclusiveFrom, final int inclusiveTo) {
      return (((long)inclusiveFrom) << 32) | (long)inclusiveTo;
    }

    static class CodePointRangesBuilder {

      private int [] lowerCodePointRanges = new int[16];
      private long [] upperCodePointRanges = new long[4];
      private int lowerEndIndex = 0;
      private int upperEndIndex = 0;

      void addAllWhiteSpace() {
        addCodePoints(WhiteSpaceCharacters.getAllWhiteSpaceCodePoints());
      }

      void addAllDashes() {
        addCodePoints(DashPunctuationCharacters.getAllDashPunctuationCodePoints());
      }

      void addChar(final char ch) {
        addCharRange(ch, ch);
      }

      void addChars(final char... chars) {
        if (chars != null) {
          ensureLowerCapacity(chars.length);
          for (int i = 0; i < chars.length; ++i) {
            addChar(chars[i]);
          }
        }
      }

      void addCharRange(final char inclusiveFrom, final char inclusiveTo) {
        ensureLowerCapacity();
        lowerCodePointRanges[lowerEndIndex] = rangeToInt(inclusiveFrom, inclusiveTo);
        ++lowerEndIndex;
      }

      void addCodePoint(final int codePoint) {
        addCodePointRange(codePoint, codePoint);
      }

      void addCodePoints(final int ... codePoints) {
        if (codePoints != null) {
          ensureLowerCapacity(codePoints.length);
          for (int i = 0; i < codePoints.length; ++i) {
            addCodePoint(codePoints[i]);
          }
        }
      }

      void addCodePointRange(final int [] codePointRanges) {
        if (codePointRanges != null) {
          ensureLowerCapacity(codePointRanges.length);
          for (int i = 0; i < codePointRanges.length; ++i) {
            addCodePointRange(codePointRanges[i]);
          }
        }
      }

      void addCodePointRange(final long [] codePointRanges) {
        if (codePointRanges != null) {
          ensureUpperCapacity(codePointRanges.length);
          for (int i = 0; i < codePointRanges.length; ++i) {
            addCodePointRange(codePointRanges[i]);
          }
        }
      }

      void addCodePointRange(final long codePointRange) {
        addCodePointRange((int)(codePointRange >>> 32), (int)codePointRange);
      }

      void addCodePointRange(final int inclusiveFrom, final int inclusiveTo) {
        if (inclusiveFrom < 0xffff) {
          if (inclusiveTo < 0xffff) {
            addCharRange((char)inclusiveFrom, (char)inclusiveTo);
          } else {
            addCharRange((char)inclusiveFrom, (char)0xffff);
            ensureUpperCapacity();
            upperCodePointRanges[upperEndIndex] = rangeToLong(inclusiveFrom, inclusiveTo);
            ++upperEndIndex;
          }
        } else {
          ensureUpperCapacity();
          upperCodePointRanges[upperEndIndex] = rangeToLong(inclusiveFrom, inclusiveTo);
          ++upperEndIndex;
        }
      }

      private void removeLowerElement(final int index) {
        System.arraycopy(lowerCodePointRanges, index + 1, lowerCodePointRanges, index, lowerCodePointRanges.length - index - 1);
        --lowerEndIndex;
      }

      private void removeUpperElement(final int index) {
        System.arraycopy(upperCodePointRanges, index + 1, upperCodePointRanges, index, upperCodePointRanges.length - index - 1);
        --upperEndIndex;
      }

      private void ensureLowerCapacity() {
        if (lowerEndIndex == lowerCodePointRanges.length) {
          ensureLowerCapacity(16);
        }
      }

      private void ensureUpperCapacity() {
        if (upperEndIndex == upperCodePointRanges.length) {
          ensureUpperCapacity(16);
        }
      }

      private void ensureLowerCapacity(final int amount) {
        if ((lowerCodePointRanges.length - lowerEndIndex) < amount) {
          lowerCodePointRanges = Arrays.copyOf(lowerCodePointRanges, lowerCodePointRanges.length + amount);
        }
      }

      private void ensureUpperCapacity(final int amount) {
        if ((upperCodePointRanges.length - upperEndIndex) < amount) {
          upperCodePointRanges = Arrays.copyOf(upperCodePointRanges, upperCodePointRanges.length + amount);
        }
      }



      CodePointRanges build() {
        {
          Arrays.sort(lowerCodePointRanges, 0, lowerEndIndex);
          char previousInclusiveFrom;
          char previousInclusiveTo;
          char currentInclusiveFrom;
          char currentInclusiveTo;
          int i = 0;
          int j = 1;
          while (j < lowerEndIndex) {
            previousInclusiveFrom = getInclusiveFrom(lowerCodePointRanges[i]);
            previousInclusiveTo = getInclusiveTo(lowerCodePointRanges[i]);
            currentInclusiveFrom = getInclusiveFrom(lowerCodePointRanges[j]);
            currentInclusiveTo = getInclusiveTo(lowerCodePointRanges[j]);
            if (previousInclusiveTo >= currentInclusiveFrom) {
              if (previousInclusiveTo <= currentInclusiveTo) {
                lowerCodePointRanges[i] = rangeToInt(previousInclusiveFrom, currentInclusiveTo);
              }
              removeLowerElement(j);
            } else if ((previousInclusiveTo + 1) == currentInclusiveFrom) {
              lowerCodePointRanges[i] = rangeToInt(previousInclusiveFrom, currentInclusiveTo);
              removeLowerElement(j);
            } else {
              ++i;
              ++j;
            }
          }
        }
        {
          Arrays.sort(upperCodePointRanges, 0, upperEndIndex);
          int previousInclusiveFrom;
          int previousInclusiveTo;
          int currentInclusiveFrom;
          int currentInclusiveTo;
          int i = 0;
          int j = 1;
          while (j < upperEndIndex) {
            previousInclusiveFrom = getInclusiveFrom(upperCodePointRanges[i]);
            previousInclusiveTo = getInclusiveTo(upperCodePointRanges[i]);
            currentInclusiveFrom = getInclusiveFrom(upperCodePointRanges[j]);
            currentInclusiveTo = getInclusiveTo(upperCodePointRanges[j]);
            if (previousInclusiveTo >= currentInclusiveFrom) {
              if (previousInclusiveTo <= currentInclusiveTo) {
                upperCodePointRanges[i] = rangeToLong(previousInclusiveFrom, currentInclusiveTo);
              }
              removeLowerElement(j);
            } else if ((previousInclusiveTo + 1) == currentInclusiveFrom) {
              upperCodePointRanges[i] = rangeToLong(previousInclusiveFrom, currentInclusiveTo);
              removeLowerElement(j);
            } else {
              ++i;
              ++j;
            }
          }
        }
        return new CodePointRanges(
            Arrays.copyOf(lowerCodePointRanges, lowerEndIndex),
            Arrays.copyOf(upperCodePointRanges, upperEndIndex));
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

  enum DashPunctuationCharacters {
    HYPHEN_MINUS('\u002D', "HYPHEN-MINUS"),
    ARMENIAN_HYPHEN('\u058A', "ARMENIAN HYPHEN"),
    HEBREW_PUNCTUATION_MAQAF('\u05BE', "HEBREW PUNCTUATION MAQAF"),
    CANADIAN_SYLLABICS_HYPHEN('\u1400', "CANADIAN SYLLABICS HYPHEN"),
    MONGOLIAN_TODO_SOFT_HYPHEN('\u1806', "MONGOLIAN TODO SOFT HYPHEN"),
    HYPHEN('\u2010', "HYPHEN"),
    NON_BREAKING_HYPHEN('\u2011', "NON-BREAKING HYPHEN"),
    FIGURE_DASH('\u2012', "FIGURE DASH"),
    EN_DASH('\u2013', "EN DASH"),
    EM_DASH('\u2014', "EM DASH"),
    HORIZONTAL_BAR('\u2015', "HORIZONTAL BAR"),
    DOUBLE_OBLIQUE_HYPHEN('\u2E17', "DOUBLE OBLIQUE HYPHEN"),
    HYPHEN_WITH_DIAERESIS('\u2E1A', "HYPHEN WITH DIAERESIS"),
    TWO_EM_DASH('\u2E3A', "TWO-EM DASH"),
    THREE_EM_DASH('\u2E3B', "THREE-EM DASH"),
    DOUBLE_HYPHEN('\u2E40', "DOUBLE HYPHEN"),
    WAVE_DASH('\u301C', "WAVE DASH"),
    WAVY_DASH('\u3030', "WAVY DASH"),
    KATAKANA_HIRAGANA_DOUBLE_HYPHEN('\u30A0', "KATAKANA-HIRAGANA DOUBLE HYPHEN"),
    PRESENTATION_FORM_FOR_VERTICAL_EM_DASH('\uFE31', "PRESENTATION FORM FOR VERTICAL EM DASH"),
    PRESENTATION_FORM_FOR_VERTICAL_EN_DASH('\uFE32', "PRESENTATION FORM FOR VERTICAL EN DASH"),
    SMALL_EM_DASH('\uFE58', "SMALL EM DASH"),
    SMALL_HYPHEN_MINUS('\uFE63', "SMALL HYPHEN-MINUS"),
    FULLWIDTH_HYPHEN_MINUS('\uFF0D', "FULLWIDTH HYPHEN-MINUS"),
    YEZIDI_HYPHENATION_MARK(0x10EAD, "YEZIDI HYPHENATION MARK");

    public final int codePoint;
    public final String unicodeValue;
    public final String unicodeName;

    private static int[] allDashPunctuationCodePoints = null;

    DashPunctuationCharacters(int codePoint, String unicodeName) {
      this.codePoint = codePoint;
      this.unicodeValue = Character.isSupplementaryCodePoint(codePoint)
          ? String.format("\\u%08x", codePoint) : String.format("\\u%04x", codePoint);
      this.unicodeName = unicodeName;
    }

    public static int[] getAllDashPunctuationCodePoints() {
      if (allDashPunctuationCodePoints == null) {
        final DashPunctuationCharacters[] values = values();
        allDashPunctuationCodePoints = new int[values.length];
        for (int i = 0; i < values.length; ++i) {
          allDashPunctuationCodePoints[i] = values[i].codePoint;
        }
      }
      return allDashPunctuationCodePoints;
    }

  }

  enum WhiteSpaceCharacters {
    CHARACTER_TABULATION('\u0009', "CHARACTER TABULATION"),
    LINE_FEED('\n', "LINE FEED"),
    LINE_TABULATION('\u000B', "LINE TABULATION"),
    FORM_FEED('\u000C', "FORM FEED"),
    CARRIAGE_RETURN('\r', "CARRIAGE RETURN"),
    SPACE('\u0020', "SPACE"),
    NEXT_LINE('\u0085', "NEXT LINE"),
    NO_BREAK_SPACE('\u00A0', "NO BREAK SPACE"),
    OGHAM_SPACE_MARK('\u1680', "OGHAM SPACE MARK"),
    EN_QUAD('\u2000', "EN QUAD"),
    EM_QUAD('\u2001', "EM QUAD"),
    EN_SPACE('\u2002', "EN SPACE"),
    EM_SPACE('\u2003', "EM SPACE"),
    THREE_PER_EM_SPACE('\u2004', "THREE PER EM SPACE"),
    FOUR_PER_EM_SPACE('\u2005', "FOUR PER EM SPACE"),
    SIX_PER_EM_SPACE('\u2006', "SIX PER EM SPACE"),
    FIGURE_SPACE('\u2007', "FIGURE SPACE"),
    PUNCTUATION_SPACE('\u2008', "PUNCTUATION SPACE"),
    THIN_SPACE('\u2009', "THIN SPACE"),
    HAIR_SPACE('\u200A', "HAIR SPACE"),
    LINE_SEPARATOR('\u2028', "LINE SEPARATOR"),
    PARAGRAPH_SEPARATOR('\u2029', "PARAGRAPH SEPARATOR"),
    NARROW_NO_BREAK_SPACE('\u202F', "NARROW NO BREAK SPACE"),
    MEDIUM_MATHEMATICAL_SPACE('\u205F', "MEDIUM MATHEMATICAL SPACE"),
    IDEOGRAPHIC_SPACE('\u3000', "IDEOGRAPHIC SPACE");

    public final int codePoint;
    public final String unicodeValue;
    public final String unicodeName;

    private static int[] allWhiteSpaceCodePoints = null;

    WhiteSpaceCharacters(int codePoint, String unicodeName) {
      this.codePoint = codePoint;
      this.unicodeValue = String.format("\\u%04x", codePoint);
      this.unicodeName = unicodeName;
    }

    public static int[] getAllWhiteSpaceCodePoints() {
      if (allWhiteSpaceCodePoints == null) {
        final WhiteSpaceCharacters[] values = values();
        allWhiteSpaceCodePoints = new int[values.length];
        for (int i = 0; i < values.length; ++i) {
          allWhiteSpaceCodePoints[i] = values[i].codePoint;
        }
      }
      return allWhiteSpaceCodePoints;
    }
  }

}
