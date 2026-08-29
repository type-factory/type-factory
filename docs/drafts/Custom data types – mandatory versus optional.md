Mandatory versus Optional Custom Data Types
===========================================

Q. How do I create a custom data type that can be used for both mandatory and optional values?

Your custom data type should allow null and/or empty values alongside rules for "valid values".

For example, if your data type requires valid values to be made up of 8..12 alphanumeric characters, then your type also needs to handle values of `null` and/or empty string values for the APIs in which the property is optional.


```java
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ItemModel {

  // A mandatory item code – 12..16 characters, alphanumeric only.
  @NotEmpty
  private final ItemCode code;

  // A Mandatory item name – 2..50 characters, alphanumeric, spaces and hyphens only.
  @NotEmpty
  private final ItemName name;

  // An optional item category - 2..50 characters, alphanumeric, spaces and hyphens only
  @Nullable
  private final ItemCategory category;
}
```

When using the Type-Factory TypeParser to defined you custom data type., you can create a custom data type that can be used for both mandatory and optional values by configuring the type parser appropriately.