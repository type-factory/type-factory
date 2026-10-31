Creating your own custom data types
===================================

Overview
--------

In this article, we will demonstrate how easy it is to create a custom data type using an ISO 4217 currency code as an example. 

The aim is to enable us to create a custom data type that will ensure data quality while avoiding extra validation and clean-up code. It will be an immutable data type implementing `CharSequence` and supporting `equals()`, `hashCode()` and `toString()` methods and created with minimal effort.

We are firm believers in the [robustness principle](https://en.wikipedia.org/wiki/Robustness_principle), also known as Postel's Law, which can be paraphrased as "be conservative or strict in what you send and liberal or forgiving in what you accept".

First we will consider the current common practice to validate a `String` property containing a currency code. Then we will show how we can simplify our code by creating a custom data type for the currency code.

Using strings with validation constraints and clean-up code
-----------------------------------------------------------

The code below shows what we are trying avoid. It requires specifying a `@Pattern` validation constraint which, for robustness, is configured to allow lower case letters along with optional leading or trailing whitespace. 

When using `@Pattern` validation constraints it is important to craft your regular expressions such that they are correct, efficient and are not a vulnerability exposing your application to [catastrophic backtracking](https://www.regular-expressions.info/catastrophic.html).

```java
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CurrencyAmount {

  public CurrencyAmount(@NotBlank String currencyCode, @NotNull BigDecimal amount) {
    this.currencyCode = currencyCode.toUpperCase().trim();
    this.amount = amount;
  }
  
  @NotBlank
  @Pattern(regexp = "\\s*+[a-zA-Z]{3}\\s*+")
  private String currencyCode;

  @NotNull
  private BigDecimal amount;
  
  public void setCurrencyCode(@NotBlank String currencyCode) {
    this.currencyCode = currencyCode.toUpperCase().trim();
  }
}
```

Because having clean and robust data is important to us, we have to add code that ensures the currency code is always upper-cased and trimmed of leading or trailing whitespace. Doing this in both the constructor and setter ensure that any calls to `equals()`, `hashCode()` and `toString()` will always be consistent and predictable.

As you can see there is a lot to remember and maintain to ensure both data quality and a robust API. This is just one small data model class and it is likely that our application would have many such data models or service methods that would also accept currency codes. All these models and methods would require the same validation and clean-up code to ensure consistent behaviour.

Using a custom data type
------------------------

By defining custom data type we can simplify our data models to the following. Notice that we don't liberal regex patterns or clean-up code.

```java
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CurrencyAmount {

  @NotBlank
  private CurrencyCode currencyCode;

  @NotNull
  private BigDecimal amount;
}
```

In the above code we specify the `@NotBlank` constraint to signify the `currencyCode` as a mandatory property. If it were optional we would omit this constraint.

### Creating a custom data type

So how does this work and what does the `CurrencyCode` class look like? 

First let's get acquainted with the Type Factory's `TypeParser` class which lies at the heart of our custom data types. `TypeParser` instances are immutable and thread-safe – they can be shared across multiple threads. They are relatively expensive to create, so it is recommended to create them once and reuse them.

We create `TypeParser` instances using a builder to configure what characters are acceptable, the min and max size, and what transformations should be applied to the data. An example for a currency code is shown below.

```java
private static final TypeParser TYPE_PARSER = TypeParser.builder()
    .acceptCharRange('a', 'z')
    .acceptCharRange('A', 'Z')
    .fixedSize(3)
    .removeAllWhitespace()
    .toUpperCase()
    .build();
```

Now that we have defined our `TypeParser` instance, let's look at creating a `CurrencyCode` class. It will be a plain-old-Java-object (POJO) that extends the `StringType` class from the Type Factory library. Java `record` types are also supported. In the future, we will also support the, as yet unreleased, [Java "value" types](https://openjdk.org/projects/valhalla/).

A description for each numbered line follows the example code.

```java
import org.typefactory.StringType;
import org.typefactory.TypeParser;

public final class CurrencyCode extends StringType {                 // ①

  public static final MessageCode ERROR_MESSAGE = MessageCode.of(    // ②
      "invalid.currency.code", 
      "must be a 3-character ISO 4217 alpha currency code");

  private static final TypeParser TYPE_PARSER = TypeParser.builder() // ③
      .messageCode(ERROR_MESSAGE) // ②
      .acceptCharRange('a', 'z')  // ④
      .acceptCharRange('A', 'Z')
      .fixedSize(3)               // ⑤
      .removeAllWhitespace()      // ⑥
      .convertEmptyToNull()       // ⑦
      .toUpperCase()              // ⑧
      .build();

  private CurrencyCode(final String value) {  // ⑨
    super(value);
  }

  public static CurrencyCode of(final CharSequence value) {  // ⑩
    return TYPE_PARSER.parseToStringType(value, CurrencyCode::new); 
  }
}
```

① Our currency code class is declared to be “final” and it will also be immutable.
By extending `StringType` it will also implement `CharSequence`, `Comparable`
and `Serializable`, and receive default implementations of all their required
methods, as well as receive appropriate implementations of the `equals`,
`hashCode` and `toString` methods.

② We provide a message code with a default error message that will be used to
create an `InvalidValueException` when the value being parsed doesn't meet
the required criteria for a currency-code. Error messages can be localized
by provide localized resource bundles. Consider defining all your message codes
in a separate class.

③ We create a static, immutable, thread-safe, type-parser using a builder.
This type-parser will do the heavy lifting of parsing and/or cleaning a
value so that a valid `CurrencyCode` can be created.

④ We specify the characters that are acceptable for a currency-code.

⑤ We specify that the parsed value for a currency-code must be exactly 3
characters. For other types you can specify min and max sizes.

⑥ We will remove any whitespace that is present in incoming values while parsing
it. For other types, you could also choose to _normalise_ or _preserve_
whitespace characters, or _convert_ them to some other character.

⑦ We would like the type-parser to convert empty values to null. This means that 
the type parser will return `null` if provided a value that is null, empty or blank 
(contains only whitespace). If you prefer, you could also choose to configure it to
`preserveNullAndEmpty()` or `convertNullToEmpty()`.

⑧ The parser will convert any lowercase letters to uppercase.

⑨ In this example, we specify a private constructor because we'd like all
instantiation to occur via the factory method defined in step ⑩. You can,
of course, choose to use a constructor instead a factory method.

⑩ We will provide a static factory method, `of(value)`, to instantiate
a `CurrencyCode` using the value provided. Because we have configured the 
to remove all whitespace and to convert empty string to null, the `of(value)` method
will return null if the provided `value` is null, empty or blank (contains only whitespace).

Test once, use everywhere
-------------------------

Once you have written tests for your custom data type, you can be confident that it will work correctly in all your data models and any method arguments that use it. You can also be confident that it will work correctly in all your applications that use it.

If however, you use validation constraints to validate simple strings then you should really be adding  validate simpe find a bug in your custom data type, you can fix it once and be confident that it will be fixed everywhere.

An example of a unit tests for the `CurrencyCode` class is shown below.

```java
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatObject;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.typefactory.InvalidValueException;

class CurrencyCodeTest {

  @ParameterizedTest
  @NullSource
  @EmptySource
  @ValueSource(strings = {" ", "   ", "\t\t", "\n\n", "\n\r", "\t\n\r"})
  void of_shouldReturnNullForNullEmptyAndBlank(final String value) {
    final CurrencyCode actual = CurrencyCode.of(value);
    assertThat((CharSequence) actual).isNull();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE     | EXPECTED-value() | EXPECTED-toString()
      AUD       | AUD              | AUD
      ' aud '   | AUD              | AUD
      '\tAUD\t' | AUD              | AUD
      uSd       | USD              | USD
      Nzd       | NZD              | NZD
      eur       | EUR              | EUR
      """,
      delimiter = '|', useHeadersInDisplayName = true)
  void of_shouldCreateCurrencyCodeInstanceAsExpected(
      final String value, final String expectedValue, final String expectedToString) {

    final CurrencyCode actual = CurrencyCode.of(value);

    assertThat(actual.value()).isEqualTo(expectedValue);
    assertThatObject(actual).hasToString(expectedToString);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      AU   | Invalid value - too short, minimum length is 3.
      AUSD | Invalid value - too long, maximum length is 3.
      USAD | Invalid value - too long, maximum length is 3.
      61D  | Invalid value - invalid character '6' U+0036 DIGIT SIX.
      """, delimiter = '|')
  void of_shouldThrowExceptionForInvalidValues(
      final String value, final String expectedExceptionMessage) {
    
    assertThatThrownBy(() -> CurrencyCode.of(value))
        .isInstanceOf(InvalidValueException.class)
        .hasMessage(CurrencyCode.ERROR_MESSAGE.message() + ". " + expectedExceptionMessage)
        .hasFieldOrPropertyWithValue("parserErrorMessage", expectedExceptionMessage)
        .hasFieldOrPropertyWithValue("messageCode", CurrencyCode.ERROR_MESSAGE.code());
  }
}
```

A note about valid values versus null or empty values
-----------------------------------------------------

When creating your custom data types it is important to consider null or empty values separately from valid values. 

For example, if in some models our currency code property is optional then we would need to allow for it to be null or empty. 

The Type Factory `TypeParser` does not consider null or empty values to be invalid. It will only throw an `InvalidValueException` if the value is non-null/non-empty and does not meet the criteria specified in the builder.

You should add a `@NotBlank` constraint to any _mandatory_ custom data type property or argument. _Optional_ properties need no such constraint.

Conclusion
----------

In this article, we have demonstrated how easy it is to create a custom data type using an ISO 4217 currency code as an example. We have shown how to create a custom data type implementing `CharSequence` that will ensure data quality while avoiding extra validation and clean-up code, while also providing `equals()`, `hashCode()` and `toString()` methods with no extra effort.

An example of the currency code custom data type and associated unit tests are provided in GitHub:

- [CurrencyCode.java](https://github.com/type-factory/type-factory/blob/main/type-factory-examples/src/main/java/org/typefactory/stringtypes/CountryCode.java)
- [CurrencyCodeTest.java](https://github.com/type-factory/type-factory/blob/main/type-factory-examples/src/test/java/org/typefactory/stringtypes/CurrencyCodeTest.java)

