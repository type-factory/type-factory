/*
   Copyright 2021-2022 Evan Toliopoulos (typefactory.org)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package org.typefactory;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import org.typefactory.impl.Factory;

/**
 * <p>Thrown by the {@link TypeParser} when it is unable to parse a value.</p>
 *
 * <p>The exception message will be the combination of the custom error message along with the parser error message.
 * The custom error message is specified when creating the type-parser, for example:</p>
 * <pre>{@code
 *   private static final TypeParser TYPE_PARSER =
 *       TypeParser.builder()
 *           .messageCode("must be a 2-character ISO 3166-1 alpha country code")
 *           ...
 *           .build();
 * }</pre>
 *
 * <p>The exception contains the invalid value which can be retrieved with {@link #getInvalidValue()}. This value will not be presented in the
 * exception message as it may contain sensitive, secret or personal information. It is captured solely to enable any exception handlers meaningful
 * access to the invalid value.</p>
 *
 * @author Evan Toliopoulos
 */
public class InvalidValueException extends IllegalArgumentException {


  @Serial
  private static final long serialVersionUID = -7198769839039479407L;

  /**
   * An immutable empty string constant.
   */
  private static final String EMPTY_STRING = "";

  /**
   * An immutable empty {@link MessageCode} constant.
   */
  private static final MessageCode EMPTY_MESSAGE_CODE = Factory.messageCode(EMPTY_STRING, EMPTY_STRING);

  /**
   * An immutable empty {@link ParserMessageCode} constant.
   */
  private static final ParserMessageCode EMPTY_PARSER_MESSAGE_CODE = Factory.parserMessageCode(EMPTY_STRING, EMPTY_STRING);

  /**
   * Immutable empty map for parser error properties.
   */
  private static final Map<String, Serializable> EMPTY_PARSER_ERROR_PROPERTIES = Map.of();

  /**
   * Immutable empty array for parser message arguments.
   */
  private static final Serializable[] EMPTY_PARSER_MESSAGE_ARGS = new Serializable[0];

  private final Class<?> targetTypeClass;

  private final MessageCode errorCode;

  private final ParserMessageCode parserErrorCode;

  private final Map<String, Serializable> parserErrorCodeArgs;

  private final Serializable[] parserErrorCodeArgsValues;

  private final String invalidValue;

  /**
   * Provides a builder to create {@link InvalidValueException} instances.
   *
   * @return a builder to create {@link InvalidValueException} instances.
   */
  public static InvalidValueExceptionBuilder builder() {
    return new InvalidValueExceptionBuilder();
  }


  /**
   * Protected constructor – use the {@link #builder()} to create an {@code InvalidValueException}.
   *
   * @param cause                 the optional cause of the exception. Pass {@code null} if there is no cause.
   * @param invalidValue          the invalid value that was rejected by the {@link TypeParser} when parsing the value to try to create a custom
   *                              type.
   * @param targetTypeClass       an optional target custom type class. Pass {@code null} if the target type class is not known.
   * @param errorCode           the key used to find the error message in the {@code org.typefactory.Messages} resource-bundle or properties file.
   *                              If no error message is found in the resource-bundle or properties file then this value will be used as the error
   *                              message.
   * @param parserErrorCode     the key used to find the parser error message in the {@code org.typefactory.Messages} resource-bundle or properties
   *                              file. If no parser error message is found in the resource-bundle or properties file then this value will be used as
   *                              the error message.
   * @param parserErrorCodeArgs the message arguments to supply to the message formatter used to create the error message.
   * @see #builder()
   */
  protected InvalidValueException(
      final Throwable cause,
      final CharSequence invalidValue,
      final Class<?> targetTypeClass,
      final MessageCode errorCode,
      final ParserMessageCode parserErrorCode,
      final LinkedHashMap<String, Serializable> parserErrorCodeArgs) {
    super(combineMessagesIntoSentences(errorCode, parserErrorCode,
            parserErrorCodeArgs == null
                ? EMPTY_PARSER_MESSAGE_ARGS
                : parserErrorCodeArgs.values().toArray(EMPTY_PARSER_MESSAGE_ARGS)),
        cause);
    this.invalidValue = invalidValue == null ? null : invalidValue.toString();
    this.targetTypeClass = targetTypeClass;
    this.errorCode = errorCode == null ? EMPTY_MESSAGE_CODE : errorCode;
    this.parserErrorCode = parserErrorCode == null ? EMPTY_PARSER_MESSAGE_CODE : parserErrorCode;
    this.parserErrorCodeArgs = parserErrorCodeArgs == null
        ? EMPTY_PARSER_ERROR_PROPERTIES
        : parserErrorCodeArgs;
    this.parserErrorCodeArgsValues = parserErrorCodeArgs == null
        ? EMPTY_PARSER_MESSAGE_ARGS
        : parserErrorCodeArgs.values().toArray(EMPTY_PARSER_MESSAGE_ARGS);
  }

