package org.typefactory.assertions;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;
import org.assertj.core.api.AbstractCharSequenceAssert;
import org.typefactory.MessageCode;

/**
 * Abstract base class for {@link MessageCode} specific assertions.
 */
@SuppressWarnings("java:S119") // Generic parameter naming
public abstract class AbstractMessageCodeAssert<
    SELF extends AbstractMessageCodeAssert<SELF>>
    extends AbstractCharSequenceAssert<SELF, MessageCode> {

  /**
   * Creates a new <code>{@link AbstractMessageCodeAssert}</code> to make assertions on actual MessageCode.
   *
   * @param actual the MessageCode we want to make assertions on.
   */
  protected AbstractMessageCodeAssert(MessageCode actual, Class<SELF> selfType) {
    super(actual, selfType);
  }

  public SELF hasCode(final CharSequence expected) {
    // check that actual MessageCode we want to make assertions on is not null.
    isNotNull();

    final String actualCode = actual.code();
    if (AssertionUtils.equals(actualCode, expected)) {
       return myself;
    }
    throw failure("%nExpected actual of type:  MessageCode%nto have code:  %s%nbut code was:  %s",
        AssertionUtils.valueOf(expected), AssertionUtils.valueOf(actualCode));
  }

  public SELF hasDefaultMessage(final CharSequence expected) {
    // check that actual MessageCode we want to make assertions on is not null.
    isNotNull();

    final String actualDefaultMessage = actual.defaultMessage();
    if (AssertionUtils.equals(actualDefaultMessage, expected)) {
      return myself;
    }
    throw failure("%nExpected actual of type:  MessageCode%nto have defaultMessage:  %s%nbut defaultMessage was:  %s",
        AssertionUtils.valueOf(expected), AssertionUtils.valueOf(actual.defaultMessage()));
  }

  public SELF hasMessage(final CharSequence expected) {
    // check that actual MessageCode we want to make assertions on is not null.
    isNotNull();

    final String actualMessage = actual.message();
    if (AssertionUtils.equals(actualMessage, expected)) {
      return myself;
    }
    throw failure("%nExpected actual of type:  MessageCode%nto have message:  %s%nbut message was:  %s",
        AssertionUtils.valueOf(expected), AssertionUtils.valueOf(actualMessage));
  }

  public SELF hasMessageForLocale(CharSequence expected, final Locale locale) {
    // check that actual MessageCode we want to make assertions on is not null.
    isNotNull();

    final String actualMessage = actual.message(locale);
    if (AssertionUtils.equals(actualMessage, expected)) {
      return myself;
    }
    throw failure("%nExpected actual of type:  MessageCode%nto have message:  %s%nfor locale:  %s%nbut message was:  %s",
        AssertionUtils.valueOf(expected), locale.toLanguageTag(), AssertionUtils.valueOf(actualMessage));
  }

  public SELF hasMessageForArgs(CharSequence expected, final Object... args) {
    // check that actual MessageCode we want to make assertions on is not null.
    isNotNull();

    final String actualMessage = actual.message(args);
    final String argsString = '[' + (args == null ? "" : Arrays.stream(args)
        .map(Object::toString)
        .collect(Collectors.joining(", "))) + ']';

    if (AssertionUtils.equals(actualMessage, expected)) {
      return myself;
    }
    throw failure("%nExpected actual of type:  MessageCode%nto have message:  %s%nfor message-args:  %s%nbut message was:  %s",
        AssertionUtils.valueOf(expected), argsString, AssertionUtils.valueOf(actualMessage));
  }

  public SELF hasMessageForLocaleAndArgs(CharSequence expected, final Locale locale, final Object... args) {
    // check that actual MessageCode we want to make assertions on is not null.
    isNotNull();

    final String actualMessage = actual.message(locale, args);
    final String argsString = '[' + (args == null ? "" : Arrays.stream(args)
        .map(Object::toString)
        .collect(Collectors.joining(", "))) + ']';

    if (AssertionUtils.equals(actualMessage, expected)) {
      return myself;
    }
    throw failure("%nExpected actual of type:  MessageCode%nto have message:  %s%nfor locale:  %s%nand for message-args:  %s%nbut message was:  %s",
        AssertionUtils.valueOf(expected), locale.toLanguageTag(), argsString, AssertionUtils.valueOf(actualMessage));
  }
}
