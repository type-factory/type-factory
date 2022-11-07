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

import org.typefactory.Subset;

/**
 * <p>Provides a way of collecting a set of code-points by capturing only the extremities of the code-point ranges in the set.
 * The code-point ranges are stored as single-byte, double-byte and triple-byte code point ranges.</p>
 *
 * <p>Package-scoped because we may replace with another implementation if this implementation is sub-optimal.</p>
 */
interface RangedSubset extends Subset {

  char[] getSingleByteCodePointRanges();

  int[] getDoubleByteCodePointRanges();

  long[] getTripleByteCodePointRanges();

}
