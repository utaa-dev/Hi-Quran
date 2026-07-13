# Hi-Quran Development Roadmap

Status Project

Current Phase

Generator Refactoring

---

# Milestone 0

Build Recovery

Status

✅ Complete

Target

- Build berhasil
- Dependency selesai
- Compile tanpa error

---

# Milestone 1

Recovery

Status

✅ Complete

Target

- insertSurahs()
- insertAyahs()
- saveMetadata()

---

# Milestone 2

Domain Layer

Status

✅ Complete

Target

- Domain Model
- Repository Interface

---

# Milestone 3

Data Layer

Status

🟡 In Progress

Target

- RepositoryImpl
- Mapper
- RemoteDataSource
- LocalDataSource

---

# Milestone 4

UseCase Layer

Status

⬜ Planned

Target

- GenerateQuranDatabaseUseCase
- Validation Pipeline

---

# Milestone 5

Presentation Cleanup

Status

⬜ Planned

Target

- Main.kt hanya sebagai CLI

---

# Milestone 6

Generator Validation

Status

⬜ Planned

Target

- Empty String Validation
- Sequence Validation
- Duplicate Validation
- Metadata Validation

---

# Milestone 7

Android Integration

Status

⬜ Planned

Target

- Room Entity
- DAO
- createFromAsset()

---

# Milestone 8

Offline First

Status

⬜ Planned

Target

- Hilangkan JSON Import
- Android menggunakan quran.db langsung

---

# Milestone 9

Testing

Status

⬜ Planned

Target

- Unit Test
- Integration Test
- Database Verification

---

# Milestone 10

Release Candidate

Status

⬜ Planned

Target

Generator

- Stable
- Verified
- Reproducible

Android

- Stable
- Offline
- Ready Production

---

# Long Term Goals

Generator

- Multi Language
- Tafsir
- Audio Metadata
- Search Index
- FTS5
- Database Versioning
- Migration Support

Android

- Bookmark
- Last Read
- Search
- Tafsir
- Audio
- Notes
- Widget
- Kotlin Multiplatform