  /**
   * <p>Provides the message associated with this exception.</p>
   *
   * <p>The message is a combination of the custom error message and the parser error message
   * provided by {@link #getErrorMessage()} and {@link #getParserErrorMessage()}.</p>
   *
   * @return the message associated with this exception.
   * @see #getLocalizedMessage()
   * @see #getLocalizedMessage(Locale)
   */
  @Override
  public String getMessage() {
    return super.getMessage();
  }

  /**
   * <p>Provides a localized message using {@link Locale#getDefault()} as the desired locale.</p>
   *
   * <p>Consider using {@link #getLocalizedMessage(Locale)} to get a message localized to the specified locale.</p>
   *
   * @return a localized message using {@link Locale#getDefault()} as the desired locale.
   * @see #getLocalizedMessage(Locale)
   * @see #getMessage()
   */
  @Override
  public String getLocalizedMessage() {
    return getLocalizedMessage(Locale.getDefault());
  }

  /**
   * <p>Provides a message localized to the specified {@code locale}.</p>
   *
   * @param locale the locale that you wish the message to be localized into.
   * @return a message localized to the specified {@code locale}.
   * @see #getLocalizedMessage()
   * @see #getMessage()
   */
  public String getLocalizedMessage(final Locale locale) {
    return combineMessagesIntoSentences(
        errorCode.message(locale),
        parserErrorCode.message(locale, parserErrorCodeArgsValues));
  }


  public Class<?> getTargetTypeClass() {
    return targetTypeClass;
  }


  /**
   * <p>Provides the error code for the custom type as a {@link MessageCode} instance.</p>
   *
   * <p>{@code MessageCode} values can be used in most places that a string value can be used
   * as it implements {@link CharSequence}. If an actual string value is required then use {@link #getErrorCodeAsString()}.</p>
   *
   * <p>Use {@link #getErrorMessage()} to return the message associated with this error code.</p>
   *
   * @return the error code for the custom type.
   * @see #getMessage()
   * @see #getLocalizedMessage()
   * @see #getLocalizedMessage(Locale)
   * @see #getErrorCodeAsString()
   * @see #getErrorMessage(Locale)
   * @see #getErrorMessage()
   * @see #getParserErrorCode()
   * @see #getParserErrorMessage()
   * @see #getParserErrorMessage(Locale)
   */
  public MessageCode getErrorCode() {
    return errorCode;
  }

  /**
   * <p>Provides the error code for the custom type as a string.</p>
   *
   * <p>Use {@link #getErrorCode()} to return the error code as a {@link MessageCode} instance
   * which can be used in most places that a string value can, as it implements {@link CharSequence}</p>
   *
   * <p>Use {@link #getErrorMessage()} to return the message associated with this error code.</p>
   *
   * @return the error code for the custom type as a string.
   * @see #getMessage()
   * @see #getLocalizedMessage()
   * @see #getLocalizedMessage(Locale)
   * @see #getErrorCode()
   * @see #getErrorMessage(Locale)
   * @see #getErrorMessage()
   * @see #getParserErrorCode()
   * @see #getParserErrorMessage()
   * @see #getParserErrorMessage(Locale)
   */
  public String getErrorCodeAsString() {
    return errorCode.code();
  }

