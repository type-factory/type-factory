# Type Factory

Type Factory helps Java developers create small, explicit custom types for values that need validation or cleanup.

Use it when a raw `String` is too loose, repeated annotation and regex validation is hard to maintain, and handwritten value objects would add too much boilerplate.



## What it gives you

- Immutable custom types backed by clear parsing and validation rules.
- Reusable `TypeParser` definitions for cleanup, formatting, regex checks, and custom validators.
- Consistent boundary validation, so invalid values fail before they spread through your application.
- Built-in `equals`, `hashCode`, `compareTo`, and `toString` behavior for objects that extend `StringType` which also implements `CharSequence` and `Comparable`.

## Start here

- [Getting started](getting-started.md) &ndash; add Type Factory to a Maven or Gradle project and create your first custom types.
- [Regex validation pitfalls](regex-validation-pitfalls.md) &ndash; understand when Unicode scripts, blocks, categories, and regex validation are broader than the business rule you need.

## Documentation

- [Getting started](getting-started.md)
- [Regex validation pitfalls](regex-validation-pitfalls.md)

## Release notes

- [Release notes - v1.1.0](release-notes/release-notes-v1.1.0.md)
- [Release notes - v1.0.1](release-notes/release-notes-v1.0.1.md)
- [Release notes - v1.0.0](release-notes/release-notes-v1.0.0.md)

## Project

- [Source code](https://github.com/type-factory/type-factory)
- [Maven Central](https://central.sonatype.com/search?q=g%253Aorg.typefactory)
- [Javadocs](https://javadoc.io/doc/org.typefactory/type-factory-core)
