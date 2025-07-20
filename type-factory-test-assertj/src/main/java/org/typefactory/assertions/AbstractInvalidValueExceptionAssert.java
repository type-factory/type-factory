package org.typefactory.assertions;

import static org.typefactory.assertions.AssertionUtils.valueOf;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.assertj.core.api.AbstractThrowableAssert;
import org.typefactory.InvalidValueException;
import org.typefactory.InvalidValueException.ParserMessageCode;
import org.typefactory.MessageCode;

/**
 * Abstract base class for {@link InvalidValueException} specific assertions.
 */
@SuppressWarnings("java:S119") // Generic parameter naming
public abstract class AbstractInvalidValueExceptionAssert<
    SELF extends AbstractInvalidValueExceptionAssert<SELF, ACTUAL>,
    ACTUAL extends InvalidValueException>
    extends AbstractThrowableAssert<SELF, ACTUAL> {

  /**
   * Creates a new <code>{@link AbstractInvalidValueExceptionAssert}</code> to make assertions on actual InvalidValueException.
   *
   * @param actual the InvalidValueException we want to make assertions on.
   */
  protected AbstractInvalidValueExceptionAssert(ACTUAL actual, Class<SELF> selfType) {
    super(actual, selfType);
  }

  protected String classNameOfActual() {
    return actual == null ? "<? extends InvalidValueException>" : actual.getClass().getSimpleName();
  }

  protected static <T> boolean isEmpty(final T[] array) {
    return array == null || array.length == 0;
  }

  protected static String parserErrorPropertiesString(final Map<String, Serializable> properties) {
    return "[\n  " + properties.entrySet().stream()
        .map(entry ->
            '"' + entry.getKey() + "\": " +
            (entry.getValue() == null
                ? "null"
                : entry.getValue() instanceof Number
                    ? String.valueOf(entry.getValue())
                    : '"' + String.valueOf(entry.getValue()) + '"'))
        .collect(Collectors.joining(",\n  ")) + ']';
  }

  protected static String parserErrorArgsString(final Serializable[] args) {
    return args == null || args.length == 0 ? "[] (empty)" : parserErrorArgsString(List.of(args));
  }

  protected static String parserErrorArgsString(final Collection<Serializable> args) {
    if (args == null || args.isEmpty()) {
      return "[] (empty)";
    }
    return "[\n  " + args.stream()
        .map(arg ->
            arg == null ? "null"
                : (arg instanceof Number)
                    ? String.valueOf(arg)
                    : '"' + String.valueOf(arg) + '"')
        .collect(Collectors.joining(",\n  ")) + ']';
  }


  /**
   * Verifies that the actual InvalidValueException's targetTypeClass is equal to the given one.
   *
   * @param expected the given targetTypeClass to compare the actual InvalidValueException's targetTypeClass to.
   * @return this assertion object.
   * @throws AssertionError - if the actual InvalidValueException's targetTypeClass is not equal to the given one.
   */
  public SELF hasTargetTypeClass(Class<?> expected) {
    // check that actual InvalidValueException we want to make assertions on is not null.
    isNotNull();

    final var actualTargetTypeClass = actual.getTargetTypeClass();

    if (actualTargetTypeClass == expected) {
      // both are null, or the same instance
      return myself;
    }
    if (actualTargetTypeClass == null) {
      throw failure("%nExpected actual of type:  %s%nto have targetTypeClass:  %s%nbut targetTypeClass was:  null",
          classNameOfActual(), expected.getName());
    }
    if (expected == null) {
      throw failure("%nExpected actual of type:  %s%nto have targetTypeClass:  null%nbut targetTypeClass was:  %s",
          classNameOfActual(), actualTargetTypeClass.getName());
    }
    throw failure("%nExpected actual of type:  %s%nto have targetTypeClass:  %s%nbut targetTypeClass was:  %s",
        classNameOfActual(), expected.getName(), actualTargetTypeClass.getName());
  }

  /**
   * Verifies that the actual {@link InvalidValueException#getErrorCode()} is equal to the given {@code errorCode}.
   *
   * @param expected the given {@code errorCode} to compare to the actual {@link InvalidValueException#getErrorCode()} .
   * @return this assertion object.
   * @throws AssertionError - if the actual {@link InvalidValueException#getErrorCode()} is not equal to the given one.
   */
  public SELF hasErrorCode(final MessageCode expected) {
    // check that actual InvalidValueException we want to make assertions on is not null.
    isNotNull();

    final var actualErrorCode = actual.getErrorCode();

    if (MessageCode.notEquals(actualErrorCode, expected)) {
      throw failure("%nExpected actual of type:  %s%nto have errorCode:  %s%nbut errorCode was:  %s",
          classNameOfActual(), valueOf(expected), valueOf(actualErrorCode));
    }

    return myself;
  }

  /**
   * Verifies that the actual InvalidValueException's messageCode is equal to the given one.
   *
   * @param expected the given messageCode to compare the actual InvalidValueException's messageCode to.
   * @return this assertion object.
   * @throws AssertionError - if the actual InvalidValueException's messageCode is not equal to the given one.
   */
  public SELF hasErrorCodeAsString(final String expected) {
    // check that actual InvalidValueException we want to make assertions on is not null.
    isNotNull();

    final var actualErrorCode = actual.getErrorCodeAsString();

    if (AssertionUtils.notEquals(actualErrorCode, expected)) {
      throw failure("%nExpected actual of type:  %s%nto have errorCode:  %s%nbut errorCode was:  %s",
          classNameOfActual(), valueOf(expected), valueOf(actualErrorCode));
    }

    return myself;
  }

  /**
   * Verifies that the actual InvalidValueException's errorMessage is equal to the given one.
   *
   * @param expected the given errorMessage to compare the actual InvalidValueException's errorMessage to.
   * @return this assertion object.
   * @throws AssertionError - if the actual InvalidValueException's errorMessage is not equal to the given one.
   */
  public SELF hasErrorMessage(String expected) {
    // check that actual InvalidValueException we want to make assertions on is not null.
    isNotNull();

    final var actualErrorMessage = actual.getErrorMessage();

    if (AssertionUtils.notEquals(actualErrorMessage, expected)) {
      throw failure("%nExpected actual of type:  %s%nto have errorMessage:  %s%nbut errorMessage was:  %s",
          classNameOfActual(), valueOf(expected), valueOf(actualErrorMessage));
    }

    return myself;
  }

  /**
   * Verifies that the actual InvalidValueException's errorMessage is equal to the given one for the specified locale.
   *
   * @param expected the expected error message to compare the actual InvalidValueException's errorMessage to.
   * @return this assertion object.
   * @throws AssertionError - if the actual InvalidValueException's errorMessage is not equal to the given one.
   */
  public SELF hasErrorMessage(final Locale locale, final String expected) {
    // check that actual InvalidValueException we want to make assertions on is not null.
    isNotNull();

    final var actualErrorMessage = actual.getErrorMessage(locale);

    if (AssertionUtils.notEquals(actualErrorMessage, expected)) {
      throw failure("%nExpected actual of type:  %s%nto have errorMessage:  %s%nfor locale:  %s%nbut errorMessage was:  %s",
          classNameOfActual(), valueOf(expected), locale.toLanguageTag(), valueOf(actualErrorMessage));
    }

    return myself;
  }

  /**
   * Verifies that the actual InvalidValueException's invalidValue is equal to the given one.
   *
   * @param expected the given invalidValue to compare the actual InvalidValueException's invalidValue to.
   * @return this assertion object.
   * @throws AssertionError - if the actual InvalidValueException's invalidValue is not equal to the given one.
   */
  public SELF hasInvalidValue(String expected) {
    // check that actual InvalidValueException we want to make assertions on is not null.
    isNotNull();

    final var actualInvalidValue = actual.getInvalidValue();

    if (AssertionUtils.notEquals(actualInvalidValue, expected)) {
      throw failure("%nExpected actual of type:  %s%nto have invalidValue:  %s%nbut invalidValue was:  %s",
          classNameOfActual(), valueOf(expected), valueOf(actualInvalidValue));
    }

    return myself;
  }

  /**
   * Verifies that the actual InvalidValueException's localizedMessage is equal to the given one.
   *
   * @param expected the given localizedMessage to compare the actual InvalidValueException's localizedMessage to.
   * @return this assertion object.
   * @throws AssertionError - if the actual InvalidValueException's localizedMessage is not equal to the given one.
   */
  public SELF hasLocalizedMessage(String expected) {
    // check that actual InvalidValueException we want to make assertions on is not null.
    isNotNull();

    final var actualLocalizedMessage = actual.getLocalizedMessage();

    if (AssertionUtils.notEquals(actualLocalizedMessage, expected)) {
      throw failure("%nExpected actual of type:  %s%nto have localizedMessage:  %s%nfor locale:  %s%nbut localizedMessage was:  %s",
          classNameOfActual(), valueOf(expected), Locale.getDefault().toLanguageTag(), valueOf(actualLocalizedMessage));
    }

    return myself;
  }

  /**
   * Verifies that the actual InvalidValueException's localizedMessage is equal to the given one.
   *
   * @param expected the given localizedMessage to compare the actual InvalidValueException's localizedMessage to.
   * @return this assertion object.
   * @throws AssertionError - if the actual InvalidValueException's localizedMessage is not equal to the given one.
   */
  public SELF hasLocalizedMessage(final Locale locale, String expected) {
    // check that actual InvalidValueException we want to make assertions on is not null.
    isNotNull();

    final var actualLocalizedMessage = actual.getLocalizedMessage(locale);

    if (AssertionUtils.notEquals(actualLocalizedMessage, expected)) {
      throw failure("%nExpected actual of type:  %s%nto have localizedMessage:  %s%nfor locale:  %s%nbut localizedMessage was:  %s",
          classNameOfActual(), valueOf(expected), locale.toLanguageTag(), valueOf(actualLocalizedMessage));
    }

    return myself;
  }

  /**
   * Verifies that the actual InvalidValueException's parserErrorCode is equal to the given one.
   *
   * @param expected the given parserErrorCode to compare the actual InvalidValueException's parserErrorCode to.
   * @return this assertion object.
   * @throws AssertionError - if the actual InvalidValueException's parserErrorCode is not equal to the given one.
   */
  public SELF hasParserErrorCode(ParserMessageCode expected) {
    // check that actual InvalidValueException we want to make assertions on is not null.
    isNotNull();

    final var actualParserErrorCode = actual.getParserErrorCode();

    if (MessageCode.notEquals(actualParserErrorCode, expected)) {
      throw failure("%nExpected actual of type:  %s%nto have parserErrorCode:  %s%nbut parserErrorCode was:  %s",
          classNameOfActual(), valueOf(expected), valueOf(actualParserErrorCode));
    }

    return myself;
  }

  /**
   * Verifies that the actual InvalidValueException's parserErrorCode is equal to the given one.
   *
   * @param expected the given parserErrorCode to compare the actual InvalidValueException's parserErrorCode to.
   * @return this assertion object.
   * @throws AssertionError - if the actual InvalidValueException's parserErrorCode is not equal to the given one.
   */
  public SELF hasParserErrorCodeAsString(final String expected) {
    // check that actual InvalidValueException we want to make assertions on is not null.
    isNotNull();

    final var actualParserErrorCodeAsString = actual.getParserErrorCodeAsString();

    if (AssertionUtils.notEquals(actualParserErrorCodeAsString, expected)) {
      throw failure("%nExpected actual of type:  %s%nto have parserErrorCode:  %s%nbut parserErrorCode was:  %s",
          classNameOfActual(), valueOf(expected), valueOf(actualParserErrorCodeAsString));
    }

    return myself;
  }

  /**
   * Verifies that the actual InvalidValueException's parserErrorMessage is equal to the given one.
   *
   * @param expected the given parserErrorMessage to compare the actual InvalidValueException's parserErrorMessage to.
   * @return this assertion object.
   * @throws AssertionError - if the actual InvalidValueException's parserErrorMessage is not equal to the given one.
   */
  public SELF hasParserErrorMessage(String expected) {
    // check that actual InvalidValueException we want to make assertions on is not null.
    isNotNull();

    final var actualParserErrorMessage = actual.getParserErrorMessage();

    if (AssertionUtils.notEquals(actualParserErrorMessage, expected)) {
      throw failure("%nExpected actual of type:  %s%nto have parserErrorMessage:  %s%nbut parserErrorMessage was:  %s",
          classNameOfActual(), valueOf(expected), valueOf(actualParserErrorMessage));
    }

    return myself;
  }

  /**
   * Verifies that the actual InvalidValueException's parserErrorMessage is equal to the given one.
   *
   * @param expected the given parserErrorMessage to compare the actual InvalidValueException's parserErrorMessage to.
   * @return this assertion object.
   * @throws AssertionError - if the actual InvalidValueException's parserErrorMessage is not equal to the given one.
   */
  public SELF hasParserErrorMessage(final Locale locale, final String expected) {
    // check that actual InvalidValueException we want to make assertions on is not null.
    isNotNull();

    final var actualParserErrorMessage = actual.getParserErrorMessage(locale);

    if (AssertionUtils.notEquals(actualParserErrorMessage, expected)) {
      throw failure("%nExpected actual of type:  %s%nto have parserErrorMessage:  %s%nfor locale:  %s%nbut parserErrorMessage was:  %s",
          classNameOfActual(), valueOf(expected), locale.toLanguageTag(), valueOf(actualParserErrorMessage));
    }

    return myself;
  }

  public SELF hasParserErrorProperties() {
    // check that actual InvalidValueException we want to make assertions on is not null.
    isNotNull();

    final var actualParserErrorProperties = actual.getParserErrorProperties();

    if (actualParserErrorProperties.isEmpty()) {
      throw failure("%nExpected actual of type:  %s%nto have non-empty parserErrorProperties%nbut parserErrorProperties was:  [] (empty)",
          classNameOfActual());
    }

    return myself;

  }

  public SELF hasNoParserErrorProperties() {
    // check that actual InvalidValueException we want to make assertions on is not null.
    isNotNull();

    final var actualParserErrorProperties = actual.getParserErrorProperties();

    if (!actualParserErrorProperties.isEmpty()) {
      throw failure("%nExpected actual of type:  %s%nto have no parserErrorProperties%nbut parserErrorProperties contained:  %s",
          classNameOfActual(), parserErrorPropertiesString(actualParserErrorProperties));
    }

    return myself;

  }

  /**
   * Verifies that the actual InvalidValueException's parserErrorProperties is equal to the given one.
   *
   * @param expected the given parserErrorProperties to compare the actual InvalidValueException's parserErrorProperties to.
   * @return this assertion object.
   * @throws AssertionError - if the actual InvalidValueException's parserErrorProperties is not equal to the given one.
   */
  public SELF hasParserErrorPropertiesContainingAllEntriesOf(Map<String, Serializable> expected) {
    // check that actual InvalidValueException we want to make assertions on is not null.
    isNotNull();

    final var actualParserErrorProperties = actual.getParserErrorProperties();

    if (expected == null) {
      throw failure("%nExpected actual of type:  %s%nto have parserErrorProperties:  null%nbut parserErrorProperties was:  %s",
          classNameOfActual(), parserErrorPropertiesString(actualParserErrorProperties));

    }

    final var actualParserErrorPropertiesEntries = actualParserErrorProperties.entrySet();
    final var didNotContainExpectedProperties = expected.entrySet().stream()
        .filter(entry -> !actualParserErrorPropertiesEntries.contains(entry))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    if (!didNotContainExpectedProperties.isEmpty()) {
      throw failure("%nExpected actual of type:  %s%nwith parserErrorProperties:  %s%nto contain all entries:  %s%nbut could not find:  %s",
          classNameOfActual(),
          parserErrorPropertiesString(actualParserErrorProperties),
          parserErrorPropertiesString(expected),
          parserErrorPropertiesString(didNotContainExpectedProperties));
    }

    return myself;
  }

  /**
   * Verifies that the actual InvalidValueException's parserErrorProperties is equal to the given one.
   *
   * @param expected the given parserErrorProperties to compare the actual InvalidValueException's parserErrorProperties to.
   * @return this assertion object.
   * @throws AssertionError - if the actual InvalidValueException's parserErrorProperties is not equal to the given one.
   */
  public SELF hasParserErrorPropertiesContainingExactlyEntriesOf(Map<String, Serializable> expected) {
    // check that actual InvalidValueException we want to make assertions on is not null.
    isNotNull();

    final var actualParserErrorProperties = actual.getParserErrorProperties();

    if (expected == null) {
      throw failure("%nExpected actual of type:  %s%nto have parserErrorProperties:  null%nbut parserErrorProperties was:  %s",
          classNameOfActual(), parserErrorPropertiesString(actualParserErrorProperties));

    }

    final var actualParserErrorPropertiesEntries = actualParserErrorProperties.entrySet();
    final var didNotContainExpectedProperties = expected.entrySet().stream()
        .filter(entry -> !actualParserErrorPropertiesEntries.contains(entry))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    final var extraParserErrorProperties = actualParserErrorPropertiesEntries.stream()
        .filter(entry -> !expected.containsKey(entry.getKey()))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    if (!didNotContainExpectedProperties.isEmpty() && !extraParserErrorProperties.isEmpty()) {
      throw failure(
          "%nExpected actual of type:  %s%nwith parserErrorProperties:  %s%nto contain exactly entries:  %s%nbut could not find:  %s%nand found extra entries:  %s",
          classNameOfActual(),
          parserErrorPropertiesString(actualParserErrorProperties),
          parserErrorPropertiesString(expected),
          parserErrorPropertiesString(didNotContainExpectedProperties),
          parserErrorPropertiesString(extraParserErrorProperties));
    } else if (!didNotContainExpectedProperties.isEmpty()) {
      throw failure("%nExpected actual of type:  %s%nwith parserErrorProperties:  %s%nto contain exactly entries:  %s%nbut could not find:  %s",
          classNameOfActual(),
          parserErrorPropertiesString(actualParserErrorProperties),
          parserErrorPropertiesString(expected),
          parserErrorPropertiesString(didNotContainExpectedProperties));
    } else if (!extraParserErrorProperties.isEmpty()) {
      throw failure("%nExpected actual of type:  %s%nwith parserErrorProperties:  %s%nto contain exactly entries:  %s%nbut found extra entries:  %s",
          classNameOfActual(),
          parserErrorPropertiesString(actualParserErrorProperties),
          parserErrorPropertiesString(expected),
          parserErrorPropertiesString(extraParserErrorProperties));
    }

    return myself;
  }

  /**
   * Verifies that the actual InvalidValueException's has some parserErrorArgs.
   *
   * @return this assertion object.
   * @throws AssertionError - if the actual InvalidValueException's parserErrorArgs is empty.
   */
  public SELF hasParserErrorArgs() {
    // check that actual InvalidValueException we want to make assertions on is not null.
    isNotNull();

    final var actualParserErrorArgs = actual.getParserErrorArgs();

    if (isEmpty(actualParserErrorArgs)) {
      throw failure("%nExpected actual of type:  %s%nto have non-empty parserErrorArgs%nbut parserErrorArgs was:  [] (empty)",
          classNameOfActual());
    }

    return myself;
  }

  /**
   * Verifies that the actual InvalidValueException's parserErrorArgs contains all arg values in the expected array.
   *
   * @param expected the expected parser error args that we expect to find on the actual InvalidValueException's parserErrorArgs.
   * @return this assertion object.
   * @throws AssertionError - if the actual InvalidValueException's parserErrorArgs does not contain one or more of the expected values.
   */
  public SELF hasParserErrorArgsContainingAllOf(Serializable... expected) {
    // check that actual InvalidValueException we want to make assertions on is not null.
    isNotNull();

    final var actualParserErrorArgs = actual.getParserErrorArgs();

    if (expected == null) {
      throw failure("%nExpected actual of type:  %s%nto have parserErrorArgs:  null%nbut parserErrorArgs was:  %s",
          classNameOfActual(), parserErrorArgsString(actualParserErrorArgs));
    }

    final var actualParserErrorArgsList = List.of(actualParserErrorArgs);
    final var didNotContainExpectedArgs = Stream.of(expected)
        .filter(arg -> !actualParserErrorArgsList.contains(arg))
        .toList();

    if (!didNotContainExpectedArgs.isEmpty()) {
      throw failure("%nExpected actual of type:  %s%nwith parserErrorArgs:  %s%nto contain all entries:  %s%nbut could not find:  %s",
          classNameOfActual(),
          parserErrorArgsString(actualParserErrorArgs),
          parserErrorArgsString(expected),
          parserErrorArgsString(didNotContainExpectedArgs));
    }

    return myself;
  }

  /**
   * Verifies that the actual InvalidValueException's parserErrorArgs contains all arg values in the expected array.
   *
   * @param expected the expected parser error args that we expect to find on the actual InvalidValueException's parserErrorArgs.
   * @return this assertion object.
   * @throws AssertionError - if the actual InvalidValueException's parserErrorArgs does not contain one or more of the expected values.
   */
  public SELF hasParserErrorArgsContainingExactly(Serializable... expected) {
    // check that actual InvalidValueException we want to make assertions on is not null.
    isNotNull();

    final var actualParserErrorArgs = actual.getParserErrorArgs();

    if (expected == null) {
      throw failure("%nExpected actual of type:  %s%nto have parserErrorArgs:  null%nbut parserErrorArgs was:  %s",
          classNameOfActual(), parserErrorArgsString(actualParserErrorArgs));
    }

    final var actualParserErrorArgsList = List.of(actualParserErrorArgs);
    final var expectedParserArgsList = List.of(expected);
    final var didNotContainExpectedArgs = Stream.of(expected)
        .filter(arg -> !actualParserErrorArgsList.contains(arg))
        .toList();
    final var extraParserErrorArgs = actualParserErrorArgsList.stream()
        .filter(arg -> !expectedParserArgsList.contains(arg))
        .toList();

    if (!didNotContainExpectedArgs.isEmpty() && !extraParserErrorArgs.isEmpty()) {
      throw failure(
          "%nExpected actual of type:  %s%nwith parserErrorArgs:  %s%nto contain exactly:  %s%nbut could not find:  %s%nand found extra values:  %s",
          classNameOfActual(),
          parserErrorArgsString(actualParserErrorArgs),
          parserErrorArgsString(expected),
          parserErrorArgsString(didNotContainExpectedArgs),
          parserErrorArgsString(extraParserErrorArgs));
    } else if (!didNotContainExpectedArgs.isEmpty()) {
      throw failure("%nExpected actual of type:  %s%nwith parserErrorArgs:  %s%nto contain exactly:  %s%nbut could not find:  %s",
          classNameOfActual(),
          parserErrorArgsString(actualParserErrorArgs),
          parserErrorArgsString(expected),
          parserErrorArgsString(didNotContainExpectedArgs));
    } else if (!extraParserErrorArgs.isEmpty()) {
      throw failure(
          "%nExpected actual of type:  %s%nwith parserErrorArgs:  %s%nto contain exactly:  %s%nbut found extra values:  %s",
          classNameOfActual(),
          parserErrorArgsString(actualParserErrorArgs),
          parserErrorArgsString(expected),
          parserErrorArgsString(extraParserErrorArgs));
    }

    return myself;
  }



}
