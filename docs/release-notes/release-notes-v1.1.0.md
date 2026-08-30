# Release notes &ndash; version v1.1.0

## Using this release version with Maven / Gradle

Use the following Maven or Gradle coordinates to import the Type Factory BOM and modules into your project.

<details name="import-coordinates">
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
  <dependency>  <!-- Required: the core Type Factory library. -->
    <groupId>org.typefactory</groupId>
    <artifactId>type-factory-core</artifactId>
  </dependency>
  <dependency>  <!-- Optional: the locale Unicode CLDR library. -->
    <groupId>org.typefactory</groupId>
    <artifactId>type-factory-unicode-cldr</artifactId>
  </dependency>
  <dependency>  <!-- Optional: the test assertions library extending AssertJ. -->
    <groupId>org.typefactory</groupId>
    <artifactId>type-factory-test-assertj</artifactId>
    <scope>test</scope>
  </dependency>
</dependencies>
```
</details>

<details name="import-coordinates">
<summary>Gradle</summary>

```groovy
dependencies {
  implementation platform("org.typefactory:type-factory-bom:1.1.0")

  // Required: the core Type Factory library. 
  implementation "org.typefactory:type-factory-core"

  // Optional: the locale Unicode CLDR library.
  implementation "org.typefactory:type-factory-unicode-cldr"

  // Optional: the test assertions library extending AssertJ.
  testImplementation "org.typefactory:type-factory-test-assertj"
}
```

</details>


## Updates / what's new


### InvalidValueException error messages

Exception messages will now always include invalid Unicode code point identifier, for example ‘U+0430’, alongside the invalid character. This is to help make obvious homoglyphs/homographs – letters from other scripts/alphabets that look like letters in the target script/alphabet. 
 
For example, for a type that only expects English letters, spelling amazon.com or apple.com with the Cyrillic `а` (U+0430) instead of the Latin `a` (U+0061) will result in an `InvalidValueException` message that will include the invalid character and its code point identifier:

- previously returned: `...invalid character 'а'`
- now returns: `...invalid character а U+0430`
- will also return the code point name if configured: `...invalid character а U+0430 CYRILLIC SMALL LETTER A`



### MessageCode now extends CharSequence and Comparable

The `MessageCode` interface now extends `CharSequence` and `Comparable<MessageCode>` to make it obvious that, first and foremost, it is a message-code class that has a default message along for the ride.

Two message code instances are considered equal if their code is the same:

- Deprecated the `MessageCode.hasSameCodeAs(MessageCode other)` method.
- Use the `MessageCode.equals(Object)` instead.



### Added a Types utility class

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



### Added ability to parse invalid sequences

The `TypeParser` can now parse invalid character sequences with the choice of _replacing_ or _removing_ invalid characters:

-  `ParseResult parseToStringReplacingInvalidCharacters(CharSequence)`
-  `ParseResult parseToStringRemovingInvalidCharacters(CharSequence)`

#### Example – replacing or removing invalid characters

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



### Added the Type Factory Assertions library

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


### Add the ability to convert decimal digits

Added the ability to configure the type-parser to convert any Unicode decimal digit in the input sequence to a different set of decimal digits starting with the specified target zero-digit:

- `convertAnyDecimalDigitsToDigitsStartingWithZeroDigit(char zeroDigitChar)`
- `convertAnyDecimalDigitsToDigitsStartingWithZeroDigit(int zeroDigitCodePoint)`

#### Example – converting decimal digits

| Target zero digit | Input value | Parsed value | Notes                                                                         | 
|-------------------|-------------|--------------|-------------------------------------------------------------------------------|
| 0                 | 0123456789  | 0123456789   | Western Arabic digits remain as they are                                      |
| ०                 | ४५ Pies     | 45 Pies      | Only the decimal digits converted from Devanagari to Western Arabic digits    |
| 0                 | ０１２３４５６７８９  | 0123456789   | full-width digits converted to ordinary Western Arabic digits                 | 
| ०                 | 0123456789  | ०१२३४५६७८९   | Western Arabic digits converted to  to Devanagari digits                      | 
| ൦                 | 0123456789  | ൦൧൨൩൪൫൬൭൮൯   | Western Arabic digits parsed to Malayalam digits                              | 
| 0                 | ൦൧൨൩൪൫൬൭൮൯  | 0123456789   | Malayalam digits parsed to Western Arabic digits                              |


### TypeFactoryConfig to configure global type parser

A `TypeFactoryConfig` has been added which currently allows global configuration options. The configuration can be set by setting:

- Environment variables
- System properties
- Programatically calling static methods on the `TypeFactoryConfig` class.

#### Configure code point names in exception messages

Determines whether Unicode code points names are included in `InvalidValueException` messages. For example, whether you wish the message to contain either:

- `...invalid character Ε U+0395`
- `...invalid character Ε U+0395 GREEK CAPITAL LETTER EPSILON`

Configuration:

- **System property:** `org.typefactory.codePointNamesInExceptionMessages`
- **Environment variable:** `ORG_TYPE_FACTORY_CODE_POINT_NAMES_IN_EXCEPTION_MESSAGES`
- **Options:** `true`/`false` (default: `false`)

#### Configure code point names cache size

The size of the cache to create when setting the configuration option `CODE_POINT_NAMES_IN_EXCEPTION_MESSAGES=true`.

Retrieving code points is via a lookup in the JVM and can be costly if you expect a lot of `InvalidValueException` messages to be thrown requiring code points names to be included.

The cache is useful if you expect most invalid characters to be roughly of a smallish set of the same characters over and over. For example, clients passing punctuation or special characters when only alphanumerics are allowed.

Configuration:

- **System property:** `org.typefactory.codePointNamesCacheSize`
- **Environment variable:** `ORG_TYPE_FACTORY_CODE_POINT_NAME_CACHE_SIZE`
- **Options:** 1..MAX_INTEGER (default: `500`)



### Provide a fluent string formatter class

Added a `StringFormatter` class that wraps the Java `StringBuilder` to provide fluent string formatting methods.



### Subset changes

The `Subset` interface now provides a method to return the subset code point ranges as a pattern string.

Moved the `SubsetWithCategories` interface into the `org.typefactory.impl` package as it provides no value in the core `org.typefactory` package.



### Type parser builder exceptions

Added the `TypeParserBuilderException` class. These may be thrown while creating a type parser when:

- the `TypeParserBuilder.convertAnyDecimalDigitsToDigitsStartingWithZeroDigit(zeroDigit)` is passed a character or code point that is not the zero-character of a consecutive set of ten decimal digits.
  


### Deprecating the type-factory-language module

The `type-factory-language` module is being deprecated and will be removed in a future release.

The module was intended to provide a way to provide alphabet subsets for various languages. It was a naive implementation.

It has been replaced by the `type-factory-unicode-cldr` module which provides a more complete  range of alphabet subsets for various languages. It is created from the Unicode CLDR datasets.

We suggest you remove the following dependency from your Maven pom files and replace it with the `type-factory-unicode-cldr` dependency:

```xml
<dependency>   
  <groupId>org.typefactory</groupId>  
  <artifactId>type-factory-language</artifactId>  
  <version>...</version>
