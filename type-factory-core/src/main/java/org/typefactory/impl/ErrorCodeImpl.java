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

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.typefactory.ErrorCode;

class ErrorCodeImpl implements ErrorCode {

  @Serial
  private static final long serialVersionUID = -8041222544374363104L;

  private static final HashMap<String, String> PROPERTIES = new HashMap<>();

  private final String errorCode;
  private final String defaultMessage;

  ErrorCodeImpl(final String errorCode, final String defaultMessage) {
    PROPERTIES.put(errorCode, defaultMessage);
    this.errorCode = errorCode;
    this.defaultMessage = defaultMessage;
  }

  @Override
  public final String errorCode() {
    return errorCode;
  }

  @Override
  public final String defaultMessage() {
    return defaultMessage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ErrorCode other)) {
      return false;
    }
    return Objects.equals(errorCode, other.errorCode()) && Objects.equals(defaultMessage, other.defaultMessage());
  }

  @Override
  public int hashCode() {
    return Objects.hash(errorCode, defaultMessage);
  }

  static Map<String, String> defaultErrorCodeProperties() {
    return Map.copyOf(PROPERTIES);
  }
}
