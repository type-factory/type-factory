/*
 * Copyright © 2021-2026 Evan Toliopoulos (typefactory.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.typefactory.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EmptyIterable<T> implements Iterable<T> {

  private final Iterator<T> iterator;

  public EmptyIterable(final Class<T> type) {
    this.iterator = new EmptyIterator<>(type);
  }

  @Override
  public Iterator<T> iterator() {
    return iterator;

  }

  private record EmptyIterator<T>(Class<T> type) implements Iterator<T> {

    @Override
      public boolean hasNext() {
        return false;
      }

      @Override
      public T next() {
        throw new NoSuchElementException("No more elements of type: " + type.getName());
      }
    }
}
