package land.artli.easytype;

public abstract class AbstractTypeParserTest {

  static class SomeType extends StringType {

    private SomeType(final String value) {
      super(value);
    }
  }

}
