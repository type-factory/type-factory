package land.artli.easytype;

import java.io.Serial;
import java.util.ResourceBundle;

public class InvalidTypeValueException extends IllegalArgumentException {

  @Serial
  private static final long serialVersionUID = -7198769839039479407L;

  private final ResourceBundle localizationResourceBundle;
  private final String parserErrorMessage;
  private final String invalidValue;

  InvalidTypeValueException(
      final String parserErrorMessage, 
      final String message,
      final int[] successfullyParsedCodePoints, 
      final int successfullyParsedCount) {
    super(message == null || message.isBlank() ? parserErrorMessage : message);
    this.localizationResourceBundle = null;
    this.parserErrorMessage = parserErrorMessage;
    this.invalidValue = new String(successfullyParsedCodePoints, 0, successfullyParsedCount);
  }

  InvalidTypeValueException(
      final String parserErrorMessage, 
      final String message,
      final int[] successfullyParsedCodePoints, 
      final int successfullyParsedCount,
      final CharSequence invalidValue) {
    super(message == null || message.isBlank() ? parserErrorMessage : message);
    this.localizationResourceBundle = null;
    this.parserErrorMessage = parserErrorMessage;
    this.invalidValue = invalidValue == null ? null : invalidValue.toString();
  }

  InvalidTypeValueException(
      final String parserErrorMessage, 
      final String message, 
      final CharSequence invalidValue) {
    super(message == null || message.isBlank() ? parserErrorMessage : message);
    this.localizationResourceBundle = null;
    this.parserErrorMessage = parserErrorMessage;
    this.invalidValue = invalidValue == null ? null : invalidValue.toString();
  }

  InvalidTypeValueException(
      final String parserErrorMessage, 
      final String message, 
      final CharSequence invalidValue, 
      final Throwable cause) {
    super(message == null || message.isBlank() ? parserErrorMessage : message, cause);
    this.localizationResourceBundle = null;
    this.parserErrorMessage = parserErrorMessage;
    this.invalidValue = invalidValue == null ? null : invalidValue.toString();
  }

  public String getParserErrorMessage() {
    return parserErrorMessage;
  }

  public String getInvalidValue() {
    return invalidValue;
  }

  @Override
  public String getLocalizedMessage() {
    if (localizationResourceBundle == null) {
      return getMessage();
    }
    try {
      return localizationResourceBundle.getString(getMessage());
    } catch (final Exception e) {
      return getMessage();
    }
  }

  public static InvalidTypeValueException forValueTooShort(
      final String message,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final int minLength) {

    final StringBuilder parserErrorMessage = new StringBuilder();
    parserErrorMessage.append("Invalid ")
        .append(targetTypeClass.getSimpleName())
        .append(" value - too short, min length must be '")
        .append(minLength)
        .append("'.");
    return new InvalidTypeValueException(parserErrorMessage.toString(), message, value);
  }

  public static InvalidTypeValueException forValueTooLong(
      final String message,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final int maxLength) {

    final StringBuilder parserErrorMessage = new StringBuilder();
    parserErrorMessage.append("Invalid ")
        .append(targetTypeClass.getSimpleName())
        .append(" value - too long, max length must be '")
        .append(maxLength)
        .append("'.");
    return new InvalidTypeValueException(parserErrorMessage.toString(), message, value);
  }

  public static InvalidTypeValueException forInvalidCodePoint(
      final String message,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final int indexOfInvalidCharacter,
      final int invalidCodePoint) {

    final StringBuilder parserErrorMessage = new StringBuilder();
    parserErrorMessage.append("Invalid ").append(targetTypeClass.getSimpleName()).append(" value - invalid ");
    if (Character.isWhitespace(invalidCodePoint)) {
      parserErrorMessage.append("white-space ");
    } else if (Character.isISOControl(invalidCodePoint)) {
      parserErrorMessage.append("control ");
    }
    parserErrorMessage.append("character '");
    appendCodePoint(parserErrorMessage, invalidCodePoint);
    parserErrorMessage.append("'.");
    return new InvalidTypeValueException(parserErrorMessage.toString(), message, value);
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
