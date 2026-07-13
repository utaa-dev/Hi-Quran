# Hi-Quran Architecture Decisions

Dokumen ini berisi keputusan-keputusan arsitektur yang telah disepakati selama pengembangan Hi-Quran.

Semua developer maupun AI Assistant wajib mengikuti keputusan yang ada di dokumen ini.

Jika ingin mengubah salah satu keputusan, lakukan diskusi terlebih dahulu sebelum implementasi.

---

# ADR-001

## Generator adalah Single Source of Truth

Status

Accepted

Decision

Seluruh data Al-Qur'an hanya boleh dibuat oleh Generator Tool.

Android tidak diperbolehkan menghasilkan ulang database.

Reason

- Konsistensi data
- Validasi dilakukan satu kali
- Android lebih ringan
- Menghindari perbedaan struktur database

Impact

Generator menjadi satu-satunya tempat perubahan data.

---

# ADR-002

## Android menggunakan createFromAsset()

Status

Accepted

Decision

Android menggunakan

Room.createFromAsset()

untuk membuka database.

Tidak ada proses import JSON.

Reason

- Startup lebih cepat
- Tidak ada parsing ribuan ayat
- Database sudah tervalidasi

Impact

Generator wajib menghasilkan database final.

---

# ADR-003

## SQLite sebagai Format Distribusi

Status

Accepted

Decision

Format distribusi resmi adalah SQLite.

Bukan JSON.

Reason

SQLite

- cepat
- hemat memori
- mudah di-query
- memiliki index

Impact

Generator tidak menghasilkan file JSON untuk Android.

---

# ADR-004

## Composite Primary Key

Status

Accepted

Decision

Tabel ayah menggunakan

PRIMARY KEY

```
(surah_number, number)
```

Reason

Nomor ayat hanya unik di dalam satu surah.

Tidak membutuhkan id tambahan.

Impact

Room menggunakan

```
@Entity(
    primaryKeys = [
        "surah_number",
        "number"
    ]
)
```

---

# ADR-005

## Offline First

Status

Accepted

Decision

Aplikasi harus dapat digunakan tanpa internet.

Reason

Pengguna harus tetap bisa membaca Al-Qur'an kapan saja.

Impact

Tidak boleh ada request API saat membaca Al-Qur'an.

---

# ADR-006

## Generator mengikuti Clean Architecture

Status

Accepted

Decision

Generator dipisahkan menjadi

Presentation

↓

UseCase

↓

Repository

↓

Data Source

Reason

Agar mudah diuji.

Agar mudah dikembangkan.

Agar konsisten dengan aplikasi Android.

---

# ADR-007

## Domain Layer harus Pure Kotlin

Status

Accepted

Decision

Domain Layer tidak boleh bergantung pada

- Retrofit
- JDBC
- SQLite
- Room
- Android SDK

Reason

Agar mudah dipindahkan.

Agar mudah diuji.

Impact

Semua mapping dilakukan di Data Layer.

---

# ADR-008

## Snake Case vs Camel Case

Status

Accepted

Decision

SQLite

snake_case

Kotlin

camelCase

Reason

Mengikuti standar masing-masing platform.

---

# ADR-009

## Validasi dilakukan di Generator

Status

Accepted

Decision

Generator bertanggung jawab melakukan

- duplicate check
- foreign key check
- empty string check
- sequence check
- checksum

Android tidak mengulang validasi tersebut.

Reason

Validasi cukup dilakukan sekali.

---

# ADR-010

## Android hanya sebagai Consumer

Status

Accepted

Decision

Android

boleh

✔ membaca

✔ mencari

✔ bookmark

✔ history

✔ last read

Android

tidak boleh

✘ generate database

✘ download quran

✘ import json

Reason

Semua data sudah final.

---

# ADR-011

## Fail Fast

Status

Accepted

Decision

Jika validasi generator gagal

maka

- rollback transaction

- hapus database

- hentikan proses

Reason

Lebih baik gagal saat development daripada membawa database rusak ke production.

---

# ADR-012

## Source API dapat berubah

Status

Accepted

Decision

Generator harus menyembunyikan implementasi API.

UseCase tidak boleh mengetahui Retrofit.

Reason

Jika nanti API berpindah,

Repository saja yang berubah.

---

# ADR-013

## Future Ready

Status

Accepted

Generator harus mudah dikembangkan untuk

- Tafsir

- Audio

- Multi Language

- FTS5

- Bookmark Metadata

- Search Index

tanpa mengubah arsitektur utama.

---

# ADR-014

## Database Versioning

Status

Accepted

Setiap perubahan schema database wajib menaikkan versi metadata.

Reason

Agar Android mengetahui kapan harus melakukan migration.

---

# ADR-015

## Development Rules

Semua perubahan harus melalui tahapan berikut

1.

Analisis

↓

2.

