package com.example.hi_quran.generator

import com.example.hi_quran.generator.api.QuranFetcher
import com.example.hi_quran.generator.constant.AppConstants
import com.example.hi_quran.generator.data.datasource.local.QuranLocalDataSourceImpl
import com.example.hi_quran.generator.data.datasource.remote.QuranRemoteDataSourceImpl
import com.example.hi_quran.generator.data.repository.QuranRepositoryImpl
import com.example.hi_quran.generator.database.DatabaseManager
import com.example.hi_quran.generator.domain.usecase.GenerateQuranDatabaseUseCase
import com.example.hi_quran.generator.report.ReportData
import com.example.hi_quran.generator.report.ReportGenerator
import com.example.hi_quran.generator.util.TimeUtil
import kotlinx.coroutines.runBlocking
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
    // Infrastructure
    val fetcher = QuranFetcher(config)
    val dbManager = DatabaseManager(config)
    val reporter = ReportGenerator(config)

    // Data Layer
    val remoteDataSource = QuranRemoteDataSourceImpl(fetcher)
    val localDataSource = QuranLocalDataSourceImpl(dbManager)
    val repository = QuranRepositoryImpl(remoteDataSource, localDataSource)

    // Domain Layer
    val generateUseCase = GenerateQuranDatabaseUseCase(repository)

    // 1. Fetch Surah List (Initial connection check)
    println("[INFO] Connecting to API: ${config.baseUrl}")
    val initialSurahs = fetcher.fetchSurahList()
    println("[SUCCESS] API Connection verified. ${initialSurahs.size} surahs found.")

    if (config.isDryRun) {
        println("[DRY RUN] Validating first surah data structure...")
        fetcher.fetchSurahDetail(initialSurahs.first().nomor)
        println("[DRY RUN] Data structure is valid. No database created.")
        return
    }

    // 2. Init Database
    dbManager.connect()
    dbManager.createSchema()
    dbManager.beginTransaction()

    val startTime = System.currentTimeMillis()

    try {
        println("[PROCESS] Starting data generation pipeline...")

        val result = generateUseCase(
            params = GenerateQuranDatabaseUseCase.Params(
                dbVersion = config.dbVersion.toString(),
                apiSource = config.baseUrl
            ),
            onProgress = { index, total, surahNum, surahName ->
                val eta = TimeUtil.calculateEta(startTime, index, total)
                val progress = (surahNum.toDouble() / total * 100).toInt()
                print("\r[%3d%%] [%d/%d] Downloading: %-20s | ETA: %s".format(
                    progress, surahNum, total, surahName, TimeUtil.formatDuration(eta)
                ))
            }
        )

        dbManager.commitTransaction()
        println("\n[SUCCESS] All data committed to database.")

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
                totalSurah = result.totalSurahs,
                totalAyah = result.totalAyahs,
                validationResult = "PASSED",
                checksum = checksum,
                finalStatus = "SUCCESS"
            )
        )

    } catch (e: Exception) {
        dbManager.rollbackTransaction()
        dbManager.deleteDatabaseFile()
        throw Exception("Pipeline failed: ${e.message}")
    }
}
