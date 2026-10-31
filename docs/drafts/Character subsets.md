Character subsets
-----------------

These are normally created implicitly by the `TypeParser` builder. They are used to define what characters the type parser will accept as valid.

If required, we can also create immutable and thread-safe character `Subset` instances that we can use to configure type parsers. `Subset` instances are relatively expensive to create, so it is recommended to create them once and reuse them.
