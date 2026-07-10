package com.example.hi_quran.generator.report

import com.example.hi_quran.generator.GeneratorConfig
import com.example.hi_quran.generator.constant.AppConstants
import java.io.File
import java.time.OffsetDateTime

data class ReportData(
    val generatorVersion: String = AppConstants.GENERATOR_VERSION,
    val dbVersion: Int,
    val schemaVersion: Int = 1,
    val apiSource: String,
    val generatedAt: String = OffsetDateTime.now().toString(),
    val totalSurah: Int,
    val totalAyah: Int,
    val validationResult: String,
    val checksum: String,
    val finalStatus: String
)

class ReportGenerator(private val config: GeneratorConfig) {

    fun generateAll(data: ReportData) {
        generateTxtReport(data)
        generateJsonMetadata(data)
    }

    private fun generateTxtReport(data: ReportData) {
        val report = """
            ==================================================
                       HI-QURAN GENERATION REPORT
            ==================================================
            Generator Version : ${data.generatorVersion}
            Database Version  : ${data.dbVersion}
            Schema Version    : ${data.schemaVersion}
            Source API        : ${data.apiSource}
            Generated At      : ${data.generatedAt}
            --------------------------------------------------
            Total Surah       : ${data.totalSurah}
            Total Ayah        : ${data.totalAyah}
            Validation Result : ${data.validationResult}
            Content Checksum  : ${data.checksum}
            --------------------------------------------------
            FINAL STATUS      : ${data.finalStatus}
            ==================================================
        """.trimIndent()

        File(config.outputDir, "generation-report.txt").writeText(report)
        println("[REPORT] generation-report.txt created.")
    }

    private fun generateJsonMetadata(data: ReportData) {
        val json = """
            {
              "db_version": ${data.dbVersion},
              "schema_version": ${data.schemaVersion},
              "generated_at": "${data.generatedAt}",
              "total_surah": ${data.totalSurah},
              "total_ayah": ${data.totalAyah},
              "checksum": "${data.checksum}",
              "generator_version": "${data.generatorVersion}"
            }
        """.trimIndent()

        File(config.outputDir, "metadata.json").writeText(json)
        println("[REPORT] metadata.json created.")
    }
}
