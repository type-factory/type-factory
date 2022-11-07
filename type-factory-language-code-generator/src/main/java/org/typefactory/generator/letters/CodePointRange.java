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
package org.typefactory.generator.letters;

class CodePointRange {

  final int fromCodePoint;
  final int toCodePoint;

  CodePointRange(int fromCodePoint, int toCodePoint) {
    this.fromCodePoint = fromCodePoint;
    this.toCodePoint = toCodePoint;
  }

  static CodePointRange range(char fromChar, char toChar) {
    return new CodePointRange(fromChar, toChar);
  }

  static CodePointRange range(int fromCodePoint, int toCodePoint) {
    return new CodePointRange(fromCodePoint, toCodePoint);
  }

}
