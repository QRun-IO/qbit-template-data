# qbit-template-data

GitHub template repo for scaffolding QQQ "Data QBits" (reference data +
table prefixing + sync process + Liquibase generation). Placeholder package
`com.kingsrook.qbits.example`; meant to be renamed by template consumers.

## Current 4.0 preparation — 2026-09-10

The historical review below predates the 4.0 migration. This release branch targets Java 21 and QQQ 4.0.0; source and renamed/generated scaffolds pass clean verification against the local RC.3 candidate. Final Maven Central publication is still pending. The corrected APIs, license references and actual template commands are in README.md and docs/00-getting-started.md. Preserve the documented multi-instance and scaffold limitations.

## Knowledge base

- Domain hub: `R:/Git.Local/KofTwentyTwo/second-brain/knowledge/qqq/qqq-hub.md`
- This repo's dossier: `R:/Git.Local/KofTwentyTwo/second-brain/knowledge/qqq/repos/qbit-template-data.md`
  (reviewed commit `62de7d6063c1`, branch `main`, 2026-07-04)
- QBit mechanics: `knowledge/qqq/architecture/metadata-model.md` in the same vault.

Key facts from the review (see dossier for detail):

- The template Java does NOT compile (4 errors: legacy
  `model.MetaDataProducerInterface` import, duplicate `RunBackendStepInput`
  imports, nonexistent `QProcessMetaData.withInputFields`, unimplemented
  `getProcessSummary` from `AbstractTransformStep`). CircleCI has been red
  since it was added.
- Pins `qqq-backend-core` 0.35.0 directly; no `qbit-build-parent`, no BOM
  import — diverges from the convention used by all real qbit repos.
- Licensing: LICENSE/NOTICE are Apache-2.0 but the README still says AGPL-3.0
  and the pom has no `<licenses>` block; source files have no license headers.
- Multi-instance prefixing only covers tables — PVS and process names collide
  if the QBit is produced twice (compare `qbit-geo-data` for the full pattern).
