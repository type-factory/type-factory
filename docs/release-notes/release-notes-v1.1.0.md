# Release notes &ndash; version v1.1.0

## Using this release version with Maven / Gradle

<details name="import-coordinates" open>
<summary>Maven</summary>

```xml
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>org.typefactory</groupId>
      <artifactId>type-factory-bom</artifactId>
      <version>1.1.0</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>

<dependencies>
  <dependency>  <!-- Required: the core Type Factory module. -->
    <groupId>org.typefactory</groupId>
    <artifactId>type-factory-core</artifactId>
  </dependency>
  <dependency>  <!-- Optional: locale Unicode CLDR module. -->
    <groupId>org.typefactory</groupId>
    <artifactId>type-factory-unicode-cldr</artifactId>
  </dependency>
</dependencies>
```
</details>

<details name="import-coordinates">
<summary>Gradle</summary>

```groovy
dependencies {
  implementation platform("org.typefactory:type-factory-bom:1.1.0")

  // Required: the core Type Factory module. 
  implementation "org.typefactory:type-factory-core"

  // Optional: locale Unicode CLDR module.
  implementation "org.typefactory:type-factory-unicode-cldr"
}
```

</details>

## Unicode categories

The Category enumeration now has methods to help discern if a code point is in a set of Unicode categories based on their bitflags.

## InvalidValueException error messages

Exception messages will now always include invalid Unicode code point identifier, for example ‘U+0430’, alongside the invalid character. This is to help make obvious homoglyphs/homographs – letters from other scripts/alphabets that look like letters in the target script/alphabet. 
 
For example, spelling amazon.com or apple.com with the Cyrillic `а` (U+0430) instead of the Latin `a` (U+0061) will result in an `InvalidValueException` message that will include the invalid character and its code point identifier:

- previously returned: `...invalid character 'а'`
- now returns: `...invalid character а U+0430`
- will also return the code point name if configured: `...invalid character а U+0430 CYRILLIC SMALL LETTER A`

## TypeFactoryConfig to configure global type parser operation

A `TypeFactoryConfig` has been added which currently allows global configuration options. The configuration can be set by setting:

- Environment variables
- System properties
- Programatically calling static methods on the `TypeFactoryConfig` class.

### Configuration options

#### Code point names in exception messages

Determines whether Unicode code points names are included in `InvalidValueException` messages. For example, whether you wish the message to contain either:

- `...invalid character Ε U+0395`
- `...invalid character Ε U+0395 GREEK CAPITAL LETTER EPSILON`

Configuration:

- **System property:** `org.typefactory.codePointNamesInExceptionMessages`
- **Environment variable:** `ORG_TYPE_FACTORY_CODE_POINT_NAMES_IN_EXCEPTION_MESSAGES`
- **Options:** `true`/`false` (default: `false`)

#### Code point names cache size

The size of the cache to create when setting the configuration option `CODE_POINT_NAMES_IN_EXCEPTION_MESSAGES=true`.

Retrieving code points is via a lookup in the JVM and can be costly if you expect a lot of `InvalidValueException` messages to be thrown requiring code points names to be included.

The cache is useful if you expect most invalid characters to be roughly of a smallish set of the same characters over and over. For example, clients passing punctuation or special characters when only alphanumerics are allowed.

Configuration:

- **System property:** `org.typefactory.codePointNamesCacheSize`
- **Environment variable:** `ORG_TYPE_FACTORY_CODE_POINT_NAME_CACHE_SIZE`
- **Options:** 1..MAX_INTEGER (default: `500`)


## MessageCode now extends CharSequence and Comparable

The `MessageCode` interface now extends `CharSequence` and `Comparable<MessageCode>` to make it obvious that, first and foremost, it is a message-code class that has a default message along for the ride.

Two message code instances are considered equal if their code is the same:

- Deprecated the `MessageCode.hasSameCodeAs(MessageCode other)` method. 
- Use the `MessageCode.equals(Object)` instead. 

## Added a Types utility class

Added a `Types` class with utility methods to help compare custom type values `T` where `T extends CharSequenceType<T>`:

- `boolean isBlank(T)` – returns true if the value is null, empty or contains only whitespace characters.
- `boolean isEmpty(T)` – returns true if the value is null or empty.
- `boolean isNull(T)` – returns true if the value is null.
- `T defaultIfEmpty(T value, T defaultValue)` – returns the value if it is not null or empty, otherwise returns the default value.
- `CS.compare(T value1, T value2)` – case-sensitive comparison of two values, returning a negative integer, zero, or a positive integer as the first value is less than, equal to, or greater than the second value.
- `CS.equals(T value1, T value2)` – case-sensitive equality comparison of two values, returning true if they are equal, false otherwise.
- `CS.equalsAny(T value1, T ... values)` – case-sensitive equality comparison of a value with any of the provided values, returning true if any are equal, false otherwise.
- `CI.compare(T value1, T value2)` – case-insensitive comparison of two values, returning a negative integer, zero, or a positive integer as the first value is less than, equal to, or greater than the second value.
- `CI.equals(T value1, T value2)` – case-insensitive equality comparison of two values, returning true if they are equal, false otherwise.
- `CI.equalsAny(T value1, T ... values)` – case-insensitive equality comparison of a value with any of the provided values, returning true if any are equal, false otherwise.

