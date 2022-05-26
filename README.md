## The Data Type Project

The aim of this Java library is to help you as a software developer say what you mean and mean what you say. Along the way, we like to help you remove a lot of cruft and boilerplate from your code.

The data type project aims to make constructing custom reusable data-types trivial.

### An example
Imagine that you need a custom data-type for currency codes and that you'd like it to extend the Java `CharSequence` interface so that it can continue to be used with many third party libraries.

```java
public final class CurrencyCode extends StringType {     ①

  public static final CurrencyCode EMPTY_CURRENCY_CODE = new CurrencyCode("");  ②

  private static final TypeParser TYPE_PARSER =   ③
      TypeParser.builder(CurrencyCode.class)
          .errorMessage("must be a 3-character ISO 4217 currency code")    ④
          .acceptCharRange('a', 'z')    ⑤
          .acceptCharRange('A', 'Z')
          .fixedSizeNumberOfCodePoints(3)  ⑥
          .removeAllWhitespace()     ⑦
          .preserveNullAndEmpty()    ⑧
          .toUpperCase()    ⑨
          .build();

  private CurrencyCode(final String value) { ⑩
    super(value);
  }

  public static CurrencyCode of(final CharSequence value) {    ⑪
    return TYPE_PARSER.parseToStringType(value, CurrencyCode::new); 
  }
}
```
① We define our currency code class to be “final”, and it will also be immutable. By extending `StringType` it will also implement `CharSequence`, `Comparable` and `Serializable`, and receive default implementations of all their required methods, as well as receive appropriate implementations of the `equals`, `hashCode` and `toString` methods.

② We think it is useful to have an “empty” constant which can be re-used throughout your code when needed. Of course, this line is optional.

③ We create a static, immutable, threadsafe, type-parser using a builder. This type-parser will do the heavy lifting of parsing and/or cleaning a value so that a valid `CurrencyCode` can be created.

④ We provide a message that will be used to create an `InvalidTypeValueException` when the value being parsed doesn't meet the required criteria for a currency-code.

⑤ We specify the characters that are acceptable for a currency-code.

⑥ We specify that the parsed value for a currency-code must be exactly 3 characters. For other types you can specify min and max sizes.

⑦ We will tolerate and remove any whitespace that is present in the value while parsing it. For other types, you could also choose to forbid, normalise, preserve, or convert whitespace characters.

⑧ We would like the type-parser to preserve whether incoming values were null versus empty. If you prefer, you could also choose to convert null-to-empty or empty-to-null.

⑨ The parser will convert successfully parsed values to uppercase.

⑩ In this example, we specify a private constructor because we'd like all instantiation to occur via the factory method defined in step ⑪. You can, of course, choose to use a constructor instead a factory method.

⑪ We will provide a static factory method, `of(value)`, to instantiate a `CurrencyCode` using the value provided. Note that because we chose to preserve null and empty (see ⑧), the factory method will return `null` instead of an instantiated object for incoming null values. If we had chosen to configure the parser to convert null-to-empty, then the factory method would always give you an instantiated currency-code whose value may be an empty string.

