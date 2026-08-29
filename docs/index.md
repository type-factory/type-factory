---
layout: page
title: "Type Factory"
nav_order: 1000
---

# Type Factory

Type Factory helps Java developers replace loosely validated `String` values with small, explicit custom types.

Use it when a value has its own rules, but repeated validation logic is hard to maintain and handwritten value objects would add too much boilerplate.

## What it provides

- Immutable, thread-safe `TypeParser` definitions for cleanup, formatting, and validation.
- Simple creation of immutable custom value-object types from `TypeParser` instances.
- Consistent boundary validation, so invalid values fail before spreading through your application.
- Less repeated annotation validation, regex validation, and cleanup boilerplate.

## Start here

- [Getting started](getting-started) &ndash; create your first custom types.

## Then continue with

_There is a lot more to come in this section._

- [Regex validation pitfalls](regex-validation-pitfalls) &ndash; some regular expression shortcomings and a comparison to TypeFactory types.

## Release notes

- [Release notes on GitHub](https://github.com/type-factory/type-factory/releases)

## Project

- [Type Factory on GitHub](https://github.com/type-factory/type-factory)
- [Type Factory on Maven Central](https://central.sonatype.com/search?q=g:org.typefactory)
- [Type Factory Javadocs](https://javadoc.io/doc/org.typefactory/type-factory-core)