  /**
   * <p>Provides the error code for the custom type. <b>Deprecated</b>, use {@link #getErrorCode()} instead.</p>
   *
   * <p>Use {@link #getErrorMessage()} to return the message associated with this error code.</p>
   *
   * @return the error message for the custom type.
   * @see #getMessage()
   * @see #getLocalizedMessage()
   * @see #getLocalizedMessage(Locale)
   * @see #getErrorCode()
   * @see #getErrorCodeAsString()
   * @see #getErrorMessage(Locale)
   * @see #getErrorMessage()
   * @see #getParserErrorCode()
   * @see #getParserErrorMessage()
   * @see #getParserErrorMessage(Locale)
   * @deprecated use {@link #getErrorCode()} or {@link #getErrorCodeAsString()} instead.
   */
  @Deprecated(since = "1.1", forRemoval = true)
  public String getMessageCode() {
    return getErrorCodeAsString();
  }

  /**
   * <p>Provides the error message associated with the error code that is provided by {@link #getErrorCode()}.</p>
   *
   * <p>The result differs from the {@link #getMessage()} method in that {@code getMessage()} returns a
   * complete error message combining the two messages provided by {@code getErrorMessage()} and {@link #getParserErrorMessage()}.</p>
   *
   * <p>Use {@link #getErrorMessage(Locale)} to return the message localized to the specified locale.</p>
   *
   * @return the error message for the custom type.
   * @see #getMessage()
   * @see #getLocalizedMessage()
   * @see #getLocalizedMessage(Locale)
   * @see #getErrorCode()
   * @see #getErrorMessage(Locale)
   * @see #getParserErrorCode()
   * @see #getParserErrorMessage()
   * @see #getParserErrorMessage(Locale)
   */
  public String getErrorMessage() {
    return errorCode.message();
  }

  /**
   * <p>Provides the localized error message associated with the error code that is provided by {@link #getErrorCode()}.</p>
   *
   * <p>The result differs from the {@link #getLocalizedMessage(Locale)} method in that {@code getLocalizedMessage(Locale)}
   * returns a complete error message combining the two messages provided by {@link #getErrorMessage(Locale)} and
   * {@link #getParserErrorMessage(Locale)}.</p>
   *
   * @param locale the locale that you wish the message to be localized into.
   * @return the error message for the custom type localized to the specified locale.
   * @see #getMessage()
   * @see #getLocalizedMessage()
   * @see #getLocalizedMessage(Locale)
   * @see #getErrorCode()
   * @see #getErrorMessage()
   * @see #getParserErrorCode()
   * @see #getParserErrorMessage()
   * @see #getParserErrorMessage(Locale)
   */
  public String getErrorMessage(final Locale locale) {
    return errorCode.message(locale);
  }

  /**
   * <p>Provides the parser error code as a {@link ParserMessageCode} instance.</p>
   *
   * <p>{@code ParserMessageCode} values can be used in most places that a string value can be used
   * as it implements {@link CharSequence}. If an actual string value is required then use {@link #getParserErrorCodeAsString()}.</p>
   *
   * <p>Use {@link #getParserErrorMessage()} to return the parser error message associated with this parser error code.</p>
   *
   * @return the parser error code.
   * @see #getMessage()
   * @see #getLocalizedMessage()
   * @see #getLocalizedMessage(Locale)
   * @see #getErrorCode()
   * @see #getErrorCodeAsString()
   * @see #getErrorMessage(Locale)
   * @see #getErrorMessage()
   * @see #getParserErrorMessage()
   * @see #getParserErrorMessage(Locale)
   */
  public ParserMessageCode getParserErrorCode() {
    return parserErrorCode;
  }

