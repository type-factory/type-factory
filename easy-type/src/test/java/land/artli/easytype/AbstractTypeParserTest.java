package land.artli.easytype;

public abstract class AbstractTypeParserTest {

  static class SomeType extends CharType<SomeType> {
    private SomeType(final String value) {
      super(value);
    }
  }

}
