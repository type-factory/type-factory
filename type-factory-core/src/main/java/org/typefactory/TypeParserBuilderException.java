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
import org.typefactory.TypeParser.TypeParserBuilder;
import org.typefactory.impl.Factory;

/**
 * <p>Thrown by the {@link TypeParserBuilder} when it is unable to configure or create a builder.</p>
 *
 * @author Evan Toliopoulos
 */
public class TypeParserBuilderException extends RuntimeException  {

  @Serial
  private static final long serialVersionUID = -7198769839039479407L;

  private static final String EMPTY_STRING = "";

  /**
   * Immutable empty array.
   */
  private static final Serializable[] EMPTY_MESSAGE_ARGS_ARRAY = new Serializable[0];

  /**
   * Immutable empty map.
   */
  private static final Map<String, Serializable> EMPTY_MESSAGE_ARGS_MAP = Map.of();

  private static final MessageCode EMPTY_MESSAGE_CODE = Factory.messageCode(EMPTY_STRING, EMPTY_STRING);

  private final MessageCode messageCode;

  private final Map<String, Serializable> messageCodeArgs;

  private final Serializable[] messageCodeArgsValues;

  /**
   * Provides a builder to create {@link TypeParserBuilderException} instances.
   *
   * @return a builder to create {@link TypeParserBuilderException} instances.
   */
  public static TypeParserBuilderExceptionBuilder builder() {
    return new TypeParserBuilderExceptionBuilder();
  }


  /**
   * Protected constructor – use the {@link #builder()} to create an {@code TypeParserBuilderException}.
   *
   * @param cause           the optional cause of the exception. Pass {@code null} if there is no cause.
   * @param messageCode     the key used to find the error message in the {@code org.typefactory.Messages} resource-bundle or properties file. If no
   *                        error message is found in the resource-bundle or properties file then this value will be used as the error message.
   * @param messageCodeArgs the message arguments to supply to the message formatter used to create the error message.
   * @see #builder()
   */
  protected TypeParserBuilderException(
      final Throwable cause,
      final MessageCode messageCode,
      final LinkedHashMap<String, Serializable> messageCodeArgs) {
    super(messageCode == null ? null : messageCode.message(), cause);
    this.messageCode = messageCode == null ? EMPTY_MESSAGE_CODE : messageCode;
    this.messageCodeArgs = messageCodeArgs == null
        ? EMPTY_MESSAGE_ARGS_MAP
        : messageCodeArgs;
    this.messageCodeArgsValues = messageCodeArgs == null
        ? EMPTY_MESSAGE_ARGS_ARRAY
        : messageCodeArgs.values().toArray(EMPTY_MESSAGE_ARGS_ARRAY);
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
    return messageCode.message(locale);
  }


  public String getMessageCode() {
    return messageCode.code();
  }

  public String getErrorMessage() {
    return messageCode.message(messageCodeArgsValues);
  }

  public String getErrorMessage(final Locale locale) {
    return messageCode.message(locale, messageCodeArgsValues);
  }

  public Map<String, Serializable> getErrorProperties() {
    return messageCodeArgs;
  }

  /**
   * A builder to create {@link TypeParserBuilderException} instances.
   */
  public static class TypeParserBuilderExceptionBuilder {

    private Throwable cause;
    private MessageCode messageCode;

    private final LinkedHashMap<String, Serializable> messageCodeArgs = new LinkedHashMap<>();

    /**
     * Creates and returns an {@link TypeParserBuilderException} instance.
     *
     * @return an {@link TypeParserBuilderException} instance.
     */
    public TypeParserBuilderException build() {
      return new TypeParserBuilderException(cause, messageCode, messageCodeArgs);
    }

    /**
     * Set the cause of the exception you are creating.
     *
     * @param cause the cause of the exception.
     * @return this builder
     */
    public TypeParserBuilderExceptionBuilder cause(final Throwable cause) {
      this.cause = cause;
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
    public TypeParserBuilderExceptionBuilder messageCode(final MessageCode messageCode) {
      this.messageCode = messageCode;
      return this;
    }

    /**
     * <p>Add message arguments to supply to the message formatter used to create the error message.</p>
     *
     * <p>Note: this method will silently ignore attempts to add parser message arguments that are provided with a null or blank {@code key}.</p>
     *
     * @param key   the message argument name. Note that this is ignored for purposes of message creation by the message formatter which uses argument
     *              index only. It is captured solely to enable any exception handlers meaningful access to argument name-value pairs. This method
     *              will silently ignore attempts to add message arguments that are provided with a null or blank {@code key}.
     * @param value the message argument value.
     * @param <V>   message argument values must be serializable because the {@link TypeParserBuilderException} class is serializable.
     * @return this builder
     */
    public <V extends Serializable> TypeParserBuilderExceptionBuilder addMessageCodeArg(final String key, final V value) {
      if (key != null && !key.isBlank()) {
        this.messageCodeArgs.put(key, value);
      }
      return this;
    }
  }

  /**
   * <p>Provides a string representation of the {@link TypeParserBuilderException}.</p>
   *
   * @return a string representation of the {@link TypeParserBuilderException}.
   */
  @Override
  public String toString() {
    final StringBuilder s = new StringBuilder();
    s.append(this.getClass().getSimpleName())
        .append("{")
        .append("message='").append(getMessage()).append('\'')
        .append(", messageCode='").append(messageCode.code()).append('\'')
        .append(", defaultErrorMessage='").append(messageCode.defaultMessage()).append('\'');
    for (Map.Entry<String, Serializable> entry : messageCodeArgs.entrySet()) {
      s.append(", ").append(entry.getKey()).append("='").append(entry.getValue()).append('\'');
    }
    if (getCause() != null) {
      s.append(", cause='").append(getCause().getClass().getSimpleName()).append(" - ").append(getCause().getMessage()).append('\'');
    }
    s.append("}");
    return s.toString();
  }

  /**
   * <p>Message codes are used internally by the {@link TypeParserBuilder}.</p>
   */
  public static final class MessageCodes {

    private MessageCodes() {
      // don't instantiate me
    }

    public static final MessageCode NO_RADIX_CONFIGURED_EXCEPTION_MESSAGE = Factory.messageCode(
        "no_radix_configured",
        "No numeric-base has been configured for the numeric type parser.");

    public static final MessageCode DUPLICATE_CUSTOM_RADIX_CHARACTER_EXCEPTION_MESSAGE = Factory.messageCode(
        "duplicate_custom_radix_character",
        "Custom numeric-base character-set must not contain duplicates – check both your custom base character-set and case-sensitivity.");
  }

}