  /**
   * <p>Provides the parser error code as a string.</p>
   *
   * <p>Use {@link #getParserErrorCode()} to return the parser error code as a {@link ParserMessageCode} instance
   * which can be used in most places that a string value can, as it implements {@link CharSequence}</p>
   *
   * <p>Use {@link #getParserErrorMessage()} to return the parser error message associated with this parser error code.</p>
   *
   * @return the parser error code as a string.
   * @see #getMessage()
   * @see #getLocalizedMessage()
   * @see #getLocalizedMessage(Locale)
   * @see #getErrorCode()
   * @see #getErrorCodeAsString()
   * @see #getErrorMessage(Locale)
   * @see #getErrorMessage()
   * @see #getParserErrorCode()
   * @see #getParserErrorMessage()
   * @see #getParserErrorMessage(Locale)
   */
  public String getParserErrorCodeAsString() {
    return parserErrorCode.code();
  }

  /**
   * <p>Provides the parser error code as a string. <b>Deprecated</b>, use {@link #getParserErrorCode()} instead.</p>
   *
   * <p>Use {@link #getParserErrorMessage()} to return the parser error message associated with this parser error code.</p>
   *
   * @return the parser error code as a string.
   * @see #getMessage()
   * @see #getLocalizedMessage()
   * @see #getLocalizedMessage(Locale)
   * @see #getErrorCode()
   * @see #getErrorCodeAsString()
   * @see #getErrorMessage(Locale)
   * @see #getErrorMessage()
   * @see #getParserErrorCode()
   * @see #getParserErrorMessage()
   * @see #getParserErrorMessage(Locale)
   * @deprecated use {@link #getParserErrorCode()} or {@link #getParserErrorCodeAsString()} instead.
   */
  @Deprecated(since = "1.1", forRemoval = true)
  public String getParserMessageCode() {
    return parserErrorCode.code();
  }

  /**
   * <p>Provides the parser error message associated with the parser error code that is provided by {@link #getParserErrorCode()}.</p>
   *
   * <p>The result differs from the {@link #getMessage()} method in that {@code getMessage()} returns a
   * complete error message combining the two messages provided by {@link #getErrorMessage()} and
   * {@code #getParserErrorMessage()}.</p>
   *
   * <p>Use {@link #getParserErrorMessage(Locale)} to return the parser error message localized to the specified locale.</p>
   *
   * @return the parser error message.
   * @see #getMessage()
   * @see #getLocalizedMessage()
   * @see #getLocalizedMessage(Locale)
   * @see #getErrorCode()
   * @see #getErrorCodeAsString()
   * @see #getErrorMessage(Locale)
   * @see #getErrorMessage()
   * @see #getParserErrorCode()
   * @see #getParserErrorMessage(Locale)
   */
  public String getParserErrorMessage() {
    return parserErrorCode.message(parserErrorCodeArgsValues);
  }

  /**
   * <p>Provides the parser error message associated with the parser error code that is provided by {@link #getParserErrorCode()}.</p>
   *
   * <p>The result differs from the {@link #getLocalizedMessage(Locale)} method in that {@code getLocalizedMessage(Locale)}
   * returns a complete error message combining the two messages provided by {@link #getErrorMessage(Locale)} and
   * {@code #getParserErrorMessage(Locale)}.</p>
   *
   * @param locale the locale that you wish the message to be localized into.
   * @return the parser error message localized to the specified locale.
   * @see #getMessage()
   * @see #getLocalizedMessage()
   * @see #getLocalizedMessage(Locale)
   * @see #getErrorCode()
   * @see #getErrorCodeAsString()
   * @see #getErrorMessage(Locale)
   * @see #getErrorMessage()
   * @see #getParserErrorCode()
   * @see #getParserErrorMessage()
   */
  public String getParserErrorMessage(final Locale locale) {
    return parserErrorCode.message(locale, parserErrorCodeArgsValues);
  }

