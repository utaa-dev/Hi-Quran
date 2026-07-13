# Hi-Quran Architecture

Project menggunakan Clean Architecture.

```
Presentation
        │
        ▼
UseCase
        │
        ▼
Repository
        │
 ┌──────┴────────┐
 ▼               ▼
Remote       Local
DataSource   DataSource
```

---

# Android Architecture

```
Activity
    │
Fragment
    │
ViewModel
    │
UseCase
    │
Repository
    │
Room
```

---

# Generator Architecture

```
CLI(Main)

↓

GenerateQuranDatabaseUseCase

↓

QuranRepository

↓

RemoteDataSource
        +
LocalDataSource

↓

SQLite
```

---

# Layer Responsibility

## Presentation

Contoh

Main.kt

Tugas

- menerima command
- menampilkan progress
- menampilkan hasil

Tidak boleh

- SQL
- Retrofit
- Mapping rumit

---

## Domain

Berisi

- Model
- Repository Interface
- UseCase

Tidak boleh bergantung pada

- SQLite
- Retrofit
- JDBC
- Room

---

## Data

Berisi

- RepositoryImpl
- RemoteDataSource
- LocalDataSource
- Mapper

Data Layer bertanggung jawab menerjemahkan data antara API, Database dan Domain.

---

# Data Flow

Generator

```
API

↓

DTO

↓

Mapper

↓

Domain Model

↓

Repository

↓

SQLite
```

Android

```
SQLite

↓

Room Entity

↓

Mapper

↓

Domain

↓

UseCase

↓

ViewModel

↓

UI
```

---

# Database Flow

```
API

↓

Generator

↓

Validation

↓

SQLite

↓

VACUUM

↓

ANALYZE

↓

quran.db

↓

Assets

↓

Room
```

---

# Design Decisions

Generator adalah satu-satunya yang boleh menghasilkan database.

Android tidak boleh:

- import json
- generate sqlite
- download quran

Android hanya membaca database.

---

# Future Expansion

Generator nantinya dapat menghasilkan:

- Quran Indonesia
- Quran English
- Tafsir
- Audio Metadata
- Bookmark Metadata
- Search Index
- FTS5 Database

tanpa mengubah arsitektur utama.