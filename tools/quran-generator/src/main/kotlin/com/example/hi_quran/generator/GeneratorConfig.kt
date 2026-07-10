package com.example.hi_quran.generator

import com.example.hi_quran.generator.constant.AppConstants
import java.io.File
import java.util.Properties

class GeneratorConfig(configPath: String = AppConstants.DEFAULT_CONFIG_PATH) {
    private val properties = Properties()
    
    var isDryRun: Boolean = false
        private set

    init {
        val configFile = File(configPath)
        if (configFile.exists()) {
            configFile.inputStream().use { properties.load(it) }
        } else {
            throw Exception("Configuration file not found at: ${configFile.absolutePath}")
        }
    }

    val baseUrl: String get() = properties.getProperty("api.base_url") ?: "https://equran.id/api/v2/"
    val outputDir: String get() = properties.getProperty("output.dir") ?: "tools/quran-generator/output/"
    val dbName: String get() = properties.getProperty("db.name") ?: "quran.db"
    val dbVersion: Int get() = properties.getProperty("db.version")?.toInt() ?: 1
    val apiTimeout: Long get() = properties.getProperty("api.timeout")?.toLong() ?: 30L
    val maxRetry: Int get() = properties.getProperty("api.max_retry")?.toInt() ?: 3

    fun enableDryRun() {
        this.isDryRun = true
    }
}