  /**
   * <p>Provides the parser error properties which can be interrogated or logged as needs.
   * Internally, the entry order is maintained so that the values can be used as arguments
   * to the message formatter.</p>
   *
   * @return the parser error properties that were used to create the parser error message.
   */
  public Map<String, Serializable> getParserErrorProperties() {
    return new LinkedHashMap<>(parserErrorCodeArgs);
  }

  /**
   * <p>Provides the parser error arguments which are used as arguments
   * to the message formatter.</p>
   *
   * @return the parser error arguments that were used to create the parser error message.
   */
  public Serializable [] getParserErrorArgs() {
    return Arrays.copyOf(parserErrorCodeArgsValues, parserErrorCodeArgsValues.length, Serializable[].class);
  }

  /**
   * <p>Provides the value considered invalid by the {@link TypeParser}.</p>
   *
   * <p>This value is not presented in the {@link InvalidValueException} message as it may contain sensitive, secret or personal information.
   * It is captured solely to enable any exception-handlers meaningful access to the invalid value.</p>
   *
   * @return the value that was considered invalid by the {@link TypeParser}.
   */
  public String getInvalidValue() {
    return invalidValue;
  }

  /**
   * A builder to create {@link InvalidValueException} instances.
   */
  public static class InvalidValueExceptionBuilder {

    private Throwable cause;
    private Class<?> targetTypeClass;
    private MessageCode errorCode;
    private ParserMessageCode parserErrorCode;

    private final LinkedHashMap<String, Serializable> parserErrorCodeArgs = new LinkedHashMap<>();
    private String invalidValue;

    /**
     * Creates and returns an {@link InvalidValueException} instance.
     *
     * @return an {@link InvalidValueException} instance.
     */
    public InvalidValueException build() {
      return new InvalidValueException(cause, invalidValue, targetTypeClass, errorCode, parserErrorCode, parserErrorCodeArgs);
    }

    /**
     * Set the cause of the exception you are creating.
     *
     * @param cause the cause of the exception.
     * @return this builder
     */
    public InvalidValueExceptionBuilder cause(final Throwable cause) {
      this.cause = cause;
      return this;
    }

    /**
     * Set the target custom type class if known. For example, if the custom type is a class called, say {@code CurrencyCode}, then you would provide
     * {@code CurrencyCode.class}.
     *
     * @param targetTypeClass the target custom type class if known.
     * @return this builder
     */
    public InvalidValueExceptionBuilder targetTypeClass(final Class<?> targetTypeClass) {
      this.targetTypeClass = targetTypeClass;
      return this;
    }

    /**
     * Set the key used to find the error message in the {@code org.typefactory.Messages} resource-bundle or properties file.
     *
     * @param errorCode the key used to find the error message in the {@code org.typefactory.Messages} resource-bundle or properties file. If no
     *                  error message is found in the resource-bundle or properties file then the value you provide here will be used as the error
     *                  message.
     * @return this builder
     */
    public InvalidValueExceptionBuilder errorCode(final MessageCode errorCode) {
      this.errorCode = errorCode;
      return this;
    }

    /**
     * Set the key used to find the error message in the {@code org.typefactory.Messages} resource-bundle or properties file.
     *
     * @param errorCode the key used to find the error message in the {@code org.typefactory.Messages} resource-bundle or properties file. If no
     *                    error message is found in the resource-bundle or properties file then the value you provide here will be used as the error
     *                    message.
     * @return this builder
     * @deprecated use {@link #errorCode(MessageCode)} instead.
     */
    @Deprecated(since = "1.1", forRemoval = true)
    public InvalidValueExceptionBuilder messageCode(final MessageCode errorCode) {
      this.errorCode = errorCode;
      return this;
    }

