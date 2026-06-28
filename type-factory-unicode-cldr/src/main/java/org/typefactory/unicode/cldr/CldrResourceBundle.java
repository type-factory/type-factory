package org.typefactory.unicode.cldr;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.typefactory.Subset;

public abstract class CldrResourceBundle extends ResourceBundle {

  public static final String STANDARD_CHARACTERS = "standard.characters";
  public static final String AUXILIARY_CHARACTERS = "auxiliary.characters";
  public static final String PUNCTUATION_CHARACTERS = "punctuation.characters";

  private static final List<String> KEYS = List.of(
      STANDARD_CHARACTERS,
      AUXILIARY_CHARACTERS,
      PUNCTUATION_CHARACTERS);

  private final Subset standardSubset;
  private final Subset auxiliarySubset;
  private final Subset punctuationSubset;

  protected CldrResourceBundle(
      final Subset standardSubset,
      final Subset auxiliarySubset,
      final Subset punctuationSubset) {
    this.standardSubset = standardSubset;
    this.auxiliarySubset = auxiliarySubset;
    this.punctuationSubset = punctuationSubset;
  }

  @Override
  protected Subset handleGetObject(final String key) {
    return switch (key) {
      case STANDARD_CHARACTERS -> standardSubset;
      case AUXILIARY_CHARACTERS -> auxiliarySubset;
      case PUNCTUATION_CHARACTERS -> punctuationSubset;
      default -> throw new MissingResourceException(
          "Cannot load locale data " + getClass().getPackageName() + '_' + getClass().getSimpleName(), "", key);
    };
  }

  @Override
  public Enumeration<String> getKeys() {
    return Collections.enumeration(KEYS);
  }

  public Subset getStandardSubset() {
    return standardSubset;
  }

  public Subset getAuxiliarySubset() {
    return auxiliarySubset;
  }

  public Subset getPunctuationSubset() {
    return punctuationSubset;
  }
}
