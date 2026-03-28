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
package org.typefactory.generator.unicodedata;

public record UnicodeCodePoint(
    String cp,
    String firstCp,
    String lastCp,
    String script,
    String block,
    String generalCategory,
    boolean whitespace,
    String kIRGJSource,
    String kIRGKSource,
    String kIRGGSource,
    String kIRGTSource) {

  public boolean hasCodePoint() {
    return  cp != null && !cp.isBlank();
  }

  public Integer codePoint() {
    if (hasCodePoint()) {
      return Integer.parseInt(cp, 16);
    }
    return null;
  }

  public boolean hasFirstCodePoint() {
    return firstCp != null && !firstCp.isBlank();
  }

  public boolean hasLastCodePoint() {
    return lastCp != null && !lastCp.isBlank();
  }

  public boolean hasCodePointRange() {
    return hasFirstCodePoint() && hasLastCodePoint();
  }

  public Integer getFirstCodePoint() {
    if (hasFirstCodePoint()) {
      return Integer.parseInt(firstCp, 16);
    }
    return null;
  }

  public Integer getLastCodePoint() {
    if (hasLastCodePoint()) {
      return Integer.parseInt(lastCp, 16);
    }
    return null;
  }

  public boolean isWhitespace() {
    return whitespace;
  }

  public boolean hasJSource() {
    return kIRGJSource != null && !kIRGJSource.isBlank();
  }

  public boolean hasKSource() {
    return kIRGKSource != null && !kIRGKSource.isBlank();
  }

  public boolean hasGSource() {
    return kIRGGSource != null && !kIRGGSource.isBlank();
  }

  public boolean hasTSource() {
    return kIRGTSource != null && !kIRGTSource.isBlank();
  }
}
