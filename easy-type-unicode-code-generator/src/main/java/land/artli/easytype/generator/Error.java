package land.artli.easytype.generator;


public enum Error {
  CANNOT_LOAD_UNICODE_ALL_GROUPED_XML_FILE_NAME(1, "Cannot load the Unicode grouped data file '%s'."),
  THERE_ARE_NO_REPERTOIRE_ELEMENTS_IN_THE_FILE(2, "There are no repertoire elements in the file '%s'."),
  THERE_ARE_TOO_MANY_REPERTOIRE_ELEMENTS_IN_THE_FILE(3, "There are too many repertoire elements in the file '%s'."),
  THERE_ARE_NO_GROUP_ELEMENTS_IN_THE_FILE(4, "There are no group elements in the file '%s'."),

  ;
  private final int errorCode;
  private final String errorMessage;

  Error(final int errorCode, final String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public String getErrorMessage(final String dataFile) {
    return String.format(errorMessage, dataFile);
  }
}