</dependency>    
```


### Added documentation for the Type Factory project

Added [Type Factory documentation](https://www.typefactory.org/) built and published as a GitHub Pages site.



## Build and continuous integration updates

* Bump junit-jupiter.version from 5.11.3 to 5.11.4 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/266
* Bump org.apache.maven.plugins:maven-javadoc-plugin from 3.10.1 to 3.11.2 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/265
* Bump org.apache.maven.plugins:maven-surefire-plugin from 3.5.1 to 3.5.2 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/261
* Bump org.codehaus.mojo:exec-maven-plugin from 3.4.1 to 3.5.0 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/260
* Bump org.sonarsource.scanner.maven:sonar-maven-plugin from 4.0.0.4121 to 5.0.0.4389 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/262
* Bump org.mockito:mockito-bom from 5.14.2 to 5.17.0 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/281
* Bump org.apache.maven.plugins:maven-compiler-plugin from 3.13.0 to 3.14.0 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/278
* Bump junit-jupiter.version from 5.11.4 to 5.12.0 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/277
* Bump org.relaxng:trang from 20220510 to 20241231 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/275
* Bump org.assertj:assertj-core from 3.26.3 to 3.27.3 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/276
* Bump icu4j.version from 76.1 to 77.1 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/283
* Bump org.sonarsource.scanner.maven:sonar-maven-plugin from 5.0.0.4389 to 5.1.0.4751 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/282
* Bump junit-jupiter.version from 5.12.0 to 5.13.0 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/285
* Bump org.codehaus.mojo:flatten-maven-plugin from 1.6.0 to 1.7.0 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/286
* Bump org.codehaus.mojo:exec-maven-plugin from 3.5.0 to 3.5.1 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/284
* Bump junit-jupiter.version from 5.13.0 to 5.13.1 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/290
* Bump org.apache.maven.plugins:maven-deploy-plugin from 3.1.3 to 3.1.4 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/291
* Bump org.codehaus.mojo:jaxb2-maven-plugin from 3.2.0 to 3.3.0 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/294
* Bump org.jacoco:jacoco-maven-plugin from 0.8.12 to 0.8.13 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/293
* Bump org.apache.maven.plugins:maven-surefire-plugin from 3.5.2 to 3.5.3 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/292
* Bump org.mockito:mockito-bom from 5.17.0 to 5.18.0 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/296
* Bump org.codehaus.mojo:flatten-maven-plugin from 1.7.0 to 1.7.1 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/297
* Bump org.assertj:assertj-core from 3.27.3 to 3.27.6 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/310
* Bump org.apache.maven.plugins:maven-javadoc-plugin from 3.11.2 to 3.12.0 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/308
* Bump org.assertj:assertj-core from 3.27.6 to 3.27.7 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/312
* Bump org.apache.maven.plugins:maven-resources-plugin from 3.3.1 to 3.4.0 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/314
* Bump org.codehaus.mojo:exec-maven-plugin from 3.5.1 to 3.6.3 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/313
* Bump org.codehaus.mojo:flatten-maven-plugin from 1.7.1 to 1.7.3 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/307
* Bump org.apache.maven.plugins:maven-gpg-plugin from 3.2.7 to 3.2.8 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/302
* Bump junit-jupiter.version from 5.13.1 to 5.13.4 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/303
* Bump jakarta.xml.bind:jakarta.xml.bind-api from 4.0.2 to 4.0.5 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/319
* Bump com.sun.xml.bind:jaxb-impl from 4.0.5 to 4.0.6 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/318
* Bump org.jacoco:jacoco-maven-plugin from 0.8.13 to 0.8.14 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/317
* Bump org.sonarsource.scanner.maven:sonar-maven-plugin from 5.1.0.4751 to 5.5.0.6356 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/316
* Bump org.apache.maven.plugins:maven-compiler-plugin from 3.14.0 to 3.15.0 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/320
* Bump org.apache.maven.plugins:maven-resources-plugin from 3.4.0 to 3.5.0 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/321
* Bump org.apache.maven.plugins:maven-surefire-plugin from 3.5.3 to 3.5.5 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/322
* Bump junit-jupiter.version from 5.13.4 to 6.0.3 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/315
* Bump org.codehaus.mojo:jaxb2-maven-plugin from 3.3.0 to 4.1.0 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/323
* Bump org.sonarsource.scanner.maven:sonar-maven-plugin from 5.5.0.6356 to 5.7.0.6970 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/331
* Bump junit-jupiter.version from 6.0.3 to 6.1.0 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/329
* Bump org.apache.maven.plugins:maven-surefire-plugin from 3.5.5 to 3.5.6 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/330
* Bump org.apache.maven.plugins:maven-source-plugin from 3.3.1 to 3.4.0 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/327
* Bump org.mockito:mockito-bom from 5.18.0 to 5.23.0 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/326
* build(deps): bump org.codehaus.mojo:flatten-maven-plugin from 1.7.3 to 1.8.0 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/340
* build(deps-dev): bump org.jacoco:jacoco-maven-plugin from 0.8.14 to 0.8.15 by @dependabot[bot] in https://github.com/type-factory/type-factory/pull/339
* Update version for release to 1.1.0 – version was 1.0.2-SNAPSHOT by @evantoli in https://github.com/type-factory/type-factory/pull/351

## Miscellaneous

* Update workflows to address issues reported by Sonar by @evantoli in https://github.com/type-factory/type-factory/pull/334
* Update workflows action versions by @evantoli in https://github.com/type-factory/type-factory/pull/335
* Update license information by @evantoli in https://github.com/type-factory/type-factory/pull/336
* Tweak CLDR code generator for import statements by @evantoli in https://github.com/type-factory/type-factory/pull/337
* Added documentation by @evantoli in https://github.com/type-factory/type-factory/pull/342
* Tweak GitHub Pages documentation config by @evantoli in https://github.com/type-factory/type-factory/pull/344
* More GitHub pages workflow tweaks by @evantoli in https://github.com/type-factory/type-factory/pull/345
* Tweak GitHub Pages workflow by @evantoli in https://github.com/type-factory/type-factory/pull/346
* Added docs for best practices. by @evantoli in https://github.com/type-factory/type-factory/pull/347
* Updated the README by @evantoli in https://github.com/type-factory/type-factory/pull/348
* Fix issues reported by Sonar by @evantoli in https://github.com/type-factory/type-factory/pull/350
* Update the maven-gpg-plugin config in the pom.xml by @evantoli in https://github.com/type-factory/type-factory/pull/352
* Update the Sonatype Maven Central config to publish by @evantoli in https://github.com/type-factory/type-factory/pull/353
* Update the Sonatype Maven Central config to use central-publishing-maven-plugin by @evantoli in https://github.com/type-factory/type-factory/pull/354
* Again update the Sonatype Maven Central config to use central-publishing-maven-plugin by @evantoli in https://github.com/type-factory/type-factory/pull/355
* More updates to the Sonatype Maven Central config to use central-publishing-maven-plugin by @evantoli in https://github.com/type-factory/type-factory/pull/356
* More updates to the Sonatype Maven Central config to use central-publishing-maven-plugin by @evantoli in https://github.com/type-factory/type-factory/pull/357

**Full Changelog**: https://github.com/type-factory/type-factory/compare/v1.0.1...v1.1.0
