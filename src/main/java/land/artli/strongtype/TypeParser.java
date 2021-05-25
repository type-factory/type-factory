package land.artli.strongtype;

import static java.lang.Character.isWhitespace;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import land.artli.strongtype.TypeParser.AcceptedCodePointRanges.AcceptedCodePointRangesBuilder;
import land.artli.strongtype.TypeParser.CodePointConversions.CodePointConversionsBuilder;

public class TypeParser {

  private final Class<? extends CharType> targetClass;
  private final String errorMessage;
  private final TargetCase targetCase;
  private final WhiteSpace whiteSpace;
  private final NullHandling nullHandling;
  private final int minNumberOfCodePoints;
  private final int maxNumberOfCodePoints;
  private final AcceptedCodePointRanges acceptedCodePointRanges;
  private final CodePointConversions codePointConversions;

  private TypeParser(
      final Class<? extends CharType> targetClass,
      final String errorMessage,
      final TargetCase targetCase,
      final WhiteSpace whiteSpace,
      final NullHandling nullHandling,
      final int minNumberOfCodePoints, final int maxNumberOfCodePoints,
      final AcceptedCodePointRanges acceptedCodePointRanges,
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
      return switch(nullHandling) {
        case PRESERVE_NULL_AND_EMPTY -> null;
        case CONVERT_EMPTY_TO_NULL -> null;
        case CONVERT_NULL_TO_EMPTY -> "";
      };
    }
    final int length = value.length();
    final int[] result = new int[length];
    final int endIndex = endIndexIgnoringTrailingWhitespace(value);
    final int startIndex = startIndexIgnoringLeadingWhitespace(value, endIndex);
    if ((endIndex - startIndex) == 0) {
      return "";
    }
    int i = startIndex;
    int j = 0;
    char ch;
    int codePoint;
    while (i < length) {
      ch = value.charAt(i);
      // TODO handle case where surrogate is incomplete because ++i is greater than length
      codePoint = Character.isSurrogate(ch) ? Character.toCodePoint(ch, value.charAt(++i)) : (int) ch;
      if (acceptedCodePointRanges.isNotAcceptedCodePoint(codePoint)) {
        throw InvalidTypeValueException.forInvalidCodePoint(errorMessage, targetClass, value, i, codePoint);
      }
      if (Character.isWhitespace(codePoint)) {
        CodePointConversion codePointConversion;
        switch (whiteSpace) {
          case PRESERVE_WHITESPACE:
            result[j++] = codePoint;
            while (++i < length && Character.isWhitespace(codePoint = value.charAt(i)) && acceptedCodePointRanges.isAcceptedCodePoint(codePoint)) {
              result[j++] = codePoint;
            }
            break;
          case PRESERVE_AND_CONVERT_WHITESPACE:
            codePointConversion = codePointConversions.getCodePointConversion(codePoint);
            result[j++] = codePointConversion == null ? codePoint : (char) codePointConversion.toCodePoints[0];
            while (++i < length && Character.isWhitespace(codePoint = value.charAt(i)) && acceptedCodePointRanges.isAcceptedCodePoint(codePoint)) {
              result[j++] = codePointConversion == null ? codePoint : (char) codePointConversion.toCodePoints[0];
            }
            break;
          case NORMALIZE_WHITESPACE:
            result[j++] = ' ';
            while (++i < length && Character.isWhitespace(codePoint = value.charAt(i)) && acceptedCodePointRanges.isAcceptedCodePoint(codePoint)) {
              // ignore extra whitespace;
            }
            break;
          case NORMALIZE_AND_CONVERT_WHITESPACE:
            codePointConversion = codePointConversions.getCodePointConversion(codePoint);
            result[j++] = codePointConversion == null ? codePoint : (char) codePointConversion.toCodePoints[0];
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
        if (acceptedCodePointRanges.isNotAcceptedCodePoint(codePoint)) {
          throw InvalidTypeValueException.forInvalidCodePoint(errorMessage, targetClass, value, i, codePoint);
        }
      }
      if (j >= maxNumberOfCodePoints) {
        throw InvalidTypeValueException.forValueTooLong(errorMessage, targetClass, value, maxNumberOfCodePoints);
      }
      result[j++] = switch (targetCase) {
        case PRESERVE_CASE -> codePoint;
        case TO_LOWER_CASE -> Character.toLowerCase(codePoint);
        case TO_UPPER_CASE -> Character.toUpperCase(codePoint);
        case TO_TITLE_CASE -> j == 1 ? Character.toTitleCase(codePoint) : Character.toLowerCase(codePoint);
      };
      ++i;
    }
    if (j < minNumberOfCodePoints) {
      throw InvalidTypeValueException.forValueTooShort(errorMessage, targetClass, value, minNumberOfCodePoints);
    }
    return new String(result, 0, j);
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
    private final AcceptedCodePointRangesBuilder acceptedCodePointRangesBuilder = AcceptedCodePointRanges.builder();
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
      acceptedCodePointRangesBuilder.addChars(ch);
      return this;
    }

