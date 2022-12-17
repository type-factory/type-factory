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
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import org.typefactory.impl.ExceptionUtils;
import org.typefactory.impl.Factory;

/**
 * <p>Thrown by the {@link TypeParser} when it is unable to parse a value.</p>
 *
 * <p>The exception message will be the combination of the custom error message along with the parser error message.
 * The custom error message is specified when creating the type-parser, for example:</p>
 * <pre>{@code
 *   private static final TypeParser TYPE_PARSER =
 *       TypeParser.builder()
 *           .errorCode("must be a 2-character ISO 3166-1 alpha country code")
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
   * Immutable empty array.
   */
  private static final Serializable[] EMPTY_PARSER_MESSAGE_ARGS = new Serializable[0];

  /**
   * Immutable empty map.
   */
  private static final Map<String, Serializable> EMPTY_PARSER_ERROR_PROPERTIES = Map.of();

  private final Class<?> targetTypeClass;

  private final ErrorCode errorCode;

  private final ParserErrorCode parserErrorCode;

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
   * @param cause               the optional cause of the exception. Pass {@code null} if there is no cause.
   * @param invalidValue        the invalid value that was rejected by the {@link TypeParser} when parsing the value to try to create a custom type.
   * @param targetTypeClass     an optional target custom type class. Pass {@code null} if the target type class is not known.
   * @param errorCode           the key used to find the error message in the {@code org.typefactory.Messages} resource-bundle or properties file. If
   *                            no error message is found in the resource-bundle or properties file then this value will be used as the error
   *                            message.
   * @param parserErrorCode     the key used to find the parser error message in the {@code org.typefactory.Messages} resource-bundle or properties
   *                            file. If no parser error message is found in the resource-bundle or properties file then this value will be used as
   *                            the error message.
   * @param parserErrorCodeArgs the message arguments to supply to the message formatter used to create the error message.
   * @see #builder()
   */
  protected InvalidValueException(
      final Throwable cause,
      final CharSequence invalidValue,
      final Class<?> targetTypeClass,
      final ErrorCode errorCode,
      final ParserErrorCode parserErrorCode,
      final LinkedHashMap<String, Serializable> parserErrorCodeArgs) {
    super(ExceptionUtils.getCombinedMessage(errorCode, parserErrorCode, parserErrorCodeArgs == null
            ? EMPTY_PARSER_MESSAGE_ARGS
            : parserErrorCodeArgs.values().toArray(EMPTY_PARSER_MESSAGE_ARGS)),
        cause);
    this.invalidValue = invalidValue == null ? null : invalidValue.toString();
    this.targetTypeClass = targetTypeClass;
    this.errorCode = errorCode;
    this.parserErrorCode = parserErrorCode;
    this.parserErrorCodeArgs = parserErrorCodeArgs == null
        ? EMPTY_PARSER_ERROR_PROPERTIES
        : parserErrorCodeArgs;
    this.parserErrorCodeArgsValues = parserErrorCodeArgs == null
        ? EMPTY_PARSER_MESSAGE_ARGS
        : parserErrorCodeArgs.values().toArray(EMPTY_PARSER_MESSAGE_ARGS);
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
    return ExceptionUtils.getCombinedMessage(locale, errorCode, parserErrorCode, parserErrorCodeArgsValues);
  }


  public Class<?> getTargetTypeClass() {
    return targetTypeClass;
  }

  public String getErrorCode() {
    return errorCode.code();
  }

  public String getErrorMessage() {
    return getErrorMessage(Locale.getDefault());
  }

  public String getErrorMessage(final Locale locale) {
    return ExceptionUtils.getMessage(locale, errorCode);
  }

  public String getParserErrorCode() {
    return parserErrorCode.code();
  }

  public String getParserErrorMessage() {
    return getParserErrorMessage(Locale.getDefault());
  }

  public String getParserErrorMessage(final Locale locale) {
    return ExceptionUtils.getMessage(locale, parserErrorCode, parserErrorCodeArgsValues);
  }

  public Map<String, Serializable> getParserErrorProperties() {
    return parserErrorCodeArgs;
  }

  public String getInvalidValue() {
    return invalidValue;
  }

  /**
   * A builder to create {@link InvalidValueException} instances.
   */
  public static class InvalidValueExceptionBuilder {

    private Throwable cause;
    private Class<?> targetTypeClass;
    private ErrorCode errorCode;
    private ParserErrorCode parserErrorCode;

    private LinkedHashMap<String, Serializable> parserErrorCodeArgs = new LinkedHashMap<>();
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
     * @param errorCode the key used to find the error message in the {@code org.typefactory.Messages} resource-bundle or properties file. If no error
     *                  message is found in the resource-bundle or properties file then the value you provide here will be used as the error message.
     * @return this builder
     */
    public InvalidValueExceptionBuilder errorCode(final ErrorCode errorCode) {
      this.errorCode = errorCode;
      return this;
    }

    /**
     * Set the key used to find the parser error message in the {@code org.typefactory.Messages} resource-bundle or properties file.
     *
     * @param parserErrorCode the key used to find the parser error message in the {@code org.typefactory.Messages} resource-bundle or properties
     *                        file. If no parser error message is found in the resource-bundle or properties file then the value you provide here will
     *                        be used as the error message.
     * @return this builder
     */
    public InvalidValueExceptionBuilder parserErrorCode(final ParserErrorCode parserErrorCode) {
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
     * @return
     */
    public <V extends Serializable> InvalidValueExceptionBuilder addParserErrorCodeArgs(final String key, final V value) {
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
        .append(", errorCode='").append(errorCode.code()).append('\'')
        .append(", defaultErrorMessage='").append(errorCode.defaultMessage()).append('\'')
        .append(", parserErrorCode='").append(parserErrorCode.code()).append('\'')
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
   * <p>Parser error-codes are used internally by the {@link TypeParser}.</p>
   */
  public interface ParserErrorCode extends ErrorCode {

    ParserErrorCode INVALID_VALUE_DOES_NOT_MATCH_REGEX_PATTERN = Factory.parserErrorCode(
        "invalid_value_does_not_match_regex_pattern",
        "Invalid value - does not match regular-expression pattern {0}");
    ParserErrorCode INVALID_VALUE_DOES_NOT_PASS_CUSTOM_VALIDATION = Factory.parserErrorCode(
        "invalid_value_does_not_pass_custom_validation",
        "Invalid value - does not pass custom validation criteria.");
    ParserErrorCode INVALID_VALUE_INVALID_CHARACTER = Factory.parserErrorCode(
        "invalid_value_invalid_character",
        "Invalid value - invalid character ''{0}''.");
    ParserErrorCode INVALID_VALUE_INVALID_CONTROL_CHARACTER = Factory.parserErrorCode(
        "invalid_value_invalid_control_character",
        "Invalid value - invalid control character {0}.");
    ParserErrorCode INVALID_VALUE_INVALID_QUOTE_CHARACTER = Factory.parserErrorCode(
        "invalid_value_invalid_quote_character",
        "Invalid value - invalid quote character \"{0}\".");
    ParserErrorCode INVALID_VALUE_INVALID_WHITESPACE_CHARACTER = Factory.parserErrorCode(
        "invalid_value_invalid_whitespace_character",
        "Invalid value - invalid white-space character {0}.");
    ParserErrorCode INVALID_VALUE_TOO_LONG = Factory.parserErrorCode(
        "invalid_value_too_long",
        "Invalid value - too long, maximum length is {0,number,integer}.");
    ParserErrorCode INVALID_VALUE_TOO_SHORT = Factory.parserErrorCode(
        "invalid_value_too_short",
        "Invalid value - too short, minimum length is {0,number,integer}.");
  }

}
