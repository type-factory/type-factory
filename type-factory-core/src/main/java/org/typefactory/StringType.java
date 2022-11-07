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

public abstract class StringType implements CharSequenceType<StringType> {

  @Serial
  private static final long serialVersionUID = -1263119529456254274L;

  private final String value;

  protected StringType(final String value) {
    this.value = value;
  }

  @Override
  public String value() {
    return value;
  }

  /**
   * Returns the internal value as returned by {@link #value()} or empty string {@code ""} when internal value is {@code null}.
   *
   * @return the internal value as returned by {@link #value()} or empty string {@code ""} when internal value is {@code null}.
   */
  @Override
  public String toString() {
    return isNull() ? "" : value;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    final StringType s = (StringType) o;
    return value == s.value || (value != null && value.equals(s.value));
  }

  @Override
  public int hashCode() {
    return value == null ? 0 : value.hashCode();
  }
}
