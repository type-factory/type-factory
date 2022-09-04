package org.datatypeproject;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import org.datatypeproject.impl.MessageUtils;

public class InvalidValueException extends IllegalArgumentException {

  @Serial
  private static final long serialVersionUID = -7198769839039479407L;
  private static final String[] EMPTY_PARSER_ERROR_MESSAGE_ARG_KEYS = new String[0];
  private static final Serializable[] EMPTY_PARSER_ERROR_MESSAGE_ARG_VALUES = new Serializable[0];

  private final Class<?> targetTypeClass;
  private final String errorMessageKey;
  private final String parserErrorMessageKey;
  private final String[] parserErrorMessageArgKeys;
  private final Serializable[] parserErrorMessageArgValues;
  private final String invalidValue;

  protected InvalidValueException(
      final CharSequence invalidValue,
      final Class<?> targetTypeClass,
      final String errorMessageKey,
      final String parserErrorMessageKey) {
    this(null,
        invalidValue,
        targetTypeClass,
        errorMessageKey,
        parserErrorMessageKey,
        EMPTY_PARSER_ERROR_MESSAGE_ARG_KEYS,
        EMPTY_PARSER_ERROR_MESSAGE_ARG_VALUES);
  }

  protected InvalidValueException(
      final Throwable cause,
      final CharSequence invalidValue,
      final Class<?> targetTypeClass,
      final String errorMessageKey,
      final String parserErrorMessageKey) {
    this(cause,
        invalidValue,
        targetTypeClass,
        errorMessageKey,
        parserErrorMessageKey,
        EMPTY_PARSER_ERROR_MESSAGE_ARG_KEYS,
        EMPTY_PARSER_ERROR_MESSAGE_ARG_VALUES);
  }

  protected <V extends Serializable> InvalidValueException(
      final CharSequence invalidValue,
      final Class<?> targetTypeClass,
      final String errorMessageKey,
      final String parserErrorMessageKey,
      final String parserErrorMessageArgKey1, final V parserErrorMessageArgValue1) {
    this(null,
        invalidValue,
        targetTypeClass,
        errorMessageKey,
        parserErrorMessageKey,
        new String[]{parserErrorMessageArgKey1},
        new Serializable[]{parserErrorMessageArgValue1});
  }

  @SuppressWarnings("java:S107")
  protected <V extends Serializable> InvalidValueException(
      final CharSequence invalidValue,
      final Class<?> targetTypeClass,
      final String errorMessageKey,
      final String parserErrorMessageKey,
      final String parserErrorMessageArgKey1, final V parserErrorMessageArgValue1,
      final String parserErrorMessageArgKey2, final V parserErrorMessageArgValue2) {
    this(null,
        invalidValue,
        targetTypeClass,
        errorMessageKey,
        parserErrorMessageKey,
        new String[]{parserErrorMessageArgKey1, parserErrorMessageArgKey2},
        new Serializable[]{parserErrorMessageArgValue1, parserErrorMessageArgValue2});
  }

  @SuppressWarnings("java:S107")
  protected <V extends Serializable> InvalidValueException(
      final CharSequence invalidValue,
      final Class<?> targetTypeClass,
      final String errorMessageKey,
      final String parserErrorMessageKey,
      final String parserErrorMessageArgKey1, final V parserErrorMessageArgValue1,
      final String parserErrorMessageArgKey2, final V parserErrorMessageArgValue2,
      final String parserErrorMessageArgKey3, final V parserErrorMessageArgValue3) {
    this(null,
        invalidValue,
        targetTypeClass,
        errorMessageKey,
        parserErrorMessageKey,
        new String[]{parserErrorMessageArgKey1, parserErrorMessageArgKey2, parserErrorMessageArgKey3},
        new Serializable[]{parserErrorMessageArgValue1, parserErrorMessageArgValue2, parserErrorMessageArgValue3});
  }

