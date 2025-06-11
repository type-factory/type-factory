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

/**
 * <p>Represents the result of parsing a value. It contains the parsed value, whether it was valid or invalid, and any invalid code points
 * encountered during parsing.</p>
 *
 * <p>A valid parsed value will have met the requirements of the configured {@link TypeParser}.</p>
 *
 * <p>An invalid parsed value will have either had invalid characters removed or replaced with the Unicode Replacement Character � {@code (U+FFFD)}.
 * If this is the case, the invalid characters will be listed in the {@link #invalidCodePoints()} array. The removal or replacement will depend on
 * which of the following two methods were invoked.</p>
 *
 * <ul>
 *   <li>{@link TypeParser#parseToStringRemovingInvalidCharacters(CharSequence)}</li>
 *   <li>{@link TypeParser#parseToStringReplacingInvalidCharacters(CharSequence)}</li>
 * </ul>
 *
 * @see TypeParser#parseToStringRemovingInvalidCharacters(CharSequence)
 * @see TypeParser#parseToStringReplacingInvalidCharacters(CharSequence)
 */
public interface ParseResult {

  /**
   * <p>Returns a parsed value that may be valid or invalid. The {@link #parsedValueWasValid()} and {@link #parsedValueWasInvalid()} methods indicate
   * validity.</p>
   *
   * <p>A valid parsed value will have met the requirements of the configured {@link TypeParser}.</p>
   *
   * <p>An invalid parsed value will have either had invalid characters removed or replaced with the Unicode Replacement Character �
   * {@code (U+FFFD)}. If this is the case, the invalid characters will be listed in the {@link #invalidCodePoints()} array. The removal or
   * replacement will depend on which of the following two methods were invoked.</p>
   *
   * <ul>
   *   <li>{@link TypeParser#parseToStringRemovingInvalidCharacters(CharSequence)}</li>
   *   <li>{@link TypeParser#parseToStringReplacingInvalidCharacters(CharSequence)}</li>
   * </ul>
   *
   * @return a parsed value that may be valid or invalid. A valid value will be intact. An invalid value will have had all invalid characters either
   * removed or replaced with the Unicode Replacement Character � {@code (U+FFFD)}.
   * @see TypeParser#parseToStringRemovingInvalidCharacters(CharSequence)
   * @see TypeParser#parseToStringReplacingInvalidCharacters(CharSequence)
   */
  String parsedValue();

  /**
   * <p>Indicates whether the parsed value was valid according to the configured {@link TypeParser}.</p>
   *
   * @return {@code true} if the parsed value was valid, {@code false} otherwise.
   */
  boolean parsedValueWasValid();

  /**
   * <p>Indicates whether the parsed value was invalid according to the configured {@link TypeParser}.</p>
   *
   * @return {@code true} if the parsed value was invalid, {@code false} otherwise.
   */
  boolean parsedValueWasInvalid();

  /**
   * <p>Returns an array of invalid code points encountered during parsing.</p>
   *
   * <p>These code points would have either removed or replaced in the parsed value depending on which of the two following methods were invoked:</p>
   *
   * <ul>
   *   <li>{@link TypeParser#parseToStringRemovingInvalidCharacters(CharSequence)}</li>
   *   <li>{@link TypeParser#parseToStringReplacingInvalidCharacters(CharSequence)}</li>
   * </ul>
   *
   * <p>If the parsed value was valid, this will return an empty array.</p>
   *
   * @return an array of invalid code points, or an empty array if the parsed value was valid.
   * @see TypeParser#parseToStringRemovingInvalidCharacters(CharSequence)
   * @see TypeParser#parseToStringReplacingInvalidCharacters(CharSequence)
   */
  int[] invalidCodePoints();
}
