package org.typefactory.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SubsetExceptionTest {

  @Test
  void exceptionContainsMeesage() {
    final String message = "some message";
    final var actual = new SubsetException(message);
    Assertions.assertThat(actual)
        .isNotNull()
        .isInstanceOf(SubsetException.class)
        .hasMessage(message);
  }

}
