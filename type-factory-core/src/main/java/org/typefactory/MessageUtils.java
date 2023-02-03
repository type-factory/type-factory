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

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;
import java.util.logging.Level;
import java.util.logging.Logger;

class MessageUtils {

  private MessageUtils() {
    // don't instantiate me
  }

  private static final Logger logger = Logger.getLogger(MessageUtils.class.getName());

  private static final Control DEFAULT_RESOURCE_BUNDLE_CONTROL = Control.getControl(Control.FORMAT_DEFAULT);

  private static final Object[] EMPTY_MESSAGE_ARGS = new Object[0];

  private static final String EMPTY_STRING = "";

  static final String TYPE_FACTORY_MESSAGES_RESOURCE_BUNDLE_BASE_NAME = "org.typefactory.Messages";

  static String getMessage(
      final String resourceBundleBaseName,
      final MessageCode messageCode) {
    return getMessage(resourceBundleBaseName, Locale.getDefault(), messageCode, EMPTY_MESSAGE_ARGS);
  }

  static String getMessage(
      final String resourceBundleBaseName,
      final Locale locale,
      final MessageCode messageCode) {
    return getMessage(resourceBundleBaseName, locale, messageCode, EMPTY_MESSAGE_ARGS);
  }

  static String getMessage(
      final String resourceBundleBaseName,
      final Locale locale,
      final MessageCode messageCode,
      final Object[] messageArgs) {

    if (messageCode == null || messageCode.isBlank()) {
      return EMPTY_STRING;
    }
    try {
      final String message = getResourceBundleMessage(resourceBundleBaseName, locale, messageCode);
      return new MessageFormat(message, locale).format(messageArgs == null ? EMPTY_MESSAGE_ARGS : messageArgs);
    } catch (final Exception e) {
      logger.fine(() ->
          String.format("Can't format message for key '%s' from resource bundle for base name %s, locale %s – %s",
              messageCode.code(), resourceBundleBaseName, locale.toLanguageTag(), e.getClass().getSimpleName()));
      if (messageCode.defaultMessage() == null || messageCode.defaultMessage().isBlank()) {
        return messageCode.code();
      } else {
        return messageCode.defaultMessage();
      }
    }
  }

  protected static String getResourceBundleMessage(
      final String resourceBundleBaseName,
      final Locale locale,
      final MessageCode messageCode) {

    final ResourceBundle resourceBundle = getResourceBundle(resourceBundleBaseName, locale, messageCode);
    String message;
    if (resourceBundle != null) {
      try {
        message = resourceBundle.getString(messageCode.code());
        if (!message.isBlank()) {
          return message;
        }
      } catch (final Exception e) {
        logger.fine(() -> String.format("Can't load message for key '%s' from resource bundle for base name %s, locale %s – %s",
            messageCode.code(), resourceBundle.getBaseBundleName(), locale.toLanguageTag(), e.getClass().getName()));
      }
    }
    message = messageCode.defaultMessage();
    return message == null || message.isBlank()
        ? messageCode.code()
        : message;
  }

  /**
   * @param resourceBundleBaseName
   * @param locale
   * @param messageCode
   * @return
   * @throws NullPointerException          if {@code baseName}, {@code locales} or {@code control} is {@code null}
   * @throws MissingResourceException      if no resource bundle for the specified base name in any of the {@code locales} can be found.
   * @throws IllegalArgumentException      if the given {@code control} doesn't perform properly (e.g., {@code control.getCandidateLocales} returns
   *                                       null.) Note that validation of {@code control} is performed as needed.
   * @throws UnsupportedOperationException if this method is called in a named module
   */
  protected static ResourceBundle getResourceBundle(
      final String resourceBundleBaseName,
      final Locale locale,
      final MessageCode messageCode) {

    try {
      return ResourceBundle.getBundle(
          resourceBundleBaseName, locale, Thread.currentThread().getContextClassLoader(), DEFAULT_RESOURCE_BUNDLE_CONTROL);
    } catch (final Exception e1) {
      try {
        return ResourceBundle.getBundle(
            resourceBundleBaseName, locale, messageCode.getClass().getClassLoader(), DEFAULT_RESOURCE_BUNDLE_CONTROL);
      } catch (final Exception e2) {
        try {
          return ResourceBundle.getBundle(
              resourceBundleBaseName, locale, MessageUtils.class.getClassLoader(), DEFAULT_RESOURCE_BUNDLE_CONTROL);
        } catch (final Exception e3) {
          logger.log(Level.FINE, e1, () ->
              String.format("""
                      Can't load resource bundle for base name %s, locale %s – caused by:
                      - %s: %s,
                      - %s: %s,
                      - %s: %s""",
                  resourceBundleBaseName, locale.toLanguageTag(),
                  e1.getClass().getName(), e1.getMessage(),
                  e2.getClass().getName(), e2.getMessage(),
                  e3.getClass().getName(), e3.getMessage()));
        }
      }
    }
    return null;
  }
}
