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
