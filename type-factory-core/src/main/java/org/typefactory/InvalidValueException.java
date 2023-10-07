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

  private static final String EMPTY_STRING = "";

  /**
   * Immutable empty array.
   */
  private static final Serializable[] EMPTY_PARSER_MESSAGE_ARGS = new Serializable[0];

  private static final MessageCode EMPTY_MESSAGE_CODE = Factory.messageCode(EMPTY_STRING, EMPTY_STRING);

  private static final ParserMessageCode EMPTY_PARSER_MESSAGE_CODE = Factory.parserMessageCode(EMPTY_STRING, EMPTY_STRING);

  /**
   * Immutable empty map.
   */
  private static final Map<String, Serializable> EMPTY_PARSER_ERROR_PROPERTIES = Map.of();

  private final Class<?> targetTypeClass;

  private final MessageCode messageCode;

  private final ParserMessageCode parserMessageCode;

  private final Map<String, Serializable> parserMessageCodeArgs;

  private final Serializable[] parserMessageCodeArgsValues;

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
   * @param messageCode           the key used to find the error message in the {@code org.typefactory.Messages} resource-bundle or properties file.
   *                              If no error message is found in the resource-bundle or properties file then this value will be used as the error
   *                              message.
   * @param parserMessageCode     the key used to find the parser error message in the {@code org.typefactory.Messages} resource-bundle or properties
   *                              file. If no parser error message is found in the resource-bundle or properties file then this value will be used as
   *                              the error message.
   * @param parserMessageCodeArgs the message arguments to supply to the message formatter used to create the error message.
   * @see #builder()
   */
  protected InvalidValueException(
      final Throwable cause,
      final CharSequence invalidValue,
      final Class<?> targetTypeClass,
      final MessageCode messageCode,
      final ParserMessageCode parserMessageCode,
      final LinkedHashMap<String, Serializable> parserMessageCodeArgs) {
    super(combineMessagesIntoSentences(messageCode, parserMessageCode,
            parserMessageCodeArgs == null
                ? EMPTY_PARSER_MESSAGE_ARGS
                : parserMessageCodeArgs.values().toArray(EMPTY_PARSER_MESSAGE_ARGS)),
        cause);
    this.invalidValue = invalidValue == null ? null : invalidValue.toString();
    this.targetTypeClass = targetTypeClass;
    this.messageCode = messageCode == null ? EMPTY_MESSAGE_CODE : messageCode;
    this.parserMessageCode = parserMessageCode == null ? EMPTY_PARSER_MESSAGE_CODE : parserMessageCode;
    this.parserMessageCodeArgs = parserMessageCodeArgs == null
        ? EMPTY_PARSER_ERROR_PROPERTIES
        : parserMessageCodeArgs;
    this.parserMessageCodeArgsValues = parserMessageCodeArgs == null
        ? EMPTY_PARSER_MESSAGE_ARGS
        : parserMessageCodeArgs.values().toArray(EMPTY_PARSER_MESSAGE_ARGS);
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
        messageCode.message(locale),
        parserMessageCode.message(locale, parserMessageCodeArgsValues));
  }


  public Class<?> getTargetTypeClass() {
    return targetTypeClass;
  }

  public String getMessageCode() {
    return messageCode.code();
  }

  public String getErrorMessage() {
    return messageCode.message();
  }

  public String getErrorMessage(final Locale locale) {
    return messageCode.message(locale);
  }

  public String getParserMessageCode() {
    return parserMessageCode.code();
  }

  public String getParserErrorMessage() {
    return parserMessageCode.message(parserMessageCodeArgsValues);
  }

  public String getParserErrorMessage(final Locale locale) {
    return parserMessageCode.message(locale, parserMessageCodeArgsValues);
  }

  public Map<String, Serializable> getParserErrorProperties() {
    return parserMessageCodeArgs;
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
    private MessageCode messageCode;
    private ParserMessageCode parserMessageCode;

    private final LinkedHashMap<String, Serializable> parserMessageCodeArgs = new LinkedHashMap<>();
    private String invalidValue;

    /**
     * Creates and returns an {@link InvalidValueException} instance.
     *
     * @return an {@link InvalidValueException} instance.
     */
    public InvalidValueException build() {
      return new InvalidValueException(cause, invalidValue, targetTypeClass, messageCode, parserMessageCode, parserMessageCodeArgs);
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
     * @param messageCode the key used to find the error message in the {@code org.typefactory.Messages} resource-bundle or properties file. If no
     *                    error message is found in the resource-bundle or properties file then the value you provide here will be used as the error
     *                    message.
     * @return this builder
     */
    public InvalidValueExceptionBuilder messageCode(final MessageCode messageCode) {
      this.messageCode = messageCode;
      return this;
    }

    /**
     * Set the key used to find the parser error message in the {@code org.typefactory.Messages} resource-bundle or properties file.
     *
     * @param parserMessageCode the key used to find the parser error message in the {@code org.typefactory.Messages} resource-bundle or properties
     *                          file. If no parser error message is found in the resource-bundle or properties file then the value you provide here
     *                          will be used as the error message.
     * @return this builder
     */
    public InvalidValueExceptionBuilder parserMessageCode(final ParserMessageCode parserMessageCode) {
      this.parserMessageCode = parserMessageCode;
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
    public <V extends Serializable> InvalidValueExceptionBuilder addParserMessageCodeArg(final String key, final V value) {
      if (key != null && !key.isBlank()) {
        this.parserMessageCodeArgs.put(key, value);
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
        .append(", messageCode='").append(messageCode.code()).append('\'')
        .append(", defaultErrorMessage='").append(messageCode.defaultMessage()).append('\'')
        .append(", parserMessageCode='").append(parserMessageCode.code()).append('\'')
        .append(", defaultParserErrorMessage='").append(parserMessageCode.defaultMessage()).append('\'');
    for (Map.Entry<String, Serializable> entry : parserMessageCodeArgs.entrySet()) {
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
        "Invalid value - invalid character ''{0}''.");
    ParserMessageCode INVALID_VALUE_INVALID_CONTROL_CHARACTER = Factory.parserMessageCode(
        "invalid_value_invalid_control_character",
        "Invalid value - invalid control character {0}.");
    ParserMessageCode INVALID_VALUE_INVALID_QUOTE_CHARACTER = Factory.parserMessageCode(
        "invalid_value_invalid_quote_character",
        "Invalid value - invalid quote character \"{0}\".");
    ParserMessageCode INVALID_VALUE_INVALID_WHITESPACE_CHARACTER = Factory.parserMessageCode(
        "invalid_value_invalid_whitespace_character",
        "Invalid value - invalid white-space character {0}.");

    /**
     * In the <a href="https://www.unicode.org/glossary/">Unicode documentation</a>, a surrogate code point is:
     * <blockquote>
     *   A Unicode code point in the range U+D800..U+DFFF. Reserved for use by UTF-16,
     *   where a pair of surrogate code units (a
     *   <a href="https://www.unicode.org/glossary/#high_surrogate_code_unit">high surrogate</a>
     *   followed by a <a href="https://www.unicode.org/glossary/#low_surrogate_code_unit">low surrogate</a>)
     *   “stand in” for a <a href="https://www.unicode.org/glossary/#supplementary_code_point">supplementary code point</a>.
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
     *   A Unicode code point in the range U+D800..U+DFFF. Reserved for use by UTF-16,
     *   where a pair of surrogate code units (a
     *   <a href="https://www.unicode.org/glossary/#high_surrogate_code_unit">high surrogate</a>
     *   followed by a <a href="https://www.unicode.org/glossary/#low_surrogate_code_unit">low surrogate</a>)
     *   “stand in” for a <a href="https://www.unicode.org/glossary/#supplementary_code_point">supplementary code point</a>.
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

    ParserMessageCode INVALID_VALUE_MUST_BE_GREATER_THAN = Factory.parserMessageCode(
        "invalid_value_must_be_greater_than",
        "Invalid value - must be greater than {0}.");
    ParserMessageCode INVALID_VALUE_MUST_BE_GREATER_THAN_OR_EQUAL_TO = Factory.parserMessageCode(
        "invalid_value_must_be_greater_than_or_equal_to",
        "Invalid value - must be greater than or equal to {0}.");
    ParserMessageCode INVALID_VALUE_MUST_BE_LESS_THAN = Factory.parserMessageCode(
        "invalid_value_must_be_less_than",
        "Invalid value - must be less than {0}.");
    ParserMessageCode INVALID_VALUE_MUST_BE_LESS_THAN_OR_EQUAL_TO = Factory.parserMessageCode(
        "invalid_value_must_be_less_than_or_equal_to",
        "Invalid value - must be less than or equal to {0}.");
    ParserMessageCode INVALID_VALUE_MUST_BE_NUMERICALLY_COMPARABLE = Factory.parserMessageCode(
        "invalid_value_must_be_numerically_comparable",
        "Invalid value - must be numerically comparable, invalid character ''{0}''.");
  }

}