    public TypeParserBuilder acceptChars(final char... chars) {
      acceptedCodePointRangesBuilder.addChars(chars);
      return this;
    }

    public TypeParserBuilder acceptCharRange(final char inclusiveFrom, final char inclusiveTo) {
      acceptedCodePointRangesBuilder.addCharRange(inclusiveFrom, inclusiveTo);
      return this;
    }

    public TypeParserBuilder acceptCodePoint(final int codePoint) {
      acceptedCodePointRangesBuilder.addCodePoint(codePoint);
      return this;
    }

    public TypeParserBuilder acceptCodePoints(final int... codePoints) {
      acceptedCodePointRangesBuilder.addCodePoints(codePoints);
      return this;
    }

    public TypeParserBuilder acceptCodePointRange(final char inclusiveFrom, final char inclusiveTo) {
      acceptedCodePointRangesBuilder.addCodePointRange(inclusiveFrom, inclusiveTo);
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

    public TypeParserBuilder acceptAllWhitespace() {
      acceptedCodePointRangesBuilder.addAllWhiteSpace();
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

    public TypeParserBuilder normalizeAndConvertWhitespace(final char toChar) {
      whiteSpace = WhiteSpace.NORMALIZE_AND_CONVERT_WHITESPACE;
      codePointConversionsBuilder.addCodePointConversions(WhiteSpaceCharacters.getAllWhiteSpaceCodePoints(), toChar);
      return this;
    }

    public TypeParserBuilder preserveWhitespace() {
      whiteSpace = WhiteSpace.PRESERVE_WHITESPACE;
      return this;
    }

    public TypeParserBuilder preserveAndConvertWhitespace(final char toChar) {
      whiteSpace = WhiteSpace.PRESERVE_AND_CONVERT_WHITESPACE;
      codePointConversionsBuilder.addCodePointConversions(WhiteSpaceCharacters.getAllWhiteSpaceCodePoints(), toChar);
      return this;
    }

    public TypeParser build() {
      return new TypeParser(
          targetClass, errorMessage, targetCase,
          whiteSpace, nullHandling,
          minNumberOfCodePoints, maxNumberOfCodePoints,
          acceptedCodePointRangesBuilder.build(),
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

    static CodePointConversionsBuilder builder() {
      return new CodePointConversionsBuilder();
    }

    private CodePointConversions(final CodePointConversion[] codePointConversions) {
      int slots = determineNumberOfSlots(codePointConversions);
      this.codePointConversions = new CodePointConversion[slots][];
      for (CodePointConversion codePointConversion : codePointConversions) {
        addConversion(this.codePointConversions, codePointConversion);
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

  private static class CodePointRange {

    static final Comparator<CodePointRange> COMPARATOR =
        Comparator.nullsFirst(Comparator
            .comparing(CodePointRange::getInclusiveFrom)
            .thenComparing(CodePointRange::getInclusiveTo));

    final int inclusiveFrom;
    final int inclusiveTo;
    final int rangeLength;

    CodePointRange(final int inclusiveFrom, final int inclusiveTo) {
      this.inclusiveFrom = Math.max(Character.MIN_CODE_POINT, Math.min(inclusiveFrom, inclusiveTo));
      this.inclusiveTo = Math.min(Character.MAX_CODE_POINT, Math.max(inclusiveFrom, inclusiveTo));
      this.rangeLength = this.inclusiveTo - this.inclusiveFrom + 1;
    }

    int getInclusiveFrom() {
      return inclusiveFrom;
    }

    int getInclusiveTo() {
      return inclusiveTo;
    }
  }

  static class AcceptedCodePointRanges {

    private final CodePointRange[] acceptedCodePointRanges;

    private AcceptedCodePointRanges(final CodePointRange[] acceptedCodePointRanges) {
      this.acceptedCodePointRanges = acceptedCodePointRanges;
    }

    static AcceptedCodePointRangesBuilder builder() {
      return new AcceptedCodePointRangesBuilder();
    }

    boolean isAcceptedCodePoint(final int codePoint) {
      return getCodePointRange(codePoint) != null;
    }

    boolean isNotAcceptedCodePoint(final int codePoint) {
      return !isAcceptedCodePoint(codePoint);
    }

    private CodePointRange getCodePointRange(final int codePoint) {
      if (acceptedCodePointRanges.length == 0 ||
          codePoint < acceptedCodePointRanges[0].inclusiveFrom ||
          codePoint > acceptedCodePointRanges[acceptedCodePointRanges.length - 1].inclusiveTo) {
        return null; // not found, outside of range
      }

      // Try to find using a binary search
      int low = 0;
      int high = acceptedCodePointRanges.length - 1;
      while (low <= high) {
        final int mid = (low + high) >>> 1;
        final CodePointRange midVal = acceptedCodePointRanges[mid];
        if (codePoint > midVal.inclusiveTo) {
          low = mid + 1;
        } else if (codePoint < midVal.inclusiveFrom) {
          high = mid - 1;
        } else if (codePoint >= midVal.inclusiveFrom && codePoint <= midVal.inclusiveTo) {
          return midVal; // found
        } else {
          return null; // not found
        }
      }
      return null; // not found
    }

    static class AcceptedCodePointRangesBuilder {

      private CodePointRange[] acceptedCodePointRanges = new CodePointRange[16];
      private int endIndex = 0;

      void addAllWhiteSpace() {
        addCodePoints(WhiteSpaceCharacters.getAllWhiteSpaceCodePoints());
      }

      void addChars(final char ch) {
        addCharRange(ch, ch);
      }

      void addChars(final char... chars) {
        for (int i = 0; i < chars.length; ++i) {
          addChars(chars[i]);
        }
      }

      void addCharRange(final char inclusiveFrom, final char inclusiveTo) {
        addCodePointRange(inclusiveFrom, inclusiveTo);
      }

      void addCodePoint(final int codePoint) {
        addCodePointRange(codePoint, codePoint);
      }

      void addCodePoints(final int... codePoints) {
        if (codePoints != null) {
          for (int i = 0; i < codePoints.length; ++i) {
            addCodePoint(codePoints[i]);
          }
        }
      }

      void addCodePointRange(final int inclusiveFrom, final int inclusiveTo) {
        if (endIndex == acceptedCodePointRanges.length) {
          acceptedCodePointRanges = Arrays.copyOf(acceptedCodePointRanges, acceptedCodePointRanges.length + 16);
        }
        acceptedCodePointRanges[endIndex] = new CodePointRange(inclusiveFrom, inclusiveTo);
        ++endIndex;
      }

      private void removeElement(final int index) {
        System.arraycopy(acceptedCodePointRanges, index + 1, acceptedCodePointRanges, index, acceptedCodePointRanges.length - index - 1);
        --endIndex;
      }

      AcceptedCodePointRanges build() {
        Arrays.sort(acceptedCodePointRanges, 0, endIndex, CodePointRange.COMPARATOR);
        CodePointRange previous;
        CodePointRange current;
        int i = 0;
        int j = 1;
        while (j < endIndex) {
          previous = acceptedCodePointRanges[i];
          current = acceptedCodePointRanges[j];
          if (previous.inclusiveTo >= current.inclusiveFrom) {
            if (previous.inclusiveTo <= current.inclusiveTo) {
              acceptedCodePointRanges[i] = new CodePointRange(previous.inclusiveFrom, current.inclusiveTo);
            }
            removeElement(j);
          } else if ((previous.inclusiveTo + 1) == current.inclusiveFrom) {
            acceptedCodePointRanges[i] = new CodePointRange(previous.inclusiveFrom, current.inclusiveTo);
            removeElement(j);
          } else {
            ++i;
            ++j;
          }
        }
        return new AcceptedCodePointRanges(Arrays.copyOf(acceptedCodePointRanges, endIndex));
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