    /**
     * Set the key used to find the parser error message in the {@code org.typefactory.Messages} resource-bundle or properties file.
     *
     * @param parserErrorCode the key used to find the parser error message in the {@code org.typefactory.Messages} resource-bundle or properties
     *                          file. If no parser error message is found in the resource-bundle or properties file then the value you provide here
     *                          will be used as the error message.
     * @return this builder
     */
    public InvalidValueExceptionBuilder parserErrorCode(final ParserMessageCode parserErrorCode) {
      this.parserErrorCode = parserErrorCode;
      return this;
    }

    /**
     * Set the key used to find the parser error message in the {@code org.typefactory.Messages} resource-bundle or properties file.
     *
     * @param parserErrorCode the key used to find the parser error message in the {@code org.typefactory.Messages} resource-bundle or properties
     *                          file. If no parser error message is found in the resource-bundle or properties file then the value you provide here
     *                          will be used as the error message.
     * @return this builder
     * @deprecated use {@link #parserErrorCode(ParserMessageCode)} instead.
     */
    @Deprecated(since = "1.1", forRemoval = true)
    public InvalidValueExceptionBuilder parserMessageCode(final ParserMessageCode parserErrorCode) {
      this.parserErrorCode = parserErrorCode;
      return this;
    }

    /**
     * <p>Add message arguments to supply to the message formatter used to create the parser error message.</p>
     *
     * <p>Note: this method will silently ignore attempts to add parser message arguments that are provided with a null or blank {@code key}.</p>
     *
     * @param key   the message argument name. Note that this is ignored for purposes of message creation by the message formatter which uses argument
     *              index only. It is captured solely to enable any exception handlers meaningful access to argument name-value pairs. This method
     *              will silently ignore attempts to add parser message arguments that are provided with a null or blank {@code key}.
     * @param value the message argument value.
     * @param <V>   message argument values must be serializable because the {@link InvalidValueException} class is serializable.
     * @return this builder
     */
    public <V extends Serializable> InvalidValueExceptionBuilder addParserErrorCodeArg(final String key, final V value) {
      if (key != null && !key.isBlank()) {
        this.parserErrorCodeArgs.put(key, value);
      }
      return this;
    }

    /**
     * <p>Add message arguments to supply to the message formatter used to create the parser error message.</p>
     *
     * <p>Note: this method will silently ignore attempts to add parser message arguments that are provided with a null or blank {@code key}.</p>
     *
     * @param key   the message argument name. Note that this is ignored for purposes of message creation by the message formatter which uses argument
     *              index only. It is captured solely to enable any exception handlers meaningful access to argument name-value pairs. This method
     *              will silently ignore attempts to add parser message arguments that are provided with a null or blank {@code key}.
     * @param value the message argument value.
     * @param <V>   message argument values must be serializable because the {@link InvalidValueException} class is serializable.
     * @return this builder
     * @deprecated use {@link #addParserErrorCodeArg(String, Serializable)} instead.
     */
    @Deprecated(since = "1.1", forRemoval = true)
    public <V extends Serializable> InvalidValueExceptionBuilder addParserMessageCodeArg(final String key, final V value) {
      if (key != null && !key.isBlank()) {
        this.parserErrorCodeArgs.put(key, value);
      }
      return this;
    }

    /**
     * Set the value that was considered invalid by the {@link TypeParser}. This value is not presented in the {@link InvalidValueException} message
     * as it may contain sensitive, secret or personal information. It is captured solely to enable any exception handlers meaningful access to the
     * invalid value.
     *
     * @param invalidValue the value that was considered invalid by the {@link TypeParser}.
     * @return this builder
     */
    public InvalidValueExceptionBuilder invalidValue(final CharSequence invalidValue) {
      if (invalidValue instanceof String invalidValueString) {
        this.invalidValue = invalidValueString;
      } else {
        this.invalidValue = invalidValue == null ? null : invalidValue.toString();
      }
      return this;
    }
  }

