package org.typefactory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

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
  void staticIsNull_returnsTrue(){
    assertThat(Type.isNull(null)).isTrue();
    assertThat(Type.isNull(new ConcreteType(null))).isTrue();
  }

  @Test
  void staticIsNull_returnsFalse(){
    assertThat(Type.isNull(new ConcreteType("some-value"))).isFalse();
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
}
