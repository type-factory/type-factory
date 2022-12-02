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
package org.typefactory.impl;

import static org.typefactory.impl.Constants.EMPTY_STRING;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

public final class MessageUtils {

  private MessageUtils() {
  }

  private static final Object[] EMPTY_MESSAGE_ARGS = new Object[0];


  public static String getMessage(final String messageKey) {
    return getMessage(Locale.getDefault(), messageKey, null);
  }

  public static String getMessage(final String messageKey, final Object[] messageArgs) {
    return getMessage(Locale.getDefault(), messageKey, messageArgs);
  }

  public static String getMessage(final Locale locale, final String messageKey, final Object[] messageArgs) {
    if (messageKey == null) {
      return EMPTY_STRING;
    }
    final ResourceBundle resourceBundle = ResourceBundle
        .getBundle("org.typefactory.Messages",
            locale,
            Thread.currentThread().getContextClassLoader(),
            Control.getControl(Control.FORMAT_DEFAULT));
    try {
      final Object[] args = messageArgs == null ? EMPTY_MESSAGE_ARGS : messageArgs;
      return resourceBundle.containsKey(messageKey)
          ? new MessageFormat(resourceBundle.getString(messageKey), locale).format(args)
          : messageKey;
    } catch (final IllegalArgumentException e) {
      // swallow exception.
      return messageKey;
    }
  }

  public static String combineMessagesIntoSentences(
      final String message1,
      final String message2) {

    if (message1 == null || message1.isBlank()) {
      return message2;
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

}