Persetujuan

↓

3.

Implementasi

↓

4.

Verifikasi

↓

5.

Commit

Tidak boleh langsung melakukan perubahan kode tanpa analisis terlebih dahulu.

---

# Hi-Quran Architecture Decisions

Dokumen ini berisi seluruh keputusan arsitektur yang telah disepakati selama pengembangan Hi-Quran.

Tujuan dokumen ini adalah menjaga konsistensi desain dan mencegah perubahan arsitektur tanpa alasan yang kuat.

---

# ADR-001

## Generator adalah Single Source of Truth

Status

Accepted

Tanggal

2026

### Keputusan

Semua data Al-Qur'an harus dibuat oleh Generator Tool.

Android tidak diperbolehkan membuat ulang database.

### Alasan

Generator memiliki proses:

- Fetch
- Mapping
- Validation
- Optimization
- Reporting

Android seharusnya hanya membaca data.

### Konsekuensi

Generator menghasilkan

```

quran.db

```

Android menggunakan

```

createFromAsset()

```

Tidak ada import JSON.

---

# ADR-002

## Offline First

Status

Accepted

### Keputusan

Aplikasi harus tetap dapat digunakan sepenuhnya tanpa internet.

### Alasan

Pengguna dapat membaca Al-Qur'an kapan saja.

Startup lebih cepat.

Tidak bergantung pada server.

### Konsekuensi

Database sudah tersedia saat aplikasi di-install.

---

# ADR-003

## Database Dibuat Sebelum Aplikasi Di-build

Status

Accepted

### Keputusan

Generator dijalankan sebelum proses build Android.

Output generator menjadi asset aplikasi.

### Alur

```

API

↓

Generator

↓

quran.db

↓

assets/database

↓

APK

```

---

# ADR-004

## Composite Primary Key

Status

Accepted

### Keputusan

Tabel ayah menggunakan

```

PRIMARY KEY (
surah_number,
number
)

```

### Alasan

Nomor ayat hanya unik di dalam satu surah.

Tidak membutuhkan id tambahan.

Lebih sesuai dengan struktur Al-Qur'an.

---

# ADR-005

## Snake Case di SQLite

Status

Accepted

### Keputusan

SQLite menggunakan snake_case.

Contoh

```

surah_number

number_of_ayahs

english_name

```

Kotlin menggunakan camelCase.

Contoh

```

surahNumber

numberOfAyahs

englishName

```

### Alasan

Mengikuti standar SQL dan Kotlin.

---

# ADR-006

## Tidak Menggunakan JSON Import

Status

Accepted

### Keputusan

Android tidak melakukan import JSON saat startup.

### Alasan

Lebih lambat.

Menambah kompleksitas.

Data sudah tersedia dalam SQLite.

### Konsekuensi

JSON hanya digunakan oleh Generator.

---

# ADR-007

## Room createFromAsset()

Status

Accepted

### Keputusan

Room harus menggunakan

```kotlin
createFromAsset("database/quran.db")
```

### Alasan

Startup lebih cepat.

Tidak perlu membuat tabel satu per satu.

Menghindari proses import.

---

# ADR-008

## Generator Menggunakan Clean Architecture

Status

Accepted

### Layer

Presentation

↓

UseCase

↓

Repository

↓

Remote + Local DataSource

↓

SQLite

### Alasan

Mudah diuji.

Mudah dikembangkan.

Konsisten dengan Android.

---

# ADR-009

## Android Hanya Consumer

Status

Accepted

Android tidak boleh

- Generate database
- Download Al-Qur'an
- Mengubah data Al-Qur'an

Android hanya

- Membaca
- Menampilkan
- Menyimpan data pengguna

Contoh

- Bookmark
- Last Read
- Notes

---

# ADR-010

## Validation Dilakukan di Generator

Status

Accepted

Generator melakukan validasi

- Total Surah
- Total Ayah
- Foreign Key
- Duplicate
- Empty String
- Sequence
- Checksum

Android tidak mengulang validasi tersebut.

---

# ADR-011

## Metadata Database

Status

Accepted

Generator menghasilkan metadata

- version
- generated_at
- checksum
- api_source
- source_license

Metadata disimpan di

database_metadata

dan

metadata.json

---

# ADR-012

## Future Expansion

Status

Accepted

Generator nantinya dapat menghasilkan

- Tafsir
- Audio
- Multi Bahasa
- Search Index
- FTS5
- Database Versioning

tanpa mengubah arsitektur utama.

---

# Decision Rule

Semua perubahan arsitektur baru harus memenuhi syarat berikut:

1. Memiliki alasan teknis yang jelas.
2. Tidak melanggar prinsip Offline First.
3. Tidak menghilangkan Single Source of Truth.
4. Tidak menambah kompleksitas tanpa manfaat nyata.
5. Didokumentasikan pada file ini.