  private <V extends Serializable> InvalidValueException(
      final Throwable cause,
      final CharSequence invalidValue,
      final Class<?> targetTypeClass,
      final String errorMessageKey,
      final String parserErrorMessageKey,
      final String[] parserErrorMessageArgKeys,
      final V[] parserErrorMessageArgValues) {
    super(MessageUtils.combineMessagesIntoSentences(
            MessageUtils.getMessage(errorMessageKey),
            MessageUtils.getMessage(parserErrorMessageKey, parserErrorMessageArgValues)),
        cause);
    this.invalidValue = invalidValue == null ? null : invalidValue.toString();
    this.targetTypeClass = targetTypeClass;
    this.errorMessageKey = errorMessageKey;
    this.parserErrorMessageKey = parserErrorMessageKey;
    this.parserErrorMessageArgKeys = parserErrorMessageArgKeys;
    this.parserErrorMessageArgValues = parserErrorMessageArgValues;
  }

  @Override
  public String getLocalizedMessage() {
    return getLocalizedMessage(Locale.getDefault());
  }

  public String getLocalizedMessage(final Locale locale) {
    return MessageUtils.combineMessagesIntoSentences(getDataTypeErrorMessage(locale), getParserErrorMessage(locale));
  }

  public Class<?> getTargetTypeClass() {
    return targetTypeClass;
  }

  public String getErrorMessageKey() {
    return errorMessageKey;
  }

  public String getDataTypeErrorMessage() {
    return getDataTypeErrorMessage(Locale.getDefault());
  }

  public String getDataTypeErrorMessage(final Locale locale) {
    return MessageUtils.getMessage(locale, errorMessageKey, parserErrorMessageArgValues);
  }

  public String getParserErrorMessageKey() {
    return parserErrorMessageKey;
  }

  public String getParserErrorMessage() {
    return getParserErrorMessage(Locale.getDefault());
  }

  public String getParserErrorMessage(final Locale locale) {
    return MessageUtils.getMessage(locale, parserErrorMessageKey, parserErrorMessageArgValues);
  }

  public Map<String, Serializable> getErrorMessageProperties() {
    final Map<String, Serializable> result = new HashMap<>();
    for (int i = 0; i < parserErrorMessageArgKeys.length; ++i) {
      result.put(parserErrorMessageArgKeys[i], parserErrorMessageArgValues[i]);
    }
    return result;
  }

  public String getInvalidValue() {
    return invalidValue;
  }

  public static InvalidValueException forValueTooShort(
      final String message,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final int minLength) {

    return new InvalidValueException(
        value,
        targetTypeClass,
        message,
        ParserErrorCode.INVALID_VALUE_TOO_SHORT,
        ParserErrorMessageArgKeys.MIN_LENGTH, minLength);
  }

  public static InvalidValueException forValueTooLong(
      final String message,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final int maxLength) {

    return new InvalidValueException(
        value,
        targetTypeClass,
        message,
        ParserErrorCode.INVALID_VALUE_TOO_LONG,
        ParserErrorMessageArgKeys.MAX_LENGTH, maxLength);
  }

  public static InvalidValueException forInvalidCodePoint(
      final String message,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final int invalidCodePoint) {

    if (Character.isWhitespace(invalidCodePoint)) {
      return new InvalidValueException(
          value,
          targetTypeClass,
          message,
          ParserErrorCode.INVALID_VALUE_INVALID_WHITESPACE_CHARACTER,
          ParserErrorMessageArgKeys.INVALID_CODE_POINT,
          unicodeHexCode(invalidCodePoint));
    }

    if (Character.isISOControl(invalidCodePoint)) {
      return new InvalidValueException(
          value,
          targetTypeClass,
          message,
          ParserErrorCode.INVALID_VALUE_INVALID_CONTROL_CHARACTER,
          ParserErrorMessageArgKeys.INVALID_CODE_POINT,
          unicodeHexCode(invalidCodePoint));
    }

    if ('\'' == invalidCodePoint) {
      return new InvalidValueException(
          value,
          targetTypeClass,
          message,
          ParserErrorCode.INVALID_VALUE_INVALID_QUOTE_CHARACTER,
          ParserErrorMessageArgKeys.INVALID_CODE_POINT,
          "''");
    }

    return new InvalidValueException(
        value,
        targetTypeClass,
        message,
        ParserErrorCode.INVALID_VALUE_INVALID_CHARACTER,
        ParserErrorMessageArgKeys.INVALID_CODE_POINT,
        new String(new int[]{invalidCodePoint}, 0, 1));
  }

