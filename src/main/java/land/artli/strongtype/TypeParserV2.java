package land.artli.strongtype;

import static java.lang.Character.isWhitespace;

import java.util.Arrays;
import java.util.Comparator;

public class TypeParserV2 {

  private final TargetCase targetCase;
  private final CharRange [] acceptedCharRanges;

  private TypeParserV2(final TargetCase targetCase, CharRange[] acceptedCharRanges) {
    this.targetCase = targetCase;
    this.acceptedCharRanges = acceptedCharRanges;
  }

  public static TypeParserBuilder builder() {
    return new TypeParserBuilder();
  }

  public String parse(final CharSequence value) {
    if (value == null) {
      return null;
    }
    final int length = value.length();
    final char [] result = new char[length];
    final int endIndex = endIndexIgnoringTrailingWhitespace(value);
    final int startIndex = startIndexIgnoringLeadingWhitespace(value, endIndex);
    if ((endIndex - startIndex) == 0) {
      return "";
    }
    int i = startIndex;
    int j = 0;
    char ch;
    while (i < length) {
      ch = value.charAt(i);
      if (Character.isWhitespace(ch)) {
        result[j++] = ' ';
        while (++i < length && Character.isWhitespace(value.charAt(i))); // swallow extra whitespace
        ch = value.charAt(i);
      }
      if (isAcceptedChar(ch)) {
        result[j++] = switch (targetCase) {
          case PRESERVE_CASE -> ch;
          case TO_LOWER_CASE -> Character.toLowerCase(ch);
          case TO_UPPER_CASE -> Character.toUpperCase(ch);
          case TO_TITLE_CASE -> j == 1 ? Character.toTitleCase(ch) : Character.toLowerCase(ch);
        };
      }
      ++i;
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

  private boolean isAcceptedChar(final char ch) {
    return binarySearch(ch) >= 0;
  }

  private int binarySearch(final char ch) {
    if (ch < acceptedCharRanges[0].inclusiveFrom || ch > acceptedCharRanges[acceptedCharRanges.length-1].inclusiveTo) {
      return -1; // outside of range
    }
    int low = 0;
    int high = acceptedCharRanges.length - 1;

    while (low <= high) {
      int mid = (low + high) >>> 1;
      CharRange midVal = acceptedCharRanges[mid];

      if (ch > midVal.inclusiveTo) {
        low = mid + 1;
      } else if (ch < midVal.inclusiveFrom) {
        high = mid - 1;
      } else if (ch >= midVal.inclusiveFrom && ch <= midVal.inclusiveTo) {
        return mid; // found
      } else {
        return -1;
      }
    }
    return -1; // not found.
  }

  enum TargetCase {
    PRESERVE_CASE,
    TO_UPPER_CASE,
    TO_LOWER_CASE,
    TO_TITLE_CASE,
  }

  public static class TypeParserBuilder {

    private int minLength;
    private int maxLength;
    private TargetCase targetCase = TargetCase.PRESERVE_CASE;
    private final AcceptedChars acceptedChars = new AcceptedChars();
    private final AcceptedCharRanges acceptedCharRanges = new AcceptedCharRanges();
    private final AcceptedCodePoints acceptedCodePoints = new AcceptedCodePoints();

    public TypeParserBuilder acceptChar(final char ch) {
      acceptedCharRanges.addCharRange(ch, ch);
      return this;
    }

    public TypeParserBuilder acceptChars(final char... chars) {
      if (chars != null) {
        for (int i = 0; i < chars.length; ++i) {
          acceptChar(chars[i]);
        }
      }
      return this;
    }

    public TypeParserBuilder acceptCharRange(final char inclusiveFrom, final char inclusiveTo) {
      acceptedCharRanges.addCharRange(inclusiveFrom, inclusiveTo);
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

    public TypeParserBuilder acceptCodePoint(final int codePoint) {
      if (codePoint < 0x10000) {
        return acceptChar((char) codePoint);
      }
      if (Character.isValidCodePoint(codePoint)) {
        acceptedCodePoints.addCodePoint(codePoint);
      }
      return this;
    }

    public TypeParserBuilder acceptCodePoints(final int... codePoints) {
      if (codePoints != null) {
        for (int i = 0; i < codePoints.length; ++i) {
          acceptCodePoint(codePoints[i]);
        }
      }
      return this;
    }

    public TypeParserV2 build() {
      return new TypeParserV2(targetCase, acceptedCharRanges.getAcceptedCharRanges());
    }
  }

  private static class CharRange {

    static final Comparator<CharRange> COMPARATOR =
        Comparator.nullsFirst(Comparator.comparing(CharRange::getInclusiveFrom).thenComparing(CharRange::getInclusiveTo));

    final char inclusiveFrom;
    final char inclusiveTo;
    final int rangeLength;

    CharRange(final char inclusiveFrom, final char inclusiveTo) {
      this.inclusiveFrom = inclusiveFrom < inclusiveTo ? inclusiveFrom : inclusiveTo;
      this.inclusiveTo = inclusiveFrom < inclusiveTo ? inclusiveTo : inclusiveFrom;
      this.rangeLength = this.inclusiveTo - this.inclusiveFrom;
    }

    char getInclusiveFrom() {
      return inclusiveFrom;
    }

    char getInclusiveTo() {
      return inclusiveTo;
    }
  }

  private static class AcceptedChars {

    private char[] acceptedChars = new char[128];
    private int endIndex = 0;

    public void addChar(final char ch) {
      if (endIndex == acceptedChars.length) {
        acceptedChars = Arrays.copyOf(acceptedChars, acceptedChars.length + 128);
      }
      acceptedChars[endIndex] = ch;
      ++endIndex;
    }

    public void addChars(final char... chars) {
      if (chars.length > (acceptedChars.length - endIndex)) {
        acceptedChars = Arrays.copyOf(acceptedChars, acceptedChars.length + chars.length + 128);
      }
      System.arraycopy(chars, 0, acceptedChars, endIndex, chars.length);
      endIndex += chars.length;
    }
  }

  private static class AcceptedCodePoints {

    private int[] acceptedCodePoints = new int[128];
    private int endIndex = 0;

    public void addCodePoint(final int codePoint) {
      if (endIndex == acceptedCodePoints.length) {
        acceptedCodePoints = Arrays.copyOf(acceptedCodePoints, acceptedCodePoints.length + 128);
      }
      acceptedCodePoints[endIndex] = codePoint;
      ++endIndex;
    }
  }

  private static class AcceptedCharRanges {

    private CharRange[] acceptedCharRanges = new CharRange[64];
    private int endIndex = 0;

    public void addCharRange(final char inclusiveFrom, final char inclusiveTo) {
      if (endIndex == acceptedCharRanges.length) {
        acceptedCharRanges = Arrays.copyOf(acceptedCharRanges, acceptedCharRanges.length + 64);
      }
      acceptedCharRanges[endIndex] = new CharRange(inclusiveFrom, inclusiveTo);
      ++endIndex;
    }

    private void removeElement(final int index) {
      if ((endIndex - index) == 1) {
        --endIndex;
      } else {
        System.arraycopy(acceptedCharRanges, index + 1, acceptedCharRanges, index, acceptedCharRanges.length - index - 1);
      }
    }

    public CharRange [] getAcceptedCharRanges() {
      Arrays.sort(acceptedCharRanges, 0, endIndex, CharRange.COMPARATOR);
      CharRange previous;
      CharRange current;
      int i = 0;
      int j = 1;
      while (j < endIndex) {
        previous = acceptedCharRanges[i];
        current = acceptedCharRanges[j];
        if (previous.inclusiveTo >= current.getInclusiveFrom()) {
          if (previous.inclusiveTo <= current.getInclusiveTo()) {
            acceptedCharRanges[i] = new CharRange(previous.inclusiveFrom, current.inclusiveTo);
          }
          removeElement(j);
        } else {
          ++i;
          ++j;
        }
      }
      return Arrays.copyOf(acceptedCharRanges, endIndex);
    }
  }
}
