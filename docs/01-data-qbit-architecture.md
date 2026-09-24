# Data QBit Architecture

## Table Prefixing

The producer can prefix metadata names. Prefixing alone does not guarantee multiple independent instances: review process names, relationships and any hard-coded table references before registering more than one instance.

```java
public String applyPrefix(String tableName)
{
   if(StringUtils.hasContent(tableNamePrefix))
   {
      return tableNamePrefix + "_" + tableName;
   }
   return tableName;
}
```

**Result:**
- No prefix: `country`, `state_province`
- With "shipping" prefix: `shipping_country`, `shipping_state_province`

## Scope Tracking

All metadata is marked with source QBit via `SourceQBitAware`:

```java
if(output instanceof SourceQBitAware sqa)
{
   sqa.setSourceQBitName(qBitMetaData.getName());
}
```

## Natural Keys

Each table defines a natural key for upsert matching:

| Table | Natural Key |
|-------|-------------|
| exampleEntity | `code` |
| exampleChildEntity | `exampleEntityId` + `code` |

## Entity Pattern

```java
@QMetaDataProducingEntity(producePossibleValueSource = true)
public class ExampleEntity extends QRecordEntity
{
   public static final String TABLE_NAME = "exampleEntity";

   @QField(isPrimaryKey = true)
   private Integer id;

   @QField(isRequired = true, maxLength = 10)
   private String code;  // Natural key

   @QField
   private Boolean isActive = true;  // Used by the sync process's explicit deactivation logic
}
```

An `isActive` field is not a global soft-delete facility. Add appropriate query filters and application behavior for inactive rows.