## Provide a fluent string formatter class

Added a `StringFormatter` class that wraps the Java `StringBuilder` to provide fluent string formatting methods.

## Subset changes

The `Subset` interface now provides a method to return the subset code point ranges as a pattern string.

Moved the `SubsetWithCategories` interface into the `org.typefactory.impl` package as it provides no value in the core `org.typefactory` package.

## Added ability to parse invalid sequences

The `TypeParser` can now parse invalid character sequences with the choice of _replacing_ or _removing_ invalid characters:

-  `ParseResult parseToStringReplacingInvalidCharacters(CharSequence)`
-  `ParseResult parseToStringRemovingInvalidCharacters(CharSequence)`

### Example – replacing or removing invalid characters

For a type-parser configured to accept only Unicode letters and decimal digits (alphanumeric) then, for the
  following input values, the parsed value will be as shown below in the _‘Parsed value with replacement’_ and
  _‘Parsed value with removal’_ columns:

| Input value                         | Parsed value with replacement | Parsed value with removal | 
|-------------------------------------|-------------------------------|---------------------------|
| abc123                              | abc123                        | abc123                    | 
| abc 123                             | abc�123                       | abc123                    | 
| abc-123                             | abc�123                       | abc123                    | 
| abc_123                             | abc�123                       | abc123                    | 
| abc123!                             | abc123�                       | abc123                    | 
| abc123!@#                           | abc123���                     | abc123                    | 
| &lt;body onload="alert('Boo!')"&gt; | �body�onload��alert��Boo����� | bodyonloadalertBoo        | 

## Add the ability to convert decimal digits

Added the ability to configure the type-parser to convert any Unicode decimal digit in the input sequence to a different set of decimal digits starting with the specified target zero-digit:

- `convertAnyDecimalDigitsToDigitsStartingWithZeroDigit(char zeroDigitChar)`
- `convertAnyDecimalDigitsToDigitsStartingWithZeroDigit(int zeroDigitCodePoint)`

### Example – converting decimal digits

| Target zero digit | Input value | Parsed value | Notes                                                                         | 
|-------------------|-------------|--------------|-------------------------------------------------------------------------------|
| 0                 | 0123456789  | 0123456789   | Western Arabic digits remain as they are                                      |
| ०                 | ४५ Pies     | 45 Pies      | Only the decimal digits converted from Devanagari to Western Arabic digits    |
| 0                 | ０１２３４５６７８９  | 0123456789   | full-width digits converted to ordinary Western Arabic digits                 | 
| ०                 | 0123456789  | ०१२३४५६७८९   | Western Arabic digits converted to  to Devanagari digits                      | 
| ൦                 | 0123456789  | ൦൧൨൩൪൫൬൭൮൯   | Western Arabic digits parsed to Malayalam digits                              | 
| 0                 | ൦൧൨൩൪൫൬൭൮൯  | 0123456789   | Malayalam digits parsed to Western Arabic digits                              | 

## Type parser builder exceptions

Added the `TypeParserBuilderException` class. These may be thrown while creating a type parser when:

- the `TypeParserBuilder.convertAnyDecimalDigitsToDigitsStartingWithZeroDigit(zeroDigit)` is passed a character or code point that is not the zero-character of a consecutive set of ten decimal digits.


## Added the Type Factory Assertions library

Added a Type Factory Assertions library that extends the AssertJ assertions library. Use one of the following assertions classes:

- `TypeFactoryAssertions` – for assertions on type factory objects
- `TypeFactorySoftAssertions` – for soft assertions on type factory objects
- `TypeFactoryBDDAssertions` – for BDD style assertions on type factory objects

Include the following dependency in your Maven pom file:

```xml
<dependency>   
  <groupId>org.typefactory</groupId>  
  <artifactId>type-factory-test-assertj</artifactId>  
  <version>...</version>
  <scope>test</scope>  
</dependency>    
```
  
## Deprecating the type-factory-language module

The `type-factory-language` module is being deprecated and will be removed in a future release.

The module was intended to provide a way to provide alphabet subsets for various language. It a naive implementation.

It has been replaced by the `type-factory-unicode-cldr` module which provides a more complete  range of alphabet subsets for various languages. It is created from the Unicode CLDR datasets.

We suggest you remove the following dependency from your Maven pom files and replace it with the `type-factory-unicode-cldr` dependency:

```xml
<dependency>   
  <groupId>org.typefactory</groupId>  
  <artifactId>type-factory-language</artifactId>  
  <version>...</version>
</dependency>    
```

## Created the type-factory-unicode-cldr module

The `type-factory-unicode-cldr` module which provides various locale-based character subsets for a large set of languages. It has been created from the Unicode CLDR datasets.

It replaces the deprecated and naive `type-factory-language` module which was intended to provide the same kind of thing.

Include the following dependency in your Maven pom file:

```xml
<dependency>   
  <groupId>org.typefactory</groupId>  
  <artifactId>type-factory-unicode-cldr</artifactId>  
  <version>...</version>
</dependency>    
```
