package land.artli.easytype.generator;

public class GeneratorException extends RuntimeException {

  private final int errorCode;

  public GeneratorException(final Error error, final String messageArgument) {
    super(error.getErrorMessage(messageArgument));
    this.errorCode = error.getErrorCode();
  }

  public GeneratorException(final Error error, final String messageArgument, Throwable cause) {
    super(error.getErrorMessage(messageArgument), cause);
    this.errorCode = error.getErrorCode();
  }

  public int getErrorCode() {
    return errorCode;
  }
}
