# QBit Template: Data

Template repository for creating QQQ Data QBits - reference data with table prefixing, sync processes, and Liquibase generation.

## What is a Data QBit?

Data QBits provide reusable reference data sets that applications consume. They support:
- **Table prefixing** for multi-instance deployment
- **Sync processes** with natural key upsert
- **Liquibase generation** for schema management
- **PossibleValueSources** for dropdown lookups

## Quick Start

Requires **Java 21**, **Maven 3.8+**, and QQQ **4.0.0**. Create your repository, rename its Maven coordinates and Java package/classes, then run `mvn clean verify`. See [Getting Started](docs/00-getting-started.md).

## Structure

```
src/main/java/com/kingsrook/qbits/example/
├── ExampleDataQBitConfig.java       # Configuration
├── ExampleDataQBitProducer.java     # QBit registration
├── model/
│   ├── ExampleEntity.java
│   └── ExampleChildEntity.java
├── sync/
│   ├── ExampleDataSyncStep.java
│   └── ExampleDataSyncProcessMetaDataProducer.java
└── liquibase/
    └── ExampleLiquibaseGenerator.java
```

## Key Characteristics

- **Table prefixing** - `shipping_country`, `billing_country`
- **Host configuration** - Backend and optional table prefix
- **Sync process** - Upsert by natural key
- **Liquibase generator** - Template-based, prefix-aware

## Host integration

```java
new ExampleDataQBitProducer()
   .withConfig(new ExampleDataQBitConfig().withBackendName("rdbms"))
   .produce(qInstance, "example-data");
```

The host must already define the backend. The example prefixes table names; adapting it for multiple instances also requires unique process and possible-value-source names and consistent references. See [Getting Started](docs/00-getting-started.md).

## Documentation

- [Getting Started](docs/00-getting-started.md)
- [Data QBit Architecture](docs/01-data-qbit-architecture.md)
- [Sync Process](docs/02-sync-process.md)
- [Liquibase Generation](docs/03-liquibase-generation.md)

## License

See [LICENSE](LICENSE) and [NOTICE](NOTICE).
