package com.example.hi_quran.generator.database

import com.example.hi_quran.generator.GeneratorConfig
import java.io.File
import java.sql.Connection
import java.sql.DriverManager

class DatabaseManager(private val config: GeneratorConfig) {
    private var connection: Connection? = null
    private val dbFile = File(config.outputDir, config.dbName)

    fun connect() {
        if (dbFile.exists()) dbFile.delete()
        
        val url = "jdbc:sqlite:${dbFile.absolutePath}"
        connection = DriverManager.getConnection(url)
        
        // Konfigurasi PRAGMA
        connection?.createStatement()?.use { stmt ->
            stmt.execute("PRAGMA foreign_keys = ON;")
            stmt.execute("PRAGMA journal_mode = DELETE;")
            stmt.execute("PRAGMA synchronous = OFF;")
        }
        
        println("[DB] Connected and PRAGMA configured.")
    }

    fun beginTransaction() {
        connection?.autoCommit = false
    }

    fun commitTransaction() {
        connection?.commit()
        connection?.autoCommit = true
    }

    fun rollbackTransaction() {
        connection?.rollback()
        connection?.autoCommit = true
    }

    fun createSchema() {
        connection?.createStatement()?.use { stmt ->
            // Tabel Surah
            stmt.execute("""
                CREATE TABLE surah (
                    number INTEGER PRIMARY KEY CHECK(number BETWEEN 1 AND 114),
                    name TEXT NOT NULL,
                    english_name TEXT NOT NULL,
                    english_name_translation TEXT NOT NULL,
                    number_of_ayahs INTEGER NOT NULL CHECK(number_of_ayahs > 0),
                    revelation_type TEXT NOT NULL CHECK(revelation_type IN ('Meccan', 'Medinan'))
                )
            """.trimIndent())

            // Tabel Ayah (Hapus kolom Juz untuk akurasi v1)
            stmt.execute("""
                CREATE TABLE ayah (
                    surah_number INTEGER NOT NULL,
                    number INTEGER NOT NULL CHECK(number > 0),
                    text_arabic TEXT NOT NULL,
                    text_arabic_plain TEXT NOT NULL,
                    text_latin TEXT NOT NULL,
                    text_indonesia TEXT NOT NULL,
                    PRIMARY KEY (surah_number, number),
                    FOREIGN KEY (surah_number) REFERENCES surah(number) ON DELETE CASCADE
                )
            """.trimIndent())

            // Metadata
            stmt.execute("""
                CREATE TABLE database_metadata (
                    key TEXT PRIMARY KEY,
                    value TEXT NOT NULL
                )
            """.trimIndent())

            // Index
            stmt.execute("CREATE INDEX idx_ayah_surah_number ON ayah(surah_number)")
        }
        println("[DB] Schema created (Juz column removed for v1 accuracy).")
    }

    fun insertSurahs(surahs: List<Map<String, Any?>>) {
        val sql = """
            INSERT INTO surah (number, name, english_name, english_name_translation, number_of_ayahs, revelation_type)
            VALUES (?, ?, ?, ?, ?, ?)
        """.trimIndent()
        
        connection?.prepareStatement(sql)?.use { pstmt ->
            for (surah in surahs) {
                pstmt.setInt(1, surah["number"] as Int)
                pstmt.setString(2, surah["name"] as String)
                pstmt.setString(3, surah["english_name"] as String)
                pstmt.setString(4, surah["english_name_translation"] as String)
                pstmt.setInt(5, surah["number_of_ayahs"] as Int)
                pstmt.setString(6, surah["revelation_type"] as String)
                pstmt.addBatch()
            }
            pstmt.executeBatch()
        }
    }

    fun insertAyahs(ayahs: List<Map<String, Any?>>) {
        val sql = """
            INSERT INTO ayah (surah_number, number, text_arabic, text_arabic_plain, text_latin, text_indonesia)
            VALUES (?, ?, ?, ?, ?, ?)
        """.trimIndent()

        connection?.prepareStatement(sql)?.use { pstmt ->
            for (ayah in ayahs) {
                pstmt.setInt(1, ayah["surah_number"] as Int)
                pstmt.setInt(2, ayah["number"] as Int)
                pstmt.setString(3, ayah["text_arabic"] as String)
                pstmt.setString(4, ayah["text_arabic_plain"] as String)
                pstmt.setString(5, ayah["text_latin"] as String)
                pstmt.setString(6, ayah["text_indonesia"] as String)
                pstmt.addBatch()
            }
            pstmt.executeBatch()
        }
    }

    fun saveMetadata(metadata: Map<String, String>) {
        val sql = "INSERT OR REPLACE INTO database_metadata (key, value) VALUES (?, ?)"
        connection?.prepareStatement(sql)?.use { pstmt ->
            for ((key, value) in metadata) {
                pstmt.setString(1, key)
                pstmt.setString(2, value)
                pstmt.addBatch()
            }
            pstmt.executeBatch()
        }
    }

    fun optimizeAndClose() {
        connection?.createStatement()?.use { stmt ->
            println("[DB] Optimizing database (VACUUM & ANALYZE)...")
            stmt.execute("VACUUM;")
            stmt.execute("ANALYZE;")
        }
        connection?.close()
        println("[DB] Connection closed safely.")
    }

    /**
     * Melakukan validasi integritas data.
     * Mengembalikan true jika valid.
     */
    fun validate(): Boolean {
        connection?.createStatement()?.use { stmt ->
            // 1. Cek Jumlah Surah
            val surahCount = stmt.executeQuery("SELECT COUNT(*) FROM surah").let { 
                if (it.next()) it.getInt(1) else 0 
            }
            if (surahCount != 114) {
                println("[VALIDATION ERROR] Surah count is $surahCount, expected 114.")
                return false
            }

            // 2. Cek Jumlah Ayat
            val ayahCount = stmt.executeQuery("SELECT COUNT(*) FROM ayah").let {
                if (it.next()) it.getInt(1) else 0
            }
            if (ayahCount != 6236) {
                println("[VALIDATION ERROR] Ayah count is $ayahCount, expected 6236.")
                return false
            }

            // 3. Cek Orphan Ayah (Foreign Key Integrity)
            val orphanAyahs = stmt.executeQuery("""
                SELECT COUNT(*) FROM ayah 
                WHERE surah_number NOT IN (SELECT number FROM surah)
            """).let { if (it.next()) it.getInt(1) else 0 }
            
            if (orphanAyahs > 0) {
                println("[VALIDATION ERROR] Found $orphanAyahs orphan ayahs.")
                return false
            }
        }
        return true
    }

    fun deleteDatabaseFile() {
        if (dbFile.exists()) {
            dbFile.delete()
            println("[DB] Corrupt or invalid database file deleted.")
        }
    }
    
    // Fungsi insert sederhana (Tanpa logic mapping API di sini)
    fun getInternalConnection() = connection

    /**
     * Menghitung hash berdasarkan seluruh konten teks Arab di tabel ayah.
     * Ini menjamin isi Al-Quran valid secara tekstual.
     */
    fun calculateContentChecksum(): String {
        val allText = StringBuilder()
        connection?.createStatement()?.use { stmt ->
            val rs = stmt.executeQuery("SELECT text_arabic FROM ayah ORDER BY surah_number, number")
            while (rs.next()) {
                allText.append(rs.getString(1))
            }
        }
        return com.example.hi_quran.generator.util.HashUtil.calculateSHA256(allText.toString())
    }
}
