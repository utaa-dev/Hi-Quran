# Hi-Quran Project Context

## Project Overview

Hi-Quran adalah aplikasi Al-Qur'an berbasis Kotlin yang mengusung prinsip **Offline-First**. Seluruh data Al-Qur'an diproses terlebih dahulu oleh Generator Tool dan kemudian digunakan oleh aplikasi Android melalui database SQLite yang telah dibangun sebelumnya.

Project ini terdiri dari dua bagian utama:

1. Android Application
2. Quran Generator Tool

Generator bertanggung jawab menghasilkan database final, sedangkan Android hanya bertugas mengonsumsi database tersebut.

---

# Project Goals

Target utama project ini adalah:

- Offline First
- Cepat saat startup
- Tanpa import JSON
- Tanpa download data setelah install
- Database tervalidasi
- Clean Architecture
- Mudah dikembangkan

---

# Modules

## :app

Android Application

Tanggung jawab:

- UI
- Navigation
- ViewModel
- Room Database
- Repository
- UseCase

Tidak boleh:

- Download data Al-Qur'an
- Generate database
- Mengubah isi database

---

## :tools:quran-generator

CLI Tool

Tanggung jawab:

- Mengambil data dari API
- Normalisasi data
- Validasi data
- Membuat SQLite Database
- Membuat metadata
- Membuat report

Output:

output/quran.db

---

# Single Source of Truth

Generator Tool merupakan satu-satunya sumber data Al-Qur'an.

```
API
      ↓
Generator Tool
      ↓
quran.db
      ↓
Android Assets
      ↓
Room Database
```

Android tidak pernah membuat ulang database.

Android hanya membaca database.

---

# Database

Output Generator:

```
output/quran.db
```

Android Asset:

```
app/src/main/assets/database/quran.db
```

Database akan dibuka menggunakan

```
Room.createFromAsset(...)
```

---

# Current Database

Tables

- surah
- ayah
- database_metadata

Total Data

- 114 Surah
- 6236 Ayah

Primary Key

surah

```
number
```

ayah

```
(surah_number, number)
```

---

# Naming Convention

SQLite

snake_case

Contoh

```
surah_number
english_name
number_of_ayahs
```

Kotlin

camelCase

Contoh

```
surahNumber
englishName
numberOfAyahs
```

---

# Current Architecture Status

Generator

- Recovery Complete
- Domain Layer Complete
- Data Layer In Progress

Android

- Offline First
- Room Database
- Waiting Integration

---

# Development Principles

- Offline First
- Clean Architecture
- SOLID
- Single Source of Truth
- Simplicity over Cleverness
- Fail Fast
- Explicit is better than Implicit