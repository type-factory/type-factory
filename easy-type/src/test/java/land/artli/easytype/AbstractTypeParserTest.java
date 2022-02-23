package land.artli.easytype;

public abstract class AbstractTypeParserTest {

  static class SomeType extends StringType<SomeType> {

    private SomeType(final String value) {
      super(value);
    }
  }

}