  public static InvalidValueException forValueNotMatchRegex(
      final String message,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final Pattern regex) {

    return new InvalidValueException(
        value,
        targetTypeClass,
        message,
        ParserErrorCode.INVALID_VALUE_DOES_NOT_MATCH_REGEX_PATTERN,
        ParserErrorMessageArgKeys.REGEX_PATTERN,
        regex.toString());
  }

  public static InvalidValueException forValueNotValidUsingCustomValidation(
      final String message,
      final Class<?> targetTypeClass,
      final CharSequence value,
      final Exception cause) {

    return new InvalidValueException(
        cause,
        value,
        targetTypeClass,
        message,
        ParserErrorCode.INVALID_VALUE_DOES_NOT_PASS_CUSTOM_VALIDATION);
  }

  private static String unicodeHexCode(final int codePoint) {
    return Character.isSupplementaryCodePoint(codePoint)
        ? String.format("U+%06x", codePoint)
        : String.format("U+%04x", (short) codePoint);
  }


  @Override
  public String toString() {
    final StringBuilder s = new StringBuilder();
    s.append(this.getClass().getSimpleName())
        .append("{")
        .append("message='").append(getMessage()).append('\'')
        .append(", dataTypeErrorCode='").append(errorMessageKey).append('\'')
        .append(", parserErrorCode='").append(parserErrorMessageKey).append('\'');
    if (targetTypeClass != null) {
      s.append(", targetTypeClass='").append(targetTypeClass).append('\'');
    }
    for (int i = 0; i < parserErrorMessageArgKeys.length; ++i) {
      s.append(", ").append(parserErrorMessageArgKeys[i]).append("='").append(parserErrorMessageArgValues[i]).append('\'');
    }
    s.append("}");
    return s.toString();
  }

  public static class ParserErrorMessageArgKeys {

    private ParserErrorMessageArgKeys() {
    }

    public static final String MIN_LENGTH = "minLength";
    public static final String MAX_LENGTH = "maxLength";
    public static final String INVALID_CODE_POINT = "invalidCodePoint";
    public static final String REGEX_PATTERN = "regexPattern";
  }

  public static class ParserErrorCode {

    private ParserErrorCode() {
    }

    public static final String INVALID_VALUE_DOES_NOT_MATCH_REGEX_PATTERN = "invalid_value_does_not_match_regex_pattern";
    public static final String INVALID_VALUE_DOES_NOT_PASS_CUSTOM_VALIDATION = "invalid_value_does_not_pass_custom_validation";
    public static final String INVALID_VALUE_INVALID_CHARACTER = "invalid_value_invalid_character";
    public static final String INVALID_VALUE_INVALID_CONTROL_CHARACTER = "invalid_value_invalid_control_character";
    public static final String INVALID_VALUE_INVALID_QUOTE_CHARACTER = "invalid_value_invalid_quote_character";
    public static final String INVALID_VALUE_INVALID_WHITESPACE_CHARACTER = "invalid_value_invalid_whitespace_character";
    public static final String INVALID_VALUE_TOO_LONG = "invalid_value_too_long";
    public static final String INVALID_VALUE_TOO_SHORT = "invalid_value_too_short";
  }

}
