package land.artli.easytype;

import static java.lang.Character.isWhitespace;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.function.Function;

class TypeParserImpl implements TypeParser {

  private final Class<? extends CharType<?>> targetClass;
  private final String errorMessage;
  private final TargetCase targetCase;
  private final WhiteSpace whiteSpace;
  private final int[] convertWhiteSpaceToCodePoints;
  private final NullHandling nullHandling;
  private final Normalizer.Form targetCharacterNormalizationForm;
  private final int minNumberOfCodePoints;
  private final int maxNumberOfCodePoints;
  private final long acceptedCategories; // bit values, currently 'int' is enough but new categories have been defined.
  private final Subset acceptedCodePoints;
  private final CodePointConversions codePointConversions;

  TypeParserImpl(
      final Class<? extends CharType<?>> targetClass,
      final String errorMessage,
      final TargetCase targetCase,
      final WhiteSpace whiteSpace,
      final int[] convertWhiteSpaceToCodePoints,
      final NullHandling nullHandling,
      final Normalizer.Form targetCharacterNormalizationForm,
      final int minNumberOfCodePoints,
      final int maxNumberOfCodePoints,
      final long acceptedCategories,
      final Subset acceptedCodePoints,
      final CodePointConversions codePointConversions) {
    this.targetClass = targetClass;
    this.errorMessage = errorMessage;
    this.targetCase = targetCase;
    this.whiteSpace = whiteSpace;
    this.convertWhiteSpaceToCodePoints = convertWhiteSpaceToCodePoints;
    this.nullHandling = nullHandling;
    this.targetCharacterNormalizationForm = targetCharacterNormalizationForm;
    this.minNumberOfCodePoints = minNumberOfCodePoints;
    this.maxNumberOfCodePoints = maxNumberOfCodePoints;
    this.acceptedCategories = acceptedCategories;
    this.acceptedCodePoints = acceptedCodePoints;
    this.codePointConversions = codePointConversions;
  }

  public <T extends CharType<?>> T parse(final CharSequence value, Function<String, T> constructorOrFactoryMethod) {
    return constructorOrFactoryMethod.apply(parse(value));
  }

  public String parse(final CharSequence originalValue) {
    if (originalValue == null) {
      return switch (nullHandling) {
        case PRESERVE_NULL_AND_EMPTY, CONVERT_EMPTY_TO_NULL -> null;
        case CONVERT_NULL_TO_EMPTY -> "";
      };
    }

    final CharSequence value = targetCharacterNormalizationForm == null || Normalizer.isNormalized(originalValue, targetCharacterNormalizationForm)
        ? originalValue
        : Normalizer.normalize(originalValue, targetCharacterNormalizationForm);

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

    while (i < length) {
      ch = value.charAt(i);
      if (Character.isSurrogate(ch)) {
        if (++i < length) {
          codePoint = Character.toCodePoint(ch, value.charAt(i));
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
              // ignore extra whitespace
            }
            break;
          case NORMALIZE_AND_CONVERT_WHITESPACE:
            for (int j = 0; j < convertWhiteSpaceToCodePoints.length; ++j) {
              result = appendCodePoint(result, k++, convertWhiteSpaceToCodePoints[j]);
            }
            while (++i < length && Character.isWhitespace(codePoint = value.charAt(i))) {
              // ignore extra whitespace
            }
            break;
          case REMOVE_WHITESPACE:
            while (++i < length && Character.isWhitespace(codePoint = value.charAt(i))) {
              // ignore extra whitespace
            }
            break;
        }
      }
      toCodePoints = codePointConversions.convertCodePoint(codePoint, reusableSingleCodePointArray);
      for (int j = 0; j < toCodePoints.length; ++j) {
        codePoint = toCodePoints[j];
        if (!isAcceptedCodePoint(codePoint)) {
          throw InvalidTypeValueException.forInvalidCodePoint(errorMessage, targetClass, value, i, codePoint);
        }
        if (k >= maxNumberOfCodePoints) {
          throw InvalidTypeValueException.forValueTooLong(errorMessage, targetClass, value, maxNumberOfCodePoints);
        }
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
    return (acceptedCategories > 0L
        && (acceptedCategories & (0x1L << Character.getType(codePoint))) > 0L)
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

}