  protected static String combineMessagesIntoSentences(
      final MessageCode messageCode1,
      final MessageCode messageCode2,
      final Object[] messageCode2Args) {

    final Object[] message2Args = messageCode2Args == null ? EMPTY_PARSER_MESSAGE_ARGS : messageCode2Args;
    final String message1 = messageCode1 == null ? EMPTY_STRING : messageCode1.message();
    final String message2 = messageCode2 == null ? EMPTY_STRING : messageCode2.message(message2Args);

    return combineMessagesIntoSentences(message1, message2);
  }

  protected static String combineMessagesIntoSentences(
      final String message1,
      final String message2) {

    if (message1 == null || message1.isBlank()) {
      if (message2 == null || message2.isBlank()) {
        return EMPTY_STRING;
      }
      return message2;
    }

    if (message2 == null || message2.isBlank()) {
      return message1;
    }

    final StringBuilder s = new StringBuilder(message2.length() + message1.length() + 4);
    s.append(message1.trim());
    if (s.charAt(s.length() - 1) == '.') {
      s.append(' ');
    } else {
      s.append(". ");
    }
    s.append(message2);
    return s.toString();
  }

  /**
   * <p>Provides a string representation of the {@link InvalidValueException}.</p>
   *
   * <p><b>Note</b>, it will not include the invalid-value (provided by {@link #getInvalidValue()}) as it may include sensitive, secret, or personal
   * information. It is left to your own exception handlers to decide if they wish to show or log the invalid-value.</p>
   *
   * @return a string representation of the {@link InvalidValueException}.
   * @see #getInvalidValue()
   */
  @Override
  public String toString() {
    final StringBuilder s = new StringBuilder();
    s.append(this.getClass().getSimpleName())
        .append("{")
        .append("message='").append(getMessage()).append('\'')
        .append(", messageCode='").append(errorCode.code()).append('\'')
        .append(", defaultErrorMessage='").append(errorCode.defaultMessage()).append('\'')
        .append(", parserMessageCode='").append(parserErrorCode.code()).append('\'')
        .append(", defaultParserErrorMessage='").append(parserErrorCode.defaultMessage()).append('\'');
    for (Map.Entry<String, Serializable> entry : parserErrorCodeArgs.entrySet()) {
      s.append(", ").append(entry.getKey()).append("='").append(entry.getValue()).append('\'');
    }
    if (targetTypeClass != null) {
      s.append(", targetTypeClass='").append(targetTypeClass).append('\'');
    }
    if (getCause() != null) {
      s.append(", cause='").append(getCause().getClass().getSimpleName()).append(" - ").append(getCause().getMessage()).append('\'');
    }
    s.append("}");
    return s.toString();
  }

  /**
   * <p>Parser message codes are used internally by the {@link TypeParser}.</p>
   */
  public interface ParserMessageCode extends MessageCode {

    ParserMessageCode INVALID_VALUE_DOES_NOT_MATCH_REGEX_PATTERN = Factory.parserMessageCode(
        "invalid_value_does_not_match_regex_pattern",
        "Invalid value - does not match regular-expression pattern {0}");

    ParserMessageCode INVALID_VALUE_DOES_NOT_PASS_CUSTOM_VALIDATION = Factory.parserMessageCode(
        "invalid_value_does_not_pass_custom_validation",
        "Invalid value - does not pass custom validation criteria.");

    ParserMessageCode INVALID_VALUE_INVALID_CHARACTER = Factory.parserMessageCode(
        "invalid_value_invalid_character",
        "Invalid value - invalid character {0}.");

    ParserMessageCode INVALID_VALUE_INVALID_CONTROL_CHARACTER = Factory.parserMessageCode(
        "invalid_value_invalid_control_character",
        "Invalid value - invalid control character {0}.");

    ParserMessageCode INVALID_VALUE_INVALID_FORMAT_CHARACTER = Factory.parserMessageCode(
        "invalid_value_invalid_format_character",
        "Invalid value - invalid format character {0}.");

