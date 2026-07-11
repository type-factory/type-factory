package org.typefactory.unicode.cldr;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.ResourceBundle;
import org.typefactory.Subset;
import org.typefactory.impl.Factory;

public abstract class AbstractCldrResourceBundle extends ResourceBundle {

  public static final String STANDARD_CHARACTERS = "standard.characters";
  public static final String AUXILIARY_CHARACTERS = "auxiliary.characters";
  public static final String PUNCTUATION_CHARACTERS = "punctuation.characters";
  public static final String DECIMAL_DIGITS = "decimal.digits";

  private static final List<String> KEYS = List.of(
      STANDARD_CHARACTERS,
      AUXILIARY_CHARACTERS,
      PUNCTUATION_CHARACTERS,
      DECIMAL_DIGITS);

  private final Subset standardSubset;
  private final Subset auxiliarySubset;
  private final Subset punctuationSubset;
  private final Subset decimalDigitsSubset;

  protected AbstractCldrResourceBundle(
      final Subset standardSubset,
      final Subset auxiliarySubset,
      final Subset punctuationSubset,
      final Subset decimalDigitsSubset) {
    this.standardSubset = Objects.requireNonNullElse(standardSubset, Factory.emptySubset());
    this.auxiliarySubset = Objects.requireNonNullElse(auxiliarySubset, Factory.emptySubset());
    this.punctuationSubset = Objects.requireNonNullElse(punctuationSubset, Factory.emptySubset());
    this.decimalDigitsSubset = Objects.requireNonNullElse(decimalDigitsSubset, Factory.emptySubset());
  }

  @Override
  protected Subset handleGetObject(final String key) {
    return switch (key) {
      case STANDARD_CHARACTERS -> standardSubset;
      case AUXILIARY_CHARACTERS -> auxiliarySubset;
      case PUNCTUATION_CHARACTERS -> punctuationSubset;
      case DECIMAL_DIGITS -> decimalDigitsSubset;
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

  public Subset getDecimalDigitsSubset() {
    return decimalDigitsSubset;
  }
}
