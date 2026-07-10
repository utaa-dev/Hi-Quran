package com.example.hi_quran.generator

import com.example.hi_quran.generator.api.QuranFetcher
import com.example.hi_quran.generator.constant.AppConstants
import com.example.hi_quran.generator.database.DatabaseManager
import com.example.hi_quran.generator.report.ReportData
import com.example.hi_quran.generator.report.ReportGenerator
import com.example.hi_quran.generator.util.ArabicNormalizer
import com.example.hi_quran.generator.util.TimeUtil
import kotlinx.coroutines.runBlocking
import java.time.OffsetDateTime
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    println("========================================")
    println("   Hi-Quran Database Generator v${AppConstants.GENERATOR_VERSION}")
    println("========================================")

    val config = try {
        GeneratorConfig().apply {
            if (args.contains("--dry-run")) enableDryRun()
        }
    } catch (e: Exception) {
        println("[ERROR] ${e.message}")
        exitProcess(1)
    }

    runBlocking {
        try {
            executePipeline(config)
            exitProcess(0)
        } catch (e: Exception) {
            println("\n[FATAL ERROR] ${e.message}")
            e.printStackTrace()
            exitProcess(1)
        }
    }
}

suspend fun executePipeline(config: GeneratorConfig) {
    val fetcher = QuranFetcher(config)
    val dbManager = DatabaseManager(config)
    val reporter = ReportGenerator(config)

    // 1. Fetch Surah List
    println("[INFO] Connecting to API: ${config.baseUrl}")
    val remoteSurahs = fetcher.fetchSurahList()
    println("[SUCCESS] API Connection verified. ${remoteSurahs.size} surahs found.")

    if (config.isDryRun) {
        println("[DRY RUN] Validating first surah data structure...")
        fetcher.fetchSurahDetail(remoteSurahs.first().nomor)
        println("[DRY RUN] Data structure is valid. No database created.")
        return
    }

    // 2. Init Database
    dbManager.connect()
    dbManager.createSchema()
    dbManager.beginTransaction()

    var totalAyahInserted = 0
    val startTime = System.currentTimeMillis()

    try {
        // 3. Insert Surahs
        println("[PROCESS] Mapping surah metadata...")
        val surahMaps = remoteSurahs.map { s ->
            mapOf(
                "number" to s.nomor,
                "name" to s.nama,
                "english_name" to s.namaLatin,
                "english_name_translation" to s.arti,
                "number_of_ayahs" to s.jumlahAyat,
                "revelation_type" to if (s.tempatTurun.equals("Mekah", ignoreCase = true)) "Meccan" else "Medinan"
            )
        }
        dbManager.insertSurahs(surahMaps)

        // 4. Batch Process Ayahs with Progress
        println("[PROCESS] Starting data download and mapping:")
        remoteSurahs.forEachIndexed { index, s ->
            val surahNum = index + 1
            val eta = TimeUtil.calculateEta(startTime, index, remoteSurahs.size)
            val progress = (surahNum.toDouble() / remoteSurahs.size * 100).toInt()
            
            print("\r[%3d%%] [%d/114] Downloading: %-20s | ETA: %s".format(
                progress, surahNum, s.namaLatin, TimeUtil.formatDuration(eta)
            ))

            val detail = fetcher.fetchSurahDetail(s.nomor)
            val ayahMaps = detail.ayat.map { a ->
                mapOf(
                    "surah_number" to s.nomor,
                    "number" to a.nomorAyat,
                    "text_arabic" to a.teksArab,
                    "text_arabic_plain" to ArabicNormalizer.normalize(a.teksArab),
                    "text_latin" to a.teksLatin,
                    "text_indonesia" to a.teksIndonesia
                )
            }
            dbManager.insertAyahs(ayahMaps)
            totalAyahInserted += ayahMaps.size
        }
        
        // 5. Save Metadata
        val metadataMaps = mapOf(
            "version" to config.dbVersion.toString(),
            "generated_at" to OffsetDateTime.now().toString(),
            "api_source" to config.baseUrl,
            "source_license" to "CC BY-SA 4.0",
            "locale" to "id-ID",
            "generated_by" to "Hi-Quran Generator"
        )
        dbManager.saveMetadata(metadataMaps)

        dbManager.commitTransaction()
        println("\n[SUCCESS] All data committed to database.")

    } catch (e: Exception) {
        dbManager.rollbackTransaction()
        dbManager.deleteDatabaseFile()
        throw Exception("Pipeline failed during insertion: ${e.message}")
    }

    // 6. Final Validation & Optimization
    println("[PROCESS] Running final validation...")
    val isValid = dbManager.validate()
    val checksum = dbManager.calculateContentChecksum()
    
    if (!isValid) {
        dbManager.deleteDatabaseFile()
        throw Exception("Database validation failed. Output deleted.")
    }

    dbManager.optimizeAndClose()

    // 7. Generate Reports
    reporter.generateAll(
        ReportData(
            dbVersion = config.dbVersion,
            apiSource = config.baseUrl,
            totalSurah = remoteSurahs.size,
            totalAyah = totalAyahInserted,
            validationResult = "PASSED",
            checksum = checksum,
            finalStatus = "SUCCESS"
        )
    )
}
