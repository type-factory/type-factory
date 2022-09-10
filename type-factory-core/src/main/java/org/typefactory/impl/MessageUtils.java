package org.typefactory.impl;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;
import org.typefactory.InvalidValueException.ParserErrorCode;

public class MessageUtils {

  private MessageUtils() {
  }

  private static final Object[] EMPTY_MESSAGE_ARGS = new Object[0];


  public static String getMessage(final String messageKey) {
    return getMessage(Locale.getDefault(), messageKey, null);
  }

  public static String getMessage(final String messageKey, final Object[] messageArgs) {
    return getMessage(Locale.getDefault(), messageKey, messageArgs);
  }

  public static String getMessage(final ParserErrorCode parserErrorCode, final Object[] messageArgs) {
    return getMessage(Locale.getDefault(), parserErrorCode, messageArgs);
  }

  public static String getMessage(final Locale locale, final ParserErrorCode parserErrorCode, final Object[] messageArgs) {
    return getMessage(locale, parserErrorCode, messageArgs);
  }

  public static String getMessage(final Locale locale, final String messageKey, final Object[] messageArgs) {
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
