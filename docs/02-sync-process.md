# Sync Process Pattern

Data QBits use a sync process to load and update reference data using natural key upsert.

## Natural Key Design

Each table defines fields that uniquely identify records for upsert matching:

| Table | Natural Key | Example |
|-------|-------------|---------|
| exampleEntity | `code` | "US", "CA", "MX" |
| exampleChildEntity | `exampleEntityId` + `code` | (1, "CA"), (1, "TX") |

## Sync Algorithm

```
Source Data (JSON) --> Sync Process --> Database Tables
                           |
                           v
                    [Compare by natural key]
                           |
         +-----------------+------------------+
         |                 |                  |
      Insert           Update            Deactivate
    (new keys)    (changed values)    (removed keys)
```

## Implementation

The checked implementation is [ExampleDataSyncStep](../src/main/java/com/kingsrook/qbits/example/sync/ExampleDataSyncStep.java), with process metadata in [ExampleDataSyncProcessMetaDataProducer](../src/main/java/com/kingsrook/qbits/example/sync/ExampleDataSyncProcessMetaDataProducer.java). It implements both `runOnePage` and the required `getProcessSummary` method.

The step loads classpath JSON, queries existing rows by their natural key, separates inserts/updates/deactivations, and calls its insert/update helpers. Deactivation explicitly sets `isActive=false` for removed active rows. Follow the source helper signatures when adapting the step, and review its execution behavior before introducing a preview or validation screen. The presence of an `isActive` field does not itself filter normal queries.


## Data File Format

Reference data ships as JSON in `src/main/resources/data/`:

```json
[
   {
      "code": "US",
      "name": "United States",
      "description": "United States of America"
   },
   {
      "code": "CA",
      "name": "Canada"
   }
]
```

## Best Practices

1. **Always use natural keys** - Enables upsert without duplicates
2. **Include `isActive` field** - Soft deletes during sync
3. **Version data files** - For reproducible syncs
4. **Log sync summary** - Inserted, updated, deactivated counts
