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

interface Converter {

  /**
   * Indicates whether there are any configured conversions.
   *
   * @return {@code true} if there are no conversions configured, {@code false} otherwise.
   */
  boolean isEmpty();

  /**
   * The maximum number of code-points that a single code-point could be converted to.
   */
  int getMaxConvertedLength();

  /**
   * Creates a reusable {@link ConverterResults} instance that should be used with the
   * {@link #codePointConversionIsRequired(int, int, ConverterResults)} method.
   *
   * @return a reusable {@link ConverterResults} instance.
   */
  ConverterResults createConverterResults();

  boolean codePointConversionIsRequired(final int currentCodePoint, final int currentIndex, final ConverterResults converterResults);

  default boolean codePointConversionIsNotRequired(final int currentCodePoint, final int currentIndex, final ConverterResults converterResults) {
    return !codePointConversionIsRequired(currentCodePoint, currentIndex, converterResults);
  }

  static ConverterBuilder builder() {
    return new ConverterBuilder();
  }

  interface ConverterResults {

    int getConvertFromIndex();

    int[] getConvertToCodePointSequence();
  }

}
