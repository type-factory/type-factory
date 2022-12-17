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

import java.io.Serializable;
import java.util.Map;
import org.typefactory.impl.Factory;

/**
 * Error code instances provide both an error code as well as a default error message.
 */
public interface ErrorCode extends Serializable {

  /**
   * Returns the error code.
   *
   * @return the error code.
   */
  String code();

  /**
   * Returns the default error message.
   *
   * @return the default error message.
   */
  String defaultMessage();

  /**
   * Returns {@code true} if the provided {@code errorCode} argument matches the value of {@link #code()}, and {@code false} otherwise.
   *
   * @param errorCode the error code to test.
   * @return {@code true} if the provided {@code errorCode} argument matches the value of {@link #code()}, and {@code false} otherwise.
   */
  default boolean matches(final String errorCode) {
    if (code() == null && errorCode == null) {
      return true;
    }
    return code() != null && code().equals(errorCode);
  }

  /**
   * Returns {@code true} if both the error-code and default error-message are blank and {@code false} otherwise.
   *
   * @return {@code true} if both the {@link #code()} and {@link #defaultMessage()} are blank and {@code false} otherwise.
   */
  default boolean isBlank() {
    return (code() == null || code().isBlank()) && (defaultMessage() == null || defaultMessage().isBlank());
  }

  /**
   * Creates a new error code instance, registering the error-code and default error-message into map that can be retrieved via the
   * {@link #defaultErrorCodeProperties()} method.
   *
   * @param errorCode      the error-code.
   * @param defaultMessage the default error-message.
   * @return a new error code instance for the error-code and default error-message.
   */
  static ErrorCode of(final String errorCode, final String defaultMessage) {
    return Factory.errorCode(errorCode, defaultMessage);
  }

  /**
   * <p>Returns a map containing all error-codes with their associated default error-messages for {@link ErrorCode} instances that were
   * created with {@link ErrorCode#of(String, String)}.</p>
   *
   * <p>This can be useful if you wish to examine or log all known error-codes.</p>
   *
   * @return a map containing all error-codes with their associated default error-messages
   */
  static Map<String, String> defaultErrorCodeProperties() {
    return Factory.defaultErrorCodeProperties();
  }
}
