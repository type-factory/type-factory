package land.artli.strongtype;

import java.io.Serial;

public class InvalidTypeValueException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = -7198769839039479407L;

  private final String localisedMessage;

  InvalidTypeValueException(
      final String message, final String localisedMessage,
      final int[] successfullyParsedCodePoints, final int successfullyParsedCount) {
    super(message);
    this.localisedMessage = localisedMessage;
  }

  InvalidTypeValueException(
      final String message, final String localisedMessage,
      final int[] successfullyParsedCodePoints, final int successfullyParsedCount,
      final CharSequence invalidValue) {
    super(message);
    this.localisedMessage = localisedMessage;
  }

  InvalidTypeValueException(final String message, final String localisedMessage, final CharSequence invalidValue) {
    super(message);
    this.localisedMessage = localisedMessage;
  }

  InvalidTypeValueException(final String message, final String localisedMessage, final CharSequence invalidValue, final Throwable cause) {
    super(message, cause);
    this.localisedMessage = localisedMessage;
  }

  @Override
  public String getLocalizedMessage() {
    return localisedMessage == null || localisedMessage.isBlank() ? getMessage() : localisedMessage;
  }

  public static InvalidTypeValueException forValueTooShort(
      final String localisedMessage,
      final Class<? extends CharType> targetTypeClass,
      final CharSequence value,
      final int minLength) {

    final StringBuilder message = new StringBuilder();
    message.append("Invalid ")
        .append(targetTypeClass.getSimpleName())
        .append(" value - too short, min length must be '")
        .append(minLength)
        .append("'.");
    return new InvalidTypeValueException(message.toString(), localisedMessage, value);
  }

  public static InvalidTypeValueException forValueTooLong(
      final String localisedMessage,
      final Class<? extends CharType> targetTypeClass,
      final CharSequence value,
      final int maxLength) {

    final StringBuilder message = new StringBuilder();
    message.append("Invalid ")
        .append(targetTypeClass.getSimpleName())
        .append(" value - too long, max length must be '")
        .append(maxLength)
        .append("'.");
    return new InvalidTypeValueException(message.toString(), localisedMessage, value);
  }

  static InvalidTypeValueException forInvalidCodePoint(
      final String localisedMessage,
      final Class<? extends CharType> targetTypeClass,
      final CharSequence value,
      final int indexOfInvalidCharacter,
      final int invalidCodePoint) {

    final StringBuilder message = new StringBuilder();
    message.append("Invalid ").append(targetTypeClass.getSimpleName()).append(" value - invalid ");
    if (Character.isWhitespace(invalidCodePoint)) {
      message.append("white-space ");
    } else if (Character.isISOControl(invalidCodePoint)) {
      message.append("control ");
    }
    message.append("character '");
    appendCodePoint(message, invalidCodePoint);
    message.append("'.");
    return new InvalidTypeValueException(message.toString(), localisedMessage, value);
  }

  private static StringBuilder appendCodePoint(final StringBuilder s, final int invalidCodePoint) {
    if (Character.isWhitespace(invalidCodePoint) || Character.isISOControl(invalidCodePoint)) {
      if (Character.isSupplementaryCodePoint(invalidCodePoint)) {
        s.append(String.format("\\u%08x", invalidCodePoint));
      } else {
        s.append(String.format("\\u%04x", (short) invalidCodePoint));
      }
    } else {
      s.appendCodePoint(invalidCodePoint);
    }
    return s;
  }

  private static StringBuilder appendValueTruncatedIfNecessary(
      final StringBuilder s, final CharSequence value, final int indexOfInvalidCharacter) {

    if (value.length() <= 40) {
      return s.append(value);
    }

    final int minIndex = indexOfInvalidCharacter <= 20 ? 0 : Math.max(3, indexOfInvalidCharacter - 17);
    final int maxIndex = (minIndex + 40) > value.length() ? value.length() : value.length() - 3;
    if (minIndex == 3) {
      s.append("...");
    }
    s.append(value, minIndex, maxIndex);
    if (maxIndex < value.length()) {
      s.append("...");
    }
    return s;
  }
}
