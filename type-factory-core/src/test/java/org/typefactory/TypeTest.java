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
package org.typefactory;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.Serial;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TypeTest {

  @Test
  void isNull_returnsTrue(){
    final Type type = new ConcreteType(null);
    assertThat(type.isNull()).isTrue();
  }

  @Test
  void isNull_returnsFalse(){
    final Type type = new ConcreteType("some-value");
    assertThat(type.isNull()).isFalse();
  }

  @Test
  void static_isNull_returnsTrue(){
    assertThat(Type.isNull(null)).isTrue();
    assertThat(Type.isNull(new ConcreteType(null))).isTrue();
  }

  @Test
  void static_isNull_returnsFalse(){
    assertThat(Type.isNull(new ConcreteType("some-value"))).isFalse();
  }

  @ParameterizedTest
  @CsvSource(value = {
      "null  | null  | true  ",
      "empty | empty | true  ",
      "' '   | ' '   | true  ",
      "a     | a     | true  ",
      "abc   | abc   | true  ",
      "null  | empty | false ",
      "null  | ' '   | false ",
      "null  | a     | false ",
      "null  | abc   | false ",
      "empty | null  | false ",
      "empty | ' '   | false ",
      "empty | a     | false ",
      "empty | abc   | false ",
      "' '   | null  | false ",
      "' '   | empty | false ",
      "' '   | a     | false ",
      "' '   | abc   | false ",
      "a     | null  | false ",
      "a     | empty | false ",
      "a     | ' '   | false ",
      "a     | abc   | false ",
      "abc   | null  | false ",
      "abc   | empty | false ",
      "abc   | ' '   | false ",
      "abc   | a     | false ",
  }, delimiter = '|', nullValues = "null", emptyValue = "empty")
  void static_equals_returnsAsExpectedForStringType(final String value1, final String value2, final boolean expected) {

    final StringType actual1 = new ConcreteStringType(value1);
    final StringType actual2 = new ConcreteStringType(value2);
    final StringType actual3 = new AnotherStringType(value2);

    // Should return as expected is same type
    assertThat(Type.equals(actual1,actual2)).isEqualTo(expected);

    // Should always return true if same instance
    assertThat(Type.equals(actual1,actual1)).isTrue();
    assertThat(Type.equals(actual2,actual2)).isTrue();

    // Should always return true if both instances are null
    assertThat(Type.equals(null,null)).isTrue();

    // Should always return false if one value is null
    assertThat(Type.equals(actual1,null)).isFalse();
    assertThat(Type.equals(null,actual2)).isFalse();

    // Should always return false if different types
    assertThat(Type.equals(actual1,actual3)).isFalse();
  }

  static class ConcreteType implements Type<String, ConcreteType> {

    final String value;

    public ConcreteType(final String value) {
      this.value = value;
    }

    @Override
    public String value() {
      return value;
    }
  }

  static class ConcreteStringType extends StringType {

    @Serial
    private static final long serialVersionUID = -3396697346851690904L;

    protected ConcreteStringType(final String value) {
      super(value);
    }
  }

  static class AnotherStringType extends StringType {

    @Serial
    private static final long serialVersionUID = -3746497687089278183L;

    public AnotherStringType(final String value) {
      super(value);
    }
  }

}
