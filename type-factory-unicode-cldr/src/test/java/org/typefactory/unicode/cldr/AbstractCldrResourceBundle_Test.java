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
package org.typefactory.unicode.cldr;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import org.typefactory.Subset;
import org.typefactory.impl.Factory;

abstract class AbstractCldrResourceBundle_Test {

  static final Subset NULL_SUBSET = null;
  static final Subset EMPTY_SUBSET = Factory.emptySubset();
  static final Subset SUBSET_1 = Subset.builder().includeChar('A').build();
  static final Subset SUBSET_2 = Subset.builder().includeChars('A', '1', '@').build();

  protected static Stream<Arguments> constructorTestArguments() {
    return Stream.of(
        arguments(NULL_SUBSET, NULL_SUBSET, NULL_SUBSET, NULL_SUBSET),
        arguments(NULL_SUBSET, NULL_SUBSET, NULL_SUBSET, EMPTY_SUBSET),
        arguments(NULL_SUBSET, NULL_SUBSET, EMPTY_SUBSET, NULL_SUBSET),
        arguments(NULL_SUBSET, EMPTY_SUBSET, NULL_SUBSET, NULL_SUBSET),
        arguments(EMPTY_SUBSET, NULL_SUBSET, NULL_SUBSET, NULL_SUBSET),
        arguments(EMPTY_SUBSET, EMPTY_SUBSET, EMPTY_SUBSET, EMPTY_SUBSET),
        arguments(SUBSET_1, SUBSET_1, SUBSET_1, SUBSET_1),
        arguments(SUBSET_2, SUBSET_2, SUBSET_2, SUBSET_2));
  }
}
