---
layout: page
title: "Best practices"
nav_order: 3010
---

<details markdown="block">
  <summary>
    Table of contents
  </summary>
  {: .text-delta }
- TOC
{:toc}
</details>

# Best practices

## One type parser per custom type

Every custom type should have its own static final `TypeParser` instance. Don't reuse type parsers for multiple types.

The risk to resuing type parsers is that changes to rule sets for one type may inadvertently affect other types that share the same type parser.

The expense of creating a separate one for each type is minimal. The instantiated type parser is compiled to a compact memory footprint utilising primitive-based data structures internally. They are immutable and thread-safe.

## Create robust custom types and parsers

Aim to make your custom type adhere to the [Robustness Principle or Postel's Law](https://en.wikipedia.org/wiki/Robustness_principle): _"Be lenient in what you expect and strict in what you provide."_:

For example, for a currency type, allow your client to provide values like `"EUR"`, `"eur"`, or `" Eur "` since the intended meaning is clear and the value cannot be confused with any other currency.

Configure your type parser to transform the value to a strict format like `"EUR"` when parsing the value. 

Always return the strict format when your type is used to provide a value.

## Provide meaningful error codes and messages

Provide error message codes and default messages that are meaningful and helpful to the client for each type. Avoid returning generic `invalid.value` style codes. 

For example, for a currency code type, consider creating an error message code with a default message like:

```java
MessageCode.of(
    "invalid.currency.code",
    "must be a valid 3-letter ISO currency code");
```

### Error messages can be localized

You can provide localized/internationalized error messages using resource bundles:

- `org/typefactory/Messages.properties`
    ```properties
    invalid.currency.code=must be a valid 3-letter ISO currency code
    ```
- `org/typefactory/Messages_fr.properties`
    ```properties
    invalid.currency.code=doit être un code devise ISO valide à 3 lettres
    ```

The default message will be used if no resource bundle message is found for the desired locale.

## Use the `Types` utility for null safe comparisons

When comparing two custom types, use the Type Factory `Types` utility class instead of the Apache Commons `Strings` or `StringUtils` classes. 

The `Types` utility method signatures ensure that both objects are of the same type. 

The Apache Commons `Strings` or `StringUtils` classes will compare the custom type instances as `CharSequence` values, perhaps missing situations where the developer is comparing two different types.

The Java `Objects.equals(o1, o2)` method can also be used for a null safe equality check as it will eventually invoke `o1.equals(o2)` after checking that `o1` is not null. 

Your custom types extending `StringType`, or implemented using Java records extending `CharSequenceType`, will have `equals()` methods that ensure the two objects are of the same type before comparing their values.

