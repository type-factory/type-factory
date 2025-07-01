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
import java.util.Objects;
import org.typefactory.impl.Factory;

/**
 * <p>Message codes are keys for messages provided by locale-specific {@link java.util.ResourceBundle resource bundles}
 * with base name {@code org.typefactory.Messages}. Type factory builders and parsers use message codes when creating exceptions for parser and
 * builder errors. Every {@code MessageCode} instance provides a default error message.</p>
 *
 * <p>{@code MessageCode} instances implement {@link CharSequence} with the character sequence representing
 * the message code providing the key to the message in the resource bundles.</p>
 *
 * <p>The resource bundles can be either:</p>
 *
 * <ul>
 *   <li>Java properties files.</li>
 *   <li>Java classes that implement {@link java.util.ResourceBundle}.</li>
 * </ul>
 *
 * <p>Examples of property-files and classes for providing localized error messages are:</p>
 * <pre>
 *   org/typefactory/Messages.properties
 *   org/typefactory/Messages_en.properties
 *   org/typefactory/Messages_en_US.properties
 *   class org.typefactory.Messages_fr    extends java.util.ListResourceBundle
 *   class org.typefactory.Messages_fr_CA extends java.util.ListResourceBundle
 * </pre>
 */
public interface MessageCode extends CharSequence, Comparable<MessageCode>, Serializable {

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
   * @param messageCode    the message code – a key to an entry in a {@link java.util.ResourceBundle} with base name
   *                       {@code org.typefactory.Messages}.
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
   * <p>Returns {@code true} if the provided {@code messageCode.code()} equals {@link #code()}, and {@code false} otherwise.</p>
   *
   * <p>If the provided {@code messageCode} argument is {@code null} then this method will return {@code false}.</p>
   *
   * @param messageCode the message code instance to test.
   * @return {@code true} if the provided {@code messageCode.code()} equals {@link #code()}, and {@code false} otherwise.
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

  /**
   * <p>Returns the message-code as a string which can be used as a key to retrieve messages from a resource-bundle.</p>
   *
   * <p>It does not return the default error message associated with the message-code.</p>
   *
   * @return {@code true} if the {@link #code()} is empty or blank and {@code false} otherwise.
   */
  @Override
  String toString();

  /**
   * <p>Returns the length of this message code character sequence.  The length is the number
   * of 16-bit {@code char}s in the sequence.</p>
   *
   * @return the number of {@code char}s in this message code character sequence
   */
  @Override
  default int length() {
    return code() == null ? 0 : code().length();
  }

  /**
   * <p>Returns the {@code char} value at the specified index of the message code character sequence.
   * An index ranges from zero to {@code length() - 1}.  The first {@code char} value of the
   * message code character sequence is at index zero, the next at index one, and so on, as for array
   * indexing.</p>
   *
   * <p>If the {@code char} value specified by the index is a
   * <a href="{@docRoot}/java.base/java/lang/Character.html#unicode">surrogate</a>, the surrogate
   * value is returned.</p>
   *
   * @param   index   the index of the {@code char} value to be returned
   * @return  the specified {@code char} value
   * @throws  IndexOutOfBoundsException if the {@code index} argument is negative or not less than {@code length()}
   */  @Override
  default char charAt(int index) {
    if (isEmpty()) {
      throw new StringIndexOutOfBoundsException("String index out of range: " + index);
    }
    return code().charAt(index);
  }

  /**
   * <p>Returns a new {@code CharSequence} that is a subsequence of this message code character sequence.</p>
   *
   * <p>The subsequence starts with the {@code char} value at the specified {@code start} and ends with the
   * {@code char} value at index {@code end - 1}.  The length (in {@code char}s) of the
   * subsequence is {@code end - start}, so if {@code start == end} then an empty sequence is returned.</p>
   *
   * @param start the start index, inclusive
   * @param end the end index, exclusive
   * @return the specified subsequence
   * @throws IndexOutOfBoundsException if {@code start} or {@code end} are negative, or if {@code end}
   *         is greater than {@code length()}, or if {@code start} is greater than {@code end}
   */
  @Override
  default CharSequence subSequence(int start, int end) {
    if (isEmpty()) {
      throw new StringIndexOutOfBoundsException(
          "begin " + start + ", end " + end + ", length " + length());
    }
    return code().subSequence(start, end);
  }

  /**
   * <p>Lexicographically compares this message code character sequence with the specified message
   * code character sequence {@code o}.</p>
   *
   * <p>Returns zero if the message code character sequence {@code o} contains a character sequence
   * that is Lexicographically the same as this message code character sequence, or if both
   * message code character sequences are empty.</p>
   *
   * <p>Otherwise, returns a negative or positive integer depending on whether the character sequence
   * represented by this message code character sequence is lexicographically less than or
   * greater than the character sequence represented by {@code o}.</p>
   *
   * <p>Will return a positive integer if {@code o} is null.</p>
   *
   * @return a negative integer, zero, or a positive integer as this object
   *         is less than, equal to, or greater than the specified object.
   */
  @Override
  default int compareTo(final MessageCode o) {
    return compare(this, o);
  }

  default int compareToIgnoreCase(final MessageCode o) {
    return compareIgnoreCase(this, o);
  }

  default boolean equalsIgnoreCase(final MessageCode o) {
    return equalsIgnoreCase(this, o);
  }

  @SuppressWarnings("java:S4973") // SonarQube false positive: Strings and Boxed types should be compared using "equals()"
  static int compare(final MessageCode c1, final MessageCode c2) {
    if (c1 == c2) {
      // both are null or both are the same MessageCode instance
      return 0;
    }
    if (c1 == null) {
      // c1 is null, therefore c2 is not
      return -1;
    }
    final String code1 = c1.code();
    final String code2 = c2.code();
    if (code1 == code2) {
      // both are null or both are the same String instance
      return 0;
    }
    if (code1 == null) {
      // code1 is null, therefore code2 is not
      return -1;
    }
    return code1.compareTo(code2);
  }

  @SuppressWarnings("java:S4973") // SonarQube false positive: Strings and Boxed types should be compared using "equals()"
  static int compareIgnoreCase(final MessageCode c1, final MessageCode c2) {
    if (c1 == c2) {
      // both are null or both are the same MessageCode instance
      return 0;
    }
    if (c1 == null) {
      // c1 is null, therefore c2 is not
      return -1;
    }
    final String code1 = c1.code();
    final String code2 = c2.code();
    if (code1 == code2) {
      // both are null or both are the same String instance
      return 0;
    }
    if (code1 == null) {
      // code1 is null, therefore code2 is not
      return -1;
    }
    return code1.compareToIgnoreCase(code2);
  }

  static <T extends MessageCode> boolean equalsIgnoreCase(final T value1, final T value2) {
    return compareIgnoreCase(value1, value2) == 0;
  }
}
