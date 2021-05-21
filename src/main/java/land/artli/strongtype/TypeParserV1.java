package land.artli.strongtype;

import java.util.Arrays;

public class TypeParserV1 {

  public static TypeParserBuilder builder() {
    return new TypeParserBuilder();
  }

  public static class TypeParserBuilder {

    private final AcceptedChars acceptedChars = new AcceptedChars();
    private final AcceptedCharRanges acceptedCharRanges = new AcceptedCharRanges();
    private final AcceptedCodePoints acceptedCodePoints = new AcceptedCodePoints();

    public TypeParserBuilder acceptChar(final char ch) {
      acceptedChars.addChar(ch);
      return this;
    }

    public TypeParserBuilder acceptChars(final char... ch) {
      if (ch != null) {
        acceptedChars.addChars(ch);
      }
      return this;
    }

    public TypeParserBuilder acceptCharRange(final char inclusiveFrom, final char inclusiveTo) {
      acceptedCharRanges.addCharRange(inclusiveFrom, inclusiveTo);
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

  }

  private static class CharRange {

    public final char inclusiveFrom;
    public final char inclusiveTo;
    public final int rangeLength;

    public CharRange(final char inclusiveFrom, final char inclusiveTo) {
      this.inclusiveFrom = inclusiveFrom < inclusiveTo ? inclusiveFrom : inclusiveTo;
      this.inclusiveTo = inclusiveFrom < inclusiveTo ? inclusiveTo : inclusiveFrom;
      this.rangeLength = this.inclusiveTo - this.inclusiveFrom;
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
  }
}
