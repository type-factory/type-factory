package org.datatypeproject.generator.unicodedata;

import java.io.File;

public class UnicodeException extends RuntimeException {

  private final int errorCode;

  public UnicodeException(final Error error, final String messageArgument) {
    super(error.getErrorMessage(messageArgument));
    this.errorCode = error.getErrorCode();
  }

  public UnicodeException(final Error error, final String messageArgument, Throwable cause) {
    super(error.getErrorMessage(messageArgument), cause);
    this.errorCode = error.getErrorCode();
  }

  public UnicodeException(final Error error, final File messageArgument) {
    super(error.getErrorMessage(messageArgument.toString()));
    this.errorCode = error.getErrorCode();
  }

  public UnicodeException(final Error error, final File messageArgument, Throwable cause) {
    super(error.getErrorMessage(messageArgument.toString()), cause);
    this.errorCode = error.getErrorCode();
  }

  public int getErrorCode() {
    return errorCode;
  }
}