    ParserMessageCode INVALID_VALUE_INVALID_QUOTE_CHARACTER = Factory.parserMessageCode(
        "invalid_value_invalid_quote_character",
        "Invalid value - invalid quote character {0}.");

    ParserMessageCode INVALID_VALUE_INVALID_WHITESPACE_CHARACTER = Factory.parserMessageCode(
        "invalid_value_invalid_whitespace_character",
        "Invalid value - invalid white-space character {0}.");

    /**
     * In the <a href="https://www.unicode.org/glossary/">Unicode documentation</a>, a surrogate code point is:
     * <blockquote>
     * A Unicode code point in the range U+D800..U+DFFF. Reserved for use by UTF-16, where a pair of surrogate code units (a
     * <a href="https://www.unicode.org/glossary/#high_surrogate_code_unit">high surrogate</a>
     * followed by a <a href="https://www.unicode.org/glossary/#low_surrogate_code_unit">low surrogate</a>) “stand in” for a <a
     * href="https://www.unicode.org/glossary/#supplementary_code_point">supplementary code point</a>.
     * </blockquote>
     *
     * @see <a href="https://www.unicode.org/glossary/#surrogate_code_point">Surrogate code point</a>
     * @see <a href="https://www.unicode.org/glossary/#surrogate_pair">Surrogate pair</a>
     * @see <a href="https://www.unicode.org/glossary/#high_surrogate_code_unit">High-surrogate code unit</a>
     * @see <a href="https://www.unicode.org/glossary/#low_surrogate_code_unit">Low-surrogate code unit</a>
     * @see <a href="https://www.unicode.org/glossary/#supplementary_code_point">Supplementary code point</a>
     */
    ParserMessageCode INVALID_VALUE_HIGH_SURROGATE_WITHOUT_LOW_SURROGATE = Factory.parserMessageCode(
        "invalid_value_high_surrogate_without_low_surrogate",
        "Invalid value - incomplete surrogate-pair - the low-surrogate code unit is missing for the high-surrogate code unit {0}.");

    /**
     * In the <a href="https://www.unicode.org/glossary/">Unicode documentation</a>, a surrogate code point is:
     * <blockquote>
     * A Unicode code point in the range U+D800..U+DFFF. Reserved for use by UTF-16, where a pair of surrogate code units (a
     * <a href="https://www.unicode.org/glossary/#high_surrogate_code_unit">high surrogate</a>
     * followed by a <a href="https://www.unicode.org/glossary/#low_surrogate_code_unit">low surrogate</a>) “stand in” for a <a
     * href="https://www.unicode.org/glossary/#supplementary_code_point">supplementary code point</a>.
     * </blockquote>
     *
     * @see <a href="https://www.unicode.org/glossary/#surrogate_code_point">Surrogate code point</a>
     * @see <a href="https://www.unicode.org/glossary/#surrogate_pair">Surrogate pair</a>
     * @see <a href="https://www.unicode.org/glossary/#high_surrogate_code_unit">High-surrogate code unit</a>
     * @see <a href="https://www.unicode.org/glossary/#low_surrogate_code_unit">Low-surrogate code unit</a>
     * @see <a href="https://www.unicode.org/glossary/#supplementary_code_point">Supplementary code point</a>
     */
    ParserMessageCode INVALID_VALUE_LOW_SURROGATE_WITHOUT_HIGH_SURROGATE = Factory.parserMessageCode(
        "invalid_value_low_surrogate_without_high_surrogate",
        "Invalid value - incomplete surrogate-pair - the high-surrogate code unit is missing for the low-surrogate code unit {0}.");

    ParserMessageCode INVALID_VALUE_TOO_LONG = Factory.parserMessageCode(
        "invalid_value_too_long",
        "Invalid value - too long, maximum length is {0,number,integer}.");

    ParserMessageCode INVALID_VALUE_TOO_SHORT = Factory.parserMessageCode(
        "invalid_value_too_short",
        "Invalid value - too short, minimum length is {0,number,integer}.");
  }

}
