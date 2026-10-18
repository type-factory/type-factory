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

import java.util.ArrayList;

interface StringSubset {

  StringSubset EMPTY = EmptyStringSubsetImpl.INSTANCE;

  boolean isEmpty();

  boolean containsString(String string);

  boolean containsString(CharSequence charSequence);

  /**
   * <p>Looks for a string in the subset that matches a portion of the provided {@code charSequence} starting at the provided {@code startingAtIndex}
   * value. If found, returns the index of the first character after the matching portion of the provided {@code charSequence}. If not found, returns
   * the provided {@code startingAtIndex} value.</p>
   *
   * @param charSequence    the character sequence to search within for a matching string in this subset
   * @param startingAtIndex the index to start searching from.  It is a char-based index and not a codepoint-based index.
   *                        If it is not in the range {@code 0 <= startingAtIndex <= charSequence.length()}, then
   *                        a search will not be performed and the provided {@code startingAtIndex} value will be returned.
   * @return the index of the first character after the matching portion of the provided {@code charSequence} if a match is found; otherwise, returns
   * {@code startingAtIndex}.
   */
  int containsString(CharSequence charSequence, int startingAtIndex);

  Iterable<String> strings();

  int stringsSize();

  default String toPattern() {
    return SubsetUtils.toPattern(
        Constants.EMPTY_CODE_POINT_RANGE_ITERABLE,
        Constants.EMPTY_CATEGORY_ITERABLE,
        strings());
  }
}

final class EmptyStringSubsetImpl implements StringSubset {

  static final EmptyStringSubsetImpl INSTANCE = new EmptyStringSubsetImpl();

  private EmptyStringSubsetImpl() {
  }

  @Override
  public boolean isEmpty() {
    return true;
  }

  @Override
  public Iterable<String> strings() {
    return Constants.EMPTY_STRING_ITERABLE;
  }

  @Override
  public int stringsSize() {
    return 0;
  }

  @Override
  public boolean containsString(final String string) {
    return false;
  }

  @Override
  public boolean containsString(final CharSequence charSequence) {
    return false;
  }

  @Override
  public int containsString(final CharSequence charSequence, final int startingAtIndex) {
    return startingAtIndex;
  }

  @Override
  public String toString() {
    return SubsetUtils.toString(
        Constants.EMPTY_CODE_POINT_RANGE_ITERABLE,
        Constants.EMPTY_CATEGORY_ITERABLE,
        Constants.EMPTY_STRING_ITERABLE);
  }
}

class StringSubsetImpl implements StringSubset {

  private final PrimitiveHashMapOfIntKeyToObjectValue<? extends SortedSetOfString> stringsByFirstChar;

  StringSubsetImpl(final ImmutablePrimitiveHashMapOfIntKeyToObjectValue<ImmutableSortedSetOfString> stringsByFirstChar) {
    this.stringsByFirstChar = stringsByFirstChar;
  }

  @Override
  public boolean isEmpty() {
    return stringsByFirstChar.isEmpty();
  }

  @Override
  public Iterable<String> strings() {
    final var strings = new ArrayList<String>();
    final var keys = stringsByFirstChar.keySet();
    for (int i = 0; i < keys.size(); ++i) {
      final var key = keys.get(i);
      final var stringSet = stringsByFirstChar.get(key);
      for (int j = 0; j < stringSet.size(); ++j) {
        strings.add(stringSet.get(j));
      }
    }
    return strings;
  }

  @Override
  public int stringsSize() {
    return 0;
  }

  @Override
  public boolean containsString(final String string) {
    return string != null
           && !string.isEmpty()
           && stringsByFirstChar.get(string.charAt(0)).contains(string);
  }

  @Override
  public boolean containsString(final CharSequence charSequence) {
    return charSequence != null
           && !charSequence.isEmpty()
           && stringsByFirstChar.get(charSequence.charAt(0)).contains(charSequence);
  }

  @Override
  public int containsString(final CharSequence charSequence, final int startingAtIndex) {
    if (charSequence == null || charSequence.isEmpty() || startingAtIndex < 0 || startingAtIndex >= charSequence.length()) {
      return startingAtIndex;
    }
    final var subsetStrings = stringsByFirstChar.get(charSequence.charAt(startingAtIndex));
    if (subsetStrings == null) {
      return startingAtIndex;
    }
    for (int i = 0; i < subsetStrings.size(); ++i) {
      final String string = subsetStrings.get(i);
      if (string.length() <= charSequence.length() - startingAtIndex) {
        boolean match = true;
        for (int j = 0; j < string.length(); ++j) {
          if (charSequence.charAt(startingAtIndex + j) != string.charAt(j)) {
            match = false;
            break;
          }
        }
        if (match) {
          return startingAtIndex + string.length();
        }
      }
    }
    return startingAtIndex;
  }

  @Override
  public String toString() {
    return SubsetUtils.toString(
        Constants.EMPTY_CODE_POINT_RANGE_ITERABLE,
        Constants.EMPTY_CATEGORY_ITERABLE,
        strings());
  }

}
