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

import static org.typefactory.MessageUtils.TYPE_FACTORY_MESSAGES_RESOURCE_BUNDLE_BASE_NAME;

import java.io.Serializable;
import java.util.Locale;
import org.typefactory.impl.Factory;

/**
 * <p>Message code instances provide both a message code and a default message.</p>
 *
 * <p>The message code should be a key to an entry in a {@link java.util.ResourceBundle} with base name {@code org.typefactory.Messages}.
 * Messages can be localized by providing language specific resource bundles. The resource bundles can be either:</p>
 *
 * <ul>
 *   <li>Java properties files.</li>
 *   <li>Java classes that implement {@link java.util.ResourceBundle}.</li>
 * </ul>
 *
 * <p>For example, the following are valid examples of localization properties files and classes for providing localized error messages:</p>
 * <pre>
 *   org/typefactory/Messages.properties
 *   org/typefactory/Messages_en.properties
 *   org/typefactory/Messages_en_US.properties
 *   class org.typefactory.Messages_fr extends java.util.ListResourceBundle
 *   class org.typefactory.Messages_fr_CA extends java.util.ListResourceBundle
 * </pre>
 */
public interface MessageCode extends Serializable {


  /**
   * Returns the message code – a key to an entry in a {@link java.util.ResourceBundle} with base name {@code org.typefactory.Messages}.
   *
   * @return the message code – a key to an entry in a {@link java.util.ResourceBundle} with base name {@code org.typefactory.Messages}.
   */
  String code();

  /**
   * Returns the default message that will be used if no entry in a resource bundle can be found.
   *
   * @return the default message that will be used if no entry in a resource bundle can be found.
   */
  String defaultMessage();

  /**
   * <p>Creates a new message code instance.</p>
   *
   * <p><b>Note</b>, null, empty or blank values will be accepted and converted to an empty string.</p>
   *
   * @param messageCode    the message code – a key to an entry in a {@link java.util.ResourceBundle} with base name {@code org.typefactory.Messages}.
   *                       <b>Note</b>, null, empty or blank values will be accepted and converted to an empty string.
   * @param defaultMessage the default message that will be used if no entry in a resource bundle can be found.
   *                       <b>Note</b>, null, empty or blank values will be accepted and converted to an empty string.
   * @return a new {@link MessageCode} instance for the message code and default message.
   */
  static MessageCode of(final String messageCode, final String defaultMessage) {
    return Factory.messageCode(messageCode, defaultMessage);
  }

  /**
   * Returns the message for the default {@link Locale} or the {@link #defaultMessage()} if no localized message can be found.
   *
   * @return the message for the default {@link Locale} or the {@link #defaultMessage()} if no localized message can be found.
   */
  default String message() {
    return MessageUtils.getMessage(
        TYPE_FACTORY_MESSAGES_RESOURCE_BUNDLE_BASE_NAME, Locale.getDefault(), this);
  }

  /**
   * Returns the message formatted using the provided {@code messageArgs} for the default {@link Locale} or the {@link #defaultMessage()} if no
   * localized message can be found.
   *
   * @param messageArgs the values to format into a message that expecting parameters.
   * @return the message formatted using the provided {@code messageArgs} for the default {@link Locale} or the {@link #defaultMessage()} if no
   * localized message can be found.
   */
  default String message(final Object[] messageArgs) {
    return MessageUtils.getMessage(
        TYPE_FACTORY_MESSAGES_RESOURCE_BUNDLE_BASE_NAME, Locale.getDefault(), this, messageArgs);
  }

  /**
   * Returns the message for the specified {@code locale} or the {@link #defaultMessage()} if no localized message can be found.
   *
   * @return the message for the specified {@code locale} or the {@link #defaultMessage()} if no localized message can be found.
   */
  default String message(final Locale locale) {
    return MessageUtils.getMessage(
        TYPE_FACTORY_MESSAGES_RESOURCE_BUNDLE_BASE_NAME, locale, this);
  }

  default String message(final Locale locale, final Object[] messageArgs) {
    return MessageUtils.getMessage(
        TYPE_FACTORY_MESSAGES_RESOURCE_BUNDLE_BASE_NAME, locale, this, messageArgs);
  }

  /**
   * <p>Returns {@code true} if the provided {@code messageCode.code()} equals {@link this#code()}, and {@code false} otherwise.</p>
   *
   * <p>If the provided {@code messageCode} argument is {@code null} then this method will return {@code false}.</p>
   *
   * @param messageCode the message code instance to test.
   * @return {@code true} if the provided {@code messageCode.code()} equals {@link this#code()}, and {@code false} otherwise.
   */
  default boolean hasSameCodeAs(final MessageCode messageCode) {
    if (messageCode == null) {
      return false;
    }
    if (code() == null) {
      return messageCode.code() == null;
    }
    return code().equals(messageCode.code());
  }

  /**
   * Returns {@code true} if both the code and the default message are blank and {@code false} otherwise.
   *
   * @return {@code true} if both the {@link #code()} and {@link #defaultMessage()} are blank and {@code false} otherwise.
   */
  default boolean isBlank() {
    return (code() == null || code().isBlank()) && (defaultMessage() == null || defaultMessage().isBlank());
  }

}
