package org.typefactory.generator.unicodedata;

import org.unicode.ns._2003.ucd._1.Boolean;
import org.unicode.ns._2003.ucd._1.CodePoint;
import org.unicode.ns._2003.ucd._1.Group;

public class UnicodeCodePoint {

  private Group group;
  private CodePoint codePoint;

  public UnicodeCodePoint(final Group group, final CodePoint codePoint) {
    this.group = group;
    this.codePoint = codePoint;
  }

  public UnicodeCodePoint decorate(final Group group, final CodePoint codePoint) {
    this.group = group;
    this.codePoint = codePoint;
    return this;
  }

  public boolean hasCodePoint() {
    return codePoint.getCp() != null && !codePoint.getCp().isBlank();
  }

  public Integer getCodePoint() {
    if (hasCodePoint()) {
      return Integer.parseInt(codePoint.getCp(), 16);
    }
    return null;
  }

  public boolean hasFirstCodePoint() {
    return codePoint.getFirstCp() != null && !codePoint.getFirstCp().isBlank();
  }

  public boolean hasLastCodePoint() {
    return codePoint.getLastCp() != null && !codePoint.getLastCp().isBlank();
  }
  public boolean hasCodePointRange() {
    return hasFirstCodePoint() && hasLastCodePoint();
  }

  public Integer getFirstCodePoint() {
    if (hasFirstCodePoint()) {
      return Integer.parseInt(codePoint.getFirstCp(), 16);
    }
    return null;
  }

  public Integer getLastCodePoint() {
    if (hasFirstCodePoint()) {
      return Integer.parseInt(codePoint.getLastCp(), 16);
    }
    return null;
  }

  public String getScript() {
    if (codePoint.getScript() != null) {
      return codePoint.getScript().name();
    } else if (group.getScript() != null) {
      return group.getScript().name();
    } else {
      return null;
    }
  }

  public String getBlock() {
    if (codePoint.getBlock() != null && !codePoint.getBlock().isBlank()) {
      return codePoint.getBlock();
    } else if (group.getBlock() != null && !group.getBlock().isBlank()) {
      return group.getBlock();
    } else {
      return null;
    }
  }

  public String getGeneralCategory() {
    if (codePoint.getGeneralCategory() != null && !codePoint.getGeneralCategory().isBlank()) {
      return codePoint.getGeneralCategory();
    }
    if (group.getGeneralCategory() != null && !group.getGeneralCategory().isBlank()) {
      return group.getGeneralCategory();
    }
    return null;
  }

  public boolean isWhitespace() {
    return Boolean.Y == codePoint.getWhitespace() || Boolean.Y == group.getWhitespace();
  }

  public boolean hasJSource() {
    return codePoint.getKIRGJSource() != null && !codePoint.getKIRGJSource().isBlank();
  }

  public boolean hasKSource() {
    return codePoint.getKIRGKSource() != null && !codePoint.getKIRGKSource().isBlank();
  }

  public boolean hasGSource() {
    return codePoint.getKIRGGSource() != null && !codePoint.getKIRGGSource().isBlank();
  }

  public boolean hasTSource() {
    return codePoint.getKIRGTSource() != null && !codePoint.getKIRGTSource().isBlank();
  }

}
